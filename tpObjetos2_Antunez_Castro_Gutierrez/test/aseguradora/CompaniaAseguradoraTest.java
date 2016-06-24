package aseguradora;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import modeloRegistroYequipamiento.Modelo;
import persona.Participante;

public class CompaniaAseguradoraTest {

	Participante mockParticipante;
	Modelo mockModelo;
	CompaniaAseguradora aseguradoraTest;
	
	@Before
	public void setUp(){
		mockParticipante = mock(Participante.class);
		mockModelo = mock(Modelo.class);
		aseguradoraTest = new CompaniaAseguradora();
	}
	
	
	@Test
	public void test01MontoDelSeguroConLosDosCasos() {
		when(mockParticipante.edad()).thenReturn(25);
		when(mockModelo.porcentajeDelPrecio(5f)).thenReturn(2000f);
		assertTrue(aseguradoraTest.montoDelSeguro(mockParticipante, mockModelo).equals(2050f));
		
		when(mockParticipante.edad()).thenReturn(60);
		assertTrue(aseguradoraTest.montoDelSeguro(mockParticipante, mockModelo).equals(2150f));
		
	}

	
}
