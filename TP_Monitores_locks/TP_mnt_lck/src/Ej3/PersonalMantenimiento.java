package Ej3;

public class PersonalMantenimiento implements Runnable {
  private SalaObservatorio salaMuseo;

  public PersonalMantenimiento(SalaObservatorio s) {
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
