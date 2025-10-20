package Ejercicio3;

public class test {
  public static void main(String[] args) {
    GeneradorAgua generador = new GeneradorAgua();
    Thread[] threads = new Thread[30];

    for (int i = 1; i <= 30; i++) {
      if(i % 2 == 0) {
        threads[i-1] = new Thread(new Oxigeno(generador), "Oxi-" + i);
      } else {
        threads[i-1] = new Thread(new Hidrogeno(generador), "Hidro-" + i);
      }
    }

    for (Thread thread : threads) {
      thread.start();
    }
  }
}
