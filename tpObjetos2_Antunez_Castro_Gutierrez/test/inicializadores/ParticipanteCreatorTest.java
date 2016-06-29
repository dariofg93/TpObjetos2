package inicializadores;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import persona.Cliente;
import planDeAhorro.PlanDeAhorro;

public class ParticipanteCreatorTest {

	ParticipanteCreator creadorTest;
	Cliente clienteMock;
	PlanDeAhorro planMock;
	
	
	@Before
	public void setUp() throws Exception {
		creadorTest = new ParticipanteCreator();
		clienteMock = mock(Cliente.class);
		planMock = mock(PlanDeAhorro.class);
	}

	@Test
	public void test() {
		creadorTest.crearParticipante(clienteMock,planMock);
	}
}
