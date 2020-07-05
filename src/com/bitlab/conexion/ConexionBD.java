/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.conexion;

import com.bitlab.encriptador.Encriptador;
import com.bitlab.propiedades.ConfigProperties;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author carlosGodoy
 */
public class ConexionBD {
    
    private static String nombreFicheroPropiedades = "config.properties";
    private static Properties prop = new Properties();
    public static Encriptador encriptar = new Encriptador();
    

    public static Connection AbrirConexion() throws ClassNotFoundException, SQLException, IOException { 
        
        prop.load(ConfigProperties.getResourceAsInputStream(nombreFicheroPropiedades));
        Class.forName(encriptar.getTextoDesencriptado((String) prop.get("DR")));
        
        return DriverManager.getConnection(encriptar.getTextoDesencriptado((String) prop.get("UL")),
                encriptar.getTextoDesencriptado((String) prop.get("UR")),
                encriptar.getTextoDesencriptado((String) prop.get("PD")));
    }
    
    //Metodo para cerrar las conexiones.
    public static void CerrandoConexion(Connection con) throws SQLException{
        if(con!=null && !con.isClosed())
            con.close();
    }
    
    public static String getNombreFicheroPropiedades() {
        return nombreFicheroPropiedades;
    }
    
    public static Properties getProp() {
        return prop;
    }
    
    
}
