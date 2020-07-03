/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.EstadoCivil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author carlosGodoy
 */
public class EstadoCivilDAO extends PatronDAO<EstadoCivil> {

    @Override
    protected EstadoCivil getMapeoDeResultados(ResultSet rs) throws SQLException {
        return new EstadoCivil(
                rs.getInt("ECI_ID"),
                rs.getString("ECI_NOMBRE")
        );
    }

    @Override
    protected void setMapeoParametrosInsertar(PreparedStatement ps, EstadoCivil entidad) throws SQLException {
        ps.setInt(1, entidad.getId_estadoCivil());
        ps.setString(2, entidad.getNombreEstadoCivil());
    }

    @Override
    protected void setMapeoParametrosActualizar(PreparedStatement ps, EstadoCivil entidad) throws SQLException {
        ps.setInt(1, entidad.getId_estadoCivil());
        ps.setString(2, entidad.getNombreEstadoCivil());
        ps.setInt(3, entidad.getId_estadoCivil());
    }

    @Override
    protected String getLlaveTabla() {
        return "ECI_ID";
    }

    @Override
    protected String[] getColumnasDeLaTabla() {
        String[] str = {"ECI_ID", "ECI_NOMBRE"};
        return str;
    }

    @Override
    protected String getNombreTabla() {
        return "APL_ECI_ECIVIL";
    }

}
