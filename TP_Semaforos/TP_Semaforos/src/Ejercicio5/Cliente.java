package Ejercicio5;

public class Cliente extends Thread {
  private String nombre;
  private Gestor gestor;
  private char tipoImpresion; // 'A' o 'B'
  
  public Cliente(String nombre, Gestor gestor, char tipoImpresion) {
    this.nombre = nombre;
    this.gestor = gestor;
    this.tipoImpresion = tipoImpresion;
  }

  public void run() {
    while(true) {
      gestor.intentarImprimir(nombre, tipoImpresion);
    }
  }
}
