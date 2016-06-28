package persona;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import comprobantes.ComprobanteDePago;

public class ParticipanteTest {

	Participante participanteTest;
	Cliente clienteMock;
	DateTime fechaNac;
	ComprobanteDePago comprobanteMock;
	
	@Before
	public void setUp(){
		clienteMock = mock(Cliente.class);
		fechaNac = new DateTime(1994,9,25,00,00,00);
		
		when(clienteMock.getFecNac()).thenReturn(fechaNac);
		participanteTest = new Participante(clienteMock);
		comprobanteMock = mock(ComprobanteDePago.class);
	}
	
	
	@Test
	public void testcuotasPagas() {
		assertTrue(participanteTest.cuotasPagas().equals(0));
		
	}

	
	@Test
	public void testEstaDisponible() {
		assertTrue(participanteTest.estaDisponible());
	}
	
	
	@Test
	public void testFuiAdjudicado() {
		assertTrue(participanteTest.estaDisponible());
		participanteTest.fuiAdjudicado();
		assertTrue(participanteTest.estaDisponible().equals(false));
	}
	
	
	@Test
	public void testGetCliente() {
		assertTrue(participanteTest.getCliente().equals(clienteMock));
	}
	
	
	@Test
	public void testGetFecNac() {
		assertTrue(participanteTest.getFecNac().equals(fechaNac));
	}
	
	
	@Test
	public void testGetFechaDeInscripcion() {
		assertTrue(participanteTest.getFechaDeInscripcion().equals(new DateTime()));
	}
	
	
	@Test
	public void testCuotasPagas(){
		assertTrue(participanteTest.cuotasPagas().equals(0));
	}
	
	
	@Test
	public void testAgregarCuota() {
		participanteTest.agregarCuota(comprobanteMock);
		assertTrue(participanteTest.cuotasPagas().equals(1));
	}
	
	
	@Test
	public void testEdad() {
		assertTrue(participanteTest.edad().equals(21));
		
	}
	
	
}
