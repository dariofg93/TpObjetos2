package inicializadores;

import comprobantes.ComprobanteDePago;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class ComprobanteCreator {

	//Prop: Crea una instancia de ComprobanteDePago
	public ComprobanteDePago crearComprobante(Integer cuotaPaga, PlanDeAhorro plan, Participante p) {
		return new ComprobanteDePago(cuotaPaga, plan, p);
	}
}
