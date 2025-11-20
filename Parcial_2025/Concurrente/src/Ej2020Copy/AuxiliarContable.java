package Ej2020Copy;

import java.util.concurrent.ThreadLocalRandom;

public class AuxiliarContable implements Runnable {
  private FarmaciaManager farmaciaManager;

  public AuxiliarContable(FarmaciaManager f) {
    this.farmaciaManager = f;
  }
  
  public void run() {
    boolean random;
    try {
      while (true) {
        random = ThreadLocalRandom.current().nextBoolean();
        if(random) {
          farmaciaManager.tomarFichasContable('G');
        } else {
          farmaciaManager.tomarFichasContable('C');
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}