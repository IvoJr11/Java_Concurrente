package Punto6;

public class Corredor implements Runnable  {
  public String nombre;
  public int distanciaRecorrida;

  public Corredor(String n, int d) {
    this.nombre = n;
    this.distanciaRecorrida = d;
  }

  public void recorrer() {
    int pasos = (int) (Math.random()*10);
    this.distanciaRecorrida += pasos;
    System.out.println("Hilo " + nombre + " recorre " + pasos);
  }

  public void run() {
    System.out.println("Inicio de hilo: " + nombre);
    while(distanciaRecorrida < 100) {
      recorrer();
      try{
        Thread.sleep(400);
      }catch(InterruptedException e){
        System.out.println("Error en la ejecución del hilo");
      }
    }
    System.out.println("Hilo " + nombre + " finalizado");
  }
}
