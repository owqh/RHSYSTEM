/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.SexoEmpleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author carlosGodoy
 */
public class SexoEmpleadoDAO extends PatronDAO<SexoEmpleado> {

    @Override
    protected SexoEmpleado getMapeoDeResultados(ResultSet rs) throws SQLException {
        return new SexoEmpleado(
                rs.getInt("SEX_ID"),
                rs.getString("SEX_NOMBRE")
        );
    }

    @Override
    protected void setMapeoParametrosInsertar(PreparedStatement ps, SexoEmpleado entidad) throws SQLException {
        ps.setInt(1, entidad.getId_sexoEmpleado());
        ps.setString(2, entidad.getNombreSexoEmpleado());
    }

    @Override
    protected void setMapeoParametrosActualizar(PreparedStatement ps, SexoEmpleado entidad) throws SQLException {
        ps.setInt(1, entidad.getId_sexoEmpleado());
        ps.setString(2, entidad.getNombreSexoEmpleado());
        ps.setInt(3, entidad.getId_sexoEmpleado());
    }

    @Override
    protected String getLlaveTabla() {
        return "SEX_ID";
    }

    @Override
    protected String[] getColumnasDeLaTabla() {
        String[] str = {"SEX_ID", "SEX_NOMBRE"};
        return str;
    }

    @Override
    protected String getNombreTabla() {
        return "APL_SEX_SEXO";
    }

}
