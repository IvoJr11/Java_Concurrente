package Ejercicio6;

public class Sumador implements Runnable{
  private int[] arreglo;
  private int inicio;
  private int fin;
  private Resultado resultado;

  public Sumador(int[] arreglo, int inicio, int fin, Resultado resultado) {
    this.arreglo = arreglo;
    this.inicio = inicio;
    this.fin = fin;
    this.resultado = resultado;
  }

  public void run() {
    sumar();
  }

  public void sumar() {
    int resultado = 0;
    for(int i=inicio; i<fin; i++) {
      resultado += arreglo[i];
    }
    this.resultado.agregar(resultado);
  }
}
