package modoDeAdjudicacion;

import excepciones.ExceptionParticipante;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public interface ModoDeAdjudicacion {
	
	/**
	 * Prop: Dado un plan de ahorro, devuelve uno de sus participantes disponibles,
	 * 		 el cual sera el ganador de una adjudicacion.
	 * Prec: Debe haber almenos 1 participante disponible en el plan de ahorro dado.
	 */
	public abstract Participante elegirConcursante(PlanDeAhorro plan) throws ExceptionParticipante;
}
