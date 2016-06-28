package inicializadores;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import persona.Cliente;

public class ParticipanteCreatorTest {

	ParticipanteCreator creadorTest;
	Cliente clienteMock;
	
	
	@Before
	public void setUp() throws Exception {
		creadorTest = new ParticipanteCreator();
		clienteMock = mock(Cliente.class);
	}

	@Test
	public void test() {
		creadorTest.crearParticipante(clienteMock);
	}

}
