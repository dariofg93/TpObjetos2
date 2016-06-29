package inicializadores;

import comprobantes.ComprobanteDePago;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class ComprobanteCreator {

	public ComprobanteDePago crearComprobante(Integer cuotaPaga, PlanDeAhorro plan, Participante p) {
		return new ComprobanteDePago(cuotaPaga, plan, p);
	}

}
