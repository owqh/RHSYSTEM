/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;


import com.bitlab.entidades.Empleado;
import com.bitlab.entidades.Usuario;
import com.bitlab.principal.Proyecto4;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author carlosGodoy
 */
public class UsuarioDAO extends PatronDAO<Usuario> {
 private static final Logger logger = LoggerFactory.getLogger(Proyecto4.class);
    @Override
    protected Usuario getMapeoDeResultados(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("USR_ID"),
                rs.getString("USR_ACCESO"),
                rs.getString("USR_NOMBRE"),
                rs.getString("USR_APELLIDO"),
                rs.getString("USR_CONTRASENA"),
                rs.getInt("TIP_ID"));
    }

    @Override
    protected void setMapeoParametrosInsertar(PreparedStatement ps, Usuario entidad) throws SQLException {
        ps.setInt(1, entidad.getId_usuario());
        ps.setString(2, entidad.getAcceso_usuario());
        ps.setString(3, entidad.getNombre_usuario());
        ps.setString(4, entidad.getApellido_usuario());
        ps.setString(5, entidad.getContra_usuario());
        ps.setInt(6, entidad.getTipo_id_fk());
       
        
    }

    @Override
    protected void setMapeoParametrosActualizar(PreparedStatement ps, Usuario entidad) throws SQLException {
        ps.setInt(1, entidad.getId_usuario());
        ps.setString(2, entidad.getAcceso_usuario());
        ps.setString(3, entidad.getNombre_usuario());
        ps.setString(4, entidad.getApellido_usuario());
        ps.setString(5, entidad.getContra_usuario());
        ps.setInt(6, entidad.getTipo_id_fk());
        ps.setInt(7, entidad.getId_usuario());
    }

    @Override
    protected String getLlaveTabla() {
        return "USR_ID";
    }

    @Override
    protected String[] getColumnasDeLaTabla() {
        String[] str = {"USR_ID", "USR_ACCESO", "USR_NOMBRE", "USR_APELLIDO",
            "USR_CONTRASENA", "TIP_ID"};
        return str;
    }


    @Override
    protected String getNombreTabla() {
        return "APL_USR_USUARIO";
    }
    
    //Metodo para obtener todos los datos segun la cantidad maxima establecido en la constante.
    public List<Usuario> getLogin(String correo, String pass) throws ClassNotFoundException, SQLException, IOException{
        Connection con = getConectando(); //Se conecta a la BD
        PreparedStatement st = con.prepareStatement("SELECT USR_ID, USR_ACCESO, USR_NOMBRE, USR_APELLIDO,USR_CONTRASENA, TIP_ID FROM HRSYSTEM.APL_USR_USUARIO WHERE USR_ACCESO = '"+correo+"' and USR_CONTRASENA='"+pass+"';"); //Crea el Statement
        st.setMaxRows(1); //aplica el máximo de registros.
        logger.debug("Realizando validacion de usuario en bd");
        ResultSet rs = st.executeQuery(); //Ejecuta la query        
        
        List<Usuario> objects = new ArrayList<>();
        while(rs.next()){ //Si la BD encuentra registros por cada uno itera.
        objects.add(  new Usuario(
                rs.getInt("USR_ID"),
                rs.getString("USR_ACCESO"),
                rs.getString("USR_NOMBRE"),
                rs.getString("USR_APELLIDO"),
                rs.getString("USR_CONTRASENA"),
                rs.getInt("TIP_ID")));
        }
        CerrandoOcjetosJDBC(con, st, rs);
        return objects;
    }


}
