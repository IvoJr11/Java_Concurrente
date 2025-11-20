package Ej2020;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FarmaciaManager {
  private Lock lockFichasGen = new ReentrantLock(true);
  private Lock lockFichasCon = new ReentrantLock(true);
  private Lock lockFichasEnc = new ReentrantLock(true);
  
  private Condition contableFichasGen;
  private Condition contableFichasCon;
  private Condition encargadoFichasGen;
  private Condition encargadoFichasEnc;

  private Queue<Receta> fichasGen = new LinkedList<>();
  private Queue<Receta> fichasCon = new LinkedList<>();
  private Queue<Receta> fichasEnc = new LinkedList<>();

  private int importeGlobal = 0;
  private Lock lockImporte = new ReentrantLock(true);

  public FarmaciaManager() {
    contableFichasGen = lockFichasGen.newCondition();
    contableFichasCon = lockFichasCon.newCondition();
    encargadoFichasGen = lockFichasGen.newCondition();
    encargadoFichasEnc = lockFichasEnc.newCondition();
  }

  public void colocarFichaGeneral() {
    lockFichasGen.lock();
    try {
      int numRandom = ThreadLocalRandom.current().nextInt(0, 1000);
      int numMedRandom = ThreadLocalRandom.current().nextInt(0, 1000);
      int impRandom = ThreadLocalRandom.current().nextInt(0, 1000);
      fichasGen.add(new Receta(numRandom, numMedRandom, impRandom));
      contableFichasGen.signalAll();
      encargadoFichasGen.signalAll();
    } finally {
      lockFichasGen.unlock();
    }
  }

  public void tomarFichasContable(char tipo) throws Exception {
    Condition colaCondition;
    Lock lockLocal;

    if(tipo == 'G') {
      lockLocal = lockFichasGen;
      colaCondition = contableFichasGen; 
    } else {
      lockLocal = lockFichasCon;
      colaCondition = contableFichasCon;
    }
    
    lockLocal.lock();
    Receta receta;
    try {
      if(tipo == 'G') {
        while (fichasGen.isEmpty()) {
          colaCondition.await();
        }
        receta = fichasGen.peek();
        fichasGen.remove();
      } else {
        while (fichasCon.isEmpty()) {
          colaCondition.await();
        }
        receta = fichasCon.peek();
        fichasCon.remove();
      }
    } finally {
      lockLocal.unlock();
    }

    System.out.println("Sumando total del medico num: " + receta.getNumMedico());
    Thread.sleep(500);

    if(tipo == 'G') {
      lockFichasEnc.lock();
      try {
        fichasEnc.add(receta);
        encargadoFichasEnc.signalAll();
      } finally {
        lockFichasCon.unlock();
      }
    }
  }
  
  public void tomarFichasEncargado(char tipo) throws Exception {
    Lock lockLocal;
    Condition colaCondicion;
    
    if(tipo == 'G') {
      lockLocal = lockFichasGen;
      colaCondicion = encargadoFichasGen;
    } else {
      lockLocal = lockFichasEnc;
      colaCondicion = encargadoFichasEnc;
    }

    Receta receta;
    lockLocal.lock();
    try {
      if(tipo == 'G') {
        while (fichasGen.isEmpty()) {
          colaCondicion.await();
        }
        receta = fichasGen.peek();
        fichasGen.remove();
      } else {
        while (fichasCon.isEmpty()) {
          colaCondicion.await();
        }
        receta = fichasCon.peek();
        fichasCon.remove();
      }
    } finally {
      lockLocal.unlock();
    }

    System.out.println("Sumando al importe global...");
    lockImporte.lock();
    try {
      importeGlobal += receta.getImporte();
    } finally {
      lockImporte.unlock();
    }
    Thread.sleep(1000);
    System.out.println("Importe global actual: " + importeGlobal);

    if(tipo == 'G') {
      lockFichasCon.lock();
      try {
        fichasCon.add(receta);
        contableFichasCon.signalAll();
      } finally {
        lockFichasCon.unlock();
      }
    }
  }
}