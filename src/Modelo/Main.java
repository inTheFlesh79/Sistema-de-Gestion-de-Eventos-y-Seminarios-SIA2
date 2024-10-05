package Modelo;


import Exceptions.BadIdRecintoException;
import Vista.VP;

public class Main {
    public static void main(String[] args) throws BadIdRecintoException {
        //Se instancia VP, que significa "Vista Principal"
        VP v = new VP();
        v.setVisible(true);
    }
}

