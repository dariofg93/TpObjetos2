package persona;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import comprobantes.ComprobanteDePago;

public class Participante{
	
	private ArrayList<ComprobanteDePago> comprobantes;
	private Boolean disponible;
	private Date fechaInscripcion;
	private Cliente cliente;
	
	public Participante(Cliente unCliente) {
		this.comprobantes = new ArrayList<>();
		this.disponible = true;
		this.cliente = unCliente;
		this.fechaInscripcion = new Date();
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
	public Date getFecNac(){
		//System.out.println(cliente.getFecNac());
		return cliente.getFecNac();
	}
	
	/** Obtiene la fecha de inscripcion al Plan. */
	public Date getFechaDeInscripcion(){
		return fechaInscripcion;
	}
	
	/** Paga una cuota del Plan. */
	public void agregarCuota(ComprobanteDePago comp){
		comprobantes.add(comp);
	}

	/** Devuelve la edad del participante. */
	public Integer edad()
	{
		Calendar fechaNac = Calendar.getInstance();
		fechaNac.setTime(this.getFecNac());
		Calendar fechaActual = Calendar.getInstance();
		
		// Cálculo de las diferencias.
	    int anios = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
	    int meses = fechaActual.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
	    int dias = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
	 
	    // Hay que comprobar si el día de su cumpleaños es posterior
	    // a la fecha actual, para restar 1 a la diferencia de años,
	    // porque en ese caso, aun no es su cumpleaños.
	 
	    if(meses != 0 // Todavia no es el mes de su cumpleaños
	       || (meses == 0 && dias != 0)) { // o es el mes pero no llego el día.
	 
	        anios--;
	    }
	    
//	    System.out.println(fechaNac);
//	    System.out.println(fechaActual);
	    
//	    System.out.println(anios);
	    return anios;
	}
}
