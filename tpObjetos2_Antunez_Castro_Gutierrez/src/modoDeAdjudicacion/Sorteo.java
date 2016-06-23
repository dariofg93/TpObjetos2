package modoDeAdjudicacion;

import java.util.Random;

import excepciones.ExceptionParticipante;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class Sorteo implements ModoDeAdjudicacion{
	
	@Override
	public Participante elegirConcursante(PlanDeAhorro plan) throws ExceptionParticipante {
		Random  rnd = new Random();
		Participante retorno = null;
		
		try {
		
			     int ganador = (int)(rnd.nextDouble() * plan.cantidadDeParticipantes()-1);
			     retorno = plan.participantesDisponibles().get(ganador);
		    }
		
		catch (ArrayIndexOutOfBoundsException excepcion) {
			throw new ExceptionParticipante();
		}
		
		return retorno;
	}
	
	
}
