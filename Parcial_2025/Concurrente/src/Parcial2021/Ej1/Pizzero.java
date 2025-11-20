package Parcial2021.Ej1;

import java.util.concurrent.ThreadLocalRandom;

public class Pizzero implements Runnable{
  private EntregasLock entregasLock;

  public Pizzero(EntregasLock e) {
    this.entregasLock = e;
  }
  
  public void run() {
    int random = ThreadLocalRandom.current().nextInt(0,2);
    char tipoPizza;
    if(random == 0) {
      tipoPizza = 'N';
    } else {
      tipoPizza = 'V';
    }
    try {
      while (true) {
        entregasLock.prepararPedido(tipoPizza);
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
