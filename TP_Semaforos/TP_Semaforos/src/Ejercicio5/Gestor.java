package Ejercicio5;

public class Gestor {
  private Impresora[] impresoras;
  private int cantA;
  private int cantB;
  public Gestor(int cantidadImpresoras, int cantA, int cantB) {
    impresoras = new Impresora[cantidadImpresoras];
    this.cantA = cantA;
    this.cantB = cantB;
  }

  public void intentarImprimir(String cliente, char tipoCliente) {
    boolean isImpreso = false;
    int i = 0;
    do {
      System.out.println(cliente + " intentando imprimir en impresora " + (i + 1));
      isImpreso = impresoras[i].imprimir(cliente, tipoCliente);
      i++;
    } while (!isImpreso && i < impresoras.length);
    try {
      Thread.sleep(1000); // Espera antes de intentar imprimir de nuevo
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public Impresora[] getImpresoras() {
    return impresoras;
  }

  public void cargarImpresoras() {
    int i = 0;
    for (i = 0; i < cantA; i++) {
      impresoras[i] = new Impresora(i + 1, 'A');
    }
    
    for (int j = i; j < cantB + cantA; j++) {
      impresoras[j] = new Impresora(j + 1, 'B');
    }
  }
}
