package modeloRegistroYequipamiento;

public class Modelo {

	private String nombre;
	private Integer a�oDeLanzamiento;
	private Integer cantidadDePuertas;
	private Equipamiento equipamiento;
	private Float valorDelModelo;
	private Float valorDeVenta;
	
	public Modelo(String aName, Integer year, Integer doors, 
					Equipamiento equipment, Float value){
		
		this.setNombre(aName);
		this.setA�oDeLanzamiento(year);
		this.setCantidadDePuertas(doors);
		this.setEquipamiento(equipment);
		this.valorDelModelo = value;
	}

	// Otros mensajes

	// Getters y Setters
	
	private void setNuevoValor(Float nuevoValor) {
		this.valorDeVenta = nuevoValor;
	}
	
	public Float getValorDeVenta(){
		return valorDeVenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getA�oDeLanzamiento() {
		return a�oDeLanzamiento;
	}

	public void setA�oDeLanzamiento(Integer a�oDeLanzamiento) {
		this.a�oDeLanzamiento = a�oDeLanzamiento;
	}

	public Integer getCantidadDePuertas() {
		return cantidadDePuertas;
	}

	public void setCantidadDePuertas(Integer cantidadDePuertas) {
		this.cantidadDePuertas = cantidadDePuertas;
	}

	public Equipamiento getEquipamiento() {
		return equipamiento;
	}

	public void setEquipamiento(Equipamiento equipamiento) {
		this.equipamiento = equipamiento;
	}

	public Float porcentajeDelModelo(Integer porcent) {
		return (valorDelModelo * porcent) / 100;
	}
}
