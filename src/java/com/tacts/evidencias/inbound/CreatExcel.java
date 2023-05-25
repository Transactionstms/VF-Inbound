/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.inbound;
/**
 *
 * @author luis_
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.dao.ServiceDAO;
import com.onest.train.consultas.ConsultasQuery;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreatExcel {

    public static String crearAPartirDeArrayList(String agenteId) {

        ArrayList<Persona> personas = new ArrayList<>();
        ServiceDAO dao = new ServiceDAO();
        ConsultasQuery fac = new ConsultasQuery();
        
        try {

            Statement stmt = dao.conectar().prepareStatement(fac.consultarEventosDetalleAgenteAduanal(agenteId));
            ResultSet rs = stmt.executeQuery(fac.consultarEventosDetalleAgenteAduanal(agenteId));
            while(rs.next()){
                personas.add(new Persona(
                        rs.getString(1),    // numEvento
                        rs.getString(2),    // Responsable
                        rs.getString(3),    // FinalDestination
                        rs.getString(22),   // BrandDivision
                        rs.getString(5),    // Division
                        rs.getString(6),    // ShipmentId
                        rs.getString(7),    // Container
                        rs.getString(8),    // BlAwbPro 
                        rs.getString(23),   // LoadType
                        rs.getString(10),   // Quantity
                        rs.getString(20),   // Pod 
                        rs.getString(12),   // DeparturePol
                        rs.getString(13),   // RealPort
                        rs.getString(14),   // Lt2  
                        rs.getString(24),   // EtaDc
                        rs.getString(25),   // Indc2DaysPutAway 
                        rs.getString(15),   // InboundNotif
                        rs.getString(21),   // Pol
                        rs.getString(17),   // Aa   
                        rs.getString(26)    // Observaciones
                ));
            }
            
            rs.close();
       
        }catch(Exception e){
            e.printStackTrace();
        }
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();
        
        final String nombreArchivo = "ModifaciónDeEventos"+agenteId+".xls";
        Sheet hoja = workbook.createSheet("Modificación de Eventos");
        
        String[] encabezados = {
             "Número de evento", 
             "Responsable", 
             "Final Destination (Shipment)", 
             "Brand-Division", 
             "Division", 
             "Shipment ID", 
             "Container", 
             "BL/AWB/PRO", 
             "Load Type", 
             "Quantity", 
             "POD /", 
             "Est. Departure from POL", 
             "ETA REAL PORT", 
             "LT2", 
             "ETA DC", 
             "INDC +2 Days Put Away", 
             "Inbound notification", 
             "POL", 
             "A.A.", 
             "Observaciones"};
        int indiceFila = 0;

        Row fila = hoja.createRow(indiceFila);
        for (int i = 0; i < encabezados.length; i++) {
            String encabezado = encabezados[i];
            Cell celda = fila.createCell(i);
            celda.setCellValue(encabezado);
        }

        indiceFila++;
        for (int i = 0; i < personas.size(); i++) {
            fila = hoja.createRow(indiceFila);
            Persona persona = personas.get(i);
            
            fila.createCell(0).setCellValue(persona.getnumEvento());
            fila.createCell(1).setCellValue(persona.getResponsable());
            fila.createCell(2).setCellValue(persona.getFinalDestination());
            fila.createCell(3).setCellValue(persona.getBrandDivision());
            fila.createCell(4).setCellValue(persona.getDivision());
            fila.createCell(5).setCellValue(persona.getShipmentId());
            fila.createCell(6).setCellValue(persona.getContainer());
            fila.createCell(7).setCellValue(persona.getBlAwbPro());
            fila.createCell(8).setCellValue(persona.getLoadType());
            fila.createCell(9).setCellValue(persona.getQuantity());
            fila.createCell(10).setCellValue(persona.getPod());
            fila.createCell(11).setCellValue(persona.getDeparturePol());
            fila.createCell(12).setCellValue(persona.getRealPort());
            fila.createCell(13).setCellValue(persona.getLt2());  
            fila.createCell(14).setCellValue(persona.getEtaDc());
            fila.createCell(15).setCellValue(persona.getIndc2DaysPutAway());  
            fila.createCell(16).setCellValue(persona.getInboundNotif());
            fila.createCell(17).setCellValue(persona.getPol());
            fila.createCell(18).setCellValue(persona.getAa());       
            fila.createCell(19).setCellValue(persona.getObservaciones());
            
            indiceFila++;
        }

        // Guardamos
        //File directorioActual = new File(".");
        //String ubicacion = directorioActual.getAbsolutePath();
        String ubicacionArchivoSalida = "D:\\Servicios\\VFINBOUND\\"+nombreArchivo;
        
        try {
            FileOutputStream outputStream = new FileOutputStream(ubicacionArchivoSalida);
            workbook.write(outputStream);
            outputStream.close();
            
            return ubicacionArchivoSalida;
        } catch (FileNotFoundException ex) {
            return "Error de filenotfound: " + ex;
        } catch (IOException ex) {
            return "Error de IOException: " + ex;
        }
         
    }

    /*public static void main(String[] args) {
        crearAPartirDeArrayList();
    }*/
}

class Persona {
    
    private String 
            numEvento,
            responsable, 
            finalDestination, 
            brandDivision, 
            division, 
            shipmentId, 
            container, 
            blAwbPro, 
            loadType, 
            quantity, 
            pod, 
            departurePol, 
            realPort, 
            lt2,
            etaDc, 
            indc2DaysPutAway,
            inboundNotif, 
            pol, 
            aa, 
            observaciones;
    
    public Persona(String numEvento, String responsable, String finalDestination, String brandDivision, String division, String shipmentId, String container, String blAwbPro, String loadType, String quantity, String pod, String departurePol, String realPort, String lt2, String etaDc, String indc2DaysPutAway, String inboundNotif, String pol, String aa, String observaciones) {
        this.numEvento = numEvento;
        this.responsable = responsable;
        this.finalDestination = finalDestination;
        this.brandDivision = brandDivision;
        this.division = division;
        this.shipmentId = shipmentId;
        this.container = container;
        this.blAwbPro = blAwbPro;
        this.loadType = loadType;
        this.quantity = quantity;
        this.pod = pod;
        this.departurePol = departurePol;
        this.realPort =realPort;
        this.lt2 = lt2;
        this.etaDc = etaDc;
        this.indc2DaysPutAway = indc2DaysPutAway;
        this.inboundNotif = inboundNotif;
        this.pol = pol;
        this.aa = aa; 
        this.observaciones = observaciones;
    }

    public String getnumEvento() {
        return numEvento;
    }

    public void setnumEvento(String numEvento) {
        this.numEvento = numEvento;
    }
    
    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }
    
    public String getBrandDivision() {
        return brandDivision;
    }

    public void setBrandDivision(String brandDivision) {
        this.brandDivision = brandDivision;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getBlAwbPro() {
        return blAwbPro;
    }

    public void setBlAwbPro(String blAwbPro) {
        this.blAwbPro = blAwbPro;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getDeparturePol() {
        return departurePol;
    }

    public void setDeparturePol(String departurePol) {
        this.departurePol = departurePol;
    }

    public String getRealPort() {
        return realPort;
    }

    public void setRealPort(String realPort) {
        this.realPort = realPort;
    }

    public String getLt2() {
        return lt2;
    }

    public void setLt2(String lt2) {
        this.lt2 = lt2;
    }

    public String getEtaDc() {
        return etaDc;
    }

    public void setEtaDc(String etaDc) {
        this.etaDc = etaDc;
    }

    public String getIndc2DaysPutAway() {
        return indc2DaysPutAway;
    }

    public void setIndc2DaysPutAway(String indc2DaysPutAway) {
        this.indc2DaysPutAway = indc2DaysPutAway;
    }
    
    public String getInboundNotif() {
        return inboundNotif;
    }

    public void setInboundNotif(String inboundNotif) {
        this.inboundNotif = inboundNotif;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }
    
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String getNumEvento() {
        return numEvento;
    }

    public void setNumEvento(String numEvento) {
        this.numEvento = numEvento;
    }
}