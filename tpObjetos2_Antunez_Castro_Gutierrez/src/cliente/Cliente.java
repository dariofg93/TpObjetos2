package cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import planDeAhorro.PlanDeAhorro;
import recibo.Recibo;

public class Cliente {
	
	private String nombreYApellido;
	private Integer dni;
	private String correoElectronico;
	private Integer direccion;
	private Date fechaDeNacimiento;
	private Date fechaDeIngreso;
	private List<Recibo> recibos;
	
	public Cliente(Integer unDni, String nyAp, String mail, Integer dir, Date nacimiento, Date ingreso){
		
		this.nombreYApellido = nyAp;
		this.dni = unDni;
		this.correoElectronico = mail; 
		this.direccion = dir;
		this.fechaDeNacimiento = nacimiento;
		this.fechaDeIngreso = ingreso;
		this.recibos = new ArrayList<Recibo>();
	}

	public Integer cuotasPagas(PlanDeAhorro plan) {
		Recibo r = reciboDelPlan(plan);
		return r.getCuotasPagas();
	}

	private Recibo reciboDelPlan(PlanDeAhorro plan) {
		Recibo r = null;
		for(Recibo current : recibos){
			if(current.getNumeroDeGrupo()==plan.getNumeroDeGrupo())
				r = current;	
		}
		return r;
	}

	public Date getFecNac() {
		return this.fechaDeNacimiento;
	}

	public Date getFechaDeInscripcion() {
		return this.fechaDeIngreso;
	}
}