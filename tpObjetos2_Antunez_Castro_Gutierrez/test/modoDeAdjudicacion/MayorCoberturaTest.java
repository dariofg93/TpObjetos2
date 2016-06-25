package modoDeAdjudicacion;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import excepciones.ExceptionParticipante;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class MayorCoberturaTest {

	MayorCobertura mayorCoberturaTest;
	PlanDeAhorro planMock;
	
	@Before
	public void setUp(){
		planMock = mock(PlanDeAhorro.class);
		mayorCoberturaTest = new MayorCobertura();
	}
	
	@Test(expected = ExceptionParticipante.class)
	public void testElegirGanador() {
		Participante participanteMock = mock(Participante.class);
		List<Participante> disponibles = Arrays.asList(participanteMock);
		
		when(planMock.hayParticipantesDisponibles()).thenReturn(true);
		when(planMock.participantesDisponibles()).thenReturn(disponibles);
		
		assertEquals(mayorCoberturaTest.elegirConcursante(planMock),participanteMock);
		////
		when(planMock.hayParticipantesDisponibles()).thenReturn(false);
		//Mockito.doThrow(new ExceptionParticipante()).when(planMock).participantesDisponibles();
		
		mayorCoberturaTest.elegirConcursante(planMock);
	}
}
