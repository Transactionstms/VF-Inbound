/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.business;

import com.bean.DocumentoBean;
import com.onest.util.ServiceDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RicardoMartinez
 */
public class InsertarEnviosTR2Business extends ServiceDAO {

    public DocumentoBean obtenerDocumento(String referencia) {
        DocumentoBean bean = null;
        try {
            String sql = " SELECT "
                    + "  /*+ FIRST_ROWS */ "
                    + "  ods.docto_referencia, "
                    + "  to_char(ods.DOCTO_FEC_CAPTURA,'dd/MM/yyyy'), "
                    + "  to_char(ods.docto_fec_factura,'dd/MM/yyyy'), "
                    + "  NVL(ods.docto_pedido,0), "
                    + "  to_char(ods.docto_fec_pedido,'dd/MM/yyyy'), "
                    + "  NVL(ods.docto_concentrado,'0'), "
                    + "  to_char(ods.docto_fec_concentrado,'dd/MM/yyyy'), "
                    + "  NVL(ods.destino_id, 0), "
                    + "  ods.bodega_id, "
                    + "  ods.cbdiv_id, "
                    + "  NVL(ods.docto_piezas,0), "
                    + "  NVL(ods.docto_cajas,0), "
                    + "  TIPO_DOCTO_ID,"
                    + " NVL(DOCTO_IMPORTE,0),"
                    + " NVL(DOCTO_PESO,0),"
                    + " NVL(DOCTO_VOLUMEN,0),"
                    + " DOCTO_SAL_ID "
                    + " FROM ontms_docto_sal ods "
                    + " where ods.docto_referencia = '" + referencia + "'  ";
            ResultSet res = this.consulta(sql);
            while (res.next()) {
                bean = new DocumentoBean();
                bean.setDOCTO_REFERENCIA(res.getString(1));
                bean.setDOCTO_FEC_CAPTURA(res.getString(2));
                bean.setDOCTO_FEC_FACTURA(res.getString(3));
                bean.setDOCTO_PEDIDO(res.getString(4));
                bean.setDOCTO_FEC_PEDIDO(res.getString(5));
                bean.setDOCTO_CONCENTRADO(res.getString(6));
                bean.setDOCTO_FEC_CONCENTRADO(res.getString(7));
                bean.setDESTINO_ID(res.getString(8));
                bean.setBODEGA_ID(res.getString(9));
                bean.setCBDIV_ID(res.getString(10));
                bean.setDOCTO_PIEZAS(res.getString(11));

                bean.setDOCTO_CAJAS(res.getString(12));
                bean.setTIPO_DOCTO_ID(res.getString(13));
                bean.setDOCTO_IMPORTE(res.getString(14));
                bean.setDOCTO_PESO(res.getString(15));
                bean.setDOCTO_VOLUMEN(res.getString(16));
                bean.setDOCTO_SAL_ID(res.getString(17));

            }

        } catch (SQLException ex) {
            Logger.getLogger(InsertarEnviosTR2Business.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }

    public boolean obtenerCbdiv(String cbdiv, String bodega, String referencia) {
        boolean bandera = false;
        try {
            String sql = " SELECT * FROM ontms_docto_sal WHERE docto_referencia ='" + referencia + "' "
                    + "and cbdiv_id  in(  "
                    + "select cbdiv_id_dest from ONTMS_CONSTANTES_TR2 "
                    + "where cbdiv_id_ori =" + cbdiv + " and  bodega_id_dest=" + bodega + " "
                    + ")  ";
            ResultSet res = this.consulta(sql);
            while (res.next()) {
                bandera = true;
            }
            this.desconectarService(res);
        } catch (SQLException ex) {
            Logger.getLogger(InsertarEnviosTR2Business.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }

    public String labelBodega(String cve) {

        String salida = " ";

        try {
            String sql = " select bodega_nombre from ontms_bodega\n"
                    + " where bodega_id =" + cve + " ";
            ResultSet res;
            res = this.consulta(sql);
            while (res.next()) {
                salida = res.getString(1);
            }
            this.desconectarService(res);
        } catch (SQLException ex) {
            Logger.getLogger(InsertarEnviosTR2Business.class.getName()).log(Level.SEVERE, null, ex);
        }

        return salida;
    }

    public String validarCbdiv(String cbdiv, String bodega) {
        String salida = "";
        try {
            String sql = " SELECT cbdiv_id_dest"
                    + "  FROM ONTMS_CONSTANTES_TR2 "
                    + "  WHERE cbdiv_id_ori =" + cbdiv + " "
                    + "  AND bodega_id_dest =" + bodega + "";
            ResultSet res = this.consulta(sql);
            while (res.next()) {
                salida = res.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(InsertarEnviosTR2Business.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }

    public String getDocumentoRecibidos(String estadoId, String doctoId, String cbdivid) {

        String condicion = "";

        if (!estadoId.equals("")) {
            condicion = " and  ods.docto_estado_id =" + estadoId + " ";
        } else {
            if (!doctoId.equals("")) {
                condicion = " and  ods.DOCTO_SAL_ID in (" + doctoId + ") ";
            }
        }

        String sql = " SELECT"
                + "  /*+ FIRST_ROWS */"
                + "  ods.docto_referencia,"
                + "  NVL(ods.docto_fec_factura,''),"
                + "  NVL(ods.docto_pedido,0),"
                + "  NVL(ods.docto_fec_pedido,''),"
                + "  NVL(ods.docto_concentrado,'0'),"
                + "  ods.docto_fec_concentrado,"
                + "  NVL(ods.destino_id, 0),"
                + "  ods.bodega_id,"
                + "  ods.cbdiv_id,"
                + "  NVL(ods.docto_piezas,0),"
                + "  NVL(ods.docto_cajas,0),"
                + "  ods.TIPO_DOCTO_ID,"
                + "  DOCTO_IMPORTE,"
                + "  DOCTO_PESO,"
                + "  DOCTO_VOLUMEN,"
                + "  DOCTO_SAL_ID,"
                + "  DOCTO_AGRUPADOR_ENVIO,"
                + "  otd.tipo_docto_desc,"
                + "  NVL(ods.DOCTO_PALLETS,0)"
                + " FROM ontms_docto_sal ods"
                + " LEFT JOIN ontms_embarque oe"
                + " ON oe.embarque_agrupador=ods.docto_sal_agrupador"
                + " INNER JOIN app_estado oes"
                + " ON oes.edo_valor =ods.docto_estado_id"
                + " INNER JOIN ontms_tipo_docto otd"
                + " ON otd.tipo_docto_id = ods.tipo_docto_id"
                + " INNER JOIN dilog_destinos_hilti oddh"
                + " ON oddh.DESTINO_SHIP_TO = ods.destino_id"
                + " AND oddh.division_id    = ods.cbdiv_id"
                + " WHERE oes.edo_tabla  ='ONTMS_DOCTO_SAL'"
                + "" + condicion + ""
                + " AND ods.cbdiv_id =" + cbdivid + " ORDER BY ods.Docto_Referencia ";

        return sql;
    }

    public String getAgrupador() {
        String salida = "";

        String sql = " SELECT TO_CHAR(sysdate,'YYYYMMDDHH24miss') FROM dual ";

        try {
            ResultSet res = this.consulta(sql);
            while (res.next()) {
                salida = res.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertarEnviosTR2Business.class.getName()).log(Level.SEVERE, null, ex);
        }

        return salida;

    }

    public String updateRecibirDocumentos(String idDocumento, String piezas, String cajas, String pallets) {

        String sql = " UPDATE  ontms_docto_sal set"
                + " DOCTO_PIEZAS_REC =" + piezas + " , "
                + " DOCTO_CAJAS_REC=" + cajas + " ,DOCTO_PALLET_REC=" + pallets
                + " WHERE DOCTO_SAL_ID = '" + idDocumento + "'"
                + "";

        this.update(sql);

       

        this.desconectarService(null);

        return null;
    }

    public String getConsultaReporteRecepcion(String folioRecibo, String cuenta_id) {
        folioRecibo = folioRecibo.replaceAll(",", "','");
        String sql = " SELECT "
                + "  /*+ FIRST_ROWS */ "
                + "  ods.docto_referencia, "
                + "  NVL(ods.docto_fec_factura,''), "
                + "  NVL(ods.docto_pedido,0), "
                + "  NVL(to_char(ods.docto_fec_pedido),' '),"
                + "  NVL(ods.docto_concentrado,'0'), "
                + "  NVL(to_char(ods.docto_fec_concentrado),' '), "
                + "  NVL(ods.destino_id, 0), "
                + "  ods.bodega_id, "
                + "  ods.cbdiv_id, "
                + "  NVL(ods.docto_piezas,0), "
                + "  NVL(ods.docto_cajas,0), "
                + "  TIPO_DOCTO_ID, "
                + "  DOCTO_IMPORTE, "
                + "  DOCTO_PESO, "
                + "  DOCTO_VOLUMEN,otd.tipo_docto_desc,ods.docto_folio_recibo "
                + " FROM ontms_docto_sal ods "
                + " LEFT JOIN ontms_embarque oe "
                + " ON oe.embarque_agrupador=ods.docto_sal_agrupador "
                + " INNER JOIN app_estado oes "
                + " ON oes.edo_valor =ods.docto_estado_id "
                + " INNER JOIN ontms_tipo_docto otd "
                + " ON otd.tipo_docto_id  = ods.tipo_docto_id "
                + " WHERE oes.edo_tabla   ='ONTMS_DOCTO_SAL' "
                + " and ods.DOCTO_FOLIO_RECIBO IN ('" + folioRecibo + "') and ods.cbdiv_id=" + cuenta_id
                + " ORDER BY ods.Docto_Referencia ";

        return sql;
    }

    public String updateFolioRecibo(String cuenta_idd, String DOCTO_AGRUPADOR_ENVIO, ArrayList<String> listaDoctoSal) {
        String returnFolio = "";
        String sqlSelectFolio = " SELECT PREFIJO_RECIBOTR2 ||"
                + " lpad(SECUENCIA_RECIBOTR2,5,0) "
                + " FROM broker_cuenta  WHERE cuenta_id = " + cuenta_idd + " ";

        try {
            ResultSet res = this.consulta(sqlSelectFolio);

            while (res.next()) {
                returnFolio = res.getString(1);
            }

            for (String documento : listaDoctoSal) {
                String sqlUpdate = " UPDATE ONTMS_DOCTO_SAL "
                        + "         SET "
                        + "         DOCTO_ESTADO_ID=0, "
                        + "         DOCTO_FEC_RECIBO=SYSDATE, "
                        + "         DOCTO_FOLIO_RECIBO='" + returnFolio + "' "
                        + "         where docto_agrupador_envio='" + DOCTO_AGRUPADOR_ENVIO + "' "
                        + "     AND    DOCTO_SAL_ID= '" + documento + "'";
                System.out.println(sqlUpdate);
                this.update(sqlUpdate);
            }

            this.updateSecuencia(cuenta_idd);
            this.desconectarService(res);

        } catch (SQLException ex) {
            Logger.getLogger(InsertarEnviosTR2Business.class.getName()).log(Level.SEVERE, null, ex);
        }

        return returnFolio;
    }

    public String updateSecuencia(String cuenta_idd) {
        String salida = "";
        String sql = " update broker_cuenta set SECUENCIA_RECIBOTR2 = SECUENCIA_RECIBOTR2 + 1"
                + " where cuenta_id=" + cuenta_idd + "";
        this.update(sql);

        return salida;
    }

}
