package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;


public class Menu {
    private HashMap<String, Evento> mapaEventos = new HashMap<String, Evento>();

    private ArrayList<Recinto> arrayRecintos = new ArrayList<Recinto>();
     // almacena una coleccion de claves String para luego iterarlas y mostrar los elementos del mapa 

    private void inicializarEventosyAsistentes(){
        Evento evento1 = new Evento(null, "Seminario de Psicologia Juvenil", "01/01/2022", "Repertorio de algoritmos", "Tercera Edad");
        Evento evento2 = new Evento(null, "Charla POO", "02/01/2022", "clases y sus metodos", "Pregrado");
        mapaEventos.put("1111", evento1);
        mapaEventos.put("1112", evento2);
        evento1.inicializarAsistentes();
        evento2.inicializarAsistentes();
    }

    private void inicializarRecintos(){
        Recinto recinto1 = new Recinto();
        recinto1.setIdRecinto("0001");
        recinto1.setNombreRecinto("Movistar Arena");
        recinto1.setUbicacion("Av. Pedro Picapiedra #1, Valparaiso");
        recinto1.setCupos(100);
        arrayRecintos.add(recinto1);

        Recinto recinto2 = new Recinto();
        recinto2.setIdRecinto("0002");
        recinto2.setNombreRecinto("IBC");
        recinto2.setUbicacion("Av. Francia #403, Valparaiso");
        recinto2.setCupos(50);
        arrayRecintos.add(recinto2);
    }
    
    private void menuPrincipal() {
      System.out.println("**MENU PRINCIPAL**");
      System.out.println("1) Gestionar Eventos");
      System.out.println("2) Gestionar Asistentes");
      System.out.println("3) Gestionar Recintos");
      System.out.println("4) Salir");
    }

    private void menuEventos() {
      System.out.println("**MENU EVENTOS**");
      System.out.println("1) Agregar Nuevo Evento");
      System.out.println("2) Modificar Evento");
      System.out.println("3) Eliminar Evento");
      System.out.println("4) Mostrar Eventos");
      System.out.println("5) Regresar al menu principal");
    }

    private void menuAsistentes() {
      System.out.println("**MENU ASISTENTES**");
      System.out.println("1) Registrar nuevo Asistente.");
      System.out.println("2) Modificar Asistentes de un evento");
      System.out.println("3) Eliminar Asistentes de un evento");
      System.out.println("4) Mostrar Asistentes de un evento");
      System.out.println("5) Regresar al menu principal");
    }

    private void menuRecintos() {
        System.out.println("**MENU RECINTOS**");
        System.out.println("1) Agregar recinto.");
        System.out.println("2) Modificar recinto");
        System.out.println("3) Eliminar recinto");
        System.out.println("4) Mostrar recintos");
        System.out.println("5) Regresar al menu principal");
    }

    private int opcionValida(int inf, int sup, BufferedReader lector) throws IOException {
        int opcion;
        do {
            System.out.print("Ingrese una opcion: ");
            opcion = Integer.parseInt(lector.readLine());
        } while (!(opcion >= inf && opcion <= sup));

        return opcion;
    }


    // metodos de evento
    private boolean agregarEvento(BufferedReader lector, ArrayList<Recinto> arrayRecintos) throws IOException{
        String llave, nombreEvento, fechaEvento, descripcion, grupoObjetivo, idRecinto;
        int valorEntrada;
        System.out.println("Ingrese el id del evento a agregar: ");
        llave = lector.readLine();
        Evento evento = mapaEventos.get(llave);
        if (evento != null){
            // ya hay un evento con ese ID
            System.out.println("El evento ya existe");
            return false;
        }
        // si no, lo agregamos.

        System.out.println("Ingrese el nombre del evento: ");
        nombreEvento = lector.readLine();

        System.out.println("Ingrese la fecha del evento: ");
        fechaEvento = lector.readLine();

        System.out.println("Ingrese una descripcion del evento: ");
        descripcion = lector.readLine();

        System.out.println("Ingrese el grupo objetivo del evento: ");
        grupoObjetivo = lector.readLine();

        System.out.println("Ingrese el valor de la entrada para el evento: ");
        valorEntrada = Integer.parseInt(lector.readLine());

        //validaciones para verificar que el idRecinto a ingresar este asociado a un Recinto en la listaRecintos
        while (true){
            System.out.println("Ingrese el id del Recinto donde se realizara el evento: ");
            idRecinto = lector.readLine();
            //la lista Recintos esta VACIA
            if (arrayRecintos.isEmpty()){
                System.out.println("No existe ningun Recinto registrado en el sistema. Por favor, registre un recinto antes de continuar.");
                agregarRecinto(lector, arrayRecintos, idRecinto);
                evento = new Evento(idRecinto, nombreEvento, fechaEvento, descripcion, grupoObjetivo);
                break;
            }
            else{
                Recinto recintoTMP = new Recinto();
                recintoTMP = obtenerRecinto(arrayRecintos, idRecinto);
                //el Recinto existe
                if (recintoTMP != null){
                    
                }
                // el Recinto NO existe
                else{ 
                    System.out.println("ERROR: El Recinto ingresado no se encuentra registrado en el sistema");
                }
            }
        }
        mapaEventos.put(llave, evento);
        return true;
    }

    private boolean eliminarEvento(BufferedReader lector) throws IOException {
        // Solicitar al usuario el ID del evento a eliminar
        String idEvento;
        System.out.println("Ingrese el ID del evento a eliminar");
        idEvento = lector.readLine();
        Evento eventoTMP = mapaEventos.get(idEvento);

        if (eventoTMP != null) {
            mapaEventos.remove(idEvento);
            return true; // Se eliminó correctamente el evento
        }
        return false; // El evento no existe
    }


    private void mostrarEventos(){
        Collection<Evento> eventos = mapaEventos.values();  // Obtiene la colección de eventos
        String idRecinto;
        Recinto recintoTMP;
        for (Evento evento : eventos) {  // Itera directamente sobre los eventos
            System.out.println("Evento: " + evento.getNombreEvento());
            System.out.println("Fecha: " + evento.getFechaEvento());
            idRecinto = evento.getidRecinto();
            recintoTMP = obtenerRecinto(arrayRecintos, idRecinto);
            if (evento.getidRecinto() != null && recintoTMP != null) {
                recintoTMP.getNombreRecinto();
                System.out.println("Lugar: " + recintoTMP.getNombreRecinto() );
            } else {
                System.out.println("Lugar: N/A");
            }
            System.out.println("Descripcion: " + evento.getDescripcion());
            System.out.println("Grupo Objetivo: " + evento.getGrupoObjetivo());
            System.out.println("------------------------------------------------------");
        }
    }

    private boolean modificarEvento( BufferedReader lector) throws IOException{
        String idEvento;
        System.out.println("Ingres el id del evento a modificar: ");
        idEvento = lector.readLine();
        int valorEntrada;
        String nombre, fecha, descripcion, grupoObjetivo, idRecinto;
        Evento eventoTMP = (Evento) mapaEventos.get(idEvento);
        if (eventoTMP != null){
            System.out.println("Ingrese el nuevo nombre del evento: ");
            nombre = lector.readLine();

            while (true){
                System.out.println("Ingrese el Recinto donde se realizara el evento: ");
                idRecinto = lector.readLine();
                //la lista Recintos esta VACIA
                if (arrayRecintos.isEmpty()){
                    System.out.println("No existe ningun Recinto registrado en el sistema. Por favor, registre un recinto antes de continuar.");
                    agregarRecinto(lector, arrayRecintos, idRecinto);
                    break;
                }
                else{
                    Recinto recintoTMP = new Recinto();
                    recintoTMP = obtenerRecinto(arrayRecintos, idRecinto);
                    
                    //el Recinto existe
                    if (recintoTMP != null){
                        break;
                    }
                    // el Recinto NO existe
                    else{ 
                        System.out.println("ERROR: El Recinto ingresado no se encuentra registrado en el sistema");
                    }
                }
            }
            System.out.println("Ingrese la nueva fecha del evento: ");
            fecha = lector.readLine();

            System.out.println("Ingrese la nueva descripcion del evento: ");
            descripcion = lector.readLine();

            System.out.println("Ingrese el nuevo grupo Objetivo (Charla/Seminario) del evento: ");
            grupoObjetivo = lector.readLine();

            System.out.println("Ingrese el nuevo Valor de entrada del evento: ");
            valorEntrada = Integer.parseInt(lector.readLine());
            // setters de los parametros.
            eventoTMP.setNombreEvento(nombre);
            eventoTMP.setFechaEvento(fecha);
            eventoTMP.setGrupoObjetivo(grupoObjetivo);
            eventoTMP.setDescripcion(descripcion);
            eventoTMP.setidRecinto(idRecinto);
            eventoTMP.setValorTotal(valorEntrada);
            
            return true; // se pudo modificar
        }  

        return false; // no se pudo modificar
    }


    // metodos de Array Recintos

    //AGREGAR RECINTO #1
    private boolean agregarRecinto(BufferedReader lector, ArrayList<Recinto> arrayRecintos) throws IOException {
        Recinto recintoTMP = new Recinto();

        while (true){
            System.out.println("Ingrese el id del recinto: ");
            String idRecinto = lector.readLine();
            if (obtenerRecinto(arrayRecintos, idRecinto) == null){
                recintoTMP.setIdRecinto(idRecinto);
                break;
            }
            else{
                System.out.println("ERROR: El id ingresado ya esta asociado a un Recinto.");
                return false;
            }
        }

        System.out.println("Ingrese el nombre del recinto: ");
        recintoTMP.setNombreRecinto(lector.readLine());

        System.out.println("Ingrese la ubicacion del recinto: ");
        recintoTMP.setUbicacion(lector.readLine());

        System.out.println("Ingrese la capacidad del recinto: ");
        recintoTMP.setCupos(Integer.parseInt(lector.readLine()));

        // Agregar recinto al ArrayList
        arrayRecintos.add(recintoTMP);

        return true;
    }

    // AGREGAR RECINTO #2
    private boolean agregarRecinto(BufferedReader lector, ArrayList<Recinto> arrayRecintos, String idRecinto) throws IOException {
        Recinto recintoTMP = new Recinto();

        System.out.println("Ingrese el nombre del recinto: ");
        recintoTMP.setNombreRecinto(lector.readLine());

        System.out.println("Ingrese la ubicacion del recinto: ");
        recintoTMP.setUbicacion(lector.readLine());

        System.out.println("Ingrese la capacidad del recinto: ");
        recintoTMP.setCupos(Integer.parseInt(lector.readLine()));

        System.out.println("Ingrese el id del recinto: ");
        recintoTMP.setIdRecinto(idRecinto);

        // Agregar recinto al ArrayList
        arrayRecintos.add(recintoTMP);

        return false;
    }

    private boolean eliminarRecinto(String idRecintoEliminar, ArrayList<Recinto> arrayRecintos) {
    if (arrayRecintos != null && !arrayRecintos.isEmpty()) {
        for (Recinto recintoTMP : arrayRecintos) {
            String idRecintoTMP = recintoTMP.getIdRecinto();
            if (idRecintoTMP.equals(idRecintoEliminar)) {  // Assuming getIdRecinto() returns String
                arrayRecintos.remove(recintoTMP);
                return true;
            }
        }
    }
    return false;
}

    private Recinto obtenerRecinto(ArrayList<Recinto> array, String idRecinto){
        int i;
        for (i = 0; i < array.size(); i++){
            if (array.get(i).getIdRecinto().equals(idRecinto) ){
                return array.get(i);
            }
        }

        return null; // en caso de no encontrarse.
    }

    private void mostrarRecintos(){
        int i;
        for (i = 0; i < arrayRecintos.size(); i++){
            System.out.println("Nombre: " + arrayRecintos.get(i).getNombreRecinto());
            System.out.println("Ubicacion: " + arrayRecintos.get(i).getUbicacion());
            System.out.println("Capacidad: " + arrayRecintos.get(i).getCupos());
            System.out.println("Id: " + arrayRecintos.get(i).getIdRecinto());
            System.out.println("------------------------------------------------------");
        }
    }

    private boolean modificarRecinto( BufferedReader lector)throws IOException{
        System.out.println("Ingrese el id del recinto que desea modificar: ");
        String idRecinto = lector.readLine();

        Recinto recintoTMP = (Recinto)obtenerRecinto(arrayRecintos, idRecinto);
        if (recintoTMP != null){
            String nombre, ubicacion;
            int cupos;
            System.out.println("Ingrese el nuevo nombre del recinto: ");
            nombre = lector.readLine();
            System.out.println("Ingrese la nueva ubicacion del recinto: ");
            ubicacion = lector.readLine();
            System.out.println("Ingrese nueva capacidad del Recinto: ");
            cupos = Integer.parseInt(lector.readLine());

            // setters de los parametros.
            recintoTMP.setNombreRecinto(nombre);
            recintoTMP.setUbicacion(ubicacion);
            recintoTMP.setCupos(cupos);

            return true; // se pudo modificar
        }

        return false;
    }
    
    public boolean checkEvento(String idEvento){
        return mapaEventos.containsKey(idEvento);
    }
    
    public void runWea(){//FUNCION TEMPORAL DE TESTEO
        inicializarEventosyAsistentes();
        inicializarRecintos();
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