package cupon;

import modeloRegistroYequipamiento.Modelo;
import persona.Cliente;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class CuponDeAdjudicacion {

	private Float montoTotal;
	private Modelo modelo;
	private Cliente cliente;
	
	public CuponDeAdjudicacion(PlanDeAhorro plan,Participante winner){
		this.modelo = plan.getModelo();
		this.cliente = winner.getCliente();
		this.montoTotal = montoTotal(plan);
	}

	private Float montoTotal(PlanDeAhorro plan){
		return montoDelFlete(plan) + montoDelFinanciamiento(plan);
	}
	
	private Float montoDelFinanciamiento(PlanDeAhorro plan) {
		return plan.montoDelFinanciamiento();
	}

	private Float montoDelFlete(PlanDeAhorro plan) {
		return plan.getConcesionaria().gastoDeFlete(
				plan.getConcesionaria().getFabrica().plantaMasCercanaAConcesionaria(
						plan.getModelo()));
	}

	public Modelo getModelo() {
		return modelo;
	}
}
