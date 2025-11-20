package Parcial2021.Ej2Tema2;

import java.util.concurrent.ThreadLocalRandom;

public class Persona implements Runnable {
  private Salon salon;

  public Persona(Salon s) {
    this.salon = s;
  }
  
  public void run() {
    int random;
    try { 
      random = ThreadLocalRandom.current().nextInt(1,4);
      salon.ingresarActividad(random);
      salon.iniciarSegundaActividad((random%3)+1);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
