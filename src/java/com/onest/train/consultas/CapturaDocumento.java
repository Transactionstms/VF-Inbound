/*ELECT d.d_docto_sal_id, d.d_docto_referencia,d.cue_cuenta_nombre,d.ddh_destino_nombre,   d.ddh_destino_ciudad,d.ddh_destino_estado,TO_CHAR(d.d_docto_fec_captura,'dd/mm/yyyy'),  d.d_docto_piezas, d.d_docto_cajas, (d.d_doct
 * To change this template, choose Tools | Templates
 * AND open the template in the editor.
 */
package com.onest.train.consultas;

import com.onest.oracle.OracleDB;
//import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * muestraDocumentos
 *
 * @author tere
 */
public class CapturaDocumento {

    String sql = "";

    public String embConsolidar(String cve) {
        sql = "SELECT oe_embarque_agrupador,oe_embarque_id,oe_embarque_fec_captura,"
                + " olt_ltransporte_nombre,oca_camion_placas,oc_chofer_nombre"
                + " FROM vw_pq_em"
                + " INNER JOIN ontms_vw_reporte_doc od ON od.d_docto_sal_agrupador=vw_pq_em.oe_embarque_agrupador"
                + " WHERE oe_embarque_estado_id=4 AND od.d_cbdiv_id in (" + cve + ")"
                + " AND oe_embarque_id LIKE 'EM%' ORDER BY oe_embarque_id";
        return sql;
    }

    public String muestraDatosEmbarcados(String referencia) {

        sql = " select  "
                + " ods.docto_folio_recibo, "
                + " to_char(ods.docto_fec_recibo,'dd/MM/yyyy hh24:mi:ss'), "
                + " ob.bodega_nombre, "
                + " NVL(oe.embarque_id, ' '), "
                + " NVL(to_char(oe.embarque_fec_captura,'dd/MM/yyyy hh24:mi:ss'), ' '), "
                + " NVL(ods.docto_rechazo, '  '), "
                + " NVL(to_char(ods.docto_fec_rechazo,'dd/MM/yyyy hh24:mi:ss'),'  ' ), "
                + " NVL(ods.docto_folio_evidencia,' ' ), "
                + " to_char(ods.docto_captura_evidencia,'dd/MM/yyyy hh24:mi:ss'), "
                + " to_char(ods.docto_fec_evidencia,'dd/MM/yyyy hh24:mi:ss'), "
                + " ods.docto_folio_control, "
                + " to_char(ods.docto_fec_control,'dd/MM/yyyy hh24:mi:ss'), "
                + " olg.folio_liquidacion, "
                + " to_char(olg.fecha,'dd/MM/yyyy hh24:mi:ss'), "//
                + " ods.docto_cargo, "
                + " to_char(ods.docto_fec_cargo,'dd/MM/yyyy hh24:mi:ss'),to_char(ol.FECHA,'dd/MM/yyyy hh24:mi:ss'),ol.FOLIO_LIQUIDACION,och.chofer_nombre "
                + " from ontms_docto_sal ods "
                + " left join ontms_embarque oe "
                + " on ods.docto_sal_agrupador=oe.embarque_agrupador "
                + " inner join ontms_bodega ob "
                + " on ods.bodega_id=ob.bodega_id "
                + " LEFT JOIN ONTMS_LIQUIDACION ol "
                + " ON ol.embarque_agrupador = oe.embarque_agrupador "
                + " LEFT JOIN ONTMS_LIQUIDACIONG olg ON olg.embarque_agrupador = oe.embarque_agrupador"
                + " LEFT JOIN ontms_camion oc  "
                + " ON oc.camion_id=oe.camion_id "
                + " LEFT JOIN ontms_chofer och  "
                + " ON och.chofer_id=oe.chofer_id "
                + " LEFT JOIN ontms_unidad_transporte outr  "
                + " ON outr.utransporte_id=oc.utransporte_id "
                + " LEFT JOIN ontms_linea_transporte olt "
                + " ON olt.ltransporte_id         =oc.ltransporte_id"
                + "  "
                + " where ods.docto_referencia='" + referencia + "' "
                + " ORDER BY 1 desc ";
        return sql;
    }
    
    
    public String muestraDatosEmbarcados(String referencia,int idLenguaje) {

        sql = " select  "
                + " ods.docto_folio_recibo, "
                + " to_char(ods.docto_fec_recibo,'dd/MM/yyyy hh24:mi:ss'), "
                + " ob.bodega_nombre, "
                + " NVL(oe.embarque_id, ' '), ";
        switch(idLenguaje)
        {
            case 3:  sql +=  " NVL(to_char(oe.embarque_fec_captura+INTERVAL '4' HOUR,'dd/MM/yyyy hh24:mi:ss'), ' '), ";break;
            case 4:  sql +=  " NVL(to_char(oe.embarque_fec_captura+INTERVAL '3' HOUR,'dd/MM/yyyy hh24:mi:ss'), ' '), ";break;
            default: sql +=  " NVL(to_char(oe.embarque_fec_captura+INTERVAL '0' HOUR,'dd/MM/yyyy hh24:mi:ss'), ' '), ";
        }
        
            
        sql +=  " NVL(ods.docto_rechazo, '  '), "
                + " NVL(to_char(ods.docto_fec_rechazo,'dd/MM/yyyy hh24:mi:ss'),'  ' ), "
                + " NVL(ods.docto_folio_evidencia,' ' ), ";
                
        //sql     + " to_char(ods.docto_captura_evidencia,'dd/MM/yyyy hh24:mi:ss'), "
        switch(idLenguaje)
        {
            case 3:  sql +=  " NVL(to_char(ods.docto_captura_evidencia+INTERVAL '4' HOUR,'dd/MM/yyyy hh24:mi:ss'), ' '), ";break;
            case 4:  sql +=  " NVL(to_char(ods.docto_captura_evidencia+INTERVAL '3' HOUR,'dd/MM/yyyy hh24:mi:ss'), ' '), ";break;
            default: sql +=  " NVL(to_char(ods.docto_captura_evidencia+INTERVAL '0' HOUR,'dd/MM/yyyy hh24:mi:ss'), ' '), ";
        }
                //+ " to_char(ods.docto_fec_evidencia,'dd/MM/yyyy hh24:mi:ss'), "
        switch(idLenguaje)
        {
            case 3:  sql +=  " NVL(to_char(ods.docto_fec_evidencia+INTERVAL '4' HOUR,'dd/MM/yyyy hh24:mi:ss'), ' '), ";break;
            case 4:  sql +=  " NVL(to_char(ods.docto_fec_evidencia+INTERVAL '3' HOUR,'dd/MM/yyyy hh24:mi:ss'), ' '), ";break;
            default: sql +=  " NVL(to_char(ods.docto_fec_evidencia+INTERVAL '0' HOUR,'dd/MM/yyyy hh24:mi:ss'), ' '), ";
        }
        sql     += " ods.docto_folio_control, "
                + " to_char(ods.docto_fec_control,'dd/MM/yyyy hh24:mi:ss'), "
                + " olg.folio_liquidacion, "
                + " to_char(olg.fecha,'dd/MM/yyyy hh24:mi:ss'), "//
                + " ods.docto_cargo, "
                + " to_char(ods.docto_fec_cargo,'dd/MM/yyyy hh24:mi:ss'),to_char(ol.FECHA,'dd/MM/yyyy hh24:mi:ss'),ol.FOLIO_LIQUIDACION,och.chofer_nombre "
                + " from ontms_docto_sal ods "
                + " left join ontms_embarque oe "
                + " on ods.docto_sal_agrupador=oe.embarque_agrupador "
                + " inner join ontms_bodega ob "
                + " on ods.bodega_id=ob.bodega_id "
                + " LEFT JOIN ONTMS_LIQUIDACION ol "
                + " ON ol.embarque_agrupador = oe.embarque_agrupador "
                + " LEFT JOIN ONTMS_LIQUIDACIONG olg ON olg.embarque_agrupador = oe.embarque_agrupador"
                + " LEFT JOIN ontms_camion oc  "
                + " ON oc.camion_id=oe.camion_id "
                + " LEFT JOIN ontms_chofer och  "
                + " ON och.chofer_id=oe.chofer_id "
                + " LEFT JOIN ontms_unidad_transporte outr  "
                + " ON outr.utransporte_id=oc.utransporte_id "
                + " LEFT JOIN ontms_linea_transporte olt "
                + " ON olt.ltransporte_id         =oc.ltransporte_id"
                + "  "
                + " where ods.docto_referencia='" + referencia + "' "
                + " ORDER BY 1 desc ";
        return sql;
    }
    
    

    public String tipoEmba() {
        sql = "select DISTINCT(tipo_destino_LOF) from ontms_tipo_destino";
        return sql;
    }

    public String folioFactura(String[] id) {
        sql = "SELECT ol.id_doc_embarque,ol.folio_liquidacion, ol.fecha,oe.olt_ltransporte_nombre "
                + " FROM ontms_liquidaciong ol"
                + " INNER JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ol.embarque_agrupador"
                + " INNER JOIN ontms_hist_docto_sal oh ON oh.docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " WHERE ol.folio_liquidacion='" + id[0] + "' and oh.cbdiv_id in (" + id[1] + ")";
        return sql;
    }

    public String detalleFactura(String[] id) {
        sql = "SELECT ol.id_doc_embarque,ol.folio_liquidacion, ol.fecha,oe.olt_ltransporte_nombre "
                + " FROM ontms_liquidaciong ol"
                + " INNER JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ol.embarque_agrupador"
                + " INNER JOIN ontms_hist_docto_sal oh ON oh.docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " WHERE ol.id_doc_embarque=" + id[0] + " ";
        return sql;
    }

    public String detalleFacturaE(String[] id) {
        sql = "SELECT oe.oe_embarque_id,oe.oe_embarque_fec_captura,oe.olt_ltransporte_nombre,"
                + " oe.oca_camion_placas,oe.oc_chofer_nombre,oe.oe_embarque_costo_real"
                + " FROM ontms_liquidaciong ol"
                + " INNER JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ol.embarque_agrupador"
                + " INNER JOIN ontms_hist_docto_sal oh ON oh.docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " WHERE  ol.id_doc_embarque=" + id[0] + "";
        return sql;
    }

    public String embarquePlaneacionConts(String e_cuenta, String tipo, String bo, String documentos,String cbdivid) {
         sql = "SELECT ods.docto_sal_id, "
                + "  ods.docto_referencia, "
                + "  oc.cuenta_nombre, "
                + "  DDH.destino_nombre, "
                + "   "
                + "  DDH.DESTINO_CIUDAD, "
                + "  DDH.DESTINO_ESTADO, "
                + "  OTMR.ruta_nombre, "
                + "  NVL(TO_CHAR(ods.docto_fec_captura,'dd/mm/yyyy'),' '), "
                + "  NVL(TO_CHAR(ods.docto_piezas),' 0.00'), "
                + "  NVL(TO_CHAR(ods.docto_cajas),'0.00 '), "
                + "  NVL(TO_CHAR(ods.docto_pallets),' 0.00'), "
                + "  NVL(TO_CHAR(ods.docto_peso),'0.00'), "
                + "  NVL(TO_CHAR(ods.docto_volumen),'0.00'), "
                + "  ob.bodega_id, "
                + "  ob.bodega_nombre, "
                + "  ods.docto_sal_agrupador, "
                + "  NVL(TO_CHAR(ods.docto_fec_factura,'dd/mm/yyyy'),' '), "
                + "  NVL(TO_CHAR(ods.docto_fec_programacion,'dd/mm/yyyy'),' ') "
                + " FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_cta_bod_div ocbd "
                + " ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " "
                + " INNER JOIN ontms_bodega ob "
                + " ON ob.bodega_id = ocbd.bodega_id "
                + " INNER JOIN ontms_cuenta oc "
                + " ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv "
                + " ON odv.division_id = ocbd.division_id "
                + " INNER JOIN DILOG_DESTINOS_HILTI DDH "
                + " ON DDH.DESTINO_SHIP_TO= ODS.DESTINO_ID "
                + " AND DDH.DIVISION_ID =  " + cbdivid
                + " INNER JOIN DILOG_ORIG_DEST_PAQ DDP "
                + " ON DDP.IATA_CP_DESTINO = DDH.DESTINO_CP "
                + " LEFT JOIN ontms_ruta_ciudad ru "
                + " ON ru.ciudad_id=DDH.DESTINO_CP "
                + " LEFT JOIN ONTMS_RUTAS OTMR "
                + " ON OTMR.RUTA_ID = RU.RUTA_ID "
                + " "
                + "WHERE 1                 =1 "
                + "AND ods.docto_estado_id = 0 "
                + "AND oc.cuenta_id       IN (" + e_cuenta + ") "
                + "AND ob.bodega_id        ='" + bo + "' "
                + "AND ods.DOCTO_ORDEN_COMPRA         IN ("+ documentos + ") "
                + "GROUP BY ods.docto_sal_id, "
                + "  ods.docto_referencia, "
                + "  oc.cuenta_nombre, "
                + "  DDH.destino_nombre, "
                + "   "
                + "  DDH.DESTINO_CIUDAD, "
                + "  DDH.DESTINO_ESTADO, "
                + "  OTMR.ruta_nombre, "
                + "  ods.docto_fec_captura, "
                + "  ods.docto_piezas, "
                + "  ods.docto_cajas, "
                + "  ods.docto_pallets, "
                + "  ods.docto_peso, "
                + "  ods.docto_volumen, "
                + "  ob.bodega_id, "
                + "  ob.bodega_nombre, "
                + "  ods.docto_sal_agrupador, "
                + "  ods.docto_fec_factura, "
                + "  ods.docto_fec_programacion "
                + "ORDER BY OTMR.ruta_nombre, "
                + "  DDH.DESTINO_CIUDAD, "
                + "  DDH.DESTINO_ESTADO, "
                + "  ods.docto_referencia";
        return sql;
    }
    
    
    public String cveDivision(String e_cuenta, String e_bodega1, String e_division) {
        sql = "SELECT cbdiv_id FROM ontms_cta_bod_div WHERE cuenta_id=" + e_cuenta + " AND bodega_id=" + e_bodega1 + " AND division_id=" + e_division + "";
        return sql;
    }

    public String folioRechazo(String cuenta) {
        sql = "select nvl(prefijo_rechazo,'RE')||to_char(sysdate,'yyy')||lpad(secuencia_rechazo,5,0)"
                + " from broker_cuenta where cuenta_id = " + cuenta + "";
        return sql;
    }

    public String evidenciaP(String[] id) {
        sql = "SELECT ov.op_embarque_paq,ov.op_embarque_paq_captura,ov.op_chofer,ov.opa_paqueteria,"
                + " ov.op_embarque_paq_agrupador FROM ontms_vw_paqueteria ov"
                + " WHERE ov.op_embarque_paq IN ('" + id[0] + "') AND ov.ods_cbdiv_id in (" + id[1] + ")";
        return sql;
    }

    public String evidenciaPa(String[] id) {
        sql = "SELECT ov.op_embarque_paq,ov.op_embarque_paq_captura,ov.op_chofer,ov.opa_paqueteria,"
                + " ov.op_embarque_paq_agrupador FROM ontms_vw_paqueteria ov"
                + " WHERE ov.op_embarque_paq_agrupador = '" + id[0] + "'";
        return sql;
    }

    public String evidenciaPaq(String[] id) {
        sql = "SELECT co.ods_docto_referencia, co.ods_docto_piezas,co.ode_destino_nombre,oci.ciudad_nombre,"
                + " oes.estado_nombre,co.ods_docto_sal_id,oe.embarque_paq_agrupador,oe.embarque_paq,SUM(NVL(odsk.cantidad_rechazada,0))"
                + " FROM vw_consulta_doc co INNER JOIN ontms_embarque_paqueteria oe ON co.ods_docto_sal_agrupador=oe.embarque_paq_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON co.ods_cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id =co.ods_destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_ciudad oci ON oci.ciudad_id=co.ode_ciudad_id"
                + " INNER JOIN ontms_estado oes ON oes.estado_id=oci.estado_id"
                + " LEFT JOIN ontms_docto_sku odsk ON odsk.docto_sal_id=co.ods_docto_sal_id"
                + " WHERE oe.embarque_paq_agrupador IN ('" + id[0] + "') AND co.ods_docto_estado_id in (2,10)"
                + " GROUP BY co.ods_docto_referencia, co.ods_docto_piezas,co.ode_destino_nombre, oci.ciudad_nombre, oes.estado_nombre,"
                + " co.ods_docto_sal_id, oe.embarque_paq_agrupador, oe.embarque_paq ORDER BY co.ods_docto_referencia";
        return sql;
    }

    public String cuenta(String userIDNumber) {
        sql = "SELECT ocbd.cuenta_id, oc.cuenta_nombre FROM broker_usuario_div bud"
                + " INNER JOIN broker_passwd bp ON bp.user_nid = bud.user_nid"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id = bud.cbdiv_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_division od ON od.division_id = ocbd.division_id "
                + " WHERE 1=1 AND bp.user_nid = " + userIDNumber + " GROUP BY ocbd.cuenta_id, oc.cuenta_nombre"
                + " ORDER BY ocbd.cuenta_id ";
        return sql;
    }

    public boolean consultaValidar(OracleDB oraDB, String docto_sal, String medida) {
        sql = "SELECT * FROM ontms_det_sal WHERE docto_sal_id='" + docto_sal + "' AND umedida_id=" + medida + "";
        boolean val = oraDB.execute(sql);
        return val;
    }

    public boolean insertarDocumento(OracleDB oraDB, String docto_sal, String cantidad, String medida) {
        sql = "INSERT INTO ontms_det_sal VALUES(null,'" + docto_sal + "'," + cantidad + "," + medida + ")";
        boolean oraOut = oraDB.execute(sql);
        return oraOut;
    }

    public boolean validarDetalleProducto(OracleDB oraDB, String id, String docto_sal) {
        sql = "SELECT * FROM ontms_docto_sku WHERE docto_sal_id IN ('" + docto_sal + "') AND producto_sku IN ('" + id + "')";
        System.out.println(sql);
        boolean oraOut = oraDB.execute(sql);
        return oraOut;
    }

    public boolean insertarDetalleProducto(OracleDB oraDB, String docto_sal, String id, String cantidad) {
        boolean oraOut = oraDB.execute("INSERT INTO ontms_docto_sku VALUES (null,'" + docto_sal + "','" + id + "'," + cantidad + ",0,0,0,'')");
        return oraOut;
    }

    public boolean insertarDetalleProductoR(OracleDB oraDB, String docto_sal, String id, String cantidad) {
        boolean oraOut = oraDB.execute("insert into ontms_docto_sku values(null,'" + docto_sal + "','" + id + "'," + cantidad + ",0,0,0,null)");
        return oraOut;
    }

    public String documentoRE(String v_documento, String cveCuenta, String estado) {
        //rechazo parcial --10
        // embarcado -- 2
        sql = "SELECT ods.d_cbdiv_id,ods.d_docto_referencia,ods.cue_cuenta_nombre,"
                + " ods.bod_bodega_nombre,ods.div_division_nombre,ods.d_docto_sal_id"
                + " FROM vw_pq_em vq"
                + " INNER JOIN ontms_vw_reporte_doc2 ods ON ods.d_docto_sal_agrupador=vq.oe_embarque_agrupador"
                + " left join ontms_docto_factura odf on ods.d_docto_sal_id=odf.docto_sal_id "
                + " WHERE 1=1 AND UPPER(ods.d_docto_referencia)='" + v_documento + "'"
                + " AND ods.d_docto_estado_id IN (" + estado + ") AND ods.d_cbdiv_id IN (" + cveCuenta + ")"
                + " AND vq.oe_embarque_estado_id<>4"
                + " GROUP BY ods.d_cbdiv_id,ods.d_docto_referencia,ods.cue_cuenta_nombre,"
                + " ods.bod_bodega_nombre,ods.div_division_nombre,ods.d_docto_sal_id";
        return sql;
    }
public String documentoREBR(String v_documento, String cveCuenta, String estado) {
        //rechazo parcial --10
        // embarcado -- 2
        sql = "SELECT ods.d_cbdiv_id,odf.docto_factura,ods.cue_cuenta_nombre,"
                + " ods.bod_bodega_nombre,ods.div_division_nombre,ods.d_docto_sal_id"
                + " FROM vw_pq_em vq"
                + " INNER JOIN ontms_vw_reporte_doc2 ods ON ods.d_docto_sal_agrupador=vq.oe_embarque_agrupador"
                + " inner join ontms_docto_factura odf on ods.d_docto_sal_id=odf.docto_sal_id "
                + " WHERE 1=1 AND UPPER(odf.docto_factura)='" + v_documento + "'"
                + " AND ods.d_docto_estado_id IN (" + estado + ") AND ods.d_cbdiv_id IN (" + cveCuenta + ")"
                + " AND vq.oe_embarque_estado_id<>4"
                + " GROUP BY ods.d_cbdiv_id,odf.docto_factura,ods.cue_cuenta_nombre,"
                + " ods.bod_bodega_nombre,ods.div_division_nombre,ods.d_docto_sal_id";
        return sql;
    }
    public String documentoRechazoDetalle(String v_documento, String cveCuenta) {
        sql = "SELECT ob.bodega_nombre,"
                + "  ddh.DESTINO_NOMBRE,"
                + "  odv.division_nombre,"
                + "  td.tipo_docto_desc,"
                + "  NVL(ods.docto_pedido,' '),"
                + "  NVL(TO_CHAR(ods.docto_fec_programacion,'dd/mm/yyyy hh:mi'),' '),"
                + "  TO_CHAR(ods.docto_fec_captura,'dd/mm/yyyy hh:mi'),' ',"
                + "  NVL(TO_CHAR(ods.docto_importe),' '),"
                + "  NVL(TO_CHAR(ods.docto_peso),' '),"
                + "  NVL(TO_CHAR(ods.docto_volumen),' '),"
                + "  ods.docto_totales,"
                + "  outr.utransporte_desc,"
                + "  olt.ltransporte_nombre,"
                + "  och.chofer_nombre,"
                + "  oe.embarque_id,"
                + "  oe.embarque_fec_captura,"
                + "  ods.docto_sal_id,"
                + "  ods.docto_referencia,"
                + "  NVL(TO_CHAR(ods.docto_piezas),' ')"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd"
                + " ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob"
                + " ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc"
                + " ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv"
                + " ON odv.division_id = ocbd.division_id"
                + " INNER JOIN DILOG_DESTINOS_HILTI ddh"
                + " ON ddh.destino_ship_to =ods.destino_id"
                + " and ods.cbdiv_id = ddh.division_id"
                + " LEFT JOIN ontms_bodega ob1"
                + " ON ob1.bodega_id=ods.bodega_id"
                + " left JOIN ontms_tipo_docto td"
                + " ON ods.tipo_docto_id =td.tipo_docto_id"
                + " INNER JOIN ontms_embarque oe"
                + " ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_camion oca"
                + " ON oe.camion_id=oca.camion_id"
                + " INNER JOIN ontms_unidad_transporte outr"
                + " ON oca.utransporte_id=outr.utransporte_id"
                + " INNER JOIN ontms_linea_transporte olt"
                + " ON oca.ltransporte_id=olt.ltransporte_id"
                + " INNER JOIN ontms_chofer och"
                + " ON oe.chofer_id        = och.chofer_id"
                + " WHERE 1=1 AND ods.docto_sal_id='" + v_documento + "'"
                + " AND ods.docto_estado_id='2' AND ods.cbdiv_id='" + cveCuenta + "'"
                + " GROUP BY ob.bodega_nombre, ddh.DESTINO_NOMBRE, odv.division_nombre,"
                + " td.tipo_docto_desc, ods.docto_pedido, ods.docto_fec_programacion,"
                + " ods.docto_fec_captura, oc.cuenta_nombre, ods.docto_importe,"
                + " ods.docto_peso, ods.docto_volumen, ods.docto_totales,"
                + " outr.utransporte_desc, olt.ltransporte_nombre,och.chofer_nombre,oe.embarque_id,"
                + " oe.embarque_fec_captura, ods.docto_sal_id, ods.docto_referencia,ods.docto_piezas"
                + " ORDER BY ods.docto_fec_captura desc ";
        return sql;
    }

    public String muestraDocumentos2(String id) {

        sql = " SELECT docto_sal_id, "
                + " docto_referencia, "
                + " NVL(docto_concentrado,' '), "
                + " NVL(TO_CHAR(docto_fec_concentrado,'dd/mm/yyyy'),' '), "
                + " NVL(docto_talon,' '), "
                + " NVL(TO_CHAR(docto_fec_talon,'dd/mm/yyyy'),' '), "
                + " NVL(docto_pedido,' '), "
                + " NVL(TO_CHAR(docto_fec_pedido,'dd/mm/yyyy'),' '), "
                + " NVL(docto_programacion,' '), "
                + " NVL(TO_CHAR(docto_fec_programacion,'dd/mm/yyyy'),' '), "
                + " NVL(TO_CHAR(docto_fec_cancelacion,'dd/mm/yyyy'),' '), "
                + " NVL(TO_CHAR(docto_importe),' '), "
                + " NVL(TO_CHAR(docto_peso),' '), "
                + " NVL(TO_CHAR(docto_volumen),' '), "
                + " NVL(TO_CHAR(docto_fec_captura,'dd/mm/yyyy'),' '), "
                + " otd.tipo_docto_desc, "
                + " ddh.destino_nombre, "
                + " NVL(docto_sal_cantidad,0), "
                + " ae.edo_desc, "
                + " NVL(TO_CHAR(ods.docto_piezas),' '), "
                + " NVL(TO_CHAR(ods.docto_cajas),' '), "
                + " NVL(TO_CHAR(ods.docto_pallets),' '), "
                + " NVL(TO_CHAR(ods.docto_colgados),' '), "
                + " NVL(TO_CHAR(ods.docto_contenedor),' '), "
                + " NVL(TO_CHAR(ods.docto_atados),' '), "
                + " NVL(TO_CHAR(ods.docto_bulks),' '), "
                + " odv.division_nombre, "
                + " ods.docto_estado_id, "
                + " NVL(ods.docto_folio_control,'0'), "
                + " NVL(TO_CHAR(DOCTO_FEC_CONTROL,'dd/mm/yyyy'),' '), "
                + " NVL(TO_CHAR(docto_fec_factura,'dd/mm/yyyy'),' ') "
                + " FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_cta_bod_div ocbd "
                + " ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_bodega ob "
                + " ON ob.bodega_id = ocbd.bodega_id "
                + " INNER JOIN ontms_cuenta oc "
                + " ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv "
                + " ON odv.division_id = ocbd.division_id "
                + " INNER JOIN DILOG_DESTINOS_HILTI ddh "
                + " ON ddh.destino_ship_to = ods.destino_id "
                + " and ddh.division_id = ods.cbdiv_id "
                + " INNER JOIN ontms_tipo_docto otd "
                + " ON ods.tipo_docto_id=otd.tipo_docto_id "
                + " INNER JOIN app_estado ae "
                + " ON ae.edo_valor       =ods.docto_estado_id "
                + " WHERE ods.docto_sal_id='" + id + "' "
                + " AND ae.edo_tabla     IN ('ONTMS_DOCTO_SAL') "
                + " GROUP BY NVL(TO_CHAR(docto_fec_factura,'dd/mm/yyyy'),' '), "
                + " ODS.docto_fec_control, "
                + " ods.docto_folio_control, "
                + " ODS.docto_sal_id, "
                + " ODS.docto_referencia, "
                + " ODS.docto_concentrado, "
                + " ODS.docto_fec_concentrado, "
                + " ods.docto_estado_id , "
                + " ODS.docto_talon, "
                + " ODS.docto_fec_talon, "
                + " ODS.docto_pedido, "
                + " ODS.docto_fec_pedido, "
                + " ODS.docto_programacion, "
                + " ODS.DOCTO_FEC_PROGRAMACION, "
                + " ODS.docto_fec_cancelacion, "
                + " ODS.docto_importe, "
                + " ODS.docto_peso, "
                + " ODS.docto_volumen, "
                + " ODS.docto_fec_captura, "
                + " otd.tipo_docto_desc, "
                + " ddh.destino_nombre, "
                + " ODS.docto_sal_cantidad, "
                + " ods.docto_sal_agrupador, "
                + " ae.edo_desc, "
                + " ods.docto_piezas, "
                + " ods.docto_cajas, "
                + " ods.docto_pallets, "
                + " ods.docto_colgados, "
                + " ods.docto_contenedor, "
                + " ods.docto_atados, "
                + " ods.docto_bulks, "
                + " odv.division_nombre";

        return sql;

    }

    public String muestraDocumentosDetalle(String agrupador) {
        sql = "SELECT oum.umedida_desc,det_sal_cantidad"
                + " FROM ontms_det_sal odes"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_id=odes.docto_sal_id"
                + " INNER JOIN ontms_unidad_medida oum ON odes.umedida_id=oum.umedida_id"
                + " WHERE ods.docto_sal_id='" + agrupador + "'"
                + " GROUP BY oum.umedida_desc,det_sal_cantidad ";
        return sql;
    }

    public String muestraDocumentosDetalleProducto(String agrupador) {
        sql = "SELECT op.producto_desc,ods.docto_sku_cantidad,ods.producto_sku,NVL(cantidad_rechazada,0), ods.producto_sku "
                + " FROM ontms_docto_sku ods"
                + " INNER JOIN ontms_producto op ON ods.producto_sku=op.producto_sku"
                + " WHERE ods.docto_sal_id='" + agrupador + "' GROUP BY ods.producto_sku,op.producto_desc,ods.docto_sku_cantidad,ods.producto_sku,cantidad_rechazada"
                + " ORDER BY op.producto_desc ";
        return sql;
    }

    public String muestraDocumentosEmbarcados(String agrupador, String id, String es) {
        sql = " SELECT oe.embarque_id,to_char(embarque_fec_captura ,'dd/mm/yyyy hh:mi'), oe.embarque_agrupador FROM ontms_embarque oe"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador WHERE ods.docto_sal_agrupador='" + agrupador + "'"
                + " GROUP BY oe.embarque_id,to_char(embarque_fec_captura ,'dd/mm/yyyy hh:mi'),oe.embarque_agrupador union SELECT oe.embarque_id,to_char(embarque_fec_captura ,'dd/mm/yyyy hh:mi'), oe.embarque_agrupador"
                + " FROM  ontms_embarque oe"
                + " INNER JOIN ontms_hist_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " LEFT JOIN ontms_camion oc ON oc.camion_id=oe.camion_id"
                + " LEFT JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " LEFT JOIN ontms_unidad_transporte outr ON outr.utransporte_id=oc.utransporte_id"
                + " LEFT JOIN ontms_linea_transporte olt ON olt.ltransporte_id=oc.ltransporte_id"
                + " WHERE ods.docto_sal_id='" + id + "' AND ods.docto_estado_id in (" + es + ") "
                + " GROUP BY oe.embarque_id,to_char(embarque_fec_captura ,'dd/mm/yyyy hh:mi'),oe.embarque_agrupador ORDER BY 2";
        return sql;
    }

    public String muestraDocumentosRechazo(String agrupador, String tabla) {
        sql = "SELECT docto_rechazo,to_char(docto_fec_rechazo ,'dd/mm/yyyy') FROM " + tabla + ""
                + " WHERE docto_sal_id ='" + agrupador + "' AND (docto_estado_id = 10 or docto_estado_id = 11) GROUP BY docto_rechazo,to_char(docto_fec_rechazo ,'dd/mm/yyyy')";
        return sql;
    }

    public String muestraDocumentosEvidencia(String agrupador, String tabla) {
        sql = "SELECT docto_folio_evidencia,NVL(TO_CHAR(TO_DATE(docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ') FROM " + tabla + ""
                + " WHERE docto_sal_id ='" + agrupador + "'"
                + " AND (docto_estado_id = 7 or docto_estado_id = 8) GROUP BY docto_folio_evidencia,NVL(TO_CHAR(TO_DATE(docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ')  ";
        return sql;
    }

    public String evidenciaDocumento(String documento) {

        sql = " SELECT docto_referencia, "
                + "  TO_CHAR(docto_fec_captura,'dd/mm/yyyy'), "
                + "  otd.tipo_docto_desc, "
                + "  oc.destino_nombre, "
                + "  NVL(oc.destino_domicilio,' ') docto_importe, "
                + "  NVL(docto_piezas,0), "
                + "  oe.embarque_id, "
                + "  TO_CHAR(oe.embarque_fec_captura,'dd/mm/yyyy'), "
                + "  NVL(SUM(odsk.cantidad_rechazada),0), "
                + "  ods.docto_estado_id "
                + " FROM ontms_docto_sal ods "
                + " LEFT JOIN ontms_tipo_docto otd "
                + " ON ods.tipo_docto_id=otd.tipo_docto_id "
                + " INNER JOIN dilog_destinos_hilti oc "
                + " ON ods.destino_id=oc.destino_ship_to "
                + " and ods.cbdiv_id = oc.division_id "
                + " INNER JOIN ontms_embarque oe "
                + " ON ods.docto_sal_agrupador=oe.embarque_agrupador "
                + " LEFT JOIN ontms_docto_sku odsk "
                + " ON ods.docto_sal_id=odsk.docto_sal_id "
                + " LEFT JOIN ontms_bodega ob1 "
                + " ON ob1.bodega_id        =ods.bodega_id "
                + " WHERE ods.docto_sal_id      ='" + documento + "' "
                + " AND NVL(docto_piezas,0)<>0 "
                + " AND (ods.docto_estado_id=10 "
                + " OR ods.docto_estado_id  =2) "
                + " GROUP BY docto_referencia, "
                + "  TO_CHAR(docto_fec_captura,'dd/mm/yyyy'), "
                + "  otd.tipo_docto_desc, "
                + "  oc.destino_nombre, "
                + "  oc.destino_domicilio, "
                + "  docto_importe, "
                + "  docto_piezas, "
                + "  oe.embarque_id, "
                + "  TO_CHAR(oe.embarque_fec_captura,'dd/mm/yyyy'), "
                + "  ods.docto_estado_id  ";

        /* sql = "select docto_referencia, to_char(docto_fec_captura,'dd/mm/yyyy'),"
         + " otd.tipo_docto_desc, oc.cliente_nombre,NVL(oc.cliente_direccion,' ')"
         + " docto_importe,NVL(docto_piezas,0), oe.embarque_id, to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),"
         + " NVL(sum(odsk.cantidad_rechazada),0),ods.docto_estado_id"
         + " FROM ontms_docto_sal ods"
         + " INNER JOIN ontms_tipo_docto otd on ods.tipo_docto_id=otd.tipo_docto_id"
         + " INNER JOIN ontms_cliente oc ON ods.cliente_id=oc.cliente_id"
         + " INNER JOIN ontms_embarque oe ON ods.docto_sal_agrupador=oe.embarque_agrupador"
         + " INNER JOIN ontms_docto_sku odsk ON ods.docto_sal_id=odsk.docto_sal_id"
         + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
         + " WHERE docto_sal_id ='" + documento + "' AND NVL(docto_piezas,0)<>0"
         + " AND (ods.docto_estado_id=10 or ods.docto_estado_id=2)"
         + " GROUP BY docto_referencia, to_char(docto_fec_captura,'dd/mm/yyyy'),"
         + " otd.tipo_docto_desc, oc.cliente_nombre, oc.cliente_direccion,"
         + " docto_importe, docto_piezas, oe.embarque_id, to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),ods.docto_estado_id";
         */
        return sql;
    }

    public String evidenciaDocumentoSinProducto(String documento) {
        sql = "SELECT docto_referencia, to_char(docto_fec_captura,'dd/mm/yyyy'),"
                + " otd.tipo_docto_desc, oc.cliente_nombre,NVL(oc.cliente_direccion,' '),"
                + " docto_importe,ods.docto_piezas, oe.embarque_id,"
                + " to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),ods.docto_estado_id,NVL(ods.docto_sal_cantidad,0)"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_tipo_docto otd ON ods.tipo_docto_id=otd.tipo_docto_id"
                + " INNER JOIN ontms_cliente oc ON ods.cliente_id=oc.cliente_id"
                + " INNER JOIN ontms_embarque oe ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino ode ON ode.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ood ON ood.id_origen=ob.zona_id AND ood.id_destino=ode.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE docto_sal_id='" + documento + "' AND NVL(docto_piezas,0)<>0"
                + " AND (ods.docto_estado_id=10 or ods.docto_estado_id=2)"
                + " GROUP BY docto_referencia, to_char(docto_fec_captura,'dd/mm/yyyy'),"
                + " otd.tipo_docto_desc, oc.cliente_nombre, oc.cliente_direccion,"
                + " docto_importe, ods.docto_piezas, oe.embarque_id,"
                + " to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),ods.docto_estado_id,ods.docto_sal_cantidad";
        return sql;
    }

    public String muestraDocumetosDetalleCantidad(String idR) {
        sql = "SELECT docto_sal_cantidad FROM ontms_docto_sal WHERE docto_sal_id='" + idR + "'";
        return sql;
    }
//**

    public String embarqueDetalle(String e_cuenta, String tipo, String e) {
        sql = "SELECT d.d_docto_sal_id, d.d_docto_referencia,d.cue_cuenta_nombre,d.ddh_destino_nombre,  "
                + " d.ddh_destino_ciudad,d.ddh_destino_estado,TO_CHAR(d.d_docto_fec_captura,'dd/mm/yyyy'), "
                + " d.d_docto_piezas, d.d_docto_cajas, (d.d_docto_peso/1000), d.d_docto_volumen,d.dodp_origen_dest_id "
                + " FROM ontms_vw_reporte_doc2 d "
                + " WHERE 1=1 AND d.d_docto_estado_id IN ( " + e + " )"
                + " order by d.d_docto_referencia ";


        /*sql = "SELECT d.d_docto_sal_id, d.d_docto_referencia,d.cue_cuenta_nombre,d.des_destino_nombre,"
         + " d.ciu_ciudad_nombre,oe.estado_nombre,TO_CHAR(d.d_docto_fec_captura,'dd/mm/yyyy'),"
         + " d.d_docto_piezas, d.d_docto_cajas, (d.d_docto_peso/1000), d.d_docto_volumen,d.ord_origen_dest_id"
         + " FROM ontms_vw_reporte_doc d"
         + " INNER JOIN ontms_estado oe ON oe.estado_id=d.ciu_estado_id"
         + " INNER JOIN ontms_tipo_destino  ot ON ot.tipo_destino_id=d.ord_tipo_destino_id"
         + " WHERE 1=1 AND d.d_docto_estado_id IN (" + e + ") AND ot.tipo_destino_lof IN (" + tipo + ")"
         + " AND d.d_cbdiv_id IN (" + e_cuenta + ") "
         + " order by d.des_destino_nombre";
         */
        return sql;
    }

    public String embarqueDHL(String e_cuenta) {

        sql = "  SELECT   TRUNC(TARIFAPAQ_DHL,2) FROM ontms_vw_reporte_doc d INNER JOIN ontms_estado oe ON oe.estado_id=d.ciu_estado_id"
                + " INNER JOIN ontms_tipo_destino ot ON ot.tipo_destino_id =d.ord_tipo_destino_id INNER JOIN DILOG_ORIG_DEST_PAQ dop ON dop.origen_dest_id = d.ord_origen_dest_id"
                + " INNER JOIN DILOG_TARIFAS_PAQUETERIAS DTP ON dtP.zona_tarifa          = dop.zona_tarifa AND DTP.TARIFAPAQ_PRODUCTO IN('N') "
                + " AND dtp.tarifapaq_peso      = CEIL((d.d_docto_peso/1000)) AND dop.ORIGEN_DEST_ID      =d.ord_origen_dest_id WHERE 1                     =1"
                + " AND d.d_docto_estado_id    IN (0) AND ot.tipo_destino_lof    IN ('Local','Foraneo') AND d.d_cbdiv_id           IN (" + e_cuenta + ")  "
                + " ORDER BY d.des_destino_nombre";
        return sql;

    }

    public String embarqueDHLE(String e_cuenta) {

        sql = "  SELECT   TRUNC(TARIFAPAQ_DHL,2) FROM ontms_vw_reporte_doc d INNER JOIN ontms_estado oe ON oe.estado_id=d.ciu_estado_id"
                + " INNER JOIN ontms_tipo_destino ot ON ot.tipo_destino_id =d.ord_tipo_destino_id INNER JOIN DILOG_ORIG_DEST_PAQ dop ON dop.origen_dest_id = d.ord_origen_dest_id"
                + " INNER JOIN DILOG_TARIFAS_PAQUETERIAS DTP ON dtP.zona_tarifa          = dop.zona_tarifa AND DTP.TARIFAPAQ_PRODUCTO IN('G') "
                + " AND dtp.tarifapaq_peso      = CEIL((d.d_docto_peso/1000)) AND dop.ORIGEN_DEST_ID      =d.ord_origen_dest_id WHERE 1                     =1"
                + " AND d.d_docto_estado_id    IN (0) AND ot.tipo_destino_lof    IN ('Local','Foraneo') AND d.d_cbdiv_id           IN (" + e_cuenta + ")  "
                + " ORDER BY d.des_destino_nombre";
        return sql;

    }

    public String tarifapaqueteExpress(String e_cuenta, String cbdivId) {

        sql = "SELECT   TRUNC(TARIFAPAQ_PAQUETEEXPRESS,2) FROM ontms_vw_reporte_doc d "
                + "INNER JOIN ontms_estado oe ON oe.estado_id=d.ciu_estado_id "
                + "INNER JOIN ontms_tipo_destino ot ON ot.tipo_destino_id =d.ord_tipo_destino_id "
                + "INNER JOIN DILOG_ORIG_DEST_PAQ dop ON dop.origen_dest_id = d.ord_origen_dest_id "
                + "INNER JOIN DILOG_TARIFAS_PAQUETERIAS DTP ON dtP.zona_tarifa          = dop.zona_tarifa "
                + "AND DTP.TARIFAPAQ_PRODUCTO = dop.tipo_producto AND dtp.tarifapaq_peso     =60 "
                + "AND dop.ORIGEN_DEST_ID      =d.ord_origen_dest_id WHERE 1                     =1 "
                + "AND d.d_docto_estado_id  IN (0) AND ot.tipo_destino_lof    IN ('Local','Foraneo') "
                + "AND d.d_cbdiv_id IN (" + cbdivId + ") ORDER BY d.des_destino_nombre";

        return sql;

    }

    public String embarqueDetallePlaneacion(String e_cuenta, String tipo, String e) {
        sql = "Select oe.embarque_agrupador, oe.embarque_id,oe.embarque_fec_captura "
                + " from ontms_embarque oe "
                + " inner join ontms_docto_sal ods on oe.embarque_agrupador=ods.docto_sal_agrupador "
                + " where embarque_estado_id=8 "
                + " and embarque_id like 'PL%' "
                + " and ods.cbdiv_id in (" + e_cuenta + ") "
                + " group by oe.embarque_agrupador, oe.embarque_id,oe.embarque_fec_captura "
                + " order by oe.embarque_id";
        return sql;
    }

    public String embarqueDetalleMod(String e_cuenta, String e, String fec1, String fec2) {
        sql = "SELECT d.d_docto_sal_id, d.d_docto_referencia,d.cue_cuenta_nombre,d.des_destino_nombre,"
                + " d.ciu_ciudad_nombre,oe.estado_nombre,TO_CHAR(d.d_docto_fec_captura,'dd/mm/yyyy'),"
                + " d.d_docto_piezas, d.d_docto_cajas"
                + " FROM ontms_vw_reporte_doc d"
                + " INNER JOIN ontms_estado oe ON oe.estado_id=d.ciu_estado_id"
                + " INNER JOIN ontms_tipo_destino  ot ON ot.tipo_destino_id=d.ord_tipo_destino_id"
                + " WHERE 1=1 AND d.d_docto_estado_id IN (" + e + ") AND to_date(d_docto_fec_captura) BETWEEN '" + fec1 + "' AND '" + fec2 + "'"
                + " AND d.d_cbdiv_id IN (" + e_cuenta + ") "
                + " GROUP BY d.d_docto_sal_id,TO_CHAR(d.d_docto_fec_captura,'dd/mm/yyyy'),d.d_docto_referencia,d.cue_cuenta_nombre,"
                + " d.des_destino_nombre,d.ciu_ciudad_nombre,oe.estado_nombre,d.d_docto_piezas, d.d_docto_cajas";
        return sql;
    }

    public String recibirDoc(String e_cuenta, String tipo, String e) {
        sql = "SELECT d.d_docto_sal_id, d.d_docto_referencia,d.cue_cuenta_nombre,d.des_destino_nombre,"
                + " d.ciu_ciudad_nombre,oe.estado_nombre,to_char(d.d_docto_fec_captura,'dd/mm/yyyy'),"
                + " d.d_docto_piezas, d.d_docto_cajas"
                + " FROM ontms_vw_reporte_doc d"
                + " INNER JOIN ontms_estado oe ON oe.estado_id=d.ciu_estado_id"
                + " INNER JOIN ontms_tipo_destino  ot ON ot.tipo_destino_id=d.ord_tipo_destino_id"
                + " WHERE 1=1 AND ((d.d_docto_estado_id IN (" + e + ")) OR (d.ob1_bodega_identificador=2"
                + " AND d.d_docto_estado_id IN (22))) AND ot.tipo_destino_lof IN (" + tipo + ")"
                /*
                 * + " AND d.d_cbdiv_id IN ("+e_cuenta+") "
                 */
                + " GROUP BY d.d_docto_sal_id, d.d_docto_referencia,d.cue_cuenta_nombre,to_char(d.d_docto_fec_captura,'dd/mm/yyyy'),"
                + " d.des_destino_nombre,d.ciu_ciudad_nombre,oe.estado_nombre,d.d_docto_piezas, d.d_docto_cajas";
        return sql;
    }
//**

    public String embarqueContar(String e_cuenta, String tipo, String e) {
        sql = "SELECT count(DISTINCT ods.docto_referencia)"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                //+ " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                //+ " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN DILOG_DESTINOS_HILTI DDH ON DDH.DESTINO_SHIP_TO =ods.destino_id AND DDH.division_id = ods.CBDIV_ID"
                + " INNER JOIN DILOG_ORIG_DEST_PAQ ord ON ord.id_origen = ob.zona_id AND ord.id_destino = ddh.destino_cp"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id"
                //+ " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id"
                //+ " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id"
                //+ " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
                + " WHERE 1=1 AND ods.docto_estado_id in (" + e + ")";
        if(!tipo.equals("'Mixto'")){
                sql += " AND otd.tipo_destino_lof in(" + tipo + ")";
                        }
                sql += " AND ods.cbdiv_id in (" + e_cuenta + ") ";
        return sql;
    }

    public String ContarPlaneaciones(String e_cuenta, String tipo, String e) {
        sql = "select count(*) "
                + " from ontms_embarque oe "
                + " inner join ontms_docto_sal ods on oe.embarque_agrupador=ods.docto_sal_agrupador "
                + " where embarque_estado_id=8 and embarque_id like 'PL%' and ods.cbdiv_id in (" + e_cuenta + ")";
        return sql;
    }

    public String EmbarqueContarPla(String tipo, String cuenta, String bo, String ruta) {
        sql = "Select count(*) "
                + "FROM ontms_docto_sal ods "
                + "INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id "
                + "INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id "
                + "INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id "
                + "INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id "
                + "INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id "
                + "INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id "
                + "INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id "
                + "INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id "
                + "LEFT JOIN ontms_bodega ob2 ON ob2.bodega_id= ord.bodega_id "
                + "LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id "
                + "INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id "
                + "INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id "
                + "INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id "
                + "INNER JOIN ontms_rutas ru ON ru.ciudad_id=od.ciudad_id "
                + "INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id "
                + "WHERE 1=1 AND ods.docto_estado_id in(0) ";
        if(!tipo.equals("'Mixto'")){
                sql+= " AND otd.tipo_destino_lof in(" + tipo + ") ";
                        }
                sql+= "AND oc.cuenta_id in (" + cuenta + ") "
                + "AND ob.bodega_id=" + bo + " and ru.ruta_nombre LIKE '%" + ruta + "%'";
        return sql;
    }

//pendiente
    public String embarqueDetalleSeleccONado() {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia,oc.cliente_nombre,"
                + " ode.destino_nombre, oci.ciudad_nombre,oe.estado_nombre,"
                + " to_char(ods.docto_fec_captura,'dd/mm/yyyy'),NVL(to_char(ods.docto_piezas),' '),NVL(to_char(ods.docto_cajas),' '),ods.cbdiv_id"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_cliente oc ON ods.cliente_id=oc.cliente_id"
                + " INNER JOIN ontms_destino ode ON ode.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_colonia onc ON ode.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON onc.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id ";
        return sql;
    }
///

    public String embarqueDocumentosAceptados(String sal_id, String cvecuenta, String t, String e) {
        sql = "SELECT ods.docto_sal_id ,ods.docto_referencia,oc.cliente_nombre,"
                + " ode.destino_nombre, oci.ciudad_nombre,oe.estado_nombre,to_char(ods.docto_fec_captura,'dd/mm/yyyy hh:mi'),"
                + " NVL(to_char(ods.docto_piezas),' '),NVL(to_char(ods.docto_cajas),' '),"
                + " (SELECT NVL(sum(DOCTO_SKU_CANTIDAD),0) FROM ONTMS_DOCTO_SKU  WHERE docto_sal_id=ods.docto_sal_id)"
                + " FROM " + t + " ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_cliente oc ON ods.cliente_id=oc.cliente_id"
                + " INNER JOIN ontms_destino ode ON ode.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ood ON ood.id_origen=ob.zona_id AND ood.id_destino=ode.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_colonia onc ON ode.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON onc.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
                + " WHERE 1=1 AND docto_estado_id = " + e + " AND ods.docto_sal_agrupador='" + sal_id + "' AND ods.cbdiv_id in(" + cvecuenta + ")"
                + " GROUP BY ods.docto_sal_id ,ods.docto_referencia,oc.cliente_nombre,"
                + " ode.destino_nombre, oci.ciudad_nombre,oe.estado_nombre, ods.docto_fec_captura,"
                + " ods.docto_piezas,ods.docto_cajas ORDER BY oci.ciudad_nombre,oe.estado_nombre,ods.docto_referencia";
        return sql;
    }
//**

    public String embarqueDocumentosAceptados(String sal_id, String edo) {
        sql = "  SELECT d.d_docto_sal_id,d.d_docto_referencia,d.d_cliente_id,"
                + " d.des_destino_nombre,d.ciu_ciudad_nombre,oe.estado_nombre,"
                + " to_char(d.d_docto_fec_captura,'dd/mm/yyyy hh:mi'),d.d_docto_piezas, d.d_docto_cajas"
                + " FROM ontms_vw_reporte_doc d"
                + " INNER JOIN ontms_estado oe ON oe.estado_id=d.ciu_estado_id"
                + " INNER JOIN ontms_cliente oc on oc.cliente_id=d.d_cliente_id"
                + " WHERE 1=1 AND d.d_docto_estado_id IN (" + edo + ")"
                + " AND d.d_docto_sal_agrupador='" + sal_id + "' GROUP BY d.d_docto_sal_id,"
                + " d.d_docto_referencia,d.d_cliente_id,d.des_destino_nombre,"
                + " d.ciu_ciudad_nombre,oe.estado_nombre,to_char(d.d_docto_fec_captura,'dd/mm/yyyy hh:mi')"
                + " ,d.d_docto_piezas, d.d_docto_cajas ORDER BY d.ciu_ciudad_nombre,oe.estado_nombre,d.d_docto_referencia";
        return sql;
    }

    public String validaRechazo(String id) {
        sql = "SELECT docto_sal_cantidad FROM ontms_docto_sal WHERE docto_sal_id='" + id + "' AND docto_estado_id=2";
        return sql;
    }

    public String trakingGeneralLista(String documento, String cveCuenta, String e) {
        sql = "SELECT ohds.cbdiv_id,docto_referencia, oc.cuenta_nombre,ob.bodega_nombre, odv.division_nombre, ohds.docto_sal_id"
                + " FROM ontms_docto_sal ohds"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ohds.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ohds.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_cliente oc ON oc.cliente_id=ohds.cliente_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ood ON ood.id_origen=ob.zona_id AND ood.id_destino=od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ohds.bodega_id"
                + " INNER JOIN ontms_tipo_docto otd ON otd.tipo_docto_id=ohds.tipo_docto_id"
                + " WHERE upper(docto_referencia) in ('" + documento + "')"
                + " AND ohds.cbdiv_id in (" + cveCuenta + ") " + e + ""
                + " GROUP BY ohds.cbdiv_id,docto_referencia, oc.cuenta_nombre,ob.bodega_nombre, odv.division_nombre,ohds.docto_sal_id"
                + " ORDER BY oc.cuenta_nombre";

        return sql;
    }

    public String trakingGeneral(String documento) {
        sql = "SELECT d.d_docto_referencia,to_char(d.d_docto_fec_captura,'dd/mm/yyyy hh:mi'),"
                + " d.d_docto_piezas,oc.cliente_nombre,oc.cliente_direccion,d.des_destino_nombre,"
                + " des_destino_nombre,ot.tipo_docto_desc,d.d_docto_estado_id,d_docto_folio_control,"
                + " d.d_docto_cajas, d.d_docto_pallets,d.d_docto_colgados,d.d_docto_contenedor,"
                + " d.d_docto_atados,d.d_docto_bulks,d.d_docto_importe,d.d_docto_peso,d.d_docto_volumen"
                + " FROM ontms_vw_reporte_doc d"
                + " INNER JOIN ontms_estado oe ON oe.estado_id=d.ciu_estado_id"
                + " INNER JOIN ontms_cliente oc ON oc.cliente_id=d.d_cliente_id"
                + " INNER JOIN ontms_tipo_docto ot ON ot.tipo_docto_id=d.d_tipo_docto_id"
                + " WHERE 1=1 AND d.d_docto_sal_id ='" + documento + "' GROUP BY d.d_docto_referencia,"
                + " to_char(d.d_docto_fec_captura,'dd/mm/yyyy hh:mi'),d.d_docto_piezas,oc.cliente_nombre,"
                + " oc.cliente_direccion,d.des_destino_nombre,des_destino_nombre,ot.tipo_docto_desc,"
                + " d.d_docto_estado_id,d_docto_folio_control,d.d_docto_cajas, d.d_docto_pallets,"
                + " d.d_docto_colgados,d.d_docto_contenedor,d.d_docto_atados,"
                + " d.d_docto_bulks,d.d_docto_importe,d.d_docto_peso,d.d_docto_volumen";

        return sql;
    }

    public String trakingDocumentoEmbarque(String documento, String tabla) {
        sql = "SELECT to_char(embarque_fec_captura,'dd/mm/yyyy'),"
                + " camion_placas, camion_modelo, utransporte_desc,chofer_nombre,oe.embarque_id, oe.embarque_agrupador,oe.embarque_id"
                + " FROM " + tabla + " ohds"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=ohds.docto_sal_agrupador"
                + " INNER JOIN ontms_camion oc ON oc.camion_id=oe.camion_id"
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " INNER JOIN ontms_unidad_transporte outr ON outr.utransporte_id=oc.utransporte_id"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ohds.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd.sucliente_id=ohds.destino_id"
                + " INNER JOIN ontms_destino ode ON ode.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ood ON ood.id_origen=ocbd.zona_id AND ood.id_destino=ode.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ohds.bodega_id"
                + " WHERE ohds.docto_sal_id in ('" + documento + "'"
                + " AND docto_estado_id=2 GROUP BY"
                + " to_char(embarque_fec_captura,'dd/mm/yyyy'),"
                + " camion_placas, camion_modelo, utransporte_desc,chofer_nombre,oe.embarque_id, oe.embarque_agrupador,oe.embarque_id";
        return sql;
    }

    public String trackingRechazo(String documento, String tabla) {
        sql = "SELECT docto_rechazo,docto_fec_rechazo FROM " + tabla + ""
                + " WHERE docto_sal_id in ('" + documento + "') AND (docto_estado_id=10 or docto_estado_id=11) ";
        return sql;
    }

    public String trakingDocumentoEvidencia(String documento, String tabla) {
        sql = "SELECT docto_folio_evidencia,NVL(TO_CHAR(TO_DATE(docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' '), NVL(docto_observaciones,' ')"
                + " FROM " + tabla + ""
                + " WHERE docto_sal_id in ('" + documento + "')"
                + " AND (docto_estado_id=7 or docto_estado_id=8)";
        return sql;
    }

    public String cabeceroPdf(String agrupador) {
        sql =  ""
                + " SELECT"
                + " e.embarque_id, \n" +
" to_char(e.embarque_fec_captura,' dd/mm/yyyy hh:mi'),\n" +
" e.embarque_agrupador, \n" +
" nvl(e.camion_id,'n/a'), \n" +
"  nvl(e.camion_id,'n/a'), \n" +
" 'cam.camion_modelo', \n" +
" 'cam.color_id', \n" +
" 'col.color_desc',\n" +
" 'cam.utransporte_id', \n" +
"  nvl(ut.utransporte_desc,'n/a'), \n" +
" ' cam.ltransporte_id', \n" +
" nvl(lt.ltransporte_nombre,'n/a'), " +
" 'cam.tipo_caja_id', \n" +
" 'tc.tipo_caja_desc', \n" +
" nvl(e.chofer_id,'n/a'),\n" +
" ' ', \n" +
" e.embarque_costo_real,\n" +
" NVL(e.embarque_auditor,'n/a'), \n" +
" nvl(lt.ltransporte_direccion,'n/a')\n" +
" FROM tra_inb_embarque e  \n" +
" LEFT JOIN ontms_unidad_transporte ut ON ut.utransporte_id=e.utransporte_id\n" +
" LEFT JOIN tra_inb_linea_transporte lt ON lt.ltransporte_id=e.EMBARQUE_TRANSPORTISTA\n" +
" WHERE e.embarque_agrupador='"+agrupador+"'" ;
        System.out.println("sql"+sql);
        return sql;
    
    }

    public String cabeceroPdfPa(String agrupador) {
        sql = "SELECT embarque_paq,to_char(embarque_paq_captura,'dd/mm/yyyy hh:mi'),embarque_paq_auditor,op.paqueteria,"
                + " chofer,camion,embarque_paq_agrupador,embarque_paq_costo_real FROM ontms_embarque_paqueteria oep"
                + " INNER JOIN ontms_paqueterias op ON op.paqueteria_id=oep.paqueteria_id"
                + " WHERE embarque_paq_agrupador='" + agrupador + "'";
        return sql;
    }

    public String detallePdf(String agrupador, String ti) {

              sql = "    SELECT \n" +
                    "    tie.id_evento, \n" +
                    "    gtn.container1,\n" +
                    "    gtn.bl_awb_pro,\n" +
                    "    gtn.shipment_id,\n" +
                    "    gtn.load_type_final,\n" +
                    "    gtn.QUANTITY,\n" +
                    "    tibd.nombre_bd,\n" +
                    "    nvl(tid.division_nombre, ' '), \n" +
                    "    tip1.nombre_pod,  \n" +
                    "    to_char(gtn.est_departure_pol, 'MM/DD/YY')   AS est_departure_pol, \n" +
                    "    nvl(to_char(gtn.eta_plus2, 'MM/DD/YY'), ' ') AS eta_dc\n" +
                    "   ,'1','2','3','4','5','6','7','8','9' " +
                    " FROM\n" +
                    " tra_inc_gtn_test gtn\n" +
                    "              \n" +
                    "    left JOIN tra_inb_evento        tie  ON gtn.plantilla_id = tie.plantilla_id\n" +
                    "    LEFT JOIN tra_inb_pod             tip1 ON tip1.id_pod = gtn.pod\n" +
                    "    LEFT JOIN tra_inb_pol             tip2 ON tip2.id_pol = gtn.pol\n" +
                    "    LEFT JOIN tra_inb_brand_division  tibd ON tibd.id_bd = gtn.brand_division\n" +
                    "    LEFT JOIN tra_inb_agente_aduanal  taa  ON taa.agente_aduanal_id = tip1.agente_aduanal_id\n" +
                    "    LEFT JOIN tra_inb_division        tid  ON tid.id_division = gtn.sbu_name\n" +
                    " \n" +
                    " WHERE\n" +
                    "       EMBARQUE_AGRUPADOR='"+agrupador+"'\n" +
                    " ORDER BY\n" +
                    "    tie.id_evento"
                  + "  ";

        System.out.println("sql   "+sql);
        return sql;
    }
//***
      public String detallePdfTrans(String agrupador, String ti) {

        sql = " SELECT ods.docto_referencia, "
                + " OCD.destino_nombre, "
                + " OCD.DESTINO_CIUDAD, "
                + " OCD.DESTINO_ESTADO, "
                + " NVL(ods.docto_volumen,0), "
                + " NVL(ods.docto_peso,0), "
                + " NVL(TO_CHAR(ods.docto_fec_captura,'dd/mm/yyyy'),' '), "
                + " NVL(TO_CHAR(ods.docto_fec_programacion,'dd/mm/yyyy'),' '), "
                + " NVL(ods.docto_piezas,0), "
                + " NVL(ods.docto_cajas,0), "
                + " NVL(ods.docto_pallets,0), "
                + " NVL(ods.docto_colgados,0), "
                + " NVL(ods.docto_contenedor,0), "
                + " NVL(ods.docto_atados,0), "
                + " NVL(ods.docto_bulks,0), "
                + " oc.cuenta_nombre,"
                + " tc.TRA_CAJA" 
                + " FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_cta_bod_div ocbd "
                + " ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " left JOIN ontms_bodega ob "
                + " ON ob.bodega_id = ocbd.bodega_id "
                + " left JOIN ontms_cuenta oc "
                + " ON oc.cuenta_id = ocbd.cuenta_id "
                + " left JOIN ontms_division odv "
                + " ON odv.division_id = ocbd.division_id "
                + " left JOIN dilog_destinos_hilti ocd "
                + " ON ocd.DESTINO_SHIP_TO =ods.destino_id "
                + " LEFT JOIN ontms_bodega ob1 "
                + " ON ob1.bodega_id=ods.bodega_id "
                + " LEFT JOIN tra_cajas tc "
                + " on tc.TRA_CAJA_PEDIDO= ods.DOCTO_REFERENCIA"
                + " WHERE 1                      =1 "
                + " AND ods.docto_sal_agrupador IN ('" + agrupador + "') "
                + " GROUP BY oc.cuenta_nombre, "
                + "   ods.docto_referencia, "
                + "   OCD.destino_nombre, "
                + "   OCD.DESTINO_CIUDAD, "
                + "   OCD.DESTINO_ESTADO, "
                + "   ods.docto_volumen, "
                + "  ods.docto_peso, "
                + "  ods.docto_fec_captura, "
                + "  ods.docto_fec_programacion, "
                + "  ods.docto_piezas, "
                + "  ods.docto_cajas, "
                + "  ods.docto_pallets, "
                + "  ods.docto_colgados, "
                + "  ods.docto_contenedor, "
                + "  ods.docto_atados, "
                + "  ods.docto_bulks,"
                + "  tc.TRA_CAJA "
                + " ORDER BY ods.docto_referencia ";

        /*
         sql = "SELECT ods.docto_referencia,ode.destino_nombre,"
         + " oci.ciudad_nombre,oe.estado_nombre,"
         + " NVL(ods.docto_volumen,0), NVL(ods.docto_peso,0), NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy'),' '),"
         + " NVL(to_char(ods.docto_fec_programacion,'dd/mm/yyyy'),' '),"
         + " NVL(ods.docto_piezas,0),NVL(ods.docto_cajas,0),NVL(ods.docto_pallets,0),"
         + " NVL(ods.docto_colgados,0),NVL(ods.docto_contenedor,0),NVL(ods.docto_atados,0),"
         + " NVL(ods.docto_bulks,0),oc.cuenta_nombre"
         + " FROM ontms_docto_sal ods"
         + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
         + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
         + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
         + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
         + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
         + " INNER JOIN ontms_cliente oc ON ods.cliente_id=oc.cliente_id"
         + " INNER JOIN ontms_destino ode ON ode.destino_id = ocd.destino_id"
         + " INNER JOIN ontms_origen_destino ood ON ood.id_origen=ob.zona_id AND ood.id_destino=ode.ciudad_id"
         + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
         + " INNER JOIN ontms_colonia onc ON ode.colonia_id=onc.colonia_id"
         + " INNER JOIN ontms_ciudad oci ON onc.ciudad_id=oci.ciudad_id"
         + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
         + " WHERE 1=1 AND ods.docto_sal_agrupador in ('" + agrupador + "')"
         + " GROUP BY oc.cuenta_nombre,ods.docto_referencia,ode.destino_nombre,"
         + " oci.ciudad_nombre,oe.estado_nombre,"
         + " ods.docto_volumen, ods.docto_peso,ods.docto_fec_captura,"
         + " ods.docto_fec_programacion,ods.docto_piezas,ods.docto_cajas,ods.docto_pallets,"
         + " ods.docto_colgados,ods.docto_contenedor,ods.docto_atados,ods.docto_bulks"
         + " ORDER BY ods.docto_referencia";
         */
        return sql;
    }
//***

    public String detallePdfRe(String agrupador) {
        sql = " SELECT d.d_docto_referencia,d.des_destino_nombre,d.ciu_ciudad_nombre,oe.estado_nombre,"
                + " d.d_docto_volumen,d.d_docto_peso,to_char(d.d_docto_fec_captura,'dd/mm/yyyy hh:mi'),"
                + " d.d_docto_fec_programacion,d.d_docto_piezas,d.d_docto_cajas, d.d_docto_pallets,"
                + " d.d_docto_colgados,d.d_docto_contenedor,d.d_docto_atados,d.d_docto_bulks,d.cue_cuenta_nombre"
                + " FROM ontms_vw_reporte_doc d"
                + " INNER JOIN ontms_estado oe ON oe.estado_id=d.ciu_estado_id"
                + " WHERE 1=1 AND d.d_docto_sal_agrupador ='" + agrupador + "'"
                + " GROUP BY d.d_docto_referencia,d.des_destino_nombre,d.ciu_ciudad_nombre,oe.estado_nombre,"
                + " d.d_docto_volumen,d.d_docto_peso,to_char(d.d_docto_fec_captura,'dd/mm/yyyy hh:mi'),"
                + " d.d_docto_fec_programacion,d.d_docto_piezas,d.d_docto_cajas, d.d_docto_pallets,"
                + " d.d_docto_colgados,d.d_docto_contenedor,d.d_docto_atados,"
                + " d.d_docto_bulks,d.cue_cuenta_nombre"
                + " ORDER BY d.d_docto_referencia";
        return sql;
    }
///verificar

    public String servicioCliente(String embarque) {
        sql = "SELECT embarque_id, to_char(embarque_fec_captura,'dd/mm/yyyy hh:mi'),"
                + " oltr.ltransporte_nombre,outr.utransporte_desc,oc.chofer_nombre"
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_chofer oc ON oc.chofer_id=oe.chofer_id"
                + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id"
                + " INNER JOIN ontms_linea_transporte oltr ON oltr.ltransporte_id=oca.ltransporte_id"
                + " INNER JOIN ontms_unidad_transporte outr ON outr.utransporte_id=oca.utransporte_id"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " WHERE oe.EMBARQUE_AGRUPADOR='" + embarque + "'"
                + " GROUP BY embarque_id, embarque_fec_captura,"
                + " oltr.ltransporte_nombre,outr.utransporte_desc,oc.chofer_nombre";
        return sql;
    }

    public String detalleSal(String sal_id) {
        sql = "SELECT oum.umedida_desc ,det_sal_cantidad"
                + " FROM ontms_det_sal ods"
                + " INNER JOIN ontms_unidad_medida oum ON oum.umedida_id=ods.umedida_id"
                + " WHERE docto_sal_id='" + sal_id + "'"
                + " ORDER BY oum.umedida_id";
        return sql;
    }

    public String capturaDocumentoUnidadMedida(String cveCuenta) {
        sql = "SELECT oum.umedida_id, oum.umedida_desc, oc.cuenta_nombre, ob.bodega_nombre,od.division_nombre"
                + " FROM ontms_um_div oud"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id = oud.cbdiv_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_division od ON od.division_id = ocbd.division_id"
                + " INNER JOIN ontms_unidad_medida oum ON oum.umedida_id = oud.umedida_id"
                + " WHERE 1=1 AND ocbd.cbdiv_id in (" + cveCuenta + ")"
                + " ORDER BY   oum.umedida_id";
        return sql;
    }
//***

    public String detalleDocumentosEmbarcados(String agrupador, String division, String e) {
        sql = "SELECT ods.docto_referencia,"
                + "  NVL(TO_CHAR(ods.docto_piezas),' '),"
                + "  oddh.DESTINO_NOMBRE,"
                + "  oddh.DESTINO_CIUDAD,"
                + "  oddh.DESTINO_ESTADO,"
                + "  ods.docto_sal_id,"
                + "  oe.embarque_agrupador,"
                + "  oe.embarque_id,"
                + "  ocbd.DIVISION_ID,"
                + "  ods.CBDIV_ID,"
                + "  NVL(TO_CHAR(ods.docto_cajas),' '),"
                + "  NVL(TO_CHAR(ods.docto_pallets),' '),"
                + "  NVL(TO_CHAR(ods.docto_colgados),' '),"
                + "  NVL(TO_CHAR(ods.docto_contenedor),' '),"
                + "  NVL(TO_CHAR(ods.docto_atados),' '),"
                + "  NVL(TO_CHAR(ods.docto_bulks),' ')"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_embarque oe"
                + " ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd"
                + " ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob"
                + " ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN dilog_destinos_hilti oddh"
                + " ON oddh.DESTINO_SHIP_TO = ods.destino_id"
                + " AND oddh.division_id    = ods.CBDIV_ID"
                + " INNER JOIN ontms_cuenta oc"
                + " ON oc.cuenta_id = ocbd.cuenta_id"
                + ""
                + " INNER JOIN DILOG_ORIG_DEST_PAQ ood"
                + " ON ood.ID_ORIGEN  =ob.zona_id"
                + " AND ood.IATA_CP_DESTINO=oddh.DESTINO_CP"
                + " LEFT JOIN ontms_bodega ob1"
                + " ON ob1.bodega_id             =ods.bodega_id"
                + " WHERE oe.embarque_agrupador IN ('" + agrupador + "') AND ocbd.division_id in (" + division + ") " + e + " "
                + " GROUP BY ods.docto_cajas,\n"
                + "  ods.docto_pallets,"
                + "  ods.docto_colgados,"
                + "  ods.docto_contenedor,"
                + "  ods.docto_atados,"
                + "  ods.docto_bulks,"
                + "  ods.docto_referencia,"
                + "  ods.docto_piezas,"
                + "  oddh.DESTINO_NOMBRE,"
                + "  oddh.DESTINO_CIUDAD,"
                + "  oddh.DESTINO_ESTADO,"
                + "  ods.docto_sal_id,"
                + "  oe.embarque_agrupador,"
                + "  oe.embarque_id,"
                + "  ocbd.division_id,"
                + "  ods.cbdiv_id"
                + " ORDER BY ods.docto_referencia";
        return sql;
    }

    public String documentosEstadoIngresadoTracking(String division,String documento) {
         sql = "SELECT ods.docto_referencia,"
                + "  NVL(TO_CHAR(ods.docto_piezas),' '),"
                + "  oddh.DESTINO_NOMBRE,"
                + "  oddh.DESTINO_CIUDAD,"
                + "  oddh.DESTINO_ESTADO,"
                + "  ods.docto_sal_id,"
                + "  '',"
                + "  '',"
                + "  ocbd.DIVISION_ID,"
                + "  ods.CBDIV_ID,"
                + "  NVL(TO_CHAR(ods.docto_cajas),' '),"
                + "  NVL(TO_CHAR(ods.docto_pallets),' '),"
                + "  NVL(TO_CHAR(ods.docto_colgados),' '),"
                + "  NVL(TO_CHAR(ods.docto_contenedor),' '),"
                + "  NVL(TO_CHAR(ods.docto_atados),' '),"
                + "  NVL(TO_CHAR(ods.docto_bulks),' ')"
                + " FROM ontms_docto_sal ods"
                + ""
                + " INNER JOIN ontms_cta_bod_div ocbd"
                + " ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob"
                + " ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN dilog_destinos_hilti oddh"
                + " ON oddh.DESTINO_SHIP_TO = ods.destino_id"
                + " AND oddh.division_id    = ods.CBDIV_ID"
                + " INNER JOIN ontms_cuenta oc"
                + " ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN DILOG_ORIG_DEST_PAQ ood"
                + " ON ood.ID_ORIGEN       =ob.zona_id"
                + " AND ood.IATA_CP_DESTINO=oddh.DESTINO_CP"
                + " LEFT JOIN ontms_bodega ob1"
                + " ON ob1.bodega_id             =ods.bodega_id"
                + " WHERE  ocbd.division_id        IN ("+division+")"
                + " AND ods.docto_estado_id     =0"
                 + " AND ODS.DOCTO_REFERENCIA=UPPER('" + documento + "') "
                + " GROUP BY ods.docto_cajas,"
                + "  ods.docto_pallets,"
                + "  ods.docto_colgados,"
                + "  ods.docto_contenedor,"
                + "  ods.docto_atados,"
                + "  ods.docto_bulks,"
                + "  ods.docto_referencia,"
                + "  ods.docto_piezas,"
                + "  oddh.DESTINO_NOMBRE,"
                + "  oddh.DESTINO_CIUDAD,"
                + "  oddh.DESTINO_ESTADO,"
                + "  ods.docto_sal_id,"
                + "  '',"
                + "  '',"
                + "  ocbd.division_id,"
                + "  ods.cbdiv_id"
                + " ORDER BY ods.docto_referencia";

        return sql;

    }

//***
    public String detalleDocumentosEmbarcadosCancelar(String id_doc, String cveCuenta, String division) {

        sql = "SELECT ods.docto_referencia, NVL(to_char(ods.docto_piezas),' '), od.destino_nombre,oc.ciudad_nombre, oes.estado_nombre,ods.docto_sal_id,ods.docto_sal_agrupador"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_embarque oe ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ood ON ood.id_origen=ob.zona_id AND ood.id_destino=od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_ciudad oc ON oc.ciudad_id=od.ciudad_id"
                + " INNER JOIN ontms_estado oes ON oes.estado_id=oc.estado_id"
                + " WHERE ods.docto_sal_id IN ('" + id_doc + "')"
                + " AND ods.cbdiv_id in (" + cveCuenta + ") AND ods.docto_estado_id=2"
                + " GROUP BY ods.docto_referencia, ods.docto_piezas, od.destino_nombre,oc.ciudad_nombre, oes.estado_nombre,ods.docto_sal_id,ods.docto_sal_agrupador"
                + " ORDER BY ods.docto_referencia";

        return sql;
    }

    public String historicoEmbarque(String id_embarque, String agrupador) {
        sql = "INSERT INTO ontms_hist_embarque SELECT oe.*,SYSDATE FROM ontms_embarque oe WHERE oe.embarque_id='" + id_embarque + "' AND oe.embarque_agrupador='" + agrupador + "'";
        return sql;
    }

    public String trakingDocumentoObservaciones(String agrupador) {
        sql = "SELECT NVL(EMBARQUE_OBSERVACIONES,' '), to_char(FECHA,'dd/mm/yyyy hh:mi') FROM ONTMS_HIST_EMBARQUE WHERE EMBARQUE_AGRUPADOR='" + agrupador + "' ORDER BY fecha";
        return sql;
    }

    public String trakingDocumentoObservacionesD(String agrupador) {
        sql = "SELECT observacion,to_char(FECHA,'dd/mm/yyyy hh:mi') FROM ontms_docto_observaciones WHERE docto_sal_id='" + agrupador + "' ORDER BY fecha";
        return sql;
    }

    public String conversiondestino(String cveCuenta, String division) {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia, ods.destino_id"
                + " FROM ontms_docto_Sal ods"
                + " WHERE ((not ods.destino_id in(SELECT sucliente_id FROM ontms_conversion_destino WHERE division_id IN (" + division + "))) or ods.cliente_id is null)"
                + " AND ods.cbdiv_id in(" + cveCuenta + ")"
                + " GROUP BY ods.docto_referencia, ods.destino_id,ods.docto_sal_id ORDER BY ods.docto_referencia";
        return sql;
    }

    public String conversiondestinohilti(String cveCuenta, String division) {
        sql = " SELECT ods.docto_sal_id, "
                + " ods.docto_referencia, "
                + " ods.destino_id "
                + " FROM ontms_docto_Sal ods "
                + " WHERE ( ods.destino_id NOT IN "
                + "   (SELECT DESTINO_SHIP_TO FROM dilog_destinos_hilti  "
                + "   )) "
                + " AND ods.cbdiv_id  IN(" + cveCuenta + ") "
                + " GROUP BY ods.docto_referencia, "
                + "   ods.destino_id, "
                + "   ods.docto_sal_id "
                + " ORDER BY ods.DESTINO_ID ";
        return sql;
    }

    public String conversionDestinoModificar(String id_doc, String division, String cve) {
        sql = "SELECT ods.docto_sal_id ,ods.docto_referencia, ods.destino_id,ods.cliente_id "
                + " FROM ontms_docto_Sal ods"
                + " WHERE ((not ods.destino_id in(SELECT sucliente_id FROM ontms_conversion_destino WHERE division_id in (" + division + ")))"
                + " OR cliente_id is null)"
                + " AND ods.cbdiv_id in(" + cve + ") AND ods.docto_sal_id in ('" + id_doc + "')"
                + " GROUP BY ods.docto_referencia, ods.destino_id,ods.docto_sal_id,ods.cliente_id ";
        return sql;
    }

    public String servicioClienteLista(String embarque, String cve) {
        sql = "SELECT oe.oe_embarque_id,to_char(oe.oe_embarque_fec_captura,'dd/mm/yyyy'),"
                + " NVL(oe.oc_chofer_nombre,' '),NVL(oe.olt_ltransporte_nombre,' '),oe.oe_embarque_agrupador"
                + " FROM vw_consulta_em oe"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id = ods.cbdiv_id"
                + " WHERE upper(oe.oe_embarque_id) IN ('" + embarque + "') AND ods.cbdiv_id in(" + cve + ")"
                + " GROUP BY oe.oe_embarque_id,to_char(oe.oe_embarque_fec_captura,'dd/mm/yyyy'),"
                + " NVL(oe.oc_chofer_nombre,' '),NVL(oe.olt_ltransporte_nombre,' '),oe.oe_embarque_agrupador";
        return sql;
    }

    public String servicioClienteListaHis(String embarque, String cve) {
        sql = "SELECT oe.oe_embarque_id,to_char(oe.oe_embarque_fec_captura,'dd/mm/yyyy'),"
                + " NVL(oe.oc_chofer_nombre,' '),NVL(oe.olt_ltransporte_nombre,' '),oe.oe_embarque_agrupador"
                + " FROM vw_consulta_em oe"
                + " INNER JOIN ontms_hist_docto_sal ods ON ods.docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id = ods.cbdiv_id"
                + " WHERE upper(oe.oe_embarque_id) IN ('" + embarque + "') AND ods.cbdiv_id in(" + cve + ")"
                + " GROUP BY oe.oe_embarque_id,to_char(oe.oe_embarque_fec_captura,'dd/mm/yyyy'),"
                + " NVL(oe.oc_chofer_nombre,' '),NVL(oe.olt_ltransporte_nombre,' '),oe.oe_embarque_agrupador";
        return sql;
    }

    public String detalleEvidenciaGuiaEmbarque(String agrupador) {

        sql = " SELECT ods.docto_referencia, "
                + "  NVL(ods.docto_piezas,0), "
                + "  oddh.DESTINO_CIUDAD, "
                + "  oddh.DESTINO_NOMBRE, "
                + "  oddh.DESTINO_ESTADO, "
                + "  ods.docto_sal_id, "
                + "  oe.embarque_agrupador, "
                + "  oe.embarque_id, "
                + "  oddh.DIVISION_ID, "
                + "  ods.CBDIV_ID, "
                + "  SUM(NVL(odsk.cantidad_rechazada,0)), "
                + "  NVL(ods.docto_sal_cantidad,0) "
                + "FROM ontms_docto_sal ods "
                + "INNER JOIN ontms_embarque oe ON ods.docto_sal_agrupador=oe.embarque_agrupador "
                + "INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id "
                + "INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id "
                + "INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id "
                + "INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id "
                + "inner join dilog_destinos_hilti oddh  on oddh.DESTINO_SHIP_TO = ods.destino_id "
                + " and oddh.division_id = ods.CBDIV_ID  "
                + "LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id "
                + "LEFT JOIN ontms_docto_sku odsk ON odsk.docto_sal_id         =ods.docto_sal_id "
                + "WHERE oe.embarque_agrupador IN ('" + agrupador + "') "
                + "AND ods.docto_estado_id     IN (2,10) "
                + "AND oe.embarque_estado_id   <>4 "
                + "GROUP BY ods.docto_referencia, "
                + "  NVL(ods.docto_piezas,0), "
                + "  oddh.DESTINO_CIUDAD, "
                + "  oddh.DESTINO_NOMBRE, "
                + "  oddh.DESTINO_ESTADO, "
                + "  ods.docto_sal_id, "
                + "  oe.embarque_agrupador, "
                + "  oe.embarque_id, "
                + "  oddh.DIVISION_ID, "
                + "  ods.CBDIV_ID, "
                + "  NVL(ods.docto_sal_cantidad,0) "
                + "ORDER BY ods.docto_referencia ";

        /* sql = " SELECT ods.docto_referencia,NVL(ods.docto_piezas,0),od.destino_nombre,oc.ciudad_nombre, oes.estado_nombre,ods.docto_sal_id, oe.embarque_agrupador,oe.embarque_id,ocd.division_id,ods.CBDIV_ID,sum(NVL(odsk.cantidad_rechazada,0)),"
         + " NVL(ods.docto_sal_cantidad,0)"
         + " FROM ontms_docto_sal ods"
         + " INNER JOIN ontms_embarque oe ON ods.docto_sal_agrupador=oe.embarque_agrupador"
         + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
         + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
         + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
         + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
         + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
         + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
         + " INNER JOIN ontms_origen_destino ood ON ood.id_origen=ob.zona_id AND ood.id_destino=od.ciudad_id"
         + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
         + " INNER JOIN ontms_ciudad oc ON oc.ciudad_id=od.ciudad_id"
         + " INNER JOIN ontms_estado oes ON oes.estado_id=oc.estado_id"
         + " LEFT JOIN ontms_docto_sku odsk ON odsk.docto_sal_id=ods.docto_sal_id"
         + " WHERE oe.embarque_agrupador IN ('" + agrupador + "')"
         + " AND ods.docto_estado_id IN (2,10)  AND oe.embarque_estado_id<>4"
         + " GROUP BY ods.docto_referencia,ods.docto_piezas,od.destino_nombre,oc.ciudad_nombre, oes.estado_nombre,ods.docto_sal_id,oe.embarque_agrupador,oe.embarque_id,ocd.division_id,ods.cbdiv_id,"
         + " ods.docto_sal_cantidad ORDER BY ods.docto_referencia";
         */
        return sql;
    }

    public String obserEmbarque(String a) {
        sql = " SELECT nvl(EMBARQUE_OBSERVACIONES,' ') FROM tra_inb_embarque  where  embarque_agrupador='"+a+"'  " ;
        return sql;
    }

    public String obserEmPaq(String a) {
        sql = "SELECT oh.embarque_observaciones FROM ontms_hist_embarque oh, ontms_embarque_paqueteria oe"
                + " WHERE oh.embarque_agrupador=oe.embarque_paq_agrupador"
                + " AND oh.embarque_agrupador='" + a + "'"
                + " ORDER BY oh.fecha";
        return sql;
    }

    public String destinoEmbarque(String a, String b) {
        sql = "SELECT  COUNT(DISTINCT ode.destino_nombre)"
                + " FROM ontms" + b + "docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino ode ON ocd.destino_id=ode.destino_id"
                + " WHERE 1=1 AND ods.docto_sal_agrupador in ('" + a + "')";
        return sql;
    }

    public String trackingAutorizaciones(String id) {
        sql = "SELECT oa.autorizacion_id, oa.clave_autorizacion,oa.autorizacion_costo, oa.docto_sal_id,oa.gtransp_id,oa.autoriza,ogt.gtransp_desc "
                + " FROM ontms_autorizaciones oa, ontms_docto_sal ods, ontms_gastos_transporte ogt "
                + " WHERE ods.docto_sal_id=oa.docto_sal_id "
                + " AND oa.gtransp_id=ogt.gtransp_id"
                + " AND ods.docto_sal_id='" + id + "'"
                + " GROUP BY oa.autorizacion_id,oa.clave_autorizacion,oa.autorizacion_costo,oa.docto_sal_id,oa.gtransp_id,oa.autoriza,ogt.gtransp_desc"
                + " ORDER BY oa.clave_autorizacion";
        return sql;
    }

    public String seguimientoDetalle1(String cveDocto, String division) {
        sql = "SELECT d.d_docto_referencia,d.d_docto_piezas,d.des_destino_nombre,d.ciu_ciudad_nombre,"
                + " oe.estado_nombre,d.d_docto_sal_id,op.oe_embarque_agrupador,op.oe_embarque_id,d.div_division_nombre,"
                + " d.d_cbdiv_id,d.d_docto_cajas,d.d_docto_pallets,d.d_docto_colgados,d.d_docto_contenedor,"
                + " d.d_docto_atados,d.d_docto_bulks,op.oe_embarque_fec_captura,op.olt_ltransporte_nombre,"
                + " op.outr_utransporte_desc,op.oc_chofer_nombre,d.d_docto_estado_id,"
                + " edo.edo_desc,  NVL(TO_CHAR(TO_DATE(d.d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ')      ,d.d_docto_peso,d.ods_docto_concentrado,d.d_docto_pedido"
                + " FROM ontms_vw_reporte_doc d"
                + " INNER JOIN ontms_estado oe ON oe.estado_id=d.ciu_estado_id"
                + " INNER JOIN vw_pq_em op ON op.oe_embarque_agrupador=d.d_docto_sal_agrupador"
                + " INNER JOIN app_estado edo ON d.d_docto_estado_id=edo.edo_valor AND edo_tabla='ONTMS_DOCTO_SAL'"
                + " WHERE op.oe_embarque_agrupador ='" + cveDocto + "'";
        return sql;
    }

    public String seguimAutoGral() {
        sql = "SELECT gtransp_id, gtransp_desc FROM ontms_gastos_transporte WHERE gtransp_extra1=1 ORDER BY gtransp_desc";
        return sql;
    }

    public String seguimientoAutoriza(String idDocto) {
        sql = "SELECT ogt.gtransp_desc,oa.clave_autorizacion,oa.autorizacion_costo,oa.autoriza,oa.docto_sal_id,oa.gtransp_id"
                + " FROM ontms_autorizaciones oa,ontms_docto_sal ods,ontms_gastos_transporte ogt"
                + " WHERE oa.docto_sal_id=ods.docto_sal_id"
                + " AND oa.gtransp_id=ogt.gtransp_id"
                + " AND oa.docto_sal_id='" + idDocto + "'"
                + " ORDER BY ogt.gtransp_desc";
        return sql;
    }
//***

    public String consultaEmbarqueCon(String e_cuenta, String tipo, String e, String t, String folio) {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia, oc.cuenta_nombre, od.destino_nombre, oci.ciudad_nombre,oe.estado_nombre, "
                + " NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy'),' '), NVL(to_char(ods.docto_piezas),' '), NVL(to_char(ods.docto_cajas),' '), "
                + " ods.bodega_id, ob1.bodega_nombre, ods.docto_sal_agrupador FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id "
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id "
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id  "
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id "
                + " LEFT JOIN ontms_bodega ob2 ON ob2.bodega_id=ord.bodega_id"
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id "
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id "
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id "
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id "
                + " INNER JOIN ontms_embarque oem ON oem.embarque_agrupador=ods.docto_sal_agrupador"
                + " WHERE 1=1 AND ods.docto_estado_id in(" + e + ") AND otd.tipo_destino_lof in(" + tipo + ")"
                + " AND ods.cbdiv_id in (" + e_cuenta + ") AND oem.embarque_id='" + folio + "' "
                + " AND ob1.bodega_identificador=1"
                + " GROUP BY ods.docto_sal_id, ods.docto_referencia, oc.cuenta_nombre, od.destino_nombre, oci.ciudad_nombre, oe.estado_nombre, "
                + " NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy'),' '), NVL(to_char(ods.docto_piezas),' '), "
                + " NVL(to_char(ods.docto_cajas),' '), ods.bodega_id, ob1.bodega_nombre, ods.bodega_id, ods.docto_sal_agrupador"
                + " ORDER BY oci.ciudad_nombre,oe.estado_nombre,ods.docto_referencia";
        return sql;
    }

    public String embarqueContarC(String e_cuenta, String tipo, String e, String t, String bo) {
        sql = "SELECT COUNT (DISTINCT ods.docto_referencia)"
                + " FROM " + t + " ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id "
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id"
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
                + " WHERE 1=1 AND ods.docto_estado_id in (" + e + ") "
                + " AND otd.tipo_destino_lof in(" + tipo + ")"
                + " AND ods.cbdiv_id in (" + e_cuenta + ") " + bo + "";
        return sql;
    }
///verificar
//****

    public String embarqueDetalleSelecconado(String ta) {
        sql = "SELECT ods.docto_sal_id,"
                + "  ods.docto_referencia,"
                + "  oc.cuenta_nombre,"
                + "  oc.DESTINO_NOMBRE,"
                + "  oc.DESTINO_CIUDAD,"
                + "  oc.DESTINO_ESTADO,"
                + "  TO_CHAR(ods.docto_fec_captura,'dd/mm/yyyy'),"
                + "  NVL(TO_CHAR(ods.docto_piezas),' '),"
                + "  NVL(TO_CHAR(ods.docto_cajas),' '),"
                + "  ods.cbdiv_id,"
                + "  (SELECT NVL(SUM(docto_sku_cantidad),0)"
                + "  FROM ontms_docto_sku"
                + "  WHERE docto_sal_id=ods.docto_sal_id"
                + "  )"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd"
                + " ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob"
                + " ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc"
                + " ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv"
                + " ON odv.division_id = ocbd.division_id"
                + " INNER JOIN dilog_destinos_hilti oc"
                + " ON ods.destino_id=oc.destino_ship_to"
                + " AND ods.cbdiv_id = oc.division_id ";
        return sql;
    }

    public String embarqueDetalleSeleccionado(String ta) {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia,"
                + " NVL(trim(to_char(ods.docto_fec_factura,'dd/mm/yyyy')),' '), "
                + " NVL(trim(to_char(ods.docto_fec_captura,'dd/mm/yyyy')),' '), "
                + " NVL(trim(to_char(ods.docto_fec_programacion,'dd/mm/yyyy')),' '),"
                + "  ocli.cliente_nombre, "
                + "  od.destino_nombre, "
                + "  oci.ciudad_nombre, "
                + "  oe.estado_nombre, "
                + " OTMR.ruta_nombre,"
                + " NVL(ods.docto_piezas,0),"
                + " NVL(ods.docto_cajas,0),"
                + " NVL(ods.docto_pallets,0),"
                + " NVL(ods.docto_peso,0),"
                + " NVL(ods.docto_volumen,0),"
                + " ods.cbdiv_id,"
                + " (SELECT NVL(sum(docto_sku_cantidad),0)"
                + " FROM ontms_docto_sku"
                + " WHERE docto_sal_id=ods.docto_sal_id)"
                + " FROM " + ta + " ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob2 ON ob2.bodega_id= ord.bodega_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id "
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id"
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_ruta_ciudad ru ON ru.ciudad_id=od.ciudad_id "
                + " INNER JOIN ONTMS_RUTAS OTMR ON  OTMR.RUTA_ID = RU.RUTA_ID "
                + " INNER JOIN ontms_estado oe   ON oci.estado_id        =oe.estado_id  ";
        return sql;
    }

    public String embarquePlaneacionContar(String e_cuenta, String tipo, String bo, String ruta) {
        sql = " SELECT count(ods.docto_sal_id)   "
                + " FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id "
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id "
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id "
                + " LEFT JOIN ontms_bodega ob2 ON ob2.bodega_id= ord.bodega_id "
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id  "
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id "
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id "
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id "
                + " INNER JOIN ontms_ruta_ciudad rcu ON rcu.ciudad_id=od.ciudad_id "
                + " INNER JOIN ontms_rutas ru ON rcu.ruta_id =ru.ruta_id   "
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id "
                + " WHERE 1=1 AND ods.docto_estado_id in(0)"//+ " AND otd.tipo_destino_lof in('" + tipo + "')"
                + " AND oc.cuenta_id in (" + e_cuenta + ") AND ob.bodega_id='" + bo + "' and rcu.RUTA_ID in (" + ruta + ")"
                + " GROUP BY ods.docto_sal_id,ods.docto_referencia,oc.cuenta_nombre,od.destino_nombre,"
                + " oci.ciudad_nombre,oe.estado_nombre,ru.ruta_nombre,ods.docto_fec_captura,ods.docto_piezas,ods.docto_cajas,ods.docto_pallets,ods.docto_peso,ods.docto_volumen, ord.bodega_id, ob1.bodega_nombre, ods.docto_sal_agrupador,ods.docto_fec_factura,ods.docto_fec_programacion "
                + " ORDER BY  ru.ruta_nombre,oci.ciudad_nombre,oe.estado_nombre,ods.docto_referencia ";
        return sql;
    }

    public String embarquePlaneacionConts(String e_cuenta, String tipo, String bo, String ruta) {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia,oc.cuenta_nombre,"
                + " od.destino_nombre, oci.ciudad_nombre,oe.estado_nombre,"
                + " ru.ruta_nombre,"
                + " NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy'),' '),"
                + " NVL(to_char(ods.docto_piezas),'0'),NVL(to_char(ods.docto_cajas),'0'),"
                + " NVL(to_char(ods.docto_pallets),'0'),NVL(to_char(ods.docto_peso),'0.00'),NVL(to_char(ods.docto_volumen),'0.00'),"
                + " ord.bodega_id, ob1.bodega_nombre,ods.docto_sal_agrupador,"
                + " NVL(to_char(ods.docto_fec_factura,'dd/mm/yyyy'),' '), "
                + " NVL(to_char(ods.docto_fec_programacion,'dd/mm/yyyy'),' ') "
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id "
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id "
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id "
                + " LEFT JOIN ontms_bodega ob2 ON ob2.bodega_id= ord.bodega_id "
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id  "
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id "
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id "
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id "
                + " INNER JOIN ontms_ruta_ciudad rcu ON rcu.ciudad_id=od.ciudad_id "
                + " INNER JOIN ontms_rutas ru ON rcu.ruta_id =ru.ruta_id   "
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id "
                + " WHERE 1=1 AND ods.docto_estado_id in(0)" //+ " AND otd.tipo_destino_lof in('" + tipo + "')"
                + " AND oc.cuenta_id in (" + e_cuenta + ") AND ob.bodega_id='" + bo + "' and rcu.RUTA_ID in (" + ruta + ")"
                + " GROUP BY ods.docto_sal_id,ods.docto_referencia,oc.cuenta_nombre,od.destino_nombre,"
                + " oci.ciudad_nombre,oe.estado_nombre,ru.ruta_nombre,ods.docto_fec_captura,ods.docto_piezas,ods.docto_cajas,ods.docto_pallets,ods.docto_peso,ods.docto_volumen, ord.bodega_id, ob1.bodega_nombre, ods.docto_sal_agrupador,ods.docto_fec_factura,ods.docto_fec_programacion "
                + " ORDER BY  ru.ruta_nombre,oci.ciudad_nombre,oe.estado_nombre,ods.docto_referencia ";
        return sql;
    }

    public String embarqueDetalleC(String e_cuenta, String tipo, String e, String t, String bo, String ruta) {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia,oc.cuenta_nombre,"
                + " od.destino_nombre, oci.ciudad_nombre,oe.estado_nombre,"
                + " OTMR.ruta_nombre,"
                + " NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy'),' '),"
                + " NVL(to_char(ods.docto_piezas),' '),NVL(to_char(ods.docto_cajas),' '),"
                + " NVL(to_char(ods.docto_pallets),' '),NVL(to_char(ods.docto_peso),'0.00'),NVL(to_char(ods.docto_volumen),'0.00'),"
                + " ord.bodega_id, ob1.bodega_nombre,ods.docto_sal_agrupador,"
                + " NVL(to_char(ods.docto_fec_factura,'dd/mm/yyyy'),' '), "
                + " NVL(to_char(ods.docto_fec_programacion,'dd/mm/yyyy'),' ') "
                + " FROM " + t + " ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob2 ON ob2.bodega_id= ord.bodega_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id "
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id"
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_ruta_ciudad ru ON ru.ciudad_id=od.ciudad_id "
                + " INNER JOIN ONTMS_RUTAS OTMR ON  OTMR.RUTA_ID = RU.RUTA_ID "
                + " INNER JOIN ontms_estado oe   ON oci.estado_id        =oe.estado_id  "
                + " WHERE 1=1 AND ods.docto_estado_id = " + e
                + " AND oc.cuenta_id in (" + e_cuenta + ") AND ob.bodega_id='" + bo + "' and ru.RUTA_ID in (" + ruta + ") "
                + " GROUP BY ods.docto_sal_id,ods.docto_referencia, "
                + " oc.cuenta_nombre,  od.destino_nombre,  oci.ciudad_nombre,  oe.estado_nombre,  OTMR.ruta_nombre, "
                + " ods.docto_fec_captura,  ods.docto_piezas,  ods.docto_cajas,  ods.docto_pallets,  ods.docto_peso, "
                + " ods.docto_volumen,  ord.bodega_id,  ob1.bodega_nombre,  ods.docto_sal_agrupador,  ods.docto_fec_factura, "
                + " ods.docto_fec_programacion ORDER BY OTMR.ruta_nombre,   oci.ciudad_nombre,   oe.estado_nombre,   ods.docto_referencia";
        return sql;
    }

    public String embarqueDetalleD(String e_cuenta, String tipo, String e, String t, String bo) {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia, oc.cuenta_nombre,"
                + " od.destino_nombre, oci.ciudad_nombre,oe.estado_nombre,"
                + " NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy'),' '),"
                + " NVL(to_char(ods.docto_piezas),' '),NVL(to_char(ods.docto_cajas),' '),"
                + " ord.bodega_id, ob1.bodega_nombre,ods.docto_sal_agrupador "
                + " FROM " + t + " ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob2 ON ob2.bodega_id= ord.bodega_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id "
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id"
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
                + " WHERE 1=1 AND ods.docto_estado_id in(" + e + ")"
                + " AND otd.tipo_destino_lof in(" + tipo + ")"
                /*
                 * + " AND ods.cbdiv_id in (" + e_cuenta + ")"
                 */
                + " " + bo + ""
                + " GROUP BY ods.docto_sal_id,ods.docto_referencia, oc.cuenta_nombre,od.destino_nombre,"
                + " oci.ciudad_nombre,oe.estado_nombre,ods.docto_fec_captura,ods.docto_piezas,ods.docto_cajas, ord.bodega_id, ob1.bodega_nombre, ods.docto_sal_agrupador "
                + " ORDER BY  oci.ciudad_nombre,oe.estado_nombre,ods.docto_referencia ";
        return sql;
    }
//***

    public String embarqueDetalleBod(String e_cuenta, String tipo, String e, String t) {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia, oc.cuenta_nombre,"
                + " od.destino_nombre, oci.ciudad_nombre,oe.estado_nombre,"
                + " NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy'),' '),"
                + " NVL(to_char(ods.docto_piezas),' '),NVL(to_char(ods.docto_cajas),' '),"
                + " ord.bodega_id, ob1.bodega_nombre,ods.docto_sal_agrupador "
                + " FROM " + t + " ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id "
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id"
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
                + " WHERE 1=1 AND ods.docto_estado_id in(" + e + ")"
                + " AND otd.tipo_destino_lof in(" + tipo + ")"
                + " AND ods.cbdiv_id in (" + e_cuenta + ") "
                + " GROUP BY ods.docto_sal_id,ods.docto_referencia, oc.cuenta_nombre,od.destino_nombre,"
                + " oci.ciudad_nombre,oe.estado_nombre,ods.docto_fec_captura,ods.docto_piezas,ods.docto_cajas, ord.bodega_id, ob1.bodega_nombre, ods.docto_sal_agrupador "
                + " ORDER BY  oci.ciudad_nombre,oe.estado_nombre,ods.docto_referencia ";
        return sql;
    }
//***

    public String embarqueDocumentosAceptados(String sal_id, String division, String cvecuenta, String t, String e) {
        /*      sql = " SELECT ods.docto_referencia, " +
         " ddh.destino_nombre, " +
         " ddh.destino_ciudad, " +
         " ddh.destino_estado, " +
         " ods.docto_fec_captura, " +
         " ods.docto_piezas, " +
         " ods.docto_cajas, " +
         " ods.docto_peso, " +
         " ods.DOCTO_IMP_TOTAL " +
         " FROM ontms_docto_sal ods " +
         " INNER JOIN dilog_destinos_hilti ddh " +
         " ON ods.destino_id=ddh.DESTINO_SHIP_TO " +
         " INNER JOIN dilog_orig_dest_paq dodp " +
         " ON ddh.destino_cp=dodp.iata_cp_destino " +
         " INNER JOIN ontms_tipo_destino OTD " +
         " ON dodp.tipo_destino_id    =otd.tipo_destino_id " +
         " WHERE ODS.docto_estado_id IN (1) " +
         " AND ODS.cbdiv_id   in(" + cvecuenta + ")" +
         " AND ods.docto_sal_agrupador      IN('" + sal_id + "') ";
      
         */
        sql = "SELECT ods.docto_sal_id ,ods.docto_referencia,oc.cliente_nombre,"
                + " ode.destino_nombre, oci.ciudad_nombre,oe.estado_nombre,to_char(ods.docto_fec_captura,'dd/mm/yyyy hh:mi'),"
                + " NVL(to_char(ods.docto_piezas),' '),NVL(to_char(ods.docto_cajas),' '),"
                + " (SELECT NVL(sum(docto_sku_cantidad),0) FROM ontms_docto_sku WHERE docto_sal_id=ods.docto_sal_id)"
                + " FROM " + t + " ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_cliente oc ON ods.cliente_id=oc.cliente_id"
                + " INNER JOIN ontms_destino ode ON ode.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ood ON ood.id_origen=ob.zona_id AND ood.id_destino=ode.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_colonia onc ON ode.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON onc.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
                + " WHERE 1=1 AND docto_estado_id = " + e + " AND ods.docto_sal_agrupador='" + sal_id + "' AND ods.cbdiv_id in(" + cvecuenta + ")"
                + " GROUP BY ods.docto_sal_id ,ods.docto_referencia,oc.cliente_nombre,"
                + " ode.destino_nombre, oci.ciudad_nombre,oe.estado_nombre, ods.docto_fec_captura,"
                + " ods.docto_piezas,ods.docto_cajas ORDER BY oci.ciudad_nombre,oe.estado_nombre,ods.docto_referencia";

        return sql;

    }

    public String consultaEmbarquePlaneacion(String e_cuenta, String tipo, String e, String t, String folio)//String e_cuenta, String tipo, String division, String e, String t
    {
        sql = "SELECT"
                + " ods.docto_sal_id,"
                + " ods.docto_referencia,"
                + " oc.cuenta_nombre,"
                + " od.destino_nombre,"
                + " oci.ciudad_nombre,"
                + " oe.estado_nombre,"
                + " ru.ruta_nombre,"
                + " NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy'),' '),"
                + " NVL(to_char(ods.docto_piezas),' '),"
                + " NVL(to_char(ods.docto_cajas),' '),"
                + " NVL(to_char(ods.docto_pallets),' '),"
                + " ods.docto_peso,ods.docto_volumen,"
                + " ord.bodega_id,"
                + " ob1.bodega_nombre,"
                + " ods.docto_sal_agrupador,"
                + " NVL(to_char(ods.docto_fec_factura,'dd/mm/yyyy'),' '), "
                + " NVL(to_char(ods.docto_fec_programacion,'dd/mm/yyyy'),' ')"
                + " FROM "
                + " ontms_docto_sal ods "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id "
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id "
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id "
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino=od.ciudad_id"
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id"
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id "
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id "
                + " INNER JOIN ontms_rutas ru ON ru.ciudad_id=od.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id "
                + " INNER JOIN ontms_embarque oem ON oem.embarque_agrupador=ods.docto_sal_agrupador"
                + " WHERE 1=1 AND ods.docto_estado_id in(" + e + ") AND otd.tipo_destino_lof in(" + tipo + ")"
                + " AND ods.cbdiv_id in (" + e_cuenta + ") AND oem.embarque_id='" + folio + "' "
                + " GROUP BY ods.docto_sal_id,ods.docto_referencia,oc.cuenta_nombre,od.destino_nombre, oci.ciudad_nombre,oe.estado_nombre,"
                + " ru.ruta_nombre,NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy'),' '),NVL(to_char(ods.docto_piezas),' '),NVL(to_char(ods.docto_cajas),' '),"
                + " NVL(to_char(ods.docto_pallets),' '),ods.docto_peso,ods.docto_volumen,ord.bodega_id, ob1.bodega_nombre,ods.docto_sal_agrupador,"
                + " NVL(to_char(ods.docto_fec_factura,'dd/mm/yyyy'),' '), NVL(to_char(ods.docto_fec_programacion,'dd/mm/yyyy'),' '),"
                + " ods.docto_sal_agrupador "
                + " ORDER BY oci.ciudad_nombre,oe.estado_nombre,ods.docto_referencia";
        return sql;
    }

    public String consultaEmbarqueN(String e_cuenta, String tipo, String division, String e, String t, String folio)//String e_cuenta, String tipo, String division, String e, String t
    {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia, oc.cuenta_nombre, od.destino_nombre, oci.ciudad_nombre,oe.estado_nombre, "
                + " NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy'),' '), NVL(to_char(ods.docto_piezas),' '), NVL(to_char(ods.docto_cajas),' '), "
                + " ods.bodega_id, ob1.bodega_nombre, ods.docto_sal_agrupador FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id "
                + " INNER JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id "
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id  "
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id "
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id ord.id_destino=od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob2 ob2.bodega_id=ord.bodega_id"
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id "
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id "
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id "
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id "
                + " INNER JOIN ontms_embarque oem ON oem.embarque_agrupador=ods.docto_sal_agrupador"
                + " WHERE 1=1 AND ods.docto_estado_id in(" + e + ") AND otd.tipo_destino_lof in(" + tipo + ")"
                + " AND ods.cbdiv_id in (" + e_cuenta + ") AND ocd.division_id in (" + division + ") AND oem.embarque_id='" + folio + "' "
                + " GROUP BY ods.docto_sal_id, ods.docto_referencia, oc.cuenta_nombre, od.destino_nombre, oci.ciudad_nombre, oe.estado_nombre, "
                + " NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy'),' '), NVL(to_char(ods.docto_piezas),' '), "
                + " NVL(to_char(ods.docto_cajas),' '), ods.bodega_id, ob1.bodega_nombre, ods.bodega_id, ods.docto_sal_agrupador"
                + " ORDER BY oci.ciudad_nombre,oe.estado_nombre,ods.docto_referencia";
        return sql;
    }
///***55

    public String consultaEmbarqueNBod(String e_cuenta, String tipo, String e, String folio)//String e_cuenta, String tipo, String division, String e, String t
    {

        sql = " select ods.docto_sal_id,ods.docto_referencia, "
                + " ddh.destino_nombre, "
                + " ddh.destino_ciudad, "
                + " ddh.destino_estado, "
                + " ods.docto_fec_captura, "
                + " ods.docto_piezas, "
                + " ods.docto_cajas, "
                + " ods.docto_peso"
                + " ods.DOCTO_IMP_TOTAL "
                + " from ontms_docto_sal ods "
                + " inner join dilog_destinos_hilti ddh "
                + " on ods.destino_id=ddh.DESTINO_SHIP_TO "
                + " and ddh.division_id = ods.CBDIV_ID "
                + " inner join dilog_orig_dest_paq dodp "
                + " on ddh.destino_cp=dodp.iata_cp_destino "
                + " INNER JOIN ontms_tipo_destino OTD "
                + " ON dodp.tipo_destino_id=otd.tipo_destino_id "
                + " where ODS.docto_estado_id=" + e + " "
                + " and ODS.cbdiv_id =" + e_cuenta + " "
                + " AND ods.docto_concentrado='" + folio + "' ";

        return sql;
        /*    sql = "SELECT d.d_docto_sal_id, d.d_docto_referencia,d.cue_cuenta_nombre,d.des_destino_nombre,"
         + " d.ciu_ciudad_nombre,oe.estado_nombre,TO_CHAR(d.d_docto_fec_captura,'dd/mm/yyyy'),"
         + " d.d_docto_piezas, d.d_docto_cajas"
         + " FROM ontms_vw_reporte_doc2 d"
         + " INNER JOIN ontms_estado oe ON oe.estado_id=d.ciu_estado_id"
         + " INNER JOIN ontms_tipo_destino  ot ON ot.tipo_destino_id=d.ord_tipo_destino_id"
         + " WHERE 1=1 AND d.d_docto_estado_id IN (" + e + ") AND ot.tipo_destino_lof IN (" + tipo + ")"
         + " AND d.d_cbdiv_id IN (" + e_cuenta + ") and d.d_docto_concentrado ='" + folio + "'"
         + " GROUP BY d.d_docto_sal_id,TO_CHAR(d.d_docto_fec_captura,'dd/mm/yyyy'),d.d_docto_referencia,d.cue_cuenta_nombre,"
         + " d.des_destino_nombre,d.ciu_ciudad_nombre,oe.estado_nombre,d.d_docto_piezas, d.d_docto_cajas";
         */

    }

    public String bodegaPlaneacion() {
        sql = "SELECT BODEGA_ID,  BODEGA_NOMBRE FROM ONTMS_BODEGA ORDER BY BODEGA_NOMBRE";
        return sql;
    }
//***

    public String embarqueContarBod(String e_cuenta, String tipo, String e, String t) {
        sql = "SELECT count(ods.docto_sal_id) "
                + " FROM " + t + " ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_cliente ocli ON ods.cliente_id = ocli.cliente_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " INNER JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id "
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id"
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
                + " WHERE 1=1 AND ods.docto_estado_id in(" + e + ")"
                + " AND otd.tipo_destino_lof in(" + tipo + ")"
                + " AND ods.cbdiv_id in (" + e_cuenta + ") AND  ob1.bodega_identificador=1 ";
        return sql;
    }
    //***nan

    public String cargosEncabezado(String documento) {
        sql = "select embarque_id,embarque_fec_captura,olt.ltransporte_nombre, oc.chofer_nombre from ontms_embarque oe"
                + " inner join ontms_chofer oc on oc.chofer_id=oe.chofer_id"
                + " inner join ontms_camion oca on oca.camion_id=oe.camion_id"
                + " inner join ontms_linea_transporte olt on olt.ltransporte_id=oca.ltransporte_id"
                + " where embarque_agrupador='" + documento + "'";
        return sql;
    }

    public String causasCargos() {
        sql = "SELECT rechazo_id,ocr.rechazo_desc"
                + " FROM ontms_causa_rechazo ocr"
                + " INNER JOIN ontms_responsable ore ON ore.responsable_id=ocr.responsable_id"
                + " WHERE ore.responsable_id=4";
        return sql;
    }

    public String cargoEmbarqueDocumentos(String documento) {
        sql = "SELECT ods.docto_referencia,NVL(ods.docto_piezas,0),od.destino_nombre,oc.ciudad_nombre, "
                + " oes.estado_nombre,ods.docto_sal_id, oe.embarque_agrupador,oe.embarque_id,ocd.division_id,ods.cbdiv_id"
                + " FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_embarque oe ON ods.docto_sal_agrupador=oe.embarque_agrupador "
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id "
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id "
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id "
                + " INNER JOIN ontms_ciudad oc ON oc.ciudad_id=od.ciudad_id "
                + " INNER JOIN ontms_estado oes ON oes.estado_id=oc.estado_id"
                + " LEFT JOIN ontms_docto_sku odsk ON odsk.docto_sal_id=ods.docto_sal_id"
                + " WHERE oe.embarque_agrupador IN ('" + documento + "') "
                + " AND (ods.docto_estado_id=2 OR ods.docto_estado_id=10) "
                + " GROUP BY ods.docto_referencia, NVL(ods.docto_piezas,0), od.destino_nombre, oc.ciudad_nombre, oes.estado_nombre, ods.docto_sal_id,"
                + " oe.embarque_agrupador, oe.embarque_id, ocd.division_id, ods.cbdiv_id "
                + " ORDER BY ods.docto_referencia";
        return sql;
    }

    public String cargoIn(String agrupador) {
        sql = "SELECT cargo_docto_id,costo,docto_sal_agrupador FROM ontms_docto_sal_cargos"
                + " WHERE docto_sal_agrupador='" + agrupador + "' ";
        return sql;
    }

    public String conVal(String documento, String rechazo) {
        sql = "SELECT embarque_agrupador,rechazo_id FROM ontms_cargos WHERE embarque_agrupador='" + documento + "' "
                + " GROUP BY embarque_agrupador,rechazo_id";
        return sql;
    }

    public String cons(String cveDocto) {
        sql = "SELECT ods.docto_sal_id,  ods.docto_referencia, osku.producto_sku, osku.docto_sku_id, op.producto_desc "
                + " FROM ontms_docto_sal ods, ontms_docto_sku osku, ontms_producto op"
                + " WHERE ods.docto_sal_id=osku.docto_sal_id"
                + " AND osku.producto_sku=op.producto_sku"
                + " AND ods.docto_sal_id='" + cveDocto + "'";
        return sql;
    }

    public String compara(String agrupador) {
        sql = "SELECT odsc.COSTO FROM ONTMS_DOCTO_SAL_CARGOS odsc, ontms_docto_sku osku, ontms_producto op"
                + " WHERE odsc.docto_sku_id=osku.docto_sku_id"
                + " AND osku.producto_sku=op.producto_sku"
                + " AND odsc.docto_sal_agrupador='" + agrupador + "'";
        return sql;
    }

    public String productos(String sku, String agrupador) {
        sql = "SELECT docto_sal_agrupador, producto_sku "
                + " FROM ontms_docto_sal_cargos WHERE producto_sku='" + sku + "' "
                + " AND docto_sal_agrupador='" + agrupador + "'"
                + " GROUP BY docto_sal_agrupador, producto_sku";
        return sql;
    }

    public String comparaAgrupador(String agrupador) {
        sql = "SELECT odsc.COSTO FROM ONTMS_DOCTO_SAL_CARGOS odsc, ontms_docto_sku osku, ontms_producto op"
                + " WHERE odsc.producto_sku=osku.producto_sku "
                + " AND osku.producto_sku=op.producto_sku"
                + " AND odsc.docto_sal_agrupador='" + agrupador + "'";
        return sql;
    }

    public String muestraProd(String docto_id) {
        sql = "SELECT odsc.COSTO,odsc.DOCTO_SAL_AGRUPADOR, op.producto_desc"
                + " FROM ONTMS_DOCTO_SAL_CARGOS odsc, ontms_docto_sku osku, ontms_producto op "
                + " WHERE osku.producto_sku=op.producto_sku AND osku.producto_sku=odsc.producto_sku "
                + " AND osku.docto_sal_id='" + docto_id + "'"
                + " GROUP BY odsc.COSTO,odsc.DOCTO_SAL_AGRUPADOR, op.producto_desc";
        return sql;
    }

    public String prorrateo(String agrupador, String cveCuenta) {
        sql = "SELECT em.embarque_id, em.embarque_fec_captura, em.embarque_agrupador, oc.chofer_nombre, "
                + " oca.camion_placas, olt.ltransporte_nombre, ods.docto_sal_agrupador, em.embarque_costo_real"
                + " FROM ontms_embarque em, ontms_chofer oc, ontms_camion oca,ontms_linea_transporte olt, ontms_docto_sal ods"
                + " WHERE em.chofer_id=oc.chofer_id "
                + " AND em.camion_id=oca.camion_id "
                + " AND olt.ltransporte_id=oca.ltransporte_id "
                + " AND em.embarque_agrupador=ods.docto_sal_agrupador "
                + " and ods.cbdiv_id in (" + cveCuenta + ") "
                + " AND em.embarque_agrupador='" + agrupador + "' "
                + " GROUP BY em.embarque_id, em.embarque_fec_captura, em.embarque_agrupador, oc.chofer_nombre, "
                + " oca.camion_placas, olt.ltransporte_nombre, ods.docto_sal_agrupador, em.embarque_costo_real";
        return sql;
    }

    public String prorrateo2(String embarque, String cve) {
        sql = "SELECT oe.oe_embarque_id,oe.oe_embarque_fec_captura,oe.oc_chofer_nombre,"
                + " oe.olt_ltransporte_nombre,oe.oe_embarque_agrupador"
                + " FROM vw_pq_em oe"
                + " WHERE NOT EXISTS (SELECT docto_sal_agrupador FROM ontms_docto_sal"
                + " WHERE docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " AND docto_estado_id  IN (21,22,24,25) AND cbdiv_id IN (" + cve + "))"
                + " AND oe.oe_embarque_id ='" + embarque + "'  AND oe.oe_embarque_costo_real<>0"
                + " GROUP BY oe.oe_embarque_id,oe.oe_embarque_fec_captura,oe.oc_chofer_nombre,"
                + " oe.olt_ltransporte_nombre,oe.oe_embarque_agrupador";
        return sql;
    }

    public String doctosProrrateo(String agrupador) {
        sql = "SELECT count(ocb.cuenta_id),oc.cuenta_nombre,sum(NVL(ods.docto_piezas,0)),sum(NVL(ods.docto_cajas,0)),"
                + " sum(NVL(ods.docto_atados,0)),sum(NVL(ods.docto_bulks,0)),"
                + " sum(NVL(ods.docto_colgados,0)), sum(NVL(ods.docto_contenedor,0)),sum(NVL(ods.docto_importe,0)),"
                + " sum(NVL(ods.docto_peso,0)),sum(NVL(ods.docto_volumen,0)),sum(NVL(ods.docto_pallets,0)),ocb.cuenta_id"
                + " FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_cta_bod_div ocb on ocb.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_cuenta oc on oc.cuenta_id=ocb.cuenta_id where ods.docto_sal_agrupador='" + agrupador + "' "
                + " GROUP BY oc.cuenta_nombre,ocb.cuenta_id";
        return sql;
    }

    public String consultaPaq(String[] id) {
        sql = "SELECT ov.op_embarque_paq,ov.op_embarque_paq_captura,ov.op_embarque_paq_agrupador"
                + " FROM ontms_vw_paqueteria ov"
                + " WHERE ov.op_embarque_paq_agrupador='" + id[0] + "'"
                + " UNION"
                + " SELECT ov.op_embarque_paq,ov.op_embarque_paq_captura,ov.op_embarque_paq_agrupador"
                + " FROM ontms_vw_paqueteria ov"
                + " INNER JOIN ontms_hist_docto_Sal ods ON ods.docto_sal_agrupador=ov.op_embarque_paq_agrupador"
                + " WHERE ods.cbdiv_id in (" + id[2] + ") and ods.docto_sal_id='" + id[1] + "'"
                + " ORDER BY 2";
        return sql;
    }

    public String detallePa(String[] id) {
        sql = "select op_embarque_paq, op_embarque_paq_captura,opa_paqueteria,op_camion,op_chofer"
                + " FROM ontms_vw_paqueteria"
                + " WHERE op_embarque_paq_agrupador='" + id[0] + "'";
        return sql;
    }

    public String detallePaDoc(String[] id) {
        sql = "SELECT ods.docto_referencia,NVL(TO_CHAR(ods.docto_piezas),' '),od.destino_nombre,"
                + " oc.ciudad_nombre,oes.estado_nombre,ods.docto_sal_id,oe.embarque_paq_agrupador"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_embarque_paqueteria oe ON oe.embarque_paq_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_cta_bod_div oc ON oc.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_division od ON od.division_id=oc.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id =ods.destino_id  AND ocd.division_id = od.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_ciudad oc ON oc.ciudad_id=od.ciudad_id"
                + " INNER JOIN ontms_estado oes ON oes.estado_id=oc.estado_id"
                + " WHERE " + id[1] + "='" + id[0] + "' and ods.docto_estado_id=2";
        return sql;
    }
    
    
    
    
    
        public String CustodiaTipo(String agrupador){
        sql=" SELECT OE.EMBARQUE_TCUSTODIA,TTC.TC_DESCRIPCION, OE.NOMBRE_CUSTODIO, OE.NOMBRE_CUSTODIO2 FROM"
                + " tra_inb_embarque OE" 
                + " LEFT JOIN tra_inb_tipo_custodia TTC ON TTC.ID_TC = OE.EMBARQUE_TCUSTODIA" 
                + " WHERE OE.EMBARQUE_AGRUPADOR ='"+agrupador+"'";
        
        return sql;
        
    }
}
