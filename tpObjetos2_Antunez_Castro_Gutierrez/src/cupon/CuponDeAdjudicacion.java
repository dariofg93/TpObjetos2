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
		this.montoTotal = calcularMontoTotal(plan);
		this.modelo = plan.getModelo();
		this.cliente = winner.getCliente();
	}

	private Float calcularMontoTotal(PlanDeAhorro plan) {
		return plan.getConcesionaria().gastoDeFlete(
				plan.getConcesionaria().getFabrica().plantaMasCercanaAConcesionaria(
						plan.getModelo()));
	}

	public Modelo getModelo() {
		return modelo;
	}
}
