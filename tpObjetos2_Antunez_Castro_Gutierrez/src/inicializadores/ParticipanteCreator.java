package inicializadores;

import persona.Cliente;
import persona.Participante;

public class ParticipanteCreator {

	public Participante crearParticipante(Cliente c) {
		return new Participante(c);
	}

}
