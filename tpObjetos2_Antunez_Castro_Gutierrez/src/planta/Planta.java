package planta;

import java.util.ArrayList;
import java.util.List;

import modeloRegistroYequipamiento.Modelo;
import modeloRegistroYequipamiento.RegistroDeModelo;

/** Explicacion de la clase Planta, y su comportamiento. */

public class Planta {

	/** Variables*/
	
	// La ubicacion de la planta.
	private String direccion;

	// Sublista de los registros de los modelos pertenecientes a su Fabrica.
	private List<RegistroDeModelo> registros;
	
	/** Instanciando la clase */
	
	public Planta(String unaDireccion){
		this.setDireccion(unaDireccion);
		
		// Se instancia a los modelos como un arreglo.
		// Podria ser un conjunto sin repetidos, pero se tienen en cuenta que
		// no se guardan registros con modelos iguales entre ellos.
		this.setModelos(new ArrayList<RegistroDeModelo>());
	}
	
	/** Otros mensajes */
	
	// Devuelve el nombre de todos los modelos que produce.
	public List<String> nombreDeLosModelos() {
		List<String> todosLosNombres = new ArrayList<String>();
		
		for (RegistroDeModelo unModelo: this.getRegistros()){
			todosLosNombres.add(unModelo.getNombreDelModelo());
		}

		return todosLosNombres;
	}
	
	/** Quita un ejemplar del modelo que tenga el nombre dado.
	 *  */
	public RegistroDeModelo buscarRegistroDelModelo(Modelo modelo) {
		RegistroDeModelo registroEncontrado = registros.get(0);
		
		for (RegistroDeModelo unRegistro: this.getRegistros()){
			if (unRegistro.getNombreDelModelo().equals(modelo.getNombre())){
				registroEncontrado = unRegistro;
			}
		}
		
		return registroEncontrado;
	}
	
	public Integer stock(Modelo modelo) {
		RegistroDeModelo registroBuscado = registros.get(0);
		
		if(perteneceModelo(modelo)){
			for (RegistroDeModelo unRegistro: this.getRegistros()){
				if (unRegistro.getNombreDelModelo().equals(modelo.getNombre())){
					registroBuscado = unRegistro;
					break;
				}
			}
			return registroBuscado.getCantidad();
		}
		
		else{
			return 0;
		}
	}
	
	public void quitarEjemplar(Modelo modelo){
		RegistroDeModelo registro = buscarRegistroDelModelo(modelo);
		
		if(registro.getCantidad()>1){
			registro.descontarUnidad();
		} else{
			registros.remove(registro);
		}
	}
	
	// Getters and Setters
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<RegistroDeModelo> getRegistros() {
		return registros;
	}

	public void setModelos(List<RegistroDeModelo> modelos) {
		this.registros = modelos;
	}

	private Boolean perteneceModelo(Modelo modelo) {
		return nombreDeLosModelos().contains(modelo.getNombre());
	}
}
