package Ejercicio6;

public class Visitante implements Runnable {
  private Mirador mirador;
  public Visitante(Mirador m) {
    this.mirador = m;
  }

  public void run() {
    try {
      mirador.subirEscalera();
      mirador.subirMirador();
      mirador.tirarsePorTobogan();
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
