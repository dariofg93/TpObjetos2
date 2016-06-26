package excepciones;

@SuppressWarnings("serial")
public class SinPlanesExcepcion extends Exception{

	public SinPlanesExcepcion(){
		this("No hay planes");
	}
	
	public SinPlanesExcepcion(String str){
		super(str+" en la concesionaria");
	}
}
