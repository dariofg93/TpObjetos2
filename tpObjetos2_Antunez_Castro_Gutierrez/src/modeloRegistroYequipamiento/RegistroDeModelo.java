package modeloRegistroYequipamiento;

public class RegistroDeModelo {

	private Integer cantidad;
	private Modelo modelo;
	
	public RegistroDeModelo(Integer unaCantidad, Modelo unasCaracteristicas){
		this.setCantidad(unaCantidad);
		this.setCaracteristicas(modelo);
	}
	
	// Otros mensajes
	
	/** Devuelve el nombre.
	 * Es lo que lo identifica. */
	public String getNombreDelModelo() {
		return this.getCaracteristicas().getNombre();		
	}
	
	// Getters y Setters
	
	public Modelo getCaracteristicas() {
		return modelo;
	}
	
	public void setCaracteristicas(Modelo caracteristicas) {
		this.modelo = caracteristicas;
	}
	
	/** Devuelve la cantidad del modelo */
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Float getPrecioDelModelo() {
		return this.modelo.getValorDeVenta();
	}

	public void descontarUnidad() {
		this.cantidad--;
	}
}
