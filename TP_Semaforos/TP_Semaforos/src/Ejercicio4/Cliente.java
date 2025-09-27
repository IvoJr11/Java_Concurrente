package Ejercicio4;

public class Cliente extends Thread {
  private String nombre;
  private Gestor gestor;
  public Cliente(String nombre, Gestor gestor) {
    this.nombre = nombre;
    this.gestor = gestor;
  }

  public void run() {
    while(true) {
      gestor.intentarImprimir(nombre);
      try {
        Thread.sleep(5000); // Espera antes de intentar imprimir de nuevo
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
