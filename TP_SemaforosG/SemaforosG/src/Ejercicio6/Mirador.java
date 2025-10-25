package Ejercicio6;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Mirador {
  private Semaphore escalera;
  private Semaphore toboganL = new Semaphore(1 ,true);
  private Semaphore toboganR = new Semaphore(1, true);
  private Semaphore permisoSubir = new Semaphore(1);
  private Semaphore encargado = new Semaphore(0);
  private Semaphore visitante = new Semaphore(0);
  private int ultimaAsignacion = 0;

  public Mirador(int e) {
    escalera = new Semaphore(e, true);
  }

  public void atenderVisitante() throws Exception {
    visitante.acquire();
    System.out.println("Asignando tobogan al visitante 🕑...");
    Thread.sleep(1000);
    int bit = ThreadLocalRandom.current().nextInt(0, 2);
    if(bit == 1) {
      System.out.println("Asignado el tobogan de la derecha.");
      ultimaAsignacion = 1;
    } else {
      System.out.println("Asignado el tobogan de la izquierda.");
      ultimaAsignacion = 0;
    }
    encargado.release();
  }

  public void subirEscalera() throws Exception {
    escalera.acquire();
  }

  public void subirMirador() throws Exception {
    permisoSubir.acquire();
    System.out.println(Thread.currentThread().getName() + " subió al mirador.");
    visitante.release();
    encargado.acquire();
    permisoSubir.release();
  }

  public void tirarsePorTobogan() throws Exception {
    if(ultimaAsignacion == 1) {
      toboganR.acquire();
      System.out.println(Thread.currentThread().getName() + " bajando por el tobogan derecho...");
      Thread.sleep(5000);
      System.out.println("Liberado el tobogan derecho.");
      toboganR.release();
    } else {
      toboganL.acquire();
      System.out.println(Thread.currentThread().getName() + " bajando por el tobogan izquierdo...");
      Thread.sleep(5000);
      System.out.println("Liberado el tobogan izquierdo.");
      toboganL.release();
    }
    
  }
}
