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

	private Float montoTotal;
	private Modelo modelo;
	private Cliente cliente;
	
	
	public CuponDeAdjudicacion(PlanDeAhorro plan,Participante winner) throws SinStockExcepcion{
		this.modelo = plan.getModelo();
		this.cliente = winner.getCliente();
		this.montoTotal = this.montoTotal(plan);
	}

	
	private Float montoTotal(PlanDeAhorro plan) throws SinStockExcepcion{
		return montoDelFleteDeConcesionaria(plan) + plan.montoDelFinanciamientoDeAdjudicacion();
	}

	private Float montoDelFleteDeConcesionaria(PlanDeAhorro plan) throws SinStockExcepcion {
		Concesionaria concesionaria = plan.getConcesionaria();
		Fabrica fabrica = concesionaria.getFabrica();
		Modelo modelo = plan.getModelo();
		Planta planta = fabrica.plantaMasCercanaAConcesionaria(modelo);
		
		return concesionaria.gastoDeFlete(planta);		
	}
	
	public Modelo getModelo() {
		return modelo;
	}
}
