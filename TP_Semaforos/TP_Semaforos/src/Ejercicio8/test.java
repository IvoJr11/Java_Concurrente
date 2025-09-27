package Ejercicio8;
import java.util.concurrent.Semaphore;

public class test {
  public static void main(String[] args) {
    Semaphore lineaElectrica = new Semaphore(0);
    Semaphore lineaMecanica = new Semaphore(0);
    ControladorProduccion controlador = new ControladorProduccion(lineaElectrica, lineaMecanica);
    Producto producto1 = new Producto(lineaElectrica, lineaMecanica, 'E', controlador);
    Producto producto2 = new Producto(lineaElectrica, lineaMecanica, 'M', controlador);
    Producto producto3 = new Producto(lineaElectrica, lineaMecanica, 'E', controlador);
    Producto producto4 = new Producto(lineaElectrica, lineaMecanica, 'M', controlador);
    Producto producto5 = new Producto(lineaElectrica, lineaMecanica, 'E', controlador);
    Producto producto6 = new Producto(lineaElectrica, lineaMecanica, 'M', controlador);
    Producto producto7 = new Producto(lineaElectrica, lineaMecanica, 'M', controlador);
    Thread productoThread1 = new Thread(producto1);
    Thread productoThread2 = new Thread(producto2);
    Thread productoThread3 = new Thread(producto3);
    Thread productoThread4 = new Thread(producto4);
    Thread productoThread5 = new Thread(producto5);
    Thread productoThread6 = new Thread(producto6);
    Thread productoThread7 = new Thread(producto7);
    Thread controladorThread = new Thread(controlador);
    productoThread1.start();
    productoThread2.start();
    productoThread3.start();
    productoThread4.start();
    productoThread5.start();
    productoThread6.start();
    productoThread7.start();
    controladorThread.start();
  }
}

