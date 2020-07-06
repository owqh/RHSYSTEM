/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import com.bitlab.clienteHilo.ClienteHilo;
import com.bitlab.dao.UsuarioDAO;
import com.bitlab.encriptador.Encriptador;
import com.bitlab.entidades.Usuario;
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
import java.sql.SQLException;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Oscar
 */
public class FuncionesPricipales  {
    //Creando las funciones pricipales que me serviran en todo el proyecto
     Scanner scanner = new Scanner(System.in);
     Validaciones validar = new Validaciones();
     UsuarioDAO usuarioDAO = new UsuarioDAO();
     Menu menu = new Menu();
     ClienteHilo cliente = new ClienteHilo();
     PrintWriter salida = null;
     BufferedReader entrada = null;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Proyecto4.class);
    
     //Funcion para validar acceso al sistema de un cliente
     
     public void login(Socket socketCliente, String correo, String pass) throws IOException, ClassNotFoundException, SQLException{
       logger.debug("Iniciando verificacion de acceso al sistema");
       salida = new PrintWriter(socketCliente.getOutputStream(), true);
       entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        List<Usuario> objects = new ArrayList<>();
       objects = usuarioDAO.getLogin(correo, pass);
       boolean flag = false;
         if (objects.size() == 1 ) {
             for (Usuario u : objects) {
                 logger.debug("Solicitando doble factor de autenticacion");
                 salida.println("Revise su direccion de correo electronico e ingrese el codigo que se le envio.");
                 validarAcceso(socketCliente,u.getNombre_usuario(), u.getAcceso_usuario(), u.getTipo_id_fk());
             }
         }else{
             logger.debug("Correo o contrasena incorrecta, cerrando hilo");
             salida.println("Correo o contrasena incorrecta, porfavor intente de nuevo");
             salida.println("Se cerrara el sistema.");
             socketCliente.close();
         }
//       salida.println("El usuario es: "+correo);
//       salida.println("La contraseña es: "+pass);

       
     }
     
    //Funcion para generar cadena de seguridad 
    public String codigoAcceso(){
        logger.debug("Preparando cadena de seguridad");
         //Variables para Generar el ID de Forma Aleatoria
            Random aleatorio = new Random();
            final String CODIGO_TOKEN = "AB7CDE0FG9HIJK2LMN6OPQRST8UVW4XY5Z163@#%+*";
            final byte NIVEL_SEGURIDAD=7; //cantidad de caracteres para el codigo
            String cadena = ""; //Cadena de seguridad
            
            //Preparando la cadena
            for (int i = 1; i < NIVEL_SEGURIDAD; i++) {
                //Método para el Cálculo de las letras
                byte caracter=(byte)(aleatorio.nextDouble() * CODIGO_TOKEN.length()-1+0);
                cadena=cadena+CODIGO_TOKEN.charAt(caracter);
            }
        return cadena;
    }
    
       
        //funicon para enviar correo electronico del codigo de seguridad previamente establecido
        public void enviarCorreo(String nombre, String correo, String codigo, Properties props ){
            Encriptador encriptado = new Encriptador();
            logger.debug("Preparando envio de correo electronico de seguridad");
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
                         +"<p>Hay un intento de inicio de sesión a su sistema de Human Resources</p>"
                         +"<h3>Su codigo de acceso a la plataforma es: <strong>"+codigo+"</strong></h3> \n "
                         +"<p><b>Si usted no solicito este acceso porfavor ignore este correo.</b></p>"           
                         +"<p>Has recibido este e-mail porque tienes una cuenta registrada en Human Resources System</p>"        
                         + "<h4>Por su seguirdad nunca comparta este correo electronico con nadie.</h4>"
                         +"<p>&copy; 2020 Human Resources System<p>");
            email.addTo(correo);
            logger.debug("Enviando correo electronico");
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(Proyecto4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        //Metodo para validar que el codigo introducido sea igual al enviado
    public void validarAcceso (Socket socketCliente,String nombre, String correo, int nivel){
        try {
            salida = new PrintWriter(socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
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
                  logger.debug("Recibiendo codigo de seguridad del usuario: "+nombre+"\n\r");
                  salida.println("Digite el codigo que recibio en su correo electronico: \n\r");
                  String codigoUsuario = entrada.readLine();
                  logger.debug("Validando el codigo de seguridad de "+nombre);
                   if (codigo.equalsIgnoreCase(codigoUsuario) && nivel == 1){
                       logger.info("El codigo de seguridad ha sido aceptado");
                       logger.debug("Administrador detectado ");
                       menu.menuAdmin(socketCliente, nombre);
                       flag = true;
                   }
                   else if (codigo.equalsIgnoreCase(codigoUsuario) && nivel == 2){
                       logger.info("El codigo de seguridad ha sido aceptado");
                       logger.debug("Cuenta de recusos humanos ");
                      menu.menuHR(socketCliente, nombre);
                      flag = true;
                   } else if(codigo.equalsIgnoreCase(codigoUsuario) && nivel > 2){
                       logger.info("El codigo de seguridad ha sido aceptado");
                       logger.debug("otra cuenta humanos ");
                       menu.otro(socketCliente, nombre);
                       flag = true;
                   }
                   else {
                       flag = false;
                       logger.debug("Condigo de seguridad incorrecto intento: "+contador +"\n\r");
                       salida.println("Por favor digite el codigo correcto.");
                       contador++;
                       salida.println("Numero de intentos restantes: " + (3-contador)+"\n\r");
                       if (contador > 2) {
                           logger.debug("Acceso invalido, cerrando hilo");
                            salida.println("Acceso invalido, saliendo del sistema...");
                            socketCliente.close();
                           flag = true;
                       }                       
                   }
            } while (flag != true);
            
        } catch (IOException ex) {
            Logger.getLogger(Proyecto4.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    
}
