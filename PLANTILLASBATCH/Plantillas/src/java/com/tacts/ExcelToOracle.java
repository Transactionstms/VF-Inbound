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

public class ExcelToOracle {

    public ExcelToOracle() {

    }

    public String Insert(String insertSql, String inputFile2,String folio) throws Exception {
        // Configura la conexión a la base de datos Oracle
        String url = "jdbc:oracle:thin:@74.208.140.125:1521:tacts125";
        String username = "VANS39TEST";
        String password = "XUKidn49N875RBH54Cq2";
        String errorM="";
        String correcto = "";

        Connection connection = DriverManager.getConnection(url, username, password);
   // try (Connection connection = DriverManager.getConnection(url, username, password)) {

        
           int timeout = 1600000; // 30 minutos
Executor executor = Executors.newSingleThreadExecutor();
connection.setNetworkTimeout(executor, timeout);
 
     

        // Abre el archivo de Excel
        // String inputFile = "D:\\datosdns.xlsx";
        String inputFile = inputFile2;//"D:\\DSNVN2.xls";
        FileInputStream inputStream = new FileInputStream(inputFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Recorre las filas del archivo de Excel y realiza las inserciones
        int batchSize = 1000; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        PreparedStatement statement = connection.prepareStatement(insertSql);
        for (int i = 0; i <= rowCount; i++) {
            //System.out.println("rowCount" + rowCount);
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            for (int j = 0; j < row.getLastCellNum(); j++) {
              //  System.out.println("row.getLastCellNum(): " + row.getLastCellNum());
                Cell cell = row.getCell(j);
               // System.out.println("cell" + cell);
                if (cell == null) {
                    statement.setNull(j + 1, Types.NULL);
                    // cell = null;
                } else {
                  //  System.out.println("cell.getCellType()" + cell.getCellType());

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
            }
            System.out.println("row.getLastCellNum()"+row.getLastCellNum());
              statement.setString(42,  (i+1)+"" );
            statement.addBatch();
            correcto += "<p>Agregado fila "+i+"</p>";
            if (i % batchSize == 0) {
                try {
        statement.executeBatch(); // Ejecuta el lote
           
         
            } catch (SQLException e) {
                
                  int[] updateCounts = statement.executeBatch(); 
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                        System.out.println("Error al insertar el registro en la posición: " + (i - batchSize + index + 1));
                        errorM+="</p>Error al insertar el registro en la posición: " + (i - batchSize + index + 1)+"</p>";
                    }else{
                     errorM+="<p>Error al insertar el registro en la posición: " + ( batchSize + index + 1)+"</p>";
                    }
                }
            }
            statement.clearBatch();
            }else{
            }
             
        }
       
        
        
        
           try {
        statement.executeBatch(); // Ejecuta el lote
                  //  System.out.println("se registro - "+i+"ttttt-----------");
                  //  correcto += "<p>Agregado fila "+i+"</p>";

            } catch (SQLException e) {
                //   System.out.println("no se registro -----"+i+"-----------");

                  int[] updateCounts = statement.executeBatch(); 
                for (int index = 0; index < updateCounts.length; index++) {
                    if (updateCounts[index] == Statement.EXECUTE_FAILED) {
                        // Registro del registro que no se pudo insertar
                        System.out.println("2Error al insertar el registro en la posición: " + ( batchSize + index + 1));
                        errorM+="<p>Error al insertar el registro en la posición: " + ( batchSize + index + 1)+"</p>";
                    }else{
                         errorM+="<p>Error al insertar el registro en la posición: " + ( batchSize + index + 1)+"</p>";
                                        System.out.println("no se registroxx -----"+index+"-----------");

                    }
                }
            }
       
          

        // Cierra la conexión y el archivo de Excel
        connection.close();
        inputStream.close();
         statement.close();
        
       
    
String respueston=errorM+"<br><br><br>"+correcto;
        return respueston;
    }

}
