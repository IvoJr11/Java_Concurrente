package Parcial2021.Ej2;

public class Coordinador implements Runnable {
  private SalaBaile salaBaile;

  public Coordinador(SalaBaile sala) {
    this.salaBaile = sala;
  }

  public void run() {
    try {
      while (true) {
        salaBaile.organizarParejas();
        Thread.sleep(500);
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
