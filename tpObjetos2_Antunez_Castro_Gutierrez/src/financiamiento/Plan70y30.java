package financiamiento;

import modeloRegistroYequipamiento.Modelo;
import planDeAhorro.PlanDeAhorro;

public class Plan70y30 extends Financiamiento{

	@Override
	public Float totalAabonarDeCuota(PlanDeAhorro plan) {
		return porcentajeDelModelo(plan.getModelo(), 70);
	}

	private Float porcentajeDelModelo(Modelo modelo, int porcent) {
		return (modelo.getValorDeVenta() * porcent) / 100;
	}

	@Override
	public Float totalAabonarDeAdjudicacion(PlanDeAhorro plan) {
		return porcentajeDelModelo(plan.getModelo(), 30);
	}
}