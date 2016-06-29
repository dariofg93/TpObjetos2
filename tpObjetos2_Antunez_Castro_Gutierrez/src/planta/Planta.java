package planta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import excepciones.SinStockExcepcion;
import inicializadores.RecordsCreator;
import modeloRegistroYequipamiento.Modelo;
import modeloRegistroYequipamiento.RegistroDeModelo;

public class Planta {

	@SuppressWarnings("unused")
	private String direccion;
	private List<RegistroDeModelo> registros;
	private RecordsCreator creador;
		
	public Planta(String unaDireccion){
		this.direccion = unaDireccion;
		this.registros = new ArrayList<RegistroDeModelo>();
		this.creador = new RecordsCreator();
	}
	
	//Prop: Agrega un registro a la lista de registros de la planta.
	public void agregarRegistro(RegistroDeModelo unRegistro) {
		registros.add(unRegistro);
	}

	/**
	 * Prop: Quita el registro dado por parametro de la lista de registros de la planta.
	 * Prec: Debe existir el registro en la lista de registros de la planta.
	 */
	public void quitarRegistro(RegistroDeModelo unRegistro) {
		registros.remove(unRegistro);
	}
	
	// Prop: Devuelve el nombre de todos los modelos que produce.
	public List<String> nombreDeLosModelos() {
		return registros
			.stream()
			.map(RegistroDeModelo::getNombreDelModelo)
			.collect(Collectors.toList());
	}
	
	/**
	 * Prop: Retorna el registro dado por parametro comparandolos por el nombre
	 * 		 del modelo que registran.
	 * Prec: El registro dado por parametro existe en la lista de registros de la planta
	 */
	public RegistroDeModelo buscarRegistroDelModelo(Modelo modelo) {

		List<RegistroDeModelo> registroEncontrado = registros
			.stream()
			.filter(r-> r.getNombreDelModelo().equals(modelo.getNombre()))
			.collect(Collectors.toList());
		
		return registroEncontrado.get(0);
	}
	
	/**
	 * Prop: Devuelve el stock del modelo dado por parametro en la planta.
	 * Prec: El stock debe ser de almenos un sino se la lanzará un SinStockException.
	 */
	public Integer stock(Modelo modelo) throws SinStockExcepcion{		
		if(perteneceModelo(modelo))
			return buscarRegistroDelModelo(modelo).getCantidad();
		
		throw new SinStockExcepcion();
	}
	
	//Prop: Devuelve true si el modelo se encuentra en la planta, falso caso contrario.
	private Boolean perteneceModelo(Modelo modelo) {
		return nombreDeLosModelos().contains(modelo.getNombre());
	}
	
	/**
	 * Prop: Descuenta en uno la cantidad de ejemplares que tiene el registro
	 * 		 del modelo dado por parametro.
	 * Prec: Debe haber stock del modelo dado por parametro.
	 */
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

	//Prop: Suma en uno la cantidad de ejemplares que tiene el registro
	// 	    del modelo dado por parametro.
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

	//Prop: Actualiza el valor de todos los modelos de la planta.
	public void cambiarValorDeTodos(Float porcent) {
		registros
			.stream()
			.forEach(r-> r.cambiarPrecio(porcent));
	}
}
