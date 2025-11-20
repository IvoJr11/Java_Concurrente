package Ej1;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlbumLock {
  private Lock lockMesa = new ReentrantLock();
  private Lock lockAlbum = new ReentrantLock();
  private Condition managerCola;
  private Condition productorCola;

  public AlbumLock() {
    managerCola = lockAlbum.newCondition();
    productorCola = lockAlbum.newCondition();
  }

  private int cantRock = 0;
  private int cantFolk = 0;
  private int cantBlues = 0;
  private int cantCountry = 0;

  private static final double duracionR = 2.5;
  private static final double duracionF = 1.0;
  private static final double duracionB = 2.0;
  private static final double duracionC = 3.0;

  private String nombreAlbum = "";
  private double duracionAlbum = 0;
  private boolean enPromocion = false;
  private boolean promocionEnCurso = false;

  public void generarCancion(char genero) {
    try {
      lockMesa.lock();
      if(genero == 'R') {
        cantRock++;
      } else if(genero == 'F') {
        cantFolk++;
      } else if(genero == 'C') {
        cantCountry++;
      } else if(genero == 'B') {
        cantBlues++;
      }
    } finally {
      lockMesa.unlock();
    }
  }

  public void crearAlbum() throws Exception {
    try {
      lockAlbum.lock();
      while(!nombreAlbum.equals("")) {
        managerCola.await();
      }
      nombreAlbum = "Album1";
      duracionAlbum = ThreadLocalRandom.current().nextDouble(9.0, 15.0);
      productorCola.signalAll();
    } finally {
      lockAlbum.unlock();
    }
  }

  public void promocionarAlbum() throws Exception {
    try {
      lockAlbum.lock();
      while(!enPromocion || promocionEnCurso) {
        managerCola.await();
      }
      promocionEnCurso = true;
    } finally {
      lockAlbum.unlock();
    }
    System.out.println("Promocionando Álbum...");
    Thread.sleep(5000);
    try {
      lockAlbum.lock();
      System.out.println("Album promocionado!");
      enPromocion = false;
      promocionEnCurso = false;
      nombreAlbum = "";
      productorCola.signalAll();
    } finally {
      lockAlbum.unlock();
    }
  }

  public void agregarCancion(char genero) throws Exception {
    try {
      lockAlbum.lock();
      while(nombreAlbum.equals("") || enPromocion) {
        productorCola.await();
      }
    } finally {
      lockAlbum.unlock();
    }
    boolean obtuvoCancion = false;
    try {
      lockMesa.lock();
      if(genero == 'R') {
        if(cantRock!=0) {
          cantRock--;
          obtuvoCancion = true;
        }
      } else if(genero == 'F') {
        if(cantFolk!=0) {
          cantFolk--;
          obtuvoCancion = true;
        }
      } else if(genero == 'B') {
        if(cantBlues!=0) {
          cantBlues--;
          obtuvoCancion = true;
        }
      } else {
        if(cantCountry!=0) {
          cantCountry--;
          obtuvoCancion = true;
        }
      }
    } finally {
      lockMesa.unlock();
    }
      
    try {
      lockAlbum.lock();
      if(obtuvoCancion) {
        if(genero == 'R') {
          double resta = duracionAlbum - duracionR;
          if(resta>0) {
            duracionAlbum = resta;
          } else if(resta == 0) {
            duracionAlbum = resta;
            enPromocion = true;
            managerCola.signal();
          } else {
            cantRock++;
            enPromocion = true;
            managerCola.signal();
          }
        } else if (genero == 'F') {
          double resta = duracionAlbum - duracionF;
          if(resta>0) {
            duracionAlbum = resta;
          } else if(resta == 0) {
            duracionAlbum = resta;
            enPromocion = true;
            managerCola.signal();
          } else {
            cantFolk++;
            enPromocion = true;
            managerCola.signal();
          }
        } else if (genero == 'B') {
          double resta = duracionAlbum - duracionB;
          if(resta>0) {
            duracionAlbum = resta;
          } else if(resta == 0) {
            duracionAlbum = resta;
            enPromocion = true;
            managerCola.signal();
          } else {
            cantBlues++;
            enPromocion = true;
            managerCola.signal();
          }
        } else {
          double resta = duracionAlbum - duracionC;
          if(resta>0) {
            duracionAlbum = resta;
          } else if(resta == 0) {
            duracionAlbum = resta;
            enPromocion = true;
            managerCola.signal();
          } else {
            cantCountry++;
            enPromocion = true;
            managerCola.signal();
          }
        }
        System.out.println("Duracion actual del album: " + duracionAlbum);
      }
    } finally {
      lockAlbum.unlock();
    }
  }
}
