package planta;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import excepciones.ExceptionStock;
import modeloRegistroYequipamiento.Modelo;
import modeloRegistroYequipamiento.RegistroDeModelo;

public class PlantaTest {

	Planta plantaTest;
	
	@Before
	public void setUp(){
		plantaTest = new Planta("Roque Saenz Peña 352");
	}
	
	@Test
	public void testNombreDeLosModelos(){

		Modelo modeloMock1 = mock(Modelo.class);
		Modelo modeloMock2 = mock(Modelo.class);
		when(modeloMock1.getNombre()).thenReturn("Chevrolet Corvette");
		when(modeloMock2.getNombre()).thenReturn("Chevrolet Agile");
		List<String> nombres = new ArrayList<String>(Arrays.asList("Chevrolet Corvette",
																	"Chevrolet Agile"));
		plantaTest.sumarEjemplar(modeloMock1);
		plantaTest.sumarEjemplar(modeloMock2);
		
		assertEquals(plantaTest.nombreDeLosModelos(),nombres);
	}
	
	@Test
	public void testBuscarRegistroDelModelo(){
		
		Modelo modeloMock = mock(Modelo.class);
		when(modeloMock.getNombre()).thenReturn("Chevrolet Corvette");
		
		RegistroDeModelo registroMock1 = mock(RegistroDeModelo.class);
		when(registroMock1.getNombreDelModelo()).thenReturn("Chevrolet Corvette");
		RegistroDeModelo registroMock2 = mock(RegistroDeModelo.class);
		when(registroMock2.getNombreDelModelo()).thenReturn("Chevrolet Agile");

		plantaTest.agregarRegistro(registroMock2);
		assertFalse(plantaTest.buscarRegistroDelModelo(modeloMock).equals(registroMock1));
		
		plantaTest.agregarRegistro(registroMock1);
		assertEquals(plantaTest.buscarRegistroDelModelo(modeloMock),registroMock1);
	}
	
	@Test
	public void testStock(){
		
		Modelo modeloMock = mock(Modelo.class);
		when(modeloMock.getNombre()).thenReturn("Chevrolet Corvette");

		Integer total = 0;
		for(int i = 0; i<3;i+=2){
			try{
				total = plantaTest.stock(modeloMock);
			}catch(ExceptionStock arg){}
			
			assertTrue(total.equals(i));
			plantaTest.sumarEjemplar(modeloMock);
			plantaTest.sumarEjemplar(modeloMock);
		}
	}
	
	@Test
	public void testQuitarEjemplar(){
		
		Modelo modeloMock = mock(Modelo.class);
		when(modeloMock.getNombre()).thenReturn("Chevrolet Corvette");

		for(int i = 0; i<2;i++){
			try{
				plantaTest.quitarEjemplar(modeloMock);
			}catch(ExceptionStock arg){}
			
			plantaTest.sumarEjemplar(modeloMock);
			plantaTest.sumarEjemplar(modeloMock);
			plantaTest.quitarEjemplar(modeloMock);
		}
	}
	
	@Test
	public void testCambiarValorDeTodos(){
		
		RegistroDeModelo registroMock = mock(RegistroDeModelo.class);
		plantaTest.agregarRegistro(registroMock);
		plantaTest.cambiarValorDeTodos(50f);
		
		verify(registroMock).cambiarPrecio(50f);
	}
}
