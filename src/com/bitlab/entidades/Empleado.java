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
public class Empleado {

    private int id_empleado;
    private Date fecha_cumple;
    private String Nombre_empleado;
    private String apellido_empleado;
    private String genero_empleado;
    private Date fecha_contra;

    public Empleado(int id_empleado, Date fecha_cumple, String Nombre_empleado, String apellido_empleado, String genero_empleado, Date fecha_contra) {
        this.id_empleado = id_empleado;
        this.fecha_cumple = fecha_cumple;
        this.Nombre_empleado = Nombre_empleado;
        this.apellido_empleado = apellido_empleado;
        this.genero_empleado = genero_empleado;
        this.fecha_contra = fecha_contra;
    }

    public Empleado() {
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Date getFecha_cumple() {
        return fecha_cumple;
    }

    public void setFecha_cumple(Date fecha_cumple) {
        this.fecha_cumple = fecha_cumple;
    }

    public String getNombre_empleado() {
        return Nombre_empleado;
    }

    public void setNombre_empleado(String Nombre_empleado) {
        this.Nombre_empleado = Nombre_empleado;
    }

    public String getApellido_empleado() {
        return apellido_empleado;
    }

    public void setApellido_empleado(String apellido_empleado) {
        this.apellido_empleado = apellido_empleado;
    }

    public String getGenero_empleado() {
        return genero_empleado;
    }

    public void setGenero_empleado(String genero_empleado) {
        this.genero_empleado = genero_empleado;
    }

    public Date getFecha_contra() {
        return fecha_contra;
    }

    public void setFecha_contra(Date fecha_contra) {
        this.fecha_contra = fecha_contra;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id_empleado=" + id_empleado + ", fecha_cumple=" + fecha_cumple + ", Nombre_empleado=" + Nombre_empleado + ", apellido_empleado=" + apellido_empleado + ", genero_empleado=" + genero_empleado + ", fecha_contra=" + fecha_contra + '}';
    }

}
