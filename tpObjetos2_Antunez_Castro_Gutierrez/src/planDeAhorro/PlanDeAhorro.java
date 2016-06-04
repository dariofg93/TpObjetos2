package planDeAhorro;

import java.util.ArrayList;
import java.util.List;

import cliente.Cliente;
import financiamiento.Financiamiento;
import modeloRegistroYequipamiento.Modelo;
import modoDeAdjudicacion.ModoDeAdjudicacion;

public class PlanDeAhorro {
	
	private Integer numeroDeGrupo;
	private Modelo modeloSuscripto;
	private List<Cliente> suscriptos;
	private Financiamiento financiamiento;
	private Integer cantidadDeCuotas;
	private ModoDeAdjudicacion modoDeAdjudicacion;
	
	public PlanDeAhorro(Integer n, Modelo unModelo, Financiamiento unFinanciamiento,
						Integer cantCuotas, ModoDeAdjudicacion unModo){
		this.numeroDeGrupo = n;
		this.modeloSuscripto = unModelo;
		this.suscriptos = new ArrayList<Cliente>();
		this.financiamiento = unFinanciamiento;
		this.cantidadDeCuotas = cantCuotas;
		this.modoDeAdjudicacion = unModo;
	}

	public int cantidadDeParticipantes() {
		return this.suscriptos.size();
	}

	public Modelo getModelo() {
		return this.modeloSuscripto;
	}

	public List<Cliente> getParticipantes() {
		return this.suscriptos;
	}
	
	public List<Cliente> losQueMasPagaron(){
		List<Cliente>  ganadores = new ArrayList<Cliente>();
		
		for(Cliente cliente:  this.suscriptos){
			if(cliente.cuotasPagas(this)==mayorCantidadCuotasPagas())
				ganadores.add(cliente);
		}
		return ganadores;		
	}

	private Integer mayorCantidadCuotasPagas() {
		Integer mayorPagas = 0;
		
		for(Cliente current : suscriptos) {
			if(current.cuotasPagas(this)>mayorPagas)
				mayorPagas = current.cuotasPagas(this);
		}
		return mayorPagas;
	}
	
	public List<Cliente> losMasViejos(List<Cliente> ganadores){
		List<Cliente> mayores = new ArrayList<Cliente>();
		
		for(Cliente current : ganadores){
			if(current.getFecNac().equals(elmasViejo(ganadores)))
				mayores.add(current);
		}
		return mayores;
	}

	private Cliente elmasViejo(List<Cliente> ganadores) {
		Cliente mayor = ganadores.get(0);
		
		for(Cliente current : ganadores) {
			if(current.getFecNac().before(mayor.getFecNac()))
				mayor = current;
		}
		return mayor;
	}

	public Integer getNumeroDeGrupo() {
		return numeroDeGrupo;
	}
	
	public Cliente elPrimerSuscriptor(List<Cliente> ganadores){
		Cliente elGanador = ganadores.get(0);
		
		for(Cliente current : ganadores) {
			if(current.getFechaDeInscripcion().before(elGanador.getFecNac()))
				elGanador = current;
		}
		return elGanador;
	}
}