package persona;


import org.joda.time.DateTime;

public class Cliente{
	
	@SuppressWarnings("unused")
	private String nombreYApellido;
	@SuppressWarnings("unused")
	private Integer dni;
	@SuppressWarnings("unused")
	private String mail;
	@SuppressWarnings("unused")
	private String direccion;
	private DateTime fechaNac;
	private DateTime fechaDeIngreso;
	
	public Cliente(Integer unDni, String nyAp, String mail, String dir, DateTime nacimiento, DateTime ingreso){
		
		this.nombreYApellido = nyAp;
		this.dni = unDni;
		this.mail = mail; 
		this.direccion = dir;
		this.fechaNac = nacimiento;
		this.fechaDeIngreso = ingreso;
	}

	//Getters:
	public DateTime getFecNac() {
		return this.fechaNac;
	}
	public DateTime getFechaDeInscripcion() {
		return this.fechaDeIngreso;
	}
}