package Modelo;

import Exceptions.BadIdAsistenteException;

public class Asistente {
    private String idAsistente;
    private String nombre;
    private int edad;
    private String email;
    
    public Asistente(){
        //empty
    }
    
    public Asistente(String idAsistente, String nombre, int edad, String email) {
        //Inserte validaciones pertinentes
        this.idAsistente = idAsistente;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
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

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    //Funciones de Validacion para Setters
    private boolean validaFormatoIdAsistente(String idAsistente) {
        return idAsistente.matches("A\\d+");
    }

}