package inicializadores;


import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import excepciones.SinStockExcepcion;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;

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
		cuponCreatorTest.crearCupon(planMock, winnerMock);
	}

	
	/** Se intenta realizar la creacion de un Cupon,
	 * pero el Plan no obtiene el monto del flete
	 * (no hay una Planta que tenga Stock de ese Modelo).
	 * En consecuencia, se espera que tire una excepcion.  */
	@Test(expected = SinStockExcepcion.class)
	public void testCrearCuponSinStock() throws SinStockExcepcion {
		doThrow(new SinStockExcepcion()).when(planMock).montoDelFlete();
		cuponCreatorTest.crearCupon(planMock, winnerMock);
		
	}
}
