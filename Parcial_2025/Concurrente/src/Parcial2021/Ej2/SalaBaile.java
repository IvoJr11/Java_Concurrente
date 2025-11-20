package Parcial2021.Ej2;

import java.util.concurrent.Semaphore;

public class SalaBaile {
  private Semaphore fila1Sem = new Semaphore(0, true);
  private Semaphore fila2Sem = new Semaphore(0, true);

  private Semaphore mutexFila1 = new Semaphore(1);
  private Semaphore mutexFila2 = new Semaphore(1);
  
  private Semaphore puedeEntrarFila1 = new Semaphore(0);
  private Semaphore puedeEntrarFila2 = new Semaphore(0);
  
  private Semaphore puedenEntrarBaile = new Semaphore(0);
  private Semaphore mutexPista = new Semaphore(1);

  public void ingresarFila(boolean sonPareja, int fila) throws Exception{
    if(sonPareja) {
      System.out.println("Pareja uniéndose a la fila");
      mutexFila1.acquire();
      fila1Sem.release();
      mutexFila1.release();
      mutexFila2.acquire();
      fila2Sem.release();
      mutexFila2.release();
    } else {
      if(fila == 1) {
        System.out.println("Persona uniendose a la fila 1");
        mutexFila1.acquire();
        fila1Sem.release();
        mutexFila1.release();
      } else {
        System.out.println("Persona uniendose a la fila 2");
        mutexFila2.acquire();
        fila2Sem.release();
        mutexFila2.release();
      }
    }
  }

  public void entrarBaile(int fila) throws Exception {
    if(fila == 1) {
      puedeEntrarFila1.acquire();
    } else {
      puedeEntrarFila2.acquire();
    }
    mutexPista.acquire();
    puedenEntrarBaile.acquire();
    System.out.println("Pareja entrando a bailar... 💃");
    Thread.sleep((int) (Math.random() * 9000 + 1));
    System.out.println("Pareja termino de bailar, salen del salon");
    mutexPista.release();
  }

  public void organizarParejas() throws Exception {
    fila1Sem.acquire();
    fila2Sem.acquire();
    mutexFila1.acquire();
    mutexFila1.release();
    mutexFila2.acquire();
    mutexFila2.release();
    puedeEntrarFila1.release();
    puedeEntrarFila2.release();
    puedenEntrarBaile.release(2);
  }
}
