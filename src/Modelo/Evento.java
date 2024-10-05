package Modelo;


/**
 *
 * Integrantes:
 * Constanza Contreras
 * Vicente Parada
 * Raul Rozas
 */
import Exceptions.BadIdAsistenteException;
import Exceptions.BadRangeEdadException;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Evento{
    private String idEvento;
    private String nombreEvento;
    private String fechaEvento;
    private String idRecinto;
    private String descripcion;
    private String grupoObjetivo;
    private int montoTotal;
    private int valorEntrada;
    private ArrayList<Asistente> arrayAsistentes = new ArrayList<Asistente>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // constructor Evento.
    
    public Evento(){
        //empty
    }
    
    public boolean cargarDatosArrayListAsistentes(String idEvento) {
        String rutaCSV = "src/Recurso/datosAsistentes.csv"; 
        String currentLineaCSV;

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaCSV))) {
            currentLineaCSV = reader.readLine();
            while (currentLineaCSV != null) { 
                String[] datosAsistentes = currentLineaCSV.split(",");
                if (datosAsistentes[4].equals(idEvento)) {
                    if (datosAsistentes.length == 5) {
                        Asistente newAsistente = new Asistente();
                        try {
                            newAsistente.setidAsistente(datosAsistentes[0]);
                            newAsistente.setNombre(datosAsistentes[1]); 
                            newAsistente.setEdad(Integer.parseInt(datosAsistentes[2]));
                            newAsistente.setEmail(datosAsistentes[3]); 
                            arrayAsistentes.add(newAsistente);
                        } 
                        catch (BadIdAsistenteException | BadRangeEdadException | NumberFormatException e) {
                            return false;
                        }
                    }
                }
                currentLineaCSV = reader.readLine();
            }
        } 
        catch (IOException e) {
            return false;
        }

        return true;
    }
    
    public boolean guardarDatosArrayAsistentes(String idEvento){
        String rutaAsistentesCSV = "src/Recurso/datosAsistentes.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaAsistentesCSV, true))) { // Append mode
            for (int i = 0; i < arrayAsistentes.size(); i++) {
                Asistente asistente = arrayAsistentes.get(i);
                String newLineaCSV = asistente.getidAsistente() + "," +
                                     asistente.getNombre() + "," +
                                     asistente.getEdad() + "," +
                                     asistente.getEmail() + "," +
                                     idEvento;
                writer.write(newLineaCSV);
                writer.newLine();
            }
        } 
        catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
        System.out.println("Data Asistentes Saved!");
        return true;
    }
    
    public void calcularMontoTotal(){//MODIFICAR
        int tamano = arrayAsistentes.size();
        setMontoTotal(tamano * this.valorEntrada);
    }
    

    /*public void inicializarAsistentes(){
        Asistente asistente1 = new Asistente("A123", "Alexis Sanchez", 18, "messi@gmail.com");
        Asistente asistente2 = new Asistente("A124", "Vicente VANcoco", 20, "viceVancoco@mail.pucv.cl");
        arrayAsistentes.add(asistente1);
        arrayAsistentes.add(asistente2);
    }*/

    /*Funcion que agrega un asistente al evento, y vende una entrada*/
    public void ventaEntrada(String idAsistente, String nombre, String email, String edad) throws BadIdAsistenteException, BadRangeEdadException, IllegalArgumentException{
        Asistente newA = new Asistente();
        newA.setidAsistente(idAsistente);
        newA.setNombre(nombre);
        newA.setEmail(email);
        if (!edad.matches("\\d+")){
            throw new IllegalArgumentException("ERROR: Edad Invalida. Por favor, ingrese solo valores numericos.");
        }
        newA.setEdad(Integer.parseInt(edad));
        arrayAsistentes.add(newA);
        montoTotal += valorEntrada;
    } 

    public Asistente obtenerAsistente(String id){
        int i;
        Asistente asisTMP;
        for (i = 0; i < arrayAsistentes.size(); i++){
            asisTMP = (Asistente) arrayAsistentes.get(i);
            if( asisTMP.getidAsistente().equals(id) ){
                return asisTMP;
            }
        }
        return null;
    }

    public boolean eliminarAsistente(String idAsistente) {      
        Asistente a = (Asistente) obtenerAsistente(idAsistente);
        if (obtenerAsistente(idAsistente) != null){
            montoTotal -= valorEntrada; // se descuenta por el reembolso
            arrayAsistentes.remove(a);
            return true; // se removio corretamente.
        }
        return false;
    }
    
    public boolean modificarAsistente(String idAsistente, String nombreAsistente, String edad, String email) throws BadRangeEdadException, IllegalArgumentException{//TOMAR DE EJEMPLO PARA CAMBIAR LOGICA TRY-CATCH
        Asistente asisTMP = (Asistente) obtenerAsistente(idAsistente);
        if (asisTMP != null){
            asisTMP.setNombre(nombreAsistente);
            if (!edad.matches("\\d+")){
                throw new IllegalArgumentException("ERROR: Edad Invalida. Por favor, ingrese solo valores numericos.");
            }
            asisTMP.setEdad(Integer.parseInt(edad));
            asisTMP.setEmail(email);
            return true;
        }
        return false;
    }
    
    public void mostrarAsistentes(DefaultTableModel tableEventos){
        tableEventos.setRowCount(0);
        for (int i = 0; i < arrayAsistentes.size(); i++) {
            Object[] filaTableAsistentes = {arrayAsistentes.get(i).getidAsistente(),
                                            arrayAsistentes.get(i).getNombre(),
                                            arrayAsistentes.get(i).getEmail(),
                                            arrayAsistentes.get(i).getEdad()};
            tableEventos.addRow(filaTableAsistentes);
        }
    }
    
    public void mostrarAsistentes(DefaultTableModel tableEventos, String idAsistente){
        tableEventos.setRowCount(0);
        Asistente currentAsistente = obtenerAsistente(idAsistente);
        if (currentAsistente != null) {
            Object[] filaTableAsistentes = {currentAsistente.getidAsistente(),
                                            currentAsistente.getNombre(),
                                            currentAsistente.getEmail(),
                                            currentAsistente.getEdad()};
            tableEventos.addRow(filaTableAsistentes);
        }
    }
    
    public void mostrarAsistentes(){

        int i;
        System.out.println("**Asistentes del evento: "+ nombreEvento+"** \n");
        for (i = 0; i < arrayAsistentes.size(); i++){
            

            System.out.println("Id Asistente: "+arrayAsistentes.get(i).getidAsistente());
            System.out.println("Nombre Asistente: "+arrayAsistentes.get(i).getNombre());
            System.out.println("Edad Asistente: "+arrayAsistentes.get(i).getEdad());
            System.out.println("Email Asistente: "+arrayAsistentes.get(i).getEmail());    
            System.out.println("--------------------------------------------");
        }
    }
    

    // getters
    public String getIdEvento() {
        return idEvento;
    }
    
    public String getNombreEvento() {
        return nombreEvento;
    } 

    public String getFechaEvento() {
        return fechaEvento;
    }   

    public String getidRecinto() {
        return idRecinto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGrupoObjetivo() {
        return grupoObjetivo;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public int getValorEntrada() {
        return valorEntrada;
    }

    public int getSizeArrayAsistentes() {
        return arrayAsistentes.size();
    }
    
    // setters
    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }
    
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }   

    public void setidRecinto(String idRecinto) {
        this.idRecinto = idRecinto;
    }  

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    } 

    public void setGrupoObjetivo(String grupoObjetivo) {
        this.grupoObjetivo = grupoObjetivo;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public void setValorEntrada(int valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

}