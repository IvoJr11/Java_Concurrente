package Ejercicio4;

public class test {
  public static void main(String[] args) {
    Area[] areas = new Area[5];
    generarAreas(areas);
    Parque parque = new Parque("Parque Nacional", areas);
    Thread[] visitantes = new Thread[20];
    generarVisitantes(visitantes, parque);
    iniciarVisitantes(visitantes);
  }

  public static void generarAreas(Area[] areas) {
    for(int i=0; i<areas.length; i++) {
      // int reservas = (int) (Math.random() * 10) + 1;
      int reservas = 5;
      areas[i] = new Area(reservas, "Area " + (i+1));
    }
  }

  public static void generarVisitantes(Thread[] visitantes, Parque parque) {
    for(int i=0; i<visitantes.length; i++) {
      visitantes[i] = new Thread(new Visitante("Visitante " + (i+1), parque), "Visitante " + (i+1));
    }
  }

  public static void iniciarVisitantes(Thread[] visitantes) {
    for(int i=0; i<visitantes.length; i++) {
      visitantes[i].start();
    }
  }
}
