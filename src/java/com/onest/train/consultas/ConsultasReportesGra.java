/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

/**
 *
 * @author Teresa Martin
 */
public class ConsultasReportesGra {

    String sql = "";

    public String paqueteria() {
        sql = "SELECT paqueteria_id, paqueteria FROM ontms_paqueterias ORDER BY paqueteria";
        return sql;
    }

    public String viajesPaqueteria(String[] id) {
        sql = "SELECT op_embarque_paq_captura,op_embarque_paq,destino_nombre,op_embarque_paq_costo_real,"
                + " ods_docto_piezas,ods_docto_cajas,ods_docto_pallets,ods_docto_colgados,ods_docto_contenedor,"
                + " ods_docto_atados,ods_docto_bulks,ods_docto_importe,ods_docto_peso,ods_docto_volumen,op_chofer,"
                + " op_camion,opa_paqueteria FROM ontms_vw_paqueteria"
                + " WHERE ods_cbdiv_id in (" + id[0] + ") and"
                + " to_date(op_embarque_paq_captura) between '" + id[1] + "' AND '" + id[2] + "' " + id[3] + ""
                + "ORDER BY op_embarque_paq";
        return sql;
    }  
    public String rechazoPaq(String[] id) {
        sql = "SELECT ov.ods_docto_sal_id,ov.ods_docto_referencia,ov.oc_cuenta_nombre,ov.ob_bodega_nombre,ov.od_division_nombre FROM vw_consulta_doc ov"
                + " INNER JOIN ontms_embarque_paqueteria oep ON oep.embarque_paq_agrupador=ov.ods_docto_sal_agrupador"
                + " WHERE ov.ods_cbdiv_id IN (" + id[1] + ") AND ov.ods_docto_estado_id=" + id[2] + ""
                + " AND upper(ov.ods_docto_referencia) ='" + id[0] + "'";
        return sql;
    }

    public String rechazoPaq1(String[] id) {
        sql = "SELECT ov.ods_docto_sal_id,ov.ods_docto_referencia,otd.tipo_docto_desc,ov.ob_bodega_nombre,ov.ode_destino_nombre,"
                + " ov.od_division_nombre, nvl(ov.ods_docto_pedido,' '), nvl(to_char(ov.ods_docto_fec_captura,'dd/mm/yyyy'),' '),"
                + " ov.ods_docto_piezas,ov.ods_docto_importe, ov.ods_docto_peso, ov.ods_docto_volumen,"
                + " op.embarque_paq,op.embarque_paq_captura,op.camion,op.chofer,pa.paqueteria,nvl(to_char(ov.ods_docto_fec_programacion,'dd/mm/yyyy'),' ')"
                + " FROM vw_consulta_doc ov"
                + " INNER JOIN ontms_embarque_paqueteria op ON op.embarque_paq_agrupador=ov.ods_docto_sal_agrupador"
                + " INNER JOIN ontms_tipo_docto otd ON otd.tipo_docto_id=ov.ods_tipo_docto_id"
                + " INNER JOIN ontms_paqueterias pa ON pa.paqueteria_id=op.paqueteria_id"
                + " WHERE ov.ods_docto_sal_id='" + id[0] + "'";
        return sql;
    }

    public String fletepaq(String[] id) {
        sql = " SELECT op_embarque_paq,COUNT(ods.docto_sal_id),opa_paqueteria,op_chofer,"
                + " op_embarque_paq_costo_real,op_embarque_paq_agrupador,SUM(NVL(docto_piezas,0)),SUM(NVL(docto_cajas,0)),"
                + " SUM(NVL(docto_pallets,0)),SUM(NVL(docto_colgados,0)),SUM(NVL(docto_contenedor,0)),SUM(NVL(docto_atados,0)),"
                + " SUM(NVL(docto_bulks,0)),SUM(NVL(docto_importe,0)),SUM(NVL(docto_peso,0)),SUM(NVL(docto_volumen,0)),"
                + " ocu.cuenta_nombre FROM ontms_vw_paqueteria op"
                + " INNER JOIN ontms_docto_sal ods on ods.docto_sal_agrupador=op.op_embarque_paq_agrupador"
                + " INNER JOIN ontms_cta_bod_div oc ON oc.cbdiv_id=op.ods_cbdiv_id"
                + " INNER JOIN ontms_cuenta ocu on ocu.cuenta_id=oc.cuenta_id"
                + " WHERE ocu.cuenta_id IN (" + id[0] + ") AND to_date(op.op_embarque_paq_captura) BETWEEN '" + id[1] + "' "
                + " AND '" + id[2] + "' AND op.op_sap_migrado IS NULL " + id[3] + ""
                + " GROUP BY op_embarque_paq,opa_paqueteria,op_chofer,op_embarque_paq_costo_real,"
                + " op_embarque_paq_agrupador,ocu.cuenta_nombre ORDER BY op_embarque_paq";
        return sql;
    }

    public String paqueterias(String[] id) {
        sql = " SELECT ov.op_embarque_paq,ov.op_embarque_paq_captura,ov.op_embarque_paq_agrupador,"
                + " ov.opa_paqueteria,ov.op_camion,ov.op_chofer, ov.op_embarque_paq_estado_id,ov.op_embarque_paq_costo_real,"
                + " oc.cuenta_nombre"
                + " FROM ontms_vw_paqueteria ov"
                + " INNER JOIN ontms_docto_Sal ods ON ods.docto_sal_agrupador=ov.op_embarque_paq_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocb ON ocb.cbdiv_id=ods_cbdiv_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id=ocb.cuenta_id"
                + " WHERE oc.cuenta_id in ("+id[0]+") AND to_date(ov.op_embarque_paq_captura) between '"+id[1]+"'  AND '"+id[2]+"'"
                + " AND ov.op_embarque_paq_estado_id in ("+id[3]+") GROUP BY ov.op_embarque_paq,ov.op_embarque_paq_captura,ov.op_embarque_paq_agrupador,"
                + " ov.opa_paqueteria,ov.op_camion,ov.op_chofer, ov.op_embarque_paq_estado_id,ov.op_embarque_paq_costo_real,"
                + " oc.cuenta_nombre";
        return sql;
    }
    public String embarqueDocPaq(String fec1, String fec2, String cve) {
        sql="SELECT op_embarque_paq_captura,op_embarque_paq,ods_docto_piezas,ods_docto_cajas,ods_docto_pallets,ods_docto_colgados,"
            +" ods_docto_contenedor,ods_docto_atados,ods_docto_bulks,op_chofer,op_camion,op_embarque_paq_costo_real,"
            +" ods_docto_importe,ods_docto_peso,ods_docto_volumen, ods_cbdiv_id,op_paqueteria_id,"
            +" opa_paqueteria,destino_nombre,op_embarque_paq_estado_id, op_embarque_paq_agrupador"
            +" FROM ontms_vw_paqueteria"
            +" WHERE op_embarque_paq_captura BETWEEN '" + fec1 + "' AND '" + fec2 + "' "
            +" AND ods_cbdiv_id IN (" + cve + ")";
        return sql;
    }
    public String embarqueDocPaq(String agrupador)
    {
        sql = "select ods.docto_referencia,to_char(ods.docto_fec_captura,'dd/mm/yyyy'),"
                + " NVL(ods.docto_piezas,0),NVL(ods.docto_cajas,0),NVL(ods.docto_pallets,0),"
                + " NVL(ods.docto_colgados,0),NVL(ods.docto_contenedor,0),NVL(ods.docto_atados,0),"
                + " NVL(ods.docto_bulks,0),od.destino_nombre,oci.ciudad_nombre,oes.estado_nombre,"
                + " NVL(od.destino_ruta,' '),NVL(ods.docto_importe,0), NVL(ods.docto_peso,0),NVL(ods.docto_volumen,0)"
                + " ,NVL(to_char(ods.docto_fec_pedido,'dd/mm/yyyy'),' '),NVL(ods.docto_pedido,' ') "
                + " FROM ontms_docto_sal ods, ontms_destino od, ontms_conversion_destino ocd, "
                + " ontms_cta_bod_div ocbd, ontms_ciudad oci, ontms_estado oes"
                + " WHERE ods.docto_sal_agrupador='" + agrupador + "'"
                + " AND ocd.sucliente_id=ods.destino_id"
                + " AND ocd.destino_id=od.destino_id"
                + " AND oci.ciudad_id=od.ciudad_id"
                + " AND oci.estado_id=oes.estado_id"
                + " AND ocbd.cbdiv_id=ods.cbdiv_id"
                + " AND ocbd.division_id=ocd.division_id"
                + " GROUP BY ods.docto_pedido,ods.docto_fec_pedido,NVL(ods.docto_importe,0), NVL(ods.docto_peso,0),NVL(ods.docto_volumen,0),ods.docto_referencia,ods.docto_piezas,ods.docto_cajas,ods.docto_pallets,oci.ciudad_nombre,oes.estado_nombre,od.destino_ruta,"
                + " ods.docto_colgados,ods.docto_contenedor,ods.docto_atados,ods.docto_bulks,od.destino_nombre,ods.docto_fec_captura order by ods.docto_referencia";
        return sql;
    }
    public String status_paq(String fec1, String fec2, String cveCuenta)
    {
        sql="SELECT odi_division_nombre,ods_docto_referencia, ods_docto_fec_captura,  ods_docto_pedido,ods_docto_fec_pedido,ods_docto_programacion,"
            +" ods_docto_fec_programacion,ods_destino_id,od_destino_nombre,oc_ciudad_nombre,oem.embarque_paq,"
            +" oem_embarque_paq_captura,ods_docto_piezas,ods_docto_cajas,ods_docto_pallets,ods_docto_colgados,"
            +" ods_docto_contenedor,ods_docto_atados,ods_docto_bulks,ods_docto_importe,ods_docto_peso,ods_docto_volumen,"
            +" ods_docto_rechazo,ods_docto_fec_rechazo,  NVL(TO_CHAR(TO_DATE(ods1_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' '),ods_docto_captura_evidencia,"
            +" ods_docto_folio_control,ods_docto_fec_control,edo_edo_desc,"
            +" ods_docto_sal_id,ods_cbdiv_id,edo_edo_tabla,ods_bodega_id,ocd_sucliente_id,"
            +" ods_docto_sal_agrupador,ods_docto_aceptacion_reenvio,ods_docto_concentrado,"
            +" ods_docto_fec_concentrado,em_embarque_paq_id,ods_docto_folio_evidencia,   NVL(TO_CHAR(TO_DATE(ods_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ')  "
            +" FROM ontms_vw_status "
            +" WHERE to_date(ods_docto_fec_captura) BETWEEN '"+fec1+"' AND '"+fec2+"'"
            +" AND ods_cbdiv_id IN ("+cveCuenta+")"
            +" AND edo_edo_tabla='ONTMS_DOCTO_SAL'";
    return sql;
    }
    public String agregarQuitar(String[] id)
    {
        sql="SELECT op_embarque_paq,op_chofer,op_camion,opa_paqueteria,op_embarque_paq_agrupador"
                + " FROM ontms_vw_paqueteria"
                + " WHERE op_embarque_paq='"+id[0]+"' AND ods_cbdiv_id IN ("+id[1]+")";
    return sql;
    }
}
