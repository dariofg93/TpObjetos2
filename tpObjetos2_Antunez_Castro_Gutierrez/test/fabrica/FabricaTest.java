package fabrica;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import javax.swing.text.PlainDocument;

import org.junit.Before;
import org.junit.Test;

import concesionaria.Concesionaria;
import excepciones.ExceptionStock;
import modeloRegistroYequipamiento.Modelo;
import planta.Planta;

public class FabricaTest {

	Fabrica fabricaTest;
	Concesionaria concesionariaMock;
	
	@Before
	public void setUp(){
		concesionariaMock = mock(Concesionaria.class);
		fabricaTest = new Fabrica(concesionariaMock);
	}
	
	@Test
	public void testStock(){					//Seguir desde aca
		Modelo modeloMock = mock(Modelo.class);
		when(modeloMock.getNombre()).thenReturn("Chevrolet Corvette");

		Planta plantaMock1 = mock(Planta.class);
		when(plantaMock1.nombreDeLosModelos()).thenReturn(Arrays.asList("Chevrolet Corvette"));
		Planta plantaMock2 = mock(Planta.class);
		when(plantaMock2.nombreDeLosModelos()).thenReturn(Arrays.asList("Chevrolet Corvette"));

		fabricaTest.inaugurarPlanta(plantaMock2);
		Integer total = 0;
		
		for(int i = 0; i<3;i+=2){
			try{
				total = fabricaTest.stock(modeloMock);
			}catch(ExceptionStock arg){}
			fabricaTest.inaugurarPlanta(plantaMock1);
			assertTrue(total.equals(i));
			fabricaTest.sumarEjemplar(modeloMock);
			fabricaTest.sumarEjemplar(modeloMock);
		}
	}
}
