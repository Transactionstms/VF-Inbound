/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Desarrollo Tacts
 */
public class Conexion {
    private Connection conexion = null;
    private String baseDeDatos = "";
    private String usuario = "";
    private String password = "";
    private String servidor = "";
    private final String jdbc = "oracle.jdbc.OracleDriver";
    
    public Conexion() {
        Configuracion configuracion = new Configuracion();
        this.baseDeDatos = configuracion.getSid();
        this.usuario = configuracion.getUsuario();
        this.password = configuracion.getPassword();
        this.servidor = "jdbc:oracle:thin:@" + configuracion.getServidor() + ":" + configuracion.getPuerto() + ":" + configuracion.getSid();
    }
    
    public Conexion(String baseDeDatos, String usuario, String pwd) {
        Configuracion configuracion = new Configuracion();
        this.baseDeDatos = baseDeDatos;
        this.usuario = usuario;
        this.password = pwd;
        this.servidor = "jdbc:oracle:thin:@" + configuracion.getServidor() + ":" + configuracion.getPuerto() + ":" + configuracion.getSid();
    }
    
    public void Iniciar() {
        try {
            this.getClass();
            Class.forName("oracle.jdbc.OracleDriver");
            this.setConexion(DriverManager.getConnection(this.servidor, this.usuario, this.password));
            
            System.out.println("Conexion Satisfactoria");
        }
        catch (Exception error) {
            System.out.println("Error al realizar la conexion");
            error.printStackTrace();
        }
    }

    public void Cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception error) {
                System.out.print("No es posible cerrar la Conexion");
            }
        }
        System.out.println("Conexion Cerrada Satisfactoria");
    }

    public void CerrarStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception var2_2) {
                // empty catch block
            }
        }
        
        System.out.println("Conexion Cerrada Satisfactoria");
    }

    public void Finalizar() {
        if (this.getConexion() != null) {
            try {
                this.getConexion().close();
            }
            catch (Exception var1_1) {
                // empty catch block
            }
        }
        
        System.out.println("Conexion Cerrada Satisfactoria");
    }

    public Connection getConexion() {
        return this.conexion;
    }

    public static void main(String[] agrs) {
        Conexion conexion = new Conexion(); //"trans", "HILTIBR", "HILTIBRXXX");
        conexion.Iniciar();
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}