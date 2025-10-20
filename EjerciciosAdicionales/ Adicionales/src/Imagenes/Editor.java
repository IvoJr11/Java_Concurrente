package Imagenes;

import java.util.concurrent.Semaphore;

public class Editor implements Runnable {
  private Semaphore semEditor;
  private Semaphore semImagen;

  public Editor(Semaphore semEditor, Semaphore semImagen) {
    this.semEditor = semEditor;
    this.semImagen = semImagen;
  }

  public void run() {
    while(true) {
      try {
        System.out.println("Esperando imagen para editar...");
        semEditor.acquire();
        System.out.println("Editando imagen...");
        Thread.sleep(2000);
        semImagen.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  
}
