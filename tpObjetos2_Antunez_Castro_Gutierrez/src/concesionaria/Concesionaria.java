package concesionaria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import aseguradora.CompaniaAseguradora;
import calculadora.CalculadorDeDistancia;
import cupon.CuponDeAdjudicacion;
import excepciones.ExceptionParticipante;
import excepciones.SinPlanesExcepcion;
import excepciones.SinStockExcepcion;
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
	
	public void setCreador(CuponCreator unCreador) {
		creadorCupon = unCreador;
	}
	
	public void crearPlan(PlanDeAhorro plan){
		planes.add(plan);
	}
	
	public void registrarCliente(Cliente unCliente){
		clientes.add(unCliente);
	}
	
	public void suscribirClienteAPlan(Cliente c, PlanDeAhorro p) throws SinPlanesExcepcion{
		buscarPlan(p).suscribirCliente(c);
	}
	
	public List<PlanDeAhorro> losDiezPlanesConMasSubscriptos() throws SinPlanesExcepcion{
		
		List<PlanDeAhorro> orderedPlans = iniciarRecorridoDePlanes();
		Comparator<PlanDeAhorro> nDisponibles = (p1, p2) -> 
	    	p1.cantidadDeParticipantesDisponibles().compareTo(
	    	p2.cantidadDeParticipantesDisponibles());

	    orderedPlans = orderedPlans.stream()
	    .sorted(nDisponibles.reversed()).limit(10).collect(Collectors.toList());
	    
		return orderedPlans;
	}
	
	public void sortearMovil(PlanDeAhorro plan) throws ExceptionParticipante, SinPlanesExcepcion, SinStockExcepcion{
		Participante winner;
		winner = buscarPlan(plan).elegirGanador();
		emitirCupon(creadorCupon.crearCupon(buscarPlan(plan),winner));	
	}
	
	public void emitirCupon(CuponDeAdjudicacion cupon) throws SinStockExcepcion{
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
	
	public Integer stock(Modelo modelo) throws SinStockExcepcion{
		return miFabrica.stock(modelo);
	}

	public Float montoDelSeguro(Participante p, Modelo modelo) {
		return compañia.montoDelSeguro(p,modelo);
	}
	
	private PlanDeAhorro buscarPlan(PlanDeAhorro p) throws SinPlanesExcepcion {
		PlanDeAhorro planFound = null;
		for(PlanDeAhorro plan: iniciarRecorridoDePlanes()){
			if(plan.getNumeroDeGrupo().equals(p.getNumeroDeGrupo()))
				planFound = plan;
		}
		return planFound;
	}

	private List<PlanDeAhorro> iniciarRecorridoDePlanes() throws SinPlanesExcepcion{
		if(planes.isEmpty())
			throw new SinPlanesExcepcion();
		return planes;
	}
}
