package Ejercicio4;

public class VendedorTicket implements Runnable{
  private Garita garita;
  public VendedorTicket(Garita g) {
    this.garita = g;
  }
  public void run() {
    while(true) {
      try {
        garita.venderTicket();
      } catch (Exception e) {

      }
    }
  }
}
