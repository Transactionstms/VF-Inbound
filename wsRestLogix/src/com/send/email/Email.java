/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.send.email;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
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
import json.JSONObject;

/**
 *
 * @author User_Windows10
 */
public class Email {
    
    Properties properties = new Properties();
    
    private String asunto = "Avisos Logix Customs";
    private final String REMITENTE = "alertas1@tacts.mx";
    private final String CLAVE = "Tacts*24*";
    private final String HOST = "smtp.gmail.com";       
    
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    public Email() {

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        //properties.put("mail.debug", "true");

    }
    
    public boolean alertaLogixWebservice(String msg_logError, JSONObject jsonObject) throws SQLException {

        boolean enviado = false;

        
        String[] vect = "oamorales@tacts.mx/jlmateos@tacts.mx/".split("/");

        try {
            Session session = Session.getInstance(properties,
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
            message.setSubject("Error de registro en servicio");
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent(getLogix(msg_logError, jsonObject), "text/html");

            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            //Part two is attachment
            /*messageBodyPart = new MimeBodyPart();

            messageBodyPart.setDataHandler(new DataHandler(new FileDataSource(fichero)));
            messageBodyPart.setFileName("Detalle Eventos " + tipoCorreo + ".xls");

            multipart.addBodyPart(messageBodyPart);*/
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
   
    private String getLogix(String msg_logError, JSONObject jsonObject){
         String mensaje = "<body style=\"font-family: Helvetica,Arial.sans-serif;\">"
                        + "    <div style=\"max-width:600px;margin:0 auto\">"
                        + "        <div style=\"background:#000\\9;font:14px sans-serif;color:#686f7a;border-top:4px solid #;margin-bottom:20px\">"
                        + "            <div style=\"border-bottom:1px solid #adc9ff;padding:20px 30px\">"
                        + "                <p style=\"margin:0;color:#333333\">"
                        + "                <div align=\"center\">"
                        + "             <img src=\"https://cdn-icons-png.flaticon.com/512/1792/1792931.png\" width=\"120\">"
                        + "                </div>"
                        + "            </div>"
                        + "            <div style=\"padding:20px 30px\">"
                        + "                <h3> Buen día. </h3>"
                        + "                <br>"
                        + "                <h3>Error al subir la siguiente información.</h3><br>"
                        + "                <h5>"+msg_logError+"</h5>"
                        + "                <h3>A continuación se muestra el response..</h3><br>"
                        + "                <h5>"+jsonObject+"</h5>"
                        + "            </div>"
                        + "        </div>"
                        + "    </div>"
                        + "<div style=\"max-width:600px;margin:0 auto\">"
                        + "    <div style=\"font:11px sans-serif;color:#686f7a\">"
                        + "        <p style=\"font-size:11px;color:#686f7a\">"
                        + "        <center>"
                        + "            Este es un mensaje informativo, favor de no contestar a este correo."
                        + "        </center>"
                        + "        </p>"
                        + "    </div>"
                        + "</div>"
                        + "</body>";
        return mensaje;
    }
   
    
}