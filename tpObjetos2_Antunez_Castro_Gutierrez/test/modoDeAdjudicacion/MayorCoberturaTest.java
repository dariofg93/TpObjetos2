package modoDeAdjudicacion;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import excepciones.SinParticipantesExcepcion;
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
	
	@Test
	public void testElegirGanadorUnSoloPagador() throws SinParticipantesExcepcion {
		
		when(planMock.hayParticipantesDisponibles()).thenReturn(true);
		
		Participante participanteMock1 = mock(Participante.class);
		when(planMock.losQueMasPagaron()).thenReturn(Arrays.asList(participanteMock1));
		
		assertEquals(mayorCoberturaTest.elegirConcursante(planMock),participanteMock1);
	}
	
	@Test
	public void testElegirGanadorMasDeUnPagadorUnoMasViejo() throws SinParticipantesExcepcion {
		
		when(planMock.hayParticipantesDisponibles()).thenReturn(true);
		
		Participante participanteMock1 = mock(Participante.class);
		Participante participanteMock2 = mock(Participante.class);
		
		List<Participante> pagadores = Arrays.asList(participanteMock1,participanteMock2);
		when(planMock.losQueMasPagaron()).thenReturn(pagadores);
		when(planMock.losMasViejos(pagadores)).thenReturn(Arrays.asList(participanteMock1));
		
		assertEquals(mayorCoberturaTest.elegirConcursante(planMock),participanteMock1);
	}
	
	@Test
	public void testElegirGanadorMasDeUnPagadorVariosMasViejos() throws SinParticipantesExcepcion {
		
		when(planMock.hayParticipantesDisponibles()).thenReturn(true);
		
		Participante participanteMock1 = mock(Participante.class);
		Participante participanteMock2 = mock(Participante.class);
		
		List<Participante> pagadores = Arrays.asList(participanteMock1,participanteMock2);
		when(planMock.losQueMasPagaron()).thenReturn(pagadores);
		when(planMock.losMasViejos(pagadores)).thenReturn(pagadores);
		when(planMock.elPrimerSuscriptor(planMock.losMasViejos(pagadores))).thenReturn(participanteMock1);
		
		assertEquals(mayorCoberturaTest.elegirConcursante(planMock),participanteMock1);
	}
	
	@Test(expected = SinParticipantesExcepcion.class)
	public void testElegirGanadorNingunDisponible() throws SinParticipantesExcepcion {
		
		when(planMock.hayParticipantesDisponibles()).thenReturn(false);
		
		mayorCoberturaTest.elegirConcursante(planMock);
	}
}