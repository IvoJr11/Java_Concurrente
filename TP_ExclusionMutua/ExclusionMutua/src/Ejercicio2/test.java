package Ejercicio2;

public class test {
  public static void main(String[] args) {
    Entidad entidad = new Entidad();
    Thread thread1 = new Thread(entidad, "Sanador");
    Thread thread2 = new Thread(entidad, "Criatura Oscura");
    thread1.start();
    thread2.start();
  }
}
