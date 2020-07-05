/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import com.bitlab.conexion.ConexionBD;
import java.io.IOException;

/**
 *
 * @author Manuel@Ramos
 * @param <T>
 */
public abstract class PatronDAO <T> {
    
    public static Logger log = Logger.getLogger(PatronDAO.class.getSimpleName());
    
    public final int LIMITE_DATOS = 100;
    
    //Bloque de constantes para el uso y manipulacion del query.
    public final String BD_WHERE = " WHERE ";
    public final String BD_SELECT = "SELECT [COLUMNS] FROM [TABLE]";
    public final String BD_INSERT = "INSERT INTO [TABLE] ([COLUMNS]) VALUES ([COLUMNS_INDEX])";
    public final String BD_DELETE = "DELETE FROM [TABLE] WHERE [CONDITIONS]";
    public final String BD_UPDATE = "UPDATE [TABLE] SET [COLUMNS] WHERE [CONDITIONS]";
    
    
    
    //Indicador de tabla
    private final String INDICADOR_TABLA = "[TABLE]";
    //Indicador de columnas en la tabla
    private final String INDICADOR_COLUMNAS = "[COLUMNS]";
    //Indicador de columnas por index
    private final String INDEX_COLUMNAS = "[COLUMNS_INDEX]";
    //Indicador de condiciones de la tabla
    private final String INDICADOR_CONDICION = "[CONDITIONS]";
    
    
    //Metodo para hacer un select en la base de datos.
    protected String getSelectSQL(){
        //se invoca la contante.
        String sql = BD_SELECT;
        sql = sql.replace(INDICADOR_TABLA, getNombreTabla()).replace(INDICADOR_COLUMNAS, Arrays.toString(
                getColumnasDeLaTabla())).replace("[", "").replace("]", ""); //Reemplazo de los corchetes del array.
        
        log.info("Dato SQL obtenido : "+sql);
        return sql;
    }
    
    
    //Metodo para eliminar elementos de la base de datos
    protected String getDeleteSQL(){
        //Reemplazando los comodines [TABLE] y [CONDITIONS] por las condiciones de "llaveTabla = ?"
        String sql = BD_DELETE.replace(INDICADOR_TABLA, getNombreTabla()).replace(INDICADOR_CONDICION, getLlaveTabla() + " = ?");
        log.info("Dato SQL eliminado : "+sql);
        return sql;
    }
    
    
    //Metodo para hacer un INSERT a la base de datos
    protected String getInsertSQL(){
        //se invoca la contante para insertar.
        String sql = BD_INSERT;
        //Concatenando elementos segun cantidad de columnas
        String sqlCodigoColumnas = "";
        for(int i= 0;i<getColumnasDeLaTabla().length;i++){
            sqlCodigoColumnas += "?,";
        }
        sqlCodigoColumnas = sqlCodigoColumnas.substring(0,sqlCodigoColumnas.length()-1);
        //Se reemplaza el [INDEX_COLUMNAS] con el  proceso de concatenación
        sql = sql.replace(INDEX_COLUMNAS, sqlCodigoColumnas);
        
        //Se reemplazan los valores de los comodines [TABLE] y [COLUMNS]
        sql = sql.replace(INDICADOR_TABLA, getNombreTabla())
                .replace(INDICADOR_COLUMNAS, Arrays.toString(
                        //Reemplazo de los corchetes del array.
                        getColumnasDeLaTabla())).replace("[", "").replace("]", "");
        
        log.info("Dato InserSQL Generado : "+sql);
        return sql;
    }
    
    
    //Metodo para hacer una actualizacion en la base de datos.
    protected String getUpdateSQL(){
        //se invoca la contante para actualizar.
        String sql = BD_UPDATE.replace(INDICADOR_CONDICION, getLlaveTabla()+ " = ?");
        //Reemplazando el comodin [TABLE]
        sql = sql.replace(INDICADOR_TABLA, getNombreTabla());
        //Concatenando elementos segun cantidad de columnas.
        StringBuilder strBuild = new StringBuilder();
        for(String str : getColumnasDeLaTabla()){
            strBuild.append(str).append("=?,");
        }
        //Reemplazamos el comodin y eliminamos la ultima "," que queda reflejada.
        sql = sql.replace(INDICADOR_COLUMNAS, strBuild.toString().substring(0,strBuild.toString().length()-1));
        
        log.info("Dato SQL actualizado : "+sql);
        return sql;
    }
    
    //Metodo para conectar al sql por medio de la clase ConexionBD
    protected Connection getConectando() throws ClassNotFoundException, SQLException, IOException{
        return ConexionBD.AbrirConexion();
    }
    
    
    //Metodos de cierre de los objetos de la LDBC.
    protected void CerrandoOcjetosJDBC(Connection con) throws SQLException{
        ConexionBD.CerrandoConexion(con);
    }
    protected void CerrandoOcjetosJDBC(Connection con, Statement st) throws SQLException{
        if(st!=null && !st.isClosed())
            st.close();
        ConexionBD.CerrandoConexion(con);
    }
    protected void CerrandoOcjetosJDBC(Connection con, PreparedStatement ps) throws SQLException{
        if(ps!=null && !ps.isClosed())
            ps.close();
        ConexionBD.CerrandoConexion(con);
    }
    protected void CerrandoOcjetosJDBC(Connection con, Statement st, ResultSet rs) throws SQLException{
        if(rs!=null && !rs.isClosed())
            rs.close();
        if(st!=null && !st.isClosed())
            st.close();
        ConexionBD.CerrandoConexion(con);
    }  
    protected void CerrandoOcjetosJDBC(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException{
        if(rs!=null && !rs.isClosed())
            rs.close();
        if(ps!=null && !ps.isClosed())
            ps.close();
        ConexionBD.CerrandoConexion(con);
    }
    
    
    //Metodo para obtener todos los datos segun el limite establecido en la constante
    public List<T> getTodosLosDatos() throws ClassNotFoundException, SQLException, IOException{
        return getTodosLosDatos(LIMITE_DATOS);
    }
    
    //Metodo para obtener todos los datos segun la cantidad maxima establecido en la constante.
    public List<T> getTodosLosDatos(int maximoDatos) throws ClassNotFoundException, SQLException, IOException{
        Connection con = getConectando(); //Se conecta a la BD
        Statement st = con.createStatement(); //Crea el Statement
        st.setMaxRows(maximoDatos); //aplica el máximo de registros.
        ResultSet rs = st.executeQuery(getSelectSQL()); //Ejecuta la query
        List<T> objects = new ArrayList<>();
        while(rs.next()){ //Si la BD encuentra registros por cada uno itera.
            objects.add(getMapeoDeResultados(rs));
        }
        CerrandoOcjetosJDBC(con, st, rs);
        return objects;
    }
    
    //Metodo que devuelve la cantidad de registros de una entidad por medio del ID
    public T getDatosPorID(Object id) throws ClassNotFoundException, SQLException, IOException{
        String sql = getSelectSQL() + BD_WHERE + getLlaveTabla()+ " = ?"; //Se usa SQL dinámico
        Connection con = getConectando(); //Se obtiene la conexión
        PreparedStatement ps = con.prepareStatement(sql); //Se prepara el Statement
        ps.setObject(1, id); //Se aplican los parametros
        ResultSet rs = ps.executeQuery(); // ResultSet para obtener los valores
        T e = null;
        if(rs.next()){ //Si la BD devolvio registros, se mapean y se asignan a la variable
            e = getMapeoDeResultados(rs);
        }
        CerrandoOcjetosJDBC(con, ps, rs);
        return e;
    }
    
    //Metodo para inserta registros a una entidad de la base de datos.
    public void insertDatos(T entidad) throws ClassNotFoundException, SQLException, IOException{
        Connection con = getConectando();//Se crea la conexión
        PreparedStatement ps = con.prepareStatement(getInsertSQL());//Se prepará la sentencia con el SQL Insert generado
        setMapeoParametrosInsertar(ps, entidad); //Se mapean los datos de la entidad al statement para enviarlos a la BD
        ps.execute();
        CerrandoOcjetosJDBC(con, ps);
    }
    
    //Metodo para eliminar registros a una entidad de la base de datos.
    public void EliminarDatos(Object id) throws ClassNotFoundException, SQLException, IOException{
        Connection con = getConectando();//Se obtiene la conexión
        PreparedStatement ps = con.prepareStatement(getDeleteSQL()); //Se aplica el Statement
        ps.setObject(1, id); //Se aplica el parametro de condición
        ps.execute();
        CerrandoOcjetosJDBC(con, ps);
    }
    
    //Metodo para actualizar registros a una entidad de la base de datos.
    public void ActualizarDatos(T entidad) throws ClassNotFoundException, SQLException, IOException{
        Connection con = getConectando();//Se obtiene la conexión
        PreparedStatement ps = con.prepareStatement(getUpdateSQL());//Se aplica el SQL
        setMapeoParametrosActualizar(ps,entidad);//Se hace el mapeo de la entidad al PreparedStatement
        ps.execute();
        CerrandoOcjetosJDBC(con, ps);
    }
    
    //Método abstracto que hace el mapeo del ResultSet hacia un objeto Entidad.
    protected abstract T getMapeoDeResultados(ResultSet rs) throws SQLException;
    
    //Método abstracto para aplicar un mapeo de la entidad hacia el PreparedStatement para aplicar un insert.
    protected abstract void setMapeoParametrosInsertar(PreparedStatement ps, T entidad)throws SQLException;
    
    //Método abstracto que hace un mapeo de la entidad hacia el PreparedStatement para hacer un update en la BD
    protected abstract void setMapeoParametrosActualizar(PreparedStatement ps, T entidad) throws SQLException;
    
    
    //Función abstracta que retorna el nombre de la Tabla que se estará gestionando.
    protected abstract String getLlaveTabla();
    
    //Función abstracta que retorna un arreglo de los nombres de las columnas de la BD segun su referencia.
    protected abstract String[] getColumnasDeLaTabla();
    
    //Función abstracta que retorna el nombre de la columna llave en la tabla
    protected abstract String getNombreTabla();
    
    
}
