package excepciones;

public class ExceptionParticipante extends RuntimeException {

	public ExceptionParticipante(){
		super("Sin participantes disponibles.");
	}

}
