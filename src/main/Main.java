package main;

import javax.swing.JOptionPane;
import processos.DuasThreads;
import processos.ProcessamentoComum;
import processos.QuatroThreads;

public class Main {

	public static void main(String[] args) {
		
		int tam = 0;
		String entrada;
		
		do {
			// Tratamento de entrada para o teste da jaca
			
			entrada = JOptionPane.showInputDialog(null, "Qual o tamanho da matriz?\n" +
					"Digite um número inteiro maior do que 2\n" + "Sugestões: 256, 512, 1024 ou 2048: ");
			
			if (entrada != null && entrada.matches("[0-9]*")) {
					
				tam = Integer.parseInt(entrada);
				
				if (tam <= 2) {
					JOptionPane.showMessageDialog(null, "Digite um número positivo maior do que 2!\n");
				}
				
			}
			
			else {
				
				if(entrada.equals("jaca") || entrada.equals("JACA") || entrada.equals("Jaca")) {
					JOptionPane.showMessageDialog(null, "O teste da Jaca não vai dar erro aqui.\n" + 
							"Digite um número positivo maior do que 2!\n");
				}
				
				else if(entrada.equals("abacate") || entrada.equals("ABACATE") || entrada.equals("Abacate")) {
					JOptionPane.showMessageDialog(null, "O teste do Abacate não vai dar erro aqui.\n" + 
							"Digite um número positivo maior do que 2!\n");
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Digite um número positivo maior do que 2!\n");
				}
			
			}

		} while (tam <= 2);

		int processamento = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Qual o tipo de processamento desejado?\n" + "Escolha uma das opções\n" + "1 - Processamento Simples\n"
						+ "2 - Processamento com Duas Threads\n" + "3 - Processamento com Quatro Threads: "));

		// PROCESSAMENTO SIMPLES
		if (processamento == 1) {
			new ProcessamentoComum(tam);
		}

		// PROCESSAMENTO COM 2 THREADS
		else if (processamento == 2) {
			new DuasThreads(tam);

		}

		// PROCESSAMENTO COM 4 THREADS
		else if (processamento == 3) {
			new QuatroThreads(tam);

		} else {
			JOptionPane.showMessageDialog(null, "Opção incorreta. Nenhuma Alteração na matriz");
		}
	}
}
