/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author MSI
 */
public class BadRangeEdadException extends Exception{
    public BadRangeEdadException(){
        super("ERROR: La edad ingresada se encuentra fuera de un rango etario razonable. Intente con valores ente 0 y 120");
    }
}
