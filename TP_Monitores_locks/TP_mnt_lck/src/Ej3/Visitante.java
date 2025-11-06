package Ej3;

public class Visitante implements Runnable {
  private SalaObservatorio salaMuseo;
  private boolean esDiscapacitado;

  public Visitante(SalaObservatorio s, boolean e) {
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
