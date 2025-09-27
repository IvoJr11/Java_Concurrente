package Ejercicio3;

import java.util.concurrent.Semaphore;

public class Administrador {
  public static void main(String[] args) {
    Semaphore s1 = new Semaphore(1);
    Semaphore s2 = new Semaphore(0);
    Semaphore s3 = new Semaphore(0);
    Thread proceso1 = new Thread(new Proceso(s1, s3), "P1");
    Thread proceso2 = new Thread(new Proceso(s2, s1), "P2");
    Thread proceso3 = new Thread(new Proceso(s3, s2), "P3");
    proceso1.start();
    proceso2.start();
    proceso3.start();
  }
}
