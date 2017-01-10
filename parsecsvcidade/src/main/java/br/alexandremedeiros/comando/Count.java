package br.alexandremedeiros.comando;

import java.util.ArrayList;
import java.util.List;

import br.alexandremedeiros.model.Cidade;

public class Count {
	
	private List<Cidade> lista;

	public Count(List<Cidade> lista) {
		super();
		this.lista = lista;
	}
	
	public Integer totalRegistros() {
		return lista.size();
	}
	
	public long getCountDistinct(String coluna) {
		return getListaComColunaEspecifica(coluna).stream().distinct().count();
	}
	
	private List<Object> getListaComColunaEspecifica(String nomeColuna) {
		List<Object> listaComColunasEspecificas = new ArrayList<Object>();
		
		switch (nomeColuna) {
			case "ibge_id":
				for (Cidade cidade : lista) {
					listaComColunasEspecificas.add(cidade.getIbgeId());
				}
				break;
			case "uf":
				for (Cidade cidade : lista) {
					listaComColunasEspecificas.add(cidade.getUf());
				}
				break;
			case "name":
				for (Cidade cidade : lista) {
					listaComColunasEspecificas.add(cidade.getName());
				}
				break;
			case "capital":
				for (Cidade cidade : lista) {
					listaComColunasEspecificas.add(cidade.getCapital());
				}
				break;
			case "lon":
				for (Cidade cidade : lista) {
					listaComColunasEspecificas.add(cidade.getLon());
				}
				break;
			case "lat":
				for (Cidade cidade : lista) {
					listaComColunasEspecificas.add(cidade.getLat());
				}
				break;
			case "no_accents":
				for (Cidade cidade : lista) {
					listaComColunasEspecificas.add(cidade.getNoAccents());
				}
				break;
			case "alternative_names":
				for (Cidade cidade : lista) {
					listaComColunasEspecificas.add(cidade.getAlternativeNames());
				}
				break;
			case "microregion":
				for (Cidade cidade : lista) {
					listaComColunasEspecificas.add(cidade.getMicroregion());
				}
				break;
			case "mesoregion":
				for (Cidade cidade : lista) {
					listaComColunasEspecificas.add(cidade.getMesoregion());
				}
				break;
		}
		
		return listaComColunasEspecificas;
	}

}
