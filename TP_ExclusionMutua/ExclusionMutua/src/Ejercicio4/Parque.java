package Ejercicio4;

public class Parque {
  public String nombre;
  public Area[] listaAreas;

  public Parque(String nombre, Area[] listaAreas) {
    this.nombre = nombre;
    this.listaAreas = listaAreas;
  }

  public String getNombre() {
    return nombre;
  }
  public Area[] getListaAreas() {
    return listaAreas;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public void setListaAreas(Area[] listaAreas) {
    this.listaAreas = listaAreas;
  }
  
}
