package br.alexandremedeiros.comando;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.alexandremedeiros.model.Cidade;

public class Filter {
	
	private List<Cidade> lista;
	
	public Filter(List<Cidade> lista) {
		super();
		this.lista = lista;
	}
	
	
	public List<Cidade> filtrarCidadesPor(String nomeColuna, String valor) {
		
		Predicate<Cidade> predicate;
		
		switch (nomeColuna) {
			case "ibge_id":
				predicate = e -> e.getIbgeId() != null && e.getIbgeId().equals(Integer.parseInt(valor));
				return lista.stream().filter(e -> e.getUf() != null).filter(predicate).collect(Collectors.toList());
			case "uf":
				predicate = e -> e.getUf() != null && e.getUf().toLowerCase().trim().equals(valor.trim().toLowerCase());
				return lista.stream().filter(e -> e.getUf() != null).filter(predicate).collect(Collectors.toList());
			case "name":
				predicate = e -> e.getName() != null && e.getName().toLowerCase().trim().equals(valor.trim().toLowerCase());
				return lista.stream().filter(e -> e.getName() != null).filter(predicate).collect(Collectors.toList());
			case "capital":
				predicate = e -> e.getCapital() != null && e.getCapital().toLowerCase().trim().equals(valor.trim().toLowerCase());
				return lista.stream().filter(e -> e.getCapital() != null).filter(predicate).collect(Collectors.toList());
			case "lon":
				predicate = e -> e.getLon() != null && e.getLon().equals(Double.parseDouble(valor));
				return lista.stream().filter(e -> e.getLon() != null).filter(predicate).collect(Collectors.toList());
			case "lat":
				predicate = e -> e.getLat() != null && e.getLat().equals(Double.parseDouble(valor));
				return lista.stream().filter(e -> e.getLat() != null).filter(predicate).collect(Collectors.toList());
			case "no_accents":
				predicate = e -> e.getNoAccents() != null && e.getNoAccents().toLowerCase().trim().equals(valor.trim().toLowerCase());
				return lista.stream().filter(e -> e.getNoAccents() != null).filter(predicate).collect(Collectors.toList());
			case "alternative_names":
				predicate = e -> e.getAlternativeNames() != null && e.getAlternativeNames().toLowerCase().trim().contains(valor.trim().toLowerCase());
				return lista.stream().filter(e -> e.getAlternativeNames() != null).filter(predicate).collect(Collectors.toList());
			case "microregion":
				predicate = e -> e.getMicroregion() != null && e.getMicroregion().toLowerCase().trim().equals(valor.trim().toLowerCase());
				return lista.stream().filter(e -> e.getMicroregion() != null).filter(predicate).collect(Collectors.toList());
			case "mesoregion":
				predicate = e -> e.getMesoregion() != null && e.getMesoregion().toLowerCase().trim().contains(valor.trim().toLowerCase());
				return lista.stream().filter(e -> e.getMesoregion() != null).filter(predicate).collect(Collectors.toList());
		}
		
		return null;
	}

}
