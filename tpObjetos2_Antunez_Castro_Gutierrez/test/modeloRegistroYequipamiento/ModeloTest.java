package modeloRegistroYequipamiento;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ModeloTest {

	Modelo modeloTest;
	Equipamiento equipMock;
	
	@Before
	public void setUp(){
		equipMock = mock(Full.class);
		modeloTest = new Modelo("Chevrolet Corvette",1953,2,equipMock,80000f/**Dólares*/);
	}
	
	@Test
	public void testGetValorDeVenta(){
		assertTrue(modeloTest.getValorDeVenta().equals(80000f));
	}
	
	@Test
	public void testGetNombre(){
		assertEquals(modeloTest.getNombre(),"Chevrolet Corvette");
	}
	
	@Test
	public void testPorcentajeDelPrecio(){
		Float valorEsperado = 8000f;
		assertTrue(modeloTest.porcentajeDelPrecio(10f).equals(valorEsperado));
	}
	
	@Test
	public void testCambiarPrecio(){
		modeloTest.cambiarPrecio(-10f);
		assertTrue(modeloTest.getValorDeVenta().equals(72000f));
	}
}
