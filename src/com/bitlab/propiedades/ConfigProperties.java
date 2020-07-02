/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.propiedades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlosGodoy
 */
public class ConfigProperties {
    
    private Properties propSistema;
    private final String ARCHIVO_PROPIEDADES = "propiedades.properties";
    
    /*esta clase de propiedades se usa como un pivote
        se usa para poder leer la informacion que este dentro del paquete de propiedades
        se obtiene un recurso como un entrada de Stream
    */
    public static InputStream getResourceAsInputStream (String nombre){
        // vamos a retornar de nuestro configproperties obtenemos como clase
        //dentro de esa clase dentro del paquete tengo diferentes recursos
        // y de esos recursos que me obtenga uno de esos recursos como stream
        //este return no lo que hace es regresar un nombre de tipo Stream
        return ConfigProperties.class.getResourceAsStream(nombre);
        
        /*
        el inputStream es una entrada de datos en este caso como en este paque
        tenemos datos este lo hace el inputStream
        */
    }
    //Metodo para cargar las informacion del archivo properties
    public void cargarPropiedades (){
        try {
            propSistema = new Properties();
            propSistema.load(ConfigProperties.getResourceAsInputStream(ARCHIVO_PROPIEDADES));
        } catch (IOException ex) {
            Logger.getLogger(ConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public Properties getPropSistema() {
        return propSistema;
    }
}
