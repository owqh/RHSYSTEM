/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.correo;

import com.bitlab.datos.correo.DatosCorreo;
import com.bitlab.encriptador.Encriptador;
import java.util.Properties;
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

    private static final Logger logger = Logger.getLogger(Correo.class.getName());

    

    public static void enviarCorreo(DatosCorreo datos, Properties prop) {//UsuarioDeSistema usuario) {

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

            email.addTo(datos.getCorreo());
            //email.addTo(usuario.getCorreo());
            
            email.send();
            logger.info("se ha enviado el correo electronico");
        } catch (EmailException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
