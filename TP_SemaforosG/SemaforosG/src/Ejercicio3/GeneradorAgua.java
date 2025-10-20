package Ejercicio3;

import java.util.concurrent.Semaphore;

public class GeneradorAgua {
  private Semaphore oxigenosEspera = new Semaphore(0);
  private Semaphore hidrogenosEspera = new Semaphore(0);
  private int oxigenosDisponibles = 0;
  private int hidrogenosDisponibles = 0;
  private Semaphore grupoAtomos = new Semaphore(0);
  private Semaphore mutex = new Semaphore(1);
  private int depositoAgua = 0;
  private final int CAPACIDAD_DEPOSITO = 10;


  public void Olisto() {
    try {
      mutex.acquire();
      oxigenosDisponibles++;
      if (hidrogenosDisponibles >= 2) {
        hidrogenosDisponibles -= 2;
        oxigenosDisponibles--;
        hidrogenosEspera.release(2);
        oxigenosEspera.release();
      }
      mutex.release();
      oxigenosEspera.acquire();
      HacerAgua();
      grupoAtomos.release();
      grupoAtomos.acquire();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public void Hlisto() {
    try {
      mutex.acquire();
      hidrogenosDisponibles++;
      if(hidrogenosDisponibles >= 2 && oxigenosDisponibles >= 1) {
        hidrogenosDisponibles -= 2;
        oxigenosDisponibles--;
        hidrogenosEspera.release(2);
        oxigenosEspera.release();
      }
      mutex.release();
      hidrogenosEspera.acquire();
      grupoAtomos.release();
      grupoAtomos.acquire();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public synchronized void HacerAgua() {
    System.out.println("Molecula "+ Thread.currentThread().getName() + " formando agua.");
    if(depositoAgua < CAPACIDAD_DEPOSITO) {
      depositoAgua++;
      try {
        Thread.sleep((int)(Math.random() * 1000));
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      System.out.println("Agua formada: " + depositoAgua);
    }
  }
}
