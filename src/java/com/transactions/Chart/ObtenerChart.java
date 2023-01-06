/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transactions.Chart;

import com.conexion.Conexion;
import com.onest.sql.reportes.ObtenerReportes;
import excel.Genera;
import excel.ResultsetToExcel;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ObtenerChart{

    private Conexion conexion;

    public ObtenerChart() {
        conexion = new Conexion();
    }

    public String getReporte(
            String nombreProcedimientoAlmacenado,
            String cuenta,
            String division,
            String lineaTransporte,
            String estado,
            String fecha1,
            String fecha2,
            int idLenguaje,
            String inputFile,
            String ouputFile,
            String titulo,
            String tienda) throws Exception {
        CallableStatement cS = null;
        ResultSet rs = null;
        String html = "";
        try {
            conexion.conectar();
            cS = ObtenerReportes.getReporte(conexion,
                    nombreProcedimientoAlmacenado,
                    cuenta,
                    division,
                    lineaTransporte,
                    estado,
                    fecha1,
                    fecha2,
                    idLenguaje,
                    tienda
                    );
            cS.execute();
            rs = (ResultSet) cS.getObject(8);
            String hhh="";
            while(rs.next()){
            hhh=rs.getString(1);
            }
            html = Genera.generateExcelFile(rs, inputFile, ouputFile, titulo);
        } catch (Exception ex) {
            html=ex.getMessage();
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();
            throw ex;

        } finally {
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();

        }
        return html;

    }
 /**
     * Metodo DocumentosNodisponibles consulta los documentos no disponibles
     * Param String Cuentas
     * @return String[][] resultados de la consulta
     * @throws SQLException
     */
    public String[][] DocumentosNodisponibles(String fecha1, String fecha2,String cuentas) throws SQLException {
        CallableStatement cS = null;
        ResultSet rs = null;
        String[][] resultados = null;
        try {
            conexion.conectar();
            cS = ObtenerReportes.DocumentosNodisponibles(conexion, fecha1,  fecha2,cuentas);
            cS.execute();
            rs = (ResultSet) cS.getObject(4);
            int colum = rs.getMetaData().getColumnCount();
            int index = 0;
            ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
            ArrayList<String> datos;
            while (rs.next()) {
                datos = new ArrayList<String>();
                for (int i = 1; i <= colum; i++) {
                    datos.add(rs.getString(i));
                }
                lista.add(datos);
            }
            if (lista.size() == 0) {
                resultados = null;
            } else {
                resultados = new String[lista.size()][colum];
            }
            for (int c = 0; c < lista.size(); c++) {
                for (int i = 0; i < lista.get(c).size(); i++) {
                    resultados[c][i] = lista.get(c).get(i);
                }
            }

        } catch (Exception error) {
            System.out.println(error.toString());
        } finally {
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();

        }
        return resultados;

    }

    public void DocumentosNodisponibles(String fecha1, String fecha2,String cuentas, String inputFile, String outputFile,String titulo) throws SQLException {
        CallableStatement cS = null;
        ResultSet rs = null;
        String[][] resultados = null;
        try {
            conexion.conectar();
            cS = ObtenerReportes.DocumentosNodisponibles(conexion,fecha1, fecha2, cuentas);
            cS.execute();
            rs = (ResultSet) cS.getObject(4);
            ResultsetToExcel resultsetToExcel = new ResultsetToExcel();
            resultsetToExcel.generateExcelFile(rs, inputFile, outputFile,titulo);

        } catch (Exception error) {
            System.out.println(error.toString());
        } finally {
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();

        }

    }
    
    
    
    public String[][] Cartonesporbl(String fecha1, String fecha2,String cuentas,String bol) throws SQLException {
        CallableStatement cS = null;
        ResultSet rs = null;
        String[][] resultados = null;
        try {
            conexion.conectar();
            cS = ObtenerReportes.Cartonesporbl(conexion, fecha1, fecha2,cuentas,bol);
            cS.execute();
            rs = (ResultSet) cS.getObject(5);
            int colum = rs.getMetaData().getColumnCount();
            int index = 0;
            ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
            ArrayList<String> datos;
            while (rs.next()) {
                datos = new ArrayList<String>();
                for (int i = 1; i <= colum; i++) {
                    datos.add(rs.getString(i));
                }
                lista.add(datos);
            }
            if (lista.size() == 0) {
                resultados = null;
            } else {
                resultados = new String[lista.size()][colum];
            }
            for (int c = 0; c < lista.size(); c++) {
                for (int i = 0; i < lista.get(c).size(); i++) {
                    resultados[c][i] = lista.get(c).get(i);
                }
            }

        } catch (Exception error) {
            System.out.println(error.toString());
        } finally {
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();

        }
        return resultados;

    }

    public void Cartonesporbl(String fecha1, String fecha2,String cuentas, String inputFile, String outputFile,String titulo,String bol) throws SQLException {
        CallableStatement cS = null;
        ResultSet rs = null;
        String[][] resultados = null;
        try {
            conexion.conectar();
            cS = ObtenerReportes.Cartonesporbl(conexion,fecha1, fecha2, cuentas,bol);
            cS.execute();
            rs = (ResultSet) cS.getObject(5);
            ResultsetToExcel resultsetToExcel = new ResultsetToExcel();
            resultsetToExcel.generateExcelFile(rs, inputFile, outputFile,titulo);

        } catch (Exception error) {
            System.out.println(error.toString());
        } finally {
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();

        }

    }

    public String[][] CartonesporTDA(String fecha1, String fecha2,String cuentas,String tda,String userIDNumber) throws SQLException {
        CallableStatement cS = null;
        ResultSet rs = null;
        String[][] resultados = null;
        try {
            conexion.conectar();
            cS = ObtenerReportes.CartonesporTDA(conexion, fecha1, fecha2,cuentas,userIDNumber);
            cS.execute();
            rs = (ResultSet) cS.getObject(5);
            int colum = rs.getMetaData().getColumnCount();
            int index = 0;
            ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
            ArrayList<String> datos;
            while (rs.next()) {
                datos = new ArrayList<String>();
                for (int i = 1; i <= colum; i++) {
                    datos.add(rs.getString(i));
                }
                lista.add(datos);
            }
            if (lista.size() == 0) {
                resultados = null;
            } else {
                resultados = new String[lista.size()][colum];
            }
            for (int c = 0; c < lista.size(); c++) {
                for (int i = 0; i < lista.get(c).size(); i++) {
                    resultados[c][i] = lista.get(c).get(i);
                }
            }

        } catch (Exception error) {
            System.out.println(error.toString());
        } finally {
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();

        }
        return resultados;

    }

    public void CartonesporTDA(String fecha1, String fecha2,String cuentas, String inputFile, String outputFile,String titulo,String tda,String userIDNumber) throws SQLException {
        CallableStatement cS = null;
        ResultSet rs = null;
        String[][] resultados = null;
        try {
            conexion.conectar();
            cS = ObtenerReportes.CartonesporTDA(conexion,fecha1, fecha2, cuentas,userIDNumber);
            cS.execute();
            rs = (ResultSet) cS.getObject(5);
            ResultsetToExcel resultsetToExcel = new ResultsetToExcel();
            resultsetToExcel.generateExcelFile(rs, inputFile, outputFile,titulo);

        } catch (Exception error) {
            System.out.println(error.toString());
        } finally {
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();

        }

    }


}
