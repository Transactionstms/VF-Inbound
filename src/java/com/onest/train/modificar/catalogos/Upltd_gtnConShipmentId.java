/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.modificar.catalogos;

import com.onest.oracle.Conexion;
import com.onest.util.PropertiesUtil;
import java.sql.CallableStatement;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;
import oracle.sql.ARRAY;

/**
 *
 * @author Desarrollo Tacts
 */
public class Upltd_gtnConShipmentId {
    
    public static CallableStatement ltdGtnShipmentId(Conexion conexion, String shipmentId) throws Exception {
        try {
            CallableStatement sp = conexion.getConexion().prepareCall("{ call sp_inb_actualizaltd_gtnConShip(?,?) }");
            sp.setString("pshipment_details", shipmentId);
            sp.registerOutParameter("resultado", -10);
            return sp;
        } catch (Exception exception) {
            System.out.println("--" + exception);
            return null;
        }
    }
    
}
