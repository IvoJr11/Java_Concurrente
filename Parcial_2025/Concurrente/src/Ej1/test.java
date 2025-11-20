package Ej1;

public class test {
  public static void main(String[] args) {
    AlbumLock albumLock = new AlbumLock();
    Thread[] productores = new Thread[10];
    Thread bobDylan = new Thread(new BobDylan(albumLock));
    Thread manager = new Thread(new Manager(albumLock));

    for(int i=0;i<productores.length;i++){
      productores[i] = new Thread(new Productor(albumLock));
    }

    bobDylan.start();
    manager.start();
    for(Thread productor: productores) {
      productor.start();
    }
  }
}
