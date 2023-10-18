/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author grecendiz
 */
public class Logout extends HttpServlet {

       //Ya sea que el método sea por GET o POST, cerraremos la sesion.
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession(true);
        
        
         HttpSession ownsession = request.getSession(false);
ownsession.removeAttribute("login.user_id_number");            
ownsession.removeAttribute("login.user_id");                   
ownsession.removeAttribute("login.user_name");                 
ownsession.removeAttribute("login.user_group");                
ownsession.removeAttribute("login.user_main_group");           
ownsession.removeAttribute("login.user_cuenta");               
ownsession.removeAttribute("login.is_super_user");             
ownsession.removeAttribute("login.ip");                        
ownsession.removeAttribute("login.controlUsuario");            
ownsession.removeAttribute("login.lastoctet");                 
ownsession.removeAttribute("login.root");                      
ownsession.removeAttribute("prefix.call.procid");                
ownsession.removeAttribute("db.servers");                      
ownsession.removeAttribute("onest.session");                   
ownsession.removeAttribute("cbdivcuenta");             
ownsession.removeAttribute("cbbodegaId");            
ownsession.removeAttribute("user.bodegabean");                 
ownsession.removeAttribute("credenciales.paqueteexpress");     
ownsession.removeAttribute("db.too.many.servers");                       
ownsession.removeAttribute("login.too.many.accounts");                    
ownsession.removeAttribute("db.data");                         
ownsession.removeAttribute("cuenta.valida.lote");                
ownsession.removeAttribute("cuenta.valida.id");                  
ownsession.removeAttribute("cuenta.nombre");                   
ownsession.removeAttribute("bodega.nombre");                   
ownsession.removeAttribute("bodega.id");                       
ownsession.removeAttribute("db.too.many.servers");                       
ownsession.removeAttribute("cliente.login");                   
ownsession.removeAttribute("login.too.many.accounts");                    
ownsession.removeAttribute("db.data"); 
                        
             
            //Cerrar sesion
        sesion.invalidate();
         
         //Redirecciono a index.jsp
        response.sendRedirect("index.jsp");
        
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}