package fabrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import concesionaria.Concesionaria;
import excepciones.SinStockExcepcion;
import modeloRegistroYequipamiento.Modelo;
import planta.Planta;

public class Fabrica {

	private List<Planta> plantas;
	private Concesionaria miConcesionaria;

	public Fabrica(Concesionaria unaConcesionaria){
		this.plantas = new ArrayList<Planta>();
		this.miConcesionaria = unaConcesionaria;
	}
	
	public List<Planta> getPlantas() {
		return plantas;
	}
	
	public void inaugurarPlanta(Planta unaPlanta){
		plantas.add(unaPlanta);
	}
	
	public List<Planta> plantasConModelo(Modelo modelo) throws SinStockExcepcion{
		List<Planta> plantasEncontradas = new ArrayList<>();
		
		for(Planta unaPlanta: getPlantas()){
			if (unaPlanta.nombreDeLosModelos().contains(modelo.getNombre()))
				plantasEncontradas.add(unaPlanta);
		}
		if(plantasEncontradas.size()==0)
			throw new SinStockExcepcion();
		
		return plantasEncontradas;
	}
	
	public Integer stock(Modelo modelo) throws SinStockExcepcion{
		Integer total = 0;
		List<Planta> plantasEncontradas = plantasConModelo(modelo);
		
		for(Planta unaPlanta : plantasEncontradas)
			total += unaPlanta.stock(modelo);
	
		return total;
	}

	//Dado un modelo, devuelve la planta mas cercana a la concesionaria que tenga el modelo
	public Planta plantaMasCercanaAConcesionaria(Modelo model) throws SinStockExcepcion{
		Planta retorno = plantasConModelo(model).get(0);
			
		for(Planta unaPlanta: plantasConModelo(model)){
			if(miConcesionaria.gastoDeFlete(unaPlanta)    
								<
				miConcesionaria.gastoDeFlete(retorno))
					retorno = unaPlanta;
		}
		return retorno;
	}
	
	public void quitarEjemplar(Modelo model) throws SinStockExcepcion {
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
	
	public void cambiarValorMovil(Float porcent, Modelo model) throws SinStockExcepcion{
		for(Planta p: plantasConModelo(model))
			p.buscarRegistroDelModelo(model).cambiarPrecio(porcent);
	}
	
	public void cambiarValorTodos(Float porcent){
		for(Planta p: plantas)
			p.cambiarValorDeTodos(porcent);
	}
}