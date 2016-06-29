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
	
	/**	Prueba que la fecha de inscripcion del Participante
	 *  es de cuando este es creado. 
	 * Observacion:
	 *  Al momento de crear un Participante, su fecha de inscripcion se genera
	 *  en el mismo instante en el que es creado. Entonces, puede que al momento
	 *  de compilar los test, este test genere un AssertionError a causa de que el instante
	 *  de la fecha de inscripcion es diferente al de compilacion del test.
	 *  Si falla, se puede correr otra vez el test y va a funcionar. */
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
		
		// Para contemplar el otro caso, deberia llegar al punto de que hoy es el mes
		// o es el dia del cumpleaños del Participante.
		DateTime hoy = new DateTime();
		Integer dia = hoy.getDayOfMonth();
		Integer mes = hoy.getMonthOfYear();
		DateTime unDiaComoHoy = new DateTime(participanteTest.getFecNac().getYear(),mes,dia,00,00);
		when(clienteMock.getFecNac()).thenReturn(unDiaComoHoy);
		assertTrue(participanteTest.edad().equals(22));
	}
	
	
	@Test
	public void testTiempoDesdeInscripcion() {
		assertTrue(participanteTest.tiempoDesdeInscripcion().equals(0));
	}
}
