package Ejercicio7;
import java.util.concurrent.Semaphore;
public class test {
  public static void main(String[] args) {
    int numEmpleados = 5;

    Semaphore semaforoMozo = new Semaphore(0);
    Semaphore semaforoSilla = new Semaphore(1);
    Semaphore semaforoAtendido = new Semaphore(0);

    Mozo mozo = new Mozo(semaforoMozo, semaforoAtendido);
    Thread hiloMozo = new Thread(mozo);
    hiloMozo.start();

    for (int i = 1; i <= numEmpleados; i++) {
      Empleado empleado = new Empleado("Empleado " + i, semaforoAtendido, semaforoMozo, semaforoSilla);
      Thread hiloEmpleado = new Thread(empleado);
      hiloEmpleado.start();
    }
  }
}
