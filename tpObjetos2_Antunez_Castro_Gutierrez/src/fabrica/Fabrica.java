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
		Integer total = 0;
		
		for(Planta unaPlanta : buscarPlantasConModelo(modelo))
			total += unaPlanta.stock(modelo);
		return total;
	}
	
	/** Dado un modelo, en el que solo nos interesa su nombre,
	 *  busca entre sus plantas el modelo y lo devuelve,
	 *  lo encuentra por el nombre comparando los nombres.
	 *  
	 *  
	 *  Parcial, en caso de que el modelo no se encuentre.
	 */
	public ArrayList<Planta> buscarPlantasConModelo(Modelo modelo){
		ArrayList<Planta> plantasEncontradas = new ArrayList<>();
		
		for(Planta unaPlanta: this.getPlantas()){
			if (unaPlanta.nombreDeLosModelos().contains(modelo.getNombre()))
				plantasEncontradas.add(unaPlanta);
		}
		return plantasEncontradas;
	}
	
	// Getters y Setters
	
	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}
}