package planDeAhorro;

import java.util.ArrayList;
import java.util.List;

import concesionaria.Concesionaria;
import cupon.CuponDeAdjudicacion;
import financiamiento.Financiamiento;
import modeloRegistroYequipamiento.Modelo;
import modoDeAdjudicacion.ModoDeAdjudicacion;
import modoDeAdjudicacion.SinParticipantesException;
import persona.Participante;

public class PlanDeAhorro {
	
	private Integer numeroDeGrupo;
	private Modelo modeloSuscripto;
	private List<Participante> suscriptos;
	private Financiamiento financiamiento;
	private Integer cantidadDeCuotas;
	private ModoDeAdjudicacion modoDeAdjudicacion;
	private Concesionaria concesionaria;
	
	public PlanDeAhorro(Integer n, Modelo unModelo, Financiamiento unFinanciamiento,
						Integer cantCuotas, ModoDeAdjudicacion unModo, Concesionaria unaConcesionaria){
		this.numeroDeGrupo = n;
		this.modeloSuscripto = unModelo;
		this.suscriptos = new ArrayList<Participante>();
		this.financiamiento = unFinanciamiento;
		this.cantidadDeCuotas = cantCuotas;
		this.modoDeAdjudicacion = unModo;
		this.concesionaria = unaConcesionaria;
	}

	public int cantidadDeParticipantes() {
		return this.suscriptos.size();
	}

	public Modelo getModelo() {
		return this.modeloSuscripto;
	}

	public List<Participante> getParticipantes() {
		return this.suscriptos;
	}
	
	public List<Participante> losQueMasPagaron(){
		List<Participante>  ganadores = new ArrayList<Participante>();
		
		for(Participante participantes: participantesDisponibles()){
			if(participantes.cuotasPagas()==mayorCantidadCuotasPagas())
				ganadores.add(participantes);
		}
		return ganadores;		
	}

	private Integer mayorCantidadCuotasPagas() {
		Integer mayorPagas = 0;
		
		for(Participante current : participantesDisponibles()) {
			if(current.cuotasPagas()>mayorPagas)
				mayorPagas = current.cuotasPagas();
		}
		return mayorPagas;
	}
	
	public ArrayList<Participante> participantesDisponibles() {
		ArrayList<Participante> disponibles = new ArrayList<>();
		
		for(Participante p: suscriptos){
			if(p.estaDisponible())
				disponibles.add(p);
		}
		return disponibles;
	}
	
	public Integer cantidadDeParticipantesDisponibles() {
		return participantesDisponibles().size();
	}

	public List<Participante> losMasViejos(List<Participante> ganadores){
		List<Participante> mayores = new ArrayList<Participante>();
		
		for(Participante current : ganadores){
			if(current.getFecNac().equals(elmasViejo(ganadores)))
				mayores.add(current);
		}
		return mayores;
	}

	private Participante elmasViejo(List<Participante> ganadores) {
		Participante mayor = ganadores.get(0);
		
		for(Participante current : ganadores) {
			if(current.getFecNac().before(mayor.getFecNac()))
				mayor = current;
		}
		return mayor;
	}

	public Integer getNumeroDeGrupo() {
		return numeroDeGrupo;
	}
	
	public Participante elPrimerSuscriptor(List<Participante> ganadores){
		Participante elGanador = ganadores.get(0);
		
		for(Participante current : ganadores) {
			if(current.getFechaDeInscripcion().before(elGanador.getFecNac()))
				elGanador = current;
		}
		return elGanador;
	}

	public Concesionaria getConcesionaria() {
		return concesionaria;
	}

	public Float calcularAlicuota() {
		return financiamiento.totalAabonar(this) / cantidadDeCuotas;
	}
	
	public CuponDeAdjudicacion elegirGanador() throws SinParticipantesException{
		
		Participante elGanador = modoDeAdjudicacion.elegirConcursante(this);
		elGanador.fuiAdjudicado();
		
	}
}