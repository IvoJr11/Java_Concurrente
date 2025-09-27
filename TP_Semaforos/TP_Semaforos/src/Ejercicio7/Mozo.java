package Ejercicio7;

import java.util.concurrent.Semaphore;

public class Mozo implements Runnable {
  private Semaphore semaforoMozo;
  private Semaphore semaforoAtendido;

  public Mozo(Semaphore semaforoMozo, Semaphore semaforoAtendido) {
    this.semaforoMozo = semaforoMozo;
    this.semaforoAtendido = semaforoAtendido;
  }

  public void run() {
    while (true) {
      try {
        System.out.println("Mozo esperando para atender...");
        semaforoMozo.acquire();
        
        System.out.println("Mozo atendiendo");
        Thread.sleep(2000); // Simula el tiempo de atención
        
        semaforoAtendido.release();
        System.out.println("Mozo ha terminado de atender");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
