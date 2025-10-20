package Ejercicio1;

import java.util.concurrent.Semaphore;

public class GestorPiscina {
  private Semaphore capacidadActual;
  
  public GestorPiscina(int capacidadMaxima) {
    this.capacidadActual = new Semaphore(capacidadMaxima, true);
  }

  public boolean ingresarPiscina() {
    boolean entro = false;
    try {
      System.out.println("Persona" + Thread.currentThread().getName() + " intentando ingresar a la piscina.");
      capacidadActual.acquire();
      System.out.println("Persona" + Thread.currentThread().getName() + " ingresó a la piscina.");
      entro = true;
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return entro;
  }

  public void salirPiscina() {
    capacidadActual.release();
    System.out.println("Persona" + Thread.currentThread().getName() + " salió de la piscina.");
  }
}
