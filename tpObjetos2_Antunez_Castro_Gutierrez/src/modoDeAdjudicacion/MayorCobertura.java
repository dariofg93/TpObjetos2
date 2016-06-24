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
		
		if (plan.cantidadDeParticipantesDisponibles() == 0){
			throw new ExceptionParticipante();
		}else{
		
		if(pagadores.size()==1){
			retorno = pagadores.get(0);
		}
		else{
			if(plan.losMasViejos(pagadores).size()==1){
				retorno = plan.losMasViejos(pagadores).get(0);	
			} 
			else {
				retorno = plan.elPrimerSuscriptor(plan.losMasViejos(pagadores));
			}
		}
		
		}
		
			
		
		
		return retorno;
	}
}
