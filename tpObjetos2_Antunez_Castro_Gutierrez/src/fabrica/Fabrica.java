package fabrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		List<Planta> plantas = plantasConModelo(modelo);
		
		if(plantas.size()==0)
			throw new ExceptionStock();
		for(Planta unaPlanta : plantas)
			total += unaPlanta.stock(modelo);
	
		return total;
	}
	
	public ArrayList<Planta> plantasConModelo(Modelo modelo){
		ArrayList<Planta> plantasEncontradas = new ArrayList<>();
		
		for(Planta unaPlanta: plantas){
			if (unaPlanta.nombreDeLosModelos().contains(modelo.getNombre()))
				plantasEncontradas.add(unaPlanta);
		}
		return plantasEncontradas;
	}
	
	//Dado un modelo, devuelve la planta mas cercana a la concesionaria que tenga el modelo
	public Planta plantaMasCercanaAConcesionaria(Modelo model){
		Planta retorno = plantasConModelo(model).get(0);
			
		for(Planta unaPlanta: plantasConModelo(model)){
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
	
	public void sumarEjemplar(Modelo model) {
		cualquierPlanta().sumarEjemplar(model);
	}

	public Planta cualquierPlanta() {
		Random  rnd = new Random();
		int winner = (int)(rnd.nextDouble() * (plantas.size()-1));
		return plantas.get(winner);
	}
}