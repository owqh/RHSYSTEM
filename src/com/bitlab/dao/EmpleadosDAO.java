/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import static com.bitlab.conexion.ConexionBD.AbrirConexion;
import com.bitlab.entidades.Empleado;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlosGodoy
 */
public class EmpleadosDAO {

    public List<Empleado> obtenerTodoDatos() throws ClassNotFoundException, SQLException, IOException {
        String sql = "select * from employees LIMIT 10";
        Connection con = AbrirConexion();
//        Statement st = con.createStatement();
        ResultSet rs = con.createStatement().executeQuery(sql);

        List<Empleado> empleado = new ArrayList<>();
        while (rs.next()) {
            empleado.add(new Empleado(rs.getInt("emp_no"),
                    rs.getDate("birth_date"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("gender"),
                    rs.getDate("hire_date")));

        }
        rs.close();
        con.close();
        return empleado;

    }
}
