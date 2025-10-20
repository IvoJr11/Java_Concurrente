package Toboganes;

public class test {
  public static void main(String[] args) {
    int capacidad = 5;
    Mirador mirador = new Mirador(capacidad);
    Thread[] personas = new Thread[capacidad];
    for (int i = 0; i < capacidad; i++) {
      personas[i] = new Thread(new Persona("Persona " + (i + 1), mirador));
    }
    for (int i = 0; i < capacidad; i++) {
      personas[i].start();
    }
  }
}