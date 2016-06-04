package financiamiento;

import planDeAhorro.PlanDeAhorro;

public class Plan100 extends Financiamiento {

	@Override
	public Float totalAabonar(PlanDeAhorro plan) {
		return plan.getModelo().getValorDeVenta();
	}
}
