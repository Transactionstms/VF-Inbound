/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author miguel
 */
public class PackingListRowMapper implements RowMapper<ListaPedidoBean> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public ListaPedidoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
            ListaPedidoBean bean = new ListaPedidoBean();
            bean.setNumPackingList(rs.getString(1));
            bean.setNumPedido(rs.getString(3));
            bean.setError(rs.getString(2));
        return bean;
    }
}
