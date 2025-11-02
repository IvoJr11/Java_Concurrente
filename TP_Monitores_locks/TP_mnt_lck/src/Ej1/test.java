package Ej1;

public class test {
  public static void main(String[] args) {
    MonitorSala sala = new MonitorSala(5);
    Thread[] threads = new Thread[10];
    for(int i = 0;i<threads.length; i++) {
      threads[i] = new Thread(new Estudiante(sala), "Estudiante" + "(" + (i+1) + ")");
    }
    for(Thread e: threads) {
      e.start();
    }
  }
}
