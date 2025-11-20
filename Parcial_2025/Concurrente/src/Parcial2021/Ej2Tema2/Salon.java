package Parcial2021.Ej2Tema2;

import java.util.concurrent.Semaphore;

public class Salon {
  private Semaphore act1t1 = new Semaphore(4);
  private Semaphore act2t1 = new Semaphore(4);
  private Semaphore act3t1 = new Semaphore(4);
  
  private Semaphore act1t2 = new Semaphore(4);
  private Semaphore act2t2 = new Semaphore(4);
  private Semaphore act3t2 = new Semaphore(4);

  private Semaphore iniciarActividad = new Semaphore(0);
  private Semaphore mutex = new Semaphore(1);
  private Semaphore ingresarActividadSem1 = new Semaphore(12);
  private Semaphore ingresarActividadSem2 = new Semaphore(12);

  private int cantPersonas = 0;

  public void ingresarActividad(int i) throws Exception {
    ingresarActividadSem1.acquire();
    switch(i) {
      case 1:
        if(!act1t1.tryAcquire()) { if(!act2t1.tryAcquire()) { act3t1.acquire(); } }
        break;
      case 2:
        if(!act2t1.tryAcquire()) { if(!act1t1.tryAcquire()) { act3t1.acquire(); } }
        break;
      case 3:
        if(!act3t1.tryAcquire()) { if(!act2t1.tryAcquire()) { act1t1.acquire(); } }
        break;
    }
    mutex.acquire();
    try {
      System.out.println("Ingresa a actividad " + i);
      cantPersonas++;
      System.out.println("Cant personas: " + cantPersonas);
      if(cantPersonas == 12) {
        iniciarActividad.release(12);
      }
    } finally {
      mutex.release();
    }
    iniciarActividad.acquire();
    System.out.println(Thread.currentThread().getName() + " inicia su actividad");
    Thread.sleep(750);
    System.out.println(Thread.currentThread().getName() + " finaliza su actividad");
    mutex.acquire();
    try {
      cantPersonas--;
      switch(i) {
        case 1:
          act1t1.release();
          break;
        case 2:
          act2t1.release();
          break;
        case 3:
          act3t1.release();
          break;
      }
      if(cantPersonas == 0) {
        ingresarActividadSem2.release(12);
      }
    } finally {
      mutex.release();
    }
    mutex.release();
  }

  public void iniciarSegundaActividad(int i) throws Exception {
    ingresarActividadSem2.acquire();
    switch(i) {
      case 1:
        if(!act1t2.tryAcquire()) { if(!act2t2.tryAcquire()) { act3t2.acquire(); } }
        break;
      case 2:
        if(!act2t2.tryAcquire()) { if(!act1t2.tryAcquire()) { act3t2.acquire(); } }
        break;
      case 3:
        if(!act3t2.tryAcquire()) { if(!act2t2.tryAcquire()) { act1t2.acquire(); } }
        break;
    }
    mutex.acquire();
    System.out.println("Ingresa a actividad " + i);
    cantPersonas++;
    System.out.println("Cant personas: " + cantPersonas);
    if(cantPersonas == 12) {
      iniciarActividad.release(12);
    }
    mutex.release();
    iniciarActividad.acquire();
    System.out.println(Thread.currentThread().getName() + " inicia su actividad");
    Thread.sleep(750);
    System.out.println(Thread.currentThread().getName() + " finaliza su actividad");
    mutex.acquire();
    cantPersonas--;
    switch(i) {
      case 1:
        act1t2.release();
        break;
      case 2:
        act2t2.release();
        break;
      case 3:
        act3t2.release();
        break;
    }
    if(cantPersonas == 0) {
      ingresarActividadSem1.release(12);
    }
    mutex.release();
  }

}
