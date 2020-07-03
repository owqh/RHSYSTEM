/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.Planilla;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author carlosGodoy
 */
public class PlanillaDAO extends PatronDAO<Planilla> {

    @Override
    protected Planilla getMapeoDeResultados(ResultSet rs) throws SQLException {
        return new Planilla(
                rs.getInt("PLA_ID"),
                rs.getFloat("PLA_AFP_PATRONAL"),
                rs.getFloat("PLA_AFP_LABORAL"),
                rs.getFloat("PLA_ISSS_PATRONAL"),
                rs.getFloat("PLA_ISSS_LABORAL"),
                rs.getFloat("PLA_RENTA"),
                rs.getInt("EMP_ID")
        );
    }

    @Override
    protected void setMapeoParametrosInsertar(PreparedStatement ps, Planilla entidad) throws SQLException {
        ps.setInt(1, entidad.getId_planilla());
        ps.setFloat(2, entidad.getAfpPatronal());
        ps.setFloat(3, entidad.getAfpLaboral());
        ps.setFloat(4, entidad.getIsssPatronal());
        ps.setFloat(5, entidad.getIsssLaboral());
        ps.setFloat(6, entidad.getRenta());
        ps.setInt(7, entidad.getId_empleado_fk());
       
    }

    @Override
    protected void setMapeoParametrosActualizar(PreparedStatement ps, Planilla entidad) throws SQLException {
      ps.setInt(1, entidad.getId_planilla());
        ps.setFloat(2, entidad.getAfpPatronal());
        ps.setFloat(3, entidad.getAfpLaboral());
        ps.setFloat(4, entidad.getIsssPatronal());
        ps.setFloat(5, entidad.getIsssLaboral());
        ps.setFloat(6, entidad.getRenta());
        ps.setInt(7, entidad.getId_empleado_fk());
        ps.setInt(8, entidad.getId_planilla());
    }

    @Override
    protected String getLlaveTabla() {
        return "PLA_ID";
    }

    @Override
    protected String[] getColumnasDeLaTabla() {
        String[] str = {"PLA_ID", "PLA_AFP_PATRONAL", "PLA_AFP_LABORAL", "PLA_ISSS_PATRONAL", "PLA_ISSS_LABORAL",
             "PLA_RENTA", "EMP_ID"};
        return str;
    }

    @Override
    protected String getNombreTabla() {
        return "APL_PLA_PLANILLA";
    }

}
