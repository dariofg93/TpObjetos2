package modeloRegistroYequipamiento;

public class Modelo {

	private String nombre;
	@SuppressWarnings("unused")
	private Integer añoDeLanzamiento;
	@SuppressWarnings("unused")
	private Integer cantidadDePuertas;
	@SuppressWarnings("unused")
	private Equipamiento equipamiento;
	private Float valorDelModelo;
	
	public Modelo(String aName, Integer year, Integer doors, Equipamiento equipment, Float value){
		this.nombre = aName;
		this.añoDeLanzamiento = year;
		this.cantidadDePuertas = doors;
		this.equipamiento = equipment;
		this.valorDelModelo = value;
	}

	//Prop: Devuelve el porcentaje pedido por parametro del precio total del modelo.
	public Float porcentajeDelPrecio(Float porcent) {
		return (valorDelModelo * porcent) / 100;
	}

	//Prop: Modifica el precio del modelo.
	public void cambiarPrecio(Float porcent) {
		valorDelModelo += porcentajeDelPrecio(porcent);
	}
	
	//Getters:
	public Float getValorDeVenta(){
		return valorDelModelo;
	}
	public String getNombre() {
		return nombre;
	}
}