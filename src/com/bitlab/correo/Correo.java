/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.correo;

import com.bitlab.datos.correo.DatosCorreo;
import com.bitlab.encriptador.Encriptador;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author carlosGodoy
 */
public class Correo {
    
    //Intancia del scanner
    Scanner sc = new Scanner(System.in);
    
    private static String codigoToken ="";
    private static final Logger logger = Logger.getLogger(Correo.class.getName());
    
    public void enviarCorreo(DatosCorreo datos, Properties prop) {//UsuarioDeSistema usuario)
        
        String correo = datos.getCorreo();
        //instancia de la clase encriptador que maneja la desencriptacion de los datos
        Encriptador encriptacionTexto = new Encriptador();
        Email email = new SimpleEmail();
        
        try { 
            //email.setHostName("smtp.gmail.com");
            email.setHostName(encriptacionTexto.getTextoDesencriptado(prop.getProperty("setHostN")));
            //email.setSmtpPort
            email.setSmtpPort(Integer.parseInt(encriptacionTexto.getTextoDesencriptado(prop.getProperty("setSmtpPort"))));
            //email.setAuthentication
            email.setAuthentication(encriptacionTexto.getTextoDesencriptado(prop.getProperty("setUser")),
                    encriptacionTexto.getTextoDesencriptado(prop.getProperty("setPassw")));
            email.setSSLOnConnect(true);
            //correo desde donde se envia
            email.setFrom(encriptacionTexto.getTextoDesencriptado(prop.getProperty("setEFrom")));
            //Asunto de correo
            email.setSubject(encriptacionTexto.getTextoDesencriptado(prop.getProperty("setSubj")));
            //email.setMsg("hola "+ usuario.getNombre()+" su clave de acceso al sistema es :" + usuario.getClave());
            email.setMsg(datos.getMensaje());
            //email.addTo(usuario.getCorreo());
            email.addTo(correo);
            
            email.send();
            logger.info("se ha enviado el correo electronico");
        } catch (EmailException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //Metodo para generar el codigo de seguridad random.
    public String codigoSeguridadToken(){
        byte codigo = 15;
        StringBuilder generador = new StringBuilder();
        while (codigo-- != 0) {
            int caracter = (int)(Math.random()*DatosCorreo.getCODIGO_TOKEN().length());
            generador.append(DatosCorreo.getCODIGO_TOKEN().charAt(caracter));
        }
        logger.info("se ha generado el codigo de seguridad");
        return generador.toString();
    }
    
    //Metodo que redacta el correo con la alerta de inicio de sesion al sistema.
    public static String MensajeConCodigoToken(){
        StringBuilder mensaje = new StringBuilder("Hola");
        mensaje.append(", Buen dia\n");
        mensaje.append("Hay un intento de inicio de sesión a su sistema de RRHH, ");
        mensaje.append("por favor verifique que usted es un usuario authorizado ");
        mensaje.append("ingresando el siguiente codigo: " + getCodigoToken());
        for (int i = 0; i < 5; i++) {
            mensaje.append("\n");
        }mensaje.append("Feliz dia.");
        return mensaje.toString();
    }
    
    //Metodo que evalua el codigo de seguridad recibido para verificar que sea el correcto.
    public void recibiendoCodigoT(String codigoGenerado){
        String codigoIngresado ="";
        do{
            System.out.println("Ingrese el codigo de seguridad recibido: ");
            codigoIngresado = sc.nextLine();
        }while(!codigoIngresado.equals(codigoGenerado));
        logger.info("El codigo de seguridad ha sido aceptado");
    }
    
    
    protected static String getCodigoToken() {
        return codigoToken;
    }
    
    protected static void setCodigoToken(String codigoToken) {
        Correo.codigoToken = codigoToken;
    }
    
}
