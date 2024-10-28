package com.transactions.mailsender.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class GenericJdbc {
    protected static Connection connection;

   public void openConection(){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
           // connection = DriverManager.getConnection("jdbc:oracle:thin:@74.208.89.176:1521:orcldb18c","VANS39TEST","UKidn49N875RBH54Cq2");
         connection = DriverManager.getConnection("jdbc:oracle:thin:@74.208.140.125:1695:tacts125","VANS39TEST","xQoPXqAYBjz9hSdirJaCyw_a");

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
}