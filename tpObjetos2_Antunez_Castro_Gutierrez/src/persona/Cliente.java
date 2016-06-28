package persona;


import org.joda.time.DateTime;

public class Cliente{
	
	protected String nombreYApellido;
	protected Integer dni;
	protected String mail;
	protected String direccion;
	protected DateTime fechaNac;
	protected DateTime fechaDeIngreso;
	
	public Cliente(Integer unDni, String nyAp, String mail, String dir, DateTime nacimiento, DateTime ingreso){
		
		this.nombreYApellido = nyAp;
		this.dni = unDni;
		this.mail = mail; 
		this.direccion = dir;
		this.fechaNac = nacimiento;
		this.fechaDeIngreso = ingreso;
	}

	public DateTime getFecNac() {
		return this.fechaNac;
	}

	public DateTime getFechaDeInscripcion() {
		return this.fechaDeIngreso;
	}
}