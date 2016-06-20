package fabrica;

import java.util.ArrayList;
import java.util.List;

import concesionaria.Concesionaria;
import excepciones.ExceptionStock;
import modeloRegistroYequipamiento.Modelo;
import planta.Planta;

public class Fabrica {

	private List<Planta> plantas;
	private Concesionaria miConcesionaria;

	public Fabrica(Concesionaria unaConcesionaria){
		this.plantas = new ArrayList<Planta>();
		this.miConcesionaria = unaConcesionaria;
	}
	
	// Otros mensajes
	
	public Integer stock(Modelo modelo) throws ExceptionStock{
		Integer total = 0;
		for(Planta unaPlanta : buscarPlantasConModelo(modelo))
			total += unaPlanta.stock(modelo);
		
		if(total == 0)
			throw new ExceptionStock();
		return total;
	}
	
	public ArrayList<Planta> buscarPlantasConModelo(Modelo modelo){
		ArrayList<Planta> plantasEncontradas = new ArrayList<>();
		
		for(Planta unaPlanta: plantas){
			if (unaPlanta.nombreDeLosModelos().contains(modelo.getNombre()))
				plantasEncontradas.add(unaPlanta);
		}
		return plantasEncontradas;
	}
	
	//El modelo se encuentra en el stock de la concesionaria
	public Planta plantaMasCercanaAConcesionaria(Modelo model){
		Planta retorno = buscarPlantasConModelo(model).get(0);
			
		for(Planta unaPlanta: buscarPlantasConModelo(model)){
			if(miConcesionaria.getCalculadora().calcularDistancia(unaPlanta)    
										<
				miConcesionaria.getCalculadora().calcularDistancia(retorno))
					retorno = unaPlanta;
		}
		return retorno;
	}
	
	public void quitarEjemplar(Modelo model) {
		plantaMasCercanaAConcesionaria(model).quitarEjemplar(model);
	}
}