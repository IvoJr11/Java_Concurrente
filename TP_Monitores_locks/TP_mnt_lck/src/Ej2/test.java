package Ej2;

public class test {
  public static void main(String[] args) {
    MonitorFerri monitor = new MonitorFerri();
    Thread ferry = new Thread(new Ferry(monitor));
    Thread[] threads = new Thread[20];
    int random;
    for(int i = 0; i<threads.length; i++) {
      random = (int) (Math.random() * 10);
      if(random == 1) {
        threads[i] = new Thread(new Automovil(monitor), "Auto(" + i + ")");
      } else {
        threads[i] = new Thread(new Pasajero(monitor), "Pasajero(" + i + ")");        
      }
    }
    for(Thread thread: threads) {
      thread.start();
    }
    ferry.start();
  }
}
