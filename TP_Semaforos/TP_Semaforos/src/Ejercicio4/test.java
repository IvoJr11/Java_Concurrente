package Ejercicio4;

public class test {
  public static void main(String[] args) {
    Gestor gestor = new Gestor(3); // Crea un gestor con 3 impresoras

    // Crea varios clientes que intentarán imprimir
    Cliente cliente1 = new Cliente("Cliente1", gestor);
    Cliente cliente2 = new Cliente("Cliente2", gestor);
    Cliente cliente3 = new Cliente("Cliente3", gestor);
    Cliente cliente4 = new Cliente("Cliente4", gestor);

    // Inicia los hilos de los clientes
    cliente1.start();
    cliente2.start();
    cliente3.start();
    cliente4.start();
  }
}
