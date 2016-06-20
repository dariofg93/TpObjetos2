package concesionaria;

import java.util.ArrayList;
import java.util.List;

import aseguradora.CompañiaAseguradora;
import calculadora.CalculadorDeDistancia;
import cupon.CuponDeAdjudicacion;
import excepciones.ExceptionStock;
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
	private CompañiaAseguradora compañia;
	private List<CuponDeAdjudicacion> cupones;
	
	public Concesionaria(String lugar, Fabrica unaFabrica, Float ganancia){
		this.direccion = lugar;
		this.calculadora = new CalculadorDeDistancia();
		this.miFabrica = unaFabrica;
		this.clientes = new ArrayList<Cliente>();
		this.planes = new ArrayList<PlanDeAhorro>();
		this.gananciaAdministrativa = ganancia;
		this.compañia = new CompañiaAseguradora();
		this.cupones = new ArrayList<CuponDeAdjudicacion>();
	}
	
	public void setCalculadora(CalculadorDeDistancia calc){
		this.calculadora = calc;
	}
	
	public void setCompañia(CompañiaAseguradora comp){
		this.compañia = comp;
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
		Integer total = 0;
		try{
			total = miFabrica.stock(modelo);
		}
		catch(ExceptionStock arg){
			System.out.println(arg);
		}
		return total;
	}
	
	public List<PlanDeAhorro> losDiezPlanesConMasSubscriptos(){
		List<PlanDeAhorro> orderedPlans = new ArrayList<PlanDeAhorro>();
		List<PlanDeAhorro> allPlans = planes;
		Integer repetitions = 0;
		
		while(repetitions<10 && !(allPlans.isEmpty())){
			PlanDeAhorro nextPlan = planConMasSubscriptos(allPlans);
			orderedPlans.add(nextPlan);
			
			allPlans.remove(nextPlan);
			repetitions++;
		}
		return orderedPlans;
	}
	
	//Precondicion: hay por lo menos un plan en la concesionaria.
	private PlanDeAhorro planConMasSubscriptos(List<PlanDeAhorro> plans){
		PlanDeAhorro winner = planes.get(0);
		
		for(PlanDeAhorro current: plans){
			if(current.cantidadDeParticipantes() > winner.cantidadDeParticipantes())
				winner = current;
		}
		return winner;
	}
	
	//Reveer
	public void adjudicarMovil(PlanDeAhorro plan,CuponDeAdjudicacion cupon){
		quitarEjemplar(plan.getModelo());
		cupones.add(cupon);
	}
	
	public void elegirGanador(PlanDeAhorro plan,CuponDeAdjudicacion cupon) throws SinParticipantesException{
		if(hayStock(plan.getModelo())){
			plan.elegirGanador(); 
			adjudicarMovil(plan,cupon);
		}
	}
	
	public Boolean hayStock(Modelo modelo) {
		return stock(modelo)>=1;
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
		return compañia.montoDelSeguro(p,modelo);
	}
}
