package Modelo;

import Exceptions.BadIdRecintoException;

public class Recinto {

    private String idRecinto;
    private String nombreRecinto;
    private String ubicacion;
    private int cupos;
    // constructor
    public Recinto(){
       //empty
    }

    // ---- Setters ----
    
    // Esta Funcion establece el identificador del recinto, validando su formato.
    // Lanza una excepción si el formato del id del recinto es incorrecto.
    public void setIdRecinto(String idRecinto) throws BadIdRecintoException{
      if (!validarFormatoIdRecinto(idRecinto)){
          throw new BadIdRecintoException();
      }
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

    // ------ Getters ------
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
    
    //Funciones de Validacion para setters
    
    // Esta función se encarga de validar el id del Recinto para que  solo contenga valores numericos
    public boolean validarFormatoIdRecinto(String idRecinto) {
        return idRecinto.matches("\\d+");// validacion de contenga SOLO valores numnéricos.
    }
}
