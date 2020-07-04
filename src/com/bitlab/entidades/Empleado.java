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
    private String nombre_empleado;
    private String apellido_empleado;
    private String correo_empleado;
    private String telefono_empleado;
    private String dui_empleado;
    private String direccion_empleado;
    private Date fecha_nac_empleado;
    private Date fecha_contrat_empleado;
    private int sexIDempleado_fk;
    private int estadoEmpleado_fk;
    private int estadoCivil_empleado_fk;
    private int deparID_empleado_fk;
    private int cargoID_empleado_fk;
    
    public Empleado(int id_empleado, String nombre_empleado, String apellido_empleado, String correo_empleado, String telefono_empleado, String dui_empleado, String direccion_empleado, Date fecha_nac_empleado, Date fecha_contrat_empleado, int sexIDempleado_fk, int estadoEmpleado_fk, int estadoCivil_empleado_fk, int deparID_empleado_fk, int cargoID_empleado_fk) {
        this.id_empleado = id_empleado;
        this.nombre_empleado = nombre_empleado;
        this.apellido_empleado = apellido_empleado;
        this.correo_empleado = correo_empleado;
        this.telefono_empleado = telefono_empleado;
        this.dui_empleado = dui_empleado;
        this.direccion_empleado = direccion_empleado;
        this.fecha_nac_empleado = fecha_nac_empleado;
        this.fecha_contrat_empleado = fecha_contrat_empleado;
        this.sexIDempleado_fk = sexIDempleado_fk;
        this.estadoEmpleado_fk = estadoEmpleado_fk;
        this.estadoCivil_empleado_fk = estadoCivil_empleado_fk;
        this.deparID_empleado_fk = deparID_empleado_fk;
        this.cargoID_empleado_fk = cargoID_empleado_fk;
    }
    
    public Empleado() {
    }
    
    public int getId_empleado() {
        return id_empleado;
    }
    
    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
    
    public String getNombre_empleado() {
        return nombre_empleado;
    }
    
    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }
    
    public String getApellido_empleado() {
        return apellido_empleado;
    }
    
    public void setApellido_empleado(String apellido_empleado) {
        this.apellido_empleado = apellido_empleado;
    }
    
    public String getCorreo_empleado() {
        return correo_empleado;
    }
    
    public void setCorreo_empleado(String correo_empleado) {
        this.correo_empleado = correo_empleado;
    }
    
    public String getTelefono_empleado() {
        return telefono_empleado;
    }
    
    public void setTelefono_empleado(String telefono_empleado) {
        this.telefono_empleado = telefono_empleado;
    }
    
    public String getDui_empleado() {
        return dui_empleado;
    }
    
    public void setDui_empleado(String dui_empleado) {
        this.dui_empleado = dui_empleado;
    }
    
    public String getDireccion_empleado() {
        return direccion_empleado;
    }
    
    public void setDireccion_empleado(String direccion_empleado) {
        this.direccion_empleado = direccion_empleado;
    }
    
    public Date getFecha_nac_empleado() {
        return fecha_nac_empleado;
    }
    
    public void setFecha_nac_empleado(Date fecha_nac_empleado) {
        this.fecha_nac_empleado = fecha_nac_empleado;
    }
    
    public Date getFecha_contrat_empleado() {
        return fecha_contrat_empleado;
    }
    
    public void setFecha_contrat_empleado(Date fecha_contrat_empleado) {
        this.fecha_contrat_empleado = fecha_contrat_empleado;
    }
    
    public int getSexIDempleado_fk() {
        return sexIDempleado_fk;
    }
    
    public void setSexIDempleado_fk(int sexIDempleado_fk) {
        this.sexIDempleado_fk = sexIDempleado_fk;
    }
    
    public int getEstadoEmpleado_fk() {
        return estadoEmpleado_fk;
    }
    
    public void setEstadoEmpleado_fk(int estadoEmpleado_fk) {
        this.estadoEmpleado_fk = estadoEmpleado_fk;
    }
    
    public int getEstadoCivil_empleado_fk() {
        return estadoCivil_empleado_fk;
    }
    
    public void setEstadoCivil_empleado_fk(int estadoCivil_empleado_fk) {
        this.estadoCivil_empleado_fk = estadoCivil_empleado_fk;
    }
    
    public int getDeparID_empleado_fk() {
        return deparID_empleado_fk;
    }
    
    public void setDeparID_empleado_fk(int deparID_empleado_fk) {
        this.deparID_empleado_fk = deparID_empleado_fk;
    }
    
    public int getCargoID_empleado_fk() {
        return cargoID_empleado_fk;
    }
    
    public void setCargoID_empleado_fk(int cargoID_empleado_fk) {
        this.cargoID_empleado_fk = cargoID_empleado_fk;
    }
    
    @Override
    public String toString() {
        
        StringBuilder empleados = new StringBuilder();
        empleados.append(" || ID: ").append(id_empleado);
        empleados.append(" || Nombre: ").append(nombre_empleado);
        empleados.append(" || Apellido: ").append(apellido_empleado);
        empleados.append(" || Email: ").append(correo_empleado);
        empleados.append(" || Telefono: ").append(telefono_empleado);
        empleados.append(" || DUI: ").append(dui_empleado);
        empleados.append(" || Dirreccion: ").append(direccion_empleado);
        empleados.append(" || Fecha_Nacimiento: ").append(fecha_nac_empleado);
        empleados.append(" || Fecha_Contratacion: ").append(fecha_contrat_empleado);
        empleados.append(" || Sex_ID: ").append(sexIDempleado_fk);
        empleados.append(" || Estado_Laboral: ").append(estadoEmpleado_fk);
        empleados.append(" || Estado_Civil: ").append(estadoCivil_empleado_fk);
        empleados.append(" || ID_Departamento: ").append(deparID_empleado_fk);
        empleados.append(" || ID_Cargo: ").append(cargoID_empleado_fk);
       
        return empleados.toString().concat("\n\n");
        
    }
    
    
}
