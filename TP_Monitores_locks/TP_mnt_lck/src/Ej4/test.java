package Ej4;

public class test {
  public static void main(String[] args) {
    Thread[] threads = new Thread[50];
    GestionarTrafico gestionarTrafico = new GestionarTrafico();
    for(int i = 0; i<threads.length; i++) {
      threads[i] = new Thread(new Auto(gestionarTrafico), "Auto(" + i + ")");
    }
    for(Thread thread: threads) {
      thread.start();
    }
  }
}
