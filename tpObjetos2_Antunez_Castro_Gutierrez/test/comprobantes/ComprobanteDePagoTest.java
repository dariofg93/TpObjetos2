package comprobantes;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import concesionaria.Concesionaria;
import modeloRegistroYequipamiento.Modelo;
import persona.Participante;
import planDeAhorro.PlanDeAhorro;


/** Al ejecutarse, prueba que se obtuvo
 *  una instancia de ComprobanteDePago correctamente. */
public class ComprobanteDePagoTest {

	ComprobanteDePago comprobanteTest;
	Integer nroCuota;
	PlanDeAhorro planMock;
	Participante participanteMock;
	Concesionaria concesionariaMock;
	Modelo modeloMock;
	
	@Before
	public void setUp() {
		participanteMock = mock(Participante.class);
		planMock = mock(PlanDeAhorro.class);
		concesionariaMock = mock(Concesionaria.class);
		
		when(planMock.getConcesionaria()).thenReturn(concesionariaMock);
		when(concesionariaMock.gastoAdministrativos()).thenReturn(150f);
		when(concesionariaMock.montoDelSeguro(participanteMock,planMock.getModelo())).thenReturn(100f);
		when(planMock.calcularAlicuota()).thenReturn(900f);
	}

	
	@Test
	public void testComprobanteDePago() {
		new ComprobanteDePago(5, planMock, participanteMock);
	}
}
