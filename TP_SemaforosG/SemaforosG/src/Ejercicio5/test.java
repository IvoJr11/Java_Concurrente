package Ejercicio5;

public class test {
  public static void main(String[] args) {
    Thread[] threadList = new Thread[30];
    TorreControl torre = new TorreControl();
    for(int i=0;i<threadList.length;i++) {
      threadList[i] = new Thread(new Avion(torre), "Avion " + "(" + (i+1) + ")");
    }
    for(Thread avion : threadList) {
      avion.start();
    }
  }
}
