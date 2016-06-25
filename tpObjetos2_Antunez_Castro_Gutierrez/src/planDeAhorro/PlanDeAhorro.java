package planDeAhorro;

import java.util.ArrayList;
import java.util.List;

import concesionaria.Concesionaria;
import excepciones.ExceptionParticipante;
import financiamiento.Financiamiento;
import modeloRegistroYequipamiento.Modelo;
import modoDeAdjudicacion.ModoDeAdjudicacion;
import persona.Cliente;
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
	
	public Integer getNumeroDeGrupo() {
		return numeroDeGrupo;
	}
	
	public Concesionaria getConcesionaria() {
		return concesionaria;
	}
	
	public List<Participante> getParticipantes() {
		return this.suscriptos;
	}
	
	public int cantidadDeParticipantes() {
		return this.suscriptos.size();
	}

	/**
	 * Crea una instancia de participante utilizando el cliente dado por parametro y
	 * su colaborador interno creado o como se llame... y lo agrega a su lista de participantes
	 */
	public void suscribirCliente(Cliente c) {
		// TODO Auto-generated method stub	
	}

	public Boolean hayParticipantesDisponibles() {
		return participantesDisponibles().size() > 0;
	}
	
	public Modelo getModelo() {
		return this.modeloSuscripto;
	}

	public List<Participante> participantesDisponibles() throws ExceptionParticipante{
		ArrayList<Participante> disponibles = new ArrayList<>();
		
		for(Participante p: suscriptos)
			if(p.estaDisponible()) disponibles.add(p);
		/**												
		if(disponibles.size()==0)
			throw new ExceptionParticipante(); */
		
		return disponibles;
	}
	
	
	public Integer cantidadDeParticipantesDisponibles() {
		return participantesDisponibles().size();
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

	
	
	public Participante elPrimerSuscriptor(List<Participante> ganadores){
		Participante elGanador = ganadores.get(0);
		
		for(Participante current : ganadores) {
			if(current.getFechaDeInscripcion().before(elGanador.getFecNac()))
				elGanador = current;
		}
		return elGanador;
	}

	

	public Float calcularAlicuota() {
		return financiamiento.totalAabonar(this) / cantidadDeCuotas;
	}
	
	public Participante elegirGanador() throws ExceptionParticipante{
		
		Participante elGanador;
		
		if(participantesDisponibles().size()>0){
			elGanador = modoDeAdjudicacion.elegirConcursante(this);
			elGanador.fuiAdjudicado();
		}else{
			throw new ExceptionParticipante();
		}
		
		return elGanador;
	}

	public Float montoDelFinanciamiento() {
		return financiamiento.totalAabonar(this);
	}

	
}