package Ej1;

public class MonitorSala {
  private int mesasDisponibles;

  public MonitorSala(int m) {
    this.mesasDisponibles = m;
  }
  
  public synchronized void verificarMesa() throws Exception {
    System.out.println(Thread.currentThread().getName() + " intentando ocupar mesa");
    while(mesasDisponibles == 0) {
      System.out.println("Todas las Mesas ocupadas");
      this.wait();
    }
    mesasDisponibles--;
    System.out.println(Thread.currentThread().getName() + " ocupando mesa");
    System.out.println("Mesas disponibles: " + mesasDisponibles);
  }
  
  public synchronized void liberarMesa() throws Exception {
    System.out.println(Thread.currentThread().getName() + " libera mesa");
    System.out.println("Mesas disponibles: " + mesasDisponibles);
    mesasDisponibles++;
    this.notify();
  }
}
