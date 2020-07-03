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
public class EstadoCivil {
    private int id_estadoCivil;
    private String nombreEstadoCivil;

    public EstadoCivil() {
    }

    public EstadoCivil(int id_estadoCivil, String nombreEstadoCivil) {
        this.id_estadoCivil = id_estadoCivil;
        this.nombreEstadoCivil = nombreEstadoCivil;
    }

    public int getId_estadoCivil() {
        return id_estadoCivil;
    }

    public void setId_estadoCivil(int id_estadoCivil) {
        this.id_estadoCivil = id_estadoCivil;
    }

    public String getNombreEstadoCivil() {
        return nombreEstadoCivil;
    }

    public void setNombreEstadoCivil(String nombreEstadoCivil) {
        this.nombreEstadoCivil = nombreEstadoCivil;
    }

    @Override
    public String toString() {
        return "EstadoCivil{" + "id_estadoCivil=" + id_estadoCivil + ", nombreEstadoCivil=" + nombreEstadoCivil + '}';
    }
    
    
}
