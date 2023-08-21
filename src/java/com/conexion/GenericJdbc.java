/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gerardorecendiz
 */
public  class GenericJdbc {
    protected static Connection connection;

    public void openConection(){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
               connection = DriverManager.getConnection("jdbc:oracle:thin:@74.208.140.125:1521:tacts125","VANS39TEST","UKidn49N875RBH54Cq2");
         } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }	
    }

    public void closeConnection(){
        try{
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
   
}
