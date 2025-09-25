package Ejercicio5;

public class Auto extends Vehiculo implements Runnable {
  private static final int CAPACIDAD_MAXIMA = 2000;
  private int tanque;
  private Surtidor[] surtidores;

  public Auto(String patente, String modelo, String marca, int km, Surtidor[] surtidores) {
    super(patente, modelo, marca, km);
    this.tanque = CAPACIDAD_MAXIMA;
    this.surtidores = surtidores;
  }

  public void run(){
    boolean exito = false;
    while(hayCombustibleEnSurtidores()) {
      if (this.tanque > 0) {
        this.avanzar();
        try {
          Thread.sleep(250);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        exito = recargar();
        if(!exito) {
          try {
            Thread.sleep(250);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  public void avanzar() {
    super.avanzar();
    if(this.tanque < 10) {
      this.tanque = 0;
    } else {
      this.tanque -= 100;
    }
    System.out.println("Tanque de " + this.getPatente() + ": " + this.tanque + " - km recorridos: " + this.getKm());
  }

  public boolean recargar() {
    int i = 0;
    boolean exito = false;
    do {
      exito = this.surtidores[i].cargarCombustible(this);
      i++;
    } while (!exito && i < this.surtidores.length);
    return exito;
  }

  private boolean hayCombustibleEnSurtidores() {
    boolean hay = false;
    for (int i = 0; i < this.surtidores.length && !hay; i++) {
      if (this.surtidores[i].getCombustibleRestante() > 0) {
        hay = true;
      }
    }
    return hay;
  }

  public int getTanque() {
    return tanque;
  }
  public void setTanque(int tanque) {
    this.tanque = tanque;
  }
}
