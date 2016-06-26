package excepciones;

@SuppressWarnings("serial")
public class SinStockExcepcion extends Exception{

	public SinStockExcepcion(){
		this("Sin stock");
	}
	
	public SinStockExcepcion(String str){
		super(str+" del modelo");
	}
}
