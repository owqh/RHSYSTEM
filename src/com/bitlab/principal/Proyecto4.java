/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.principal;

import com.bitlab.correo.Correo;
import com.bitlab.dao.EmpleadosDAO;
import com.bitlab.datos.correo.DatosCorreo;
import com.bitlab.encriptador.Encriptador;
import com.bitlab.entidades.Empleado;
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
          //instancias
        Properties prop = new Properties();
        Encriptador encriptar = new Encriptador();
        String opcionCliente = "";

        try {
            ServerSocket socketServidor = new ServerSocket(8888);
            Socket socketCliente = null;
            PrintWriter salida = null;
            BufferedReader entrada = null;
            boolean bandera = true;
            do {
                logger.debug("EL servidor espera conexion en el puerto 8888");
                System.out.println("El servidor espera conexion en el puerto 8888");
                socketCliente = socketServidor.accept();
                logger.debug("El cliente ha sido aceptado satisfactoriamente");
                entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                salida = new PrintWriter(socketCliente.getOutputStream(), true);
                
                boolean banderaOpciones = true;
                salida.println("Bienvenido clliente\n\r ");

                while (banderaOpciones) {
                    salida.println(" **** ¡Servidor BitLab.-Echotech Elija la opcion que desee! **** \n\r");
                    salida.println(" ||------------------------------------------- ||\n\r");
                    salida.println(" || 1. Seleccionar empleados                   ||\n\r");
                    salida.println(" || 2.                                         ||\n\r");
                    salida.println(" || 3.                                         ||\n\r");
                    salida.println(" || 4.                                         ||\n\r");
                    salida.println(" || 5. salir                                   ||\n\r");
                    salida.println(" || apagar (Digite la palabra exactamente)     ||\n\r");
                    salida.println(" || -------------------------------------------||\n\r");

                    if ((opcionCliente = entrada.readLine()) != null) {

                        switch (opcionCliente) {
                            case "1":
                                salida.println("mostrando select");
                                EmpleadosDAO empDao = new EmpleadosDAO();
                                logger.debug("mostrando el select de la consulta \n\r");

                                List<Empleado> empleado = empDao.obtenerTodoDatos();
                                for (Empleado emp : empleado) {
                                    System.out.println(emp.toString());
                                    salida.println(emp.toString() + "\n\r");
                                }

                                break;
                            case "5":
                                salida.print("saliendo del sistema");
                                banderaOpciones = false;
                                break;
                            case "apagar":
                                logger.debug("se esta apagando el servidor");
                                salida.println("esta apagando el servidor \n\r");
                                logger.debug("el cliente ha apagado el servidor y el correo ha sido enviado al administrador");

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

            } while (!"apagar".equals(opcionCliente));
            logger.debug("¡APAGANDO EL SERVIDOR!");
            System.out.println(" el cliente: " + socketCliente.getInetAddress() + " esta apagando el servidor");
            //socketServidor y la instruccion close, hacen que el servidor se apague y finalice el proyecto.
            socketServidor.close();
        } catch (IOException | InterruptedException | ClassNotFoundException | SQLException ex) {
            java.util.logging.Logger.getLogger(Proyecto4.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
