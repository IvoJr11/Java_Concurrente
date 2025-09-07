package Ejercicio2;

public class Energia {
  private int energia = 10;
  public Energia(){};
  
  public synchronized int getEnergia() {
    return this.energia;
  }
  public synchronized void drenarEnergia() {
    this.energia -= 3;
  }
  public synchronized void sanarEnergia() {
    this.energia += 3;
  }
}
