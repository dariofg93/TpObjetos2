package cupon;

import modeloRegistroYequipamiento.Modelo;
import planDeAhorro.PlanDeAhorro;

public class CuponDeAdjudicacion {

	@SuppressWarnings("unused")
	private Float montoTotal;
	@SuppressWarnings("unused")
	private Modelo modelo;
	
	public CuponDeAdjudicacion(PlanDeAhorro plan){
		this.montoTotal = calcularMontoTotal(plan);
		this.modelo = plan.getModelo();
	}

	private Float calcularMontoTotal(PlanDeAhorro plan) {
		return plan.getConcesionaria().gastoDeFlete(
				plan.getConcesionaria().plantaMasCercanaConModelo(
						plan.getModelo()));
	}
}
