/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.detalles.facturacion;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
public class AddTablaConceptos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        String cve = (String) ownsession.getAttribute("cbdivcuenta");
        String UserId = (String) ownsession.getAttribute("login.user_id_number");
        oraDB.connect(dbData.getUser(), dbData.getPassword());
        ConsultasQuery fac = new ConsultasQuery();
        NumberFormat formatter = new DecimalFormat("#0.00"); 
        
        try (PrintWriter out = response.getWriter()) {
        
        String serie = request.getParameter("serie"); 
        String folio = request.getParameter("folio"); 
        String codigo_concepto = "";
        String caramelo = "";
        String check = "";
        String salida = "";
        int sal = 0;
        
        
        /*ARMADO DE TABLA*/
        if (db.doDB(fac.consultarConceptosIngresados(serie, folio, cve))) {
            for (String[] rowF : db.getResultado()) {
                
               /*PASO DE PARAMETROS (MODAL EDITAR CONCEPTOS)*/
               caramelo = rowF[1] + "*"                                         /*CONTADOR*/
                        + rowF[2] + "*"                                         /*DESCRIPCION*/
                        + rowF[3] + "*"                                         /*CANTIDAD*/
                        + rowF[4] + "*"                                         /*NUM IDENTIFICACION*/
                        + formatter.format(Float.parseFloat(rowF[6])) + "*"     /*PRECIO UNITARIO*/
                        + rowF[4] + "*"                                         /*CLAVE PROD/SERV SAT*/
                        + rowF[5] + "*"                                         /*CLAVE UNIDAD SAT*/
                        + formatter.format(Float.parseFloat(rowF[7])) + "*"     /*IMPORTE DESCUENTO*/
                        + "0";                                                  /*UNIDAD MEDIDA*/
               
                if(rowF[9] == null){ check = "✔"; }
                
                salida += "<tr id=\"tr" + sal + "\"> "
                        + " <td>"
                        + "     <center>" + check + "</center>"
                        + "     <input value=\"" + rowF[9] + "\" name=\"comentarios[" + sal + "]\"  id=\"comentarios[" + sal + "]\" type=\"hidden\" />"
                        + "     <input value=\"" + rowF[4] + "\" name=\"rel_clvsat_id[" + sal + "]\"  id=\"rel_clvsat_id[" + sal + "]\" type=\"hidden\" />"
                        + "     <input value=\"" + rowF[5] + "\" name=\"unidad_sat_id[" + sal + "]\"  id=\"unidad_sat_id[" + sal + "]\" type=\"hidden\" />"
                        + " </td>"
                        + " <td>" + rowF[2] + "<input value=\"" + rowF[1] + "\" name=\"concepto_id[" + sal + "]\"  id=\"concepto_id[" + sal + "]\" type=\"hidden\" /><input value=\"" + rowF[2] + "\" name=\"concepto_desc[" + sal + "]\"  id=\"concepto_desc[" + sal + "]\" type=\"hidden\" /></td> "
                        + " <td><center>" + rowF[3] + "</center><input value=\"" + rowF[3] + "\" name=\"cantidad[" + sal + "]\"  id=\"cantidad[" + sal + "]\" type=\"hidden\" /></td> "
                        + " <td><center>$ " + formatter.format(Float.parseFloat(rowF[6])) + "</center><input value=\"" + formatter.format(Float.parseFloat(rowF[6])) + "\" name=\"precio_unitario[" + sal + "]\"  id=\"precio_unitario[" + sal + "]\" type=\"hidden\" /></td> "
                        + " <td><center>$ " + formatter.format(Float.parseFloat(rowF[7])) + "</center><input value=\"" + formatter.format(Float.parseFloat(rowF[7])) + "\" name=\"importe_descuento[" + sal + "]\"  id=\"importe_descuento[" + sal + "]\" type=\"hidden\" /></td> "
                        + " <td><center>$ " + formatter.format(Float.parseFloat(rowF[8])) + "</center><input value=\"" + formatter.format(Float.parseFloat(rowF[8])) + "\" name=\"importe[" + sal + "]\"  id=\"importe[" + sal + "]\" type=\"hidden\" /></td> "
                        + " <td>"
                        + "     <a class=\"me-3 text-lg text-info\"><i class=\"fa fa-copyright fa-lg fa-fw btn btn-default btn-rounded\"></i></a> "
                        + "     <a class=\"me-3 text-lg text-info\" href=\"#\" role=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#modalEditarConceptos\" onclick=\"add_modalConceptos('" + caramelo + "')\"><i class=\"far fa-edit\"></i></a> "
                        + "     <a class=\"text-lg text-info\" onclick=\"delete_concepto('"+rowF[1]+"','"+rowF[0]+"')\"><i class=\"far fa-trash-alt\"></i></a>"
                        + " </td>"
                        + "</tr> ";
                
                        sal++;
            }
        }

            salida += "<input type=\"hidden\" id=\"numConceptos\" name=\"numConceptos\" value=\""+sal+"\">";
            
            oraDB.close();
            out.print(salida);   
            
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
            Logger.getLogger(AddTablaConceptos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddTablaConceptos.class.getName()).log(Level.SEVERE, null, ex);
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
