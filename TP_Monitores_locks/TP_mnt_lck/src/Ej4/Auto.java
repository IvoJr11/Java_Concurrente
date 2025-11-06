package Ej4;

import java.util.concurrent.ThreadLocalRandom;

public class Auto implements Runnable {
  private GestionarTrafico gestion;
  
  public Auto(GestionarTrafico g) {
    this.gestion = g;
  }

  public void run() {
    int random = ThreadLocalRandom.current().nextInt(0,2);
    try {
      if(random == 0) {
        gestion.EntrarCocheDelNorte();
        gestion.cruzarPuente();
        gestion.SalirCocheDelNorte();
      } else {
        gestion.EntrarCocheDelSur();
        gestion.cruzarPuente();
        gestion.SalirCocheDelSur();
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
