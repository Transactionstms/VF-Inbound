/*
 * To change this template, choose Tools | Templates
 * AND open the template in the editor.
 */
package com.onest.train.consultas;

/**
 *
 * @author Teresa Martin
 */
public class ConsultasReportes {

    String sql = "";

    public String planeacion(String[] id) {
        sql = "SELECT d_docto_pedido,d_docto_fec_pedido,d_docto_referencia, "
                + " to_char(d_docto_fec_captura, 'dd/mm/yyyy hh24:mi'),des_destino_nombre,"
                + " col_colonia_nombre,ciu_ciudad_nombre,d_docto_piezas,d_docto_cajas,"
                + " d_docto_pallets,d_docto_colgados,d_docto_contenedor,d_docto_atados,"
                + " d_docto_importe,d_docto_peso,d_docto_volumen,div_division_nombre"
                + " FROM ontms_vw_reporte_doc"
                + " WHERE trunc(d_docto_fec_captura) BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') "
                + " AND to_date(to_char(d_docto_fec_captura, 'hh24:mi'), 'hh24:mi')"
                + " BETWEEN to_date('1:00','hh24:mi') AND to_date('16:30','hh24:mi')"
                + " AND d_docto_estado_id IN (0,1) AND cbd_cuenta_id IN (" + id[0] + ") ORDER BY d_docto_referencia";
        return sql;

    }

    public String consultaEnviarTR2(String embarque, String cbdiv, String bodega) {

        String condicion = " ";

        if (!embarque.equals("")) {
            condicion += " and oe.embarque_id IN(" + embarque + ")";
        }
        /*
         *
         * if(!fecha1.equals("") && !fecha2.equals("") ){ condicion += " and
         * to_date(oe.embarque_fec_captura) BETWEEN '" + fecha1 + "' AND '" +
         * fecha2 + "' "; }
         */

        sql = " SELECT"
                + "  /*+ FIRST_ROWS */"
                + "  ods.docto_referencia,"
                + "  NVL(to_Char(ods.docto_fec_factura),'  '),"
                + "  NVL(ods.docto_pedido,0),"
                + "  NVL(to_char(ods.docto_fec_pedido),'  '),"
                + "  NVL(ods.docto_concentrado,'0'),"
                + "  ods.docto_fec_concentrado,"
                + "  NVL(ods.destino_id, 0),"
                + "  ods.bodega_id,"
                + "  ods.cbdiv_id,"
                + "  NVL(ods.docto_piezas,0),"
                + "  NVL(ods.docto_cajas,0),ods.TIPO_DOCTO_ID,otd.tipo_docto_desc,ddh.destino_ship_to"
                + " FROM ontms_docto_sal ods "
                + "INNER JOIN ontms_embarque oe "
                + "ON oe.embarque_agrupador=ods.docto_sal_agrupador "
                + "INNER JOIN app_estado oes "
                + "ON oes.edo_valor =ods.docto_estado_id "
                + "AND  oes.edo_tabla     ='ONTMS_DOCTO_SAL' "
                + "INNER JOIN ontms_cta_bod_div ocbd "
                + "ON ods.cbdiv_id = ocbd.cbdiv_id "
                + "INNER JOIN ontms_bodega ob "
                + "ON ob.bodega_id = ocbd.bodega_id "
                + "INNER JOIN dilog_destinos_hilti ddh "
                + "ON ods.destino_id   =ddh.DESTINO_SHIP_TO "
                + "AND ddh.division_id = ods.cbdiv_id "
                + "INNER JOIN dilog_orig_dest_paq dodp "
                + "ON dodp.ID_ORIGEN       =ob.zona_id "
                + "AND DODP.IATA_CP_DESTINO=DDH.DESTINO_CP "
                + "INNER JOIN ontms_tipo_destino OTD "
                + "ON dodp.tipo_destino_id =otd.tipo_destino_id  "
                + " INNER JOIN ontms_tipo_docto otd"
                + " ON otd.tipo_docto_id          = ods.tipo_docto_id"
                + " where oes.edo_tabla             ='ONTMS_DOCTO_SAL'"
                + " " + condicion + " "
                + " and ods.cbdiv_id =" + cbdiv + ""
                + " and ods.bodega_id not in (" + bodega + ") AND otd.tipo_destino_id<>1"
                + " ORDER BY ods.Docto_Referencia  ";

        //" AND to_date(oe.embarque_fec_captura) BETWEEN '"+fecha1+"' AND '"+fecha2+"'" +
        //" WHERE ods.cbdiv_id             IN ("+cbdiv+") " +
        System.out.println("SQL Reporte Descarga>" + sql);

        return sql;

    }

    public String d_evidencia(String[] id) {
        sql = "SELECT op.d_docto_referencia,op.d_docto_fec_captura,oe.oe_embarque_id,oe.oe_embarque_fec_captura,"
                + " oe.olt_ltransporte_nombre,oe.oc_chofer_nombre,oe.oca_camion_placas,op.d_docto_piezas,op.d_docto_cajas,"
                + " op.d_docto_pallets,op.d_docto_colgados,op.d_docto_contenedor,op.d_docto_atados,"
                + " op.d_docto_bulks,op.d_docto_importe,op.d_docto_peso,op.d_docto_volumen,op.d_docto_evidencia,"
                + " NVL(TO_CHAR(TO_DATE(op.d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' '),op.ord_origen_dest_dias_entrega,"
                + " (to_date(op.d_docto_fec_evidencia)-to_date(oe.oe_embarque_fec_captura)),"
                + " op.d_docto_captura_evidencia,op.ord_origen_dest_dias_retorno,(to_date(op.d_docto_captura_evidencia)-to_date(oe.oe_embarque_fec_captura))"
                + " FROM ontms_vw_reporte_doc op"
                + " INNER JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=op.d_docto_sal_agrupador"
                + " INNER JOIN ontms_cta_bod_div oc ON oc.cbdiv_id=op.d_cbdiv_id"
                + " WHERE oc.cuenta_id=" + id[0] + " AND to_date(oe.oe_embarque_fec_captura) BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') "
                + " AND oe.olt_ltransporte_id=" + id[3] + " AND op.d_docto_captura_evidencia <>' ' AND oe_embarque_id like '" + id[4] + "%'"
                + " ORDER BY op.d_docto_referencia";
        return sql;

    }

    public String tms(String[] id) {
        sql = "SELECT oe_embarque_id,oe_embarque_fec_captura,"
                + " oc_chofer_nombre,olt_ltransporte_nombre,nvl(oe_embarque_fec_flete,' '),"
                + " oe_sap_migrado,oe_sap_estatus,oe_embarque_estado_id,oe_embarque_costo_real"
                + " FROM vw_pq_em oe"
                + " INNER JOIN ontms_docto_sal ods on ods.docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div oc ON oc.cbdiv_id=ods.cbdiv_id"
                + " WHERE oc.cuenta_id in (" + id[0] + ") AND TO_DATE(oe_embarque_fec_captura) BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') "
                + " AND oe_embarque_id LIKE 'EM%'"
                + " GROUP BY oe_embarque_id,oe_embarque_fec_captura,"
                + " oc_chofer_nombre,olt_ltransporte_nombre,nvl(oe_embarque_fec_flete,' '),"
                + " oe_sap_migrado,oe_sap_estatus,oe_embarque_estado_id,oe_embarque_costo_real ORDER BY oe_embarque_id";
        return sql;

    }

    public String resumenFlete(String gto, Object iterador) {
        sql = "SELECT NVL(sum(embarque_indirectos_costo),0) "
                + " FROM ontms_embarque_indirectos oei "
                + " INNER JOIN ontms_gastos_transporte ogt ON ogt.gtransp_id=oei.gtransp_id "
                + " WHERE ogt.gtransp_id IN(" + gto + ") AND oei.embarque_agrupador IN (" + iterador + ")";
        return sql;
    }

    public String reporte(String[] id) {
        sql = "select  /*+ FIRST_ROWS */ * FROM ontms_vw_reporte_doc ods"
                + " LEFT JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador"
                + " WHERE d_docto_estado_id IN (" + id[3] + ") AND d_cbdiv_id IN (" + id[0] + ")"
                + " AND to_date(" + id[4] + ") BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY')  ORDER BY D_Docto_Referencia";
        return sql;
    }

    public String docEntregados(String[] id) {
        sql = "SELECT  /*+ FIRST_ROWS */ d_docto_captura_evidencia,ods.d_docto_referencia,"
                + " ods.d_docto_evidencia,ods.des_destino_nombre,ods.col_colonia_nombre,"
                + " ods.ciu_ciudad_nombre,ods.d_docto_piezas,ods.d_docto_cajas,ods.d_docto_pallets,"
                + " ods.d_docto_colgados, ods.d_docto_contenedor,ods.d_docto_atados,ods.d_docto_bulks,"
                + " ods.d_docto_importe,ods.d_docto_peso,ods.d_docto_volumen,ods.d_docto_observaciones,"
                + " ods.div_division_nombre"
                + " FROM ontms_vw_reporte_doc ods"
                + " LEFT JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador"
                + " WHERE d_docto_estado_id IN (" + id[3] + ") AND d_cbdiv_id IN (" + id[0] + ")"
                + " AND to_date(d_docto_captura_evidencia) BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') "
                + " ORDER BY d_docto_referencia";
        return sql;
    }

    public String pendienteEm(String[] id) {
        sql = "SELECT ods.d_docto_pedido,ods.d_docto_fec_pedido,ods.d_docto_referencia,"
                + " ods.d_docto_fec_captura,ods.des_destino_nombre,ods.col_colonia_nombre,"
                + " ods.ciu_ciudad_nombre,ods.d_docto_piezas,ods.d_docto_cajas,"
                + " ods.d_docto_pallets,ods.d_docto_colgados, ods.d_docto_contenedor,ods.d_docto_atados,"
                + " ods.d_docto_bulks,ods.d_docto_importe,ods.d_docto_peso,ods.d_docto_volumen,ods.div_division_nombre"
                + " FROM ontms_vw_reporte_doc ods"
                + " WHERE d_docto_estado_id IN (" + id[3] + ") AND d_cbdiv_id IN (" + id[0] + ")"
                + " AND trunc(d_docto_fec_captura) BETWEEN to_date('" + id[1] + "','DD/MM/YYYY') AND to_date('" + id[2] + "','DD/MM/YYYY') "
                + " ORDER BY D_Docto_Referencia";
        return sql;
    }

    public String reporteRechazo(String[] id) {
        sql = "SELECT /*+ FIRST_ROWS */ ods.d_docto_fec_rechazo,ods.d_docto_referencia, ods.d_docto_rechazo,"
                + " och.rechazo_desc,ods.des_destino_nombre,ods.col_colonia_nombre, ods.ciu_ciudad_nombre,"
                + "ods.d_docto_piezas, ods.d_docto_cajas,ods.d_docto_pallets, ods.d_docto_colgados,ods.d_docto_contenedor,"
                + "ods.d_docto_atados, ods.d_docto_bulks, ods.d_docto_importe,ods.d_docto_peso,ods.d_docto_volumen,ods.div_division_nombre "
                + "FROM ontms_vw_reporte_doc2 ods "
                + "INNER JOIN ontms_causa_rechazo och ON och.rechazo_id=ods.d_rechazo_id "
                + "WHERE d_cbdiv_id IN (" + id[0] + ") AND (to_date(TRIM(d_docto_fec_rechazo)) BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') "
                + "AND TRIM(d_docto_fec_rechazo) IS NOT NULL)";
        return sql;
    }

    public String pEvidencia(String[] id) {
        sql = "SELECT /*+ FIRST_ROWS */ ods.d_docto_fec_factura,"
                + " ods.d_docto_referencia,v.oe_embarque_id,v.oe_embarque_fec_captura,"
                + " v.olt_ltransporte_nombre,ods.des_destino_nombre,ods.col_colonia_nombre,"
                + " ods.ciu_ciudad_nombre,ods.d_docto_piezas,ods.d_docto_cajas,ods.d_docto_pallets,"
                + " ods.d_docto_colgados,ods.d_docto_contenedor,ods.d_docto_atados,ods.d_docto_bulks,"
                + " ods.d_docto_peso,ods.d_docto_importe,ods.d_docto_volumen,ods.div_division_nombre"
                + " FROM vw_pq_em v"
                + " INNER JOIN ontms_vw_reporte_doc ods ON ods.d_docto_sal_agrupador=v.oe_embarque_agrupador"
                + " WHERE ods.d_docto_estado_id IN (" + id[1] + ")  AND d_cbdiv_id IN (" + id[0] + ") " + id[2] + ""
                + " ORDER BY D_Docto_Referencia";
        return sql;
    }

    public String reporteStatus1(String cve, String fec, String fec1, String se, String le, String g) {
        sql = "SELECT  /*+ parallel(vw_reporte_ontime 16) */ vi.*" + se + " FROM vw_reporte_ontime vi"
                + " " + le + " WHERE vi.ods_cbdiv_id in (" + cve + ") AND edo_edo_tabla='ONTMS_DOCTO_SAL' AND "
                + " to_date(ods_docto_fec_captura) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + " ORDER BY " + g + "";
        return sql;
    }

    public String reporteO(String[] id) {
        sql = "SELECT /*+ FIRST_ROWS */ ods.div_division_nombre,ods.d_docto_referencia,ods.d_docto_fec_captura,"
                + " ods.d_docto_pedido,ods.d_docto_fec_pedido,ods.ods_docto_concentrado,ods.ods_docto_fec_concentrado,ods.conv_sucliente_id,"
                + " ods.des_destino_nombre,ods.ciu_ciudad_nombre,oe.oe_embarque_id,oe.oe_embarque_fec_captura,oe.oe_embarque_fec_fin,"
                + " oe.oe_embarque_fec_fin,ods.d_docto_piezas,ods.d_docto_cajas,ods.d_docto_pallets,ods.d_docto_colgados,"
                + " ods.d_docto_contenedor,ods.d_docto_atados,ods.d_docto_bulks,ods.d_docto_importe,ods.d_docto_peso, ods.d_docto_volumen,"
                + " oe.olt_ltransporte_nombre,oe.oc_chofer_nombre,oe.outr_utransporte_desc,oe.oca_camion_placas,ods.d_docto_rechazo,ods.d_docto_fec_rechazo,"
                + " ods.d_docto_evidencia,  NVL(TO_CHAR(TO_DATE(ods.d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ')   ,nvl(ods.d_docto_captura_evidencia,' '),ods.d_docto_observaciones,"
                + " ods.d_docto_folio_control,ods.d_docto_fec_control,nvl(ol.folio_liquidacion,' '),nvl(to_char(ol.fecha,'dd/mm/yyyy'),' ') "
                + " ,oa.edo_desc " + id[3] + ""
                + " FROM ontms_vw_reporte_doc ods"
                + " LEFT JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador"
                + " " + id[4] + ""
                + " LEFT JOIN ontms_liquidacion ol ON ol.embarque_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN app_estado oa ON edo_valor=ods.d_docto_estado_id"
                + " WHERE d_cbdiv_id IN (" + id[0] + ") AND oa.edo_tabla='ONTMS_DOCTO_SAL'"
                + " AND trunc(ods.d_docto_fec_captura) BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') "
                + " ORDER BY " + id[5] + "";

        return sql;
    }

    public String reporteOPuig(String[] id) {
        sql = "SELECT /*+ FIRST_ROWS */ "
                + " ods.d_docto_fec_captura,"
                + " ods.d_docto_pedido,"
                + " ods.conv_sucliente_id,"
                + " ods.des_destino_nombre,"
                + " ods.d_docto_referencia,"
                + " ods.d_docto_importe,"
                + " ods.d_docto_fec_programacion,"
                + " oa.edo_desc " + id[3] + ","
                + " ods.d_docto_observaciones"
                + " FROM ontms_vw_reporte_doc ods"
                + " LEFT JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador"
                + " " + id[4] + ""
                + " LEFT JOIN ontms_liquidacion ol ON ol.embarque_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN app_estado oa ON edo_valor=ods.d_docto_estado_id"
                + " WHERE d_cbdiv_id IN (" + id[0] + ") AND oa.edo_tabla='ONTMS_DOCTO_SAL'"
                + " AND trunc(ods.d_docto_fec_captura) BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') "
                + " ORDER BY " + id[5] + "";

        return sql;
    }

    public String reporteH(String[] id) {
        sql = "SELECT /*+ FIRST_ROWS */ ods.div_division_nombre,ods.d_docto_referencia,ods.d_docto_fec_captura,"
                + " ods.d_docto_pedido,ods.d_docto_fec_pedido,ods.ods_docto_concentrado,ods.ods_docto_fec_concentrado,ods.conv_sucliente_id,"
                + " ods.des_destino_nombre,ods.ciu_ciudad_nombre,oe.oe_embarque_id,oe.oe_embarque_fec_captura,oe.oe_embarque_fec_fin,"
                + " oe.oe_embarque_fec_fin,ods.d_docto_piezas,ods.d_docto_cajas,ods.d_docto_pallets,ods.d_docto_colgados,"
                + " ods.d_docto_contenedor,ods.d_docto_atados,ods.d_docto_bulks,ods.d_docto_importe,ods.d_docto_peso, ods.d_docto_volumen,"
                + " oe.olt_ltransporte_nombre,oe.oc_chofer_nombre,oe.outr_utransporte_desc,oe.oca_camion_placas,ods.d_docto_rechazo,ods.d_docto_fec_rechazo,"
                + " ods.d_docto_evidencia,  NVL(TO_CHAR(TO_DATE(ods.d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ')     ,nvl(ods.d_docto_captura_evidencia,' '),ods.d_docto_observaciones,"
                + " ods.d_docto_folio_control,ods.d_docto_fec_control,nvl(ol.folio_liquidacion,' '),nvl(to_char(ol.fecha,'dd/mm/yyyy'),' ') "
                + " ,oa.edo_desc " + id[3] + ""
                + " FROM ontms_vw_reporte_doc_hist ods"
                + " LEFT JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador"
                + " " + id[4] + ""
                + " LEFT JOIN ontms_liquidacion ol ON ol.embarque_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN app_estado oa ON edo_valor=ods.d_docto_estado_id"
                + " WHERE d_cbdiv_id IN (" + id[0] + ") AND oa.edo_tabla='ONTMS_DOCTO_SAL'"
                + " AND trunc(ods.d_docto_fec_captura) BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') "
                + " ORDER BY " + id[5] + "";
        return sql;
    }

    public String viajesDiariosDocumento(String[] id) {

        sql = "SELECT  /*+ FIRST_ROWS */  ods.div_division_nombre,  ods.d_docto_referencia,  ods.d_docto_fec_captura,  ods.d_docto_pedido,  ods.d_docto_fec_pedido,  ods.ddh_destino_ship_to,  ods.ddh_destino_nombre,  ods.ddh_destino_ciudad,  oe.oe_embarque_id,  oe.oe_embarque_fec_captura,  ods.d_docto_piezas,  ods.d_docto_cajas,  ods.d_docto_pallets,  ods.d_docto_colgados,  ods.d_docto_contenedor,  ods.d_docto_atados,  ods.d_docto_bulks,  ods.d_docto_importe,  ods.d_docto_peso,  ods.d_docto_volumen,  oe.olt_ltransporte_nombre,  oe.oc_chofer_nombre,  oe.outr_utransporte_desc,  oe.oca_camion_placas, oes.edo_desc,  ods.d_docto_evidencia,  NVL(TO_CHAR(TO_DATE(ods.d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ') ,  ods.d_docto_folio_control,  ods.d_docto_fec_control  "
                + " FROM ontms_vw_reporte_doc2 ods"
                + " LEFT JOIN vw_pq_em oe"
                + " ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador"
                + " INNER JOIN app_estado oes"
                + " ON oes.edo_valor                       =ods.d_docto_estado_id"
                + " WHERE d_cbdiv_id                      IN (" + id[0] + ")"
                + " AND oes.edo_tabla                      ='ONTMS_DOCTO_SAL'"
                + " AND to_Date(oe.oe_embarque_fec_captura,'dd/mm/yy') >= to_Date('" + id[1] + "','dd/mm/yy')"
                + " AND to_Date(oe.oe_embarque_fec_captura,'dd/mm/yy') <= to_Date('" + id[2] + "','dd/mm/yy') "
                + " ORDER BY D_Docto_Referencia";

        return sql;
    }

    public String reprogramados(String[] id) {

        sql = "SELECT   /*+ FIRST_ROWS */   ods.div_division_nombre,   ods.d_docto_referencia,   "
                + "ods.d_docto_fec_captura,   ods.d_docto_pedido,   ods.d_docto_fec_pedido,"
                + " ods.des_destino_nombre,  ods.ciu_ciudad_nombre, "
                + "ods.d_docto_piezas,  "
                + "ods.d_docto_cajas,  ods.d_docto_pallets,  ods.d_docto_colgados,"
                + "ods.d_docto_contenedor,  ods.d_docto_atados,  ods.d_docto_bulks,  "
                + "ods.d_docto_importe,  ods.d_docto_peso,  ods.d_docto_volumen,"
                + "oes.edo_desc FROM ontms_vw_reporte_doc ods INNER JOIN app_estado oes ON oes.edo_valor  =ods.d_docto_estado_id WHERE d_cbdiv_id IN (" + id[0] + ")"
                + " AND oes.edo_tabla ='ONTMS_DOCTO_SAL' AND to_date(ods.d_docto_fec_factura) BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY')  and ods.d_docto_estado_id=25 "
                + " ORDER BY D_Docto_Referencia";
        return sql;
    }

    public String viajes(String[] id) {
        sql = "SELECT oe.oe_embarque_id,oe.oe_embarque_fec_captura,oe.oe_embarque_agrupador,"
                + " '' as test,oe.oe_embarque_costo_real,"
                + " SUM(NVL(oc.d_docto_piezas, 0)),SUM(NVL(oc.d_docto_cajas, 0)),"
                + " SUM(NVL(oc.d_docto_pallets, 0)),SUM(NVL(oc.d_docto_colgados, 0)),"
                + " SUM(NVL(oc.d_Docto_Contenedor, 0)),SUM(NVL(oc.d_docto_atados, 0)),"
                + " SUM(NVL(oc.d_docto_bulks, 0)),SUM(NVL(oc.d_docto_importe, 0)),"
                + " SUM(NVL(oc.d_docto_peso, 0)),SUM(NVL(oc.d_docto_volumen, 0)),"
                + " NVL(oe.oc_chofer_nombre,' '),NVL(oe.olt_ltransporte_nombre,' '),NVL(oe.outr_utransporte_desc,' '),"
                + " oe_embarque_fec_inicio,oe_embarque_fec_fin"
                + " FROM vw_pq_em oe"
                + " INNER JOIN ontms_vw_reporte_doc2 oc ON oc.d_docto_Sal_agrupador=oe.oe_embarque_agrupador"
                //+ " WHERE oc.d_cbdiv_id IN (" + id[0] + ") AND to_date(oe_embarque_fec_captura,'DD/MM/YYYY') BETWEEN TO_DATE('"+id[1]+"','DD/MM/YYYY') AND TO_DATE('"+id[2]+"','DD/MM/YYYY') " + id[3] + ""
                + " WHERE oc.d_cbdiv_id IN (" + id[0] + ")  "
                + " AND oe.OE_EMBARQUE_FEC_CAPTURA >= to_date('" + id[1] + "','dd/mm/yy') "
                + " AND oe.OE_EMBARQUE_FEC_CAPTURA <= to_date('" + id[2] + "','dd/mm/yy') "
                + " GROUP BY oe.oe_embarque_id, oe.oe_embarque_fec_captura, oe.oe_embarque_agrupador,"
                + " oe.oe_embarque_costo_real, oe.oc_chofer_nombre, oe.olt_ltransporte_nombre, oe.outr_utransporte_desc,"
                + " oe_embarque_fec_inicio,oe_embarque_fec_fin"
                + " ORDER BY oe.oe_embarque_id";
        return sql;
    }

    public String gastos(String fec, String fec1, String cve, String cons, String t) {
        sql = "SELECT oe.embarque_id,to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),ol.ltransporte_nombre,oe.embarque_agrupador,oe.EMBARQUE_COSTO_REAL "
                + " FROM ontms_gastos_transporte ot"
                + " INNER JOIN ontms_embarque_indirectos oi ON oi.gtransp_id=ot.gtransp_id"
                + " " + cons + " JOIN ontms_embarque oe ON oe.embarque_agrupador=oi.embarque_agrupador"
                + " INNER JOIN ontms_camion oc ON oc.camion_id=oe.camion_id"
                + " INNER JOIN ontms_linea_transporte ol ON ol.ltransporte_id=oc.ltransporte_id"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id "
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + " AND ods.cbdiv_id in (" + cve + ") " + t + ""
                + " GROUP BY oe.embarque_id,oe.embarque_fec_captura,ol.ltransporte_nombre,oe.embarque_agrupador,oe.EMBARQUE_COSTO_REAL "
                + " order by oe.embarque_id";
        return sql;
    }

    public String reporteCargo(String cve) {
        sql = "SELECT cargo_id,cargo_fec,costo,cargo_folio,ocr.rechazo_desc,och.chofer_nombre, olt.ltransporte_nombre"
                + " FROM ontms_cargos oc"
                + " INNER JOIN ontms_causa_rechazo ocr ON ocr.rechazo_id=oc.rechazo_id"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=oc.embarque_agrupador"
                + " INNER JOIN ontms_camion oca ON oe.camion_id=oca.camion_id"
                + " INNER JOIN ontms_chofer och ON oe.chofer_id=och.chofer_id"
                + " INNER JOIN ontms_linea_transporte olt ON oca.ltransporte_id=olt.ltransporte_id"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id "
                + " INNER JOIN ontms_conversion_destino ocd oc ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " AND ods.cbdiv_id in (" + cve + ") AND oc.estado_cargo=0"
                + " GROUP BY CARGO_ID,CARGO_FEC,COSTO,CARGO_FOLIO,OCR.RECHAZO_DESC,och.chofer_nombre, olt.ltransporte_nombre";
        return sql;
    }

    public String reporteEmbarqueDoc(String fec1, String fec2, String cve, String t) {
        sql = "SELECT oe.embarque_agrupador,oe.embarque_id,to_char(oe.embarque_fec_captura,'dd/mm/yyyy'), SUM(NVL(ods.docto_piezas,0)),SUM(NVL(ods.docto_cajas,0)),SUM(NVL(ods.docto_pallets,0)),"
                + " SUM(NVL(ods.docto_colgados,0)),SUM(NVL(ods.docto_contenedor,0)),SUM(NVL(ods.docto_atados,0)),SUM(NVL(ods.docto_bulks,0)),"
                + " ot.ltransporte_nombre,outr.utransporte_desc,och.chofer_nombre,oe.embarque_costo_real,SUM(NVL(ods.docto_importe,0)), SUM(NVL(ods.docto_peso,0)),SUM(NVL(ods.docto_volumen,0))"
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id"
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " INNER JOIN ontms_linea_transporte ot ON ot.ltransporte_id=oca.ltransporte_id"
                + " INNER JOIN ontms_unidad_transporte outr ON outr.utransporte_id=oca.utransporte_id"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_division odi ON odi.division_id=ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY')  " + t + ""
                + " AND  ods.cbdiv_id in (" + cve + ") GROUP BY oe.embarque_agrupador, oe.embarque_id, oe.embarque_fec_captura,"
                + " ot.ltransporte_nombre, outr.utransporte_desc, och.chofer_nombre,oe.embarque_costo_real order by oe.embarque_id";
        return sql;
    }

    public String reporteEmbarqueDoc(String agrupador) {
        sql = "SELECT ods.docto_referencia,to_char(ods.docto_fec_captura,'dd/mm/yyyy'),NVL(ods.docto_piezas,0),NVL(ods.docto_cajas,0),NVL(ods.docto_pallets,0),"
                + " NVL(ods.docto_colgados,0),NVL(ods.docto_contenedor,0),NVL(ods.docto_atados,0),"
                + " NVL(ods.docto_bulks,0),od.destino_nombre,oci.ciudad_nombre,oes.estado_nombre,NVL(od.destino_ruta,' '),NVL(ods.docto_importe,0), NVL(ods.docto_peso,0),NVL(ods.docto_volumen,0)"
                + " ,NVL(to_char(ods.docto_fec_pedido,'dd/mm/yyyy'),' '),NVL(ods.docto_pedido,' ') ,odi.division_nombre"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_division odi ON odi.division_id=ocbd.division_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocbd.division_id=ocd.division_id"
                + " INNER JOIN ontms_destino od ON ocd.destino_id=od.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_ciudad oci ON oci.ciudad_id=od.ciudad_id"
                + " INNER JOIN ontms_estado oes ON oci.estado_id=oes.estado_id"
                + " WHERE ods.docto_sal_agrupador='" + agrupador + "'"
                + " GROUP BY odi.division_nombre,ods.docto_pedido,ods.docto_fec_pedido,NVL(ods.docto_importe,0), NVL(ods.docto_peso,0),NVL(ods.docto_volumen,0),ods.docto_referencia,ods.docto_piezas,ods.docto_cajas,ods.docto_pallets,oci.ciudad_nombre,oes.estado_nombre,od.destino_ruta,"
                + " ods.docto_colgados,ods.docto_contenedor,ods.docto_atados,ods.docto_bulks,od.destino_nombre,ods.docto_fec_captura order by ods.docto_referencia";
        return sql;
    }

    public String reporteStatus(String cve, String fec, String fec1) {
        sql = "SELECT div_division_nombre, d_docto_referencia, d_docto_fec_factura, d_docto_pedido, d_docto_fec_pedido, conv_sucliente_id,"
                + " des_destino_nombre, ciu_ciudad_nombre, oe_embarque_id, oe_embarque_fec_captura,"
                + " d_docto_piezas, d_docto_cajas, d_docto_pallets, d_docto_colgados, d_docto_contenedor,"
                + " d_docto_atados, d_docto_bulks, d_docto_importe, d_docto_peso, d_docto_volumen,"
                + " olt_ltransporte_nombre, oc_chofer_nombre, outr_utransporte_desc,"
                + " oca_camion_placas, d_docto_rechazo, d_docto_fec_rechazo, d_docto_evidencia, NVL(TO_CHAR(TO_DATE(d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' '),"
                + " d_docto_observaciones, d_docto_folio_control, d_docto_fec_control, nvl(ol.folio_liquidacion,' '), nvl(to_char(ol.fecha,'dd/mm/yyyy'),' '),"
                + " appe.edo_desc, conv_dias_objetivos, conv_dias_evidencia, d_docto_programacion, d_docto_fec_programacion, d_docto_captura_evidencia,"
                + " (select ontms_pkg.ontms_ontime(oe_embarque_fec_captura,conv_dias_objetivos, NVL(TO_CHAR(TO_DATE(d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ')  ,conv_dias_evidencia,d_docto_captura_evidencia)"
                + " FROM dual where d_docto_captura_evidencia is not null )"
                + " FROM vw_pq_em oem"
                + " left JOIN ontms_vw_reporte_doc ods ON ods.d_docto_sal_agrupador=oem.oe_embarque_agrupador"
                + " left JOIN ontms_liquidaciong ol ON ol.embarque_agrupador=oem.oe_embarque_agrupador"
                + " INNER JOIN app_estado appe on appe.edo_valor=ods.d_docto_estado_id"
                + " WHERE to_date(d_docto_fec_captura) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY')   "
                + " AND appe.edo_tabla='ONTMS_DOCTO_SAL' "
                + " AND d_cbdiv_id IN (" + cve + ")"
                + " ORDER BY div_division_nombre, d_docto_referencia";
        return sql;

    }

    public String reporteStatusHilti(String cve, String fec, String fec1, String cbdivId) {
        sql = " SELECT  "
                + " ob.bodega_nombre,  "
                + " ods.docto_referencia,  "
                + " ods.docto_fec_captura,  "
                + " NVL(ods.docto_pedido,' '),  "
                + " ods.docto_fec_pedido,  "
                + " NVL(ods.docto_programacion,' '),  "
                + " NVL(ods.docto_fec_programacion,''),  "
                + " ddh.destino_ship_to,  "
                + " ddh.destino_nombre,  "
                + " ddh.destino_ciudad,  "
                + " ddh.destino_estado,  "
                + " NVL(OE.EMBARQUE_ID,' '),  "
                + " NVL(oe.embarque_fec_captura,''),  "
                + " ods.docto_piezas,  "
                + " ods.docto_cajas,  "
                + " ods.docto_peso,  "
                + " ods.DOCTO_IMP_TOTAL,  "
                + " ae.edo_desc  "
                + " FROM ontms_docto_sal ods  "
                + " LEFT JOIN ontms_embarque OE  "
                + " ON ods.docto_sal_agrupador=oe.embarque_agrupador  "
                + " INNER JOIN dilog_destinos_hilti ddh  "
                + " ON ods.destino_id=ddh.DESTINO_SHIP_TO  "
                + " and ddh.division_id = ods.cbdiv_id "
                + " INNER JOIN dilog_orig_dest_paq dodp  "
                + " ON ddh.destino_cp=dodp.iata_cp_destino  "
                + " INNER JOIN ontms_tipo_destino OTD  "
                + " ON dodp.tipo_destino_id =otd.tipo_destino_id  "
                + " INNER JOIN ontms_bodega OB  "
                + " ON ods.bodega_id=ob.bodega_id  "
                + " INNER JOIN app_estado AE  "
                + " ON ods.docto_estado_id=ae.edo_valor  "
                + " AND ae.edo_tabla='ONTMS_DOCTO_SAL'  "
                + " WHERE ODS.cbdiv_id      =    " + cbdivId;
        return sql;

    }

    public String reporteManiobras(String cve, String fec, String fec1) {
        sql = "Select om.maniobra_folio, to_char(om.maniobra_fecha_captura,'DD/MM/YY HH:MI:SS'), op.proveedor_razon_social, outr.utransporte_desc, "
                + " NVL(om.chofer_nombre,' '),otpm.tipo_pago_maniobra_descripcion, om.maniobra_pagado,NVL(om.maniobra_referencia_pago,' '),"
                + " NVL(om.maniobra_observaciones,' '),om.maniobra_cortina,NVL(om.maniobra_tipo_carga,' '),om.maniobra_costo_neto,om.maniobra_comision,bp.user_name,bud.cbdiv_id,ocbd.cuenta_id,ap.edo_desc"
                + " from ontms_maniobras om "
                + " inner join ontms_proveedores op on om.proveedor_id=op.proveedor_id"
                + " inner join ontms_unidad_transporte outr on om.utransporte_id=outr.utransporte_id"
                + " inner join ontms_tipo_pago_maniobra otpm on om.tipo_pago_maniobra_id=otpm.tipo_pago_maniobra_id"
                + " inner join broker_passwd bp on om.usuario_id=bp.user_nid"
                + " inner join broker_usuario_div bud on bp.user_nid=bud.user_nid"
                + " inner join ontms_cta_bod_div ocbd on ocbd.cbdiv_id=bud.cbdiv_id"
                + " inner join app_estado ap on ap.edo_valor=om.maniobra_estado_id and ap.edo_tabla='ONTMS_MANIOBRAS'"
                + " WHERE to_date(om.maniobra_fecha_captura) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + " AND ocbd.cuenta_id IN (" + cve + ")"
                + " group by om.maniobra_folio,"
                + " om.maniobra_fecha_captura,"
                + " op.proveedor_razon_social,"
                + " outr.utransporte_desc,"
                + " om.chofer_nombre,"
                + " otpm.tipo_pago_maniobra_descripcion,"
                + " om.maniobra_pagado,"
                + " om.maniobra_referencia_pago,"
                + " om.maniobra_observaciones,"
                + " om.maniobra_cortina,"
                + " om.maniobra_tipo_carga,"
                + " om.maniobra_costo_neto,"
                + " om.maniobra_comision,"
                + " bp.user_name,"
                + " bud.cbdiv_id,"
                + " ocbd.cuenta_id,"
                + " ap.edo_desc"
                + " ORDER BY om.maniobra_folio";
        return sql;

    }

    public String reporteManiobrasDetalle(String cve, String fec, String fec1, String tipo) {
        sql = "Select om.maniobra_folio, to_char(om.maniobra_fecha_captura,'DD/MM/YY HH:MI:SS'), op.proveedor_razon_social, outr.utransporte_desc, "
                + " NVL(om.chofer_nombre,' '),otpm.tipo_pago_maniobra_descripcion, om.maniobra_pagado,NVL(om.maniobra_referencia_pago,' '),"
                + " NVL(om.maniobra_observaciones,' '),om.maniobra_cortina,NVL(om.maniobra_tipo_carga,' '),om.maniobra_costo_neto,om.maniobra_comision,bp.user_name,bud.cbdiv_id,ocbd.cuenta_id,ap.edo_desc"
                + " from ontms_maniobras om "
                + " inner join ontms_proveedores op on om.proveedor_id=op.proveedor_id"
                + " inner join ontms_unidad_transporte outr on om.utransporte_id=outr.utransporte_id"
                + " inner join ontms_tipo_pago_maniobra otpm on om.tipo_pago_maniobra_id=otpm.tipo_pago_maniobra_id"
                + " inner join broker_passwd bp on om.usuario_id=bp.user_nid"
                + " inner join broker_usuario_div bud on bp.user_nid=bud.user_nid"
                + " inner join ontms_cta_bod_div ocbd on ocbd.cbdiv_id=bud.cbdiv_id"
                + " inner join app_estado ap on ap.edo_valor=om.maniobra_estado_id and ap.edo_tabla='ONTMS_MANIOBRAS'"
                + " WHERE to_date(om.maniobra_fecha_captura) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + " AND ocbd.cuenta_id IN (" + cve + ") and otpm.tipo_pago_maniobra_id='" + tipo + "'"
                + " group by om.maniobra_folio,"
                + " om.maniobra_fecha_captura,"
                + " op.proveedor_razon_social,"
                + " outr.utransporte_desc,"
                + " om.chofer_nombre,"
                + " otpm.tipo_pago_maniobra_descripcion,"
                + " om.maniobra_pagado,"
                + " om.maniobra_referencia_pago,"
                + " om.maniobra_observaciones,"
                + " om.maniobra_cortina,"
                + " om.maniobra_tipo_carga,"
                + " om.maniobra_costo_neto,"
                + " om.maniobra_comision,"
                + " bp.user_name,"
                + " bud.cbdiv_id,"
                + " ocbd.cuenta_id,"
                + " ap.edo_desc"
                + " ORDER BY om.maniobra_folio";
        return sql;

    }

    public String reporteManiobrasA(String cve, String fec, String fec1, String proveedor) {
        sql = "SELECT om.maniobra_folio,   om.maniobra_fecha_captura,   om.chofer_nombre,   otpm.tipo_pago_maniobra_descripcion, "
                + "  NVL(to_char(om.maniobra_fecha_inicio,'DD/MM/YYYY HH24:MM:SS'),' '), NVL(to_char(om.maniobra_fecha_fin,'DD/MM/YYYY HH24:MM:SS'),' '),   NVL(om.maniobra_cortina,0),   NVL(om.maniobra_pagado,' '), "
                + "  NVL(om.maniobra_referencia_pago,0),   NVL(op.proveedor_razon_social,' '),   NVL(outra.utransporte_desc,' '), "
                + "  NVL(omd.maniobra_docto_proveedor_maq,' '),   NVL(omd.maniobra_docto_remision,' '),   NVL(omd.maniobra_docto_pedimento,' '), "
                + "  NVL(omd.maniobra_docto_rr,' '),   NVL(omd.maniobra_docto_piezas,0),   NVL(omd.maniobra_docto_cajas,0), "
                + "  NVL(omd.maniobra_docto_pallets,0),   NVL(omd.maniobra_docto_lineatransporte,0),   NVL(omd.maniobra_docto_placas_tractor,0), "
                + "  NVL(omd.maniobra_docto_caja,0),  NVL(omd.maniobra_docto_placascaja,0),   NVL(om.maniobra_tipo_carga,' '), "
                + "  NVL(om.maniobra_costo_neto,0),   ouc.cuenta_id FROM ontms_maniobras om LEFT JOIN ontms_maniobra_docto omd "
                + " ON om.maniobra_id=omd.maniobra_id  INNER JOIN ontms_proveedores op ON op.proveedor_id=om.proveedor_id "
                + " INNER JOIN ontms_unidad_transporte outra ON outra.utransporte_id=om.utransporte_id "
                + " INNER JOIN ontms_tipo_pago_maniobra otpm  ON otpm.tipo_pago_maniobra_id=om.tipo_pago_maniobra_id "
                + " INNER JOIN ontms_vw_usuario_cuenta ouc ON ouc.user_nid=om.usuario_id "
                + " WHERE to_date(om.maniobra_fecha_captura) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') AND ouc.cuenta_id = (" + cve + ") "
                + " AND om.proveedor_id IN (" + proveedor + ") "
                + " ORDER BY om.maniobra_folio";
        return sql;

    }

    public String reporteResumenManiobras(String cve, String fec, String fec1) {
        sql = "select (case tipo_pago_maniobra_id when 1 then 'Efectivo' when 2 then 'Deposito' when 3 then 'Credito' ELSE 'N/A' end),"
                + "   sum(maniobra_costo_neto) , sum(maniobra_comision),"
                + "   tipo_pago_maniobra_id"
                + "  from ontms_maniobras OM "
                + "  where trunc(MANIOBRA_FECHA_CAPTURA) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + "  group by  OM.tipo_pago_maniobra_id";
        return sql;

    }

    public String reportecomisionesManiobras(String cve, String fec, String fec1) {
        sql = "select om.maniobra_folio, om.MANIOBRA_FECHA_CAPTURA, utr.UTRANSPORTE_DESC, ome.empleado_id, oe.empleado_nombre,ome.EMPLEADO_COMISION"
                + " from ontms_maniobras om"
                + " inner join ontms_maniobra_empleado ome on om.maniobra_folio=ome.maniobra_folio"
                + " inner join ontms_empleados oe on ome.empleado_id = oe.empleado_id"
                + " inner join ontms_unidad_transporte utr on utr.utransporte_id = om.utransporte_id inner join broker_passwd bp on bp.user_nid=om.usuario_id"
                + " inner join broker_usuario_div bud on bud.user_nid=bp.user_nid"
                + " inner join ONTMS_CTA_BOD_DIV ctbdv on ctbdv.cbdiv_id  = bud.cbdiv_id"
                + " where to_date(om.maniobra_fecha_captura) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY')  and ctbdv.cuenta_id in (" + cve + ") AND ome.MANIOBRA_EMPLEADO_ESTADO_ID=1"
                + " group by om.maniobra_folio, om.MANIOBRA_FECHA_CAPTURA, utr.UTRANSPORTE_DESC, ome.empleado_id, oe.empleado_nombre,ome.EMPLEADO_COMISION"
                + " order by om.maniobra_folio, ome.empleado_id";
        return sql;

    }

    public String ReporteOrdenesdeCarga(String fec, String fec1) {
        sql = "select ooc.ordencarga_folio,ooc.ordencarga_fecha,olt.ltransporte_nombre,oca.camion_placas,outr.utransporte_desc,och.chofer_nombre,"
                + " NVL(ooc.ordencarga_nombreayudante,' '),ocu.cuenta_nombre,NVL(ooc.observaciones,' '), to_char(ooc.ordencarga_fechacitado,'DD/MM/YYYY HH:MI:SS'),bp.user_name from ontms_ordencarga ooc "
                + " inner join ontms_camion oca on ooc.camion_id=oca.camion_id"
                + " INNER JOIN ONTMS_UNIDAD_TRANSPORTE outr ON outr.utransporte_id=oca.utransporte_id"
                + " inner join ontms_chofer och on ooc.chofer_id=och.chofer_id"
                + " inner join ontms_linea_transporte olt on oca.ltransporte_id=olt.ltransporte_id"
                + " inner join ontms_cuenta ocu on ooc.cuenta_id =ocu.cuenta_id"
                + " inner join broker_passwd bp on ooc.usuario_id=bp.user_nid"
                + " where to_date(ooc.ordencarga_fecha) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + " order by ordencarga_folio";
        return sql;

    }

    public String OrdenesdeIngreso(String fec, String fec1) {
        sql = "select oi.ordeningreso_folio, "
                + "to_char(oi.ordeningreso_fecha,'DD/MM/YYYY HH:MI:SS'), "
                + "oi.transportista, "
                + "outr.utransporte_desc, "
                + "oi.placas,oi.chofer, "
                + "oi.ordeningreso_nombreayudante, "
                + "oc.cuenta_id, "
                + "oc.cuenta_nombre, "
                + "NVL(to_char(oi.ORDENINGRESO_FECHACITADO,'DD/MM/YYYY'),' '), "
                + "NVL(oi.ordeningreso_observaciones,' '), "
                + "ae.edo_desc, "
                + "bp.user_name, oi.ORDENINGRESO_ID "
                + "from ontms_ordeningresos oi "
                + "inner join ONTMS_UNIDAD_TRANSPORTE outr on outr.utransporte_id=oi.camion_id "
                + "inner join ontms_cuenta oc on oc.cuenta_id=oi.cuenta_id "
                + "inner join broker_passwd bp on bp.user_nid=oi.usuario_id "
                + "inner join app_estado ae on ae.edo_valor=oi.ordeningreso_status AND ae.edo_tabla='ONTMS_ORDENINGRESOS' "
                + "where oi.ordeningreso_status = 0 and to_date(oi.ordeningreso_fecha) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + "order by oi.ordeningreso_folio";
        return sql;

    }

    public String ReporteOrdenesdeIngreso(String cta, String fec, String fec1) {
        sql = "select oi.ordeningreso_folio, "
                + "to_char(oi.ordeningreso_fecha,'DD/MM/YYYY HH:MI:SS'), "
                + "oi.transportista, "
                + "outr.utransporte_desc, "
                + "oi.placas,oi.chofer, "
                + "oi.ordeningreso_nombreayudante, "
                + "oc.cuenta_id, "
                + "oc.cuenta_nombre, "
                + "NVL(to_char(oi.ORDENINGRESO_FECHACITADO,'DD/MM/YYYY'),' '), "
                + "NVL(oi.ordeningreso_observaciones,' '), "
                + "ae.edo_desc, "
                + "bp.user_name, oi.ORDENINGRESO_ID "
                + "from ontms_ordeningresos oi "
                + "inner join ONTMS_UNIDAD_TRANSPORTE outr on outr.utransporte_id=oi.camion_id "
                + "inner join ontms_cuenta oc on oc.cuenta_id=oi.cuenta_id "
                + "inner join broker_passwd bp on bp.user_nid=oi.usuario_id "
                + "inner join app_estado ae on ae.edo_valor=oi.ordeningreso_status AND ae.edo_tabla='ONTMS_ORDENINGRESOS' "
                + "where oi.cuenta_id in (" + cta + ") and to_date(oi.ordeningreso_fecha) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + "order by oi.ordeningreso_folio";
        return sql;

    }

    public String ReporteOrdenesdeIngresoCR(String cta, String fec, String fec1) {

        sql = "SELECT ocr.CR_DOCTO_SAL_REFERENCIA,ocr.CR_DOCTO_SAL_FECHA,  "
                + "ape.edo_desc, ocr.CR_DOCTO_SAL_FECHA_CANCELACION, "
                + "ocr.CR_CLIENTE_NOMBRE,  ocr.CR_CALLE,   ocr.CR_COLONIA,   ocr.CR_CLIENTE_CIUDAD,   "
                + "ocr.CR_CLIENTE_CP,   ocr.CR_CLIENTE_DELEGACION,   ocr.CR_CLIENTE_ESTADO,"
                + " ocr.CR_DOCTO_SAL_OBS,  ocr.CR_DOCTO_SAL_REF_CLIENTE,   ocr.CR_DESTINO_NOMBRE,   ocr.CR_DESTINO_CALLE,   "
                + "ocr.CR_DESTINO_COLONIA,   ocr.CR_DESTINO_CIUDAD,"
                + " ocr.CR_DESTINO_CP,   ocr.CR_DESTINO_DELEGACION,   ocr.CR_DESTINO_ESTADO FROM ONTMS_CLIENTE_RECOGE ocr "
                + "INNER JOIN APP_ESTADO ape ON ape.EDO_VALOR                =ocr.estado_id "
                + " AND ape.edo_tabla                ='ONTMS_CLIENTE_RECOGE'" + ""
                + " and to_date(ocr.CR_DOCTO_SAL_FECHA) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') ";

        return sql;

    }

    public String OrdenesdeSalida(String fec, String fec1) {
        sql = "SELECT oos.ordensalida_folio,"
                + "  oos.ordensalida_fecha,"
                + "  oos.transportista,"
                + "  outr.UTRANSPORTE_DESC,"
                + "  oos.placas,"
                + "  NVL(oos.chofer,' '),"
                + "  NVL(oos.ordensalida_nombreayudante,' '),"
                + "  oos.ordensalida_tipo,"
                + "  oct.cuenta_nombre,"
                + "  oos.ordensalida_fecha_salida,"
                + "  NVL(oos.ordensalida_observaciones,' '),"
                + "  bp.user_name,ae.edo_desc,oos.ordensalida_id"
                + "  FROM ontms_ordensalida oos"
                + "  INNER JOIN ONTMS_UNIDAD_TRANSPORTE outr ON outr.utransporte_id=oos.camion_id"
                + "  INNER JOIN ontms_cuenta oct ON oos.cuenta_id=oct.cuenta_id"
                + "  INNER JOIN broker_passwd bp ON oos.usuario_id = bp.user_nid "
                + "  INNER JOIN app_estado ae on ae.edo_valor=oos.ordensalida_status and ae.edo_tabla='ONTMS_ORDENSALIDA'"
                + "  WHERE oos.ORDENSALIDA_STATUS = 0 and to_date(oos.ordensalida_fecha) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + "  order by oos.ordensalida_folio";
        return sql;
    }

    public String repOrdenesdeSalida(String cta, String fec, String fec1) {
        sql = "SELECT oos.ordensalida_folio,"
                + "  oos.ordensalida_fecha,"
                + "  oos.transportista,"
                + "  outr.UTRANSPORTE_DESC,"
                + "  oos.placas,"
                + "  NVL(oos.chofer,' '),"
                + "  NVL(oos.ordensalida_nombreayudante,' '),"
                + "  oos.ordensalida_tipo,"
                + "  oct.cuenta_nombre,"
                + "  oos.ordensalida_fecha_salida,"
                + "  NVL(oos.ordensalida_observaciones,' '),"
                + "  bp.user_name,ae.edo_desc,oos.ordensalida_id"
                + "  FROM ontms_ordensalida oos"
                + "  INNER JOIN ONTMS_UNIDAD_TRANSPORTE outr ON outr.utransporte_id=oos.camion_id"
                + "  INNER JOIN ontms_cuenta oct ON oos.cuenta_id=oct.cuenta_id"
                + "  INNER JOIN broker_passwd bp ON oos.usuario_id = bp.user_nid "
                + "  INNER JOIN app_estado ae on ae.edo_valor=oos.ordensalida_status and ae.edo_tabla='ONTMS_ORDENSALIDA'"
                + "  WHERE oos.cuenta_id in (" + cta + ") and to_date(oos.ordensalida_fecha) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + "  order by oos.ordensalida_folio";
        return sql;
    }

    public String reporteSeguimiento(String cve, String fec, String fec1) {
        sql = "SELECT div_division_nombre, d_docto_referencia, d_docto_fec_captura, d_docto_fec_factura, d_docto_pedido, d_docto_fec_pedido, conv_sucliente_id,"
                + " des_destino_nombre, ciu_ciudad_nombre, oe_embarque_id, oe_embarque_fec_captura,"
                + " d_docto_piezas, d_docto_cajas, d_docto_pallets, d_docto_colgados, d_docto_contenedor,"
                + " d_docto_atados, d_docto_bulks, d_docto_importe, d_docto_peso, d_docto_volumen,"
                + " olt_ltransporte_nombre, oc_chofer_nombre, outr_utransporte_desc,"
                + " oca_camion_placas, d_docto_rechazo, d_docto_fec_rechazo, d_docto_evidencia, NVL(TO_CHAR(TO_DATE(d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' '),"
                + " d_docto_observaciones, d_docto_folio_control, d_docto_fec_control, nvl(ol.folio_liquidacion,' '), nvl(to_char(ol.fecha,'dd/mm/yyyy'),' '),"
                + " appe.edo_desc, conv_dias_objetivos, conv_dias_evidencia, d_docto_programacion, d_docto_fec_programacion, d_docto_captura_evidencia,"
                + " (select ontms_pkg.ontms_ontime(oe_embarque_fec_captura,conv_dias_objetivos, NVL(TO_CHAR(TO_DATE(d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' '),conv_dias_evidencia,d_docto_captura_evidencia)"
                + " FROM dual where d_docto_captura_evidencia is not null )"
                + " FROM vw_pq_em oem"
                + " left JOIN ontms_vw_reporte_doc ods ON ods.d_docto_sal_agrupador=oem.oe_embarque_agrupador"
                + " left JOIN ontms_liquidaciong ol ON ol.embarque_agrupador=oem.oe_embarque_agrupador"
                + " INNER JOIN app_estado appe on appe.edo_valor=ods.d_docto_estado_id"
                + " WHERE to_date(d_docto_fec_captura) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + " AND appe.edo_tabla='ONTMS_DOCTO_SAL' "
                + " AND d_cbdiv_id IN (" + cve + ")"
                + " ORDER BY div_division_nombre, d_docto_referencia ";
        return sql;

    }

    public String reporteStatusa(String se, String le, String fec, String fec1, String g, String cve) {
        sql = "SELECT /*+ parallel(vw_reporte_ontime 16) */ vi.*" + se + " FROM VW_REPORTE_HIST_DOCTO vi"
                + " " + le + " WHERE edo_edo_tabla='ONTMS_DOCTO_SAL' AND vi.ods_cbdiv_id in (" + cve + ") AND"
                + " to_date(ods_docto_fec_captura) BETWEEN TO_DATE('" + fec + "','DD/MM/YYYY') AND TO_DATE('" + fec1 + "','DD/MM/YYYY') "
                + " AND vi.ods_docto_estado_id=11 "
                + " ORDER BY " + g + "";
        return sql;
    }

    public String FrecuenciaCuenta(String div) {
        sql = "SELECT DISTINCT(ocbd.cuenta_id), cta.cuenta_nombre "
                + " FROM ontms_embarque oe "
                + " INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta cta ON cta.cuenta_id=ocbd.cuenta_id "
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE ods.cbdiv_id in (" + div + ")  GROUP BY ocbd.cuenta_id, cta.cuenta_nombre ";
        return sql;
    }

    public String FrecuenciaDestinos(String div) {
        sql = "SELECT ods.destino_id, NVL(od.destino_nombre, ' '), NVL(od.destino_ruta, ' '), ocd.sucliente_id  "
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE ods.cbdiv_id in (" + div + ")"
                + " GROUP BY ods.destino_id, od.destino_nombre, od.destino_ruta, ocd.sucliente_id"
                + " order by od.destino_ruta";
        return sql;
    }

    public String FrecuenciaContador(String fec1, String fec2, String div, String id_destino1) {
        sql = "SELECT to_char(oe.embarque_fec_captura,'dd/mm/yyyy'), ods.destino_id, "
                + " NVL(od.destino_nombre, ' '), NVL(od.destino_ruta, ' ')"
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY') "
                + " AND ods.cbdiv_id in (" + div + ") AND ods.destino_id='" + id_destino1 + "'"
                + " GROUP BY to_char(oe.embarque_fec_captura,'dd/mm/yyyy'), ods.destino_id, "
                + " od.destino_nombre, od.destino_ruta order by to_char(oe.embarque_fec_captura,'dd/mm/yyyy')";
        return sql;
    }

    public String FecuenciaContadorEmbarque(String fecE, String div, String id_destino1) {
        sql = "SELECT COUNT( DISTINCT oe.embarque_agrupador) "
                + " FROM ontms_embarque oe "
                + " INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE trunc(oe.embarque_fec_captura) = '" + fecE + "' "
                + " AND ods.cbdiv_id in (" + div + ") "
                + " AND ods.destino_id=" + id_destino1 + " ";
        return sql;
    }

    public String FrecuenciaGeneral(String fec1, String fec2, String cveDivision) {
        sql = " SELECT COUNT( DISTINCT oe.embarque_agrupador), to_char(oe.embarque_fec_captura,'dd/mm/yyyy') "
                + " FROM ontms_embarque oe "
                + " INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY') "
                + " AND ods.cbdiv_id in (" + cveDivision + ") "
                + " GROUP BY to_char(oe.embarque_fec_captura,'dd/mm/yyyy') ";
        return sql;
    }

    public String FrecuenciaGeneralContar(String fecE, String cveDivision) {
        sql = " SELECT COUNT( DISTINCT oe.embarque_agrupador), to_char(oe.embarque_fec_captura,'dd/mm/yyyy') "
                + " FROM ontms_embarque oe "
                + " INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE trunc(oe.embarque_fec_captura) = '" + fecE + "'"
                + " AND ods.cbdiv_id in (" + cveDivision + ") "
                + " GROUP BY to_char(oe.embarque_fec_captura,'dd/mm/yyyy') ";
        return sql;
    }

    public String FrecuenciaDestinosEmbarque(String cveDivision) {
        sql = "SELECT ods.destino_id, NVL(od.destino_nombre, ' '), NVL(od.destino_ruta, ' '), ocd.sucliente_id  "
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms-bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE ods.cbdiv_id in (" + cveDivision + ")"
                + " GROUP BY ods.destino_id, od.destino_nombre, od.destino_ruta, ocd.sucliente_id";
        return sql;
    }

    public String FrecuenciaContadorEmbarqueDestino(String fec1, String fec2, String cveDivision) {
        sql = "SELECT DISTINCT(to_char(oe.embarque_fec_captura,'dd/mm/yyyy')) "
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id "
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE to_date(to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),'dd/mm/yyyy') "
                + " BETWEEN to_date('" + fec1 + "','dd/mm/yyyy') AND to_date('" + fec2 + "','dd/mm/yyyy') "
                + " AND  ods.cbdiv_id in (" + cveDivision + ")"
                + " GROUP BY to_char(oe.embarque_fec_captura, 'dd/mm/yyyy') order by to_char(oe.embarque_fec_captura, 'dd/mm/yyyy')";
        return sql;
    }

    public String FrecContEmbDest2(String fecE, String cveDivision) {
        sql = "SELECT COUNT( DISTINCT ods.destino_id) as numero"
                + " FROM ontms_embarque oe "
                + "INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE trunc(oe.embarque_fec_captura)= ('" + fecE + "') AND  ods.cbdiv_id in (" + cveDivision + ")"
                + " order by oe.embarque_fec_captura ";
        return sql;
    }

    public String statusOb(String id) {
        sql = "SELECT observacion,to_char(fecha,'dd/mm/yyyy hh:mi')"
                + " FROM ontms_docto_observaciones WHERE docto_sal_id='" + id + "' "
                + " ORDER BY fecha";
        return sql;
    }

    public String cuota(String c, String l, String t) {
        sql = "SELECT od.id_destino,ob.bodega_nombre,oc.ciudad_nombre,ot.cuota_transp_costo_flete,ou.utransporte_desc,ote.tipo_embarque_desc FROM ontms_origen_destino od"
                + " INNER JOIN ontms_cuota_transporte ot ON ot.origen_dest_id=od.origen_dest_id"
                + " INNER JOIN ontms_unidad_transporte ou ON ou.utransporte_id=ot.utransporte_id"
                + " INNER JOIN ontms_tipo_embarque ote ON ote.tipo_embarque_id=ot.tipo_embarque_id"
                + " INNER JOIN ontms_bodega ob ON ob.zona_id=od.id_origen"
                + " INNER JOIN ontms_ciudad oc ON oc.ciudad_id=od.id_destino"
                + " WHERE ob.bodega_id in (" + c + ") " + l + " " + t + ""
                + " ORDER BY oc.ciudad_nombre";
        return sql;
    }

    public String liquidacion(String cve, String f1, String f2) {
        sql = "SELECT ddh.division_nombre,ol.folio_liquidacion,to_char(fecha,'dd/mm/yyyy'),embarque_id,to_char(oe.embarque_fec_captura,'dd/mm/yyyy'), COUNT (DISTINCT ods.docto_sal_id),olt.ltransporte_nombre,chofer_nombre,NVL(oe.embarque_costo_real,0),"
                + " sum(DISTINCT NVL(oei.embarque_indirectos_costo,0)),sum (DISTINCT NVL(costo,0))"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_liquidacion ol ON ol.embarque_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div oc ON oc.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN DILOG_DESTINOS_HILTI ddh  ON ddh.DESTINO_SHIP_TO= ods.DESTINO_ID AND ods.cbdiv_id      = ddh.division_id"
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id"
                + " INNER JOIN ontms_linea_transporte olt ON olt.ltransporte_id=oca.ltransporte_id"
                + " LEFT JOIN ontms_embarque_indirectos oei ON oei.embarque_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " LEFT JOIN ontms_cargos oca ON oca.embarque_agrupador=oe.embarque_agrupador"
                + " WHERE ods.cbdiv_id IN (" + cve + ") AND trunc(fecha) BETWEEN TO_DATE('" + f1 + "','DD/MM/YYYY') AND TO_DATE('" + f2 + "','DD/MM/YYYY') "
                + " GROUP BY ol.folio_liquidacion,fecha,od.division_nombre,embarque_id,oe.embarque_fec_captura,olt.ltransporte_nombre,chofer_nombre,NVL(oe.embarque_costo_real,0)";
        return sql;
    }

    public String resumen(String c, String a, String b) {
        sql = "SELECT " + a + ", oc.cuenta_nombre FROM ontms_cta_bod_div oct, ontms_cuenta oc"
                + " WHERE oct.cuenta_id=oc.cuenta_id AND " + b + " in (" + c + ") "
                + " GROUP BY " + a + ", oc.cuenta_nombre ORDER BY oc.cuenta_nombre";
        return sql;
    }

    public String resumenIndividual(String c, String f, String f1) {
        sql = "SELECT COUNT (docto_sal_id), NVL(sum(docto_piezas),0),"
                + " NVL(sum(docto_cajas),0),NVL(sum(docto_pallets),0),NVL(sum(docto_colgados),0),NVL(sum(docto_contenedor),0),"
                + " NVL(sum(docto_atados),0),NVL(sum(docto_bulks),0),NVL(sum(docto_importe),0),NVL(sum(docto_peso),0),NVL(sum(docto_volumen),0)"
                + " FROM ontms_docto_Sal ods"
                + " INNER JOIN ontms_cta_bod_div ocb ON ocb.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id=ocb.cuenta_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocb.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocb.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE oc.cuenta_id in (" + c + ") AND trunc(ods.docto_fec_captura)"
                + " BETWEEN TO_DATE('" + f + "','DD/MM/YYYY') AND TO_DATE('" + f1 + "','DD/MM/YYYY') ";
        return sql;
    }

    public String resumenEmbarque(String c, String f, String f1) {
        sql = "SELECT COUNT(DISTINCT embarque_agrupador),COUNT(DISTINCT docto_sal_id),NVL(sum(docto_piezas),0),"
                + " NVL(sum(docto_cajas),0),NVL(sum(docto_pallets),0),NVL(sum(docto_colgados),0),NVL(sum(docto_contenedor),0),"
                + " NVL(sum(docto_atados),0),NVL(sum(docto_bulks),0),NVL(sum(docto_importe),0),NVL(sum(docto_peso),0),NVL(sum(docto_volumen),0)"
                + " FROM ontms_docto_Sal ods"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocb ON ocb.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id=ocb.cuenta_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocb.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocb.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE oc.cuenta_id in (" + c + ") AND trunc(oe.embarque_fec_captura)"
                + " BETWEEN TO_DATE('" + f + "','DD/MM/YYYY') AND TO_DATE('" + f1 + "','DD/MM/YYYY') ";
        return sql;
    }
///verificar

    public String resumenTotal(String c, String f, String f1) {
        sql = "SELECT (SELECT count(DISTINCT oe.oe_embarque_id) from vw_pq_em oe"
                + " INNER JOIN ontms_docto_Sal ods ON ods.docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " WHERE ocbd.cuenta_id IN (" + c + ") AND to_date(oe.oe_embarque_fec_captura) BETWEEN '" + f + "' AND '" + f1 + "'),"
                + " (SELECT count(DISTINCT ods.docto_sal_id) from vw_pq_em oe"
                + " INNER JOIN ontms_docto_Sal ods ON ods.docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " WHERE ocbd.cuenta_id IN (" + c + ") AND to_date(oe.oe_embarque_fec_captura) BETWEEN '" + f + "' AND '" + f1 + "'),"
                + " (SELECT COUNT(docto_Sal_id) FROM ontms_docto_Sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " WHERE docto_estado_id IN (0,1)"
                + " AND ocbd.cuenta_id    IN (" + c + ")"
                + " AND TRUNC(docto_fec_captura) BETWEEN '" + f + "' AND '" + f1 + "'),"
                + " (SELECT COUNT(docto_Sal_id) FROM ontms_docto_Sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " WHERE docto_estado_id=10"
                + " AND ocbd.cuenta_id  IN (" + c + ")"
                + " AND TRUNC(docto_fec_rechazo) BETWEEN '" + f + "' AND '" + f1 + "'),"
                + " (SELECT COUNT(docto_Sal_id) FROM ontms_docto_Sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " WHERE docto_estado_id   =11 AND ocbd.cuenta_id   IN (" + c + ")"
                + " AND TRUNC(docto_fec_rechazo) BETWEEN '" + f + "' AND '" + f1 + "'),"
                + " (SELECT COUNT(docto_Sal_id) FROM ontms_docto_Sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " WHERE docto_estado_id IN (2,10,11)"
                + " AND ocbd.cuenta_id    IN (" + c + ")"
                + " AND TRUNC(docto_fec_captura) BETWEEN '" + f + "' AND '" + f1 + "'),"
                + " (SELECT COUNT(docto_Sal_id) FROM ontms_docto_Sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " WHERE docto_estado_id IN (8,7)"
                + " AND ocbd.cuenta_id    IN (" + c + ")"
                + " AND TRUNC(docto_captura_evidencia) BETWEEN '" + f + "' AND '" + f1 + "')FROM dual";
        return sql;
    }

    public String comenFinal(String a) {
        sql = "SELECT /*+ FIRST_ROWS */ a.* FROM  ontms_docto_observaciones a WHERE rowid = (SELECT max(rowid)"
                + " FROM ontms_docto_observaciones WHERE docto_sal_id='" + a + "')";
        return sql;
    }

    public String clienteR(String c, String f, String f1) {
        sql = "SELECT /*+ FIRST_ROWS */ to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),oe.embarque_id,COUNT(ods.docto_sal_id),NVL(sum(ods.docto_piezas),0),"
                + " NVL(sum(ods.docto_cajas),0),NVL(sum(ods.docto_pallets),0),NVL(sum(ods.docto_colgados),0),"
                + " NVL(sum(ods.docto_contenedor),0),NVL(sum(ods.docto_atados),0),NVL(sum(ods.docto_bulks),0),"
                + " NVL(sum(ods.docto_importe),0),NVL(sum(ods.docto_peso),0), NVL(sum(ods.docto_volumen),0),"
                + " oe.embarque_agrupador, oe.embarque_costo_real "
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador= ods.docto_sal_agrupador"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE oe.embarque_id like 'CR%' AND ods.cbdiv_id in (" + c + ") AND trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + f + "','DD/MM/YYYY') AND TO_DATE('" + f1 + "','DD/MM/YYYY') "
                + " GROUP BY oe.embarque_fec_captura, oe.embarque_id, oe.embarque_agrupador,oe.embarque_costo_real order by oe.embarque_id";
        return sql;
    }

    public String extrangeros(String c, String f, String f1) {
        sql = " SELECT /*+ FIRST_ROWS */ odi.division_nombre,ods.docto_referencia,to_char(ods.docto_fec_captura,'dd/mm/yyyy'),oe.embarque_id, to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),"
                + " ods.docto_piezas,ods.docto_cajas,ods.docto_pallets,ods.docto_colgados,ods.docto_contenedor,"
                + " ods.docto_atados,ods.docto_bulks,ods.docto_importe,ods.docto_peso,ods.docto_volumen,"
                + " od.destino_nombre,oc.ciudad_nombre, oes.estado_nombre, op.pais_nombre"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_division odi ON odi.division_id=ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocbd.division_id=ocd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_ciudad oc ON oc.ciudad_id=od.ciudad_id"
                + " INNER JOIN ontms_estado oes ON oes.estado_id=oc.estado_id"
                + " INNER JOIN ontms_pais op ON op.pais_id=oes.pais_id"
                + " WHERE op.pais_id<>1 AND ocbd.cbdiv_id in (" + c + ") AND trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + f + "','DD/MM/YYYY') AND TO_DATE('" + f1 + "','DD/MM/YYYY') "
                + "order by oe.embarque_id";
        return sql;
    }

    public String promedio(String div) {
        sql = " SELECT COUNT(clave) FROM( "
                + " SELECT /*+ FIRST_ROWS */ ods.destino_id as clave, NVL(od.destino_nombre, ' '), "
                + " NVL(od.destino_ruta, ' '), ocd.sucliente_id  "
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd_bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE ods.cbdiv_id in (" + div + ")"
                + " GROUP BY ods.destino_id, od.destino_nombre, od.destino_ruta, ocd.sucliente_id"
                + " order by od.destino_ruta)";
        return sql;
    }

    public String promedio1(String cveDivision) {
        sql = " SELECT COUNT(clave) FROM( "
                + " SELECT /*+ FIRST_ROWS */ ods.destino_id as clave, NVL(od.destino_nombre, ' '), "
                + " NVL(od.destino_ruta, ' '), ocd.sucliente_id  "
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_docto_sal ods ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE ods.cbdiv_id in (" + cveDivision + ")"
                + " GROUP BY ods.destino_id, od.destino_nombre, od.destino_ruta, ocd.sucliente_id"
                + " order by od.destino_ruta)";
        return sql;
    }

    public String emProducto(String c, String f1, String f2) {
        sql = " SELECT /*+ FIRST_ROWS */ odi.division_nombre,oe.embarque_id,to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),"
                + " olt.ltransporte_nombre,outr.utransporte_desc,"
                + " och.chofer_nombre,ods.docto_referencia,NVL(ods.docto_piezas,0),NVL(ods.docto_cajas,0), NVL(ods.docto_pallets,0),NVL(ods.docto_colgados,0),"
                + " NVL(ods.docto_contenedor,0),NVL(ods.docto_atados,0), NVL(ods.docto_bulks,0),NVL(ods.docto_importe,0),NVL(ods.docto_peso,0),NVL(ods.docto_volumen,0),"
                + " NVL(odk.docto_sku_id,0),NVL(op.producto_desc,' ') FROM ontms_embarque oe "
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_division odi ON odi.division_id=ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " LEFT JOIN ontms_docto_sku odk ON odk.docto_sal_id=ods.docto_sal_id"
                + " LEFT JOIN ontms_producto op ON op.producto_sku=odk.producto_sku "
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id "
                + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id "
                + " INNER JOIN ontms_linea_transporte olt ON olt.ltransporte_id=oca.ltransporte_id"
                + " INNER JOIN ontms_unidad_transporte outr ON outr.utransporte_id=oca.utransporte_id "
                + " WHERE ods.cbdiv_id in(" + c + ") AND trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + f1 + "','DD/MM/YYYY') AND TO_DATE('" + f2 + "','DD/MM/YYYY') "
                + " GROUP BY odi.division_nombre,oe.embarque_id,to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),"
                + " olt.ltransporte_nombre,outr.utransporte_desc, "
                + " och.chofer_nombre,ods.docto_referencia,ods.docto_piezas,ods.docto_cajas, ods.docto_pallets,ods.docto_colgados,"
                + " ods.docto_contenedor,ods.docto_atados, ods.docto_bulks,ods.docto_importe,ods.docto_peso,ods.docto_volumen,"
                + " odk.docto_sku_id,op.producto_desc"
                + " order by oe.embarque_id";
        return sql;
    }

    public String cargosTicket(String documento, String cveCuenta) {
        sql = "SELECT em.embarque_id, em.embarque_fec_captura, em.embarque_agrupador, oc.chofer_nombre, oca.camion_placas, olt.ltransporte_nombre, "
                + " ods.docto_sal_agrupador, ods.cbdiv_id, ocr.costo, ocr.rechazo_id, ocrec.rechazo_desc, ocr.cargo_fec, ocr.cargo_folio "
                + " FROM ontms_embarque em, ontms_chofer oc, ontms_camion oca,ontms_linea_transporte olt, ontms_docto_sal ods,"
                + " ontms_cargos ocr, ontms_causa_rechazo ocrec WHERE em.chofer_id=oc.chofer_id "
                + " AND em.camion_id=oca.camion_id "
                + " AND olt.ltransporte_id=oca.ltransporte_id "
                + " AND em.embarque_agrupador=ods.docto_sal_agrupador "
                + " AND ocr.embarque_agrupador=em.embarque_agrupador "
                + " and ocr.rechazo_id=ocrec.rechazo_id "
                + " and ods.cbdiv_id='" + cveCuenta + "' "
                + " AND em.embarque_id='" + documento + "' "
                + " GROUP BY em.embarque_id, em.embarque_fec_captura, em.embarque_agrupador, oc.chofer_nombre, oca.camion_placas, olt.ltransporte_nombre, "
                + " ods.docto_sal_agrupador, ods.cbdiv_id, ocr.costo, ocr.rechazo_id, ocrec.rechazo_desc, ocr.cargo_fec, ocr.cargo_folio ";
        return sql;
    }

    public String doctoTicket(String agrupador) {
        sql = "SELECT ods.docto_referencia,NVL(ods.docto_piezas,0),od.destino_nombre,oc.ciudad_nombre, "
                + " oes.estado_nombre,ods.docto_sal_id, oe.embarque_agrupador,oe.embarque_id,ocd.division_id,ods.cbdiv_id"
                + " FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_embarque oe ON ods.docto_sal_agrupador=oe.embarque_agrupador "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id "
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id "
                + " INNER JOIN ontms_ciudad oc ON oc.ciudad_id=od.ciudad_id "
                + " INNER JOIN ontms_estado oes ON oes.estado_id=oc.estado_id"
                + " LEFT JOIN ontms_docto_sku odsk ON odsk.docto_sal_id=ods.docto_sal_id"
                + " WHERE (ods.docto_estado_id=2 OR ods.docto_estado_id=10) "
                + " AND oe.embarque_agrupador IN ('170220110815104157')  "
                + " GROUP BY ods.docto_referencia, NVL(ods.docto_piezas,0), od.destino_nombre, oc.ciudad_nombre, oes.estado_nombre, ods.docto_sal_id,"
                + " oe.embarque_agrupador, oe.embarque_id, ocd.division_id, ods.cbdiv_id "
                + " ORDER BY ods.docto_referencia";
        return sql;
    }

    public String prodTicket(String agrupador) {
        sql = "SELECT odsc.cargo_docto_id,  odsc.cargo_fec_inicio,  odsc.costo,  odsc.docto_sal_agrupador,  odsc.rechazo_id,  odsc.cargo_id,  odsc.producto_sku,"
                + " op.producto_desc"
                + " FROM ontms_docto_sal_cargos odsc, ontms_producto op"
                + " WHERE odsc.docto_sal_agrupador='" + agrupador + "'"
                + " AND odsc.producto_sku=op.producto_sku"
                + " order by op.producto_desc";
        return sql;
    }

    public String viajesCon(String[] da) {
        sql = "SELECT vce.oe_embarque_fec_captura,vce.oe_embarque_id,od.destino_nombre,vce.oe_embarque_costo_real,"
                + " SUM(ods.docto_piezas),SUM(ods.docto_cajas),SUM(ods.docto_pallets),SUM(ods.docto_colgados),"
                + " SUM(ods.docto_contenedor),SUM(ods.docto_atados),SUM(ods.docto_bulks),SUM(ods.docto_importe),SUM(ods.docto_peso),"
                + " SUM(ods.docto_volumen),vce.oc_chofer_nombre,vce.olt_ltransporte_nombre,vce.outr_utransporte_desc,ods.docto_sal_agrupador"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_hist_docto_sal oh ON ods.docto_sal_id=ods.docto_sal_id"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN vw_consulta_em vce ON vce.oe_embarque_agrupador=ods.docto_sal_agrupador"
                + " WHERE oh.docto_estado_id=24 AND ods.cbdiv_id in (" + da[0] + ")"
                + " AND to_date(vce.oe_embarque_fec_captura) BETWEEN '" + da[1] + "' AND '" + da[2] + "'"
                + " GROUP BY vce.outr_utransporte_desc,od.destino_nombre,vce.oe_embarque_fec_captura, vce.oe_embarque_id, vce.oe_embarque_costo_real,"
                + " vce.oc_chofer_nombre, vce.olt_ltransporte_nombre, ods.docto_sal_agrupador";
        return sql;
    }

    public String registroEmbarque(String fec1, String fec2, String cveCuenta) {

        sql = "SELECT vis.*,  octa.cuenta_nombre"
                + " FROM ontms_vw_reporte_viajes vis,  ontms_cta_bod_div ocbdiv, ontms_cuenta octa"
                + " where ocbdiv.cuenta_id=octa.cuenta_id and vis.ods_cbdiv_id=ocbdiv.cbdiv_id"
                + " and  octa.cuenta_id=ocbdiv.cuenta_id"
                + " and  vis.ods_cbdiv_id IN (" + cveCuenta + ") "
                + " AND to_date( vis.oe_embarque_fec_captura)  BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY') ";
        return sql;
    }

    public String sap(String fec1, String fec2, String cuenta) {
        sql = "select to_char(fecha, 'dd/mm/yyyy'), NVL(rfc, ' '), control_vehicular, importe_flete, cliente, concepto_servicio, NVL(cuenta_inc1, ' '), NVL(importe_inc1, 0), "
                + " NVL(cuenta_inc2, ' '), NVL(importe_inc2, 0), NVL(cuenta_inc3, ' '), NVL(importe_inc3, 0), NVL(cuenta_inc4, ' '), "
                + " NVL(importe_inc4, 0), NVL(cuenta_inc5, ' '), NVL(importe_inc5, 0),NVL(cuenta_inc6, ' '), NVL(importe_inc6, 0),NVL(cuenta_inc7, ' '), NVL(importe_inc7, 0)"
                + " from onsap_tms@sap where estatus_flete='2' "
                + " and trunc(fecha) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY') " + cuenta + ""
                + " ORDER BY control_vehicular ";
        return sql;
    }

    public String pendSap1(String fec1, String fec2, String estado, String cveCuenta, String trans) {
        sql = "SELECT embarque.embarque_id, "
                + " cuenta.cuenta_nombre, "
                + " embarque.EMBARQUE_FEC_CAPTURA , "
                + " chofer.CHOFER_NOMBRE, "
                + " ltrans.LTRANSPORTE_DESCRIPCION, "
                + " utrans.UTRANSPORTE_DESC, "
                + " embarque.EMBARQUE_COSTO_REAL , "
                + " costoind.embarque_indirectos_costo, "
                + " gastost.GTRANSP_DESC, "
                + " embarque.EMBARQUE_AGRUPADOR, "
                + " costoind.gtransp_id "
                + " FROM ontms_embarque embarque "
                + " INNER JOIN ontms_camion camion "
                + " ON camion.CAMION_ID = embarque.CAMION_ID "
                + " INNER JOIN ontms_chofer chofer "
                + " ON embarque.chofer_id = chofer.chofer_id "
                + " INNER JOIN ontms_linea_transporte ltrans "
                + " ON ltrans.LTRANSPORTE_ID = camion.LTRANSPORTE_ID "
                + " INNER JOIN ontms_unidad_transporte utrans "
                + " ON utrans.UTRANSPORTE_ID = camion.UTRANSPORTE_ID "
                + " LEFT OUTER JOIN ontms_embarque_indirectos costoind "
                + " ON embarque.EMBARQUE_AGRUPADOR = costoind.EMBARQUE_AGRUPADOR "
                + " LEFT OUTER JOIN ONTMS_GASTOS_TRANSPORTE gastost "
                + " ON gastost.gtransp_id = costoind.gtransp_id "
                + " INNER JOIN ontms_docto_sal historico "
                + " ON historico.DOCTO_SAL_AGRUPADOR = embarque.EMBARQUE_AGRUPADOR "
                + " INNER JOIN ontms_cta_bod_div ctaboddiv "
                + " ON ctaboddiv.cbdiv_id=historico.cbdiv_id "
                + " INNER JOIN ontms_cuenta cuenta "
                + " ON ctaboddiv.cuenta_id=cuenta.cuenta_id "
                + " WHERE embarque.embarque_estado_id IN (" + estado + ") "
                + " AND TRUNC(embarque.embarque_fec_captura) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY') "
                + " AND cuenta.cuenta_id IN (" + cveCuenta + ")"
                + " AND ltrans.LTRANSPORTE_ID IN(" + trans + ") "
                + " GROUP BY embarque.embarque_id, "
                + " embarque.EMBARQUE_FEC_CAPTURA , "
                + " ltrans.LTRANSPORTE_DESCRIPCION, "
                + " utrans.UTRANSPORTE_DESC, "
                + " embarque.EMBARQUE_COSTO_REAL, "
                + " chofer.CHOFER_NOMBRE , "
                + " embarque.EMBARQUE_AGRUPADOR , "
                + " costoind.embarque_indirectos_costo, "
                + " costoind.gtransp_id, "
                + " gastost.GTRANSP_DESC , "
                + " cuenta.cuenta_nombre, "
                + " cuenta.cuenta_id "
                + " ORDER BY ltrans.LTRANSPORTE_DESCRIPCION ASC";
        return sql;
    }

    public String pendSap(String fec1, String fec2, String estado, String cveCuenta) {
        sql = "SELECT embarque.embarque_id, "
                + " cuenta.cuenta_nombre, "
                + " embarque.EMBARQUE_FEC_CAPTURA , "
                + " chofer.CHOFER_NOMBRE, "
                + " ltrans.LTRANSPORTE_DESCRIPCION, "
                + " utrans.UTRANSPORTE_DESC, "
                + " embarque.EMBARQUE_COSTO_REAL , "
                + " costoind.embarque_indirectos_costo, "
                + " gastost.GTRANSP_DESC, "
                + " embarque.EMBARQUE_AGRUPADOR, "
                + " costoind.gtransp_id "
                + " FROM ontms_embarque embarque "
                + " INNER JOIN ontms_camion camion "
                + " ON camion.CAMION_ID = embarque.CAMION_ID "
                + " INNER JOIN ontms_chofer chofer "
                + " ON embarque.chofer_id = chofer.chofer_id "
                + " INNER JOIN ontms_linea_transporte ltrans "
                + " ON ltrans.LTRANSPORTE_ID = camion.LTRANSPORTE_ID "
                + " INNER JOIN ontms_unidad_transporte utrans "
                + " ON utrans.UTRANSPORTE_ID = camion.UTRANSPORTE_ID "
                + " LEFT OUTER JOIN ontms_embarque_indirectos costoind "
                + " ON embarque.EMBARQUE_AGRUPADOR = costoind.EMBARQUE_AGRUPADOR "
                + " LEFT OUTER JOIN ONTMS_GASTOS_TRANSPORTE gastost "
                + " ON gastost.gtransp_id = costoind.gtransp_id "
                + " INNER JOIN ontms_docto_sal historico "
                + " ON historico.DOCTO_SAL_AGRUPADOR = embarque.EMBARQUE_AGRUPADOR "
                + " INNER JOIN ontms_cta_bod_div ctaboddiv "
                + " ON ctaboddiv.cbdiv_id=historico.cbdiv_id "
                + " INNER JOIN ontms_cuenta cuenta "
                + " ON ctaboddiv.cuenta_id=cuenta.cuenta_id "
                + " AND embarque.embarque_estado_id IN (" + estado + ") "
                + " AND TRUNC(embarque.embarque_fec_captura) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY') "
                + " AND cuenta.cuenta_id IN (" + cveCuenta + ")"
                + " GROUP BY embarque.embarque_id, "
                + " embarque.EMBARQUE_FEC_CAPTURA , "
                + " ltrans.LTRANSPORTE_DESCRIPCION, "
                + " utrans.UTRANSPORTE_DESC, "
                + " embarque.EMBARQUE_COSTO_REAL, "
                + " chofer.CHOFER_NOMBRE , "
                + " embarque.EMBARQUE_AGRUPADOR , "
                + " costoind.embarque_indirectos_costo, "
                + " costoind.gtransp_id, "
                + " gastost.GTRANSP_DESC , "
                + " cuenta.cuenta_nombre, "
                + " cuenta.cuenta_id "
                + " ORDER BY ltrans.LTRANSPORTE_DESCRIPCION";
        return sql;
    }

    public String fleteGral(String fec1, String fec2, String cbdiv) {
        sql = " SELECT"
                + " (SELECT COUNT(DISTINCT oe.embarque_id)"
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_hist_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " WHERE embarque_estado_id IN(1,2,3) AND trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY') AND ods.cbdiv_id IN (" + cbdiv + ")"
                + " ) AS novalidados ,"
                + " (SELECT COUNT( DISTINCT oe.embarque_id)"
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_hist_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " WHERE embarque_estado_id IN(5,7) AND trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY') AND ods.cbdiv_id IN (" + cbdiv + ")"
                + " ) AS validados,"
                + " (SELECT COUNT( DISTINCT oe.embarque_id)"
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_hist_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " WHERE embarque_estado_id IN(6) AND trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY')  AND ods.cbdiv_id IN (" + cbdiv + ")"
                + " ) AS liquidados,"
                + " (SELECT SUM(oe.embarque_costo_real)"
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_hist_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " WHERE embarque_estado_id IN(5,6,7) AND trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY')  AND ods.cbdiv_id IN (" + cbdiv + ")"
                + " )AS flete"
                + " FROM dual";
        return sql;
    }

    public String fletGralCont(String fec1, String fec2, String estado, String cveCuenta) {
        sql = " SELECT oe.embarque_id, oe.embarque_agrupador, oe.embarque_estado_id,"
                + " oe.embarque_liquidacion,oe.embarque_costo_real, ocu.cuenta_nombre,ocu.cuenta_id "
                + " FROM ontms_embarque oe, ontms_cuenta ocu, ontms_cta_bod_div ocbd, ontms_hist_docto_sal ods "
                + " WHERE oe.embarque_estado_id IN (" + estado + ") AND ods.docto_sal_agrupador =oe.embarque_agrupador "
                + " AND ods.cbdiv_id =ocbd.cbdiv_id AND ocbd.cuenta_id =ocu.cuenta_id "
                + " AND ocu.cuenta_id IN (" + cveCuenta + ") AND trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY') "
                + " GROUP BY oe.embarque_id, oe.embarque_agrupador, oe.embarque_estado_id,"
                + " oe.embarque_liquidacion,oe.embarque_costo_real, ocu.cuenta_nombre,ocu.cuenta_id";
        return sql;
    }

    public String fletGralCont1(String fec1, String fec2, String estado, String cveCuenta) {
        sql = "SELECT count(em), sum(flete) FROM"
                + " ( "
                + " SELECT oe.embarque_id AS em, oe.embarque_agrupador, oe.embarque_estado_id,  "
                + " oe.embarque_liquidacion,NVL(oe.embarque_costo_real, 0) AS flete,  ocu.cuenta_nombre AS cta,ocu.cuenta_id"
                + " FROM ontms_embarque oe,"
                + " ontms_cuenta ocu,"
                + " ontms_cta_bod_div ocbd,"
                + " ontms_hist_docto_sal ods,  "
                + " ontms_division od"
                + " WHERE oe.embarque_estado_id IN (" + estado + ")"
                + " AND ods.docto_sal_agrupador  =oe.embarque_agrupador"
                + " AND ods.cbdiv_id  =ocbd.cbdiv_id"
                + " AND ocbd.cuenta_id =ocu.cuenta_id"
                + " AND od.division_id = ocbd.division_id"
                + " AND ocu.cuenta_id  IN (" + cveCuenta + ")"
                + " AND trunc(oe.embarque_fec_captura) BETWEEN TO_DATE('" + fec1 + "','DD/MM/YYYY') AND TO_DATE('" + fec2 + "','DD/MM/YYYY')  "
                + "GROUP BY oe.embarque_id, oe.embarque_agrupador,oe.embarque_estado_id,"
                + " oe.embarque_liquidacion,NVL(oe.embarque_costo_real, 0),"
                + " ocu.cuenta_nombre,ocu.cuenta_id )";
        return sql;
    }

    public String cN(String cveCuenta, String comple) {
        sql = "SELECT ocbd.cuenta_id, ocu.cuenta_nombre"
                + " FROM ontms_cta_bod_div ocbd,ontms_cuenta ocu"
                + " WHERE ocu.cuenta_id =ocbd.cuenta_id"
                + " AND " + comple + "  IN (" + cveCuenta + ")"
                + " GROUP BY ocbd.cuenta_id, ocu.cuenta_nombre"
                + " ORDER BY ocu.cuenta_nombre";
        return sql;
    }

    public String presupuesto(String mes, String anio, String cta) {
        sql = "SELECT cuenta_id,id_mes,anio,  presupuesto FROM ontms_presupuestos WHERE id_mes=" + mes + ""
                + " AND anio=" + anio + " AND cuenta_id=" + cta + " GROUP BY cuenta_id,id_mes,anio,  presupuesto";
        return sql;
    }

    public String muestraAuditoria(String id) {
        sql = "SELECT oe.embarque_id,to_char(oe.embarque_fec_captura,'dd/mm/yyyy') , oae.fecha, bp.user_name, "
                + "oae.tipo, op.descripcion, oae.costo, oae.embarque_agrupador, "
                + "oae.usuario_id,  oae.ip,  oae.gtransp_id,  bp.user_nid,oae.desc_proceso_id "
                + "FROM ontms_auditoria_embarques oae, ontms_desc_procesos op, broker_passwd bp, ontms_embarque oe "
                + "WHERE oae.desc_proceso_id=op.desc_proceso_id "
                + "AND oae.usuario_id=bp.user_nid "
                + "AND oe.embarque_agrupador=oae.embarque_agrupador "
                + "AND  oe.embarque_id='" + id + "' ORDER BY oe.embarque_id";
        return sql;
    }

    public String auditoria() {
        sql = "SELECT bp.user_nid, bp.user_name, bg.grupo_nombre"
                + " FROM broker_passwd bp, broker_usuario_grupo bug, broker_grupo bg"
                + " WHERE bp.user_id=bug.user_id"
                + " AND bug.grupo_id=bg.grupo_id"
                + " AND bg.grupo_nombre LIKE 'CTA%' "
                + " ORDER BY bp.user_name";
        return sql;
    }

    public String resumenEvidencias(String[] id) {
        sql = "SELECT count(ods.docto_sal_id) FROM ontms_docto_sal ods  "
                + "INNER JOIN ontms_cta_bod_div oc on oc.cbdiv_id=ods.cbdiv_id"
                + " WHERE to_char(ods.docto_fec_factura,'yyyy')= " + id[0] + " AND to_char(ods.docto_fec_factura,'mm')=" + id[1] + " "
                + " AND ods.docto_estado_id IN (2,10) and oc.cuenta_id in (" + id[2] + ")";
        return sql;
    }

    public String detEvidencia(String[] id) {
        sql = "SELECT  /*+ FIRST_ROWS */   ods.d_docto_fec_factura,  ods.d_docto_referencia,  v.oe_embarque_id,  v.oe_embarque_fec_captura,"
                + " v.olt_ltransporte_nombre,  ods.des_destino_nombre,  ods.col_colonia_nombre,  ods.ciu_ciudad_nombre,"
                + " ods.d_docto_piezas,  ods.d_docto_cajas,  ods.d_docto_pallets,  ods.d_docto_colgados,  ods.d_docto_contenedor,"
                + " ods.d_docto_atados,  ods.d_docto_bulks,  ods.d_docto_peso,  ods.d_docto_importe,  ods.d_docto_volumen,"
                + " ods.div_division_nombre  FROM vw_pq_em v"
                + " INNER JOIN ontms_vw_reporte_doc ods ON ods.d_docto_sal_agrupador =v.oe_embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.d_cbdiv_id=ocbd.cbdiv_id"
                + " INNER JOIN ontms_cuenta ocu ON ocbd.cuenta_id=ocu.cuenta_id"
                + " WHERE ods.d_docto_estado_id IN (" + id[1] + ")"
                + " AND ocu.cuenta_id  IN (" + id[0] + ")"
                + " AND SUBSTR(ods.d_docto_fec_captura,7)= '" + id[2] + "'";
        return sql;
    }

    public String muestraEmbarques01(String cta, String f1, String f2) {
        sql = "SELECT oe_embarque_id,  oe_embarque_fec_captura,  oc_chofer_nombre,"
                + " olt_ltransporte_nombre,  outr_utransporte_desc  FROM vw_pq_em v"
                + " INNER JOIN ontms_vw_reporte_doc ods ON ods.d_docto_sal_agrupador =v.oe_embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.d_cbdiv_id=ocbd.cbdiv_id"
                + " INNER JOIN ontms_cuenta ocu ON ocbd.cuenta_id  =ocu.cuenta_id"
                + " WHERE to_date( oe_embarque_fec_captura) BETWEEN TO_DATE('" + f1 + "','DD/MM/YYYY') AND TO_DATE('" + f2 + "','DD/MM/YYYY')  "
                + " AND ocu.cuenta_id  IN (" + cta + ")"
                + " GROUP BY  oe_embarque_id,  oe_embarque_fec_captura,  oc_chofer_nombre,"
                + " olt_ltransporte_nombre,  outr_utransporte_desc ORDER BY oe_embarque_id";
        return sql;
    }

    public String doctoEmb(String cta, String f1, String f2) {
        sql = "SELECT  /*+ FIRST_ROWS */  ods.d_docto_fec_captura, ods.d_docto_referencia,  v.oe_embarque_id,  v.oe_embarque_fec_captura,"
                + " v.olt_ltransporte_nombre,  ods.des_destino_nombre, ods.col_colonia_nombre,  ods.ciu_ciudad_nombre,  ods.d_docto_piezas,"
                + " ods.d_docto_cajas,  ods.d_docto_pallets,  ods.d_docto_colgados,ods.d_docto_contenedor,  ods.d_docto_atados,  ods.d_docto_bulks,"
                + " ods.d_docto_peso,  ods.d_docto_importe,  ods.d_docto_volumen,ods.div_division_nombre FROM vw_pq_em v"
                + " INNER JOIN ontms_vw_reporte_doc ods ON ods.d_docto_sal_agrupador =v.oe_embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.d_cbdiv_id=ocbd.cbdiv_id"
                + " INNER JOIN ontms_cuenta ocu ON ocbd.cuenta_id =ocu.cuenta_id"
                + " WHERE ocu.cuenta_id  IN (" + cta + ") "
                + " AND to_date(oe_embarque_fec_captura) BETWEEN TO_DATE('" + f1 + "','DD/MM/YYYY') AND TO_DATE('" + f2 + "','DD/MM/YYYY')  ";
        return sql;
    }

    public String doctoPendEmb(String cta, String f1, String f2, String edo, String fecha) {
        sql = "SELECT /*+ FIRST_ROWS */ * FROM ontms_vw_reporte_doc ods"
                + " LEFT JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador"
                + " WHERE d_docto_estado_id IN (" + edo + ") AND ods.cbd_cuenta_id IN (" + cta + ")"
                + " AND  " + fecha + " BETWEEN TO_DATE('" + f1 + "','DD/MM/YYYY') AND TO_DATE('" + f2 + "','DD/MM/YYYY') "
                + " ORDER BY D_Docto_Referencia";
        return sql;
    }

    /*
     * public String excelPendEvid(String... id) { sql = "SELECT /*+ FIRST_ROWS
     */ /*
     * vi.*,oe.embarque_id,to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),ol.ltransporte_nombre"
     * + " FROM ontms_vw_reporte_doc vi" + " LEFT JOIN ontms_embarque oe ON
     * oe.embarque_agrupador=vi.d_docto_sal_agrupador" + " LEFT JOIN
     * ontms_camion oca ON oca.camion_id=oe.camion_id" + " LEFT JOIN
     * ontms_linea_transporte ol ON ol.ltransporte_id=oca.ltransporte_id" + "
     * INNER JOIN ontms_cta_bod_div ocbd ON d_cbdiv_id=ocbd.cbdiv_id" + " inner
     * join ontms_cuenta ocu on ocu.cuenta_id=ocbd.cuenta_id" + " WHERE
     * SUBSTR(d_docto_fec_captura,7)=" + id[2] + " " + " AND D_Docto_Estado_Id
     * IN (" + id[1] + ")" + " AND ocu.cuenta_id=" + id[0] + "" + " ORDER BY
     * D_Docto_Referencia"; return sql; }
     */

    public String diasEvidencia(String[] id) {
        sql = "SELECT od.oc_cuenta_nombre,oe.oe_embarque_id,oe.oe_embarque_fec_captura,"
                + " oe.olt_ltransporte_nombre,oc.ciudad_nombre,(trunc(SYSDATE)-to_date(oe_embarque_fec_captura))"
                + " FROM vw_pq_em oe"
                + " INNER JOIN vw_consulta_doc od ON od.ods_docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN ontms_ciudad oc ON oc.ciudad_id=od.ode_ciudad_id"
                + " INNER JOIN ontms_cta_bod_div oct ON oct.cbdiv_id=od.ods_cbdiv_id"
                + " WHERE oct.cuenta_id IN (" + id[0] + ") "
                + " AND oe.oe_embarque_fec_captura BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') "
                + " AND od.ods_docto_estado_id IN (2)"
                + " GROUP BY od.oc_cuenta_nombre, oe.oe_embarque_id, oe.oe_embarque_fec_captura,"
                + " oc.ciudad_nombre, oe.olt_ltransporte_nombre";
        return sql;
    }
}
