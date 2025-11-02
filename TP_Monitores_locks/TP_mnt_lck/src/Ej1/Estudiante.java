package Ej1;

public class Estudiante implements Runnable {
  private MonitorSala salaEstudio;

  public Estudiante(MonitorSala m) {
    this.salaEstudio = m;
  }

  public void run() {    
    try {
      salaEstudio.verificarMesa();
      Thread.sleep((int) (Math.random() * 10000 + 1));
      salaEstudio.liberarMesa();
    } catch (Exception e) {
    }
  }
}
