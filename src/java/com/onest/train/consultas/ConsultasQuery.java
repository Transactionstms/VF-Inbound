/*
 * To change this template, choose Tools | Templates
 * AND open the template in the editor.
 */
package com.onest.train.consultas;

import com.onest.oracle.OracleDB;

/**
 * embarqueContarReenvios
 *
 * @author tere
 */
public class ConsultasQuery {

    String sql = "";

    public String trakingGeneralLista2(String documento, String cveCuenta) {
        sql = "SELECT ohds.cbdiv_id,docto_referencia, oc.cuenta_nombre,ob.bodega_nombre, odv.division_nombre,ohds.docto_sal_id"
                + " from ontms_docto_sal ohds"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ohds.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ohds.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_cliente oc ON oc.cliente_id=ohds.cliente_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ohds.bodega_id"
                + " INNER JOIN ontms_tipo_docto otd ON otd.tipo_docto_id=ohds.tipo_docto_id"
                + " WHERE upper(docto_referencia) in (upper('" + documento + "'))"
                + " AND ohds.cbdiv_id in (" + cveCuenta + ")"
                + " GROUP BY ohds.cbdiv_id,docto_referencia, oc.cuenta_nombre,ob.bodega_nombre, odv.division_nombre,ohds.docto_sal_id"
                + " ORDER BY oc.cuenta_nombre";
        return sql;
    }

    public String liquidacionG(String[] id) {
        sql = " SELECT oe.oe_embarque_id,"
                + "  NVL(TO_CHAR(oe.oe_embarque_fec_captura,'dd/mm/yyyy'),' '),"
                + "  COUNT(DISTINCT docto_sal_id),"
                + "  oe.olt_ltransporte_nombre,"
                + "  oe.oc_chofer_nombre,"
                + "  oe.oe_embarque_costo_real,"
                + "  oe_embarque_agrupador,"
               // + "  ocu.cuenta_nombre,"
                + "  NVL(oc.costo,0)"
                + " FROM ontms_hist_docto_sal ods"
                + " INNER JOIN VW_CONSULTA_EM oe"
                + " ON ods.docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN ontms_liquidacion oli" // Estas lineas desapareceran si se brinca el paso de Liberacion de flete
                + " ON oli.embarque_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocb"
                + " ON ocb.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob"
                + " ON ob.bodega_id=ocb.bodega_id"
                + " LEFT JOIN ontms_cargos oc"
                + " ON oc.embarque_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN DILOG_DESTINOS_HILTI ddh"
                + " ON ddh.DESTINO_SHIP_TO= ods.DESTINO_ID"
                + " AND ods.cbdiv_id      = ddh.division_id"
                + " LEFT JOIN ontms_bodega ob1"
                + " ON ob1.bodega_id=ods.bodega_id"
                //+ " INNER JOIN ontms_cuenta ocu"
                //+ " ON ocu.cuenta_id         =ocb.cuenta_id"
                + " WHERE oe.olt_ltransporte_id=" + id[1] + ""
                + " AND  oe.oe_embarque_estado_id=5"
                + " GROUP BY oc.costo,oe.oe_embarque_id, oe.olt_ltransporte_nombre, oe.oc_chofer_nombre, oe.oe_embarque_costo_real, "
                + " oe.oe_embarque_agrupador, "
                //+ " ocu.cuenta_nombre, "
                + " oe.oe_embarque_fec_captura ";
        return sql;
    }

    public String liquidacionGS(String[] id) {
        sql = " SELECT oe.oe_embarque_id,"
                + "  NVL(TO_CHAR(oe.oe_embarque_fec_captura,'dd/mm/yyyy'),' '),"
                + "  COUNT(DISTINCT docto_sal_id),"
                + "  oe.olt_ltransporte_nombre,"
                + "  oe.oc_chofer_nombre,"
                + "  oe.oe_embarque_costo_real,"
                + "  oe_embarque_agrupador,"
                //+ "  ocu.cuenta_nombre,"
                + "  NVL(oc.costo,0)"
                + " FROM ontms_hist_docto_sal ods"
                + " INNER JOIN VW_CONSULTA_EM oe"
                + " ON ods.docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN ontms_liquidacion oli" // Estas lineas desapareceran si se brinca el paso de Liberacion de flete
                + " ON oli.embarque_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocb"
                + " ON ocb.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob"
                + " ON ob.bodega_id=ocb.bodega_id"
                + " LEFT JOIN ontms_cargos oc"
                + " ON oc.embarque_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN DILOG_DESTINOS_HILTI ddh"
                + " ON ddh.DESTINO_SHIP_TO= ods.DESTINO_ID"
                + " AND ods.cbdiv_id      = ddh.division_id"
                + " LEFT JOIN ontms_bodega ob1"
                + " ON ob1.bodega_id=ods.bodega_id"
                //+ " INNER JOIN ontms_cuenta ocu"
                //+ " ON ocu.cuenta_id         =ocb.cuenta_id"
                + " WHERE  oe.oe_embarque_estado_id=5 and oe.oe_embarque_agrupador in(" + id[0] + ")"
                + " GROUP BY oc.costo,oe.oe_embarque_id, oe.olt_ltransporte_nombre, oe.oc_chofer_nombre, oe.oe_embarque_costo_real, "
                + " oe.oe_embarque_agrupador, "
                //+ " ocu.cuenta_nombre,"
                + " oe.oe_embarque_fec_captura ";
        return sql;
    }

    public String division(String cvediv) {
        sql = "SELECT division_id FROM ontms_cta_bod_div WHERE cbdiv_id IN(" + cvediv + ")";
        return sql;
    }

    public String unidad(String cve) {
        sql = "select oum.umedida_id, oum.umedida_desc, oc.cuenta_nombre, ob.bodega_nombre,od.division_nombre"
                + " from ontms_um_div oud"
                + " inner join ontms_cta_bod_div ocbd on ocbd.cbdiv_id = oud.cbdiv_id"
                + " inner join ontms_cuenta oc on oc.cuenta_id = ocbd.cuenta_id"
                + " inner join ontms_bodega ob on ob.bodega_id = ocbd.bodega_id"
                + " inner join ontms_division od on od.division_id = ocbd.division_id"
                + " inner join ontms_unidad_medida oum on oum.umedida_id = oud.umedida_id"
                + " WHERE 1=1 and ocbd.cbdiv_id in (" + cve + ")"
                + " ORDER BY oum.umedida_id";
        return sql;
    }

    public String cliente(String division) {
        sql = "select  otc.cliente_id ,otc.cliente_nombre "
                + " FROM ontms_conversion_destino ocd, ontms_destino od, ontms_cliente otc"
                + " WHERE ocd.destino_id=od.destino_id AND otc.cliente_id=od.cliente_id AND"
                + " ocd.division_id='" + division + "' "
                + " GROUP BY otc.cliente_id ,otc.cliente_nombre ORDER BY otc.cliente_nombre";
        return sql;
    }

    public String clienteHilti(String division) {
        sql = "select DESTINO_SHIP_TO,DESTINO_NOMBRE "
                + " from dilog_destinos_hilti "
                + " where DIVISION_ID =" + division + " order by DESTINO_NOMBRE";
        return sql;
    }

//****
    public String embarqueContarReenvios(String e_cuenta, String division, String estado) {
        sql = "SELECT count(DISTINCT ods.docto_referencia)"
                + " from ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
                + " WHERE 1=1 AND ods.docto_estado_id in (" + estado + ") AND ((ods.docto_folio_control IS NULL) OR (ods.docto_folio_control='0'))"
                + " AND ods.cbdiv_id in (" + e_cuenta + ") AND ocd.division_id in(" + division + ")";
        return sql;
    }

    public String embarqueDetalleReenvios(String e_cuenta, String division, String estado , String transportista) {
        sql = "  SELECT ods.docto_sal_id,"
                + " ods.docto_referencia,"
                + " oc.cuenta_nombre,"
                + " oc.destino_nombre,"
                + " nvl(oc.destino_colonia,' '),"
                + " nvl(oc.destino_ciudad,' '),"
                + " oc.DESTINO_ESTADO,"
                + " oc.DESTINO_CP,"
                + " NVL(TO_CHAR(ods.docto_fec_captura,'dd/mm/yyyy'),' '),"
                + " NVL(TO_CHAR(ods.docto_piezas),' '),"
                + " NVL(TO_CHAR(ods.docto_cajas),' '),"
                + " ae.edo_desc,"
                + " TRA.LTRANSPORTE_NOMBRE"
                + " FROM ontms_docto_sal ods"
                + " INNEr JOIN ontms_cta_bod_div ocbd"
                + " ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " inner JOIN ontms_bodega ob"
                + " ON ob.bodega_id = ocbd.bodega_id"
                + " inner JOIN ontms_cuenta oc"
                + " ON oc.cuenta_id = ocbd.cuenta_id"
                + " inner JOIN ontms_division odv"
                + " ON odv.division_id = ocbd.division_id"
                + " INNER JOIN dilog_destinos_hilti oc"
                + " ON ods.destino_id=oc.destino_ship_to"
                + " AND ods.cbdiv_id = oc.division_id"
                + " INNER JOIN app_estado ae"
                + " ON ae.edo_valor                =ods.docto_estado_id "
                + " INNER JOIN ontms_embarque emb ON ods.docto_sal_agrupador = emb.embarque_agrupador " 
                + " LEFT JOIN ONTMS_CAMION CAM ON emb.CAMION_ID = CAM.CAMION_ID "
                + " LEFT JOIN ONTMS_LINEA_TRANSPORTE TRA ON CAM.LTRANSPORTE_ID= TRA.LTRANSPORTE_ID "
                + " WHERE 1=1 AND ae.edo_tabla='ONTMS_DOCTO_SAL' AND ods.docto_estado_id in (" + estado + ") "
                + " AND ((ods.docto_folio_control IS NULL) OR (ods.docto_folio_control='0'))"
                + " AND ods.cbdiv_id in (" + e_cuenta + ")   AND CAM.LTRANSPORTE_ID ='" + transportista + "'"
                + " GROUP BY ae.edo_desc,"
                + " ods.docto_sal_id,"
                + " ods.docto_referencia,"
                + " oc.cuenta_nombre,"
                + " oc.destino_nombre,"
                + " oc.DESTINO_COLONIA,"
                + " oc.DESTINO_CIUDAD,"
                + " oc.DESTINO_ESTADO,"
                + " oc.DESTINO_CP,"
                + " ods.docto_fec_captura,"
                + " ods.docto_piezas,"
                + " ods.docto_cajas,"
                + " TRA.LTRANSPORTE_NOMBRE"
                + " order by ods.docto_referencia ";
        return sql;
    }

    public String detallePdf(String agrupador, String cve) {
        sql = "SELECT ods.docto_sal_id,"
                + "  ods.docto_referencia,"
                + "  oc.cuenta_nombre,"
                + "  oc.destino_nombre,"
                + "  oc.DESTINO_CIUDAD,"
                + "  oc.DESTINO_ESTADO,"
                + "  NVL(TO_CHAR(ods.docto_fec_captura,'dd/mm/yyyy'),' '),"
                + "  NVL(ods.docto_piezas,0),"
                + "  NVL(ods.docto_cajas,0),"
                + "  NVL(ods.docto_pallets,0),"
                + "  NVL(ods.docto_colgados,0),"
                + "  NVL(ods.docto_contenedor,0),"
                + "  NVL(ods.docto_atados,0),"
                + "  NVL(ods.docto_bulks,0),"
                + "  NVL(TO_CHAR(ods.docto_fec_control,'dd/mm/yyyy'),' '),"
                + "  ods.docto_observaciones"
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
                + " ON ods.destino_id     =oc.destino_ship_to"
                + " AND ods.cbdiv_id      = oc.division_id"
                + " WHERE 1=1 AND ods.docto_estado_id in (7,8,16,19) AND "
                + " docto_folio_control='" + agrupador + "'"
                + " AND ods.cbdiv_id in (" + cve + ") ORDER BY ods.docto_referencia";
        return sql;
    }

    public String embarqueContarReenvios1(String e_cuenta, String tipo, String division, String estado) {
        sql = "SELECT COUNT(DISTINCT ods.docto_referencia)"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.SUCLIENTE_ID=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id"
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
                + " WHERE 1=1 AND ods.docto_estado_id IN (" + estado + ") "
                + " AND otd.tipo_destino_lof IN('" + tipo + "')"
                + " AND ods.cbdiv_id in (" + e_cuenta + ") AND ocd.division_id IN(" + division + ")";
        return sql;
    }
///****

    public String embarqueDetalleReenvios1(String e_cuenta, String tipo, String division, String estado) {
        sql = "SELECT ods.docto_sal_id,"
                + "  ods.docto_referencia,"
                + "  oc.cuenta_nombre,"
                + "  ddh.DESTINO_NOMBRE,"
                + "  ddh.DESTINO_CIUDAD,"
                + "  ddh.DESTINO_ESTADO,"
                + "  NVL(TO_CHAR(ods.docto_fec_captura,'dd/mm/yyyy hh:mi'),' '),"
                + "  NVL(TO_CHAR(ods.docto_piezas),' '),"
                + "  NVL(TO_CHAR(ods.docto_cajas),' ')"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd"
                + " ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob"
                + " ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc"
                + " ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN dilog_destinos_hilti ddh "
                + " ON ddh.destino_ship_to = ods.destino_id "
                + " and ddh.division_id = ods.cbdiv_id "
                + " LEFT JOIN ontms_bodega ob1"
                + " ON ob1.bodega_id=ods.bodega_id"
                + " WHERE 1=1 AND ods.docto_estado_id in (" + estado + ") "
                + " AND ods.cbdiv_id in (" + e_cuenta + ") AND ddh.division_id                      =ods.cbdiv_id"
                + " GROUP BY ods.docto_sal_id,"
                + "  ods.docto_referencia,"
                + "  oc.cuenta_nombre,"
                + "  ddh.DESTINO_NOMBRE,"
                + "  ddh.DESTINO_CIUDAD,"
                + "  ddh.DESTINO_ESTADO,"
                + "  ods.docto_fec_captura,"
                + "  ods.docto_piezas,"
                + "  ods.docto_cajas"
                + " ORDER BY ods.docto_referencia ";
        return sql;
    }

    public String cancelarDocumento(String cve) {
        sql = "SELECT docto_sal_id,docto_referencia,to_char(docto_fec_captura,'dd/mm/yyyy'),otd.tipo_docto_desc,od.destino_nombre,odv.division_nombre,"
                + " NVL(to_char(docto_piezas),' '),NVL(to_char(docto_importe),' '),NVL(to_char(docto_peso),' '),NVL(to_char(docto_volumen),' ')"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_tipo_docto otd ON otd.tipo_docto_id=ods.tipo_docto_id"
                + " WHERE ods.cbdiv_id IN(" + cve + ")"
                + " AND docto_estado_id=0 GROUP BY"
                + " docto_sal_id,docto_referencia,to_char(docto_fec_captura,'dd/mm/yyyy'),otd.tipo_docto_desc,od.destino_nombre,odv.division_nombre,"
                + " docto_piezas,docto_importe,docto_peso,docto_volumen"
                + " ORDER BY docto_referencia";
        return sql;
    }

    public String cancelarDocumentoContar(String cve) {
        sql = "SELECT COUNT(docto_sal_id)"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_tipo_docto otd ON otd.tipo_docto_id=ods.tipo_docto_id"
                + " WHERE ods.cbdiv_id IN (" + cve + ")"
                + " AND docto_estado_id=0";
        return sql;
    }

    public String cancelarDocumentoSeleccionado() {
        sql = "SELECT docto_sal_id,docto_referencia,to_char(docto_fec_captura,'dd/mm/yyyy'),otd.tipo_docto_desc,od.destino_nombre,odv.division_nombre,"
                + " NVL(to_char(docto_piezas),' '),NVL(to_char(docto_importe),' '),NVL(to_char(docto_peso),' '),NVL(to_char(docto_volumen),' ')"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_tipo_docto otd ON otd.tipo_docto_id=ods.tipo_docto_id";
        return sql;
    }

    public String detalleDocumentoModificar(String id) {
        sql = "SELECT docto_sal_id,docto_referencia,NVL(docto_concentrado,' '),NVL(to_char(docto_fec_concentrado,'dd/mm/yyyy'),' '),NVL(docto_talon,' '),"
                + " NVL(to_char(docto_fec_talon,'dd/mm/yyyy'),' '),NVL(docto_pedido,' '),NVL(to_char(docto_fec_pedido,'dd/mm/yyyy'),' '),NVL(docto_programacion,' '),NVL(to_char(docto_fec_programacion,'dd/mm/yyyy'),' '),"
                + " NVL(to_char(docto_fec_cancelacion,'dd/mm/yyyy'),' '),NVL(docto_importe,0),NVL(docto_peso,0),NVL(docto_volumen,0),docto_fec_captura,"
                + " ods.tipo_docto_id,otd.tipo_docto_desc,ods.cliente_id,oc.cliente_nombre,ods.destino_id,od.destino_id,"
                + " od.destino_nombre,NVL(docto_piezas,0),NVL(docto_cajas,0), NVL(docto_pallets,0), NVL(docto_colgados,0),"
                + " NVL(docto_contenedor,0),NVL(docto_atados,0),NVL(docto_bulks,0),"
                + " NVL(to_char(docto_fec_factura,'dd/mm/yyyy'),' ')"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cliente oc ON oc.cliente_id=ods.cliente_id"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division od ON od.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id = od.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_tipo_docto otd ON otd.tipo_docto_id=ods.tipo_docto_id"
                + " WHERE docto_sal_id='" + id + "'";
        return sql;
    }

    public String trakingGeneralLista(String documento, String cveCuenta) {
        sql = "SELECT ohds.cbdiv_id,docto_referencia, oc.cuenta_nombre,ob.bodega_nombre, odv.division_nombre,ohds.docto_sal_id"
                + " from ontms_docto_sal ohds"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ohds.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ohds.destino_id AND ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_cliente oc ON oc.cliente_id=ohds.cliente_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ohds.bodega_id"
                + " INNER JOIN ontms_tipo_docto otd ON otd.tipo_docto_id=ohds.tipo_docto_id"
                + " WHERE upper(docto_referencia) in (upper('" + documento + "'))"
                + " AND ohds.cbdiv_id in (" + cveCuenta + ")"
                + " AND docto_estado_id in (0,11,1)"
                + " GROUP BY ohds.cbdiv_id,docto_referencia, oc.cuenta_nombre,ob.bodega_nombre, odv.division_nombre,ohds.docto_sal_id"
                + " ORDER BY oc.cuenta_nombre";
        return sql;
    }

    public String muestraDocumentos(String id) {
        sql = "SELECT docto_sal_id,docto_referencia,NVL(to_char(docto_concentrado),' '),"
                + " NVL(docto_fec_concentrado,' '),NVL(to_char(docto_talon),' '),"
                + " NVL(docto_fec_talon,' '), NVL(to_char(docto_pedido),' '),"
                + " NVL(docto_fec_pedido,' '),  NVL(to_char(docto_programacion),' '),"
                + " NVL(docto_fec_programacion,' '),"
                + " NVL(docto_fec_cancelacion,' '), NVL(to_char(docto_importe),' '),"
                + " NVL(to_char(docto_peso),' '), NVL(to_char(docto_volumen),' '),"
                + " NVL(docto_fec_captura,' '), docto_estado,"
                + " tipo_docto_cliente, destino, cbdiv_id, docto_sal_cantidad, docto_evidencia,"
                + " NVL(TO_CHAR(TO_DATE(docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ') ,docto_rechazo,NVL(docto_fec_rechazo,' '),"
                + " docto_folio_evidencia, docto_sal_rma, NVL(to_char(docto_piezas),' '),"
                + " NVL(to_char(docto_cajas),' '),NVL(to_char(docto_pallets),' '),"
                + " NVL(to_char(docto_colgados),' '),NVL(to_char(docto_contenedor),' ') ,"
                + " NVL(to_char(docto_atados),' '), NVL(to_char(docto_bulks),' '),"
                + " docto_folio_control,docto_aceptacion_reenvio, embarque, NVL(embarque_fec,' ')"
                + " FROM ontms_historico WHERE docto_sal_id ='" + id + "' ";
        return sql;
    }

    public boolean procesoUsuario(OracleDB oraDB, String user, int proceso, String id, String ip) {
        sql = "INSERT INTO ontms_procesos VALUES(null," + user + "," + proceso + ",'" + id + "',SYSDATE,'" + ip + "')";

        boolean val = oraDB.execute(sql);
        System.out.println(sql + "*****************+" + val);
        return val;
    }

    public boolean procesoEmbarque(OracleDB oraDB, String[] id) {
        boolean val = oraDB.execute("INSERT INTO ontms_auditoria_embarques VALUES (null,'" + id[0] + "'," + id[1] + ",'" + id[2] + "'," + id[3] + ",sysdate," + id[4] + ",'" + id[5] + "'," + id[6] + ")");
        return val;
    }

    public String listSku(String n_documento) {
        sql = "UPDATE ontms_docto_sku set cantidad_rechazada=0 WHERE docto_sal_id='" + n_documento + "'";
        return sql;
    }

    public String modificarCargo(String n_documento) {
        sql = "SELECT oe.embarque_id,to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),oc.chofer_nombre,"
                + " olt.ltransporte_nombre,ocar.costo,ocr.rechazo_id,ocr.rechazo_desc,ocar.cargo_id FROM ontms_cargos ocar"
                + " INNER JOIN ontms_causa_rechazo ocr ON ocr.rechazo_id=ocar.rechazo_id"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=ocar.embarque_agrupador"
                + " INNER JOIN ontms_chofer oc ON oc.chofer_id=oe.chofer_id"
                + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id"
                + " INNER JOIN ontms_linea_transporte olt ON olt.ltransporte_id=oca.ltransporte_id"
                + " WHERE ocar.embarque_agrupador='" + n_documento + "'"
                + " GROUP BY oe.embarque_id,oe.embarque_fec_captura,oc.chofer_nombre,"
                + " olt.ltransporte_nombre,ocar.costo,ocr.rechazo_id,ocr.rechazo_desc,ocar.cargo_id";
        return sql;
    }

    public String autorizarCargo(String cve, String e) {
        sql = "SELECT cargo_id,to_char(CARGO_FEC,'dd/mm/yyyy'),costo,cargo_folio,ocr.rechazo_desc,och.chofer_nombre, olt.ltransporte_nombre"
                + " FROM ontms_cargos oc"
                + " INNER JOIN ontms_causa_rechazo ocr ON ocr.rechazo_id=oc.rechazo_id"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=oc.embarque_agrupador"
                + " INNER JOIN ontms_camion oca ON oe.camion_id=oca.camion_id"
                + " INNER JOIN ontms_linea_transporte olt ON olt.ltransporte_id=oca.ltransporte_id"
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE ods.cbdiv_id IN (" + cve + ") AND oc.estado_cargo=" + e + ""
                + " GROUP BY cargo_id,cargo_fec,costo,cargo_folio,ocr.rechazo_desc,och.chofer_nombre, olt.ltransporte_nombre";
        return sql;
    }

    public String autorizarCargoContar(String cve, String e) {
        sql = "SELECT COUNT(distinct(cargo_id))"
                + " FROM ontms_cargos oc"
                + " INNER JOIN ontms_causa_rechazo ocr ON ocr.rechazo_id=oc.rechazo_id"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=oc.embarque_agrupador"
                + " INNER JOIN ontms_camion oca ON oe.camion_id=oca.camion_id"
                + " INNER JOIN ontms_chofer och ON oe.chofer_id=och.chofer_id"
                + " INNER JOIN ontms_linea_transporte olt ON oca.ltransporte_id=olt.ltransporte_id"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocbd.division_id=ocd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE ods.cbdiv_id in (" + cve + ") AND oc.estado_cargo=" + e + "";
        return sql;
    }

    public String autorizarCargoSeleccionados() {
        sql = "SELECT cargo_id,to_char(cargo_fec,'dd/mm/yyyy'),costo,cargo_folio,ocr.rechazo_desc,och.chofer_nombre, olt.ltransporte_nombre"
                + " FROM ontms_cargos oc"
                + " INNER JOIN ontms_causa_rechazo ocr ON ocr.rechazo_id=oc.rechazo_id"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=oc.embarque_agrupador"
                + " INNER JOIN ontms_camion oca ON oe.camion_id=oca.camion_id"
                + " INNER JOIN ontms_chofer och ON oe.chofer_id=och.chofer_id"
                + " INNER JOIN ontms_linea_transporte olt ON oca.ltransporte_id=olt.ltransporte_id"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocbd.division_id=ocd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id AND ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id";

        return sql;
    }
///verificar

    public String pdfcargos(String folio, String cve) {
        sql = "SELECT oe.embarque_id,olt.ltransporte_nombre,outr.utransporte_desc,och.chofer_nombre,ocr.rechazo_desc,oc.costo from ontms_cargos oc"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=oc.embarque_agrupador"
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id"
                + " INNER JOIN ontms_linea_transporte olt ON olt.ltransporte_id=oca.ltransporte_id"
                + " INNER JOIN ontms_unidad_transporte outr ON outr.utransporte_id=oca.utransporte_id"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocbd.division_id=ocd.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_causa_rechazo ocr ON ocr.rechazo_id=oc.rechazo_id"
                + " WHERE cargo_folio='" + folio + "' AND ods.cbdiv_id in (" + cve + ")";
        return sql;
    }
///verificar
////vericar liquidacion

    public String liquidacion(String cve, String trans) {
        sql = "SELECT oe.embarque_id,TO_CHAR(oe.embarque_fec_captura,'dd/mm/yyyy'),"
                + " COUNT(DISTINCT ods.docto_sal_id),ot.ltransporte_nombre,och.chofer_nombre,"
                + " NVL(TO_CHAR(oe.embarque_costo_real),' '),NVL(TO_CHAR(oc.costo),' '),oe.embarque_agrupador "
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_docto_Sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_camion oc ON oc.camion_id=oe.camion_id"
                + " LEFT JOIN ontms_cargos oc ON oc.embarque_agrupador=oe.embarque_agrupador"
                + " LEFT JOIN ontms_liquidacion ol ON ol.embarque_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_linea_transporte ot ON ot.ltransporte_id=oc.ltransporte_id"
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " where ((docto_folio_control IS NOT NULL OR docto_folio_control<>'0')"
                + " OR (docto_folio_control IS NULL AND docto_estado_id IN (11)))"
                + " AND cbdiv_id  IN (" + cve + ") and oc.ltransporte_id =" + trans + " AND ol.embarque_agrupador IS NULL"
                + " GROUP BY oe.embarque_id, TO_CHAR(oe.embarque_fec_captura,'dd/mm/yyyy'), ot.ltransporte_nombre, "
                + " och.chofer_nombre, NVL(TO_CHAR(oe.embarque_costo_real),' '), NVL(TO_CHAR(oc.costo),' '), "
                + " oe.embarque_agrupador";
        return sql;
    }

    public String liquidacionDoc(String a) {
        sql = "SELECT * FROM ontms_docto_sal"
                + " where docto_sal_agrupador='" + a + "' and docto_estado_id in (3,10)";
        return sql;
    }

//verificar
    public String liquidacionContar(String cve, String trans) {
        sql = "select count(distinct(oe.embarque_id))"
                + " FROM (SELECT * FROM (SELECT * FROM ontms_docto_Sal ods"
                + " INNER JOIN ontms_embarque oe ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " WHERE docto_folio_control IS NOT NULL AND docto_folio_control<>'0' AND cbdiv_id in (" + cve + "))"
                + " WHERE not exists (SELECT * FROM ontms_docto_Sal ods"
                + " INNER JOIN ontms_embarque oe ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " WHERE ((docto_folio_control IS NOT NULL OR docto_folio_control   <>'0') "
                + " or(docto_folio_control IS NULL and docto_estado_id IN (11))) AND cbdiv_id in (" + cve + ")"
                + " AND docto_estado_id in (3,10))) ods"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " LEFT JOIN ontms_cargos oc ON oc.embarque_agrupador=oe.embarque_agrupador"
                + " LEFT JOIN ontms_liquidacion ol ON ol.embarque_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id"
                + " INNER JOIN ontms_linea_transporte ot ON ot.ltransporte_id=oca.ltransporte_id"
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " LEFT JOIN ontms_embarque_indirectos ogi ON ogi.embarque_agrupador=oe.embarque_agrupador"
                + " WHERE ol.embarque_agrupador IS NULL AND ot.ltransporte_id=" + trans + "";
        return sql;
    }
///verificar
///revisr------

    public String liquidacionSeleccionados(String agrupador) {
        sql = "select  NVL(sum(distinct ogi.embarque_indirectos_costo),0),oe.embarque_id,to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),count(distinct ods.docto_sal_id),ot.ltransporte_nombre,och.chofer_nombre,NVL(to_char(oe.embarque_costo_real),' '),NVL(to_char(oc.costo),' '),oe.embarque_agrupador from ontms_embarque oe"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " LEFT JOIN ontms_cargos oc ON oc.embarque_agrupador=oe.embarque_agrupador"
                + " LEFT JOIN ontms_liquidacion ol ON ol.embarque_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id"
                + " INNER JOIN ontms_linea_transporte ot ON ot.ltransporte_id=oca.ltransporte_id"
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " LEFT JOIN ontms_embarque_indirectos ogi ON ogi.embarque_agrupador=oe.embarque_agrupador"
                + " WHERE ol.embarque_agrupador is null AND docto_estado_id in (7,8,11)"
                + " AND oe.embarque_agrupador in (" + agrupador + ") AND ((docto_folio_control IS NOT NULL AND docto_folio_control   <>'0') "
                + " OR (docto_folio_control IS NULL and docto_estado_id IN (11)))"
                + " GROUP BY oe.embarque_id,oe.embarque_fec_captura,ot.ltransporte_nombre, och.chofer_nombre, oe.embarque_costo_real, oc.costo, oe.embarque_agrupador";
        return sql;
    }

    public String gastosLiquidacion(String id, String ie) {
        sql = "SELECT NVL(sum(embarque_indirectos_costo),0) FROM ontms_embarque_indirectos"
                + " WHERE gtransp_id=" + ie + " AND embarque_agrupador in (" + id + ")";
        return sql;
    }
///verificar

    public String liquidacionFolio(String[] id) {
        sql = "SELECT DISTINCT(oe.embarque_id),to_char(oe.embarque_fec_captura,'dd/mm/yyyy'),"
                + " count(distinct ods.docto_sal_id),"
                + " ot.ltransporte_nombre,"
                + " och.chofer_nombre, "
                + " NVL(oe.embarque_costo_real,0), "
                + " NVL(oc.costo,0),"
                + " oe.embarque_agrupador, "
                + " ol.total,"
                + " to_char(ol.fecha,'dd/mm/yyyy'),ol.folio_liquidacion, "
                + " (SELECT NVL(sum(embarque_indirectos_costo),0) "
                + " FROM ontms_embarque_indirectos "
                + " where "
                + " embarque_agrupador=oe.embarque_agrupador),"
                + " ocu.cuenta_nombre,"
                + " empsa_nombre"
                + " from ontms_embarque oe"
                + " LEFT JOIN ontms" + id[2] + "docto_sal ods "
                + " ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " LEFT JOIN ontms_cta_bod_div ocb ON ocb.cbdiv_id=ods.cbdiv_id"
                + " LEFT JOIN ontms_cuenta ocu ON ocu.cuenta_id=ocb.cuenta_id"
                + " LEFT JOIN ontms_empresa om ON om.empsa_id=ocu.empsa_id"
                + " LEFT JOIN ontms_cargos oc ON oc.embarque_agrupador=oe.embarque_agrupador"
                + " LEFT JOIN ontms_liquidacion" + id[3] + " ol ON ol.embarque_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id"
                + " INNER JOIN ontms_linea_transporte ot ON ot.ltransporte_id=oca.ltransporte_id"
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " LEFT JOIN ontms_embarque_indirectos ogi ON ogi.embarque_agrupador=oe.embarque_agrupador"
                + " WHERE ol.id_doc_embarque='" + id[1] + "'"
                + " group by "
                + " ocu.cuenta_nombre,"
                + " empsa_nombre,"
                + " ol.folio_liquidacion,"
                + " ol.total,"
                + " ol.fecha,"
                + " oe.embarque_id,"
                + " oe.embarque_fec_captura,"
                + " ot.ltransporte_nombre,"
                + " och.chofer_nombre,"
                + " oe.embarque_costo_real, "
                + " oc.costo, "
                + " oe.embarque_agrupador "
                + " ORDER BY oe.embarque_id";
        return sql;
    }

    public String plagrupador(String agrupador, String doc) {
        sql = "SELECT ods.docto_sal_id"
                + " FROM ontms_docto_sal ods"
                + " WHERE ods.docto_sal_id IN (SELECT ods.docto_sal_id FROM ontms_docto_sal ods WHERE ods.docto_sal_agrupador='" + agrupador + "')"
                + " MINUS "
                + " SELECT ods.docto_sal_id"
                + " FROM ontms_docto_sal ods"
                + " WHERE ods.docto_sal_id in (" + doc + ")";
        return sql;
    }

    public String lib_paq(String cbdiv, String paquete) {
        sql = "SELECT oe.embarque_paq,TO_CHAR(oe.embarque_paq_captura,'dd/mm/yyyy'),"
                + " COUNT(DISTINCT ods.docto_sal_id),NVL(TO_CHAR(oe.embarque_paq_costo_real),' '),"
                + " NVL(TO_CHAR(oc.costo),' '),oe.embarque_paq_agrupador,oe.camion,oe.chofer"
                + " FROM ontms_embarque_paqueteria oe"
                + " INNER JOIN ontms_docto_Sal ods ON ods.docto_sal_agrupador=oe.embarque_paq_agrupador"
                + " LEFT JOIN ontms_liquidacion ol ON ol.embarque_agrupador=oe.embarque_paq_agrupador"
                + " LEFT JOIN ontms_cargos oc ON oc.embarque_agrupador=oe.embarque_paq_agrupador"
                + " where ((docto_folio_control IS NOT NULL OR docto_folio_control<>'0')"
                + " OR (docto_folio_control IS NULL AND docto_estado_id IN (11)))"
                + " AND cbdiv_id IN (" + cbdiv + ") AND oe.paqueteria_id=" + paquete + ""
                + " AND ol.folio_liquidacion IS NULL GROUP BY oe.embarque_paq, TO_CHAR(oe.embarque_paq_captura,'dd/mm/yyyy'), "
                + " NVL(TO_CHAR(oe.embarque_paq_costo_real),' '),NVL(TO_CHAR(oc.costo),' '), "
                + "oe.embarque_paq_agrupador, oe.camion, oe.chofer";
        return sql;
    }

    public String lib_paqAgrup(String agrupador) {
        sql = "SELECT oe.embarque_paq,  TO_CHAR(oe.embarque_paq_captura,'dd/mm/yyyy'),"
                + " COUNT(DISTINCT ods.docto_sal_id),"
                + " NVL(TO_CHAR(oe.embarque_paq_costo_real),' '),"
                + " oe.camion,  oe.chofer,"
                + " oe.embarque_paq_agrupador"
                + " FROM ontms_embarque_paqueteria oe"
                + " INNER JOIN ontms_docto_sal ods"
                + " ON ods.docto_sal_agrupador=oe.embarque_paq_agrupador"
                + " LEFT JOIN ontms_liquidacion ol"
                + " ON ol.embarque_agrupador=oe.embarque_paq_agrupador"
                + " WHERE ol.embarque_agrupador IS NULL"
                + " AND docto_estado_id  IN (7,8,11)"
                + " AND oe.embarque_paq_agrupador   IN (" + agrupador + ")"
                + " AND ods.DOCTO_FOLIO_CONTROL IS NOT NULL"
                + " AND ods.DOCTO_FOLIO_CONTROL  <> '0'"
                + " GROUP BY oe.embarque_paq, TO_CHAR(oe.embarque_paq_captura,'dd/mm/yyyy'), "
                + " NVL(TO_CHAR(oe.embarque_paq_costo_real),' '), oe.camion,  oe.chofer,oe.embarque_paq_agrupador ";
        return sql;
    }

    public String lib_paq_doc(String cbdiv, String paquete) {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia,to_char(ods.docto_fec_captura,'dd/mm/yyyy'),ods.docto_piezas,"
                + " oe.embarque_paq,to_char(oe.embarque_paq_captura,'dd/mm/yyyy'),"
                + " op.paqueteria FROM ontms_docto_Sal ods,ontms_embarque_paqueteria oe,"
                + " ontms_paqueterias op WHERE ods.docto_sal_agrupador=oe.embarque_paq_agrupador"
                + " AND op.paqueteria_id=oe.paqueteria_id AND ((DOCTO_FOLIO_CONTROL   IS NOT NULL"
                + " OR docto_folio_control<>'0') OR (docto_folio_control IS NULL"
                + " AND docto_estado_id IN (11))) AND (ods.docto_totales IS NULL OR ods.docto_totales='0')AND cbdiv_id IN (" + cbdiv + ") AND op.paqueteria_id=" + paquete + "";
        return sql;
    }

    public String lib_paqAgrup_doc(String agrupador) {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia,to_char(ods.docto_fec_captura,'dd/mm/yyyy'),ods.docto_piezas,"
                + " oe.embarque_paq,to_char(oe.embarque_paq_captura,'dd/mm/yyyy'),"
                + " op.paqueteria FROM ontms_docto_Sal ods,ontms_embarque_paqueteria oe,"
                + " ontms_paqueterias op WHERE ods.docto_sal_agrupador=oe.embarque_paq_agrupador"
                + " AND op.paqueteria_id=oe.paqueteria_id AND ((docto_folio_control IS NOT NULL"
                + " OR docto_folio_control<>'0') OR (docto_folio_control IS NULL"
                + " AND docto_estado_id IN (11))) AND docto_sal_id IN (" + agrupador + ")";
        return sql;
    }

    public String liqPaqueteria(String cveCuenta, String paquete) {
        sql = "SELECT oep.op_embarque_paq,oep.op_embarque_paq_captura,oep.op_embarque_paq_costo_real,oep.op_embarque_paq_agrupador,"
                + " oep.ods_cbdiv_id,oep.op_paqueteria_id,oep.opa_paqueteria,oep.op_chofer,oep.op_camion,ocb.cuenta_id, ocu.cuenta_nombre, "
                + " COUNT(DISTINCT ods.docto_sal_id) FROM ontms_vw_paqueteria oep"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oep.op_embarque_paq_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocb ON ocb.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocb.bodega_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_cuenta ocu ON ocu.cuenta_id  =ocb.cuenta_id"
                + " INNER JOIN ontms_liquidacion ol ON ol.embarque_agrupador=oep.op_embarque_paq_agrupador"
                + " WHERE ocb.cuenta_id IN (" + cveCuenta + ")"
                + " AND oep.op_embarque_paq LIKE 'PA%' AND oep.op_embarque_paq_estado_id=5"
                + " AND oep.op_paqueteria_id= " + paquete + " "
                + " GROUP BY oep.op_embarque_paq,oep.op_embarque_paq_captura,oep.op_embarque_paq_costo_real,oep.op_embarque_paq_agrupador,oep.ods_cbdiv_id,"
                + " oep.op_paqueteria_id,oep.opa_paqueteria,oep.op_chofer,oep.op_camion,ocb.cuenta_id, ocu.cuenta_nombre";
        return sql;
    }

    public String liqPaqAgrup(String[] id) {
        sql = "SELECT oep.op_embarque_paq,oep.op_embarque_paq_captura,oep.op_embarque_paq_costo_real,oep.op_embarque_paq_agrupador,"
                + " oep.ods_cbdiv_id,oep.op_paqueteria_id,oep.opa_paqueteria,oep.op_chofer,oep.op_camion,ocb.cuenta_id, ocu.cuenta_nombre,"
                + " COUNT(DISTINCT ods.docto_sal_id)"
                + " FROM ontms_vw_paqueteria oep"
                + " INNER JOIN ontms_docto_Sal ods ON ods.docto_sal_agrupador=oep.op_embarque_paq_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocb ON ocb.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocb.bodega_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_cuenta ocu ON ocu.cuenta_id  =ocb.cuenta_id"
                + " WHERE oep.op_embarque_paq LIKE 'PA%'"
                + " AND oep.op_embarque_paq_agrupador IN (" + id[0] + ")"
                + " and oep.op_embarque_paq_estado_id=5"
                + " GROUP BY oep.op_embarque_paq,oep.op_embarque_paq_captura,"
                + " oep.op_embarque_paq_costo_real,oep.op_embarque_paq_agrupador,oep.ods_cbdiv_id,oep.op_paqueteria_id,oep.opa_paqueteria,"
                + " oep.op_chofer,oep.op_camion,ocb.cuenta_id, ocu.cuenta_nombre";
        return sql;
    }

    public String reimpLiq(String[] id) {
        sql = "SELECT oe.embarque_paq, oe.embarque_paq_id,to_char(oe.embarque_paq_captura,'dd/mm/yyyy'),count(distinct ods.docto_sal_id),"
                + " NVL(oe.embarque_paq_costo_real,0),oe.embarque_paq_agrupador,"
                + " ol.total,to_char(ol.fecha,'dd/mm/yyyy'),ol.folio_liquidacion, oe.chofer, op.paqueteria"
                + " FROM ontms_embarque_paqueteria oe"
                + " LEFT JOIN ontms" + id[1] + "docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_paq_agrupador"
                + " INNER JOIN ontms_paqueterias op on oe.paqueteria_id=op.paqueteria_id"
                + " LEFT JOIN ontms_cta_bod_div ocb ON ocb.cbdiv_id=ods.cbdiv_id "
                + " LEFT JOIN ontms_cuenta ocu ON ocu.cuenta_id=ocb.cuenta_id"
                + " LEFT JOIN ontms_liquidacion" + id[2] + " ol ON ol.embarque_agrupador=oe.embarque_paq_agrupador"
                + " WHERE ol.id_doc_embarque='" + id[0] + "'"
                + " GROUP BY oe.embarque_paq, oe.embarque_paq_id, to_char(oe.embarque_paq_captura,'dd/mm/yyyy'),"
                + " NVL(oe.embarque_paq_costo_real,0), oe.embarque_paq_agrupador, ol.total, to_char(ol.fecha,'dd/mm/yyyy'), ol.folio_liquidacion, oe.chofer, op.paqueteria";
        return sql;
    }

    public String reimpLiqP(String[] id) {
        sql = "SELECT ol.folio_" + id[1] + ",to_char(ol.fecha,'dd/mm/yyyy'),op.paqueteria,oe.embarque_paq,"
                + " to_char(oe.embarque_paq_captura,'dd/mm/yyyy'),ods.docto_referencia,to_char(ods.docto_fec_captura,'dd/mm/yyyy'),ods.docto_piezas,"
                + " NVL(ods.docto_cajas,0),NVL(ods.docto_pallets,0),NVL(ods.docto_colgados,0),NVL(ods.docto_contenedor,0),"
                + " NVL(ods.docto_atados,0),NVL(ods.docto_bulks,0),oc.cuenta_nombre,od.division_nombre,oe.embarque_paq_costo_real"
                + " FROM ontms" + id[2] + "_docto_sal ods"
                + " INNER JOIN ontms_" + id[1] + "_paq ol ON ods.docto_sal_id=ol.docto_sal_id"
                + " INNER JOIN ontms_embarque_paqueteria oe ON oe.embarque_paq_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN ontms_paqueterias op ON op.paqueteria_id=oe.paqueteria_id"
                + " INNER JOIN ontms_cta_bod_div oct ON oct.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id=oct.cuenta_id"
                + " INNER JOIN ontms_division od ON od.division_id=oct.division_id"
                + " WHERE ol.id_doc=" + id[0] + "";
        return sql;
    }

    public String liq_paq_doc(String[] id) {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia,to_char(ods.docto_fec_captura,'dd/mm/yyyy'),ods.docto_piezas,"
                + " op.embarque_paq,to_char(op.embarque_paq_captura)"
                + " FROM ontms_liberacion_paq ol"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_id=ol.docto_sal_id"
                + " INNER JOIN ontms_embarque_paqueteria op ON op.embarque_paq_agrupador=ods.docto_sal_agrupador"
                + " LEFT JOIN ontms_liquidacion_paq oq ON oq.docto_sal_id=ods.docto_sal_id"
                + " WHERE op.embarque_paq_estado_id=5 AND ods.cbdiv_id IN (" + id[0] + ")"
                + " AND " + id[2] + "='" + id[1] + "' AND oq.folio_liquidacion IS NULL";
        return sql;
    }
    
    public String listarSusClientes(String cve) {
        sql = "SELECT DISTINCT CLIENTE_ID, CLIENTE_DESCRIPCION, RFC FROM TRA_SUSCLIENTES WHERE CBDIV_ID = '" + cve + "' ORDER BY CLIENTE_ID ASC";
        return sql;
    }
    
    public String listarClientesFrecuentes(String cve) {
        sql = "SELECT DISTINCT CLIENTE_ID, CLIENTE_DESCRIPCION, RFC FROM TRA_SUSCLIENTES WHERE CBDIV_ID = '" + cve + "' AND ROWNUM <= 3 ORDER BY CLIENTE_ID ASC"; //Agregar cve y por núm de transacciones
        return sql;
    }
    
    public String consultarCliente(String id, String cve) {
        sql = "SELECT DISTINCT CLIENTE_ID, RFC, USOCFDI_ID, CORREO_CONTACTO1, CORREO_CONTACTO2, CORREO_CONTACTO3 FROM TRA_CLIENTE WHERE CLIENTE_ID = '" + id + "' AND ESTATUS_ID = 1 AND CBDIV_ID = '" + cve + "' ORDER BY CLIENTE_ID ASC";
        return sql;
    }
    
    public String consultarSusClientes(String id, String cve) {
        sql = "SELECT DISTINCT CLIENTE_ID, RFC, USOCFDI_ID, REGIMEN_ID, METODO_ID, FORMA_ID, CORREO_CONTACTO1, CORREO_CONTACTO2, CORREO_CONTACTO3 FROM TRA_SUSCLIENTES WHERE CLIENTE_ID = '" + id + "' AND ESTATUS_ID = 1 AND CBDIV_ID = '" + cve + "' ORDER BY CLIENTE_ID ASC";
        return sql;
    }
    
    public String dataSusCliente(String id, String cve) {
        sql = " SELECT DISTINCT "
            + " TS.RFC, " 
            + " TS.METODO_ID, "
            + " TS.FORMA_ID, "
            + " TS.USOCFDI_ID, "
            + " TS.REGIMEN_ID "
            + " FROM TRA_SUSCLIENTES TS "
            + " WHERE TS.CLIENTE_ID = " +  id + " "
            + " AND TS.ESTATUS_ID = 1 "
            + " AND TS.CBDIV_ID = '" + cve + "' "
            + " ORDER BY TS.CLIENTE_ID ASC ";
        return sql;
    }
    
    public String consultarUsoCfdi() {
        sql = "SELECT DISTINCT USOCFDI_ID, ALIAS_USO, USO_DESCRIPCION FROM TRA_USO_CFDI WHERE ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarSerieFactura(String cve) {
        sql = "SELECT DISTINCT PREFIJO_SERIE FROM TRA_SERIES_FOLIOS WHERE CBDIV_ID = '" + cve + "' AND ESTADO_ID = 1 ORDER BY PREFIJO_SERIE ASC";
        return sql;
    }
    
    public String consultarFolioFactura(String serie, String cve) {
        sql = "SELECT NVL(SECUENCIA_FOLIO,0) + 1 FROM TRA_SERIES_FOLIOS WHERE PREFIJO_SERIE = '" + serie + "' AND CBDIV_ID = '" + cve + "' AND ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarFolioConcepto(String cve) {
        sql = " SELECT NVL(BC.SECUENCIA_CONCEPTO,0) + 1 "
            + " FROM ONTMS_CTA_BOD_DIV OCBD "
            + " INNER JOIN BROKER_CUENTA BC ON OCBD.BODEGA_ID = BC.BODEGA_ID "
            + " WHERE OCBD.DIVISION_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarFolioImpuesto(String cve) {
        sql = " SELECT NVL(BC.SECUENCIA_REL_IMPUESTO,0) + 1 "
            + " FROM ONTMS_CTA_BOD_DIV OCBD "
            + " INNER JOIN BROKER_CUENTA BC ON OCBD.BODEGA_ID = BC.BODEGA_ID "
            + " WHERE OCBD.DIVISION_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarFolioPedimento(String cve) {
        sql = " SELECT NVL(BC.SECUENCIA_REL_PEDIMENTO,0) + 1 "
            + " FROM ONTMS_CTA_BOD_DIV OCBD "
            + " INNER JOIN BROKER_CUENTA BC ON OCBD.BODEGA_ID = BC.BODEGA_ID "
            + " WHERE OCBD.DIVISION_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarFolioParte(String cve) {
        sql = " SELECT NVL(BC.SECUENCIA_REL_PARTES,0) + 1 "
            + " FROM ONTMS_CTA_BOD_DIV OCBD "
            + " INNER JOIN BROKER_CUENTA BC ON OCBD.BODEGA_ID = BC.BODEGA_ID "
            + " WHERE OCBD.DIVISION_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarFolioRelcfdi(String cve) {
        sql = " SELECT NVL(BC.SECUENCIA_REL_CFDI,0) + 1 "
            + " FROM ONTMS_CTA_BOD_DIV OCBD "
            + " INNER JOIN BROKER_CUENTA BC ON OCBD.BODEGA_ID = BC.BODEGA_ID "
            + " WHERE OCBD.DIVISION_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarFolioRelComercial(String cve) {
        sql = " SELECT NVL(BC.SECUENCIA_REL_COMERCIAL,0) + 1 "
            + " FROM ONTMS_CTA_BOD_DIV OCBD "
            + " INNER JOIN BROKER_CUENTA BC ON OCBD.BODEGA_ID = BC.BODEGA_ID "
            + " WHERE OCBD.DIVISION_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarFolioCartaPorte(String cve) {
        sql = " SELECT NVL(BC.SECUENCIA_CARTA_PORTE,0) + 1 "
            + " FROM ONTMS_CTA_BOD_DIV OCBD "
            + " INNER JOIN BROKER_CUENTA BC ON OCBD.BODEGA_ID = BC.BODEGA_ID "
            + " WHERE OCBD.DIVISION_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarFolioComplemento(String cve) {
        sql = " SELECT NVL(BC.SECUENCIA_COMPLEMENTO,0) + 1 "
            + " FROM ONTMS_CTA_BOD_DIV OCBD "
            + " INNER JOIN BROKER_CUENTA BC ON OCBD.BODEGA_ID = BC.BODEGA_ID "
            + " WHERE OCBD.DIVISION_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarCuenta(String cve) {
        sql = " SELECT BC.CUENTA_ID "
            + " FROM ONTMS_CTA_BOD_DIV OCBD "
            + " INNER JOIN BROKER_CUENTA BC ON OCBD.BODEGA_ID = BC.BODEGA_ID "
            + " WHERE OCBD.DIVISION_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultaridCliente(String rfc, String cve) {
        sql = " SELECT DISTINCT CLIENTE_ID FROM TRA_SUSCLIENTES WHERE RFC = '" + rfc + "' AND CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarTipoComprobante() {
        sql = "SELECT DISTINCT COMPROBANTE_ID, COMPROBANTE_ID, COMP_DESCRIPCION FROM TRA_TIPO_COMPROBANTE WHERE ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarTipoDocumento() {
        sql = "SELECT DISTINCT DOCUMENTO_ID, DOC_DESCRIPCION FROM TRA_TIPO_DOCUMENTO WHERE ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarRegimenFiscal() {
        sql = "SELECT DISTINCT REGIMEN_ID, ALIAS_REG, REG_DESCRIPCION FROM TRA_REGIMEN_FISCAL WHERE ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarDescripcion(String cve) {
        sql = "SELECT DISTINCT REL_CLVSAT_ID, CODIGO, DESCRIPCION FROM TRA_PRODUCTOS_SERVICIOS WHERE ESTADO_ID = 1 AND TIPO_REGISTRO IN (1,2) AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarDescripcionCartaPorte(String cve) {
        sql = "SELECT DISTINCT REL_CLVSAT_ID, CODIGO, DESCRIPCION FROM TRA_PRODUCTOS_SERVICIOS WHERE ESTADO_ID = 1 AND TIPO_REGISTRO = 1 AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarMetodoPago() {
        sql = "SELECT METODO_ID, ALIAS_METODO, METODO_DESCRIPCION FROM TRA_METODO_PAGO WHERE ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarFormaPago() {
        sql = "SELECT IDENTIFICADOR_ID, FORMA_DESCRIPCION FROM TRA_FORMA_PAGO";
        return sql;
    }
    
    public String consultarFormaPagoPlebes() {
        sql = "SELECT IDENTIFICADOR_ID, FORMA_DESCRIPCION FROM TRA_FORMA_PAGO WHERE IDENTIFICADOR_ID IN (01, 03, 04, 28)";
        return sql;
    }
    
    public String consultarMoneda() {
        sql = "SELECT MONEDA_ID, MONEDA_DESCRIPCION FROM TRA_TIPO_MONEDA WHERE ESTADO_ID = 1";
        return sql;
    }
    
    
    public String consultarClasificacionSAT() {
        sql = "SELECT CLASIF_ID, CLASIF_DESCRIPCION FROM TRA_CLASIFICACION_SAT WHERE ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarUnidadMedidaSAT() {
        sql = "SELECT DISTINCT CLVU_ID, CLV_UNIDAD_SAT, NOMBRE FROM TRA_UNIDAD_MEDIDA_SAT ORDER BY CLV_UNIDAD_SAT ASC";
        return sql;
    }
    
    public String consultarDivisionSAT(String clasif_id) {
        sql = "SELECT DISTINCT "
            + "TDS.DIVISION_SAT_ID, "
            + "TDS.DIVISION_DESCRIPCION "
            + "FROM TRA_REL_PRODUCT_SERV_SAT TRPS "
            + "INNER JOIN TRA_DIVISION_SAT TDS ON TRPS.DIVISION_SAT_ID = TDS.DIVISION_SAT_ID "
            + "WHERE TRPS.CLASIF_ID = '" + clasif_id + "' "
            + "AND TDS.ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarGrupoSAT(String div_sat_id) {
        sql = "SELECT DISTINCT "
            + "TGS.GRUPO_ID, "
            + "TGS.GRUPO_DESCRIPCION "
            + "FROM TRA_REL_PRODUCT_SERV_SAT TRPS "
            + "INNER JOIN TRA_GRUPO_SAT TGS ON TRPS.GRUPO_ID = TGS.GRUPO_ID "
            + "WHERE TRPS.DIVISION_SAT_ID = '" + div_sat_id + "' "
            + "AND TGS.ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarClaseSAT(String grupo_id) {
        sql = "SELECT DISTINCT "
            + "TCS.CLASE_ID, "
            + "TCS.CLASE_DESCRIPCION "
            + "FROM TRA_REL_PRODUCT_SERV_SAT TRPS "
            + "INNER JOIN TRA_CLASE_SAT TCS ON TRPS.CLASE_ID = TCS.CLASE_ID "
            + "WHERE TRPS.GRUPO_ID = '" + grupo_id + "' "
            + "AND TCS.ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarClavesSAT(String clase_id) {
        sql = "SELECT DISTINCT "
            + "TPSS.CLAVE_SAT, "
            + "TPSS.CONCEPTO_DESCRIPCION "
            + "FROM TRA_REL_PRODUCT_SERV_SAT TRPS "
            + "INNER JOIN TRA_PRODUCTOS_SERVICIOS_SAT TPSS ON TRPS.CONCEPTO_ID = TPSS.CONCEPTO_ID "
            + "WHERE TRPS.CLASE_ID = '" + clase_id + "' "
            + "AND TRPS.ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarTipoRelacionSAT() {
        sql = "SELECT DISTINCT TIPO_REL_ID, TIPO_DESCRIPCION FROM TRA_TIPO_RELACION_SAT WHERE ESTADO_ID = 1";
        return sql;
    }
    
    public String consultarUuidSAT(String cve) {
        sql = "SELECT DISTINCT CLVFACT_ID, COM_UUID, SERIE, FOLIO, TOTAL_GLOBAL FROM TRA_FACTURACION WHERE ESTADO_ID = 1 AND CBDIV_ID = '" + cve + "' AND COM_UUID IS NOT NULL";
        return sql;
    }
    
    public String consultarImpuesto(String tipoImpuesto) {
        
        String impuestoId = "";
        
              if(tipoImpuesto.equals("1")){ impuestoId = "2,3"; }
        else if (tipoImpuesto.equals("2")){ impuestoId = "1,2,3"; }
       
        sql = "SELECT IMPUESTO_ID, IMPUESTO_DESCRIPCION FROM TRA_TIPO_IMPUESTO WHERE IMPUESTO_ID IN (" + impuestoId + ")";
        return sql;
    }
    
    public String consultarTipoFactor(String tipoImpuesto, String idImpuesto) {
        
        String factorId = "";
        
        //Traslado:
        if(tipoImpuesto.equals("1")){ 
                 if(idImpuesto.equals("2")){ factorId = "1,3"; }
            else if(idImpuesto.equals("3")){ factorId = "1,2,3"; }
            
        //Retención:    
        }else if (tipoImpuesto.equals("2")){  
                 if(idImpuesto.equals("1")){ factorId = "1"; }
            else if(idImpuesto.equals("2")){ factorId = "1"; }
            else if(idImpuesto.equals("3")){ factorId = "1,2"; }
        }
       
        sql = "SELECT FACTOR_ID, FACTOR_DESCRIPCION FROM TRA_TIPO_FACTOR WHERE FACTOR_ID IN (" + factorId + ")";
        return sql;
    }
    
    public String consultarTipoTasa() {
     
        sql = "SELECT TASA_ID, TASA_DESCRIPCION FROM TRA_TIPO_TASA";
        return sql;
    }
    
    public String listarImpuestos() {
     
        sql = "SELECT TASA_ID, TASA_DESCRIPCION FROM TRA_TIPO_TASA";
        return sql;
    }
    
    public String tipoImpuesto() {
     
        sql = "SELECT TIPO_ID, TIPO_DESCRIPCION FROM TRA_IMPUESTO";
        return sql;
    }
    
    public String Impuesto() {
     
        sql = "SELECT IMPUESTO_ID, IMPUESTO_DESCRIPCION FROM TRA_TIPO_IMPUESTO";
        return sql;
    }
    
    public String tipoFactor() {
     
        sql = "SELECT FACTOR_ID, FACTOR_DESCRIPCION FROM TRA_TIPO_FACTOR";
        return sql;
    }
    
    public String tasa() {
     
        sql = "SELECT TASA_ID, TASA_DESCRIPCION FROM TRA_TIPO_TASA";
        return sql;
    }
    
    public String tasaIva() {
     
        sql = "SELECT VALOR, TASA_DESCRIPCION FROM TRA_TIPO_TASA WHERE TASA_ID IN (9,13,5)";
        return sql;
    }
    
    public String tasaIeps() {
     
        sql = "SELECT VALOR, TASA_DESCRIPCION FROM TRA_TIPO_TASA WHERE TASA_ID IN (1,2,3,4,5,6,7,8,9,10,11,13,14)";
        return sql;
    }
    
    public String tasaIepsRet() {
     
        sql = "SELECT VALOR, TASA_DESCRIPCION FROM TRA_TIPO_TASA WHERE TASA_ID IN (1,2,3,4,5,6,7,8,9,10,11)";
        return sql;
    }
    
    public String FolioFactura(String serie, String folio, String cve) { 
     
        sql = "SELECT DISTINCT CLVFACT_ID FROM TRA_FACTURACION WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String sellosDigitales(String serie, String folio, String cve) { 
     
        sql = "SELECT DISTINCT NVL(COM_SELLOCFD,'0'), NVL(COM_SELLOSAT,'0'), NVL(SELLO,'0'), NVL(COM_SCHEMALOCATION,'0') FROM TRA_FACTURACION WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String fechaCertificacionSAT(String serie, String folio, String cve) { 
     
        sql = "SELECT DISTINCT NVL(COM_FECHATIMBRADO,0) FROM TRA_FACTURACION WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String fechaEmision(String serie, String folio, String cve) {
        
        sql = "SELECT DISTINCT NVL(FECHA_EMISION,'0'), NVL(HORA_EMISION,'0') FROM TRA_FACTURACION WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String clientesSAT(String cliente_id, String cve) {
        
        sql = "SELECT CLIENTE_DESCRIPCION, RFC, CODIGO_POSTAL FROM TRA_CLIENTE WHERE CLIENTE_ID = " + cliente_id + " AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
        
    public String susclienteSAT(String cliente_id, String cve) {
        
        sql = " SELECT DISTINCT "
            + " TSC.CLIENTE_DESCRIPCION, TSC.RFC, TUC.ALIAS_USO, TUC.USO_DESCRIPCION "
            + " FROM TRA_SUSCLIENTES TSC "
            + " INNER JOIN TRA_USO_CFDI TUC ON TSC.USOCFDI_ID = TUC.USOCFDI_ID "
            + " WHERE TSC.CLIENTE_ID = " + cliente_id + ""
            + " AND TSC.CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String monedaSAT(String moneda_id) {
        
        sql = " SELECT MONEDA_SAT FROM TRA_TIPO_MONEDA WHERE MONEDA_ID = " + moneda_id + "";
        return sql;
    }
    
    public String tipoComprobanteSAT(String comprobante_id) {
        
        sql = " SELECT ALIAS_TIPO, COMP_DESCRIPCION FROM TRA_TIPO_COMPROBANTE WHERE COMPROBANTE_ID = " + comprobante_id + "";
        return sql;
    }
    
    public String MetodoPagoSAT(String metodo_id) {
        
        sql = " SELECT ALIAS_METODO, METODO_DESCRIPCION FROM TRA_METODO_PAGO WHERE METODO_ID = " + metodo_id + "";
        return sql;
    }
    
    public String formaPagoSAT(String forma_id) {
        
        sql = " SELECT IDENTIFICADOR_ID, FORMA_DESCRIPCION FROM TRA_FORMA_PAGO WHERE IDENTIFICADOR_ID = " + forma_id + "";
        return sql;
    }
    
    public String certificadosDigitales(String serie, String folio, String cve) { 
     
        sql = "SELECT DISTINCT NVL(COM_UUID,'0'), NVL(COM_NOCERTIFICADOSAT,'0'), NVL(COM_NOCERTIFICADOSAT,'0') FROM TRA_FACTURACION WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String regimenFiscalSAT(String regimen_id) {
        
        sql = "SELECT DISTINCT ALIAS_REG, REG_DESCRIPCION FROM TRA_REGIMEN_FISCAL WHERE REGIMEN_ID = " + regimen_id + "";
        return sql;
    }
    
    public String desc_tipoImpuesto(String idTipoImpuesto) {
        sql = "SELECT TIPO_DESCRIPCION FROM TRA_IMPUESTO WHERE TIPO_ID = '" + idTipoImpuesto + "' ";
        return sql;
    }
    
    public String idClaveUnidadSat(String unidad_sat_clave) {
        sql = "SELECT DISTINCT CLV_UNIDAD_SAT, NOMBRE FROM TRA_UNIDAD_MEDIDA_SAT WHERE CLV_UNIDAD_SAT = '" + unidad_sat_clave + "' ";
        return sql;
    }
    
    public String idClaveUnidadSatFact(String unidad_sat_clave) {
        sql = "SELECT DISTINCT CLVU_ID FROM TRA_UNIDAD_MEDIDA_SAT WHERE CLV_UNIDAD_SAT = '" + unidad_sat_clave + "' ";
        return sql;
    }
    
    public String desc_impuesto(String idImpuesto) {
        sql = "SELECT IMPUESTO_DESCRIPCION FROM TRA_TIPO_IMPUESTO WHERE IMPUESTO_ID = '" + idImpuesto + "' ";
        return sql;
    }
    
    public String desc_tipoFactor(String idFactor) {
        sql = "SELECT FACTOR_DESCRIPCION FROM TRA_TIPO_FACTOR WHERE FACTOR_ID = '" + idFactor + "' ";
        return sql;
    }
    
    public String desc_tasa(String idTasa) {
        sql = "SELECT TASA_DESCRIPCION FROM TRA_TIPO_TASA WHERE TASA_ID = '" + idTasa + "' ";
        return sql;
    }
    
    public String ubicacion1(String cve) {
        
        sql = "SELECT DISTINCT CLVTL_ID, NVL(NOMBRE,' ') FROM TRA_LUGARES WHERE TIPO_LUGAR = 1 AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String ubicacion2(String cve) {
        
        sql = "SELECT DISTINCT CLVTL_ID, NVL(NOMBRE,' ') FROM TRA_LUGARES WHERE TIPO_LUGAR = 2 AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
     
    public String ubicacion3(String cve) {
        
        sql = "SELECT DISTINCT CLVTL_ID, NVL(NOMBRE,' ') FROM TRA_LUGARES WHERE TIPO_LUGAR = 3 AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String ubicacionesGral(String idUbic, String cve) {
        
        sql = "SELECT DISTINCT "
                + "CLVTL_ID, "
                + "NVL(NOMBRE,' '), "
                + "NVL(RFC,' '), "
                + "NVL(CALLE,' '), "
                + "NVL(NO_INTERIOR,' '), "
                + "NVL(NO_EXTERIOR,' '), "
                + "NVL(CODIGO_POSTAL,0), "
                + "NVL(ESTADO,' '), "
                + "NVL(MUNICIPIO,' '),  "
                + "NVL(COLONIA,' '), "
                + "NVL(REFERENCIA,'.'), "
                + "NVL(LOCALIDAD,' '), "
                + "NVL(PAIS,' '), "
                + "NVL(TRANSINTERNACIONAL, ' '), "
                + "NVL(TIPO_UBICACION,' ')  "
                + "FROM TRA_LUGARES "
                + "WHERE CLVTL_ID = '" + idUbic + "'"
                + "AND CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String configuracionVehicular() {
        
        sql = "SELECT CLAVE_NOMENCLATURA, CONFIG_DESCRIPCION FROM TRA_CONFIG_AUTOTRANSPORTE ORDER BY CLVA_ID ASC";
        return sql;
    }
        
    public String tipoFigura() {
        
        sql = "SELECT CLAVE, FIGURA_DESCRIPCION FROM TRA_FIGURA_TRANSPORTE ORDER BY CLAVE ASC";
        return sql;
    }  
    
    public String tipoPermiso() {
        
        sql = "SELECT CLAVE_IDENTIFICACION, PERMISO_DESCRIPCION FROM TRA_TIPO_PERMISO ORDER BY CLVTP_ID ASC";
        return sql;
    }  
    
    public String tipoRemolque() {
        
        sql = "SELECT CLAVE_TIPO, REMOLQUE_DESCRIPCION FROM TRA_TIPO_REMOLQUE ORDER BY CLVR_ID ASC";
        return sql;
    }   
     
    public String tipoMoneda() {    
        sql = "SELECT MONEDA_SAT, MONEDA_DESCRIPCION FROM TRA_TIPO_MONEDA ORDER BY MONEDA_ID ASC";
        return sql;
    }
    
    public String tipoUnidad() {    
        sql = "SELECT CLV_UNIDAD_SAT, NOMBRE FROM TRA_UNIDAD_MEDIDA_SAT ORDER BY CLVU_ID ASC";
        return sql;
    }
    
    public String consultarCadena(String serie, String folio, String cve) {
        sql = "SELECT RFC, FOLIO, SERIE, COM_UUID FROM TRA_FACTURACION WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarRutaCfdiXML(String serie, String folio, String cve) {
        sql = "SELECT URL_XML FROM TRA_FACTURACION WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarRutaCancelacionXML(String uuid, String cve) {
        sql = "SELECT URL_CANCELACION FROM TRA_CANCELACION WHERE COM_UUID = '" + uuid + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarRutaCfdiPDF(String serie, String folio, String cve) {
        sql = "SELECT URL_PDF_CFDI FROM TRA_FACTURACION WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarRutaCartaPortePDF(String serie, String folio, String cve) {
        sql = "SELECT URL_PDF_CP FROM TRA_FACTURACION WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarRutaReciboDePagoPDF(String serie, String folio, String cve) {
        sql = "SELECT URL_PDF_RDP FROM TRA_FACTURACION WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarRutaTicketsPDF(String serie, String folio, String cve) {
        sql = "SELECT URL_PDF_TICKET FROM TRA_FACTURACION WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String clientes(String id, String cve) {
        sql = "SELECT DISTINCT\n"
                + "CLIENTE_ID,               \n"
                + "CLIENTE_DESCRIPCION,\n"
                + "RFC,               \n"
                + "NOMBRE_COMERCIAL, \n"
                + "CODIGO_CLIENTE,     \n"
                + "NUM_CLIENTE,        \n"
                + "NACIONALIDAD_ID,    \n"
                + "CALLE,              \n"
                + "NUM_EXT,            \n"
                + "NUM_INT,            \n"
                + "COLONIA,            \n"
                + "LOCALIDAD,          \n"
                + "REFERENCIA,         \n"
                + "NUM_REGID_TRIB,     \n"
                + "CODIGO_POSTAL,      \n"
                + "PAIS_ID,            \n"
                + "PROVINCIA_ID,       \n"
                + "ESTADO_ID,          \n"
                + "MUNICIPIO_ID,       \n"
                + "NOMBRE_COMPRADOR,   \n"
                + "METODO_ID,          \n"
                + "FORMA_ID,           \n"
                + "USOCFDI_ID,         \n"
                + "TIPOENVIO_ID,       \n"
                + "REL_CONTAC_ID,      \n"
                + "TELEFONO,           \n"
                + "PAGINA_WEB,         \n"
                + "COMENT_EMAIL_CFDI,  \n"
                + "REL_DATBANC_ID,     \n"
                + "FECHA_REGISTRO,     \n"
                + "ESTATUS_ID,         \n"
                + "CBDIV_ID,           \n"
                + "REGIMEN_ID,         \n"
                + "NOMBRE_CONTACTO1,   \n"
                + "CORREO_CONTACTO1,   \n"
                + "NOMBRE_CONTACTO2,   \n"
                + "CORREO_CONTACTO2,   \n"
                + "NOMBRE_CONTACTO3,   \n"
                + "CORREO_CONTACTO3,   \n"
                + "EMAIL_XML,          \n"
                + "EMAIL_PDF \n"
                + "FROM TRA_SUSCLIENTES"
                + "WHERE CLIENTE_ID = '" + id + "' "
                + "AND CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String productos(String id, String cve) {
        sql = "SELECT DISTINCT\n"
            + "CLVREG_ID,         \n"
            + "TIPO_REGISTRO,     \n"
            + "CODIGO,            \n"
            + "DESCRIPCION,       \n"
            + "VALOR_UNITARIO,    \n"
            + "UNIDAD_MEDIDA,     \n"
            + "IVA_ID,            \n"
            + "IVA_RET,           \n"
            + "IEPSTASA_ID,       \n"
            + "IEPS_CUOTA,        \n"
            + "ISR,               \n"
            + "EPSRET_ID,         \n"
            + "IEPS_RET_CUOTA,    \n"
            + "REL_CLVSAT_ID,     \n"
            + "UNIDAD_SAT_ID,     \n"
            + "FECHA_REGISTRO,    \n"
            + "ESTADO_ID,         \n"
            + "CBDIV_ID,          \n"
            + "ID_TIPO_IMPUESTO,  \n"
            + "ID_IMPUESTO,       \n"
            + "ID_TIPO_FACTOR,    \n"
            + "ID_TIPO_PORCENTAJE,\n"
            + "EXENTO            \n"
            + "FROM TRA_PRODUCTOS_SERVICIOS "
            + "WHERE CLVREG_ID = '" + id + "' "
            + "AND CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String estado() {
        sql = "SELECT DISTINCT ID_ESTADO, ESTADO_DESCRIPCION FROM TRA_ESTADOS ORDER BY ID_ESTADO ASC";
        return sql;
    }
    
    public String municipio() {
        sql = "SELECT MUNICIPIO, ESTADO_DESCRIPCION FROM TRA_MUNICIPIO " 
           //+ "WHERE ESTADO = '" + idEstado + "' "
            + "ORDER BY ESTADO_DESCRIPCION ASC";
        return sql;
    }
    
    public String localidad() {
        sql = "SELECT DISTINCT LOCALIDAD, LOCALIDAD_DESCRIPCION FROM TRA_LOCALIDAD "
            // + "WHERE ESTADO = '" + idEstado + "' "
             + "ORDER BY LOCALIDAD_DESCRIPCION ASC";
        return sql;
    }
    
    public String codigopostal() {
        sql = "SELECT CODIGO_POSTAL FROM TRA_CP "
            //+ "WHERE ESTADO = '" + idEstado + "' "
            + "ORDER BY CODIGO_POSTAL ASC";
        return sql;
    }
    
    public String colonia() {
        
        sql = "SELECT C_COLONIA, COLONIA_DESCRIPCION FROM TRA_COLONIAS "
            //+ "WHERE CODIGO_POSTAL = '" + codigoPostal + "' "
            + "ORDER BY C_COLONIA ASC";
        return sql;
    }
    
    public String unidadMedidaValor(String idConcepto, String cve) {
        sql = "SELECT NVL(UNIDAD_MEDIDA,'0') FROM TRA_PRODUCTOS_SERVICIOS WHERE REL_CLVSAT_ID = '" + idConcepto + "' AND CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String detalleProducto(String id, String cve) {
        sql = " SELECT DISTINCT "
            + " TPS.CLVREG_ID, "
            + " TPS.TIPO_REGISTRO, "
            + " TPS.CODIGO, "
            + " TPS.DESCRIPCION, "
            + " TPS.VALOR_UNITARIO, "
            + " TPS.UNIDAD_MEDIDA, "
            + " TPS.REL_CLVSAT_ID, "
            + " TUMS.NOMBRE "
            + " FROM TRA_PRODUCTOS_SERVICIOS TPS "
            + " INNER JOIN TRA_UNIDAD_MEDIDA_SAT TUMS ON TPS.CLVREG_ID = TUMS.CLVU_ID "
            + " WHERE CLVREG_ID = '" + id + "' "
            + " AND TPS.CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultaCliente(String cve) {
        
        sql =  " SELECT DISTINCT "
              +" NVL(TC.CLIENTE_DESCRIPCION,' '), "
              +" NVL(TC.RFC,' '), "
              +" NVL(TRF.ALIAS_REG,' ')||' - '|| NVL(TRF.REG_DESCRIPCION,' '), "
              +" TC.CALLE ||' '|| NVL(TC.NUM_EXT,' ') ||' '|| NVL(TC.NUM_INT,' ') ||' '|| TC.COLONIA ||' '|| TC.LOCALIDAD ||' '|| TC.CODIGO_POSTAL ||' '|| TC.ESTADO_ID ||' '|| TC.MUNICIPIO_ID, "
              +" NVL(TC.CODIGO_POSTAL,0) "
              +" FROM TRA_CLIENTE TC "
              +" INNER JOIN TRA_REGIMEN_FISCAL TRF ON TC.REGIMEN_ID = TRF.REGIMEN_ID "
              +" WHERE TC.CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarSusCliente(String id, String cve) {
        
        sql =  " SELECT DISTINCT "  
              +" TS.CLIENTE_DESCRIPCION, "
              +" TS.RFC, "
              +" TUC.ALIAS_USO ||' - '|| TUC.USO_DESCRIPCION, "
              +" TS.CALLE ||' '|| NVL(TS.NUM_EXT,' ') ||' '|| NVL(TS.NUM_INT,' ') ||' '|| TS.COLONIA ||' '|| TS.LOCALIDAD ||' '|| TS.CODIGO_POSTAL ||' '|| TS.ESTADO_ID ||' '|| TS.MUNICIPIO_ID, "
              +" TF.REL_CARTAPORTE_ID, "
              +" TF.COM_UUID, "
              +" TF.COM_NOCERTIFICADOSAT, "
              +" TF.COM_NOCERTIFICADOSAT, "
              +" TF.FECHA_EMISION ||' '|| TF.HORA_EMISION, "
              +" TF.COM_RFCPROVCERTIF, "
              +" TF.COM_FECHATIMBRADO "
              +" FROM TRA_FACTURACION TF " 
              +" INNER JOIN TRA_SUSCLIENTES TS ON TF.CLIENTE_ID = TS.CLIENTE_ID "
              +" INNER JOIN TRA_USO_CFDI TUC ON TF.USOCFDI_ID = TUC.USOCFDI_ID "
              +" WHERE TF.CLVFACT_ID = '" + id + "' "
              +" AND TF.CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarMercancias(String id, String cve) {
        
        sql = "SELECT DISTINCT "
            + " TM.MERC_ID, "
            + " TM.MONEDA, "
            + " TM.VALOR_MERCANCIA, "
            + " TM.PESOENKG, "
            + " TM.UNIDAD, "
            + " TM.CLAVE_UNIDAD, "
            + " TM.CANTIDAD, "
            + " TM.DESCRIPCION, "
            + " TM.BIENES_TRANSP "
            + "FROM TRA_FACTURACION TF "
            + "INNER JOIN TRA_CARTA_PORTE TCP ON TF.REL_CARTAPORTE_ID = TCP.REL_CARTAPORTE_ID "
            + "INNER JOIN TRA_MERCANCIA TM ON TCP.REL_MERCANCIAS = TM.REL_MERC_ID "
            + "WHERE TF.CLVFACT_ID = '" + id + "' "
            + " AND TF.CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarinfoCfdi(String id, String cve) {
        sql = " SELECT DISTINCT "
            + " TF.CLIENTE_ID, " 
            + " TS.RFC, "
            + " TS.CLIENTE_DESCRIPCION, " 
            + " SUM(TF.TOTAL_GLOBAL) " 
            + " FROM TRA_FACTURACION TF "
            + " INNER JOIN TRA_SUSCLIENTES TS ON TF.CLIENTE_ID = TS.CLIENTE_ID "
            + " WHERE TF.CLVFACT_ID IN ('" + id + "') "
            + " AND TF.CBDIV_ID = '" + cve + "'"  
            + " GROUP BY TF.CLIENTE_ID, TS.RFC, TS.CLIENTE_DESCRIPCION ";
        return sql;
    }
    
    public String searchAbonosFacturas(String serie, String folio, String cve) { 
        sql = " SELECT DISTINCT "
            + " TDR.IMP_PAGADO "
            + " FROM TRA_DOCUMENTOS_RELACIONADOS TDR "
            + " WHERE TDR.SERIE = '" + serie + "' "
            + " AND TDR.FOLIO = '" + folio + "' "
            + " AND TDR.CBDIV_ID = '" + cve + "' ";
        
        return sql;
    }
    
    public String consultarAbonosFacturas(String serie, String folio, String cve) {
        sql = " SELECT DISTINCT "
            + " NUM_PARCIALIDAD, "
            + " IMP_SALDO_ANT, "
            + " IMP_PAGADO "
            + " FROM TRA_DOCUMENTOS_RELACIONADOS "
            + " WHERE SERIE = '" + serie + "' "
            + " AND FOLIO = '" + folio +"'"
                + " AND CBDIV_ID = '" + cve + "' ";
        
        return sql;
    }
    
    public String consultarConceptos(String cve) {
        sql = "SELECT DISTINCT REL_CLVSAT_ID, CODIGO, DESCRIPCION FROM TRA_PRODUCTOS_SERVICIOS WHERE ESTADO_ID = 1 AND TIPO_REGISTRO = 2 AND CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarFacturaComplemento(String idFacturas, String cve) {
        sql = " SELECT"
            + " TF.COM_UUID, "
            + " TF.SERIE, "
            + " TF.FOLIO, "
            + " TMP.ALIAS_METODO, "
            + " TF.TOTAL_GLOBAL, "
            + " TTM.MONEDA_SAT "
            + " FROM TRA_FACTURACION TF"
            + " INNER JOIN TRA_METODO_PAGO TMP ON TF.METODO_ID = TMP.METODO_ID "
            + " INNER JOIN TRA_TIPO_MONEDA TTM ON TF.MONEDA_ID = TTM.MONEDA_ID "
            + " WHERE TF.CLVFACT_ID IN (" + idFacturas + ") "
            + " AND TF.CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarMonedaDescripcion(String idMoneda) {
        sql = "SELECT MONEDA_SAT FROM TRA_TIPO_MONEDA WHERE MONEDA_ID='" + idMoneda + "' ";
        return sql;
    }
    
    public String consultarFacturasComplemento(String idCliente, String cve) {
        sql = " SELECT DISTINCT "
            + " TF.CLVFACT_ID, "
            + " TS.CLIENTE_DESCRIPCION, "
            + " TO_CHAR(TF.FECHA_EMISION,'dd/mm/yyyy'), "
            + " TF.SERIE, "
            + " TF.FOLIO, "
            + " TMP.ALIAS_METODO, "
            + " TTM.MONEDA_SAT, "
            + " TF.TOTAL_GLOBAL, "
            + " TF.URL_PDF_CFDI "
            + " FROM TRA_FACTURACION TF "
            + " INNER JOIN TRA_SUSCLIENTES TS ON TF.CLIENTE_ID = TS.CLIENTE_ID "
            + " INNER JOIN TRA_METODO_PAGO TMP ON TF.METODO_ID = TMP.METODO_ID "
            + " INNER JOIN TRA_TIPO_MONEDA TTM ON TF.MONEDA_ID = TTM.MONEDA_ID "
            + " WHERE TF.CLIENTE_ID = '" + idCliente + "' "
            + " AND TF.CBDIV_ID = '" + cve + "' "
            + " AND COM_NOCERTIFICADOSAT IS NOT NULL"
            + " ORDER BY TF.CLVFACT_ID DESC ";
        return sql;
    }
    
    public String listarConceptosFrecuentes(String cve) {
        sql = "SELECT DISTINCT REL_CLVSAT_ID, CODIGO, DESCRIPCION FROM TRA_PRODUCTOS_SERVICIOS WHERE ESTADO_ID = 1 AND TIPO_REGISTRO IN (1,2) AND CBDIV_ID = '" + cve + "' AND ROWNUM <= 3 ORDER BY REL_CLVSAT_ID ASC";
        return sql;
    }
    
    public String consultarConceptoDesc(String codigo_concepto, String cve) {
        sql = "SELECT DESCRIPCION FROM TRA_PRODUCTOS_SERVICIOS WHERE CODIGO = '" + codigo_concepto + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarIdTasa(String desc_tasa) {
        sql = "SELECT TASA_ID FROM TRA_TIPO_TASA WHERE VALOR = '" + desc_tasa + "'";
        return sql;
    }
    
    public String consultarFacturasGuardadasPendientesPorTimbrar(String cbdiv_id) { 
        sql = " SELECT DISTINCT "
            + " TF.FECHA_EMISION || ' ' || TF.HORA_EMISION, "
            + " TF.SERIE ||'-'|| TF.FOLIO, " 
            + " TS.CLIENTE_DESCRIPCION, " 
            + " TF.TOTAL_GLOBAL, " 
            + " TTM.MONEDA_DESCRIPCION "
            + " FROM TRA_FACTURACION TF "
            + " INNER JOIN TRA_SUSCLIENTES TS ON TF.CLIENTE_ID = TS.CLIENTE_ID "
            + " INNER JOIN TRA_TIPO_MONEDA TTM ON TF.MONEDA_ID = TTM.MONEDA_ID "
            + " WHERE TF.CBDIV_ID IN (10) "
            + " AND TO_DATE(TF.FECHA_EMISION,'dd/mm/yy') >= to_Date(SYSDATE-90,'dd/mm/yy') "
            + " AND TO_DATE(TF.FECHA_EMISION,'dd/mm/yy') <= to_Date(SYSDATE,'dd/mm/yy') "
            + " AND TF.TIPO_XML = 1 "
            + " AND TF.COM_UUID IS NULL "
            + " ORDER BY TF.SERIE||'-'||TF.FOLIO ASC ";
        return sql;
    }
       
    public String consultarRecibosPagosPendientesPorTimbrar(String cbdiv_id) { 
        sql = " SELECT DISTINCT "
            + " TF.FECHA_EMISION || ' ' || TF.HORA_EMISION, "
            + " TF.SERIE ||'-'|| TF.FOLIO, " 
            + " TS.CLIENTE_DESCRIPCION, " 
            + " TF.TOTAL_GLOBAL, " 
            + " TTM.MONEDA_DESCRIPCION "
            + " FROM TRA_FACTURACION TF "
            + " INNER JOIN TRA_SUSCLIENTES TS ON TF.CLIENTE_ID = TS.CLIENTE_ID "
            + " INNER JOIN TRA_TIPO_MONEDA TTM ON TF.MONEDA_ID = TTM.MONEDA_ID "
            + " WHERE TF.CBDIV_ID IN (10) "
            + " AND TO_DATE(TF.FECHA_EMISION,'dd/mm/yy') >= to_Date(SYSDATE-90,'dd/mm/yy') "
            + " AND TO_DATE(TF.FECHA_EMISION,'dd/mm/yy') <= to_Date(SYSDATE,'dd/mm/yy') "
            + " AND TF.TIPO_XML = 1 "
            + " AND TF.COM_UUID IS NULL "
            + " ORDER BY TF.SERIE||'-'||TF.FOLIO ASC ";
        return sql;
    }
    
    public String clienteLogo(String cve) {
        
        sql = "SELECT URL_LOGO FROM TRA_CLIENTE WHERE CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String listClientes(String cve) {
        sql = " SELECT DISTINCT "
            + " CLIENTE_ID, "
            + " NVL(RFC,'SIN REGISTRO'), "
            + " NVL(CLIENTE_DESCRIPCION,'SIN REGISTRO'), "
            + " NVL(NOMBRE_CONTACTO1,'SIN REGISTRO'), "
            + " NVL(CORREO_CONTACTO1,'SIN REGISTRO'), "
            + " NVL(TELEFONO,0) "
            + " FROM TRA_SUSCLIENTES "
            + " WHERE CBDIV_ID = '" + cve + "' "
            + " AND ESTATUS_ID = 1 "
            + " ORDER BY CLIENTE_ID ASC ";
        return sql;
    }
    
    public String consultarEstados() {
        sql = "SELECT ID_ESTADO, ESTADO_DESCRIPCION FROM TRA_ESTADOS ORDER BY ESTADO_DESCRIPCION ASC";
        return sql;
    }
    
    public String consultarRegimenFiscalAlias() {
        sql = "SELECT DISTINCT ALIAS_REG, REG_DESCRIPCION FROM TRA_REGIMEN_FISCAL WHERE ESTADO_ID = 1 ORDER BY ALIAS_REG ASC";
        return sql;
    }
    
    public String consultarMunicipios(String estado_id) {
        sql = "SELECT MUNICIPIO, ESTADO, ESTADO_DESCRIPCION FROM TRA_MUNICIPIO WHERE ESTADO = '" + estado_id + "' ORDER BY ESTADO_DESCRIPCION ASC";
        return sql;
    }
    
    public String consultarEstadoCp(String cve) {
        sql = "SELECT DISTINCT TC.CODIGO_POSTAL, CP.ESTADO FROM TRA_CLIENTE TC INNER JOIN TRA_CP CP ON TC.CODIGO_POSTAL = CP.CODIGO_POSTAL WHERE TC.CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String clienteEmisor(String cve) {
        sql = "SELECT CLIENTE_ID, CLIENTE_DESCRIPCION, RFC, CODIGO_POSTAL, REGIMEN_ID FROM TRA_CLIENTE WHERE CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarTipoConcepto(String cve, String codigo_concepto) {
        sql = " SELECT DISTINCT TIPO_REGISTRO, NVL(VALOR_UNITARIO,0) FROM TRA_PRODUCTOS_SERVICIOS WHERE CODIGO = '" + codigo_concepto + "' AND CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarPrecioUnitarioProducto(String cve, String concepto_id) {
        sql = " SELECT DISTINCT VALOR_UNITARIO FROM TRA_PRODUCTOS_SERVICIOS WHERE REL_CLVSAT_ID = '" + concepto_id + "' AND CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarDescripcionRegimenFiscalSusCliente(String regimen_desc) {
        sql = " SELECT DISTINCT REGIMEN_ID FROM TRA_REGIMEN_FISCAL WHERE ALIAS_REG = '" + regimen_desc + "' ";
        return sql;
    }
    
    public String consultarFolioImpuesto3_3(String cve) {
        sql = " SELECT NVL(BC.SECUENCIA_REL_IMPUESTO,0)"
            + " FROM ONTMS_CTA_BOD_DIV OCBD "
            + " INNER JOIN BROKER_CUENTA BC ON OCBD.BODEGA_ID = BC.BODEGA_ID "
            + " WHERE OCBD.DIVISION_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarDescripcionUnidadMedidaSat(String regimen_desc) {
        sql = " SELECT DISTINCT CLV_UNIDAD_SAT FROM TRA_UNIDAD_MEDIDA_SAT WHERE CLVU_ID = '" + regimen_desc + "' ";
        return sql;
    }
    
    public String consultarDescRegimenFiscalSusCliente(String regimen_id) {
        sql = " SELECT DISTINCT ALIAS_REG FROM TRA_REGIMEN_FISCAL WHERE REGIMEN_ID = '" + regimen_id + "' ";
        return sql;
    }
    
    public String consultarDescUsoCfdi(String usocfdi_id) {
        sql = " SELECT '('|| USOCFDI_ID ||') '|| ALIAS_USO ||'-'|| USO_DESCRIPCION FROM TRA_USO_CFDI WHERE USOCFDI_ID = '" + usocfdi_id + "' ";
        return sql;
    }
    
    public String consultarDescRegimenFiscal(String regimen_id) {
        sql = " SELECT '('|| REGIMEN_ID ||') '|| ALIAS_REG ||'-'|| REG_DESCRIPCION FROM TRA_REGIMEN_FISCAL WHERE REGIMEN_ID = '" + regimen_id + "' ";
        return sql;
    }
    
    public String consultarDescFormaPago(String forma_id) {
        sql = " SELECT '('|| FORMA_ID ||') '|| IDENTIFICADOR_ID ||'-'|| FORMA_DESCRIPCION FROM TRA_FORMA_PAGO WHERE IDENTIFICADOR_ID = '" + forma_id + "' ";
        return sql;
    }
    
    public String consultarEmailSupport() {
        sql = " SELECT CORREO FROM TRA_CORREO WHERE CORREO_ID IN (20) ";
        return sql;
    }
    
    public String consultarFoliosImpuestosFactura(String codigo_concepto, String concepto_id, String cve) {
        sql = " SELECT REL_IMPUESTO_ID FROM TRA_REL_CONCEPTOS WHERE CONCEPTO_ID = '" + codigo_concepto + "' AND REL_CONCEPTO_ID = '" + concepto_id + "' AND CBDIV_ID = '" + cve + "'";
        return sql;
    }
    
    public String consultarConceptosIngresados(String serie, String folio, String cve) {
        sql = " SELECT DISTINCT "
            + " TRC.REL_CONCEPTO_ID, "                          
            + " TRC.CONCEPTO_ID, " 
            + " UPPER(TRC.DESCRIPCION_CONCEPTO), " 
            + " SUM(NVL(TRC.CANTIDAD,0)), " 
            + " TRC.REL_CLVSAT_ID, " 
            + " TRC.UNIDAD_SAT_ID, " 
            + " SUM(NVL(TRC.PRECIO_UNITARIO,0)), "
            + " SUM(NVL(TRC.IMPORTE_DESCUENTO,0)), " 
            + " SUM(NVL(TRC.IMPORTE,0)), "
            + " NVL(TRC.COMENTARIOS,' ') "
            + " FROM TRA_FACTURACION TF "
            + " INNER JOIN TRA_REL_CONCEPTOS TRC ON TF.REL_CONCEPTO_ID = TRC.REL_CONCEPTO_ID "
            + " WHERE TF.SERIE = '" + serie + "' "
            + " AND TF.FOLIO = '" + folio + "' "
            + " AND TRC.CBDIV_ID = '" + cve + "' "
            + " AND TRC.ESTADO_ID = 0 "
            + " GROUP BY " 
            + " TRC.REL_CONCEPTO_ID, "                          
            + " TRC.CONCEPTO_ID, " 
            + " UPPER(TRC.DESCRIPCION_CONCEPTO), "
            + " TRC.REL_CLVSAT_ID, " 
            + " TRC.UNIDAD_SAT_ID, "
            + " NVL(TRC.COMENTARIOS,' ') "    
            + " ORDER BY "
            + " TRC.REL_CONCEPTO_ID "
            + " DESC ";
        return sql;
    }
    
    public String consultarConceptosIngresadosPDF(String serie, String folio, String cve) {
        sql = " SELECT DISTINCT "
            + " TRC.REL_CONCEPTO_ID, "                          
            + " TRC.CONCEPTO_ID, " 
            + " UPPER(TRC.DESCRIPCION_CONCEPTO), " 
            + " SUM(NVL(TRC.CANTIDAD,0)), " 
            + " TRC.REL_CLVSAT_ID, " 
            + " TRC.UNIDAD_SAT_ID, " 
            + " SUM(NVL(TRC.PRECIO_UNITARIO,0)), "
            + " SUM(NVL(TRC.IMPORTE_DESCUENTO,0)), " 
            + " SUM(NVL(TRC.IMPORTE,0)), "
            + " NVL(TRC.COMENTARIOS,' ') "
            + " FROM TRA_FACTURACION TF "
            + " INNER JOIN TRA_REL_CONCEPTOS TRC ON TF.REL_CONCEPTO_ID = TRC.REL_CONCEPTO_ID "
            + " WHERE TF.SERIE = '" + serie + "' "
            + " AND TF.FOLIO = '" + folio + "' "
            + " AND TRC.CBDIV_ID = '" + cve + "' "
            + " GROUP BY " 
            + " TRC.REL_CONCEPTO_ID, "                          
            + " TRC.CONCEPTO_ID, " 
            + " UPPER(TRC.DESCRIPCION_CONCEPTO), "
            + " TRC.REL_CLVSAT_ID, " 
            + " TRC.UNIDAD_SAT_ID, "
            + " NVL(TRC.COMENTARIOS,' ') "    
            + " ORDER BY "
            + " TRC.REL_CONCEPTO_ID "
            + " DESC ";
        return sql;
    }
    
    public String consultarTotalConceptos(String concepto_id, String cve) {
        sql = " SELECT COUNT(REL_CONCEP_ID) FROM TRA_REL_CONCEPTOS WHERE REL_CONCEPTO_ID = '" + concepto_id + "' AND CBDIV_ID = '" + cve + "' ";
        return sql;
    }
    
    public String consultarCantidadImporteConcepto(String codigo_concepto, String folio_concepto, String cve) {
        sql = " SELECT DISTINCT CANTIDAD FROM TRA_REL_CONCEPTOS WHERE CONCEPTO_ID = '" + codigo_concepto + "' AND REL_CONCEPTO_ID ='" + folio_concepto + "' AND CBDIV_ID = '" + cve + "' AND ESTADO_ID =0 ";
        return sql;
} 
 
    public String consultarBaseImporteImpuesto(String codigo_concepto, String cve) {
        sql = "SELECT TRC.CANTIDAD FROM TRA_REL_CONCEPTOS TRC WHERE TRC.CONCEPTO_ID ='" + codigo_concepto + "' AND TRC.CBDIV_ID = '" + cve + "' AND TRC.ESTADO_ID =0";
        return sql;
    }
    
    public String consultarEventosDetalle() {
        sql= "WITH sum_quantity AS ("
        +"   SELECT shipment_id, container1, SUM(quantity) AS suma"
        +"   FROM tra_inc_gtn_test"
        +"   GROUP BY shipment_id, container1"
        +" )"
        +" SELECT DISTINCT"
        +"   tie.id_evento,"
        +"   NVL(bp.responsable, ' ') AS responsable,"
        +"   gtn.final_destination,"
        +"   tibd.NOMBRE_BD,"
        +"   tid.division_nombre,"
        +"   gtn.shipment_id,"
        +"   gtn.container1,"
        +"   gtn.bl_awb_pro,"
        +"   gtn.load_type,"
        +"   sq.suma,"
        +"   tip1.NOMBRE_POD,"
        +"   TO_CHAR(gtn.est_departure_pol, 'MM/DD/YY') AS est_departure_pol,"
        +"   TO_CHAR(gtn.eta_port_discharge, 'MM/DD/YY') AS eta_real_port,"
        +"   NVL(gtn.max_flete, 0) AS est_eta_dc,"
        +"   'Inbound notification' AS notification_type,"
        +"   tip2.NOMBRE_POL,"
        +"   NVL(taa.agente_aduanal_nombre, ' ') AS agente_aduanal,"
        +"   gtn.plantilla_id,"
        +"   TO_CHAR(gtn.fecha_captura, 'MM/DD/YY') AS fecha_captura,"
        +"   tip1.nombre_pod,"
        +"   tip2.nombre_pol,"
        +"   tibd.nombre_bd,"
        +"   CASE"
        +"     WHEN gtn.load_type = 'LTL' THEN 'LTL'"
        +"     WHEN EXISTS ("
        +"       SELECT 1"
        +"       FROM tra_inc_gtn_test"
        +"       WHERE container1 = gtn.container1"
        +"       HAVING COUNT(DISTINCT brand_division) > 1"
        +"     ) THEN 'FCL / LCL'"
        +"     WHEN gtn.load_type = 'FCL' THEN 'FCL'"
        +"     WHEN gtn.load_type = 'LCL' THEN 'LCL'"
        +"     ELSE '-'"
        +"   END AS estado,"
        +"   NVL(TO_CHAR(gtn.eta_plus2, 'MM/DD/YY'), ' ') AS eta_dc,"
        +"   NVL(TO_CHAR(gtn.eta_plus, 'MM/DD/YY'), ' ') AS eta_dc1,"
        +"   NVL(tie.observaciones, ' ') AS observaciones"
        +" FROM"
        +"   tra_inb_evento tie"
        +"   LEFT JOIN tra_destino_responsable bp ON bp.user_nid = tie.user_nid"
        +"   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id"
        +"   LEFT JOIN tra_inb_pod tip1 ON tip1.id_pod = gtn.pod"
        +"   LEFT JOIN tra_inb_pol tip2 ON tip2.id_pol = gtn.pol"
        +"   LEFT JOIN tra_inb_brand_division tibd ON tibd.id_bd = gtn.brand_division"
        +"   LEFT JOIN tra_inb_agente_aduanal taa ON taa.agente_aduanal_id = tip1.agente_aduanal_id"
        +"   LEFT JOIN tra_inb_division tid ON tid.id_division = gtn.sbu_name"
        +"   LEFT JOIN sum_quantity sq ON sq.shipment_id = gtn.shipment_id AND sq.container1 = gtn.container1"
        +" ORDER BY"
        +"   tie.id_evento";
        return sql;
    }
    
    public String consultarAAEventosDetalle(String UserId){
         sql = " SELECT DISTINCT AGENTE_ADUANAL_ID FROM TRA_INB_USUARIO_AA_RELACION WHERE USER_NID = '" + UserId + "' ";
        return sql;
    }
    
    public String consultarAgenteAduanalCustoms(String UserId){
         sql = " SELECT DISTINCT "
             + " TIAR.AGENTE_ADUANAL_ID, "
             + " TIAR.TRA_PLANTILLA_ID, "
             + " TP.NOMBRE "
             + " FROM TRA_INB_USUARIO_AA_RELACION TIAR "
             + " LEFT JOIN TRA_PLANTILLA TP ON TIAR.TRA_PLANTILLA_ID = TP.ID "
             + " WHERE TIAR.USER_NID = '" + UserId + "'";
        return sql;
    }
    
    public String consultarMultiselectCustoms(String tipoAgente){
         sql = " WITH SUM_QUANTITY AS (SELECT SHIPMENT_ID, CONTAINER1, SUM(QUANTITY) AS SUMA FROM TRA_INC_GTN_TEST GROUP BY SHIPMENT_ID, CONTAINER1) "
             + " SELECT DISTINCT "
        /*1*/ + " TIE.ID_EVENTO, "
        /*2*/ + " NVL(BP.RESPONSABLE, ' ') AS RESPONSABLE, "
        /*3*/ + " GTN.FINAL_DESTINATION, "
        /*4*/ + " GTN.BRAND_DIVISION, "
        /*5*/ + " NVL(TID.DIVISION_NOMBRE,' '), "
        /*6*/ + " GTN.SHIPMENT_ID, "
        /*7*/ + " GTN.CONTAINER1, "
        /*8*/ + " GTN.BL_AWB_PRO, "
        /*9*/ + " GTN.LOAD_TYPE_FINAL, "
        /*10*/ + " SQ.SUMA, "
        /*11*/ + " TIP1.NOMBRE_POD, "
        /*12*/ + " TO_CHAR(GTN.EST_DEPARTURE_POL, 'MM/DD/YYYY') AS EST_DEPARTURE_POL, "
        /*13*/ + " TO_CHAR(GTN.ETA_PORT_DISCHARGE, 'MM/DD/YYYY') AS ETA_REAL_PORT, "
        /*14*/ + " NVL(GTN.MAX_FLETE, 0) AS EST_ETA_DC, "
        /*15*/  + " TO_CHAR(GTN.FECHA_CAPTURA, 'MM/DD/YYYY') AS FECHA_CAPTURA, "
        /*16*/ + " TIP2.NOMBRE_POL, "
        /*17*/ + " NVL(TAA.AGENTE_ADUANAL_NOMBRE, ' ') AS AGENTE_ADUANAL, "
        /*18*/ + " GTN.PLANTILLA_ID, "
        /*19*/ + " SYSDATE AS FECHA_CAPTURAOLD, "
        /*20*/ + " TIP1.NOMBRE_POD, "
        /*21*/ + " TIP2.NOMBRE_POL, "
        /*22*/ + " TIBD.NOMBRE_BD, "
        /*23*/ + " NVL(TO_CHAR(TIE.EST_ETA_DC, 'MM/DD/YYYY'),' '), "
        /*24*/ + " NVL(TO_CHAR(GTN.ETA_PLUS2, 'MM/DD/YYYY'), ' ') AS ETA_DC, "
        /*25*/ + " NVL(TO_CHAR(GTN.ETA_PLUS, 'MM/DD/YYYY'), ' ') AS ETA_DC1, "
        /*26*/ + " NVL(TIE.OBSERVACIONES, ' ') AS OBSERVACIONES, "
        /*27*/ + " TIE.ESTATUS_EVENTO, "
        /*28*/ + " NVL(TIE.REFERENCIA_AA,' '), "
        /*29*/ + " NVL(TO_CHAR(TIE.FECHA_CAPTURA, 'MM/DD/YYYY'),' '), "    
        /*30*/ + " NVL(TIE.PRIORIDAD,' '), "  
        /*31*/ + " NVL(TIC.REFERENCIA_AA,' '), " 
        /*32*/ + " NVL(TIC.PAIS_ORIGEN,' '), "   
        /*33*/ + " NVL(TIC.SIZE_CONTAINER,' '), "   
        /*34*/ + " NVL(TIC.VALOR_USD,' '), "               
        /*35*/ + " NVL(TO_CHAR(TIC.ETA_PORT_OF_DISCHARGE, 'MM/DD/YYYY'),' '), "       
        /*36*/ + " NVL(TIC.AGENTE_ADUANAL,' '), "             
        /*37*/ + " NVL(TIC.PEDIMENTO_A1,' '), "               
        /*38*/ + " NVL(TIC.PEDIMENTO_R1,' '), "           
        /*39*/ + " NVL(TIC.MOTIVO_RECTIFICACION_1,' '), "   
        /*40*/ + " NVL(TIC.PEDIMENTO_R1_2DO,' '), "           
        /*41*/ + " NVL(TIC.MOTIVO_RECTIFICACION_2,' '), "   
        /*42*/ + " NVL(TO_CHAR(TIC.FECHA_RECEPCION_DOCUMENTOS, 'MM/DD/YYYY'),' '), "       
        /*43*/ + " NVL(TIC.RECINTO,' '), "     
        /*44*/ + " NVL(TIC.NAVIERA_FORWARDER,' '), "     
        /*45*/ + " NVL(TIC.BUQUE,' '), "    
        /*46*/ + " NVL(TO_CHAR(TIC.FECHA_REVALID_LIBE_BL, 'MM/DD/YYYY'),' '), "   
        /*47*/ + " NVL(TO_CHAR(TIC.FECHA_PREVIO_ORIGEN, 'MM/DD/YYYY'),' '), "   
        /*48*/ + " NVL(TO_CHAR(TIC.FECHA_PREVIO_DESTINO, 'MM/DD/YYYY'),' '), "   
        /*49*/ + " NVL(TO_CHAR(TIC.FECHA_RESULTADO_PREVIO, 'MM/DD/YYYY'),' '), "   
        /*50*/ + " NVL(TO_CHAR(TIC.PROFORMA_FINAL, 'MM/DD/YYYY'),' '), "           
        /*51*/ + " NVL(TIC.REQUIERE_PERMISO,' '), "    
        /*52*/ + " NVL(TO_CHAR(TIC.FECHA_ENVIO_FICHAS_NOTAS, 'MM/DD/YYYY'),' '), " 
        /*53*/ + " NVL(TO_CHAR(TIC.FEC_RECEPCION_PERMISOS_TRAMIT, 'MM/DD/YYYY'),' '), " 
        /*54*/ + " NVL(TO_CHAR(TIC.FEC_ACT_PERMISOS, 'MM/DD/YYYY'),' '), " 
        /*55*/ + " NVL(TO_CHAR(TIC.FEC_PERM_AUT, 'MM/DD/YYYY'),' '), " 
        /*56*/ + " NVL(TIC.CO_APLIC_PREF_ARANCELARIA,' '), "                     
        /*57*/ + " NVL(TIC.APLIC_PREF_ARANCELARIA_CO,' '), "     
        /*58*/ + " NVL(TIC.REQUIERE_UVA,' '), "                                  
        /*59*/ + " NVL(TIC.REQUIERE_CA,' '), "    
        /*60*/ + " NVL(TO_CHAR(TIC.FECHA_RECEPCION_CA, 'MM/DD/YYYY'),' '), " 
        /*61*/ + " NVL(TIC.NÚMERO_CONSTANCIA_CA,' '), "                         
        /*62*/ + " NVL(TIC.MONTO_CA,' '), "   
        /*63*/ + " NVL(TO_CHAR(TIC.FECHA_DOCUMENTOS_COMPLETOS, 'MM/DD/YYYY'),' '), " 
        /*64*/ + " NVL(TO_CHAR(TIC.FECHA_PAGO_PEDIMENTO, 'MM/DD/YYYY'),' '), " 
        /*65*/ + " NVL(TO_CHAR(TIC.FECHA_SOLICITUD_TRANSPORTE, 'MM/DD/YYYY'),' '), " 
        /*66*/ + " NVL(TO_CHAR(TIC.FECHA_MODULACION, 'MM/DD/YYYY'),' '), " 
        /*67*/ + " NVL(TIC.MODALIDAD_CAMION_TREN,' '), "                  
        /*68*/ + " NVL(TIC.RESULT_MODULACION_VERDE_ROJO,' '), "       
        /*69*/ + " NVL(TO_CHAR(TIC.FECHA_RECONOCIMIENTO, 'MM/DD/YYYY'),' '), " 
        /*70*/ + " NVL(TO_CHAR(TIC.FECHA_LIBERACION, 'MM/DD/YYYY'),' '), "    
        /*71*/ + " NVL(TIC.SELLO_ORIGEN,' '), "                                         
        /*72*/ + " NVL(TIC.SELLO_FINAL,' '), "                
        /*73*/ + " NVL(TO_CHAR(TIC.FECHA_RETENCION_AUTORIDAD, 'MM/DD/YYYY'),' '), "    
        /*74*/ + " NVL(TO_CHAR(TIC.FECHA_LIB_POR_RET_AUT, 'MM/DD/YYYY'),' '), "    
        /*75*/ + " NVL(TEC.DESCRIPCION_ESTADO,' '), "           
        /*76*/ + " NVL(TIC.MOTIVO_ATRASO,' '), "              
        /*77*/ + " NVL(TIC.OBSERVACIONES,' '), "         
        /*78*/ + " NVL(TO_CHAR(TIC.LLEGADA_A_NOVA, 'MM/DD/YYYY'),' '), " 
        /*79*/ + " NVL(TO_CHAR(TIC.LLEGADA_A_GLOBE_TRADE_SD, 'MM/DD/YYYY'),' '), " 
        /*80*/ + " NVL(TIC.ARCHIVO_M,' '), "  
        /*81*/ + " NVL(TO_CHAR(TIC.FECHA_ARCHIVO_M, 'MM/DD/YYYY'),' '), "    
        /*82*/ + " NVL(TO_CHAR(TIC.FECHA_SOLICIT_MANIP, 'MM/DD/YYYY'),' '), "    
        /*83*/ + " NVL(TO_CHAR(TIC.FECHA_VENCIM_MANIP, 'MM/DD/YYYY'),' '), "    
        /*84*/ + " NVL(TO_CHAR(TIC.FECHA_CONFIRM_CLAVE_PEDIM, 'MM/DD/YYYY'),' '), "    
        /*85*/ + " NVL(TO_CHAR(TIC.FECHA_RECEP_INCREMENT, 'MM/DD/YYYY'),' '), "    
        /*86*/ + " NVL(TIC.T_E,' '), "  
        /*87*/ + " NVL(TO_CHAR(TIC.FECHA_VENCIM_INBOUND, 'MM/DD/YYYY'),' '), "                  
        /*88*/ + " NVL(TIC.NO_BULTOS,' '), " 
        /*89*/ + " NVL(TIC.PESO_KG,' '), "  
        /*90*/ + " NVL(TIC.TRANSFERENCIA,' '), "  
        /*91*/ + " NVL(TO_CHAR(TIC.FECHA_INICIO_ETIQUETADO, 'MM/DD/YYYY'),' '), "    
        /*92*/ + " NVL(TO_CHAR(TIC.FECHA_TERMINO_ETIQUETADO, 'MM/DD/YYYY'),' '), "    
        /*93*/ + " NVL(TIC.HORA_TERMINO_ETIQUETADO,' '), "  
        /*94*/ + " NVL(TIC.PROVEEDOR,' '), "  
        /*95*/ + " NVL(TIC.PROVEEDOR_CARGA,' '), " 						 
        /*96*/ + " NVL(TIC.FY,' '), "                                                
        /*97*/ + " NVL(TIC.AGENTE_ADUANAL_ID,0), "                                   
        /*98*/ + " NVL(TIC.PRIORIDAD,' ') "                                     
               + " FROM TRA_INB_EVENTO TIE "
               + " LEFT JOIN TRA_DESTINO_RESPONSABLE BP ON BP.USER_NID = TIE.USER_NID "
               + " INNER JOIN TRA_INC_GTN_TEST GTN ON GTN.PLANTILLA_ID = TIE.PLANTILLA_ID "
               + " LEFT JOIN TRA_INB_POD TIP1 ON TIP1.ID_POD = GTN.POD "
               + " LEFT JOIN TRA_INB_POL TIP2 ON TIP2.ID_POL = GTN.POL "
               + " LEFT JOIN TRA_INB_BRAND_DIVISION TIBD ON TIBD.ID_BD = GTN.BRAND_DIVISION "
               + " LEFT JOIN TRA_INB_AGENTE_ADUANAL TAA ON TAA.AGENTE_ADUANAL_ID = TIP1.AGENTE_ADUANAL_ID "
               + " LEFT JOIN TRA_INB_DIVISION TID ON TID.ID_DIVISION = GTN.SBU_NAME "
               + " LEFT JOIN SUM_QUANTITY SQ ON SQ.SHIPMENT_ID = GTN.SHIPMENT_ID AND SQ.CONTAINER1 = GTN.CONTAINER1 "
               + " LEFT JOIN TRA_INB_CUSTOMS TIC ON GTN.SHIPMENT_ID = TIC.SHIPMENT_ID "
               + " LEFT JOIN TRA_ESTADOS_CUSTOMS TEC ON GTN.ESTATUS = TEC.ID_ESTADO "
                 + " "
               + " WHERE TIE.ESTADO = 1 ";  //ESTATUS EVENTO
               //+ " AND GTN.ESTATUS <>19 "; //ESTATUS SHIPMENT

    if(!tipoAgente.equals("4006")){ //VF GENERAL
          sql += " AND TIP1.AGENTE_ADUANAL_ID IN ('" + tipoAgente + "') ";  
    }else{                         //OTROS
          sql += " ";
    }
          sql += " ORDER BY TIE.ID_EVENTO DESC ";
          
        return sql;
    }

    public String consultarEventosCustoms(String tipoAgente, String tipoFiltro, String id){
         sql = " WITH SUM_QUANTITY AS (SELECT SHIPMENT_ID, CONTAINER1, SUM(QUANTITY) AS SUMA FROM TRA_INC_GTN_TEST GROUP BY SHIPMENT_ID, CONTAINER1) "
             + " SELECT DISTINCT "
        /*1*/ + " TIE.ID_EVENTO, "
        /*2*/ + " NVL(BP.RESPONSABLE, ' ') AS RESPONSABLE, "
        /*3*/ + " GTN.FINAL_DESTINATION, "
        /*4*/ + " GTN.BRAND_DIVISION, "
        /*5*/ + " NVL(TID.DIVISION_NOMBRE,' '), "
        /*6*/ + " GTN.SHIPMENT_ID, "
        /*7*/ + " GTN.CONTAINER1, "
        /*8*/ + " GTN.BL_AWB_PRO, "
        /*9*/ + " GTN.LOAD_TYPE_FINAL, "
        /*10*/ + " SQ.SUMA, "
        /*11*/ + " TIP1.NOMBRE_POD, "
        /*12*/ + " TO_CHAR(GTN.EST_DEPARTURE_POL, 'MM/DD/YYYY') AS EST_DEPARTURE_POL, "
        /*13*/ + " TO_CHAR(GTN.ETA_PORT_DISCHARGE, 'MM/DD/YYYY') AS ETA_REAL_PORT, "
        /*14*/ + " NVL(GTN.MAX_FLETE, 0) AS EST_ETA_DC, "
        /*15*/+ " TO_CHAR(GTN.FECHA_CAPTURA, 'MM/DD/YYYY') AS FECHA_CAPTURA, "//INBOUND NOTIFICATION
        /*16*/ + " TIP2.NOMBRE_POL, "
        /*17*/ + " NVL(TAA.AGENTE_ADUANAL_NOMBRE, ' ') AS AGENTE_ADUANAL, "
        /*18*/ + " GTN.PLANTILLA_ID, "
        /*19*/ + " SYSDATE AS FECHA_CAPTURAOLD, "
        /*20*/ + " TIP1.NOMBRE_POD, "
        /*21*/ + " TIP2.NOMBRE_POL, "
        /*22*/ + " TIBD.NOMBRE_BD, "
        /*23*/ + " NVL(TO_CHAR(GTN.ETA_PLUS2, 'MM/DD/YYYY'), ' ') AS ETA_DC, "
        /*24*/ + " NVL(TO_CHAR(TIE.EST_ETA_DC, 'MM/DD/YYYY'),' '), "
        /*25*/ + " NVL(TO_CHAR(GTN.ETA_PLUS, 'MM/DD/YYYY'), ' ') AS ETA_DC1, "
        /*26*/ + " NVL(TIE.OBSERVACIONES, ' ') AS OBSERVACIONES, "
        /*27*/ + " TIE.ESTATUS_EVENTO, "
        /*28*/ + " NVL(TIE.REFERENCIA_AA,' '), "
        /*29*/ + " NVL(TO_CHAR(TIE.FECHA_CAPTURA, 'MM/DD/YYYY'),' '), "    
        /*30*/ + " NVL(TIE.PRIORIDAD,' '), "  
        /*31*/ + " NVL(TIC.REFERENCIA_AA,' '), " 
        /*32*/ + " NVL(TIC.PAIS_ORIGEN,' '), "   
        /*33*/ + " NVL(TIC.SIZE_CONTAINER,' '), "   
        /*34*/ + " NVL(TIC.VALOR_USD,' '), "               
        /*35*/ + " NVL(TO_CHAR(TIC.ETA_PORT_OF_DISCHARGE, 'MM/DD/YYYY'),' '), "       
        /*36*/ + " NVL(TIC.AGENTE_ADUANAL,' '), "             
        /*37*/ + " NVL(TIC.PEDIMENTO_A1,' '), "               
        /*38*/ + " NVL(TIC.PEDIMENTO_R1,' '), "           
        /*39*/ + " NVL(TIC.MOTIVO_RECTIFICACION_1,' '), "   
        /*40*/ + " NVL(TIC.PEDIMENTO_R1_2DO,' '), "           
        /*41*/ + " NVL(TIC.MOTIVO_RECTIFICACION_2,' '), "   
        /*42*/ + " NVL(TO_CHAR(TIC.FECHA_RECEPCION_DOCUMENTOS, 'MM/DD/YYYY'),' '), "       
        /*43*/ + " NVL(TIC.RECINTO,' '), "     
        /*44*/ + " NVL(TIC.NAVIERA_FORWARDER,' '), "     
        /*45*/ + " NVL(TIC.BUQUE,' '), "    
        /*46*/ + " NVL(TO_CHAR(TIC.FECHA_REVALID_LIBE_BL, 'MM/DD/YYYY'),' '), "   
        /*47*/ + " NVL(TO_CHAR(TIC.FECHA_PREVIO_ORIGEN, 'MM/DD/YYYY'),' '), "   
        /*48*/ + " NVL(TO_CHAR(TIC.FECHA_PREVIO_DESTINO, 'MM/DD/YYYY'),' '), "   
        /*49*/ + " NVL(TO_CHAR(TIC.FECHA_RESULTADO_PREVIO, 'MM/DD/YYYY'),' '), "   
        /*50*/ + " NVL(TO_CHAR(TIC.PROFORMA_FINAL, 'MM/DD/YYYY'),' '), "           
        /*51*/ + " NVL(TIC.REQUIERE_PERMISO,' '), "    
        /*52*/ + " NVL(TO_CHAR(TIC.FECHA_ENVIO_FICHAS_NOTAS, 'MM/DD/YYYY'),' '), " 
        /*53*/ + " NVL(TO_CHAR(TIC.FEC_RECEPCION_PERMISOS_TRAMIT, 'MM/DD/YYYY'),' '), " 
        /*54*/ + " NVL(TO_CHAR(TIC.FEC_ACT_PERMISOS, 'MM/DD/YYYY'),' '), " 
        /*55*/ + " NVL(TO_CHAR(TIC.FEC_PERM_AUT, 'MM/DD/YYYY'),' '), " 
        /*56*/ + " NVL(TIC.CO_APLIC_PREF_ARANCELARIA,' '), "                     
        /*57*/ + " NVL(TIC.APLIC_PREF_ARANCELARIA_CO,' '), "     
        /*58*/ + " NVL(TIC.REQUIERE_UVA,' '), "                                  
        /*59*/ + " NVL(TIC.REQUIERE_CA,' '), "    
        /*60*/ + " NVL(TO_CHAR(TIC.FECHA_RECEPCION_CA, 'MM/DD/YYYY'),' '), " 
        /*61*/ + " NVL(TIC.NÚMERO_CONSTANCIA_CA,' '), "                         
        /*62*/ + " NVL(TIC.MONTO_CA,' '), "   
        /*63*/ + " NVL(TO_CHAR(TIC.FECHA_DOCUMENTOS_COMPLETOS, 'MM/DD/YYYY'),' '), " 
        /*64*/ + " NVL(TO_CHAR(TIC.FECHA_PAGO_PEDIMENTO, 'MM/DD/YYYY'),' '), " 
        /*65*/ + " NVL(TO_CHAR(TIC.FECHA_SOLICITUD_TRANSPORTE, 'MM/DD/YYYY'),' '), " 
        /*66*/ + " NVL(TO_CHAR(TIC.FECHA_MODULACION, 'MM/DD/YYYY'),' '), " 
        /*67*/ + " NVL(TIC.MODALIDAD_CAMION_TREN,' '), "                  
        /*68*/ + " NVL(TIC.RESULT_MODULACION_VERDE_ROJO,' '), "       
        /*69*/ + " NVL(TO_CHAR(TIC.FECHA_RECONOCIMIENTO, 'MM/DD/YYYY'),' '), " 
        /*70*/ + " NVL(TO_CHAR(TIC.FECHA_LIBERACION, 'MM/DD/YYYY'),' '), "    
        /*71*/ + " NVL(TIC.SELLO_ORIGEN,' '), "                                         
        /*72*/ + " NVL(TIC.SELLO_FINAL,' '), "                
        /*73*/ + " NVL(TO_CHAR(TIC.FECHA_RETENCION_AUTORIDAD, 'MM/DD/YYYY'),' '), "    
        /*74*/ + " NVL(TO_CHAR(TIC.FECHA_LIB_POR_RET_AUT, 'MM/DD/YYYY'),' '), "    
        /*75*/ + " NVL(TEC.DESCRIPCION_ESTADO,' '), "           
        /*76*/ + " NVL(TIC.MOTIVO_ATRASO,' '), "              
        /*77*/ + " NVL(TIC.OBSERVACIONES,' '), "         
        /*78*/ + " NVL(TO_CHAR(TIC.LLEGADA_A_NOVA, 'MM/DD/YYYY'),' '), " 
        /*79*/ + " NVL(TO_CHAR(TIC.LLEGADA_A_GLOBE_TRADE_SD, 'MM/DD/YYYY'),' '), " 
        /*80*/ + " NVL(TIC.ARCHIVO_M,' '), "  
        /*81*/ + " NVL(TO_CHAR(TIC.FECHA_ARCHIVO_M, 'MM/DD/YYYY'),' '), "    
        /*82*/ + " NVL(TO_CHAR(TIC.FECHA_SOLICIT_MANIP, 'MM/DD/YYYY'),' '), "    
        /*83*/ + " NVL(TO_CHAR(TIC.FECHA_VENCIM_MANIP, 'MM/DD/YYYY'),' '), "    
        /*84*/ + " NVL(TO_CHAR(TIC.FECHA_CONFIRM_CLAVE_PEDIM, 'MM/DD/YYYY'),' '), "    
        /*85*/ + " NVL(TO_CHAR(TIC.FECHA_RECEP_INCREMENT, 'MM/DD/YYYY'),' '), "    
        /*86*/ + " NVL(TIC.T_E,' '), "  
        /*87*/ + " NVL(TO_CHAR(TIC.FECHA_VENCIM_INBOUND, 'MM/DD/YYYY'),' '), "                  
        /*88*/ + " NVL(TIC.NO_BULTOS,' '), " 
        /*89*/ + " NVL(TIC.PESO_KG,' '), "  
        /*90*/ + " NVL(TIC.TRANSFERENCIA,' '), "  
        /*91*/ + " NVL(TO_CHAR(TIC.FECHA_INICIO_ETIQUETADO, 'MM/DD/YYYY'),' '), "    
        /*92*/ + " NVL(TO_CHAR(TIC.FECHA_TERMINO_ETIQUETADO, 'MM/DD/YYYY'),' '), "    
        /*93*/ + " NVL(TIC.HORA_TERMINO_ETIQUETADO,' '), "  
        /*94*/ + " NVL(TIC.PROVEEDOR,' '), "  
        /*95*/ + " NVL(TIC.PROVEEDOR_CARGA,' '), " 						 
        /*96*/ + " NVL(TIC.FY,' '), "                                                
        /*97*/ + " NVL(TIC.AGENTE_ADUANAL_ID,0), "                                   
        /*98*/ + " NVL(TIC.PRIORIDAD,'No'), "
        /*99*/ + " NVL(GTN.ESTATUS,1), "
       /*100*/ + " NVL(TIC.ESTATUS_SEMAFORO,'0') "                                     
               + " FROM TRA_INB_EVENTO TIE "
               + " LEFT JOIN TRA_DESTINO_RESPONSABLE BP ON BP.USER_NID = TIE.USER_NID "
               + " INNER JOIN TRA_INC_GTN_TEST GTN ON GTN.PLANTILLA_ID = TIE.PLANTILLA_ID "
               + " LEFT JOIN TRA_INB_POD TIP1 ON TIP1.ID_POD = GTN.POD "
               + " LEFT JOIN TRA_INB_POL TIP2 ON TIP2.ID_POL = GTN.POL "
               + " LEFT JOIN TRA_INB_BRAND_DIVISION TIBD ON TIBD.ID_BD = GTN.BRAND_DIVISION "
               + " LEFT JOIN TRA_INB_AGENTE_ADUANAL TAA ON TAA.AGENTE_ADUANAL_ID = TIP1.AGENTE_ADUANAL_ID "
               + " LEFT JOIN TRA_INB_DIVISION TID ON TID.ID_DIVISION = GTN.SBU_NAME "
               + " LEFT JOIN SUM_QUANTITY SQ ON SQ.SHIPMENT_ID = GTN.SHIPMENT_ID AND SQ.CONTAINER1 = GTN.CONTAINER1 "
               + " LEFT JOIN TRA_INB_CUSTOMS TIC ON GTN.SHIPMENT_ID = TIC.SHIPMENT_ID "
               + " LEFT JOIN TRA_ESTADOS_CUSTOMS TEC ON GTN.ESTATUS = TEC.ID_ESTADO "
               + " LEFT JOIN TRA_INB_SEMAFORO TISE ON TIC.SHIPMENT_ID = TISE.SHIPMENT_ID "
               + " WHERE TIE.ESTADO = 1 "
               + " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/mm/yy') >= to_date('01/10/2023','dd/mm/yyyy') " 
               + " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/mm/yy') <= to_date('30/11/2023','dd/mm/yyyy') "
               //+ " AND TIE.ID_EVENTO IN (231215,230960) "
               + " AND tid.division_nombre <> 'No/DSN' "
               + " AND gtn.load_type_final IS NOT NULL "   
               + " AND GTN.ESTATUS <> 19 ";  
         
        if(!tipoAgente.equals("4006")){ //VF GENERAL
          sql += " AND TIP1.AGENTE_ADUANAL_ID IN ('" + tipoAgente + "') ";  
        }
        
        if(tipoFiltro.equals("0")){        //Sin filtros
          sql += "";
        }else if(tipoFiltro.equals("1")){  //Referncia AA
          sql += " AND TIC.REFERENCIA_AA IN (" + id + ") ";
        }else if(tipoFiltro.equals("2")){  //Evento
          sql += " AND TIE.ID_EVENTO IN (" + id + ") ";
        }else if(tipoFiltro.equals("3")){  //Responsable
          sql += " AND BP.RESPONSABLE IN (" + id + ") ";
        }else if(tipoFiltro.equals("4")){  //Final Destination
          sql += " AND GTN.FINAL_DESTINATION IN (" + id + ") ";
        }else if(tipoFiltro.equals("5")){  //Brand-Division
          sql += " AND TIBD.NOMBRE_BD IN (" + id + ") ";
        }else if(tipoFiltro.equals("6")){  //División
          sql += " AND TID.DIVISION_NOMBRE IN (" + id + ") ";
        }else if(tipoFiltro.equals("7")){  //Shipment Id
          sql += " AND GTN.SHIPMENT_ID IN (" + id + ") ";
        }else if(tipoFiltro.equals("8")){  //Container
          sql += " AND GTN.CONTAINER1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("9")){  //BL/AWB/PRO
          sql += " AND GTN.BL_AWB_PRO IN (" + id + ") ";
        }else if(tipoFiltro.equals("10")){ //Load Type
          sql += " AND GTN.LOAD_TYPE IN (" + id + ") ";
        }else if(tipoFiltro.equals("11")){ //Quantity
          sql += " AND SQ.SUMA IN (" + id + ") ";
        }else if(tipoFiltro.equals("12")){ //Pod
          sql += " AND TIP1.NOMBRE_POD IN (" + id + ") ";
        }else if(tipoFiltro.equals("13")){ //Departure Pol 
          sql += " AND GTN.EST_DEPARTURE_POL IN (" + id + ") ";
        }else if(tipoFiltro.equals("14")){ //ETA REAL Port of Discharge
          sql += " AND TO_DATE(trunc(GTN.ETA_PORT_DISCHARGE),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("15")){ //Est. Eta DC
          sql += " AND GTN.ETA_PLUS2 IN (" + id + ") ";
        }else if(tipoFiltro.equals("16")){ //Inbound notification
          sql += "";
        }else if(tipoFiltro.equals("17")){ //Pol
          sql += " AND TIP2.NOMBRE_POL IN (" + id + ") ";
        }else if(tipoFiltro.equals("18")){ //Agente Aduanal
          sql += " AND TAA.AGENTE_ADUANAL_NOMBRE IN (" + id + ") ";
        }else if(tipoFiltro.equals("19")){ //Fecha Mes de Venta
          sql += " AND TO_DATE(trunc(TIE.FECHA_CAPTURA),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("20")){ //Prioridad
          sql += " AND TIC.PRIORIDAD IN (" + id + ") ";
        }else if(tipoFiltro.equals("21")){ // País Origen 
          sql += " AND TIC.PAIS_ORIGEN IN (" + id + ") ";
        }else if(tipoFiltro.equals("22")){ // Size Container
          sql += " AND TIC.SIZE_CONTAINER IN (" + id + ") ";
        }else if(tipoFiltro.equals("23")){ // Valor USD
          sql += " AND TIC.VALOR_USD IN (" + id + ") ";
        }else if(tipoFiltro.equals("24")){ // ETA Port Of Discharge
          sql += " AND TO_DATE(trunc(TIC.ETA_PORT_OF_DISCHARGE),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("25")){ // Agente Aduanal 
          sql += " AND TIC.AGENTE_ADUANAL IN (" + id + ") ";
        }else if(tipoFiltro.equals("26")){ // Pedimento A1 
          sql += " AND TIC.PEDIMENTO_A1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("27")){ // Pedimento R1
          sql += " AND TIC.PEDIMENTO_R1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("28")){ // Motivo Rectificación 1
          sql += " AND TIC.MOTIVO_RECTIFICACION_1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("29")){ // Pedimento R1 (2DO)
          sql += " AND TIC.PEDIMENTO_R1_2DO IN (" + id + ") ";
        }else if(tipoFiltro.equals("30")){ // Motivo Rectificación 2
          sql += " AND TIC.MOTIVO_RECTIFICACION_2 IN (" + id + ") ";
        }else if(tipoFiltro.equals("31")){ // Fecha Recepción Documentos
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECEPCION_DOCUMENTOS),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("32")){ // Recinto
          sql += " AND TIC.RECINTO IN (" + id + ") ";
        }else if(tipoFiltro.equals("33")){ // Naviera/Forwarder
          sql += " AND TIC.NAVIERA_FORWARDER IN (" + id + ") ";
        }else if(tipoFiltro.equals("34")){ // Buque
          sql += " AND TIC.BUQUE IN (" + id + ") ";
        }else if(tipoFiltro.equals("35")){ // Fecha Revalidación/Liberación de BL
          sql += " AND TO_DATE(trunc(TIC.FECHA_REVALID_LIBE_BL),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("36")){ // Fecha Previo Origen
          sql += " AND TO_DATE(trunc(TIC.FECHA_PREVIO_ORIGEN),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("37")){ // Fecha Previo en destino
          sql += " AND TO_DATE(trunc(TIC.FECHA_PREVIO_DESTINO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("38")){ // Fecha Resultado Previo
          sql += " AND TO_DATE(trunc(TIC.FECHA_RESULTADO_PREVIO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("39")){ // Proforma Final 
          sql += " AND TIC.PROFORMA_FINAL IN (" + id + ") ";
        }else if(tipoFiltro.equals("40")){ // Requiere permiso
          sql += " AND TIC.REQUIERE_PERMISO IN (" + id + ") ";
        }else if(tipoFiltro.equals("41")){ // Fecha envío Fichas/notas
          sql += " AND TO_DATE(trunc(TIC.FECHA_ENVIO_FICHAS_NOTAS),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("42")){ // Fec. Recepción de permisos tramit.
          sql += " AND TO_DATE(trunc(TIC.FEC_RECEPCION_PERMISOS_TRAMIT),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("43")){ // Fec. Act Permisos (Inic Vigencia)
          sql += " AND TO_DATE(trunc(TIC.FEC_ACT_PERMISOS),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("44")){ // Fec. Perm. Aut. (Fin de Vigencia) 
          sql += " AND TO_DATE(trunc(TIC.FEC_PERM_AUT),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("45")){ // Cuenta con CO para aplicar preferencia Arancelaria
          sql += " AND TIC.CO_APLIC_PREF_ARANCELARIA IN (" + id + ") ";
        }else if(tipoFiltro.equals("46")){ // Aplico Preferencia Arancelaria 
          sql += " AND TIC.APLIC_PREF_ARANCELARIA_CO IN (" + id + ") ";
        }else if(tipoFiltro.equals("47")){ // Requiere UVA
          sql += " AND TIC.REQUIERE_UVA IN (" + id + ") ";
        }else if(tipoFiltro.equals("48")){ // Requiere CA
          sql += " AND TIC.REQUIERE_CA IN (" + id + ") ";
        }else if(tipoFiltro.equals("49")){ // Fecha Recepción CA
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECEPCION_CA),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("50")){ // Número de Constancia CA 
          sql += " AND IC.NÚMERO_CONSTANCIA_CA IN (" + id + ") ";
        }else if(tipoFiltro.equals("51")){ // Monto CA
          sql += " AND TIC.MONTO_CA IN (" + id + ") ";
        }else if(tipoFiltro.equals("52")){ // Fecha Documentos Completos
          sql += " AND TO_DATE(trunc(TIC.FECHA_DOCUMENTOS_COMPLETOS),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("53")){ // Fecha Pago Pedimento
          sql += " AND TO_DATE(trunc(TIC.FECHA_PAGO_PEDIMENTO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("54")){ // Fecha Solicitud de transporte
          sql += " AND TO_DATE(trunc(TIC.FECHA_SOLICITUD_TRANSPORTE),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("55")){ // Fecha Modulacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_MODULACION),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("56")){ // Modalidad
          sql += " AND TIC.MODALIDAD_CAMION_TREN IN (" + id + ") ";
        }else if(tipoFiltro.equals("57")){ // Resultado Modulacion
          sql += " AND TIC.RESULT_MODULACION_VERDE_ROJO IN (" + id + ") ";
        }else if(tipoFiltro.equals("58")){ // Fecha Reconocimiento
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECONOCIMIENTO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("59")){ // Fecha Liberacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_LIBERACION),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("60")){ // Sello Origen 
          sql += " AND TIC.SELLO_ORIGEN IN (" + id + ") ";
        }else if(tipoFiltro.equals("61")){ // Sello Final
          sql += " AND TIC.SELLO_FINAL IN (" + id + ") ";
        }else if(tipoFiltro.equals("62")){ // Fecha de retencion por la autoridad
          sql += " AND TO_DATE(trunc(TIC.FECHA_RETENCION_AUTORIDAD),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("63")){ // Fec. de liberacion por ret. de la aut.
          sql += " AND TO_DATE(trunc(TIC.FECHA_LIB_POR_RET_AUT),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("64")){ // Estatus de la operación
          sql += " AND TEC.DESCRIPCION_ESTADO IN (" + id + ") ";
        }else if(tipoFiltro.equals("65")){ // Motivo Atraso
          sql += " AND TIC.MOTIVO_ATRASO IN (" + id + ") ";
        }else if(tipoFiltro.equals("66")){ // Observaciones
          sql += " AND TIC.OBSERVACIONES IN (" + id + ") ";
        }else if(tipoFiltro.equals("67")){ // Llegada a NOVA
          sql += " AND TIC.LLEGADA_A_NOVA IN (" + id + ") ";
        }else if(tipoFiltro.equals("68")){ // Llegada a Globe trade SD  
          sql += " AND TIC.LLEGADA_A_GLOBE_TRADE_SD IN (" + id + ") ";
        }else if(tipoFiltro.equals("69")){ // Archivo M 
          sql += " AND TIC.ARCHIVO_M IN (" + id + ") ";
        }else if(tipoFiltro.equals("70")){ // Fecha de Archivo M 
          sql += " AND TO_DATE(trunc(TIC.FECHA_ARCHIVO_M),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("71")){ // Fecha Solicitud de Manipulacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_ARCHIVO_M),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("72")){ // Fecha de vencimiento de Manipulacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_VENCIM_MANIP),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("73")){ // Fecha confirmacion Clave de Pedimento
          sql += " AND TO_DATE(trunc(TIC.FECHA_CONFIRM_CLAVE_PEDIM),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("74")){ // Fecha de Recepcion de Incrementables
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECEP_INCREMENT),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("75")){ // T&E 
          sql += " AND TIC.T_E IN (" + id + ") ";
        }else if(tipoFiltro.equals("76")){ // Fecha de Vencimiento del Inbound  
          sql += " AND TO_DATE(trunc(TIC.FECHA_VENCIM_INBOUND),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("77")){ // No. BULTOS
          sql += " AND TIC.NO_BULTOS IN (" + id + ") ";
        }else if(tipoFiltro.equals("78")){ // Peso (KG)
          sql += " AND TIC.PESO_KG IN (" + id + ") ";
        }else if(tipoFiltro.equals("79")){ // Transferencia 
          sql += " AND TIC.TRANSFERENCIA IN (" + id + ") ";
        }else if(tipoFiltro.equals("80")){ // Fecha Inicio Etiquetado
          sql += " AND TO_DATE(trunc(TIC.FECHA_INICIO_ETIQUETADO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("81")){ // Fecha Termino Etiquetado 
          sql += " AND TO_DATE(trunc(TIC.FECHA_TERMINO_ETIQUETADO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("82")){ // Hora de termino Etiquetado
          sql += " AND TIC.HORA_TERMINO_ETIQUETADO IN (" + id + ") ";
        }else if(tipoFiltro.equals("83")){ // Proveedor
          sql += " AND TIC.PROVEEDOR IN (" + id + ") ";
        }else if(tipoFiltro.equals("84")){ // Proveedor de Carga
          sql += " AND TIC.PROVEEDOR_CARGA IN (" + id + ") ";
        }else if(tipoFiltro.equals("85")){ // FY
          sql += " AND TIC.FY IN (" + id + ") ";
        }
           sql += " ORDER BY tie.id_evento, tibd.nombre_bd ASC ";  
        return sql;
    }
    
    public String consultarReporteCustoms(String tipoAgente, String tipoFiltro, String id){
         sql = " WITH SUM_QUANTITY AS (SELECT SHIPMENT_ID, CONTAINER1, SUM(QUANTITY) AS SUMA FROM TRA_INC_GTN_TEST GROUP BY SHIPMENT_ID, CONTAINER1) "
             + " SELECT DISTINCT "
        /*1*/ + " TIE.ID_EVENTO, "
        /*2*/ + " NVL(BP.RESPONSABLE, ' ') AS RESPONSABLE, "
        /*3*/ + " GTN.FINAL_DESTINATION, "
        /*4*/ + " GTN.BRAND_DIVISION, "
        /*5*/ + " NVL(TID.DIVISION_NOMBRE,' '), "
        /*6*/ + " GTN.SHIPMENT_ID, "
        /*7*/ + " GTN.CONTAINER1, "
        /*8*/ + " GTN.BL_AWB_PRO, "
        /*9*/ + " GTN.LOAD_TYPE_FINAL, "
        /*10*/ + " SQ.SUMA, "
        /*11*/ + " TIP1.NOMBRE_POD, "
        /*12*/ + " TO_CHAR(GTN.EST_DEPARTURE_POL, 'MM/DD/YY') AS EST_DEPARTURE_POL, "
        /*13*/ + " TO_CHAR(GTN.ETA_PORT_DISCHARGE, 'MM/DD/YY') AS ETA_REAL_PORT, "
        /*14*/ + " NVL(GTN.MAX_FLETE, 0) AS EST_ETA_DC, "
        /*15*/ + " 'INBOUND NOTIFICATION' AS NOTIFICATION_TYPE, "
        /*16*/ + " TIP2.NOMBRE_POL, "
        /*17*/ + " NVL(TAA.AGENTE_ADUANAL_NOMBRE, ' ') AS AGENTE_ADUANAL, "
        /*18*/ + " GTN.PLANTILLA_ID, "
        /*19*/ + " TO_CHAR(GTN.FECHA_CAPTURA, 'MM/DD/YY') AS FECHA_CAPTURA, "
        /*20*/ + " TIP1.NOMBRE_POD, "
        /*21*/ + " TIP2.NOMBRE_POL, "
        /*22*/ + " TIBD.NOMBRE_BD, "
        /*23*/ + " NVL(TO_CHAR(TIE.EST_ETA_DC, 'MM/DD/YY'),' '), "
        /*24*/ + " NVL(TO_CHAR(GTN.ETA_PLUS2, 'MM/DD/YY'), ' ') AS ETA_DC, "
        /*25*/ + " NVL(TO_CHAR(GTN.ETA_PLUS, 'MM/DD/YY'), ' ') AS ETA_DC1, "
        /*26*/ + " NVL(TIE.OBSERVACIONES, ' ') AS OBSERVACIONES, "
        /*27*/ + " TIE.ESTATUS_EVENTO, "
        /*28*/ + " NVL(TIE.REFERENCIA_AA,' '), "
        /*29*/ + " NVL(TO_CHAR(TIE.FECHA_CAPTURA, 'MM/DD/YY'),' '), "    
        /*30*/ + " NVL(TIE.PRIORIDAD,' '), "  
        /*31*/ + " NVL(TIC.REFERENCIA_AA,' '), " 
        /*32*/ + " NVL(TIC.PAIS_ORIGEN,' '), "   
        /*33*/ + " NVL(TIC.SIZE_CONTAINER,' '), "   
        /*34*/ + " NVL(TIC.VALOR_USD,' '), "               
        /*35*/ + " NVL(TO_CHAR(TIC.ETA_PORT_OF_DISCHARGE, 'MM/DD/YYYY'),' '), "       
        /*36*/ + " NVL(TIC.AGENTE_ADUANAL,' '), "             
        /*37*/ + " NVL(TIC.PEDIMENTO_A1,' '), "               
        /*38*/ + " NVL(TIC.PEDIMENTO_R1,' '), "           
        /*39*/ + " NVL(TIC.MOTIVO_RECTIFICACION_1,' '), "   
        /*40*/ + " NVL(TIC.PEDIMENTO_R1_2DO,' '), "           
        /*41*/ + " NVL(TIC.MOTIVO_RECTIFICACION_2,' '), "   
        /*42*/ + " NVL(TO_CHAR(TIC.FECHA_RECEPCION_DOCUMENTOS, 'MM/DD/YY'),' '), "       
        /*43*/ + " NVL(TIC.RECINTO,' '), "     
        /*44*/ + " NVL(TIC.NAVIERA_FORWARDER,' '), "     
        /*45*/ + " NVL(TIC.BUQUE,' '), "    
        /*46*/ + " NVL(TO_CHAR(TIC.FECHA_REVALID_LIBE_BL, 'MM/DD/YY'),' '), "   
        /*47*/ + " NVL(TO_CHAR(TIC.FECHA_PREVIO_ORIGEN, 'MM/DD/YY'),' '), "   
        /*48*/ + " NVL(TO_CHAR(TIC.FECHA_PREVIO_DESTINO, 'MM/DD/YY'),' '), "   
        /*49*/ + " NVL(TO_CHAR(TIC.FECHA_RESULTADO_PREVIO, 'MM/DD/YY'),' '), "   
        /*50*/ + " NVL(TO_CHAR(TIC.PROFORMA_FINAL, 'MM/DD/YY'),' '), "           
        /*51*/ + " NVL(TIC.REQUIERE_PERMISO,' '), "    
        /*52*/ + " NVL(TO_CHAR(TIC.FECHA_ENVIO_FICHAS_NOTAS, 'MM/DD/YY'),' '), " 
        /*53*/ + " NVL(TO_CHAR(TIC.FEC_RECEPCION_PERMISOS_TRAMIT, 'MM/DD/YY'),' '), " 
        /*54*/ + " NVL(TO_CHAR(TIC.FEC_ACT_PERMISOS, 'MM/DD/YY'),' '), " 
        /*55*/ + " NVL(TO_CHAR(TIC.FEC_PERM_AUT, 'MM/DD/YY'),' '), " 
        /*56*/ + " NVL(TIC.CO_APLIC_PREF_ARANCELARIA,' '), "                     
        /*57*/ + " NVL(TIC.APLIC_PREF_ARANCELARIA_CO,' '), "     
        /*58*/ + " NVL(TIC.REQUIERE_UVA,' '), "                                  
        /*59*/ + " NVL(TIC.REQUIERE_CA,' '), "    
        /*60*/ + " NVL(TO_CHAR(TIC.FECHA_RECEPCION_CA, 'MM/DD/YY'),' '), " 
        /*61*/ + " NVL(TIC.NÚMERO_CONSTANCIA_CA,' '), "                         
        /*62*/ + " NVL(TIC.MONTO_CA,' '), "   
        /*63*/ + " NVL(TO_CHAR(TIC.FECHA_DOCUMENTOS_COMPLETOS, 'MM/DD/YY'),' '), " 
        /*64*/ + " NVL(TO_CHAR(TIC.FECHA_PAGO_PEDIMENTO, 'MM/DD/YY'),' '), " 
        /*65*/ + " NVL(TO_CHAR(TIC.FECHA_SOLICITUD_TRANSPORTE, 'MM/DD/YY'),' '), " 
        /*66*/ + " NVL(TO_CHAR(TIC.FECHA_MODULACION, 'MM/DD/YY'),' '), " 
        /*67*/ + " NVL(TIC.MODALIDAD_CAMION_TREN,' '), "                  
        /*68*/ + " NVL(TIC.RESULT_MODULACION_VERDE_ROJO,' '), "       
        /*69*/ + " NVL(TO_CHAR(TIC.FECHA_RECONOCIMIENTO, 'MM/DD/YY'),' '), " 
        /*70*/ + " NVL(TO_CHAR(TIC.FECHA_LIBERACION, 'MM/DD/YY'),' '), "    
        /*71*/ + " NVL(TIC.SELLO_ORIGEN,' '), "                                         
        /*72*/ + " NVL(TIC.SELLO_FINAL,' '), "                
        /*73*/ + " NVL(TO_CHAR(TIC.FECHA_RETENCION_AUTORIDAD, 'MM/DD/YY'),' '), "    
        /*74*/ + " NVL(TO_CHAR(TIC.FECHA_LIB_POR_RET_AUT, 'MM/DD/YY'),' '), "    
        /*75*/ + " NVL(TEC.DESCRIPCION_ESTADO,' '), "           
        /*76*/ + " NVL(TIC.MOTIVO_ATRASO,' '), "              
        /*77*/ + " NVL(TIC.OBSERVACIONES,' '), "         
        /*78*/ + " NVL(TO_CHAR(TIC.LLEGADA_A_NOVA, 'MM/DD/YY'),' '), " 
        /*79*/ + " NVL(TO_CHAR(TIC.LLEGADA_A_GLOBE_TRADE_SD, 'MM/DD/YY'),' '), " 
        /*80*/ + " NVL(TIC.ARCHIVO_M,' '), "  
        /*81*/ + " NVL(TO_CHAR(TIC.FECHA_ARCHIVO_M, 'MM/DD/YY'),' '), "    
        /*82*/ + " NVL(TO_CHAR(TIC.FECHA_SOLICIT_MANIP, 'MM/DD/YY'),' '), "    
        /*83*/ + " NVL(TO_CHAR(TIC.FECHA_VENCIM_MANIP, 'MM/DD/YY'),' '), "    
        /*84*/ + " NVL(TO_CHAR(TIC.FECHA_CONFIRM_CLAVE_PEDIM, 'MM/DD/YY'),' '), "    
        /*85*/ + " NVL(TO_CHAR(TIC.FECHA_RECEP_INCREMENT, 'MM/DD/YY'),' '), "    
        /*86*/ + " NVL(TIC.T_E,' '), "  
        /*87*/ + " NVL(TO_CHAR(TIC.FECHA_VENCIM_INBOUND, 'MM/DD/YY'),' '), "                  
        /*88*/ + " NVL(TIC.NO_BULTOS,' '), " 
        /*89*/ + " NVL(TIC.PESO_KG,' '), "  
        /*90*/ + " NVL(TIC.TRANSFERENCIA,' '), "  
        /*91*/ + " NVL(TO_CHAR(TIC.FECHA_INICIO_ETIQUETADO, 'MM/DD/YY'),' '), "    
        /*92*/ + " NVL(TO_CHAR(TIC.FECHA_TERMINO_ETIQUETADO, 'MM/DD/YY'),' '), "    
        /*93*/ + " NVL(TIC.HORA_TERMINO_ETIQUETADO,' '), "  
        /*94*/ + " NVL(TIC.PROVEEDOR,' '), "  
        /*95*/ + " NVL(TIC.PROVEEDOR_CARGA,' '), " 						 
        /*96*/ + " NVL(TIC.FY,' '), "                                                
        /*97*/ + " NVL(TIC.AGENTE_ADUANAL_ID,0), "                                   
        /*98*/ + " NVL(TIC.PRIORIDAD,' '), "
        /*99*/ + " NVL(TIC.ESTATUS_SEMAFORO,'3') "           
               + " FROM TRA_INB_EVENTO TIE "
               + " LEFT JOIN TRA_DESTINO_RESPONSABLE BP ON BP.USER_NID = TIE.USER_NID "
               + " INNER JOIN TRA_INC_GTN_TEST GTN ON GTN.PLANTILLA_ID = TIE.PLANTILLA_ID "
               + " LEFT JOIN TRA_INB_POD TIP1 ON TIP1.ID_POD = GTN.POD "
               + " LEFT JOIN TRA_INB_POL TIP2 ON TIP2.ID_POL = GTN.POL "
               + " LEFT JOIN TRA_INB_BRAND_DIVISION TIBD ON TIBD.ID_BD = GTN.BRAND_DIVISION "
               + " LEFT JOIN TRA_INB_AGENTE_ADUANAL TAA ON TAA.AGENTE_ADUANAL_ID = TIP1.AGENTE_ADUANAL_ID "
               + " LEFT JOIN TRA_INB_DIVISION TID ON TID.ID_DIVISION = GTN.SBU_NAME "
               + " LEFT JOIN SUM_QUANTITY SQ ON SQ.SHIPMENT_ID = GTN.SHIPMENT_ID AND SQ.CONTAINER1 = GTN.CONTAINER1 "
               + " LEFT JOIN TRA_INB_CUSTOMS TIC ON GTN.SHIPMENT_ID = TIC.SHIPMENT_ID "
               + " LEFT JOIN TRA_ESTADOS_CUSTOMS TEC ON GTN.ESTATUS = TEC.ID_ESTADO "
               //+ " LEFT JOIN TRA_INB_SEMAFORO TIS ON TIC.SHIPMENT_ID = TIS.SHIPMENT_ID "   
               + " WHERE TIE.ESTADO = 1 ";  //ESTATUS EVENTO
               //+ " AND GTN.ESTATUS <>19 "; //ESTATUS SHIPMENT
         
        if(!tipoAgente.equals("4006")){ //VF GENERAL
          sql += " AND TIP1.AGENTE_ADUANAL_ID IN ('" + tipoAgente + "') ";  
        }else{                         //OTROS
          sql += "";
        }     
        
        if(tipoFiltro.equals("0")){        //Sin filtros
          sql += "";
        }else if(tipoFiltro.equals("1")){  //Referncia AA
          sql += " AND TIC.REFERENCIA_AA IN (" + id + ") ";
        }else if(tipoFiltro.equals("2")){  //Evento
          sql += " AND TIE.ID_EVENTO IN (" + id + ") ";
        }else if(tipoFiltro.equals("3")){  //Responsable
          sql += " AND BP.RESPONSABLE IN (" + id + ") ";
        }else if(tipoFiltro.equals("4")){  //Final Destination
          sql += " AND GTN.FINAL_DESTINATION IN (" + id + ") ";
        }else if(tipoFiltro.equals("5")){  //Brand-Division
          sql += " AND TIBD.NOMBRE_BD IN (" + id + ") ";
        }else if(tipoFiltro.equals("6")){  //División
          sql += " AND TID.DIVISION_NOMBRE IN (" + id + ") ";
        }else if(tipoFiltro.equals("7")){  //Shipment Id
          sql += " AND GTN.SHIPMENT_ID IN (" + id + ") ";
        }else if(tipoFiltro.equals("8")){  //Container
          sql += " AND GTN.CONTAINER1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("9")){  //BL/AWB/PRO
          sql += " AND GTN.BL_AWB_PRO IN (" + id + ") ";
        }else if(tipoFiltro.equals("10")){ //Load Type
          sql += " AND GTN.LOAD_TYPE IN (" + id + ") ";
        }else if(tipoFiltro.equals("11")){ //Quantity
          sql += " AND SQ.SUMA IN (" + id + ") ";
        }else if(tipoFiltro.equals("12")){ //Pod
          sql += " AND TIP1.NOMBRE_POD IN (" + id + ") ";
        }else if(tipoFiltro.equals("13")){ //Departure Pol 
          sql += " AND GTN.EST_DEPARTURE_POL IN (" + id + ") ";
        }else if(tipoFiltro.equals("14")){ //ETA REAL Port of Discharge
          sql += " AND GTN.ETA_PORT_DISCHARGE IN (" + id + ") ";
        }else if(tipoFiltro.equals("15")){ //Est. Eta DC
          sql += " AND GTN.ETA_PLUS2 IN (" + id + ") ";
        }else if(tipoFiltro.equals("16")){ //Inbound notification
          sql += "";
        }else if(tipoFiltro.equals("17")){ //Pol
          sql += " AND TIP2.NOMBRE_POL IN (" + id + ") ";
        }else if(tipoFiltro.equals("18")){ //Agente Aduanal
          sql += " AND TAA.AGENTE_ADUANAL_NOMBRE IN (" + id + ") ";
        }else if(tipoFiltro.equals("19")){ //Fecha Mes de Venta
          sql += " AND TIE.FECHA_CAPTURA IN (" + id + ") ";
        }else if(tipoFiltro.equals("20")){ //Prioridad
          sql += " AND TIC.PRIORIDAD IN (" + id + ") ";
        }else if(tipoFiltro.equals("21")){ // País Origen 
          sql += " AND TIC.PAIS_ORIGEN IN (" + id + ") ";
        }else if(tipoFiltro.equals("22")){ // Size Container
          sql += " AND TIC.SIZE_CONTAINER IN (" + id + ") ";
        }else if(tipoFiltro.equals("23")){ // Valor USD
          sql += " AND TIC.VALOR_USD IN (" + id + ") ";
        }else if(tipoFiltro.equals("24")){ // ETA Port Of Discharge
          sql += " AND TO_CHAR(TIC.ETA_PORT_OF_DISCHARGE,'MM/DD/YYYY') = TO_DATE('" + id + "','MM/DD/YYYY') ";
        }else if(tipoFiltro.equals("25")){ // Agente Aduanal 
          sql += " AND TIC.AGENTE_ADUANAL IN (" + id + ") ";
        }else if(tipoFiltro.equals("26")){ // Pedimento A1 
          sql += " AND TIC.PEDIMENTO_A1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("27")){ // Pedimento R1
          sql += " AND TIC.PEDIMENTO_R1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("28")){ // Motivo Rectificación 1
          sql += " AND TIC.MOTIVO_RECTIFICACION_1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("29")){ // Pedimento R1 (2DO)
          sql += " AND TIC.PEDIMENTO_R1_2DO IN (" + id + ") ";
        }else if(tipoFiltro.equals("30")){ // Motivo Rectificación 2
          sql += " AND TIC.MOTIVO_RECTIFICACION_2 IN (" + id + ") ";
        }else if(tipoFiltro.equals("31")){ // Fecha Recepción Documentos
          sql += " AND TIC.FECHA_RECEPCION_DOCUMENTOS IN (" + id + ") ";
        }else if(tipoFiltro.equals("32")){ // Recinto
          sql += " AND TIC.RECINTO IN (" + id + ") ";
        }else if(tipoFiltro.equals("33")){ // Naviera/Forwarder
          sql += " AND TIC.NAVIERA_FORWARDER IN (" + id + ") ";
        }else if(tipoFiltro.equals("34")){ // Buque
          sql += " AND TIC.BUQUE IN (" + id + ") ";
        }else if(tipoFiltro.equals("35")){ // Fecha Revalidación/Liberación de BL
          sql += " AND TIC.FECHA_REVALID_LIBE_BL IN (" + id + ") ";
        }else if(tipoFiltro.equals("36")){ // Fecha Previo Origen
          sql += " AND TIC.FECHA_PREVIO_ORIGEN IN (" + id + ") ";
        }else if(tipoFiltro.equals("37")){ // Fecha Previo en destino
          sql += " AND TIC.FECHA_PREVIO_DESTINO IN (" + id + ") ";
        }else if(tipoFiltro.equals("38")){ // Fecha Resultado Previo
          sql += " AND TIC.FECHA_RESULTADO_PREVIO IN (" + id + ") ";
        }else if(tipoFiltro.equals("39")){ // Proforma Final 
          sql += " AND TIC.PROFORMA_FINAL IN (" + id + ") ";
        }else if(tipoFiltro.equals("40")){ // Requiere permiso
          sql += " AND TIC.REQUIERE_PERMISO IN (" + id + ") ";
        }else if(tipoFiltro.equals("41")){ // Fecha envío Fichas/notas
          sql += " AND TIC.FECHA_ENVIO_FICHAS_NOTAS IN (" + id + ") ";
        }else if(tipoFiltro.equals("42")){ // Fec. Recepción de permisos tramit.
          sql += " AND TIC.FEC_RECEPCION_PERMISOS_TRAMIT IN (" + id + ") ";
        }else if(tipoFiltro.equals("43")){ // Fec. Act Permisos (Inic Vigencia)
          sql += " AND TIC.FEC_ACT_PERMISOS IN (" + id + ") ";
        }else if(tipoFiltro.equals("44")){ // Fec. Perm. Aut. (Fin de Vigencia) 
          sql += " AND TIC.FEC_PERM_AUT IN (" + id + ") ";
        }else if(tipoFiltro.equals("45")){ // Cuenta con CO para aplicar preferencia Arancelaria
          sql += " AND TIC.CO_APLIC_PREF_ARANCELARIA IN (" + id + ") ";
        }else if(tipoFiltro.equals("46")){ // Aplico Preferencia Arancelaria 
          sql += " AND TIC.APLIC_PREF_ARANCELARIA_CO IN (" + id + ") ";
        }else if(tipoFiltro.equals("47")){ // Requiere UVA
          sql += " AND TIC.REQUIERE_UVA IN (" + id + ") ";
        }else if(tipoFiltro.equals("48")){ // Requiere CA
          sql += " AND TIC.REQUIERE_CA IN (" + id + ") ";
        }else if(tipoFiltro.equals("49")){ // Fecha Recepción CA
          sql += " AND TIC.FECHA_RECEPCION_CA IN (" + id + ") ";
        }else if(tipoFiltro.equals("50")){ // Número de Constancia CA 
          sql += " AND IC.NÚMERO_CONSTANCIA_CA IN (" + id + ") ";
        }else if(tipoFiltro.equals("51")){ // Monto CA
          sql += " AND TIC.MONTO_CA IN (" + id + ") ";
        }else if(tipoFiltro.equals("52")){ // Fecha Documentos Completos
          sql += " AND TIC.FECHA_DOCUMENTOS_COMPLETOS IN (" + id + ") ";
        }else if(tipoFiltro.equals("53")){ // Fecha Pago Pedimento
          sql += " AND TIC.FECHA_PAGO_PEDIMENTO IN (" + id + ") ";
        }else if(tipoFiltro.equals("54")){ // Fecha Solicitud de transporte
          sql += " AND TIC.FECHA_SOLICITUD_TRANSPORTE IN (" + id + ") ";
        }else if(tipoFiltro.equals("55")){ // Fecha Modulacion
          sql += " AND TIC.FECHA_MODULACION IN (" + id + ") ";
        }else if(tipoFiltro.equals("56")){ // Modalidad
          sql += " AND TIC.MODALIDAD_CAMION_TREN IN (" + id + ") ";
        }else if(tipoFiltro.equals("57")){ // Resultado Modulacion
          sql += " AND TIC.RESULT_MODULACION_VERDE_ROJO IN (" + id + ") ";
        }else if(tipoFiltro.equals("58")){ // Fecha Reconocimiento
          sql += " AND TIC.FECHA_RECONOCIMIENTO IN (" + id + ") ";
        }else if(tipoFiltro.equals("59")){ // Fecha Liberacion
          sql += " AND TIC.FECHA_LIBERACION IN (" + id + ") ";
        }else if(tipoFiltro.equals("60")){ // Sello Origen 
          sql += " AND TIC.SELLO_ORIGEN IN (" + id + ") ";
        }else if(tipoFiltro.equals("61")){ // Sello Final
          sql += " AND TIC.SELLO_FINAL IN (" + id + ") ";
        }else if(tipoFiltro.equals("62")){ // Fecha de retencion por la autoridad
          sql += " AND TIC.FECHA_RETENCION_AUTORIDAD IN (" + id + ") ";
        }else if(tipoFiltro.equals("63")){ // Fec. de liberacion por ret. de la aut.
          sql += " AND TIC.FECHA_LIB_POR_RET_AUT IN (" + id + ") ";
        }else if(tipoFiltro.equals("64")){ // Estatus de la operación
          sql += " AND TEC.DESCRIPCION_ESTADO IN (" + id + ") ";
        }else if(tipoFiltro.equals("65")){ // Motivo Atraso
          sql += " AND TIC.MOTIVO_ATRASO IN (" + id + ") ";
        }else if(tipoFiltro.equals("66")){ // Observaciones
          sql += " AND TIC.OBSERVACIONES IN (" + id + ") ";
        }else if(tipoFiltro.equals("67")){ // Llegada a NOVA
          sql += " AND TIC.LLEGADA_A_NOVA IN (" + id + ") ";
        }else if(tipoFiltro.equals("68")){ // Llegada a Globe trade SD  
          sql += " AND TIC.LLEGADA_A_GLOBE_TRADE_SD IN (" + id + ") ";
        }else if(tipoFiltro.equals("69")){ // Archivo M 
          sql += " AND TIC.ARCHIVO_M IN (" + id + ") ";
        }else if(tipoFiltro.equals("70")){ // Fecha de Archivo M 
          sql += " AND TIC.FECHA_ARCHIVO_M IN (" + id + ") ";
        }else if(tipoFiltro.equals("71")){ // Fecha Solicitud de Manipulacion
          sql += " AND TIC.FECHA_ARCHIVO_M IN (" + id + ") ";
        }else if(tipoFiltro.equals("72")){ // Fecha de vencimiento de Manipulacion
          sql += " AND TIC.FECHA_VENCIM_MANIP IN (" + id + ") ";
        }else if(tipoFiltro.equals("73")){ // Fecha confirmacion Clave de Pedimento
          sql += " AND TIC.FECHA_CONFIRM_CLAVE_PEDIM IN (" + id + ") ";
        }else if(tipoFiltro.equals("74")){ // Fecha de Recepcion de Incrementables
          sql += " AND TIC.FECHA_RECEP_INCREMENT IN (" + id + ") ";
        }else if(tipoFiltro.equals("75")){ // T&E 
          sql += " AND TIC.T_E IN (" + id + ") ";
        }else if(tipoFiltro.equals("76")){ // Fecha de Vencimiento del Inbound  
          sql += " AND TIC.FECHA_VENCIM_INBOUND IN (" + id + ") ";
        }else if(tipoFiltro.equals("77")){ // No. BULTOS
          sql += " AND TIC.NO_BULTOS IN (" + id + ") ";
        }else if(tipoFiltro.equals("78")){ // Peso (KG)
          sql += " AND TIC.PESO_KG IN (" + id + ") ";
        }else if(tipoFiltro.equals("79")){ // Transferencia 
          sql += " AND TIC.TRANSFERENCIA IN (" + id + ") ";
        }else if(tipoFiltro.equals("80")){ // Fecha Inicio Etiquetado
          sql += " AND TIC.FECHA_INICIO_ETIQUETADO IN (" + id + ") ";
        }else if(tipoFiltro.equals("81")){ // Fecha Termino Etiquetado 
          sql += " AND TIC.FECHA_TERMINO_ETIQUETADO IN (" + id + ") "; 
        }else if(tipoFiltro.equals("82")){ // Hora de termino Etiquetado
          sql += " AND TIC.HORA_TERMINO_ETIQUETADO IN (" + id + ") ";
        }else if(tipoFiltro.equals("83")){ // Proveedor
          sql += " AND TIC.PROVEEDOR IN (" + id + ") ";
        }else if(tipoFiltro.equals("84")){ // Proveedor de Carga
          sql += " AND TIC.PROVEEDOR_CARGA IN (" + id + ") ";
        }else if(tipoFiltro.equals("85")){ // FY
          sql += " AND TIC.FY IN (" + id + ") ";
        }
          sql += " ORDER BY NVL(TIC.ESTATUS_SEMAFORO,'3') DESC ";

        return sql;
    }
    
    public String consultarEstatusOperacionCustoms(){
         sql = "SELECT DISTINCT ID_ESTADO, DESCRIPCION_ESTADO FROM TRA_ESTADOS_CUSTOMS WHERE ESTATUS = 1 ORDER BY ID_ESTADO ASC";
        return sql;
    }
    
    public String searchEventos(){
         sql = "SELECT DISTINCT ID_EVENTO FROM TRA_INB_EVENTO WHERE ESTADO = 1 AND USER_NID IS NOT NULL ORDER BY ID_EVENTO ASC";
        return sql;
    }
    
    public String searchShipment(){
         sql = "SELECT DISTINCT SHIPMENT_ID FROM TRA_INB_EVENTO WHERE ESTADO = 1 AND USER_NID IS NOT NULL ORDER BY SHIPMENT_ID ASC";
        return sql;
    }
    
    public String searchContainer(){
         sql = "SELECT DISTINCT CONTAINER1 FROM TRA_INB_EVENTO WHERE ESTADO = 1 AND USER_NID IS NOT NULL ORDER BY CONTAINER1 ASC";
        return sql;
    }
    
    public String searchReferenciaAA(){
         sql = "";
        return sql;
    }
    
    public String searchResponsable(){
         sql = "SELECT DISTINCT RESPONSABLE FROM TRA_DESTINO_RESPONSABLE WHERE ESTATUS = 1 ORDER BY RESPONSABLE ASC";
        return sql;
    }
    
    public String searchBrandDivision(){
         sql = "SELECT DISTINCT NOMBRE_BD FROM TRA_INB_BRAND_DIVISION WHERE STATUS = 1 ORDER BY NOMBRE_BD ASC";
        return sql;
    }
    
    public String searchDivision(){
         sql = "SELECT DISTINCT SBU_NAME FROM TRA_INB_DNS WHERE STATUS = 1 ORDER BY SBU_NAME ASC";
        return sql;
    }
    
    public String searchShipmentId(){
         sql = "SELECT DISTINCT SHIPMENT_ID FROM TRA_INB_EVENTO WHERE ESTADO = 1 ORDER BY SHIPMENT_ID ASC";
        return sql;
    }
    
    public String searchBlAwbPro(){
         sql = "SELECT DISTINCT BL_AWB_PRO FROM TRA_INC_GTN_TEST WHERE ESTATUS = 1 ORDER BY BL_AWB_PRO ASC";
        return sql;
    }
    
    public String searchLoadType(){
         sql = "";
        return sql;
    }
    
    public String searchQuantity(){
         sql = "SELECT DISTINCT QUANTITY FROM TRA_INC_GTN_TEST WHERE ESTATUS = 1 ORDER BY QUANTITY ASC";
        return sql;
    }
    
    public String searchPod(){
         sql = "SELECT DISTINCT NOMBRE_POD FROM TRA_INB_POD WHERE STATUS = 1 ORDER BY NOMBRE_POD ASC";
        return sql;
    }
    
    public String searchDepartureFromPOL(){
         sql = "SELECT DISTINCT NVL(TO_CHAR(EST_DEPARTURE_POL, 'dd/mm/yyyy'),' ') FROM TRA_INC_GTN_TEST WHERE ESTATUS = 1 ORDER BY NVL(TO_CHAR(EST_DEPARTURE_POL, 'dd/mm/yyyy'),' ') ASC";
        return sql;
    }
    
    public String searchRealPortDischarge(){
         sql = "SELECT DISTINCT NVL(TO_CHAR(ETA_PORT_DISCHARGE, 'dd/mm/yyyy'),' ') FROM TRA_INC_GTN_TEST WHERE ESTATUS = 1 ORDER BY NVL(TO_CHAR(ETA_PORT_DISCHARGE, 'dd/mm/yyyy'),' ') ASC";
        return sql;
    }
    
    public String searchEtaDC(){
         sql = "SELECT DISTINCT NVL(TO_CHAR(EST_ETA_DC, 'dd/mm/yyyy'),' ') FROM TRA_INB_EVENTO WHERE ESTADO = 1 ORDER BY NVL(TO_CHAR(EST_ETA_DC, 'dd/mm/yyyy'),' ') ASC";
        return sql;
    }
    
    public String searchInboundNotificacion(){
         sql = "";
        return sql;
    }
    
    public String searchPol(){
         sql = "SELECT DISTINCT NOMBRE_POL FROM TRA_INB_POL WHERE STATUS = 1 ORDER BY NOMBRE_POL ASC";
        return sql;
    }
    
    public String searchAA(){
         sql = "SELECT DISTINCT AGENTE_ADUANAL_NOMBRE FROM TRA_INB_AGENTE_ADUANAL WHERE ESTATUS = 1 ORDER BY AGENTE_ADUANAL_NOMBRE ASC";
        return sql;
    }
    
    public String searchFechaMesVenta(){
         sql = "SELECT DISTINCT NVL(TO_CHAR(FECHA_CAPTURA, 'dd/mm/yyyy'),' ') FROM TRA_INB_EVENTO WHERE ESTADO = 1 ORDER BY NVL(TO_CHAR(FECHA_CAPTURA, 'dd/mm/yyyy'),' ') ASC";
        return sql;
    }
    
    public String searchEventosPrioritarios(){
         sql = "";
        return sql;
    }
    
    public String consultarUsuarioName(String userId) {
        sql = " SELECT DISTINCT "
            + " NVL(USER_NAME,' ') "
            + " FROM BROKER_PASSWD "
            + " WHERE USER_NID = '" + userId + "' ";
        return sql;
    }
    
    
    public String consultarEventosDetalleAgenteAduanalExcel(String agenteAduanalId) {
        sql = " WITH sum_quantity AS ( "
            + "   SELECT shipment_id, container1, SUM(quantity) AS suma"
            + "   FROM tra_inc_gtn_test"
            + "   GROUP BY shipment_id, container1"
            + " )"
            + " SELECT DISTINCT"
            + "   tie.id_evento,"
            + "   NVL(bp.responsable, ' ') AS responsable,"
            + "   gtn.final_destination,"
            + "   gtn.brand_division,"
            + "   nvl(tid.division_nombre,' '), "
            + "   gtn.shipment_id,"
            + "   gtn.container1,"
            + "   gtn.bl_awb_pro,"
            + "   gtn.load_type,"
            + "   sq.suma,"
            + "   tip1.NOMBRE_POD,"
            + "   TO_CHAR(gtn.est_departure_pol, 'MM/DD/YY') AS est_departure_pol,"
            + "   TO_CHAR(gtn.eta_port_discharge, 'MM/DD/YY') AS eta_real_port,"
            + "   NVL(gtn.max_flete, 0) AS est_eta_dc,"
            + "   TO_CHAR(tie.FECHA_CAPTURA, 'MM/DD/YY') AS fecha_captura,"
            + "   tip2.NOMBRE_POL,"
            + "   NVL(taa.agente_aduanal_nombre, ' ') AS agente_aduanal,"
            + "   NVL(gtn.plantilla_id,0), "
            + "   TO_CHAR(gtn.fecha_captura, 'MM/DD/YY') AS fecha_captura,"
            + "   tip1.nombre_pod,"
            + "   tip2.nombre_pol,"
            + "   tibd.nombre_bd,"
             + "   CASE"
            + "     WHEN gtn.load_type = 'LTL' THEN 'LTL'"
            + "     WHEN EXISTS ("
            + "       SELECT 1"
           + "       FROM tra_inc_gtn_test"
            + "       WHERE container1 = gtn.container1"
            + "       HAVING COUNT(DISTINCT brand_division) > 1"
            + "          "// and COUNT(DISTINCT gtn.sbu_name)   > 1
            + "     ) THEN 'FCL / LCL'"
            + "  WHEN EXISTS (  SELECT 1  FROM   tra_inc_gtn_test   WHERE  container1 = gtn.container1  HAVING COUNT(DISTINCT sbu_name) > 1   ) THEN 'FCL / LCL'"
            + "  WHEN  gtn.pod IN ('2003','2012','2010') and gtn.MODE1='Truck'  THEN 'LTL'"
            + "  WHEN SUBSTR(gtn.container1, 1, 3) = 'TMW' THEN 'LTL' "
            + "     WHEN gtn.load_type = 'FCL' THEN 'FCL'"
            + "     WHEN gtn.load_type = 'LCL' THEN 'LCL'"
            + "     WHEN gtn.load_type = 'AIR' THEN 'AIR' "
            + "     ELSE '-'"
            + "   END AS estado,"
            + "   NVL(TO_CHAR(gtn.eta_plus2, 'MM/DD/YY'), ' ') AS eta_dc,"
            + "   NVL(TO_CHAR(gtn.eta_plus, 'MM/DD/YY'), ' ') AS eta_dc1,"
            + "   NVL(tie.observaciones, ' ') AS observaciones"
            + " FROM"
            + "   tra_inb_evento tie"
            + "   INNER JOIN tra_destino_responsable bp ON bp.user_nid = tie.user_nid"
            + "   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id"
            + "   INNER JOIN tra_inb_pod tip1 ON tip1.id_pod = gtn.pod"
            + "   INNER JOIN tra_inb_pol tip2 ON tip2.id_pol = gtn.pol"
            + "   INNER JOIN tra_inb_brand_division tibd ON tibd.id_bd = gtn.brand_division"
            + "   INNER JOIN tra_inb_agente_aduanal taa ON taa.agente_aduanal_id = tip1.agente_aduanal_id"
            + "   INNER JOIN tra_inb_division tid ON tid.id_division = gtn.sbu_name"
            + "   INNER JOIN sum_quantity sq ON sq.shipment_id = gtn.shipment_id AND sq.container1 = gtn.container1";
        
        if(!agenteAduanalId.equals("4006")){ //VF
          sql += " WHERE taa.AGENTE_ADUANAL_ID IN ("+agenteAduanalId+") ";       
        }
          sql += " AND TID.DIVISION_NOMBRE <> 'No/DSN' "
               + " AND TIE.ESTATUS_EVENTO IN (0) "
               + " ORDER BY tie.id_evento desc ";
        System.out.println(".."+sql);
        return sql;
    }
    
    
    
    public String consultarEventosDetalleAgenteAduanal(String agenteAduanalId) {
        sql = " WITH sum_quantity AS ( "
            + "   SELECT shipment_id, container1, SUM(quantity) AS suma"
            + "   FROM tra_inc_gtn_test"
            + "   GROUP BY shipment_id, container1"
            + " )"
            + " SELECT DISTINCT"
            + "   tie.id_evento,"
            + "   NVL(bp.responsable, ' ') AS responsable,"
            + "   gtn.final_destination,"
            + "   gtn.brand_division,"
            + "   nvl(tid.division_nombre,' '), "
            + "   gtn.shipment_id,"
            + "   gtn.container1,"
            + "   gtn.bl_awb_pro,"
            + "   gtn.load_type,"
            + "   sq.suma,"
            + "   tip1.NOMBRE_POD,"
            + "   TO_CHAR(gtn.est_departure_pol, 'MM/DD/YY') AS est_departure_pol,"
            + "   TO_CHAR(gtn.eta_port_discharge, 'MM/DD/YY') AS eta_real_port,"
            + "   NVL(gtn.max_flete, 0) AS est_eta_dc,"
            + "   TO_CHAR(tie.FECHA_CAPTURA, 'MM/DD/YY') AS fecha_captura,"
            + "   tip2.NOMBRE_POL,"
            + "   NVL(taa.agente_aduanal_nombre, ' ') AS agente_aduanal,"
            + "   NVL(gtn.plantilla_id,0), "
            + "   TO_CHAR(gtn.fecha_captura, 'MM/DD/YY') AS fecha_captura,"
            + "   tip1.nombre_pod,"
            + "   tip2.nombre_pol,"
            + "   tibd.nombre_bd,"
            + "   CASE"
            + "     WHEN gtn.load_type = 'LTL' THEN 'LTL'"
            + "     WHEN EXISTS ("
            + "       SELECT 1"
            + "       FROM tra_inc_gtn_test"
            + "       WHERE container1 = gtn.container1"
            + "       HAVING COUNT(DISTINCT brand_division) > 1"
            + "     ) THEN 'FCL / LCL'"
            + "     WHEN gtn.load_type = 'FCL' THEN 'FCL'"
            + "     WHEN gtn.load_type = 'LCL' THEN 'LCL'"
            + "     ELSE '-'"
            + "   END AS estado,"
            + "   NVL(TO_CHAR(gtn.eta_plus2, 'MM/DD/YY'), ' ') AS eta_dc,"
            + "   NVL(TO_CHAR(gtn.eta_plus, 'MM/DD/YY'), ' ') AS eta_dc1,"
            + "   NVL(tie.observaciones, ' ') AS observaciones"
            + " FROM"
            + "   tra_inb_evento tie"
            + "   INNER JOIN tra_destino_responsable bp ON bp.user_nid = tie.user_nid"
            + "   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id"
            + "   INNER JOIN tra_inb_pod tip1 ON tip1.id_pod = gtn.pod"
            + "   INNER JOIN tra_inb_pol tip2 ON tip2.id_pol = gtn.pol"
            + "   INNER JOIN tra_inb_brand_division tibd ON tibd.id_bd = gtn.brand_division"
            + "   INNER JOIN tra_inb_agente_aduanal taa ON taa.agente_aduanal_id = tip1.agente_aduanal_id"
            + "   INNER JOIN tra_inb_division tid ON tid.id_division = gtn.sbu_name"
            + "   INNER JOIN sum_quantity sq ON sq.shipment_id = gtn.shipment_id AND sq.container1 = gtn.container1";
        
        if(!agenteAduanalId.equals("4006")){ //VF
          sql += " WHERE taa.AGENTE_ADUANAL_ID IN ("+agenteAduanalId+") ";       
        }
          sql += " AND TID.DIVISION_NOMBRE <> 'No/DSN' "
               + " AND TIE.ESTATUS_EVENTO IN (1) "
               + " ORDER BY tie.id_evento desc ";
        
        return sql;
    }
    
    public String consultarCustomsSemaforo(String agenteAduanalId, String pullShipmentId) {
        sql= "WITH sum_quantity AS ("
            +"   SELECT shipment_id, container1, SUM(quantity) AS suma"
            +"   FROM tra_inc_gtn_test"
            +"   GROUP BY shipment_id, container1"
            +" )"
            +" SELECT DISTINCT"
            +"   tie.id_evento,"
            +"   NVL(bp.responsable, ' ') AS responsable,"
            +"   gtn.final_destination,"
            +"   tibd.NOMBRE_BD,"
            +"   tid.division_nombre,"
            +"   gtn.shipment_id,"
            +"   gtn.container1,"
            +"   gtn.bl_awb_pro,"
            +"   gtn.load_type,"
            +"   sq.suma,"
            +"   tip1.NOMBRE_POD,"
            +"   TO_CHAR(gtn.est_departure_pol, 'MM/DD/YY') AS est_departure_pol,"
            +"   TO_CHAR(gtn.eta_port_discharge, 'MM/DD/YY') AS eta_real_port,"
            +"   NVL(gtn.max_flete, 0) AS est_eta_dc,"
            +"   'Inbound notification' AS notification_type,"
            +"   tip2.NOMBRE_POL, "
            +"   NVL(taa.agente_aduanal_nombre, ' ') AS agente_aduanal,"
            +"   gtn.plantilla_id,"
            +"   TO_CHAR(gtn.fecha_captura, 'MM/DD/YY') AS fecha_captura,"
            +"   tip1.nombre_pod,"
            +"   tip2.nombre_pol,"
            +"   tibd.nombre_bd,"
            +"   CASE"
            +"     WHEN gtn.load_type = 'LTL' THEN 'LTL'"
            +"     WHEN EXISTS ("
            +"       SELECT 1"
            +"       FROM tra_inc_gtn_test"
            +"       WHERE container1 = gtn.container1"
            +"       HAVING COUNT(DISTINCT brand_division) > 1"
            +"     ) THEN 'FCL / LCL'"
            +"     WHEN gtn.load_type = 'FCL' THEN 'FCL'"
            +"     WHEN gtn.load_type = 'LCL' THEN 'LCL'"
            +"     ELSE '-'"
            +"   END AS estado,"
            +"   NVL(TO_CHAR(gtn.eta_plus2, 'MM/DD/YY'), ' ') AS eta_dc,"
            +"   NVL(TO_CHAR(gtn.eta_plus, 'MM/DD/YY'), ' ') AS eta_dc1,"
            +"   NVL(tie.observaciones, ' ') AS observaciones"
            +" FROM"
            +"   tra_inb_evento tie"
            +"   LEFT JOIN tra_destino_responsable bp ON bp.user_nid = tie.user_nid"
            +"   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id"
            +"   LEFT JOIN tra_inb_pod tip1 ON tip1.id_pod = gtn.pod"
            +"   LEFT JOIN tra_inb_pol tip2 ON tip2.id_pol = gtn.pol"
            +"   LEFT JOIN tra_inb_brand_division tibd ON tibd.id_bd = gtn.brand_division"
            +"   LEFT JOIN tra_inb_agente_aduanal taa ON taa.agente_aduanal_id = tip1.agente_aduanal_id"
            +"   LEFT JOIN tra_inb_division tid ON tid.id_division = gtn.sbu_name"
            +"   LEFT JOIN sum_quantity sq ON sq.shipment_id = gtn.shipment_id AND sq.container1 = gtn.container1 "
            +"   INNER JOIN TRA_INB_CUSTOMS TIC ON GTN.SHIPMENT_ID = TIC.SHIPMENT_ID "
            + "  INNER JOIN TRA_INB_SEMAFORO TIS ON TIC.SHIPMENT_ID = TIS.SHIPMENT_ID "
            +"   WHERE TIS.SHIPMENT_ID IN ('" + pullShipmentId + "') ";     
        
        if(!agenteAduanalId.equals("4006")){ //VF
          sql += " AND tip1.AGENTE_ADUANAL_ID IN ("+ agenteAduanalId +") ";
               
        }
          sql += " ORDER BY tie.id_evento ";
        
        return sql;
    }
    
    public String consultarEstatusCustoms() {
        sql = " SELECT "
            + " ID_ESTADO, "
            + " DESCRIPCION_ESTADO "
            + " FROM TRA_ESTADOS_CUSTOMS "
            + " WHERE ESTATUS = 1 "
            + " ORDER BY ID_ESTADO ASC ";
        return sql;
    }
    
    public String consultarFechaSemaforo(String shipmentId) {
        sql = " SELECT DISTINCT "
            + " TO_CHAR(FECHA_ACTIVACION,'MM/DD/YYYY'), "
            + " DIAS_TRANSCURRIDOS, "
            + " LOAD_TYPE_FINAL "
            + " FROM TRA_INB_SEMAFORO "
            + " WHERE SHIPMENT_ID = '" + shipmentId + "' ";
        return sql;
    }
    
    public String consultarInfoGralEmbarqueInbound(String embarque_id, String cbdiv_id) {
        sql = " SELECT DISTINCT "
            + " TIE.EMBARQUE_ID, " 
            + " TO_CHAR(TIE.EMBARQUE_FEC_CAPTURA,'DD/MM/YYYY hh:mi'), "
            + " UPPER(TIE.CHOFER_ID), "
            + " UPPER(TILT.LTRANSPORTE_NOMBRE), "
            + " TIE.EMBARQUE_ESTADO_ID "
            + " FROM TRA_INB_EMBARQUE  TIE "
            + " LEFT JOIN TRA_INB_LINEA_TRANSPORTE TILT ON TIE.EMBARQUE_TRANSPORTISTA  = TILT.LTRANSPORTE_ID "
            + " WHERE TIE.EMBARQUE_ID IN ('"+ embarque_id +"') "
            + " AND TIE.EMBARQUE_ESTADO_ID IN (1,4) ";
        return sql;
    }
    
    public String consultarInfoDetalleEmbarqueInbound(String embarque_id, String cbdiv_id){
        sql = " SELECT DISTINCT "  
            + " TIE.EMBARQUE_ID, "  
            + " TO_CHAR(TIE.EMBARQUE_FEC_CAPTURA, 'DD/MM/YYYY hh:mi'), "
            + " UPPER(TILT.LTRANSPORTE_NOMBRE), "
            + " UPPER(OUT.UTRANSPORTE_DESC), "
            + " UPPER(TIE.CHOFER_ID) "
            + " FROM TRA_INB_EMBARQUE  TIE "  
            + " INNER JOIN TRA_INB_LINEA_TRANSPORTE TILT ON TIE.EMBARQUE_TRANSPORTISTA  = TILT.LTRANSPORTE_ID  " 
            + " INNER JOIN ONTMS_UNIDAD_TRANSPORTE OUT ON TIE.UTRANSPORTE_ID = OUT.UTRANSPORTE_ID "
            + " WHERE TIE.EMBARQUE_ID IN ('" + embarque_id + "') "
            + " AND TIE.EMBARQUE_ESTADO_ID IN (1) ";
      return sql; 
    }
    
    public String consultarDetalleShipmentIdInbound(String embarque_id, String cbdiv_id){
       sql = " SELECT DISTINCT "
           + " TIEV.ID_EVENTO, " 
           + " NVL(TIGT.SHIPMENT_ID,' '), "
           + " NVL(TIGT.CONTAINER1,' '), "
           + " NVL(TIGT.QUANTITY,0), "
           + " NVL(TIP.NOMBRE_POD,' '), "
           + " NVL(TIBD.NOMBRE_BD,' '), "
           + " TIGT.STATUS_EMBARQUE "
           + " FROM TRA_INB_EMBARQUE  TIE "   
           + " INNER JOIN TRA_INC_GTN_TEST TIGT ON TIE.EMBARQUE_AGRUPADOR = TIGT.EMBARQUE_AGRUPADOR " 
           + " INNER JOIN TRA_INB_EVENTO TIEV ON  TIGT.PLANTILLA_ID = TIEV.PLANTILLA_ID " 
           + " INNER JOIN TRA_INB_BRAND_DIVISION TIBD ON TIGT.BRAND_DIVISION = TIBD.ID_BD " 
           + " LEFT JOIN TRA_INB_POD TIP ON TIGT.POD = TIP.ID_POD "
           + " WHERE TIE.EMBARQUE_ID IN ('" + embarque_id + "') "
           + " AND TIE.EMBARQUE_ESTADO_ID IN (1) "
           + " ORDER BY TIGT.STATUS_EMBARQUE ASC ";
      return sql; 
    }
    
    public String consultarEvidenciaPODEmbarqueInbound(String embarque_id, String cbdiv_id){
       sql = " SELECT "
           + " TIGT.SHIPMENT_ID, "
           + " NVL(TIGT.URL_POD,' ') "
           + " FROM TRA_INB_EMBARQUE  TIE "    
           + " INNER JOIN TRA_INC_GTN_TEST TIGT ON TIE.EMBARQUE_AGRUPADOR = TIGT.EMBARQUE_AGRUPADOR "  
           + " INNER JOIN TRA_INB_EVENTO TIEV ON  TIGT.PLANTILLA_ID = TIEV.PLANTILLA_ID "  
           + " WHERE TIE.EMBARQUE_ID IN ('" + embarque_id + "') " 
           + " AND TIE.EMBARQUE_ESTADO_ID IN (1) ";
      return sql; 
    }
    
    public String consultarDetalleEvidenciaEvento(String fechaInicial, String fechaFinal){
        sql = " SELECT DISTINCT "   
            + " TIE.EMBARQUE_ID, "   
            + " TO_CHAR(TIE.EMBARQUE_FEC_CAPTURA, 'DD/MM/YYYY'), " 
            + " UPPER(TILT.LTRANSPORTE_NOMBRE), "
            + " UPPER(OUT.UTRANSPORTE_DESC), " 
            + " UPPER(TIE.CHOFER_ID), "
            + " TIE.EMBARQUE_AGRUPADOR, "
            + " NVL(TIGT.URL_POD,'0'), "
            + " NVL(TIE.URL_CFDI,'0'), "
            + " TIGT.SHIPMENT_ID "
            + " FROM TRA_INB_EMBARQUE  TIE "   
            + " INNER JOIN TRA_INC_GTN_TEST TIGT ON TIE.EMBARQUE_AGRUPADOR = TIGT.EMBARQUE_AGRUPADOR "
            + " INNER JOIN TRA_INB_LINEA_TRANSPORTE TILT ON TIE.EMBARQUE_TRANSPORTISTA  = TILT.LTRANSPORTE_ID "   
            + " INNER JOIN ONTMS_UNIDAD_TRANSPORTE OUT ON TIE.UTRANSPORTE_ID = OUT.UTRANSPORTE_ID  "
            + " AND TO_DATE(trunc(TIE.EMBARQUE_FEC_CAPTURA),'dd/mm/yy') >= TO_DATE('" + fechaInicial + "','dd/mm/yyyy') "
            + " AND TO_DATE(trunc(TIE.EMBARQUE_FEC_CAPTURA),'dd/mm/yy') <= TO_DATE('" + fechaFinal + "','dd/mm/yyyy') "
            + " AND TIE.EMBARQUE_ESTADO_ID IN (4) "
            + " ORDER BY TIE.EMBARQUE_ID ASC ";
      return sql; 
    }
    
    public String consultarEventosNuevos(String agenteAduanal){
        sql = " WITH sum_quantity AS ("
            + "   SELECT shipment_id, container1, SUM(quantity) AS suma"
            + "   FROM tra_inc_gtn_test"
            + "   GROUP BY shipment_id, container1"
            + " )"
            + " SELECT DISTINCT"
            + "   tie.id_evento,"
            + "   NVL(bp.responsable, ' ') AS responsable,"
            + "   gtn.final_destination,"
            + "   gtn.brand_division,"
            + "   nvl(tid.division_nombre,' '), "
            + "   gtn.shipment_id,"
            + "   gtn.container1,"
            + "   gtn.bl_awb_pro,"
            + "   gtn.load_type,"
            + "   sq.suma,"
            + "   tip1.NOMBRE_POD,"
            + "   TO_CHAR(gtn.est_departure_pol, 'MM/DD/YY') AS est_departure_pol,"
            + "   TO_CHAR(gtn.eta_port_discharge, 'MM/DD/YY') AS eta_real_port,"
            + "   NVL(gtn.max_flete, 0) AS est_eta_dc,"
            + "   TO_CHAR(gtn.fecha_captura, 'MM/DD/YY')  AS notification_type,"
            + "   tip2.NOMBRE_POL,"
            + "   NVL(taa.agente_aduanal_nombre, ' ') AS agente_aduanal,"
            + "   NVL(gtn.plantilla_id,0), "
            + "   ' ',"
            + "   tip1.nombre_pod,"
            + "   tip2.nombre_pol,"
            + "   tibd.nombre_bd,"
            + "   CASE"
         
            + "  WHEN EXISTS (  SELECT 1  FROM tra_inc_gtn_test WHERE container1 = gtn.container1  HAVING COUNT(DISTINCT brand_division) > 1  ) THEN 'FCL / LCL'"
            + "  WHEN EXISTS (  SELECT 1  FROM tra_inc_gtn_test WHERE container1 = gtn.container1  HAVING COUNT(DISTINCT sbu_name) > 1   ) THEN 'FCL / LCL'"
            + "  WHEN  gtn.pod IN ('2003','2012','2010') and gtn.MODE1='Truck'  THEN 'LTL'"
            + "  WHEN SUBSTR(gtn.container1, 1, 3) = 'TMW' THEN 'LTL' "
             + "     WHEN gtn.load_type = 'FCL' THEN 'FCL'"
             + "     WHEN gtn.load_type = 'LCL' THEN 'LCL'"
             + "     WHEN gtn.load_type = 'AIR' THEN 'AIR' "
             + "     WHEN gtn.load_type = 'LTL' THEN 'LTL'"
             + "     ELSE '-'"
            + "   END AS estado,"
            + "   NVL(TO_CHAR(gtn.eta_plus2, 'MM/DD/YY'), ' ') AS eta_dc,"
            + "   NVL(TO_CHAR(gtn.eta_plus, 'MM/DD/YY'), ' ') AS eta_dc1,"
            + "   NVL(tie.observaciones, ' ') AS observaciones,"
                + " nvl(gtn.CANTIDAD_FINAL,sq.suma)"
            + " FROM"
            + "   tra_inb_evento tie"
            + "   INNER JOIN tra_destino_responsable bp ON bp.user_nid = tie.user_nid"
            + "   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id"
            + "   INNER JOIN tra_inb_pod tip1 ON tip1.id_pod = gtn.pod"
            + "   INNER JOIN tra_inb_pol tip2 ON tip2.id_pol = gtn.pol"
            + "   INNER JOIN tra_inb_brand_division tibd ON tibd.id_bd = gtn.brand_division"
            + "   INNER JOIN tra_inb_agente_aduanal taa ON taa.agente_aduanal_id = tip1.agente_aduanal_id"
            + "   INNER JOIN tra_inb_division tid ON tid.id_division = gtn.sbu_name"
            + "   INNER JOIN sum_quantity sq ON sq.shipment_id = gtn.shipment_id AND sq.container1 = gtn.container1"
            + " WHERE taa.AGENTE_ADUANAL_ID IN ("+agenteAduanal+") "
            + " AND tie.ESTATUS_EVENTO in (0) "
            + " ORDER BY tie.id_evento desc ";    
        System.out.println("Asignación de Eventos: "+sql);
      return sql; 
    } 
    
    public String consultarEstatusSemaforo(String agenteAduanal) {
        sql = " SELECT DISTINCT "
            + " TIC.NUMERO_DE_EVENTO, "
            + " TIS.SHIPMENT_ID, "
            + " NVL(TIC.PRIORIDAD,'No'), "
            + " TIS.LOAD_TYPE_FINAL, "
            + " NVL(TIS.DIAS_TRANSCURRIDOS,0), "
            + " TIS.ESTATUS_SEMAFORO, "
            + " NVL(TO_CHAR(TIS.FECHA_ACTIVACION, 'MM/DD/YY'),' '), "
            + " NVL(TO_CHAR(TIS.FECHA_TERMINO, 'MM/DD/YY'),' '), "
            + " NVL(TIS.DIAS_CALCULADOS,0), "
            + " NVL(TIS.DAY_LIMIT_GREEN,0), "
            + " NVL(TIS.DAY_LIMIT_YELLOW,0), "
            + " NVL(TIS.DAY_LIMIT_RED,0) "  
            + " FROM TRA_INB_CUSTOMS TIC "
            + " INNER JOIN TRA_INB_SEMAFORO TIS ON TIC.SHIPMENT_ID = TIS.SHIPMENT_ID "
            + " WHERE TIC.AGENTE_ADUANAL_ID IN ("+agenteAduanal+") "
            + " AND TIC.ESTADO_ID IN (1) "
            + " AND NVL(TO_CHAR(TIS.FECHA_ACTIVACION, 'MM/DD/YY'),' ') IS NOT NULL "    
            + " ORDER BY TIS.ESTATUS_SEMAFORO DESC ";
        return sql;
    }
    
    public String consultarEventoFormulario(String evento, String shipment_id, String container) {
        sql = " WITH sum_quantity AS ("
            + "   SELECT shipment_id, container1, SUM(quantity) AS suma"
            + "   FROM tra_inc_gtn_test"
            + "   GROUP BY shipment_id, container1"
            + " )"
            + " SELECT DISTINCT"
            + "   tie.id_evento,"
            + "   NVL(bp.responsable, ' ') AS responsable,"
            + "   gtn.final_destination,"
            + "   gtn.brand_division,"
            + "   nvl(tid.division_nombre,' '), "
            + "   gtn.shipment_id,"
            + "   gtn.container1,"
            + "   gtn.bl_awb_pro,"
            + "   gtn.load_type,"
            + "   sq.suma,"
            + "   gtn.pod," //10
            + "   nvl(to_char(to_date(trim(gtn.est_departure_pol),'dd/mm/yy'),'mm/dd/YYyy'),' ') AS est_departure_pol, "    
            + "   nvl(to_char(to_date(trim(gtn.eta_port_discharge),'dd/mm/yy'),'mm/dd/YYyy'),' ') AS eta_real_port, " 
            + "   NVL(gtn.max_flete, 0) AS est_eta_dc,"
            + "   tie.FECHA_CAPTURA AS notification_type,"
            + "   tip2.NOMBRE_POL,"
            + "   NVL(taa.agente_aduanal_nombre, ' ') AS agente_aduanal,"
            + "   gtn.plantilla_id,"
            + "   nvl(to_char(to_date(trim(gtn.fecha_captura),'dd/mm/yy'),'mm/dd/YYyy'),' ') AS fecha_captura, "     
            + "   tip1.nombre_pod,"
            + "   tip2.nombre_pol,"//20
            + "   tibd.nombre_bd,"
            + "   CASE"
            + "     WHEN gtn.load_type = 'LTL' THEN 'LTL'"
            + "     WHEN EXISTS ("
            + "       SELECT 1"
            + "       FROM tra_inc_gtn_test"
            + "       WHERE container1 = gtn.container1"
            + "       HAVING COUNT(DISTINCT brand_division) > 1"
            + "     ) THEN 'FCL / LCL'"
            + "     WHEN gtn.load_type = 'FCL' THEN 'FCL'"
            + "     WHEN gtn.load_type = 'LCL' THEN 'LCL'"
            + "     ELSE '-'"
            + "   END AS estado,"
            + "   nvl(to_char(to_date(trim(gtn.eta_plus2),'dd/mm/yy'),'mm/dd/YYyy'),' ') AS eta_dc, "  
            + "   nvl(to_char(to_date(trim(gtn.eta_plus),'dd/mm/yy'),'mm/dd/YYyy'),' ') AS eta_dc1, "      
            + "   NVL(tie.observaciones, ' ') AS observaciones,"
            + "   tie.user_nid,"
            + "   gtn.sbu_name," //27
            + "   gtn.pol, "
            + "   nvl(to_char(to_date(trim(gtn.ACTUAL_CRD),'dd/mm/yy'),'mm/dd/YYyy'),' ') AS actual_crd, "   
            + "   nvl(gtn.CANTIDAD_FINAL,0),  "    
            + "   upper(taa.agente_aduanal_nombre) "
                +",gtn.DOCTOS_ADUANEROS, "//32
                +" gtn.TIPO_MATERIA,"
                +" nvl(gtn.clave,' ') "
            + " FROM"
            + "   tra_inb_evento tie"
            + "   LEFT JOIN tra_destino_responsable bp ON bp.user_nid = tie.user_nid"
            + "   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id"
            + "   LEFT JOIN tra_inb_pod tip1 ON tip1.id_pod = gtn.pod"
            + "   LEFT JOIN tra_inb_pol tip2 ON tip2.id_pol = gtn.pol"
            + "   LEFT JOIN tra_inb_brand_division tibd ON tibd.id_bd = gtn.brand_division"
            + "   LEFT JOIN tra_inb_agente_aduanal taa ON taa.agente_aduanal_id = tip1.agente_aduanal_id"
            + "   LEFT JOIN tra_inb_division tid ON tid.id_division = gtn.sbu_name"
            + "   LEFT JOIN sum_quantity sq ON sq.shipment_id = gtn.shipment_id AND sq.container1 = gtn.container1"
            + " where tie.id_evento='"+evento+"' "
            + " and gtn.shipment_id='" + shipment_id + "' "
            + " and  gtn.container1='" + container + "'"
            + " ORDER BY"
            + " tie.id_evento";
        return sql;
    }
    
    public String consultarDivisionFormulario(String division) {
        sql = " SELECT  "
            + " DIVISION_NOMBRE "
            + " FROM TRA_INB_DIVISION "
            + " WHERE ID_DIVISION = '"+division+"' "
            + " AND STATUS = 1";
        return sql;
    }
    
    public String consultarResponsable_me(){
        sql = "select DISTINCT USER_NID,RESPONSABLE from tra_destino_responsable where estatus=1";
        return sql;
    }
    
    public String consultarPol_me(){
        sql = "select ID_POL, NOMBRE_POL from tra_inb_pol";
        return sql;
    }
    
    public String consultarPod_me(){
        sql = "select ID_POD, NOMBRE_POD from tra_inb_pod";
        return sql;
    }
    
    public String consultarBrandDivision_me(){
        sql = "select ID_BD, NOMBRE_BD from tra_inb_brand_division";
        return sql;
    }
    
    public String consultarDivision_me(){
        sql = "select ID_DIVISION, DIVISION_NOMBRE from tra_inb_division";
        return sql;
    }
                
    public String consultarLoadType_me(){
        sql = "SELECT LOAD_TYPE FROM TRA_INB_LOAD_TYPE ORDER BY ID_REG ASC";
        return sql;
    }
    
    public String consultarColorSemaforo(String shipment_id){
        sql = "SELECT DISTINCT ESTATUS_SEMAFORO FROM TRA_INB_SEMAFORO WHERE SHIPMENT_ID = '"+shipment_id+"'";
        return sql;
    }
    
    public String perfilUsuario(String UserId){
        sql = " select DISTINCT "
            + " id_perfil "
            + " from tra_perfil_usuario "
            + " where ID_USUARIO=" + UserId;
        return sql;
    }
    
    public String menus(String perfilUsuario){
        sql = " select "
            + " tm.descripcion, "
            + " tm.url, "
            + " tm.icono, "
            + " tm.id_padre "
            + " from tra_perfil_menu tpm "
            + " inner join tra_menu tm on tm.id=tpm.id_menu "
            + " inner join tra_perfil tp on tp.id_perfil=tpm.id "
            + " where tpm.id=" + perfilUsuario + " "
            + " and tpm.id_menu = tm.id_padre "
            + " order by tm.id";
        return sql;
    }
    
     public String submenus(String perfilUsuario, String idPadre){
        sql = " select "
            + " tpm.id,"
            + " tpm.id_menu, "
            + " tm.id_padre, "
            + " tm.descripcion, "
            + " tm.url, "
            + " tm.icono "
            + " from tra_perfil_menu tpm "
            + " inner join tra_menu tm on tm.id=tpm.id_menu "
            + " inner join tra_perfil tp on tp.id_perfil=tpm.id "
            + " where tpm.id=" + perfilUsuario + " "
            + " and tm.id_padre=" + idPadre + " "
            + " and tpm.id_menu <> tm.id_padre "
            + " order by tm.id ";
        return sql;
    }
     
     
      public String consultarReporteEventosImportados(String tipoAgente, String tipoFiltro, String id, String fechaInicial, String fechaFinal){
         sql = " WITH SUM_QUANTITY AS (SELECT SHIPMENT_ID, CONTAINER1, SUM(QUANTITY) AS SUMA FROM TRA_INC_GTN_TEST GROUP BY SHIPMENT_ID, CONTAINER1) "
             + " SELECT DISTINCT "
        /*1*/ + " TIE.ID_EVENTO, "
        /*2*/ + " NVL(BP.RESPONSABLE, ' ') AS RESPONSABLE, "
        /*3*/ + " GTN.FINAL_DESTINATION, "
        /*4*/ + " GTN.BRAND_DIVISION, "
        /*5*/ + " NVL(TID.DIVISION_NOMBRE,' '), "
        /*6*/ + " GTN.SHIPMENT_ID, "
        /*7*/ + " GTN.CONTAINER1, "
        /*8*/ + " GTN.BL_AWB_PRO, "
        /*9*/ + " GTN.LOAD_TYPE_FINAL, "
        /*10*/ + " SQ.SUMA, "
        /*11*/ + " TIP1.NOMBRE_POD, "
        /*12*/ + " TO_CHAR(GTN.EST_DEPARTURE_POL, 'MM/DD/YYYY') AS EST_DEPARTURE_POL, "
        /*13*/ + " TO_CHAR(GTN.ETA_PORT_DISCHARGE, 'MM/DD/YYYY') AS ETA_REAL_PORT, "
        /*14*/ + " NVL(GTN.MAX_FLETE, 0) AS EST_ETA_DC, "
        /*15*/+ " TO_CHAR(GTN.FECHA_CAPTURA, 'MM/DD/YYYY') AS FECHA_CAPTURA, "//INBOUND NOTIFICATION
        /*16*/ + " TIP2.NOMBRE_POL, "
        /*17*/ + " NVL(TAA.AGENTE_ADUANAL_NOMBRE, ' ') AS AGENTE_ADUANAL, "
        /*18*/ + " GTN.PLANTILLA_ID, "
        /*19*/ + " SYSDATE AS FECHA_CAPTURAOLD, "
        /*20*/ + " TIP1.NOMBRE_POD, "
        /*21*/ + " TIP2.NOMBRE_POL, "
        /*22*/ + " TIBD.NOMBRE_BD, "
        /*23*/ + " NVL(TO_CHAR(GTN.ETA_PLUS2, 'MM/DD/YYYY'), ' ') AS ETA_DC, "
        /*24*/ + " NVL(TO_CHAR(TIE.EST_ETA_DC, 'MM/DD/YYYY'),' '), "
        /*25*/ + " NVL(TO_CHAR(GTN.ETA_PLUS, 'MM/DD/YYYY'), ' ') AS ETA_DC1, "
        /*26*/ + " NVL(TIE.OBSERVACIONES, ' ') AS OBSERVACIONES, "
        /*27*/ + " TIE.ESTATUS_EVENTO, "
        /*28*/ + " NVL(TIE.REFERENCIA_AA,' '), "
        /*29*/ + " NVL(TO_CHAR(TIE.FECHA_CAPTURA, 'MM/DD/YYYY'),' '), "    
        /*30*/ + " NVL(TIE.PRIORIDAD,' '), "  
        /*31*/ + " NVL(TIC.REFERENCIA_AA,' '), " 
        /*32*/ + " NVL(TIC.PAIS_ORIGEN,' '), "   
        /*33*/ + " NVL(TIC.SIZE_CONTAINER,' '), "   
        /*34*/ + " NVL(TIC.VALOR_USD,' '), "               
        /*35*/ + " NVL(TO_CHAR(TIC.ETA_PORT_OF_DISCHARGE, 'MM/DD/YYYY'),' '), "       
        /*36*/ + " NVL(TIC.AGENTE_ADUANAL,' '), "             
        /*37*/ + " NVL(TIC.PEDIMENTO_A1,' '), "               
        /*38*/ + " NVL(TIC.PEDIMENTO_R1,' '), "           
        /*39*/ + " NVL(TIC.MOTIVO_RECTIFICACION_1,' '), "   
        /*40*/ + " NVL(TIC.PEDIMENTO_R1_2DO,' '), "           
        /*41*/ + " NVL(TIC.MOTIVO_RECTIFICACION_2,' '), "   
        /*42*/ + " NVL(TO_CHAR(TIC.FECHA_RECEPCION_DOCUMENTOS, 'MM/DD/YYYY'),' '), "       
        /*43*/ + " NVL(TIC.RECINTO,' '), "     
        /*44*/ + " NVL(TIC.NAVIERA_FORWARDER,' '), "     
        /*45*/ + " NVL(TIC.BUQUE,' '), "    
        /*46*/ + " NVL(TO_CHAR(TIC.FECHA_REVALID_LIBE_BL, 'MM/DD/YYYY'),' '), "   
        /*47*/ + " NVL(TO_CHAR(TIC.FECHA_PREVIO_ORIGEN, 'MM/DD/YYYY'),' '), "   
        /*48*/ + " NVL(TO_CHAR(TIC.FECHA_PREVIO_DESTINO, 'MM/DD/YYYY'),' '), "   
        /*49*/ + " NVL(TO_CHAR(TIC.FECHA_RESULTADO_PREVIO, 'MM/DD/YYYY'),' '), "   
        /*50*/ + " NVL(TO_CHAR(TIC.PROFORMA_FINAL, 'MM/DD/YYYY'),' '), "           
        /*51*/ + " NVL(TIC.REQUIERE_PERMISO,' '), "    
        /*52*/ + " NVL(TO_CHAR(TIC.FECHA_ENVIO_FICHAS_NOTAS, 'MM/DD/YYYY'),' '), " 
        /*53*/ + " NVL(TO_CHAR(TIC.FEC_RECEPCION_PERMISOS_TRAMIT, 'MM/DD/YYYY'),' '), " 
        /*54*/ + " NVL(TO_CHAR(TIC.FEC_ACT_PERMISOS, 'MM/DD/YYYY'),' '), " 
        /*55*/ + " NVL(TO_CHAR(TIC.FEC_PERM_AUT, 'MM/DD/YYYY'),' '), " 
        /*56*/ + " NVL(TIC.CO_APLIC_PREF_ARANCELARIA,' '), "                     
        /*57*/ + " NVL(TIC.APLIC_PREF_ARANCELARIA_CO,' '), "     
        /*58*/ + " NVL(TIC.REQUIERE_UVA,' '), "                                  
        /*59*/ + " NVL(TIC.REQUIERE_CA,' '), "    
        /*60*/ + " NVL(TO_CHAR(TIC.FECHA_RECEPCION_CA, 'MM/DD/YYYY'),' '), " 
        /*61*/ + " NVL(TIC.NÚMERO_CONSTANCIA_CA,' '), "                         
        /*62*/ + " NVL(TIC.MONTO_CA,' '), "   
        /*63*/ + " NVL(TO_CHAR(TIC.FECHA_DOCUMENTOS_COMPLETOS, 'MM/DD/YYYY'),' '), " 
        /*64*/ + " NVL(TO_CHAR(TIC.FECHA_PAGO_PEDIMENTO, 'MM/DD/YYYY'),' '), " 
        /*65*/ + " NVL(TO_CHAR(TIC.FECHA_SOLICITUD_TRANSPORTE, 'MM/DD/YYYY'),' '), " 
        /*66*/ + " NVL(TO_CHAR(TIC.FECHA_MODULACION, 'MM/DD/YYYY'),' '), " 
        /*67*/ + " NVL(TIC.MODALIDAD_CAMION_TREN,' '), "                  
        /*68*/ + " NVL(TIC.RESULT_MODULACION_VERDE_ROJO,' '), "       
        /*69*/ + " NVL(TO_CHAR(TIC.FECHA_RECONOCIMIENTO, 'MM/DD/YYYY'),' '), " 
        /*70*/ + " NVL(TO_CHAR(TIC.FECHA_LIBERACION, 'MM/DD/YYYY'),' '), "    
        /*71*/ + " NVL(TIC.SELLO_ORIGEN,' '), "                                         
        /*72*/ + " NVL(TIC.SELLO_FINAL,' '), "                
        /*73*/ + " NVL(TO_CHAR(TIC.FECHA_RETENCION_AUTORIDAD, 'MM/DD/YYYY'),' '), "    
        /*74*/ + " NVL(TO_CHAR(TIC.FECHA_LIB_POR_RET_AUT, 'MM/DD/YYYY'),' '), "    
        /*75*/ + " NVL(TEC.DESCRIPCION_ESTADO,' '), "           
        /*76*/ + " NVL(TIC.MOTIVO_ATRASO,' '), "              
        /*77*/ + " NVL(TIC.OBSERVACIONES,' '), "         
        /*78*/ + " NVL(TO_CHAR(TIC.LLEGADA_A_NOVA, 'MM/DD/YYYY'),' '), " 
        /*79*/ + " NVL(TO_CHAR(TIC.LLEGADA_A_GLOBE_TRADE_SD, 'MM/DD/YYYY'),' '), " 
        /*80*/ + " NVL(TIC.ARCHIVO_M,' '), "  
        /*81*/ + " NVL(TO_CHAR(TIC.FECHA_ARCHIVO_M, 'MM/DD/YYYY'),' '), "    
        /*82*/ + " NVL(TO_CHAR(TIC.FECHA_SOLICIT_MANIP, 'MM/DD/YYYY'),' '), "    
        /*83*/ + " NVL(TO_CHAR(TIC.FECHA_VENCIM_MANIP, 'MM/DD/YYYY'),' '), "    
        /*84*/ + " NVL(TO_CHAR(TIC.FECHA_CONFIRM_CLAVE_PEDIM, 'MM/DD/YYYY'),' '), "    
        /*85*/ + " NVL(TO_CHAR(TIC.FECHA_RECEP_INCREMENT, 'MM/DD/YYYY'),' '), "    
        /*86*/ + " NVL(TIC.T_E,' '), "  
        /*87*/ + " NVL(TO_CHAR(TIC.FECHA_VENCIM_INBOUND, 'MM/DD/YYYY'),' '), "                  
        /*88*/ + " NVL(TIC.NO_BULTOS,' '), " 
        /*89*/ + " NVL(TIC.PESO_KG,' '), "  
        /*90*/ + " NVL(TIC.TRANSFERENCIA,' '), "  
        /*91*/ + " NVL(TO_CHAR(TIC.FECHA_INICIO_ETIQUETADO, 'MM/DD/YYYY'),' '), "    
        /*92*/ + " NVL(TO_CHAR(TIC.FECHA_TERMINO_ETIQUETADO, 'MM/DD/YYYY'),' '), "    
        /*93*/ + " NVL(TIC.HORA_TERMINO_ETIQUETADO,' '), "  
        /*94*/ + " NVL(TIC.PROVEEDOR,' '), "  
        /*95*/ + " NVL(TIC.PROVEEDOR_CARGA,' '), " 						 
        /*96*/ + " NVL(TIC.FY,' '), "                                                
        /*97*/ + " NVL(TIC.AGENTE_ADUANAL_ID,0), "                                   
        /*98*/ + " NVL(TIC.PRIORIDAD,'No'), "
        /*99*/ + " NVL(GTN.ESTATUS,1), "
       /*100*/ + " NVL(TIC.ESTATUS_SEMAFORO,'0') "                                     
               + " FROM TRA_INB_EVENTO TIE "
               + " LEFT JOIN TRA_DESTINO_RESPONSABLE BP ON BP.USER_NID = TIE.USER_NID "
               + " INNER JOIN TRA_INC_GTN_TEST GTN ON GTN.PLANTILLA_ID = TIE.PLANTILLA_ID "
               + " LEFT JOIN TRA_INB_POD TIP1 ON TIP1.ID_POD = GTN.POD "
               + " LEFT JOIN TRA_INB_POL TIP2 ON TIP2.ID_POL = GTN.POL "
               + " LEFT JOIN TRA_INB_BRAND_DIVISION TIBD ON TIBD.ID_BD = GTN.BRAND_DIVISION "
               + " LEFT JOIN TRA_INB_AGENTE_ADUANAL TAA ON TAA.AGENTE_ADUANAL_ID = TIP1.AGENTE_ADUANAL_ID "
               + " LEFT JOIN TRA_INB_DIVISION TID ON TID.ID_DIVISION = GTN.SBU_NAME "
               + " LEFT JOIN SUM_QUANTITY SQ ON SQ.SHIPMENT_ID = GTN.SHIPMENT_ID AND SQ.CONTAINER1 = GTN.CONTAINER1 "
               + " LEFT JOIN TRA_INB_CUSTOMS TIC ON GTN.SHIPMENT_ID = TIC.SHIPMENT_ID "
               + " LEFT JOIN TRA_ESTADOS_CUSTOMS TEC ON GTN.ESTATUS = TEC.ID_ESTADO "
               + " LEFT JOIN TRA_INB_SEMAFORO TISE ON TIC.SHIPMENT_ID = TISE.SHIPMENT_ID "
               + " WHERE TIE.ESTADO = 1 "
               + " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/mm/yy') >= to_date('"+fechaInicial+"','dd/mm/yyyy') " 
               + " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/mm/yy') <= to_date('"+fechaFinal+"','dd/mm/yyyy') "
               + " AND tid.division_nombre <> 'No/DSN' "
               + " AND gtn.load_type_final IS NOT NULL "   
               + " AND TIC.ESTATUS_OPERACION = '19' "
               + " AND gtn.ESTATUS = '19' ";  
         
        if(!tipoAgente.equals("4006")){ //VF GENERAL
          sql += " AND TIP1.AGENTE_ADUANAL_ID IN ('" + tipoAgente + "') ";  
        }
        
        if(tipoFiltro.equals("0")){        //Sin filtros
          sql += "";
        }else if(tipoFiltro.equals("1")){  //Referncia AA
          sql += " AND TIC.REFERENCIA_AA IN (" + id + ") ";
        }else if(tipoFiltro.equals("2")){  //Evento
          sql += " AND TIE.ID_EVENTO IN (" + id + ") ";
        }else if(tipoFiltro.equals("3")){  //Responsable
          sql += " AND BP.RESPONSABLE IN (" + id + ") ";
        }else if(tipoFiltro.equals("4")){  //Final Destination
          sql += " AND GTN.FINAL_DESTINATION IN (" + id + ") ";
        }else if(tipoFiltro.equals("5")){  //Brand-Division
          sql += " AND TIBD.NOMBRE_BD IN (" + id + ") ";
        }else if(tipoFiltro.equals("6")){  //División
          sql += " AND TID.DIVISION_NOMBRE IN (" + id + ") ";
        }else if(tipoFiltro.equals("7")){  //Shipment Id
          sql += " AND GTN.SHIPMENT_ID IN (" + id + ") ";
        }else if(tipoFiltro.equals("8")){  //Container
          sql += " AND GTN.CONTAINER1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("9")){  //BL/AWB/PRO
          sql += " AND GTN.BL_AWB_PRO IN (" + id + ") ";
        }else if(tipoFiltro.equals("10")){ //Load Type
          sql += " AND GTN.LOAD_TYPE IN (" + id + ") ";
        }else if(tipoFiltro.equals("11")){ //Quantity
          sql += " AND SQ.SUMA IN (" + id + ") ";
        }else if(tipoFiltro.equals("12")){ //Pod
          sql += " AND TIP1.NOMBRE_POD IN (" + id + ") ";
        }else if(tipoFiltro.equals("13")){ //Departure Pol 
          sql += " AND GTN.EST_DEPARTURE_POL IN (" + id + ") ";
        }else if(tipoFiltro.equals("14")){ //ETA REAL Port of Discharge
          sql += " AND TO_DATE(trunc(GTN.ETA_PORT_DISCHARGE),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("15")){ //Est. Eta DC
          sql += " AND GTN.ETA_PLUS2 IN (" + id + ") ";
        }else if(tipoFiltro.equals("16")){ //Inbound notification
          sql += "";
        }else if(tipoFiltro.equals("17")){ //Pol
          sql += " AND TIP2.NOMBRE_POL IN (" + id + ") ";
        }else if(tipoFiltro.equals("18")){ //Agente Aduanal
          sql += " AND TAA.AGENTE_ADUANAL_NOMBRE IN (" + id + ") ";
        }else if(tipoFiltro.equals("19")){ //Fecha Mes de Venta
          sql += " AND TO_DATE(trunc(TIE.FECHA_CAPTURA),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("20")){ //Prioridad
          sql += " AND TIC.PRIORIDAD IN (" + id + ") ";
        }else if(tipoFiltro.equals("21")){ // País Origen 
          sql += " AND TIC.PAIS_ORIGEN IN (" + id + ") ";
        }else if(tipoFiltro.equals("22")){ // Size Container
          sql += " AND TIC.SIZE_CONTAINER IN (" + id + ") ";
        }else if(tipoFiltro.equals("23")){ // Valor USD
          sql += " AND TIC.VALOR_USD IN (" + id + ") ";
        }else if(tipoFiltro.equals("24")){ // ETA Port Of Discharge
          sql += " AND TO_DATE(trunc(TIC.ETA_PORT_OF_DISCHARGE),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("25")){ // Agente Aduanal 
          sql += " AND TIC.AGENTE_ADUANAL IN (" + id + ") ";
        }else if(tipoFiltro.equals("26")){ // Pedimento A1 
          sql += " AND TIC.PEDIMENTO_A1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("27")){ // Pedimento R1
          sql += " AND TIC.PEDIMENTO_R1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("28")){ // Motivo Rectificación 1
          sql += " AND TIC.MOTIVO_RECTIFICACION_1 IN (" + id + ") ";
        }else if(tipoFiltro.equals("29")){ // Pedimento R1 (2DO)
          sql += " AND TIC.PEDIMENTO_R1_2DO IN (" + id + ") ";
        }else if(tipoFiltro.equals("30")){ // Motivo Rectificación 2
          sql += " AND TIC.MOTIVO_RECTIFICACION_2 IN (" + id + ") ";
        }else if(tipoFiltro.equals("31")){ // Fecha Recepción Documentos
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECEPCION_DOCUMENTOS),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("32")){ // Recinto
          sql += " AND TIC.RECINTO IN (" + id + ") ";
        }else if(tipoFiltro.equals("33")){ // Naviera/Forwarder
          sql += " AND TIC.NAVIERA_FORWARDER IN (" + id + ") ";
        }else if(tipoFiltro.equals("34")){ // Buque
          sql += " AND TIC.BUQUE IN (" + id + ") ";
        }else if(tipoFiltro.equals("35")){ // Fecha Revalidación/Liberación de BL
          sql += " AND TO_DATE(trunc(TIC.FECHA_REVALID_LIBE_BL),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("36")){ // Fecha Previo Origen
          sql += " AND TO_DATE(trunc(TIC.FECHA_PREVIO_ORIGEN),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("37")){ // Fecha Previo en destino
          sql += " AND TO_DATE(trunc(TIC.FECHA_PREVIO_DESTINO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("38")){ // Fecha Resultado Previo
          sql += " AND TO_DATE(trunc(TIC.FECHA_RESULTADO_PREVIO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("39")){ // Proforma Final 
          sql += " AND TIC.PROFORMA_FINAL IN (" + id + ") ";
        }else if(tipoFiltro.equals("40")){ // Requiere permiso
          sql += " AND TIC.REQUIERE_PERMISO IN (" + id + ") ";
        }else if(tipoFiltro.equals("41")){ // Fecha envío Fichas/notas
          sql += " AND TO_DATE(trunc(TIC.FECHA_ENVIO_FICHAS_NOTAS),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("42")){ // Fec. Recepción de permisos tramit.
          sql += " AND TO_DATE(trunc(TIC.FEC_RECEPCION_PERMISOS_TRAMIT),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("43")){ // Fec. Act Permisos (Inic Vigencia)
          sql += " AND TO_DATE(trunc(TIC.FEC_ACT_PERMISOS),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("44")){ // Fec. Perm. Aut. (Fin de Vigencia) 
          sql += " AND TO_DATE(trunc(TIC.FEC_PERM_AUT),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("45")){ // Cuenta con CO para aplicar preferencia Arancelaria
          sql += " AND TIC.CO_APLIC_PREF_ARANCELARIA IN (" + id + ") ";
        }else if(tipoFiltro.equals("46")){ // Aplico Preferencia Arancelaria 
          sql += " AND TIC.APLIC_PREF_ARANCELARIA_CO IN (" + id + ") ";
        }else if(tipoFiltro.equals("47")){ // Requiere UVA
          sql += " AND TIC.REQUIERE_UVA IN (" + id + ") ";
        }else if(tipoFiltro.equals("48")){ // Requiere CA
          sql += " AND TIC.REQUIERE_CA IN (" + id + ") ";
        }else if(tipoFiltro.equals("49")){ // Fecha Recepción CA
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECEPCION_CA),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("50")){ // Número de Constancia CA 
          sql += " AND IC.NÚMERO_CONSTANCIA_CA IN (" + id + ") ";
        }else if(tipoFiltro.equals("51")){ // Monto CA
          sql += " AND TIC.MONTO_CA IN (" + id + ") ";
        }else if(tipoFiltro.equals("52")){ // Fecha Documentos Completos
          sql += " AND TO_DATE(trunc(TIC.FECHA_DOCUMENTOS_COMPLETOS),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("53")){ // Fecha Pago Pedimento
          sql += " AND TO_DATE(trunc(TIC.FECHA_PAGO_PEDIMENTO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("54")){ // Fecha Solicitud de transporte
          sql += " AND TO_DATE(trunc(TIC.FECHA_SOLICITUD_TRANSPORTE),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("55")){ // Fecha Modulacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_MODULACION),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("56")){ // Modalidad
          sql += " AND TIC.MODALIDAD_CAMION_TREN IN (" + id + ") ";
        }else if(tipoFiltro.equals("57")){ // Resultado Modulacion
          sql += " AND TIC.RESULT_MODULACION_VERDE_ROJO IN (" + id + ") ";
        }else if(tipoFiltro.equals("58")){ // Fecha Reconocimiento
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECONOCIMIENTO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("59")){ // Fecha Liberacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_LIBERACION),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("60")){ // Sello Origen 
          sql += " AND TIC.SELLO_ORIGEN IN (" + id + ") ";
        }else if(tipoFiltro.equals("61")){ // Sello Final
          sql += " AND TIC.SELLO_FINAL IN (" + id + ") ";
        }else if(tipoFiltro.equals("62")){ // Fecha de retencion por la autoridad
          sql += " AND TO_DATE(trunc(TIC.FECHA_RETENCION_AUTORIDAD),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("63")){ // Fec. de liberacion por ret. de la aut.
          sql += " AND TO_DATE(trunc(TIC.FECHA_LIB_POR_RET_AUT),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("64")){ // Estatus de la operación
          sql += " AND TEC.DESCRIPCION_ESTADO IN (" + id + ") ";
        }else if(tipoFiltro.equals("65")){ // Motivo Atraso
          sql += " AND TIC.MOTIVO_ATRASO IN (" + id + ") ";
        }else if(tipoFiltro.equals("66")){ // Observaciones
          sql += " AND TIC.OBSERVACIONES IN (" + id + ") ";
        }else if(tipoFiltro.equals("67")){ // Llegada a NOVA
          sql += " AND TIC.LLEGADA_A_NOVA IN (" + id + ") ";
        }else if(tipoFiltro.equals("68")){ // Llegada a Globe trade SD  
          sql += " AND TIC.LLEGADA_A_GLOBE_TRADE_SD IN (" + id + ") ";
        }else if(tipoFiltro.equals("69")){ // Archivo M 
          sql += " AND TIC.ARCHIVO_M IN (" + id + ") ";
        }else if(tipoFiltro.equals("70")){ // Fecha de Archivo M 
          sql += " AND TO_DATE(trunc(TIC.FECHA_ARCHIVO_M),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("71")){ // Fecha Solicitud de Manipulacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_ARCHIVO_M),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("72")){ // Fecha de vencimiento de Manipulacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_VENCIM_MANIP),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("73")){ // Fecha confirmacion Clave de Pedimento
          sql += " AND TO_DATE(trunc(TIC.FECHA_CONFIRM_CLAVE_PEDIM),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("74")){ // Fecha de Recepcion de Incrementables
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECEP_INCREMENT),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("75")){ // T&E 
          sql += " AND TIC.T_E IN (" + id + ") ";
        }else if(tipoFiltro.equals("76")){ // Fecha de Vencimiento del Inbound  
          sql += " AND TO_DATE(trunc(TIC.FECHA_VENCIM_INBOUND),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("77")){ // No. BULTOS
          sql += " AND TIC.NO_BULTOS IN (" + id + ") ";
        }else if(tipoFiltro.equals("78")){ // Peso (KG)
          sql += " AND TIC.PESO_KG IN (" + id + ") ";
        }else if(tipoFiltro.equals("79")){ // Transferencia 
          sql += " AND TIC.TRANSFERENCIA IN (" + id + ") ";
        }else if(tipoFiltro.equals("80")){ // Fecha Inicio Etiquetado
          sql += " AND TO_DATE(trunc(TIC.FECHA_INICIO_ETIQUETADO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("81")){ // Fecha Termino Etiquetado 
          sql += " AND TO_DATE(trunc(TIC.FECHA_TERMINO_ETIQUETADO),'DD/MM/YY') = TO_DATE(" + id + ",'MM/DD/YY') ";
        }else if(tipoFiltro.equals("82")){ // Hora de termino Etiquetado
          sql += " AND TIC.HORA_TERMINO_ETIQUETADO IN (" + id + ") ";
        }else if(tipoFiltro.equals("83")){ // Proveedor
          sql += " AND TIC.PROVEEDOR IN (" + id + ") ";
        }else if(tipoFiltro.equals("84")){ // Proveedor de Carga
          sql += " AND TIC.PROVEEDOR_CARGA IN (" + id + ") ";
        }else if(tipoFiltro.equals("85")){ // FY
          sql += " AND TIC.FY IN (" + id + ") ";
        }
           sql += " ORDER BY tie.id_evento, tibd.nombre_bd ASC ";  
        return sql;
    }
      
    public String consultarEstatusImportacion(String shipment_id){
          sql = "SELECT DISTINCT NVL(ESTATUS,0) FROM TRA_INC_GTN_TEST WHERE SHIPMENT_ID = '"+shipment_id+"' ";
          return sql;
    }
    
} 
 
