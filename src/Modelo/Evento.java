package Modelo;


/**
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
    //Esta Funcion carga los datos del archivo CSV y los agrega al ArrayList de Recintos.
    public boolean cargarDatosArrayListAsistentes(String idEvento) {
        String rutaCSV = "src/Recurso/datosAsistentes.csv"; 
        String currentLineaCSV;
        
        /*
            El manejo de excepciones en esta funcion no tiene influencias en la Vista del programa,
            ya que, la funciones creadas para las colecciones utilizadas (agregar, eliminar, etc)
            se encargan de que todos los datos esten cumpliendo las normas y formatos que se esperan de ellos
        */
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
                            // Retorna false si ocurre alguna excepcion durante la validacion de datos
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
    // Esta Funcion guarda los datos de los asistentes en un archivo CSV asociado a un evento.
    // Agrega las entradas al archivo en modo append, permitiendo añadir nuevos datos sin sobrescribir los existentes
    public boolean guardarDatosArrayAsistentes(String idEvento){
        String rutaAsistentesCSV = "src/Recurso/datosAsistentes.csv";
        /*
            El manejo de excepciones en esta funcion no tiene influencias en la Vista del programa,
            ya que, la funciones creadas para las colecciones utilizadas (agregar, eliminar, etc)
            se encargan de que todos los datos esten cumpliendo las normas y formatos que se esperan de ellos
        */
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaAsistentesCSV, true))) {
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
            return false; 
        }
        return true; 
    }
    
    // Esta funcion calcula el monto total del evento
    public void calcularMontoTotal(){//MODIFICAR
        int tamano = arrayAsistentes.size();
        setMontoTotal(tamano * this.valorEntrada);
    }
    
    
    //Esta Funcion agrega un asistente al evento y vende una entrada.
    //Lanza excepciones si el id, la edad o el formato de los datos del asistente son incorrectos.
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
    
    //Esta cuncion obtiene el asistente del evento mediante un ID Asistente.
    public Asistente obtenerAsistente(String id){
        int i;
        Asistente asisTMP;
        for (i = 0; i < arrayAsistentes.size(); i++){
            asisTMP = (Asistente) arrayAsistentes.get(i);
            if( asisTMP.getidAsistente().equals(id) ){
                return asisTMP; // retorna el Asistente en caso de encontrarlo
            }
        }
        return null; // si no encuentra nada entonces retorna null
    }

    // Esta funcion elimina un asistete de un evento a partir del ID Asistente.
    public boolean eliminarAsistente(String idAsistente) {      
        Asistente a = (Asistente) obtenerAsistente(idAsistente);
        if (obtenerAsistente(idAsistente) != null){
            montoTotal -= valorEntrada; // se descuenta por el reembolso
            arrayAsistentes.remove(a);
            return true; // se removio corretamente.
        }
        return false; // no se removió ya que no se encontró el asistente.
    }
    
    // Esta Funcion modifica los detalles de un asistente existente, incluyendo su nombre, edad y email.
    // Lanza excepciones si la edad no es un valor numérico válido o si el asistente no se encuentra.
    public boolean modificarAsistente(String idAsistente, String nombreAsistente, String edad, String email) throws BadRangeEdadException, IllegalArgumentException{//TOMAR DE EJEMPLO PARA CAMBIAR LOGICA TRY-CATCH
        Asistente asisTMP = (Asistente) obtenerAsistente(idAsistente);
        if (asisTMP != null){
            asisTMP.setNombre(nombreAsistente);
            if (!edad.matches("\\d+")){
                // Lanza una excepción si la edad no es un número válido
                throw new IllegalArgumentException("ERROR: Edad Invalida. Por favor, ingrese solo valores numericos.");
            }
            asisTMP.setEdad(Integer.parseInt(edad));
            asisTMP.setEmail(email);
            return true; // Modificación exitosa
        }
        return false; // Asistente no encontrado
    }
    
    
    //Esta Funcion muestra la lista de asistentes en una tabla, limpiando previamente las filas existentes.
    //No retorna ningun valor, solo actualiza el modelo de la tabla con los datos de los asistentes
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
    
    // Esta Funcion muestra los datos de un asistente especifico en la tabla, basado en su id.
    // No retorna ningun valor, solo actualiza la tabla con los datos del asistente si existe.
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
    
    // ----- Getters -----
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
    
    // ----- Setters -----
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