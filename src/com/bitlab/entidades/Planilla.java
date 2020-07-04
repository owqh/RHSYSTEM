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
public class Planilla {
    private int id_planilla;
    private float afpPatronal;
    private float afpLaboral;
    private float isssPatronal;
    private float isssLaboral;
    private float renta;
    private int id_empleado_fk;

    public Planilla() {
    }

    public Planilla(int id_planilla, float afpPatronal, float afpLaboral, float isssPatronal, float isssLaboral, float renta, int id_empleado_fk) {
        this.id_planilla = id_planilla;
        this.afpPatronal = afpPatronal;
        this.afpLaboral = afpLaboral;
        this.isssPatronal = isssPatronal;
        this.isssLaboral = isssLaboral;
        this.renta = renta;
        this.id_empleado_fk = id_empleado_fk;
    }

   

    public int getId_planilla() {
        return id_planilla;
    }

    public void setId_planilla(int id_planilla) {
        this.id_planilla = id_planilla;
    }

    public float getAfpPatronal() {
        return afpPatronal;
    }

    public void setAfpPatronal(float afpPatronal) {
        this.afpPatronal = afpPatronal;
    }

    public float getAfpLaboral() {
        return afpLaboral;
    }

    public void setAfpLaboral(float afpLaboral) {
        this.afpLaboral = afpLaboral;
    }

    public float getIsssPatronal() {
        return isssPatronal;
    }

    public void setIsssPatronal(float isssPatronal) {
        this.isssPatronal = isssPatronal;
    }

    public float getIsssLaboral() {
        return isssLaboral;
    }

    public void setIsssLaboral(float isssLaboral) {
        this.isssLaboral = isssLaboral;
    }

    public float getRenta() {
        return renta;
    }

    public void setRenta(float renta) {
        this.renta = renta;
    }

    public int getId_empleado_fk() {
        return id_empleado_fk;
    }

    public void setId_empleado_fk(int id_empleado_fk) {
        this.id_empleado_fk = id_empleado_fk;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(" || ID_Planilla: ").append(id_planilla);
        sb.append(" || AFP_Patronal: ").append(afpPatronal);
        sb.append(" || AFP_Laboral: ").append(afpLaboral);
        sb.append(" || ISSS_Patronal: ").append(isssPatronal);
        sb.append(" || ISSS_Laboral ").append(isssLaboral);
        sb.append("\n");
        
        return sb.toString();
    }
    
}
