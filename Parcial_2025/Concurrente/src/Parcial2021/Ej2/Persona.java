package Parcial2021.Ej2;

import java.util.concurrent.ThreadLocalRandom;

public class Persona implements Runnable {
  private SalaBaile salaBaile;
  
  public Persona(SalaBaile sala) {
    this.salaBaile = sala;
  }

  public void run() {
    int random = ThreadLocalRandom.current().nextInt(1,3);
    try {
      boolean sonPareja = ThreadLocalRandom.current().nextBoolean();
      salaBaile.ingresarFila(sonPareja, random);
      salaBaile.entrarBaile(random);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
