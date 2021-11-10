package processos;

import javax.swing.JOptionPane;

import matriz.Matriz;

public class ProcessamentoComum {
	private static double[][] matriz;
	private double[][] auxiliar;
	
	public ProcessamentoComum(int tam) {
		double menor = 10000;
		auxiliar = new double[tam][tam];
		matriz = new double[tam][tam];

		// CRIAÇÃO DAS MATRIZES
		Matriz.criaMatriz(matriz, tam);
		Matriz.criaMatriz(auxiliar, tam);

		// VISUALIZAÇÃO DA MATRIZ INICIAL (OPCIONAL)
		Object[] options = { "NÃO", "SIM" };
		int opcao = JOptionPane.showOptionDialog(null, "Deseja visualizar no console a matriz criada?", "SAIR",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

		if (opcao == 1) {
			System.out.print("Matriz Inicial\n");
			Matriz.visualizaMatriz(matriz, tam);
		}
		//INÍCIO DO PROCESSAMENTO
		System.out.print("\nProcessando...\n");
		long start = System.currentTimeMillis(); // Tempo no início
		
		do{
			menor = 1000;
			//PREENCHE AS POSIÇÕES DA MATRIZ AUXILIAR COM O CÁLCULO - A PARTIR DA MATRIZ ORIGINAL
			for (int i = 1; i < tam-1; i++) {
				for (int j = 1; j < tam-1; j++) {
					auxiliar[i][j] = (matriz[i - 1][j] + matriz[i][j - 1] + matriz[i][j] 
							+ matriz[i + 1][j] + matriz[i][j + 1])/5;
				}
			}
			//ATUALIZA A MATRIZ ORIGINAL COM OS VALORES CALCULADOS E GUARDADOS NA AUXILIAR
			for (int i = 1; i < tam-1; i++) {
				for (int j = 1; j < tam-1; j++) {
					matriz[i][j] = auxiliar[i][j];
					//CAUCLA O MENOR ELEMENTO
					if (matriz[i][j] < menor) {
						menor = matriz[i][j]; 
					}
				}
			}
			
		}while (menor < 500);
		
		long end = System.currentTimeMillis();
		String msg =  Double.toString((end - start) / 1000d);
		
		//IMPRESSÃO DO RESULTADO NO CONSOLE
		System.out.printf("Tempo: %.3f segundos\n\n", (end - start) / 1000d);
		
		//IMPRESSÃO DO RESULTADO EM CAIXA
		JOptionPane.showMessageDialog(null, "Tempo " + msg + " segundos");
		
		//VISUALIZAÇÃO OPCIONAL DE MATRIZ RESULTANTE
		visualiza(tam);

	}

	private static void visualiza(int tam) {
		// VISUALIZAÇÃO OPCIONAL DA MATRIZ NO FIM DO PROCESSO
		Object[] options = { "NÃO", "SIM" };
		int OP = JOptionPane.showOptionDialog(null, "Deseja visualizar no console a matriz final?", "SAIR",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

		if (OP == 1) {
			System.out.println("Final da execução. Matriz:\n");
			Matriz.visualizaMatriz(matriz, tam);
		}
		else {
			System.exit(0);
		}

	}
}
