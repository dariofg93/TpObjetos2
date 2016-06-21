package modeloRegistroYequipamiento;

public class Modelo {

	private String nombre;
	private Integer añoDeLanzamiento;
	private Integer cantidadDePuertas;
	private Equipamiento equipamiento;
	private Float valorDelModelo;
	
	public Modelo(String aName, Integer year, Integer doors, Equipamiento equipment, Float value){
		this.nombre = aName;
		this.añoDeLanzamiento = year;
		this.cantidadDePuertas = doors;
		this.equipamiento = equipment;
		this.valorDelModelo = value;
	}

	public Float getValorDeVenta(){
		return valorDelModelo;
	}

	public String getNombre() {
		return nombre;
	}

	public Float porcentajeDelModelo(Integer porcent) {
		return (valorDelModelo * porcent) / 100;
	}

	public void cambiarPrecio(Float porcent) {
		valorDelModelo += ((valorDelModelo * porcent)/100);
	}
}