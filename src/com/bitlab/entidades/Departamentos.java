/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.entidades;

/**
 *
 * @author Manuel@Ramos
 */
public class Departamentos {
    
    private String idDepartamento;
    private String nombreDepartamento;
    private String edeDepartamento;
    
    
    public Departamentos(String idDepartamento, String nombreDepartamento, String edeDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.edeDepartamento = edeDepartamento;
        
    }
    
    public Departamentos() {
    }
    
    
    
    public String getIdDepartamento() {
        return idDepartamento;
    }
    
    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
    
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }
    
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
    
    public String getEdeDepartamento() {
        return edeDepartamento;
    }
    
    public void setEdeDepartamento(String edeDepartamento) {
        this.edeDepartamento = edeDepartamento;
    }


    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(" || ID_Departamento: ").append(idDepartamento);
        sb.append(" || Nombre_Departamento: ").append(nombreDepartamento);
        sb.append(" || Estado_Departamento: ").append(edeDepartamento);
        
        return sb.toString();
    }  
    
}
