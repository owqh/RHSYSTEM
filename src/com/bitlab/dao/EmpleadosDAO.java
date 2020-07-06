/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import static com.bitlab.conexion.ConexionBD.AbrirConexion;
import com.bitlab.entidades.Empleado;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlosGodoy
 */
public class EmpleadosDAO extends PatronDAO<Empleado> {
     @Override
    protected Empleado getMapeoDeResultados(ResultSet rs) throws SQLException {
        return new Empleado(
                rs.getInt("EMP_ID"),
                rs.getString("EMP_NOMBRE"),
                rs.getString("EMP_APELLIDO"),
                rs.getString("EMP_CORREO"),
                rs.getInt("EMP_TELEFONO"),
                rs.getInt("EMP_DUI"),
                rs.getString("EMP_DIRECCION"),
                rs.getDate("EMP_FECHA_NAC"),
                rs.getDate("EMP_FECHA_CONTRATO"),
                rs.getInt("SEX_ID"),
                rs.getInt("EEM_ID"),
                rs.getInt("ECI_ID"),
                rs.getInt("DEP_ID"),
                rs.getInt("CAR_ID"));
    }

    @Override
    protected void setMapeoParametrosInsertar(PreparedStatement ps, Empleado entidad) throws SQLException {
        ps.setInt(      1, entidad.getId_empleado());
        ps.setString(   2, entidad.getNombre_empleado());
        ps.setString(   3, entidad.getApellido_empleado());
        ps.setString(   4, entidad.getCorreo_empleado());
        ps.setInt(5, entidad.getTelefono_empleado());
        ps.setInt(   6, entidad.getDui_empleado());
        ps.setString(   7, entidad.getDireccion_empleado());
        ps.setDate(     8, entidad.getFecha_nac_empleado() );
        ps.setDate(     9, entidad.getFecha_contrat_empleado());
        ps.setInt(      10, entidad.getSexIDempleado_fk() );
        ps.setInt(      11, entidad.getEstadoEmpleado_fk());
        ps.setInt(      12, entidad.getEstadoCivil_empleado_fk());
        ps.setInt(      13, entidad.getDeparID_empleado_fk());
        ps.setInt(      14, entidad.getCargoID_empleado_fk());
        
    }

    @Override
    protected void setMapeoParametrosActualizar(PreparedStatement ps, Empleado entidad) throws SQLException {
         ps.setInt(      1, entidad.getId_empleado());
        ps.setString(   2, entidad.getNombre_empleado());
        ps.setString(   3, entidad.getApellido_empleado());
        ps.setString(   4, entidad.getCorreo_empleado());
        ps.setInt(   5, entidad.getTelefono_empleado());
        ps.setInt(   6, entidad.getDui_empleado());
        ps.setString(   7, entidad.getDireccion_empleado());
        ps.setDate(     8, entidad.getFecha_nac_empleado() );
        ps.setDate(     9, entidad.getFecha_contrat_empleado());
        ps.setInt(      10, entidad.getSexIDempleado_fk() );
        ps.setInt(      11, entidad.getEstadoEmpleado_fk());
        ps.setInt(      12, entidad.getEstadoCivil_empleado_fk());
        ps.setInt(      13, entidad.getDeparID_empleado_fk());
        ps.setInt(      14, entidad.getCargoID_empleado_fk());
        ps.setInt(      15, entidad.getId_empleado());
    }

    @Override
    protected String getLlaveTabla() {
        return "EMP_ID";
    }

    @Override
    protected String[] getColumnasDeLaTabla() {
        String[] str = {"EMP_ID", "EMP_NOMBRE", "EMP_APELLIDO", "EMP_CORREO", "EMP_TELEFONO",
             "EMP_DUI", "EMP_DIRECCION", "EMP_FECHA_NAC", "EMP_FECHA_CONTRATO", "SEX_ID", "EEM_ID",
             "ECI_ID", "DEP_ID", "CAR_ID"};
        return str;
    }

    @Override
    protected String getNombreTabla() {
        return "APL_EMP_EMPLEADO";
    }

   
}
        
       
