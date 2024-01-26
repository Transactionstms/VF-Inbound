/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacts.evidencias.inbound;
 
import com.dao.ServiceDAO;
import com.onest.train.consultas.ConsultasQuery;
import com.tacts.model.inbound.excelReporteEventosCustoms;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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

    public static String crearAPartirDeArrayListReporteEventosCustoms(String tipoAgente, String tipoFiltro, String id, String fechaInicial, String fechaFinal) {

        ArrayList<excelReporteEventosCustoms> eventosCustoms = new ArrayList<>();
        ServiceDAO dao = new ServiceDAO();
        ConsultasQuery fac = new ConsultasQuery();

        try {

            Statement stmt = dao.conectar().prepareStatement(fac.consultarEventosCustoms(tipoAgente, tipoFiltro, id));
            ResultSet rs = stmt.executeQuery(fac.consultarEventosCustoms(tipoAgente, tipoFiltro, id));
            while (rs.next()) {
                eventosCustoms.add(new excelReporteEventosCustoms(
                        rs.getString(1), // evento 
                        rs.getString(2), // referenciaAA	
                        rs.getString(3), // responsable 
                        rs.getString(4), // finalDestination	
                        rs.getString(5), // brandDivision 
                        rs.getString(6), // division	
                        rs.getString(7), // shipmentId 
                        rs.getString(8), // containerId 
                        rs.getString(9), // blAwbPro	
                        rs.getString(10), // loadType	
                        rs.getString(11), // quantity	
                        rs.getString(12), // pod 
                        rs.getString(13), // estDepartFromPol	
                        rs.getString(14), // etaRealPortOfDischarge 
                        rs.getString(15), // estEtaDc	
                        rs.getString(16), // inboundNotification	
                        rs.getString(17), // pol 
                        rs.getString(18), // aa 
                        rs.getString(19), // fechaMesVenta 
                        rs.getString(20), // prioridad
                        rs.getString(21), // pais_origen	
                        rs.getString(22), // size_container	
                        rs.getString(23), // valor_usd	
                        rs.getString(24), // eta_port_discharge	
                        rs.getString(25), // agente_aduanal	
                        rs.getString(26), // pedimento_a1	
                        rs.getString(27), // pedimento_r1_1er	
                        rs.getString(28), // motivo_rectificacion_1er	
                        rs.getString(29), // pedimento_r1_2do	
                        rs.getString(30), // motivo_rectificacion_2do	
                        rs.getString(31), // fecha_recepcion_doc	
                        rs.getString(32), // recinto	
                        rs.getString(33), // naviera	
                        rs.getString(34), // buque	
                        rs.getString(35), // fecha_revalidacion	
                        rs.getString(36), // fecha_previo_origen	
                        rs.getString(37), // fecha_previo_destino	
                        rs.getString(38), // fecha_resultado_previo	
                        rs.getString(39), // proforma_final	
                        rs.getString(40), // permiso	
                        rs.getString(41), // fecha_envio	
                        rs.getString(42), // fecha_recepcion_perm	
                        rs.getString(43), // fecha_activacion_perm	
                        rs.getString(44), // fecha_permisos_aut	
                        rs.getString(45), // co_pref_arancelaria	
                        rs.getString(46), // aplic_pref_arancelaria	
                        rs.getString(47), // req_uva	
                        rs.getString(48), // req_ca	
                        rs.getString(49), // fecha_recepcion_ca	
                        rs.getString(50), // num_constancia_ca	
                        rs.getString(51), // monto_ca	
                        rs.getString(52), // fecha_doc_completos	
                        rs.getString(53), // fecha_pago_pedimento	
                        rs.getString(54), // fecha_solicitud_transporte	
                        rs.getString(55), // fecha_modulacion	
                        rs.getString(56), // modalidad	
                        rs.getString(57), // resultado_modulacion	
                        rs.getString(58), // fecha_reconocimiento	
                        rs.getString(59), // fecha_liberacion	
                        rs.getString(60), // sello_origen	
                        rs.getString(61), // sello_final	
                        rs.getString(62), // fecha_retencion_aut	
                        rs.getString(63), // fecha_liberacion_aut	
                        rs.getString(64), // estatus_operacion	
                        rs.getString(65), // motivo_atraso	
                        rs.getString(66), // observaciones	
                        rs.getString(67), // llegada_a_nova	
                        rs.getString(68), // llegada_a_globe_trade_sd	
                        rs.getString(69), // archivo_m	
                        rs.getString(70), // fecha_archivo_m	
                        rs.getString(71), // fecha_solicit_manip	
                        rs.getString(72), // fecha_vencim_manip	
                        rs.getString(73), // fecha_confirm_clave_pedim	
                        rs.getString(74), // fecha_recep_increment	
                        rs.getString(75), // t_e	
                        rs.getString(76), // fecha_vencim_inbound	
                        rs.getString(77), // no_bultos	
                        rs.getString(78), // peso_kg	
                        rs.getString(79), // transferencia	
                        rs.getString(80), // fecha_inicio_etiquetado	
                        rs.getString(81), // fecha_termino_etiquetado	
                        rs.getString(82), // hora_termino_etiquetado	
                        rs.getString(83), // proveedor	
                        rs.getString(84), // proveedor_carga
                        rs.getString(85) // fy
                ));
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();

        final String nombreArchivo = "ReporteEventosCustoms.xls";
        Sheet hoja = workbook.createSheet("Detalle Eventos Customs " + tipoAgente);

        String[] encabezados = {
                "Evento",
                "Referenciaaa",
                "Responsable",
                "Finaldestination",
                "Branddivision",
                "Division",
                "Shipmentid",
                "Containerid",
                "Blawbpro",
                "Loadtype",
                "Quantity",
                "Pod",
                "Estdepartfrompol",
                "Etarealportofdischarge",
                "Estetadc",
                "Inboundnotification",
                "Pol",
                "Aa",
                "Fechamesventa",
                "Prioridad",
                "Pais_Origen",
                "Size_Container",
                "Valor_Usd",
                "Eta_Port_Discharge",
                "Agente_Aduanal",
                "Pedimento_A1",
                "Pedimento_R1_1Er",
                "Motivo_Rectificacion_1Er",
                "Pedimento_R1_2Do",
                "Motivo_Rectificacion_2Do",
                "Fecha_Recepcion_Doc",
                "Recinto",
                "Naviera",
                "Buque",
                "Fecha_Revalidacion",
                "Fecha_Previo_Origen",
                "Fecha_Previo_Destino",
                "Fecha_Resultado_Previo",
                "Proforma_Final",
                "Permiso",
                "Fecha_Envio",
                "Fecha_Recepcion_Perm",
                "Fecha_Activacion_Perm",
                "Fecha_Permisos_Aut",
                "Co_Pref_Arancelaria",
                "Aplic_Pref_Arancelaria",
                "Req_Uva",
                "Req_Ca",
                "Fecha_Recepcion_Ca",
                "Num_Constancia_Ca",
                "Monto_Ca",
                "Fecha_Doc_Completos",
                "Fecha_Pago_Pedimento",
                "Fecha_Solicitud_Transporte",
                "Fecha_Modulacion",
                "Modalidad",
                "Resultado_Modulacion",
                "Fecha_Reconocimiento",
                "Fecha_Liberacion",
                "Sello_Origen",
                "Sello_Final",
                "Fecha_Retencion_Aut",
                "Fecha_Liberacion_Aut",
                "Estatus_Operacion",
                "Motivo_Atraso",
                "Observaciones",
                "Llegada_A_Nova",
                "Llegada_A_Globe_Trade_Sd",
                "Archivo_M",
                "Fecha_Archivo_M",
                "Fecha_Solicit_Manip",
                "Fecha_Vencim_Manip",
                "Fecha_Confirm_Clave_Pedim",
                "Fecha_Recep_Increment",
                "T_E",
                "Fecha_Vencim_Inbound",
                "No_Bultos",
                "Peso_Kg",
                "Transferencia",
                "Fecha_Inicio_Etiquetado",
                "Fecha_Termino_Etiquetado",
                "Hora_Termino_Etiquetado",
                "Proveedor",
                "Proveedor_Carga",
                "Fy"};
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
            excelReporteEventosCustoms persona = eventosCustoms.get(i);

            fila.createCell(0).setCellValue(persona.getEvento());
            fila.createCell(1).setCellValue(persona.getReferenciaAA());
            fila.createCell(2).setCellValue(persona.getResponsable());
            fila.createCell(3).setCellValue(persona.getFinalDestination());
            fila.createCell(4).setCellValue(persona.getBrandDivision());
            fila.createCell(5).setCellValue(persona.getDivision());
            fila.createCell(6).setCellValue(persona.getShipmentId());
            fila.createCell(7).setCellValue(persona.getContainerId());
            fila.createCell(8).setCellValue(persona.getBlAwbPro());
            fila.createCell(9).setCellValue(persona.getLoadType());
            fila.createCell(10).setCellValue(persona.getQuantity());
            fila.createCell(11).setCellValue(persona.getPod());
            fila.createCell(12).setCellValue(persona.getEstDepartFromPol());
            fila.createCell(13).setCellValue(persona.getEtaRealPortOfDischarge());
            fila.createCell(14).setCellValue(persona.getEstEtaDc());
            fila.createCell(15).setCellValue(persona.getInboundNotification());
            fila.createCell(16).setCellValue(persona.getPol());
            fila.createCell(17).setCellValue(persona.getAa());
            fila.createCell(18).setCellValue(persona.getFechaMesVenta());
            fila.createCell(19).setCellValue(persona.getPrioridad());
            fila.createCell(20).setCellValue(persona.getPais_origen());
            fila.createCell(21).setCellValue(persona.getSize_container());
            fila.createCell(22).setCellValue(persona.getValor_usd());
            fila.createCell(23).setCellValue(persona.getEta_port_discharge());
            fila.createCell(24).setCellValue(persona.getAgente_aduanal());
            fila.createCell(25).setCellValue(persona.getPedimento_a1());
            fila.createCell(26).setCellValue(persona.getPedimento_r1_1er());
            fila.createCell(27).setCellValue(persona.getMotivo_rectificacion_1er());
            fila.createCell(28).setCellValue(persona.getPedimento_r1_2do());
            fila.createCell(29).setCellValue(persona.getMotivo_rectificacion_2do());
            fila.createCell(30).setCellValue(persona.getFecha_recepcion_doc());
            fila.createCell(31).setCellValue(persona.getRecinto());
            fila.createCell(32).setCellValue(persona.getNaviera());
            fila.createCell(33).setCellValue(persona.getBuque());
            fila.createCell(34).setCellValue(persona.getFecha_revalidacion());
            fila.createCell(35).setCellValue(persona.getFecha_previo_origen());
            fila.createCell(36).setCellValue(persona.getFecha_previo_destino());
            fila.createCell(37).setCellValue(persona.getFecha_resultado_previo());
            fila.createCell(38).setCellValue(persona.getProforma_final());
            fila.createCell(39).setCellValue(persona.getPermiso());
            fila.createCell(40).setCellValue(persona.getFecha_envio());
            fila.createCell(41).setCellValue(persona.getFecha_recepcion_perm());
            fila.createCell(42).setCellValue(persona.getFecha_activacion_perm());
            fila.createCell(43).setCellValue(persona.getFecha_permisos_aut());
            fila.createCell(44).setCellValue(persona.getCo_pref_arancelaria());
            fila.createCell(45).setCellValue(persona.getAplic_pref_arancelaria());
            fila.createCell(46).setCellValue(persona.getReq_uva());
            fila.createCell(47).setCellValue(persona.getReq_ca());
            fila.createCell(48).setCellValue(persona.getFecha_recepcion_ca());
            fila.createCell(49).setCellValue(persona.getNum_constancia_ca());
            fila.createCell(50).setCellValue(persona.getMonto_ca());
            fila.createCell(51).setCellValue(persona.getFecha_doc_completos());
            fila.createCell(52).setCellValue(persona.getFecha_pago_pedimento());
            fila.createCell(53).setCellValue(persona.getFecha_solicitud_transporte());
            fila.createCell(54).setCellValue(persona.getFecha_modulacion());
            fila.createCell(55).setCellValue(persona.getModalidad());
            fila.createCell(56).setCellValue(persona.getResultado_modulacion());
            fila.createCell(57).setCellValue(persona.getFecha_reconocimiento());
            fila.createCell(58).setCellValue(persona.getFecha_liberacion());
            fila.createCell(59).setCellValue(persona.getSello_origen());
            fila.createCell(60).setCellValue(persona.getSello_final());
            fila.createCell(61).setCellValue(persona.getFecha_retencion_aut());
            fila.createCell(62).setCellValue(persona.getFecha_liberacion_aut());
            fila.createCell(63).setCellValue(persona.getEstatus_operacion());
            fila.createCell(64).setCellValue(persona.getMotivo_atraso());
            fila.createCell(65).setCellValue(persona.getObservaciones());
            fila.createCell(66).setCellValue(persona.getLlegada_a_nova());
            fila.createCell(67).setCellValue(persona.getLlegada_a_globe_trade_sd());
            fila.createCell(68).setCellValue(persona.getArchivo_m());
            fila.createCell(69).setCellValue(persona.getFecha_archivo_m());
            fila.createCell(70).setCellValue(persona.getFecha_solicit_manip());
            fila.createCell(71).setCellValue(persona.getFecha_vencim_manip());
            fila.createCell(72).setCellValue(persona.getFecha_confirm_clave_pedim());
            fila.createCell(73).setCellValue(persona.getFecha_recep_increment());
            fila.createCell(74).setCellValue(persona.getT_e());
            fila.createCell(75).setCellValue(persona.getFecha_vencim_inbound());
            fila.createCell(76).setCellValue(persona.getNo_bultos());
            fila.createCell(77).setCellValue(persona.getPeso_kg());
            fila.createCell(78).setCellValue(persona.getTransferencia());
            fila.createCell(79).setCellValue(persona.getFecha_inicio_etiquetado());
            fila.createCell(80).setCellValue(persona.getFecha_termino_etiquetado());
            fila.createCell(81).setCellValue(persona.getHora_termino_etiquetado());
            fila.createCell(82).setCellValue(persona.getProveedor());
            fila.createCell(83).setCellValue(persona.getProveedor_carga());
            fila.createCell(84).setCellValue(persona.getFy());

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
}
