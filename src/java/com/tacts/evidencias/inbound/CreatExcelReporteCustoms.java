/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacts.evidencias.inbound;
 
import com.dao.ServiceDAO;
import com.onest.train.consultas.ConsultasQuery;
import com.tacts.model.inbound.excelReporteEventosAdmin;
import com.tacts.model.inbound.excelReporteEventosCusa;
import com.tacts.model.inbound.excelReporteEventosGral;
import com.tacts.model.inbound.excelReporteEventosLogix;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Desarrollo Tacts
 */
public class CreatExcelReporteCustoms {

    public static String crearAPartirDeArrayListReporteEventosAdmin(String consultaReporte, String AgentType, String nameAgentType) {

        ArrayList<excelReporteEventosAdmin> eventosCustoms = new ArrayList<>();
        ServiceDAO dao = new ServiceDAO();
        ConsultasQuery fac = new ConsultasQuery();

        try {

            Statement stmt = dao.conectar().prepareStatement(consultaReporte);
            ResultSet rs = stmt.executeQuery(consultaReporte);
            while (rs.next()) {
                eventosCustoms.add(new excelReporteEventosAdmin(
                        rs.getString(31), // referenciaAA	
                        rs.getString(1), // evento 
                        rs.getString(2), // responsable 
                        rs.getString(3), // finalDestination	
                        rs.getString(22), // brandDivision 
                        rs.getString(5), // division	
                        rs.getString(6), // shipmentId 
                        rs.getString(7), // containerId 
                        rs.getString(8), // blAwbPro	
                        rs.getString(9), // loadType	
                        rs.getString(10), // quantity	
                        rs.getString(20), // pod 
                        rs.getString(12), // estDepartFromPol	
                        rs.getString(13), // etaRealPortOfDischarge 
                        rs.getString(23), // estEtaDc	
                        rs.getString(15), // inboundNotification	
                        rs.getString(21), // pol 
                        rs.getString(17), // aa 
                        rs.getString(29), // fechaMesVenta 
                        rs.getString(98), // prioridad
                        rs.getString(32), // pais_origen	
                        rs.getString(33), // size_container	
                        rs.getString(34), // valor_usd	
                        rs.getString(35), // eta_port_discharge	
                        rs.getString(36), // agente_aduanal	
                        rs.getString(37), // pedimento_a1	
                        rs.getString(38), // pedimento_r1_1er	
                        rs.getString(39), // motivo_rectificacion_1er	
                        rs.getString(40), // pedimento_r1_2do	
                        rs.getString(41), // motivo_rectificacion_2do	
                        rs.getString(42), // fecha_recepcion_doc	
                        rs.getString(43), // recinto	
                        rs.getString(44), // naviera	
                        rs.getString(45), // buque	
                        rs.getString(46), // fecha_revalidacion	
                        rs.getString(47), // fecha_previo_origen	
                        rs.getString(48), // fecha_previo_destino	
                        rs.getString(49), // fecha_resultado_previo	
                        rs.getString(50), // proforma_final	
                        rs.getString(51), // permiso	
                        rs.getString(52), // fecha_envio	
                        rs.getString(53), // fecha_recepcion_perm	
                        rs.getString(54), // fecha_activacion_perm	
                        rs.getString(55), // fecha_permisos_aut	
                        rs.getString(56), // co_pref_arancelaria	
                        rs.getString(57), // aplic_pref_arancelaria	
                        rs.getString(58), // req_uva	
                        rs.getString(59), // req_ca	
                        rs.getString(60), // fecha_recepcion_ca							
                        rs.getString(61), // num_constancia_ca	
                        rs.getString(62), // monto_ca	
                        rs.getString(63), // fecha_doc_completos	
                        rs.getString(64), // fecha_pago_pedimento	
                        rs.getString(65), // fecha_solicitud_transporte	
                        rs.getString(66), // fecha_modulacion	
                        rs.getString(67), // modalidad	
                        rs.getString(68), // resultado_modulacion	
                        rs.getString(69), // fecha_reconocimiento	
                        rs.getString(70), // fecha_liberacion	
                        rs.getString(71), // sello_origen	
                        rs.getString(72), // sello_final	
                        rs.getString(73), // fecha_retencion_aut	
                        rs.getString(74), // fecha_liberacion_aut	
                        rs.getString(75), // estatus_operacion	
                        rs.getString(76), // motivo_atraso	
                        rs.getString(77), // observaciones	
                        rs.getString(78), // llegada_a_nova	
                        rs.getString(79), // llegada_a_globe_trade_sd	
                        rs.getString(80), // archivo_m	
                        rs.getString(81), // fecha_archivo_m	
                        rs.getString(82), // fecha_solicit_manip	
                        rs.getString(83), // fecha_vencim_manip	
                        rs.getString(84), // fecha_confirm_clave_pedim	
                        rs.getString(85), // fecha_recep_increment	
                        rs.getString(86), // t_e	
                        rs.getString(87), // fecha_vencim_inbound	
                        rs.getString(88), // no_bultos	
                        rs.getString(89), // peso_kg	
                        rs.getString(90), // transferencia	
                        rs.getString(91), // fecha_inicio_etiquetado	
                        rs.getString(92), // fecha_termino_etiquetado	
                        rs.getString(93), // hora_termino_etiquetado	
                        rs.getString(94), // proveedor	
                        rs.getString(95), // proveedor_carga
                        rs.getString(96) // fy
                ));
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();

        final String nombreArchivo = "ReporteEventosCustoms.xls";
        Sheet hoja = workbook.createSheet("Detalle Eventos Customs " + nameAgentType);

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
                "LLEGADA_A_NOVA",
                "LLEGADA_A_GLOBE_TRADE_SD",
                "ARCHIVO_M",
                "FECHA_ARCHIVO_M",
                "FECHA_SOLICIT_MANIP",
                "FECHA_VENCIM_MANIP",
                "FECHA_CONFIRM_CLAVE_PEDIM",
                "FECHA_RECEP_INCREMENT",
                "T_E",
                "FECHA_VENCIM_INBOUND",
                "NO_BULTOS",
                "PESO_KG",
                "TRANSFERENCIA",
                "FECHA_INICIO_ETIQUETADO",
                "FECHA_TERMINO_ETIQUETADO",
                "HORA_TERMINO_ETIQUETADO",
                "PROVEEDOR",
                "PROVEEDOR_CARGA",
                "FY"};
        int indiceFila = 0;

        Row fila = hoja.createRow(indiceFila);
        for (int i = 0; i < encabezados.length; i++) {
            String encabezado = encabezados[i];
            Cell celda = fila.createCell(i);
            celda.setCellValue(encabezado);
        }

        indiceFila++;
        for (int i = 0; i < eventosCustoms.size(); i++) {
            fila = hoja.createRow(indiceFila);
            excelReporteEventosAdmin customs = eventosCustoms.get(i);

            fila.createCell(0).setCellValue(customs.getReferenciaAA());
            fila.createCell(1).setCellValue(customs.getEvento());
            fila.createCell(2).setCellValue(customs.getResponsable());
            fila.createCell(3).setCellValue(customs.getFinalDestination());
            fila.createCell(4).setCellValue(customs.getBrandDivision());
            fila.createCell(5).setCellValue(customs.getDivision());
            fila.createCell(6).setCellValue(customs.getShipmentId());
            fila.createCell(7).setCellValue(customs.getContainerId());
            fila.createCell(8).setCellValue(customs.getBlAwbPro());
            fila.createCell(9).setCellValue(customs.getLoadType());
            fila.createCell(10).setCellValue(customs.getQuantity());
            fila.createCell(11).setCellValue(customs.getPod());
            fila.createCell(12).setCellValue(customs.getEstDepartFromPol());
            fila.createCell(13).setCellValue(customs.getEtaRealPortOfDischarge());
            fila.createCell(14).setCellValue(customs.getEstEtaDc());
            fila.createCell(15).setCellValue(customs.getInboundNotification());
            fila.createCell(16).setCellValue(customs.getPol());
            fila.createCell(17).setCellValue(customs.getAa());
            fila.createCell(18).setCellValue(customs.getFechaMesVenta());
            fila.createCell(19).setCellValue(customs.getPrioridad());
            fila.createCell(20).setCellValue(customs.getPais_origen());
            fila.createCell(21).setCellValue(customs.getSize_container());
            fila.createCell(22).setCellValue(customs.getValor_usd());
            fila.createCell(23).setCellValue(customs.getEta_port_discharge());
            fila.createCell(24).setCellValue(customs.getAgente_aduanal());
            fila.createCell(25).setCellValue(customs.getPedimento_a1());
            fila.createCell(26).setCellValue(customs.getPedimento_r1_1er());
            fila.createCell(27).setCellValue(customs.getMotivo_rectificacion_1er());
            fila.createCell(28).setCellValue(customs.getPedimento_r1_2do());
            fila.createCell(29).setCellValue(customs.getMotivo_rectificacion_2do());
            fila.createCell(30).setCellValue(customs.getFecha_recepcion_doc());
            fila.createCell(31).setCellValue(customs.getRecinto());
            fila.createCell(32).setCellValue(customs.getNaviera());
            fila.createCell(33).setCellValue(customs.getBuque());
            fila.createCell(34).setCellValue(customs.getFecha_revalidacion());
            fila.createCell(35).setCellValue(customs.getFecha_previo_origen());
            fila.createCell(36).setCellValue(customs.getFecha_previo_destino());
            fila.createCell(37).setCellValue(customs.getFecha_resultado_previo());
            fila.createCell(38).setCellValue(customs.getProforma_final());
            fila.createCell(39).setCellValue(customs.getPermiso());
            fila.createCell(40).setCellValue(customs.getFecha_envio());
            fila.createCell(41).setCellValue(customs.getFecha_recepcion_perm());
            fila.createCell(42).setCellValue(customs.getFecha_activacion_perm());
            fila.createCell(43).setCellValue(customs.getFecha_permisos_aut());
            fila.createCell(44).setCellValue(customs.getCo_pref_arancelaria());
            fila.createCell(45).setCellValue(customs.getAplic_pref_arancelaria());
            fila.createCell(46).setCellValue(customs.getReq_uva());
            fila.createCell(47).setCellValue(customs.getReq_ca());
            fila.createCell(48).setCellValue(customs.getFecha_recepcion_ca());
            fila.createCell(49).setCellValue(customs.getNum_constancia_ca());
            fila.createCell(50).setCellValue(customs.getMonto_ca());
            fila.createCell(51).setCellValue(customs.getFecha_doc_completos());
            fila.createCell(52).setCellValue(customs.getFecha_pago_pedimento());
            fila.createCell(53).setCellValue(customs.getFecha_solicitud_transporte());
            fila.createCell(54).setCellValue(customs.getFecha_modulacion());
            fila.createCell(55).setCellValue(customs.getModalidad());
            fila.createCell(56).setCellValue(customs.getResultado_modulacion());
            fila.createCell(57).setCellValue(customs.getFecha_reconocimiento());
            fila.createCell(58).setCellValue(customs.getFecha_liberacion());
            fila.createCell(59).setCellValue(customs.getSello_origen());
            fila.createCell(60).setCellValue(customs.getSello_final());
            fila.createCell(61).setCellValue(customs.getFecha_retencion_aut());
            fila.createCell(62).setCellValue(customs.getFecha_liberacion_aut());
            fila.createCell(63).setCellValue(customs.getEstatus_operacion());
            fila.createCell(64).setCellValue(customs.getMotivo_atraso());
            fila.createCell(65).setCellValue(customs.getObservaciones());
            fila.createCell(66).setCellValue(customs.getLlegada_a_nova());
            fila.createCell(67).setCellValue(customs.getLlegada_a_globe_trade_sd());
            fila.createCell(68).setCellValue(customs.getArchivo_m());
            fila.createCell(69).setCellValue(customs.getFecha_archivo_m());
            fila.createCell(70).setCellValue(customs.getFecha_solicit_manip());
            fila.createCell(71).setCellValue(customs.getFecha_vencim_manip());
            fila.createCell(72).setCellValue(customs.getFecha_confirm_clave_pedim());
            fila.createCell(73).setCellValue(customs.getFecha_recep_increment());
            fila.createCell(74).setCellValue(customs.getT_e());
            fila.createCell(75).setCellValue(customs.getFecha_vencim_inbound());
            fila.createCell(76).setCellValue(customs.getNo_bultos());
            fila.createCell(77).setCellValue(customs.getPeso_kg());
            fila.createCell(78).setCellValue(customs.getTransferencia());
            fila.createCell(79).setCellValue(customs.getFecha_inicio_etiquetado());
            fila.createCell(80).setCellValue(customs.getFecha_termino_etiquetado());
            fila.createCell(81).setCellValue(customs.getHora_termino_etiquetado());
            fila.createCell(82).setCellValue(customs.getProveedor());
            fila.createCell(83).setCellValue(customs.getProveedor_carga());
            fila.createCell(84).setCellValue(customs.getFy());

            indiceFila++;
        }

        // Guardamos
        //File directorioActual = new File(".");
        //String ubicacion = directorioActual.getAbsolutePath();
        String ubicacionArchivoSalida = "D:\\Servicios\\VFINBOUND\\" + nombreArchivo;

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

    public static String crearAPartirDeArrayListReporteEventosGral(String consultaReporte, String AgentType, String nameAgentType,String id) {

        ArrayList<excelReporteEventosGral> eventosCustoms = new ArrayList<>();
        ServiceDAO dao = new ServiceDAO();
        ConsultasQuery fac = new ConsultasQuery();

        try {

            Statement stmt = dao.conectar().prepareStatement(consultaReporte);
            ResultSet rs = stmt.executeQuery(consultaReporte);
            while (rs.next()) {
                System.out.println("rs.getString(1)************************"+rs.getString(1));
                System.out.println("rs.getString(23)"+rs.getString(23));
                eventosCustoms.add(new excelReporteEventosGral(
                        rs.getString(31), // referenciaAA	
                        rs.getString(1), // evento 
                        rs.getString(2), // responsable 
                        rs.getString(3), // finalDestination	
                        rs.getString(22), // brandDivision 
                        rs.getString(5), // division	
                        rs.getString(6), // shipmentId 
                        rs.getString(7), // containerId 
                        rs.getString(8), // blAwbPro	
                        rs.getString(9), // loadType	
                        rs.getString(10), // quantity	
                        rs.getString(20), // pod 
                        rs.getString(12), // estDepartFromPol	
                        rs.getString(13), // etaRealPortOfDischarge 
                        rs.getString(23), // estEtaDc	
                        rs.getString(15), // inboundNotification	
                        rs.getString(21), // pol 
                        rs.getString(17), // aa 
                        rs.getString(29), // fechaMesVenta 
                        rs.getString(98), // prioridad
                        rs.getString(32), // pais_origen	
                        rs.getString(33), // size_container	
                        rs.getString(34), // valor_usd	
                        rs.getString(35), // eta_port_discharge	
                        rs.getString(36), // agente_aduanal	
                        rs.getString(37), // pedimento_a1	
                        rs.getString(38), // pedimento_r1_1er	
                        rs.getString(39), // motivo_rectificacion_1er	
                        rs.getString(40), // pedimento_r1_2do	
                        rs.getString(41), // motivo_rectificacion_2do	
                        rs.getString(42), // fecha_recepcion_doc	
                        rs.getString(43), // recinto	
                        rs.getString(44), // naviera	
                        rs.getString(45), // buque	
                        rs.getString(46), // fecha_revalidacion	
                        rs.getString(47), // fecha_previo_origen	
                        rs.getString(48), // fecha_previo_destino	
                        rs.getString(49), // fecha_resultado_previo	
                        rs.getString(50), // proforma_final	
                        rs.getString(51), // permiso	
                        rs.getString(52), // fecha_envio	
                        rs.getString(53), // fecha_recepcion_perm	
                        rs.getString(54), // fecha_activacion_perm	
                        rs.getString(55), // fecha_permisos_aut	
                        rs.getString(56), // co_pref_arancelaria	
                        rs.getString(57), // aplic_pref_arancelaria	
                        rs.getString(58), // req_uva	
                        rs.getString(59), // req_ca	
                        rs.getString(60), // fecha_recepcion_ca	
                        rs.getString(61), // num_constancia_ca	
                        rs.getString(62), // monto_ca	
                        rs.getString(63), // fecha_doc_completos	
                        rs.getString(64), // fecha_pago_pedimento	
                        rs.getString(65), // fecha_solicitud_transporte	
                        rs.getString(66), // fecha_modulacion	
                        rs.getString(67), // modalidad	
                        rs.getString(68), // resultado_modulacion	
                        rs.getString(69), // fecha_reconocimiento	
                        rs.getString(70), // fecha_liberacion	
                        rs.getString(71), // sello_origen	
                        rs.getString(72), // sello_final	
                        rs.getString(73), // fecha_retencion_aut	
                        rs.getString(74), // fecha_liberacion_aut	
                        rs.getString(75), // estatus_operacion	
                        rs.getString(76), // motivo_atraso	
                        rs.getString(77), // observaciones	
                        rs.getString(96) // fy
                ));
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();

        final String nombreArchivo = "ReporteEventosCustoms"+id+".xls";
        Sheet hoja = workbook.createSheet("Detalle Eventos Customs " + nameAgentType);

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
                "FY"};
        int indiceFila = 0;

        Row fila = hoja.createRow(indiceFila);
        for (int i = 0; i < encabezados.length; i++) {
            String encabezado = encabezados[i];
            Cell celda = fila.createCell(i);
            celda.setCellValue(encabezado);
        }

        indiceFila++;
        for (int i = 0; i < eventosCustoms.size(); i++) {
            fila = hoja.createRow(indiceFila);
            excelReporteEventosGral customs = eventosCustoms.get(i);

            fila.createCell(0).setCellValue(customs.getReferenciaAA());
            fila.createCell(1).setCellValue(customs.getEvento());
            fila.createCell(2).setCellValue(customs.getResponsable());
            fila.createCell(3).setCellValue(customs.getFinalDestination());
            fila.createCell(4).setCellValue(customs.getBrandDivision());
            fila.createCell(5).setCellValue(customs.getDivision());
            fila.createCell(6).setCellValue(customs.getShipmentId());
            fila.createCell(7).setCellValue(customs.getContainerId());
            fila.createCell(8).setCellValue(customs.getBlAwbPro());
            fila.createCell(9).setCellValue(customs.getLoadType());
            fila.createCell(10).setCellValue(customs.getQuantity());
            fila.createCell(11).setCellValue(customs.getPod());
            fila.createCell(12).setCellValue(customs.getEstDepartFromPol());
            fila.createCell(13).setCellValue(customs.getEtaRealPortOfDischarge());
            fila.createCell(14).setCellValue(customs.getEstEtaDc());
            fila.createCell(15).setCellValue(customs.getInboundNotification());
            fila.createCell(16).setCellValue(customs.getPol());
            fila.createCell(17).setCellValue(customs.getAa());
            fila.createCell(18).setCellValue(customs.getFechaMesVenta());
            fila.createCell(19).setCellValue(customs.getPrioridad());
            fila.createCell(20).setCellValue(customs.getPais_origen());
            fila.createCell(21).setCellValue(customs.getSize_container());
            fila.createCell(22).setCellValue(customs.getValor_usd());
            fila.createCell(23).setCellValue(customs.getEta_port_discharge());
            fila.createCell(24).setCellValue(customs.getAgente_aduanal());
            fila.createCell(25).setCellValue(customs.getPedimento_a1());
            fila.createCell(26).setCellValue(customs.getPedimento_r1_1er());
            fila.createCell(27).setCellValue(customs.getMotivo_rectificacion_1er());
            fila.createCell(28).setCellValue(customs.getPedimento_r1_2do());
            fila.createCell(29).setCellValue(customs.getMotivo_rectificacion_2do());
            fila.createCell(30).setCellValue(customs.getFecha_recepcion_doc());
            fila.createCell(31).setCellValue(customs.getRecinto());
            fila.createCell(32).setCellValue(customs.getNaviera());
            fila.createCell(33).setCellValue(customs.getBuque());
            fila.createCell(34).setCellValue(customs.getFecha_revalidacion());
            fila.createCell(35).setCellValue(customs.getFecha_previo_origen());
            fila.createCell(36).setCellValue(customs.getFecha_previo_destino());
            fila.createCell(37).setCellValue(customs.getFecha_resultado_previo());
            fila.createCell(38).setCellValue(customs.getProforma_final());
            fila.createCell(39).setCellValue(customs.getPermiso());
            fila.createCell(40).setCellValue(customs.getFecha_envio());
            fila.createCell(41).setCellValue(customs.getFecha_recepcion_perm());
            fila.createCell(42).setCellValue(customs.getFecha_activacion_perm());
            fila.createCell(43).setCellValue(customs.getFecha_permisos_aut());
            fila.createCell(44).setCellValue(customs.getCo_pref_arancelaria());
            fila.createCell(45).setCellValue(customs.getAplic_pref_arancelaria());
            fila.createCell(46).setCellValue(customs.getReq_uva());
            fila.createCell(47).setCellValue(customs.getReq_ca());
            fila.createCell(48).setCellValue(customs.getFecha_recepcion_ca());
            fila.createCell(49).setCellValue(customs.getNum_constancia_ca());
            fila.createCell(50).setCellValue(customs.getMonto_ca());
            fila.createCell(51).setCellValue(customs.getFecha_doc_completos());
            fila.createCell(52).setCellValue(customs.getFecha_pago_pedimento());
            fila.createCell(53).setCellValue(customs.getFecha_solicitud_transporte());
            fila.createCell(54).setCellValue(customs.getFecha_modulacion());
            fila.createCell(55).setCellValue(customs.getModalidad());
            fila.createCell(56).setCellValue(customs.getResultado_modulacion());
            fila.createCell(57).setCellValue(customs.getFecha_reconocimiento());
            fila.createCell(58).setCellValue(customs.getFecha_liberacion());
            fila.createCell(59).setCellValue(customs.getSello_origen());
            fila.createCell(60).setCellValue(customs.getSello_final());
            fila.createCell(61).setCellValue(customs.getFecha_retencion_aut());
            fila.createCell(62).setCellValue(customs.getFecha_liberacion_aut());
            fila.createCell(63).setCellValue(customs.getEstatus_operacion());
            fila.createCell(64).setCellValue(customs.getMotivo_atraso());
            fila.createCell(65).setCellValue(customs.getObservaciones());
            fila.createCell(66).setCellValue(customs.getFy());

            indiceFila++;
        }

        // Guardamos
        //File directorioActual = new File(".");
        //String ubicacion = directorioActual.getAbsolutePath();
        String ubicacionArchivoSalida = "D:\\Servicios\\VFINBOUND\\" + nombreArchivo;

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

    
    
    
    
    
    
    
    
    public static String crearAPartirDeArrayListReporteEventosLogix(String consultaReporte, String AgentType, String nameAgentType) {

        ArrayList<excelReporteEventosLogix> eventosCustoms = new ArrayList<>();
        ServiceDAO dao = new ServiceDAO();
        ConsultasQuery fac = new ConsultasQuery();

        try {

            Statement stmt = dao.conectar().prepareStatement(consultaReporte);
            ResultSet rs = stmt.executeQuery(consultaReporte);
            while (rs.next()) {
                eventosCustoms.add(new excelReporteEventosLogix(
                        rs.getString(31), // referenciaAA	
                        rs.getString(1), // evento 
                        rs.getString(2), // responsable 
                        rs.getString(3), // finalDestination	
                        rs.getString(22), // brandDivision 
                        rs.getString(5), // division	
                        rs.getString(6), // shipmentId 
                        rs.getString(7), // containerId 
                        rs.getString(8), // blAwbPro	
                        rs.getString(9), // loadType	
                        rs.getString(10), // quantity	
                        rs.getString(20), // pod 
                        rs.getString(12), // estDepartFromPol	
                        rs.getString(13), // etaRealPortOfDischarge 
                        rs.getString(23), // estEtaDc	
                        rs.getString(15), // inboundNotification	
                        rs.getString(21), // pol 
                        rs.getString(17), // aa 
                        rs.getString(29), // fechaMesVenta 
                        rs.getString(98), // prioridad
                        rs.getString(32), // pais_origen	
                        rs.getString(33), // size_container	
                        rs.getString(34), // valor_usd	
                        rs.getString(35), // eta_port_discharge	
                        rs.getString(36), // agente_aduanal	
                        rs.getString(37), // pedimento_a1	
                        rs.getString(38), // pedimento_r1_1er	
                        rs.getString(39), // motivo_rectificacion_1er	
                        rs.getString(40), // pedimento_r1_2do	
                        rs.getString(41), // motivo_rectificacion_2do	
                        rs.getString(42), // fecha_recepcion_doc	
                        rs.getString(43), // recinto	
                        rs.getString(44), // naviera	
                        rs.getString(45), // buque	
                        rs.getString(46), // fecha_revalidacion	
                        rs.getString(47), // fecha_previo_origen	
                        rs.getString(48), // fecha_previo_destino	
                        rs.getString(49), // fecha_resultado_previo	
                        rs.getString(50), // proforma_final	
                        rs.getString(51), // permiso	
                        rs.getString(52), // fecha_envio	
                        rs.getString(53), // fecha_recepcion_perm	
                        rs.getString(54), // fecha_activacion_perm	
                        rs.getString(55), // fecha_permisos_aut	
                        rs.getString(56), // co_pref_arancelaria	
                        rs.getString(57), // aplic_pref_arancelaria	
                        rs.getString(58), // req_uva	
                        rs.getString(59), // req_ca	
                        rs.getString(60), // fecha_recepcion_ca							
                        rs.getString(61), // num_constancia_ca	
                        rs.getString(62), // monto_ca	
                        rs.getString(63), // fecha_doc_completos	
                        rs.getString(64), // fecha_pago_pedimento	
                        rs.getString(65), // fecha_solicitud_transporte	
                        rs.getString(66), // fecha_modulacion	
                        rs.getString(67), // modalidad	
                        rs.getString(68), // resultado_modulacion	
                        rs.getString(69), // fecha_reconocimiento	
                        rs.getString(70), // fecha_liberacion	
                        rs.getString(71), // sello_origen	
                        rs.getString(72), // sello_final	
                        rs.getString(73), // fecha_retencion_aut	
                        rs.getString(74), // fecha_liberacion_aut	
                        rs.getString(75), // estatus_operacion	
                        rs.getString(76), // motivo_atraso	
                        rs.getString(77), // observaciones	
                        rs.getString(78), // llegada_a_nova	
                        rs.getString(79), // llegada_a_globe_trade_sd	
                        rs.getString(80), // archivo_m	
                        rs.getString(81), // fecha_archivo_m	
                        rs.getString(82), // fecha_solicit_manip	
                        rs.getString(83), // fecha_vencim_manip	
                        rs.getString(84), // fecha_confirm_clave_pedim	
                        rs.getString(85), // fecha_recep_increment	
                        rs.getString(86), // t_e	
                        rs.getString(87), // fecha_vencim_inbound	
                        rs.getString(96) // fy
                ));
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();

        final String nombreArchivo = "ReporteEventosCustoms.xls";
        Sheet hoja = workbook.createSheet("Detalle Eventos Customs " + nameAgentType);

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
                "LLEGADA_A_NOVA",
                "LLEGADA_A_GLOBE_TRADE_SD",
                "ARCHIVO_M",
                "FECHA_ARCHIVO_M",
                "FECHA_SOLICIT_MANIP",
                "FECHA_VENCIM_MANIP",
                "FECHA_CONFIRM_CLAVE_PEDIM",
                "FECHA_RECEP_INCREMENT",
                "T_E",
                "FECHA_VENCIM_INBOUND",
                "FY"};

        int indiceFila = 0;

        Row fila = hoja.createRow(indiceFila);
        for (int i = 0; i < encabezados.length; i++) {
            String encabezado = encabezados[i];
            Cell celda = fila.createCell(i);
            celda.setCellValue(encabezado);
        }

        indiceFila++;
        for (int i = 0; i < eventosCustoms.size(); i++) {
            fila = hoja.createRow(indiceFila);
            excelReporteEventosLogix customs = eventosCustoms.get(i);

            fila.createCell(0).setCellValue(customs.getReferenciaAA());
            fila.createCell(1).setCellValue(customs.getEvento());
            fila.createCell(2).setCellValue(customs.getResponsable());
            fila.createCell(3).setCellValue(customs.getFinalDestination());
            fila.createCell(4).setCellValue(customs.getBrandDivision());
            fila.createCell(5).setCellValue(customs.getDivision());
            fila.createCell(6).setCellValue(customs.getShipmentId());
            fila.createCell(7).setCellValue(customs.getContainerId());
            fila.createCell(8).setCellValue(customs.getBlAwbPro());
            fila.createCell(9).setCellValue(customs.getLoadType());
            fila.createCell(10).setCellValue(customs.getQuantity());
            fila.createCell(11).setCellValue(customs.getPod());
            fila.createCell(12).setCellValue(customs.getEstDepartFromPol());
            fila.createCell(13).setCellValue(customs.getEtaRealPortOfDischarge());
            fila.createCell(14).setCellValue(customs.getEstEtaDc());
            fila.createCell(15).setCellValue(customs.getInboundNotification());
            fila.createCell(16).setCellValue(customs.getPol());
            fila.createCell(17).setCellValue(customs.getAa());
            fila.createCell(18).setCellValue(customs.getFechaMesVenta());
            fila.createCell(19).setCellValue(customs.getPrioridad());
            fila.createCell(20).setCellValue(customs.getPais_origen());
            fila.createCell(21).setCellValue(customs.getSize_container());
            fila.createCell(22).setCellValue(customs.getValor_usd());
            fila.createCell(23).setCellValue(customs.getEta_port_discharge());
            fila.createCell(24).setCellValue(customs.getAgente_aduanal());
            fila.createCell(25).setCellValue(customs.getPedimento_a1());
            fila.createCell(26).setCellValue(customs.getPedimento_r1_1er());
            fila.createCell(27).setCellValue(customs.getMotivo_rectificacion_1er());
            fila.createCell(28).setCellValue(customs.getPedimento_r1_2do());
            fila.createCell(29).setCellValue(customs.getMotivo_rectificacion_2do());
            fila.createCell(30).setCellValue(customs.getFecha_recepcion_doc());
            fila.createCell(31).setCellValue(customs.getRecinto());
            fila.createCell(32).setCellValue(customs.getNaviera());
            fila.createCell(33).setCellValue(customs.getBuque());
            fila.createCell(34).setCellValue(customs.getFecha_revalidacion());
            fila.createCell(35).setCellValue(customs.getFecha_previo_origen());
            fila.createCell(36).setCellValue(customs.getFecha_previo_destino());
            fila.createCell(37).setCellValue(customs.getFecha_resultado_previo());
            fila.createCell(38).setCellValue(customs.getProforma_final());
            fila.createCell(39).setCellValue(customs.getPermiso());
            fila.createCell(40).setCellValue(customs.getFecha_envio());
            fila.createCell(41).setCellValue(customs.getFecha_recepcion_perm());
            fila.createCell(42).setCellValue(customs.getFecha_activacion_perm());
            fila.createCell(43).setCellValue(customs.getFecha_permisos_aut());
            fila.createCell(44).setCellValue(customs.getCo_pref_arancelaria());
            fila.createCell(45).setCellValue(customs.getAplic_pref_arancelaria());
            fila.createCell(46).setCellValue(customs.getReq_uva());
            fila.createCell(47).setCellValue(customs.getReq_ca());
            fila.createCell(48).setCellValue(customs.getFecha_recepcion_ca());
            fila.createCell(49).setCellValue(customs.getNum_constancia_ca());
            fila.createCell(50).setCellValue(customs.getMonto_ca());
            fila.createCell(51).setCellValue(customs.getFecha_doc_completos());
            fila.createCell(52).setCellValue(customs.getFecha_pago_pedimento());
            fila.createCell(53).setCellValue(customs.getFecha_solicitud_transporte());
            fila.createCell(54).setCellValue(customs.getFecha_modulacion());
            fila.createCell(55).setCellValue(customs.getModalidad());
            fila.createCell(56).setCellValue(customs.getResultado_modulacion());
            fila.createCell(57).setCellValue(customs.getFecha_reconocimiento());
            fila.createCell(58).setCellValue(customs.getFecha_liberacion());
            fila.createCell(59).setCellValue(customs.getSello_origen());
            fila.createCell(60).setCellValue(customs.getSello_final());
            fila.createCell(61).setCellValue(customs.getFecha_retencion_aut());
            fila.createCell(62).setCellValue(customs.getFecha_liberacion_aut());
            fila.createCell(63).setCellValue(customs.getEstatus_operacion());
            fila.createCell(64).setCellValue(customs.getMotivo_atraso());
            fila.createCell(65).setCellValue(customs.getObservaciones());
            fila.createCell(66).setCellValue(customs.getLlegada_a_nova());
            fila.createCell(67).setCellValue(customs.getLlegada_a_globe_trade_sd());
            fila.createCell(68).setCellValue(customs.getArchivo_m());
            fila.createCell(69).setCellValue(customs.getFecha_archivo_m());
            fila.createCell(70).setCellValue(customs.getFecha_solicit_manip());
            fila.createCell(71).setCellValue(customs.getFecha_vencim_manip());
            fila.createCell(72).setCellValue(customs.getFecha_confirm_clave_pedim());
            fila.createCell(73).setCellValue(customs.getFecha_recep_increment());
            fila.createCell(74).setCellValue(customs.getT_e());
            fila.createCell(75).setCellValue(customs.getFecha_vencim_inbound());
            fila.createCell(76).setCellValue(customs.getFy());

            indiceFila++;
        }

        // Guardamos
        //File directorioActual = new File(".");
        //String ubicacion = directorioActual.getAbsolutePath();
        String ubicacionArchivoSalida = "D:\\Servicios\\VFINBOUND\\" + nombreArchivo;

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
    
    public static String crearAPartirDeArrayListReporteEventosCusa(String consultaReporte, String AgentType, String nameAgentType) {

        ArrayList<excelReporteEventosCusa> eventosCustoms = new ArrayList<>();
        ServiceDAO dao = new ServiceDAO();
        ConsultasQuery fac = new ConsultasQuery();

        try {

            Statement stmt = dao.conectar().prepareStatement(consultaReporte);
            ResultSet rs = stmt.executeQuery(consultaReporte);
            while (rs.next()) {
                eventosCustoms.add(new excelReporteEventosCusa(
                        rs.getString(31), // referenciaAA	
                        rs.getString(1), // evento 
                        rs.getString(2), // responsable 
                        rs.getString(3), // finalDestination	
                        rs.getString(22), // brandDivision 
                        rs.getString(5), // division	
                        rs.getString(6), // shipmentId 
                        rs.getString(7), // containerId 
                        rs.getString(8), // blAwbPro	
                        rs.getString(9), // loadType	
                        rs.getString(10), // quantity	
                        rs.getString(20), // pod 
                        rs.getString(12), // estDepartFromPol	
                        rs.getString(13), // etaRealPortOfDischarge 
                        rs.getString(23), // estEtaDc	
                        rs.getString(15), // inboundNotification	
                        rs.getString(21), // pol 
                        rs.getString(17), // aa 
                        rs.getString(29), // fechaMesVenta 
                        rs.getString(98), // prioridad
                        rs.getString(32), // pais_origen	
                        rs.getString(33), // size_container	
                        rs.getString(34), // valor_usd	
                        rs.getString(35), // eta_port_discharge	
                        rs.getString(36), // agente_aduanal	
                        rs.getString(37), // pedimento_a1	
                        rs.getString(38), // pedimento_r1_1er	
                        rs.getString(39), // motivo_rectificacion_1er	
                        rs.getString(40), // pedimento_r1_2do	
                        rs.getString(41), // motivo_rectificacion_2do	
                        rs.getString(42), // fecha_recepcion_doc	
                        rs.getString(43), // recinto	
                        rs.getString(44), // naviera	
                        rs.getString(45), // buque	
                        rs.getString(46), // fecha_revalidacion	
                        rs.getString(47), // fecha_previo_origen	
                        rs.getString(48), // fecha_previo_destino	
                        rs.getString(49), // fecha_resultado_previo	
                        rs.getString(50), // proforma_final	
                        rs.getString(51), // permiso	
                        rs.getString(52), // fecha_envio	
                        rs.getString(53), // fecha_recepcion_perm	
                        rs.getString(54), // fecha_activacion_perm	
                        rs.getString(55), // fecha_permisos_aut	
                        rs.getString(56), // co_pref_arancelaria	
                        rs.getString(57), // aplic_pref_arancelaria	
                        rs.getString(58), // req_uva	
                        rs.getString(59), // req_ca	
                        rs.getString(60), // fecha_recepcion_ca							
                        rs.getString(61), // num_constancia_ca	
                        rs.getString(62), // monto_ca	
                        rs.getString(63), // fecha_doc_completos	
                        rs.getString(64), // fecha_pago_pedimento	
                        rs.getString(65), // fecha_solicitud_transporte	
                        rs.getString(66), // fecha_modulacion	
                        rs.getString(67), // modalidad	
                        rs.getString(68), // resultado_modulacion	
                        rs.getString(69), // fecha_reconocimiento	
                        rs.getString(70), // fecha_liberacion	
                        rs.getString(71), // sello_origen	
                        rs.getString(72), // sello_final	
                        rs.getString(73), // fecha_retencion_aut	
                        rs.getString(74), // fecha_liberacion_aut	
                        rs.getString(75), // estatus_operacion	
                        rs.getString(76), // motivo_atraso	
                        rs.getString(77), // observaciones	
                        rs.getString(88), // no_bultos	
                        rs.getString(89), // peso_kg	
                        rs.getString(90), // transferencia	
                        rs.getString(91), // fecha_inicio_etiquetado	
                        rs.getString(92), // fecha_termino_etiquetado	
                        rs.getString(93), // hora_termino_etiquetado	
                        rs.getString(94), // proveedor	
                        rs.getString(95), // proveedor_carga
                        rs.getString(96) // fy
                ));
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();

        final String nombreArchivo = "ReporteEventosCustoms.xls";
        Sheet hoja = workbook.createSheet("Detalle Eventos Customs " + nameAgentType);

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
                "NO_BULTOS",
                "PESO_KG",
                "TRANSFERENCIA",
                "FECHA_INICIO_ETIQUETADO",
                "FECHA_TERMINO_ETIQUETADO",
                "HORA_TERMINO_ETIQUETADO",
                "PROVEEDOR",
                "PROVEEDOR_CARGA",
                "FY"};

        int indiceFila = 0;

        Row fila = hoja.createRow(indiceFila);
        for (int i = 0; i < encabezados.length; i++) {
            String encabezado = encabezados[i];
            Cell celda = fila.createCell(i);
            celda.setCellValue(encabezado);
        }

        indiceFila++;
        for (int i = 0; i < eventosCustoms.size(); i++) {
            fila = hoja.createRow(indiceFila);
            excelReporteEventosCusa customs = eventosCustoms.get(i);

            fila.createCell(0).setCellValue(customs.getReferenciaAA());
            fila.createCell(1).setCellValue(customs.getEvento());
            fila.createCell(2).setCellValue(customs.getResponsable());
            fila.createCell(3).setCellValue(customs.getFinalDestination());
            fila.createCell(4).setCellValue(customs.getBrandDivision());
            fila.createCell(5).setCellValue(customs.getDivision());
            fila.createCell(6).setCellValue(customs.getShipmentId());
            fila.createCell(7).setCellValue(customs.getContainerId());
            fila.createCell(8).setCellValue(customs.getBlAwbPro());
            fila.createCell(9).setCellValue(customs.getLoadType());
            fila.createCell(10).setCellValue(customs.getQuantity());
            fila.createCell(11).setCellValue(customs.getPod());
            fila.createCell(12).setCellValue(customs.getEstDepartFromPol());
            fila.createCell(13).setCellValue(customs.getEtaRealPortOfDischarge());
            fila.createCell(14).setCellValue(customs.getEstEtaDc());
            fila.createCell(15).setCellValue(customs.getInboundNotification());
            fila.createCell(16).setCellValue(customs.getPol());
            fila.createCell(17).setCellValue(customs.getAa());
            fila.createCell(18).setCellValue(customs.getFechaMesVenta());
            fila.createCell(19).setCellValue(customs.getPrioridad());
            fila.createCell(20).setCellValue(customs.getPais_origen());
            fila.createCell(21).setCellValue(customs.getSize_container());
            fila.createCell(22).setCellValue(customs.getValor_usd());
            fila.createCell(23).setCellValue(customs.getEta_port_discharge());
            fila.createCell(24).setCellValue(customs.getAgente_aduanal());
            fila.createCell(25).setCellValue(customs.getPedimento_a1());
            fila.createCell(26).setCellValue(customs.getPedimento_r1_1er());
            fila.createCell(27).setCellValue(customs.getMotivo_rectificacion_1er());
            fila.createCell(28).setCellValue(customs.getPedimento_r1_2do());
            fila.createCell(29).setCellValue(customs.getMotivo_rectificacion_2do());
            fila.createCell(30).setCellValue(customs.getFecha_recepcion_doc());
            fila.createCell(31).setCellValue(customs.getRecinto());
            fila.createCell(32).setCellValue(customs.getNaviera());
            fila.createCell(33).setCellValue(customs.getBuque());
            fila.createCell(34).setCellValue(customs.getFecha_revalidacion());
            fila.createCell(35).setCellValue(customs.getFecha_previo_origen());
            fila.createCell(36).setCellValue(customs.getFecha_previo_destino());
            fila.createCell(37).setCellValue(customs.getFecha_resultado_previo());
            fila.createCell(38).setCellValue(customs.getProforma_final());
            fila.createCell(39).setCellValue(customs.getPermiso());
            fila.createCell(40).setCellValue(customs.getFecha_envio());
            fila.createCell(41).setCellValue(customs.getFecha_recepcion_perm());
            fila.createCell(42).setCellValue(customs.getFecha_activacion_perm());
            fila.createCell(43).setCellValue(customs.getFecha_permisos_aut());
            fila.createCell(44).setCellValue(customs.getCo_pref_arancelaria());
            fila.createCell(45).setCellValue(customs.getAplic_pref_arancelaria());
            fila.createCell(46).setCellValue(customs.getReq_uva());
            fila.createCell(47).setCellValue(customs.getReq_ca());
            fila.createCell(48).setCellValue(customs.getFecha_recepcion_ca());
            fila.createCell(49).setCellValue(customs.getNum_constancia_ca());
            fila.createCell(50).setCellValue(customs.getMonto_ca());
            fila.createCell(51).setCellValue(customs.getFecha_doc_completos());
            fila.createCell(52).setCellValue(customs.getFecha_pago_pedimento());
            fila.createCell(53).setCellValue(customs.getFecha_solicitud_transporte());
            fila.createCell(54).setCellValue(customs.getFecha_modulacion());
            fila.createCell(55).setCellValue(customs.getModalidad());
            fila.createCell(56).setCellValue(customs.getResultado_modulacion());
            fila.createCell(57).setCellValue(customs.getFecha_reconocimiento());
            fila.createCell(58).setCellValue(customs.getFecha_liberacion());
            fila.createCell(59).setCellValue(customs.getSello_origen());
            fila.createCell(60).setCellValue(customs.getSello_final());
            fila.createCell(61).setCellValue(customs.getFecha_retencion_aut());
            fila.createCell(62).setCellValue(customs.getFecha_liberacion_aut());
            fila.createCell(63).setCellValue(customs.getEstatus_operacion());
            fila.createCell(64).setCellValue(customs.getMotivo_atraso());
            fila.createCell(65).setCellValue(customs.getObservaciones());
            fila.createCell(66).setCellValue(customs.getNo_bultos());
            fila.createCell(67).setCellValue(customs.getPeso_kg());
            fila.createCell(68).setCellValue(customs.getTransferencia());
            fila.createCell(69).setCellValue(customs.getFecha_inicio_etiquetado());
            fila.createCell(70).setCellValue(customs.getFecha_termino_etiquetado());
            fila.createCell(71).setCellValue(customs.getHora_termino_etiquetado());
            fila.createCell(72).setCellValue(customs.getProveedor());
            fila.createCell(73).setCellValue(customs.getProveedor_carga());
            fila.createCell(74).setCellValue(customs.getFy());

            indiceFila++;
        }

        // Guardamos
        //File directorioActual = new File(".");
        //String ubicacion = directorioActual.getAbsolutePath();
        String ubicacionArchivoSalida = "D:\\Servicios\\VFINBOUND\\" + nombreArchivo;

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

     ArrayList<excelReporteEventosAdmin> eventosCustoms = new ArrayList<>();
        ServiceDAO dao = new ServiceDAO();
        ConsultasQuery fac = new ConsultasQuery();

        try {

            Statement stmt = dao.conectar().prepareStatement(consultaReporte);
            ResultSet rs = stmt.executeQuery(consultaReporte);
            while (rs.next()) {
                eventosCustoms.add(new excelReporteEventosAdmin(
                rs.getString(31), // referenciaAA	
                        rs.getString(1), // evento 
                        rs.getString(2), // responsable 
                        rs.getString(3), // finalDestination	
                        rs.getString(22), // brandDivision 
                        rs.getString(5), // division	
                        rs.getString(6), // shipmentId 
                        rs.getString(7), // containerId 
                        rs.getString(8), // blAwbPro	
                        rs.getString(9), // loadType	
                        rs.getString(10), // quantity	
                        rs.getString(20), // pod 
                        rs.getString(12), // estDepartFromPol	
                        rs.getString(13), // etaRealPortOfDischarge 
                        rs.getString(23), // estEtaDc	
                        rs.getString(15), // inboundNotification	
                        rs.getString(21), // pol 
                        rs.getString(17), // aa 
                        rs.getString(29), // fechaMesVenta 
                        rs.getString(98), // prioridad
                        rs.getString(32), // pais_origen	
                        rs.getString(33), // size_container	
                        rs.getString(34), // valor_usd	
                        rs.getString(35), // eta_port_discharge	
                        rs.getString(36), // agente_aduanal	
                        rs.getString(37), // pedimento_a1	
                        rs.getString(38), // pedimento_r1_1er	
                        rs.getString(39), // motivo_rectificacion_1er	
                        rs.getString(40), // pedimento_r1_2do	
                        rs.getString(41), // motivo_rectificacion_2do	
                        rs.getString(42), // fecha_recepcion_doc	
                        rs.getString(43), // recinto	
                        rs.getString(44), // naviera	
                        rs.getString(45), // buque	
                        rs.getString(46), // fecha_revalidacion	
                        rs.getString(47), // fecha_previo_origen	
                        rs.getString(48), // fecha_previo_destino	
                        rs.getString(49), // fecha_resultado_previo	
                        rs.getString(50), // proforma_final	
                        rs.getString(51), // permiso	
                        rs.getString(52), // fecha_envio	
                        rs.getString(53), // fecha_recepcion_perm	
                        rs.getString(54), // fecha_activacion_perm	
                        rs.getString(55), // fecha_permisos_aut	
                        rs.getString(56), // co_pref_arancelaria	
                        rs.getString(57), // aplic_pref_arancelaria	
                        rs.getString(58), // req_uva	
                        rs.getString(59), // req_ca	
                        rs.getString(60), // fecha_recepcion_ca							
                        rs.getString(61), // num_constancia_ca	
                        rs.getString(62), // monto_ca	
                        rs.getString(63), // fecha_doc_completos	
                        rs.getString(64), // fecha_pago_pedimento	
                        rs.getString(65), // fecha_solicitud_transporte	
                        rs.getString(66), // fecha_modulacion	
                        rs.getString(67), // modalidad	
                        rs.getString(68), // resultado_modulacion	
                        rs.getString(69), // fecha_reconocimiento	
                        rs.getString(70), // fecha_liberacion	
                        rs.getString(71), // sello_origen	
                        rs.getString(72), // sello_final	
                        rs.getString(73), // fecha_retencion_aut	
                        rs.getString(74), // fecha_liberacion_aut	
                        rs.getString(75), // estatus_operacion	
                        rs.getString(76), // motivo_atraso	
                        rs.getString(77), // observaciones	
                        rs.getString(78), // llegada_a_nova	
                        rs.getString(79), // llegada_a_globe_trade_sd	
                        rs.getString(80), // archivo_m	
                        rs.getString(81), // fecha_archivo_m	
                        rs.getString(82), // fecha_solicit_manip	
                        rs.getString(83), // fecha_vencim_manip	
                        rs.getString(84), // fecha_confirm_clave_pedim	
                        rs.getString(85), // fecha_recep_increment	
                        rs.getString(86), // t_e	
                        rs.getString(87), // fecha_vencim_inbound	
                        rs.getString(88), // no_bultos	
                        rs.getString(89), // peso_kg	
                        rs.getString(90), // transferencia	
                        rs.getString(91), // fecha_inicio_etiquetado	
                        rs.getString(92), // fecha_termino_etiquetado	
                        rs.getString(93), // hora_termino_etiquetado	
                        rs.getString(94), // proveedor	
                        rs.getString(95), // proveedor_carga
                        rs.getString(96) // fy
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
                "LLEGADA_A_NOVA",
                "LLEGADA_A_GLOBE_TRADE_SD",
                "ARCHIVO_M",
                "FECHA_ARCHIVO_M",
                "FECHA_SOLICIT_MANIP",
                "FECHA_VENCIM_MANIP",
                "FECHA_CONFIRM_CLAVE_PEDIM",
                "FECHA_RECEP_INCREMENT",
                "T_E",
                "FECHA_VENCIM_INBOUND",
                "NO_BULTOS",
                "PESO_KG",
                "TRANSFERENCIA",
                "FECHA_INICIO_ETIQUETADO",
                "FECHA_TERMINO_ETIQUETADO",
                "HORA_TERMINO_ETIQUETADO",
                "PROVEEDOR",
                "PROVEEDOR_CARGA",
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
    for (excelReporteEventosAdmin customs : eventosCustoms) {
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
        private static String obtenerValorPorIndice(excelReporteEventosAdmin customs, int indice) {
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
case 66: return customs.getLlegada_a_nova();
case 67: return customs.getLlegada_a_globe_trade_sd();
case 68: return customs.getArchivo_m();
case 69: return customs.getFecha_archivo_m();
case 70: return customs.getFecha_solicit_manip();
case 71: return customs.getFecha_vencim_manip();
case 72: return customs.getFecha_confirm_clave_pedim();
case 73: return customs.getFecha_recep_increment();
case 74: return customs.getT_e();
case 75: return customs.getFecha_vencim_inbound();
case 76: return customs.getNo_bultos();
case 77: return customs.getPeso_kg();
case 78: return customs.getTransferencia();
case 79: return customs.getFecha_inicio_etiquetado();
case 80: return customs.getFecha_termino_etiquetado();
case 81: return customs.getHora_termino_etiquetado();
case 82: return customs.getProveedor();
case 83: return customs.getProveedor_carga();
case 84: return customs.getFy();
             
                default: return "";
            }
        }

    
    
 



}
