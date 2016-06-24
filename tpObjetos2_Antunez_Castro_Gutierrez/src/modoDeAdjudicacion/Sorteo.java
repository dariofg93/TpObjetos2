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
	
	@Override
	public Participante elegirConcursante(PlanDeAhorro plan) throws ExceptionParticipante {
		Participante retorno = null;
		
		if(plan.participantesDisponibles().size()>0){
			     int ganador = (int)(rnd.nextDouble() * plan.participantesDisponibles().size()-1);
			     retorno = plan.participantesDisponibles().get(ganador);
		}else{
			throw new ExceptionParticipante();
		}
		return retorno;
	}
}
