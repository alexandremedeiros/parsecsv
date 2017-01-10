package br.alexandremedeiros.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.alexandremedeiros.model.Cidade;

public class ParseArquivoCsv {
	
	private static final String COMMA = ",";
	
	public List<Cidade> processInputFile(String inputFilePath) {
	    List<Cidade> inputList = new ArrayList<Cidade>();
	    
	    try {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + inputFilePath)));
	    	inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
	    	br.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    return inputList;
	}
	
	
	public static String getCabecalhoArquivo(String inputFilePath) throws IOException {
		Path path = Paths.get(Constantes.DIRETORIO_RESOURCES + inputFilePath);
	    Stream<String> lines = Files.lines(path);
        
	    List<String> columns = lines
	    	.findFirst()
            .map((line) -> Arrays.asList(line.split(",")))
            .get();
        
        lines.close();
        
        String cabecalho = "";
        for (String st : columns) {
        	cabecalho += st + ",";
        }
        
        cabecalho = cabecalho.substring(0, cabecalho.length() - 1);
        
        return cabecalho;
	}
	
	
	private Function<String, Cidade> mapToItem = (line) -> {
	  String[] p = line.split(COMMA);
	  Cidade cidade = new Cidade();
	  
	  String ibgeId = p[0];
	  String uf = p[1];
	  String name = p[2];
	  String capital = p[3];
	  String lon = p[4];
	  String lat = p[5];
	  String no_accents = p[6];
	  String alternative_names = p[7];
	  String microregion = p[8];
	  String mesoregion = p[9];
	  
	  cidade.setIbgeId(Integer.parseInt(ibgeId));
	  
	  if (uf != null && uf.trim().length() > 0) {
		  cidade.setUf(uf);
	  }
	  if (name != null && name.trim().length() > 0) {
		  cidade.setName(name);
	  }
	  if (capital != null && capital.trim().length() > 0) {
		  cidade.setCapital(p[3]);
	  }
	  if (lon != null && lon.trim().length() > 0) {
		  cidade.setLon(Double.parseDouble(lon));
	  }
	  if (lat != null && lat.trim().length() > 0) {
		  cidade.setLat(Double.parseDouble(lat));
	  }
	  if (no_accents != null && no_accents.trim().length() > 0) {
		  cidade.setNoAccents(no_accents);
	  }
	  if (alternative_names != null && alternative_names.trim().length() > 0) {
		  cidade.setAlternativeNames(alternative_names);
	  }
	  if (microregion != null && microregion.trim().length() > 0) {
		  cidade.setMicroregion(microregion);
	  }
	  if (mesoregion != null && mesoregion.trim().length() > 0) {
		  cidade.setMesoregion(mesoregion);
	  }
	  
	  return cidade;
	};
	

}
