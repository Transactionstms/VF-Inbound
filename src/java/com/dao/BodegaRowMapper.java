/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.BodegaBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author miguel
 */
public class BodegaRowMapper implements RowMapper<BodegaBean> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public BodegaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
        BodegaBean bean = new BodegaBean();
        bean.setBodegaCpOrigen(rs.getString("DESTINO_CP"));
        bean.setBodegaDireccion(rs.getString("BODEGA_DIRECCION"));
        bean.setBodegaId(rs.getString("BODEGA_ID"));
        bean.setBodegaNombre(rs.getString("BODEGA_NOMBRE"));
        bean.setCbdivId(rs.getString("CBDIV_ID"));
        
        return bean;
    }
}