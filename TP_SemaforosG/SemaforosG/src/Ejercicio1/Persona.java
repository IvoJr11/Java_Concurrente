package Ejercicio1;

public class Persona implements Runnable {
  GestorPiscina gestor;
  public Persona(GestorPiscina gestor) {
    this.gestor = gestor;
  }
  
  public void run() {
    boolean bool = gestor.ingresarPiscina();
    if(bool) {
      try {
        Thread.sleep((int)(Math.random() * 5000));
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      gestor.salirPiscina();
    }
  }
}
