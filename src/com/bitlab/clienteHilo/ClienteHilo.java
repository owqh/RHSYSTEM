/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.clienteHilo;

import com.bitlab.dao.EmpleadosDAO;

import com.bitlab.dao.UsuarioDAO;
import com.bitlab.entidades.Empleado;
import com.bitlab.entidades.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.bitlab.utilidades.Validaciones;
import java.util.List;

/**
 *
 * @author carlosGodoy
 */
public class ClienteHilo extends Thread {
    
    private Socket socketCliente;
    EmpleadosDAO empDao = new EmpleadosDAO();
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
                salida.println(" ||------------------------------------------- ||\n\r");
                salida.println(" || 1. Seleccionar empleados                   ||\n\r");
                salida.println(" || 2. Gestion de roles                        ||\n\r");
                salida.println(" || 3. Gestion de estado de empleados          ||\n\r");
                salida.println(" || 4. Gestion de usuarios.                    ||\n\r");
                salida.println(" || 5. salir                                   ||\n\r");
                salida.println(" || apagar (Digite la palabra exactamente)     ||\n\r");
                salida.println(" || -------------------------------------------||\n\r");
                
                if ((opcionCliente = entrada.readLine()) != null) {
                    
                    switch (opcionCliente) {
                        case "1":
                            salida.println(empDao.getTodosLosDatos().toString());
                            
                            break;
                        case "2":
                            
                            salida.println(" ||------------------------------------------- ||\n\r");
                            salida.println(" || 1. Ver todos los empleados                 ||\n\r");
                            salida.println(" || 2. Ver datos de un empleado                ||\n\r");
                            salida.println(" || 3. Eliminar empleado                       ||\n\r");
                            salida.println(" || 4. Actualizar estado de empleado           ||\n\r");
                            salida.println(" || 5. salir                                   ||\n\r");
                            salida.println(" || -------------------------------------------||\n\r");
                            
                            break;
                        case "3":
                            
                            salida.println("Pendiente...");
                            
                            break;
                        case "4":
                            
                            boolean bandera = true;
                            
                            do{
                                salida.println(" ||------------------------------------------- ||\n\r");
                                salida.println(" || 1. Ver todos los usuarios                  ||\n\r");
                                salida.println(" || 2. Ver datos de un usuario                 ||\n\r");
                                salida.println(" || 3. Eliminar usuario                        ||\n\r");
                                salida.println(" || 4. Actualizar usuario                      ||\n\r");
                                salida.println(" || 5. Agregar usuario                         ||\n\r");
                                salida.println(" || 6. salir                                   ||\n\r");
                                salida.println(" || -------------------------------------------||\n\r");
                                
                                String seleccion = entrada.readLine();
                                String idUsuario = "";
                                
                                
                                if ("1".equals(seleccion)){
                                    salida.println(usuarioDAO.getTodosLosDatos());
                                    
                                }else if ("2".equals(seleccion)){
                                    salida.println("Ingrese el ID del usuario a verificar");
                                    idUsuario = entrada.readLine();
                                    salida.println(usuarioDAO.getDatosPorID(idUsuario).toString());
                                    
                                }else if("3".equals(seleccion)){
                                    salida.println("Ingrese el ID del usuario a eliminar");
                                    idUsuario = entrada.readLine();
                                    Usuario u = usuarioDAO.getDatosPorID(idUsuario);
                                    salida.println("Se eliminara el usuario: "+u.getNombre_usuario()+" "+u.getApellido_usuario()+
                                            ", email_acceso: "+u.getAcceso_usuario());
                                    
                                }else if ("4".equals(seleccion)) {
                                    salida.println("Ingrese ID del usuario a actualizar");
                                    idUsuario = entrada.readLine();
                                    Usuario u = usuarioDAO.getDatosPorID(idUsuario);
                                    salida.println("Nombre actual de usuario: "+u.getNombre_usuario());
                                    salida.println("Ingrese el nombre que lo reemplazara: ");
                                    String nuevoNombre = entrada.readLine();
                                    salida.println("Appellido actual de usuario: "+u.getApellido_usuario());
                                    salida.println("Ingrese el Apellido que lo reemplazara: ");
                                    String nuevoApellido = entrada.readLine();
                                    salida.println("Acceso actual de usuario: "+u.getAcceso_usuario());
                                    salida.println("Ingrese el Email de acceso que lo reemplazara: ");
                                    String nuevoacceso = entrada.readLine();
                                    
                                    u.setAcceso_usuario(nuevoacceso);
                                    u.setNombre_usuario(nuevoNombre);
                                    u.setApellido_usuario(nuevoApellido);
                                    usuarioDAO.ActualizarDatos(u);
                                    salida.println("Nombre usuario actualizado a: "+u.getNombre_usuario()+" "+
                                            u.getApellido_usuario()+", Email_Acceso: "+u.getAcceso_usuario());
                                    
                                }else if("5".equals(seleccion)){
                                    Usuario u = null;
                                    salida.println("Ingrese ID del nuevo usuario");
                                    int id_U = Integer.parseInt(entrada.readLine());
                                    salida.println("Ingrese nombre del nuevo usuario");
                                    String nombre = entrada.readLine();
                                    salida.println("Ingrese apellido del nuevo usuario");
                                    String apellido = entrada.readLine();
                                    salida.println("Ingrese correo del nuevo usuario");
                                    String correo = entrada.readLine();
                                    salida.println("Ingrese contraseña del nuevo usuario");
                                    String contraseña = entrada.readLine();
                                    salida.println("Ingrese Tipo_ID del nuevo usuario");
                                    int tipoID = Integer.parseInt(entrada.readLine());
                                    
                                    u = new Usuario(id_U, correo, nombre, apellido, contraseña, tipoID);
                                    usuarioDAO.insertDatos(u);
                                    salida.printf("ID: %2d Email: %s Nombre: %s Apellido %s Contraseña %s Tipo ID: %2d",
                                            u.getId_usuario(),u.getAcceso_usuario(),u.getNombre_usuario(),u.getApellido_usuario(),
                                            u.getContra_usuario(),u.getTipo_id_fk());
                                    
                                }else if("6".equals(seleccion)){
                                    salida.println("Saliendo de Usuarios...");
                                    bandera = false;
                                }else{
                                    salida.println("Seleccione una opcion valida");
                                }
                            }while(bandera);
                            
                            
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
            
        } catch (IOException | InterruptedException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
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
