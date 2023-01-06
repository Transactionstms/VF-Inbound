package com.transactions.mailsender.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class GenericJdbc {
    protected static Connection connection;

    public void openConection(){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@74.208.184.39:1521:trans39","VANS39","U9xbQa530fdFjaV7");
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