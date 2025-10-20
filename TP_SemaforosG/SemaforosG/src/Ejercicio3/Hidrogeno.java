package Ejercicio3;

public class Hidrogeno implements Runnable {
  private GeneradorAgua generador;
  
  public Hidrogeno(GeneradorAgua generador) {
    this.generador = generador;
  }

  public void run() {
    try {
      Thread.sleep((int) Math.random()*5000 + 1);
      generador.Hlisto();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
