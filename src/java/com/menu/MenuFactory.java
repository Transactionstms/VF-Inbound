/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.menu;

import com.onest.util.PropertiesUtil;
import com.usuario.Usuario;
import java.util.List;

/**
 *
 * @MOD OVALOIS
 */
public class MenuFactory {

    private static String menu = "";

    
    public static String generaMenu(List<Menu> nodes,String cliente) {
        getMenu(nodes,cliente);
        return menu;
    }

    public static String getMenu() {
        return menu;
    }

    private static String getMenu(List<Menu> nodes,String cliente) {
        menu =
                /* HOME */

         "<li> <a href=\"<CONTEXT_PATH>/forms/main.jsp\" target=\"data\" class=\"waves-effect\"><i class=\"linea-icon linea-basic fa-fw text-danger\" data-icon=\"7\"></i> <span class=\"hide-menu text-danger\"> Home <span class=\"fa arrow\"></span></span></a>\n";
        
        
        
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getId() == nodes.get(i).getIdPadre()) {
                menu +=
                        /* MENUS*/
        "<li> <a href='" + nodes.get(i).getUrl() + "' target=\"data\" class=\"waves-effect\"><i class=\'  fa-fw "+ nodes.get(i).getIcono() +"\'></i> "
                        + "<span class=\"hide-menu\"><i class=\"linea-icon linea-basic\">\n"; /**/
        
                menu += nodes.get(i).getDescripcion() + "</i></span>\n";
                
         /* SUB-MENUS*/       
                menu += "   <ul class=\"nav nav-second-level\">\n";
                getSubmenus(nodes.get(i).getSubmenus(), nodes.get(i));
                menu += "</ul></li>\n";
            }
        }
        return menu;
    }

    private static void getSubmenus(List<Menu> nodes, Menu parentNode) {

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getIdPadre() == parentNode.getId()) {
                menu += "<li>";
                if(!nodes.get(i).getUrl().equals("#"))
                        menu += "<a href='<CONTEXT_PATH>/" + nodes.get(i).getUrl() + "'  target='data'>";
                else
                        menu += "<a href='" + nodes.get(i).getUrl() + "'>";
                menu += "<span>";
                menu += nodes.get(i).getDescripcion() +((nodes.get(i).getUrl().equals("#"))?"  >":"")+ "</span></a>\n";
                if(nodes.get(i).getSubmenus().size()>0){
                        menu += "<ul class=\"nav nav-third-level\">";
                        getSubmenus(nodes.get(i).getSubmenus(), nodes.get(i));
                        menu += "</ul></li>\n";
                }
            }
        }
    }
}
