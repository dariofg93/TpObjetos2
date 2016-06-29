package financiamiento;

import planDeAhorro.PlanDeAhorro;

public class Plan100 extends Financiamiento {

	@Override
	public Float totalAabonarDeCuota(PlanDeAhorro plan) {
		return super.totalAabonar(plan);
	}

	@Override
	public Float totalAabonarDeAdjudicacion(PlanDeAhorro planDeAhorro) {
		return 0f;
	}
}
