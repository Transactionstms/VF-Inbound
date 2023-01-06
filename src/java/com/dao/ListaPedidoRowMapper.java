/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bb.web.beans.ColumnaBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author miguel
 */
public class ListaPedidoRowMapper implements RowMapper<ColumnaBean> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public ColumnaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
        ColumnaBean bean = new ColumnaBean();
        bean.setNombreColumna(rs.getString(1));
        bean.setTipo(rs.getString(2));
        bean.setLongitud(rs.getString(3));
        bean.setNullable(rs.getString(4));
        bean.setColumnId(rs.getString(5));
        bean.setAlias(rs.getString(6));
        bean.setLayoutMsg(rs.getString(7));
        bean.setIgnore(rs.getString(8));
        return bean;
    }
}