package Ej2020Copy;

public class test {
  public static void main(String[] args) {
    FarmaciaManager farmacia = new FarmaciaManager();
    Thread[] encargados = new Thread[3];
    Thread[] auxiliaresF = new Thread[10];
    Thread[] auxiliaresC = new Thread[10];
    for(int i = 0; i<encargados.length; i++) {
      encargados[i] = new Thread(new Encargado(farmacia));
      encargados[i].start();
    }
    for(int i = 0; i<auxiliaresF.length; i++) {
      auxiliaresF[i] = new Thread(new AuxiliarFarmacia(farmacia));
      auxiliaresF[i].start();
    }
    for(int i = 0; i<auxiliaresC.length; i++) {
      auxiliaresC[i] = new Thread(new AuxiliarContable(farmacia));
      auxiliaresC[i].start();
    }
  }
}