/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documento;

import com.conexion.Conexion;
import com.documento.pojos.DetalleDocumento;
import com.documento.pojos.DocumentoEmbarque;
import com.documento.pojos.Encabezado;
import com.documento.pojos.PiePagina;
import com.usuario.Usuario;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DaoDocumento {

    private final Conexion conexion;

    public DaoDocumento() {
        conexion = new Conexion();
    }

    public DocumentoEmbarque getDocumento(String embarque, Usuario usuario) throws SQLException {
        CallableStatement cS = null;
        ResultSet rs = null;
        DocumentoEmbarque documentoEmbarque = new DocumentoEmbarque();
        try {
            conexion.conectar();
            cS = ScriptDocumento.getDocumentoEncabezado(conexion, embarque, usuario.getIdLenguaje());
            cS.execute();
            rs = (ResultSet) cS.getObject(3);
            Encabezado encabezado = null;
            while (rs.next()) {
                encabezado = new Encabezado(
                        rs.getString("AGRUPADOR"),
                        rs.getString("EMBARQUE"),
                        rs.getString("FECHA_CAPTURA"),
                        "",//cargador
                        usuario.getDireccion(),//Direccion
                        "",//RFC
                        "",//Porteador
                        rs.getString("L_TRANSPORTE_NOMBRE"),
                        rs.getString("DIRECCION_TRANSPORTE"),
                        rs.getString("UN_TRANSPORTE_DESCRIPCION"),
                        rs.getString("PLACAS"),//placa
                        rs.getString("MODELO"),//modelo
                        rs.getString("COLOR_DESCRIPCION"),//color
                        rs.getString("NOMBRE_CHOFER")//chofer

                );

            }
            documentoEmbarque.setEncabezado(encabezado);
            cS = ScriptDocumento.getDocumentoDetalle(conexion, encabezado.getAgrupador(), usuario.getIdLenguaje());
            cS.execute();
            rs = (ResultSet) cS.getObject(3);
            List<DetalleDocumento> detalleDocumento = new ArrayList<>();
            while (rs.next()) {
                detalleDocumento.add(
                        new DetalleDocumento(
                                rs.getString("docto_referencia"),//Docume nto
                                rs.getString("destino_nombre"),//destino
                                rs.getString("DESTINO_CIUDAD"),//ciudad
                                rs.getString("DESTINO_ESTADO"),//estado
                                rs.getString("PIEZAS"),//piezas
                                rs.getString("CAJAS"),//cajas
                                rs.getString("PALETS"),//palets
                                rs.getString("COLGADOS"),//col
                                rs.getString("CONTENEDOR"),//con
                                rs.getString("ATADOS"),//atados
                                rs.getString("BULTOS"),//bultos
                                rs.getString("VOLUMEN"),//volumen
                                rs.getString("PESO"),//kgs
                                rs.getString("FECHA_CAPTURA"),//factura
                                rs.getString("RECIBIDO"),//recibido
                                rs.getString("OBSERVACIONES"),//observ
                                rs.getString("CUENTA")//cuenta
                        ));
            }
            documentoEmbarque.setDetalleDocumento(detalleDocumento);
            PiePagina piePagina = null;
            cS = ScriptDocumento.getDocumentoPie(conexion, encabezado.getAgrupador(), usuario.getIdLenguaje());
            cS.execute();
            rs = (ResultSet) cS.getObject(3);
            while (rs.next()) {
                piePagina = new PiePagina(
                        usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno(),//NOMBRE fIRMA
                        "",//PUESTO
                        "",//FECHA SALIDA
                        encabezado.getLineaTransportista(),//LINEA TRANSPORTE
                        encabezado.getModelo() + ", " + encabezado.getColor() + ", " + encabezado.getPlacas(),//DESCRIPCION UNIDAD
                        rs.getInt("CUSTODIA"),// CUSTODIA
                        rs.getString("OBSERVACIONES"),//OBSERVACIONES
                        rs.getString("AUTORIZA"),//AUTORIZA SALIDA
                        rs.getString("RECIBI"),//RECIBI
                        rs.getString("REPORTE"),//LEYENDA 1
                        rs.getString("LEYENDAS"),// LEYENDA 2
                        ""// LEYENDA 3
                );
            }
            if (piePagina != null) {

                if (piePagina.getLeyendaLinea2() != null) {
                    Calendar calendario = Calendar.getInstance();
                    int hora = calendario.get(Calendar.HOUR_OF_DAY);
                    int minutos = calendario.get(Calendar.MINUTE);
                    int segundos = calendario.get(Calendar.SECOND);
                    String hms = hora + ":" + minutos + ":" + segundos;
                    Date fechaActual = new Date();
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MMMM-dd");
                    String cadenaFecha = formato.format(fechaActual);
                    String mesLetra[] = cadenaFecha.split("-");
                    String mes = "";
                    String m = mesLetra[1].toUpperCase();
                    if (m.equals("JANUARY") || m.equals("ENERO")) {
                        mes = "ENERO";
                    }
                    if (m.equals("FEBRUARY") || m.equals("FEBRERO")) {
                        mes = "FEBRERO";
                    }
                    if (m.equals("MARCH") || m.equals("MARZO")) {
                        mes = "MARZO";
                    }
                    if (m.equals("APRIL") || m.equals("ABRIL")) {
                        mes = "ABRIL";
                    }
                    if (m.equals("MAY") || m.equals("MAYO")) {
                        mes = "MAYO";
                    }
                    if (m.equals("JUNE") || m.equals("JUNIO")) {
                        mes = "JUNIO";
                    }
                    if (m.equals("JULY") || m.equals("JULIO")) {
                        mes = "JULIO";
                    }
                    if (m.equals("AUGUST") || m.equals("AGOSTO")) {
                        mes = "AGOSTO";
                    }
                    if (m.equals("SEPTEMBER") || m.equals("SEPTIEMBRE")) {
                        mes = "SEPTIEMBRE";
                    }
                    if (m.equals("OCTOBER") || m.equals("OCTUBRE")) {
                        mes = "OCTUBRE";
                    }
                    if (m.equals("NOVEMBER") || m.equals("NOVIEMBRE")) {
                        mes = "NOVIEMBRE";
                    }
                    if (m.equals("DECEMBER") || m.equals("DICIEMBRE")) {
                        mes = "DICIEMBRE";
                    }
                    piePagina.getLeyendaLinea2().replace("HHMMSS", hms);
                    piePagina.getLeyendaLinea2().replace("MMMMMMMM", mes);
                    piePagina.getLeyendaLinea2().replace("YYYY", mesLetra[0]);
                }

            }
            documentoEmbarque.setPiePagina(piePagina);
            if (cS != null) {
                cS.close();
                rs.close();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
        if (documentoEmbarque.getEncabezado().getAgrupador() == null || documentoEmbarque.getEncabezado().getAgrupador().isEmpty()) {
            documentoEmbarque = null;
        }
        return documentoEmbarque;
    }

}
