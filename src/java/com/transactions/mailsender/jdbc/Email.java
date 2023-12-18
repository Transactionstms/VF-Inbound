/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transactions.mailsender.jdbc;

/**
 *
 * @author grecendiz
 */
import com.dao.ServiceDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
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

/**
 *
 * @author sergiocastrosauceda
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
    
    private String getMensaje(String aNombreCliente){
        String mensaje = "<body style=\"margin:0px; background: #f8f8f8;\"> "
            + "<div width=\"100%\" style=\"background: #f8f8f8; padding: 0px 0px; font-family:arial; line-height:28px; height:100%;  width: 100%; color: #514d6a;\"/> "
            + "  <div style=\"max-width: 700px; padding:50px 0;  margin: 0px auto; font-size: 14px\"> "
            + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%; margin-bottom: 20px\"> "
            + "      <tbody> "
            + "        <tr> "
            + "         <td style=\"vertical-align: top; padding-bottom:30px;\" align=\"center\">"
            + "         <a href=\"https://www.hilti.com.mx/\" target=\"_blank\"> "
            + "            <img src=\"http://hiltitms.com/MX/imgClients/logoHILTI.png\" alt=\"logo\" width=\"300\" style=\"border:none\"></a> </td> "
            + "        </tr> "
            + "      </tbody> "
            + "   </table> "
            + "    <div style=\"padding: 40px; background: #fff;\"> "
            + "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\"> "
            + "        <tbody> "
            + "          <tr> "
            + "            <td><b>Estimado cliente " + aNombreCliente +",</b> "
            + "              <p>Le informamos que su pedido ha sido entregado correctamente.</p> "
            + "              <p>Si desea tener mas detalles de su pedido por favor de click en el botón siguiente y sera llevado a Pedidos Hilti</p> "
            + "             <a href=\"http://hiltitms.com/MX/HiltiOnline\" style=\"display: inline-block; padding: 11px 30px; margin: 20px 0px 30px; font-size: 15px; color: #fff; background: #00c0c8; border-radius: 60px; text-decoration:none;\">Detalle en HOL </a> "
            + "              <br><br><b>- Gracias (Hilti)</b> </td> "
            + "          </tr> "
            + "        </tbody> "
            + "      </table> "
            + "    </div> "
            + "    <div style=\"text-align: center; font-size: 12px; color: #b2b2b5; margin-top: 20px\"> "
            + "      <p> TransactionsTMS <br> "
            + "        <a href=\"javascript: void(0);\" style=\"color: #b2b2b5; text-decoration: underline;\">Unsubscribe</a> </p> "
            + "     </div> "
            + "    </div> "
            + "   </div> "
            + "  </body> "
            + " </html>";
        return mensaje;
    }
    
    private String getMensajeDocumentosCargados(){
        String mensaje = "<body style=\"margin:0px; background: #f8f8f8;\"> "
            + "<div width=\"100%\" style=\"background: #f8f8f8; padding: 0px 0px; font-family:arial; line-height:28px; height:100%;  width: 100%; color: #514d6a;\"/> "
            + "  <div style=\"max-width: 700px; padding:50px 0;  margin: 0px auto; font-size: 14px\"> "
            + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%; margin-bottom: 20px\"> "
            + "      <tbody> "
            + "        <tr> "
            + "         <td style=\"vertical-align: top; padding-bottom:30px;\" align=\"center\">"
            + "         <a href=\"https://www.hilti.com.mx/\" target=\"_blank\"> "
            + "            <img src=\"http://hiltitms.com/MX/imgClients/logoHILTI.png\" alt=\"logo\" width=\"300\" style=\"border:none\"></a> </td> "
            + "        </tr> "
            + "      </tbody> "
            + "   </table> "
            + "    <div style=\"padding: 40px; background: #fff;\"> "
            + "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\"> "
            + "        <tbody> "
            + "          <tr> "
            + "            <td><b>HOLA </b> "
            + "              <p>Le informamos ALGO!</p> "
                         
            + "              <br><br><b>- Gracias  ::::ddddv</b> </td> "
            + "          </tr> "
            + "        </tbody> "
            + "      </table> "
            + "    </div> "
            + "    <div style=\"text-align: center; font-size: 12px; color: #b2b2b5; margin-top: 20px\"> "
            + "      <p> TransactionsTMS <br> "
            + "        <a href=\"javascript: void(0);\" style=\"color: #b2b2b5; text-decoration: underline;\">Unsubscribe</a> </p> "
            + "     </div> "
            + "    </div> "
            + "   </div> "
            + "  </body> "
            + " </html>";
        return mensaje;
    }
 
    private String getPlaneacion(String idLTransporte, String nameLTransporte){
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
                        + "                <h4>Para mayor infomación puede consultarlo en la siguienta liga: https://www.tacts.mx/INBOUND7/detalleTransportista.jsp?transporte=" + idLTransporte + "</h4>\n "
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
    
    private String getShipments(String idLTransporte, String nameLTransporte){
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
                        + "                <h4>La autorización de pago, se ha generado de forma exitosa.</h4>\n "
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
    
    public Email() {
        
        properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.user", REMITENTE);
        properties.put("mail.smtp.password", CLAVE);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth","true");
    }

    public boolean enviarCorreo(String para, String nombreCliente) {
        boolean enviado = false;

        try {
            
            Session sesion = Session.getDefaultInstance(properties,null);
            MimeMessage message = new MimeMessage(sesion);
            
            message.setFrom(new InternetAddress(REMITENTE));
            
            /*
            Nota para enviar correo electronico Masivo
            
            InternetAddress[] direcciones = new InternetAddress[para.length];
            
                 for (i=0; i< direcciones.length;i++){
                
                 message.addRecipient(Message.RecipientType.TO, direciones[i])
                 
                }
            
            */
            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress (para));
            
            message.setSubject(asunto);
            message.setContent(getMensaje(nombreCliente),"text/html");
            
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
    
    public boolean enviarCorreoAlerta(String mensaje, String correotransporte,String subtitulo ) {


  
         boolean enviado = false;
        String[] vect;
        vect = correotransporte.split("/");

       
             Session sesion=null;
        
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
          message.setSubject(subtitulo);
          message.setContent(mensaje, "text/html");

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
      
    public boolean enviacorreoCapturaDocumento(String mensaje, String correo,String subtitulo ) {


  
         boolean enviado = false;
        String[] vect;
        vect = correo.split("/");

       
             Session sesion=null;
        
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
          message.setSubject(subtitulo);
          message.setContent(mensaje, "text/html");

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
           
    public boolean enviocorreoEditarGastos(String mensaje, String correo,String subtitulo ) {


  
         boolean enviado = false;
        String[] vect;
        vect = correo.split("/");

       
             Session sesion=null;
        
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
          message.setSubject(subtitulo);
          message.setContent(mensaje, "text/html");

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
    
    public boolean alertaLiberacionV2(byte[] data, String embarque_id, String idLTransporte, String nameLTransporte) throws SQLException {
        
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
                emailenvio += "grecendiz@tacts.mx/oamorales@tacts.mx";
                 
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
                messageBodyPart.setContent(getPlaneacion(idLTransporte, nameLTransporte),"text/html");

                Multipart multipart = new MimeMultipart();

                // Set text message part
                 multipart.addBodyPart(messageBodyPart);

                // Part two is attachment
             //   messageBodyPart = new MimeBodyPart();
              //  DataSource source = new ByteArrayDataSource(data, "application/pdf");
              //  messageBodyPart.setDataHandler(new DataHandler(source));
              //  messageBodyPart.setFileName(embarque_id + ".pdf");
             //   multipart.addBodyPart(messageBodyPart);
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
    
    public boolean alertaLiberacionPaqueteria(String idLTransporte, String nameLTransporte, String agrupador) {
        
        ServiceDAO dao = new ServiceDAO();
        
        String emailenvio = "";
        String nameTransporte = "";
        
        String Sql = "SELECT DISTINCT TC.CORREO FROM ONTMS_LINEA_TRANSPORTE OLT INNER JOIN TRA_CORREO TC ON  OLT.LTRANSPORTE_NOMBRE = TC.NOMBRE_CORREO WHERE OLT.LTRANSPORTE_ID = '" + idLTransporte + "' "; 
        try {
            
            ResultSet rs = dao.consulta(Sql);
            while (rs.next()) {
                emailenvio += rs.getString(1) + "/"; 
            }
                emailenvio += "Maria_Gonzalez4@vfc.com/Luis_Alba@vfc.com/Jorge_Venegas@vfc.com/Julissa_Clemente@vfc.com/oamorales@tacts.mx";
                 
        } catch (SQLException e) {
            return false;
        }

        
        String Sql2 = "SELECT DISTINCT LTRANSPORTE_NOMBRE FROM ONTMS_LINEA_TRANSPORTE WHERE LTRANSPORTE_ID = '" + idLTransporte + "' "; 
        try {
            ResultSet rs = dao.consulta(Sql2);
            while (rs.next()) {
                nameTransporte = rs.getString(1); 
            }
                 
        } catch (SQLException e) {
            return false;
        }
        
        boolean enviado = false;
        String[] vect;
        vect = emailenvio.split("/");

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
            
            String subtitulo = "Aviso de Asignación de Embarque";
            
            message.setFrom(new InternetAddress(REMITENTE));
            message.setSubject(subtitulo);
            
            
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
                            + "                <h3>Estimado:&nbsp;&nbsp;" + nameTransporte + "</h3>\n"
                            + "                <br>"
                            + "                <p>\n"
                            + "                <h4>La asignación de embarque, se ha generado de forma exitosa.</h4>\n "
                            + "                <h4>Para mayor infomación puede consultarlo en la siguienta liga: https://www.tacts.mx/VF/detallePaqueteria.jsp?paq=" + idLTransporte + "&id=" + agrupador + "</h4>\n " //Parámetros: Paquetería/Agrupador
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
            
            message.setContent(mensaje, "text/html");

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
    
    public boolean alertaCFDI(String idLTransporte, String nameLTransporte, String tipoT, String agrupador) {
        
        ServiceDAO dao = new ServiceDAO();
        
        String emailenvio = "carlos_martinez3@vfc.com/said_flores@vfc.com/Julissa_Clemente@vfc.com/oamorales@tacts.mx";

        /*String Sql = "SELECT DISTINCT TC.CORREO FROM ONTMS_LINEA_TRANSPORTE OLT INNER JOIN TRA_CORREO TC ON  OLT.LTRANSPORTE_NOMBRE = TC.NOMBRE_CORREO WHERE OLT.LTRANSPORTE_ID = '" + idLTransporte + "' "; 
        try {
            
            ResultSet rs = dao.consulta(Sql);
            while (rs.next()) {
                emailenvio += rs.getString(1) + "/"; 
            }
                emailenvio += "Maria_Gonzalez4@vfc.com/Luis_Alba@vfc.com/Jorge_Venegas@vfc.com/Julissa_Clemente@vfc.com/oamorales@tacts.mx";
                 
        } catch (SQLException e) {
            return false;
        }*/

        boolean enviado = false;
        String[] vect;
        vect = emailenvio.split("/");

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
            
            String subtitulo = "Aviso de Asignación de Evidencia CFDI";
            
            message.setFrom(new InternetAddress(REMITENTE));
            message.setSubject(subtitulo);
            
            
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
                            + "                <h4>La asignación de evidencia CFDI, se ha generado de forma exitosa.</h4>\n ";
                            
                        if(tipoT.equals("1")){
                    mensaje +="                <h4>Para mayor infomación puede consultarlo en la siguienta liga: https://www.tacts.mx/VF/detalleTransportista.jsp?transporte=" + idLTransporte + "</h4>\n ";
                        }else{
                   mensaje += "                <h4>Para mayor infomación puede consultarlo en la siguienta liga: https://www.tacts.mx/VF/detallePaqueteria.jsp?paq=" + idLTransporte + "&id=" + agrupador + "</h4>\n "; //Parámetros: Paquetería/Agrupador         
                        }
                        
                    mensaje +="                 <br>"
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
            
            message.setContent(mensaje, "text/html");

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
 
    public boolean alertaAuthorisedShipments(byte[] data, String embarque_id, String idLTransporte, String nameLTransporte) throws SQLException {
        
        String emailenvio = "";
        ServiceDAO dao = new ServiceDAO();
        
        String Sql = "SELECT DISTINCT TC.CORREO FROM ONTMS_LINEA_TRANSPORTE OLT INNER JOIN TRA_CORREO TC ON  OLT.LTRANSPORTE_NOMBRE = TC.NOMBRE_CORREO WHERE OLT.LTRANSPORTE_ID = '" + idLTransporte + "' "; 
        try {
            
            ResultSet rs = dao.consulta(Sql);
            while (rs.next()) {
                emailenvio += rs.getString(1) + "/"; 
            }
                emailenvio += "Maria_Gonzalez4@vfc.com/Luis_Alba@vfc.com/Jorge_Venegas@vfc.com/Julissa_Clemente@vfc.com/Carlos_Martinez3@vfc.com/Said_Flores@vfc.com/Patricia_Hernandez1@vfc.com/oamorales@tacts.mx";
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
                message.setSubject("Autorización de Pago");
                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setContent(getShipments(idLTransporte, nameLTransporte),"text/html");

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
    
}
