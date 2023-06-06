/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.facturacion;

import com.conexion.GenericJdbc;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import javax.activation.URLDataSource;
import com.dao.ServiceDAO;
import com.onest.train.consultas.ConsultasQuery;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author luis_
 */
public class Email {

    private final String CLAVE = "Tacts23*";
    private final String HOST = "smtp.gmail.com";
    private final String REMITENTE = "alertas1@tacts.mx";

    private Properties properties;
    private String asunto = "AvisosVF";

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    private String getFactura() {
        String mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">\n"
                + "    <div style=\"max-width:600px;margin:0 auto\">\n"
                + "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">\n"
                + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">\n"
                + "                <p style=\"margin:0;color:#333333\">\n"
                + "                <div align=\"center\">\n"
                + " <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Vf-corp-logo.svg/1920px-Vf-corp-logo.svg.png\" width=\"120\">"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div style=\"padding:20px 30px\">\n"
                + "                <h3> Buen día. </h3>\n"
                // + "                <h3>Folio:&nbsp;&nbsp;" + folio + "</h3>\n"
                + "                <br>"
                + "                <p>\n"
                //+ "                <h4>" + tipoProceso + ", se ha generado de forma exitosa.</h4>\n "
                + "                 <br>"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "<div style=\"max-width:600px;margin:0 auto\">\n"
                + "    <div style=\"font:11px sans-serif;color:#686f7a\">\n"
                + "        <p style=\"font-size:11px;color:#686f7a\">\n"
                + "        <center>\n"
                + "            Este es un mensaje informativo, favor de no contestar a este correo.\n"
                + "        </center>\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</div>\n"
                + "</body>";
        return mensaje;
    }

    private String getFacturaCancelacion(String nameEmail) {
        String mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">\n"
                + "    <div style=\"max-width:600px;margin:0 auto\">\n"
                + "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">\n"
                + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">\n"
                + "                <p style=\"margin:0;color:#333333\">\n"
                + "                <div align=\"center\">\n"
                + " <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Vf-corp-logo.svg/1920px-Vf-corp-logo.svg.png\" width=\"120\">"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div style=\"padding:20px 30px\">\n"
                + "                <h3> Buen día. </h3>\n"
                + "                <h3>" + nameEmail + "</h3>\n"
                + "                <br>"
                + "                <p>\n"
                + "                <h4>La cancelación, se ha generado de forma exitosa.</h4>\n "
                + "                 <br>"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "<div style=\"max-width:600px;margin:0 auto\">\n"
                + "    <div style=\"font:11px sans-serif;color:#686f7a\">\n"
                + "        <p style=\"font-size:11px;color:#686f7a\">\n"
                + "        <center>\n"
                + "            Este es un mensaje informativo, favor de no contestar a este correo.\n"
                + "        </center>\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</div>\n"
                + "</body>";
        return mensaje;
    }

    private String getFacturaPlebes() {
        String mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">\n"
                + "    <div style=\"max-width:600px;margin:0 auto\">\n"
                + "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">\n"
                + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">\n"
                + "                <p style=\"margin:0;color:#333333\">\n"
                + "                <div align=\"center\">\n"
                + " <img src=\"data:image/png;base64,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\" width=\"120\">"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div style=\"padding:20px 30px\">\n"
                + "                <h3> Buen día. </h3>\n"
                // + "                <h3>Folio:&nbsp;&nbsp;" + folio + "</h3>\n"
                + "                <br>"
                + "                <p>\n"
                //+ "                <h4>" + tipoProceso + ", se ha generado de forma exitosa.</h4>\n "
                + "                 <br>"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "<div style=\"max-width:600px;margin:0 auto\">\n"
                + "    <div style=\"font:11px sans-serif;color:#686f7a\">\n"
                + "        <p style=\"font-size:11px;color:#686f7a\">\n"
                + "        <center>\n"
                + "            Este es un mensaje informativo, favor de no contestar a este correo.\n"
                + "        </center>\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</div>\n"
                + "</body>";
        return mensaje;
    }

    private String getSupportClient(String fechaEmison, String num_ticket, String rfc, String codigo_postal, String razon_social, String correoCliente, String usocfdi_id, String regimen_id, String forma_id, String comentarios) {
        String mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">\n"
                + "    <div style=\"max-width:600px;margin:0 auto\">\n"
                + "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">\n"
                + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">\n"
                + "                <p style=\"margin:0;color:#333333\">\n"
                + "                <div align=\"center\">\n"
                + "                 <h2>RESTAURANTE LOS PLEBES</h2>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div style=\"padding:20px 30px\">\n"
                + "                 <h3>Información del cliente, para emisón de la factura electrónica: </h3>\n"
                + "                 <br>"
                + "                 <h4>Fecha Solicitud: " + fechaEmison + "</h4>\n "
                + "                 <h4>Número de Ticket: " + num_ticket + "</h4>\n "
                + "                 <h4>RFC: " + rfc + "</h4>\n "
                + "                 <h4>Código Postal: " + codigo_postal + "</h4>\n "
                + "                 <h4>Razón Social: " + razon_social + "</h4>\n "
                + "                 <h4>Correo del Cliente: " + correoCliente + "</h4>\n "
                + "                 <h4>Uso de cfdi: " + usocfdi_id + "</h4>\n "
                + "                 <h4>Régimen Fiscal: " + regimen_id + "</h4>\n "
                + "                 <h4>Forma de Pago: " + forma_id + "</h4>\n "
                + "                 <h4>Comentarios: " + comentarios + "</h4>\n "
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "<div style=\"max-width:600px;margin:0 auto\">\n"
                + "    <div style=\"font:11px sans-serif;color:#686f7a\">\n"
                + "        <p style=\"font-size:11px;color:#686f7a\">\n"
                + "        <center>\n"
                + "            Este es un mensaje informativo, favor de no contestar a este correo.\n"
                + "        </center>\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</div>\n"
                + "</body>";
        return mensaje;
    }

    private String getGTN() {
        String mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">\n"
                + "    <div style=\"max-width:600px;margin:0 auto\">\n"
                + "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">\n"
                + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">\n"
                + "                <p style=\"margin:0;color:#333333\">\n"
                + "                <div align=\"center\">\n"
                + "                 <h2>VF INBOUND</h2>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div style=\"padding:20px 30px\">\n"
                //+ "                 <h3>Información del cliente, para emisón de la factura electrónica: </h3>\n"
                + "                 <br>"
                + "                 <h4>Brand-Division: OD TIMBERLAND</h4>\n "
                + "                 <h4>Shipment ID: 5011885374</h4>\n "
                + "                 <h4>Container: TCKU7711790</h4>\n "
                + "                 <h4>BL/AWB/PRO: MAEU222517139</h4>\n "
                + "                 <h4>Load Type: FCL</h4>\n "
                + "                 <h4>Container Type: 40' High Cube Dry</h4>\n "
                + "                 <h4># of Packages: 149</h4>\n "
                + "                 <h4>Quantity: 860</h4>\n "
                + "                 <h4>Mode: Ocean</h4>\n "
                + "                 <h4>Final Destination (Shipment): VF OUTDOOR MEXICO S.de R.L.de.</h4>\n "
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "<div style=\"max-width:600px;margin:0 auto\">\n"
                + "    <div style=\"font:11px sans-serif;color:#686f7a\">\n"
                + "        <p style=\"font-size:11px;color:#686f7a\">\n"
                + "        <center>\n"
                + "            Este es un mensaje informativo, favor de no contestar a este correo.\n"
                + "        </center>\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</div>\n"
                + "</body>";
        return mensaje;
    }

    private String getModificarEventos(String agenteId) {

        ServiceDAO dao = new ServiceDAO();
        String mensaje = "";
        ConsultasQuery fac = new ConsultasQuery();

        try {

            Statement stmt = dao.conectar().prepareStatement(fac.consultarEventosDetalleAgenteAduanal(agenteId));
            ResultSet rsc = stmt.executeQuery(fac.consultarEventosDetalleAgenteAduanal(agenteId));

            /*mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">\n"
                        + "    <div style=\"max-width:600px;margin:0 auto\">\n"
                        //+ "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">\n"
                        + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">\n"
                        + "                <p style=\"margin:0;color:#333333\">\n"
                        + "                <div align=\"center\">\n"
                        + "                 <h2>Modificación de Eventos Pruebas</h2>\n"
                        + "                 <h2>Favor de hacer caso omiso a este correo.</h2>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        //+ "            <div style=\"padding:20px 30px\">\n"
                        + "                 <br>"
                        + "<table id=\"main-table\" class=\"main-table\" style=\"table-layout:fixed; width:200%;\">\n"
                        + "    <thead>\n"
                        + "        <tr>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\">Número de evento <strong style=\"color:red\">*</strong></th>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\">Responsable <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Final Destination (Shipment) <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Brand-Division <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Division <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Shipment ID <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Container <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">BL/ AWB/ PRO <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Load Type <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Quantity <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">POD /&nbsp; <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Est. Departure from POL <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">ETA REAL PORT <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\" style=\"background-color:#C65911\">Est. Eta DC <strong style=\"color:white\">*</strong></th>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\" style=\"background-color:#C65911\">ETA DC  </th>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\" style=\"background-color:#C65911\">DC </th>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\">Inbound notification <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">POL <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">A.A. <strong style=\"color:red\">*</strong></th>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\">Observaciones </th>\n"
                        + "        </tr>\n"
                        + "    </thead>\n"
                        + "    <tbody>\n";*/
            while (rsc.next()) {

                /*mensaje += "        <tr>\n"
                        + "            <th class=\"font-numero\" style=\"cursor: pointer\" onclick=\"editarEvento('2023028')\">" + rsc.getInt(1) + "</th>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(2) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(3) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(4) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(5) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(6) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(7) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(8) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(9) + "</td>\n"		
                        + "            <td class=\"font-texto\">" + rsc.getString(10) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(11) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(12) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(13) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(14) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(15) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(16) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(17) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(18) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(19) + "</td>\n"
                        + "        </tr>\n";*/
            }

            rsc.close();

            /*mensaje += "    </tbody>\n"
                        + "</table>\n"
                        + "    </div>\n"
                        + "<div style=\"max-width:600px;margin:0 auto\">\n"
                        + "    <div style=\"font:11px sans-serif;color:#686f7a\">\n"
                        + "        <p style=\"font-size:11px;color:#686f7a\">\n"
                        + "        <center>\n"
                        + "            Este es un mensaje informativo, favor de no contestar a este correo.\n"
                        + "        </center>\n"
                        + "        </p>\n"
                        + "    </div>\n"
                        + "</div>\n"
                        + "</body>";*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mensaje;
    }

    private String getSemaforoCustoms(String agenteId) {

        ServiceDAO dao = new ServiceDAO();
        String mensaje = "";
        ConsultasQuery fac = new ConsultasQuery();

        try {

            Statement stmt = dao.conectar().prepareStatement(fac.consultarEventosDetalleAgenteAduanal(agenteId));
            ResultSet rsc = stmt.executeQuery(fac.consultarEventosDetalleAgenteAduanal(agenteId));

            /*mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">\n"
                        + "    <div style=\"max-width:600px;margin:0 auto\">\n"
                        //+ "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">\n"
                        + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">\n"
                        + "                <p style=\"margin:0;color:#333333\">\n"
                        + "                <div align=\"center\">\n"
                        + "                 <h2>Modificación de Eventos Pruebas</h2>\n"
                        + "                 <h2>Favor de hacer caso omiso a este correo.</h2>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        //+ "            <div style=\"padding:20px 30px\">\n"
                        + "                 <br>"
                        + "<table id=\"main-table\" class=\"main-table\" style=\"table-layout:fixed; width:200%;\">\n"
                        + "    <thead>\n"
                        + "        <tr>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\">Número de evento <strong style=\"color:red\">*</strong></th>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\">Responsable <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Final Destination (Shipment) <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Brand-Division <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Division <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Shipment ID <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Container <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">BL/ AWB/ PRO <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Load Type <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Quantity <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">POD /&nbsp; <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">Est. Departure from POL <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">ETA REAL PORT <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\" style=\"background-color:#C65911\">Est. Eta DC <strong style=\"color:white\">*</strong></th>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\" style=\"background-color:#C65911\">ETA DC  </th>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\" style=\"background-color:#C65911\">DC </th>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\">Inbound notification <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">POL <strong style=\"color:red\">*</strong></th>\n"	
                        + "            <th scope=\"col\" class=\"font-titulo\">A.A. <strong style=\"color:red\">*</strong></th>\n"
                        + "            <th scope=\"col\" class=\"font-titulo\">Observaciones </th>\n"
                        + "        </tr>\n"
                        + "    </thead>\n"
                        + "    <tbody>\n";*/
            while (rsc.next()) {

                /*mensaje += "        <tr>\n"
                        + "            <th class=\"font-numero\" style=\"cursor: pointer\" onclick=\"editarEvento('2023028')\">" + rsc.getInt(1) + "</th>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(2) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(3) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(4) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(5) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(6) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(7) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(8) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(9) + "</td>\n"		
                        + "            <td class=\"font-texto\">" + rsc.getString(10) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(11) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(12) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(13) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(14) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(15) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(16) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(17) + "</td>\n"
                        + "            <td class=\"font-texto\">" + rsc.getString(18) + "</td>\n"	
                        + "            <td class=\"font-texto\">" + rsc.getString(19) + "</td>\n"
                        + "        </tr>\n";*/
            }

            rsc.close();

            /*mensaje += "    </tbody>\n"
                        + "</table>\n"
                        + "    </div>\n"
                        + "<div style=\"max-width:600px;margin:0 auto\">\n"
                        + "    <div style=\"font:11px sans-serif;color:#686f7a\">\n"
                        + "        <p style=\"font-size:11px;color:#686f7a\">\n"
                        + "        <center>\n"
                        + "            Este es un mensaje informativo, favor de no contestar a este correo.\n"
                        + "        </center>\n"
                        + "        </p>\n"
                        + "    </div>\n"
                        + "</div>\n"
                        + "</body>";*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mensaje;
    }
    
    public Email() {

        properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.user", REMITENTE);
        properties.put("mail.smtp.password", CLAVE);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
    }

    public boolean alertaFactura(String fileName, String correos, String ruta, String nameEmail) throws SQLException {

        boolean enviado = false;
        String[] vect;
        vect = correos.split("/");

        try {
            Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("alertas@tacts.mx", "Alertas2017");
                }
            });
            // Session sesion = Session.getDefaultInstance(properties, null);
            MimeMessage message = new MimeMessage(session);

            for (int i = 0; i < vect.length; i++) {

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));

            }

            message.setFrom(new InternetAddress(REMITENTE));
            message.setHeader("X-Priority", "1");
            message.setSubject("Emisión de Factura " + nameEmail);
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent(getFactura(), "text/html");

            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            //Part two is attachment
            messageBodyPart = new MimeBodyPart();

            messageBodyPart.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            messageBodyPart.setFileName(fileName);

            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(HOST, REMITENTE, CLAVE);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Se envío correo");
            enviado = true;

        } catch (MessagingException e) {

            e.printStackTrace();

        }
        return enviado;
    }

    public boolean alertaFacturaCancelacion(String fileName, String correos, String ruta, String nameEmail) throws SQLException {

        boolean enviado = false;
        String[] vect;
        vect = correos.split("/");

        try {
            Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("alertas@tacts.mx", "Alertas2017");
                }
            });
            // Session sesion = Session.getDefaultInstance(properties, null);
            MimeMessage message = new MimeMessage(session);

            for (int i = 0; i < vect.length; i++) {

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));

            }

            message.setFrom(new InternetAddress(REMITENTE));
            message.setHeader("X-Priority", "1");
            message.setSubject("Cancelación de Factura " + nameEmail);
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent(getFacturaCancelacion(nameEmail), "text/html");

            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            //Part two is attachment
            messageBodyPart = new MimeBodyPart();

            messageBodyPart.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            messageBodyPart.setFileName(fileName);

            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(HOST, REMITENTE, CLAVE);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Se envío correo");
            enviado = true;

        } catch (MessagingException e) {

            e.printStackTrace();

        }
        return enviado;
    }

    public boolean alertaFacturaPlebes(String fileName, String correos, String ruta, String nameEmail) throws SQLException {

        boolean enviado = false;
        String[] vect;
        vect = correos.split("/");

        try {
            Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("alertas@tacts.mx", "Alertas2017");
                }
            });
            // Session sesion = Session.getDefaultInstance(properties, null);
            MimeMessage message = new MimeMessage(session);

            for (int i = 0; i < vect.length; i++) {

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));

            }

            message.setFrom(new InternetAddress(REMITENTE));
            message.setHeader("X-Priority", "1");
            message.setSubject("Emisión de Factura " + nameEmail);
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent(getFacturaPlebes(), "text/html");

            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            //Part two is attachment
            messageBodyPart = new MimeBodyPart();

            messageBodyPart.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            messageBodyPart.setFileName(fileName);

            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(HOST, REMITENTE, CLAVE);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Se envío correo");
            enviado = true;

        } catch (MessagingException e) {

            e.printStackTrace();

        }
        return enviado;

    }

    public boolean alertaSupport(String fechaEmison, String correosInternos, String num_ticket, String rfc, String codigo_postal, String razon_social, String correoCliente, String usocfdi_id, String regimen_id, String forma_id, String comentarios) {

        boolean enviado = false;
        String[] vect;
        vect = correosInternos.split("/");

        Session sesion = null;

        try {
            sesion = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("alertas@tacts.mx", "Alertas2017");
                }
            });

            MimeMessage message = new MimeMessage(sesion);

            for (int i = 0; i < vect.length; i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));
            }

            message.setFrom(new InternetAddress(REMITENTE));
            message.setSubject("Soporte - Facturación Electrónica: " + razon_social);
            message.setContent(getSupportClient(fechaEmison, num_ticket, rfc, codigo_postal, razon_social, correoCliente, usocfdi_id, regimen_id, forma_id, comentarios), "text/html");

            Transport transport = sesion.getTransport("smtp");
            transport.connect(HOST, REMITENTE, CLAVE);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            enviado = true;

        } catch (MessagingException e) {

            e.printStackTrace();

        }
        return enviado;

    }

    public boolean alertaGTN(String correosInternos) {

        boolean enviado = false;
        String[] vect;
        vect = correosInternos.split("/");

        Session sesion = null;

        try {
            sesion = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("alertas1@tacts.mx", "Tacts23*");
                }
            });

            MimeMessage message = new MimeMessage(sesion);

            for (int i = 0; i < vect.length; i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));
            }

            message.setFrom(new InternetAddress(REMITENTE));
            message.setSubject("Informe - Actualización GTN");
            message.setContent(getGTN(), "text/html");

            Transport transport = sesion.getTransport("smtp");
            transport.connect(HOST, REMITENTE, CLAVE);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            enviado = true;

        } catch (MessagingException e) {

            e.printStackTrace();

        }
        return enviado;

    }

    public boolean alertaModificarEventos(String correosInternos, String fichero, String agenteId) throws SQLException {

        boolean enviado = false;
        String[] vect;
        vect = correosInternos.split("/");

        try {
            Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("alertas1@tacts.mx", "Tacts23*");
                }
            });
            // Session sesion = Session.getDefaultInstance(properties, null);
            MimeMessage message = new MimeMessage(session);

            for (int i = 0; i < vect.length; i++) {

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));

            }

            message.setFrom(new InternetAddress(REMITENTE));
            message.setHeader("X-Priority", "1");
            message.setSubject("Informe - Modificación de Eventos Nuevos PRUEBAS");
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent(getModificarEventos(agenteId), "text/html");

            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            //Part two is attachment
            messageBodyPart = new MimeBodyPart();

            messageBodyPart.setDataHandler(new DataHandler(new FileDataSource(fichero)));
            messageBodyPart.setFileName("ModifaciónDeEventos" + agenteId + ".xls");

            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(HOST, REMITENTE, CLAVE);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Se envío correo");
            enviado = true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return enviado;

    }

    public boolean alertaSemaforoCustoms(String correosInternos, String fichero, String agenteId, String tipoCorreo) throws SQLException {

        boolean enviado = false;
        String[] vect;
        vect = correosInternos.split("/");

        try {
            Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("alertas1@tacts.mx", "Tacts23*");
                }
            });
            // Session sesion = Session.getDefaultInstance(properties, null);
            MimeMessage message = new MimeMessage(session);

            for (int i = 0; i < vect.length; i++) {

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));

            }

            message.setFrom(new InternetAddress(REMITENTE));
            message.setHeader("X-Priority", "1");
            message.setSubject("Detalle Eventos " + tipoCorreo + "");
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent(getSemaforoCustoms(agenteId), "text/html");

            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            //Part two is attachment
            messageBodyPart = new MimeBodyPart();

            messageBodyPart.setDataHandler(new DataHandler(new FileDataSource(fichero)));
            messageBodyPart.setFileName("Detalle Eventos " + tipoCorreo + ".xls");

            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(HOST, REMITENTE, CLAVE);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Se envío correo");
            enviado = true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return enviado;

    }
   
    private String getPlaneacion(String idLTransporte, String nameLTransporte, String urlT) {
        String mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">\n"
                + "    <div style=\"max-width:600px;margin:0 auto\">\n"
                + "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">\n"
                + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">\n"
                + "                <p style=\"margin:0;color:#333333\">\n"
                + "                <div align=\"center\">\n"
                + " <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Vf-corp-logo.svg/1920px-Vf-corp-logo.svg.png\" width=\"120\">"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div style=\"padding:20px 30px\">\n"
                + "                <h3> Buen día. </h3>\n"
                + "                <h3>Estimado:&nbsp;&nbsp;" + nameLTransporte + "</h3>\n"
                + "                <br>"
                + "                <p>\n"
                + "                <h4>La asignación de embarque, se ha generado de forma exitosa.</h4>\n "
                + "                <h4>Para mayor infomación puede consultarlo en la siguienta liga: https://www.rtms.mx" + urlT + "/detalleTransportista.jsp?transporte=" + idLTransporte + "</h4>\n "
                + "                 <br>"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "<div style=\"max-width:600px;margin:0 auto\">\n"
                + "    <div style=\"font:11px sans-serif;color:#686f7a\">\n"
                + "        <p style=\"font-size:11px;color:#686f7a\">\n"
                + "        <center>\n"
                + "            Este es un mensaje informativo, favor de no contestar a este correo.\n"
                + "        </center>\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</div>\n"
                + "</body>";
        return mensaje;
    }

    public boolean alertaLiberacionV2(byte[] data, String embarque_id, String idLTransporte, String nameLTransporte, String urlT) throws SQLException {

        String emailenvio = "";
        ServiceDAO dao = new ServiceDAO();

        String Sql = "SELECT DISTINCT "
                + " TC.CORREO FROM tra_inb_linea_transporte OLT "
                + " INNER JOIN tra_inb_CORREO TC ON  OLT.LTRANSPORTE_NOMBRE = TC.NOMBRE_CORREO "
                + " WHERE OLT.LTRANSPORTE_ID = '" + idLTransporte + "' ";
        try {

            ResultSet rs = dao.consulta(Sql);
            while (rs.next()) {
                emailenvio += rs.getString(1) + "/";
            }
            emailenvio += "grecendiz@tacts.mx";

        } catch (SQLException e) {
            return false;
        } finally {

            boolean enviado = false;
            String[] vect;
            vect = emailenvio.split("/");

            try {
                Session session = Session.getDefaultInstance(properties,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("alertas1@tacts.mx", "Tacts23*");
                    }
                });
                // Session sesion = Session.getDefaultInstance(properties, null);
                MimeMessage message = new MimeMessage(session);

                for (int i = 0; i < vect.length; i++) {

                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));

                }

                message.setFrom(new InternetAddress(REMITENTE));
                message.setHeader("X-Priority", "1");
                message.setSubject("Aviso de Asignación de Embarque");
                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setContent(getPlaneacion(idLTransporte, nameLTransporte, urlT), "text/html");

                Multipart multipart = new MimeMultipart();

                // Set text message part
                multipart.addBodyPart(messageBodyPart);

                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
                DataSource source = new ByteArrayDataSource(data, "application/pdf");
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(embarque_id + ".pdf");
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);

                Transport transport = session.getTransport("smtp");
                transport.connect(HOST, REMITENTE, CLAVE);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                System.out.println("Se envío correo");
                enviado = true;

            } catch (MessagingException e) {

                e.printStackTrace();

            }
            return enviado;
        }
    }

    private String getPlaneacionCus(String idLTransporte, String nameLTransporte, String urlT) {
        String mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">\n"
                + "    <div style=\"max-width:600px;margin:0 auto\">\n"
                + "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">\n"
                + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">\n"
                + "                <p style=\"margin:0;color:#333333\">\n"
                + "                <div align=\"center\">\n"
                + " <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Vf-corp-logo.svg/1920px-Vf-corp-logo.svg.png\" width=\"120\">"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div style=\"padding:20px 30px\">\n"
                + "                <h3> Buen día. </h3>\n"
                + "                <h3> &nbsp;&nbsp;" + nameLTransporte + "</h3>\n"
                + "                <br>"
                + "                <p>\n"
                + "                <h4>La asignación de embarque, se ha generado de forma exitosa.</h4>\n "
                + "                <h4>Para mayor infomación puede consultarlo en la siguienta liga: https://www.rtms.mx" + urlT + "/detalleCustodia.jsp?transporte=" + idLTransporte + "</h4>\n "
                + "                 <br>"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "<div style=\"max-width:600px;margin:0 auto\">\n"
                + "    <div style=\"font:11px sans-serif;color:#686f7a\">\n"
                + "        <p style=\"font-size:11px;color:#686f7a\">\n"
                + "        <center>\n"
                + "            Este es un mensaje informativo, favor de no contestar a este correo.\n"
                + "        </center>\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</div>\n"
                + "</body>";
        return mensaje;
    }

    public boolean alertaLiberacionV2Cus(byte[] data, String embarque_id, String idLTransporte, String nameLTransporte, String urlT) throws SQLException {

        String emailenvio = "";
        ServiceDAO dao = new ServiceDAO();

        String Sql = "SELECT DISTINCT "
                + " TC.CORREO FROM tra_inb_tipo_custodia OLT "
                + " INNER JOIN tra_inb_CORREO TC ON  OLT.TC_DESCRIPCION = TC.NOMBRE_CORREO "
                + " WHERE OLT.ID_TC = '" + idLTransporte + "' and ID_TIPO_CORREO=2";
        try {

            ResultSet rs = dao.consulta(Sql);
            while (rs.next()) {
                emailenvio += rs.getString(1) + "/";
            }
            emailenvio += "grecendiz@tacts.mx";

        } catch (SQLException e) {
            return false;
        } finally {

            boolean enviado = false;
            String[] vect;
            vect = emailenvio.split("/");

            try {
                Session session = Session.getDefaultInstance(properties,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("alertas1@tacts.mx", "Tacts23*");
                    }
                });
                // Session sesion = Session.getDefaultInstance(properties, null);
                MimeMessage message = new MimeMessage(session);

                for (int i = 0; i < vect.length; i++) {

                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));

                }

                message.setFrom(new InternetAddress(REMITENTE));
                message.setHeader("X-Priority", "1");
                message.setSubject("Aviso de Asignación de Embarque");
                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setContent(getPlaneacionCus(idLTransporte, nameLTransporte, urlT), "text/html");

                Multipart multipart = new MimeMultipart();

                // Set text message part
                multipart.addBodyPart(messageBodyPart);

                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
                DataSource source = new ByteArrayDataSource(data, "application/pdf");
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(embarque_id + ".pdf");
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);

                Transport transport = session.getTransport("smtp");
                transport.connect(HOST, REMITENTE, CLAVE);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                System.out.println("Se envío correo");
                enviado = true;

            } catch (MessagingException e) {

                e.printStackTrace();

            }
            return enviado;
        }
    }

    private String getConfirmacionTransporte(String agrupador, String nameLTransporte) {
        String mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">\n"
                + "    <div style=\"max-width:600px;margin:0 auto\">\n"
                + "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">\n"
                + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">\n"
                + "                <p style=\"margin:0;color:#333333\">\n"
                + "                <div align=\"center\">\n"
                + " <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Vf-corp-logo.svg/1920px-Vf-corp-logo.svg.png\" width=\"120\">"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div style=\"padding:20px 30px\">\n"
                + "                <h3> Buen día. </h3>\n"
                + "                <h3>  </h3>\n"
                + "                <br>"
                + "                <p>\n"
                + "                <h4>Le informamos que el embarque " + agrupador + ", fue confirmado por el transportista.</h4>\n "
                + "                 <br>"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "<div style=\"max-width:600px;margin:0 auto\">\n"
                + "    <div style=\"font:11px sans-serif;color:#686f7a\">\n"
                + "        <p style=\"font-size:11px;color:#686f7a\">\n"
                + "        <center>\n"
                + "            Este es un mensaje informativo, favor de no contestar a este correo.\n"
                + "        </center>\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</div>\n"
                + "</body>";
        return mensaje;
    }

    public boolean alertaConfirmacionTransporte(String agrupador, String nameLTransporte) throws SQLException {

        String emailenvio = "";
        GenericJdbc generico = new GenericJdbc();
        generico.openConection();
        Statement statement = generico.getConnection().createStatement();
        int sal = 0;

        String Sql = " select DISTINCT CORREO from tra_inb_CORREO where ID_TIPO_CORREO=3 and ESTADO_CORREO=1 ";
        ResultSet rs = statement.executeQuery(Sql);
        try {

            while (rs.next()) {
                emailenvio += rs.getString(1) + "/";
                sal++;
            }
            emailenvio += "grecendiz@tacts.mx";

        } catch (SQLException e) {
            return false;
        } finally {

            boolean enviado = false;
            String[] vect;
            vect = emailenvio.split("/");

            try {
                Session session = Session.getDefaultInstance(properties,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("alertas1@tacts.mx", "Tacts23*");
                    }
                });
                // Session sesion = Session.getDefaultInstance(properties, null);
                MimeMessage message = new MimeMessage(session);

                for (int i = 0; i < vect.length; i++) {

                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));

                }

                message.setFrom(new InternetAddress(REMITENTE));
                message.setHeader("X-Priority", "1");
                message.setSubject("Confirmacion transportista");
                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setContent(getConfirmacionTransporte(agrupador, nameLTransporte), "text/html");

                Multipart multipart = new MimeMultipart();

                multipart.addBodyPart(messageBodyPart);

                // Establecer el contenido del mensaje
                message.setContent(multipart);

                // Enviar el mensaje
                Transport transport = session.getTransport("smtp");
                transport.connect(HOST, REMITENTE, CLAVE);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                System.out.println("Se envío correo");
                enviado = true;

                statement.close();
                rs.close();
                generico.closeConnection();

            } catch (MessagingException e) {

                e.printStackTrace();
                statement.close();
                rs.close();
                generico.closeConnection();

            }
            return enviado;
        }
    }

    private String getConfirmacionCustodia(String agrupador, String nameLTransporte) {
        String mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">\n"
                + "    <div style=\"max-width:600px;margin:0 auto\">\n"
                + "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">\n"
                + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">\n"
                + "                <p style=\"margin:0;color:#333333\">\n"
                + "                <div align=\"center\">\n"
                + " <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Vf-corp-logo.svg/1920px-Vf-corp-logo.svg.png\" width=\"120\">"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div style=\"padding:20px 30px\">\n"
                + "                <h3> Buen día. </h3>\n"
                + "                <h3>  </h3>\n"
                + "                <br>"
                + "                <p>\n"
                + "                <h4>Le informamos que el embarque " + agrupador + ", fue confirmado por el Custodio.</h4>\n "
                + "                 <br>"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "<div style=\"max-width:600px;margin:0 auto\">\n"
                + "    <div style=\"font:11px sans-serif;color:#686f7a\">\n"
                + "        <p style=\"font-size:11px;color:#686f7a\">\n"
                + "        <center>\n"
                + "            Este es un mensaje informativo, favor de no contestar a este correo.\n"
                + "        </center>\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</div>\n"
                + "</body>";
        return mensaje;
    }

    public boolean alertaConfirmacionCustodia(String agrupador, String nameLTransporte) throws SQLException {

        String emailenvio = "";
        GenericJdbc generico = new GenericJdbc();
        generico.openConection();
        Statement statement = generico.getConnection().createStatement();

        String Sql = " select DISTINCT CORREO from tra_inb_CORREO where ID_TIPO_CORREO=3 and ESTADO_CORREO=1 ";
        ResultSet rs = statement.executeQuery(Sql);
        try {

            while (rs.next()) {
                emailenvio += rs.getString(1) + "/";

            }
            emailenvio += "grecendiz@tacts.mx";

        } catch (SQLException e) {
            return false;
        } finally {

            boolean enviado = false;
            String[] vect;
            vect = emailenvio.split("/");

            try {
                Session session = Session.getDefaultInstance(properties,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("alertas1@tacts.mx", "Tacts23*");
                    }
                });
                // Session sesion = Session.getDefaultInstance(properties, null);
                MimeMessage message = new MimeMessage(session);

                for (int i = 0; i < vect.length; i++) {

                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));

                }

                message.setFrom(new InternetAddress(REMITENTE));
                message.setHeader("X-Priority", "1");
                message.setSubject("Confirmacion custodia");
                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setContent(getConfirmacionCustodia(agrupador, nameLTransporte), "text/html");

                Multipart multipart = new MimeMultipart();

                multipart.addBodyPart(messageBodyPart);

                // Establecer el contenido del mensaje
                message.setContent(multipart);

                // Enviar el mensaje
                Transport transport = session.getTransport("smtp");
                transport.connect(HOST, REMITENTE, CLAVE);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                System.out.println("Se envío correo");
                enviado = true;

                statement.close();
                rs.close();
                generico.closeConnection();

            } catch (MessagingException e) {

                e.printStackTrace();
                statement.close();
                rs.close();
                generico.closeConnection();

            }
            return enviado;
        }
    }

}
