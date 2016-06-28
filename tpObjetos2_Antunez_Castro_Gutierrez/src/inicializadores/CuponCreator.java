package inicializadores;

import cupon.CuponDeAdjudicacion;
import excepciones.SinStockExcepcion;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class CuponCreator {

	public CuponDeAdjudicacion crearCupon(PlanDeAhorro plan, Participante winner) throws SinStockExcepcion {
		return new CuponDeAdjudicacion(plan, winner);
	}

}
