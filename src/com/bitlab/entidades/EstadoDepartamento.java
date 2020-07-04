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
public class EstadoDepartamento {
    private int id_EstadoDepartament;
    private String nombre_EstadoDepartamento;

    public EstadoDepartamento() {
    }

    public EstadoDepartamento(int id_EstadoDepartament, String nombre_EstadoDepartamento) {
        this.id_EstadoDepartament = id_EstadoDepartament;
        this.nombre_EstadoDepartamento = nombre_EstadoDepartamento;
    }

    public int getId_EstadoDepartament() {
        return id_EstadoDepartament;
    }

    public void setId_EstadoDepartament(int id_EstadoDepartament) {
        this.id_EstadoDepartament = id_EstadoDepartament;
    }

    public String getNombre_EstadoDepartamento() {
        return nombre_EstadoDepartamento;
    }

    public void setNombre_EstadoDepartamento(String nombre_EstadoDepartamento) {
        this.nombre_EstadoDepartamento = nombre_EstadoDepartamento;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(" || ID_Estado_despartamento ").append(id_EstadoDepartament);
        sb.append(" || Estado_Departamento: ").append(nombre_EstadoDepartamento);
        
        return sb.toString();
    }
    
}
