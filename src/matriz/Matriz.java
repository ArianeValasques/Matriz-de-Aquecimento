package matriz;

import java.util.Random;
import java.text.DecimalFormat;

public class Matriz {
	double [][]matriz;
	static Random random = new Random();
	static DecimalFormat df = new DecimalFormat("#,###"); //Configuração para mostrar apenas parte inteira

	public Matriz() {
		
	}

	public static void criaMatriz(double [][]matriz, int tam) { //PREENCHIMENTO DOS ELEMENTOS
		// BORDAS COM 1000 E ALEATORIOS PARA O INTERIOR
		for (int i = 0; i < tam; i++) {
				
			matriz[i][0] = 1000;
				matriz[0][i] = 1000;
				matriz[tam-1][i] = 1000;
				matriz[i][tam-1] = 1000;
			
		}
		
		for (int i = 1; i < tam-1; i++) {
			for (int j = 1; j < tam-1; j++) {
					matriz[i][j] = random.nextInt(100) + 1;
			}
		}
	}

	public static void visualizaMatriz(double [][]matriz, int tam) {
		// VISUALIZAÇÃO DA MATRIZ NA TELA
		for (int i = 0; i < tam; i++) {
			for (int j = 0; j < tam; j++) {
				if (matriz[i][j] == 1000) {
					System.out.print(df.format(matriz[i][j]) + " ");
				} else {
					System.out.print(" " + df.format(matriz[i][j]) + "  ");
				}
			}
			System.out.println();
		}

	}

}
