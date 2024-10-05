package Modelo;

import Exceptions.BadIdRecintoException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map;
import Vista.*;
import javax.swing.table.DefaultTableModel;


public class Menu {
    private HashMap<String, Evento> mapaEventos = new HashMap<String, Evento>();
    private ArrayList<Recinto> arrayRecintos = new ArrayList<Recinto>();
    private FechaFormatChecker fechaChecker = new FechaFormatChecker();
    
    public boolean cargarDatosArrayListRecintos(){
        String rutaCSV = "src/Recurso/datosRecintos.csv"; 
        String currentLineaCSV; 
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaCSV))) {
            currentLineaCSV = reader.readLine();
            while (currentLineaCSV != null) { 
                String[] datosRecinto = currentLineaCSV.split(","); 
                
                if (datosRecinto.length >= 4){
                    Recinto newRecinto = new Recinto();
                    try{
                        newRecinto.setIdRecinto(datosRecinto[0]); // ID Recinto
                        newRecinto.setNombreRecinto(datosRecinto[1]); // Name
                        newRecinto.setUbicacion(datosRecinto[2]); // Location
                        newRecinto.setCupos(Integer.parseInt(datosRecinto[3])); // Capacity (parse to int)
                        
                        // Add the Recinto object to the arrayRecintos ArrayList
                        arrayRecintos.add(newRecinto);
                    }
                    catch (BadIdRecintoException | NumberFormatException e) {
                        return false; // Validation error
                    }
                }
                currentLineaCSV = reader.readLine(); // Update to read the next line
            }
        } 
        catch (IOException e) {
            return false; // File reading error
        }
        return true; // Data loaded successfully
    }
    
    public boolean cargarDatosMapaEventos(){
        String rutaCSV = "src/Recurso/datosEventos.csv"; 
        String currentLineaCSV;
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
                        return false; // Validation error
                    }
                }
                currentLineaCSV = reader.readLine(); // Update to read the next line
            }
        } 
        catch (IOException e) {
            return false; // File reading error
        }
        return true; // Data loaded successfully
    }
    
    public boolean guardarDatosArrayListRecintos() {
        String rutaCSV = "src/Recurso/datosRecintos.csv"; 

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
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
        System.out.println("Data Recintos Saved!");
        return true;
    }

    public boolean guardarDatosMapaEventos() {
        String rutaEventosCSV = "src/Recurso/datosEventos.csv"; 
        String rutaAsistentesCSV = "src/Recurso/datosAsistentes.csv";
        
        //Limpiamos datosAsistentes para poder agregar correctamente TODO lo que se cambio durante la sesion
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(rutaAsistentesCSV));
        } 
        catch (IOException e) {
            System.out.println("Error clearing file: " + e.getMessage());
            return false;
        }
        
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
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
        System.out.println("Data Eventos Saved!");
        return true;
    }

    // metodos de evento
    public int agregarEvento(String llave, String nombreEvento, String fechaEvento, String descripcion, String grupoObjetivo, String idRecinto, String valorEntrada) 
                            throws BadIdRecintoException, IllegalArgumentException{
        Evento evento = mapaEventos.get(llave);
        if (evento != null){
            // ya existe
            return 0;
        }
        // si no, lo agregamos.
        Recinto recintoTMP;
        recintoTMP = obtenerRecinto(idRecinto);
        //el Recinto existe
        if (recintoTMP != null){ // verificar la existencia del reicnto
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
            return -2; // no se encontró el recinto
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
            return -1; // se debe crear un recinto
        }
        return 1;
    }
    
     
    public boolean eliminarEvento(String idEvento) {
        
        Evento eventoTMP = mapaEventos.get(idEvento);

        if (eventoTMP != null) {
            mapaEventos.remove(idEvento);
            return true; // Se eliminó correctamente el evento
        }
        return false; // El evento no existe
    }
    
    public void mostrarEventos(DefaultTableModel tableEventos){
        tableEventos.setRowCount(0);
        Collection<Evento> colEventos = mapaEventos.values();
        for (Evento evento : colEventos) {
            if (obtenerRecinto(evento.getidRecinto()) == null) {
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

    public int modificarEvento(String idEvento, String nombre, String fecha, String descripcion, String grupoObjetivo, String idRecinto, String valorEntrada)
                               throws IllegalArgumentException{
        
        Recinto recintoTMP;
        Evento eventoTMP = (Evento) mapaEventos.get(idEvento);
        
        
        if (eventoTMP != null){ // se encontró al evento a modificar
            recintoTMP = obtenerRecinto(idRecinto);
            if (recintoTMP != null && !checkRecintoCuposHoldEventoAsistentes(eventoTMP.getSizeArrayAsistentes(), idRecinto)){
                return 0;
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
                return 1; // se pudo modificar
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
                return -1;
            }
            else if (recintoTMP == null && arrayRecintos.size() != 0){
                return -2;
            }
        }  
        return 0; // no se pudo modificar
    }
    
    public Evento obtenerEvento(String idEvento){
        return mapaEventos.get(idEvento);
    }
    
    public boolean checkEvento(String idEvento){
        return mapaEventos.containsKey(idEvento);
    }


    // metodos de Array Recintos

    // AGREGAR RECINTO #2
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

    public Recinto obtenerRecinto(String idRecinto){
        int i;
        for (i = 0; i < arrayRecintos.size(); i++){
            if (arrayRecintos.get(i).getIdRecinto().equals(idRecinto) ){
                return arrayRecintos.get(i);
            }
        }
        return null; // en caso de no encontrarse.
    }

    public void mostrarRecintos(){//consola
        int i;
        System.out.println("RECINTOS:");
        for (i = 0; i < arrayRecintos.size(); i++){
            System.out.println("Nombre: " + arrayRecintos.get(i).getNombreRecinto());
            System.out.println("Ubicacion: " + arrayRecintos.get(i).getUbicacion());
            System.out.println("Capacidad: " + arrayRecintos.get(i).getCupos());
            System.out.println("Id: " + arrayRecintos.get(i).getIdRecinto());
            System.out.println("------------------------------------------------------");
        }
    }
    
    public void mostrarRecintos(DefaultTableModel tableRecintos){//ventana, muestra TODOS los recintos
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

    public boolean modificarRecinto(String idRecinto, String nombre, String ubicacion, String cupos) throws IllegalArgumentException {
        Recinto recintoMod = new Recinto();
        recintoMod = obtenerRecinto(idRecinto);
        if (recintoMod != null){
            // setters de los parametros.
            recintoMod.setNombreRecinto(nombre);
            recintoMod.setUbicacion(ubicacion);
            if (!cupos.matches("\\d+")){
                throw new IllegalArgumentException("ERROR: Valor de Cupos invalido. Por favor, ingrese solo valores numericos.");
            }
            if (!checkRecintoCuposHoldEventoAsistentes(idRecinto, Integer.parseInt(cupos))){
                return false;
            }
            recintoMod.setCupos(Integer.parseInt(cupos));
            return true; // se pudo modificar
        }
        return false;
    }
    
    public boolean checkRecintoCuposHoldEventoAsistentes(int eventoCupos, String idRecinto){
        if (obtenerRecinto(idRecinto).getCupos() < eventoCupos){
            return false;
        }
        return true;
    }
    
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
    
    
    
    public void runMenu() throws BadIdRecintoException{//FUNCION TEMPORAL DE TESTEO
        //inicializarEventosyAsistentes();
        //inicializarRecintos();
        cargarDatosArrayListRecintos();
    }
    
    public Menu getMenu(){
        return this;
    }
    
    /*
    public void runMenu() throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        boolean status = false;
        String idEvento, idRecinto, idAsistente;
        Evento eventoTMP;
        int opcion = 0, opcionB, opcionC, opcionD;
        
        inicializarEventosyAsistentes();
        inicializarRecintos();
        
        System.out.println("**ADMINISTRADOR DE EVENTOS v1.0**\n");

        while (flag) {
            menuPrincipal();
            opcion = opcionValida( 1, 4, lector);

            switch (opcion) {
                case 1:
                menuEventos();
                opcionB = opcionValida( 1, 5, lector);
                switch (opcionB) {
                case 1:
                  // Agregar nuevo Evento
                    status = agregarEvento(lector, arrayRecintos);
                    if (status == true){
                        System.out.println("[INFO]: evento agregado correctamente");
                    }else{
                        System.out.println("[ERROR]: no se pudo agregar el evento");
                    }
                  break;
                case 2:
                  // Modificar Evento
                    status = modificarEvento(lector);
                    if (status == true){
                        System.out.println("[INFO]: evento modificado correctamente");
                    }else{
                        System.out.println("[ERROR]: no se pudo modificar el evento");
                    }
                  break;
                case 3:
                    // Eliminar Evento
                    status = eliminarEvento(lector);
                    if (status == true){
                        System.out.println("[INFO]: evento eliminado correctamente");
                    }else{
                        System.out.println("[ERROR]: no se pudo eliminar el evento");
                    }
                    break;
                case 4:
                    // Mostrar Eventos
                    mostrarEventos();
                    break;
                case 5:
                    System.out.println("Regresando al menu principal...");
                    // Regresar al menu principal
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
                    break;
                }
                break;
                case 2:
                    menuAsistentes();
                    opcionC = opcionValida( 1, 5, lector);
                    switch (opcionC) {
                        case 1:
                            // Solicitar el id del evento al que agregar el asistente.
                            System.out.println("Ingrese el id del evento al que desea agregar el asistente: " );
                            idEvento = lector.readLine();
                            eventoTMP = mapaEventos.get(idEvento);
                            
                            if (eventoTMP != null){
                                status = eventoTMP.ventaEntrada(); // agregar asistente
                                Recinto recintoTMP = obtenerRecinto(arrayRecintos, eventoTMP.getidRecinto());
                                int cuposTMP = recintoTMP.getCupos();
                                if (eventoTMP.getSizeArrayAsistentes() <= cuposTMP){
                                    if (status){
                                        System.out.println("[INFO] Venta exitosa! El asistente se ha agregado correctamente al Evento");
                                        System.out.println("El cobro por la entrada es: " + eventoTMP.getValorEntrada());
                                    }else{
                                        System.out.println("[ERROR]: No se pudo hacer el cobro de la entrada");
                                    }
                                }else{
                                    System.out.println("[ERROR]: No se pudo hacer el cobro de la entrada");
                                }
                            }
                            // Agregar nuevo Asistente
                            break;
                        case 2:
                            // Modificar Asistente
                            // primero debo solciitar el id del evento al que pertenece el asistente.
                            System.out.println("Ingrese el id del evento al que desea modificar el asistente: " );
                            idEvento = lector.readLine();
                            eventoTMP = mapaEventos.get(idEvento);
                            if(eventoTMP != null){
                                
                                // solciitar el id del asistente a modificar.
                                System.out.println("Ingrese el id del asistente a modificar: " );
                                idAsistente = lector.readLine();
                                if(eventoTMP.modificarAsistente(idAsistente, lector) == true){
                                    System.out.println("[INFO]: asistente modificado correctamente");
                                }else{
                                    System.out.println("[ERROR] No se pudo modificar el asistente");
                                }
                            }else{
                                System.out.println("[ERROR] No se pudo modificar el asistente");
                            }



                            break;
                        case 3:
                            // Eliminar Asistente
                            System.out.println("Ingrese el id del evento al que desea eliminar el asistente: " );
                            idEvento = lector.readLine();
                            eventoTMP = mapaEventos.get(idEvento);
                            if(eventoTMP != null){

                                if(eventoTMP.eliminarAsistente(lector) == true){
                                    System.out.println("[INFO]: Se ha devuelto el dinero al cliente y se ha descontado el valor de la entrada al fondo recaudado del evento.");
                                }else{
                                    System.out.println("[ERROR] No se pudo eliminar el asistente");
                                }
                            }else{
                                System.out.println("[ERROR] No se pudo eliminar el asistente");
                            }


                            break;
                        case 4:
                            // Mostrar Asistentes
                            System.out.println("Ingrese el id del evento al que desea mostrar sus asistentes: " );
                            idEvento = lector.readLine();
                            eventoTMP = mapaEventos.get(idEvento);
                            if(eventoTMP != null){
                                eventoTMP.mostrarAsistentes();
                            }else{
                                System.out.println("[INFO]: No hay asistentes para mostrar");
                            }
                            break;
                        case 5:
                            // Regresar al menu principal
                            System.out.println("Regresando al menu principal...");
                            break;
                        default:
                            System.out.println("Ingrese una opcion valida.");
                            break;
                    }
                    break;
                case 3:
                menuRecintos();
                opcionD = opcionValida( 1, 5, lector);
                switch (opcionD) {
                    case 1:
                        // Agregar nuevo Recinto
                        if (agregarRecinto(lector, arrayRecintos) == true){
                            System.out.println("[INFO]: recinto agregado correctamente");
                        }else{
                            System.out.println("[ERROR]: no se pudo agregar el recinto");
                        }
                        break;
                    case 2:
                        // Modificar Recinto
                        if (modificarRecinto(lector) == true){
                            System.out.println("[INFO]: recinto modificado correctamente");
                        }else{
                            System.out.println( "[ERROR]: no se pudo modificar el recinto");
                        }
                        break;
                    case 3:
                        // Eliminar Recinto
                        System.out.println("Ingrese el id del recinto que desea eliminar: ");
                        idRecinto = lector.readLine();
                        if (eliminarRecinto(idRecinto, arrayRecintos) == true){
                            System.out.println("[INFO]: recinto eliminado correctamente");
                        }else{
                            System.out.println("[ERROR]: no se pudo eliminar el recinto");
                        }
                        break;
                    case 4:
                        // Mostrar Recinto
                        if (arrayRecintos.isEmpty()){
                            System.out.println("No existen Recintos registrados en el sistema");
                        }
                        mostrarRecintos();
                        break;
                    case 5:
                        // Regresar al menu principal
                        System.out.println("Regresando al menu principal...");
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida.");
                        break;
                    }
                    break;
                case 4:
                    flag = false; // salir
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
                    break;
            }
      }
    }
    */
  }