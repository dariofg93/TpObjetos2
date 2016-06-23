package modoDeAdjudicacion;

import excepciones.ExceptionParticipante;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public interface ModoDeAdjudicacion {
	
	public abstract Participante elegirConcursante(PlanDeAhorro plan) throws ExceptionParticipante;
}
