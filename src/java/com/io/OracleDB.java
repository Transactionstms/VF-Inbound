 package com.io;
 
 import java.sql.Connection;
 import java.sql.SQLException;
 import oracle.jdbc.pool.OracleDataSource;
 
 public class OracleDB
 {
   private String url = "";
   private OracleDataSource oracleDS;
   private Connection connection;
   private String[][] result;
   private int numOfCol = 0;
   private int numOfRow = 0;
   private String[] columnNames;
   public static final int ORA_VARCHAR = 12;
   public static final int ORA_NUMBER = 2;
   public static final int ORA_INTEGER = 4;
 
   public OracleDB(String serverName, String portNum, String SID)
   {
     this.url = ("jdbc:oracle:thin:@" + serverName + ":" + portNum + ":" + SID);
   }
   public Connection connect(String user, String password) {
     try {
       this.oracleDS = new OracleDataSource();
       this.oracleDS.setUser(user);
       this.oracleDS.setPassword(password);
       this.oracleDS.setURL(this.url);
      this.connection = this.oracleDS.getConnection();
       return this.connection;
     } catch (SQLException e) {
       e.printStackTrace();
     }return null;
   }

  
 }

