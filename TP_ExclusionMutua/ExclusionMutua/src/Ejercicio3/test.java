package Ejercicio3;

public class test {
  public static void main(String[] args) {
    Hamster hamster = new Hamster();
    Thread hamster1 = new Thread(hamster, "hamster1");
    Thread hamster2 = new Thread(hamster, "hamster2");
    Thread hamster3 = new Thread(hamster, "hamster3");
  
    hamster1.start();
    hamster2.start();
    hamster3.start();
  }
}
