package Ej2;

import java.util.concurrent.ThreadLocalRandom;

public class Hilo implements Runnable{
  private BufferManager buffer;

  public Hilo(BufferManager b) {
    this.buffer = b;
  }
  
  public void run() {
    int random = ThreadLocalRandom.current().nextInt(0, 2);
    while (true) {
      try {
        if(random == 0) {
          buffer.insertarElemento();
        } else {
          buffer.extraerElemento();
        }
        Thread.sleep(1000);
      } catch(Exception e) {

      }
    }
  }
}
