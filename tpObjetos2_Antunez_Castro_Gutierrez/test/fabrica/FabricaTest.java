package fabrica;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import concesionaria.Concesionaria;
import excepciones.ExceptionStock;
import modeloRegistroYequipamiento.Modelo;
import modeloRegistroYequipamiento.RegistroDeModelo;
import planta.Planta;

public class FabricaTest {

	Modelo modeloMock1;
	Planta plantaMock1;
	Fabrica fabricaTest;
	Concesionaria concesionariaMock;
	
	@Before
	public void setUp(){
		concesionariaMock = mock(Concesionaria.class);
		fabricaTest = new Fabrica(concesionariaMock);
		
		modeloMock1 = mock(Modelo.class);
		when(modeloMock1.getNombre()).thenReturn("Chevrolet Corvette");
		
		plantaMock1 = mock(Planta.class);
		when(plantaMock1.nombreDeLosModelos()).thenReturn(Arrays.asList("Chevrolet Corvette",
																		"Chevrolet Agile"));
	}
	
	@Test
	public void testStock(){
		Planta plantaMock2 = mock(Planta.class);
		when(plantaMock2.nombreDeLosModelos()).thenReturn(Arrays.asList("Chevrolet Agile"));

		fabricaTest.inaugurarPlanta(plantaMock2);
		Integer total = 0;
		
		for(int i = 0; i<3;i+=2){
			try{
				total = fabricaTest.stock(modeloMock1);
			}catch(ExceptionStock arg){}
			fabricaTest.inaugurarPlanta(plantaMock1);
			assertTrue(total.equals(i));
			when(plantaMock1.stock(modeloMock1)).thenReturn(2);
		}
	}
	
	@Test
	public void testPlantaMasCercanaAConcesionaria(){
		Planta plantaMock2 = mock(Planta.class);
		when(plantaMock2.nombreDeLosModelos()).thenReturn(Arrays.asList("Chevrolet Corvette"));

		fabricaTest.inaugurarPlanta(plantaMock1);
		fabricaTest.inaugurarPlanta(plantaMock2);
		
		when(concesionariaMock.gastoDeFlete(plantaMock1)).thenReturn(250f);
		when(concesionariaMock.gastoDeFlete(plantaMock2)).thenReturn(100f);

		assertEquals(fabricaTest.plantaMasCercanaAConcesionaria(modeloMock1),plantaMock2);
	}
	
	@Test
	public void testQuitarEjemplar(){
		when(modeloMock1.getNombre()).thenReturn("Chevrolet Agile");

		fabricaTest.inaugurarPlanta(plantaMock1);
		when(concesionariaMock.gastoDeFlete(plantaMock1)).thenReturn(250f);
		fabricaTest.quitarEjemplar(modeloMock1);
		
		verify(plantaMock1).quitarEjemplar(modeloMock1);
	}
	
	@Test
	public void testSumarEjemplar(){
		fabricaTest.inaugurarPlanta(plantaMock1);
		
		fabricaTest.sumarEjemplar(modeloMock1);
		
		verify(plantaMock1).sumarEjemplar(modeloMock1);
	}
	
	@Test
	public void testCambiarValorMovil(){
		fabricaTest.inaugurarPlanta(plantaMock1);
		
		RegistroDeModelo registroMock = mock(RegistroDeModelo.class);
		when(plantaMock1.buscarRegistroDelModelo(modeloMock1)).thenReturn(registroMock);
		fabricaTest.cambiarValorMovil(10f,modeloMock1);
		
		verify(registroMock).cambiarPrecio(10f);
	}
	
	@Test
	public void testCambiarValorTodos(){
		fabricaTest.inaugurarPlanta(plantaMock1);
		
		fabricaTest.cambiarValorTodos(10f);
		
		verify(plantaMock1).cambiarValorDeTodos(10f);
	}
}
