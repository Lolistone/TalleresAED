package aed;

import java.util.Scanner;

import java.io.PrintStream;

class Archivos {
    float[] leerVector(Scanner entrada, int largo) {
        float[] vec = new float[largo];
        for (int i = 0; i < largo; i++) {
            vec[i] = entrada.nextFloat();
        }
        return vec;
    }

    float[][] leerMatriz(Scanner entrada, int filas, int columnas) {
        float[][] matriz = new float[filas][columnas];
        for (int i = 0; i < filas; i++) {
            matriz[i] = leerVector(entrada, columnas);
        }
        return matriz;
    }

    void imprimirPiramide(PrintStream salida, int alto) {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < (2*alto - 1); j++) {
                if ( (j < alto - i - 1) || (j > alto + i - 1) ) {
                    salida.print(" ");
                }
                else {
                    salida.print("*");
                }
            }
            salida.println("");
        }
    }

}

