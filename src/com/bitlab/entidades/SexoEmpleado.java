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
public class SexoEmpleado {
    private int id_sexoEmpleado;
    private String nombreSexoEmpleado;

    public SexoEmpleado() {
    }

    public SexoEmpleado(int id_sexoEmpleado, String nombreSexoEmpleado) {
        this.id_sexoEmpleado = id_sexoEmpleado;
        this.nombreSexoEmpleado = nombreSexoEmpleado;
    }

    public int getId_sexoEmpleado() {
        return id_sexoEmpleado;
    }

    public void setId_sexoEmpleado(int id_sexoEmpleado) {
        this.id_sexoEmpleado = id_sexoEmpleado;
    }

    public String getNombreSexoEmpleado() {
        return nombreSexoEmpleado;
    }

    public void setNombreSexoEmpleado(String nombreSexoEmpleado) {
        this.nombreSexoEmpleado = nombreSexoEmpleado;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(" || ID_Sexo_Empleado: ").append(id_sexoEmpleado);
        sb.append(" || Sexo_Empleado: ").append(nombreSexoEmpleado);
        
        return sb.toString();
    }
    
    
}
