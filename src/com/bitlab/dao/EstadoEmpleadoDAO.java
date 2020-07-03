/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.EstadoEmpleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author carlosGodoy
 */
public class EstadoEmpleadoDAO extends PatronDAO<EstadoEmpleado> {

    @Override
    protected EstadoEmpleado getMapeoDeResultados(ResultSet rs) throws SQLException {
        return new EstadoEmpleado(
                rs.getInt("EEM_ID"),
                rs.getString("EEM_NOMBRE")
        );
    }

    @Override
    protected void setMapeoParametrosInsertar(PreparedStatement ps, EstadoEmpleado entidad) throws SQLException {
        ps.setInt(1, entidad.getId_EstadoEmpleado());
        ps.setString(2, entidad.getNombreEstadoEmpleado());

    }

    @Override
    protected void setMapeoParametrosActualizar(PreparedStatement ps, EstadoEmpleado entidad) throws SQLException {
        ps.setInt(1, entidad.getId_EstadoEmpleado());
        ps.setString(2, entidad.getNombreEstadoEmpleado());
        ps.setInt(3, entidad.getId_EstadoEmpleado());
    }

    @Override
    protected String getLlaveTabla() {
        return "EEM_ID";
    }

    @Override
    protected String[] getColumnasDeLaTabla() {
        String[] str = {"EEM_ID", "EEM_NOMBRE"};
        return str;
    }

    @Override
    protected String getNombreTabla() {
        return "APL_EEM_ESTADO_EMPLEADO";
    }

}
