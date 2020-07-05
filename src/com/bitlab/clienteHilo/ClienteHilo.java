/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.clienteHilo;

import com.bitlab.gestiones.Gestiones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.bitlab.utilidades.Validaciones;

/**
 *
 * @author carlosGodoy
 */
public class ClienteHilo extends Thread {
    
    private Socket socketCliente;
    
    
    public ClienteHilo() {
    }
    
    public ClienteHilo(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }
    
    @Override
    public void run() {
        
        PrintWriter salida = null;
        BufferedReader entrada = null;
        String opcionCliente = "";
        Validaciones validar = new Validaciones();
        
        try {
            salida = new PrintWriter(socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            salida.println("bienvenido cliente " + socketCliente.getInetAddress());
            
            boolean banderaOpciones = true;
            salida.println("Bienvenido clliente\n\r ");
            
            //Bienvenida del usuario y inicio de sesion
            //salida.println("****************  Sistema de gestion de recusos humanos *********************");
            //salida.println("Bienvenido/a al sistema");
            //salida.println("Identificacion de acceso al sistema.");
            //String correo = validar.validarCorreo("Por favor ingrese su correo electronico: ");
            //System.out.println("Revise su direccion de correo electronico e ingrese el codigo que se le envio.");
            //
            //funciones.validarAcceso(nombre, correo);
            
            salida.println("Bienvenido clliente\n\r ");
            
            while (banderaOpciones) {
                salida.println(" **** ¡Servidor BitLab.-Echotech Elija la opcion que desee! **** \n\r");
                salida.println(" ||---------------------------------------------------||\n\r");
                salida.println(" || 1. Seleccionar gestion de empleados               ||\n\r");
                salida.println(" || 2. Gestion de roles                               ||\n\r");
                salida.println(" || 3. Gestion de estado de empleados                 ||\n\r");
                salida.println(" || 4. Gestion de usuarios.                           ||\n\r");
                salida.println(" || 5. salir                                          ||\n\r");
                salida.println(" || ---------------------------------------------------||\n\r");
                
                if ((opcionCliente = entrada.readLine()) != null) {
                    
                    switch (opcionCliente) {
                        case "1":
                            //Gestionando empleados en la BD.
                            
                            break;
                        case "2":
                            //Gestionando roles de usuario.
                            
                            break;
                        case "3":
                            //Gestionando estado de los usuarios
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
            
        } catch (IOException | InterruptedException  ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
