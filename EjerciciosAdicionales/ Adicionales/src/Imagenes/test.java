package Imagenes;

import java.util.concurrent.Semaphore;

public class test {
  public static void main(String[] args) {
    Semaphore semEditor = new Semaphore(0);
    Semaphore semImagen = new Semaphore(1);
    Editor editor = new Editor(semEditor, semImagen);

    Thread hiloImagen1 = new Thread(new Imagen(semEditor, semImagen), "imagen1");
    Thread hiloImagen2 = new Thread(new Imagen(semEditor, semImagen), "imagen2");
    Thread hiloImagen3 = new Thread(new Imagen(semEditor, semImagen), "imagen3");
    Thread hiloImagen4 = new Thread(new Imagen(semEditor, semImagen), "imagen4");
    Thread hiloImagen5 = new Thread(new Imagen(semEditor, semImagen), "imagen5");
    Thread hiloEditor = new Thread(editor);

    hiloEditor.start();
    hiloImagen1.start();
    hiloImagen2.start();
    hiloImagen3.start();
    hiloImagen4.start();
    hiloImagen5.start();
  }
}
