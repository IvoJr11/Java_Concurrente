package Ejercicio3;

public class Oxigeno implements Runnable{
  private GeneradorAgua generador;
  
  public Oxigeno(GeneradorAgua generador) {
    this.generador = generador;
  }

  public void run() {
    try {
      Thread.sleep((int) Math.random()*5000 + 1);
      generador.Olisto();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
