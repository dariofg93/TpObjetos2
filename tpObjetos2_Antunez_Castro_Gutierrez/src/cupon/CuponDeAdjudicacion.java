package cupon;

import concesionaria.Concesionaria;
import excepciones.SinStockExcepcion;
import fabrica.Fabrica;
import modeloRegistroYequipamiento.Modelo;
import persona.Cliente;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;
import planta.Planta;

public class CuponDeAdjudicacion {

	@SuppressWarnings("unused")
	private Float montoTotal;
	private Modelo modelo;
	@SuppressWarnings("unused")
	private Cliente cliente;
	
	
	public CuponDeAdjudicacion(PlanDeAhorro plan,Participante winner) throws SinStockExcepcion{
		this.modelo = plan.getModelo();
		this.cliente = winner.getCliente();
		this.montoTotal = this.montoTotal(plan);
	}

	/**
	 * Prop: Calcula el monto total a pagar del cupon.
	 * Prec: Debe haber almenos 1 de stock del modelo del plan en la concesionaria.
	 */
	private Float montoTotal(PlanDeAhorro plan){
		return montoDelFleteDeConcesionaria(plan) + plan.montoDelFinanciamientoDeAdjudicacion();
	}

	/**
	 * Prop: Calcula el monto que se debe pegar a la concesionaria por gastos de flete.
	 * Prec: Debe haber almenos 1 de stock del modelo del plan en la concesionaria.
	 */
	private Float montoDelFleteDeConcesionaria(PlanDeAhorro plan){
		Concesionaria concesionaria = plan.getConcesionaria();
		Fabrica fabrica = concesionaria.getFabrica();
		Modelo modelo = plan.getModelo();
		Planta planta = fabrica.plantaMasCercanaAConcesionaria(modelo);
		
		return concesionaria.gastoDeFlete(planta);		
	}
	
	//Getter:
	public Modelo getModelo() {
		return modelo;
	}
}
