package persona;

import java.util.ArrayList;
import java.util.Date;

import comprobantes.ComprobanteDePago;

public class Participante{
	
	private ArrayList<ComprobanteDePago> comprobantes;
	private Boolean disponible;
	private Date fechaInscripcion;
	private Cliente cliente;
	
	public Participante(Cliente unCliente) {
		this.comprobantes = new ArrayList<>();
		this.disponible = true;
		this.cliente = unCliente;
		this.fechaInscripcion = new Date();
	}

	public Integer cuotasPagas() {
		return comprobantes.size();
	}

	public Integer edad() {
		Date actual = new Date();
		
		return (actual.getYear() - cliente.getFecNac().getYear());
	}

	public Boolean estaDisponible() {
		return disponible;
	}

	public void fuiAdjudicado() {
		disponible = false;
	}

	public Cliente getCliente() {
		return cliente;
	}
}
