package modeloRegistroYequipamiento;

import java.util.Observable;
import java.util.Observer;

import situacionEconomicoProductiva.Inflacion;

public class Modelo implements Observer{

	private String nombre;
	private Integer añoDeLanzamiento;
	private Integer cantidadDePuertas;
	private Equipamiento equipamiento;
	private Float valorDelModelo;
	private Float valorDeVenta;
	
	public Modelo(String aName, Integer year, Integer doors, 
					Equipamiento equipment, Float value, Inflacion economy){
		
		this.setNombre(aName);
		this.setAñoDeLanzamiento(year);
		this.setCantidadDePuertas(doors);
		this.setEquipamiento(equipment);
		this.valorDelModelo = value;
		
		economy.addObserver(this);
		this.valorDeVenta = value * economy.getPorcentaje();
	}

	// Otros mensajes
	
	@Override
	public void update(Observable o, Object arg) {
		setNuevoValor(valorDelModelo * (Integer) arg);
	}

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
}
