package com.io;

import com.io.OracleDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ADO {

    private String[][] result;
    private Connection connection;
    private int numOfCol = 0;
    private int numOfRow = 0;
    private String[] columnNames;

    public ADO(String serverName, String portNum, String SID, String user, String secret, String dbKind) {
        if (dbKind.equals("oracle")) {
            OracleDB oradb = new OracleDB(serverName, portNum, SID);
            this.connection = oradb.connect(user, secret);
        }
    }
    
    public boolean batchInsert(List<String> inserts){
        int[] counts = null;
        try{
            if(inserts!=null && !inserts.isEmpty()){
                this.connection.setAutoCommit(false);
                Statement stmt = this.connection.createStatement();
                for(String next : inserts){
                   stmt.addBatch(next);
                }
                counts = stmt.executeBatch();
                stmt.close();
                this.connection.commit();
            }
        }catch(Exception e){
            counts = null;
            e.printStackTrace();
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally{
            try {
                this.connection.setAutoCommit(true);
                this.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
     return (counts!=null&&counts.length>0)?true:false;
    }

    public boolean execute(String query) {
        String queryTmp = query;
        try {
            if ((queryTmp.toUpperCase().indexOf("INSERT") != -1) || (queryTmp.toUpperCase().indexOf("DELETE") != -1) || (queryTmp.toUpperCase().indexOf("UPDATE") != -1)) {
                Statement stmt = this.connection.createStatement();
                int res = stmt.executeUpdate(query);
                stmt.close();
                return true;
            }
            Statement stmt = this.connection.createStatement(1004, 1007);
            ResultSet rset = stmt.executeQuery(query);
            ResultSetMetaData rinfo = rset.getMetaData();
            int index = 0;
            this.numOfCol = rinfo.getColumnCount();
            rset.last();
            this.numOfRow = (rset.getRow() == 0 ? 1 : rset.getRow());
            rset.beforeFirst();
            this.result = new String[this.numOfRow][this.numOfCol];
            this.columnNames = new String[this.numOfCol];
            if (rset.next()) {
                rset.beforeFirst();
                while (rset.next()) {
                    for (int i = 1; i <= this.numOfCol; i++) {
                        this.result[index][(i - 1)] = rset.getString(i);
                    }
                    index++;
                }
            } else {
                this.result = new String[1][1];
                this.result[0][0] = "No hay registros";
                return false;
            }
            for (int i = 1; i <= this.numOfCol; i++) {
                this.columnNames[(i - 1)] = rinfo.getColumnName(i);
            }
            rset.close();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Falló ejecucción: " + e.toString());
            System.out.println("sentencia: " + query);
            return false;
        } catch (NullPointerException e) {
            System.out.println( "Falló conexión a base de datos: " + e.toString());
            System.out.println("sentencia: " + query);
        }
        return false;
    }
    
    public ResultSet executeprepared(String query) throws ClassNotFoundException, SQLException {
        PreparedStatement stmt = this.connection.prepareStatement(query,1004, 1007);
        stmt.setFetchSize(1000);
        ResultSet rset = stmt.executeQuery(query);
        ResultSetMetaData rinfo = rset.getMetaData();
        this.numOfCol = rinfo.getColumnCount();
        rset.last();
        this.numOfRow = (rset.getRow() == 0 ? 1 : rset.getRow());
        rset.beforeFirst();
        this.result = new String[this.numOfRow][this.numOfCol];
        this.columnNames = new String[this.numOfCol];
        return rset;
    }    

    public ResultSet execute(String query, boolean regresaRSet) throws ClassNotFoundException, SQLException {
        Statement stmt = this.connection.createStatement(1004, 1007);
        ResultSet rset = stmt.executeQuery(query);
        ResultSetMetaData rinfo = rset.getMetaData();
        this.numOfCol = rinfo.getColumnCount();
        rset.last();
        this.numOfRow = (rset.getRow() == 0 ? 1 : rset.getRow());
        rset.beforeFirst();
        this.result = new String[this.numOfRow][this.numOfCol];
        this.columnNames = new String[this.numOfCol];
        return rset;
    }

    public String execStore(String query, String[] outParams, String[] inParams, int[] paramTypes) {
        try {
            int numOfParams = 1;
            CallableStatement stmt = this.connection.prepareCall(query);
            for (int i = 0; i < outParams.length; i++) {
                stmt.registerOutParameter(numOfParams, paramTypes[i]);
                numOfParams++;
            }
            for (int i = 0; i < inParams.length; i++) {
                stmt.setString(numOfParams, inParams[i]);
                numOfParams++;
            }
            stmt.execute();
            Object obj = stmt.getObject(1);
            return obj.toString();
        } catch (SQLException e) {
            System.out.println("Falló ejecucción: " + e.toString());
            System.out.println("sentencia: " + query);
        }
        return "-1";
    }

    public ResultSet execStore(String query, String[] outParams, String[] inParams, int[] paramTypes, boolean regresaRSet) throws ClassNotFoundException, SQLException {
        int numOfParams = 1;
        CallableStatement stmt = this.connection.prepareCall(query);
        for (int i = 0; i < outParams.length; i++) {
            stmt.registerOutParameter(numOfParams, paramTypes[i]);
            numOfParams++;
        }
        for (int i = 0; i < inParams.length; i++) {
            stmt.setString(numOfParams, inParams[i]);
            numOfParams++;
        }
        stmt.execute();
        ResultSet obj = (ResultSet) stmt.getObject(1);
        return obj;
    }
    
    public void close() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Al cerrar conexión: " + e.toString());
        }
    }

    public int getNumOfCol() {
        return this.numOfCol;
    }

    public int getNumOfRow() {
        return this.numOfRow;
    }

    public String[][] getResult() {
        return this.result;
    }

    public String[] getColumnNames() {
        return this.columnNames;
    }
}