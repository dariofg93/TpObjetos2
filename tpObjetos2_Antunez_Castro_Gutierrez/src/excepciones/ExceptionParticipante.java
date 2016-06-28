package excepciones;

@SuppressWarnings("serial")
public class ExceptionParticipante extends Exception {

	public ExceptionParticipante(){
		super("Sin participantes disponibles.");
	}

}
