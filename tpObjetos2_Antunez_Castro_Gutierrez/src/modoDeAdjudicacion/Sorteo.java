package modoDeAdjudicacion;

import java.util.Random;

import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class Sorteo extends ModoDeAdjudicacion{

	@Override
	public Participante elegirConcursante(PlanDeAhorro plan) {
		Random  rnd = new Random(); 
		int ganador = (int)(rnd.nextDouble() * plan.cantidadDeParticipantes()-1);
		return plan.getParticipantes().get(ganador);
	}
}
