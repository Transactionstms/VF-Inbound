/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts;

/**
 *
 * @author grecendiz
 */
import java.sql.*;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
 
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExcelToOracleSP {
    
    public String urlG      = "jdbc:oracle:thin:@74.208.140.125:1521:tacts125";
    public String usernameG = "VANS39TEST";
    public String passwordG = "XUKidn49N875RBH54Cq2";

    public ExcelToOracleSP() {

    }

    
    public String InsertSpDSN(String insertSql, String inputFile2, String folio) throws Exception {
        // Configura la conexión a la base de datos Oracle
        String mensaje = " ";
        String url = urlG;

        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

        int timeout = 1600000; // 30 minutos
        Executor executor = Executors.newSingleThreadExecutor();
        connection.setNetworkTimeout(executor, timeout);

        // Configura la consulta SQL para insertar los registros
        //  String insertSql = "INSERT INTO TRA_TEST (campo1, campo2, campo3) VALUES (?, ?, ?)";
        String errorM = "";
        String correcto = "";

        // Abre el archivo de Excel
        // String inputFile = "D:\\datosdns.xlsx";
        String inputFile = inputFile2;//"D:\\DSNVN2.xls";
        FileInputStream inputStream = new FileInputStream(inputFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Recorre las filas del archivo de Excel y realiza las inserciones
        int batchSize = 100; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // PreparedStatement statement = connection.prepareStatement(insertSql);
        CallableStatement statement = connection.prepareCall(" {call SP_INB_DNSBATCH( ?,?,?,?,?,?,?,?,?,?  ,?,?,?,?,?,?,?,?,?,?,  ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?, '" + folio + "',?   )}");
        try {
            for (int i = 0; i <= rowCount; i++) {
                String mensajeReturn = "";
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int yy = 0;
                //row.getLastCellNum()
                for (int j = 0; j < 41; j++) {
                    Cell cell = row.getCell(j);
               //     System.out.println("+++"+j + 1+ cell.getStringCellValue());
                    if (cell == null) {
                        statement.setNull(j + 1, Types.NULL);
                    } else {

                        switch (cell.getCellType()) {
                            case STRING:
                                statement.setString(j + 1, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                statement.setDouble(j + 1, cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                statement.setBoolean(j + 1, cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                statement.setString(j + 1, cell.getCellFormula());
                                break;
                            default:
                                statement.setNull(j + 1, Types.NULL);
                                break;
                        }
                    }
                    //System.out.println("row.getLastCellNum()+1"+j+1);
                    yy++;
                }
                // statement.setString(j + 1, cell.getCellFormula()); 

                 statement.setString(42,  (i+1)+"" );
                 
                statement.addBatch();
                correcto += "<p>Agregado fila " + i + "</p>";
                if (i % batchSize == 0) {

                    // statement.executeBatch();
                    // statement.clearBatch();
                    // mensaje += "Linea " + i + " insertada";
                    try {
                        statement.executeBatch(); // Ejecuta el lote
                        statement.clearBatch();

                        System.out.println("correcto1-" + i);

                    } catch (SQLException e) {
                        System.out.println("error1-----" + i + "-----------");
                        errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + 1) + "</p>";

                        int[] updateCounts = statement.executeBatch();
                        for (int index = 0; index < updateCounts.length; index++) {
                            if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                                // Registro del registro que no se pudo insertar
                                System.out.println("Error al insertar el registro en la posición: " + (i - batchSize + index + 1));
                                errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + index + 1) + "</p>";
                            } else {
                                errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                            }
                        }
                    }

                }

            }

            try {
                statement.executeBatch(); // Ejecuta el lote
                // correcto += "<p>Agregado fila " + i + "</p>";
                System.out.println("correcto2" + batchSize + 1);
            } catch (SQLException e) {
                System.out.println("error2-----" + e + "-----------");
                errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + 1) + "</p>";

                int[] updateCounts = statement.executeBatch();
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                        System.out.println("Error al insertar el registro en la posición: " + (batchSize + index + 1));
                        errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    } else {
                        errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("e" + e);
            e.printStackTrace();
        }
         
        
                
                 
         // String gtn =    InsertSpACT (  folio);     

                 
        connection.close();
        inputStream.close();
        statement.close();
        
          
         
            
        String respueston =  "<br>"+ errorM + " <br>" + correcto;
        return respueston;
    }

    // gtn 
    public String InsertSp(String insertSql, String inputFile2, String folio) throws Exception {
        // Configura la conexión a la base de datos Oracle
        String mensaje = " ";
        String url = urlG;

        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

        int timeout = 1600000; // 30 minutos
        Executor executor = Executors.newSingleThreadExecutor();
        connection.setNetworkTimeout(executor, timeout);

        // Configura la consulta SQL para insertar los registros
        //  String insertSql = "INSERT INTO TRA_TEST (campo1, campo2, campo3) VALUES (?, ?, ?)";
        String errorM = "";
        String correcto = "";

        // Abre el archivo de Excel
        // String inputFile = "D:\\datosdns.xlsx";
        String inputFile = inputFile2;//"D:\\DSNVN2.xls";
        FileInputStream inputStream = new FileInputStream(inputFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Recorre las filas del archivo de Excel y realiza las inserciones
        int batchSize = 100; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // PreparedStatement statement = connection.prepareStatement(insertSql);
        CallableStatement statement = connection.prepareCall(" {call SP_INB_GTNBATCH( ?, ?,?,?,?,?,?,?,?,?,?     ,?,?,?,?,?,?,?,?,?,?,    ?,?,?,?,?,?,?,?,?,?, ?,?,?, '" + folio + "',?   )}");
        try {
            for (int i = 0; i <= rowCount; i++) {
                String mensajeReturn = "";
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int yy = 0;
                //row.getLastCellNum()
                for (int j = 0; j < 34; j++) {
                    Cell cell = row.getCell(j);
               //     System.out.println("+++"+j + 1+ cell.getStringCellValue());
                    if (cell == null) {
                        statement.setNull(j + 1, Types.NULL);
                    } else {

                        switch (cell.getCellType()) {
                            case STRING:
                                statement.setString(j + 1, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                statement.setDouble(j + 1, cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                statement.setBoolean(j + 1, cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                statement.setString(j + 1, cell.getCellFormula());
                                break;
                            default:
                                statement.setNull(j + 1, Types.NULL);
                                break;
                        }
                    }
                    //System.out.println("row.getLastCellNum()+1"+j+1);
                    yy++;
                }
                // statement.setString(j + 1, cell.getCellFormula()); 

                 statement.setString(35,  (i+1)+"" );
                 
                statement.addBatch();
                correcto += "<p>Agregado fila " + i + "</p>";
                if (i % batchSize == 0) {

                    // statement.executeBatch();
                    // statement.clearBatch();
                    // mensaje += "Linea " + i + " insertada";
                    try {
                        statement.executeBatch(); // Ejecuta el lote
                        statement.clearBatch();

                        System.out.println("correcto1-" + i);

                    } catch (SQLException e) {
                        System.out.println("error1T1-----" + i + "-----------");
                        errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + 1) + "</p>";

                        int[] updateCounts = statement.executeBatch();
                        for (int index = 0; index < updateCounts.length; index++) {
                            if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                                // Registro del registro que no se pudo insertar
                                 System.out.println("error1T2-----" + i + "-----------");
                                System.out.println("Error al insertar el registro en la posición: " + (i - batchSize + index + 1));
                                errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + index + 1) + "</p>";
                            } else {
                                  System.out.println("error1T3-----" + i + "-----------");
                                errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                            }
                        }
                    }

                }

            }

            try {
                statement.executeBatch(); // Ejecuta el lote
                // correcto += "<p>Agregado fila " + i + "</p>";
                System.out.println("correcto2" + batchSize + 1);
            } catch (SQLException e) {
                System.out.println("error2-----" + e + "-----------");
                  System.out.println("error1T4-----" + e + "-----------");
                errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + 1) + "</p>";

                int[] updateCounts = statement.executeBatch();
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                          System.out.println("error1T5-----" + (batchSize + index + 1) + "-----------");
                        System.out.println("Error al insertar el registro en la posición: " + (batchSize + index + 1));
                        errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    } else {
                         System.out.println("error1T6-----" + (batchSize + index + 1) + "-----------");
                        errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("e" + e);
            e.printStackTrace();
        }
         
        
                
                 
         // String gtn =    InsertSpACT (  folio);     

                 
        connection.close();
        inputStream.close();
        statement.close();
        
          
         
            
        String respueston =  "<br>"+ errorM + " <br>" + correcto;
        return respueston;
    }

    
    public String InsertSpACT( String folio) throws Exception {
        
        String url = urlG;
        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

    
      
 
        // CallableStatement cs = connection.prepareCall("{call sp_inb_actualizagtnShip(?)}");
          CallableStatement cs = connection.prepareCall("{call sp_inb_actualizaltd_gtnShip(?)}");

        
                 try {
                     System.out.println("actualizado sub name");
                    cs.setString(1, folio);
                    cs.execute();
         
                      
                     if (cs.getUpdateCount() >= 0) {
                        System.out.println("El stored procedure ha terminado de ejecutarse.");
                    } else {
                        System.out.println("El stored procedure no ha terminado de ejecutarse.");
                    }  
                 
                  
                 
                  } catch (SQLException e) {   
                     System.out.println("e1 "+e);
                  }
                    
               
                 
                 

                 
          connection.close();
          cs.close();
         // cs1.close(); 
          
         
            
        String respueston =   "Datos acualizados"  ;
        return respueston;
    }

    
    public String InsertSpACTExc( String folio) throws Exception {
        
        String url = urlG;
        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

    
      
 
        // CallableStatement cs = connection.prepareCall("{call sp_inb_actualizagtnShip(?)}");
          CallableStatement cs = connection.prepareCall("{call sp_inb_actualizaltd_gtnShipUni(?)}");

        
                 try {
                     System.out.println("actualizado sub name");
                    cs.setString(1, folio);
                    cs.execute();
         
                      
                     if (cs.getUpdateCount() >= 0) {
                        System.out.println("El stored procedure ha terminado de ejecutarse.");
                    } else {
                        System.out.println("El stored procedure no ha terminado de ejecutarse.");
                    }  
                 
                  
                 
                  } catch (SQLException e) {   
                     System.out.println("e1 "+e);
                  }
                    
               
                 
                 

                 
          connection.close();
          cs.close();
         // cs1.close(); 
          
         
            
        String respueston =   "Datos acualizados"  ;
        return respueston;
    }
    
     //ACTUALIZA DIVISION SCI
     public String actDivisionSCI( ) throws Exception {
        
        String url = urlG;
        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

    
      
 
        // CallableStatement cs = connection.prepareCall("{call sp_inb_actualizagtnShip(?)}");
          CallableStatement cs = connection.prepareCall("{call sp_inb_act_divicionSCI()}");

        
                 try {
                     System.out.println("actualizado sub name");
                   // cs.setString(1, folio);
                    cs.execute();
         
                      
                     if (cs.getUpdateCount() >= 0) {
                        System.out.println("El stored procedure ha terminado de ejecutarse.");
                    } else {
                        System.out.println("El stored procedure no ha terminado de ejecutarse.");
                    }  
                 
                  
                 
                  } catch (SQLException e) {   
                     System.out.println("e1 "+e);
                  }
                    
               
                 
                 

                 
          connection.close();
          cs.close();
         // cs1.close(); 
          
         
            
        String respueston =   "Datos acualizados"  ;
        return respueston;
    }
         
    //ACTUALIZA DIVISION
    public String actDivision( ) throws Exception {
        
        String url = urlG;
        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

    
      
 
        // CallableStatement cs = connection.prepareCall("{call sp_inb_actualizagtnShip(?)}");
          CallableStatement cs = connection.prepareCall("{call sp_inb_act_divicion()}");

        
                 try {
                     System.out.println("actualizado sub name");
                   // cs.setString(1, folio);
                    cs.execute();
         
                      
                     if (cs.getUpdateCount() >= 0) {
                        System.out.println("El stored procedure ha terminado de ejecutarse.");
                    } else {
                        System.out.println("El stored procedure no ha terminado de ejecutarse.");
                    }  
                 
                  
                 
                  } catch (SQLException e) {   
                     System.out.println("e1 "+e);
                  }
                    
               
                 
                 

                 
          connection.close();
          cs.close();
         // cs1.close(); 
          
         
            
        String respueston =   "Datos acualizados"  ;
        return respueston;
    }
    
    
    public String InsertSpActETA_ATC(String insertSql, String inputFile2, String folio) throws Exception {
        // Configura la conexión a la base de datos Oracle
        String mensaje = " ";
        String url = urlG;
        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

        int timeout = 1600000; // 30 minutos
        Executor executor = Executors.newSingleThreadExecutor();
        connection.setNetworkTimeout(executor, timeout);

         String errorM = "";
        String correcto = "";
         // Abre el archivo de Excel
        // String inputFile = "D:\\datosdns.xlsx";
        String inputFile = inputFile2;//"D:\\DSNVN2.xls";
        FileInputStream inputStream = new FileInputStream(inputFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        

        // Recorre las filas del archivo de Excel y realiza las inserciones
        int batchSize = 100; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // PreparedStatement statement = connection.prepareStatement(insertSql);
        CallableStatement statement = connection.prepareCall(" {call SP_INB_ETA_ATC( ?,?,?,?,?,?,?,?,?,?   ,?,?,? ,?,'" + folio + "'  )}");
        try {
            for (int i = 2; i <= rowCount; i++) {
                String mensajeReturn = "";
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int yy = 0;
                //row.getLastCellNum();
                for (int j = 0; j < 13; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        statement.setNull(j + 1, Types.NULL);
                    } else {

                        switch (cell.getCellType()) {
                            case STRING:
                                statement.setString(j + 1, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                statement.setDouble(j + 1, cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                statement.setBoolean(j + 1, cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                statement.setString(j + 1, cell.getCellFormula());
                                break;
                            default:
                                statement.setNull(j + 1, Types.NULL);
                                break;
                        }
                    }
                    //System.out.println("row.getLastCellNum()+1"+j+1);
                    yy++;
                }
                // statement.setString(j + 1, cell.getCellFormula());

                statement.setString(14, (i + 1) + "");
                
                statement.addBatch();
                correcto += "<p>Agregado fila " + i + "</p>";
                if (i % batchSize == 0) {

                    // statement.executeBatch();
                    // statement.clearBatch();
                    // mensaje += "Linea " + i + " insertada";
                    try {
                        statement.executeBatch(); // Ejecuta el lote
                        statement.clearBatch();

                        System.out.println("correcto1-" + i);

                    } catch (SQLException e) {
                        System.out.println("error1-----" + i + "-----------");
                        errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + 1) + "</p>";

                        int[] updateCounts = statement.executeBatch();
                        for (int index = 0; index < updateCounts.length; index++) {
                            if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                                // Registro del registro que no se pudo insertar
                                System.out.println("Error al insertar el registro en la posición: " + (i - batchSize + index + 1));
                                errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + index + 1) + "</p>";
                            } else {
                                errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                            }
                        }
                    }

                }

            }

            try {
                statement.executeBatch(); // Ejecuta el lote
                // correcto += "<p>Agregado fila " + i + "</p>";
                System.out.println("correcto2" + batchSize + 1);
            } catch (SQLException e) {
                System.out.println("error2-----" + e + "-----------");
                errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + 1) + "</p>";

                int[] updateCounts = statement.executeBatch();
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                        System.out.println("Error al insertar el registro en la posición: " + (batchSize + index + 1));
                        errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    } else {
                        errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("e" + e);
            e.printStackTrace();
        }

        // String gtn =    InsertSpACT (  folio);     
        connection.close();
        inputStream.close();
        statement.close();

        String respueston = "<br>" + errorM + " <br>" + correcto;
        return respueston;
    }
     
    
    public String InsertSpRDI( String inputFile2, String folio) throws Exception {
        // Configura la conexión a la base de datos Oracle
        String mensaje = " ";
        String url = urlG;
        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

        int timeout = 1600000; // 30 minutos
        Executor executor = Executors.newSingleThreadExecutor();
        connection.setNetworkTimeout(executor, timeout);

         String errorM = "";
        String correcto = "";
         // Abre el archivo de Excel
        // String inputFile = "D:\\datosdns.xlsx";
        String inputFile = inputFile2;//"D:\\DSNVN2.xls";
        FileInputStream inputStream = new FileInputStream(inputFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        

        // Recorre las filas del archivo de Excel y realiza las inserciones
        int batchSize = 100; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // PreparedStatement statement = connection.prepareStatement(insertSql);
        CallableStatement statement = connection.prepareCall(" {call SP_INB_RDI( ?,?,?,?,?,?,?,?,?,?   ,?,?,?,?,?,?,?,?,?,?,   ?,?,?, ?,?,?, ?,?,  '" + folio + "'  )}");
        try {
            for (int i = 2; i <= rowCount; i++) {
                String mensajeReturn = "";
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int yy = 0;
                //row.getLastCellNum();
               // for (int j = 0; j < 20; j++) {
               for (int j = 0; j < 28; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        statement.setNull(j + 1, Types.NULL);
                    } else {

                        switch (cell.getCellType()) {
                            case STRING:
                                statement.setString(j + 1, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                statement.setDouble(j + 1, cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                statement.setBoolean(j + 1, cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                statement.setString(j + 1, cell.getCellFormula());
                                break;
                            default:
                                statement.setNull(j + 1, Types.NULL);
                                break;
                        }
                    }
                    //System.out.println("row.getLastCellNum()+1"+j+1);
                    yy++;
                }
                // statement.setString(j + 1, cell.getCellFormula());

               // statement.setString(17, (i + 1) + "");
                
                statement.addBatch();
                correcto += "<p>Agregado fila " + i + "</p>";
                if (i % batchSize == 0) {

                    // statement.executeBatch();
                    // statement.clearBatch();
                    // mensaje += "Linea " + i + " insertada";
                    try {
                        statement.executeBatch(); // Ejecuta el lote
                        statement.clearBatch();

                        System.out.println("correcto1-" + i);

                    } catch (SQLException e) {
                        System.out.println("error1-----" + i + "-----------");
                        errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + 1) + "</p>";

                        int[] updateCounts = statement.executeBatch();
                        for (int index = 0; index < updateCounts.length; index++) {
                            if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                                // Registro del registro que no se pudo insertar
                                System.out.println("Error al insertar el registro en la posición: " + (i - batchSize + index + 1));
                                errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + index + 1) + "</p>";
                            } else {
                                errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                            }
                        }
                    }

                }

            }

            try {
                statement.executeBatch(); // Ejecuta el lote
                // correcto += "<p>Agregado fila " + i + "</p>";
                System.out.println("correcto2" + batchSize + 1);
            } catch (SQLException e) {
                System.out.println("error2-----" + e + "-----------");
                errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + 1) + "</p>";

                int[] updateCounts = statement.executeBatch();
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                        System.out.println("Error al insertar el registro en la posición: " + (batchSize + index + 1));
                        errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    } else {
                        errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("e" + e);
            e.printStackTrace();
        }

        // String gtn =    InsertSpACT (  folio);     
        connection.close();
        inputStream.close();
        statement.close();

        String respueston = "<br>" + errorM + " <br>" + correcto;
        return respueston;
    }
      
    
    public String InsertSpRDI2( String inputFile2, String folio) throws Exception {
        // Configura la conexión a la base de datos Oracle
        String mensaje = " ";
        String url = urlG;
        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

        int timeout = 1600000; // 30 minutos
        Executor executor = Executors.newSingleThreadExecutor();
        connection.setNetworkTimeout(executor, timeout);

         String errorM = "";
        String correcto = "";
         // Abre el archivo de Excel
        // String inputFile = "D:\\datosdns.xlsx";
        String inputFile = inputFile2;//"D:\\DSNVN2.xls";
        FileInputStream inputStream = new FileInputStream(inputFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        

        // Recorre las filas del archivo de Excel y realiza las inserciones
        int batchSize = 100; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // PreparedStatement statement = connection.prepareStatement(insertSql);
          CallableStatement statement = connection.prepareCall(" {call SP_INB_RDI2(   ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,  ?,?,?,?,?,?,?,?,?,?, ?,       '" + folio + "'  )}");
       // CallableStatement statement = connection.prepareCall(" {call SP_INB_RDI2(   ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,  ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?, '" + folio + "'  )}");

        try {
            for (int i = 2; i <= rowCount; i++) {
                String mensajeReturn = "";
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int yy = 0;
                //row.getLastCellNum();
                for (int j = 0; j < 31; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        statement.setNull(j + 1, Types.NULL);
                    } else {

                        switch (cell.getCellType()) {
                            case STRING:
                                statement.setString(j + 1, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                statement.setDouble(j + 1, cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                statement.setBoolean(j + 1, cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                statement.setString(j + 1, cell.getCellFormula());
                                break;
                            default:
                                statement.setNull(j + 1, Types.NULL);
                                break;
                        }
                    }
                    //System.out.println("row.getLastCellNum()+1"+j+1);
                    yy++;
                }
                // statement.setString(j + 1, cell.getCellFormula());

               // statement.setString(17, (i + 1) + "");
                
                statement.addBatch();
                correcto += "<p>Agregado fila " + i + "</p>";
                if (i % batchSize == 0) {

                    // statement.executeBatch();
                    // statement.clearBatch();
                    // mensaje += "Linea " + i + " insertada";
                    try {
                        statement.executeBatch(); // Ejecuta el lote
                        statement.clearBatch();

                        System.out.println("correcto1-" + i);

                    } catch (SQLException e) {
                        System.out.println("error1-----" + i + "-----------");
                        errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + 1) + "</p>";

                        int[] updateCounts = statement.executeBatch();
                        for (int index = 0; index < updateCounts.length; index++) {
                            if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                                // Registro del registro que no se pudo insertar
                                System.out.println("Error al insertar el registro en la posición: " + (i - batchSize + index + 1));
                                errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + index + 1) + "</p>";
                            } else {
                                errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                            }
                        }
                    }

                }

            }

            try {
                statement.executeBatch(); // Ejecuta el lote
                // correcto += "<p>Agregado fila " + i + "</p>";
                System.out.println("correcto2" + batchSize + 1);
            } catch (SQLException e) {
                System.out.println("error2-----" + e + "-----------");
                errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + 1) + "</p>";

                int[] updateCounts = statement.executeBatch();
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                        System.out.println("Error al insertar el registro en la posición: " + (batchSize + index + 1));
                        errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    } else {
                        errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("e" + e);
            e.printStackTrace();
        }

        // String gtn =    InsertSpACT (  folio);     
        connection.close();
        inputStream.close();
        statement.close();

        String respueston = "<br>" + errorM + " <br>" + correcto;
        return respueston;
    }
     
    
    public String InsertSpSCI( String inputFile2, String folio) throws Exception {
        // Configura la conexión a la base de datos Oracle
        String mensaje = " ";
        String url = urlG;
        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

        int timeout = 1600000; // 30 minutos
        Executor executor = Executors.newSingleThreadExecutor();
        connection.setNetworkTimeout(executor, timeout);

         String errorM = "";
        String correcto = "";
         // Abre el archivo de Excel
        // String inputFile = "D:\\datosdns.xlsx";
        String inputFile = inputFile2;//"D:\\DSNVN2.xls";
        FileInputStream inputStream = new FileInputStream(inputFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        

        // Recorre las filas del archivo de Excel y realiza las inserciones
        int batchSize = 100; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // PreparedStatement statement = connection.prepareStatement(insertSql);
        CallableStatement statement = connection.prepareCall(" {call SP_SUBIR_BATCH_INB_SCI( ?,?,?,?,?,?,?,?,?,?,   ?,?,?,?,?,?,?,?,?,?,   ?,?,?,?,?,?,?,?,?,?,   ?,?,?,?,?,  '" + folio + "'  )}");
        try {
            for (int i = 2; i <= rowCount; i++) {
                String mensajeReturn = "";
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int yy = 0;
                //row.getLastCellNum();
                for (int j = 0; j < 35; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        statement.setNull(j + 1, Types.NULL);
                    } else {

                        switch (cell.getCellType()) {
                            case STRING:
                                statement.setString(j + 1, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                statement.setDouble(j + 1, cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                statement.setBoolean(j + 1, cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                statement.setString(j + 1, cell.getCellFormula());
                                break;
                            default:
                                statement.setNull(j + 1, Types.NULL);
                                break;
                        }
                    }
                    //System.out.println("row.getLastCellNum()+1"+j+1);
                    yy++;
                }
                // statement.setString(j + 1, cell.getCellFormula());

               // statement.setString(17, (i + 1) + "");
                
                statement.addBatch();
                correcto += "<p>Agregado fila " + i + "</p>";
                if (i % batchSize == 0) {

                    // statement.executeBatch();
                    // statement.clearBatch();
                    // mensaje += "Linea " + i + " insertada";
                    try {
                        statement.executeBatch(); // Ejecuta el lote
                        statement.clearBatch();

                        System.out.println("correcto1-" + i);

                    } catch (SQLException e) {
                        System.out.println("error1-----" + i + "-----------");
                        errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + 1) + "</p>";

                        int[] updateCounts = statement.executeBatch();
                        for (int index = 0; index < updateCounts.length; index++) {
                            if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                                // Registro del registro que no se pudo insertar
                                System.out.println("Error al insertar el registro en la posición: " + (i - batchSize + index + 1));
                                errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + index + 1) + "</p>";
                            } else {
                                errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                            }
                        }
                    }

                }

            }

            try {
                statement.executeBatch(); // Ejecuta el lote
                // correcto += "<p>Agregado fila " + i + "</p>";
                System.out.println("correcto2" + batchSize + 1);
            } catch (SQLException e) {
                System.out.println("error2-----" + e + "-----------");
                errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + 1) + "</p>";

                int[] updateCounts = statement.executeBatch();
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                        System.out.println("Error al insertar el registro en la posición: " + (batchSize + index + 1));
                        errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    } else {
                        errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("e" + e);
            e.printStackTrace();
        }

        // String gtn =    InsertSpACT (  folio);     
        connection.close();
        inputStream.close();
        statement.close();

        String respueston = "<br>" + errorM + " <br>" + correcto;
        return respueston;
    }
      
     
    public String InsertSpibr1( String inputFile2, String folio) throws Exception {
        // Configura la conexión a la base de datos Oracle
        String mensaje = " ";
        String url = urlG;
        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

        int timeout = 1600000; // 30 minutos
        Executor executor = Executors.newSingleThreadExecutor();
        connection.setNetworkTimeout(executor, timeout);

         String errorM = "";
        String correcto = "";
         // Abre el archivo de Excel
        // String inputFile = "D:\\datosdns.xlsx";
        String inputFile = inputFile2;//"D:\\DSNVN2.xls";
        FileInputStream inputStream = new FileInputStream(inputFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        

        // Recorre las filas del archivo de Excel y realiza las inserciones
        int batchSize = 20; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // PreparedStatement statement = connection.prepareStatement(insertSql);
        CallableStatement statement = connection.prepareCall(" {call sp_inb_ibr_1005(   ?,?,?,?,?,?,?,?,?,?    ,?,?,?,?,?,?,?,?, '" + folio + "'  )}");
        try {
            for (int i = 2; i <= rowCount; i++) {
                String mensajeReturn = "";
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int yy = 0;
                //row.getLastCellNum();
                for (int j = 0; j < 18; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        statement.setNull(j + 1, Types.NULL);
                    } else {

                        switch (cell.getCellType()) {
                            case STRING:
                                statement.setString(j + 1, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                statement.setDouble(j + 1, cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                statement.setBoolean(j + 1, cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                statement.setString(j + 1, cell.getCellFormula());
                                break;
                            default:
                                statement.setNull(j + 1, Types.NULL);
                                break;
                        }
                    }
                    //System.out.println("row.getLastCellNum()+1"+j+1);
                    yy++;
                }
                // statement.setString(j + 1, cell.getCellFormula());

               // statement.setString(17, (i + 1) + "");
                
                statement.addBatch();
                correcto += "<p>Agregado fila " + i + "</p>";
                if (i % batchSize == 0) {

                    // statement.executeBatch();
                    // statement.clearBatch();
                    // mensaje += "Linea " + i + " insertada";
                    try {
                        statement.executeBatch(); // Ejecuta el lote
                        statement.clearBatch();

                        System.out.println("correcto1-" + i);

                    } catch (SQLException e) {
                        System.out.println("error1-----" + i + "-----------");
                        errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + 1) + "</p>";

                        int[] updateCounts = statement.executeBatch();
                        for (int index = 0; index < updateCounts.length; index++) {
                            if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                                // Registro del registro que no se pudo insertar
                                System.out.println("Error al insertar el registro en la posición: " + (i - batchSize + index + 1));
                                errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + index + 1) + "</p>";
                            } else {
                                errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                            }
                        }
                    }

                }

            }

            try {
                statement.executeBatch(); // Ejecuta el lote
                // correcto += "<p>Agregado fila " + i + "</p>";
                System.out.println("correcto2" + batchSize + 1);
            } catch (SQLException e) {
                System.out.println("error2-----" + e + "-----------");
                errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + 1) + "</p>";

                int[] updateCounts = statement.executeBatch();
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                        System.out.println("Error al insertar el registro en la posición: " + (batchSize + index + 1));
                        errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    } else {
                        errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("e" + e);
            e.printStackTrace();
        }

        // String gtn =    InsertSpACT (  folio);     
        connection.close();
        inputStream.close();
        statement.close();

        String respueston = "<br>" + errorM + " <br>" + correcto;
        return respueston;
    }
      
           
    public String InsertSpibr2( String inputFile2, String folio) throws Exception {
        // Configura la conexión a la base de datos Oracle
        String mensaje = " ";
        String url = urlG;
        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

        int timeout = 1600000; // 30 minutos
        Executor executor = Executors.newSingleThreadExecutor();
        connection.setNetworkTimeout(executor, timeout);

         String errorM = "";
        String correcto = "";
         // Abre el archivo de Excel
        // String inputFile = "D:\\datosdns.xlsx";
        String inputFile = inputFile2;//"D:\\DSNVN2.xls";
        FileInputStream inputStream = new FileInputStream(inputFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        

        // Recorre las filas del archivo de Excel y realiza las inserciones
        int batchSize = 100; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // PreparedStatement statement = connection.prepareStatement(insertSql);
        CallableStatement statement = connection.prepareCall(" {call SP_INB_IBR_1013(   ?,?,? ,?,?,?, ?,?,?,?   ,?,?,?,?,?,?,?,    '" + folio + "'  )}");
        try {
            for (int i = 2; i <= rowCount; i++) {
                String mensajeReturn = "";
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int yy = 0;
                //row.getLastCellNum();
                for (int j = 0; j < 17; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        statement.setNull(j + 1, Types.NULL);
                    } else {

                        switch (cell.getCellType()) {
                            case STRING:
                                statement.setString(j + 1, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                statement.setDouble(j + 1, cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                statement.setBoolean(j + 1, cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                statement.setString(j + 1, cell.getCellFormula());
                                break;
                            default:
                                statement.setNull(j + 1, Types.NULL);
                                break;
                        }
                    }
                    //System.out.println("row.getLastCellNum()+1"+j+1);
                    yy++;
                }
                // statement.setString(j + 1, cell.getCellFormula());

               // statement.setString(17, (i + 1) + "");
                
                statement.addBatch();
                correcto += "<p>Agregado fila " + i + "</p>";
                if (i % batchSize == 0) {

                    // statement.executeBatch();
                    // statement.clearBatch();
                    // mensaje += "Linea " + i + " insertada";
                    try {
                        statement.executeBatch(); // Ejecuta el lote
                        statement.clearBatch();

                        System.out.println("correcto1-" + i);

                    } catch (SQLException e) {
                        System.out.println("error1-----" + i + "-----------");
                        errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + 1) + "</p>";

                        int[] updateCounts = statement.executeBatch();
                        for (int index = 0; index < updateCounts.length; index++) {
                            if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                                // Registro del registro que no se pudo insertar
                                System.out.println("Error al insertar el registro en la posición: " + (i - batchSize + index + 1));
                                errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + index + 1) + "</p>";
                            } else {
                                errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                            }
                        }
                    }

                }

            }

            try {
                statement.executeBatch(); // Ejecuta el lote
                // correcto += "<p>Agregado fila " + i + "</p>";
                System.out.println("correcto2" + batchSize + 1);
            } catch (SQLException e) {
                System.out.println("error2-----" + e + "-----------");
                errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + 1) + "</p>";

                int[] updateCounts = statement.executeBatch();
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                        System.out.println("Error al insertar el registro en la posición: " + (batchSize + index + 1));
                        errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    } else {
                        errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("e" + e);
            e.printStackTrace();
        }

        // String gtn =    InsertSpACT (  folio);     
        connection.close();
        inputStream.close();
        statement.close();

        String respueston = "<br>" + errorM + " <br>" + correcto;
        return respueston;
    }
      
    //gtn 2
    public String InsertSpgtn2(String insertSql, String inputFile2, String folio) throws Exception {
        // Configura la conexión a la base de datos Oracle
        String mensaje = " ";
        String url = urlG;

        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

        int timeout = 1600000; // 30 minutos
        Executor executor = Executors.newSingleThreadExecutor();
        connection.setNetworkTimeout(executor, timeout);

        String errorM = "";
        String correcto = "";

        // Abre el archivo de Excel
        // String inputFile = "D:\\datosdns.xlsx";
        String inputFile = inputFile2;//"D:\\DSNVN2.xls";
        FileInputStream inputStream = new FileInputStream(inputFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Recorre las filas del archivo de Excel y realiza las inserciones
        int batchSize = 100; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // PreparedStatement statement = connection.prepareStatement(insertSql);
        CallableStatement statement = connection.prepareCall(" {call SP_INB_GTNBATCH2( ?,?,?,?,?,?,?,?,?,?,  ?,?,?,?,?,?,?,?,?,?,  ?,?,?,?,?,?,?,?,?,?,   ?,?,?,?,?,?,?,?, '" + folio + "',?   )}");
        try {
            for (int i = 0; i <= rowCount; i++) {
                String mensajeReturn = "";
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int yy = 0;
                //row.getLastCellNum()
                for (int j = 0; j < 38; j++) {
                    Cell cell = row.getCell(j);
               //     System.out.println("+++"+j + 1+ cell.getStringCellValue());
                    if (cell == null) {
                        statement.setNull(j + 1, Types.NULL);
                    } else {

                        switch (cell.getCellType()) {
                            case STRING:
                                statement.setString(j + 1, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                statement.setDouble(j + 1, cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                statement.setBoolean(j + 1, cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                statement.setString(j + 1, cell.getCellFormula());
                                break;
                            default:
                                statement.setNull(j + 1, Types.NULL);
                                break;
                        }
                    }
                    //System.out.println("row.getLastCellNum()+1"+j+1);
                    yy++;
                }
                // statement.setString(j + 1, cell.getCellFormula()); 

                 statement.setString(39,  (i+1)+"" );
                 
                statement.addBatch();
                correcto += "<p>Agregado fila " + i + "</p>";
                if (i % batchSize == 0) {

                    // statement.executeBatch();
                    // statement.clearBatch();
                    // mensaje += "Linea " + i + " insertada";
                    try {
                        statement.executeBatch(); // Ejecuta el lote
                        statement.clearBatch();

                        System.out.println("correcto1-" + i);

                    } catch (SQLException e) {
                        System.out.println("error1T1-----" + i + "-----------");
                        errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + 1) + "</p>";

                        int[] updateCounts = statement.executeBatch();
                        for (int index = 0; index < updateCounts.length; index++) {
                            if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                                // Registro del registro que no se pudo insertar
                                 System.out.println("error1T2-----" + i + "-----------");
                                System.out.println("Error al insertar el registro en la posición: " + (i - batchSize + index + 1));
                                errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + index + 1) + "</p>";
                            } else {
                                  System.out.println("error1T3-----" + i + "-----------");
                                errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                            }
                        }
                    }

                }

            }

            try {
                statement.executeBatch(); // Ejecuta el lote
                // correcto += "<p>Agregado fila " + i + "</p>";
                System.out.println("correcto2" + batchSize + 1);
            } catch (SQLException e) {
                System.out.println("error2-----" + e + "-----------");
                  System.out.println("error1T4-----" + e + "-----------");
                errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + 1) + "</p>";

                int[] updateCounts = statement.executeBatch();
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                          System.out.println("error1T5-----" + (batchSize + index + 1) + "-----------");
                        System.out.println("Error al insertar el registro en la posición: " + (batchSize + index + 1));
                        errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    } else {
                         System.out.println("error1T6-----" + (batchSize + index + 1) + "-----------");
                        errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("e" + e);
            e.printStackTrace();
        }
         
        
                
                 
         // String gtn =    InsertSpACT (  folio);     

                 
        connection.close();
        inputStream.close();
        statement.close();
        
          
         
            
        String respueston =  "<br>"+ errorM + " <br>" + correcto;
        return respueston;
    }

        
        
       public String InsertSpbobj(String insertSql, String inputFile2, String folio) throws Exception {
        // Configura la conexión a la base de datos Oracle
        String mensaje = " ";
        String url = urlG;

        String username = usernameG;
        String password = passwordG;
        Connection connection = DriverManager.getConnection(url, username, password);

        int timeout = 1600000; // 30 minutos
        Executor executor = Executors.newSingleThreadExecutor();
        connection.setNetworkTimeout(executor, timeout);

        String errorM = "";
        String correcto = "";

        // Abre el archivo de Excel
        // String inputFile = "D:\\datosdns.xlsx";
        String inputFile = inputFile2;//"D:\\DSNVN2.xls";
        FileInputStream inputStream = new FileInputStream(inputFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Recorre las filas del archivo de Excel y realiza las inserciones
        int batchSize = 100; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // PreparedStatement statement = connection.prepareStatement(insertSql);
        CallableStatement statement = connection.prepareCall(" {call SP_INB_BOBJ( ?,?,?,?,?,?,?,?,?,?,   ?,?,?,?,?,?,?,?,?,?,   ?,?,?,?,?,?,?,?,?,?,  "
                                                                              + " ?,?,?,?,?,?,?,?,?,?,   ?,?,?,?,?,?,?,?,?,?,   ?,?,?,?,?,?,?,?,?,?,  "
                                                                             + "  ?,?,?,?,?,?,?,?,?,     '" + folio + "'   )}");
        try {
            for (int i = 2; i <= rowCount; i++) {
                String mensajeReturn = "";
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int yy = 0;
                //row.getLastCellNum()
                for (int j = 0; j < 69; j++) {
                    Cell cell = row.getCell(j);
               //     System.out.println("+++"+j + 1+ cell.getStringCellValue());
                    if (cell == null) {
                        statement.setNull(j + 1, Types.NULL);
                    } else {

                        switch (cell.getCellType()) {
                            case STRING:
                                statement.setString(j + 1, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                statement.setDouble(j + 1, cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                statement.setBoolean(j + 1, cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                statement.setString(j + 1, cell.getCellFormula());
                                break;
                            default:
                                statement.setNull(j + 1, Types.NULL);
                                break;
                        }
                    }
                    //System.out.println("row.getLastCellNum()+1"+j+1);
                    yy++;
                }
                // statement.setString(j + 1, cell.getCellFormula()); 

                // statement.setString(69,  (i+1)+"" );
                 
                statement.addBatch();
                correcto += "<p>Agregado fila " + i + "</p>";
                if (i % batchSize == 0) {

                    // statement.executeBatch();
                    // statement.clearBatch();
                    // mensaje += "Linea " + i + " insertada";
                    try {
                        statement.executeBatch(); // Ejecuta el lote
                        statement.clearBatch();

                        System.out.println("correcto1-" + i);

                    } catch (SQLException e) {
                        System.out.println("error1T1-----" + i + "-----------");
                        errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + 1) + "</p>";

                        int[] updateCounts = statement.executeBatch();
                        for (int index = 0; index < updateCounts.length; index++) {
                            if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                                // Registro del registro que no se pudo insertar
                                 System.out.println("error1T2-----" + i + "-----------");
                                System.out.println("Error al insertar el registro en la posición: " + (i - batchSize + index + 1));
                                errorM += "</p>Error al insertar el registro en la posición: " + (i - batchSize + index + 1) + "</p>";
                            } else {
                                  System.out.println("error1T3-----" + i + "-----------");
                                errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                            }
                        }
                    }

                }

            }

            try {
                statement.executeBatch(); // Ejecuta el lote
                // correcto += "<p>Agregado fila " + i + "</p>";
                System.out.println("correcto2" + batchSize + 1);
            } catch (SQLException e) {
                System.out.println("error2-----" + e + "-----------");
                  System.out.println("error1T4-----" + e + "-----------");
                errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + 1) + "</p>";

                int[] updateCounts = statement.executeBatch();
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                          System.out.println("error1T5-----" + (batchSize + index + 1) + "-----------");
                        System.out.println("Error al insertar el registro en la posición: " + (batchSize + index + 1));
                        errorM += "</p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    } else {
                         System.out.println("error1T6-----" + (batchSize + index + 1) + "-----------");
                        errorM += "<p>Error al insertar el registro en la posición: " + (batchSize + index + 1) + "</p>";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("e" + e);
            e.printStackTrace();
        }
         
        
                
                 
         // String gtn =    InsertSpACT (  folio);     

                 
        connection.close();
        inputStream.close();
        statement.close();
        
          
         
            
        String respueston =  "<br>"+ errorM + " <br>" + correcto;
        return respueston;
    }

           
        
        
        
        
        
        
}
