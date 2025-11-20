package Parcial2021.Ej2Tema2;

public class test {
  public static void main(String[] args) {
    Thread[] threads = new Thread[40];
    Salon salon = new Salon();

    for(int i=0;i<threads.length;i++) {
      threads[i] = new Thread(new Persona(salon), "Persona(" + (i+1) + ")");
      threads[i].start();
    }
  }
}
