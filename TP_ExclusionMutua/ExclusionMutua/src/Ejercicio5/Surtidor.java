package Ejercicio5;

public class Surtidor {
  public int id;
  private int combustibleRestante;

  public Surtidor(int combustibleInicial, int id) {
    this.combustibleRestante = combustibleInicial;
    this.id = id;
  }

  public synchronized boolean cargarCombustible(Auto auto) {
    boolean exito = false;
    if(this.combustibleRestante>0) {
      if(this.combustibleRestante >= 2000) {
        auto.setTanque(2000);
        this.combustibleRestante -= 2000;
      } else {
        auto.setTanque(this.combustibleRestante);
        this.combustibleRestante = 0;
      }
      exito = true;
    }
    System.out.println("Combustible restante en surtidor " + this.id + ": " + this.combustibleRestante);
    return exito;
  }

  public synchronized int getCombustibleRestante() {
    return combustibleRestante;
  }

  public void setCombustibleRestante(int combustibleRestante) {
    this.combustibleRestante = combustibleRestante;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
