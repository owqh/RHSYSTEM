/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.clienteHilo;

import com.bitlab.dao.UsuarioDAO;
import com.bitlab.gestiones.Gestiones;
import com.bitlab.utilidades.FuncionesPricipales;
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
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    
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
        FuncionesPricipales funciones = new FuncionesPricipales();
        try {
            salida = new PrintWriter(socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            salida.println("Bienvenido/a Al sistema su direccion de acceso es: " + socketCliente.getInetAddress()+"\n\r");
            

            boolean banderaOpciones = true;
            //Bienvenida del usuario y inicio de sesion
            salida.println("****************  Sistema de gestion de recusos humanos *********************");
            salida.println("Bienvenido/a al sistema \n\r");
            salida.println("Identificacion de acceso al sistema. \n\r");
            String email = validar.validarCorreo(socketCliente,"Por favor ingrese su correo electronico: \n\r");
            salida.println("Porfavor ingrese su contrasena: \n\r");
            String pass = entrada.readLine();
            funciones.login(socketCliente,email, pass);

            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
