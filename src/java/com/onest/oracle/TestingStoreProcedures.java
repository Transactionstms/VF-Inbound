/*     */ package com.onest.oracle;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.sql.CallableStatement;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ public class TestingStoreProcedures
/*     */ {
/*  17 */   private boolean useOracleQuery = true;
/*  18 */   private String driver = "oracle.jdbc.OracleDriver";
/*  19 */   private String url = "jdbc:oracle:thin:@";
/*  20 */   private String port = "1521";
/*  21 */   private String oracleQuery = "begin ? := cesar1(?); end;";
/*  22 */   private String genericQuery = "{ call ? := cesar1(?) }";
/*  23 */   private Connection conn = null;
/*     */ 
/*     */   public TestingStoreProcedures(String host, String db, String user, String password)
/*     */     throws ClassNotFoundException, SQLException
/*     */   {
/*  30 */     this.url = (this.url + host + ":" + this.port + ":" + db);
/*     */     try
/*     */     {
/*  34 */       Class.forName(this.driver);
/*  35 */       this.conn = DriverManager.getConnection(this.url, user, password);
/*     */     }
/*     */     catch (ClassNotFoundException ex) {
/*  38 */       System.out.println("Failed to find driver class: " + this.driver);
/*  39 */       throw ex;
/*     */     }
/*     */     catch (SQLException ex) {
/*  42 */       System.out.println("Failed to establish a connection to: " + this.url);
/*  43 */       throw ex;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void execute(String sku)
/*     */     throws SQLException
/*     */   {
/*  54 */     String query = this.useOracleQuery ? this.oracleQuery : this.genericQuery;
/*  55 */     System.out.println("Query: " + query + "\n");
/*  56 */     CallableStatement stmt = this.conn.prepareCall(query);
/*     */ 
/*  59 */     stmt.registerOutParameter(1, 12);
/*     */ 
/*  62 */     stmt.setString(2, sku);
/*     */ 
/*  65 */     stmt.execute();
/*     */ 
/*  67 */     String rs = (String)stmt.getObject(1);
/*     */ 
/*  72 */     System.out.println(rs);
/*     */ 
/*  76 */     stmt.close();
/*     */   }
/*     */ 
/*     */   private void cleanup()
/*     */     throws SQLException
/*     */   {
/*  85 */     if (this.conn != null)
/*  86 */       this.conn.close();
/*     */   }
/*     */ 
/*     */   private static void usage()
/*     */   {
/*  95 */     System.out.println("java ");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/* 111 */       String host = "192.168.2.252";
/* 112 */       String db = "sica";
/* 113 */       String user = "issstedat";
/* 114 */       String password = "issstedat";
/* 115 */       String product = "7501000608249";
/*     */ 
/* 118 */       TestingStoreProcedures jdbc = new TestingStoreProcedures(host, db, user, password);
/* 119 */       jdbc.execute(product);
/* 120 */       jdbc.cleanup();
/*     */     }
/*     */     catch (ClassNotFoundException ex) {
/* 123 */       System.out.println("Demo failed");
/*     */     }
/*     */     catch (SQLException ex) {
/* 126 */       System.out.println("Demo failed: " + ex.getMessage());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\miguel\Documents\NetBeansProjects\Hiltixy\lib\onest-common-utils-0.0.1.jar
 * Qualified Name:     com.onest.oracle.TestingStoreProcedures
 * JD-Core Version:    0.6.2
 */