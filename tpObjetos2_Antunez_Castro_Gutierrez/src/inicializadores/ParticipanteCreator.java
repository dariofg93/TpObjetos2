package inicializadores;

import persona.Cliente;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class ParticipanteCreator {

	//Prop: Crea una instancia de Participante
	public Participante crearParticipante(Cliente c,PlanDeAhorro p) {
		return new Participante(c,p);
	}
}
