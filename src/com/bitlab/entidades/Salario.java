/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entidades;

import java.sql.Date;

/**
 *
 * @author carlosGodoy
 */
public class Salario {
    private int id_salario;
    private float salario;
    private Date fechaInicioSalario;
    private Date fechaFinSalario;
    private int idEmpleadoFK;

    public Salario() {
    }

    public Salario(int id_salario, float salario, Date fechaInicioSalario, Date fechaFinSalario, int idEmpleadoFK) {
        this.id_salario = id_salario;
        this.salario = salario;
        this.fechaInicioSalario = fechaInicioSalario;
        this.fechaFinSalario = fechaFinSalario;
        this.idEmpleadoFK = idEmpleadoFK;
    }

    public int getId_salario() {
        return id_salario;
    }

    public void setId_salario(int id_salario) {
        this.id_salario = id_salario;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Date getFechaInicioSalario() {
        return fechaInicioSalario;
    }

    public void setFechaInicioSalario(Date fechaInicioSalario) {
        this.fechaInicioSalario = fechaInicioSalario;
    }

    public Date getFechaFinSalario() {
        return fechaFinSalario;
    }

    public void setFechaFinSalario(Date fechaFinSalario) {
        this.fechaFinSalario = fechaFinSalario;
    }

    public int getIdEmpleadoFK() {
        return idEmpleadoFK;
    }

    public void setIdEmpleadoFK(int idEmpleadoFK) {
        this.idEmpleadoFK = idEmpleadoFK;
    }

    @Override
    public String toString(){
            
            StringBuilder sb = new StringBuilder();
            sb.append(" || ID_Salario: ").append(id_salario);
            sb.append(" || Salario: ").append(salario);
            sb.append(" || Fecha_Inicio_Salario: : ").append(fechaInicioSalario);
            sb.append(" || Fecha_Fin_Salario: ").append(fechaFinSalario);
            sb.append(" || ID_Empleado_fk: ").append(idEmpleadoFK);
            sb.append("\n");
    
    return sb.toString();
    }
    
    
}
