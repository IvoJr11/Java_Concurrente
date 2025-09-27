package Ejercicio6;

import java.util.concurrent.Semaphore;

public class Taxista implements Runnable{
  private Semaphore semTaxista;
  private Semaphore semPasajero;

  public Taxista(Semaphore semTaxista, Semaphore semPasajero) {
    this.semTaxista = semTaxista;
    this.semPasajero = semPasajero;
  }

  public void run() {
    while (true) {
      try {
        System.out.println("El taxista está esperando a un pasajero.");
        semTaxista.acquire();
      
        System.out.println("El taxista fue reservado");
        Thread.sleep(1000); // Simula el tiempo de espera para el pasajero
      
        System.out.println("El taxista avisa que llegaron al destino.");
        semPasajero.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
