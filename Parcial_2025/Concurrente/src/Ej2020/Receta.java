package Ej2020;

public class Receta {
  private int numReceta;
  private int numMedico;
  private int importe;

  public Receta(int n, int m, int i) {
    this.numReceta = n;
    this.numMedico = m;
    this.importe = i;
  }

  public int getNumReceta() {
    return numReceta;
  }

  public void setNumReceta(int numReceta) {
    this.numReceta = numReceta;
  }

  public int getNumMedico() {
    return numMedico;
  }

  public void setNumMedico(int numMedico) {
    this.numMedico = numMedico;
  }

  public int getImporte() {
    return importe;
  }

  public void setImporte(int importe) {
    this.importe = importe;
  }

  
}
