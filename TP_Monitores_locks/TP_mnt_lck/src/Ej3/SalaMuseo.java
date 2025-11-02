package Ej3;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SalaMuseo {
  private Lock mutex = new ReentrantLock(true);
  private Condition visitantes;
  private Condition investigadores;
  private Condition conserjes;
  private int capacidadSala = 50;
  private int cantVisitantes = 0;
  private int cantInvestigadores = 0;
  private int cantConserjes = 0;
  private int cantDiscapacitados = 0;

  public SalaMuseo() {
    visitantes = mutex.newCondition();
    investigadores = mutex.newCondition();
    conserjes = mutex.newCondition();
  }
  public void ingresaVisitante(boolean esDiscapacitado) throws Exception {
    try {
      mutex.lock();
      System.out.println("---- " + Thread.currentThread().getName() + " intentando ingresar ----");
      while ((cantVisitantes >= capacidadSala) || (cantInvestigadores>0 || cantConserjes>0)) {
        System.out.println("No hay espacio para visitantes");
        visitantes.await();
      }
      if(esDiscapacitado) {
        cantDiscapacitados++;
        capacidadSala = 30;
      }
      cantVisitantes++;
      System.out.println("---- " + Thread.currentThread().getName() + " pudo ingresar ----");
    } finally {
      mutex.unlock();
    }
  }

  public void estudiar(boolean esDiscapacitado) throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + " estudiando las estrellas...");
    int random = ThreadLocalRandom.current().nextInt(1, 10000);
    Thread.sleep(random);
    System.out.println(Thread.currentThread().getName() + " sale de la sala");
    try {
      mutex.lock();
      if(esDiscapacitado) {
        cantDiscapacitados--;
        if(cantDiscapacitados == 0) {
          capacidadSala = 50;
        }
      } else {
        cantVisitantes--;
      }
      if(cantVisitantes == 0) {
        conserjes.signalAll();
        investigadores.signal();
      } else {
        visitantes.signalAll();
      }
    } finally {
      mutex.unlock();
    }
  }

  public void ingresaConserje() throws Exception {
    try {
      mutex.lock();
      System.out.println("---- " + Thread.currentThread().getName() + " intentando ingresar ----");
      while ((cantConserjes>=capacidadSala) || cantInvestigadores>0 || cantVisitantes>0) {
        System.out.println("No hay espacio para conserjes");
        conserjes.await();
      }
      cantConserjes++;
      System.out.println("---- " + Thread.currentThread().getName() + " pudo ingresar ----");
    } finally {
      mutex.unlock();
    }
  }

  public void limpiar() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + "limpiando...");
    int random = ThreadLocalRandom.current().nextInt(1, 10000);
    Thread.sleep(random);
    System.out.println(Thread.currentThread().getName() + " sale de la sala");
    try {
      mutex.lock();
      cantConserjes--;
      if(cantConserjes==0) {
        visitantes.signalAll();
        investigadores.signal();
      } else {
        conserjes.signalAll();
      }
    } finally {
      mutex.unlock();
    }
  }

  public void ingresaInvestigador() throws Exception{
    try {
      mutex.lock();
      System.out.println("---- " + Thread.currentThread().getName() + " intentando ingresar ----");
      while (cantInvestigadores == 1 || cantConserjes>0 || cantVisitantes>0) {
        System.out.println("Ya hay un investigador");
        investigadores.await();
      }
      cantInvestigadores++;
      System.out.println("---- " + Thread.currentThread().getName() + " pudo ingresar ----");
    } finally {
      mutex.unlock();
    }
  }

  public void investigar() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + "investigando...");
    int random = ThreadLocalRandom.current().nextInt(1, 10000);
    Thread.sleep(random);
    System.out.println(Thread.currentThread().getName() + " sale de la sala");
    try {
      mutex.lock();
      cantInvestigadores--;
      visitantes.signalAll();
      conserjes.signalAll();
      investigadores.signal();
    } finally {
      mutex.unlock();
    }
  }
}