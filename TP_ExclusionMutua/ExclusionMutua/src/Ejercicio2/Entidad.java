package Ejercicio2;

public class Entidad implements Runnable {
  Energia energia = new Energia();

  public void run() {
    int i = 0;
    while(i<10) {
      if(Thread.currentThread().getName().equals("Sanador")) {
        energia.sanarEnergia();
        System.out.println("El Sanador ha curado energia");
      } else {
        energia.drenarEnergia();
        System.out.println("La criatura ha drenado energia");
      }
      System.out.println("Energia actual: " + energia.getEnergia());
      i++;      
    }
  }
  
}
