/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.menu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar
 */
public class Menu {
    private int id;
    private String descripcion;
    private int idPadre;
    private String icono;
    private String url;
    private List<Menu> submenus;

    public Menu(int id, String descripcion, int idPadre, String icono, String url) {
        this.id = id;
        this.descripcion = descripcion;
        this.idPadre = idPadre;
        this.icono = icono;
        this.url = url;
        this.submenus= new ArrayList<Menu>();
    }
    public Menu(){
        this.id = -1;
        this.descripcion = "";
        this.idPadre = -1;
        this.icono = "";
        this.url = "";
        this.submenus= new ArrayList<Menu>();
    }
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the idPadre
     */
    public int getIdPadre() {
        return idPadre;
    }

    /**
     * @return the icono
     */
    public String getIcono() {
        return icono;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the submenus
     */
    public List<Menu> getSubmenus() {
        return submenus;
    }

    /**
     * @param submenus the submenus to set
     */
    public void setSubmenus(List<Menu> submenus) {
        this.submenus = submenus;
    }
    public void setSubmenu(Menu submenu) {
        this.submenus.add(submenu);
    }
    
        
}
