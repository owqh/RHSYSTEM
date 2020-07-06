/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import com.bitlab.gestiones.Gestiones;
import static com.bitlab.gestiones.Gestiones.salida;
import com.bitlab.principal.Proyecto4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Oscar
 */
public class Menu {
     PrintWriter salida = null;
     BufferedReader entrada = null;
     private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Proyecto4.class);
     
    //Menu para el administrador  
    public  void menuAdmin(Socket socketCliente, String nombre) throws IOException{
       logger.debug("Iniciando menu de administrador: "+nombre);
       salida = new PrintWriter(socketCliente.getOutputStream(), true);
       entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
       //Bienvenida al menu
       salida.println(" ");
       salida.println("Bienvenido Administrador "+nombre);
       try {
            boolean banderaOpciones = true;
            while (banderaOpciones) {
                salida.println(" _______________________________________________________\n\r");
                salida.println(" |*|            Menu de Administrador                  ||\n\r");
                salida.println(" |*|***************************************************||\n\r");
                salida.println(" |*| 1. Seleccionar gestion de empleados               ||\n\r");
                salida.println(" |*| 2. Gestion de roles                               ||\n\r");
                salida.println(" |*| 3. Gestion de estado de empleados                 ||\n\r");
                salida.println(" |*| 4. Gestion de usuarios.                           ||\n\r");
                salida.println(" |*| 5. salir                                          ||\n\r");
                salida.println(" |*| --------------------------------------------------||\n\r");
                String opcionCliente = ""; 

                if ((opcionCliente = entrada.readLine()) != null) {
                    
                    switch (opcionCliente) {
                        case "1":
                            //Gestionando empleados en la BD.
                            Gestiones.gestionandoEmpleados(socketCliente);
                            break;
                            
                        case "2":
                            //Gestionando roles de usuario.
                            
                            break;
                        case "3":
                            //Gestionando estado de los empleados
                            Gestiones.gestionDeEstadoEmpleados(socketCliente);
                            break;
                            
                        case "4":
                            //Gestionando los datos de los usuarios. 
                            Gestiones.gestionUsuarios(socketCliente);
                            break;
                            
                        case "5":
                            salida.print("saliendo del sistema");
                            banderaOpciones = false;
                            break;
                            
                        case "apagar":
                            
                            salida.println("esta apagando el servidor \n\r");

                            banderaOpciones = false;
                            break;
                            
                        default:
                            salida.println("comando desconocido vuelva a escribirlo \n\r");
                    }
                }
                Thread.sleep(1000);
            }
            entrada.close();
            salida.close();
            socketCliente.close();
            
       } catch (InterruptedException ex) {
             Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
             Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
       }   
    }
    
    //Menu para Recursos humanos  
     public  void menuHR(Socket socketCliente, String nombre) throws IOException{
        logger.debug("Iniciando menu de Recursos humanos para: "+nombre);
        salida = new PrintWriter(socketCliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
         //Bienvenida al menu
        salida.println(" ");
        salida.println("Bienvenido: "+nombre);
        Gestiones.gestionandoEmpleados(socketCliente);
        
    }
    
     //Menu para Otros usuarios aun no definidos
     public  void otro(Socket socketCliente, String nombre) throws IOException{
        logger.debug("Iniciando menu de Otros para: "+nombre); 
        salida = new PrintWriter(socketCliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
         System.out.println("Este es el menu para otro tipo de usuario diferente a admin o Recursos humanos");
    }
}
