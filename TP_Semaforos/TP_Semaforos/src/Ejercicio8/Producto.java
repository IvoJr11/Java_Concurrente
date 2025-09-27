package Ejercicio8;

import java.util.concurrent.Semaphore;

public class Producto implements Runnable{
  private Semaphore lineaElectrica;
  private Semaphore lineaMecanica;
  private Semaphore producto;
  private char tipo;
  private boolean ensamblado;
  private ControladorProduccion controlador;

  public Producto(Semaphore lineaElectrica, Semaphore lineaMecanica, char tipo, ControladorProduccion controlador) {
    this.lineaElectrica = lineaElectrica;
    this.lineaMecanica = lineaMecanica;
    this.producto = new Semaphore(0);
    this.tipo = tipo;
    this.ensamblado = false;
    this.controlador = controlador;
  }

  public void run() {
    while (!ensamblado) {
      System.out.println("Producto (" + tipo + ") esperando para ser ensamblado");
      if (tipo == 'E') {
        llegaElectrico();
      } else {
        llegaMecanico();
      }
    }
  }

  public void llegaElectrico() {
    controlador.registrarProducto(this);
    lineaElectrica.release();
    try {
      producto.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  public void llegaMecanico() {
    controlador.registrarProducto(this);
    lineaMecanica.release();
    try {
      producto.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void setEnsamblado() {
    this.ensamblado = true;
  }

  public Semaphore getProductoSemaphore() {
    return this.producto;
  }

}
