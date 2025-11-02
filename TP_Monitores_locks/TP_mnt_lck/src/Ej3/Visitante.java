package Ej3;

public class Visitante implements Runnable {
  private SalaMuseo salaMuseo;
  private boolean esDiscapacitado;

  public Visitante(SalaMuseo s, boolean e) {
    this.salaMuseo = s;
    esDiscapacitado = e;
  }
  
  public void run() {
    try {
      salaMuseo.ingresaVisitante(esDiscapacitado);
      salaMuseo.estudiar(esDiscapacitado);
    } catch (Exception e) {
      
    }
  }
}
