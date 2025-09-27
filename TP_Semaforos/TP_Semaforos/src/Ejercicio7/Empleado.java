package Ejercicio7;

import java.util.concurrent.Semaphore;

public class Empleado implements Runnable {
  private String nombre;
  private Semaphore semaforoAtendido;
  private Semaphore semaforoMozo;
  private Semaphore semaforoSilla;

  public Empleado(String nombre, Semaphore semaforoAtendido, Semaphore semaforoMozo, Semaphore semaforoSilla) {
    this.nombre = nombre;
    this.semaforoAtendido = semaforoAtendido;
    this.semaforoMozo = semaforoMozo;
    this.semaforoSilla = semaforoSilla;
  }

  public void run() {
    while (true) {
      try {
        System.out.println("Esperando asiento libre...");
        semaforoSilla.acquire();
        System.out.println("Empleado " + nombre + " se ha sentado");
        
        semaforoMozo.release();
        System.out.println(nombre + " siendo atendido");
        semaforoAtendido.acquire();
        Thread.sleep(2000); // Simula el tiempo que el empleado está en la silla
        System.out.println(nombre + " ha terminado de ser atendido");
        semaforoSilla.release();
        Thread.sleep(5000); // Simula el tiempo entre atenciones
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
