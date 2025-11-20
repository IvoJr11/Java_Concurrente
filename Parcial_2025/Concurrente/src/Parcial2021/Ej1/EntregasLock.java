package Parcial2021.Ej1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EntregasLock {
  private Lock lockMesa = new ReentrantLock();
  private Lock lockPedido = new ReentrantLock();

  private Condition cocinerosCondicion;
  private Condition cocinerosMesaCondicion;
  private Condition repartidoresCondicion;
  
  private Queue<Object> pedidosPendientes = new LinkedList<>();
  private Queue<Object> pedidosPorEntregar = new LinkedList<>();
  private static final int MAX_PENDIENTES = 10;

  public EntregasLock() {
    cocinerosCondicion = lockPedido.newCondition();
    cocinerosMesaCondicion = lockMesa.newCondition();
    repartidoresCondicion = lockMesa.newCondition();
  }

  public void generarPedido() {
    lockPedido.lock();
    try {
      int random = ThreadLocalRandom.current().nextInt(0, 2);
      if(random == 0) {
        pedidosPendientes.add('V');
        System.out.println("Agregado pedido(V)");
      } else {
        pedidosPendientes.add('N');
        System.out.println("Agregado pedido(N)");
      }
      System.out.println("Pedidos pendientes: " + pedidosPendientes.size());
      cocinerosCondicion.signalAll();
    } finally {
      lockPedido.unlock();
    }
  }

  public void prepararPedido(char p) throws Exception {
    char pedidoTomado;
    lockPedido.lock();
    try {
      System.out.println("Intentando tomar pedido");
      while(p != (char) pedidosPendientes.peek()) {
        cocinerosCondicion.await();
      }
      pedidoTomado = (char) pedidosPendientes.peek();
      pedidosPendientes.remove();
      System.out.println("Pedido tomado, pedidos restantes: " + pedidosPendientes.size());
    } finally {
      lockPedido.unlock();
    }

    System.out.println("Cocinando pedido de pizza (" + pedidoTomado + ")");
    Thread.sleep(5000);

    try {
      lockMesa.lock();
      while (pedidosPorEntregar.size() == MAX_PENDIENTES) {
        cocinerosMesaCondicion.await();
      }
      pedidosPorEntregar.add(pedidoTomado);
      System.out.println("Pedido agregado a la mesa. Por entregar: " + pedidosPorEntregar.size());
      repartidoresCondicion.signalAll();
    } finally {
      lockMesa.unlock();
    }
  }

  public void entregarPedido() throws Exception {
    char pedidoAEntregar;
    lockMesa.lock();
    try {
      System.out.println("Intentando entregar pedido");
      while(pedidosPorEntregar.size() == 0) {
        repartidoresCondicion.await();
      }
      pedidoAEntregar = (char) pedidosPorEntregar.peek();
      pedidosPorEntregar.remove();
      System.out.println("Tomado pedido para entregar. Cantidad actual: " + pedidosPorEntregar.size());
      cocinerosMesaCondicion.signalAll();
    } finally {
      lockMesa.unlock();
    }

    System.out.println("Entregando pedido de pizza(" + pedidoAEntregar + ")");
    Thread.sleep(1000);
    System.out.println("Pizza entregada!");
  }
}
