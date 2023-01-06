/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excel;

import com.base.Respuesta;
import com.excel.pojos.Celda;
import com.excel.pojos.Fila;
import com.excel.pojos.Plantilla;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author omar
 */
public class BLexcel {

    public static Plantilla getPlantilla(int idPlantilla, int idLenguaje) {
        Plantilla plantilla = null;
        DaoExcel daoExcel = new DaoExcel();
        try {
            plantilla = daoExcel.getPlantilla(idPlantilla, idLenguaje);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
        return plantilla;
    }

    public static Plantilla getPlantilla(int idPlantilla, int idLenguaje, Workbook wb) {
        Plantilla plantilla =null;
        try {
            plantilla = getPlantilla(idPlantilla, idLenguaje);
            List<Fila> filas = new ArrayList();
            List<Celda> celdas = plantilla.getCeldas();
            Sheet hoja = wb.getSheetAt(0);
            Iterator<Row> rowIterator = hoja.iterator();
            Row row;
            int contador = -1;
            // Recorremos todas las filas para mostrar el contenido de cada celda
            while (rowIterator.hasNext()) {
                contador++;
                
                row = rowIterator.next();
                if(contador<2) continue;
                // Obtenemos el iterator que permite recorres todas las celdas de una fila
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell celda;
                String valor="";
                int conCelda =0;
                while (cellIterator.hasNext()) {
                    celda = cellIterator.next();
                    // Dependiendo del formato de la celda el valor se debe mostrar como String, Fecha, boolean, entero...
                    switch (celda.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(celda)) {
                                System.out.println(celda.getDateCellValue());
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");                                
                                valor = String.valueOf(formatter.format(celda.getDateCellValue()));
                            } else {
                                System.out.println(celda.getNumericCellValue());
                                valor = String.valueOf(celda.getNumericCellValue());
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.println(celda.getStringCellValue());
                            valor = String.valueOf(celda.getStringCellValue());
                            break;                        
                    }
                    celdas.get(conCelda).setValor(valor);
                    conCelda++;
                }
                filas.add(new Fila(contador, celdas));
            }
            
            plantilla.setFilas(filas);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
        return plantilla;
    }

     public static String setPlantilla(int idPlantilla, int idLenguaje,int idDivision,int idBodega, Workbook wb) {
        Plantilla plantilla =null;
        String respuesta = "";
        Respuesta response=null;
        DaoExcel daoExcel = new DaoExcel();
        try {
            plantilla = getPlantilla(idPlantilla, idLenguaje);
            List<Fila> filas = new ArrayList();
            List<Celda> celdas = plantilla.getCeldas();
            Sheet hoja = wb.getSheetAt(0);
            Iterator<Row> rowIterator = hoja.iterator();
            Row row;
            int contador = -1;
            // Recorremos todas las filas para mostrar el contenido de cada celda
            while (rowIterator.hasNext()) {
                contador++;
                row = rowIterator.next();
                if(contador<2) continue;
                Cell celda;
                String valor="";
                System.out.println("Fila: "+(contador+1));
                for(int cel=0;cel<plantilla.getCeldas().size();cel++){                   
                    if(row.getCell(cel)!=null && !row.getCell(cel).toString().trim().equals("")){
                        celda =((Cell)row.getCell(cel));
                        switch (celda.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(celda)) {
                                //System.out.println(celda.getDateCellValue());
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");                                
                                valor = String.valueOf(formatter.format(celda.getDateCellValue()));
                            } else {
                                //System.out.println(celda.getNumericCellValue());
                                if(String.valueOf(celda.getNumericCellValue()).contains("E")){
                                    Locale.setDefault(Locale.US);
                                    DecimalFormat num = new DecimalFormat("###########");
                                    valor = num.format(celda.getNumericCellValue());
                                }else{
                                valor = String.valueOf(celda.getNumericCellValue());
                                }
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            //System.out.println(celda.getStringCellValue());
                            valor = String.valueOf(celda.getStringCellValue());
                            break;                        
                    }
                        System.out.println(valor);
                        celdas.get(cel).setValor(valor);
                    }else{
                        valor="";
                    }
                }                
                response = daoExcel.setPlantilla(plantilla, celdas,idLenguaje,idDivision,idBodega);
                respuesta += "Fila: " + (contador+1) + " Mensaje:" + response.getErrorSql() + " " + response.getMensaje() + "<br>\r";
                filas.add(new Fila(contador+1, celdas));                
            }
            //respuesta += "</table>";
            plantilla.setFilas(filas);
        } catch (Exception exception) {
            respuesta="<B style=\"color: red;\">[ERROR]</B> "+exception.getMessage()+" "+(((exception.getMessage().indexOf("index"))>-1)?"El Excel no corresponde a la plantilla":"");
            System.err.println(exception.getMessage());
        }
        return respuesta;
    }
    public static HSSFWorkbook getPlantillaExcel(int idPlantilla, int idLenguaje) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet hoja = wb.createSheet();
        HSSFRow fila1 = hoja.createRow(0);
        HSSFRow fila2 = hoja.createRow(1);
        Plantilla plantilla = null;
        DaoExcel daoExcel = new DaoExcel();
        try {
            plantilla = daoExcel.getPlantilla(idPlantilla, idLenguaje);
            if (plantilla == null) {
                HSSFCell cell = fila1.createCell(0);
                cell.setCellValue("No se encontro información de la plantilla solicitada");
                cell = fila2.createCell(0);
                cell.setCellValue("Comuniquese con su administrador");
            } else {
                HSSFCell cell = null;
                for (int i = 0; i < plantilla.getCeldas().size(); i++) {
                    cell = fila1.createCell(i);
                    HSSFCellStyle style = wb.createCellStyle();
                    style.setBorderBottom((short) 1); // single line border
                    style.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);
                    HSSFFont font = wb.createFont();
                    font.setFontName(HSSFFont.FONT_ARIAL);
                    font.setFontHeightInPoints((short) 12);
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    font.setColor(HSSFColor.RED.index);
                    style.setFont(font);
                    cell.setCellStyle(style);                    
                    cell.setCellValue(plantilla.getCeldas().get(i).getDescripcion());
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    hoja.setDefaultColumnStyle(i,style); 
                    

                    cell = fila2.createCell(i);
                    style = wb.createCellStyle();
                    style.setBorderBottom((short) 0); // single line border
                    style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
                    wb.createFont();
                    font.setFontName(HSSFFont.FONT_ARIAL);
                    font.setFontHeightInPoints((short) 10);
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    font.setColor(HSSFColor.BLUE.index);
                    style.setFont(font);
                    cell.setCellStyle(style);
                    if (plantilla.getCeldas().get(i).getRequerido() == 1) {
                        cell.setCellValue("REQUERIDO");
                    } else {
                        cell.setCellValue("NO REQUERIDO");
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                }
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
                wb.write(outByteStream);

            }

        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
        return wb;
    }

    public static String setPlantilla(Plantilla plantilla,int idLenguaje,int idDivision,int idBodega) {
        String respuesta = "";
        DaoExcel daoExcel = new DaoExcel();
        for (int i = 0; i < plantilla.getFilas().size(); i++) {
            try {
                Respuesta response = daoExcel.setPlantilla(plantilla, plantilla.getFilas().get(i).getCeldas(),idLenguaje,idDivision,idBodega);
                respuesta += "Fila:" + (i + 1) + " Mensaje;" + response.getErrorSql() + " " + response.getMensaje() + "<br>\r";
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
        }
        return respuesta;
    }

}
