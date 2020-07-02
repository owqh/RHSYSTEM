/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.datos.correo;

/**
 *
 * @author carlosGodoy
 */
public class DatosCorreo {
    
    private  static final String CODIGO_TOKEN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#%+";
    String correo;
    String mensaje;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public static String getCODIGO_TOKEN() {
        return CODIGO_TOKEN;
    }
    
}
