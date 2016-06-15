package modoDeAdjudicacion;

import java.util.Random;

import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class Sorteo implements ModoDeAdjudicacion{
/*
	@Override
	public Participante elegirConcursante(PlanDeAhorro plan) {
		Random  rnd = new Random();
		Participante retorno;
		
		if(plan.cantidadDeParticipantesDisponibles().equals(0)){
			
			try {
				  throw new SinParticipantesException();
				} catch (SinParticipantesException e) {
				 e.printStackTrace();
				}
		}
		
		else {
			int ganador = (int)(rnd.nextDouble() * plan.cantidadDeParticipantes()-1);
			retorno = plan.participantesDisponibles().get(ganador);
		}
		return retorno;
	}
//		
//		try{
//
//			int ganador = (int)(rnd.nextDouble() * plan.cantidadDeParticipantes().size()-1);
//			retorno = plan.participantesDisponibles().get(ganador);
//		} catch (excecion..)
//	} 
 */
	
	@Override
	public Participante elegirConcursante(PlanDeAhorro plan) throws SinParticipantesException {
		Random  rnd = new Random();
		Participante retorno = null;
		
		try {
		
			     int ganador = (int)(rnd.nextDouble() * plan.cantidadDeParticipantes()-1);
			     retorno = plan.participantesDisponibles().get(ganador);
		    }
		
		catch (ArrayIndexOutOfBoundsException excepcion) {
			throw new SinParticipantesException();
		}
		
		return retorno;
	}
	
	
}
