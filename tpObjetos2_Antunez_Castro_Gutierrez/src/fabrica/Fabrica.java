package fabrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
	
	//Prop: Agrega una planta a la lista de plantas de la fabrica
	public void inaugurarPlanta(Planta unaPlanta){
		plantas.add(unaPlanta);
	}
	
	/**
	 * Prop: Devuelve las plantas que tenga un stock de almenos 1 entre sus modelos.
	 * Prec: Debe haber almenos 1 planta con un ejemplar del modelo en la fabrica.
	 */
	public List<Planta> plantasConModelo(Modelo modelo) throws SinStockExcepcion{
	
		List<Planta> plantasEncontradas = getPlantas()
			.stream()
			.filter(p-> p.nombreDeLosModelos().contains(modelo.getNombre()))
			.collect(Collectors.toList());
				
		if(plantasEncontradas.size()==0)
			throw new SinStockExcepcion();
		
		return plantasEncontradas;
	}
	
	/**
	 * Prop: Devuelve el stock del modelo, sumando el stock de todas las plantas.
	 * Prec: Debe haber almenos 1 planta con un ejemplar del modelo en la fabrica.
	 * @throws SinStockExcepcion 
	 */
	public Integer stock(Modelo modelo) throws SinStockExcepcion{
		List<Planta> plantasEncontradas = plantasConModelo(modelo);
		
		Integer total = plantasEncontradas
			.stream()
			.mapToInt(p-> p.stock(modelo))
			.sum();
		
		return total;
	}

	/**
	 * Prop: Dado un modelo, devuelve la planta mas cercana a la concesionaria que tenga el modelo.
	 * Prec: Debe haber almenos 1 planta con un ejemplar del modelo en la fabrica.
	 */
	public Planta plantaMasCercanaAConcesionaria(Modelo model) throws SinStockExcepcion{

		Planta plantaCercana = plantasConModelo(model)
			.stream()
			.min((Planta p1, Planta p2)-> miConcesionaria.gastoDeFlete(p1).
				compareTo(miConcesionaria.gastoDeFlete(p2)))
			.get();
		
		return plantaCercana;
	}
	
	/**
	 * Prop: Dado un modelo, quita un ejemplar de la planta mas cercana con el modelo
	 * 		 a la concesionaria 
	 * Prec: Debe haber almenos 1 planta con un ejemplar del modelo en la fabrica.
	 */
	public void quitarEjemplar(Modelo model) throws SinStockExcepcion {
		plantaMasCercanaAConcesionaria(model).quitarEjemplar(model);
	}
	
	//Prop: Dado un modelo, agrega un ejemplar en cualquier planta de la fabrica.
	public void sumarEjemplar(Modelo model) {
		cualquierPlanta().sumarEjemplar(model);
	}

	/**
	 * Prop: Devuelve una planta de la lista de plantas de la fabrica.
	 * Prec: Debe haber almenos 1 planta inaugurada en la fabrica.
	 */
	private Planta cualquierPlanta() {
		Random  rnd = new Random();
		int winner = (int)(rnd.nextDouble() * (plantas.size()-1));
		return plantas.get(winner);
	}
	
	/**
	 * Prop: Dado un modelo y un porcentaje(que representa cual es la suba o baja del 
	 * 		 precio del modelo), actualiza el valor del modelo dado en todas las plantas
	 *  	 de la fabrica.
	 * Prec: Debe haber almenos 1 planta con un ejemplar del modelo en la fabrica.
	 */
	public void cambiarValorMovil(Float porcent, Modelo model) throws SinStockExcepcion{
		plantasConModelo(model)
			.stream()
			.forEach(p-> p.buscarRegistroDelModelo(model).cambiarPrecio(porcent));
	}
	
	//Prop: Actualiza el valor de todos los modelos de las plantas de la fabrica.
	public void cambiarValorTodos(Float porcent){
		plantas
			.stream()
			.forEach(p-> p.cambiarValorDeTodos(porcent));
	}
	
	//Prop: Devuelve todas las plantas de la fabrica
	public List<Planta> getPlantas() {
		return plantas;
	}
}