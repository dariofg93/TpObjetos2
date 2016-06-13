package modoDeAdjudicacion;

import java.util.Random;

import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class Sorteo extends ModoDeAdjudicacion{

	@Override
	public Participante elegirConcursante(PlanDeAhorro plan) {
		Random  rnd = new Random();
		Participante retorno;
		
		//if(plan.cantidadDeParticipantesDisponibles().equals(0)){
			//try tiene que ver con un try
			//cuando se cumpla el try tirar excepcion
		//} else {
			int ganador = (int)(rnd.nextDouble() * plan.cantidadDeParticipantes().size()-1);
			retorno = plan.participantesDisponibles().get(ganador);
		}
		return retorno;
//		
//		try{
//
//			int ganador = (int)(rnd.nextDouble() * plan.cantidadDeParticipantes().size()-1);
//			retorno = plan.participantesDisponibles().get(ganador);
//		} catch (excecion..)
//	}
}
