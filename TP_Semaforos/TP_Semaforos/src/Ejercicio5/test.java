package Ejercicio5;

public class test {
  public static void main(String[] args) {
    Gestor gestor = new Gestor(3, 2, 1); // Crea un gestor con 3 impresoras
    gestor.cargarImpresoras(); // Carga las impresoras en el gestor
    // Crea varios clientes que intentarán imprimir
    Cliente cliente1 = new Cliente("Cliente1", gestor, 'A');
    Cliente cliente2 = new Cliente("Cliente2", gestor, 'B');
    Cliente cliente3 = new Cliente("Cliente3", gestor, 'A');
    Cliente cliente4 = new Cliente("Cliente4", gestor, 'B');
    Cliente cliente5 = new Cliente("Cliente5", gestor, 'C');

    // Inicia los hilos de los clientes
    cliente1.start();
    cliente2.start();
    cliente3.start();
    cliente4.start();
    cliente5.start();
  }
}
