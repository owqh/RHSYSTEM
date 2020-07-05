/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.clienteHilo;

import com.bitlab.dao.EmpleadosDAO;
import com.bitlab.dao.TipoUsuarioDAO;

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
                                    
                                    usuarioDAO.getTodosLosDatos();
                                    byte user = 0;
                                    for (Usuario u : usuarioDAO.getTodosLosDatos()) {
                                        user+=1;
                                    }
                                    int id_U = user+1;
                                    
                                    String nombre = validar.validarString( socketCliente, "Ingrese el nombre usuario");
                                    String apellido = validar.validarString( socketCliente, "Ingrese apellido del nuevo usuario");
                                    String correo = validar.validarCorreo(socketCliente, "Ingrese correo del nuevo usuario");
                                    salida.println("Ingrese contraseña del nuevo usuario");
                                    String contraseña = entrada.readLine();
                                    salida.println("Ingrese tipo de usuario");
                                    
                                    TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
                                    salida.println(tipoUsuarioDAO.getTodosLosDatos());
                                    int tipoID = validar.validarByte(socketCliente, "Digite el numero correspondiente al nivel de acceso : ");
                                    
                                    Usuario u = new Usuario(id_U, correo, nombre, apellido, contraseña, tipoID);
                                    
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
    
}
