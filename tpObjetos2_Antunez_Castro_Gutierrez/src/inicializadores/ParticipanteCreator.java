package inicializadores;

import persona.Cliente;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class ParticipanteCreator {

	public Participante crearParticipante(Cliente c,PlanDeAhorro p) {
		return new Participante(c,p);
	}

}
