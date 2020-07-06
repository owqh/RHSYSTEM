/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import com.bitlab.principal.Proyecto4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
       
       salida.println(" ");
       salida.println("Bienvenido Administrador "+nombre);
       salida.println("Menu de Administrador");
    }
    
    //Menu para Recursos humanos  
     public  void menuHR(Socket socketCliente, String nombre) throws IOException{
        logger.debug("Iniciando menu de Recursos humanos para: "+nombre);
        salida = new PrintWriter(socketCliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        
        salida.println(" ");
        salida.println("Bienvenido: "+nombre);
        salida.println("Menu de Recursos Humanos");
    }
    
     //Menu para Otros usuarios aun no definidos
     public  void otro(Socket socketCliente, String nombre) throws IOException{
        logger.debug("Iniciando menu de Otros para: "+nombre); 
        salida = new PrintWriter(socketCliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
         System.out.println("Este es el menu para otro tipo de usuario diferente a admin o Recursos humanos");
    }
}
