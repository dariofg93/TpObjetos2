package persona;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	Cliente testCliente;
	
	@Before
	public void setUp(){
	testCliente = new Cliente(234, "abc", "dce", "ade", new Date(12/05/2005), new Date());
		 	
	}
	
	@Test
	public void testGetFecNac() {
		Date fechaDeNacimiento = new Date(12/05/2005);
		assertTrue(testCliente.getFecNac().equals(fechaDeNacimiento));
	}

	@Test
	public void testGetFechaDeInscripcion() {
		Date fechaDeInscripcion = new Date();
		assertTrue(testCliente.getFechaDeInscripcion().equals(fechaDeInscripcion));
	}
	
}
