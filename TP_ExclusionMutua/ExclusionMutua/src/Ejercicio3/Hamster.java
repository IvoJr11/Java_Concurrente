package Ejercicio3;

public class Hamster implements Runnable {
  private Jaula jaula = new Jaula();

  public void run() {
    int comida;
    do{
      comida = jaula.getComida();
      try {
        jaula.comer();
        jaula.usarRueda();
        jaula.usarHamaca();
      } catch (InterruptedException e) {
        System.out.println("Hilo interrumpido");
      }
    } while (comida>0);
  }
}
