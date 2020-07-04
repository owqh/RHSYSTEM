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
public class Cargo {
    private int id_cargo;
    private String nombre_Cargo;
    
    public Cargo(int id_cargo, String nombre_Cargo) {
        this.id_cargo = id_cargo;
        this.nombre_Cargo = nombre_Cargo;
    }
    
    public Cargo() {
    }
    
    public int getId_cargo() {
        return id_cargo;
    }
    
    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }
    
    public String getNombre_Cargo() {
        return nombre_Cargo;
    }
    
    public void setNombre_Cargo(String nombre_Cargo) {
        this.nombre_Cargo = nombre_Cargo;
    }
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(" || ID_Cargo: ").append(id_cargo);
        sb.append(" || Nombre_Cargo: ").append(nombre_Cargo);

        return sb.toString();
    }
    
}
