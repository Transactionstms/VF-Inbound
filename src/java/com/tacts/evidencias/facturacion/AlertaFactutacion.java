/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.facturacion;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class AlertaFactutacion extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        
        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        String UserId = (String) ownsession.getAttribute("login.user_id_number");
        String cve = (String) ownsession.getAttribute("cbdivcuenta");
        
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        oraDB.connect(dbData.getUser(), dbData.getPassword());

        Email correo = new Email();
        ConsultasQuery fac = new ConsultasQuery();
        
        //Extracción de Fecha
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(date);
        String[] par1 = fecha.split("/");
        String dia = par1[0];
        String mes = par1[1];
        String anio = par1[2];

        //Extracción de Hora   
        Date hr = new Date();
        DateFormat hours = new SimpleDateFormat("HH:mm:ss");
        String hour = hours.format(hr);
        String[] par2 = hour.split(":");
        String hora = par2[0]; 
        String minutos = par2[1];
        String segundos = par2[2];
        
        String serie = request.getParameter("serie");
        String folio = request.getParameter("folio");
        String tipoGeneracion = request.getParameter("tipoGeneracion");
        String tipoTimbrado = request.getParameter("tipoTimbrado");
        String correos = request.getParameter("correos").replaceFirst(" ", "/"); 
        String uuid = "";
        String fileName = "";
        
        /*
          1 = CFDI
          2 = Carta Porte
          3 = Recibo de Pago
          4 = Tickets POS
          5 = Cancelación
        */
        
        if (db.doDB(fac.consultarCadena(serie, folio, cve))) {
            for (String[] row : db.getResultado()) {
                fileName = row[0] + "_" + dia + "_" + mes + "_" + anio + "_" + hora + "_" + minutos + "_" + segundos + "_" + row[1] + "_" + row[2];
                uuid = row[3];
            }
        }
        
        if(tipoGeneracion.equals("1")){
            
            if(tipoTimbrado.equals("2")){   //Si Timbrado
                if (db.doDB(fac.consultarRutaCfdiXML(serie, folio, cve))) {             //XML CFDI
                    for (String[] rowx : db.getResultado()) {
                        correo.alertaFactura(fileName + ".xml", correos, rowx[0], fileName); //Nombre Archivo|Correos|Ruta|Nombre Titulo correo
                    }
                }
            }
            
            if (db.doDB(fac.consultarRutaCfdiPDF(serie, folio, cve))) {          //PDF CFDI
                for (String[] rowx : db.getResultado()) {
                   correo.alertaFactura(fileName + ".pdf", correos, rowx[0], fileName);   
                }
            }
            
        }else if(tipoGeneracion.equals("2")){
            
            if(tipoTimbrado.equals("2")){   //Si Timbrado
                if (db.doDB(fac.consultarRutaCfdiXML(serie, folio, cve))) {          //XML CFDI
                    for (String[] rowx : db.getResultado()) {
                       correo.alertaFactura(fileName + ".xml", correos, rowx[0], fileName);  
                    }
                }
            }
            
            if (db.doDB(fac.consultarRutaCfdiPDF(serie, folio, cve))) {          //PDF CFDI
                for (String[] rowx : db.getResultado()) {
                   correo.alertaFactura(fileName + ".pdf", correos, rowx[0], fileName);  
                }
            }
            
            if (db.doDB(fac.consultarRutaCartaPortePDF(serie, folio, cve))) {    //PDF CARTA PORTE
                for (String[] rowx : db.getResultado()) {
                    correo.alertaFactura(fileName + ".pdf", correos, rowx[0], fileName);  
                }
            }
            
        }else if(tipoGeneracion.equals("3")){
            
            if (db.doDB(fac.consultarRutaReciboDePagoPDF(serie, folio, cve))) {  //PDF RECIBO DE PAGO
                for (String[] rowx : db.getResultado()) {
                    correo.alertaFactura(fileName + ".pdf", correos, rowx[0], fileName);  
                }
            }
            
        }else if(tipoGeneracion.equals("4")){
            
            if(tipoTimbrado.equals("2")){   //Si Timbrado
                if (db.doDB(fac.consultarRutaCfdiXML(serie, folio, cve))) {      //XML TICKETS
                    for (String[] rowx : db.getResultado()) {
                        correo.alertaFacturaPlebes(fileName + ".xml", correos, rowx[0], fileName); //Nombre Archivo|Correos|Ruta|Nombre Titulo correo
                    }
                }
            }
            
            if (db.doDB(fac.consultarRutaTicketsPDF(serie, folio, cve))) {  //PDF TICKETS
                for (String[] rowx : db.getResultado()) {
                    correo.alertaFacturaPlebes(fileName + ".pdf", correos, rowx[0], fileName);   
                }
            }
            
        }else if(tipoGeneracion.equals("5")){
            
            if(tipoTimbrado.equals("2")){   //Si Timbrado
                if (db.doDB(fac.consultarRutaCancelacionXML(uuid, cve))) {      //XML CANCELACIÓN
                    for (String[] rowx : db.getResultado()) {
                        correo.alertaFacturaCancelacion(fileName + ".xml", correos, rowx[0], fileName); //Nombre Archivo|Correos|Ruta|Nombre Titulo correo
                    }
                }
            }
        }
        
         oraDB.close(); //cerrar conexión
            
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
            Logger.getLogger(AlertaFactutacion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AlertaFactutacion.class.getName()).log(Level.SEVERE, null, ex);
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