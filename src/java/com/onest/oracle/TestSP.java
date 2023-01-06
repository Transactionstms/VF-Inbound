/*    */ package com.onest.oracle;
/*    */ 
/*    */ import com.onest.net.Connection;
/*    */ 
/*    */ public class TestSP
/*    */ {
/*    */   public static void main(String[] argv)
/*    */   {
/* 15 */     DBConfData data = new DBConfData();
/* 16 */     String[] inParam = new String[4];
/*    */ 
/* 18 */     inParam[0] = "12345678901234";
/* 19 */     inParam[1] = "12345678901234";
/* 20 */     inParam[2] = "A";
/* 21 */     inParam[3] = "432";
/* 22 */     int[] dataTypes = new int[5];
/* 23 */     Connection conn = new Connection("1", "192.168.2.252", "", "Servidor ISSSTE", "SICA", "1521", "IPRUEBADAT", "IPRUEBADAT", "Pruebas ISSSTE");
/* 24 */     data.setConf(conn);
/* 25 */     String oracleQuery = "BEGIN ? := SICA_FN_DEVOLUCIONSITYF(?,?,?,?); END;";
/* 26 */     OracleDB db = new OracleDB(data.getIPv4(), data.getPuerto(), data.getSid());
/* 27 */     db.connect(data.getUser(), data.getPassword());
/* 28 */     String[] outParam = new String[1];
/* 29 */     outParam[0] = "primer";
/* 30 */     dataTypes[0] = 4;
/* 31 */     dataTypes[1] = 12;
/* 32 */     dataTypes[2] = 12;
/* 33 */     dataTypes[3] = 12;
/* 34 */     dataTypes[4] = 2;
/* 35 */     db.execStore(oracleQuery, outParam, inParam, dataTypes);
/* 36 */     db.close();
/*    */   }
/*    */ }

/* Location:           C:\Users\miguel\Documents\NetBeansProjects\Hiltixy\lib\onest-common-utils-0.0.1.jar
 * Qualified Name:     com.onest.oracle.TestSP
 * JD-Core Version:    0.6.2
 */