package Ej1;

import java.util.concurrent.ThreadLocalRandom;

public class BobDylan implements Runnable {
  private AlbumLock albumLock;
  
  public BobDylan(AlbumLock a) {
    albumLock = a;
  }

  public void run() {
    while(true) {
      int random = ThreadLocalRandom.current().nextInt(0, 4);
      if(random == 0) {
        albumLock.generarCancion('R');
      } else if (random == 1) {
        albumLock.generarCancion('F');
      } else if (random == 2) {
        albumLock.generarCancion('B');
      } else {
        albumLock.generarCancion('C');
      }
    }
  }
}
