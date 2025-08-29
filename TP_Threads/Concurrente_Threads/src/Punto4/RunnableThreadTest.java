package Punto4;

public class RunnableThreadTest {
  public static void main(String args[]) {
    RunnableThreadEjemplo runnable1 = new RunnableThreadEjemplo();
    RunnableThreadEjemplo runnable2 = new RunnableThreadEjemplo();
    
    Thread thread1 = new Thread(runnable1, "Maria Jose");
    Thread thread2 = new Thread(runnable2, "Jose Maria");

    thread1.start();
    thread2.start();

    System.out.println("Termina el main");
  }
}
