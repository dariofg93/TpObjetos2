package persona;

import java.util.ArrayList;
import java.util.Date;

import comprobantes.ComprobanteDePago;

public class Participante extends Persona{
	
	private ArrayList<ComprobanteDePago> comprobantes;
	private Boolean disponible;
	
	
	public Participante(Integer unDni, String nyAp, String mail, String dir, Date nacimiento, Date ingreso) {
		super(unDni, nyAp, mail, dir, nacimiento, ingreso);
		this.comprobantes = new ArrayList<>();
		this.disponible = true;
	}

	public Integer cuotasPagas() {
		return comprobantes.size();
	}

	public Integer edad() {
		Date actual = new Date();
		
		return (actual.getYear() - fechaNac.getYear());
	}
	
	
}
