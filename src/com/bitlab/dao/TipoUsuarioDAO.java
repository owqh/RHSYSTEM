/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.TipoUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author carlosGodoy
 */
public class TipoUsuarioDAO extends PatronDAO<TipoUsuario> {

    @Override
    protected TipoUsuario getMapeoDeResultados(ResultSet rs) throws SQLException {
        return new TipoUsuario(
                rs.getInt("TIP_ID"),
                rs.getString("TIP_NOMBRE")
        );
    }

    @Override
    protected void setMapeoParametrosInsertar(PreparedStatement ps, TipoUsuario entidad) throws SQLException {
        ps.setInt(1, entidad.getId_TipoUsuario());
        ps.setString(2, entidad.getNombreTipoUsuario());
    }

    @Override
    protected void setMapeoParametrosActualizar(PreparedStatement ps, TipoUsuario entidad) throws SQLException {
        ps.setInt(1, entidad.getId_TipoUsuario());
        ps.setString(2, entidad.getNombreTipoUsuario());
        ps.setInt(3, entidad.getId_TipoUsuario());
    }

    @Override
    protected String getLlaveTabla() {
        return "TIP_ID";
    }

    @Override
    protected String[] getColumnasDeLaTabla() {
        String[] str = {"TIP_ID", "TIP_NOMBRE"};
        return str;
    }

    @Override
    protected String getNombreTabla() {
        return "APL_TIP_TIPO_USUARIO";
    }

}
