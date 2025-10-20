package Ejercicio2;

import java.util.concurrent.Semaphore;

public class GestorComedor {
  private Semaphore espaciosDisponibles = new Semaphore(2, true);
  private Semaphore mozoDisponible = new Semaphore(1);
  private Semaphore cocineroDisponible = new Semaphore(1);
  private Semaphore pedidoBebida = new Semaphore(0);
  private Semaphore pedidoComida = new Semaphore(0);

  public void entrarComedor() {
    try {
      System.out.println("Empleado " + Thread.currentThread().getName() + " quiere ingresar al comedor.");
      espaciosDisponibles.acquire();
      System.out.println("Empleado " + Thread.currentThread().getName() + " ha ingresado al comedor.");
      int i = decidirPedido();
      hacerPedido(i);
      espaciosDisponibles.release();
      System.out.println("Empleado " + Thread.currentThread().getName() + " ha salido del comedor.");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public int decidirPedido() {
    return ((int) (Math.random() * 3)) + 1;
  }

  public void hacerPedido(int pedido) {
    try {
      switch (pedido) {
        case 1:  
          gestionarPedidoBebida();
          break;
        case 2:
          gestionarPedidoComida();
          break;
        case 3:
          pedirComidaYBebida();
          break;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public void gestionarPedidoBebida() throws InterruptedException {
    mozoDisponible.acquire();
    System.out.println("Empleado " + Thread.currentThread().getName() + " está esperando la bebida.");
    Thread.sleep(3000);
    pedidoBebida.release();
    mozoDisponible.release();
  }

  public void gestionarPedidoComida() throws InterruptedException {
    cocineroDisponible.acquire();
    System.out.println("Empleado " + Thread.currentThread().getName() + " está esperando la comida.");
    Thread.sleep(5000);
    pedidoComida.release();
    cocineroDisponible.release();
  }

  public void pedirComidaYBebida() throws InterruptedException {
    gestionarPedidoBebida();
    gestionarPedidoComida();
  }

  public void atenderPedidoBebida() throws InterruptedException {
    pedidoBebida.acquire();
    System.out.println("Mozo ha servido la bebida.");
  }
  
  public void atenderPedidoComida() throws InterruptedException {
    pedidoComida.acquire();
    System.out.println("Cocinero ha servido la comida.");
  }  
}
