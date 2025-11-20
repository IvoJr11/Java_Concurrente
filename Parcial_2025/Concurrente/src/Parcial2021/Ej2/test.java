package Parcial2021.Ej2;

public class test {
  public static void main(String[] args) {
    Thread[] personasThreads = new Thread[20];
    SalaBaile salaBaile = new SalaBaile();
    Thread coordinador = new Thread(new Coordinador(salaBaile));
    coordinador.start();
    for(int i=0; i<personasThreads.length;i++) {
      personasThreads[i] = new Thread(new Persona(salaBaile));
      personasThreads[i].start();
    }
  }
}
