package Modelo;

import Vista.VistaPrincipal;
import Vista.VP;
import java.io.*;

// Clase Main (debe estar en su propio archivo o no ser pública)

public class Main {
    public static void main(String[] args) {
        // Create an instance of VistaPrincipal
        //VistaPrincipal ventana = new VistaPrincipal();
        VP v = new VP();
        Menu menu = new Menu();
        v.setVisible(true);
        //menu.runMenu();
        
    }
}

