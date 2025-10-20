package Ejercicio1;

public class test {
  public static void main(String[] args) {
    GestorPiscina gestor = new GestorPiscina(3); // Capacidad máxima de la piscina
    Thread[] personas = new Thread[10];

    for (int i = 0; i < 10; i++) {
      Thread persona = new Thread(new Persona(gestor), i + "");
      personas[i] = persona;
    }

    for(Thread persona : personas) {
      persona.start();
    }
  }
}
