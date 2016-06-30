package inicializadores;

import modeloRegistroYequipamiento.Modelo;
import modeloRegistroYequipamiento.RegistroDeModelo;

public class RecordsCreator {

	//Prop: Crea una instancia de RegistroDeModelo
	public RegistroDeModelo nuevoRegistro(Modelo model) {
		return new RegistroDeModelo(model);
	}
}
