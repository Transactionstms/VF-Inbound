/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.modificar.catalogos;

import com.onest.oracle.Conexion;
import com.onest.train.consultas.actualCRDModel;
import java.sql.CallableStatement;
import java.sql.Types;

/**
 *
 * @author Desarrollo Tacts
 */
public class Rule2_UpLt2_EtaDcAndPutAwayLoadTipeNot {
    
    public static CallableStatement lt2ActualCrd(Conexion conexion, String fecha_actualCrd) throws Exception {
        try {
            CallableStatement sp = conexion.getConexion().prepareCall("{ call SP_INB_SUM_FECHA2(?,?) }");
            sp.setString(1, fecha_actualCrd);
            sp.registerOutParameter(2, Types.DATE);
            
            
            return sp;
        } catch (Exception exception) {
            System.out.println("--" + exception);
            return null;
        }
    }
    
}