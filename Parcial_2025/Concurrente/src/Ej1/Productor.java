package Ej1;

import java.util.concurrent.ThreadLocalRandom;

public class Productor implements Runnable {
  private AlbumLock albumLock;

  public Productor(AlbumLock a) {
    albumLock = a;
  }
  
  public void run() {
    int random = ThreadLocalRandom.current().nextInt(0,4);
    char genero;
    if(random == 0) {
      genero = 'R';
    } else if (random == 1) {
      genero = 'F';
    } else if (random == 2) {
      genero = 'B';
    } else {
      genero = 'C';
    }
    while (true) {
      try {
        albumLock.agregarCancion(genero);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }
}