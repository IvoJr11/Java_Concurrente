package Ej2;

public class Ferry implements Runnable {
  MonitorFerri monitor;
  
  public Ferry(MonitorFerri m) {
    this.monitor = m;
  }

  public void run() {
    while (true) {
      try {
        monitor.zarpar();
        monitor.realizarViaje();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }
}
