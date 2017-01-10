package br.alexandremedeiros.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import br.alexandremedeiros.model.Cidade;
import br.alexandremedeiros.util.Constantes;
import br.alexandremedeiros.util.ParseArquivoCsv;

public class Interpretador {
	
	private ComandoFacade comandoFacade;
	private String cabecalhoArquivo;
	
	public Interpretador() {
		comandoFacade = new ComandoFacade();
		
		try {
			cabecalhoArquivo = ParseArquivoCsv.getCabecalhoArquivo(Constantes.ARQUIVO_CSV);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String interpretarComando(String comando) {
		
		if (!comando.contains("count") && !comando.contains("filter")) {
			return Constantes.COMANDO_INDISPONIVEL;
		}
		
		if (!comando.contains(" ")) {
			return Constantes.ERRO_SINTAXE_COMANDO;
		}
		
		
		if (comando.contains("count")) {
			return interpretarComandoCount(comando);
		}
		
		if (comando.contains("filter")) {
			return interpretarComandoFilter(comando);
		}
		
		
		return "";
	}
	
	
	private String interpretarComandoCount(String comando) {
		List<String> partesComando = getPartesComando(comando);
		
		if (comando.contains("*")) {
			if (partesComando.get(1).equals("*")) {
				return comandoFacade.getTotalRegistros() + " " + Constantes.REGISTROS_ENCONTRADOS;
			}
			else {
				return Constantes.ERRO_SINTAXE_COMANDO;
			}
		}
		
		if (comando.contains("distinct")) {
			if (partesComando.get(1).equals("distinct")) {
				String nomeColuna = partesComando.get(2).replace("[", "").replace("]", "");
				
				if (!existePropriedade(nomeColuna)) {
					return Constantes.PROPRIEDADE_INEXISTENTE;
				}
				
				return comandoFacade.getCountDistinct(nomeColuna) + " " + Constantes.REGISTROS_ENCONTRADOS;
			}
			else {
				return Constantes.ERRO_SINTAXE_COMANDO;
			}
		}
		
		return "";
	}
	
	
	private String interpretarComandoFilter(String comando) {
		List<String> partesComando = getPartesComando(comando);
		
		String nomeColuna =  partesComando.get(1).replace("[", "").replace("]", "");
		String valor = partesComando.get(2).replace("[", "").replace("]", "");
		
		if (!existePropriedade(nomeColuna)) {
			return Constantes.PROPRIEDADE_INEXISTENTE;
		}
		
		List<Cidade> cidades = comandoFacade.filtrarCidadesPor(nomeColuna, valor);
		
		String resultado = cabecalhoArquivo + "\n";
		
		for (Cidade cidade : cidades) {
			resultado += cidade.getIbgeId() + "," + cidade.getUf() + "," + cidade.getName() + "," 
					+ cidade.getCapital() + "," + cidade.getLon() + "," + cidade.getLat() + ","
					+ cidade.getNoAccents() + "," + cidade.getAlternativeNames() + ","
					+ cidade.getMicroregion() + "," + cidade.getMesoregion();
			resultado += "\n";
		}
		
		resultado += "\n\n" + cidades.size() + " " + Constantes.REGISTROS_ENCONTRADOS;
		
		return resultado;
	}
	
	
	private List<String> getPartesComando(String comando) {
		StringTokenizer st = new StringTokenizer(comando);
		List<String> partesComando = new ArrayList<String>();
		while (st.hasMoreElements()) {
			partesComando.add((String) st.nextElement());
		}
		
		return partesComando;
	}
	
	
	private boolean existePropriedade(String nomePropriedade) {
		return cabecalhoArquivo.indexOf(nomePropriedade) >= 0;
	}

}
