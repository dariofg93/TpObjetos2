package modoDeAdjudicacion;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import excepciones.SinParticipantesExcepcion;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class SorteoTest {

	Sorteo sorteoTest;
	Random randomMock;
	PlanDeAhorro planMock;
	
	@Before
	public void setUp(){
		randomMock = mock(Random.class);
		planMock = mock(PlanDeAhorro.class);
		sorteoTest = new Sorteo(randomMock);
	}
	
	@Test
	public void testElegirGanadorConDisponibles() throws SinParticipantesExcepcion {
		Participante participanteMock = mock(Participante.class);
		List<Participante> disponibles = Arrays.asList(participanteMock);
		
		when(planMock.hayParticipantesDisponibles()).thenReturn(true);
		when(planMock.participantesDisponibles()).thenReturn(disponibles);
		when(randomMock.nextDouble()).thenReturn(0.5);
		
		assertEquals(sorteoTest.elegirConcursante(planMock),participanteMock);
	}
	
	@Test(expected = SinParticipantesExcepcion.class)
	public void testElegirGanadorSinDisponibles() throws SinParticipantesExcepcion {
		when(planMock.hayParticipantesDisponibles()).thenReturn(false);
		
		sorteoTest.elegirConcursante(planMock);
	}
}
