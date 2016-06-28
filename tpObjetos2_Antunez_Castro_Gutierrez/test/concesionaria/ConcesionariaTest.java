package concesionaria;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import aseguradora.CompaniaAseguradora;
import calculadora.CalculadorDeDistancia;
import cupon.CuponDeAdjudicacion;
import excepciones.ExceptionParticipante;
import excepciones.SinPlanesExcepcion;
import excepciones.SinStockExcepcion;
import fabrica.Fabrica;
import inicializadores.CuponCreator;
import modeloRegistroYequipamiento.Modelo;
import persona.Cliente;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;
import planta.Planta;

public class ConcesionariaTest {

	Concesionaria concesionariaTest;
	Modelo modeloMock;
	Fabrica fabricaMock;
	Planta plantaMock;
	CuponDeAdjudicacion cuponMock;
	PlanDeAhorro planMock;
	
	@Before
	public void setUp(){
		concesionariaTest = new Concesionaria("Roque Saenz Peña 352", 150.0f);
		
		modeloMock = mock(Modelo.class);
		fabricaMock = mock(Fabrica.class);
		plantaMock = mock(Planta.class);
		cuponMock = mock(CuponDeAdjudicacion.class);
			when(cuponMock.getModelo()).thenReturn(modeloMock);
		planMock = mock(PlanDeAhorro.class);
	}
	
	@Test
	public void testCrearPlan() {
		planMock = mock(PlanDeAhorro.class);
		concesionariaTest.crearPlan(planMock);
		
		assertTrue(concesionariaTest.getPlanes().contains(planMock));
	}
	
	@Test
	public void testRegistrarCliente() {
		Cliente	clienteMock = mock(Cliente.class);
		concesionariaTest.registrarCliente(clienteMock);
		
		assertTrue(concesionariaTest.getClientes().contains(clienteMock));
	}
	
	@Test
	public void testLosDiezPlanesConMasSubscriptos() throws SinPlanesExcepcion {
		PlanDeAhorro planMock1 = mock(PlanDeAhorro.class);
			when(planMock1.cantidadDeParticipantesDisponibles()).thenReturn(1);
			concesionariaTest.crearPlan(planMock1);
		PlanDeAhorro planMock2 = mock(PlanDeAhorro.class);
			when(planMock2.cantidadDeParticipantesDisponibles()).thenReturn(2);
			concesionariaTest.crearPlan(planMock2);
		
		List<PlanDeAhorro> planes = Arrays.asList(planMock2,planMock1);
			
		assertEquals(concesionariaTest.losDiezPlanesConMasSubscriptos(),planes);
	}

	@Test(expected = SinPlanesExcepcion.class)
	public void testLosDiezPlanesConMasSubscriptosSinPlanes() throws SinPlanesExcepcion {			
		concesionariaTest.losDiezPlanesConMasSubscriptos();
	}

	@Test
	public void testEmitirCupon() throws SinStockExcepcion {		
		concesionariaTest.setFabrica(fabricaMock);
		concesionariaTest.emitirCupon(cuponMock);
		
		verify(fabricaMock).quitarEjemplar(modeloMock);
	}
	
	@Test
	public void testRegistrarPagoDelCupon() throws SinStockExcepcion {
		concesionariaTest.setFabrica(fabricaMock);
		concesionariaTest.emitirCupon(cuponMock);
		concesionariaTest.registrarPagoDelCupon(cuponMock);
		
		assertFalse(concesionariaTest.getCupones().contains(cuponMock));
	}
	
	@Test
	public void testGastoDeFlete() {
		CalculadorDeDistancia calculadoraMock = mock(CalculadorDeDistancia.class);
		
		when(calculadoraMock.calcularDistancia(plantaMock )).thenReturn(250);
		concesionariaTest.setCalculadora(calculadoraMock);
		
		assertTrue(concesionariaTest.gastoDeFlete(plantaMock).equals(5125f));
	}
	
	@Test
	public void testStockDisponible() throws SinStockExcepcion{
		concesionariaTest.setFabrica(fabricaMock);
		when(fabricaMock.stock(modeloMock)).thenReturn(5);
		assertTrue(concesionariaTest.stock(modeloMock).equals(5));
	}
	
	@Test(expected = SinStockExcepcion.class)
	public void testStockNoDisponible() throws SinStockExcepcion{
		Mockito.doThrow(new SinStockExcepcion()).when(plantaMock).stock(modeloMock);
		concesionariaTest.stock(modeloMock);
	}
	
	@Test
	public void testMontoDelSeguro() {
		Participante participanteMock = mock(Participante.class);
		CompaniaAseguradora compañiaMock = mock(CompaniaAseguradora.class);
		
		concesionariaTest.setCompañia(compañiaMock);
		when(compañiaMock.montoDelSeguro(participanteMock,modeloMock)).thenReturn(250f);
		
		assertTrue(concesionariaTest.montoDelSeguro(participanteMock,modeloMock).equals(250f));
	}
	
	@Test
	public void testSuscribirClienteAPlan() throws SinPlanesExcepcion {
		PlanDeAhorro planMock2 = mock(PlanDeAhorro.class);
		PlanDeAhorro planMock3 = mock(PlanDeAhorro.class);
			when(planMock3.getNumeroDeGrupo()).thenReturn(5);
		when(planMock.getNumeroDeGrupo()).thenReturn(5);

		concesionariaTest.crearPlan(planMock2);
		concesionariaTest.crearPlan(planMock3);
		Cliente clienteMock = mock(Cliente.class);
		
		concesionariaTest.suscribirClienteAPlan(clienteMock,planMock);
		
		verify(planMock3).suscribirCliente(clienteMock);
	}
	
	@Test
	public void testSortearMovil() throws ExceptionParticipante, SinPlanesExcepcion, SinStockExcepcion{
		PlanDeAhorro planMock2 = mock(PlanDeAhorro.class);
			when(planMock2.getNumeroDeGrupo()).thenReturn(5);
		Participante participanteMock = mock(Participante.class);
		when(planMock2.elegirGanador()).thenReturn(participanteMock);
		when(planMock2.getModelo()).thenReturn(modeloMock);
		concesionariaTest.crearPlan(planMock2);
		
		when(planMock.getNumeroDeGrupo()).thenReturn(5);
		when(cuponMock.getModelo()).thenReturn(modeloMock);
		
		concesionariaTest.setFabrica(fabricaMock);
		when(fabricaMock.stock(modeloMock)).thenReturn(5);
		
		CuponCreator creadorMock = mock(CuponCreator.class);
		concesionariaTest.setCreador(creadorMock);
		when(creadorMock.crearCupon(planMock2,participanteMock)).thenReturn(cuponMock);
		
		concesionariaTest.sortearMovil(planMock);
		
		verify(creadorMock).crearCupon(planMock2,participanteMock);
	}
	
	@Test
	public void testGastoAdministrativos() {
		assertTrue(concesionariaTest.gastoAdministrativos().equals(150.0f));
	}
	
	@Test
	public void testGetFabrica() {
		concesionariaTest.setFabrica(fabricaMock);
		assertEquals(concesionariaTest.getFabrica(),fabricaMock);
	}
}