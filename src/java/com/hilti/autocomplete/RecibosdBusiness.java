package com.hilti.autocomplete;

import com.dao.ServiceDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RicardoMartinez
 */
public class RecibosdBusiness extends ServiceDAO {

    public String actualizaAviso(String aviso, String guia) {
        String sql = "UPDATE ONTMS_DOCTO_SAL SET DOCTO_CONCENTRADO = '" + guia + "', DOCTO_ESTADO_ID = 26 WHERE DOCTO_REFERENCIA = '" + aviso + "'";
        try {
            if (update(sql)) {
                return "El aviso ha sido recibido correctamente ";
            } else {
                return "ERROR: El aviso no ha sido recibido";
            }
        } catch (Exception e) {
            return "Ocurrio un error en la Base de Datos";
        }

    }

    public boolean validaExistenciaAviso(String aviso) throws SQLException {
        ResultSet res;
        String sql = "SELECT * FROM ONTMS_DOCTO_SAL WHERE upper(DOCTO_REFERENCIA) like upper('" + aviso + "') and docto_estado_id = 25";
        res = this.consulta(sql);
        if (res.next()) {
            return true;

        } else {
            return false;
        }
    }

  

}
