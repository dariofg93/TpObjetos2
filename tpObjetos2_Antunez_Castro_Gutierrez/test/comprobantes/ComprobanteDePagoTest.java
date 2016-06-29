package comprobantes;

import static org.junit.Assert.*;
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
		nroCuota = 5;
		participanteMock = mock(Participante.class);
		planMock = mock(PlanDeAhorro.class);
		concesionariaMock = mock(Concesionaria.class);
		modeloMock = mock(Modelo.class);
		
		when(planMock.getConcesionaria()).thenReturn(concesionariaMock);
		when(planMock.getModelo()).thenReturn(modeloMock);
		when(concesionariaMock.gastoAdministrativos()).thenReturn(150f);
		when(concesionariaMock.montoDelSeguro(participanteMock,planMock.getModelo())).thenReturn(100f);
		when(planMock.calcularAlicuota()).thenReturn(900f);
		
		comprobanteTest = new ComprobanteDePago(nroCuota, planMock, participanteMock);
	}

	
	@Test
	public void testComprobanteDePago() {
	}

}
