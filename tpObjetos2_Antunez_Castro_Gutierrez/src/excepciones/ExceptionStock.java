package excepciones;

public class ExceptionStock extends RuntimeException{

	public ExceptionStock(){
		this("Sin stock");
	}
	
	public ExceptionStock(String str){
		super(str+" en el modelo");
	}
}
