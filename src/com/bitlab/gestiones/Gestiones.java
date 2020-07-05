/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.gestiones;

import com.bitlab.dao.EstadoEmpleadoDAO;
import com.bitlab.dao.TipoUsuarioDAO;
import com.bitlab.dao.UsuarioDAO;
import com.bitlab.entidades.Empleado;
import com.bitlab.entidades.EstadoEmpleado;
import com.bitlab.entidades.Usuario;
import com.bitlab.utilidades.Validaciones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Manuel@Ramos
 */
public class Gestiones {
    public static PrintWriter salida = null;
    public static BufferedReader entrada = null;
    
    public static void gestionUsuarios(Socket socketCliente) throws IOException{
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Validaciones validar = new Validaciones();
        
        boolean bandera = true;
        
        try {
            //Habilitando motores de entrada y salida de escritura.
            salida = new PrintWriter(socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            do{
                //Gestionando los datos de los usuarios
                salida.println(" _______________________________________________\n\r");
                salida.println(" |         Tabla gestion de usuarios           |\n\r");
                salida.println(" |*********************************************|\n\r");
                salida.println(" |*| 1. Ver todos los usuarios               |*|\n\r");
                salida.println(" |*| 2. Ver datos de un usuario              |*|\n\r");
                salida.println(" |*| 3. Eliminar usuario                     |*|\n\r");
                salida.println(" |*| 4. Actualizar usuario                   |*|\n\r");
                salida.println(" |*| 5. Agregar usuario                      |*|\n\r");
                salida.println(" |*| 6. Salir                                |*|\n\r");
                salida.println(" |*|_________________________________________|*|\n\r");
                
                String seleccion = entrada.readLine();
                int idUsuario = 0;
                
                switch(seleccion){
                    //Seleccionando todos los usuarios existentes.
                    case "1":
                        salida.println(usuarioDAO.getTodosLosDatos());
                        break;
                        
                    case "2":
                        //Seleccionando un solo usuario por su id
                        idUsuario = validar.validarInt(socketCliente, "Ingrese el ID del usuario a verificar");
                        salida.println(usuarioDAO.getDatosPorID(idUsuario));
                        break;
                        
                    case "3":
                        //Eliminando un usuario.
                        idUsuario = validar.validarInt(socketCliente, "Ingrese el ID del usuario a eliminar");
                        Usuario u = usuarioDAO.getDatosPorID(idUsuario);
                        salida.println("Se eliminara el usuario: "+u.getNombre_usuario()+" "+u.getApellido_usuario()+
                                ", email_acceso: "+u.getAcceso_usuario());
                        usuarioDAO.EliminarDatos(u);
                        break;
                        
                    case "4":
                        //Actualizando datos de un usuario.
                        idUsuario = validar.validarInt(socketCliente, "Ingrese ID del usuario a actualizar");
                        Usuario u2 = usuarioDAO.getDatosPorID(idUsuario);
                        salida.println("Nombre actual de usuario: "+u2.getNombre_usuario());
                        String nuevoNombre = validar.validarString( socketCliente, "Ingrese el nombre que lo reemplazara: ");
                        salida.println("Appellido actual de usuario: "+u2.getApellido_usuario());
                        String nuevoApellido = validar.validarString(socketCliente, "Ingrese el Apellido que lo reemplazara: ");
                        salida.println("Acceso actual de usuario: "+u2.getAcceso_usuario());
                        String nuevoacceso = validar.validarCorreo(socketCliente, "Ingrese el Email de acceso que lo reemplazara: ");
                        salida.println("Contrasena actual: "+u2.getContra_usuario());
                        salida.println("Ingrese una nueva contrasena:");
                        String nuevacontrasena = entrada.readLine();
                        
                        u2.setAcceso_usuario(nuevoacceso);
                        u2.setNombre_usuario(nuevoNombre);
                        u2.setApellido_usuario(nuevoApellido);
                        u2.setApellido_usuario(nuevacontrasena);
                        
                        usuarioDAO.ActualizarDatos(u2);
                        salida.println("Usuario Actualizado!\nNombre nuevo: "+u2.getNombre_usuario()+"\n\rApellido nuevo: "+
                                u2.getApellido_usuario()+"\n\r Email_Acceso: "+u2.getAcceso_usuario()+"\r\n Contraseña nueva: ");
                        break;
                        
                    case "5":
                        //Agregando nuevo usuario a la BD
                        usuarioDAO.getTodosLosDatos();
                        byte user = 0;
                        for (Usuario us : usuarioDAO.getTodosLosDatos()) {
                            user++;
                        }
                        int id_U = user++;
                        
                        String nombre = validar.validarString( socketCliente, "Ingrese el nombre usuario");
                        String apellido = validar.validarString( socketCliente, "Ingrese apellido del nuevo usuario");
                        String correo = validar.validarCorreo(socketCliente, "Ingrese correo del nuevo usuario");
                        salida.println("Ingrese contraseña del nuevo usuario");
                        String contraseña = entrada.readLine();
                        salida.println("Ingrese tipo de usuario");
                        
                        TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
                        salida.println(tipoUsuarioDAO.getTodosLosDatos());
                        int tipoID = validar.validarByte(socketCliente, "Digite el numero correspondiente al nivel de acceso : ");
                        
                        Usuario u3 = new Usuario(id_U, correo, nombre, apellido, contraseña, tipoID);
                        usuarioDAO.insertDatos(u3);
                        
                        salida.printf("ID: %2d Email: %s Nombre: %s Apellido %s Contraseña %s Tipo ID: %2d",
                                u3.getId_usuario(),u3.getAcceso_usuario(),u3.getNombre_usuario(),u3.getApellido_usuario(),
                                u3.getContra_usuario(),u3.getTipo_id_fk());
                        break;
                        
                    case "6":
                        salida.println("Saliendo de Usuarios...");
                        bandera = false;
                        
                        break;
                    default:
                        salida.println("Seleccione una opcion valida");
                }
                
            }while(bandera);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gestiones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Gestiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void gestionDeRoles(){
        
//                                    //Gestionando roles de usuario.
//                            salida.println(" _______________________________________________\n\r");
//                            salida.println(" |°   Tabla de gestion de roles de usuario    °|\n\r");
//                            salida.println(" |°|¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|°|\n\r");
//                            salida.println(" |°| 1. Ver Roles de usuarios actuales       |°|\n\r");
//                            salida.println(" |°| 2. Cambiar rol de un usuario            |°|\n\r");
//                            salida.println(" |°| 3. Agregar rol de un usuario            |°|\n\r");
//                            salida.println(" |°| 4. Eliminar rol de un usuario           |°|\n\r");
//                            salida.println(" |°| 3. Salir                                |°|\n\r");
//                            salida.println(" |°|_________________________________________|°|\n\r");
//                            salida.println(" ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n\r");


    }
    
    public static void gestionDeEstadoEmpleados(Socket socketCliente) throws IOException, SQLException{
        
        EstadoEmpleadoDAO estadoEmpleadoDAO = new EstadoEmpleadoDAO();
        Empleado empleadosEntidad = new Empleado();
        Validaciones validar = new Validaciones();
        
        //Habilitando motores de entrada y salida de escritura.
        PrintWriter salida = null;
        BufferedReader entrada = null;
        salida = new PrintWriter(socketCliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        
        boolean bandera = true;
        
        int idUsuario = 0;
        try {
            do {
                
                //Gestionando estado de los empleados
//                            salida.println(" _______________________________________________\n\r");
//                            salida.println(" |°       Tabla estados de empleados          °|\n\r");
//                            salida.println(" |°|¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|°|\n\r");
//                            salida.println(" |°| 1. Ver todos los estados de empleados   |°|\n\r");
//                            salida.println(" |°| 2. Ver estado actual de un empleado     |°|\n\r");
//                            salida.println(" |°| 3. modificar estado de un empleado      |°|\n\r");
//                            salida.println(" |°| 4. Agregar un estado de empleado        |°|\n\r");
//                            salida.println(" |°| 5. Eliminar un estado de empleado       |°|\n\r");
//                            salida.println(" |°| 6. Salir                                |°|\n\r");
//                            salida.println(" |°|_________________________________________|°|\n\r");
//                            salida.println(" ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n\r");

String seleccion = entrada.readLine();
switch(seleccion){
    case "1":
        //ver estado laboral de todos los empleados.
        salida.println(estadoEmpleadoDAO.getTodosLosDatos());
        break;
        
    case "2":
        //Ver estado actual de un solo empleado.
        EstadoEmpleado estadoE = new EstadoEmpleado();
        
        idUsuario = validar.validarInt(socketCliente, "Ingrese el ID del empleado");
        estadoEmpleadoDAO.getDatosPorID(idUsuario);
        salida.println("Empleado: "+empleadosEntidad.getNombre_empleado()+" "+ estadoE.getNombreEstadoEmpleado());
        break;
        
    case "3":
        //Actualizar estado de un empleado
        idUsuario = validar.validarInt(socketCliente, "Ingrese el ID del empleado");
        salida.println(estadoEmpleadoDAO.getDatosPorID(idUsuario));
        String nuevoEstado = validar.validarString(socketCliente, "Digite el nuevo estado de empleado");
        EstadoEmpleado est = estadoEmpleadoDAO.getDatosPorID(idUsuario);
        
        est.setNombreEstadoEmpleado(nuevoEstado);
        estadoEmpleadoDAO.ActualizarDatos(est);
        break;
    case "4":
        salida.println("Saliendo de tabla de estados");
        break;
        
    default:
        salida.println("Ingrese una opcion valida.");
}

            } while (bandera);
            
        } catch (IOException ex) {
            Logger.getLogger(Gestiones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gestiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void gestionandoEmpleados(){
        
//Gestionando empleados en la BD.
//                            salida.println(" _______________________________________________\n\r");
//                            salida.println(" |°       Tabla gestion de empleados          °|\n\r");
//                            salida.println(" |°|¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|°|\n\r");
//                            salida.println(" |°| 1. Ver todos los empleados actuales     |°|\n\r");
//                            salida.println(" |°| 2. Ver datos de un empleado             |°|\n\r");
//                            salida.println(" |°| 3. Agregar empleado nuevo               |°|\n\r");
//                            salida.println(" |°| 4. Modicicar datos de un empleado       |°|\n\r");
//                            salida.println(" |°| 5. Desactivar un empleado por despido   |°|\n\r");
//                            salida.println(" |°| 6. Asignar departamento a un empleado   |°|\n\r");
//                            salida.println(" |°| 7. Asignar jefatura a un empleado       |°|\n\r");
//                            salida.println(" |°| 8. Actualizar salario de un empleado    |°|\n\r");
//                            salida.println(" |°| 9. Ver pagos generados del empleado     |°|\n\r");
//                            salida.println(" |°| 10.Eliminar un empleados                |°|\n\r");
//                            salida.println(" |°| 11.Generar pagos en planilla            |°|\n\r");
//                            salida.println(" |°| 12.Salir                                |°|\n\r");
//                            salida.println(" |°|_________________________________________|°|\n\r");
//                            salida.println(" ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n\r");

    }
}
