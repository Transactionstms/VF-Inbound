/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuario;

import com.conexion.Conexion;
import com.menu.BodegaFactory;
import com.menu.Menu;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergiocastros
 */
public class DaoUsuario {

    private final Conexion conexion;

    public DaoUsuario() {
        conexion = new Conexion();
    }

    public Usuario getUsuario(String usuario, String password,String cliente, int idLenguaje) throws SQLException {
        CallableStatement cS = null;
        ResultSet rs = null;
        Usuario user = new Usuario(usuario, password,cliente);

        try {
            conexion.conectar();
            cS = ScriptUsuario.getUsuario(conexion, usuario, password, idLenguaje);
            cS.execute();
            rs = (ResultSet) cS.getObject(4);
            List<Menu> menu = new ArrayList<Menu>();
            while (rs.next()) {
                user = new Usuario(
                        rs.getInt("USER_ID"),
                        usuario,
                        password,
                        rs.getString("USER_EMAIL"),
                        rs.getString("USER_NAME"),
                        rs.getString("APELLIDO_PATERNO"),
                        rs.getString("APELLIDO_MATERNO"),
                        rs.getInt("LENGUAJE_ID"),
                        true,
                        "Usuario con Credencales Válidas",
                        cliente
                );
                user.setDireccion(rs.getString("DIRECCION"));
                Menu submenu = new Menu(
                        rs.getInt("ID"),
                        rs.getString("DESCRIPCION"),
                        rs.getInt("ID_PADRE"),
                        rs.getString("ICONO"),
                        rs.getString("URL"));
                if (submenu.getId() == submenu.getIdPadre()) {
                    menu.add(submenu);
                } else {                    
                    getPadre(menu,submenu);
                }

            }
            if (cS != null) {
                cS.close();
                rs.close();
            }
            user.setMenu(menu);
            if (user.getNombre() == null || user.getNombre().trim().equals("")) {
                user.setValido(false);
                user.setMensaje("Usuario con No Credencales Válidas");

            } else {
                conexion.conectar();
                cS = ScriptUsuario.getBodegas(conexion, user);
                cS.execute();
                rs = (ResultSet) cS.getObject(2);
                List<Bodega> bodegas = new ArrayList<Bodega>();
                while (rs.next()) {
                    bodegas.add(new Bodega(rs.getInt("bodega_id"), rs.getInt("broker_cuenta_id"), rs.getString("cuenta_nombre"), rs.getInt("CBDIV_ID"), rs.getString("bodega_nombre")));
                }
                user.setBodegas(bodegas);
                user.setBodegasHtml(BodegaFactory.setListado(bodegas, -1));

                if (cS != null) {
                    cS.close();
                    rs.close();
                }
                cS = ScriptUsuario.getLenguajes(conexion);
                cS.execute();
                rs = (ResultSet) cS.getObject(1);
                List<Lenguaje> lenguajes = new ArrayList<Lenguaje>();
                while (rs.next()) {
                    lenguajes.add(new Lenguaje(rs.getInt("id_lenguaje"), rs.getString("descripcion"), rs.getString("icono")));
                }
                user.setLenguajes(lenguajes);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            user.setValido(false);
            user.setMensaje("Usuario con No Credencales Válidas..." + ex.getMessage());
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();

        } finally {
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();

        }
        return user;
    }
    
    private void getPadre(List<Menu> menu,Menu submenu){
        for(int j=0;j<menu.size();j++){
                        if(menu.get(j).getId() == submenu.getIdPadre()){
                            menu.get(j).setSubmenu(submenu);
                        } else{
                            if(menu.get(j).getSubmenus().size()>0){
                                getPadre(menu.get(j).getSubmenus(),submenu);
                            }
                        }                       
                    }
    }

}
