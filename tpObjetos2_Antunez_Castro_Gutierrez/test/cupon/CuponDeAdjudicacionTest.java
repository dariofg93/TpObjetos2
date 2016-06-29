package cupon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import concesionaria.Concesionaria;
import excepciones.SinStockExcepcion;
import fabrica.Fabrica;
import financiamiento.Financiamiento;
import modeloRegistroYequipamiento.Modelo;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;
import planta.Planta;

public class CuponDeAdjudicacionTest {

	Concesionaria concesionariaMock;
	Fabrica fabricaMock;
	CuponDeAdjudicacion cuponTest;
	PlanDeAhorro planMock;
	Participante participanteMock;
	Planta plantaMock;
	Modelo modeloMock;
	Financiamiento financiamientoMock;
	
	@Before
	public void setUp() throws SinStockExcepcion{
		fabricaMock = mock(Fabrica.class);
		concesionariaMock = mock(Concesionaria.class);
		participanteMock = mock(Participante.class);
		modeloMock = mock(Modelo.class);
		when(modeloMock.getValorDeVenta()).thenReturn(1000f);
		plantaMock = mock(Planta.class);
		
		planMock = mock(PlanDeAhorro.class);
		when(planMock.getModelo()).thenReturn(modeloMock);
	}
	
	@Test
	public void testCrearCupon() throws SinStockExcepcion{
		when(planMock.montoDelFinanciamientoDeAdjudicacion()).thenReturn(0f);
		when(planMock.getConcesionaria()).thenReturn(concesionariaMock);
		when(planMock.getModelo()).thenReturn(modeloMock);
		
		when(concesionariaMock.getFabrica()).thenReturn(fabricaMock);
		when(concesionariaMock.gastoDeFlete(plantaMock)).thenReturn(100f);
		
		when(fabricaMock.plantaMasCercanaAConcesionaria(modeloMock)).thenReturn(plantaMock);
		
		 CuponDeAdjudicacion cupon = new CuponDeAdjudicacion(planMock,participanteMock);
		 assertEquals(cupon.getModelo(),modeloMock);
	}
	
	@Test(expected = SinStockExcepcion.class)
	public void testCrearCuponSinStock() throws SinStockExcepcion{
		when(planMock.getConcesionaria()).thenReturn(concesionariaMock);
		when(planMock.getModelo()).thenReturn(modeloMock);
		when(concesionariaMock.getFabrica()).thenReturn(fabricaMock);
		
		Mockito.doThrow(new SinStockExcepcion()).when(fabricaMock).plantaMasCercanaAConcesionaria(modeloMock);		
		new CuponDeAdjudicacion(planMock,participanteMock);
	}
}
