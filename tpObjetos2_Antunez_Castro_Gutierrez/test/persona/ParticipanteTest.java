package persona;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import comprobantes.ComprobanteDePago;
import concesionaria.Concesionaria;
import cupon.CuponDeAdjudicacion;
import excepciones.SinStockExcepcion;
import inicializadores.CuponCreator;
import modeloRegistroYequipamiento.Modelo;
import planDeAhorro.PlanDeAhorro;

public class ParticipanteTest {

	Participante participanteTest;
	Cliente clienteMock;
	DateTime fechaNac;
	ComprobanteDePago comprobanteMock;
	PlanDeAhorro planMock;
	
	@Before
	public void setUp(){
		clienteMock = mock(Cliente.class);
		planMock = mock(PlanDeAhorro.class);
		fechaNac = new DateTime(1994,9,25,00,00,00);
		
		when(clienteMock.getFecNac()).thenReturn(fechaNac);
		participanteTest = new Participante(clienteMock,planMock);
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
	public void testCuotasPagas(){
		assertTrue(participanteTest.cuotasPagas().equals(0));
	}
	
	@Test
	public void testPagarCuota() throws SinStockExcepcion {
		CuponCreator creadorCuponMock = mock(CuponCreator.class);
		Concesionaria concesionariaMock = mock(Concesionaria.class);
		Modelo modeloMock = mock(Modelo.class);
		CuponDeAdjudicacion cuponMock = mock(CuponDeAdjudicacion.class);
		
		when(planMock.getConcesionaria()).thenReturn(concesionariaMock);
		when(planMock.getModelo()).thenReturn(modeloMock);
		when(concesionariaMock.gastoAdministrativos()).thenReturn(50f);
		when(concesionariaMock.montoDelSeguro(participanteTest,modeloMock)).thenReturn(100f);
		when(planMock.getConcesionaria()).thenReturn(concesionariaMock);
		when(planMock.calcularAlicuota()).thenReturn(150f);
		
		when(planMock.getCuotas()).thenReturn(20);
		for(int i = 0; i<19;i++)
			participanteTest.pagarCuota();
		assertTrue(participanteTest.cuotasPagas().equals(19));
		
		participanteTest.setCreadorCupon(creadorCuponMock);
		when(creadorCuponMock.crearCupon(planMock, participanteTest)).thenReturn(cuponMock);
		participanteTest.pagarCuota();
		
		verify(concesionariaMock).emitirCupon(cuponMock);
		verify(planMock).dessuscribirParticipante(participanteTest);
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
