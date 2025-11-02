package Ej3;

import java.util.concurrent.ThreadLocalRandom;

public class test {
  public static void main(String[] args) {
    SalaMuseo salaMuseo = new SalaMuseo();
    Thread[] threads = new Thread[100];
    generarThreads(threads, salaMuseo);
    for (Thread thread : threads) {
      thread.start();
    }
  }

  public static void generarThreads(Thread[] t, SalaMuseo salaMuseo) {
    int random;
    for(int i = 0;i<t.length;i++) {
      random = ThreadLocalRandom.current().nextInt(1, 4);
      switch(random) {
        case 1:
          if(i!=50) {
            t[i] = new Thread(new Visitante(salaMuseo, false), "Visitante(" + i + ")");
          } else {
            t[i] = new Thread(new Visitante(salaMuseo, false), "Visitante(" + i + ")");
          }
          break;
        case 2:
          t[i] = new Thread(new PersonalMantenimiento(salaMuseo), "Conserje(" + i + ")");
          break;
        case 3:
          t[i] = new Thread(new Investigador(salaMuseo), "Investigador(" + i + ")");    
          break;
      }
    }
  }
}
