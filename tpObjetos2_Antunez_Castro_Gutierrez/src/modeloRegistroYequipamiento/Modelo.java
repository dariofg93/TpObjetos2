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
	private Float valorDeVenta;
	
	public Modelo(String aName, Integer year, Integer doors, 
					Equipamiento equipment, Float value){
		
		this.nombre = aName;
		this.añoDeLanzamiento = year;
		this.cantidadDePuertas = doors;
		this.equipamiento = equipment;
		this.valorDelModelo = value;
		this.valorDeVenta = value;
	}

	public Float getValorDeVenta(){
		return valorDeVenta;
	}

	public String getNombre() {
		return nombre;
	}

	public Float porcentajeDelModelo(Integer porcent) {
		return (valorDelModelo * porcent) / 100;
	}
}
