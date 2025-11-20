package Ej2020Copy;

public class AuxiliarFarmacia implements Runnable {
  private FarmaciaManager farmaciaManager;

  public AuxiliarFarmacia(FarmaciaManager f) {
    this.farmaciaManager = f;
  }

  public void run() {
    while (true) {
      try {
        farmaciaManager.colocarFichaGeneral();
        Thread.sleep(5000);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }
}
