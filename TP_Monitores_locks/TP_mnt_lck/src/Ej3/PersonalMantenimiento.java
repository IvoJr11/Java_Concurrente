package Ej3;

public class PersonalMantenimiento implements Runnable {
  private SalaMuseo salaMuseo;

  public PersonalMantenimiento(SalaMuseo s) {
    this.salaMuseo = s;
  }

  public void run() {
    try {
      salaMuseo.ingresaConserje();
      salaMuseo.limpiar();
    } catch (Exception e) {

    }
  }
}
