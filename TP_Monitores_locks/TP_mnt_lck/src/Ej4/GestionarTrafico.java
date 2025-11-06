package Ej4;

import java.util.concurrent.ThreadLocalRandom;

public class GestionarTrafico {
  private boolean haciaNorte = true;
  private int autosEsperaS = 0;
  private int autosEsperaN = 0;
  private int cantViajes = 0;

  public synchronized void EntrarCocheDelNorte() throws Exception {
    System.out.println(Thread.currentThread().getName() + " intentando cruzar el puente por el norte...");
    autosEsperaN++;
    while(!haciaNorte) {
      this.wait();
    }
    autosEsperaN--;
  }
  
  public synchronized void cruzarPuente() throws Exception {
    int random = ThreadLocalRandom.current().nextInt(0, 1001);
    System.out.println(Thread.currentThread().getName() + " cruzando el puente");
    Thread.sleep(random);
    cantViajes++;
    System.out.println("Viajes totales: " + cantViajes);
  }

  public synchronized void SalirCocheDelNorte() throws Exception {
    System.out.println(Thread.currentThread().getName() + " saliendo por el sur");
    if(cantViajes == 10 && autosEsperaS>0) {
      haciaNorte = false;
      cantViajes = 0;
    }
    this.notifyAll();
  }
  
  public synchronized void EntrarCocheDelSur() throws Exception {
    System.out.println(Thread.currentThread().getName() + " intentando cruzar el puente por el sur...");
    autosEsperaS++;
    while(haciaNorte) {
      this.wait();
    }
    autosEsperaS--;
  }

  public synchronized void SalirCocheDelSur() throws Exception {
    System.out.println(Thread.currentThread().getName() + " saliendo por el norte");
    if(cantViajes == 10 && autosEsperaN >0) {
      haciaNorte = true;
      cantViajes = 0;
    }
    this.notifyAll();
  }

}