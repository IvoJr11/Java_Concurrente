package Punto8;

public class Cliente {
  private String nombre;
  private int[] carroCompra;
  
  public Cliente(String n, int[] c) {
    this.nombre = n;
    this.carroCompra = c;
  }
  
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public int[] getCarroCompra() {
    return carroCompra;
  }
  public void setCarroCompra(int[] carroCompra) {
    this.carroCompra = carroCompra;
  }
  
}
