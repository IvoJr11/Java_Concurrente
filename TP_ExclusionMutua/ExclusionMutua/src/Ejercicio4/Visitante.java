package Ejercicio4;

public class Visitante implements Runnable {
  private String nombre;
  private Parque parque;

  public Visitante(String nombre, Parque parque) {
    this.nombre = nombre;
    this.parque = parque;
  }

  public void run() {
    boolean exito = false;
    int random = (int) (Math.random() * parque.getListaAreas().length);
    int i = 0;
    while(i<5 && !exito) {
      exito = intentarReserva(random);
      i++;
    }
  }

  public boolean intentarReserva(int i) {
    boolean exito = false;
    Area area = parque.getListaAreas()[i];
    if(area.reservar()) {
      System.out.println("El " + this.nombre + " ha reservado en el " + area.getNombreArea());
      System.out.println("Reservas restantes en " + area.getNombreArea() + ": " + area.getReservasDisponibles());
      exito = true;
    } else {
      System.out.println("El " + this.nombre + " no ha podido reservar en el " + area.getNombreArea() + " (No hay reservas disponibles)");
    }
    return exito;
  }

  public String getNombre() {
    return nombre;
  }
  public Parque getParque() {
    return parque;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public void setParque(Parque parque) {
    this.parque = parque;
  }
}
