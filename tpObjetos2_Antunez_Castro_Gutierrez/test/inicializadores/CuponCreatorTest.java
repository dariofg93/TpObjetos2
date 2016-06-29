package inicializadores;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import concesionaria.Concesionaria;
import excepciones.SinStockExcepcion;
import fabrica.Fabrica;
import modeloRegistroYequipamiento.Modelo;
import persona.Cliente;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;
import planta.Planta;

/** Test que prueba que el creador de Cupones de Adjudicacion se comporta segun lo esperado. */
public class CuponCreatorTest {

	CuponCreator cuponCreatorTest;
	PlanDeAhorro planMock;
	Participante winnerMock;
	
	@Before
	public void setUp() {
		planMock = mock(PlanDeAhorro.class);
		winnerMock = mock(Participante.class);
		cuponCreatorTest = new CuponCreator();
	}

	/** Se realiza la creacion de un Cupon. */
	@Test
	public void testCrearCupon() throws SinStockExcepcion {
		Modelo modeloMock = mock(Modelo.class);
		Cliente clienteMock = mock(Cliente.class);
		Concesionaria concesionariaMock = mock(Concesionaria.class);
		Fabrica fabricaMock = mock(Fabrica.class);
		Planta plantaMock = mock(Planta.class);
		
		when(planMock.getModelo()).thenReturn(modeloMock);
		when(winnerMock.getCliente()).thenReturn(clienteMock);
		when(planMock.montoDelFinanciamientoDeAdjudicacion()).thenReturn(0f);
		when(planMock.getConcesionaria()).thenReturn(concesionariaMock);
		when(concesionariaMock.getFabrica()).thenReturn(fabricaMock);
		when(planMock.getModelo()).thenReturn(modeloMock);
		when(fabricaMock.plantaMasCercanaAConcesionaria(modeloMock)).thenReturn(plantaMock);
		when(concesionariaMock.gastoDeFlete(plantaMock)).thenReturn(50f);
		
		cuponCreatorTest.crearCupon(planMock, winnerMock);
	}
}
