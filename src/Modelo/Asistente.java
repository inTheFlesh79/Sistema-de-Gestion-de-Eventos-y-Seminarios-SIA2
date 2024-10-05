package Modelo;

import Exceptions.BadIdAsistenteException;
import Exceptions.BadRangeEdadException;

public class Asistente {
    private String idAsistente;
    private String nombre;
    private int edad;
    private String email;
    
    public Asistente(){
        //empty
    }

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setidAsistente(String idAsistente) throws BadIdAsistenteException{
        if (!validaFormatoIdAsistente(idAsistente)){
            throw new BadIdAsistenteException();
        }
        this.idAsistente = idAsistente;
    }   

    public void setEmail(String email) {
        this.email = email;
    }  

    public void setEdad(int edad) throws BadRangeEdadException{
        if (edad < 0 || edad > 120) {  // You can define your age limits
            throw new BadRangeEdadException();
        }
        this.edad = edad;
    }
    
    //Funciones de Validacion para Setters
    public boolean validaFormatoIdAsistente(String idAsistente) {
        return idAsistente.matches("A\\d+");
    }
}