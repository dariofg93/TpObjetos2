package planta;

import java.util.ArrayList;
import java.util.List;

import excepciones.SinStockExcepcion;
import inicializadores.RecordsCreator;
import modeloRegistroYequipamiento.Modelo;
import modeloRegistroYequipamiento.RegistroDeModelo;



public class Planta {

	private String direccion;
	private List<RegistroDeModelo> registros;
	private RecordsCreator creador;
		
	public Planta(String unaDireccion){
		this.direccion = unaDireccion;
		this.registros = new ArrayList<RegistroDeModelo>();
		this.creador = new RecordsCreator();
	}
	
	public void agregarRegistro(RegistroDeModelo unRegistro) {
		registros.add(unRegistro);
	}

	public void quitarRegistro(RegistroDeModelo unRegistro) {
		registros.remove(unRegistro);
	}
	
	// Devuelve el nombre de todos los modelos que produce.
	public List<String> nombreDeLosModelos() {
		List<String> todosLosNombres = new ArrayList<String>();
		
		for (RegistroDeModelo unModelo: registros){
			todosLosNombres.add(unModelo.getNombreDelModelo());
		}
		return todosLosNombres;
	}
	
	//Prec.: Hay por lo menos un registro de algun modelo
	public RegistroDeModelo buscarRegistroDelModelo(Modelo modelo) {
		RegistroDeModelo registroEncontrado = registros.get(0);
		
		for (RegistroDeModelo unRegistro: registros){
			if (unRegistro.getNombreDelModelo().equals(modelo.getNombre())){
				registroEncontrado = unRegistro;
			}
		}
		return registroEncontrado;
	}
	
	public Integer stock(Modelo modelo) throws SinStockExcepcion{		
		if(perteneceModelo(modelo))
			return buscarRegistroDelModelo(modelo).getCantidad();
		
		throw new SinStockExcepcion();
	}
	
	private Boolean perteneceModelo(Modelo modelo) {
		return nombreDeLosModelos().contains(modelo.getNombre());
	}
	
	public void quitarEjemplar(Modelo modelo) throws SinStockExcepcion{
		if(!perteneceModelo(modelo))
			throw new SinStockExcepcion();
		
		RegistroDeModelo registro = buscarRegistroDelModelo(modelo);
		
		if(registro.getCantidad()>1){
			registro.descontarUnidad();
		} else{
			quitarRegistro(registro);
		}
	}

	public void sumarEjemplar(Modelo modelo){
		RegistroDeModelo registro = null;
		if(perteneceModelo(modelo))
			registro = buscarRegistroDelModelo(modelo);
		
		if(registro != null){
			registro.sumarUnidad();
		} else{
			agregarRegistro(creador.nuevoRegistro(modelo));
		}
	}

	public void cambiarValorDeTodos(Float porcent) {
		for(RegistroDeModelo r: registros)
			r.cambiarPrecio(porcent);
	}
}
