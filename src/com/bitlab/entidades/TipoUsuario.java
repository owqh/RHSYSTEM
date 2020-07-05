/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entidades;

/**
 *
 * @author carlosGodoy
 */
public class TipoUsuario {
    private int id_TipoUsuario;
    private String nombreTipoUsuario;

    public TipoUsuario() {
    }

    public TipoUsuario(int id_TipoUsuario, String nombreTipoUsuario) {
        this.id_TipoUsuario = id_TipoUsuario;
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    public int getId_TipoUsuario() {
        return id_TipoUsuario;
    }

    public void setId_TipoUsuario(int id_TipoUsuario) {
        this.id_TipoUsuario = id_TipoUsuario;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(id_TipoUsuario).append(", ").append(nombreTipoUsuario);
        
        return sb.toString();
    }
    
}
