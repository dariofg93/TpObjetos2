package planDeAhorro;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import concesionaria.Concesionaria;
import financiamiento.Financiamiento;
import financiamiento.Plan100;
import financiamiento.Plan70y30;
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
	Participante unParticipanteMock;
	Participante otroParticipanteMock;
	DateTime unaFechaDeNac;
	DateTime otraFechaDeNac;
	DateTime unaFechaDeInscripcion;
	
	@Before
	public void setUp() {
		nroDeGrupo = 1;
		modeloMock = mock(Modelo.class);
		when(modeloMock.getValorDeVenta()).thenReturn(32400f);
		financiamientoMock = mock(Plan100.class);
		adjudicacionMock = mock(MayorCobertura.class);
		consecionariaMock = mock(Concesionaria.class);
		cantCuotas = 36;
		financiamientoMock = new Plan100();
		clienteMock = mock(Cliente.class);
		unaFechaDeNac = new DateTime(1984,4,12,00,00);
		otraFechaDeNac = new DateTime(1990,6,26,00,00);
		unaFechaDeInscripcion = new DateTime(1990,12,4,00,00);
		
		unParticipanteMock = mock(Participante.class);
		when(unParticipanteMock.estaDisponible()).thenReturn(true);
		when(unParticipanteMock.cuotasPagas()).thenReturn(1);
		when(unParticipanteMock.getFecNac()).thenReturn(unaFechaDeNac);
		when(unParticipanteMock.tiempoDesdeInscripcion()).thenReturn(2);
		
		otroParticipanteMock = mock(Participante.class);
		when(otroParticipanteMock.estaDisponible()).thenReturn(true);
		when(otroParticipanteMock.cuotasPagas()).thenReturn(4);
		when(otroParticipanteMock.getFecNac()).thenReturn(otraFechaDeNac);
		when(otroParticipanteMock.tiempoDesdeInscripcion()).thenReturn(5);
		
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


	@Test
	public void testLosQueMasPagaron() {
		assertTrue(planTest.losQueMasPagaron().isEmpty());
		
		// En un caso normal utilizaria suscribirCliente(clienteMock),
		// pero hay que controlar los resultados de las consultas de cada Participante.
		// Esto se debe a que un Participante se fabrica a traves de un Cliente.
		planTest.getParticipantes().add(unParticipanteMock);
		planTest.getParticipantes().add(otroParticipanteMock);
		
		assertFalse(planTest.losQueMasPagaron().contains(unParticipanteMock));
		assertTrue(planTest.losQueMasPagaron().contains(otroParticipanteMock));
	}
	
	
	@Test
	public void testMayorCantidadCuotasPagas() {
		planTest.getParticipantes().add(unParticipanteMock);
		planTest.getParticipantes().add(otroParticipanteMock);
		
		assertTrue(planTest.mayorCantidadCuotasPagas().equals(4));
	}
	
	
	@Test
	public void testLosMasViejos() {
		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(unParticipanteMock);
		participantes.add(otroParticipanteMock);
		// System.out.println(planTest.losMasViejos(participantes));
		assertTrue(planTest.losMasViejos(participantes).contains(unParticipanteMock));
	}
	
	
	@Test
	public void testElMasViejo() {
		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(unParticipanteMock);
		participantes.add(otroParticipanteMock);
		assertTrue(planTest.elMasViejo(participantes).equals(unParticipanteMock));
	}
	
	
	@Test
	public void testElPrimerSuscriptor() {
		
		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(unParticipanteMock);
		participantes.add(otroParticipanteMock);
		assertTrue(planTest.elPrimerSuscriptor(participantes).equals(otroParticipanteMock));
	}
	
	
	@Test
	public void testCalcularAlicuota() {
		// El Financiamiento es del Plan100, por lo que la alicuota deberia ser de $900
		assertTrue(planTest.calcularAlicuota().equals(900f));
		
		// El Financiamiento ahora es del Plan70y30, por lo que la alicuota deberia ser de $630
		planTest.setFinanciamiento(new Plan70y30());
		assertTrue(planTest.calcularAlicuota().equals(630f));
	}
	
	
	
	
	
}
