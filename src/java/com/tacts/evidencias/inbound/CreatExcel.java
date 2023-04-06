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
import java.sql.ResultSet;
import java.sql.Statement;

public class CreatExcel {

    public static String crearAPartirDeArrayList(String res) {

        ArrayList<Persona> personas = new ArrayList<>();
        ServiceDAO dao = new ServiceDAO();
        
        try {
            String consulta = " SELECT DISTINCT " 
                            + " TIE.ID_EVENTO, " 
                            + " BP.RESPONSABLE, " 
                            + " GTN.FINAL_DESTINATION, " 
                            + " GTN.BRAND_DIVISION, " 
                            + " 'DIVISION', " 
                            + " GTN.SHIPMENT_ID, " 
                            + " GTN.CONTAINER1, " 
                            + " GTN.BL_AWB_PRO, " 
                            + " GTN.LOAD_TYPE, " 
                            + " (SELECT SUM(tt.QUANTITY) FROM TRA_INC_GTN_TEST tt WHERE tt.PLANTILLA_ID =GTN.PLANTILLA_ID ) AS SUMA, GTN.POD, TO_CHAR(GTN.EST_DEPARTURE_POL,'MM/DD/YYYY'), " 
                            + " TO_CHAR(GTN.ETA_PORT_DISCHARGE,'MM/DD/YYYY') AS ETA_REAL_PORT, " 
                            + " ("
                     + "   SELECT   MAX(nvl(recommended_lt2,80))  FROM   tra_inb_costofleteytd   WHERE  TRIM(id_bd) = TRIM(gtn.brand_division)   AND TRIM(id_pod) = TRIM(gtn.pod)   AND TRIM(id_pol) = TRIM(gtn.pol) "
                  + ")AS EST_ETA_DC, " 
                            + " 'INBOUND NOTIFICATION', " 
                            + " GTN.POL, " 
                            + " 'A.A', " 
                            + " GTN.PLANTILLA_ID, " 
                            + " TO_CHAR(GTN.FECHA_CAPTURA,'MM/DD/YYYY') " 
                    
                        + " ,TIP1.NOMBRE_POD,"//19
                        + " TIP2.NOMBRE_POL,"
                        + " tibd.NOMBRE_BD "
                    
                            + " FROM TRA_INB_EVENTO TIE " 
                            + " INNER JOIN TRA_DESTINO_RESPONSABLE BP ON BP.USER_NID=TIE.USER_NID " 
                            + " INNER JOIN TRA_INC_GTN_TEST GTN ON GTN.PLANTILLA_ID=TIE.PLANTILLA_ID "
                    + " left join tra_inb_POD tip1 on tip1.ID_POD=GTN.POD"
                    + " left join tra_inb_POL tip2 on tip2.ID_POL=GTN.POL"
                    + " left join tra_inb_BRAND_DIVISION tibd on tibd.ID_BD=GTN.BRAND_DIVISION"
                    + "ORDER BY 1 ";
            Statement stmt = dao.conectar().prepareStatement(consulta);
            ResultSet rs = stmt.executeQuery(consulta);

            while(rs.next()){
                personas.add(new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17)));
            }
            
            rs.close();
       
        }catch(Exception e){
            e.printStackTrace();
        }
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();
        
        final String nombreArchivo = "Excel-ModifaciónDeEventos.xls";
        Sheet hoja = workbook.createSheet("Modificación de Eventos");
        
        String[] encabezados = {"Número de evento", "Responsable", "Final Destination (Shipment)", "Brand-Division", "Division", "Shipment ID", "Container", "BL/AWB/PRO", "Load Type", "Quantity", "POD /", "Est. Departure from POL", "ETA REAL PORT", "Est. Eta DC", "Inbound notification", "POL", "A.A."};
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
            fila.createCell(13).setCellValue(persona.getEtaDc());
            fila.createCell(14).setCellValue(persona.getInboundNotif());
            fila.createCell(15).setCellValue(persona.getPol());
            fila.createCell(16).setCellValue(persona.getAa());
            
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

    private int numEvento;
    private String responsable, finalDestination, brandDivision, division, shipmentId, container, blAwbPro, loadType, quantity, pod, departurePol, realPort, etaDc, inboundNotif, pol, aa;
    
    public Persona(int numEvento, String responsable, String finalDestination, String brandDivision, String division, String shipmentId, String container, String blAwbPro, String loadType, String quantity, String pod, String departurePol, String realPort, String etaDc, String inboundNotif, String pol, String aa) {
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
        this.etaDc = etaDc;
        this.inboundNotif = inboundNotif;
        this.pol = pol;
        this.aa = aa;
    }

    public int getnumEvento() {
        return numEvento;
    }

    public void setnumEvento(int numEvento) {
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

    public String getEtaDc() {
        return etaDc;
    }

    public void setEtaDc(String etaDc) {
        this.etaDc = etaDc;
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
    
}