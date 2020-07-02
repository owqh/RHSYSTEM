/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.correo;

import com.bitlab.datos.correo.DatosCorreo;
import com.bitlab.encriptador.Encriptador;
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

    

    public static void enviarCorreo(DatosCorreo datos) {//UsuarioDeSistema usuario) {

        String correo = datos.getCorreo();
        //instancia de la clase encriptador que maneja la desencriptacion de los datos
        Encriptador encriptacionTexto = new Encriptador();
        Email email = new SimpleEmail();
        try {

            //email.setHostName("smtp.gmail.com");
            email.setHostName(encriptacionTexto.getTextoDesencriptado("rB0kYXpFh7144bxHVf2kcyIQmJ4fdF0T"));

            //email.setSmtpPort(587);
            email.setSmtpPort(Integer.parseInt(encriptacionTexto.getTextoDesencriptado("RmFIEnU7wGu1d+C5zzeviA==")));
            //email.setAuthentication("cg518271@gmail.com", "docky2020");
            email.setAuthentication(encriptacionTexto.getTextoDesencriptado("72F+zT7/Ec3y50C9qqOpgzjS3ljEAOENmXPYKZ+MrVE="),
                    encriptacionTexto.getTextoDesencriptado("i8LF0xRqHU+1AmVmZ2hQ4W2Z1ohd3FTa"));
            email.setSSLOnConnect(true);
            //correo desde donde se envia
            email.setFrom("cg518271@gmail.com");
            //Asunto de correo
            email.setSubject("Credenciales para el acceso");

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
