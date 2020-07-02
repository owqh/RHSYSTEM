/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.encriptador;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author carlosGodoy
 */
public class Encriptador {
    // LLAVE_VAL es la llave del encriptador, su valor viene dado por "LLAVE_JY" que esta definida en una variable de sistema
    private static final String LLAVE_VAL = System.getenv().get("LLAVE_JY");
    
     BasicTextEncryptor encriptador;

    public Encriptador(String principalValor) {
        encriptador = new BasicTextEncryptor();
        if (principalValor == null || principalValor.length()== 0) {
            principalValor = LLAVE_VAL;
            encriptador.setPassword(principalValor);
        }
        encriptador.setPassword(principalValor);
    }
    
    public Encriptador() {
        encriptador = new BasicTextEncryptor();
        encriptador.setPassword(LLAVE_VAL);
    }

    public String getTextoEncriptado(String texto) {
        return encriptador.encrypt(texto);

    }

    public String getTextoDesencriptado(String textoDesencriptado) {

        return encriptador.decrypt(textoDesencriptado);
    }
    
    
}
