package Ejercicio4;

public class Pasajero implements Runnable {
  private Garita garita;
  private Tren tren;
  public Pasajero(Garita g, Tren t) {
    this.garita = g;
    this.tren = t;
  }
  public void run() {
    try {
      garita.comprarTicket();
      System.out.println("Va a intentar subir al tren");
      tren.subirTren();
      tren.bajarTren();
    } catch (Exception e) {

    }
  }
}
