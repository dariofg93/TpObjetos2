package planta;

import java.util.ArrayList;
import java.util.List;

import excepciones.ExceptionStock;
import inicializadores.RecordsCreator;
import modeloRegistroYequipamiento.Modelo;
import modeloRegistroYequipamiento.RegistroDeModelo;

/** Explicacion de la clase Planta, y su comportamiento. */

public class Planta {

	private String direccion;
	private List<RegistroDeModelo> registros;
	private RecordsCreator creador;
		
	public Planta(String unaDireccion){
		this.direccion = unaDireccion;
		this.registros = new ArrayList<RegistroDeModelo>();
		this.creador = new RecordsCreator();
	}
	
	// Devuelve el nombre de todos los modelos que produce.
	public List<String> nombreDeLosModelos() {
		List<String> todosLosNombres = new ArrayList<String>();
		
		for (RegistroDeModelo unModelo: registros){
			todosLosNombres.add(unModelo.getNombreDelModelo());
		}
		return todosLosNombres;
	}
	
	public RegistroDeModelo buscarRegistroDelModelo(Modelo modelo) {
		RegistroDeModelo registroEncontrado = registros.get(0);
		
		for (RegistroDeModelo unRegistro: registros){
			if (unRegistro.getNombreDelModelo().equals(modelo.getNombre())){
				registroEncontrado = unRegistro;
			}
		}
		return registroEncontrado;
	}
	
	public Integer stock(Modelo modelo) throws ExceptionStock{		
		if(!perteneceModelo(modelo))
			throw new ExceptionStock();
		
		return buscarRegistroDelModelo(modelo).getCantidad();
	}
	
	private Boolean perteneceModelo(Modelo modelo) {
		return nombreDeLosModelos().contains(modelo.getNombre());
	}
	
	public void quitarEjemplar(Modelo modelo){
		RegistroDeModelo registro = buscarRegistroDelModelo(modelo);
		
		if(registro.getCantidad()>1){
			registro.descontarUnidad();
		} else{
			registros.remove(registro);
		}
	}
	
	public void sumarEjemplar(Modelo model){
		RegistroDeModelo registro = null;
		if(perteneceModelo(model))
			registro = buscarRegistroDelModelo(model);
		
		if(registro != null){
			registro.sumarUnidad();
		} else{
			registros.add(creador.nuevoRegistro(model));
		}
	}
}
