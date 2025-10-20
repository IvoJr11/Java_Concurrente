package Ejercicio2;

public class Mozo implements Runnable {
  private GestorComedor comedor;
  
  public Mozo(GestorComedor comedor) {
    this.comedor = comedor;
  }
  
  public void run() {
    while(true) {
      try {
        comedor.atenderPedidoBebida();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
