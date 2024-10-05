package Modelo;


/**
 *
 * Integrantes:
 * Constanza Contreras
 * Vicente Parada
 * Raul Rozas
 */
import java.io.*;
import java.util.ArrayList;

public class Evento{ 
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
    
    public Evento (String idRecinto, String nombre, String fecha, String descripcion, String gO) {
        //inserte validaciones pertinentes
        nombreEvento = nombre;
        fechaEvento = fecha;
        this.idRecinto = idRecinto;
        this.descripcion = descripcion;
        grupoObjetivo = gO;
    }

    public void inicializarAsistentes(){
        Asistente asistente1 = new Asistente("A123", "Alexis Sanchez", 18, "messi@gmail.com");
        Asistente asistente2 = new Asistente("A124", "Vicente VANcoco", 20, "viceVancoco@mail.pucv.cl");
        arrayAsistentes.add(asistente1);
        arrayAsistentes.add(asistente2);
    }

    /*Funcion que agrega un asistente al evento, y vende una entrada*/
    public void ventaEntrada(Asistente newAsistente) {
        montoTotal += valorEntrada;
        arrayAsistentes.add(newAsistente);
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

    public boolean modificarAsistente(String idAsistente, BufferedReader reader) throws IOException{
        String nombreAsistente, email;
        int edad;
        Asistente asisTMP = (Asistente) obtenerAsistente(idAsistente);

        if (asisTMP != null){
            System.out.println("Ingrese nuevo nombre del asistente");
            nombreAsistente = reader.readLine();
            System.out.println("Ingrese nueva edad del asistente");
            edad = Integer.parseInt(reader.readLine());
            System.out.println("Ingrese nuevo email del asistente");
            email = reader.readLine();

            asisTMP.setNombre(nombreAsistente);
            asisTMP.setEdad(edad);
            asisTMP.setEmail(email);

            return true;
        }
        return false;
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

    public void setValorTotal(int valorTotal) {
        this.valorEntrada = valorEntrada;
    }

}