/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author MSI
 */
public class BadIdAsistenteException extends Exception{
    public BadIdAsistenteException(){
        super("ERROR: Formato de ID Asistente no es correcto");
    }
}
