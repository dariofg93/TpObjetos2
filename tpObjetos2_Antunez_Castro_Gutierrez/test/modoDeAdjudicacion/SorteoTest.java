package modoDeAdjudicacion;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import excepciones.ExceptionParticipante;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

public class SorteoTest {

	Sorteo sorteoTest;
	Random randomMock;
	
	@Before
	public void setUp(){
		randomMock = mock(Random.class);
		sorteoTest = new Sorteo(randomMock);
	}
	
	@Test
	public void testElegirGanador() {
//		Participante participanteMock;
//		PlanDeAhorro planMock = mock(PlanDeAhorro.class);	
//		
//		participanteMock = mock(Participante.class);
//		ArrayList<Participante> disponibles = new ArrayList(Arrays.asList(participanteMock));
//		
//		when(planMock.participantesDisponibles()).thenReturn(disponibles);
//		when(randomMock.nextDouble()).thenReturn(0.5);
//		
//		assertEquals(sorteoTest.elegirConcursante(planMock),participanteMock);
//		
//		disponibles = new ArrayList<Participante>();
//		when(planMock.participantesDisponibles()).thenReturn(disponibles);
//		
//		
		
		Participante participanteMock = mock(Participante.class);
		ArrayList<Participante> disponibles = new ArrayList<Participante>();
		PlanDeAhorro planMock = mock(PlanDeAhorro.class);	
		when(planMock.participantesDisponibles()).thenReturn(disponibles);
		when(randomMock.nextDouble()).thenReturn(0.5);
		
		Participante winner = null;
		for(int i = 0; i<2; i++){
			
			try{
				winner = sorteoTest.elegirConcursante(planMock);
			}catch(ExceptionParticipante str){
				//assertEquals(sorteoTest.elegirConcursante(planMock),winner);
				disponibles = new ArrayList(Arrays.asList(participanteMock));
			}
		}
		
	}
}
