package modeloRegistroYequipamiento;

public class RegistroDeModelo {

	private Integer cantidad;
	private Modelo modelo;
	
	public RegistroDeModelo(Integer unaCantidad, Modelo unModelo){
		this.cantidad = unaCantidad;
		this.modelo = unModelo;
	}
	
	// Otros mensajes
	
	/** Devuelve el nombre.
	 * Es lo que lo identifica. */
	public String getNombreDelModelo() {
		return this.getModelo().getNombre();		
	}
	
	// Getters y Setters
	
	public Modelo getModelo() {
		return modelo;
	}
	
	/** Devuelve la cantidad del modelo */
	public Integer getCantidad() {
		return cantidad;
	}
	
	public Float getPrecioDelModelo() {
		return this.modelo.getValorDeVenta();
	}

	public void descontarUnidad() {
		this.cantidad--;
	}
}
