package Modelo;

import Exceptions.BadIdRecintoException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import javax.swing.table.DefaultTableModel;


public class Menu {
    private HashMap<String, Evento> mapaEventos = new HashMap<String, Evento>();
    private ArrayList<Recinto> arrayRecintos = new ArrayList<Recinto>();
    
    //============================ Carga y Guardado de Datos ============================
    
    //Esta Funcion carga los datos del archivo CSV y los agrega al ArrayList de Recintos.
    public boolean cargarDatosArrayListRecintos(){
        String rutaCSV = "src/Recurso/datosRecintos.csv"; 
        String currentLineaCSV; 
        
        /*
            El manejo de excepciones en esta funcion no tiene influencias en la Vista del programa,
            ya que, la funciones creadas para las colecciones utilizadas (agregar, eliminar, etc)
            se encargan de que todos los datos esten cumpliendo las normas y formatos que se esperan de ellos
        */
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaCSV))) {
            currentLineaCSV = reader.readLine();
            
            while (currentLineaCSV != null) { 
                String[] datosRecinto = currentLineaCSV.split(","); 
                if (datosRecinto.length >= 4){
                    Recinto newRecinto = new Recinto();
                    try{
                        newRecinto.setIdRecinto(datosRecinto[0]);
                        newRecinto.setNombreRecinto(datosRecinto[1]);
                        newRecinto.setUbicacion(datosRecinto[2]);
                        newRecinto.setCupos(Integer.parseInt(datosRecinto[3]));
                        arrayRecintos.add(newRecinto);
                    }
                    catch (BadIdRecintoException | NumberFormatException e) {
                        return false;
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
    
    //Esta Funcion carga los datos del archivo CSV y los agrega al Hashmap de Eventos y sus respectivos ArrayList de Asistentes.
    public boolean cargarDatosMapaEventos(){
        String rutaCSV = "src/Recurso/datosEventos.csv"; 
        String currentLineaCSV;
        
        /*
            El manejo de excepciones en esta funcion no tiene influencias en la Vista del programa,
            ya que, la funciones creadas para las colecciones utilizadas (agregar, eliminar, etc)
            se encargan de que todos los datos esten cumpliendo las normas y formatos que se esperan de ellos
        */
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaCSV))) {
            currentLineaCSV = reader.readLine();
            while (currentLineaCSV != null) { 
                String[] datosEventos = currentLineaCSV.split(","); 
                
                if (datosEventos.length >= 7){
                    Evento newEvento = new Evento();
                    try{
                        newEvento.setIdEvento(datosEventos[0]);      
                        newEvento.setNombreEvento(datosEventos[1]);    
                        newEvento.setFechaEvento(datosEventos[2]);     
                        newEvento.setidRecinto(datosEventos[3]);        
                        newEvento.setDescripcion(datosEventos[4]);     
                        newEvento.setGrupoObjetivo(datosEventos[5]);
                        newEvento.setValorEntrada(Integer.parseInt(datosEventos[6]));
                        newEvento.cargarDatosArrayListAsistentes(newEvento.getIdEvento());
                        mapaEventos.put(newEvento.getIdEvento(), newEvento);
                    }
                    catch (NumberFormatException e) {
                        return false;
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
    
    //Esta Funcion guarda los datos del ArrayList de Recintos en un archivo CSV.
    public boolean guardarDatosArrayListRecintos() {
        String rutaCSV = "src/Recurso/datosRecintos.csv"; 
        
        /*
            El manejo de excepciones en esta funcion no tiene influencias en la Vista del programa,
            ya que, la funciones creadas para las colecciones utilizadas (agregar, eliminar, etc)
            se encargan de que todos los datos esten cumpliendo las normas y formatos que se esperan de ellos
        */
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaCSV))) {
            for (int i = 0; i < arrayRecintos.size(); i++) {
                Recinto currentRecinto = arrayRecintos.get(i); 
                String newLineaCSV = currentRecinto.getIdRecinto() + "," + 
                                     currentRecinto.getNombreRecinto() + "," + 
                                     currentRecinto.getUbicacion() + "," + 
                                     currentRecinto.getCupos();
                writer.write(newLineaCSV);
                writer.newLine();
            }
        } 
        catch (IOException e) {
            return false;
        }
        return true;
    }
    
    //Esta Funcion guarda los datos del HashMap de Eventos, incluyendo cada uno de sus ArrayList de tipo Asistente, y su información asociada en 2 archivos CSV.
    public boolean guardarDatosMapaEventos() {
        String rutaEventosCSV = "src/Recurso/datosEventos.csv"; 
        String rutaAsistentesCSV = "src/Recurso/datosAsistentes.csv";
        
        //Limpiamos datosAsistentes.csv para poder agregar correctamente TODO lo que se cambio durante la sesion.
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(rutaAsistentesCSV));
        } 
        catch (IOException e) {
            return false;
        }
        
        /*
            El manejo de excepciones en esta funcion no tiene influencias en la Vista del programa,
            ya que, la funciones creadas para las colecciones utilizadas (agregar, eliminar, etc)
            se encargan de que todos los datos esten cumpliendo las normas y formatos que se esperan de ellos
        */
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaEventosCSV))) {
            Collection<Evento> colEventos = mapaEventos.values();
            for (Evento evento : colEventos) {
                String newLineaCSV = evento.getIdEvento() + "," +
                                     evento.getNombreEvento() + "," +
                                     evento.getFechaEvento() + "," +
                                     evento.getidRecinto() + "," +
                                     evento.getDescripcion() + "," +
                                     evento.getGrupoObjetivo() + "," +
                                     evento.getValorEntrada();
                evento.guardarDatosArrayAsistentes(evento.getIdEvento());
                writer.write(newLineaCSV);
                writer.newLine();
            }
        } 
        catch (IOException e) {
            return false;
        }
        return true;
    }

    //============================ Metodos para manejar mapaEventos ============================
    
    //Esta Funcion agrega un evento al HashMap de Eventos. Tiene manejo de excepciones y retornos con valores diferentes, con significados especificos.
    public int agregarEvento(String llave, String nombreEvento, String fechaEvento, String descripcion, String grupoObjetivo, String idRecinto, String valorEntrada) 
                            throws BadIdRecintoException, IllegalArgumentException{
        Evento evento = mapaEventos.get(llave);
        if (evento != null){
            return 0;//El Evento existe
        }
        
        Recinto recintoTMP;
        recintoTMP = obtenerRecinto(idRecinto);
        if (recintoTMP != null){ //El Recinto existe.
            Evento newE = new Evento();
            newE.setidRecinto(idRecinto);
            newE.setNombreEvento(nombreEvento);
            newE.setFechaEvento(fechaEvento);
            newE.setDescripcion(descripcion);
            newE.setGrupoObjetivo(grupoObjetivo);
            if (!valorEntrada.matches("\\d+")){
                throw new IllegalArgumentException("ERROR: Valor de entrada invalido. Por favor, ingrese solo valores numericos.");
            }   
            newE.setValorEntrada(Integer.parseInt(valorEntrada));
            newE.setIdEvento(llave);
            mapaEventos.put(llave, newE);
        }
        else if (recintoTMP == null && arrayRecintos.size() != 0){
            return -2; //Existen Recintos, pero no es el buscado.
        }
        if (arrayRecintos.size() == 0){
            Evento newE = new Evento();
            newE.setidRecinto(idRecinto);
            newE.setNombreEvento(nombreEvento);
            newE.setFechaEvento(fechaEvento);
            newE.setDescripcion(descripcion);
            newE.setGrupoObjetivo(grupoObjetivo);
            newE.setValorEntrada(Integer.parseInt(valorEntrada));
            newE.setIdEvento(llave);
            mapaEventos.put(llave, newE);
            return -1; //No existen Recintos en el sistema. Se procede a crear uno.
        }
        return 1;
    }
    
    //Esta Funcion elimina un evento del HashMap de Eventos si existe. 
    public boolean eliminarEvento(String idEvento) {
        Evento eventoTMP = mapaEventos.get(idEvento);
        if (eventoTMP != null) {
            mapaEventos.remove(idEvento);
            return true; //Se encuentra el Evento y se elimina del mapa.
        }
        return false; //El evento no existe.
    }
    
    //Esta Funcion muestra los eventos en una tabla utilizando un modelo de JTable que se obtiene desde Vista.GestionEventos.
    public void mostrarEventos(DefaultTableModel tableEventos){
        tableEventos.setRowCount(0);
        Collection<Evento> colEventos = mapaEventos.values();
        for (Evento evento : colEventos) {
            if (obtenerRecinto(evento.getidRecinto()) == null) {//Si se elimina el Recinto asociado a un Evento, se le deja declarado "No Asignado".
                Object[] filaTableEventos1 = {evento.getIdEvento(), evento.getNombreEvento(), evento.getFechaEvento(), "No Asignado", 
                                              evento.getGrupoObjetivo(), evento.getDescripcion(), evento.getValorEntrada()};
                tableEventos.addRow(filaTableEventos1);
                continue;
            }
            Object[] filaTableEventos = {evento.getIdEvento(), evento.getNombreEvento(), evento.getFechaEvento(), obtenerRecinto(evento.getidRecinto()).getNombreRecinto(), 
                                        evento.getGrupoObjetivo(), evento.getDescripcion(), evento.getValorEntrada()};
            tableEventos.addRow(filaTableEventos);
        }
    }
    
    //Esta Funcion modifica los datos de un evento en el HashMap de Eventos. Tiene manejo de excepciones y retornos con valores diferentes, y con significados especificos.
    public int modificarEvento(String idEvento, String nombre, String fecha, String descripcion, String grupoObjetivo, String idRecinto, String valorEntrada)
                               throws IllegalArgumentException{
        Recinto recintoTMP;
        Evento eventoTMP = (Evento) mapaEventos.get(idEvento);
        
        if (eventoTMP != null){
            recintoTMP = obtenerRecinto(idRecinto);
            if (recintoTMP != null && !checkRecintoCuposHoldEventoAsistentes(eventoTMP.getSizeArrayAsistentes(), idRecinto)){
                return 0;//El Evento no se modifico. (Caso Especial: El Recinto que se quiere asociar al Evento existe, pero no cuenta con suficiente capacidad para manejar la cantidad de asistentes.)
            }
            
            if (recintoTMP != null){
                eventoTMP.setNombreEvento(nombre);
                eventoTMP.setFechaEvento(fecha);
                eventoTMP.setGrupoObjetivo(grupoObjetivo);
                eventoTMP.setDescripcion(descripcion);
                eventoTMP.setidRecinto(idRecinto);
                if (!valorEntrada.matches("\\d+")){
                    throw new IllegalArgumentException("ERROR: Valor de entrada invalido. Por favor, ingrese solo valores numericos.");
                } 
                eventoTMP.setValorEntrada(Integer.parseInt(valorEntrada));
                return 1; //El Evento se modifico.
            }
            else if (recintoTMP == null && arrayRecintos.size() == 0){
                eventoTMP.setNombreEvento(nombre);
                eventoTMP.setFechaEvento(fecha);
                eventoTMP.setGrupoObjetivo(grupoObjetivo);
                eventoTMP.setDescripcion(descripcion);
                if (!valorEntrada.matches("\\d+")){
                    throw new IllegalArgumentException("ERROR: Valor de entrada invalido. Por favor, ingrese solo valores numericos.");
                } 
                eventoTMP.setValorEntrada(Integer.parseInt(valorEntrada));
                return -1;//No existen Recintos registrados en el sistema. Se procede a crear uno.
            }
            else if (recintoTMP == null && arrayRecintos.size() != 0){
                return -2;
            }
        }  
        return 0; //El Evento no se modifico.
    }
    
    //Esta Funcion obtiene y retorna un Evento del mapaEventos en base a un ID entregada.
    public Evento obtenerEvento(String idEvento){
        return mapaEventos.get(idEvento);
    }
    
    //Esta Funcion revisa si existe un Evento asociado a la ID entregada.
    public boolean checkEvento(String idEvento){
        return mapaEventos.containsKey(idEvento);
    }

    //============================ Metodos para manejar arrayRecintos ============================

    //Esta Funcion agrega un nuevo Recinto al arrayRecintos basado en los atributos entregados. Tiene manejo de excepciones.
    public void agregarRecinto(String idRecinto, String nombre, String ubicacion, String cupos) throws BadIdRecintoException, IllegalArgumentException{
        Recinto newR = new Recinto();
        newR.setIdRecinto(idRecinto);
        newR.setNombreRecinto(nombre);
        newR.setUbicacion(ubicacion);
        if (!cupos.matches("\\d+")){
            throw new IllegalArgumentException("ERROR: Valor de Cupos invalido. Por favor, ingrese solo valores numericos.");
        }
        newR.setCupos(Integer.parseInt(cupos));
        arrayRecintos.add(newR);
    }
    
    //Esta Funcion agrega un Recinto y lo vincula a un Evento usando sus IDs. Tiene manejo de excepciones.
    public void agregarRecinto(String idEvento, String idRecinto, String nombre, String ubicacion, String cupos) throws BadIdRecintoException, IllegalArgumentException{
        Recinto newR = new Recinto();
        Evento currentEvento = new Evento();
        newR.setIdRecinto(idRecinto);
        newR.setNombreRecinto(nombre);
        newR.setUbicacion(ubicacion);
        if (!cupos.matches("\\d+")){
            throw new IllegalArgumentException("ERROR: Valor de Cupos invalido. Por favor, ingrese solo valores numericos.");
        }
        newR.setCupos(Integer.parseInt(cupos));
        arrayRecintos.add(newR);
        currentEvento = mapaEventos.get(idEvento);
        currentEvento.setidRecinto(idRecinto);
    }
    
    //Esta Funcion elimina un Recinto del arrayRecintos en base a una ID.
    public boolean eliminarRecinto(String idRecintoEliminar) {
        if (arrayRecintos != null && !arrayRecintos.isEmpty()) {
            for (Recinto recintoTMP : arrayRecintos) {
                String idRecintoTMP = recintoTMP.getIdRecinto();
                if (idRecintoTMP.equals(idRecintoEliminar)) {
                    arrayRecintos.remove(recintoTMP);
                    return true;
                }
            }
        }
        return false;
    }
    
    //Esta Funcion busca y devuelve un Recinto por su ID.
    public Recinto obtenerRecinto(String idRecinto){
        int i;
        for (i = 0; i < arrayRecintos.size(); i++){
            if (arrayRecintos.get(i).getIdRecinto().equals(idRecinto) ){
                return arrayRecintos.get(i);
            }
        }
        return null; // en caso de no encontrarse.
    }
    
    //Esta Funcion muestra todos los recintos en el sistema mediante el modelo de un JTable.
    public void mostrarRecintos(DefaultTableModel tableRecintos){
        tableRecintos.setRowCount(0);
        Object[] filaTableRecintos  = new Object[4];
        for (int i = 0; i < arrayRecintos.size(); i++){
            filaTableRecintos[0] = arrayRecintos.get(i).getIdRecinto();
            filaTableRecintos[1] = arrayRecintos.get(i).getNombreRecinto();
            filaTableRecintos[2] = arrayRecintos.get(i).getUbicacion();
            filaTableRecintos[3] = arrayRecintos.get(i).getCupos();
            tableRecintos.addRow(filaTableRecintos);
        }
    }
    
    //Esta Funcion muestra UNO de los recintos en el sistema mediante el modelo de un JTable y una ID especifica.
    public boolean mostrarRecintos(DefaultTableModel tableRecintos, String idRecinto){//ventana, muestra SOLO el recinto especificado
        tableRecintos.setRowCount(0);
        Object[] filaTableRecintos = new Object[4];
        Recinto currentRecinto = obtenerRecinto(idRecinto);
        if (currentRecinto != null){
            filaTableRecintos[0] = currentRecinto.getIdRecinto();
            filaTableRecintos[1] = currentRecinto.getNombreRecinto();
            filaTableRecintos[2] = currentRecinto.getUbicacion();
            filaTableRecintos[3] = currentRecinto.getCupos();
            tableRecintos.addRow(filaTableRecintos);
            return true;
        }
        else{
            return false;
        }
    }
    
    //Esta Funcion modifica los datos de un Recinto en base a una ID. Tiene manejos de Excepciones.
    public boolean modificarRecinto(String idRecinto, String nombre, String ubicacion, String cupos) throws IllegalArgumentException {
        Recinto recintoMod = new Recinto();
        recintoMod = obtenerRecinto(idRecinto);
        if (recintoMod != null){
            recintoMod.setNombreRecinto(nombre);
            recintoMod.setUbicacion(ubicacion);
            if (!cupos.matches("\\d+")){
                throw new IllegalArgumentException("ERROR: Valor de Cupos invalido. Por favor, ingrese solo valores numericos.");
            }
            if (!checkRecintoCuposHoldEventoAsistentes(idRecinto, Integer.parseInt(cupos))){
                return false;//La nueva cantidad de cupos del Recinto no satisface la cantidad de Asistentes a un Evento/s que son dependientes de este.
            }
            recintoMod.setCupos(Integer.parseInt(cupos));
            return true; //El Recinto se modifico.
        }
        return false;//El Recinto no se modifico.
    }
    
    //Esta Funcion verifica si el recinto tiene cupos suficientes para un evento dado.
    public boolean checkRecintoCuposHoldEventoAsistentes(int eventoCupos, String idRecinto){
        if (obtenerRecinto(idRecinto).getCupos() < eventoCupos){
            return false;
        }
        return true;
    }
    
    //Esta Funcion verifica si al modificar los cupos de un Recinto, estos siguen siendo suficientes para los asistentes con Eventos asociados a este Recinto.
    public boolean checkRecintoCuposHoldEventoAsistentes(String idRecinto, int newCupos){
        Collection<Evento> colEventos = mapaEventos.values();
        for (Evento evento : colEventos) {
            if (evento.getidRecinto().equals(obtenerRecinto(idRecinto).getIdRecinto())){
                if (evento.getSizeArrayAsistentes() > newCupos){
                    return false;
                }
            }
        }
        return true;
    }
    
    //Esta Funcion retorna el menu actual, el cual contiene todos los datos de las colecciones en su actual sesion.
    public Menu getMenu(){
        return this;
    }
}