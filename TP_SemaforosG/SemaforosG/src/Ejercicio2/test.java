package Ejercicio2;

public class test {
  public static void main(String[] args) {
    GestorComedor comedor = new GestorComedor();
    Thread mozoThread = new Thread(new Mozo(comedor), "Mozo");
    Thread cocineroThread = new Thread(new Cocinero(comedor), "Cocinero");
    Thread[] empleados = new Thread[10];
    
    for (int i = 0; i < 10; i++) {
      empleados[i] = new Thread(new Empleado(comedor), "" + i);
    }
  
    mozoThread.start();
    cocineroThread.start();
    
    for (int i = 0; i < 10; i++) {
      empleados[i].start();
    }
  }
}
