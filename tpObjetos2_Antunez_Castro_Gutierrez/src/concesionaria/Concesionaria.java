package concesionaria;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Matchers;

import aseguradora.Compa�iaAseguradora;
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
	private Compa�iaAseguradora compa�ia;
	private List<CuponDeAdjudicacion> cupones;
	
	public Concesionaria(String lugar, Float ganancia){
		this.direccion = lugar;
		this.calculadora = new CalculadorDeDistancia();
		this.miFabrica = new Fabrica(this);
		this.clientes = new ArrayList<Cliente>();
		this.planes = new ArrayList<PlanDeAhorro>();
		this.gananciaAdministrativa = ganancia;
		this.compa�ia = new Compa�iaAseguradora();
		this.cupones = new ArrayList<CuponDeAdjudicacion>();
	}
	
	public void setCalculadora(CalculadorDeDistancia calc){
		this.calculadora = calc;
	}
	
	public void setCompa�ia(Compa�iaAseguradora comp){
		this.compa�ia = comp;
	}
	
	public void setFabrica(Fabrica f){
		this.miFabrica = f;
	}

	public Float gastoAdministrativos() {
		return gananciaAdministrativa;
	}
	
	public Fabrica getFabrica() {
		return miFabrica;
	}
	
	public List<PlanDeAhorro> getPlanes() {
		return planes;
	}
	
	public void crearPlan(PlanDeAhorro plan){
		planes.add(plan);
	}
	
	public void registrarCliente(Cliente unCliente){
		clientes.add(unCliente);
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
	
	public void sortearMovil(PlanDeAhorro plan)/*throws SinParticipantesException*/{
			CuponDeAdjudicacion cupon = plan.elegirGanador(); //Mejor que devuelva un participante
			emitirCupon(cupon);
	}
	
	public void emitirCupon(CuponDeAdjudicacion cupon){
		miFabrica.quitarEjemplar(cupon.getModelo());
		cupones.add(cupon);
	}
	
	/**
	 * Obs: El flete es igual a $20,50 por Kilometro.
	 */
	public Float gastoDeFlete(Planta unaPlanta){
		return calculadora.calcularDistancia(unaPlanta) * 20.5f;
	}
	
	//Prec.: El cupon existe en la lista de cupones de adjudicacion
	public void registrarPagoDelCupon(CuponDeAdjudicacion cupon){
		cupones.remove(cupon);
	}
	
	public Integer stock(Modelo modelo){
		try{
			return miFabrica.stock(modelo);
		}catch(ExceptionStock arg){
			System.out.println(arg.getMessage());
			return 0;
		}
	}

	public Float montoDelSeguro(Participante p, Modelo modelo) {
		return compa�ia.montoDelSeguro(p,modelo);
	}
}
