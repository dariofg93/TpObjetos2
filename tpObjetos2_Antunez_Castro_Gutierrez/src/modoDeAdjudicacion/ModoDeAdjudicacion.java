package modoDeAdjudicacion;

import cliente.Cliente;
import planDeAhorro.PlanDeAhorro;

public abstract class ModoDeAdjudicacion {
	
	public abstract Cliente elegirConcursante(PlanDeAhorro plan);
}
