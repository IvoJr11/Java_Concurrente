package Ejercicio4;

import java.util.concurrent.Semaphore;

/*
 * sem -> capacidad del tren (n)
 * sem -> asientos libres (n)
 * sem -> compra de ticket
 * sem -> subir al tren
 * sem -> bajar del tren
*/

public class Tren {
  private int numViajes = 0;
  private int capacidadTren;
  private int asientosDisponiblesC;
  Semaphore puertaDelantera = new Semaphore(1);
  Semaphore asientosDisponibles;
  Semaphore bajarTren = new Semaphore(0);
  Semaphore puertaTrasera = new Semaphore(0);

  public Tren(int c) {
    this.capacidadTren = c;
    this.asientosDisponibles = new Semaphore(c ,true);
    this.asientosDisponiblesC = c;
  }

  public void iniciarViaje() throws Exception {
    System.out.println("[DEBUG] Entrando a iniciarViaje()");
    System.out.println("[DEBUG] asientosDisponiblesC = " + asientosDisponiblesC);
    if(asientosDisponiblesC == 0) {
      System.out.println("Iniciando recorrido de tren 🚂");
      Thread.sleep(5000);
      System.out.println("Recorrido n°" + numViajes + " finalizado");
      this.numViajes++;
      puertaTrasera.release();
      bajarTren.release(capacidadTren);
    }
  }

  public void subirTren() throws Exception {
    puertaDelantera.acquire();
    asientosDisponibles.acquire();
    asientosDisponiblesC--;
    Thread.sleep(500);
    System.out.println(Thread.currentThread().getName() + " sube al tren.");
    puertaDelantera.release();
  }

  public void bajarTren() throws Exception{
    bajarTren.acquire();
    puertaTrasera.acquire();
    System.out.println(Thread.currentThread().getName() + " bajando del tren...");
    asientosDisponiblesC++;
    asientosDisponibles.release();
    puertaTrasera.release();
    if(asientosDisponiblesC == capacidadTren) {
      puertaDelantera.release();
    }
  }
}
