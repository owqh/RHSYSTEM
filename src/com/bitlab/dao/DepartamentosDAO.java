/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.dao;

import com.bitlab.entidades.Departamentos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cognizant32-12
 */
public class DepartamentosDAO extends PatronDAO<Departamentos>{
    
    @Override
    //Implementacion del metodo desde la super clase 'PatronDAO' para mapear todos los registros de la tabla : APL_DEP_DEPARTAMENTO
    protected Departamentos getMapeoDeResultados(ResultSet rs) throws SQLException {
        return new Departamentos(rs.getString("DEP_ID"), rs.getString("DEP_NOMBRE"));
    }
    
    @Override
    //Implementacion del metodo desde la super clase 'PatronDAO' para insertar registros a la tabla : APL_DEP_DEPARTAMENTO
    protected void setMapeoParametrosInsertar(PreparedStatement ps, Departamentos entidad) throws SQLException {
        ps.setString(1, entidad.getIdDepartamento());
        ps.setString(2, entidad.getNombreDepartamento());
    }
    
    @Override
    //Implementacion del metodo desde la super clase 'PatronDAO' para actualizar registros a la tabla : APL_DEP_DEPARTAMENTO
    protected void setMapeoParametrosActualizar(PreparedStatement ps, Departamentos entidad) throws SQLException {
        ps.setString(1, entidad.getIdDepartamento());
        ps.setString(2, entidad.getNombreDepartamento());
        ps.setString(3, entidad.getIdDepartamento());
    }
    
    
    @Override
    //Accede a la tabla por medio de su nombre(entidad).
    protected String getNombreTabla() {
        return "APL_DEP_DEPARTAMENTO";
    }
    
    @Override
    //Encuentra los elementos por medio de el numero ID de el campo de dicha tabla.
    protected String getLlaveTabla() {
        return "DEP_ID";
    }
    
    @Override
    //Selecciona las columnas encontrandolas por el nombre indicado.
    protected String[] getColumnasDeLaTabla() {
        String[] tablaColumnas = {"DEP_ID","DEP_NOMBRE"};
        return tablaColumnas;
    }

}
