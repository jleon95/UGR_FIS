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

/**
 *
 * @author javi
 */
class Vehiculo{
    
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private int numeroPlazas;
    private String categoria;
    private String confort;
    private Map<String,PlanAlquiler> planesAlquiler = new HashMap();
    
    Vehiculo(String matricula, String marca, String modelo, String color, int numeroPlazas, String categoria, String confort){
        
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.numeroPlazas = numeroPlazas;
        this.categoria = categoria;
        this.confort = confort;
    }
    
    String getMarca(){
        
        return this.marca;
    }
    
    String getModelo(){
        
        return this.modelo;
    }
    
    String getColor(){
        
        return this.color;
    }
    
    int getNumeroPlazas(){
        
        return this.numeroPlazas;
    }
    
    String getCategoria(){
        
        return this.categoria;
    }
    
    String getConfort(){
        
        return this.confort;
    }
    
    void setMatricula(String matricula){
        
        this.matricula = matricula;
    }
    
    void setMarca(String marca){
        
        this.marca = marca;
    }
    
    void setModelo(String modelo){
        
        this.modelo = modelo;
    }
    
    void setColor(String color){
        
        this.color = color;
    }
    
    void setNumeroPlazas(int numeroPlazas){
        
        if(numeroPlazas > 1)
            
            this.numeroPlazas = numeroPlazas;
    }
    
    void setCategoria(String categoria){
        
        this.categoria = categoria;
    }
    
    void setConfort(String confort){
        
        this.confort = confort;
    }
    
    boolean comprobarEstadoAlquileres(){
        
        GregorianCalendar ahora = new GregorianCalendar();
        
        for(Map.Entry<String, PlanAlquiler> plan : this.planesAlquiler.entrySet())
        
            if(ahora.after(plan.getValue().getFechaInicio()) && ahora.before(plan.getValue().getFechaFin()))
                
                return true;
        
        return false;
    }
    
    void eliminarVehiculoAlquileres(){
        
        for(Map.Entry<String, PlanAlquiler> plan : this.planesAlquiler.entrySet())
            
            plan.getValue().eliminarVehiculo();
            
    }
    
    boolean estasDisponible(GregorianCalendar fechaInicio, GregorianCalendar fechaFin){
    
        for(Map.Entry<String, PlanAlquiler> plan : this.planesAlquiler.entrySet())
            
            if((plan.getValue().getFechaInicio().after(fechaInicio) && plan.getValue().getFechaInicio().before(fechaFin)) ||
                (plan.getValue().getFechaFin().after(fechaInicio) && plan.getValue().getFechaFin().before(fechaFin)) ||
               (plan.getValue().getFechaInicio().before(fechaInicio) && plan.getValue().getFechaFin().after(fechaFin)))
                
                return false;
        
        return true;
    }
    
    void incluirPlanAlquiler(PlanAlquiler miPlanAlquiler){
        
        String clave = miPlanAlquiler.getFechaInicio() + "~" + this.matricula;
        this.planesAlquiler.put(clave, miPlanAlquiler);
    }
    
    ArrayList<String> obtenerDatosVehiculo(){
        
        ArrayList<String> datos = new ArrayList<String>();
        datos.add(this.matricula);
        datos.add(this.marca);
        datos.add(this.modelo);
        datos.add(this.color);
        datos.add(Integer.toString(this.numeroPlazas));
        datos.add(this.categoria);
        datos.add(this.confort);
        
        return datos;
    }

    String obtenerMatricula(){
        
        return this.matricula;
    } 
}