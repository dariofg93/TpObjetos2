package concesionaria;

import java.util.ArrayList;
import java.util.List;

import aseguradora.CompaniaAseguradora;
import calculadora.CalculadorDeDistancia;
import cupon.CuponDeAdjudicacion;
import excepciones.ExceptionParticipante;
import excepciones.ExceptionStock;
import fabrica.Fabrica;
import inicializadores.CuponCreator;
import modeloRegistroYequipamiento.Modelo;
import persona.Cliente;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;
import planta.Planta;

public class Concesionaria {

	@SuppressWarnings("unused")
	private String direccion;
	private CalculadorDeDistancia calculadora;
	private Fabrica miFabrica;
	private List<Cliente> clientes;
	private List<PlanDeAhorro> planes;
	private Float gananciaAdministrativa;
	private CompaniaAseguradora compañia;
	private List<CuponDeAdjudicacion> cupones;
	private CuponCreator creadorCupon;
	
	public Concesionaria(String lugar, Float ganancia){
		this.direccion = lugar;
		this.calculadora = new CalculadorDeDistancia();
		this.miFabrica = new Fabrica(this);
		this.clientes = new ArrayList<Cliente>();
		this.planes = new ArrayList<PlanDeAhorro>();
		this.gananciaAdministrativa = ganancia;
		this.compañia = new CompaniaAseguradora();
		this.cupones = new ArrayList<CuponDeAdjudicacion>();
		this.creadorCupon = new CuponCreator();
	}
	
	public void setCalculadora(CalculadorDeDistancia calc){
		this.calculadora = calc;
	}
	
	public void setCompañia(CompaniaAseguradora comp){
		this.compañia = comp;
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
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public List<CuponDeAdjudicacion> getCupones() {
		return cupones;
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
	
	public void sortearMovil(PlanDeAhorro plan){
		Participante winner;
		try{
			winner = plan.elegirGanador();
			emitirCupon(creadorCupon.crearCupon(plan,winner));	
		}catch(ExceptionParticipante arg){
			System.out.println(arg.getMessage());
		}		
	}
	
	public void emitirCupon(CuponDeAdjudicacion cupon){
		miFabrica.quitarEjemplar(cupon.getModelo());
		cupones.add(cupon);
	}
	
	//Prec.: El cupon existe en la lista de cupones de adjudicacion
	public void registrarPagoDelCupon(CuponDeAdjudicacion cupon){
		cupones.remove(cupon);
	}
	
	/**
	 * Obs: El flete es igual a $20,50 por Kilometro.
	 */
	public Float gastoDeFlete(Planta unaPlanta){
		return calculadora.calcularDistancia(unaPlanta) * 20.5f;
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
		return compañia.montoDelSeguro(p,modelo);
	}
	
	public void suscribirClienteAPlan(Cliente c, PlanDeAhorro p){
		p.suscribirCliente(c);
	}
}
