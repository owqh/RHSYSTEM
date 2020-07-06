/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.principal;

import com.bitlab.clienteHilo.ClienteHilo;
import java.io.IOException;
import java.net.ServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Oscar
 */
public class Proyecto4 {
    private static final Logger logger = LoggerFactory.getLogger(Proyecto4.class);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       String opcionCliente = "";
        //
        ServerSocket socketServidor = new ServerSocket(8888);
        boolean bandera = true;
        do {
            logger.debug("EL servidor espera conexion en el puerto 8888");
            System.out.println("El servidor espera conexion en el puerto 8888");
            new ClienteHilo(socketServidor.accept()).start();
            logger.debug("El cliente ha sido aceptado satisfactoriamente");

        } while (!"apagar".equals(opcionCliente));
        logger.debug("¡APAGANDO EL SERVIDOR!");
        socketServidor.close();

    }
    
   
    
}
