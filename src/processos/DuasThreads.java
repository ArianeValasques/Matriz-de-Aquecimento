package processos;

import javax.swing.JOptionPane;
import matriz.Matriz;

public class DuasThreads extends Thread {
	private static double[][] matriz;
	private double[][] auxiliar;
	static int inicio1;
	static int inicio2;
	static int fim1;
	static int fim2;

	public DuasThreads(int tam) {
		auxiliar = new double[tam][tam];
		matriz = new double[tam][tam];

		// CRIA��O DAS MATRIZES
		Matriz.criaMatriz(matriz, tam);
		Matriz.criaMatriz(auxiliar, tam);

		// VISUALIZA��O DA MATRIZ INICIAL (OPCIONAL)
		Object[] options = { "N�O", "SIM" };
		int opcao = JOptionPane.showOptionDialog(null, "Deseja visualizar no console a matriz criada?", "SAIR",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

		if (opcao == 1) {
			System.out.print("Matriz Inicial\n");
			Matriz.visualizaMatriz(matriz, tam);
		}

		System.out.print("\nProcessando...\n");
		long start = System.currentTimeMillis(); // TEMPO NO IN�CIO DOS PROCESSOS
		//PRIMEIRA THREAD - CALCULA A METADE DA MATRIZ (DA PRIMEIRA COLUNA AT� A DO MEIO)
		inicio1 = 1;
		fim1 = tam-1;
		inicio2 = 1;
		fim2 = tam / 2;
		MyThread(inicio1, fim1, inicio2, fim2);
		//SEGUNDA THREAD - CALCULA A OUTRA METADE DA MATRIZ (DA COLUNA DO MEIO AO FIM)
		inicio1 = 1;
		fim1 = tam-1;
		inicio2 = (tam / 2);
		fim2 = tam-1;
		MyThread(inicio1, fim1, inicio2, fim2);

		//EXIBI��O DO TEMPO GASTO NA EXECU��O DOS PRECESSOS 
		long end = System.currentTimeMillis(); // TEMPO NO FIM DOS PROCESSOS
		String msg = Double.toString((end - start) / 1000d);
		System.out.printf("Tempo: %.3f segundos\n\n", (end - start) / 1000d);
		JOptionPane.showMessageDialog(null, "Tempo " + msg + " segundos");

		// VISUALIZA��O OPCIONAL DA MATRIZ RESULTANTE
		visualiza(tam);

	}

	private static void visualiza(int tam) {
		// VISUALIZA��O DA MATRIZ NO FIM DO PROCESSO
		Object[] options = { "N�O", "SIM" };
		int OP = JOptionPane.showOptionDialog(null, "Deseja visualizar no console a matriz final?", "SAIR",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

		if (OP == 1) {
			System.out.println("Final da execu��o. Matriz:\n");
			Matriz.visualizaMatriz(matriz, tam);
		}
		else {
			System.exit(0);
		}

	}

	public void MyThread(int inicioI, int fimI, int inicioJ, int fimJ) {
		new Thread() {
			public void run() {
				double menor = 1000;

				do {
					menor = 1000;
					// PREENCHE AS POSI��ES DA MATRIZ AUXILIAR COM O C�LCULO - A PARTIR DA MATRIZ
					// ORIGINAL
					for (int i = inicioI; i < fimI; i++) {
						for (int j = inicioJ; j < fimJ; j++) {
							auxiliar[i][j] = (matriz[i - 1][j] + matriz[i][j - 1] + matriz[i][j] + matriz[i + 1][j]
									+ matriz[i][j + 1]) / 5;
						}
					}
					// ATUALIZA A MATRIZ ORIGINAL COM OS VALORES CALCULADOS E GUARDADOS NA AUXILIAR
					for (int i = inicioI; i < fimI; i++) {
						for (int j = inicioJ; j < fimJ; j++) {
							matriz[i][j] = auxiliar[i][j];
							// CAUCLA O MENOR ELEMENTO
							if (matriz[i][j] < menor) {
								menor = matriz[i][j];
							}
						}
					}
				} while (menor < 500);

			}
		}.start();
	}
}
