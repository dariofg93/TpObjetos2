package cupon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;

import excepciones.SinStockExcepcion;
import financiamiento.Financiamiento;
import financiamiento.Plan100;
import modeloRegistroYequipamiento.Modelo;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;
import planta.Planta;

public class CuponDeAdjudicacionTest {

	CuponDeAdjudicacion cuponTest;
	PlanDeAhorro planMock;
	Participante participanteMock;
	Planta plantaMock;
	Modelo modeloMock;
	Financiamiento financiamientoMock;
	
	@Before
	public void setUp() throws SinStockExcepcion{
		participanteMock = mock(Participante.class);
		modeloMock = mock(Modelo.class);
		when(modeloMock.getValorDeVenta()).thenReturn(1000f);
		plantaMock = mock(Planta.class);
		
		planMock = mock(PlanDeAhorro.class);
		when(planMock.getModelo()).thenReturn(modeloMock);
		
		
		cuponTest = new CuponDeAdjudicacion(planMock,participanteMock);
	}

	@Test
	public void testGetModelo() {
		assertTrue(cuponTest.getModelo().equals(modeloMock));
	}
	/**
	@Test
	public void testMontoDelFinanciamiento70Y30() {
		financiamientoMock = new Plan70y30();
		when(planMock.getFinanciamiento()).thenReturn(financiamientoMock);
		assertTrue(cuponTest.montoDelFinanciamiento(planMock).equals(300f));
	}*/
	
	@Test
	public void testMontoDelFinanciamiento100() {
		financiamientoMock = new Plan100();
		when(planMock.getFinanciamiento()).thenReturn(financiamientoMock);
		assertTrue(cuponTest.montoDelFinanciamiento(planMock).equals(0f));
	}
	
	@Test
	public void testMontoDelFlete() throws SinStockExcepcion {
		when(planMock.montoDelFlete()).thenReturn(250f);
		assertTrue(cuponTest.montoDelFlete(planMock).equals(250f));
	}

}
