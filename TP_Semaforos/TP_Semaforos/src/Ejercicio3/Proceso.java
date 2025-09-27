package Ejercicio3;

import java.util.concurrent.Semaphore;

public class Proceso extends Thread {
  private Semaphore sActual;
  private Semaphore sSiguiente;
  public Proceso(Semaphore s1, Semaphore s2) {
    this.sActual = s1;
    this.sSiguiente = s2;
  }
  public void run() {
    while(true) {
      try {
        sActual.acquire();
        System.out.println(Thread.currentThread().getName());
        sSiguiente.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
