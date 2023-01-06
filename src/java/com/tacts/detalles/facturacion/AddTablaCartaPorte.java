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
public class AddTablaCartaPorte extends HttpServlet {


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");


        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        String cve = (String) ownsession.getAttribute("cbdivcuenta");
        String UserId = (String) ownsession.getAttribute("login.user_id_number");
        ConsultasQuery fac = new ConsultasQuery();
        
        try (PrintWriter out = response.getWriter()) {
            
        //Parametros: Lugar de Embarque
        String lugarDeEmbarque = request.getParameter("LugarDeEmbarque");
        String lugEmb_nombre = request.getParameter("lugEmb_nombre");
        String lugEmb_rfc = request.getParameter("lugEmb_rfc");
        String lugEmb_calle = request.getParameter("lugEmb_calle");	
        String lugEmb_noInterior = request.getParameter("lugEmb_noInterior");	
        String lugEmb_noExterior = request.getParameter("lugEmb_noExterior");	
        String lugEmb_codigoPostal = request.getParameter("lugEmb_codigoPostal");	
        String lugEmb_estado = request.getParameter("lugEmb_estado");
        String lugEmb_municipio = request.getParameter("lugEmb_municipio");	
        String lugEmb_colonia = request.getParameter("lugEmb_colonia");	
        String lugEmb_referencia = request.getParameter("lugEmb_referencia");	
        String lugEmb_fechaHoraSalidaLlegada = request.getParameter("lugEmb_fechaHoraSalidaLlegada");	
        //String lugEmb_distancia = request.getParameter("lugEmb_distancia");	
        String lugEmb_localidad = request.getParameter("lugEmb_localidad");
        String lugEmb_idUbicacion = request.getParameter("lugEmb_idUbicacion");
        
        //Parametros: Lugar de Destino
        String lugarDestinatario = request.getParameter("LugarDestinatario");
        String dest_nombre = request.getParameter("dest_nombre");
        String dest_rfc = request.getParameter("dest_rfc");
        String dest_calle = request.getParameter("dest_calle");	
        String dest_noInterior = request.getParameter("dest_noInterior");	
        String dest_noExterior = request.getParameter("dest_noExterior");	
        String dest_codigoPostal = request.getParameter("dest_codigoPostal");	
        String dest_estado = request.getParameter("dest_estado");	
        String dest_municipio = request.getParameter("dest_municipio");	
        String dest_colonia = request.getParameter("dest_colonia");	
        String dest_referencia = request.getParameter("dest_referencia");  	
        String dest_fechaHoraSalidaLlegada = request.getParameter("dest_fechaHoraSalidaLlegada");	
        String dest_distancia = request.getParameter("dest_distancia");
        String dest_localidad = request.getParameter("dest_localidad");
        String dest_idUbicacion = request.getParameter("dest_idUbicacion");
        
        //Parametros: Lugar de Entrega
        String lugarEntrega = request.getParameter("LugarEntrega");
        String lugEnt_contacto = request.getParameter("lugEnt_contacto");	
        String lugEnt_nombre = request.getParameter("lugEnt_nombre");	
        String lugEnt_rfc = request.getParameter("lugEnt_rfc");
        String lugEnt_calle = request.getParameter("lugEnt_calle");	
        String lugEnt_noInterior = request.getParameter("lugEnt_noInterior");	
        String lugEnt_noExterior = request.getParameter("lugEnt_noExterior");	
        String lugEnt_codigoPostal = request.getParameter("lugEnt_codigoPostal");	
        String lugEnt_estado = request.getParameter("lugEnt_estado");	
        String lugEnt_municipio = request.getParameter("lugEnt_municipio");	
        String lugEnt_colonia = request.getParameter("lugEnt_colonia");	
        String lugEnt_referencia = request.getParameter("lugEnt_referencia");	      
        String lugEnt_pais = request.getParameter("lugEnt_pais");	
        String lugEnt_tipoTransporte = request.getParameter("lugEnt_tipoTransporte");	
        String lugEnt_fechaHoraSalidaLlegada = request.getParameter("lugEnt_fechaHoraSalidaLlegada");	
        String lugEnt_distancia = request.getParameter("lugEnt_distancia");
        String lugEnt_localidad = request.getParameter("lugEnt_localidad");
        String lugEnt_idUbicacion = request.getParameter("lugEnt_idUbicacion");
                
        //Parametros: Mercancía
        String unidad_peso_cp = request.getParameter("unidad_peso_cp");
        //String mercancia_peso_cp = request.getParameter("mercancia_peso_cp");
        String mercancia_es_peligroso_cp = request.getParameter("mercancia_es_peligroso_cp");
        String numpedimento_cp = request.getParameter("numpedimento_cp");
        String moneda_cp = request.getParameter("moneda_cp");
                
        //Parametros: Autotransporte
        String perm_sct = request.getParameter("perm_sct");
        String no_permismo_sct = request.getParameter("no_permismo_sct");
        String config_vehicular = request.getParameter("config_vehicular");
        String placa_vm = request.getParameter("placa_vm");
        String anio_modelovm = request.getParameter("anio_modelovm");
        String placa_remolque = request.getParameter("placa_remolque");
        String tipo_remolque = request.getParameter("tipo_remolque");
        String aseguradora_civil = request.getParameter("aseguradora_civil");
        String poliza_civil = request.getParameter("poliza_civil");
        String tipo_figura = request.getParameter("tipo_figura");
        String rfc_figura = request.getParameter("rfc_figura");
        String num_licencia = request.getParameter("num_licencia");
        String nombre_figura = request.getParameter("nombre_figura");
        //Parametros: Chofer Autotransporte
        String autChofer_calle = request.getParameter("autChofer_calle");  
        String autChofer_numero_exterior = request.getParameter("autChofer_noInterior"); 
        String autChofer_numero_interior = request.getParameter("autChofer_noExterior"); 
        String autChofer_colonia = request.getParameter("autChofer_colonia"); 
        String autChofer_localidad = request.getParameter("autChofer_localidad");       
        String autChofer_municipio = request.getParameter("autChofer_municipio");       
        String autChofer_referencia = request.getParameter("autChofer_referencia");      
        String autChofer_estado = request.getParameter("autChofer_estado");       
        String autChofer_codigoPostal = request.getParameter("autChofer_codigoPostal");   
            
        
          String salida = "&lugarDeEmbarque=" + lugarDeEmbarque 
                        + "&lugEmb_nombre=" + lugEmb_nombre 
                        + "&lugEmb_rfc=" + lugEmb_rfc
                        + "&lugEmb_calle=" + lugEmb_calle
                        + "&lugEmb_noInterior=" + lugEmb_noInterior
                        + "&lugEmb_noExterior=" + lugEmb_noExterior
                        + "&lugEmb_codigoPostal=" + lugEmb_codigoPostal
                        + "&lugEmb_estado=" + lugEmb_estado
                        + "&lugEmb_municipio=" + lugEmb_municipio
                        + "&lugEmb_colonia=" + lugEmb_colonia
                        + "&lugEmb_referencia=" + lugEmb_referencia
                        + "&lugEmb_fechaHoraSalidaLlegada=" + lugEmb_fechaHoraSalidaLlegada
                        //+ "&lugEmb_distancia=" + lugEmb_distancia
                        + "&lugEmb_localidad=" + lugEmb_localidad
                        + "&lugEmb_idUbicacion=" + lugEmb_idUbicacion
                  
                        + "&lugarDestinatario=" + lugarDestinatario 
                        + "&dest_nombre=" + dest_nombre
                        + "&dest_rfc=" + dest_rfc
                        + "&dest_calle=" + dest_calle
                        + "&dest_noInterior=" + dest_noInterior
                        + "&dest_noExterior=" + dest_noExterior
                        + "&dest_codigoPostal=" + dest_codigoPostal
                        + "&dest_estado=" + dest_estado
                        + "&dest_municipio=" + dest_municipio
                        + "&dest_colonia=" + dest_colonia
                        + "&dest_referencia=" + dest_referencia
                        + "&dest_fechaHoraSalidaLlegada=" + dest_fechaHoraSalidaLlegada
                        + "&dest_distancia=" + dest_distancia 
                        + "&dest_localidad=" + dest_localidad
                        + "&dest_idUbicacion=" + dest_idUbicacion
                  
                        + "&lugarEntrega=" + lugarEntrega
                        + "&lugEnt_contacto=" + lugEnt_contacto
                        + "&lugEnt_nombre=" + lugEnt_nombre
                        + "&lugEnt_rfc=" + lugEnt_rfc
                        + "&lugEnt_calle=" + lugEnt_calle
                        + "&lugEnt_noInterior=" + lugEnt_noInterior
                        + "&lugEnt_noExterior=" + lugEnt_noExterior
                        + "&lugEnt_codigoPostal=" + lugEnt_codigoPostal
                        + "&lugEnt_estado=" + lugEnt_estado
                        + "&lugEnt_municipio=" + lugEnt_municipio
                        + "&lugEnt_colonia=" + lugEnt_colonia
                        + "&lugEnt_referencia=" + lugEnt_referencia
                        + "&lugEnt_pais=" + lugEnt_pais
                        + "&lugEnt_tipoTransporte=" + lugEnt_tipoTransporte
                        + "&lugEnt_fechaHoraSalidaLlegada=" + lugEnt_fechaHoraSalidaLlegada
                        + "&lugEnt_distancia=" + lugEnt_distancia
                        + "&lugEnt_localidad=" + lugEnt_localidad
                        + "&lugEnt_idUbicacion=" + lugEnt_idUbicacion
                  
                        + "&unidad_peso_cp=" + unidad_peso_cp
                        //+ "&mercancia_peso_cp=" + mercancia_peso_cp
                        + "&mercancia_es_peligroso_cp=" + mercancia_es_peligroso_cp
                        + "&numpedimento_cp=" + numpedimento_cp
                        + "&moneda_cp=" + moneda_cp
                  
                        + "&perm_sct=" + perm_sct
                        + "&no_permismo_sct=" + no_permismo_sct
                        + "&config_vehicular=" + config_vehicular
                        + "&placa_vm=" + placa_vm
                        + "&anio_modelovm=" + anio_modelovm
                        + "&placa_remolque=" + placa_remolque
                        + "&tipo_remolque=" + tipo_remolque
                        + "&aseguradora_civil=" + aseguradora_civil
                        + "&poliza_civil=" + poliza_civil
                        + "&tipo_figura=" + tipo_figura
                        + "&rfc_figura=" + rfc_figura
                        + "&num_licencia=" + num_licencia
                        + "&nombre_figura=" + nombre_figura
                    
                        + "&autChofer_calle=" + autChofer_calle
                        + "&autChofer_numero_exterior=" + autChofer_numero_exterior
                        + "&autChofer_numero_interior=" + autChofer_numero_interior
                        + "&autChofer_colonia=" + autChofer_colonia
                        + "&autChofer_localidad=" + autChofer_localidad
                        + "&autChofer_municipio=" + autChofer_municipio
                        + "&autChofer_referencia=" + autChofer_referencia
                        + "&autChofer_estado=" + autChofer_estado
                        + "&autChofer_codigoPostal=" + autChofer_codigoPostal;
          
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