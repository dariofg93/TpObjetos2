package persona;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

public class ParticipanteTest {

	Participante participanteTest;
	Cliente clienteMock;
	
	@Before
	public void setUp(){
		clienteMock = mock(Cliente.class);
		participanteTest = new Participante(clienteMock);
	}
	
	@Test
	public void testcuotasPagas() {
		assertTrue(participanteTest.cuotasPagas().equals(0));
		
	}

}
