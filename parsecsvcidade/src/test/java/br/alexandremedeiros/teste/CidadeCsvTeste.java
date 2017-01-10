package br.alexandremedeiros.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.alexandremedeiros.service.ComandoFacade;

public class CidadeCsvTeste {
	
	private ComandoFacade comandoFacade;
	
	@Before
    public void setUp() throws Exception {
		comandoFacade = new ComandoFacade();
	}
	
	/**
	 * Teste do comando count *
	 * @throws Exception
	 */
	@Test
	public void testCountTodosRegistros() throws Exception {
		assertEquals(5565, comandoFacade.getTotalRegistros().intValue());
	}
	
	/**
	 * Teste do comando count distinct [propriedade]
	 * @throws Exception
	 */
	@Test
	public void testCountDistinct()  throws Exception {
		assertEquals(27, comandoFacade.getCountDistinct("uf"));
	}
	
	/**
	 * Teste do comando filter [propriedade] [valor]
	 * @throws Exception
	 */
	@Test
	public void testFilter() throws Exception {
		assertEquals(293, comandoFacade.filtrarCidadesPor("uf", "sc").size());
	}

}
