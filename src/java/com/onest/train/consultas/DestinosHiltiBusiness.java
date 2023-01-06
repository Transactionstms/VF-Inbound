/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

import com.dao.ServiceDAO;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author SergioCastro
 */
public class DestinosHiltiBusiness extends ServiceDAO {

    public com.bean.DestinoHiltiBean busquedaDestinoHilti(String destinoId) {
        com.bean.DestinoHiltiBean destinoHiltiBean = null;
        try {


            String sql = " SELECT DESTINO_ID,destino_ship_to,DESTINO_NOMBRE, "
                    + " DESTINO_DOMICILIO,DESTINO_CP "
                    + " FROM dilog_destinos_hilti "
                    + " where destino_ship_to =" + destinoId + " ";
            ResultSet res = this.consulta(sql);
            while (res.next()) {
                destinoHiltiBean = new com.bean.DestinoHiltiBean();

                destinoHiltiBean.setDESTINO_ID(res.getString(1));
                destinoHiltiBean.setDestCustClntId(res.getString(2));
                destinoHiltiBean.setStrtName(res.getString(3));
                destinoHiltiBean.setColonyName(res.getString(4));
                destinoHiltiBean.setZipCode(res.getString(5));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return destinoHiltiBean;
    }

    public com.bean.DestinoHiltiBean busquedaDestinoPaqueteExpress(String destinoId) {
        com.bean.DestinoHiltiBean destinoHiltiBean = null;
        try {


            String sql = " SELECT dest_nombre, "
                    + " dest_calle, "
                    + " dest_cp, "
                    + " dest_paq_id "
                    + " FROM dilog_destinos_pe "
                    + " WHERE dest_paq_id='" + destinoId + "' ";
            ResultSet res = this.consulta(sql);
            while (res.next()) {
                destinoHiltiBean = new com.bean.DestinoHiltiBean();
                destinoHiltiBean.setStrtName(res.getString(1));
                destinoHiltiBean.setColonyName(res.getString(2));
                destinoHiltiBean.setZipCode(res.getString(3));
                destinoHiltiBean.setDestCustClntId(res.getString(4));
                destinoHiltiBean.setDESTINO_ID(res.getString(4));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return destinoHiltiBean;
    }
}
