/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.principal;

import com.bitlab.clienteHilo.ClienteHilo;
import com.bitlab.correo.Correo;
import com.bitlab.dao.EmpleadosDAO;
import com.bitlab.datos.correo.DatosCorreo;
import com.bitlab.encriptador.Encriptador;
import com.bitlab.entidades.Empleado;
import com.bitlab.utilidades.FuncionesPricipales;
import com.bitlab.utilidades.Validaciones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
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
