package Modelo;


import Exceptions.BadIdAsistenteException;
import Exceptions.BadRangeEdadException;

public class Asistente {
    // atributos de la clase Asistente 
    private String idAsistente;
    private String nombre;
    private int edad;
    private String email;
    // contructor vacío
    public Asistente(){
        //empty
    }
    
    // ----- Getters -----
    public String getNombre() {
        return nombre;
    } 

    public String getidAsistente() {
        return idAsistente;
    }

    public String getEmail() {
        return email;
    }  

    public int getEdad() {
        return edad;
    } 

    // ----- Setters -----
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
    * Esta Funcion establece el id del asistente si pasa la validacion de formato.
    * Lanza una excepcion si el formato del id es incorrecto.
    */
    public void setidAsistente(String idAsistente) throws BadIdAsistenteException{
        if (!validaFormatoIdAsistente(idAsistente)){
            throw new BadIdAsistenteException();
        }
        this.idAsistente = idAsistente;
    }   
    
    public void setEmail(String email) {
        this.email = email;
    }  
    
    /**
    * Esta Funcion establece la edad del asistente si se encuentra dentro del rango valido.
    * Lanza una excepcion si la edad es menor que 0 o mayor que 120.
    */
    public void setEdad(int edad) throws BadRangeEdadException{
        if (edad < 0 || edad > 120) {  // You can define your age limits
            throw new BadRangeEdadException();
        }
        this.edad = edad;
    }
    
    //Funciones de Validacion para Setters
    // Esta función se encarga de validar el formato del ID Asistente.
    public boolean validaFormatoIdAsistente(String idAsistente) {
        return idAsistente.matches("A\\d+");
    }
}