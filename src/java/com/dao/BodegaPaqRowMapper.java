/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bb.web.beans.BodegaPaqueteExpress;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author miguel
 */
public class BodegaPaqRowMapper implements RowMapper<BodegaPaqueteExpress> {

    /**
     * 
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException 
     */
    @Override
    public BodegaPaqueteExpress mapRow(ResultSet rs, int rowNum) throws SQLException {
        BodegaPaqueteExpress bean = new BodegaPaqueteExpress();
        bean.setAgreementKey(rs.getString("agreementKey"));
        bean.setClntPswd(rs.getString("clntPswd"));
        bean.setOrgnClntId(rs.getString("orgnClntId"));
        bean.setDescripcion(rs.getString("descripcion"));
        bean.setCbDivId(rs.getString("cbDivId"));
        return bean;
    }
}
