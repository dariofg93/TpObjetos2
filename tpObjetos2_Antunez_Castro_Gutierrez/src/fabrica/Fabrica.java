package fabrica;

import java.util.ArrayList;
import java.util.List;

import modeloRegistroYequipamiento.Modelo;
import planta.Planta;

public class Fabrica {

	private List<Planta> plantas;

	public Fabrica(){
		this.setPlantas(new ArrayList<Planta>());
	}
	
	// Otros mensajes
	
	public Integer stock(Modelo modelo) {
		return this.buscarPlantaConModelo(modelo).stock(modelo);
	}
	
	/** Dado un modelo, en el que solo nos interesa su nombre,
	 *  busca entre sus plantas el modelo y lo devuelve,
	 *  lo encuentra por el nombre comparando los nombres.
	 *  
	 *  
	 *  Parcial, en caso de que el modelo no se encuentre.
	 */
	public Planta buscarPlantaConModelo(Modelo modelo){
		Planta plantaEncontrada = null;
		
		for(Planta unaPlanta: this.getPlantas()){
			if (unaPlanta.nombreDeLosModelos().contains(modelo.getNombre())){
				plantaEncontrada = unaPlanta;
				break;
			}
		}
		return plantaEncontrada;
	}
	
	/** Busca el modelo(Osea el String dado por parametro,
	 *  que es el nombre del modelo) en su lista de plantas,
	 *  de ese modelo se fija la cantidad que tiene.. 
	 *  si tiene mas de 2 quita una repeticion,
	 *  si es solo uno quita el modelo directamente..
	 */
	public void quitarEjemplar(Modelo modelo){
		buscarPlantaConModelo(modelo).quitarEjemplar(modelo);
	}
	
	// Getters y Setters
	
	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}
}