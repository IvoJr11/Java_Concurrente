package Ejercicio4;

public class Gestor {
  Impresora[] impresoras;
  public Gestor(int cantidadImpresoras) {
    impresoras = new Impresora[cantidadImpresoras];
    for (int i = 0; i < cantidadImpresoras; i++) {
      impresoras[i] = new Impresora(i);
    }
  }

  public void intentarImprimir(String cliente) {
    boolean isImpreso = false;
    int i = 0;
    do {
      System.out.println(cliente + " intentando imprimir en impresora " + i);
      isImpreso = impresoras[i].imprimir(cliente);
      i++;
    } while (!isImpreso && i < impresoras.length);
  }

  public Impresora[] getImpresoras() {
    return impresoras;
  }
}
