package aseguradora;

import modeloRegistroYequipamiento.Modelo;
import persona.Participante;

public class CompaniaAseguradora {

	/**
	 * Prop: Dado un participante y un modelo retorna el monto que impone la 
	 * compañia aseguradora
	 */
	public Float montoDelSeguro(Participante p, Modelo modelo) {
		Float total = 50f;
		Integer i = p.edad()-50;
		
		if(i>0)
			total = total + 10 * i;
		
		return total + modelo.porcentajeDelPrecio(5f);
	}	
}
