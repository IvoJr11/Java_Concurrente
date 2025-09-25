package Ejercicio4;

public class Area {
  public int reservasDisponibles;
  public String nombreArea;

  public Area(int reservasDisponibles, String nombreArea) {
    this.reservasDisponibles = reservasDisponibles;
    this.nombreArea = nombreArea;
  }
  
  public synchronized int getReservasDisponibles() {
    return reservasDisponibles;
  }
  public synchronized String getNombreArea() {
    return nombreArea;
  }

  public void setReservasDisponibles(int reservasDisponibles) {
    this.reservasDisponibles = reservasDisponibles;
  }
  public void setNombreArea(String nombreArea) {
    this.nombreArea = nombreArea;
  }

  public synchronized boolean reservar() {
    boolean exito = false;
    if(this.reservasDisponibles > 0) {
      this.reservasDisponibles--;
      exito = true;
    }
    return exito;
  }
}
