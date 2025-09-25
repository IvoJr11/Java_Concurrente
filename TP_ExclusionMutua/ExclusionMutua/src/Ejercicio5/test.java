package Ejercicio5;

public class test {
  public static void main(String[] args) {
    Surtidor[] surtidores = new Surtidor[3];
    Thread[] autos = new Thread[10];
    cargarSurtidores(surtidores);
    cargarAutos(autos, surtidores);
    iniciarAutos(autos);
  }

  public static void cargarSurtidores(Surtidor[] surtidores) {
    for (int i = 0; i < surtidores.length; i++) {
      surtidores[i] = new Surtidor(10000, i+1);
    }
  }

  public static void cargarAutos(Thread[] autos, Surtidor[] surtidores) {
    for (int i = 0; i < autos.length; i++) {
      String patente = "AAA" + (i+1) + (i+1) + (i+1);
      autos[i] = new Thread(new Auto(patente, "Modelo" + (i+1), "Marca" + (i+1), 0, surtidores), "Auto" + (i+1));
    }
  }

  public static void iniciarAutos(Thread[] autos) {
    for (int i = 0; i < autos.length; i++) {
      autos[i].start();
    }
  }
}
