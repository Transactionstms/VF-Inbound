/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuario;

import com.menu.Menu;
import com.menu.MenuFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar
 */
public class Usuario  {

    public Usuario(int id,String usuario,String password, String email, String nombre, String apellidoPaterno, String apellidoMaterno, int idLenguaje, boolean valido, String mensaje,String cliente) {
        this.id=id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idLenguaje = idLenguaje;
        this.password = password;
        this.email = email;
        this.valido = valido;
        this.mensaje = mensaje;   
        this.lenguajes= new ArrayList<>();
        this.cliente = cliente;
    }

    private List<Menu> menu;
    private String menuHtml;
    private String bodegasHtml;
    private List<Bodega> bodegas;

    public Usuario() {
        this.lenguajes= new ArrayList<>();
    }

    public Usuario(String usuario, String password,String cliente) {
        this.usuario = usuario;
        this.password = password;
        this.cliente=cliente;
        this.lenguajes= new ArrayList<>();
    }
    
    
    
    private String usuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int idLenguaje;
    private String password;
    private String email;
    private boolean valido;
    private String mensaje;
    private String direccion;
    private int id;
    private String cliente;

    private List<Lenguaje> lenguajes;
    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @return the idLenguaje
     */
    public int getIdLenguaje() {
        return idLenguaje;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the menu
     */
    public List<Menu> getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(List<Menu> menu) {
        this.menu = menu;
        this.menuHtml = MenuFactory.generaMenu(menu,this.cliente);
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @param idLenguaje the idLenguaje to set
     */
    public void setIdLenguaje(int idLenguaje) {
        this.idLenguaje = idLenguaje;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the valido
     */
    public boolean isValido() {
        return valido;
    }

    /**
     * @param valido the valido to set
     */
    public void setValido(boolean valido) {
        this.valido = valido;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the menuHtml
     */
    public String getMenuHtml() {
        return menuHtml;
    }

    /**
     * @param menuHtml the menuHtml to set
     */
    public void setMenuHtml(String menuHtml) {
        this.menuHtml = menuHtml;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the bodegas
     */
    public List<Bodega> getBodegas() {
        return bodegas;
    }

    /**
     * @param bodegas the bodegas to set
     */
    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    /**
     * @return the bodegasHtml
     */
    public String getBodegasHtml() {
        return bodegasHtml;
    }

    /**
     * @param bodegasHtml the bodegasHtml to set
     */
    public void setBodegasHtml(String bodegasHtml) {
        this.bodegasHtml = bodegasHtml;
    }

    /**
     * @return the lenguajes
     */
    public List<Lenguaje> getLenguajes() {
        return lenguajes;
    }

    /**
     * @param lenguajes the lenguajes to set
     */
    public void setLenguajes(List<Lenguaje> lenguajes) {
        this.lenguajes = lenguajes;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

   
    
}
