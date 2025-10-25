package Ejercicio5;

import java.util.concurrent.Semaphore;

public class TorreControl {
  private Semaphore pista = new Semaphore(1 ,true);
  private Semaphore permisosDisp = new Semaphore(1);
  private int cantAterrizajes = 0;
  private int aterrizajesPendientes = 0;
  private int despegesPendientes = 0;
  private Semaphore permisoAt = new Semaphore(0);
  private Semaphore permisoDesp = new Semaphore(0);
  private Semaphore mutex = new Semaphore(1);

  public void aterrizar() throws Exception {
    mutex.acquire();
    aterrizajesPendientes++;
    if(cantAterrizajes % 10 != 0 || permisosDisp.tryAcquire()) {
      permisoAt.release();
    }
    mutex.release();
    permisoAt.acquire();
    pista.acquire();
    System.out.println(Thread.currentThread().getName() + " aterrizando ✈️...");
    Thread.sleep(5000);
    System.out.println(Thread.currentThread().getName() + " ha aterrizado. ✅");
    mutex.acquire();
    cantAterrizajes++;
    aterrizajesPendientes--;
    permisosDisp.release();
    pista.release();
    if(aterrizajesPendientes > 0 && cantAterrizajes % 10 != 0) {
      permisoAt.release();
    } else if (despegesPendientes > 0) {
      permisoDesp.release();
    } else {
      permisoAt.release();
    }
    mutex.release();
  }

  public void despegar() throws Exception {
    mutex.acquire();
    despegesPendientes++;
    if((cantAterrizajes % 10 == 0 || aterrizajesPendientes == 0) && permisosDisp.tryAcquire()) {
      permisoDesp.release();
    }
    mutex.release();
    permisoDesp.acquire();
    pista.acquire();
    System.out.println(Thread.currentThread().getName() + " despegando ✈️...");
    Thread.sleep(5000);
    System.out.println(Thread.currentThread().getName() + " ha despegado. ✅");
    mutex.acquire();
    despegesPendientes--;
    permisosDisp.release();
    pista.release();
    if(aterrizajesPendientes > 0) {
      permisoAt.release();
    } else if (despegesPendientes > 0) {
      permisoDesp.release();
    }
    mutex.release();
  }
}
