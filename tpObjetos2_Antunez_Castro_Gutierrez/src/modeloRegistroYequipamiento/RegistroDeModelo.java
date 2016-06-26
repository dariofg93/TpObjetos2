package modeloRegistroYequipamiento;

public class RegistroDeModelo {

	private Integer cantidad;
	private Modelo modelo;
	
	public RegistroDeModelo(Modelo unModelo){
		this.cantidad = 1;
		this.modelo = unModelo;
	}
	
	/** Devuelve el nombre.
	 * Es lo que lo identifica. */
	public String getNombreDelModelo() {
		return this.getModelo().getNombre();		
	}
	
	public Float getPrecioDelModelo() {
		return this.modelo.getValorDeVenta();
	}
	
	public void descontarUnidad() {
		cantidad--;
	}

	public void sumarUnidad() {
		cantidad++;
	}
	
	public void cambiarPrecio(Float porcent) {
		modelo.cambiarPrecio(porcent);
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	
	/** Devuelve la cantidad del modelo */
	public Integer getCantidad() {
		return cantidad;
	}
}
