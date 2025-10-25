package Ejercicio4;

public class test {
  public static void main(String[] args) {
    int n = 20;
    int m = 10;
    Thread[] threadList = new Thread[n];
    Garita garita = new Garita();
    Thread vendedorThread = new Thread(new VendedorTicket(garita));
    Tren tren = new Tren(m);
    Thread controlTren = new Thread(new ControlTren(tren));
    controlTren.start();
    for(int i=0;i<threadList.length;i++) {
      threadList[i] = new Thread(new Pasajero(garita, tren), "Pasajero " + "(" + (i+1) + ")");
    }
    vendedorThread.start();
    for(int i=0;i<threadList.length;i++) {
      threadList[i].start();
    }
  }
}
