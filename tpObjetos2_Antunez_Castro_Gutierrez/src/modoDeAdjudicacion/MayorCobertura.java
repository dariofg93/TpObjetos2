package modoDeAdjudicacion;

import java.util.List;

import excepciones.ExceptionParticipante;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class MayorCobertura implements ModoDeAdjudicacion{

	@Override
	public Participante elegirConcursante(PlanDeAhorro plan) {
		
		Participante retorno;
		List<Participante> pagadores = plan.losQueMasPagaron();
		
		if (plan.hayParticipantesDisponibles()){
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