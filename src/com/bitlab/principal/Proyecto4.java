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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
  Encriptador encriptar = new Encriptador();
        DatosCorreo datosCorreo = new DatosCorreo();
        Scanner sc = new Scanner(System.in);
        System.out.println("esto es un texto encriptado su texto original es 'hola mundo' ");
        System.out.println(encriptar.getTextoEncriptado("hola mundo"));
        
        System.out.println("Desencriptando texto");
        System.out.println(encriptar.getTextoDesencriptado("LthK015TeUjwccjt4Nm7j91rJp0dcjhhMnJC6wCSZpI="));
        
        System.out.println("ingrese el correo");
        datosCorreo.setCorreo(sc.nextLine());
        logger.debug("se esta enviado el correo a datosCorreo");
        System.out.println("escriba el mensaje de su correo");
        datosCorreo.setMensaje(sc.nextLine());
        logger.debug("se esta enviado el mensaje a datosCorreo");
        logger.debug("se empieza con el proceso de envio de correo");
        Correo.enviarCorreo(datosCorreo);
        System.out.println("correo enviado");
       

        EmpleadosDAO empDao = new EmpleadosDAO();
        logger.debug("mostrando el select de la consulta");
        try {
            List<Empleado> empleado = empDao.obtenerTodoDatos();
            for(Empleado emp: empleado){
                System.out.println(emp.toString());
            }
            
            
            
            
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Proyecto4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Proyecto4.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    }
    
}
