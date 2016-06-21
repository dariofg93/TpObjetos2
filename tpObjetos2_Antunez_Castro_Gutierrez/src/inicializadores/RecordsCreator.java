package inicializadores;

import modeloRegistroYequipamiento.Modelo;
import modeloRegistroYequipamiento.RegistroDeModelo;

public class RecordsCreator {

	public RegistroDeModelo nuevoRegistro(Modelo model) {
		return new RegistroDeModelo(model);
	}
}
