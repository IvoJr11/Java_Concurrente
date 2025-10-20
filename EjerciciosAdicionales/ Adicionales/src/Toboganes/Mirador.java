package Toboganes;

import java.util.concurrent.Semaphore;

public class Mirador {
  private Semaphore semTobogan1;
  private Semaphore semTobogan2;
  private Semaphore semEscalera;
  private Semaphore semEncargado = new Semaphore(0);

  public Mirador(int capacidad) {
    this.semTobogan1 = new Semaphore(1);
    this.semTobogan2 = new Semaphore(1);
    this.semEscalera = new Semaphore(capacidad);
  }

  public void run() {
    while(true) {
      try {
        semEncargado.acquire();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public boolean asignarTobogan(Persona persona) {
    boolean exito = false;
    if(semTobogan1.tryAcquire()) {
      System.out.println(persona.getNombre() + " está usando el tobogán 1.");
      usarTobogan1();
      System.out.println(persona.getNombre() + " ha terminado de usar el tobogán 1.");
      exito = true;
    } else {
      if(semTobogan2.tryAcquire()) {
        System.out.println(persona.getNombre() + " está usando el tobogán 2.");
        usarTobogan2();
        System.out.println(persona.getNombre() + " ha terminado de usar el tobogán 2.");
        exito = true;
      }
    }
    semEncargado.release();
    return exito;
  }

  public void usarTobogan1() {
    try {
      System.out.println("Usando tobogán 1...");
      Thread.sleep(2000); // Simula el tiempo que tarda en usar el tobogán
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      semTobogan1.release();
    }
  }

  public void usarTobogan2() {
    try {
      System.out.println("Usando tobogán 2...");
      Thread.sleep(2000); // Simula el tiempo que tarda en usar el tobogán
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      semTobogan2.release();
    }
  }

  public Semaphore getSemTobogan1() {
    return semTobogan1;
  }

  public void setSemTobogan1(Semaphore semTobogan1) {
    this.semTobogan1 = semTobogan1;
  }

  public Semaphore getSemTobogan2() {
    return semTobogan2;
  }

  public void setSemTobogan2(Semaphore semTobogan2) {
    this.semTobogan2 = semTobogan2;
  }

  public Semaphore getSemEscalera() {
    return semEscalera;
  }

  public void setSemEscalera(Semaphore semEscalera) {
    this.semEscalera = semEscalera;
  }

  
}