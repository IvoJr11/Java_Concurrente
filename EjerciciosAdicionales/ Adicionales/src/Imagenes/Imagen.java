package Imagenes;

import java.util.concurrent.Semaphore;

public class Imagen implements Runnable {
  private boolean estaEditada;
  private Semaphore semImagen;
  private Semaphore semEditor;

  public Imagen(Semaphore semEditor, Semaphore semImagen) {
    this.estaEditada = false;
    this.semEditor = semEditor;
    this.semImagen = semImagen;
  }

  public void run() {
    System.out.println("Imagen creada, esperando a ser editada...");
    while(!estaEditada) {
      try {
        semEditor.release();
        System.out.println("Esperando a que la imagen sea editada...");
        semImagen.acquire();
        Thread.sleep(3000);
        this.estaEditada = true;
        System.out.println(Thread.currentThread().getName() + " fue editada.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
