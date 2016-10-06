/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTapuntas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author javi
 */
class Usuario{
    
    private String nombreUsuario;
    private String contraseña;
    private String direccionCorreo;
    private String nombre = " ";
    private String telefono = " ";
    private String breveDescripcionPersonal = " ";
    private TipoTransaccion preferenciaCobro = TipoTransaccion.Efectivo;
    private boolean visibilidad = false;
    private boolean pendienteBaja = false;
    private Map<String, Vehiculo> vehiculos = new HashMap();
    private Map<String, PlanAlquiler> planesAlquiler = new HashMap();
            
    Usuario(String nombreUsuario, String contraseña, String direccionCorreo){
        
      this.nombreUsuario= nombreUsuario;
      this.contraseña = contraseña;
      this.direccionCorreo = direccionCorreo;
    }
    
    String getNombre(){
        
        return this.nombre;
    }
    
    String getTelefono(){
        
        return this.telefono;
    }
    
    String getDescripcionPersonal(){
        
        return this.breveDescripcionPersonal;
    }

    TipoTransaccion getPreferenciaCobro(){
        
        return this.preferenciaCobro;
    }
    
    void setNombre(String nombre){
        
        this.nombre = nombre;
    }
    
    void setTelefono(String telefono){
        
        this.telefono = telefono;
    }
    
    void setDescripcionPersonal(String descripcion){
        
        this.breveDescripcionPersonal = descripcion;
    }
    
    void setPreferenciaCobro(TipoTransaccion preferencia){
        
        this.preferenciaCobro = preferencia;
    }
    
    void modificarVisibilidad(boolean visibilidad){
        
        this.visibilidad = visibilidad;
    }
    
    ArrayList<String> consultarPerfil(){
        
        ArrayList<String> infoPerfil = new ArrayList<String>();
        
        infoPerfil.add(this.nombre);
        infoPerfil.add(this.telefono);
        infoPerfil.add(this.breveDescripcionPersonal);
        infoPerfil.add(Boolean.toString(this.visibilidad));
        
        return infoPerfil;
    }
    
    void definirPlanAlquiler(String matricula, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, String ciudadRecogida) throws Exception{
        
        Vehiculo vehiculo = this.buscarVehiculo(matricula);
        
        if(!(vehiculo.estasDisponible(fechaInicio, fechaFin)))
            
            throw new Exception("El vehículo ya pertenece a un plan de alquiler en esas fechas");
        
        PlanAlquiler nuevo = new PlanAlquiler(vehiculo, fechaInicio, fechaFin, ciudadRecogida);
        String clave = nuevo.getFechaInicio() + "~" + matricula;
        this.planesAlquiler.put(clave, nuevo);
        vehiculo.incluirPlanAlquiler(nuevo);
    }
    
    void eliminarVehiculo(String matricula) throws Exception{
        
        Vehiculo vehiculo = this.buscarVehiculo(matricula);
        
        if(vehiculo.comprobarEstadoAlquileres())
            
            throw new Exception("El vehículo no se puede eliminar, tiene vigentes alquileres o viajes");
        
        vehiculo.eliminarVehiculoAlquileres();
        this.vehiculos.remove(matricula);
    }
    
    void introducirPerfil(String nombre, String telefono, String breveDescripcion, TipoTransaccion preferenciaCobro){
        
        this.nombre = nombre;
        this.telefono = telefono;
        this.breveDescripcionPersonal = breveDescripcion;
        this.preferenciaCobro = preferenciaCobro;
        this.visibilidad = true;
    }
    
    void nuevoVehiculo(String matricula, String marca, String modelo, String color, int numeroPlazas, String categoria, String confort){
        
        Vehiculo nuevo = new Vehiculo(matricula, marca, modelo, color, numeroPlazas, categoria, confort);
        this.vehiculos.put(matricula, nuevo);
    }
    
    ArrayList<ArrayList<String>> obtenerPlanesAlquiler(){
        
        ArrayList<ArrayList<String>> planes = new ArrayList<ArrayList<String>>();
        
        for(Map.Entry<String, PlanAlquiler> plan : this.planesAlquiler.entrySet()){
            
            if(!plan.getValue().visible() && plan.getValue().vigente())
            
                planes.add(plan.getValue().obtenerDatosPlanAlquiler());
        
        }
        
        return planes;
    }
    
    ArrayList<PlanAlquiler> obtenerPlanesQueCumplanRequisitos(String ciudadRecogida, GregorianCalendar fechaInicio, GregorianCalendar fechaFin){
        
        ArrayList<PlanAlquiler> resultado = new ArrayList<PlanAlquiler>();
        
        for(Map.Entry<String, PlanAlquiler> plan : this.planesAlquiler.entrySet()){
            
            if(plan.getValue().getCiudadRecogida().equals(ciudadRecogida) &&
               plan.getValue().getFechaInicio().equals(fechaInicio) &&
               plan.getValue().getFechaFin().equals(fechaFin))
                
                resultado.add(plan.getValue());
            
        }
        
        return resultado;
    }
    
    ArrayList<ArrayList<String>> obtenerVehiculos(){
        
        ArrayList<ArrayList<String>> vehiculosRegistrados = new ArrayList<ArrayList<String>>();
        
        for(Map.Entry<String, Vehiculo> vehiculo : this.vehiculos.entrySet())
            
            vehiculosRegistrados.add(vehiculo.getValue().obtenerDatosVehiculo());
        
        return vehiculosRegistrados;
    }
    
    void ofertarPlanAlquiler(GregorianCalendar fechaInicio, String matricula){
        
        this.buscarPlanAlquiler(fechaInicio, matricula).modificarVisibilidad(true);
    }

    boolean tienePerfilDefinido(){
        
        return !(this.nombre.equals(" ") && this.telefono.equals(" ") && this.breveDescripcionPersonal.equals(" "));
    }
    
    boolean tieneVehiculo(String matricula){
        
        return this.vehiculos.containsKey(matricula);
    }
    
    private Vehiculo buscarVehiculo(String matricula){
        
        return this.vehiculos.get(matricula);
    }
    
    private PlanAlquiler buscarPlanAlquiler(GregorianCalendar fechaInicio, String matricula){
        
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = formatoFecha.format(fechaInicio.getTime());
        String clave = fechaString + "~" + matricula;
        return this.planesAlquiler.get(clave);
    }  
}