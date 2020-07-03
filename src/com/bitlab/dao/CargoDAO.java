/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.Cargo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author carlosGodoy
 */
public class CargoDAO extends PatronDAO<Cargo> {

    @Override
    protected Cargo getMapeoDeResultados(ResultSet rs) throws SQLException {
        return new Cargo(
                rs.getInt("CAR_ID"),
                rs.getString("CAR_NOMBRE")
                );
    }

    @Override
    protected void setMapeoParametrosInsertar(PreparedStatement ps, Cargo entidad) throws SQLException {
        ps.setInt(      1, entidad.getId_cargo());
        ps.setString(   2, entidad.getNombre_Cargo());
        
        
    }

    @Override
    protected void setMapeoParametrosActualizar(PreparedStatement ps, Cargo entidad) throws SQLException {
        ps.setInt(      1, entidad.getId_cargo());
        ps.setString(   2, entidad.getNombre_Cargo());
        ps.setInt(      3, entidad.getId_cargo());
    }

    @Override
    protected String getLlaveTabla() {
     return "CAR_ID";   
    }

    @Override
    protected String[] getColumnasDeLaTabla() {
      String[] str = {"CAR_ID", "CAR_NOMBRE"};
        return str;
    }

    @Override
    protected String getNombreTabla() {
        return "APL_CAR_CARGO";
    }
    
}
