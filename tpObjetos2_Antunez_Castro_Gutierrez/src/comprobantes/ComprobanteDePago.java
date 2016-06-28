package comprobantes;

import java.util.Date;

import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class ComprobanteDePago {
	
	private Integer cuotaPaga;
	private Float gastosAdministrativos;
	private Float costoDelSeguro;
	private Float alicuota;
	private Date fechaPago;
	
	public ComprobanteDePago(Integer numeroDeCuota, PlanDeAhorro plan, Participante p){
		this.cuotaPaga = numeroDeCuota;
		this.gastosAdministrativos = plan.getConcesionaria().gastoAdministrativos();
		this.costoDelSeguro = plan.getConcesionaria().montoDelSeguro(p,plan.getModelo());
		this.alicuota = plan.calcularAlicuota();
		this.fechaPago = new Date();
	}
}
