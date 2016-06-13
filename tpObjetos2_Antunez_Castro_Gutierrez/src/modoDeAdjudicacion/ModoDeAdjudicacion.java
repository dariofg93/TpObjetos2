package modoDeAdjudicacion;

import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public abstract class ModoDeAdjudicacion {
	
	public abstract Participante elegirConcursante(PlanDeAhorro plan);
}
