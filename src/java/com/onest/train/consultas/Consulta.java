/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

/**
 *
 * @author Teresa Martin
 */
public class Consulta {

    String sql = "";
    
    public String dashBoard(String fecha, String cuenta) {

        sql = "SELECT "
                + "(SELECT sum((oe.EMBARQUE_PAQ_COSTO_REAL)) "
                + "FROM ONTMS_EMBARQUE_PAQUETERIA oe "
                + "INNER JOIN ontms_docto_sal ods "
                + "ON ods.docto_sal_agrupador=oe.EMBARQUE_PAQ_AGRUPADOR "
                + "INNER JOIN ontms_cta_bod_div ocb ON ocb.cbdiv_id=ods.cbdiv_id "
                + "WHERE TRUNC(oe.EMBARQUE_PAQ_CAPTURA) BETWEEN TO_DATE('" + fecha + "','DD/MM/YYYY') AND sysdate "
                + "AND (oe.PAQUETERIA_ID=1) "
                + "AND ocb.cuenta_id          IN (" + cuenta + ") "
                + "), "
                + "(SELECT sum((oe.EMBARQUE_PAQ_COSTO_REAL)) "
                + "FROM ONTMS_EMBARQUE_PAQUETERIA oe "
                + "INNER JOIN ontms_docto_sal ods "
                + "ON ods.docto_sal_agrupador=oe.EMBARQUE_PAQ_AGRUPADOR "
                + "INNER JOIN ontms_cta_bod_div ocb "
                + "ON ocb.cbdiv_id=ods.cbdiv_id "
                + "WHERE TRUNC(oe.EMBARQUE_PAQ_CAPTURA) BETWEEN TO_DATE('" + fecha + "','DD/MM/YYYY') AND sysdate "
                + "AND (oe.PAQUETERIA_ID=2) "
                + "AND ocb.cuenta_id          IN (" + cuenta + ") "
                + "), "
                + "(SELECT sum(DISTINCT(oe.EMBARQUE_COSTO_REAL)) "
                + "FROM ONTMS_EMBARQUE oe "
                + "INNER JOIN ontms_docto_sal ods "
                + "ON ods.docto_sal_agrupador=oe.EMBARQUE_AGRUPADOR "
                + "INNER JOIN ontms_cta_bod_div ocb "
                + "ON ocb.cbdiv_id=ods.cbdiv_id "
                + "WHERE TRUNC(oe.EMBARQUE_FEC_CAPTURA) BETWEEN TO_DATE('" + fecha + "','DD/MM/YYYY') AND sysdate "
                + "AND ocb.cuenta_id          IN (" + cuenta + ") "
                + "), "
                + "(SELECT COUNT(DISTINCT(ods.DOCTO_REFERENCIA)) "
                + "FROM  ontms_docto_sal ods "
                + "INNER JOIN ontms_cta_bod_div ocb "
                + "ON ocb.cbdiv_id=ods.cbdiv_id "
                + "WHERE TRUNC(ods.DOCTO_FEC_CAPTURA) BETWEEN TO_DATE('" + fecha + "','DD/MM/YYYY') AND sysdate "
                + "AND ods.DOCTO_ESTADO_ID = 2 "
                + "AND ocb.cuenta_id          IN (" + cuenta + ") "
                + "), "
                + "(SELECT COUNT(DISTINCT(ods.DOCTO_REFERENCIA)) "
                + "FROM  ontms_docto_sal ods "
                + "INNER JOIN ontms_cta_bod_div ocb "
                + "ON ocb.cbdiv_id=ods.cbdiv_id "
                + "WHERE TRUNC(ods.DOCTO_FEC_CAPTURA) BETWEEN TO_DATE('" + fecha + "','DD/MM/YYYY') AND sysdate "
                + "AND ods.DOCTO_ESTADO_ID < 2 "
                + "AND ocb.cuenta_id          IN (" + cuenta + ") "
                + "), "
                + "(SELECT COUNT(DISTINCT(ods.DOCTO_REFERENCIA)) "
                + "FROM  ontms_docto_sal ods "
                + "INNER JOIN ontms_cta_bod_div ocb "
                + "ON ocb.cbdiv_id=ods.cbdiv_id "
                + "WHERE TRUNC(ods.DOCTO_FEC_CAPTURA) BETWEEN TO_DATE('" + fecha + "','DD/MM/YYYY') AND sysdate "
                + "AND ods.DOCTO_ESTADO_ID = 7 "
                + "AND ocb.cuenta_id          IN (" + cuenta + ") "
                + ") "
                + "from dual";
              return sql; 
    }  
    
    public String viajesx(String[] id) {
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
                + "FROM vw_pq_em WHERE to_date(oe_embarque_fec_captura) BETWEEN '" + id[0] + "' AND '" + id[1] + "'";
        return sql;
    }
    
    public String ruteoRep(String [] id)
    {
        sql="SELECT ods.docto_referencia,ocu.cuenta_nombre, od.destino_nombre,nvl(TO_CHAR(ods.docto_fec_factura,' '),' '), ods.docto_fec_captura,NVL(ods.docto_pedido,' '),"
                + "nvl(TO_CHAR(ods.docto_fec_pedido,' '),' '),NVL(ods.docto_piezas,0),NVL(ods.docto_cajas,0),NVL(ods.docto_pallets,0),NVL(ods.docto_colgados,0),NVL(ods.docto_contenedor,0),"
                + "NVL(ods.docto_atados,0), NVL(ods.docto_bulks,0),NVL(ods.docto_peso,0), NVL(ods.docto_volumen,0),NVL(ods.docto_importe,0),"
                + " ru.ruta_nombre"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div oct ON oct.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_cuenta ocu on ocu.cuenta_id=oct.cuenta_id"
                + " INNER JOIN ontms_conversion_destino oc ON oc.sucliente_id=ods.destino_id AND oc.division_id=oct.division_id"
                + " inner join ontms_bodega ob on ob.bodega_id=oct.bodega_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=oc.destino_id"
                + " INNER JOIN ontms_rutas ru ON ru.ciudad_id=od.ciudad_id AND ru.zona_id=ob.zona_id"
                + " WHERE ods.docto_estado_id IN (0,1) AND ru.ruta_nombre LIKE '%"+id[0]+"%'"
                + " and (ob.bodega_id="+id[1]+" AND oct.cuenta_id IN ("+id[2]+")) ORDER BY ru.ruta_nombre,ocu.cuenta_nombre";
        return sql;
    }
    public String ruteoRep0(String [] id)
    {
        sql="SELECT ods.docto_referencia,ocu.cuenta_nombre, od.destino_nombre,nvl(TO_CHAR(ods.docto_fec_factura,' '),' '), ods.docto_fec_captura,NVL(ods.docto_pedido,' '),"
                + "nvl(TO_CHAR(ods.docto_fec_pedido,' '),' '),NVL(ods.docto_piezas,0),NVL(ods.docto_cajas,0),NVL(ods.docto_pallets,0),NVL(ods.docto_colgados,0),NVL(ods.docto_contenedor,0),"
                + "NVL(ods.docto_atados,0), NVL(ods.docto_bulks,0),NVL(ods.docto_peso,0), NVL(ods.docto_volumen,0),NVL(ods.docto_importe,0),"
                + " ru.ruta_nombre"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div oct ON oct.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_cuenta ocu on ocu.cuenta_id=oct.cuenta_id"
                + " INNER JOIN ontms_conversion_destino oc ON oc.sucliente_id=ods.destino_id AND oc.division_id=oct.division_id"
                + " inner join ontms_bodega ob on ob.bodega_id=oct.bodega_id"
                + " INNER JOIN ontms_destino od ON od.destino_id=oc.destino_id"
                + " INNER JOIN ontms_rutas ru ON ru.ciudad_id=od.ciudad_id AND ru.zona_id=ob.zona_id"
                + " WHERE ods.docto_estado_id IN (0,1) AND ru.ruta_nombre LIKE '%"+id[0]+"%'"
                + " and (ob.bodega_id="+id[1]+" OR oct.cuenta_id IN ("+id[2]+")) ORDER BY ru.ruta_nombre,ocu.cuenta_nombre";
        return sql;
    }
    public String cuentaBodega(String id,String bod) {
        sql = "SELECT oc.cuenta_id ,oc.cuenta_nombre FROM broker_usuario_div bud"
                + " INNER JOIN broker_passwd bp ON bp.user_nid = bud.user_nid"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id = bud.cbdiv_id"                
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " WHERE 1=1 and bp.user_nid = " + id + " and ocbd.bodega_id="+bod+""
                + " GROUP BY oc.cuenta_id, oc.cuenta_nombre ORDER BY oc.cuenta_nombre";
        return sql;
    }
     public String ruta(String id) {
        sql = "SELECT ruta_nombre FROM ontms_rutas ru"
                + " INNER JOIN  ontms_bodega ob ON ob.zona_id=ru.zona_id"
                + " WHERE ob.bodega_id ="+id+"  GROUP BY ruta_nombre ORDER BY ruta_nombre";
        return sql;
    }
    
    
    public String ordenCarga(String id)
    {
        sql="SELECT ooc.ordencarga_folio,ooc.ordencarga_fecha,olt.ltransporte_nombre,"
                + "oc.camion_placas,och.chofer_nombre,otu.utransporte_desc,ooc.ordencarga_nombreayudante,"
                + "oct.cuenta_nombre,ooc.observaciones,ooc.ordencarga_fechacitado"
                + " FROM ontms_ordencarga ooc"
                + " INNER JOIN ontms_camion oc ON oc.camion_id=ooc.camion_id"
                + " INNER JOIN ontms_linea_transporte olt ON oc.ltransporte_id = olt.ltransporte_id"
                + " INNER JOIN ontms_chofer och ON ooc.chofer_id = och.chofer_id"
                + " INNER JOIN ontms_unidad_transporte otu ON oc.utransporte_id = otu.utransporte_id"
                + " INNER JOIN ontms_cuenta oct ON ooc.cuenta_id=oct.cuenta_id"
                + " WHERE ooc.ordencarga_id="+id+"";
        return sql;
    }
    public String controlManiobra(String id)
    {
        sql="Select om.maniobra_folio, om.maniobra_fecha_captura, op.proveedor_razon_social,"
                + " outr.utransporte_desc, om.chofer_nombre,otpm.tipo_pago_maniobra_descripcion,om.maniobra_pagado,"
                + " NVL(om.maniobra_referencia_pago,' '),NVL(om.maniobra_observaciones,' '),om.maniobra_cortina,om.maniobra_costo_neto,"
                + " om.maniobra_comision, bp.user_name"
                + " from ontms_maniobras om "
                + " inner join ontms_proveedores op on om.proveedor_id=op.proveedor_id"
                + " inner join ontms_unidad_transporte outr on om.utransporte_id=outr.utransporte_id"
                + " inner join ontms_tipo_pago_maniobra otpm on om.tipo_pago_maniobra_id=otpm.tipo_pago_maniobra_id"
                + " inner join broker_passwd bp on om.usuario_id=bp.user_nid"
                + " where om.maniobra_folio='"+id+"'";
                
        return sql;
    }
    public String ordenCargaC(String id)
    {
        sql="SELECT ooc.ordencarga_folio,ooc.ordencarga_fecha,olt.ltransporte_nombre,"
                + "oc.camion_placas,och.chofer_nombre,otu.utransporte_desc,NVL(ooc.ordencarga_nombreayudante,' '),"
                + "oct.cuenta_nombre,NVL(ooc.observaciones,' '),ooc.ordencarga_fechacitado"
                + " FROM ontms_ordencarga ooc"
                + " INNER JOIN ontms_camion oc ON oc.camion_id=ooc.camion_id"
                + " INNER JOIN ontms_linea_transporte olt ON oc.ltransporte_id = olt.ltransporte_id"
                + " INNER JOIN ontms_chofer och ON ooc.chofer_id = och.chofer_id"
                + " INNER JOIN ontms_unidad_transporte otu ON oc.utransporte_id = otu.utransporte_id"
                + " INNER JOIN ontms_cuenta oct ON ooc.cuenta_id=oct.cuenta_id"
                + " WHERE ooc.ordencarga_folio="+id+"";
        return sql;
    }

    public String ordenSalida(String id)
    {
        sql="SELECT oos.ordensalida_folio, "
                + "oos.ordensalida_fecha, "
                + "olt.ltransporte_nombre, "
                + "oc.camion_placas, "
                + "och.chofer_nombre, "
                + "oos.ordensalida_nombreayudante, "
                + "oos.ordensalida_observaciones, "
                + "oos.ordensalida_pernocta, "
                + "oos.ordensalida_cortina, "
                + "oos.ordensalida_tipo, "
                + "oct.cuenta_nombre, "
                + "oos.ordensalida_fecha_salida, "
                + "bp.user_name "
                + "FROM ontms_ordensalida oos "
                + "INNER JOIN ontms_camion oc ON oc.camion_id=oos.camion_id "
                + "INNER JOIN ontms_linea_transporte olt ON oc.ltransporte_id = olt.ltransporte_id "
                + "INNER JOIN ontms_chofer och ON oos.chofer_id = och.chofer_id "
                + "INNER JOIN ontms_unidad_transporte otu ON oc.utransporte_id = otu.utransporte_id "
                + "INNER JOIN ontms_cuenta oct ON oos.cuenta_id=oct.cuenta_id "
                + "INNER JOIN broker_passwd bp ON oos.usuario_id=bp.user_nid where oos.ordensalida_id="+id+"";
        return sql;
    }
    
    public String ordenSalidaC(String id)    {
        sql="SELECT oos.ordensalida_folio,"
                + "  oos.ordensalida_fecha,"
                + "  oos.transportista,"
                + "  outr.UTRANSPORTE_DESC,"
                + "  oos.placas,"
                + "  NVL(oos.chofer,' '),"
                + "  NVL(oos.ordensalida_nombreayudante,' '),"
                + "  NVL(oos.ordensalida_observaciones,' '),"
                + "  oos.ordensalida_tipo,"
                + "  oct.cuenta_nombre,"
                + "  oos.ordensalida_fecha_salida,"
                + "  bp.user_name"
                + "  FROM ontms_ordensalida oos"
                + "  INNER JOIN ONTMS_UNIDAD_TRANSPORTE outr ON outr.utransporte_id=oos.camion_id"
                + "  INNER JOIN ontms_cuenta oct ON oos.cuenta_id=oct.cuenta_id"
                + "  INNER JOIN broker_passwd bp ON oos.usuario_id = bp.user_nid"
                + "  WHERE oos.ordensalida_folio="+id+"";
        return sql;
    }
    
    public String ordenIngresoC(String id)    {
        sql="SELECT oos.ordensalida_folio,"
                + "  oos.ordensalida_fecha,"
                + "  oos.transportista,"
                + "  outr.UTRANSPORTE_DESC,"
                + "  oos.placas,"
                + "  NVL(oos.chofer,' '),"
                + "  NVL(oos.ordensalida_nombreayudante,' '),"
                + "  NVL(oos.ordensalida_observaciones,' '),"
                + "  oos.ordensalida_tipo,"
                + "  oct.cuenta_nombre,"
                + "  oos.ordensalida_fecha_salida,"
                + "  bp.user_name"
                + "  FROM ontms_ordensalida oos"
                + "  INNER JOIN ONTMS_UNIDAD_TRANSPORTE outr ON outr.utransporte_id=oos.camion_id"
                + "  INNER JOIN ontms_cuenta oct ON oos.cuenta_id=oct.cuenta_id"
                + "  INNER JOIN broker_passwd bp ON oos.usuario_id = bp.user_nid"
                + "  WHERE oos.ordensalida_folio="+id+"";
        return sql;
    }
    
    public String clienteD(String div)
    {
        sql="SELECT destino_id,  destino_nombre,  destino_direccion, cliente_id, ciudad_id"
                + " FROM ontms_destino WHERE cliente_id="+div+" AND ciudad_id IS NULL ORDER BY destino_nombre";
        return sql;
    }
    public String chofer()
    {
        sql="SELECT chofer_id, chofer_nombre, chofer_rcontrol,chofer_extra1, chofer_extra2 FROM ontms_chofer";
        return sql;
    }
    public String rutas()
    {
        sql="select ru.RUTA_ID,ru.RUTA_NOMBRE,zona_nombre,oc.CIUDAD_NOMBRE from ontms_rutas ru inner join ontms_zonas oz ON ru.zona_id = oz.zona_id inner join ontms_ciudad oc on ru.ciudad_id = oc.ciudad_id";
        return sql;
    }
    public String cliente()
    {
        sql="SELECT cliente_id,cliente_nombre FROM ontms_cliente ORDER BY cliente_nombre";
        return sql;
    }
    public String clienteC()
    {
        sql="SELECT cliente_id,cliente_nombre,NVL(cliente_direccion,' '),NVL(cliente_rfc,' '),"
                + " NVL(cliente_tel1,' '),NVL(cliente_tel2,' '),cliente_tel3,NVL(cliente_contacto1,' '),"
                + " NVL(cliente_email1,' '),NVL(cliente_contacto2,' '),NVL(cliente_email2,' ' ),"
                + " NVL(cliente_web,' '),NVL(cliente_contrato,2),"
                + " NVL(to_char(cliente_vig_contrato,'dd/mm/yyyy'), ' '),cliente_contrato"
                + " FROM ontms_cliente";
        return sql;
    }
    
     public String clienteCHilti()
    {
        sql="  select DESTINO_ID,destino_sales_org ,DESTINO_SHIP_TO,DESTINO_NOMBRE, " +
" DESTINO_ESTADO,DESTINO_CP,DESTINO_CIUDAD,DESTINO_DOMICILIO, " +
" DESTINO_ANIO,DESTINO_COLONIA,DESTINO_RFC "
                + " from DILOG_DESTINOS_HILTI";
        return sql;
    }
    
    public String unidadT()
    {
        sql="SELECT utransporte_id,utransporte_desc"
                + " FROM ontms_unidad_transporte ORDER BY utransporte_desc";
        return sql;
    }
    public String tipoE()
    {
        sql="SELECT tipo_embarque_id, tipo_embarque_desc"
                + " FROM ontms_tipo_embarque ORDER BY tipo_embarque_desc";
        return sql;
    }
    public String camionT()
    {
        sql="SELECT oc.camion_id,oc.camion_placas||'-'||ol.ltransporte_nombre"
                + " FROM ontms_camion oc"
                + " INNER JOIN ontms_linea_transporte ol ON ol.ltransporte_id=oc.ltransporte_id"
                + " GROUP BY oc.camion_id,oc.camion_placas,ol.ltransporte_nombre"
                + " order by oc.camion_placas";
        return sql;
    }
    public String folioC(String [] id)
    {
        sql="SELECT docto_folio_control, count(docto_sal_id)"
                + " FROM ontms_docto_sal "
                + " WHERE docto_folio_control='" + id[0] + "' and cbdiv_id in(" + id[1] + ")"
                + " GROUP BY docto_folio_control";
        return sql;
    }

    public String paqueteria(String id, String cve) {
        sql = "SELECT op.embarque_paq,op.embarque_paq_agrupador,to_char(op.embarque_paq_captura,'dd/mm/yyyy'),"
                + " opa.paqueteria,op.camion,op.chofer"
                + " FROM ontms_embarque_paqueteria op"
                + " INNER JOIN ontms_docto_Sal ods ON ods.docto_sal_agrupador=op.embarque_paq_agrupador"
                + " INNER JOIN ontms_paqueterias opa on opa.paqueteria_id=op.paqueteria_id"
                + " WHERE UPPER(op.embarque_paq)='" + id + "' and ods.cbdiv_id in (" + cve + ")"
                + " GROUP BY op.embarque_paq,op.embarque_paq_agrupador,to_char(op.embarque_paq_captura,'dd/mm/yyyy'),"
                + " opa.paqueteria,op.camion,op.chofer";
        return sql;
    }

    public String trans() {
        sql = "SELECT ltransporte_id,ltransporte_nombre FROM ontms_linea_transporte"
                + " WHERE ltransporte_id<>726 ORDER BY ltransporte_nombre";
        return sql;
    }
    public String proveedor() {
        sql = "SELECT proveedor_id, proveedor_nombre FROM ontms_proveedores"
                + " ORDER BY proveedor_nombre";
        return sql;
    }
    public String tipocamion() {
        sql = "SELECT utransporte_id, utransporte_desc FROM ontms_unidad_transporte "
                + "where utransporte_maniobras=1 ORDER BY utransporte_max_volumen";
        return sql;
    }
    public String tipocamion2() {
        sql = "SELECT utransporte_id, utransporte_desc FROM ontms_unidad_transporte "
                + "ORDER BY utransporte_desc";
        return sql;
    }
    public String tipopago() {
        sql = "SELECT TIPO_PAGO_MANIOBRA_ID,TIPO_PAGO_MANIOBRA_DESCRIPCION FROM ONTMS_TIPO_PAGO_MANIOBRA "
                + "ORDER BY TIPO_PAGO_MANIOBRA_DESCRIPCION";
        return sql;
    }
    public String transPq() {
        sql = "SELECT ltransporte_id||'-EM,ltransporte_nombre FROM ontms_linea_transporte"
                + " WHERE ltransporte_id<>726 UNION"
                + " SELECT paqueteria_id||'-PA',paqueteria"
                + " FROM ontms_paqueterias ORDER BY 2";
        return sql;
    }
    
    public String transporte() {
        sql = "SELECT ltransporte_id,ltransporte_nombre FROM ontms_linea_transporte"
                + " WHERE ltransporte_id<>726 UNION"
                + " SELECT paqueteria_id,paqueteria"
                + " FROM ontms_paqueterias ORDER BY 2";
        return sql;
    }

    public String cvE(String id) {
        sql = "SELECT cuenta_id, cbdiv_id FROM ontms_cta_bod_div"
                + " WHERE cbdiv_id in (" + id + ") group by cuenta_id, cbdiv_id ORDER BY cuenta_id ";
        return sql;
    }

    public String actualizarD(String[] id) {
        sql = "UPDATE ontms_docto_sal"
                + " SET docto_totales='" + id[1] + "'"
                + " WHERE docto_sal_id='" + id[0] + "'";
        return sql;
    }

    public String fleteG(String id) {
        sql = "SELECT oc.cuenta_id, oc.cuenta_nombre FROM broker_usuario_div bud"
                + " INNER JOIN broker_passwd bp ON bp.user_nid = bud.user_nid"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id = bud.cbdiv_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " WHERE 1=1 AND bp.user_nid = " + id + ""
                + " GROUP BY oc.cuenta_id, oc.cuenta_nombre ORDER BY oc.cuenta_nombre";
        return sql;
    }

    public String insertarD(String[] id) {
        sql = "INSERT INTO ontms_docto_sal SELECT NULL,'" + id[0] + "',"
                + " docto_concentrado,docto_fec_concentrado,docto_talon,docto_fec_talon,docto_pedido,"
                + " docto_fec_pedido,docto_programacion,docto_fec_programacion,docto_fec_cancelacion,"
                + " " + id[9] + "," + id[10] + "," + id[11] + ",docto_fec_captura,0,"
                + " tipo_docto_id,cliente_id,destino_id,cbdiv_id,docto_sal_agrupador,docto_sal_consolidado,"
                + " docto_totales,tipo_viaje_id,docto_prorrateo,docto_sal_cantidad,umedida_id,"
                + " docto_evidencia,NVL(TO_CHAR(TO_DATE(docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY'),' '),docto_observaciones,docto_rechazo,docto_fec_rechazo,"
                + " docto_liquidacion,docto_fec_liquidacion,docto_cargo,docto_fec_cargo,docto_folio_evidencia,"
                + " rechazo_id,docto_sal_rma,1," + id[2] + "," + id[3] + "," + id[4] + ","
                + " " + id[5] + "," + id[6] + "," + id[7] + "," + id[8] + ",docto_folio_control,"
                + " docto_aceptacion_reenvio,docto_fec_control,docto_fec_factura,docto_captura_evidencia,"
                + " bodega_id,DATAWH FROM ontms_docto_sal WHERE docto_sal_id='" + id[1] + "'";
        return sql;
    }

    public String insertarP(String[] id) {
        sql = "INSERT INTO ontms_docto_sku SELECT null,'" + id[1] + "',producto_sku,"
                + " " + id[2] + ",cantidad_pedida,cantidad_confirmada,cantidad_rechazada,"
                + " rechazo_id FROM ontms_docto_sku WHERE docto_sal_id='" + id[0] + "'";
        return sql;
    }

    public String nombreD(String div) {
        sql = "SELECT oc.cbdiv_id,od.division_nombre"
                + " FROM ontms_cta_bod_div oc"
                + " INNER JOIN ontms_division od ON od.division_id=oc.division_id"
                + " WHERE oc.division_id IN (" + div + ")";
        return sql;
    }

    public String nombreC(String cve) {
        sql = "SELECT oc.cbdiv_id,od.division_nombre"
                + " FROM ontms_cta_bod_div oc"
                + " INNER JOIN ontms_division od ON od.division_id=oc.division_id"
                + " WHERE oc.cbdiv_id IN (" + cve + ")";
        return sql;
    }
    


    public String consultaDoc(String [] id) {
        sql = "SELECT * FROM vw_consulta_doc WHERE ods_cbdiv_id in (" + id[0] + ") "
                + "AND upper(ods_docto_referencia) = '" +id[1] + "' "+id[2]+"";
        return sql;
    }

    public String consultaDocRe(String[] id) {
        sql = "SELECT * FROM vw_consulta_doc WHERE ods_cbdiv_id in (" + id[0] + ") "
                + "AND upper(ods_docto_referencia)='" + id[1] + "' AND ods_docto_estado_id=" + id[2] + "";
        return sql;
    }

    public String convesionD(String nombre, String compara, String d) {
        sql = "SELECT sucliente_id,od.destino_nombre,odi.division_nombre,dias_objetivos,dias_evidencia FROM ontms_conversion_destino ocd INNER JOIN ontms_destino od ON od.destino_id=ocd.destino_id INNER JOIN ontms_division odi ON odi.division_id=ocd.division_id WHERE ";
        if (compara.equals("uno")) {
            sql += "upper(od.destino_nombre) like UPPER('%" + nombre + "%')  ";
        } else if (compara.equals("dos")) {
            sql += "UPPER(od.destino_nombre) like UPPER('" + nombre + "%')  ";
        } else if (compara.equals("tres")) {
            sql += "UPPER(od.destino_nombre) like UPPER('%" + nombre + "')  ";
        } else {
            sql += "UPPER(od.destino_nombre)='" + nombre + "'  ";
        }
        sql += " and ocd.division_id IN (" + d + ") ORDER BY sucliente_id";
        return sql;
    }

    public String consolidarEmbarqueContar(String tipo) {
        sql = "SELECT COUNT(DISTINCT ods.docto_referencia)"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id and ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id and ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id"
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
                + " WHERE 1=1 and ods.docto_estado_id = 18"
                + " AND otd.tipo_destino_lof IN(" + tipo + ")";
        return sql;
    }
    
//***

    public String embarqueDetalle(String tipo) {
        sql = "SELECT ods.docto_sal_id,ods.docto_referencia, oc.cuenta_nombre,"
                + " od.destino_nombre, oci.ciudad_nombre,oe.estado_nombre,"
                + " NVL(to_char(ods.docto_fec_captura,'dd/mm/yyyy hh:mi'),' '),"
                + " NVL(to_char(ods.docto_piezas),' '),NVL(to_char(ods.docto_cajas),' ')"
                + " FROM ontms_docto_sal ods"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ods.cbdiv_id = ocbd.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_division odv ON odv.division_id = ocbd.division_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id and ocd.division_id = odv.division_id"
                + " INNER JOIN ontms_destino od ON od.destino_id = ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ord ON ord.id_origen = ob.zona_id and ord.id_destino = od.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_tipo_destino otd ON otd.tipo_destino_id = ord.tipo_destino_id"
                + " INNER JOIN ontms_colonia onc ON od.colonia_id=onc.colonia_id"
                + " INNER JOIN ontms_ciudad oci ON od.ciudad_id=oci.ciudad_id"
                + " INNER JOIN ontms_estado oe ON oci.estado_id=oe.estado_id"
                + " WHERE 1=1 and ods.docto_estado_id = 18"
                + " and otd.tipo_destino_lof in(" + tipo + ")"
                + " group by ods.docto_sal_id,ods.docto_referencia, oc.cuenta_nombre,od.destino_nombre,"
                + " oci.ciudad_nombre,oe.estado_nombre,ods.docto_fec_captura,ods.docto_piezas,ods.docto_cajas"
                + " order by oci.ciudad_nombre,oe.estado_nombre,ods.docto_referencia ";
        return sql;
    }
////veriidcar liquidar

    public String rempresionLiquidacion(String[] id) {
        sql = "SELECT ol.id_doc_embarque,folio_liquidacion, to_char(ol.fecha,'dd/mm/yyyy'),count(oe.embarque_id), olt.ltransporte_nombre"
                + " FROM ontms_liquidacion" + id[3] + " ol"
                + " INNER JOIN ontms_embarque oe ON oe.embarque_agrupador=ol.embarque_agrupador"
                + " INNER JOIN ontms" + id[2] + "docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " inner JOIN dilog_destinos_hilti ocd ON ocd.destino_ship_to=ods.destino_id and ocd.division_id=ods.CBDIV_ID"
                + " inner JOIN DILOG_ORIG_DEST_PAQ ood ON ood.id_origen=ob.zona_id and ood.id_destino=ocd.destino_cp"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_camion oc ON oc.camion_id=oe.camion_id"
                + " INNER JOIN ontms_linea_transporte olt ON olt.ltransporte_id=oc.ltransporte_id"
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " WHERE ods.cbdiv_id IN (" + id[0] + ") AND ol.folio_liquidacion ='" + id[1] + "'"
                + " group by ol.id_doc_embarque, folio_liquidacion, to_char(ol.fecha,'dd/mm/yyyy'), olt.ltransporte_nombre";
        return sql;
    }
//

    public String ctaBodDiv(String id) {
        sql = "SELECT ocbd.cuenta_id, oc.cuenta_nombre,oc.cuenta_nombre_sap FROM broker_usuario_div bud"
                + " INNER JOIN broker_passwd bp ON bp.user_nid = bud.user_nid"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id = bud.cbdiv_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_division od ON od.division_id = ocbd.division_id"
                + " WHERE 1=1 AND bp.user_nid = " + id + "  and ocbd.cbdiv_id <>59"
                + " GROUP BY ocbd.cuenta_id, oc.cuenta_nombre,oc.cuenta_nombre_sap ORDER BY oc.cuenta_nombre";
        return sql;
    }
    
    public String ctaBodDivSap(String id) {
        sql = "SELECT oc.cuenta_nombre_sap FROM broker_usuario_div bud"
                + " INNER JOIN broker_passwd bp ON bp.user_nid = bud.user_nid"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id = bud.cbdiv_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_division od ON od.division_id = ocbd.division_id"
                + " WHERE 1=1 AND bp.user_nid = " + id + ""
                + " GROUP BY oc.cuenta_nombre_sap ORDER BY oc.cuenta_nombre_sap";
        return sql;
    }
public String ctaBodDiv() {
        sql = "SELECT ocbd.cuenta_id, oc.cuenta_nombre FROM ontms_cta_bod_div ocbd"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id = ocbd.bodega_id"
                + " INNER JOIN ontms_division od ON od.division_id = ocbd.division_id"
                + " GROUP BY ocbd.cuenta_id, oc.cuenta_nombre ORDER BY oc.cuenta_nombre";
        return sql;
    }
    public String division(String d) {
        sql = "SELECT ocbd.cbdiv_id,od.division_nombre FROM ontms_cta_bod_div ocbd"
                + " INNER JOIN ontms_division od ON od.division_id=ocbd.division_id"
                + " WHERE cuenta_id=" + d + "";
        return sql;
    }

    public String trans(String o, String d, String t) {
        sql = "SELECT utransporte_id,utransporte_desc FROM ontms_unidad_transporte"
                + " WHERE not utransporte_id in (SELECT oct.utransporte_id FROM ontms_origen_destino ood"
                + " INNER JOIN ontms_cuota_transporte oct ON oct.origen_dest_id=ood.origen_dest_id WHERE ood.id_origen=" + o + " and ood.id_destino=" + d + ""
                + " AND oct.tipo_embarque_id=" + t + ")"
                + " GROUP BY utransporte_id,utransporte_desc ORDER BY utransporte_desc";
        return sql;
    }
////pendiente

    public String consultaReempaque(String cve_docto) {
        sql = "SELECT NVL(ods.docto_referencia, ' '),  NVL(ods.docto_concentrado, ' '),  NVL(to_char(ods.docto_fec_concentrado,'dd/mm/yyyy'),' '),  NVL(ods.docto_talon, ' '), "
                + " NVL(to_char(ods.docto_fec_talon,'dd/mm/yyyy'),' '), NVL(ods.docto_pedido, ' '), NVL(to_char(ods.docto_fec_pedido,'dd/mm/yyyy'),' '),  NVL(ods.docto_programacion, ' '),"
                + " NVL(to_char(ods.docto_fec_programacion,'dd/mm/yyyy'),' '), NVL(to_char(ods.docto_fec_cancelacion,'dd/mm/yyyy'),' '),  NVL(ods.docto_importe, 0),  NVL(ods.docto_peso, 0),"
                + " NVL(ods.docto_volumen, 0), ods.docto_estado_id,  ods.tipo_docto_id,  NVL(ods.docto_sal_agrupador,' '),"
                + " NVL(ods.docto_totales, ' '), NVL(ods.tipo_viaje_id, 0), NVL(ods.docto_piezas, 0),  NVL(ods.docto_cajas, 0),"
                + " NVL(ods.docto_pallets, 0), NVL(ods.docto_colgados, 0), NVL(ods.docto_contenedor, 0),  NVL(ods.docto_atados, 0),"
                + " NVL(ods.docto_bulks, 0), ods.cliente_id, ods.destino_id, oc.cliente_nombre, od.destino_nombre, otd.tipo_docto_desc,NVL(to_char(ods.docto_fec_captura,'dd/mmyyyy'),' '),ods.docto_sal_id"
                + " FROM ontms_docto_sal ods, ontms_embarque oe, ontms_cliente oc, ontms_destino od, ontms_conversion_destino ocd, ontms_tipo_docto otd,"
                + " ontms_cta_bod_div ocbd, ontms_division od WHERE docto_estado_id=0 AND ods.docto_sal_id='" + cve_docto + "' AND ods.cliente_id=oc.cliente_id "
                + " AND ocd.sucliente_id=ods.destino_id AND ocd.division_id=od.division_id AND od.destino_id=ocd.destino_id AND ods.tipo_docto_id=otd.tipo_docto_id"
                + " AND ods.cbdiv_id=ocbd.cbdiv_id AND od.division_id=ocbd.division_id"
                + " GROUP BY ods.docto_sal_id,ods.docto_fec_captura,NVL(ods.docto_referencia, ' '), NVL(ods.docto_concentrado, ' '),  ods.docto_fec_concentrado, NVL(ods.docto_talon, ' '),"
                + " ods.docto_fec_talon, NVL(ods.docto_pedido, ' '), ods.docto_fec_pedido, NVL(ods.docto_programacion, ' '),  ods.docto_fec_programacion,"
                + " ods.docto_fec_cancelacion, NVL(ods.docto_importe, 0), NVL(ods.docto_peso, 0), NVL(ods.docto_volumen, 0), ods.docto_estado_id,"
                + " ods.tipo_docto_id,  NVL(ods.docto_sal_agrupador,' '), NVL(ods.docto_totales, ' '), NVL(ods.tipo_viaje_id, 0),  NVL(ods.docto_piezas, 0),"
                + " NVL(ods.docto_cajas, 0), NVL(ods.docto_pallets, 0), NVL(ods.docto_colgados, 0), NVL(ods.docto_contenedor, 0), NVL(ods.docto_atados, 0),"
                + " NVL(ods.docto_bulks, 0), ods.cliente_id, ods.destino_id, oc.cliente_nombre, od.destino_nombre, otd.tipo_docto_desc";
        return sql;
    }

    public String consultaProductos(String cve_docto_sal) {
        sql = "SELECT odsk.docto_sku_id, op.producto_desc, odsk.docto_sku_cantidad"
                + " FROM ontms_docto_sku odsk, ontms_docto_sal ods, ontms_producto op"
                + " WHERE odsk.docto_sal_id = ods.docto_sal_id"
                + " and odsk.producto_sku=op.producto_sku"
                + " and ods.docto_sal_id='" + cve_docto_sal + "'";
        return sql;
    }
    
    
    public String Citas(String fecha) {
        sql = "  SELECT tc.idcita       AS tc_idcita,"
                        + "    ddh.destino_nombre             AS ddh_destino_nombre,"
                        + "    ods.docto_orden_compra         AS ods_docto_orden_compra,"
                        + "    ods.docto_referencia           AS ods_docto_referencia,"
                        + "    tc.fecha                       AS tc_fecha,"
                        + "    tc.hora                        AS tc_hora,"
                        + "    ods.docto_fec_captura          AS ods_docto_fec_captura,"
                        + "    tc.comentarios                 AS tc_comentarios"
                        + "    FROM ontms_docto_sal ods  "
                        + "    INNER JOIN tra_citas tc  "
                        + "    ON ods.docto_orden_compra= tc.orden_compra"
                        + "    INNER JOIN dilog_destinos_hilti ddh "
                        + "    ON ods.destino_id = ddh.destino_ship_to"
                        + "    AND ods.cbdiv_id=ddh.division_id"
                        + "    where tc.bandera = 1 "
                        + "    order by fecha; ";
        return sql;
    }

    public String consultaDoctoSal(String dto_referencia) {
        sql = "SELECT docto_sal_id, docto_referencia, NVL(docto_concentrado, ' '), NVL(to_char(docto_fec_concentrado, 'dd/mm/yyyy hh:mi'), ''), NVL(docto_talon,' '),"
                + " NVL(to_char(docto_fec_talon, 'dd/mm/yyyy hh:mi'), ''), NVL(docto_pedido, ' '), NVL(to_char(docto_fec_pedido,'dd/mm/yyyy hh:mi'), ''), NVL(docto_programacion,' '), NVL(to_char(docto_fec_programacion,'dd/mm/yyyy hh:mi'), ''),"
                + " NVL(to_char(docto_fec_cancelacion, 'dd/mm/yyyy'), ''), docto_importe, docto_peso, docto_volumen, NVL(to_char(docto_fec_captura,'dd/mm/yyyy hh:mi'), ''),  DOCTO_ESTADO_ID,"
                + " tipo_docto_id, cliente_id, destino_id, cbdiv_id, docto_sal_agrupador, docto_sal_consolidacion, docto_totales,"
                + " tipo_viaje_id, docto_prorrateo,  docto_sal_cantidad, umedida_id, docto_evidencia, NVL(TO_CHAR(TO_DATE(docto_fec_evidencia, 'DD/MM/RR'), 'DD/MM/YYYY hh:mi'),' '),"
                + " docto_observaciones, docto_rechazo,  NVL(to_char(docto_fec_rechazo,'dd/mm/yyyy hh:mi'), ''), docto_liquidacion, NVL(to_char(docto_fec_liquidacion, 'dd/mm/yyyy hh:mi'), ''), docto_cargo,"
                + " NVL(to_char(docto_fec_cargo, 'dd/mm/yyyy hh:mi'), ''), docto_folio_evidencia, rechazo_id, docto_sal_rma, destino_migrado, docto_piezas, docto_cajas,"
                + " docto_pallets, docto_colgados, docto_concentrado, docto_atados, docto_bulks, docto_folio_control, docto_aceptacion_reenvio,"
                + " NVL(to_char(docto_fec_control, 'dd/mm/yyyy hh:mi'), '') FROM ontms_docto_sal WHERE docto_referencia='" + dto_referencia + "'";
        return sql;
    }

    public String cuenta(String id) {
        sql = "SELECT oc.cuenta_id ,oc.cuenta_nombre FROM broker_usuario_div bud"
                + " INNER JOIN broker_passwd bp ON bp.user_nid = bud.user_nid"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id = bud.cbdiv_id"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id = ocbd.cuenta_id"
                + " WHERE 1=1 and bp.user_nid = " + id + ""
                + " GROUP BY oc.cuenta_id, oc.cuenta_nombre ORDER BY oc.cuenta_nombre";
        return sql;
    }

    public String fletegral(String[] id) {
       /*S sql = "  SELECT oe.oe_embarque_id,COUNT(DISTINCT docto_sal_id),oe.olt_ltransporte_nombre,"
                + " oe.oc_chofer_nombre,oe.oe_embarque_costo_real,oe_embarque_agrupador,SUM(NVL(docto_piezas,0)),"
                + " SUM(NVL(docto_cajas,0)),SUM(NVL(docto_pallets,0)),SUM(NVL(docto_colgados,0)),"
                + " SUM(NVL(docto_contenedor,0)),SUM(NVL(docto_atados,0)),SUM(NVL(docto_bulks,0)),"
                + " SUM(NVL(docto_importe,0)),SUM(NVL(docto_peso,0)),SUM(NVL(docto_volumen,0)),"
                + " oe.outr_utransporte_desc,ocu.cuenta_nombre, oe.OE_EMBARQUE_ESTADO_ID FROM ontms_hist_docto_sal ods"
                + " INNER JOIN VW_CONSULTA_EM oe ON ods.docto_sal_agrupador=oe.oe_embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocb ON ocb.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocb.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocb.division_id"
                + " INNER JOIN ontms_destino ode ON ode.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ood ON ood.id_origen  =ob.zona_id"
                + " AND ood.id_destino=ode.ciudad_id LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " INNER JOIN ontms_cuenta ocu ON ocu.cuenta_id =ocb.cuenta_id"
                + " WHERE ocb.cuenta_id IN (" + id[2] + ") " + id[3] + ""
                + " AND TRUNC(oe.oe_embarque_fec_captura) BETWEEN '" + id[0] + "' AND '" + id[1] + "'"
                + " AND oe.oe_embarque_id LIKE 'EM%' AND oe.oe_sap_migrado is null and oe.oe_embarque_estado_id<=5"
                + " GROUP BY oe.oe_embarque_id, oe.olt_ltransporte_nombre, oe.oc_chofer_nombre, oe.oe_embarque_costo_real, "
                + " oe.oe_embarque_agrupador, oe.outr_utransporte_desc, ocu.cuenta_nombre, oe.OE_EMBARQUE_ESTADO_ID "
                + " order by oe.oe_embarque_id";*/
        
        sql  = "select   oe.oe_embarque_id,  COUNT(DISTINCT docto_sal_id),"
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
                + "  INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocb.division_id"
                + "  INNER JOIN ontms_destino ode ON ode.destino_id=ocd.destino_id"
                + "  INNER JOIN ontms_origen_destino ood ON ood.id_origen  =ob.zona_id"
                + "  AND ood.id_destino=ode.ciudad_id "
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
         return sql;
    }

    public String sumaIndirectos(String a) {
        sql = "SELECT NVL(sum(oei.embarque_indirectos_costo),0) FROM ontms_embarque oe INNER JOIN ontms_embarque_indirectos oei ON oei.embarque_agrupador=oe.embarque_agrupador"
                + " WHERE oe.embarque_agrupador='" + a + "'";
        return sql;
    }

    public String fleteIn(String doc) {
        sql = "SELECT embarque_id,embarque_fec_captura,olt.ltransporte_nombre, oc.chofer_nombre FROM ontms_embarque oe"
                + " INNER JOIN ontms_chofer oc ON oc.chofer_id=oe.chofer_id"
                + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id"
                + " INNER JOIN ontms_linea_transporte olt ON olt.ltransporte_id=oca.ltransporte_id"
                + " WHERE embarque_agrupador='" + doc + "'";
        return sql;
    }

    public String fletesDin() {
        sql = "SELECT * FROM ontms_gastos_transporte WHERE gtransp_extra1='1' ORDER BY gtransp_desc";
        return sql;
    }

    public String autorizaciones() {
        sql = "SELECT * FROM ontms_gastos_transporte WHERE gtransp_extra1='1' ORDER BY gtransp_desc";
        return sql;
    }

    public String conversion(String cve) {
        sql = "SELECT oc.cuenta_nombre,ocbd.division_id,od.division_nombre"
                + " FROM ontms_cta_bod_div ocbd"
                + " INNER JOIN ontms_cuenta oc ON oc.cuenta_id=ocbd.cuenta_id "
                + " INNER JOIN ontms_division od ON od.division_id=ocbd.division_id"
                + " WHERE cbdiv_id IN(" + cve + ")";
        return sql;
    }

    public String rempresionLiqPaq(String[] id) {
        sql = "SELECT ol.id_doc_embarque,  folio_liquidacion,TO_CHAR(ol.fecha,'dd/mm/yyyy'),op.paqueteria"
                + " FROM ontms_liquidacion" + id[3] + " ol"
                + " INNER JOIN ontms_embarque_paqueteria oe ON oe.embarque_paq_agrupador=ol.embarque_agrupador"
                + " INNER JOIn ontms_paqueterias op ON oe.paqueteria_id=op.paqueteria_id"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_paq_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " INNER JOIN ontms_bodega ob ON ob.bodega_id=ocbd.bodega_id"
                + " INNER JOIN ontms_conversion_destino ocd ON ocd.sucliente_id=ods.destino_id AND ocd.division_id=ocbd.division_id"
                + " INNER JOIN ontms_destino ode ON ode.destino_id=ocd.destino_id"
                + " INNER JOIN ontms_origen_destino ood ON ood.id_origen  =ob.zona_id AND ood.id_destino=ode.ciudad_id"
                + " LEFT JOIN ontms_bodega ob1 ON ob1.bodega_id=ods.bodega_id"
                + " WHERE ods.cbdiv_id      IN (" + id[0] + ")"
                + " AND ol.folio_liquidacion ='" + id[1] + "' "
                + " GROUP BY ol.id_doc_embarque, folio_liquidacion, TO_CHAR(ol.fecha,'dd/mm/yyyy'),op.paqueteria";
        return sql;
    }
     public String rempresionLiqPaqD(String[] id) {
         sql="SELECT olp.id_doc,olp.folio_liberacion,to_char(olp.fecha,'dd/mm/yyyy'),oe.embarque_paq,"
                 + " to_char(oe.embarque_paq_captura,'dd/mm/yyyy')"
                 + " FROM ontms_liberacion_paq olp"
                 + " INNER JOIN ontms" + id[2] +"docto_sal ods ON ods.docto_sal_id=olp.docto_sal_id"
                 + " INNER JOIN ontms_embarque_paqueteria oe ON oe.embarque_paq_agrupador=ods.docto_sal_agrupador"
                 + " INNER JOIN ontms_paqueterias op on op.paqueteria_id=oe.paqueteria_id"
                 + " WHERE ods.cbdiv_id in (" + id[0] +") and olp.folio_liberacion='" + id[1] + "'";
        return sql;
    }

    public String modifEmbarqueG(String agru) {
        sql = "SELECT oe.embarque_id,oe.embarque_fec_captura,"
                + " oe.embarque_agrupador,oe.camion_id,oe.chofer_id, ods.docto_estado_id,"
                + " oca.camion_placas, olt.ltransporte_nombre, outr.utransporte_desc,och.chofer_nombre,ocbd.cbdiv_id,ocu.cuenta_nombre"
                + " FROM ontms_embarque oe"
                + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador"
                + " INNER JOIN ontms_cta_bod_div ocbd ON ocbd.cbdiv_id=ods.cbdiv_id"
                + " inner join ontms_cuenta ocu on ocu.cuenta_id=ocbd.cuenta_id"
                + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id"
                + " INNER JOIN ontms_chofer och ON och.chofer_id=oe.chofer_id"
                + " INNER JOIN ontms_linea_transporte olt ON olt.ltransporte_id=oca.ltransporte_id"
                + " INNER JOIN ontms_unidad_transporte outr ON oca.utransporte_id=outr.utransporte_id"
                + " WHERE ods.docto_estado_id=2"
                + " AND oe.embarque_agrupador in ('" + agru + "')"
                + " GROUP BY oe.embarque_id,oe.embarque_fec_captura,"
                + " oe.embarque_agrupador,oe.camion_id,oe.chofer_id, ods.docto_estado_id,"
                + " oca.camion_placas, olt.ltransporte_nombre, outr.utransporte_desc, och.chofer_nombre, ocbd.cbdiv_id, ocu.cuenta_nombre"
                + " ORDER BY oe.embarque_id";
        return sql;
    }
     public String modifEmbPaqAgrup(String agrup)
    {
         sql="SELECT op_embarque_paq,  op_embarque_paq_captura, op_embarque_paq_agrupador,op_camion,op_chofer, opa_paqueteria"
                 + " FROM ontms_vw_paqueteria"
                 + " WHERE ods_docto_estado_id=2 AND  op_embarque_paq_agrupador ='"+agrup+"'";
         return sql;
     }
  
}
