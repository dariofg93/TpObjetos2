package persona;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Days;

import comprobantes.ComprobanteDePago;
import excepciones.SinStockExcepcion;
import inicializadores.ComprobanteCreator;
import inicializadores.CuponCreator;
import planDeAhorro.PlanDeAhorro;

public class Participante{
	
	private ArrayList<ComprobanteDePago> comprobantes;
	private Boolean disponible;
	private DateTime fechaInscripcion;
	private Cliente cliente;
	private PlanDeAhorro plan;
	private ComprobanteCreator creadorComprobante;
	private CuponCreator creadorCupon;
	
	public Participante(Cliente unCliente,PlanDeAhorro unPlan) {
		this.comprobantes = new ArrayList<>();
		this.disponible = true;
		this.cliente = unCliente;
		this.fechaInscripcion = new DateTime();
		this.plan = unPlan;
		this.creadorComprobante = new ComprobanteCreator();
		this.creadorCupon = new CuponCreator();
	}

	/** Retorna la cantidad de cuotas que lleva pagando. */
	public Integer cuotasPagas() {
		return comprobantes.size();
	}

	/** Devuelve true si el Participante esta disponible para un sorteo. */
	public Boolean estaDisponible() {
		return disponible;
	}

	/** Se le adjudica un vehiculo, y ya no participa mas de los sorteos. */
	public void fuiAdjudicado() {
		disponible = false;
	}

	/** Obtiene sus datos personales como Cliente de la Consecionaria. */
	public Cliente getCliente() {
		return cliente;
	}
	
	/** Obtiene la fecha de nacimiento. */
	public DateTime getFecNac(){
		//System.out.println(cliente.getFecNac());
		return cliente.getFecNac();
	}
	
	/** Obtiene la fecha de inscripcion al Plan. */
	public DateTime getFechaDeInscripcion(){
		return fechaInscripcion;
	}
	
	/** Paga una cuota del Plan. 
	 * @throws SinStockExcepcion */
	public void pagarCuota() throws SinStockExcepcion{
		Integer cuotaPaga = cuotasPagas()+1;
		
		if(cuotaPaga==plan.getCuotas()){
			if(estaDisponible())
				plan.getConcesionaria().emitirCupon(creadorCupon.crearCupon(plan, this));
			plan.dessuscribirParticipante(this);
		}else{
			comprobantes.add(creadorComprobante.crearComprobante(cuotaPaga,plan,this));
		}	
	}

	/** Devuelve la edad del participante. */
	public Integer edad(){
		DateTime fechaActual = new DateTime();
		
		return Days.daysBetween(getFecNac().toLocalDate(),
				fechaActual.toLocalDate()).getDays()/364;
	}
	
	/** Devuelve la cantidad de años que pasaron desde que se inscribio.
	 *  Observacion: su implementacion es similar al de edad() */
	public Integer tiempoDesdeInscripcion(){
		DateTime fechaActual = new DateTime();
	    
	    return Days.daysBetween(getFechaDeInscripcion().toLocalDate(),
	    						fechaActual.toLocalDate()).getDays()/364;
	}

	//Setter:
	public void setCreadorCupon(CuponCreator unCreador) {
		creadorCupon = unCreador;
	}
}
