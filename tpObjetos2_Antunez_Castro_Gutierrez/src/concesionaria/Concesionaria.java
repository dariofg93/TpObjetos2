package concesionaria;

import java.util.ArrayList;
import java.util.List;

import calculadora.CalculadorDeDistancia;
import cliente.Cliente;
import fabrica.Fabrica;
import modeloRegistroYequipamiento.Modelo;
import planDeAhorro.PlanDeAhorro;
import planta.Planta;

public class Concesionaria {

	private String direccion;
	private CalculadorDeDistancia calculadora;
	private Fabrica miFabrica;
	private List<Cliente> clientes;
	private List<PlanDeAhorro> planes;
	
	public Concesionaria(String lugar, Fabrica unaFabrica){
		this.direccion = lugar;
		this.calculadora = new CalculadorDeDistancia();
		this.miFabrica = unaFabrica;
		this.clientes = new ArrayList<Cliente>();
		this.planes = new ArrayList<PlanDeAhorro>();
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
	
	///REACEEEEEEEEER
	public void adjudicarMovil(PlanDeAhorro plan){
		miFabrica.quitarEjemplar(plan.getModelo());
		//..la implementacion sigue, depende del comportamiento de las otras clases...
	}
	
	public CalculadorDeDistancia getCalculadora(){
		return this.calculadora;
	}
}
