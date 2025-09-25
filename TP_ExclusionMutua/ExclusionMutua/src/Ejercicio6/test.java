package Ejercicio6;

import java.util.Scanner;

public class test {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Resultado resultado = new Resultado();
    int[] arregloNum = new int[50000];
    cargarArreglo(arregloNum);
    System.out.println("Introducir cantidad deseada de hilos");
    int k = scanner.nextInt();
    Thread[] arregloHilos = new Thread[k];
    cargarHilos(arregloHilos, arregloNum, k, resultado);
    iniciarHilos(arregloHilos);
    esperarHilos(arregloHilos);
    System.out.println("Valor final total: " + resultado.getValorFinal());
    scanner.close();
  }

  public static void cargarArreglo(int[] arregloNum) {
    for (int i = 0; i < arregloNum.length; i++) {
      arregloNum[i] = (int) (Math.random() * 10) + 1;
    }
  }

  public static void cargarHilos(Thread[] arregloHilos, int[] arregloNum, int k, Resultado resultado) {
    int segmento = arregloNum.length / k;
    for (int i = 0; i < arregloHilos.length; i++) {
      arregloHilos[i] = new Thread(new Sumador(arregloNum, i * segmento, ((i + 1) * segmento), resultado), "Hilo " + (i + 1));
    }
  }

  public static void iniciarHilos(Thread[] arregloHilos) {
    for (int i = 0; i < arregloHilos.length; i++) {
      arregloHilos[i].start();
    }
  }

  public static void esperarHilos(Thread[] arregloHilos) {
    for (int i = 0; i < arregloHilos.length; i++) {
      try {
        arregloHilos[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
