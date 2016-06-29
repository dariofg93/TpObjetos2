package planta;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import excepciones.SinStockExcepcion;
import modeloRegistroYequipamiento.Modelo;
import modeloRegistroYequipamiento.RegistroDeModelo;

public class PlantaTest {

	Planta plantaTest;
	Modelo modeloMock1;
	
	@Before
	public void setUp(){
		plantaTest = new Planta("Roque Saenz Peña 352");
		modeloMock1 = mock(Modelo.class);
			when(modeloMock1.getNombre()).thenReturn("Chevrolet Corvette");
	}
	
	@Test
	public void testNombreDeLosModelos(){

		List<String> nombres = Arrays.asList("Chevrolet Corvette");
		plantaTest.sumarEjemplar(modeloMock1);
		
		assertEquals(plantaTest.nombreDeLosModelos(),nombres);
	}
	
	@Test
	public void testBuscarRegistroDelModelo(){
		
		RegistroDeModelo registroMock1 = mock(RegistroDeModelo.class);
		when(registroMock1.getNombreDelModelo()).thenReturn("Chevrolet Corvette");
		RegistroDeModelo registroMock2 = mock(RegistroDeModelo.class);
		when(registroMock2.getNombreDelModelo()).thenReturn("Chevrolet Agile");

		plantaTest.agregarRegistro(registroMock2);		
		plantaTest.agregarRegistro(registroMock1);
		
		assertEquals(plantaTest.buscarRegistroDelModelo(modeloMock1),registroMock1);
	}
	
	@Test
	public void testStockDisponibleYsumarEjemplar() throws SinStockExcepcion{
		
		plantaTest.sumarEjemplar(modeloMock1);
		assertTrue(plantaTest.stock(modeloMock1).equals(1));
	}
	
	@Test(expected = SinStockExcepcion.class)
	public void testStockNoDisponible() throws SinStockExcepcion{
		
		plantaTest.stock(modeloMock1);
	}
	
	@Test
	public void testQuitarEjemplar() throws SinStockExcepcion{
		
		plantaTest.sumarEjemplar(modeloMock1);
		plantaTest.sumarEjemplar(modeloMock1);
		
		plantaTest.quitarEjemplar(modeloMock1);
		plantaTest.quitarEjemplar(modeloMock1);
	}
	
	@Test(expected = SinStockExcepcion.class)
	public void testQuitarEjemplarNoHay() throws SinStockExcepcion{
		
		plantaTest.quitarEjemplar(modeloMock1);
	}
	
	@Test
	public void testCambiarValorDeTodos(){
		
		RegistroDeModelo registroMock = mock(RegistroDeModelo.class);
		plantaTest.agregarRegistro(registroMock);
		plantaTest.cambiarValorDeTodos(50f);
		
		verify(registroMock).cambiarPrecio(50f);
	}
}
