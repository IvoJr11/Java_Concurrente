package Ejercicio8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ControladorProduccion implements Runnable {
  private Semaphore lineaElectrica;
  private Semaphore lineaMecanica;
  private List<Producto> productosEnsamblados = new ArrayList<>();

  public ControladorProduccion(Semaphore lineaElectrica, Semaphore lineaMecanica) {
    this.lineaElectrica = lineaElectrica;
    this.lineaMecanica = lineaMecanica;
  }

  public void run() {
    while (true) {
      System.out.println("Controlador esperando para asignar producto a una linea de ensamblado");
      try {
        if (lineaElectrica.tryAcquire()) {
          System.out.println("Controlador asignando producto a la linea electrica");
        } else {
          System.out.println("Controlador asignando producto a la linea mecanica");
          lineaMecanica.acquire();
        }
        Thread.sleep(2000); // Simula el tiempo de ensamblaje
        sale();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void sale() {
    System.out.println("Producto ensamblado y saliendo de la linea de produccion");
    productosEnsamblados.get(productosEnsamblados.size() - 1).getProductoSemaphore().release();
    productosEnsamblados.get(productosEnsamblados.size() - 1).setEnsamblado();
  }

  public void registrarProducto(Producto producto) {
    productosEnsamblados.add(producto);
  }
}
