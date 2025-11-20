package Ej2;

public class test {
  public static void main(String[] args) {
    Thread[] threads = new Thread[10];
    BufferManager bufferManager = new BufferManager();
    for(int i=0; i<threads.length; i++) {
      threads[i] = new Thread(new Hilo(bufferManager)); 
    }
    for(Thread thread: threads) {
      thread.start();
    }
  }
}
