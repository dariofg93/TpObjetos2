package concesionaria;

import java.util.ArrayList;
import java.util.List;

import aseguradora.Compa�iaAseguradora;
import calculadora.CalculadorDeDistancia;
import cupon.CuponDeAdjudicacion;
import fabrica.Fabrica;
import modeloRegistroYequipamiento.Modelo;
import persona.Cliente;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;
import planta.Planta;

public class Concesionaria {

	private String direccion;
	private CalculadorDeDistancia calculadora;
	private Fabrica miFabrica;
	private List<Cliente> clientes;
	private List<PlanDeAhorro> planes;
	private Float gananciaAdministrativa;
	private Compa�iaAseguradora compa�ia;
	private List<CuponDeAdjudicacion> cupones;
	
	public Concesionaria(String lugar, Fabrica unaFabrica, Float ganancia){
		this.direccion = lugar;
		this.calculadora = new CalculadorDeDistancia();
		this.miFabrica = unaFabrica;
		this.clientes = new ArrayList<Cliente>();
		this.planes = new ArrayList<PlanDeAhorro>();
		this.gananciaAdministrativa = ganancia;
		this.compa�ia = new Compa�iaAseguradora();
		this.cupones = new ArrayList<CuponDeAdjudicacion>();
	}
	
	public void crearPlan(PlanDeAhorro plan){
		planes.add(plan);
	}
	
	/**
	 * Obs: El flete es igual a $20,50 por Kilometro.
	 */
	public Float gastoDeFlete(Planta unaPlanta){
		return calculadora.calcularDistancia(unaPlanta) * 20.5f;
	}
	
	public Integer stock(Modelo modelo){
		return miFabrica.stock(modelo);
	}
	
	public List<PlanDeAhorro> losDiezPlanesConMasSubscriptos(){
		List<PlanDeAhorro> orderedPlans = new ArrayList<PlanDeAhorro>();
		List<PlanDeAhorro> allPlans = planes;
		Integer repetitions = 0;
		
		while(repetitions<10 && !(allPlans.isEmpty())){
			orderedPlans.add(planConMasSubscriptos());
			
			allPlans.remove(planConMasSubscriptos());
			repetitions++;
		}
		return orderedPlans;
	}
	
	//Precondicion: hay por lo menos un plan en la concesionaria.
	private PlanDeAhorro planConMasSubscriptos(){
		PlanDeAhorro winner = planes.get(0);
		
		for(PlanDeAhorro current: planes){
			if(current.cantidadDeParticipantes() > winner.cantidadDeParticipantes())
				winner = current;
		}
		return winner;
	}
	
	///Obs: Se le agrega un cupon como parametro en vez de un plan de ahorro para generar un cupon porque
	// si es necesario agregarle mas objetos para generar un nuevo cupon hay que modificar el codigo
	// es abierto a extencion pero no cerrado a modificacion.. principio SOLID
	public void adjudicarMovil(PlanDeAhorro plan,CuponDeAdjudicacion cupon){
		quitarEjemplar(plan.getModelo());
		cupones.add(cupon);
	}
	
	public void elegirGanador(PlanDeAhorro plan,CuponDeAdjudicacion cupon){
		plan.elegirGanador();
		adjudicarMovil(plan,cupon);
	}
	
	public CalculadorDeDistancia getCalculadora(){
		return this.calculadora;
	}
	
	//El modelo se encuentra en el stock de la concesionaria
	public Planta plantaMasCercanaConModelo(Modelo model){
		Planta retorno = miFabrica.getPlantas().get(0);
		
		for(Planta unaPlanta: miFabrica.buscarPlantasConModelo(model)){
			if(calculadora.calcularDistancia(unaPlanta)     <
					calculadora.calcularDistancia(retorno))
				retorno = unaPlanta;
		}
		return retorno;
	}
	
	public void quitarEjemplar(Modelo model){
		plantaMasCercanaConModelo(model).quitarEjemplar(model);
	}

	public Float gastoAdministrativos() {
		return gananciaAdministrativa;
	}

	public Float montoDelSeguro(Participante p, Modelo modelo) {
		return compa�ia.montoDelSeguro(p,modelo);
	}
}
