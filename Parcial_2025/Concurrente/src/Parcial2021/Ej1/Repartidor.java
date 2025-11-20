package Parcial2021.Ej1;

public class Repartidor implements Runnable {
  private EntregasLock entregasLock;
  private int cantViajes = 0;

  public Repartidor(EntregasLock e) {
    this.entregasLock = e;
  }

  public void run() {
    try {
      while (true) {
        entregasLock.entregarPedido();
        cantViajes++;
        if(cantViajes == 10) {
          Thread.sleep(2000);
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
