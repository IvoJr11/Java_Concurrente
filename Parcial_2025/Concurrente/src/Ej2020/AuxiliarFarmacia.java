package Ej2020;

public class AuxiliarFarmacia implements Runnable {
  private FarmaciaManager farmaciaManager;

  public AuxiliarFarmacia(FarmaciaManager f) {
    this.farmaciaManager = farmaciaManager;
  }

  public void run() {
    while (true) {
      farmaciaManager.colocarFichaGeneral();
    }
  }
}
