/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transactions.documentos;


import com.dao.ServiceDAO;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Luis
 */
public class LeeExcel extends ServiceDAO {

    private final String cuenta;
    private String mensaje;
    private final HashMap columnas;

    public LeeExcel(String cuenta) {
        this.cuenta = cuenta;
        this.columnas = new HashMap();
        this.columnas.put(1, "Documento");
        this.columnas.put(2, "Sku");
        this.columnas.put(3, "Cantidad");
        this.columnas.put(4, "Retiros");
        this.columnas.put(5, "Depositos");
        this.columnas.put(6, "Saldos");
        this.mensaje = "";
    }

    public String LeeArchivo(String destino) {
        try {

            FileInputStream input;
            input = new FileInputStream(destino);
            POIFSFileSystem fs = new POIFSFileSystem(input);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            int celda = 1;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                celda = 1;
                row = sheet.getRow(i);

                try {
                    Date fecha = row.getCell(0).getDateCellValue();
                    celda++;
                    SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                    String concepto = row.getCell(2).getStringCellValue();
                    celda++;
                    Integer movimiento = (int) row.getCell(1).getNumericCellValue();
                    celda++;
                    Integer deposito = (int) row.getCell(4).getNumericCellValue();
                    celda++;
                    Integer retiro = (int) row.getCell(3).getNumericCellValue();
                    celda++;
                    Integer saldo = (int) row.getCell(5).getNumericCellValue();
                    celda++;

                    insertaConciliacion(formatoDeFecha.format(fecha),
                            movimiento.toString(),
                            concepto,
                            retiro.toString(),
                            deposito.toString(),
                            saldo.toString());

                    System.out.println(formatoDeFecha.format(fecha) + " - " + movimiento + " - " + concepto);
                } catch (IllegalStateException e) {
                    this.mensaje = this.mensaje + "<br>Error de formato en el renglon: <label>" + (i + 1) + "</label> En la columna: <label>" + columnas.get(celda) + "</label>";

                }
                celda = 1;
            }

            input.close();
        } catch (org.apache.poi.poifs.filesystem.OfficeXmlFileException e) {

            return "<label>¡El formato de Excel no es compatible, por favor Descargar el Formato de Archivo correcto!<label>";

        } catch (IOException ioe) {
            System.out.println(ioe);
            return "No se pudo leer el archivo";

        }
        return this.mensaje;

    }

    private void insertaConciliacion(String... id) {
        String sql = "INSERT INTO tra_conciliacion_banc  VALUES(NULL,to_date('" 
                + id[0] + "','DD/MM/YYYY')," 
                + id[1] + ",'" 
                + id[2] + "'," 
                + id[3] + "," 
                + id[4] + "," 
                + id[5] 
                + ","
                + "0,"
                + "0,"
                + "0,"
                + "sysdate,"
                + this.cuenta + ")";

        save(sql);

    }

}
