/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author MSI
 */
public class BadIdRecintoException extends Exception{
    public BadIdRecintoException(){
        super("Formato ID Recinto ingresado es incorrecto (Debe utilizar solo valores numericos para ID Recinto)");
    }
}
