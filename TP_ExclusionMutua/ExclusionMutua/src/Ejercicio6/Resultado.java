package Ejercicio6;

public class Resultado {
  private int valorFinal;
  public synchronized void agregar(int valor) {
    this.valorFinal += valor;
    System.out.println("Valor parcial: " + valor + " - Valor final: " + this.valorFinal);
  }

  public int getValorFinal() {
    return this.valorFinal;
  }
}
