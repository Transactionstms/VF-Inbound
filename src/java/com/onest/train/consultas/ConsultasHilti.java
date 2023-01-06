/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

/**
 *
 * @author Ricardo Martinez Hernandez ricardomtzh@outlook.com
 */
public class ConsultasHilti {

    String sql = "";

    public String consultaEmbarqueNBodHilti(String e_cuenta, String tipo, String e, String folio) {
        sql = "SELECT d.d_docto_sal_id, d.d_docto_referencia,d.cue_cuenta_nombre,d.ddh_destino_nombre, "
                + " d.ddh_destino_ciudad,d.ddh_destino_estado,TO_CHAR(d.d_docto_fec_captura,'dd/mm/yyyy'), "
                + " d.d_docto_piezas, d.d_docto_cajas, d.d_docto_peso"
                + " FROM ontms_vw_reporte_doc2 d"
                + " INNER JOIN ontms_tipo_destino  ot ON ot.tipo_destino_id=d.DODP_TIPO_DESTINO_ID"
                + " WHERE 1=1 AND d.d_docto_estado_id IN (" + e + ")";
        if (!tipo.equals("'Mixto'")) {
            sql += " AND ot.tipo_destino_lof IN (" + tipo + ")";
        }
        sql += " AND d.d_cbdiv_id IN (" + e_cuenta + ") and d.d_docto_programacion in (" + folio + ")"
                + " GROUP BY d.d_docto_sal_id,TO_CHAR(d.d_docto_fec_captura,'dd/mm/yyyy'),d.d_docto_referencia,"
                + "d.cue_cuenta_nombre, "
                + "d.ddh_destino_nombre,"
                + "d.ddh_destino_ciudad,"
                + "d.ddh_destino_estado,"
                + "d.d_docto_piezas,"
                + "d.d_docto_cajas,"
                + "d.d_docto_peso";

        return sql;

    }

    public String embarqueDetalleHilti(String e_cuenta, String tipo, String e, String tipoEmbarque, String shipto, String docto, String fec1, String fec2 , String folio) {

        String condicion = " ";
        String condicion_docto = " ";
        String condicion_fechas = " ";
        String condicion_shipto = " ";
        String condicion_folio = " ";
    
         if (!folio.equals("")) {
            condicion_folio = " AND d.D_DOCTO_programacion  in ('" + folio + "' )";
        }
        
        if (!shipto.equals("")) {
            condicion_shipto = " AND d.DDH_DESTINO_SHIP_TO ='" + shipto + "' ";
        }

        if (!docto.equals("")) {
            condicion_docto = " and UPPER(d.D_DOCTO_REFERENCIA) =UPPER('" + docto + "') ";
        }

        if (!fec1.equals("(Opcional)") && !fec2.equals("(Opcional)")) {
            condicion_fechas = " and  trunc(d.d_docto_fec_captura) "
                    + " between to_date('" + fec1 + "','DD/MM/YYYY') and to_date('" + fec2 + "','DD/MM/YYYY') ";
        }

        if (!tipoEmbarque.equals("P")) {
            if (!tipo.equals("'Mixto'")) {
                condicion = " AND ot.tipo_destino_lof IN (" + tipo + ") ";
            }
        }

        if (tipoEmbarque.equals("P")) {
            if (!tipo.equals("'Mixto'")) {
                condicion = " AND ot.tipo_destino_lof IN ('Foraneo') ";
            }
        }

        sql = " SELECT d.d_docto_sal_id, "
                + " d.d_docto_referencia, "
                + " d.ddh_destino_nombre , "
                + " d.ddh_destino_ciudad, "
                + " d.ddh_destino_estado, "
                + " TO_CHAR(d.d_docto_fec_captura,'dd/mm/yyyy'), "
                + " d.d_docto_piezas, "
                + " d.d_docto_cajas, "
                + " d.d_docto_peso, " //(d.d_docto_peso/1000),
                + " d.d_docto_volumen, "
                + " d.dodp_origen_dest_id , "
                + " d.ddh_destino_ship_to, "
                + " d.ddh_destino_colonia, "
                + " d.ddh_destino_cp,"
                + " d.dodp_destino_rfc,"
                + " d.ddh_destino_domicilio,"
                + " CONSULTA_ZONAS_EXTENDIDAS(d.ddh_destino_cp),"
                + " d.cue_cuenta_nombre "
                //+ " TR.RUTA_NOMBRE"
                + " FROM ontms_vw_reporte_doc2 d "
                + " LEFT JOIN ontms_tipo_destino ot "
                + " ON ot.tipo_destino_id    =d.dodp_tipo_destino_id "
                //+ " INNER JOIN TRA_RUTA_CP TRC "
                //+ " ON d.DDH_DESTINO_CP=TRC.CP_COD_POSTAL "
               //+ " INNER JOIN TRA_RUTAS TR "
               // + " ON TR.RUTA_ID=TRC.RUTA_ID "
                + " WHERE 1                  =1"
                + " AND d.d_docto_estado_id IN (" + e + ") "
                + condicion + condicion_docto + condicion_shipto + condicion_fechas + condicion_folio
                + " AND d.d_cbdiv_id        IN (" + e_cuenta + ") "
                //+ " AND TRC.RUTA_ID IN(SELECT TR.RUTA_ID FROM TRA_RUTAS)"
                //+ " and  d.ddh_destino_colonia is not null "
                //+ " and  d.ddh_destino_cp is not null "
                + " and d.d_docto_piezas > 0 ";
                //+ " ORDER BY TR.ruta_id   ";

        //+ " and d.d_docto_referencia in ('270476712') "
        return sql;
    }

    public String countembarqueDetalleHilti(String e_cuenta, String tipo, String e) {
//aqui faltan fechas en los filtros
        sql = " SELECT count( d.d_docto_sal_id) "
                + " FROM ontms_vw_reporte_doc2 d "
                + " INNER JOIN ontms_tipo_destino ot "
                + " ON ot.tipo_destino_id = d.dodp_tipo_destino_id "
                + " WHERE 1=1 "
                + " AND d.d_docto_estado_id IN (" + e + ") "
                + " AND d.d_cbdiv_id IN (" + e_cuenta + ") "
                + " AND d.ddh_destino_colonia IS NOT NULL "
                + " AND d.ddh_destino_cp  IS NOT NULL "
                + " ORDER BY d.ddh_destino_nombre";

        return sql;
    }

    public String countembarqueDetalle(String e_cuenta, String tipo, String e, String tipoEmbarque, String shipto, String docto, String fec1, String fec2, String folio) {
//aqui se realiza el conteo de documentos disponible para embarque conforme a los filtros seleccionados.
        String condicion = " ";
        String condicion_docto = " ";
        String condicion_fechas = " ";
        String condicion_shipto = " ";
         String condicion_folio = " ";
 if (!folio.equals("")) {
            condicion_folio = " AND d.D_DOCTO_programacion ='" +folio + "' ";
        }
        if (!shipto.equals("")) {
            condicion_shipto = " AND d.DDH_DESTINO_SHIP_TO ='" + shipto + "' ";
        }

        if (!docto.equals("")) {
            condicion_docto = " and UPPER(d.D_DOCTO_REFERENCIA) =UPPER('" + docto + "') ";
        }

        if (!fec1.equals("(Opcional)") && !fec2.equals("(Opcional)")) {
            condicion_fechas = " and  trunc(d.d_docto_fec_captura) "
                    + " between to_date('" + fec1 + "','DD/MM/YYYY') and to_date('" + fec2 + "','DD/MM/YYYY') ";
        }

        if (!tipoEmbarque.equals("P")) {
            if (!tipo.equals("'Mixto'")) {
                condicion = " AND ot.tipo_destino_lof IN (" + tipo + ") ";
            }
        }

        if (tipoEmbarque.equals("P")) {
            if (!tipo.equals("'Mixto'")) {
                condicion = " AND ot.tipo_destino_lof IN ('Foraneo') ";
            }
        }

        sql = " SELECT COUNT(d.d_docto_sal_id) "
                + " FROM ontms_vw_reporte_doc2 d "
                + " LEFT JOIN ontms_tipo_destino ot ON ot.tipo_destino_id = d.dodp_tipo_destino_id "
                + " INNER JOIN tra_ruta_cp trc ON d.ddh_destino_cp = trc.cp_cod_postal"
                + " INNER JOIN tra_rutas tr ON tr.ruta_id = trc.ruta_id"
                + " WHERE 1                  =1"
                + " AND d.d_docto_estado_id IN (" + e + ") "
                + condicion + condicion_docto + condicion_shipto + condicion_fechas + condicion_folio
                + " AND d.d_cbdiv_id        IN (" + e_cuenta + ") "
                + " and  d.ddh_destino_colonia is not null "
                + " and  d.ddh_destino_cp is not null "
                + " and d.d_docto_piezas > 0 "
                + " ORDER BY d.d_docto_sal_id   ";

        //+ " and d.d_docto_referencia in ('270476712') "
        return sql;
    }

    public String planeacionRutasDoctos(String cbdiv, String docto_sal_id, String division_id, String ruta_id) {


        sql = "SELECT d.d_docto_sal_id,\n"
                + "  d.d_docto_referencia, "
                + "  TR.RUTA_ID, "
                + "  TR.RUTA_NOMBRE, "
                + "  d.ddh_destino_nombre , "
                + "  d.ddh_destino_ciudad, "
                + "  d.ddh_destino_estado, "
                + "  TO_CHAR(d.d_docto_fec_captura,'dd/mm/yyyy'), "
                + "  d.d_docto_piezas, "
                + "  d.d_docto_cajas, "
                + "  d.d_docto_peso, "
                + "  d.d_docto_volumen, "
                + "  d.dodp_origen_dest_id , "
                + "  d.ddh_destino_ship_to, "
                + "  d.ddh_destino_colonia, "
                + "  d.ddh_destino_cp, "
                + "  d.dodp_destino_rfc, "
                + "  d.ddh_destino_domicilio, "
                + "  CONSULTA_ZONAS_EXTENDIDAS(d.ddh_destino_cp), "
                + "  d.cue_cuenta_nombre "
                + "FROM ontms_vw_reporte_doc2 d "
                + "LEFT JOIN ontms_tipo_destino ot "
                + "ON ot.tipo_destino_id    =d.dodp_tipo_destino_id "
                + "INNER JOIN TRA_RUTA_CP TRC "
                + "ON d.DDH_DESTINO_CP=TRC.CP_COD_POSTAL "
                + "INNER JOIN TRA_RUTAS TR "
                + "ON TR.RUTA_ID=TRC.RUTA_ID "
                + "WHERE 1                  =1 "
                + "AND d.d_docto_estado_id IN (0,25) "
                + "AND d_cbdiv_id         IN (" + cbdiv + ") "
                + "AND d_docto_sal_id     IN (" + docto_sal_id + ") "
                + "AND TRC.RUTA_ID IN(" + ruta_id + ") "
                + "AND d.d_docto_piezas       > 0 "
                + "ORDER BY TR.RUTA_ID ";
        return sql;

    }

    public String embarqueDetalleSelecconadoHilti(String cbdiv, String docto_sal_id, String division_id) {

        sql = " SELECT ods.docto_sal_id, "
                + " ods.docto_referencia, "
                + " ddh.destino_nombre, "
                + " ddh.destino_ciudad, "
                + " ddh.destino_estado, "
                + " TO_CHAR(ods.docto_fec_captura,'dd/mm/yyyy'), "
                + " NVL(TO_CHAR(ods.docto_piezas),' '), "
                + " NVL(TO_CHAR(ods.docto_cajas),' '), "
                + "   NVL(ods.docto_peso,0), "//+ "   NVL((ods.docto_peso/1000),0), "
                + " ddh.destino_ship_to, "
                + " ddh.destino_colonia, "
                + " ddh.destino_cp "
                + " FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_cta_bod_div ocbd "
                + " ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_bodega ob "
                + " ON ob.bodega_id = ocbd.bodega_id "
                + " INNER JOIN ontms_cuenta oc "
                + " ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv "
                + " ON odv.division_id = ocbd.division_id "
                + " INNER JOIN dilog_destinos_hilti ddh "
                + " ON ddh.destino_ship_to    = ods.destino_id "
                + " and ddh.division_id = ods.cbdiv_id "
                + " WHERE 1                   =1 "
                + " AND (ods.docto_estado_id IN (0,22,25)) "
                + " AND ods.cbdiv_id         IN (" + cbdiv + ") "
                + " AND ods.docto_sal_id     IN (" + docto_sal_id + ") "
                + " GROUP BY ods.docto_sal_id, "
                + "   ods.docto_referencia, "
                + "   ddh.destino_nombre, "
                + "   ddh.destino_ciudad, "
                + "   ddh.destino_estado, "
                + "  ods.docto_fec_captura, "
                + "  ods.docto_piezas, "
                + "  ods.docto_cajas, "
                + "  ods.docto_peso, "
                + "  ddh.destino_ship_to, "
                + "  ddh.destino_colonia, "
                + "  ddh.destino_cp "
                + " ORDER BY ddh.destino_ciudad, "
                + "   ddh.destino_estado, "
                + "   ods.docto_referencia  ";

        /*    sql = " SELECT ods.docto_sal_id, "
         + " ods.docto_referencia, "
         + " ddh.destino_nombre, "
         + " ddh.destino_ciudad, "
         + " ddh.destino_estado, "
         + " TO_CHAR(ods.docto_fec_captura,'dd/mm/yyyy'), "
         + " NVL(TO_CHAR(ods.docto_piezas),' '), "
         + " NVL(TO_CHAR(ods.docto_cajas),' '), "
         + //" --ods.cbdiv_id, " +
         " (SELECT NVL(SUM(docto_sku_cantidad),0) "
         + " FROM ontms_docto_sku "
         + " WHERE docto_sal_id=ods.docto_sal_id "
         + " ),"
         + " ddh.destino_ship_to, "
         + " ddh.destino_colonia, "
         + " ddh.destino_cp"
         + " FROM ontms_docto_sal ods "
         + " INNER JOIN ontms_cta_bod_div ocbd "
         + " ON ods.cbdiv_id = ocbd.cbdiv_id "
         + " INNER JOIN ontms_bodega ob "
         + " ON ob.bodega_id = ocbd.bodega_id "
         + " INNER JOIN ontms_cuenta oc "
         + " ON oc.cuenta_id = ocbd.cuenta_id "
         + " INNER JOIN ontms_division odv "
         + " ON odv.division_id = ocbd.division_id "
         + " inner join dilog_destinos_hilti ddh "
         + " on ddh.destino_ship_to=ods.destino_id "
         + " WHERE 1               =1 "
         + " AND (ods.docto_estado_id IN (0,22)) "
         + " AND ods.cbdiv_id     IN (" + cbdiv + ") "
         + " AND ods.docto_sal_id IN (" + docto_sal_id + ") "
         + " --AND ocd.division_id  IN (304) "
         + " GROUP BY ods.docto_sal_id, "
         + "   ods.docto_referencia, "
         + "  ddh.destino_nombre, "
         + "  ddh.destino_ciudad, "
         + "  ddh.destino_estado, "
         + "   ods.docto_fec_captura, "
         + "  ods.docto_piezas, "
         + "  ods.docto_cajas, "
         + " ddh.destino_ship_to, "
         + " ddh.destino_colonia, "
         + " ddh.destino_cp"
         + //"  ods.cbdiv_id " +
         " ORDER BY ddh.destino_ciudad, "
         + "  ddh.destino_estado, "
         + "  ods.docto_referencia  ";
         */
        return sql;

    }

    public String embarqueDocumentosAceptadosHilti(String sal_id, String division, String cvecuenta, String t, String e, String doc) {
        String condicion = "";
        if (!doc.equals("")) {
            condicion = " and ods.docto_sal_id  IN(" + doc + ") ";
        }

        sql = " SELECT ods.docto_sal_id , "
                + " ods.docto_referencia, "
                + " ddh.destino_nombre, "
                + " ddh.destino_ciudad, "
                + " ddh.destino_estado, "
                + " TO_CHAR(ods.docto_fec_captura,'dd/mm/yyyy hh:mi'), "
                + " NVL(TO_CHAR(ods.docto_piezas),' '), "
                + " NVL(TO_CHAR(ods.docto_cajas),' '), "
                + "   NVL(ods.docto_peso,0), "
                //+ "   NVL((ods.docto_peso/1000),0), "
                // + " (SELECT NVL(SUM(docto_sku_cantidad),0) "
                //+ " FROM ontms_docto_sku "
                //+ " WHERE docto_sal_id=ods.docto_sal_id "
                //+ " ), "
                + " ddh.destino_ship_to,  "
                + " ddh.destino_colonia,  "
                + " ddh.destino_cp  "
                + " FROM " + t + " ods "
                + " INNER JOIN ontms_cta_bod_div ocbd "
                + " ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_bodega ob "
                + " ON ob.bodega_id = ocbd.bodega_id "
                + " INNER JOIN ontms_cuenta oc "
                + " ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv "
                + " ON odv.division_id = ocbd.division_id "
                + " INNER JOIN dilog_destinos_hilti ddh "
                + " ON ddh.destino_ship_to     =ods.destino_id "
                + " and ddh.division_id = ods.cbdiv_id  "
                + " WHERE 1                    =1 "
                + " AND docto_estado_id        IN( " + e + " ) "
                + " AND ods.docto_sal_agrupador IN('" + sal_id + "')    "
                + " AND ods.cbdiv_id          IN(" + cvecuenta + ")"
                + condicion
                + "GROUP BY ods.docto_sal_id , "
                + " ods.docto_referencia, "
                + " ddh.destino_nombre, "
                + " ddh.destino_ciudad, "
                + " ddh.destino_estado, "
                + " TO_CHAR(ods.docto_fec_captura,'dd/mm/yyyy hh:mi'), "
                + " NVL(TO_CHAR(ods.docto_piezas),' '), "
                + " NVL(TO_CHAR(ods.docto_cajas),' '), "
                //+ " NVL((ods.docto_peso/1000),0), "
                + " NVL(ods.docto_peso,0), "
                + " ddh.destino_ship_to, "
                + " ddh.destino_colonia, "
                + " ddh.destino_cp "
                + " ORDER BY ddh.destino_ciudad, "
                + "   ddh.destino_estado, "
                + "   ods.docto_referencia  ";

        return sql;

    }

    public String servicioClienteListaHilti(String embarque, String cve) {
        sql = " SELECT oep.EMBARQUE_PAQ,oep.EMBARQUE_PAQ_CAPTURA,  "
                + " oep.CHOFER, op.PAQUETERIA ,oep.EMBARQUE_PAQ_AGRUPADOR "
                + "  FROM ontms_embarque_paqueteria  oep "
                + "  INNER JOIN ontms_paqueterias op "
                + "  ON op.PAQUETERIA_ID = oep.PAQUETERIA_ID "
                + "  INNER JOIN ONTMS_DOCTO_SAL ods "
                + "  on ods.docto_sal_agrupador = oep.EMBARQUE_PAQ_AGRUPADOR "
                + "  where EMBARQUE_PAQ=('" + embarque + "')  "
                + "  and  ods.cbdiv_id in(" + cve + ") "
                + " group by oep.EMBARQUE_PAQ, "
                + " oep.EMBARQUE_PAQ_CAPTURA, "
                + " oep.CHOFER, "
                + " op.PAQUETERIA , "
                + " oep.EMBARQUE_PAQ_AGRUPADOR ";
        return sql;
    }

    public String detalleDocumentosEmbarcadosHilti(String agrupador, String division, String e) {

        sql = "  SELECT ods.docto_referencia,   "
                + "  NVL(TO_CHAR(ods.docto_piezas),' '),   "
                + "  ddh.destino_nombre ,   "
                + "  ddh.destino_ciudad,   "
                + "  ddh.destino_estado,   "
                + "  ods.docto_sal_id,   "
                + "  oe.EMBARQUE_PAQ_AGRUPADOR,   "
                + "  oe.EMBARQUE_PAQ,   "
                + "  ocbd.division_id,   "
                + "  ods.CBDIV_ID,   "
                + "  NVL(TO_CHAR(ods.docto_cajas),' '),   "
                + "  NVL(TO_CHAR(ods.docto_pallets),' '),   "
                + "  NVL(TO_CHAR(ods.docto_colgados),' '),   "
                + "  NVL(TO_CHAR(ods.docto_contenedor),' '),   "
                + "  NVL(TO_CHAR(ods.docto_atados),' '),   "
                + "  NVL(TO_CHAR(ods.docto_bulks),' ')   "
                + " FROM ontms_docto_sal ods   "
                + " INNER JOIN ontms_embarque_paqueteria oe   "
                + " ON ods.docto_sal_agrupador=oe.EMBARQUE_PAQ_AGRUPADOR   "
                + " INNER JOIN ontms_cta_bod_div ocbd   "
                + " ON ods.cbdiv_id = ocbd.cbdiv_id   "
                + " INNER JOIN ontms_bodega ob   "
                + " ON ob.bodega_id = ocbd.bodega_id   "
                + " INNER JOIN ontms_cuenta oc   "
                + " ON oc.cuenta_id = ocbd.cuenta_id   "
                + " INNER JOIN ontms_division odv   "
                + " ON odv.division_id = ocbd.division_id   "
                + " INNER JOIN DILOG_DESTINOS_HILTI ddh   "
                + " ON ddh.destino_ship_to       =ods.destino_id   "
                + " and ddh.division_id = ods.cbdiv_id "
                + " WHERE oe.EMBARQUE_PAQ_AGRUPADOR IN ('" + agrupador + "')   "
                + " AND ocbd.division_id        IN (" + division + ")   "
                + " " + e + "  "
                + " GROUP BY ods.docto_cajas,   "
                + "   ods.docto_pallets,   "
                + "  ods.docto_colgados,   "
                + "  ods.docto_contenedor,   "
                + "  ods.docto_atados,   "
                + "  ods.docto_bulks,   "
                + "  ods.docto_referencia,   "
                + "  ods.docto_piezas,   "
                + "  ddh.destino_nombre ,   "
                + "  ddh.destino_ciudad ,   "
                + "  ddh.destino_estado,   "
                + "  ods.docto_sal_id,   "
                + "  oe.EMBARQUE_PAQ_AGRUPADOR,   "
                + "  oe.EMBARQUE_PAQ,   "
                + "  ocbd.division_id,   "
                + "  ods.cbdiv_id   "
                + " ORDER BY ods.docto_referencia  ";
        return sql;
    }

    public String servicioClienteListaModificarHilti(String embarque, String cve) {
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

    public String embarqueContarReenvios1Hilti(String e_cuenta, String tipo, String division, String estado) {
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

    public String servicioClienteListaHisHilti(String embarque, String cve) {
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

    public String consultaDocHilti(String[] id) {

        String condicion = "";

        if (!id[1].equals("")) {
            condicion += " upper(ods_docto_referencia) = '" + id[1] + "'";

            if (id[4] != null) {
                if (!id[4].equals("")) {
                    condicion += " or ods_docto_pedido ='" + id[4] + "' ";
                }
            }

        } else {
            condicion += " ods_docto_pedido ='" + id[4] + "' ";
        }

        sql = "SELECT * FROM vw_consulta_doc2 WHERE ods_cbdiv_id in (" + id[0] + ") "
                + "AND ( " + condicion + ") ";

        return sql;
    }

    public String consultaDocHiltiDocuemnto(String id) {
        sql = " SELECT docto_sal_id, "
                + " docto_referencia, "
                + " NVL(docto_concentrado,' '), "
                + " NVL(TO_CHAR(docto_fec_concentrado,'dd/mm/yyyy'),' '), "
                + " NVL(ods.docto_talon,' '), "
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
                + " NVL(otd.tipo_docto_desc,' SIN INGRESAR  '),  "
                + " ddh.destino_nombre, "
                + " NVL(docto_sal_cantidad,0), "
                + "  ods.docto_rechazo, "
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
                + " NVL(TO_CHAR(docto_fec_factura,'dd/mm/yyyy'),' '), "
                + " ods.docto_sal_agrupador,ods.tipo_servicio,docto_pedido, "
                + " upper(ddh.DESTINO_DOMICILIO|| ' Col. ' ||ddh.DESTINO_COLONIA || ' CP. ' || ddh.DESTINO_CP || ' Ciudad '|| ddh.DESTINO_CIUDAD||' Estado '||ddh.DESTINO_ESTADO ),"
                + " NVL(ocre.rechazo_desc, ' '),"
                + " EM.CAMION_ID, "
                + " CM.LTRANSPORTE_ID, "
                + " nvl(lt.ltransporte_nombre,' '), "
                + " nvl(em.embarque_id, ' '),"
                + " nvl(TO_CHAR(em.embarque_fec_captura,'dd/MM/yyyy'),' '),"
                + " nvl(TO_CHAR(ods.docto_captura_evidencia,'dd/MM/yyyy'),' ')"
                + " FROM ontms_docto_sal ods "
                + " left JOIN ontms_embarque em ON ods.docto_sal_agrupador = em.embarque_agrupador "
                + " INNER JOIN ontms_cta_bod_div ocbd "
                + " ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_bodega ob "
                + " ON ob.bodega_id = ocbd.bodega_id "
                + " INNER JOIN ontms_cuenta oc "
                + " ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv "
                + " ON odv.division_id = ocbd.division_id "
                + " INNER JOIN DILOG_DESTINOS_HILTI ddh "
                + " ON ddh.destino_ship_to =ods.destino_id "
                + " and ddh.division_id = ods.cbdiv_id "
                //+ " LEFT JOIN DILOG_PLIST_PEDIDO DDP "
                //+ " ON DDP.NUM_PACKLIST=ODS.DOCTO_REFERENCIA "
                + " LEFT JOIN ontms_tipo_docto otd "
                + " ON ODS.TIPO_DOCTO_ID=otd.tipo_docto_id "
                + " INNER JOIN app_estado ae "
                + " ON ae.edo_valor       =ods.docto_estado_id LEFT JOIN ONTMS_CAUSA_RECHAZO OCRE ON OCRE.RECHazo_id = ods.rechazo_id "
                + " left JOIN  ONTMS_CAMION CM ON CM.CAMION_ID = EM.CAMION_ID "
                + " left JOIN ONTMS_LINEA_TRANSPORTE LT ON LT.LTRANSPORTE_ID = CM.LTRANSPORTE_ID "
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
                + " odv.division_nombre,"
                + "ods.docto_rechazo,ods.tipo_servicio,docto_pedido,"
                + "(ddh.DESTINO_DOMICILIO|| ' Col. ' ||ddh.DESTINO_COLONIA || ' CP. ' || ddh.DESTINO_CP || ' Ciudad '|| ddh.DESTINO_CIUDAD||' Estado '||ddh.DESTINO_ESTADO ), "
                + " ocre.rechazo_desc, "
                + " EM.CAMION_ID, "
                + " CM.LTRANSPORTE_ID, "
                + " LT.LTRANSPORTE_NOMBRE , "
                + " em.EMBARQUE_ID, "
                + " em.EMBARQUE_FEC_CAPTURA, "
                + " ods.docto_captura_evidencia";
        return sql;
    }

    public String consultaDocHiltiDocumentoOnLine(String id) {
        sql = " SELECT docto_sal_id, "
                + " docto_referencia, "
                + " NVL(docto_concentrado,' '), "
                + " NVL(TO_CHAR(docto_fec_concentrado,'dd/mm/yyyy'),' '), "
                + " NVL(ods.docto_talon,' '), "
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
                + " NVL(otd.tipo_docto_desc,' SIN INGRESAR  '),  "
                + " ddh.destino_nombre, "
                + " NVL(docto_sal_cantidad,0), "
                + "  ods.docto_rechazo, "
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
                + " NVL(TO_CHAR(docto_fec_factura,'dd/mm/yyyy'),' '), "
                + " ods.docto_sal_agrupador,ods.tipo_servicio,docto_pedido, "
                + " upper(ddh.DESTINO_DOMICILIO|| ' Col. ' ||ddh.DESTINO_COLONIA || ' CP. ' || ddh.DESTINO_CP || ' Ciudad '|| ddh.DESTINO_CIUDAD||' Estado '||ddh.DESTINO_ESTADO ),"
                + " NVL(ocre.rechazo_desc, ' ')"
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
                + " ON ddh.destino_ship_to =ods.destino_id "
                + " and ddh.division_id = ods.cbdiv_id "
                + " LEFT JOIN DILOG_PLIST_PEDIDO DDP "
                + " ON DDP.NUM_PACKLIST=ODS.DOCTO_REFERENCIA "
                + " LEFT JOIN ontms_tipo_docto otd "
                + " ON DDP.TIPO_DOCTO_ID=otd.tipo_docto_id "
                + " INNER JOIN app_estado ae "
                + " ON ae.edo_valor       =ods.docto_estado_id LEFT JOIN ONTMS_CAUSA_RECHAZO OCRE ON OCRE.RECHazo_id = ods.rechazo_id "
                + " WHERE ods.docto_referencia='" + id + "' "
                + " AND ae.edo_tabla     IN ('ONTMS_DOCTO_SAL') "
                + " AND ods.DOCTO_ESTADO_ID IN (SELECT MAX(FOLIO.DOCTO_ESTADO_ID) FROM ontms_docto_sal FOLIO WHERE FOLIO.docto_referencia='" + id + "') "
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
                + " odv.division_nombre,"
                + "ods.docto_rechazo,ods.tipo_servicio,docto_pedido,"
                + "(ddh.DESTINO_DOMICILIO|| ' Col. ' ||ddh.DESTINO_COLONIA || ' CP. ' || ddh.DESTINO_CP || ' Ciudad '|| ddh.DESTINO_CIUDAD||' Estado '||ddh.DESTINO_ESTADO ),"
                + " ocre.rechazo_desc";

        return sql;
    }

    public String muestraDocumentosEmbarcadosHilti(String agrupador, String id, String es) {

        sql = "  SELECT oe.embarque_paq , "
                + "  TO_CHAR(embarque_paq_captura ,'dd/mm/yyyy hh:mi'), "
                + "  oe.embarque_paq_agrupador "
                + " FROM ontms_embarque_paqueteria oe "
                + " INNER JOIN ontms_docto_sal ods "
                + " ON ods.docto_sal_agrupador   =oe.embarque_paq_agrupador "
                + " WHERE ods.docto_sal_agrupador='" + agrupador + "' "
                + " and ods.docto_sal_id='" + id + "' "
                + " GROUP BY oe.embarque_paq, "
                + "  TO_CHAR(embarque_paq_captura  ,'dd/mm/yyyy hh:mi'),"
                + "  oe.embarque_paq_agrupador "
                + " union all "
                + " SELECT oe.embarque_id, "
                + "  TO_CHAR(embarque_fec_captura ,'dd/mm/yyyy hh:mi'), "
                + "   oe.embarque_agrupador"
                + " FROM ontms_embarque oe "
                + " INNER JOIN ontms_docto_sal ods "
                + " ON ods.docto_sal_agrupador   =oe.embarque_agrupador "
                + " WHERE ods.docto_sal_agrupador='" + agrupador + "' "
                + " and ods.docto_sal_id='" + id + "' "
                + " GROUP BY oe.embarque_id, "
                + "  TO_CHAR(embarque_fec_captura ,'dd/mm/yyyy hh:mi'), "
                + "  oe.embarque_agrupador "
                + " UNION "
                + " SELECT oe.embarque_id, "
                + "  TO_CHAR(embarque_fec_captura ,'dd/mm/yyyy hh:mi'), "
                + "  oe.embarque_agrupador "
                + " FROM ontms_embarque oe "
                + " INNER JOIN ontms_hist_docto_sal ods "
                + " ON ods.docto_sal_agrupador=oe.embarque_agrupador "
                + " LEFT JOIN ontms_camion oc "
                + " ON oc.camion_id=oe.camion_id "
                + " LEFT JOIN ontms_chofer och "
                + " ON och.chofer_id=oe.chofer_id "
                + " LEFT JOIN ontms_unidad_transporte outr "
                + " ON outr.utransporte_id=oc.utransporte_id "
                + " LEFT JOIN ontms_linea_transporte olt "
                + " ON olt.ltransporte_id    =oc.ltransporte_id "
                + " WHERE ods.docto_sal_agrupador   ='" + agrupador + "' "
                + " and ods.docto_sal_id='" + id + "' "
                + " AND ods.docto_estado_id IN (" + es + ")"
                + " GROUP BY oe.embarque_id, "
                + "  TO_CHAR(embarque_fec_captura ,'dd/mm/yyyy hh:mi'), "
                + "  oe.embarque_agrupador "
                + " ORDER BY 2 ";

        /*  sql = " SELECT oe.embarque_id,to_char(embarque_fec_captura ,'dd/mm/yyyy hh:mi'),oe.embarque_agrupador FROM ontms_embarque oe"
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
        
         */
        return sql;
    }

    public String consultaPaqHilti(String[] id) {
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

    public String muestraDocumentosRechazoHilti(String agrupador, String tabla) {
        sql = "SELECT docto_rechazo,to_char(docto_fec_rechazo ,'dd/mm/yyyy') FROM " + tabla + ""
                + " WHERE docto_sal_id ='" + agrupador + "' AND (docto_estado_id = 10 or docto_estado_id = 11) GROUP BY docto_rechazo,to_char(docto_fec_rechazo ,'dd/mm/yyyy')";
        return sql;
    }

    public String muestraDocumentosEvidenciaHilti(String agrupador, String tabla) {
        sql = "SELECT docto_folio_evidencia,NVL(TO_CHAR(TO_DATE(docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ') FROM " + tabla + ""
                + " WHERE docto_sal_id ='" + agrupador + "'"
                + " AND (docto_estado_id = 7 or docto_estado_id = 8) GROUP BY docto_folio_evidencia,NVL(TO_CHAR(TO_DATE(docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ')";
        return sql;
    }

    public String muestraDocumentosDetalleProductoHilti(String agrupador) {
        sql = "SELECT op.producto_desc,ods.docto_sku_cantidad,ods.producto_sku,NVL(cantidad_rechazada,0), ods.producto_sku "
                + " FROM ontms_docto_sku ods"
                + " INNER JOIN ontms_producto op ON ods.producto_sku=op.producto_sku"
                + " WHERE ods.docto_sal_id='" + agrupador + "' GROUP BY ods.producto_sku,op.producto_desc,ods.docto_sku_cantidad,ods.producto_sku,cantidad_rechazada"
                + " ORDER BY op.producto_desc ";
        return sql;
    }

    public String detalleDocumentoModificarHilti(String id) {

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
                + " NVL(docto_importe,0), "
                + " NVL(docto_peso,0), "
                + " NVL(docto_volumen,0), "
                + " docto_fec_captura, "
                + " ods.tipo_docto_id, "
                + " otd.tipo_docto_desc, "
                + " ods.cliente_id, "
                + " ddh.destino_nombre,  "
                + " ods.destino_id, "
                + " ddh.destino_id, "
                + " ddh.destino_nombre , "
                + " NVL(docto_piezas,0), "
                + " NVL(docto_cajas,0), "
                + " NVL(docto_pallets,0), "
                + " NVL(docto_colgados,0), "
                + " NVL(docto_contenedor,0), "
                + " NVL(docto_atados,0), "
                + " NVL(docto_bulks,0), "
                + " NVL(TO_CHAR(docto_fec_factura,'dd/mm/yyyy'),' ') "
                + " FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_cta_bod_div ocbd "
                + " ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_bodega ob "
                + " ON ob.bodega_id = ocbd.bodega_id "
                + " INNER JOIN ontms_cuenta oc "
                + " ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division od "
                + " ON od.division_id = ocbd.division_id "
                + " INNER JOIN DILOG_DESTINOS_HILTI ddh "
                + " ON ddh.destino_ship_to =ods.destino_id "
                + " and ddh.division_id = ods.cbdiv_id "
                + " LEFT JOIN ontms_bodega ob1 "
                + " ON ob1.bodega_id=ods.bodega_id "
                + " LEFT JOIN ontms_tipo_docto otd "
                + " ON otd.tipo_docto_id=ods.tipo_docto_id "
                + " "
                + " WHERE docto_sal_id  ='" + id + "' AND ods.docto_estado_id <7 ";

        return sql;
    }

    public String trakingGeneralListaHilti(String documento, String cveCuenta, String cbdivId) {

        sql = " SELECT ohds.cbdiv_id,"
                + "  docto_referencia, "
                + "  oc.cuenta_nombre, "
                + "  ob.bodega_nombre, "
                + "  odv.division_nombre, "
                + "  ohds.docto_sal_id "
                + " FROM ontms_docto_sal ohds "
                + " INNER JOIN ontms_cta_bod_div ocbd "
                + " ON ohds.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_bodega ob "
                + " ON ob.bodega_id = ocbd.bodega_id "
                + " INNER JOIN ontms_cuenta oc "
                + " ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv "
                + " ON odv.division_id = ocbd.division_id "
                + " INNER JOIN DILOG_DESTINOS_HILTI ddh "
                + " ON ddh.destino_ship_to =ohds.destino_id "
                + " and ddh.division_id = ohds.cbdiv_id "
                + " LEFT JOIN ontms_bodega ob1 "
                + " ON ob1.bodega_id=ohds.bodega_id "
                + " INNER JOIN ontms_tipo_docto otd "
                + " ON otd.tipo_docto_id           =ohds.tipo_docto_id "
                + " WHERE upper(docto_referencia) IN (upper('" + documento + "')) "
                + " AND ohds.cbdiv_id             IN (" + cbdivId + ") "
                + " AND docto_estado_id           IN (0,11,1) "
                + " GROUP BY ohds.cbdiv_id, "
                + "   docto_referencia, "
                + "  oc.cuenta_nombre, "
                + "  ob.bodega_nombre, "
                + "  odv.division_nombre, "
                + "  ohds.docto_sal_id "
                + " ORDER BY oc.cuenta_nombre  ";

        return sql;
    }

    public String detalleDocumentosEmbarcadosCancelarHilti(String id_doc, String cveCuenta, String division) {

        sql = " SELECT ods.docto_referencia,  "
                + "  NVL(TO_CHAR(ods.docto_piezas),' '),  "
                + "  ddh.destino_nombre,  "
                + "   ddh.destino_ciudad,   "
                + "   ddh.destino_estado,  "
                + "  ods.docto_sal_id,  "
                + "  ods.docto_sal_agrupador  "
                + " FROM ontms_docto_sal ods  "
                + " INNER JOIN ontms_embarque_paqueteria oe  "
                + " ON ods.docto_sal_agrupador=oe.EMBARQUE_PAQ_AGRUPADOR "
                + " INNER JOIN ontms_cta_bod_div ocbd  "
                + " ON ods.cbdiv_id = ocbd.cbdiv_id  "
                + " INNER JOIN ontms_bodega ob  "
                + " ON ob.bodega_id = ocbd.bodega_id  "
                + " INNER JOIN ontms_cuenta oc  "
                + " ON oc.cuenta_id = ocbd.cuenta_id  "
                + " INNER JOIN ontms_division odv  "
                + " ON odv.division_id = ocbd.division_id  "
                + " INNER JOIN DILOG_DESTINOS_HILTI ddh  "
                + " ON ddh.destino_ship_to =ods.destino_id  "
                + " and ddh.division_id = ods.cbdiv_id "
                + " WHERE ods.docto_sal_id IN ('" + id_doc + "')  "
                + " AND ods.cbdiv_id       IN (" + cveCuenta + ")  "
                + " AND ods.docto_estado_id =2  "
                + " GROUP BY ods.docto_referencia,  "
                + "  ods.docto_piezas,  "
                + "   ddh.destino_nombre,  "
                + "  ddh.destino_ciudad,  "
                + "  ddh.destino_estado,   "
                + "  ods.docto_sal_id,  "
                + "  ods.docto_sal_agrupador  "
                + " ORDER BY ods.docto_referencia ";

        /*  sql = "SELECT ods.docto_referencia, NVL(to_char(ods.docto_piezas),' '), od.destino_nombre,oc.ciudad_nombre, oes.estado_nombre,ods.docto_sal_id,ods.docto_sal_agrupador"
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
         * */
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

    public String detalleEvidenciaGuiaEmbarqueHilti(String agrupador) {

        sql = " SELECT ods.docto_referencia, "
                + "  NVL(ods.docto_piezas,0), "
                + "  ddh.destino_nombre, "
                + "  ddh.destino_ciudad, "
                + "  ddh.destino_estado, "
                + "  ods.docto_sal_id, "
                + "  oe.EMBARQUE_PAQ_AGRUPADOR, "
                + "  oe.EMBARQUE_PAQ, "
                + "  odv.division_id, "
                + "  ods.CBDIV_ID, "
                + "  SUM(NVL(odsk.cantidad_rechazada,0)), "
                + "  NVL(ods.docto_sal_cantidad,0) "
                + " FROM ontms_docto_sal ods "
                + " INNER JOIN ontms_embarque_paqueteria oe "
                + " ON ods.docto_sal_agrupador=oe.EMBARQUE_PAQ_AGRUPADOR "
                + " INNER JOIN ontms_cta_bod_div ocbd "
                + " ON ods.cbdiv_id = ocbd.cbdiv_id "
                + " INNER JOIN ontms_bodega ob "
                + " ON ob.bodega_id = ocbd.bodega_id "
                + " INNER JOIN ontms_cuenta oc "
                + " ON oc.cuenta_id = ocbd.cuenta_id "
                + " INNER JOIN ontms_division odv "
                + " ON odv.division_id = ocbd.division_id "
                + " INNER JOIN dilog_destinos_hilti ddh "
                + " ON ddh.destino_ship_to=ods.destino_id "
                + " and ddh.division_id = ods.cbdiv_id "
                + " LEFT JOIN ontms_docto_sku odsk "
                + " ON odsk.docto_sal_id         =ods.docto_sal_id "
                + " WHERE oe.EMBARQUE_PAQ_AGRUPADOR IN ('" + agrupador + "') "
                + " AND ods.docto_estado_id     IN (2,10) "
                + " AND oe.EMBARQUE_PAQ_ESTADO_ID   <>4 "
                + " GROUP BY ods.docto_referencia, "
                + "  ods.docto_piezas, "
                + "  ddh.destino_nombre, "
                + "  ddh.destino_ciudad, "
                + "  ddh.destino_estado, "
                + "  ods.docto_sal_id, "
                + "  oe.EMBARQUE_PAQ_AGRUPADOR, "
                + "  oe.EMBARQUE_PAQ, "
                + "  odv.division_id, "
                + "  ods.cbdiv_id, "
                + "  ods.docto_sal_cantidad "
                + " ORDER BY ods.docto_referencia ";


        /*   sql = " SELECT ods.docto_referencia, "
         + "  NVL(ods.docto_piezas,0), "
         + "  ddh.destino_nombre, "
         + "  ddh.destino_ciudad, "
         + "  ddh.destino_estado, "
         + "  ods.docto_sal_id, "
         + "  oe.embarque_agrupador, "
         + "  oe.embarque_id, "
         + "  odv.division_id, "
         + "  ods.CBDIV_ID, "
         + "  SUM(NVL(odsk.cantidad_rechazada,0)), "
         + "  NVL(ods.docto_sal_cantidad,0) "
         + " FROM ontms_docto_sal ods "
         + " INNER JOIN ontms_embarque oe "
         + " ON ods.docto_sal_agrupador=oe.embarque_agrupador "
         + " INNER JOIN ontms_cta_bod_div ocbd "
         + " ON ods.cbdiv_id = ocbd.cbdiv_id "
         + " INNER JOIN ontms_bodega ob "
         + " ON ob.bodega_id = ocbd.bodega_id "
         + " INNER JOIN ontms_cuenta oc "
         + " ON oc.cuenta_id = ocbd.cuenta_id "
         + " INNER JOIN ontms_division odv "
         + " ON odv.division_id = ocbd.division_id "
         + " INNER JOIN dilog_destinos_hilti ddh "
         + " ON ddh.destino_ship_to=ods.destino_id "
         + " LEFT JOIN ontms_docto_sku odsk "
         + " ON odsk.docto_sal_id         =ods.docto_sal_id "
         + " WHERE oe.embarque_agrupador IN ('" + agrupador + "') "
         + " AND ods.docto_estado_id     IN (2,10) "
         + " AND oe.embarque_estado_id   <>4 "
         + " GROUP BY ods.docto_referencia, "
         + "   ods.docto_piezas, "
         + "  ddh.destino_nombre, "
         + "  ddh.destino_ciudad, "
         + "  ddh.destino_estado, "
         + "  ods.docto_sal_id, "
         + "  oe.embarque_agrupador, "
         + "  oe.embarque_id, "
         + "  odv.division_id, "
         + "  ods.cbdiv_id, "
         + "  ods.docto_sal_cantidad "
         + " ORDER BY ods.docto_referencia ";

         /*sql = "SELECT ods.docto_referencia,NVL(ods.docto_piezas,0),od.destino_nombre,oc.ciudad_nombre, oes.estado_nombre,ods.docto_sal_id, oe.embarque_agrupador,oe.embarque_id,ocd.division_id,ods.CBDIV_ID,sum(NVL(odsk.cantidad_rechazada,0)),"
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

    public String servicioClienteHilti(String embarque) {
        sql = " SELECT oep.EMBARQUE_PAQ,  "
                + "  TO_CHAR(oep.EMBARQUE_PAQ_CAPTURA,'dd/mm/yyyy hh:mi'),   "
                + "  oep.CHOFER, op.PAQUETERIA ,oep.CHOFER,oep.EMBARQUE_PAQ_AGRUPADOR  "
                + " FROM ontms_embarque_paqueteria  oep  "
                + "  INNER JOIN ontms_paqueterias op  "
                + "  ON op.PAQUETERIA_ID = oep.PAQUETERIA_ID  "
                + "  INNER JOIN ONTMS_DOCTO_SAL ods  "
                + "  on ods.docto_sal_agrupador = oep.EMBARQUE_PAQ_AGRUPADOR  "
                + " WHERE oep.EMBARQUE_PAQ_AGRUPADOR=('" + embarque + "') "
                + " GROUP BY oep.EMBARQUE_PAQ,  "
                + "  oep.EMBARQUE_PAQ_CAPTURA,  "
                + "  oep.CHOFER, op.PAQUETERIA ,oep.EMBARQUE_PAQ_AGRUPADOR";
        return sql;
    }
    //cveCuenta, fec1, fec2,"0,1",idCuentaBroker

    public String pendienteEmHilti(String[] id) {
        sql = " SELECT ods.d_docto_pedido, "
                + "  ods.d_docto_fec_pedido, "
                + "  ods.d_docto_referencia, "
                + "  ods.d_docto_fec_captura, "
                + "   ods.ddh_destino_nombre, "
                + "  ods.ddh_destino_colonia,  "
                + "   ods.ddh_destino_ciudad,  "
                + "  ods.d_docto_piezas, "
                + "  ods.d_docto_cajas, "
                + "  ods.d_docto_pallets, "
                + "  ods.d_docto_colgados, "
                + "  ods.d_docto_contenedor, "
                + "  ods.d_docto_atados, "
                + "  ods.d_docto_bulks, "
                + "  ods.d_docto_importe, "
                + "  ods.d_docto_peso, "
                + "  ods.d_docto_volumen, "
                + "  ods.div_division_nombre "
                + " FROM ontms_vw_reporte_doc2 ods "
                + " WHERE d_docto_estado_id IN (" + id[3] + ")"
                + " AND d_cbdiv_id          IN (" + id[0] + ") "
                //+ " AND TRUNC(d_docto_fec_captura) BETWEEN to_date('" + id[1] + "','DD/MM/YYYY') AND to_date('" + id[2] + "','DD/MM/YYYY') "
                + " AND TRUNC(d_docto_fec_captura) >= TO_CHAR(to_date('" + id[1] + "','dd/mm/yy')) "
                + " AND TRUNC(d_docto_fec_captura) <=  TO_CHAR(to_date('" + id[2] + "','dd/mm/yy'))"
                + " ORDER BY D_Docto_Referencia ";

        /* sql = "SELECT ods.d_docto_pedido,ods.d_docto_fec_pedido,ods.d_docto_referencia,"
         + " ods.d_docto_fec_captur a,ods.des_destino_nombre,ods.col_colonia_nombre,"
         + " ods.ciu_ciudad_nombre,ods.d_docto_piezas,ods.d_docto_cajas,"
         + " ods.d_docto_pallets,ods.d_docto_colgados, ods.d_docto_contenedor,ods.d_docto_atados,"
         + " ods.d_docto_bulks,ods.d_docto_importe,ods.d_docto_peso,ods.d_docto_volumen,ods.div_division_nombre"
         + " FROM ontms_vw_reporte_doc2 ods"
         + " WHERE d_docto_estado_id IN (" + id[3] + ") AND d_cbdiv_id IN (" + id[0] + ")"
         + " AND trunc(d_docto_fec_captura) BETWEEN to_date('"+id[1]+"','DD/MM/YYYY') AND to_date('"+id[2]+"','DD/MM/YYYY') "
         + " ORDER BY D_Docto_Referencia";
         * */
        return sql;
    }

    public String doctosnovisibles(String[] id) {

        sql = " select ods.docto_referencia, NVL(ods.docto_fec_captura,''), NVL(ods.docto_fec_factura,''), "
                + "        NVL(ods.destino_id,''), NVL(ddh.destino_ship_to,''), NVL(ddh.destino_cp,0), "
                + "        NVL(dodp.id_destino,0) "
                + " from ontms_docto_sal ods  "
                + " left join dilog_destinos_hilti ddh on ods.destino_id=ddh.destino_ship_to "
                + " left join dilog_orig_dest_paq dodp on ddh.destino_cp=dodp.iata_cp_destino "
                + " where ddh.destino_ship_to is null "
                + " or ddh.destino_cp is null  "
                + " or dodp.id_origen is null  ";
        /*
         sql = " select ods.docto_referencia,"
         + " NVL(ods.docto_fec_captura,''),"
         + " NVL(ods.docto_fec_factura,''),"
         + " NVL(ods.destino_id,''),"
         + " NVL(ddh.destino_ship_to,''),"
         + " NVL(ddh.destino_cp,0),"
         + " NVL(dodp.id_destino,0) "
         + " from ontms_docto_sal ods "
         + " left join dilog_destinos_hilti ddh"
         + " on ods.destino_id=ddh.destino_ship_to"
         + " left join dilog_orig_dest_paq dodp"
         + " on ddh.destino_cp=dodp.id_destino"
         + " where ddh.destino_ship_to is null"
         + " or ddh.destino_cp is null"
         + " or dodp.id_origen is null";
         
         */
        return sql;
    }

    public String viajesDiariosDocumentoHIli(String[] id) {
        sql = "SELECT /*+ FIRST_ROWS */ ods.div_division_nombre,ods.d_docto_referencia,ods.d_docto_fec_captura,"
                + " ods.d_docto_pedido,ods.d_docto_fec_pedido,ods.ddh_destino_ship_to,ods.ddh_destino_nombre,"
                + " ods.ddh_destino_ciudad,oe.oe_embarque_id,oe.oe_embarque_fec_captura,ods.d_docto_piezas,"
                + " ods.d_docto_cajas,ods.d_docto_pallets,ods.d_docto_importe,ods.d_docto_peso,ods.d_docto_volumen,oe.olt_ltransporte_nombre,oe.oc_chofer_nombre,"
                + " oe.outr_utransporte_desc,oe.oca_camion_placas,oes.edo_desc,ods.d_docto_evidencia, NVL(TO_CHAR(TO_DATE(ods.d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ') ,"
                + " ods.d_docto_folio_control,ods.d_docto_fec_control "
                + " FROM ontms_vw_reporte_doc2 ods"
                + " LEFT JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador"
                + " INNER JOIN app_estado oes ON oes.edo_valor  =ods.d_docto_estado_id"
                + " WHERE d_cbdiv_id IN (" + id[0] + ") AND oes.edo_tabla ='ONTMS_DOCTO_SAL'"
                + " AND to_Date(oe.oe_embarque_fec_captura,'dd/mm/yyyy') >= to_date('" + id[1] + "','dd/mm/yy') "
                + " AND to_Date(oe.oe_embarque_fec_captura,'dd/mm/yyyy') <= to_date('" + id[2] + "','dd/mm/yy') "
                + " ORDER BY D_Docto_Referencia";
        // + " AND to_date(oe.oe_embarque_fec_captura) BETWEEN TO_CHAR('"+id[1]+"','DD/MM/YYYY') AND TO_CHAR('"+id[2]+"','DD/MM/YYYY') "
        //+ " AND TO_CHAR(oe.oe_embarque_fec_captura) BETWEEN TO_CHAR('" + id[1] + "') AND TO_CHAR('" + id[2] + "') "

        return sql;
    }

    public String viajesHilti(String[] id) {
        sql = "SELECT oe.oe_embarque_id,oe.oe_embarque_fec_captura,oe.oe_embarque_agrupador,"
                + " (SELECT ode_destino_nombre FROM vw_consulta_doc "
                + " WHERE ods_docto_Sal_agrupador=oe.oe_embarque_agrupador"
                + " AND ROWNUM =1) AS destino,oe.oe_embarque_costo_real,"
                + " SUM(NVL(oc.d_docto_piezas, 0)),SUM(NVL(oc.d_docto_cajas, 0)),"
                + " SUM(NVL(oc.d_docto_pallets, 0)),SUM(NVL(oc.d_docto_colgados, 0)),"
                + " SUM(NVL(oc.d_Docto_Contenedor, 0)),SUM(NVL(oc.d_docto_atados, 0)),"
                + " SUM(NVL(oc.d_docto_bulks, 0)),SUM(NVL(oc.d_docto_importe, 0)),"
                + " SUM(NVL(oc.d_docto_peso, 0)),SUM(NVL(oc.d_docto_volumen, 0)),"
                + " NVL(oe.oc_chofer_nombre,' '),NVL(oe.olt_ltransporte_nombre,' '),NVL(oe.outr_utransporte_desc,' '),"
                + " oe_embarque_fec_inicio,oe_embarque_fec_fin"
                + " FROM vw_pq_em oe"
                + " INNER JOIN ontms_vw_reporte_doc oc ON oc.d_docto_Sal_agrupador=oe.oe_embarque_agrupador"
                + " WHERE oc.d_cbdiv_id IN (" + id[0] + ") AND to_date(oe_embarque_fec_captura,'DD/MM/YYYY') BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') " + id[3] + ""
                + " GROUP BY oe.oe_embarque_id, oe.oe_embarque_fec_captura, oe.oe_embarque_agrupador,"
                + " oe.oe_embarque_costo_real, oe.oc_chofer_nombre, oe.olt_ltransporte_nombre, oe.outr_utransporte_desc,"
                + " oe_embarque_fec_inicio,oe_embarque_fec_fin"
                + " ORDER BY oe.oe_embarque_id";
        return sql;
    }

    public String viajesxHilti(String[] id) {
        sql = "SELECT OE_EMBARQUE_ID,"
                + "OE_EMBARQUE_FEC_CAPTURA,"
                + "OC_CHOFER_ID,"
                + "OC_CHOFER_NOMBRE,"
                + "OLT_LTRANSPORTE_ID,"
                + "OLT_LTRANSPORTE_NOMBRE,"
                + "OUTR_UTRANSPORTE_ID,"
                + "OUTR_UTRANSPORTE_DESC,"
                + "OE_EMBARQUE_AGRUPADOR,"
                + "OE_EMBARQUE_COSTO_REAL,"
                + "OE_EMBARQUE_ESTADO_ID,"
                + "OE_SAP_MIGRADO,"
                + "OE_SAP_ESTATUS,"
                + "OE_EMBARQUE_FEC_FLETE,"
                + "OE_EMBARQUE_FEC_INICIO,"
                + "OE_EMBARQUE_FEC_FIN,"
                + "OCA_CAMION_PLACAS "
                + "FROM vw_pq_em WHERE TO_CHAR(oe_embarque_fec_captura) BETWEEN '" + id[0] + "' AND '" + id[1] + "'";
        return sql;
    }

    public String viajesDiariosDocumentoHilti(String[] id) {
        sql = "SELECT /*+ FIRST_ROWS */ ods.div_division_nombre,ods.d_docto_referencia,ods.d_docto_fec_captura,"
                + " ods.d_docto_pedido,ods.d_docto_fec_pedido,ods.ddh_destino_ship_to,ods.ddh_destino_nombre,"
                + " ods.ddh_destino_ciudad,,oe.oe_embarque_id,oe.oe_embarque_fec_captura,ods.d_docto_piezas,"
                + " ods.d_docto_cajas,ods.d_docto_pallets,ods.d_docto_colgados,ods.d_docto_contenedor,ods.d_docto_atados,"
                + " ods.d_docto_bulks,ods.d_docto_importe,ods.d_docto_peso,ods.d_docto_volumen,oe.olt_ltransporte_nombre,oe.oc_chofer_nombre,"
                + " oe.outr_utransporte_desc,oe.oca_camion_placas,oes.edo_desc,ods.d_docto_evidencia,NVL(TO_CHAR(TO_DATE(ods.d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ') , "
                + " ods.d_docto_folio_control,ods.d_docto_fec_control "
                + " FROM ontms_vw_reporte_doc2 ods"
                + " LEFT JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador"
                + " INNER JOIN app_estado oes ON oes.edo_valor  =ods.d_docto_estado_id"
                + " WHERE d_cbdiv_id IN (" + id[0] + ") AND oes.edo_tabla ='ONTMS_DOCTO_SAL'"
                + " AND to_date(oe.oe_embarque_fec_captura) BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') "
                + " ORDER BY D_Docto_Referencia";
        return sql;
    }

    public String pEvidenciaHilti(String[] id) {
        sql = "SELECT /*+ FIRST_ROWS */ ods.d_docto_fec_factura,"
                + " ods.d_docto_referencia,v.oe_embarque_id,v.oe_embarque_fec_captura,"
                + " v.olt_ltransporte_nombre,ods.des_destino_nombre,ods.col_colonia_nombre,"
                + " ods.ciu_ciudad_nombre,ods.d_docto_piezas,ods.d_docto_cajas,ods.d_docto_pallets,"
                + " ods.d_docto_colgados,ods.d_docto_contenedor,ods.d_docto_atados,ods.d_docto_bulks,"
                + " ods.d_docto_peso,ods.d_docto_importe,ods.d_docto_volumen,ods.div_division_nombre"
                + " FROM vw_pq_em v"
                + " INNER JOIN ontms_vw_reporte_doc2 ods ON ods.d_docto_sal_agrupador=v.oe_embarque_agrupador"
                + " WHERE ods.d_docto_estado_id IN (" + id[1] + ")  AND d_cbdiv_id IN (" + id[0] + ") " + id[2] + ""
                + " ORDER BY D_Docto_Referencia";
        return sql;
    }

    public String docEntregadosHilti(String[] id) {

        sql = " SELECT  "
                + "  /*+ FIRST_ROWS */  "
                + "  d_docto_captura_evidencia,  "
                + "  ods.d_docto_referencia,  "
                + "  ods.ddh_destino_nombre,  "
                + "  ods.ddh_destino_colonia,  "
                + "  ods.ddh_destino_ciudad,  "
                + "  ods.d_docto_piezas,  "
                + "  ods.d_docto_cajas,  "
                + "  ods.d_docto_pallets,  "
                + "  ods.d_docto_importe,  "
                + "  ods.d_docto_peso,  "
                + "  ods.d_docto_volumen,  "
                + "  ods.d_docto_observaciones,  "
                + "  ods.div_division_nombre  "
                + "FROM ontms_vw_reporte_doc2 ods  "
                + "LEFT JOIN vw_pq_em oe  "
                + "ON oe.oe_embarque_agrupador         =ods.d_docto_sal_agrupador  "
                + "WHERE d_docto_estado_id            IN (" + id[3] + ")  "
                + "AND d_cbdiv_id                     IN (" + id[0] + ")  "
                + "AND TRUNC(ods.d_docto_fec_captura) >= TO_CHAR(TO_DATE('" + id[1] + "','dd/MM/yyyy'))  "
                + "AND TRUNC(ods.d_docto_fec_captura) <= TO_CHAR(TO_DATE('" + id[2] + "','dd/MM/yyyy'))  "
                + "ORDER BY d_docto_referencia  ";

        /*
        
         sql = " SELECT "
         + " /*+ FIRST_ROWS /*
         + "  d_docto_captura_evidencia, "
         + " ods.d_docto_referencia, "
         + " ods.d_docto_evidencia, "
         + " ods.ddh_destino_nombre, "
         + " ods.ddh_destino_colonia,   "
         + " ods.ddh_destino_ciudad,   "
         + " ods.d_docto_piezas, "
         + " ods.d_docto_cajas, "
         + " ods.d_docto_pallets, "
         + " ods.d_docto_colgados, "
         + " ods.d_docto_contenedor, "
         + " ods.d_docto_atados, "
         + " ods.d_docto_bulks, "
         + " ods.d_docto_importe, "
         + " ods.d_docto_peso, "
         + " ods.d_docto_volumen, "
         + " ods.d_docto_observaciones, "
         + " ods.div_division_nombre "
         + " FROM ontms_vw_reporte_doc2 ods "
         + " LEFT JOIN vw_pq_em oe "
         + " ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador "
         + " WHERE d_docto_estado_id   IN (" + id[3] + ") "
         + " AND d_cbdiv_id            IN (" + id[0] + ") "
         //+ " AND TO_CHAR(d_docto_captura_evidencia) "
         //+ " BETWEEN TO_DATE('" + id[1] + "','DD/MM/YYYY') AND TO_DATE('" + id[2] + "','DD/MM/YYYY') "
         + " AND TRUNC(ods.d_docto_fec_captura) >= TO_CHAR(TO_DATE('" + id[1] + "','dd/MM/yyyy')) "
         + " AND TRUNC(ods.d_docto_fec_captura) <=  TO_CHAR(TO_DATE('" + id[2] + "','dd/MM/yyyy')) "
         + " ORDER BY d_docto_referencia ";

         */
 /*
         sql = "SELECT  /*+ FIRST_ROWS *//* d_docto_captura_evidencia,ods.d_docto_referencia,"
         + " ods.d_docto_evidencia,ods.des_destino_nombre,ods.col_colonia_nombre,"
         + " ods.ciu_ciudad_nombre,ods.d_docto_piezas,ods.d_docto_cajas,ods.d_docto_pallets,"
         + " ods.d_docto_colgados, ods.d_docto_contenedor,ods.d_docto_atados,ods.d_docto_bulks,"
         + " ods.d_docto_importe,ods.d_docto_peso,ods.d_docto_volumen,ods.d_docto_observaciones,"
         + " ods.div_division_nombre"
         + " FROM ontms_vw_reporte_doc ods"
         + " LEFT JOIN vw_pq_em oe ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador"
         + " WHERE d_docto_estado_id IN (" + id[3] + ") AND d_cbdiv_id IN (" + id[0] + ")"
         + " AND to_date(d_docto_captura_evidencia) BETWEEN TO_DATE('"+id[1]+"','DD/MM/YYYY') AND TO_DATE('"+id[2]+"','DD/MM/YYYY') "
         + " ORDER BY d_docto_referencia";
         */
        return sql;

    }

    public String pEvidenciaReporteHilti(String[] id) {
        sql = "SELECT /*+ FIRST_ROWS */ NVL( TO_CHAR(ods.d_docto_fec_factura),' '),"
                + " ods.d_docto_referencia,v.oe_embarque_id,v.oe_embarque_fec_captura,"
                + " v.olt_ltransporte_nombre,ods.ddh_destino_nombre,ods.ddh_destino_colonia,"
                + " ods.ddh_destino_ciudad,ods.d_docto_piezas,ods.d_docto_cajas,ods.d_docto_pallets,"
                + " ods.d_docto_colgados,ods.d_docto_contenedor,ods.d_docto_atados,ods.d_docto_bulks,"
                + " ods.d_docto_peso,ods.d_docto_importe,ods.d_docto_volumen,ods.div_division_nombre"
                + " FROM vw_pq_em v"
                + " INNER JOIN ontms_vw_reporte_doc2 ods ON ods.d_docto_sal_agrupador=v.oe_embarque_agrupador"
                + " WHERE ods.d_docto_estado_id IN (" + id[1] + ")  AND d_cbdiv_id IN (" + id[0] + ") " + id[2] + ""
                + " ORDER BY D_Docto_Referencia";
        /*sql = "SELECT /*+ FIRST_ROWS */ /*ods.d_docto_fec_factura,"
         + " ods.d_docto_referencia,v.oe_embarque_id,v.oe_embarque_fec_captura,"
         + " v.olt_ltransporte_nombre,ods.des_destino_nombre,ods.col_colonia_nombre,"
         + " ods.ciu_ciudad_nombre,ods.d_docto_piezas,ods.d_docto_cajas,ods.d_docto_pallets,"
         + " ods.d_docto_colgados,ods.d_docto_contenedor,ods.d_docto_atados,ods.d_docto_bulks,"
         + " ods.d_docto_peso,ods.d_docto_importe,ods.d_docto_volumen,ods.div_division_nombre"
         + " FROM vw_pq_em v"
         + " INNER JOIN ontms_vw_reporte_doc ods ON ods.d_docto_sal_agrupador=v.oe_embarque_agrupador"
         + " WHERE ods.d_docto_estado_id IN (" + id[1] + ")  AND d_cbdiv_id IN (" + id[0] + ") " + id[2] + ""
         + " ORDER BY D_Docto_Referencia";
         */

        return sql;
    }

    public String liquidacionHilti(String cve, String trans) {
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

    public String liquidacionEmbarqueHilti(String cve, String trans) {

        sql = " SELECT oe.embarque_paq, "
                + " TO_CHAR(oe.embarque_paq_captura,'dd/mm/yyyy'), "
                + " COUNT(DISTINCT ods.docto_sal_id), "
                + " opaq.paqueteria, "
                + " oe.chofer, "
                + " NVL(TO_CHAR(oe.embarque_paq_costo_real),' '), "
                + " NVL(TO_CHAR(oca.costo),' '), "
                + " oe.embarque_paq_agrupador "
                + " FROM ontms_embarque_paqueteria oe "
                + " INNER JOIN ontms_docto_Sal ods "
                + " ON ods.docto_sal_agrupador=oe.embarque_paq_agrupador "
                + " INNER JOIN ontms_paqueterias opaq "
                + " ON opaq.paqueteria_id = oe.paqueteria_id "
                + " LEFT JOIN ontms_cargos oca "
                + " ON oca.embarque_agrupador=oe.embarque_paq_agrupador "
                + " LEFT JOIN ontms_liquidacion ol "
                + " ON ol.embarque_agrupador=oe.embarque_paq_agrupador "
                + " WHERE ((ods.docto_folio_control IS NOT NULL "
                + " OR ods.DOCTO_FOLIO_EVIDENCIA      <>'0') "
                + " OR (ods.DOCTO_FOLIO_EVIDENCIA     IS NULL "
                + " AND ods.DOCTO_FOLIO_EVIDENCIA         IN (11))) "
                + " AND cbdiv_id                IN (" + cve + ") "
                + " AND oe.paqueteria_id        = " + trans + " "
                + " AND ol.embarque_agrupador   IS NULL "
                + " GROUP BY oe.embarque_paq, "
                + "   TO_CHAR(oe.embarque_paq_captura,'dd/mm/yyyy'), "
                + "   opaq.paqueteria, "
                + "   oe.chofer, "
                + "   NVL(TO_CHAR(oe.embarque_paq_costo_real),' '), "
                + "   NVL(TO_CHAR(oca.costo),' '), "
                + "   oe.embarque_paq_agrupador ";

        /* 
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
         */
        return sql;
    }

    public String pendSapHilti(String fec1, String fec2, String estado, String cveCuenta) {
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

        /*  sql = " SELECT embarque.embarque_paq_id, " +
         "  cuenta.cuenta_nombre, " +
         "  embarque.EMBARQUE_PAQ_CAPTURA , " +
         "  chofer.CHOFER_NOMBRE, " +
         "  ltrans.LTRANSPORTE_DESCRIPCION, " +
         "  utrans.UTRANSPORTE_DESC, " +
         "  embarque.embarque_paq_costo_real , " +
         "  costoind.embarque_indirectos_costo, " +
         "  gastost.GTRANSP_DESC, " +
         "  embarque.EMBARQUE_PAQ_AGRUPADOR, " +
         "  costoind.gtransp_id " +
         " FROM ontms_embarque_paqueteria embarque " +
         " INNER JOIN ontms_camion camion " +
         " ON camion.camion_placas = embarque.CAMION " +
         " INNER JOIN ontms_chofer chofer " +
         " ON embarque.chofer = chofer.chofer_nombre " +
         " INNER JOIN ontms_linea_transporte ltrans " +
         " ON ltrans.LTRANSPORTE_ID = camion.LTRANSPORTE_ID " +
         " INNER JOIN ontms_unidad_transporte utrans " +
         " ON utrans.UTRANSPORTE_ID = camion.UTRANSPORTE_ID " +
         " LEFT OUTER JOIN ontms_embarque_indirectos costoind " +
         " ON embarque.EMBARQUE_PAQ_AGRUPADOR = costoind.EMBARQUE_AGRUPADOR " +
         " LEFT OUTER JOIN ONTMS_GASTOS_TRANSPORTE gastost " +
         " ON gastost.gtransp_id = costoind.gtransp_id " +
         " INNER JOIN ontms_docto_sal historico " +
         " ON historico.DOCTO_SAL_AGRUPADOR = embarque.EMBARQUE_PAQ_AGRUPADOR " +
         " INNER JOIN ontms_cta_bod_div ctaboddiv " +
         " ON ctaboddiv.cbdiv_id=historico.cbdiv_id " +
         " INNER JOIN ontms_cuenta cuenta " +
         " ON ctaboddiv.cuenta_id           =cuenta.cuenta_id " +
         " AND embarque.embarque_paq_estado_id IN (" + estado + ") " +
         " AND TRUNC(embarque.embarque_paq_captura) BETWEEN TO_CHAR('"+fec1+"') AND TO_CHAR('"+fec2+"') " +
         " AND cuenta.cuenta_id IN (" + cveCuenta + ") " +
         " GROUP BY embarque.embarque_paq_id, " +
         "   embarque.EMBARQUE_PAQ_CAPTURA , " +
         "  ltrans.LTRANSPORTE_DESCRIPCION, " +
         "  utrans.UTRANSPORTE_DESC, " +
         "  embarque.embarque_paq_costo_real, " +
         "  chofer.CHOFER_NOMBRE , " +
         "  embarque.EMBARQUE_PAQ_AGRUPADOR , " +
         "  costoind.embarque_indirectos_costo, " +
         "  costoind.gtransp_id, " +
         "  gastost.GTRANSP_DESC , " +
         "  cuenta.cuenta_nombre, " +
         "  cuenta.cuenta_id " +
         " ORDER BY ltrans.LTRANSPORTE_DESCRIPCION ";
         */
        return sql;
    }

    public String fletGralCont1Hilti(String fec1, String fec2, String estado, String cveCuenta) {

        sql = " SELECT COUNT(em),  SUM(flete) "
                + " FROM "
                + " (SELECT oe.embarque_paq_id AS em, "
                + "   oe.EMBARQUE_PAQ_AGRUPADOR, "
                + "   oe.embarque_paq_estado_id, "
                + "   oe.embarque_paq_liquidacion, "
                + "   NVL(oe.embarque_paq_costo_real, 0) AS flete, "
                + "   ocu.cuenta_nombre                  AS cta, "
                + "   ocu.cuenta_id "
                + " FROM ontms_embarque_paqueteria oe, "
                + "   ontms_cuenta ocu, "
                + "   ontms_cta_bod_div ocbd, "
                + "   ontms_docto_sal ods, "
                + "   ontms_division od "
                + " WHERE  "
                + " oe.embarque_paq_estado_id IN (1,3,5,6,7) "
                + " AND ods.docto_sal_agrupador      =oe.EMBARQUE_PAQ_AGRUPADOR "
                + " AND ods.cbdiv_id                 =ocbd.cbdiv_id "
                + " AND ocbd.cuenta_id               =ocu.cuenta_id "
                + " AND od.division_id               = ocbd.division_id "
                + " AND ocu.cuenta_id               IN (963) "
                + " AND TRUNC(oe.embarque_paq_captura) BETWEEN TO_CHAR('" + fec1 + "') AND TO_CHAR('" + fec2 + "') "
                + " GROUP BY oe.embarque_paq_id, "
                + "   oe.EMBARQUE_PAQ_AGRUPADOR, "
                + "   oe.embarque_paq_estado_id, "
                + "   oe.embarque_paq_liquidacion, "
                + "   NVL(oe.embarque_paq_costo_real, 0), "
                + "   ocu.cuenta_nombre, "
                + "   ocu.cuenta_id "
                + " ) ";

        /* sql = " SELECT count(em), sum(flete) FROM"
         + " ( "
         + " SELECT oe.embarque_paq_id AS em, oe.EMBARQUE_PAQ_AGRUPADOR, oe.embarque_paq_estado_id,  "
         + " oe.embarque_paq_liquidacion,NVL(oe.embarque_paq_costo_real, 0) AS flete,  ocu.cuenta_nombre AS cta,ocu.cuenta_id"
         + " FROM ontms_embarque_paqueteria oe,"
         + " ontms_cuenta ocu,"
         + " ontms_cta_bod_div ocbd,"
         + " ontms_hist_docto_sal ods,  "
         + " ontms_division od"
         + " WHERE oe.embarque_estado_id IN (" + estado + ")"
         + " AND ods.docto_sal_agrupador  =oe.EMBARQUE_PAQ_AGRUPADOR"
         + " AND ods.cbdiv_id  =ocbd.cbdiv_id"
         + " AND ocbd.cuenta_id =ocu.cuenta_id"
         + " AND od.division_id = ocbd.division_id"
         + " AND ocu.cuenta_id  IN (" + cveCuenta + ")"
         + " AND trunc(oe.embarque_pac_captura) BETWEEN TO_CHAR('"+fec1+"') AND TO_CHAR('"+fec2+"')  "
         + "GROUP BY oe.embarque_paq_id, oe.EMBARQUE_PAQ_AGRUPADOR,oe.embarque_paq_estado_id,"
         + " oe.embarque_paq_liquidacion,NVL(oe.embarque_paq_costo_real, 0),"
         + " ocu.cuenta_nombre,ocu.cuenta_id )";
        
         */
        return sql;
    }

    public String fletGralContHilti(String fec1, String fec2, String estado, String cveCuenta) {

        sql = " SELECT oe.embarque_paq_id, "
                + " oe.EMBARQUE_PAQ_AGRUPADOR, "
                + " oe.embarque_paq_estado_id, "
                + " oe.embarque_paq_liquidacion, "
                + " oe.embarque_paq_costo_real, "
                + " ocu.cuenta_nombre, "
                + " ocu.cuenta_id "
                + " FROM ontms_embarque_paqueteria oe, "
                + " ontms_cuenta ocu, "
                + "  ontms_cta_bod_div ocbd "
                + " WHERE oe.embarque_paq_estado_id IN (" + estado + ") "
                + " AND  oe.embarque_paq_agrupador =oe.EMBARQUE_PAQ_AGRUPADOR "
                + " AND ocbd.cuenta_id               =ocu.cuenta_id "
                + " AND ocu.cuenta_id               IN (963) "
                + " AND TRUNC(oe.embarque_paq_captura) BETWEEN TO_CHAR('" + fec1 + "') AND TO_CHAR('" + fec2 + "') "
                + " GROUP BY oe.embarque_paq_id, "
                + " oe.EMBARQUE_PAQ_AGRUPADOR, "
                + "  oe.embarque_paq_estado_id, "
                + " oe.embarque_paq_liquidacion, "
                + " oe.embarque_paq_costo_real, "
                + " ocu.cuenta_nombre, "
                + " ocu.cuenta_id, "
                + "  ocbd.cbdiv_id ";

        /* sql = " SELECT oe.embarque_paq_id, oe.EMBARQUE_PAQ_AGRUPADOR, oe.embarque_paq_estado_id,"
         + " oe.embarque_paq_liquidacion,oe.embarque_paq_costo_real, ocu.cuenta_nombre,ocu.cuenta_id "
         + " FROM ontms_embarque_paqueteria oe, ontms_cuenta ocu, ontms_cta_bod_div ocbd, ontms_hist_docto_sal ods "
         + " WHERE oe.embarque_paq_estado_id IN (" + estado + ") AND ods.docto_sal_agrupador =oe.EMBARQUE_PAQ_AGRUPADOR "
         + " AND ods.cbdiv_id =ocbd.cbdiv_id AND ocbd.cuenta_id =ocu.cuenta_id "
         + " AND ocu.cuenta_id IN (" + cveCuenta + ") AND trunc(oe.embarque_paq_captura) BETWEEN TO_CHAR('" + fec1 + "') AND TO_CHAR('" + fec2 + "') "
         + " GROUP BY oe.embarque_paq_id, oe.EMBARQUE_PAQ_AGRUPADOR, oe.embarque_paq_estado_id,"
         + " oe.embarque_paq_liquidacion,oe.embarque_paq_costo_real, ocu.cuenta_nombre,ocu.cuenta_id";
         */
        return sql;
    }

    public String fletegralHilti(String[] id) {

        sql = " SELECT oe.oe_embarque_id, "
                + "  oe.olt_ltransporte_nombre, "
                + "  oe.outr_utransporte_desc, "
                + "  COUNT(DISTINCT docto_sal_id), "
                + "  oe.oe_embarque_costo_real, "
                + "  oe.oe_embarque_agrupador, "
                + "  SUM(NVL(docto_piezas,0)), "
                + "  SUM(NVL(docto_cajas,0)), "
                + "  SUM(NVL(docto_pallets,0)), "
                + "  SUM(NVL(docto_colgados,0)), "
                + "  SUM(NVL(docto_contenedor,0)), "
                + "  SUM(NVL(docto_atados,0)), "
                + "  SUM(NVL(docto_bulks,0)), "
                + "  SUM(NVL(docto_importe,0)), "
                + "  SUM(NVL(docto_peso,0)), "
                + "  SUM(NVL(docto_volumen,0)), "
                + "  ocu.cuenta_nombre, "
                + "  oe.oe_embarque_estado_id , "
                + "  oe.oe_sap_estatus,oe.oc_chofer_nombre "
                + " FROM VW_CONSULTA_EM oe "
                + " INNER JOIN ontms_docto_sal ods "
                + " ON ods.docto_sal_agrupador=oe.oe_embarque_agrupador "
                + " INNER JOIN ontms_cta_bod_div ocb "
                + " ON ocb.cbdiv_id=ods.cbdiv_id "
                + " INNER JOIN ontms_bodega ob "
                + " ON ob.bodega_id=ocb.bodega_id "
                + " LEFT JOIN ontms_bodega ob1 "
                + " ON ob1.bodega_id=ods.bodega_id "
                + " INNER JOIN ontms_cuenta ocu "
                + " ON ocu.cuenta_id =ocb.cuenta_id "
                + " WHERE TO_CHAR(oe.oe_embarque_fec_captura) BETWEEN '" + id[0] + "' AND '" + id[1] + "' "
                + " AND (oe.oe_embarque_estado_id <=5 "
                + " OR oe.oe_embarque_estado_id    =8) "
                + " AND ocb.cuenta_id                 IN (" + id[2] + ") "
                + " " + id[3]
                + " GROUP BY oe.oe_embarque_id, "
                + " oe.olt_ltransporte_nombre, "
                + " oe.outr_utransporte_desc, "
                + " oe.oe_embarque_costo_real, "
                + " oe.oe_embarque_agrupador, "
                + " ocu.cuenta_nombre, "
                + " oe.oe_embarque_estado_id, "
                + " oe.oe_sap_estatus,oe.oc_chofer_nombre "
                + " ORDER BY oe.oe_embarque_id  ";


        /*   sql = " SELECT  oe.oe_embarque_paq, "
         + " COUNT(DISTINCT docto_sal_id), "
         + " oe.olt_paqueteria, "
         + " oe.oe_chofer , "
         + " oe.oe_embarque_paq_costo_real, "
         + " oe.oe_embarque_paq_agrupador,  "
         + " SUM(NVL(docto_piezas,0)), "
         + " SUM(NVL(docto_cajas,0)), "
         + " SUM(NVL(docto_pallets,0)), "
         + " SUM(NVL(docto_colgados,0)), "
         + " SUM(NVL(docto_contenedor,0)), "
         + " SUM(NVL(docto_atados,0)), "
         + " SUM(NVL(docto_bulks,0)), "
         + " SUM(NVL(docto_importe,0)), "
         + " SUM(NVL(docto_peso,0)), "
         + " SUM(NVL(docto_volumen,0)), "
         + " oe.olt_paqueteria, "
         + " ocu.cuenta_nombre, "
         + " oe.oe_embarque_paq_estado_id  , "
         + " oe.oe_sap_estatus "
         + " FROM VW_CONSULTA_EM2 oe "
         + " INNER JOIN ontms_docto_sal ods "
         + " ON ods.docto_sal_agrupador=oe.oe_embarque_paq "
         + " INNER JOIN ontms_cta_bod_div ocb "
         + " ON ocb.cbdiv_id=ods.cbdiv_id "
         + " INNER JOIN ontms_bodega ob "
         + " ON ob.bodega_id=ocb.bodega_id "
         + " LEFT JOIN ontms_bodega ob1 "
         + " ON ob1.bodega_id=ods.bodega_id "
         + " INNER JOIN ontms_cuenta ocu "
         + " ON ocu.cuenta_id                         =ocb.cuenta_id "
         + " WHERE TO_CHAR(oe.oe_embarque_paq_fec_flete)  BETWEEN  '" + id[0] + "' AND  '" + id[1] + "' "
         + " AND (oe.oe_embarque_paq_estado_id           <=5 "
         + " OR oe.oe_embarque_paq_estado_id              =8) "
         + " AND ocb.cuenta_id                       IN (" + id[2] + ") " + id[3] + "  " + //and oe.oe_embarque_id like 'EM%'
         " GROUP BY oe.oe_embarque_paq,   "
         + "   oe.olt_paqueteria,   "
         + "   oe.oe_chofer,   "
         + "   oe.oe_embarque_paq_costo_real,  "
         + " oe.oe_embarque_paq_agrupador,  "
         + "  oe.olt_paqueteria,  "
         + "  ocu.cuenta_nombre, "
         + " oe.oe_embarque_paq_estado_id, "
         + "  oe.oe_sap_estatus "
         + " ORDER BY oe.oe_embarque_paq ";



         /* sql = "select   oe.oe_embarque_id,  COUNT(DISTINCT docto_sal_id),"
         + "  oe.olt_ltransporte_nombre,  "
         + "  oe.oc_chofer_nombre,"
         + "  oe.oe_embarque_costo_real,"
         + "  oe_embarque_agrupador,"
         + "  SUM(NVL(docto_piezas,0)),"
         + "  SUM(NVL(docto_cajas,0)),"
         + "  SUM(NVL(docto_pallets,0)),"
         + "  SUM(NVL(docto_colgados,0)),"
         + "  SUM(NVL(docto_contenedor,0)),"
         + "  SUM(NVL(docto_atados,0)),"
         + "  SUM(NVL(docto_bulks,0)),"
         + "  SUM(NVL(docto_importe,0)),"
         + "  SUM(NVL(docto_peso,0)),"
         + "  SUM(NVL(docto_volumen,0)),"
         + "  oe.outr_utransporte_desc,"
         + "  ocu.cuenta_nombre,"
         + "  oe.OE_EMBARQUE_ESTADO_ID,"
         + "  oe.oe_sap_estatus "
         + "  from VW_CONSULTA_EM oe "
         + "  INNER JOIN ontms_docto_sal ods"
         + "  ON ods.docto_sal_agrupador=oe.oe_embarque_agrupador "
         + "  INNER JOIN ontms_cta_bod_div ocb ON ocb.cbdiv_id=ods.cbdiv_id"
         + "  INNER JOIN ontms_bodega ob ON ob.bodega_id=ocb.bodega_id"
         + "  LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
         + "  INNER JOIN ontms_cuenta ocu ON ocu.cuenta_id =ocb.cuenta_id "
         + "  where"
         + "  TRUNC(oe.oe_embarque_fec_captura) >= '" + id[0] + "' AND TRUNC(oe.oe_embarque_fec_captura) <= '" + id[1] + "'"
         + "  and   (oe.oe_embarque_estado_id <=5 or oe.oe_embarque_estado_id=8)  and ocb.cuenta_id IN (" + id[2] + ") " + id[3] + " and oe.oe_embarque_id like 'EM%'"
         + "  GROUP BY oe.oe_embarque_id,  oe.olt_ltransporte_nombre,"
         + "  oe.oc_chofer_nombre,  oe.oe_embarque_costo_real,"
         + "  oe.oe_embarque_agrupador,  oe.outr_utransporte_desc,"
         + "  ocu.cuenta_nombre,  oe.OE_EMBARQUE_ESTADO_ID, oe.oe_sap_estatus"
         + "  ORDER BY oe.oe_embarque_id";
         */
        return sql;
    }

    public String reporteOHilti(String[] id) {

        sql = " SELECT "
                + " /*+ FIRST_ROWS */ "
                + " ods.div_division_nombre, "
                + " ods.d_docto_referencia, "
                + " NVL(TO_CHAR(ods.d_docto_fec_captura ,'dd/mm/yyyy HH:MM:SS'),' '), "
                + " ods.d_docto_pedido, "
                + " ods.d_docto_fec_pedido, "
                + " ods.ods_docto_concentrado, "
                + " ods.ods_docto_fec_concentrado, "
                + " NVL(ods.ddh_destino_ship_to,0), "
                + " ods.ddh_destino_nombre, "
                + " ods.ddh_destino_ciudad, "
                + " oe.oe_embarque_id, "
                + " oe.oe_embarque_fec_captura, "
                + " oe.oe_embarque_fec_fin, "
                + " oe.oe_embarque_fec_fin, "
                + " ods.d_docto_piezas, "
                + " ods.d_docto_cajas, "
                + " ods.d_docto_pallets, "
                + " ods.d_docto_colgados, "
                + " ods.d_docto_contenedor, "
                + " ods.d_docto_atados, "
                + " ods.d_docto_bulks, "
                + " ods.d_docto_importe, "
                + " ods.d_docto_peso, "
                + " ods.d_docto_volumen, "
                + " oe.olt_ltransporte_nombre, "
                + " oe.oc_chofer_nombre, "
                + " oe.outr_utransporte_desc, "
                + " oe.oca_camion_placas, "
                + " ods.d_docto_rechazo, "
                + " ods.d_docto_fec_rechazo, "
                + " ods.d_docto_evidencia, "
                + " NVL(TO_CHAR(TO_DATE(ods.d_docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' ') , "
                + " NVL(ods.d_docto_captura_evidencia,' '), "
                + " ods.d_docto_observaciones, "
                + " ods.d_docto_folio_control, "
                + " ods.d_docto_fec_control, "
                + " NVL(ol.folio_liquidacion,' '), "
                + " NVL(TO_CHAR(ol.fecha ,'dd/mm/yyyy'),' '), "
                + " oa.edo_desc  "
                + " " + id[3] + "  "
                + " FROM ontms_vw_reporte_doc2 ods "
                + " LEFT JOIN vw_pq_em oe "
                + " ON oe.oe_embarque_agrupador=ods.d_docto_sal_agrupador "
                + " " + id[4] + " "
                + " LEFT JOIN ontms_liquidacion ol "
                + " ON ol.embarque_agrupador=oe.oe_embarque_agrupador "
                + " INNER JOIN app_estado oa "
                + " ON edo_valor      =ods.d_docto_estado_id "
                + " WHERE d_cbdiv_id IN (" + id[0] + ") "
                + " AND oa.edo_tabla  ='ONTMS_DOCTO_SAL' "
                + " AND TRUNC(ods.d_docto_fec_captura) >= TO_CHAR(to_date('" + id[1] + "','dd/mm/yy')) "
                + " AND TRUNC(ods.d_docto_fec_captura) <=  TO_CHAR(to_date('" + id[2] + "','dd/mm/yy'))  "
                + " ORDER BY d_docto_fec_captura";
        return sql;
    }

    public String validarTipoEmbarque(String embarque) {

        sql = "  SELECT oe.embarque_paq , "
                + "  TO_CHAR(embarque_paq_captura ,'dd/mm/yyyy hh:mi'), coalesce(oe.chofer,'S/Chofer'), "
                + "  oe.embarque_paq_agrupador "
                + " FROM ontms_embarque_paqueteria oe "
                + " INNER JOIN ontms_docto_sal ods "
                + " ON ods.docto_sal_agrupador   =oe.embarque_paq_agrupador "
                + " WHERE oe.embarque_paq='" + embarque + "' "
                + " GROUP BY oe.embarque_paq, "
                + "  TO_CHAR(embarque_paq_captura  ,'dd/mm/yyyy hh:mi'), oe.chofer,"
                + "  oe.embarque_paq_agrupador "
                + " union all "
                + " SELECT oe.embarque_id, "
                + "  TO_CHAR(embarque_fec_captura ,'dd/mm/yyyy hh:mi'),coalesce(oc.chofer_nombre,'S/Chofer')  , "
                + "   oe.embarque_agrupador"
                + " FROM ontms_embarque oe "
                + " INNER JOIN ontms_docto_sal ods "
                + " ON ods.docto_sal_agrupador   =oe.embarque_agrupador  INNER JOIN  ontms_chofer oc ON oc.chofer_id = oe.chofer_id"
                + " WHERE oe.embarque_id='" + embarque + "' "
                + " GROUP BY oe.embarque_id, "
                + "  TO_CHAR(embarque_fec_captura ,'dd/mm/yyyy hh:mi'),oc.chofer_nombre , "
                + "  oe.embarque_agrupador "
                + " UNION "
                + " SELECT oe.embarque_id, "
                + "  TO_CHAR(embarque_fec_captura ,'dd/mm/yyyy hh:mi'), coalesce(oc.chofer_nombre,'S/Chofer'), "
                + "  oe.embarque_agrupador "
                + " FROM ontms_embarque oe "
                + " INNER JOIN ontms_hist_docto_sal ods "
                + " ON ods.docto_sal_agrupador=oe.embarque_agrupador "
                + " LEFT JOIN ontms_camion oc "
                + " ON oc.camion_id=oe.camion_id "
                + " LEFT JOIN ontms_chofer och "
                + " ON och.chofer_id=oe.chofer_id "
                + " LEFT JOIN ontms_unidad_transporte outr "
                + " ON outr.utransporte_id=oc.utransporte_id "
                + " LEFT JOIN ontms_linea_transporte olt "
                + " ON olt.ltransporte_id    =oc.ltransporte_id INNER JOIN  ontms_chofer oc ON oc.chofer_id = oe.chofer_id"
                + " WHERE oe.embarque_id='" + embarque + "' "
                + " GROUP BY oe.embarque_id, "
                + "  TO_CHAR(embarque_fec_captura ,'dd/mm/yyyy hh:mi'), oc.chofer_nombre ,"
                + "  oe.embarque_agrupador "
                + " ORDER BY 2 ";

        return sql;
    }

    public String validarTipoPDF(String agrupador) {

        sql = " SELECT  TIPO_SERVICIO "
                + " FROM  ONTMS_DOCTO_SAL  "
                + " where docto_sal_agrupador in('" + agrupador + "') ";

        return sql;
    }

    public String validarServicioPE(String agrupador) {

        sql = " SELECT oe.embarque_paq , "
                + " TO_CHAR(embarque_paq_captura ,'dd/mm/yyyy hh:mi'), "
                + " ods.tipo_servicio, "
                + " oe.chofer, "
                + " op.paqueteria, "
                + " oe.embarque_paq_agrupador "
                + " FROM ONTMS_EMBARQUE_PAQ_REIMP oe "
                + " INNER JOIN ontms_docto_sal ods "
                + " ON ods.docto_sal_agrupador   =oe.embarque_paq_agrupador "
                + " INNER JOIN ontms_paqueterias op "
                + " ON op.paqueteria_id   =oe.paqueteria_id "
                + " WHERE embarque_paq_agrupador =" + agrupador + " "
                + " AND ods.TIPO_SERVICIO        ='2' "
                + " GROUP BY oe.embarque_paq , "
                + " TO_CHAR(embarque_paq_captura ,'dd/mm/yyyy hh:mi'), "
                + " ods.tipo_servicio, "
                + " oe.chofer, "
                + " op.paqueteria, "
                + "  oe.embarque_paq_agrupador ";

        return sql;
    }

    public String validarServicioDHL(String agrupador) {

        sql = " SELECT oe.embarque_paq , "
                + " TO_CHAR(embarque_paq_captura ,'dd/mm/yyyy hh:mi'), "
                + "  ods.tipo_servicio, "
                + "  oe.chofer, "
                + " paqueteria_id"
                + " FROM ONTMS_EMBARQUE_PAQ_REIMP oe "
                + " INNER JOIN ontms_docto_sal ods "
                + " WHERE oe.embarque_paq      =" + agrupador + " "
                + " and ods.TIPO_SERVICIO ='1'  "
                + " GROUP BY oe.embarque_paq , "
                + " TO_CHAR(embarque_paq_captura ,'dd/mm/yyyy hh:mi'), "
                + "  ods.tipo_servicio, "
                + "  oe.chofer, "
                + " paqueteria_id ";

        return sql;
    }
}
