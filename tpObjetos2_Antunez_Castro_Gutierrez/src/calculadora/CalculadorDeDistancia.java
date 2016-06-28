package calculadora;

import java.util.Random;

import planta.Planta;

public class CalculadorDeDistancia {

	Random rnd;
	
	//Setea un randomMock a la calculadora para poder testear el SUT
	public void setRandom(Random unRandom){
		rnd = unRandom;
	}
	
	/**
	 * Prop: Devuelve un numero entre 1 500 que simula ser la distancia desde la
	 * 		 concesionaria hasta la planta dada por parametro.
	 * Obs: La distancia en kilometros que hay 
	 *  	entre la concesionaria y una planta es 0 < x <= 500
	 */
	public Integer calcularDistancia(Planta unaPlanta){
		
		return (int)(rnd.nextDouble() * 500 + 1);
	}
}
