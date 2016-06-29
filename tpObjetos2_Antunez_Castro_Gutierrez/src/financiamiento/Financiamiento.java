package financiamiento;

import planDeAhorro.PlanDeAhorro;

public abstract class Financiamiento {
	
	/** Devuelve el valor total del Modelo segun el modo de Financiamiento. */
	public Float totalAabonar(PlanDeAhorro plan){
		return plan.getModelo().getValorDeVenta();
	}

	/** Devuelve el valor de cuota del Modelo, a partir de un porcentaje que esta dado
	 *  segun el modo de Financiamiento. */
	public abstract Float totalAabonarDeCuota(PlanDeAhorro plan);

	/** Devuelve el valor de adjudicacion del Modelo, a partir de un porcentaje
	 *  que esta dado segun el modo de Financiamiento. */
	public abstract Float totalAabonarDeAdjudicacion(PlanDeAhorro planDeAhorro);
}
