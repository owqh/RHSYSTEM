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
public class Usuario {
    private int id_usuario;
    private String acceso_usuario;
    private String nombre_usuario;
    private String apellido_usuario;
    private String contra_usuario;
    private int tipo_id_fk;

    public Usuario() {
    }

    public Usuario(int id_usuario, String acceso_usuario, String nombre_usuario, String apellido_usuario, String contra_usuario, int tipo_id_fk) {
        this.id_usuario = id_usuario;
        this.acceso_usuario = acceso_usuario;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.contra_usuario = contra_usuario;
        this.tipo_id_fk = tipo_id_fk;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getAcceso_usuario() {
        return acceso_usuario;
    }

    public void setAcceso_usuario(String acceso_usuario) {
        this.acceso_usuario = acceso_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public String getContra_usuario() {
        return contra_usuario;
    }

    public void setContra_usuario(String contra_usuario) {
        this.contra_usuario = contra_usuario;
    }

    public int getTipo_id_fk() {
        return tipo_id_fk;
    }

    public void setTipo_id_fk(int tipo_id_fk) {
        this.tipo_id_fk = tipo_id_fk;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("|| ID: ").append(id_usuario).append("\n\r");
        sb.append(" || Acceso: ").append(acceso_usuario).append("\n\r");
        sb.append(" || Nombre: ").append(nombre_usuario).append("\n\r");;
        sb.append(" || Apellido: ").append(apellido_usuario).append("\n\r");;
        sb.append(" || Contrasena: ").append(contra_usuario).append("\n\r");;
        sb.append("\n\r\n\r");
        
        return sb.toString();
    }
    
    
    
}
