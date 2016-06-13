package persona;

import java.util.Date;

public class Cliente extends Persona{
	
	public Cliente(Integer unDni, String nyAp, String mail, String dir, Date nacimiento, Date ingreso){
		super(unDni,nyAp,mail,dir,nacimiento,ingreso);
	}
}