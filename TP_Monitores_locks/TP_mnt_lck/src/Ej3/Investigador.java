package Ej3;

public class Investigador implements Runnable{
  private SalaMuseo salaMuseo;

  public Investigador(SalaMuseo s) {
    this.salaMuseo = s;
  }

  public void run() {
    try {
      salaMuseo.ingresaInvestigador();
      salaMuseo.investigar();
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
