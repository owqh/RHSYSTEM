/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.EstadoDepartamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author carlosGodoy
 */
public class EstadoDepartamentoDAO extends PatronDAO<EstadoDepartamento> {

    @Override
    protected EstadoDepartamento getMapeoDeResultados(ResultSet rs) throws SQLException {
        return new EstadoDepartamento(
                rs.getInt("EDE_ID"),
                rs.getString("EDE_NOMBRE")
                );
    }

    @Override
    protected void setMapeoParametrosInsertar(PreparedStatement ps, EstadoDepartamento entidad) throws SQLException {
          ps.setInt(      1, entidad.getId_EstadoDepartament());
        ps.setString(   2, entidad.getNombre_EstadoDepartamento());
    }

    @Override
    protected void setMapeoParametrosActualizar(PreparedStatement ps, EstadoDepartamento entidad) throws SQLException {
        ps.setInt(      1, entidad.getId_EstadoDepartament());
        ps.setString(   2, entidad.getNombre_EstadoDepartamento());
        ps.setInt(      3, entidad.getId_EstadoDepartament());
    }
    @Override
      protected String getLlaveTabla() {
       return"EDE_ID";
    }

    @Override
    protected String[] getColumnasDeLaTabla() {
        String[] str = {"EDE_ID", "CAR_NOMBRE"};
        return str;
    }

    @Override
    protected String getNombreTabla() {
        return"APL_EDE_ESTADO_DEP";
    }

  
    
}
