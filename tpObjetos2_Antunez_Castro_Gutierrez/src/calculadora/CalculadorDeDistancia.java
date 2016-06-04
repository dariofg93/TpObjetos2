package calculadora;

import java.util.Random;

import planta.Planta;

public class CalculadorDeDistancia {

	/**
	 * Obs: La distancia en kilometros que hay 
	 * @ entre la concesionaria y una planta es 0 < x < 500
	 */
	public Integer calcularDistancia(Planta unaPlanta){
		
		Random  rnd = new Random();
		return (int)(rnd.nextDouble() * 500 + 1);
	}
}
