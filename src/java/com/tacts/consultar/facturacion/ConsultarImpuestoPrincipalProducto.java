/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.consultar.facturacion;

import com.dao.ServiceDAO;
import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_
 */
public class ConsultarImpuestoPrincipalProducto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            String UserId = (String) ownsession.getAttribute("login.user_id_number");
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            oraDB.connect(dbData.getUser(), dbData.getPassword());
            String cve = (String) ownsession.getAttribute("cbdivcuenta");

            ConsultasQuery fac = new ConsultasQuery();
            ServiceDAO dao = new ServiceDAO();
            
            String codigo_concepto = request.getParameter("codigo_concepto");
            float cantidad = Float.parseFloat(request.getParameter("cantidad")); 
            String precio_unitario = request.getParameter("precio_unitario");
            String concepto_desc = "";
            String tipoImpuesto = "";
            String campo = "";
            String salida = "";
            float cantidadBase = 0;
            float iva = 0.16f; 
            float cantidadImporte =0;
            
            float cantidadExist = 0;
            float cantidadTotal = 0;
            
            //Extraer descripción del producto por medio del id
            if (db.doDB(fac.consultarConceptoDesc(codigo_concepto, cve))) {
                for (String[] rowC : db.getResultado()) {
                    concepto_desc = rowC[0];
                }
            }
            
            /*CONSULTAR EXISTENCIA DEL IMPUESTO LIGADO AL NUMERO DE FACTURA*/
            String existConcepto = "SELECT DISTINCT TRI.REL_IMP_ID FROM TRA_REL_CONCEPTOS TRC INNER JOIN TRA_REL_IMPUESTOS TRI ON TRC.REL_IMPUESTO_ID = TRI.REL_IMPUESTO_ID WHERE TRC.CONCEPTO_ID ='" + codigo_concepto + "' AND TRI.CBDIV_ID = '" + cve + "' AND TRI.ESTADO_ID =0";
            boolean oraOutC = oraDB.execute(existConcepto); 
            
            if(oraOutC){
                if (db.doDB(fac.consultarBaseImporteImpuesto(codigo_concepto, cve))) {
                    for (String[] rowy : db.getResultado()) {
                        cantidadExist = Float.parseFloat(rowy[0]);
                    }
                }
                cantidadTotal = cantidad+cantidadExist; 
            }else{
                cantidadTotal = cantidad;
            }
            
            cantidadBase = cantidadTotal * Float.parseFloat(precio_unitario);  
            cantidadImporte = cantidadBase * iva;
            
            //Consultar id_impuesto ligado al producto:
            String consultarIdImpuesto = "SELECT DISTINCT ID_IMPUESTO FROM TRA_PRODUCTOS_SERVICIOS WHERE DESCRIPCION= '" + concepto_desc + "' AND CBDIV_ID = '" + cve + "'";
            ResultSet rsi = dao.consulta(consultarIdImpuesto);
            while (rsi.next()) {
                tipoImpuesto = rsi.getString(1);
            }
            dao.CerrarConexion();
            
              
            if(tipoImpuesto.equals("1")){           //ISR
                
               campo = " ISR, ";
               
            }else if(tipoImpuesto.equals("2")){     //IVA
                
                String consultarIva = "SELECT DISTINCT IVA_ID, IVA_RET FROM TRA_PRODUCTOS_SERVICIOS WHERE DESCRIPCION = '" + concepto_desc + "' AND CBDIV_ID = '" + cve + "'"; 
                ResultSet rs = dao.consulta(consultarIva);
                while (rs.next()) {
                    if (!rs.getString(1).equals("0.000000")) {
                        campo = " IVA_ID, ";
                    } else if (!rs.getString(2).equals("0.000000")) {
                        campo = " IVA_RET, ";
                    } else {
                        campo = " IVA_ID, ";
                    }
                }
                
            }else if(tipoImpuesto.equals("3")){     //IEPS
                
                String consultarIeps = "SELECT DISTINCT IEPSTASA_ID, IEPS_CUOTA FROM TRA_PRODUCTOS_SERVICIOS WHERE DESCRIPCION = '" + concepto_desc + "' AND CBDIV_ID = '" + cve + "'";
                ResultSet rs = dao.consulta(consultarIeps);
                while (rs.next()) {
                    if (Integer.valueOf(rs.getString(1)) > 0) {
                        campo = " IEPSTASA_ID, ";
                    } else if (Integer.valueOf(rs.getString(2)) > 0) {
                        campo = " IEPS_CUOTA, ";
                    } else {
                        campo = " IEPSTASA_ID, ";
                    }
                }
               
            }
            
            dao.CerrarConexion();
            
            String consultarData = " SELECT DISTINCT "
                                 + campo
                                 + " ID_TIPO_IMPUESTO, "
                                 + " ID_IMPUESTO, "
                                 + " ID_TIPO_FACTOR, "
                                 + " TIPO_REGISTRO, "
                                 + " CODIGO  "
                                 + " FROM TRA_PRODUCTOS_SERVICIOS "
                                 + " WHERE DESCRIPCION = '" + concepto_desc + "' "
                                 + " AND CBDIV_ID = '" + cve + "' ";
            
            ResultSet rs = dao.consulta(consultarData);
            while (rs.next()) {
                salida = rs.getString(2)  //tipoImpuestoId
                 + "*" + cantidadBase     //cantidadBase
                 + "*" + rs.getString(3)  //impuestoId
                 + "*" + rs.getString(4)  //tipoFactorId
                 + "*" + rs.getString(1)  //tasa-porcentaje valor
                 + "*0"                   //cuota
                 + "*" + cantidadImporte  //cantidad importe
                 + "*" + rs.getString(5)  //tipo registro   
                 + "*" + rs.getString(6); //codigo concepto 
            }
            
            out.print(salida);
            dao.CerrarConexion();
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarImpuestoPrincipalProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarImpuestoPrincipalProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}