package Modelo;

public class Recinto {

  private String idRecinto;
  private String nombreRecinto;
  private String ubicacion;
  private int cupos;
  // constructor
  public Recinto(){
     //empty
  }

  
  // setters
  public void setIdRecinto(String idRecinto) {
    //validaciones pertinentes
    this.idRecinto = idRecinto;
  }

  public void setNombreRecinto(String nombreRecinto) {
    //validaciones pertinentes
    this.nombreRecinto = nombreRecinto;
  }

  public void setUbicacion(String ubicacion) {
    //validaciones pertinentes
    this.ubicacion = ubicacion;
  }

  public void setCupos(int cupos) {
    //validaciones pertinentes
    this.cupos = cupos;
  }

  //getters
  public String getIdRecinto() {
    return idRecinto;
  }

  public String getNombreRecinto() {
    return nombreRecinto;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public int getCupos() {
    return cupos;
  }
}