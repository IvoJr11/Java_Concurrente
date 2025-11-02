package Ej2;

public class MonitorFerri {
  private static final int CAPACIDAD_MAXIMA = 20;
  private static final int ESPACIO_AUTO = 4;
  
  private int capacidadFerri = CAPACIDAD_MAXIMA;
  private boolean enRecorrido = false;

  private int enEspera = 0;
  private boolean abierto = true;
  private int numViajes = 0;
  private boolean ultimoRecorrido = false;

  public synchronized void abordarAuto() throws Exception {
    if(abierto) {
      enEspera++;
      System.out.println(Thread.currentThread().getName() + " intentando abordar...");
      while(capacidadFerri<ESPACIO_AUTO) {
        System.out.println("Capacidad insuficiente para un auto");
        this.wait();
      }
      enEspera--;
      System.out.println(Thread.currentThread().getName() + " abordando el ferry");
      capacidadFerri-=ESPACIO_AUTO;
      System.out.println("Capacidad actual: " + capacidadFerri);
      if(!enRecorrido) {
        this.notifyAll();
      }
    }
  }
  
  public synchronized void abordarPasajero() throws Exception {
    if(abierto) {
      enEspera++;
      System.out.println(Thread.currentThread().getName() + " intentando abordar...");
      while(capacidadFerri==0) {
        System.out.println("Capacidad insuficiente para un pasajero");
        this.wait();
      }
      enEspera--;
      System.out.println(Thread.currentThread().getName() + " abordando el ferry");
      capacidadFerri--;
      System.out.println("Capacidad actual: " + capacidadFerri);
      if(!enRecorrido) {
        this.notifyAll();
      }
    }
  }

  public synchronized void zarpar() throws Exception {
    System.out.println("Ferry intentando zarpar");
    while (capacidadFerri != 0 && (enEspera != 0 || !abierto)) {
      this.wait();
    }
    if(enEspera == 0 && numViajes != 0) {
      abierto = false;
    }
  }

  public synchronized void realizarViaje() throws Exception {
    System.out.println("---------- Ferry zarpando ----------");
    enRecorrido = true;
    Thread.sleep(7000);
    System.out.println("---------- Ferry abordando ----------");
    capacidadFerri = CAPACIDAD_MAXIMA;
    enRecorrido = false;
    numViajes++;
    this.notifyAll();
  }
}
