package Ejercicio4;

import java.util.concurrent.Semaphore;

public class Garita {
  Semaphore semVendedor = new Semaphore(1);
  Semaphore semComprador = new Semaphore(0);
  Semaphore mutex = new Semaphore(1);

  public void venderTicket() throws Exception {
    semVendedor.acquire();
    System.out.println("Ticket Disponible 🎫");
    semComprador.release();
  }

  public void comprarTicket() throws Exception {
    mutex.acquire();
    semComprador.acquire();
    System.out.println(Thread.currentThread().getName() + " compro su ticket.");
    semVendedor.release();
    mutex.release();
  }
}
