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
import excepciones.ExceptionStock;
import fabrica.Fabrica;
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
	
	@Before
	public void setUp(){
		concesionariaTest = new Concesionaria("Roque Saenz Peña 352", 150.0f);
		
		modeloMock = mock(Modelo.class);
		fabricaMock = mock(Fabrica.class);
		plantaMock = mock(Planta.class);
		cuponMock = mock(CuponDeAdjudicacion.class);
			when(cuponMock.getModelo()).thenReturn(modeloMock);
	}
	
	@Test
	public void testCrearPlan() {
		PlanDeAhorro planMock = mock(PlanDeAhorro.class);
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
	public void testLosDiezPlanesConMasSubscriptos() {
		assertEquals(concesionariaTest.losDiezPlanesConMasSubscriptos(),Arrays.asList());
		
		PlanDeAhorro planMock1 = mock(PlanDeAhorro.class);	when(planMock1.cantidadDeParticipantes()).thenReturn(1);	concesionariaTest.crearPlan(planMock1);
		PlanDeAhorro planMock2 = mock(PlanDeAhorro.class);	when(planMock2.cantidadDeParticipantes()).thenReturn(2);	concesionariaTest.crearPlan(planMock2);
		PlanDeAhorro planMock3 = mock(PlanDeAhorro.class);	when(planMock3.cantidadDeParticipantes()).thenReturn(3);	concesionariaTest.crearPlan(planMock3);
		PlanDeAhorro planMock4 = mock(PlanDeAhorro.class);	when(planMock4.cantidadDeParticipantes()).thenReturn(4);	concesionariaTest.crearPlan(planMock4);
		PlanDeAhorro planMock5 = mock(PlanDeAhorro.class);	when(planMock5.cantidadDeParticipantes()).thenReturn(5);	concesionariaTest.crearPlan(planMock5);
		PlanDeAhorro planMock6 = mock(PlanDeAhorro.class);	when(planMock6.cantidadDeParticipantes()).thenReturn(6);	concesionariaTest.crearPlan(planMock6);
		PlanDeAhorro planMock7 = mock(PlanDeAhorro.class);	when(planMock7.cantidadDeParticipantes()).thenReturn(7);	concesionariaTest.crearPlan(planMock7);
		PlanDeAhorro planMock8 = mock(PlanDeAhorro.class);	when(planMock8.cantidadDeParticipantes()).thenReturn(8);	concesionariaTest.crearPlan(planMock8);
		PlanDeAhorro planMock9 = mock(PlanDeAhorro.class);	when(planMock9.cantidadDeParticipantes()).thenReturn(9);	concesionariaTest.crearPlan(planMock9);
		PlanDeAhorro planMock10 = mock(PlanDeAhorro.class);	when(planMock10.cantidadDeParticipantes()).thenReturn(10);	concesionariaTest.crearPlan(planMock10);

		List<PlanDeAhorro> planes = Arrays.asList(planMock10,planMock9,planMock8,planMock7,planMock6,
												   planMock5,planMock4,planMock3,planMock2,planMock1);
		
		assertEquals(concesionariaTest.losDiezPlanesConMasSubscriptos(),planes);
	}
	
	@Test
	public void testEmitirCupon() {		
		concesionariaTest.setFabrica(fabricaMock);
		concesionariaTest.emitirCupon(cuponMock);
		
		verify(fabricaMock).quitarEjemplar(modeloMock);
	}
	
	@Test
	public void testRegistrarPagoDelCupon() {
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
	public void testStockDisponible(){
		concesionariaTest.setFabrica(fabricaMock);
		when(fabricaMock.stock(modeloMock)).thenReturn(5);
		assertTrue(concesionariaTest.stock(modeloMock).equals(5));
	}
	
	@Test(expected = ExceptionStock.class)
	public void testStockNoDisponible(){
		Mockito.doThrow(new ExceptionStock()).when(plantaMock).stock(modeloMock);
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
	public void testGastoAdministrativos() {
		assertTrue(concesionariaTest.gastoAdministrativos().equals(150.0f));
	}
	
	@Test
	public void testGetFabrica() {
		concesionariaTest.setFabrica(fabricaMock);
		assertEquals(concesionariaTest.getFabrica(),fabricaMock);
	}
	
	@Test
	public void testSuscribirClienteAPlan() {
		Cliente clienteMock = mock(Cliente.class);
		PlanDeAhorro planMock = mock(PlanDeAhorro.class);
		
		concesionariaTest.suscribirClienteAPlan(clienteMock,planMock);
		
		verify(planMock).suscribirCliente(clienteMock);
	}
}