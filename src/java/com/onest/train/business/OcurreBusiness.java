/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.business;

import com.bean.LineaTranporteBean;
import com.bean.OcurreBean;
import com.dao.ServiceDAO;
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
public class OcurreBusiness extends ServiceDAO {

    public List<OcurreBean> buscarDestinos(String sucursal, String tipo) {
        List<OcurreBean> lista = new ArrayList<OcurreBean>();
        try {

            //String sql = " SELECT * FROM ONTMS_PAQUETERIAS WHERE UPPER(PAQUETERIA) LIKE '%"+linea+"%'  ";
            String sql = " select SUCURSAL,"
                    + "CALLE,"
                    + "COLONIA,"
                    + "CIUDAD,"
                    + "ESTADO,"
                    + "CODIGO_POSTAL,DESTINO_OCURRE_ID from tra_destinos_ocurre where upper(sucursal) like upper('%"+sucursal+"%') and TIPO_PAQUETERIA="+ tipo;

            ResultSet res = this.consulta(sql);

            while (res.next()) {
                OcurreBean bean = new OcurreBean();
                bean.setSUCURSAL(res.getString(1));
                bean.setCALLE(res.getString(2));
                bean.setCOLONIA(res.getString(3));
                bean.setCIUDAD(res.getString(4));
                bean.setESTADO(res.getString(5));
                bean.setCODIGO_POSTAL(res.getString(6));
                bean.setDESTINO_OCURRE_ID(res.getString(7));

                lista.add(bean);

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return lista;
    }
}
