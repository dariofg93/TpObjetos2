package concesionaria;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import calculadora.CalculadorDeDistancia;
import fabrica.Fabrica;
import modeloRegistroYequipamiento.Modelo;
import modeloRegistroYequipamiento.RegistroDeModelo;
import persona.Cliente;
import planDeAhorro.PlanDeAhorro;
import planta.Planta;

public class ConcesionariaTest {

	Concesionaria concesionariaTest;
	Fabrica fabricaMock;
	
	
	@Before
	public void setUp(){
		
		fabricaMock = mock(Fabrica.class);

		concesionariaTest = new Concesionaria("Roque Saenz Peña 352", fabricaMock, 150.0f);
	}
	
	@Test
	public void testGastoDeFlete() {
		Planta plantaMock;
		plantaMock = mock(Planta.class);
		
		Float res = concesionariaTest.gastoDeFlete(plantaMock);
		Float precioXkm = 20.5f;
		
		assertTrue(res>0f && res<=500f*precioXkm);
	}
	/*
	@Test
	public void testStock() {
		Modelo modeloMock = mock(Modelo.class);
		ArrayList<String> modelsName;
		modelsName = new ArrayList<String>(Arrays.asList("Chevrolet Corvette","Chevrolet Agile"));
		
		when(modeloMock.getNombre()).thenReturn("Chevrolet Corvette");
		when(modeloMock.getCantidad()).thenReturn(1);
		
		when(fabricaMock.nombreTodosLosModelos()).thenReturn(modelsName);
		//when(fabricaMock.buscarModelo(modeloMock)).thenReturn(modeloMock);
		
		
		assertTrue(concesionariaTest.stock(modeloMock).equals(1));
	}
	
	@Test
	public void testLosDiezPlanesConMasSubscriptos(){
		PlanDeAhorro planMock1 = mock(PlanDeAhorro.class);
		PlanDeAhorro planMock2 = mock(PlanDeAhorro.class);
		
		concesionariaTest.crearPlan(planMock1); concesionariaTest.crearPlan(planMock2);

		when(planMock1.cantidadDeParticipantes()).thenReturn(5);
		when(planMock2.cantidadDeParticipantes()).thenReturn(20);
		
		ArrayList<PlanDeAhorro> res;
		res = new ArrayList<PlanDeAhorro>(Arrays.asList(planMock2,planMock1));
		
		assertEquals(concesionariaTest.losDiezPlanesConMasSubscriptos(),res);
	}
	
		@Test
		public void testAdjudicarMovil(){
			//Puede cambiar.. depende de la implementacion de adjudicarMovil, 
			//toma un Cliente o no es necesario?...
			Cliente clienteMock = mock(Cliente.class);
			Modelo modeloMock = mock(Modelo.class);
			
			when(modeloMock.getNombre()).thenReturn("Chevrolet Corvette");
			
			//concesionariaTest.adjudicarMovil(modeloMock, clienteMock);
			
			verify(fabricaMock).quitarEjemplar("Chevrolet Corvette");
		}*/
}