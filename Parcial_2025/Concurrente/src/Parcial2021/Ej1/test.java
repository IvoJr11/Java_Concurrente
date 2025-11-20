package Parcial2021.Ej1;

public class test {
  public static void main(String[] args) {
    Thread[] cocinerosThreads = new Thread[5];
    Thread[] repartidoresThreads = new Thread[2];
    EntregasLock entregasLock = new EntregasLock();
    Thread generador = new Thread(new GeneradorPedidos(entregasLock));
    generador.start();
    for(int i=0; i<cocinerosThreads.length; i++) {
      cocinerosThreads[i] = new Thread(new Pizzero(entregasLock));
      cocinerosThreads[i].start();
    }
    for(int i=0; i<repartidoresThreads.length; i++) {
      repartidoresThreads[i] = new Thread(new Repartidor(entregasLock));
      repartidoresThreads[i].start();
    }
  }
}
