/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.plantillas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Leerexcel {

    public Leerexcel() {
    }

    public String leerExcel() throws FileNotFoundException, IOException {
         FileInputStream inputStream = new FileInputStream(new File("D:\\Servicios\\wspod\\INBOUND\\SUBIR_GTN.xls"));
        Workbook workbook = new HSSFWorkbook(inputStream); // Utiliza XSSFWorkbook en lugar de Workbook
        Sheet sheet = workbook.getSheetAt(0); // Obtener la primera hoja del Excel

        // Iterar sobre las filas y columnas del Excel
        int x=0;
        for (Row row : sheet) {
            
            Row Rows=sheet.getRow(x);
            
            System.out.println("RowscellValue"+Rows.getCell(11));
            
            for (Cell cell : row) {
                
                Cell cellValue2 = row.getCell(11);
                
                // Obtener el valor de la celda y procesarlo
              //  String cellValue = cell.getStringCellValue();
                 String cellValue3 = cellValue2.getStringCellValue();
              //  System.out.println("cellValue"+cellValue3);
                // Realizar la comparación con los datos de la tabla Oracle
            }
            x++;
        }

       // workbook.close(); // Cerrar el Workbook correctamente utilizando el tipo específico XSSFWorkbook
        inputStream.close();

        return "";
    }
}
