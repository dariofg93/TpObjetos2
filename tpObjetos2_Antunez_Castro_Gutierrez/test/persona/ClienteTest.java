package persona;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	Cliente testCliente;
	DateTime unaFechaDeNac;
	@Before
	public void setUp(){
	unaFechaDeNac = new DateTime(2005,12,5,00,00);	
	testCliente = new Cliente(234, "abc", "dce", "ade", unaFechaDeNac, new DateTime());
		 	
	}
	
	@Test
	public void testGetFecNac() {
		assertTrue(testCliente.getFecNac().equals(unaFechaDeNac));
	}

	@Test
	public void testGetFechaDeInscripcion() {
		assertTrue(testCliente.getFechaDeInscripcion().equals(new DateTime()));
	}
	
}
