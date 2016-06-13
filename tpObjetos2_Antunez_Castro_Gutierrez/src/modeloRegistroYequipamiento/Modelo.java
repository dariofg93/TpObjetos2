package modeloRegistroYequipamiento;

public class Modelo {

	private String nombre;
	private Integer añoDeLanzamiento;
	private Integer cantidadDePuertas;
	private Equipamiento equipamiento;
	private Float valorDelModelo;
	private Float valorDeVenta;
	
	public Modelo(String aName, Integer year, Integer doors, 
					Equipamiento equipment, Float value){
		
		this.setNombre(aName);
		this.setAñoDeLanzamiento(year);
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

	public Integer getAñoDeLanzamiento() {
		return añoDeLanzamiento;
	}

	public void setAñoDeLanzamiento(Integer añoDeLanzamiento) {
		this.añoDeLanzamiento = añoDeLanzamiento;
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
