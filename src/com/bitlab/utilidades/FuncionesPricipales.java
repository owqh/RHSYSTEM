/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import com.bitlab.encriptador.Encriptador;
import com.bitlab.propiedades.ConfigProperties;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import com.bitlab.principal.Proyecto4;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 *
 * @author Oscar
 */
public class FuncionesPricipales {
    //Creando las funciones pricipales que me serviran en todo el proyecto
     Scanner scanner = new Scanner(System.in);
     Validaciones validar = new Validaciones();
     private  static final String CODIGO_TOKEN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#%+";

    
    public String codigoAcceso(){
         //Variables para Generar el ID de Forma Aleatoria
            Random aleatorio = new Random();
            String abecedario = "ABCDEFGHIJKLMNOPQRSTVWXYZ";
            String cadena = "";    
            byte numero1;
            byte numero2;
            //Método para el Cálculo de las letras
            byte letra1=(byte)(aleatorio.nextDouble() * abecedario.length()-1+0);
            byte letra2=(byte)(aleatorio.nextDouble() * abecedario.length()-1+0);
            
            //Definimos la cantidad máxima de números aleatorios (99) y sumamos 10 para mantener 2 números cada vez
            numero1=(byte)(aleatorio.nextDouble() * 99+10);
            numero2=(byte)(aleatorio.nextDouble() * 99+10);
            
            cadena=cadena+abecedario.charAt(letra1)+numero1+abecedario.charAt(letra2)+numero2;
        
        return cadena;
    }
    
       
        public void enviarCorreo(String nombre, String correo, String codigo, Properties props ){
            Encriptador encriptado = new Encriptador();
            
        try {
            HtmlEmail email = new HtmlEmail();
            String valor = props.getProperty("srEmName");
            String valorDesencriptado = encriptado.getTextoDesencriptado(valor);
            email.setHostName(valorDesencriptado);
            email.setSmtpPort(
                    Integer.parseInt(encriptado.getTextoDesencriptado(props.getProperty("srEmPort")))
                );
            email.setAuthenticator(new DefaultAuthenticator(
                                    encriptado.getTextoDesencriptado(props.getProperty("srEmU")), 
                                    encriptado.getTextoDesencriptado(props.getProperty("srEmP"))));
            email.setSSLOnConnect(true);
            email.setFrom(encriptado.getTextoDesencriptado(props.getProperty("srEmFrom")));
            email.setSubject("Autenticacion de dos pasos");
            email.setHtmlMsg("<h1>Gracias por utilizar nuestros sistemas "+nombre+"\n </h1> "
                         +"<h3>Su codigo de acceso a la plataforma es: <strong>"+codigo+"</strong></h3> \n "
                         + "<h4>Gracias por registarse con nosotros.</h4>");
            email.addTo(correo);
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(Proyecto4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        //Metodo para validar que el codigo introducido sea igual al enviado
    public void validarAcceso (String nombre, String correo){
        try {
            //Cargando propiedades
            Properties prop = new Properties();
            String fileName ="config.properties";
            prop.load(ConfigProperties.getResourceAsInputStream(fileName));
            //Generando codigo
            String codigo =  codigoAcceso();
            //Enviando correo electronico
            enviarCorreo(nombre, correo, codigo, prop);
            
            /*Validacion del codigo que el usario nos envia, si el codigo es correcto nos envia al menu caso contario
             se le brindan 2 oportunidades mas al usuario para insertar el codigo correcto si el usuario se equivoca 
             3 veses se cierra el sistema*/
            boolean flag = false;
            short contador = 0;
            do {   
                  System.out.println("Digite el codigo que recibio en su correo electronico: ");
                  String codigoUsuario = scanner.nextLine();
//                   if (codigo.equalsIgnoreCase(codigoUsuario)) ClienteHilo.menuPrincipal(nombre);
//                   else {
//                       flag = false;
//                       System.out.println("Por favor digite el codigo correcto.");
//                       contador++;
//                       System.out.println("Numero de intentos restantes: " + (3-contador));
//                       if (contador > 2) {
//                           System.out.println("Acceso invalido, saliendo del sistema...");
//                           System.exit(0);
//                           flag = true;
//                       }                       
//                   }
            } while (flag != true);

            
        } catch (IOException ex) {
            Logger.getLogger(Proyecto4.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    
}