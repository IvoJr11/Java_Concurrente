package Punto5;

public class UsoHilos {
  public static void main(String[] args) {
    System.out.println("Hilo principal iniciando.");
    // Primero, construye un objeto unHilo.
    MiHilo mh = new MiHilo("#1");
    MiHilo mh2 = new MiHilo("#2");
    MiHilo mh3 = new MiHilo("#3");
    // Luego, construye un hilo de ese objeto.
    mh.start();
    mh2.start();
    mh3.start();
    for (int i = 0; i < 50; i++) {
      System.out.print(" .");
    }
    try {
      Thread.sleep(100);
    } catch (InterruptedException exc) {
      System.out.println("Hilo principal interrumpido.");
    }
    System.out.println("Hilo principal finalizado.");
  }
}
