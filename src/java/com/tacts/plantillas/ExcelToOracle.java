/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.plantillas;

import java.sql.*;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
 /**
 *
 * @author grecendiz
 */
public class ExcelToOracle {

    
    
    public ExcelToOracle() {
    }
    
    
    public  String ExcelToOracle( String urlD,String sql) throws Exception{
        String mensaje="";
          // Configura la conexión a la base de datos Oracle
        String url = "jdbc:oracle:thin:@74.208.140.125:1521:tacts125";
        String username = "VANS39TEST";
        String password = "UKidn49N875RBH54Cq2";
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("urlD"+urlD);
        System.out.println("sql"+sql);
        System.out.println("connection"+connection);
         
        String insertSql = sql;
        
        
        String inputFile = "D:\\DSNVN2.xls";//urlD;//
        FileInputStream inputStream = new FileInputStream(inputFile);
        
        
        Workbook workbook = WorkbookFactory.create(inputStream);
        
        
        Sheet sheet = workbook.getSheetAt(0);
        
        int batchSize = 1000; // Tamaño del lote para las inserciones
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        PreparedStatement statement = connection.prepareStatement(insertSql);
        
        
           for (int i = 2; i <= rowCount; i++) {
            System.out.println("rowCount"+rowCount);
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
         for (int j = 0; j < row.getLastCellNum(); j++) {
                System.out.println("row.getLastCellNum(): "+row.getLastCellNum());
                Cell cell = row.getCell(j);
                System.out.println("cell"+cell);
                 if (cell == null) {
                    statement.setNull(j + 1, Types.NULL);
                    // cell = null;
                 } 
                 else 
                 {
                     
                    //  statement.setString(j + 1, cell.getStringCellValue());
                      System.out.println("cell.getCellType()"+cell.getCellType());
                    switch (cell.getCellType()) {
                        case 1:
                            statement.setString(j + 1, cell.getStringCellValue());
                            break;
                        case 0:
                            statement.setDouble(j + 1, cell.getNumericCellValue());
                            break;
                        case 4:
                            statement.setBoolean(j + 1, cell.getBooleanCellValue());
                            break;
                        case 2:
                            statement.setString(j + 1, cell.getCellFormula());
                            break;
                        default:
                            statement.setNull(j + 1, Types.NULL);
                            break;
                    } 
               }
            }
            statement.addBatch();
            if (i % batchSize == 0) {
                statement.executeBatch();
                mensaje+="linea "+i+"insertada /n <br>";
                System.out.println("i"+i);
            }
        }
        statement.executeBatch();

        // Cierra la conexión y el archivo de Excel
        connection.close();
        inputStream.close();
        
        return mensaje;
        
    }
    
    
    
    
    
    
    
    
}
