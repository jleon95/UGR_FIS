/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTapuntas;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author javi
 */
public class Tapuntas{
    
    /*
        IMPORTANTE:
        Me he tomado la libertad de añadir más excepciones aparte de las indicadas en los diagramas de comunicación.
    
    */
    
    private Map<String, Usuario> usuarios = new HashMap();
    
    private static Tapuntas tapuntas = new Tapuntas();
    
    private Tapuntas(){}

    public static Tapuntas getInstance(){
        
        return tapuntas;        
    }

    public void altaRegistro(String nombreUsuario, String contraseña, String direccionCorreo) throws Exception{
       
       if(this.existeUsuario(nombreUsuario))
           
           throw new Exception("Ya existe un usuario con ese nombre de usuario");
       
       usuarios.put(nombreUsuario, new Usuario(nombreUsuario, contraseña, direccionCorreo));
    }
    
    public void añadirVehiculo(String nombreUsuario, String matricula, String marca, String modelo, String color, int numeroPlazas, String categoria, String confort) throws Exception{
    
        if(!this.existeUsuario(nombreUsuario))
            
            throw new Exception("El usuario no existe");
        
        if(this.existeVehiculo(matricula))
            
            throw new Exception("Ya existe otro vehículo en el sistema con esa matrícula");
        
        this.usuarios.get(nombreUsuario).nuevoVehiculo(matricula, marca, modelo, color, numeroPlazas, categoria, confort);  
    }
    
    public ArrayList<String> consultarPerfil(String nombreUsuario) throws Exception{
        
        if(!this.existeUsuario(nombreUsuario))
            
            throw new Exception("El usuario no existe");
            
        return this.buscarUsuario(nombreUsuario).consultarPerfil();
    }
    
    public void definirPlanAlquiler(String nombreUsuario, String matricula, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, String ciudadRecogida) throws Exception{
        
        if(!this.existeUsuario(nombreUsuario))
            
            throw new Exception("El usuario no existe");
        
        Usuario usuario = this.buscarUsuario(nombreUsuario);
        
        if(!usuario.tieneVehiculo(matricula))
            
            throw new Exception("El usuario no tiene un vehículo asociado a esa matrícula");
        
        if(fechaInicio.after(fechaFin))
            
            throw new Exception("La fecha de inicio no puede ser mayor que la fecha de fin");
        
        this.buscarUsuario(nombreUsuario).definirPlanAlquiler(matricula, fechaInicio, fechaFin, ciudadRecogida);
    }
    
    public void eliminarVehiculoPropietario(String nombreUsuario, String matricula) throws Exception{
     
        if(!this.existeUsuario(nombreUsuario))
            
            throw new Exception("El usuario no existe");
           
        Usuario usuario = this.buscarUsuario(nombreUsuario);
        
        if(!usuario.tieneVehiculo(matricula))
            
            throw new Exception("El usuario no tiene un vehículo asociado a esa matrícula");
        
        usuario.eliminarVehiculo(matricula);
    }
    
    public void introducirPerfil(String nombreUsuario, String nombre, String telefono, String breveDescripcion, TipoTransaccion preferenciaCobro) throws Exception{
    
        if(!this.existeUsuario(nombreUsuario))
            
            throw new Exception("El usuario no existe");
        
        Usuario usuario = this.buscarUsuario(nombreUsuario);
        
        if(usuario.tienePerfilDefinido())
            
            throw new Exception("El usuario ya tiene un perfil definido");
        
        usuario.introducirPerfil(nombre, telefono, breveDescripcion, preferenciaCobro);
    }
    
    public Set<String> listaUsuarios(){
        
        return usuarios.keySet();
    }
    
    public ArrayList<ArrayList<String>> obtenerPlanesAlquiler(String nombreUsuario) throws Exception{
        
        if(!this.existeUsuario(nombreUsuario))
            
            throw new Exception("El usuario no existe");
        
        return this.buscarUsuario(nombreUsuario).obtenerPlanesAlquiler();
    }
    
    public ArrayList<ArrayList<String>> obtenerVehiculos(String nombreUsuario) throws Exception{
        
        if(!this.existeUsuario(nombreUsuario))
            
            throw new Exception("El usuario no existe");
        
        return this.buscarUsuario(nombreUsuario).obtenerVehiculos();
    }
    
    public void ofertarPlanAlquiler(String nombreUsuario, GregorianCalendar fechaInicio, String matricula) throws Exception{
        
        if(!this.existeUsuario(nombreUsuario))
            
            throw new Exception("El usuario no existe");
        
        this.buscarUsuario(nombreUsuario).ofertarPlanAlquiler(fechaInicio, matricula);
    }
    
    private Usuario buscarUsuario(String nombreUsuario){
        
        return this.usuarios.get(nombreUsuario);
    }
    
    private boolean existeUsuario(String nombreUsuario){
        
        return this.usuarios.containsKey(nombreUsuario);
    }
    
    private boolean existeVehiculo(String matricula){
        
        for(Map.Entry<String, Usuario> usuario : usuarios.entrySet()){
            
            if(usuario.getValue().tieneVehiculo(matricula))
                
                return true;
            
        }
        
        return false;
    }
}