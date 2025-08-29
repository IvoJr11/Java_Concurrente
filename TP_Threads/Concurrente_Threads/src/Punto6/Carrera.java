package Punto6;

public class Carrera {
  public static void main(String args[]) {
    Corredor[] arreglo = {
      new Corredor("Maca", 0),
      new Corredor("Tomi", 0),
      new Corredor("Santi", 0),
      new Corredor("Yo", 0),
      new Corredor("Coco", 0)
    };

    Thread[] threads = new Thread[arreglo.length];
    for(int i=0;i<arreglo.length;i++) {
      threads[i] = new Thread(arreglo[i]);
      threads[i].start();
    }
    
    for(int i=0;i<arreglo.length;i++) {
      try{
        threads[i].join();
      }catch(InterruptedException e) {
        System.out.println("El hilo principal fue interrumpido.");
      }
    }

    Corredor ganador = arreglo[0];
    for(Corredor c: arreglo) {
      if(c.distanciaRecorrida>ganador.distanciaRecorrida) {
        ganador = c;
      }
    }
    System.out.println("El ganador es: " + ganador.nombre + " con una distancia de: " + ganador.distanciaRecorrida);
  }
}
