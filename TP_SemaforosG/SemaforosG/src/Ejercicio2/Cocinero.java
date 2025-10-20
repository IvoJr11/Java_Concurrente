package Ejercicio2;

public class Cocinero implements Runnable {
  private GestorComedor comedor;
  
  public Cocinero(GestorComedor comedor) {
    this.comedor = comedor;
  }

  public void run() {
    while(true) {
      try {
        comedor.atenderPedidoComida();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
