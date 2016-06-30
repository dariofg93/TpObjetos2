package concesionaria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import aseguradora.CompaniaAseguradora;
import calculadora.CalculadorDeDistancia;
import cupon.CuponDeAdjudicacion;
import excepciones.SinParticipantesExcepcion;
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
	
	//Prop: Agrega un plan de ahorro a su lista de planes de ahorro.
	public void crearPlan(PlanDeAhorro plan){
		planes.add(plan);
	}
	
	//Prop: Agrega un nuevo cliente a su lista de clientes.
	public void registrarCliente(Cliente unCliente){
		clientes.add(unCliente);
	}
	
	/**
	 * Prop: Suscribe un cliente dado a un plan de ahorros dado.
	 * Prec: El plan de ahorro dado por parametro debe existir en la lista de los planes
	 *		 de la concesionaria, sino arroja un SinPlanesExcepcion.
	 */
	public void suscribirClienteAPlan(Cliente c, PlanDeAhorro p) throws SinPlanesExcepcion{
		buscarPlan(p).suscribirCliente(c);
	}
	
	/**
	 * Prop: Devuelve los 10 planes con mas suscriptos disponibles de la concesionaria
	 * Prec: Debe haber almenos 1 plan en la concesionaria
	 */
	public List<PlanDeAhorro> losDiezPlanesConMasSubscriptos() throws SinPlanesExcepcion{
		
		Comparator<PlanDeAhorro> nDisponibles = (p1, p2) -> 
	    	p1.cantidadDeParticipantesDisponibles().compareTo(
	    	p2.cantidadDeParticipantesDisponibles());

	    List<PlanDeAhorro> orderedPlans = iniciarRecorridoDePlanes()
	    	.stream()
	    	.sorted(nDisponibles.reversed())
	    	.limit(10)
	    	.collect(Collectors.toList());
	   
		return orderedPlans;
	}
	
	/**
	 * Prop: Elige un participante del plan dado para adjudicarlo y 
	 * 		 genera un cupon de adjudicacion para el cliente adjudicado.
	 * Prec: Si no cumple algunas reglas tirará regla alguna de estas excepciones:
	 * @throws SinParticipantesExcepcion: Si no hay participantes disponibles en el plan dado.
	 * @throws SinPlanesExcepcion: Si el plan dado no existe en la lista de planes.
	 * @throws SinStockExcepcion: Si no hay stock del vehiculo que tiene el plan dado.
	 */
	public void sortearMovil(PlanDeAhorro plan) throws SinParticipantesExcepcion, SinPlanesExcepcion, SinStockExcepcion{
		Participante winner;
		winner = buscarPlan(plan).elegirGanador();
		emitirCupon(creadorCupon.crearCupon(buscarPlan(plan),winner));	
	}
	
	/**
	 * Prop: Agrega un nuevo cupon a la lista de cupones y le quita 1 al stock
	 * 		 de modelos del modelo del cupon dado por parametro.
	 * Prec: Debe haber almenos 1 de stock del modelo del cupon en la concesionaria. 
	 */
	public void emitirCupon(CuponDeAdjudicacion cupon) throws SinStockExcepcion{
		miFabrica.quitarEjemplar(cupon.getModelo());
		cupones.add(cupon);
	}
	
	/**
	 * Prop: Saca el cupon dado por parametro de la lista de cupones de la concesionaria.
	 * Prec: El cupon existe en la lista de cupones de adjudicacion.
	 */
	public void registrarPagoDelCupon(CuponDeAdjudicacion cupon){
		cupones.remove(cupon);
	}
	
	/**
	 * Prop: Calcula los gastos del flete desde una planta a la concesionaria.
	 * Obs:  El flete es igual a $20,50 por Kilometro.
	 */
	public Float gastoDeFlete(Planta unaPlanta){
		return calculadora.calcularDistancia(unaPlanta) * 20.5f;
	}
	
	/**
	 * Prop: Retorna el stock de un modelo en la concesionaria.
	 * Prec: Debe haber un stock de almenos 1 en la concesionaria, sino se
	 * 		 lanzará un SinStockExcepcion. 
	 */
	public Integer stock(Modelo modelo) throws SinStockExcepcion{
		return miFabrica.stock(modelo);
	}

	/**
	 * Prop: Retorna el costo que impone la compañia aseguradora dados un
	 * 		 participante y un modelo. 
	 */
	public Float montoDelSeguro(Participante p, Modelo modelo) {
		return compañia.montoDelSeguro(p,modelo);
	}
	
	/**
	 * Prop: Busca en la lista de planes de la concesionaria el plan dado por parametro
	 * 		 identificandolos por su numero de grupo.
	 * Prec: El plan debe existir en la concesionaria.
	 */
	private PlanDeAhorro buscarPlan(PlanDeAhorro p) throws SinPlanesExcepcion {
		
		List<PlanDeAhorro> planFound = iniciarRecorridoDePlanes()
			.stream()
			.filter(p1 -> p1.getNumeroDeGrupo().equals(p.getNumeroDeGrupo()))
			.collect(Collectors.toList());
		
		return planFound.get(0);
	}

	/**
	 * Prop: Retorna la lista de planes de la concesionaria.
	 * Prec: Debe haber almenos 1 plan en la concesionaria.
	 */
	private List<PlanDeAhorro> iniciarRecorridoDePlanes() throws SinPlanesExcepcion{
		if(planes.isEmpty())
			throw new SinPlanesExcepcion();
		return planes;
	}
	
	//Getters and setters:
	public void setCalculadora(CalculadorDeDistancia calc){
		this.calculadora = calc;
	}
	public void setCompañia(CompaniaAseguradora comp){
		this.compañia = comp;
	}
	public void setFabrica(Fabrica f){
		this.miFabrica = f;
	}
	public void setCreador(CuponCreator unCreador) {
		creadorCupon = unCreador;
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
}
