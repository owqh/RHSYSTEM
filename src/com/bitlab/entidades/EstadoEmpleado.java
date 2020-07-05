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
public class EstadoEmpleado {
    private int id_EstadoEmpleado;
    private String nombreEstadoEmpleado;

    public EstadoEmpleado() {
    }

    public EstadoEmpleado(int id_EstadoEmpleado, String nombreEstadoEmpleado) {
        this.id_EstadoEmpleado = id_EstadoEmpleado;
        this.nombreEstadoEmpleado = nombreEstadoEmpleado;
    }

    public int getId_EstadoEmpleado() {
        return id_EstadoEmpleado;
    }

    public void setId_EstadoEmpleado(int id_EstadoEmpleado) {
        this.id_EstadoEmpleado = id_EstadoEmpleado;
    }

    public String getNombreEstadoEmpleado() {
        return nombreEstadoEmpleado;
    }

    public void setNombreEstadoEmpleado(String nombreEstadoEmpleado) {
        this.nombreEstadoEmpleado = nombreEstadoEmpleado;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(id_EstadoEmpleado).append(", ").append(nombreEstadoEmpleado).append("\n\r");
        
        return sb.toString();
    }
    
    
    
}
