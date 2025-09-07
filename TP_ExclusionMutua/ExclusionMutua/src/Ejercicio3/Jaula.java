package Ejercicio3;

public class Jaula {
  private int comida = 20;
  private final Object comidaLock = new Object();
  private final Object rueda = new Object();
  private final Object hamaca = new Object();

  public int getComida() {
    return this.comida;
  }

  public void comer() throws InterruptedException {
    synchronized (comidaLock) {
      System.out.println(Thread.currentThread().getName() + " esta comiendo");
      if(comida>0) {
        this.comida -= 1;
      }
      Thread.sleep(500);
      System.out.println(Thread.currentThread().getName() + " termino de comer");
    }
  }

  public void usarRueda() throws InterruptedException {
    synchronized (rueda) {
      System.out.println(Thread.currentThread().getName() + " esta en la rueda");
      Thread.sleep(500);
      System.out.println(Thread.currentThread().getName() + " dejo la rueda");
    }
  }

  public void usarHamaca() throws InterruptedException {
    synchronized (hamaca) {
      System.out.println(Thread.currentThread().getName() + " esta en la hamaca");
      Thread.sleep(500);
      System.out.println(Thread.currentThread().getName() + " dejo la hamaca");
    }
  }
}
