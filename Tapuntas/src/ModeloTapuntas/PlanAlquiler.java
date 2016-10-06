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

/**
 *
 * @author javi
 */
class PlanAlquiler{
    
    private boolean visible = false;
    private GregorianCalendar primerDiaAlquiler;
    private GregorianCalendar ultimoDiaAlquiler;
    private double costeAlquilerAlDia = 1.0;
    private String ciudadRecogida;
    private Vehiculo vehiculo;
    
    PlanAlquiler(Vehiculo unVehiculo, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, String ciudadRecogida){
        
        this.vehiculo = unVehiculo;
        this.primerDiaAlquiler = fechaInicio;
        this.ultimoDiaAlquiler = fechaFin;
        this.ciudadRecogida = ciudadRecogida;
    }
    
    GregorianCalendar getFechaInicio(){
        
        return this.primerDiaAlquiler;
    }
    
    GregorianCalendar getFechaFin(){
        
        return this.ultimoDiaAlquiler;
    }
    
    double getCosteDiario(){
        
        return this.costeAlquilerAlDia;
    }
    
    String getCiudadRecogida(){
        
        return this.ciudadRecogida;
    }
    
    String getMatriculaVehiculo(){
        
        if(this.vehiculo.equals(null))
            
            return "-";
        
        else
            
            return this.vehiculo.obtenerMatricula();
    }
    
    String getFechaInicioString(){
        
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(this.primerDiaAlquiler.getTime());
    }
    
    String getFechaFinString(){
        
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(ultimoDiaAlquiler.getTime());
    }
    
    void setFechaInicio(GregorianCalendar fechaInicio){
        
        this.primerDiaAlquiler = fechaInicio;
    }
    
    void setFechaFin(GregorianCalendar fechaFin){
        
        this.ultimoDiaAlquiler = fechaFin;
    }
    
    void setCosteDiario(double costeDiario){
        
        this.costeAlquilerAlDia = costeDiario;
    }
    
    void setCiudadRecogida(String ciudad){
        
        this.ciudadRecogida = ciudad;
    }
    
    void eliminarVehiculo(){
        
        this.vehiculo = null;
    }
    
    void modificarVisibilidad(boolean visible){
        
        this.visible = visible;
    }
    
    ArrayList<String> obtenerDatosPlanAlquiler(){
        
        ArrayList<String> datosPlan = new ArrayList<String>();
        datosPlan.add(this.vehiculo.obtenerMatricula());
        datosPlan.add(this.getFechaInicioString());
        datosPlan.add(this.getFechaFinString());
        datosPlan.add(Double.toString(this.costeAlquilerAlDia));
        datosPlan.add(ciudadRecogida);
        
        return datosPlan;
    }
    
    boolean vigente(){
        
        GregorianCalendar ahora = new GregorianCalendar();
        
        return ahora.before(this.ultimoDiaAlquiler);
    }
    
    boolean visible(){
        
        return visible;
    }
}