package inicializadores;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import modeloRegistroYequipamiento.Modelo;

public class RecordsCreatorTest {

	RecordsCreator recordTest;
	
	@Before
	public void setUp(){
		recordTest = new RecordsCreator();
	}
	
	@Test
	public void test() {
		Modelo model = mock(Modelo.class);
		
		recordTest.nuevoRegistro(model);
	}
}
