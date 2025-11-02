package Ej2;

public class Pasajero implements Runnable{
  private MonitorFerri monitor;
  
  public Pasajero(MonitorFerri m) {
    this.monitor = m;
  }
  
  public void run() {
    try {
      monitor.abordarPasajero();
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
