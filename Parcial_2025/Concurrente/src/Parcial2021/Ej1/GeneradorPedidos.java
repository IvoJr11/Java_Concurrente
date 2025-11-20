package Parcial2021.Ej1;

public class GeneradorPedidos implements Runnable {
  private EntregasLock entregasLock;

  public GeneradorPedidos(EntregasLock e) {
    entregasLock = e;
  }
  
  public void run() {
    try {
      while (true) {
        entregasLock.generarPedido();
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
