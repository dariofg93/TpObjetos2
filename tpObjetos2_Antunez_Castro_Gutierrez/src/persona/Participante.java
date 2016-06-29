package persona;

import java.util.ArrayList;

import org.joda.time.DateTime;

import comprobantes.ComprobanteDePago;

public class Participante{
	
	private ArrayList<ComprobanteDePago> comprobantes;
	private Boolean disponible;
	private DateTime fechaInscripcion;
	private Cliente cliente;
	
	public Participante(Cliente unCliente) {
		this.comprobantes = new ArrayList<>();
		this.disponible = true;
		this.cliente = unCliente;
		this.fechaInscripcion = new DateTime();
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
	
	/** Paga una cuota del Plan. */
	public void agregarCuota(ComprobanteDePago comp){
		comprobantes.add(comp);
	}

	/** Devuelve la edad del participante. */
	public Integer edad(){
		DateTime fechaActual = new DateTime();
		
		// C�lculo de las diferencias.
	    int anios = fechaActual.getYear() - this.getFecNac().getYear();
	    int meses = fechaActual.getMonthOfYear() - this.getFecNac().getMonthOfYear();
	    int dias = fechaActual.getDayOfMonth() - this.getFecNac().getDayOfMonth();
	 
	    // Hay que comprobar si el d�a de su cumplea�os es posterior
	    // a la fecha actual, para restar 1 a la diferencia de a�os,
	    // porque en ese caso, aun no es su cumplea�os.
	 
	 // Todavia no es el mes de su cumplea�os o es el mes pero no llego el d�a.
	    if	(meses != 0 || (meses == 0 && dias != 0)) { 
	       anios--;
	    }
	    
	    return anios;
	}
	
	/** Devuelve la cantidad de a�os que pasaron desde que se inscribio.
	 *  Observacion: su implementacion es similar al de edad() */
	public Integer tiempoDesdeInscripcion(){
		DateTime fechaActual = new DateTime();
		
	    int anios = fechaActual.getYear() - this.getFechaDeInscripcion().getYear();
	    int meses = fechaActual.getMonthOfYear() - this.getFechaDeInscripcion().getMonthOfYear();
	    int dias = fechaActual.getDayOfMonth() - this.getFechaDeInscripcion().getDayOfMonth();
	 
	    if(meses != 0 || (meses == 0 && dias != 0)) { 
	        //anios--;
	    }
	    
	    return anios;
	}
	
}
