package excepciones;

@SuppressWarnings("serial")
public class SinParticipantesExcepcion extends Exception {

	public SinParticipantesExcepcion(){
		super("Sin participantes disponibles.");
	}

}
