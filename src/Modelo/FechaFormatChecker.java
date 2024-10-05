/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MSI
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaFormatChecker{
    
    // Function to check if a string is in "dd/MM/yyyy" format and is a valid date
    public boolean checkFormatoFecha(String fecha) {
        // Regular expression for "dd/MM/yyyy"
        String formato = "\\d{2}/\\d{2}/\\d{4}";
        
        // Check if the date matches the regex
        if (!fecha.matches(formato)){
            return false;  // Date does not match the "dd/MM/yyyy" format
        }

        // Now, let's check if the date is valid by parsing it
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);  // Don't allow lenient parsing (e.g., 31/02/2024 should fail)
        
        try {
            // Try to parse the date
            Date sdfFecha = sdf.parse(fecha);
            return true;
        } catch (ParseException e) {
            // If parsing fails, the date is not valid
            return false;
        }
    }

    // Function to get the current system date in "dd/MM/yyyy" format
    public String getFechaSistema() {
        SimpleDateFormat fechaSistema = new SimpleDateFormat("dd/MM/yyyy");
        Date currentFecha = new Date();
        return fechaSistema.format(currentFecha);  // Return system date as a string in "dd/MM/yyyy" format
    }
    
}

