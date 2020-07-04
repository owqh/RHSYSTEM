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
        //Cargando clases necesarias.
        Properties prop = new Properties();
        Encriptador encriptar = new Encriptador();
        Validaciones validar = new Validaciones();
        FuncionesPricipales funciones = new FuncionesPricipales();
        
        EmpleadosDAO empDao = new EmpleadosDAO();
        String opcionCliente = "";
        
        try {
            // Instanciar clases nos permiten obtener la informacion del usuario
            ServerSocket socketServidor = new ServerSocket(8888);
            Socket socketCliente = null;
            PrintWriter salida = null;
            BufferedReader entrada = null;
            boolean bandera = true;
            do {
                logger.debug("EL servidor espera conexion en el puerto 8888");
                socketCliente = socketServidor.accept();
                logger.debug("El cliente ha sido aceptado satisfactoriamente");
                entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                salida = new PrintWriter(socketCliente.getOutputStream(), true);
                
                //Bienvenida del usuario y inicio de sesion
                salida.println("****************  Sistema de gestion de recusos humanos *********************");
                salida.println("Bienvenido/a al sistema");
                salida.println("Identificacion de acceso al sistema.");
                String correo = validar.validarCorreo("Por favor ingrese su correo electronico: ");
                System.out.println("Revise su direccion de correo electronico e ingrese el codigo que se le envio.");
                
              // funciones.validarAcceso(nombre, correo);
                
                
                boolean banderaOpciones = true;
                salida.println("Bienvenido clliente\n\r ");
             
                
                while (banderaOpciones) {
                    salida.println(" **** ¡Servidor BitLab.-Echotech Elija la opcion que desee! **** \n\r");
                    salida.println(" ||------------------------------------------- ||\n\r");
                    salida.println(" || 1. Seleccionar empleados                   ||\n\r");
                    salida.println(" || 2. Gestion de roles                        ||\n\r");
                    salida.println(" || 3. Gestion de estados de empleados         ||\n\r");
                    salida.println(" || 4.                                         ||\n\r");
                    salida.println(" || 5. salir                                   ||\n\r");
                    salida.println(" || apagar (Digite la palabra exactamente)     ||\n\r");
                    salida.println(" || -------------------------------------------||\n\r");

                    if ((opcionCliente = entrada.readLine()) != null) {

                        switch (opcionCliente) {
                            case "1":
                                salida.println(empDao.getTodosLosDatos().toString());

                                break;
                            case "2":
                                salida.println(empDao.getTodosLosDatos().toString());

                                break;
                            case "3":
                                salida.println(empDao.getTodosLosDatos().toString());

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
    
     public static void menuPrincipal(String nombre){
        // Instanciar clases nos permiten obtener la informacion del usuario
        Scanner scanner = new Scanner(System.in);
  
        
        //Menu para personas con acceso
        
        System.out.println("Bienvenido/a "+nombre+" al menú del sistema");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("1) Sistema de ordenamiento de datos.");
        System.out.println("2) Sistema de alumnos academia de idiomas");
        System.out.println("3) Sistema generador de piramide con asteriscos");
        System.out.println("4) Salir del sistema. ");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Digite el numero de la accion que deseas realizar:");
        
         //Validando que se Seleccione una opcion correta del menu
        boolean flag;
        do {
            flag = false;
            try{
                //Recogiendo el numero que indico el usuario
                byte option = scanner.nextByte();
                //Switch validacion de inicio 
                switch (option) {
                    //Iniciamos el sistema de ordenamiento de datos
                    case 1:
                        System.out.println("---------------------------------------------------------------------------------");
                        System.out.println("Iniciando sistema de ordenamiento de datos");
                        System.out.println("---------------------------------------------------------------------------------");

                        break;
                    //Iniciamos el sistema de academia de idiomas
                    case 2: 
                        System.out.println("---------------------------------------------------------------------------------");
                        System.out.println("Iniciando sistema 'Academia de idiomas pepito'"); 
                        System.out.println("---------------------------------------------------------------------------------");

                          break;
                    //Iniciamos el sistema de piramide con asteriscos  
                    case 3: 
                        System.out.println("---------------------------------------------------------------------------------");
                        System.out.println("Iniciando sistema generador de piramides");
                        System.out.println("---------------------------------------------------------------------------------");   

                          break;
                    //Salir del sistema
                    case 4: 
                        System.out.println("---------------------------------------------------------------------------------");
                        System.out.println("Saliendo del sistema ...");
                        System.out.println("---------------------------------------------------------------------------------");
                        System.exit(0); 
                          break;
                    default:
                        System.out.println("No se ha seleccionado una opcion valida, intentalo de nuevo");
                        System.exit(0);
                }
            }catch(Exception e){
                System.out.println("Por favor digite un numero entero valido.");
                flag = true;
                scanner.nextLine(); //Si no ponia esta linea se me hace un loop infinito
            }
        }while(flag != false);
        
    }
    
}
