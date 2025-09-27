package Ejercicio4;

import java.util.concurrent.Semaphore;

public class Impresora {
  private Semaphore semaphore;
  private int id;
  public Impresora(int id) {
    this.id = id;
    this.semaphore = new Semaphore(1);
  }

  public boolean imprimir(String nombreCliente) {
    boolean exito = false;
    try {
      exito = semaphore.tryAcquire();
      if (exito) {
        System.out.println(nombreCliente + " imprimiendo en impresora " + id);
        Thread.sleep(2000); // Simula el tiempo de impresión
        System.out.println("Impresion finalizada en impresora " + id);
        semaphore.release();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return exito;
  }
}
