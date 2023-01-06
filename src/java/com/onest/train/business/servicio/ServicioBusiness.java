/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.business.servicio;

import com.bean.PaqueteriaBean;
import com.dao.ServiceDAO;
import com.management.util.Loggin;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author RicardoMartinez
 */
public class ServicioBusiness extends ServiceDAO {

    public String insertaEmbarquePaqueteria(PaqueteriaBean bean) {
        String salida = "";
        try {
            this.insert(bean);
            String sql = " INSERT INTO ONTMS_EMBARQUE_PAQUETERIA ( "
                    + " EMBARQUE_PAQ_ID,EMBARQUE_PAQ,EMBARQUE_PAQ_CAPTURA,"
                    + " EMBARQUE_PAQ_REVISION,EMBARQUE_PAQ_INICIO,EMBARQUE_PAQ_FIN, "
                    + " EMBARQUE_PAQ_AUDITOR,PAQUETERIA_ID,CHOFER,CAMION,"
                    + " EMBARQUE_PAQ_AGRUPADOR,EMBARQUE_PAQ_COSTO_REAL, "
                    + " EMBARQUE_PAQ_LIBERACION,EMBARQUE_PAQ_ESTADO_ID,"
                    + " EMBARQUE_PAQ_FEC_FLETE )"
                    + " VALUES ("
                    + " null,"
                    + "'" + bean.getEMBARQUE_PAQ() + "',"
                    + "sysdate,"
                    + "sysdate,"//+ "TO_date('"+bean.getEMBARQUE_PAQ_REVISION()+"','dd/mm/yyyy'),"
                    + "sysdate,"//+ "TO_date('"+bean.getEMBARQUE_PAQ_INICIO()+"','dd/mm/yyyy'),"
                    + "sysdate,"//+ "TO_date('"+bean.getEMBARQUE_PAQ_FIN()+"','dd/mm/yyyy'),"
                    + "'" + bean.getEMBARQUE_PAQ_AUDITOR() + "',"
                    + "" + bean.getPAQUETERIA_ID() + ","
                    + "'" + bean.getCHOFER() + "',"
                    + "'" + bean.getCAMION() + "',"
                    + "" + bean.getEMBARQUE_PAQ_AGRUPADOR() + ","
                    + "" + bean.getEMBARQUE_PAQ_COSTO_REAL() + ","
                    + "'" + bean.getEMBARQUE_PAQ_LIBERACION() + "',"
                    + "'" + bean.getEMBARQUE_PAQ_ESTADO_ID() + "',"
                    + "sysdate"//+ "'"+bean.getEMBARQUE_PAQ_FEC_FLETE()+"'"
                    + ") ";
            Loggin.info(sql);
            // this.save(sql);
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String insertaEmbarquePaqueteriaPE(PaqueteriaBean bean) {
        String salida = "";
        try {

            String agrupador = bean.getEMBARQUE_PAQ_AGRUPADOR().replaceAll("\'", "");
            bean.setEMBARQUE_PAQ_AGRUPADOR(agrupador);
            this.insert(bean);



            String sql = " INSERT INTO ONTMS_EMBARQUE_PAQUETERIA ( "
                    + " EMBARQUE_PAQ_ID,EMBARQUE_PAQ,EMBARQUE_PAQ_CAPTURA,"
                    + " EMBARQUE_PAQ_REVISION,EMBARQUE_PAQ_INICIO,EMBARQUE_PAQ_FIN, "
                    + " EMBARQUE_PAQ_AUDITOR,PAQUETERIA_ID,CHOFER,CAMION,"
                    + " EMBARQUE_PAQ_AGRUPADOR,EMBARQUE_PAQ_COSTO_REAL, "
                    + " EMBARQUE_PAQ_LIBERACION,EMBARQUE_PAQ_ESTADO_ID,"
                    + " EMBARQUE_PAQ_FEC_FLETE )"
                    + " VALUES ("
                    + " null,"
                    + "'" + bean.getEMBARQUE_PAQ() + "',"
                    + "sysdate,"
                    + "sysdate,"//+ "TO_date('"+bean.getEMBARQUE_PAQ_REVISION()+"','dd/mm/yyyy'),"
                    + "sysdate,"//+ "TO_date('"+bean.getEMBARQUE_PAQ_INICIO()+"','dd/mm/yyyy'),"
                    + "sysdate,"//+ "TO_date('"+bean.getEMBARQUE_PAQ_FIN()+"','dd/mm/yyyy'),"
                    + "'" + bean.getEMBARQUE_PAQ_AUDITOR() + "',"
                    + "" + bean.getPAQUETERIA_ID() + ","
                    + "'" + bean.getCHOFER() + "',"
                    + "'" + bean.getCAMION() + "',"
                    + "" + bean.getEMBARQUE_PAQ_AGRUPADOR() + ","
                    + "" + bean.getEMBARQUE_PAQ_COSTO_REAL() + ","
                    + "'" + bean.getEMBARQUE_PAQ_LIBERACION() + "',"
                    + "'" + bean.getEMBARQUE_PAQ_ESTADO_ID() + "',"
                    + "sysdate"//+ "'"+bean.getEMBARQUE_PAQ_FEC_FLETE()+"'"
                    + ") ";
            Loggin.info(sql);
            // this.save(sql);


        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String insertaEmbarquePaqueteriaTEMP(PaqueteriaBean bean) {
        String salida = "";
        try {
            this.insertTmp(bean);



            String sql = " INSERT INTO ONTMS_EMBARQUE_PAQ_REIMP ( "
                    + " EMBARQUE_PAQ_ID,EMBARQUE_PAQ,EMBARQUE_PAQ_CAPTURA,"
                    + " EMBARQUE_PAQ_REVISION,EMBARQUE_PAQ_INICIO,EMBARQUE_PAQ_FIN, "
                    + " EMBARQUE_PAQ_AUDITOR,PAQUETERIA_ID,CHOFER,CAMION,"
                    + " EMBARQUE_PAQ_AGRUPADOR,EMBARQUE_PAQ_COSTO_REAL, "
                    + " EMBARQUE_PAQ_LIBERACION,EMBARQUE_PAQ_ESTADO_ID,"
                    + " EMBARQUE_PAQ_FEC_FLETE )"
                    + " VALUES ("
                    + " null,"
                    + "'" + bean.getEMBARQUE_PAQ() + "',"
                    + "sysdate,"
                    + "sysdate,"//+ "TO_date('"+bean.getEMBARQUE_PAQ_REVISION()+"','dd/mm/yyyy'),"
                    + "TO_date('" + bean.getEMBARQUE_PAQ_INICIO() + "','dd/mm/yyyy'),"
                    + "TO_date('" + bean.getEMBARQUE_PAQ_FIN() + "','dd/mm/yyyy'),"
                    + "'" + bean.getEMBARQUE_PAQ_AUDITOR() + "',"
                    + "" + bean.getPAQUETERIA_ID() + ","
                    + "'" + bean.getCHOFER() + "',"
                    + "'" + bean.getCAMION() + "',"
                    + "'" + bean.getEMBARQUE_PAQ_AGRUPADOR() + "',"
                    + "" + bean.getEMBARQUE_PAQ_COSTO_REAL() + ","
                    + "'" + bean.getEMBARQUE_PAQ_LIBERACION() + "',"
                    + "'" + bean.getEMBARQUE_PAQ_ESTADO_ID() + "',"
                    + "'" + bean.getEMBARQUE_PAQ_FEC_FLETE() + "'"
                    + ") ";
            System.out.println(sql);
            // this.save(sql);


        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String updateDoctoSal(String agrupador, String doc, String cadenaPE, String fileDHL, String tipoServicio,byte[] PDFpaqueteExpress) {
        String salida = "";
        String inClausule = "";
        PreparedStatement pst = null;
        try {            
            try{
                String[] docs = doc.split(",");
                String[] listaDocs = new String[docs.length];
                for (int id = 0; id < docs.length; id++) {
                    listaDocs[id] = "?";
                }
                inClausule = StringUtils.join(listaDocs, ",");               
            }catch(Exception e){
               e.printStackTrace(System.err);
            }
            try{
                agrupador = agrupador.replaceAll("\'", ""); 
            }catch(Exception e){e.printStackTrace(System.err);}
            String sql = " UPDATE ontms_docto_sal SET docto_estado_id= 2,"
                    + " SERVICIO_PE=? , "
                    + " SERVICIO_DHL=? , "
                    + " TIPO_SERVICIO=? "
                    + " where docto_sal_agrupador=? ";
                    //+ " and  docto_sal_id IN(" + inClausule + ") ";
            //doc = doc.replaceAll("\'", "");                        
            //clob.setString(1,cadenaPE.getBytes() );
            pst = this.conectar().prepareStatement(sql);
            pst.setBytes(1, PDFpaqueteExpress);//cadenaPE.getBytes("UTF-8")
            pst.setString(2, fileDHL);
            pst.setInt(3, Integer.parseInt(tipoServicio));
            pst.setString(4, agrupador);
            /*int idPrepared = 5;
            for (String next : docs) {
                pst.setString(idPrepared, next);
                idPrepared++;
            }*/
            pst.executeUpdate();
        } catch (Throwable ex) {
            ex.printStackTrace(System.err);
            try {
                this.conectar().rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.err);
                com.management.util.Loggin.error(ex);
            }
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        } finally {
            try {
                this.conectar().commit();
                pst.close();
                this.desconectarService(null);
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
                com.management.util.Loggin.error(ex);
            }
        }
        return salida;
    }

    public String updateDoctoSalDHL(String agrupador, String doc, String cadenaPE, String fileDHL, String tipoServicio) {
        String salida = "";
        PreparedStatement pst = null;
        String sql = "update ontms_docto_sal set docto_estado_id=2 , "
                + " SERVICIO_PE ='" + cadenaPE + "'  , SERVICIO_DHL='" + fileDHL + "',TIPO_SERVICIO='" + tipoServicio + "' "
                + " where docto_sal_agrupador =" + agrupador + " and  docto_sal_id      IN (" + doc + ") ";

        this.save(sql);


        return salida;
    }

    public String updateMonto(String referencia, String monto) {
        String salida = "";

        String sql = "  UPDATE ontms_docto_sal SET DOCTO_IMP_TOTAL='" + monto + "'"
                + "  where DOCTO_REFERENCIA='" + referencia + "' ";

        this.update(sql);
        
        this.desconectarService(null);
       // Loggin.info("Actualizando monto :::::::::  "+sql); 
        return salida;
    }
}
