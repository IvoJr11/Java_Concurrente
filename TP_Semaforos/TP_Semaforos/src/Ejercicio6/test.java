package Ejercicio6;

import java.util.concurrent.Semaphore;

public class test {
  public static void main(String[] args) {
    Semaphore semPasajero = new Semaphore(0);
    Semaphore semTaxista = new Semaphore(0);

    Taxista taxista = new Taxista(semTaxista, semPasajero);
    Pasajero pasajero = new Pasajero(semPasajero, semTaxista);

    Thread hiloTaxista = new Thread(taxista);
    Thread hiloPasajero = new Thread(pasajero);

    hiloTaxista.start();
    hiloPasajero.start();
  }
}