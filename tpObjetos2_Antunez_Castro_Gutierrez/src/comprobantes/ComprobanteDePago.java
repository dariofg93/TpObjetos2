package comprobantes;

import java.util.Date;

import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class ComprobanteDePago {
	
	@SuppressWarnings("unused")
	private Integer cuotaPaga;
	@SuppressWarnings("unused")
	private Float gastosAdministrativos;
	@SuppressWarnings("unused")
	private Float costoDelSeguro;
	@SuppressWarnings("unused")
	private Float alicuota;
	@SuppressWarnings("unused")
	private Date fechaPago;
	
	public ComprobanteDePago(Integer numeroDeCuota, PlanDeAhorro plan, Participante p){
		this.cuotaPaga = numeroDeCuota;
		this.gastosAdministrativos = plan.getConcesionaria().gastoAdministrativos();
		this.costoDelSeguro = plan.getConcesionaria().montoDelSeguro(p,plan.getModelo());
		this.alicuota = plan.calcularAlicuota();
		this.fechaPago = new Date();
	}
}
