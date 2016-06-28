package planDeAhorro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import concesionaria.Concesionaria;
import excepciones.ExceptionParticipante;
import excepciones.SinStockExcepcion;
import financiamiento.Financiamiento;
import inicializadores.ParticipanteCreator;
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
	private ParticipanteCreator creadorDeParticipante;
	
	public PlanDeAhorro(Integer n, Modelo unModelo, Financiamiento unFinanciamiento,
						Integer cantCuotas, ModoDeAdjudicacion unModo,
						Concesionaria unaConcesionaria){
		
		this.numeroDeGrupo = n;
		this.modeloSuscripto = unModelo;
		this.suscriptos = new ArrayList<Participante>();
		this.financiamiento = unFinanciamiento;
		this.cantidadDeCuotas = cantCuotas;
		this.modoDeAdjudicacion = unModo;
		this.concesionaria = unaConcesionaria;
		this.creadorDeParticipante = new ParticipanteCreator();
	}
	/** Retorna el numero de grupo que identifica al Plan
	 * perteneciente a una Concesionaria */
	public Integer getNumeroDeGrupo() {
		return numeroDeGrupo;
	}
	
	/** Retorna la Consecionaria al que pertenece este Plan.*/
	public Concesionaria getConcesionaria() {
		return concesionaria;
	}
	
	/** Retorna el modo de Financiamiento del Plan. */
	public Financiamiento getFinanciamiento() {
		return financiamiento;
	}
	
	/** Retorna el Modelo que se vende mediante este Plan. */
	public Modelo getModelo() {
		return this.modeloSuscripto;
	}
	
	/** Retorna los Clientes de la Concesionaria que se inscribieron
	 * en este Plan, y pasaron a ser Participantes. */
	public List<Participante> getParticipantes() {
		return this.suscriptos;
	}
	
	/** Retorna la cantidad de Participantes.*/
	public Integer cantidadDeParticipantes() {
		return this.suscriptos.size();
	}

	
	/** Dado un Cliente, lo inscribe al Plan,
	 *  y pasaria a ser un Participante de este. */
	public void suscribirCliente(Cliente c) {
		suscriptos.add(creadorDeParticipante.crearParticipante(c));
	}
	

	/** Retorna una lista con los Participantes que esten disponibles
	 * para el sorteo. */
	public List<Participante> participantesDisponibles(){
		List<Participante> disponibles = this.getParticipantes()
										 .stream()
										 .filter(participante -> participante.estaDisponible())
										 .collect(Collectors.toList());
		
		return disponibles;
	}
	
	/** Retorna la cantidad de Participantes que estan disponibles. */
	public Integer cantidadDeParticipantesDisponibles() {
		return participantesDisponibles().size();
	}
	
	/** Devuelve True si hay participantes disponibles. */
	public Boolean hayParticipantesDisponibles() {
		return cantidadDeParticipantesDisponibles() > 0;
	}
	
	
	/** Retorna los Participantes que mas cuotas llevan pagando. */
	public List<Participante> losQueMasPagaron(){
		List<Participante>  ganadores = this.participantesDisponibles()
										.stream()
										.filter(participante -> participante.cuotasPagas() == mayorCantidadCuotasPagas())
										.collect(Collectors.toList());
		//System.out.println(ganadores);
		return ganadores;		
	}

	/** Devuelve la cantidad de cuotas mas grande que algun
	 * Participante disponible lleva pagando. */
	public Integer mayorCantidadCuotasPagas() {
		Integer mayorPagas = 0;
		Participante participanteConMasCuotasPagas = this.participantesDisponibles()
												     .stream()
												     .max((Participante p1, Participante p2)->p1.cuotasPagas().compareTo(p2.cuotasPagas()))
												     .get();
		mayorPagas = participanteConMasCuotasPagas.cuotasPagas();
		return mayorPagas;
	}

	
	/** Retorna una lista con los participantes que tengan mayor edad. */
	public List<Participante> losMasViejos(List<Participante> ganadores){
		List<Participante> mayores = ganadores
									 .stream()
									 .filter(participante -> participante.edad().equals(elMasViejo(ganadores).edad()))
									 .collect(Collectors.toList());
		return mayores;
	}

	/** Retorna el participante con mayor edad. */
	public Participante elMasViejo(List<Participante> ganadores) {
		
		Participante mayor = ganadores
							 .stream()
							 .max((Participante p1, Participante p2)->p1.edad().compareTo(p2.edad()))
							 .get();
		return mayor;
	}

	
	/** Dada una lista de Participantes, retorna el primer Participante
	 *  que se inscribio a este Plan. */
	public Participante elPrimerSuscriptor(List<Participante> ganadores){
		Participante elGanador = ganadores
								 .stream()
								 .max((Participante p1, Participante p2)->p1.tiempoDesdeInscripcion().compareTo(p2.tiempoDesdeInscripcion()))
								 .get();
		return elGanador;
	}

	
	/** Retorna el monto que cada Participante debe pagar en una cuota
	 * de este Plan. */
	public Float calcularAlicuota() {
		return financiamiento.totalAabonarDeCuota(this) / cantidadDeCuotas;
	}
	
	/** Cambia el modo de Financiamiento. */
	public void setFinanciamiento(Financiamiento unFinanciamiento){
		this.financiamiento = unFinanciamiento;
	}
	
	
	/** Se realiza un sorteo, y retorna el Participante que haya ganado.
	 * Observacion: Si el Plan no tiene Participantes disponibles, va a
	 * devolver un ExceptionParticipante. */
	public Participante elegirGanador() throws ExceptionParticipante{
		
		Participante elGanador;
		
		if(hayParticipantesDisponibles()){
			elGanador = modoDeAdjudicacion.elegirConcursante(this);
			elGanador.fuiAdjudicado();
		}else{
			throw new ExceptionParticipante();
		}
		
		return elGanador;
	}

	/** Retorna el monto total de las cuotas que deben pagar los Participantes,
	 * por parte del Modelo. */
	public Float montoDelFinanciamientoDeCuota() {
		return financiamiento.totalAabonarDeCuota(this);
	}
	
	/** Retorna el monto total de la adjudicacion que deben pagar los Participantes,
	 * por parte del Modelo. */
	public Float montoDelFinanciamientoDeAdjudicacion() {
		return financiamiento.totalAabonarDeAdjudicacion(this);
	}

    /** Retorna el monto del flete correspondiente a la distancia entre
     * la Concesionaria, y la Planta mas cercana que tenga un vehiculo disponible
	 * del Modelo de este Plan.
	 *	Observacion: Si dicha Planta no existe, va a lanzar un SinStockExcepcion. 
     * @throws SinStockExcepcion */
	public Float montoDelFlete() throws SinStockExcepcion {
		return this.getConcesionaria().gastoDeFlete(
				
				this.getConcesionaria().getFabrica().plantaMasCercanaAConcesionaria(
						
						this.getModelo()));
	}
	

	
}