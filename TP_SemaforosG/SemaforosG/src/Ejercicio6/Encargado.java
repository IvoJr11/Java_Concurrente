package Ejercicio6;

public class Encargado implements Runnable {
  private Mirador mirador;
  public Encargado(Mirador m) {
    this.mirador = m;
  }

  public void run() {
    while(true) {
      try {
        mirador.atenderVisitante();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }
}
