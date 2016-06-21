package modeloRegistroYequipamiento;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class RegistroDeModeloTest {

	RegistroDeModelo registroTest;
	Modelo modeloMock;
	
	@Before
	public void setUp(){
		modeloMock = mock(Modelo.class);
		registroTest = new RegistroDeModelo(modeloMock);
	}
	
	@Test
	public void testGetNombreDelModelo(){
		when(modeloMock.getNombre()).thenReturn("Chevrolet Corvette");
		assertEquals(registroTest.getNombreDelModelo(),"Chevrolet Corvette");
	}

	@Test
	public void testGetPrecioaaDelModelo(){
		when(modeloMock.getValorDeVenta()).thenReturn(80000f);
		assertTrue(registroTest.getPrecioDelModelo().equals(80000f));
	}
	
	@Test
	public void testDescontarUnidad(){
		registroTest.descontarUnidad();
		assertTrue(registroTest.getCantidad().equals(0));
	}
	
	@Test
	public void testSumarUnidad(){
		registroTest.sumarUnidad();
		assertTrue(registroTest.getCantidad().equals(2));
	}
	
	@Test
	public void testCambiarPrecio(){
		registroTest.cambiarPrecio(5f);
		verify(modeloMock).cambiarPrecio(5f);
	}
}
