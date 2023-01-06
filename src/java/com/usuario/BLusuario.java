/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuario;

/**
 *
 * @author sergiocastros
 */
public class BLusuario {

    public static Usuario getUsuario(String usuario, String password,String cliente, int idLenguaje) {
        Usuario user = new Usuario(usuario, password,cliente);
        DaoUsuario daoUsuario = new DaoUsuario();
        try {
            user = daoUsuario.getUsuario(usuario, password,cliente, idLenguaje);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
        return user;
    }

}
