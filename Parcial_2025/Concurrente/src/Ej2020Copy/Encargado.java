package Ej2020Copy;

import java.util.concurrent.ThreadLocalRandom;

public class Encargado implements Runnable {
  private FarmaciaManager farmaciaManager;

  public Encargado(FarmaciaManager f) {
    this.farmaciaManager = f;
  }
  
  public void run() {
    boolean random;
    try {
      while (true) {
        random = ThreadLocalRandom.current().nextBoolean();
        if(random) {
          farmaciaManager.tomarFichasEncargado('G');
        } else {
          farmaciaManager.tomarFichasEncargado('E');
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}