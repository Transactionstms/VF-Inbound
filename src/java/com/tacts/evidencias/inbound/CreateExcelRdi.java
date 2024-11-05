/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.inbound;
/**
 *
 * @author grecendiz
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
import com.tacts.model.inbound.excelReporteEventosGral;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class CreateExcelRdi {
    
 public static String crearAPartirDeArrayList(String agenteId,String folio) {

        ArrayList<Persona> personas = new ArrayList<>();
        ServiceDAO dao = new ServiceDAO();
        ConsultasQuery fac = new ConsultasQuery();
        
        try {

            Statement stmt = dao.conectar().prepareStatement(fac.consultarEventosDetalleAgenteAduanalExcelRDI(agenteId,folio));
                            ResultSet rs = stmt.executeQuery(fac.consultarEventosDetalleAgenteAduanalExcelRDI(agenteId,folio));
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
 
 
 
 
    public static String crearAPartirDeArrayListReporteEventosGralINC(String consultaReporte, String AgentType, String nameAgentType, String id, List<Integer> columnasDeseadas) {

    ArrayList<excelReporteEventosGral> eventosCustoms = new ArrayList<>();
    ServiceDAO dao = new ServiceDAO();
    ConsultasQuery fac = new ConsultasQuery();

    try {
        Statement stmt = dao.conectar().prepareStatement(consultaReporte);
        ResultSet rs = stmt.executeQuery(consultaReporte);
        while (rs.next()) {
            eventosCustoms.add(new excelReporteEventosGral(
                // Inicializamos los campos del constructor con los valores de `rs.getString()`
                rs.getString(31), rs.getString(1), rs.getString(2), rs.getString(3), 
                rs.getString(22), rs.getString(5), rs.getString(6), rs.getString(7), 
                rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(20), 
                rs.getString(12), rs.getString(13), rs.getString(23), rs.getString(15), 
                rs.getString(21), rs.getString(17), rs.getString(29), rs.getString(98), 
                rs.getString(32), rs.getString(33), rs.getString(34), rs.getString(35), 
                rs.getString(36), rs.getString(37), rs.getString(38), rs.getString(39), 
                rs.getString(40), rs.getString(41), rs.getString(42), rs.getString(43), 
                rs.getString(44), rs.getString(45), rs.getString(46), rs.getString(47), 
                rs.getString(48), rs.getString(49), rs.getString(50), rs.getString(51), 
                rs.getString(52), rs.getString(53), rs.getString(54), rs.getString(55), 
                rs.getString(56), rs.getString(57), rs.getString(58), rs.getString(59), 
                rs.getString(60), rs.getString(61), rs.getString(62), rs.getString(63), 
                rs.getString(64), rs.getString(65), rs.getString(66), rs.getString(67), 
                rs.getString(68), rs.getString(69), rs.getString(70), rs.getString(71), 
                rs.getString(72), rs.getString(73), rs.getString(74), rs.getString(75), 
                rs.getString(76), rs.getString(77), rs.getString(96)
            ));
        }
        rs.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    HSSFWorkbook workbook = new HSSFWorkbook();
    final String nombreArchivo = "ReporteEventosCustoms" + id + ".xls";
    Sheet hoja = workbook.createSheet("Detalle Eventos Customs " + nameAgentType);

    // Encabezados
    String[] encabezados = { 
                "REFERENCIA_AA",
                "NUMERO_DE_EVENTO",
                "RESPONSABLE",
                "FINAL_DESTINATION",
                "BRAND_DIVISION",
                "DIVISION",
                "SHIPMENT_ID",
                "CONTAINER_ID",
                "BL_AWB_PRO",
                "LOAD_TYPE",
                "QUANTITY",
                "POD",
                "EST_DEPART_FROM_POL",
                "ETA_REAL_PORT_OF_DISCHARGE",
                "EST_ETA_DC",
                "INBOUND_NOTIFICACION",
                "POL",
                "AA",
                "FECHA_MES_VENTA",
                "PRIORIDAD",
                "PAIS_ORIGEN",
                "SIZE_CONTAINER",
                "VALOR_USD",
                "ETA_PORT_OF_DISCHARGE",
                "AGENTE_ADUANAL",
                "PEDIMENTO_A1",
                "PEDIMENTO_R1",
                "MOTIVO_RECTIFICACION_1",
                "PEDIMENTO_R1_2DO",
                "MOTIVO_RECTIFICACION_2",
                "FECHA_RECEPCION_DOCUMENTOS",
                "RECINTO",
                "NAVIERA_FORWARDER",
                "BUQUE",
                "FECHA_REVALID_LIBE_BL",
                "FECHA_PREVIO_ORIGEN",
                "FECHA_PREVIO_DESTINO",
                "FECHA_RESULTADO_PREVIO",
                "PROFORMA_FINAL",
                "REQUIERE_PERMISO",
                "FECHA_ENVIO_FICHAS_NOTAS",
                "FEC_RECEPCION_PERMISOS_TRAMIT",
                "FEC_ACT_PERMISOS",
                "FEC_PERM_AUT",
                "CO_APLIC_PREF_ARANCELARIA",
                "APLIC_PREF_ARANCELARIA_CO",
                "REQUIERE_UVA",
                "REQUIERE_CA",
                "FECHA_RECEPCION_CA",
                "NÚMERO_CONSTANCIA_CA",
                "MONTO_CA",
                "FECHA_DOCUMENTOS_COMPLETOS",
                "FECHA_PAGO_PEDIMENTO",
                "FECHA_SOLICITUD_TRANSPORTE",
                "FECHA_MODULACION",
                "MODALIDAD_CAMION_TREN",
                "RESULT_MODULACION_VERDE_ROJO",
                "FECHA_RECONOCIMIENTO",
                "FECHA_LIBERACION",
                "SELLO_ORIGEN",
                "SELLO_FINAL",
                "FECHA_RETENCION_AUTORIDAD",
                "FECHA_LIB_POR_RET_AUT",
                "ESTATUS_OPERACION",
                "MOTIVO_ATRASO",
                "OBSERVACIONES",
                "FY"
    };
    Row filaEncabezado = hoja.createRow(0);

    // Crear encabezados solo para columnas deseadas
    int colIndex = 0;
    for (Integer columna : columnasDeseadas) {
        Cell celdaEncabezado = filaEncabezado.createCell(colIndex++);
        celdaEncabezado.setCellValue(encabezados[columna]);
    }

    // Datos
    int indiceFila = 1;
    for (excelReporteEventosGral customs : eventosCustoms) {
        Row fila = hoja.createRow(indiceFila++);
        colIndex = 0;
        for (Integer columna : columnasDeseadas) {
            String valorCelda = obtenerValorPorIndice(customs, columna); // Método helper
            fila.createCell(colIndex++).setCellValue(valorCelda);
        }
    }

    // Guardar archivo
    String ubicacionArchivoSalida = "D:\\Servicios\\VFINBOUND\\" + nombreArchivo;
    try {
        FileOutputStream outputStream = new FileOutputStream(ubicacionArchivoSalida);
        workbook.write(outputStream);
        outputStream.close();
        return ubicacionArchivoSalida;
    } catch (IOException ex) {
        return "Error: " + ex;
    }
}

        // Método helper para obtener el valor por índice en excelReporteEventosGral
        private static String obtenerValorPorIndice(excelReporteEventosGral customs, int indice) {
            switch (indice) {
               
             case 0: return customs.getReferenciaAA();
             case 1: return customs.getEvento();
             case 2: return customs.getResponsable();
             case 3: return customs.getFinalDestination();
             case 4: return customs.getBrandDivision();
             case 5: return customs.getDivision();
             case 6: return customs.getShipmentId();
             case 7: return customs.getContainerId();
             case 8: return customs.getBlAwbPro();
             case 9: return customs.getLoadType();
             case 10: return customs.getQuantity();
             case 11: return customs.getPod();
             case 12: return customs.getEstDepartFromPol();
             case 13: return customs.getEtaRealPortOfDischarge();
             case 14: return customs.getEstEtaDc();
             case 15: return customs.getInboundNotification();
             case 16: return customs.getPol();
             case 17: return customs.getAa();
             case 18: return customs.getFechaMesVenta();
             case 19: return customs.getPrioridad();
             case 20: return customs.getPais_origen();
             case 21: return customs.getSize_container();
             case 22: return customs.getValor_usd();
             case 23: return customs.getEta_port_discharge();
             case 24: return customs.getAgente_aduanal();
             case 25: return customs.getPedimento_a1();
             case 26: return customs.getPedimento_r1_1er();
             case 27: return customs.getMotivo_rectificacion_1er();
             case 28: return customs.getPedimento_r1_2do();
             case 29: return customs.getMotivo_rectificacion_2do();
             case 30: return customs.getFecha_recepcion_doc();
             case 31: return customs.getRecinto();
             case 32: return customs.getNaviera();
             case 33: return customs.getBuque();
             case 34: return customs.getFecha_revalidacion();
             case 35: return customs.getFecha_previo_origen();
             case 36: return customs.getFecha_previo_destino();
             case 37: return customs.getFecha_resultado_previo();
             case 38: return customs.getProforma_final();
             case 39: return customs.getPermiso();
             case 40: return customs.getFecha_envio();
             case 41: return customs.getFecha_recepcion_perm();
             case 42: return customs.getFecha_activacion_perm();
             case 43: return customs.getFecha_permisos_aut();
             case 44: return customs.getCo_pref_arancelaria();
             case 45: return customs.getAplic_pref_arancelaria();
             case 46: return customs.getReq_uva();
             case 47: return customs.getReq_ca();
             case 48: return customs.getFecha_recepcion_ca();
             case 49: return customs.getNum_constancia_ca();
             case 50: return customs.getMonto_ca();
             case 51: return customs.getFecha_doc_completos();
             case 52: return customs.getFecha_pago_pedimento();
             case 53: return customs.getFecha_solicitud_transporte();
             case 54: return customs.getFecha_modulacion();
             case 55: return customs.getModalidad();
             case 56: return customs.getResultado_modulacion();
             case 57: return customs.getFecha_reconocimiento();
             case 58: return customs.getFecha_liberacion();
             case 59: return customs.getSello_origen();
             case 60: return customs.getSello_final();
             case 61: return customs.getFecha_retencion_aut();
             case 62: return customs.getFecha_liberacion_aut();
             case 63: return customs.getEstatus_operacion();
             case 64: return customs.getMotivo_atraso();
             case 65: return customs.getObservaciones();
             case 66: return customs.getFy();
             
                default: return "";
            }
        }

}

  
