package Ej2;

public class Automovil implements Runnable{
  private MonitorFerri monitor;

  public Automovil(MonitorFerri m) {
    this.monitor = m;
  }

  public void run() {
    try {
      monitor.abordarAuto();
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
