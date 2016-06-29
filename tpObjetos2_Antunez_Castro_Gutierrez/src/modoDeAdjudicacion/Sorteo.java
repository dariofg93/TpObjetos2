package modoDeAdjudicacion;

import java.util.Random;

import excepciones.ExceptionParticipante;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class Sorteo implements ModoDeAdjudicacion{
	Random  rnd;
	
	public Sorteo(Random unRandom){
		this.rnd = unRandom;
	}
	
	/**
	 * Prop: Dado un plan de ahorro, devuelve uno de sus participantes disponibles,
	 * 		 el cual sera el ganador de una adjudicacion.
	 * Prec: Debe haber almenos 1 participante disponible en el plan de ahorro dado.
	 */
	@Override
	public Participante elegirConcursante(PlanDeAhorro plan) throws ExceptionParticipante{
		if(plan.hayParticipantesDisponibles()){
			int ganador = (int)(rnd.nextDouble() * plan.participantesDisponibles().size()-1);
			return plan.participantesDisponibles().get(ganador);
		}else{
			throw new ExceptionParticipante();
		}
	}
}