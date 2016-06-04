package situacionEconomicoProductiva;

import java.util.Observable;
import java.util.Observer;

public class Inflacion extends Observable{

	private Integer porcentaje;
	private Observer observador;
	
	public Inflacion(Integer aPercent) {
		this.porcentaje = aPercent;
	}
	
	@Override
	public void addObserver(Observer aObserver){
		this.observador = aObserver;
	}
	
	public void setPorcentaje(Integer aPercent){
		this.porcentaje = aPercent;
		if(observador != null)
			observador.update(this, getPorcentaje());
	}
	
	public Integer getPorcentaje(){
		return porcentaje;
	}
}
