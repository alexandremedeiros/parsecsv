package br.alexandremedeiros.main;

import java.util.Scanner;

import br.alexandremedeiros.service.Interpretador;

public class Main {

	public static void main(String[] args) {
		Interpretador interpretador = new Interpretador();
		
		Scanner sc = new Scanner(System.in);
		String comando = "";

		do {
			montaMenuSistema();
			System.out.print("Digite aqui sua opção ou comando: ");
			comando = sc.nextLine();

			if ("1".equals(comando)) {
				exibeComandosDisponiveis();
			} 
			else if (!"1".equals(comando) && !"0".equals(comando)) {
				System.out.println("");
				System.out.println(interpretador.interpretarComando(comando));
				System.out.println("");
			}
		} while (!"0".equals(comando));

		sc.close();

	}
	
	
	private static void montaMenuSistema() {
		System.out.println("## CIDADES ##");
		System.out.println("Opção 1 - Comandos disponíveis");
		System.out.println("Opção 0 - Sair do programa");
		System.out.println("_______________________");
	}
	
	
	private static void exibeComandosDisponiveis() {
		System.out.println("count * - escreve no console a contagem total de registros importados");
		System.out.println("count distinct [propriedade] - escreve no console o total de valores distintos da propriedade (coluna) enviada");
		System.out.println("filter [propriedade] [valor] - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado");
		System.out.println("_______________________");
	}
	  

}
