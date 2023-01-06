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
public interface RowMapper<T> {
    
    public T mapRow(ResultSet rs,int rowNum)throws SQLException;
    
}
