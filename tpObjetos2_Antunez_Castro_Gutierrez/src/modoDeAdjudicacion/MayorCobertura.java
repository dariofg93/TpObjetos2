package modoDeAdjudicacion;

import java.util.List;

import excepciones.ExceptionParticipante;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class MayorCobertura implements ModoDeAdjudicacion{

	/**
	 * Prop: Dado un plan de ahorro, devuelve uno de sus participantes disponibles,
	 * 		 el cual sera el ganador de una adjudicacion.
	 * Prec: Debe haber almenos 1 participante disponible en el plan de ahorro dado.
	 */
	@Override
	public Participante elegirConcursante(PlanDeAhorro plan) throws ExceptionParticipante {
		
		Participante retorno;
		
		if (plan.hayParticipantesDisponibles()){
			
			List<Participante> pagadores = plan.losQueMasPagaron();
			
			if(pagadores.size()==1){
				retorno = pagadores.get(0);
			}
			else{
				retorno = findWinner(pagadores,plan);
			}
		}else{
			throw new ExceptionParticipante();
		}
		return retorno;
	}
	
	private Participante findWinner(List<Participante> pagadores, PlanDeAhorro plan){
		if(plan.losMasViejos(pagadores).size()==1)
			return plan.losMasViejos(pagadores).get(0);	 
		
		return plan.elPrimerSuscriptor(plan.losMasViejos(pagadores));
	}
}