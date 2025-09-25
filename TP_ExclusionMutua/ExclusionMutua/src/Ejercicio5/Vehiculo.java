package Ejercicio5;

/*patente modelo marca km*/
public class Vehiculo {
  private String patente;
  private String modelo;
  private String marca;
  private int km;

  public Vehiculo(String patente, String modelo, String marca, int km) {
    this.patente = patente;
    this.modelo = modelo;
    this.marca = marca;
    this.km = km;
  }
  
  public void avanzar() {
    this.km += 100;
  }

  public String getPatente() {
    return patente;
  }
  public String getModelo() {
    return modelo;
  }
  public String getMarca() {
    return marca;
  }
  public int getKm() {
    return km;
  }

  public void setPatente(String patente) {
    this.patente = patente;
  }
  public void setModelo(String modelo) {
    this.modelo = modelo;
  }
  public void setMarca(String marca) {
    this.marca = marca;
  }
  public void setKm(int km) {
    this.km = km;
  }

}
