package Ejercicio4;

public class ControlTren implements Runnable{
  private Tren tren;

  public ControlTren(Tren t) {
    this.tren = t;
  }

  public void run() {
    while (true) {
      try {
        tren.iniciarViaje();
      } catch (Exception e) {
  
      }
    }
  }
}
