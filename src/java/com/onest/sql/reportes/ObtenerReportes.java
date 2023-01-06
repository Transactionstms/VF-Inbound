/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.sql.reportes;

import com.conexion.Conexion;
import com.onest.util.PropertiesUtil;
import java.sql.CallableStatement;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;
import oracle.sql.ARRAY;

public class ObtenerReportes {

    public static CallableStatement getReporte(Conexion conexion,
            String nombreProcedimientoAlmacenado,
            String cuenta,
            String division,
            String lineaTransporte,
            String estado,
            String fecha1,
            String fecha2,
            int idLenguaje,
            String tienda
    ) throws SQLException {
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call " + nombreProcedimientoAlmacenado + "(?,?,?,?,?,?,?,?,?) }");
            Integer[] iCuentas = null;
            Integer[] iDivision = null;
            String[] iLineaTransporte = null;
            String[] iEstado = null;
            if (cuenta != null) {
                String[] vCuenta = cuenta.split(",");
                iCuentas = new Integer[vCuenta.length];
                for (int c = 0; c < vCuenta.length; c++) {
                    iCuentas[c] = Integer.parseInt(vCuenta[c]);
                }
            }
            if (division != null) {
                String[] vDivision = division.split(",");
                iDivision = new Integer[vDivision.length];
                for (int c = 0; c < vDivision.length; c++) {
                    iDivision[c] = Integer.parseInt(vDivision[c]);
                }
            }
            if (lineaTransporte != null) {
                String[] vLineaTransporte = lineaTransporte.split(",");
                iLineaTransporte = new String[vLineaTransporte.length];
                for (int c = 0; c < vLineaTransporte.length; c++) {
                    iLineaTransporte[c] = vLineaTransporte[c];
                }
            }
            if (estado != null) {
                String[] vEstado = estado.split(",");
                iEstado = new String[vEstado.length];
                for (int c = 0; c < vEstado.length; c++) {
                    iEstado[c] = vEstado[c];
                }
            }

            ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor("" + PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER) + ".ARRAY_INT", conexion.getConexion());
            ARRAY array = new ARRAY(descriptor, conexion.getConexion(), iCuentas);
            sp.setArray(1, array);
            array = new ARRAY(descriptor, conexion.getConexion(), iDivision);
            sp.setArray(2, array);
            ArrayDescriptor descriptorString = ArrayDescriptor.createDescriptor("" + PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER) + ".ARRAY_STRING", conexion.getConexion());
            ARRAY arrayString = new ARRAY(descriptorString, conexion.getConexion(), iLineaTransporte);
            sp.setArray(3, arrayString);
            descriptorString = ArrayDescriptor.createDescriptor("" + PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER) + ".ARRAY_STRING", conexion.getConexion());
            arrayString = new ARRAY(descriptorString, conexion.getConexion(), iEstado);
            sp.setArray(4, arrayString);
            sp.setString(5, fecha1);
            sp.setString(6, fecha2);
            sp.setInt(7, idLenguaje);
            sp.setString(8, tienda);
            sp.registerOutParameter(9, OracleTypes.CURSOR);
            return sp;
        } catch (Exception exception) {
            throw exception;
        }
    }
 /**
     * Metodo DocumentosNodisponibles
     * consulta los documentos no disponibles
     * @param conexion conexion ala base de datos
     * @return CallableStatement resultados de la consulta
     * @throws SQLException
     */
    public static CallableStatement DocumentosNodisponibles(Conexion conexion,String fecha1,String fecha2,String cuentas) throws SQLException{
        String[]cuenta=cuentas.split(",");
        Integer [] cuentasF= new Integer[cuenta.length];
        for(int c=0;c<cuenta.length;c++)
            cuentasF[c]=Integer.parseInt(cuenta[c]);
        ArrayDescriptor descriptor;
        ARRAY array;
        System.out.println("SP: SP_REPORTE_DOCTOS_NODISP");
        System.out.println("___________________________");
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call SP_REPORTE_DOCTOS_NODISP(?,?,?,?) }");
            sp.setString(1, fecha1);
            sp.setString(2, fecha2);
             descriptor= ArrayDescriptor.createDescriptor(PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER)+".ARRAY_INT", conexion.getConexion());
            array=new ARRAY(descriptor,conexion.getConexion(),cuentasF);
            sp.setArray(3, array);
            sp.registerOutParameter(4, OracleTypes.CURSOR);            
            return sp;
        } catch (Exception exception) {
            return null;
        }
    }
    
    public static CallableStatement Cartonesporbl(Conexion conexion,String fecha1,String fecha2,String cuentas,String bol) throws SQLException{
        String[]cuenta=cuentas.split(",");
        Integer [] cuentasF= new Integer[cuenta.length];
        for(int c=0;c<cuenta.length;c++)
            cuentasF[c]=Integer.parseInt(cuenta[c]);
        ArrayDescriptor descriptor;
        ARRAY array;
        System.out.println("SP: SP_CARTONES_BOL");
        System.out.println("___________________________");
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call SP_CARTONES_BOL(?,?,?,?,?) }");
            sp.setString(1, fecha1);
            sp.setString(2, fecha2);
             descriptor= ArrayDescriptor.createDescriptor(PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER)+".ARRAY_INT", conexion.getConexion());
            array=new ARRAY(descriptor,conexion.getConexion(),cuentasF);
            sp.setArray(3, array);
            sp.setString(4, bol);
            sp.registerOutParameter(5, OracleTypes.CURSOR); 
            
            return sp;
        } catch (Exception exception) {
            return null;
        }
    }
    
    public static CallableStatement CartonesporTDA(Conexion conexion,String fecha1,String fecha2,String cuentas, String userIDNumber) throws SQLException{
        String[]cuenta=cuentas.split(",");
        Integer [] cuentasF= new Integer[cuenta.length];
        for(int c=0;c<cuenta.length;c++)
            cuentasF[c]=Integer.parseInt(cuenta[c]);
        ArrayDescriptor descriptor;
        ARRAY array;
        System.out.println("SP: SP_CARTONES_TIENDA");
        System.out.println("___________________________");
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call SP_CARTONES_TIENDA(?,?,?,?,?) }");
            sp.setString(1, fecha1);
            sp.setString(2, fecha2);
             descriptor= ArrayDescriptor.createDescriptor(PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER)+".ARRAY_INT", conexion.getConexion());
            array=new ARRAY(descriptor,conexion.getConexion(),cuentasF);
             sp.setArray(3, array);
            sp.setString(4, userIDNumber);
            sp.registerOutParameter(5, OracleTypes.CURSOR);
            
            return sp;
        } catch (Exception exception) {
            return null;
        }
    }
    
}
