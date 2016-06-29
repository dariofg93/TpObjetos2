package modeloRegistroYequipamiento;

public class RegistroDeModelo {

	private Integer cantidad;
	private Modelo modelo;
	
	public RegistroDeModelo(Modelo unModelo){
		this.cantidad = 1;
		this.modelo = unModelo;
	}
	
	/** Devuelve el nombre. Es lo que lo identifica. */
	public String getNombreDelModelo() {
		return this.getModelo().getNombre();		
	}
	
	/**
	 * Prop: Descuenta en uno la cantidad que se registra del modelo.
	 * Obs:  Por Invariante no puede haber menos que 1 en la cantidad ejemplares del modelo.
	 */
	public void descontarUnidad() {
		cantidad--;
	}

	//Prop: Suma en uno la cantidad que se registra del modelo.
	public void sumarUnidad() {
		cantidad++;
	}
	
	//Prop: Modifica el precio del modelo que se guarda en el registro.
	public void cambiarPrecio(Float porcent) {
		modelo.cambiarPrecio(porcent);
	}
	
	//Getters:
	public Modelo getModelo() {
		return modelo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public Float getPrecioDelModelo() {
		return this.modelo.getValorDeVenta();
	}
}
