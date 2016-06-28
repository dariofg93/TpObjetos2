package planDeAhorro;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import concesionaria.Concesionaria;
import financiamiento.Financiamiento;
import financiamiento.Plan100;
import modeloRegistroYequipamiento.Modelo;
import modoDeAdjudicacion.MayorCobertura;
import modoDeAdjudicacion.ModoDeAdjudicacion;
import persona.Cliente;
import persona.Participante;

public class PlanDeAhorroTest {

	PlanDeAhorro planTest;
	Integer nroDeGrupo;
	Modelo modeloMock;
	Financiamiento financiamientoMock;
	ModoDeAdjudicacion adjudicacionMock;
	Concesionaria consecionariaMock;
	Integer cantCuotas;
	Cliente clienteMock;
	Participante participanteMock;
	
	@Before
	public void setUp() {
		nroDeGrupo = 1;
		modeloMock = mock(Modelo.class);
		financiamientoMock = mock(Plan100.class);
		adjudicacionMock = mock(MayorCobertura.class);
		consecionariaMock = mock(Concesionaria.class);
		cantCuotas = 36;
		financiamientoMock = new Plan100();
		clienteMock = new Cliente(null, null, null, null, new Date(1/1/2004), null);//mock(Cliente.class);
		
		
		
		planTest = new PlanDeAhorro(nroDeGrupo,modeloMock,financiamientoMock,cantCuotas,adjudicacionMock,consecionariaMock);
		
	}

	
	@Test
	public void testNroDeGrupo() {
		assertTrue(planTest.getNumeroDeGrupo().equals(nroDeGrupo));
	}

	@Test
	public void testGetConcesionaria() {
		assertTrue(planTest.getConcesionaria().equals(consecionariaMock));
	}
	
	@Test
	public void testGetFinanciamiento() {
		assertTrue(planTest.getFinanciamiento().equals(financiamientoMock));
	}
	
	
	@Test
	public void testGetModelo() {
		assertTrue(planTest.getModelo().equals(modeloMock));
	}
	

	@Test
	public void testGetParticipantes() {
		List<Participante> sinParticipantes = new ArrayList<Participante>(); 
		assertTrue(planTest.getParticipantes().equals(sinParticipantes));
	}
	
	
	@Test
	public void testCantidadDeParticipantes() {
		assertTrue(planTest.cantidadDeParticipantes().equals(0));
	}
	
	
	@Test
	public void testSuscribirCliente() {
		assertTrue(planTest.cantidadDeParticipantes().equals(0));
		planTest.suscribirCliente(clienteMock);
		assertTrue(planTest.cantidadDeParticipantes().equals(1));
	}
	
	
	@Test
	public void testParticipantesDisponibles() {
		// Un Plan sin participantes, no tiene participantes disponibles.
		assertTrue(planTest.participantesDisponibles().isEmpty());
		
		// Un Plan con un participante recien inscripto, si tiene.
		planTest.suscribirCliente(clienteMock);
		assertFalse(planTest.participantesDisponibles().isEmpty());
		
		// Si ese Participante se le adjudica un vehiculo, el plan vuelve
		// a no tener participatnes disponibles.
		Participante unParticipante = planTest.getParticipantes().get(0);
		unParticipante.fuiAdjudicado();
		assertTrue(planTest.participantesDisponibles().isEmpty());
	}
	
	
	@Test
	public void testCantidadDeParticipantesDisponibles() {
		assertTrue(planTest.cantidadDeParticipantesDisponibles().equals(0));
		
		planTest.suscribirCliente(clienteMock);
		assertTrue(planTest.cantidadDeParticipantesDisponibles().equals(1));
		
		Participante unParticipante = planTest.getParticipantes().get(0);
		unParticipante.fuiAdjudicado();
		assertTrue(planTest.cantidadDeParticipantesDisponibles().equals(0));
	}
	
	
	@Test
	public void testHayParticipantesDisponibles() {
		assertFalse(planTest.hayParticipantesDisponibles());
		
		planTest.suscribirCliente(clienteMock);
		assertTrue(planTest.hayParticipantesDisponibles());
		
		Participante unParticipante = planTest.getParticipantes().get(0);
		unParticipante.fuiAdjudicado();
		assertFalse(planTest.hayParticipantesDisponibles());
	}
	
	/** Siguendo... */
	@Test
	public void testLosQueMasPagaron() {
		assertTrue(planTest.losQueMasPagaron().isEmpty());
	}
	
}
