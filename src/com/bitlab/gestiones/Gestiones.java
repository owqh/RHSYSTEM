/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.gestiones;

import com.bitlab.dao.EmpleadosDAO;
import com.bitlab.dao.EstadoEmpleadoDAO;
import com.bitlab.dao.TipoUsuarioDAO;
import com.bitlab.dao.UsuarioDAO;
import com.bitlab.entidades.Empleado;
import com.bitlab.entidades.EstadoEmpleado;
import com.bitlab.entidades.TipoUsuario;
import com.bitlab.entidades.Usuario;
import com.bitlab.utilidades.Validaciones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Manuel@Ramos
 */
public class Gestiones {
    public static PrintWriter salida = null;
    public static BufferedReader entrada = null;
    
    
    //Gestionando los datos de los usuarios
    public static void gestionUsuarios(Socket socketCliente) throws IOException{
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario u = new Usuario();
        Validaciones validar = new Validaciones();
        
        boolean bandera = true;
        
        try {
            //Habilitando motores de entrada y salida de escritura.
            salida = new PrintWriter(socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            do{
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
                        idUsuario = validar.validarInt(socketCliente, "Ingrese el ID del usuario a verificar : ");
                        salida.println("Usuario "+usuarioDAO.getDatosPorID(idUsuario));
                        break;
                        
                    case "3":
                        //Eliminando usuario.
                        idUsuario = validar.validarInt(socketCliente, "Ingrese el ID del usuario a eliminar : ");
                        u = usuarioDAO.getDatosPorID(idUsuario);
                        
                        salida.println("Se eliminara el usuario: "+u.getNombre_usuario()+" "+u.getApellido_usuario()+
                                ", email_acceso: "+u.getAcceso_usuario());
                        usuarioDAO.EliminarDatos(u.getId_usuario());
                        salida.println("Usuario eliminado");
                        bandera = true;
                        break;
                        
                    case "4":
                        //Actualizando datos de un usuario.
                        idUsuario = validar.validarInt(socketCliente, "Ingrese ID del usuario a actualizar : ");
                        Usuario u2 = usuarioDAO.getDatosPorID(idUsuario);
                        salida.println("Nombre actual de usuario: "+u2.getNombre_usuario());
                        String nuevoNombre = validar.validarString( socketCliente, "Ingrese el nombre que lo reemplazara: ");
                        salida.println("Appellido actual de usuario: "+u2.getApellido_usuario());
                        String nuevoApellido = validar.validarString(socketCliente, "Ingrese el Apellido que lo reemplazara: ");
                        salida.println("Acceso actual de usuario: "+u2.getAcceso_usuario());
                        String nuevoacceso = validar.validarCorreo(socketCliente, "Ingrese el Email de acceso que lo reemplazara: ");
                        salida.println("Contrasena actual: "+u2.getContra_usuario());
                        salida.println("Ingrese una nueva contrasena: ");
                        String nuevacontrasena = entrada.readLine();
                        
                        u2.setAcceso_usuario(nuevoacceso);
                        u2.setNombre_usuario(nuevoNombre);
                        u2.setApellido_usuario(nuevoApellido);
                        u2.setContra_usuario(nuevacontrasena);
                        
                        usuarioDAO.ActualizarDatos(u2);
                        salida.println("Usuario Actualizado!\n\rNombre nuevo: "+u2.getNombre_usuario()+"\n\rApellido nuevo: "+
                                u2.getApellido_usuario()+"\n\rEmail_Acceso: "+u2.getAcceso_usuario()+"\r\nContraseña nueva: "+u2.getContra_usuario());
                        break;
                        
                    case "5":
                        //Agregando nuevo usuario a la BD
                        usuarioDAO.getTodosLosDatos();
                        byte user = 1;
                        for (Usuario us : usuarioDAO.getTodosLosDatos()) {
                            user++;
                        }
                        int id_U = user++;
                        
                        String nombre = validar.validarString( socketCliente, "Ingrese el nombre usuario: ");
                        String apellido = validar.validarString( socketCliente, "Ingrese apellido del nuevo usuario: ");
                        String correo = validar.validarCorreo(socketCliente, "Ingrese correo del nuevo usuario: ");
                        salida.println("Ingrese contraseña del nuevo usuario: ");
                        String contraseña = entrada.readLine();
                        salida.println("Ingrese tipo de usuario: ");
                        
                        TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
                        salida.println(tipoUsuarioDAO.getTodosLosDatos());
                        int tipoID = validar.validarByte(socketCliente, "Digite el numero correspondiente al nivel de acceso : ");
                        
                        Usuario u3 = new Usuario(id_U, correo, nombre, apellido, contraseña, tipoID);
                        usuarioDAO.insertDatos(u3);
                        
                        salida.printf("Usuario nuevo agregado:\n\rID: %2d Email: %s, Nombre: %s, Apellido %s, Contrasena %s, Tipo ID: %2d",
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
    
public static void gestionDeRoles(Socket socketCliente) throws SQLException {
        TipoUsuarioDAO tipoDao = new TipoUsuarioDAO();
        TipoUsuario tipoUsuario = new TipoUsuario();
        Validaciones validar = new Validaciones();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario u = new Usuario();
        boolean bandera = true;
        int idUsuario = 0;
        try {
            //Habilitando motores de entrada y salida de escritura.
            salida = new PrintWriter(socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            do {
                //Gestionando roles de usuario.
                salida.println(" _______________________________________________\n\r");
                salida.println(" |*   Tabla de gestion de roles de usuario    *|\n\r");
                salida.println(" |*|*****************************************|*|\n\r");
                salida.println(" |*| 1. Ver Roles de usuarios actuales       |*|\n\r");
                salida.println(" |*| 2. Cambiar rol de un usuario            |*|\n\r");
                salida.println(" |*| 3. Agregar rol de un usuario            |*|\n\r");
                salida.println(" |*| 4. Eliminar rol de un usuario           |*|\n\r");
                salida.println(" |*| 5. actualizar estado empleado           |*|\n\r");
                salida.println(" |*| 6.salir                                 |*|\n\r");
                salida.println(" |*|_________________________________________|*|\n\r");
                String seleccion = entrada.readLine();
                switch (seleccion) {
                    case "1":
                        //ver estado laboral de todos los empleados.
                        salida.println("Cargando..");
                        salida.println(tipoDao.getTodosLosDatos());
                        break;
                    case "2":
                        //cambiar rol de usuario
                        salida.println("Cargando..");
                        salida.println(tipoDao.getTodosLosDatos());
                        idUsuario = validar.validarInt(socketCliente, " Ingrese el ID del Rol a cambiar");
                        salida.println(tipoDao.getDatosPorID(idUsuario));
                        TipoUsuario tp = tipoDao.getDatosPorID(idUsuario);
                        String nuevoNombre = validar.validarString(socketCliente, "Digite el nuevo rol");
                        tp.setNombreTipoUsuario(nuevoNombre);
                        tipoDao.ActualizarDatos(tp);
                        break;
                    case "3":
                        //agregar rol de usuario
                        salida.println("Cargando..");
                        byte user = 1;
                        for (TipoUsuario us : tipoDao.getTodosLosDatos()) {
                            user++;
                        }
                        int id_E = user++;
                        String nuevoRolUsuario = validar.validarString(socketCliente, "Ingrese nuevo Rol");
                        TipoUsuario nu = new TipoUsuario(id_E, nuevoRolUsuario);
                        tipoDao.insertDatos(nu);
                        salida.println("datos ingresados");
                        salida.println(tipoDao.getTodosLosDatos());
                        break;
                    case "4":
                        //Eliminar Rol de usuario
                        salida.println("Cargando..");
                        salida.println(tipoDao.getTodosLosDatos());
                        byte idE = validar.validarByte(socketCliente, "Ingrese el ID del Rol a eliminar : \n\r");
                        tipoUsuario = tipoDao.getDatosPorID(idE);
                        salida.println("Se eliminara el Rol: " + tipoUsuario.getNombreTipoUsuario());
                        tipoDao.EliminarDatos(idE);
                        salida.println("Rol  eliminado.");
                        salida.println(tipoDao.getTodosLosDatos());
                        break;
                    case "5":
                        // actualizar estado empleado
                        Empleado emp = new Empleado();
                        EmpleadosDAO empdao = new EmpleadosDAO();
                        EstadoEmpleado estEm = new EstadoEmpleado();
                        EstadoEmpleadoDAO estEmD = new EstadoEmpleadoDAO();
                        salida.println("seleccione el ID del empleado a modificar el estado \n\r");
                        salida.println(empdao.getTodosLosDatos());
                        byte idEm = validar.validarByte(socketCliente, "Ingrese el ID empleado: \n\r");
                        emp = empdao.getDatosPorID(idEm);
                        salida.println("seleccione el ID del estado a asignar");
                        salida.println(estEmD.getTodosLosDatos());
                        byte idEs = validar.validarByte(socketCliente, "Ingrese el ID del estado \n\r");
                        emp.setEstadoEmpleado_fk(idEs);
                        empdao.ActualizarDatos(emp);
                        break;
                    case "6":
                    case "7":
                        bandera = false;
                        break;
                    default:
                        salida.println("Ingrese una opcion valida. \n\r");
                }
            } while (bandera);
        } catch (IOException ex) {
            Logger.getLogger(Gestiones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gestiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Gestionando estado de los empleados
    public static void gestionDeEstadoEmpleados(Socket socketCliente) throws IOException, SQLException{
        
        EstadoEmpleadoDAO estadoEmpleadoDAO = new EstadoEmpleadoDAO();
        EstadoEmpleado estadoEntidad = new EstadoEmpleado();
        Validaciones validar = new Validaciones();
        
        boolean bandera = true;
        int idUsuario = 0;
        try {
            //Habilitando motores de entrada y salida de escritura.
            salida = new PrintWriter(socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            do {
                salida.println(" _______________________________________________\n\r");
                salida.println(" |*       Tabla estados de empleados          *|\n\r");
                salida.println(" |*|*****************************************|*|\n\r");
                salida.println(" |*| 1. Ver todos los estados de empleados   |*|\n\r");
                salida.println(" |*| 2. Ver estado actual de un empleado     |*|\n\r");
                salida.println(" |*| 3. modificar estado de un empleado      |*|\n\r");
                salida.println(" |*| 4. Agregar un estado de empleado        |*|\n\r");
                salida.println(" |*| 5. Eliminar un estado de empleado       |*|\n\r");
                salida.println(" |*| 6. Salir                                |*|\n\r");
                salida.println(" |*|_________________________________________|*|\n\r");
                
                String seleccion = entrada.readLine();
                switch(seleccion){
                    case "1":
                        //ver estado laboral de todos los empleados.
                        
                        idUsuario = validar.validarInt(socketCliente, "Ingrese el ID del empleado a verificar : \n\r");
                        salida.println("Empleado "+estadoEmpleadoDAO.getDatosPorID(idUsuario));
                        salida.println(estadoEmpleadoDAO.getTodosLosDatos());
                        break;
                        
                    case "2":
                        idUsuario = validar.validarInt(socketCliente, "Ingrese el ID del empleado a verificar : \n\r");
                        salida.println("Empleado "+estadoEmpleadoDAO.getDatosPorID(idUsuario));
                        break; 
                        
                    case "3":
                        //Ver estado actual de un solo empleado.
                        idUsuario = validar.validarInt(socketCliente, "Ingrese el ID del empleado a verificar : \n\r");
                        salida.println("Empleado "+estadoEmpleadoDAO.getDatosPorID(idUsuario));
                        
                        break;
                        
                    case "4":
                        //Actualizar estado de un empleado
                        idUsuario = validar.validarInt(socketCliente, "Ingrese el ID del empleado \n\r");
                        salida.println(estadoEmpleadoDAO.getDatosPorID(idUsuario));
                        String nuevoEstado = validar.validarString(socketCliente, "Digite el nuevo estado de empleado \n\r");
                        estadoEntidad.setNombreEstadoEmpleado(nuevoEstado);
                        estadoEmpleadoDAO.ActualizarDatos(estadoEntidad);
                        break;
                        
                    case "5":
                        //Agregar un estado de empleado.
                        byte user = 1;
                        for (EstadoEmpleado us : estadoEmpleadoDAO.getTodosLosDatos()) {
                            user++;
                        }
                        int id_E = user++;
                        
                        String nuevoEstadoEmp = validar.validarString( socketCliente, "Ingrese descripción del nuevo estado: ");
                        EstadoEmpleado es = new EstadoEmpleado(id_E, nuevoEstadoEmp);
                        estadoEmpleadoDAO.insertDatos(es);
                        
                        salida.printf("Estado agregado:\n\rID: %2d\n\rEstado nuevo :%s",es.getId_EstadoEmpleado(),es.getNombreEstadoEmpleado());
                        break;
                        
                    case "6":
                        //Eliminar un estado de empleado.
                        byte id_Estado = validar.validarByte(socketCliente, "Ingrese el ID del estado empleado a eliminar : ");
                        estadoEntidad = estadoEmpleadoDAO.getDatosPorID(id_Estado);
                        salida.println("Se eliminara el estado: "+estadoEntidad.getNombreEstadoEmpleado()+"\n\r");
                        estadoEmpleadoDAO.EliminarDatos(estadoEntidad.getId_EstadoEmpleado());
                        salida.println("Estado eliminado. \n\r");
                        break;
                        
                    case "7":
                        salida.println("Saliendo de tabla estados de empleado... \n\r");
                        bandera = false;
                        break;
                        
                    default:
                        salida.println("Ingrese una opcion valida. \n\r");
                }
                
            } while (bandera);
            
        } catch (IOException ex) {
            Logger.getLogger(Gestiones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gestiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //Gestionando empleados en la BD.
    public static void gestionandoEmpleados(Socket socketCliente){
        EmpleadosDAO empleadosDao = new EmpleadosDAO();
        Empleado em = new Empleado();
        Validaciones validar = new Validaciones();
        
        boolean bandera = true;
        
        try {
            //Habilitando motores de entrada y salida de escritura.
            salida = new PrintWriter(socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            do {
                salida.println(" _______________________________________________\n\r");
                salida.println(" |*       Menu de recursos humanos            *|\n\r");
                salida.println(" |*|*****************************************|*|\n\r");
                salida.println(" |*| 1. Ver todos los empleados actuales     |*|\n\r");
                salida.println(" |*| 2. Ver datos de un empleado             |*|\n\r");
                salida.println(" |*| 3. Eliminar un empleados                |*|\n\r");
                salida.println(" |*| 4. Modificar datos de un empleado       |*|\n\r");
                salida.println(" |*| 5. Desactivar un empleado por despido   |*|\n\r");
                salida.println(" |*| 6. Contratación de empleados.           |*|\n\r");
                salida.println(" |*| 7.Salir                                 |*|\n\r");
                salida.println(" |*|_________________________________________|*|\n\r");
                
                String seleccion = entrada.readLine();
                int idEmpleado = 0;
                
                switch(seleccion){
                    
                    case "1":
                        //Seleccionando todos los empleados existentes.
                        salida.println(empleadosDao.getTodosLosDatos());
                        break;
                        
                    case "2":
                        
                        //Seleccionando datos de un solo empleado
                        idEmpleado = validar.validarInt(socketCliente, "Ingrese el ID del empleado a verificar : \n\r");
                        salida.println("Empleado "+empleadosDao.getDatosPorID(idEmpleado)+"\n\r");
                        break;
                        
                    case "3":
                        //Eliminando un empleado.
                        idEmpleado = validar.validarInt(socketCliente, "Ingrese el ID del empleado a eliminar : \n\r");
                        em = empleadosDao.getDatosPorID(idEmpleado);
                        
                        salida.println("Se eliminara el empleado: "+em.getNombre_empleado()+" "+em.getApellido_empleado()+
                                ", email_acceso: "+em.getCorreo_empleado()+"\n\r");
                        empleadosDao.EliminarDatos(em.getId_empleado());
                        salida.println("Empleado eliminado \n\r");
                        break;
                        
                    case "4":
                        //Actualizando datos de un usuario.
                        idEmpleado = validar.validarInt(socketCliente, "Ingrese ID del empleado a modificar : \n\r");
                        em = empleadosDao.getDatosPorID(idEmpleado);
                        salida.println("Nombre actual : "+em.getNombre_empleado());
                        String nuevoNombre = validar.validarString( socketCliente, "Ingrese el nombre nuevo : \n\r");
                        salida.println("Appellido actual : "+em.getApellido_empleado()+"\n\r");
                        String nuevoApellido = validar.validarString(socketCliente, "Ingrese el Apellido nuevo : \n\r");
                        salida.println("Correo actual: "+em.getCorreo_empleado());
                        String nuevoCorreo = validar.validarCorreo(socketCliente, "Ingrese el Email nuevo :\n\r ");
                        salida.println("Telefono : "+em.getTelefono_empleado());
                        int telefono = validar.validarInt(socketCliente, "Ingrese numero telefonico nuevo : \n\r");
                        salida.println("DUI actual : "+em.getDui_empleado());
                        int dui = validar.validarInt(socketCliente, "Ingrese nuevo numero DUI : ");
                        salida.println("Direccion actual : "+em.getDireccion_empleado());
                        String direccion = validar.validarString(socketCliente, "Ingrese la nueva direccion : \n\r");
                        salida.println("Fecha de nacimiento actual : "+em.getFecha_nac_empleado());
                        int fechaDia = validar.validarInt(socketCliente, "Ingrese dia de nacimiento  nuevo: \n\r");
                        int fechaMes = validar.validarInt(socketCliente, "Ingrese mes de nacimiento nuevo: \n\r");
                        int fechaAnio = validar.validarInt(socketCliente, "Ingrese anio de nacimiento nuevo: \n\r");
                        salida.println("Fecha de contrato actual : "+em.getFecha_contrat_empleado());
                        int fechaCtDia = validar.validarInt(socketCliente, "Ingrese dia de contratacion: \n\r");
                        int fechaCtMes = validar.validarInt(socketCliente, "Ingrese mes de contratacion: \n\r");
                        int fechaCtAnio = validar.validarInt(socketCliente, "Ingrese anio de contratacion: \n\r");
                        salida.println("SEX ID actual : "+em.getSexIDempleado_fk());
                        int sexId = validar.validarInt(socketCliente, "Ingrese nuevo SEX_ID : ");
                        salida.println("ID_Estado Empleado actual : "+em.getEstadoEmpleado_fk());
                        int  estadoEmID = validar.validarInt(socketCliente, "Ingrese nuevo ID_Estado_empleado : \n\r");
                        salida.println("ID_Estado Civil actual : "+em.getEstadoCivil_empleado_fk());
                        int idEstadoCivil = validar.validarInt(socketCliente, "Ingrese nuevo ID - Estado civil : \n\r");
                        salida.println("ID_Departamento actual : "+em.getDeparID_empleado_fk());
                        int departID = validar.validarInt(socketCliente, "Ingrese el nuevo ID departamento : \n\r");
                        salida.println("Cargo_ID actual : "+em.getEstadoCivil_empleado_fk());
                        int cargoID = validar.validarInt(socketCliente, "Ingrese nuevo Cargo_ID : \n\r");
                        
                        em.setNombre_empleado(nuevoNombre);
                        em.setApellido_empleado(nuevoApellido);
                        em.setCorreo_empleado(nuevoCorreo);
                        em.setTelefono_empleado(telefono);
                        em.setDui_empleado(dui);
                        em.setDireccion_empleado(direccion);
                        GregorianCalendar gc = new GregorianCalendar();
                        gc.set(GregorianCalendar.DATE,fechaDia);
                        gc.set(GregorianCalendar.MONTH,fechaMes);
                        gc.set(GregorianCalendar.YEAR,fechaAnio);
                        em.setFecha_nac_empleado(new Date(gc.getTimeInMillis()));
                        
                        GregorianCalendar gc2 = new GregorianCalendar();
                        gc2.set(GregorianCalendar.DATE,fechaCtDia);
                        gc2.set(GregorianCalendar.MONTH,fechaCtMes);
                        gc2.set(GregorianCalendar.YEAR,fechaCtAnio);
                        em.setFecha_contrat_empleado(new Date(gc.getTimeInMillis()));
                        em.setSexIDempleado_fk(sexId);
                        em.setEstadoEmpleado_fk(estadoEmID);
                        em.setEstadoCivil_empleado_fk(idEstadoCivil);
                        em.setDeparID_empleado_fk(departID);
                        em.setCargoID_empleado_fk(cargoID);
                        
                        empleadosDao.ActualizarDatos(em);
                        salida.println("Empleado Actualizado!\n\rNombre nuevo: "+em.getNombre_empleado()+"\n\rApellido nuevo: "+
                                em.getApellido_empleado()+"\n\rEmail: "+em.getCorreo_empleado()+"\n\rTelefono: "+em.getTelefono_empleado()+
                                "\r\nDUI : "+em.getDui_empleado()+"\n\rDireccion: "+em.getDireccion_empleado()+"\n\rFecha de nacimiento : "+
                                em.getFecha_nac_empleado()+"\n\rFecha de contratacion: "+em.getFecha_contrat_empleado()+"\n\rSex_ID : "+
                                em.getSexIDempleado_fk()+"\n\rID estado Empleado: "+em.getEstadoEmpleado_fk()+"\n\rEstado civil : "+
                                em.getEstadoCivil_empleado_fk()+"\n\rID_Departamento: "+em.getDeparID_empleado_fk()+"\n\rID_cargo :"+
                                em.getCargoID_empleado_fk());
                        break;
                        
                        
                    case "5":
                        //Desactivar un empleado por despido.
                        Empleado emp = new Empleado();
                        EmpleadosDAO empdao = new EmpleadosDAO();
                        EstadoEmpleado estEm = new EstadoEmpleado();
                        EstadoEmpleadoDAO estEmD = new EstadoEmpleadoDAO();
                        salida.println("seleccione el ID del empleado a modificar el estado \n\r");
                        salida.println(empdao.getTodosLosDatos());
                        byte idEm = validar.validarByte(socketCliente, "Ingrese el ID empleado: \n\r");
                        emp = empdao.getDatosPorID(idEm);
                        salida.println("seleccione el ID del estado a asignar \n\r");
                        salida.println(estEmD.getTodosLosDatos());
                        byte idEs = validar.validarByte(socketCliente, "Ingrese el ID del estado \n\r");
                        emp.setEstadoEmpleado_fk(idEs);
                        empdao.ActualizarDatos(emp);
                        
                        break;

                    case "6":
                        //Agregar empleado nuevo.
                        empleadosDao.getTodosLosDatos();
                        byte user = 1;
                        for (Empleado us : empleadosDao.getTodosLosDatos()) {
                            user++;
                        }
                        int id_e = user++;
                        
                        idEmpleado = validar.validarInt(socketCliente, "Ingrese ID del empleado : \n\r");
                        em = empleadosDao.getDatosPorID(idEmpleado);
                        String nombre = validar.validarString( socketCliente, "Ingrese el nombre : \n\r");
                        String apellido = validar.validarString(socketCliente, "Ingrese el Apellido : \n\r");
                        String correo = validar.validarCorreo(socketCliente, "Ingrese el Email : \n\r");
                        int telefono1 = validar.validarInt(socketCliente, "Ingrese numero telefonico : \n\r");
                        int duiI = validar.validarInt(socketCliente, "Ingrese nuevo numero DUI : \n\r");
                        String direccion1 = validar.validarString(socketCliente, "Ingrese direccion : \n\r");
                        int fechaDia1 = validar.validarInt(socketCliente, "Ingrese dia de nacimiento : \n\r");
                        int fechaMes1 = validar.validarInt(socketCliente, "Ingrese mes de nacimiento : \n\r");
                        int fechaAnio1 = validar.validarInt(socketCliente, "Ingrese anio de nacimiento : \n\r");
                        int fechaCtDia1 = validar.validarInt(socketCliente, "Ingrese dia de contratacion: \n\r");
                        int fechaCtMes1 = validar.validarInt(socketCliente, "Ingrese mes de contratacion: \n\r");
                        int fechaCtAnio1 = validar.validarInt(socketCliente, "Ingrese anio de contratacion: \n\r");
                        int sexId1 = validar.validarInt(socketCliente, "Ingrese nuevo SEX_ID : \n\r");
                        int estadoEmID1 = validar.validarInt(socketCliente, "Ingrese ID_Estado_empleado : \n\r");
                        int idEstadoCivil2 = validar.validarInt(socketCliente, "Ingrese ID - Estado civil : \n\r");
                        int departID1 = validar.validarInt(socketCliente, "Ingrese el ID departamento : \n\r");
                        int cargoID1 = validar.validarInt(socketCliente, "Ingrese Cargo_ID : \n\r");
                        
                        em.setId_empleado(id_e);
                        em.setNombre_empleado(nombre);
                        em.setApellido_empleado(apellido);
                        em.setCorreo_empleado(correo);
                        em.setTelefono_empleado(telefono1);
                        em.setDui_empleado(duiI);
                        em.setDireccion_empleado(direccion1);
                        
                        GregorianCalendar gc4 = new GregorianCalendar();
                        gc4.set(GregorianCalendar.DATE,fechaDia1);
                        gc4.set(GregorianCalendar.MONTH,fechaMes1);
                        gc4.set(GregorianCalendar.YEAR,fechaAnio1);
                        em.setFecha_nac_empleado(new Date(gc4.getTimeInMillis()));
                        
                        GregorianCalendar gc3 = new GregorianCalendar();
                        gc3.set(GregorianCalendar.DATE,fechaCtDia1);
                        gc3.set(GregorianCalendar.MONTH,fechaCtMes1);
                        gc3.set(GregorianCalendar.YEAR,fechaCtAnio1);
                        
                        em.setFecha_contrat_empleado(new Date(gc3.getTimeInMillis()));
                        em.setSexIDempleado_fk(sexId1);
                        em.setEstadoEmpleado_fk(estadoEmID1);
                        em.setEstadoCivil_empleado_fk(idEstadoCivil2);
                        em.setDeparID_empleado_fk(departID1);
                        em.setCargoID_empleado_fk(cargoID1);
                        
                        
                        empleadosDao.insertDatos(em);
                        
                        salida.println("Empleado Actualizado!\n\rID Empleado : "+em.getId_empleado()+"Nombre : "+em.getNombre_empleado()+"\n\rApellido : "+
                                em.getApellido_empleado()+"\n\rEmail: "+em.getCorreo_empleado()+"\n\rTelefono: "+em.getTelefono_empleado()+
                                "\r\nDUI : "+em.getDui_empleado()+"\n\rDireccion: "+em.getDireccion_empleado()+"\n\rFecha de nacimiento : "+
                                em.getFecha_nac_empleado()+"\n\rFecha de contratacion: "+em.getFecha_contrat_empleado()+"\n\rSex_ID : "+
                                em.getSexIDempleado_fk()+"\n\rID estado Empleado: "+em.getEstadoEmpleado_fk()+"\n\rEstado civil : "+
                                em.getEstadoCivil_empleado_fk()+"\n\rID_Departamento: "+em.getDeparID_empleado_fk()+"\n\rID_cargo :"+
                                em.getCargoID_empleado_fk());
                        break;
                        
                    case "7":
                        salida.println("Saliendo del  sistema");
                        bandera = false;
                        break;
                    default:
                        salida.println("Seleccione una opcion valida \n\r");
                }
            } while (bandera);
            
        } catch (IOException ex) {
            Logger.getLogger(Gestiones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gestiones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Gestiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}