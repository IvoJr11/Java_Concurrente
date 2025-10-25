package Ejercicio5;

import java.util.concurrent.ThreadLocalRandom;

public class Avion implements Runnable{
  TorreControl torre;

  public Avion(TorreControl t) {
    this.torre = t;
  }

  public void run() {
    try {
      int bit = ThreadLocalRandom.current().nextInt(0, 2);
      System.out.println("Aterrizar/despegar: " + bit);
      if(bit == 1) {
        torre.aterrizar();
      } else {
        torre.despegar();
      }
    } catch (Exception e) {

    }
  }
}
