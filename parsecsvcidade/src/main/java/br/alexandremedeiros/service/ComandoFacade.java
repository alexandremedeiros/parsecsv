package br.alexandremedeiros.service;

import java.util.List;

import br.alexandremedeiros.comando.Count;
import br.alexandremedeiros.comando.Filter;
import br.alexandremedeiros.model.Cidade;
import br.alexandremedeiros.util.ParseArquivoCsv;

public class ComandoFacade {
	
	private List<Cidade> lista;
	
	private Count count;
	private Filter filter;
	
	
	public ComandoFacade() {
		ParseArquivoCsv parseArquivoCsv = new ParseArquivoCsv();
		lista = parseArquivoCsv.processInputFile("cidades.csv");
		
		count = new Count(lista);
		filter = new Filter(lista);
	}
	
	
	public Integer getTotalRegistros() {
		return count.totalRegistros();
	}
	
	
	public long getCountDistinct(String nomeColuna) {
		return count.getCountDistinct(nomeColuna);
	}
	
	
	public List<Cidade> filtrarCidadesPor(String nomeColuna, String valor) {
		return filter.filtrarCidadesPor(nomeColuna, valor);
	}

}
