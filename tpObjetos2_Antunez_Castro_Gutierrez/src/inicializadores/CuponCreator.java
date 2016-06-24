package inicializadores;

import cupon.CuponDeAdjudicacion;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class CuponCreator {

	public CuponDeAdjudicacion crearCupon(PlanDeAhorro plan, Participante winner) {
		return new CuponDeAdjudicacion(plan, winner);
	}

}
