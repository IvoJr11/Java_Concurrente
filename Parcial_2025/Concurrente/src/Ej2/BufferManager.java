package Ej2;

import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class BufferManager {
  private Semaphore mutexInsertar = new Semaphore(1);
  private Semaphore mutexExtraer = new Semaphore(1);

  private Semaphore esperarOscilacion = new Semaphore(0);

  private Queue<Object> colaInsertar = new LinkedList<>();
  private Queue<Object> colaExtraer = new LinkedList<>();

  public void insertarElemento() throws Exception {
    mutexInsertar.acquire();
    colaInsertar.add(1);
    System.out.println("Añadido elemento a la cola de insercion");
    System.out.println("Tamaño actual: " + colaInsertar.size());
    mutexInsertar.release();
  }

  public void extraerElemento() throws Exception {
    mutexExtraer.acquire();
    if(!colaExtraer.isEmpty()) {
      colaExtraer.remove();
      System.out.println("Se extrajo un elemento de la cola");
      System.out.println("Cantidad de elementos en la cola de extraccion: " + colaExtraer.size());
      mutexExtraer.release();
      verificarColaVacia();
    } else {
      mutexExtraer.release();
      verificarColaVacia();
      esperarOscilacion.acquire();
      mutexExtraer.acquire();
      colaExtraer.remove();
      System.out.println("Se extrajo un elemento de la cola");
      System.out.println("Cantidad de elementos en la cola de extraccion: " + colaExtraer.size());
      mutexExtraer.release();
    }
  }

  public void verificarColaVacia() throws Exception {
    mutexExtraer.acquire();
    mutexInsertar.acquire();
    if(colaExtraer.isEmpty() && !colaInsertar.isEmpty()) {
      System.out.println("Oscilando colas...");
      Queue<Object> colaAux = colaExtraer;
      colaExtraer = colaInsertar;
      colaInsertar = colaAux;
    }
    mutexInsertar.release();
    mutexExtraer.release();
    esperarOscilacion.release();
  }
}
