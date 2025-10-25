package Ejercicio6;

public class test {
  public static void main(String[] args) {
    int n = 30;
    int m = 10;
    Mirador mirador = new Mirador(m);
    Thread[] threadList = new Thread[n];
    Thread encargado = new Thread(new Encargado(mirador));
    for(int i = 0; i<threadList.length; i++) {
      threadList[i] = new Thread(new Visitante(mirador), "Visitante" + "(" + (i+1) + ")");
    }
    encargado.start();
    for(Thread visitante: threadList) {
      visitante.start();
    }
  }
}
