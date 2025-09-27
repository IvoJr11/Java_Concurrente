package Ejercicio6;

import java.util.concurrent.Semaphore;

public class Pasajero implements Runnable{
  private Semaphore semPasajero;
  private Semaphore semTaxista;

  public Pasajero(Semaphore semPasajero, Semaphore semTaxista) {
    this.semPasajero = semPasajero;
    this.semTaxista = semTaxista;
  }

  public void run() {
    while (true) {
      try {
        Thread.sleep(1000); // Simula el tiempo entre viajes
        System.out.println("El pasajero está esperando para pedir un taxi.");
        semTaxista.release();
      
        System.out.println("El pasajero está en camino al taxi");
        semPasajero.acquire();
      
        System.out.println("El pasajero ha llegado al destino.");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
