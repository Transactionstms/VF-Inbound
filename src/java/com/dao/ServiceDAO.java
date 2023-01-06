/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.PaqueteriaBean;
import com.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author SergioCastro
 */
public class ServiceDAO extends Conexion {

    java.sql.Statement stmt;
   

    public ResultSet consulta(String sql) throws SQLException {
         Connection con;
        con = this.conectar();
        java.sql.Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        return res;
    }

    public boolean save(String sql) {

        boolean bandera = false;

        try {
            java.sql.Statement stmt = this.conectar().createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return bandera;
    }
    public void savesql(String sql)throws SQLException {
        try {
            java.sql.Statement stmt = this.conectar().createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
            throw ex;
        } finally {
            this.CerrarConexion();
        }
    }    

    public boolean update(String sql) {

        
        try {
            java.sql.Statement stmt = this.conectar().createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
            return false;
        }
       
    }

    public boolean insert(PaqueteriaBean bean) throws ParseException {
        boolean bandera = false;

        String insertTableSQL = "INSERT INTO ONTMS_EMBARQUE_PAQUETERIA "
                + " ( EMBARQUE_PAQ_ID,EMBARQUE_PAQ,EMBARQUE_PAQ_CAPTURA,"
                + " EMBARQUE_PAQ_REVISION,EMBARQUE_PAQ_INICIO,EMBARQUE_PAQ_FIN, "
                + " EMBARQUE_PAQ_AUDITOR,PAQUETERIA_ID,CHOFER,CAMION,"
                + " EMBARQUE_PAQ_AGRUPADOR,EMBARQUE_PAQ_COSTO_REAL, "
                + " EMBARQUE_PAQ_LIBERACION,EMBARQUE_PAQ_ESTADO_ID,"
                + " EMBARQUE_PAQ_FEC_FLETE )"
                + " VALUES "
                + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {

            java.sql.Date dateREVISION = null;
            java.sql.Date dateInicio = null;
            java.sql.Date dateFin = null;
            java.sql.Date dateActual = null;
            java.sql.Date dateFlete = null;

            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");


            SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
            dateREVISION = new java.sql.Date(sdf.parse(f.format(new Date())).getTime());
            dateInicio = new java.sql.Date(sdf.parse(f.format(new Date())).getTime());
            dateFin = new java.sql.Date(sdf.parse(f.format(new Date())).getTime());
            dateActual = new java.sql.Date(sdf.parse(f.format(new Date())).getTime());
            dateFlete = new java.sql.Date(sdf.parse(f.format(new Date())).getTime());
            /*dateREVISION = new java.sql.Date(sdf.parse(bean.getEMBARQUE_PAQ_REVISION()).getTime());
             dateInicio = new java.sql.Date(sdf.parse(bean.getEMBARQUE_PAQ_INICIO()).getTime());
             dateFin = new java.sql.Date(sdf.parse(bean.getEMBARQUE_PAQ_FIN()).getTime());
             dateActual = new java.sql.Date(sdf.parse( f.format(new Date())).getTime());
             dateFlete = new java.sql.Date(sdf.parse( bean.getEMBARQUE_PAQ_FEC_FLETE()).getTime());
             */


            PreparedStatement st = this.conectar().prepareStatement(insertTableSQL);
            //st.setInt(1, Integer.parseInt(bean.getEMBARQUE_PAQ_ID()));
            st.setString(1, null);
            st.setString(2, bean.getEMBARQUE_PAQ());
            st.setDate(3, dateActual);
            st.setDate(4, dateREVISION);
            st.setDate(5, dateInicio);
            st.setDate(6, dateFin);
            st.setString(7, bean.getEMBARQUE_PAQ_AUDITOR());
            st.setInt(8, Integer.parseInt(bean.getPAQUETERIA_ID()));
            st.setString(9, bean.getCHOFER());
            st.setString(10, bean.getCAMION());
            st.setString(11, bean.getEMBARQUE_PAQ_AGRUPADOR());
            st.setDouble(12, Double.parseDouble(bean.getEMBARQUE_PAQ_COSTO_REAL()));
            st.setString(13, bean.getEMBARQUE_PAQ_LIBERACION());
            st.setInt(14, Integer.parseInt(bean.getEMBARQUE_PAQ_ESTADO_ID()));
            st.setDate(15, dateFlete);
            bandera = st.execute();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return bandera;
    }

    public boolean insertTmp(PaqueteriaBean bean) throws ParseException {
        boolean bandera = false;

        String insertTableSQL = "INSERT INTO ONTMS_EMBARQUE_PAQ_REIMP "
                + " ( EMBARQUE_PAQ_ID,EMBARQUE_PAQ,EMBARQUE_PAQ_CAPTURA,"
                + " EMBARQUE_PAQ_REVISION,EMBARQUE_PAQ_INICIO,EMBARQUE_PAQ_FIN, "
                + " EMBARQUE_PAQ_AUDITOR,PAQUETERIA_ID,CHOFER,CAMION,"
                + " EMBARQUE_PAQ_AGRUPADOR,EMBARQUE_PAQ_COSTO_REAL, "
                + " EMBARQUE_PAQ_LIBERACION,EMBARQUE_PAQ_ESTADO_ID,"
                + " EMBARQUE_PAQ_FEC_FLETE )"
                + " VALUES "
                + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {

            java.sql.Date dateREVISION = null;
            java.sql.Date dateInicio = null;
            java.sql.Date dateFin = null;
            java.sql.Date dateActual = null;
            java.sql.Date dateFlete = null;

            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");


            SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
            dateREVISION = new java.sql.Date(sdf.parse(bean.getEMBARQUE_PAQ_REVISION()).getTime());
            dateInicio = new java.sql.Date(sdf.parse(bean.getEMBARQUE_PAQ_INICIO()).getTime());
            dateFin = new java.sql.Date(sdf.parse(bean.getEMBARQUE_PAQ_FIN()).getTime());
            dateActual = new java.sql.Date(sdf.parse(f.format(new Date())).getTime());
            dateFlete = new java.sql.Date(sdf.parse(bean.getEMBARQUE_PAQ_FEC_FLETE()).getTime());



            PreparedStatement st = this.conectar().prepareStatement(insertTableSQL);
            //st.setInt(1, Integer.parseInt(bean.getEMBARQUE_PAQ_ID()));
            st.setString(1, null);
            st.setString(2, bean.getEMBARQUE_PAQ());
            st.setDate(3, dateActual);
            st.setDate(4, dateREVISION);
            st.setDate(5, dateInicio);
            st.setDate(6, dateFin);
            st.setString(7, bean.getEMBARQUE_PAQ_AUDITOR());
            st.setInt(8, Integer.parseInt(bean.getPAQUETERIA_ID()));
            st.setString(9, bean.getCHOFER());
            st.setString(10, bean.getCAMION());
            st.setString(11, bean.getEMBARQUE_PAQ_AGRUPADOR());
            st.setDouble(12, Double.parseDouble(bean.getEMBARQUE_PAQ_COSTO_REAL()));
            st.setString(13, bean.getEMBARQUE_PAQ_LIBERACION());
            st.setInt(14, Integer.parseInt(bean.getEMBARQUE_PAQ_ESTADO_ID()));
            st.setDate(15, dateFlete);
            bandera = st.execute();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return bandera;
    }

    public void desconectarService(ResultSet res) {
        this.desconectar(null, stmt, res);
    }
    
    
    public List doMapQuery(String sqlQuery,RowMapper mapper) {
            //com.management.util.Loggin.info("doMapQuery:"+sqlQuery);
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List outComesForUpdate = new ArrayList(0);
        int rowNum = 0;
        try {
            conn = this.conectar();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                Object vo = mapper.mapRow(rs, rowNum);
                outComesForUpdate.add(vo);
                rowNum++;
            }
        } catch (Throwable e) {
            com.management.util.Loggin.error(e);
            e.printStackTrace(System.err);
        } finally {
            try {
                this.desconectar(conn, stmt, rs);
                conn=null;
                stmt=null;
                rs=null;
            } catch (Exception e) {
                com.management.util.Loggin.error(e);
            }
        }
        return outComesForUpdate;                
    }            
    
}
