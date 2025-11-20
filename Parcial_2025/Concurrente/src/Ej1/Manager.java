package Ej1;

public class Manager implements Runnable {
  private AlbumLock albumLock;

  public Manager(AlbumLock a) {
    albumLock = a;
  }
  
  public void run() {
    while(true) {
      try {
        albumLock.crearAlbum();
        albumLock.promocionarAlbum();
        Thread.sleep(5000);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }
}
