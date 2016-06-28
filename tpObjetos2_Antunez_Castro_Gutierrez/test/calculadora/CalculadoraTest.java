package calculadora;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import planta.Planta;

public class CalculadoraTest{

	Random randomMock;
	CalculadorDeDistancia calculadoraTest;
	
	@Before
	public void setUp(){
		randomMock = mock(Random.class);
		
		calculadoraTest = new CalculadorDeDistancia();
			calculadoraTest.setRandom(randomMock);
	}
	
	@Test
	public void test01MontoDelSeguroConLosDosCasos() {
		Planta plantaMock = mock(Planta.class); 
		when(randomMock.nextDouble()).thenReturn(0.5);
		
		assertTrue(calculadoraTest.calcularDistancia(plantaMock).equals(251));
	}
}