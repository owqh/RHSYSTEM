/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.Salario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author carlosGodoy
 */
public class SalarioDAO extends PatronDAO<Salario> {

    @Override
    protected Salario getMapeoDeResultados(ResultSet rs) throws SQLException {
        return new Salario(
                rs.getInt("SAL_ID"),
                rs.getFloat("SAL_SALARIO"),
                rs.getDate("SAL_FECHA_INICIO"),
                rs.getDate("SAL_FECHA_FIN"),
                rs.getInt("EMP_ID")
        );
    }

    @Override
    protected void setMapeoParametrosInsertar(PreparedStatement ps, Salario entidad) throws SQLException {

        ps.setInt(1, entidad.getId_salario());
        ps.setFloat(2, entidad.getId_salario());
        ps.setDate(3, entidad.getFechaInicioSalario());
        ps.setDate(4, entidad.getFechaFinSalario());
        ps.setInt(5, entidad.getIdEmpleadoFK());
    }

    @Override
    protected void setMapeoParametrosActualizar(PreparedStatement ps, Salario entidad) throws SQLException {
        
        ps.setInt(1, entidad.getId_salario());
        ps.setFloat(2, entidad.getId_salario());
        ps.setDate(3, entidad.getFechaInicioSalario());
        ps.setDate(4, entidad.getFechaFinSalario());
        ps.setInt(5, entidad.getIdEmpleadoFK());
        ps.setInt(6, entidad.getId_salario());
    }

    @Override
    protected String getLlaveTabla() {
        return "SAL_ID";
    }

    @Override
    protected String[] getColumnasDeLaTabla() {
        String[] str = {"SAL_ID", "SAL_SALARIO", "SAL_FECHA_INICIO", "SAL_FECHA_FIN",
            "EMP_ID"};
        return str;
    }

    @Override
    protected String getNombreTabla() {
        return "APL_SAL_SALARIO";
    }

}
