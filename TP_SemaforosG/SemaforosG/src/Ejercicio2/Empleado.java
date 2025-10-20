package Ejercicio2;

public class Empleado implements Runnable {
  private GestorComedor comedor;
  
  public Empleado(GestorComedor comedor) {
    this.comedor = comedor;
  }
  
  public void run() {
    comedor.entrarComedor();
  }
}
