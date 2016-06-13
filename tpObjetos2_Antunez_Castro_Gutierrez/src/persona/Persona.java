package persona;

import java.util.Date;

public class Persona {
	
	protected String nombreYApellido;
	protected Integer dni;
	protected String mail;
	protected String direccion;
	protected Date fechaNac;
	protected Date fechaDeIngreso;
	
	public Persona(Integer unDni, String nyAp, String mail, String dir, Date nacimiento, Date ingreso){
		
		this.nombreYApellido = nyAp;
		this.dni = unDni;
		this.mail = mail; 
		this.direccion = dir;
		this.fechaNac = nacimiento;
		this.fechaDeIngreso = ingreso;
	}

	public Date getFecNac() {
		return this.fechaNac;
	}

	public Date getFechaDeInscripcion() {
		return this.fechaDeIngreso;
	}
}
