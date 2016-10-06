/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IUTapuntas;

import ModeloTapuntas.Tapuntas;
import ModeloTapuntas.TipoTransaccion;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author aanaya
 */
public class pruebaTapuntas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
     
        // Obtener la única instancia de la clase BuenProvecho (patrón sigleton)
        Tapuntas aViajar = Tapuntas.getInstance(); 
        
        try{
            
            aViajar.altaRegistro("javiIncompleto", "claveJavi", "javiIncompleto@tapuntas.es");
            aViajar.altaRegistro("javiCompleto", "claveJavi", "javiCompleto@tapuntas.es");
            aViajar.introducirPerfil("javiCompleto", "Javi", "689789411", "Soy el creador de esto.", TipoTransaccion.Transferencia);
            aViajar.añadirVehiculo("javiCompleto", "0000ÑÑÑ", "Ford", "Mustang Cobra", "Rojo", 4, "Deportivo", "Alto");
            aViajar.definirPlanAlquiler("javiCompleto", "0000ÑÑÑ", new GregorianCalendar(2016, 5, 22), new GregorianCalendar(2016, 5, 24), "Granada");
            
        }catch(Exception e){ // captura de la excepción
            System.err.println("se ha producido la siguiente excepcion: "+ e);
        }
        
        // Definir la variable que nos permite leer String desde teclado
        final Scanner in = new Scanner(System.in);
        int opcion = 0; 
        do{
            try{ // tratamiento de las excepciones. Bloque try en el que se puede producir una excepción y la capturamos
		 
                 //Terminar de diseñar el menú (usando System.out.println(...)) con las opciones que faltan
		 // Podéis hacer vuestros propios diseños de interfaz, esta es la interfaz mínima que tenéis que entregar
                System.out.println("\n\n*********************************** MENU ***********************************\n" +
                                       "GESTIÓN DE USUARIOS   \n" +
                                     "\t10. Nuevo Usuario \n" +
                                     "\t11. Consultar usuarios del sistema \n" +
                                     "\t12. Incluir Perfil de Usuario \n" +
                                     "\t13. Consultar Perfil de un Usuario \n");	
                                 
                System.out.println("GESTIÓN DE VEHICULOS  \n" +                             
                                    "\t20. Nuevo vehículo \n" +
                                    "\t21. Consultar vehículos de un usuario \n" +
                                    "\t22. Eliminar vehículo\n");
                
                System.out.println("GESTIÓN DE PLANES DE ALQUILER  \n" +
                                    "\t30. Definir nuevo plan de alquiler \n" +
                                    "\t31. Consultar mis planes de alquiler\n" +
                                    "\t32. Ofertar un plan de alquiler \n");
                
                System.out.println("\n**********************************************************************");
                		         
                System.out.println("\t0. TERMINAR");
		System.out.println("\n**********************************************************************");
                 
                // Lectura de un int, para darle valor a opcion.
                opcion = Integer.parseInt(in.nextLine()); 
                
                // Estructura switch con todas las opciones de menú. Algunos de ellos ya lo tenéis hecho
                // Tenéis que terminar las opciones que están incompletas y las que no están hechas
                switch(opcion){
                    case 10: //incluir un nuevo usuario en el sistema 
                                            
                        System.out.print("Nombre de Usuario: ");
                        String nombreUsuario = in.nextLine();
                                       
                        System.out.print("Clave: ");
                        String claveUsuario = in.nextLine();
                        
                        System.out.print("Dirección de correo: ");
                        String correoUsuario = in.nextLine();
                        
                        aViajar.altaRegistro(nombreUsuario, claveUsuario, correoUsuario);                                             
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                        
                    break;  
                    
                    case 11:/*Ver usuarios del sistema */
                        
                        Set<String> usuarios = aViajar.listaUsuarios();
                        Iterator iter = usuarios.iterator();
                        System.out.print("Los usuarios registrados actualmente son: \n\n");
                        
                        while(iter.hasNext()){
                            
                            System.out.println(iter.next());
                        }
                                                                     
                    break;
                    
                    case 12:/*Incluir Perfil */
                        
                        System.out.print("Nombre de usuario cuyo perfil desea introducir: ");
                        String perfilIntroducir = in.nextLine();
                        
                        System.out.print("Nombre de la persona: ");
                        String nombrePerfil = in.nextLine();
                        
                        System.out.print("Número de teléfono: ");
                        String telefonoPerfil = in.nextLine();
                        
                        System.out.print("Descripción personal: ");
                        String descripcionPerfil = in.nextLine();
                        
                        System.out.print("Preferencia de cobro: ");
                        String cobroPerfil = in.nextLine();
                        
                        aViajar.introducirPerfil(perfilIntroducir, nombrePerfil, telefonoPerfil, descripcionPerfil, TipoTransaccion.valueOf(cobroPerfil));
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                        
                    break;
                    case 13:/*Consultar perfil */
                        
                        System.out.print("Nombre de usuario cuyo perfil desea consultar: ");
                        String perfilConsultar = in.nextLine();
                        
                        ArrayList<String> infoPerfil = aViajar.consultarPerfil(perfilConsultar);
                        
                        System.out.print("La información del usuario es:\n\n");
                        System.out.print("Nombre: " + infoPerfil.get(0) + "\n");
                        System.out.print("Teléfono: " + infoPerfil.get(1) + "\n");
                        System.out.print("Descripción personal: " + infoPerfil.get(2) + "\n");
                            
                                                                   
                    break;
                
                    case 20: /*Nuevo vehículo */
                        
                        System.out.print("Nombre de usuario para el que quiere introducir un vehículo: ");
                        String usuarioNuevoVehiculo = in.nextLine();
                        
                        System.out.print("Matrícula del vehículo: ");
                        String matriculaNuevo = in.nextLine();
                        
                        System.out.print("Marca del vehículo: ");
                        String marcaNuevo = in.nextLine();
                        
                        System.out.print("Modelo del vehículo: ");
                        String modeloNuevo = in.nextLine();
                        
                        System.out.print("Color del vehículo: ");
                        String colorNuevo = in.nextLine();
                        
                        System.out.print("Número de plazas del vehículo: ");
                        String plazasNuevo = in.nextLine();
                        
                        System.out.print("Categoría del vehículo: ");
                        String categoriaNuevo = in.nextLine();
                        
                        System.out.print("Confort del vehículo: ");
                        String confortNuevo = in.nextLine();
                        
                        aViajar.añadirVehiculo(usuarioNuevoVehiculo, matriculaNuevo, marcaNuevo, modeloNuevo, colorNuevo, Integer.valueOf(plazasNuevo), categoriaNuevo, confortNuevo);
                        System.out.print("++++++  Operación realizada con éxito ++++++"); 
                        
                    break;
                  
                    case 21: /* Consultar vehículos de un usuario  */
                        
                        System.out.print("Nombre de usuario cuyos vehículos quiere consultar: ");
                        String usuarioVehiculos = in.nextLine();
                        
                        ArrayList<ArrayList<String>> datosVehiculos = aViajar.obtenerVehiculos(usuarioVehiculos);
                        
                        System.out.print("Los vehículos del usuario son los siguientes:\n\n");
                        
                        for(ArrayList<String> datos : datosVehiculos){
                            
                            System.out.print("Matrícula: " + datos.get(0) + "\n");
                            System.out.print("Marca: " + datos.get(1) + "\n");
                            System.out.print("Modelo: " + datos.get(2) + "\n");
                            System.out.print("Color: " + datos.get(3) + "\n");
                            System.out.print("Número de plazas: " + datos.get(4) + "\n");
                            System.out.print("Categoría: " + datos.get(5) + "\n");
                            System.out.print("Confort: " + datos.get(6) + "\n\n");
                        }
                                                   
                    break;             
                  
                    case 22: /* Eliminar vehículo  */
                        
                        System.out.print("Nombre de usuario cuyo vehículo quiere eliminar: ");
                        String usuarioVehiculoEliminar = in.nextLine();
                        
                        System.out.print("Matrícula del vehículo a eliminar: ");
                        String vehiculoEliminar = in.nextLine();
                        
                        aViajar.eliminarVehiculoPropietario(usuarioVehiculoEliminar, vehiculoEliminar);
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                        
                    break;
  
                    case 30: /* Nuevo plan de alquiler */
                        
                        System.out.print("Nombre de usuario para el que quiere introducir un plan de alquiler: ");
                        String usuarioNuevoPlan = in.nextLine();
                        
                        System.out.print("Matrícula del vehículo que se usará en el plan de alquiler: ");
                        String vehiculoUsado = in.nextLine();
                        
                        System.out.print("Día de inicio del plan de alquiler: ");
                        int diaInicio = Integer.parseInt(in.nextLine());
                        
                        System.out.print("Mes de inicio del plan de alquiler: ");
                        int mesInicio = Integer.parseInt(in.nextLine());
                        
                        System.out.print("Año de inicio del plan de alquiler: ");
                        int anyoInicio = Integer.parseInt(in.nextLine());
                        
                        System.out.print("Día de fin del plan de alquiler: ");
                        int diaFin = Integer.parseInt(in.nextLine());
                        
                        System.out.print("Mes de fin del plan de alquiler: ");
                        int mesFin = Integer.parseInt(in.nextLine());
                        
                        System.out.print("Año de fin del plan de alquiler: ");
                        int anyoFin = Integer.parseInt(in.nextLine());
                        
                        System.out.print("Ciudad de recogida: ");
                        String ciudadRecogida = in.nextLine();
                        
                        GregorianCalendar fechaInicio = new GregorianCalendar(anyoInicio, mesInicio-1, diaInicio);
                        GregorianCalendar fechaFin = new GregorianCalendar(anyoFin, mesFin-1, diaFin);
                        
                        aViajar.definirPlanAlquiler(usuarioNuevoPlan, vehiculoUsado, fechaInicio, fechaFin, ciudadRecogida);
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                        
                    break;

                    case 31: /* Consultar planes de alquiler de un usuario */
                    
                        System.out.print("Nombre de usuario cuyos planes de alquiler quiere consultar: ");
                        String usuarioPlanes = in.nextLine();
                        
                        ArrayList<ArrayList<String>> datosPlanes = aViajar.obtenerPlanesAlquiler(usuarioPlanes);
                        
                        System.out.print("Los planes del usuario son los siguientes:\n\n");
                        
                        for(ArrayList<String> datos : datosPlanes){
                            
                            System.out.print("Matrícula del vehículo asociado: " + datos.get(0) + "\n");
                            System.out.print("Fecha de inicio: " + datos.get(1) + "\n");
                            System.out.print("Fecha de fin: " + datos.get(2) + "\n");
                            System.out.print("Coste diario: " + datos.get(3) + "\n");
                            System.out.print("Ciudad de recogida: " + datos.get(4) + "\n\n");
                        }
                        
                    break;

                    case 32: /* Ofertar un plan de alquiler */
                        
                        System.out.print("Nombre de usuario cuyo plan de alquiler quiere ofertar: ");
                        String usuarioPlan = in.nextLine();
                        
                        System.out.print("Día de inicio del plan de alquiler: ");
                        int diaInicioPlan = Integer.parseInt(in.nextLine());
                        
                        System.out.print("Mes de inicio del plan de alquiler: ");
                        int mesInicioPlan = Integer.parseInt(in.nextLine());
                        
                        System.out.print("Año de inicio del plan de alquiler: ");
                        int anyoInicioPlan = Integer.parseInt(in.nextLine());
                        
                        System.out.print("Matrícula del vehículo usado en el plan de alquiler: ");
                        String vehiculoUsadoPlan = in.nextLine();
                        
                        GregorianCalendar fechaInicioPlan = new GregorianCalendar(anyoInicioPlan, mesInicioPlan-1, diaInicioPlan);
                        
                        aViajar.ofertarPlanAlquiler(usuarioPlan, fechaInicioPlan, vehiculoUsadoPlan);
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                        
                    break;               

                    case 0: /* terminar */
                    break;                        
                                    
                    default:
                        System.out.println("Opción no válida");
                    break;
                }
//               
            }catch(Exception ex){ // captura de la excepción
                System.err.println("se ha producido la siguiente excepcion: "+ ex);
            } 
        }while(opcion !=0); 
        System.exit(0);
    }  

}