package Toboganes;

import java.util.concurrent.Semaphore;

public class Persona implements Runnable {
  private String nombre;
  private Mirador mirador;
  private Semaphore semTobogan1;
  private Semaphore semTobogan2;
  private Semaphore semEscalera;

  public Persona(String nombre, Mirador mirador) {
    this.nombre = nombre;
    this.mirador = mirador;
    this.semTobogan1 = mirador.getSemTobogan1();
    this.semTobogan2 = mirador.getSemTobogan2();
    this.semEscalera = mirador.getSemEscalera();
  }

  public void run() {
    boolean exito = false;
    try {
      semEscalera.acquire(); // SEMESCALERA --> 10 - 1 = 9
      System.out.println(nombre + " está subiendo la escalera.");
      Thread.sleep(1000); // Simula el tiempo que tarda en subir la escalera
      semEscalera.release(); // SEMESCALERA --> 9 + 1 = 10
      System.out.println(nombre + " ha llegado al mirador.");
      while (!exito) {
        exito = mirador.asignarTobogan(this);
        if(!exito) {
          System.out.println(nombre + " no pudo usar ningún tobogán, vuelve a intentar.");
          Thread.sleep(500);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Mirador getMirador() {
    return mirador;
  }

  public void setMirador(Mirador mirador) {
    this.mirador = mirador;
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


    // while (!exito) {
    //   try {
    //     System.out.println(nombre + " está esperando para subir la escalera.");
    //     semEscalera.acquire(); // SEMESCALERA --> 10 - 1 = 9
    //     System.out.println(nombre + " está subiendo la escalera.");
    //     Thread.sleep(1000); // Simula el tiempo que tarda en subir la escalera
    //     semEscalera.release(); // SEMESCALERA --> 9 + 1 = 10
    //     System.out.println(nombre + " ha llegado al mirador.");
    //     exito = semTobogan1.tryAcquire();
    //     if(exito) {
    //       System.out.println(nombre + " está usando el tobogán 1.");
    //       mirador.usarTobogan1();
    //       System.out.println(nombre + " ha terminado de usar el tobogán 1.");
    //       exito = true;
    //     } else {
    //       semTobogan2.acquire();
    //       System.out.println(nombre + " está usando el tobogán 2.");
    //       mirador.usarTobogan2();
    //       System.out.println(nombre + " ha terminado de usar el tobogán 2.");
    //       exito = true;
    //     }
    //   } catch (InterruptedException e) {
    //     e.printStackTrace();
    //   }
    // }