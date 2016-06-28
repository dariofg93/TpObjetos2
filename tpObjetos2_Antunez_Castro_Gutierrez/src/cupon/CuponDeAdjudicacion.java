package cupon;

import excepciones.SinStockExcepcion;
import modeloRegistroYequipamiento.Modelo;
import persona.Cliente;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class CuponDeAdjudicacion {

	private Float montoTotal;
	private Modelo modelo;
	private Cliente cliente;
	
	
	public CuponDeAdjudicacion(PlanDeAhorro plan,Participante winner) throws SinStockExcepcion{
		this.modelo = plan.getModelo();
		this.cliente = winner.getCliente();
		this.montoTotal = montoTotal(plan);
	}

	
	public Float montoTotal(PlanDeAhorro plan) throws SinStockExcepcion{
		return montoDelFlete(plan) + montoDelFinanciamiento(plan);
	}
	
	 
	public Float montoDelFinanciamiento(PlanDeAhorro plan) {
		return plan.montoDelFinanciamientoDeAdjudicacion();
	}

	
	public Float montoDelFlete(PlanDeAhorro plan) throws SinStockExcepcion {
		return plan.montoDelFlete();
	}

	
	public Modelo getModelo() {
		return modelo;
	}
}
