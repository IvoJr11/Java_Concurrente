package Ejercicio5;

import java.util.concurrent.Semaphore;

public class Impresora {
  private Semaphore semaphore;
  private char tipo;
  private int id;
  public Impresora(int id, char tipo) {
    this.id = id;
    this.semaphore = new Semaphore(1);
    this.tipo = tipo;
  }

  public boolean imprimir(String nombreCliente, char tipoCliente) {
    boolean exito = false;
    if(tipo == tipoCliente || tipoCliente == 'C') {
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
    }
    return exito;
  }
}
