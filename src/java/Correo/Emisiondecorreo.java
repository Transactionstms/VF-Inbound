package Correo;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.onest.train.consultas.Consulta;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.onest.oracle.DB;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.mail.MessagingException;
import com.dao.ServiceDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

/**
 * @author _luis
 */
public class Emisiondecorreo {

    private String Carton;
    public String getCarton(){
        return this.Carton;
    }
    public void mandarCorreo() {

       ArrayList Parametros = EjecutarConsulta();

        // El correo gmail de envío
        String correoEnvia = "alertas@tacts.mx";
        String claveCorreo = "Alertas2017";

        // La configuración para enviar correo
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);

        // Obtener la sesion
        Session session = Session.getInstance(properties, null);

        try {
            // Crear el cuerpo del mensaje
            MimeMessage mimeMessage = new MimeMessage(session);

            // Agregar quien envía el correo
            mimeMessage.setFrom(new InternetAddress(correoEnvia, "ALERTAS RETAIL"));

            // Los destinatarios
            InternetAddress[] internetAddresses = {
                
                new InternetAddress("Ana_Zagoya@vfc.com"),
                new InternetAddress("Geosephe_Alvarez@vfc.com"),
                new InternetAddress("Luis_E_Lopez@vfc.com"),
                new InternetAddress("Gabriel_Salgado@vfc.com"),
                new InternetAddress("Yocelin_salcedo@vfc.com"),
                new InternetAddress("Alejandro_Cabrera@vfc.com "),
                new InternetAddress("Josue_Gonzalez@vfc.com"),
                new InternetAddress("oamorales@tacts.mx")
                };

            // Agregar los destinatarios al mensaje
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    internetAddresses);

            // Agregar el asunto al correo
            mimeMessage.setSubject("Incidencias VF-RETAIL");

            // Creo la parte del mensaje
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText("A continuación, se muestra el detalle de la incidencia: \n"
                    + "\n"
                    + "\n"
                    + "Tienda:                                 "+Parametros.get(0)+ "\n"
                    + "\n"    
                    + "Cartón:                                 "+Parametros.get(1)+ "\n"
                    + "\n" 
                    + "Piezas:                                 "+Parametros.get(2)+ "\n"
                    + "\n" 
                    + "Estado:                                 "+Parametros.get(3)+ "\n"
                    + "\n" 
                    + "Incidencia Observación:       "+Parametros.get(4)+ "\n"
                    + "\n" 
                    + "Folio Incidencia:                    "+Parametros.get(5)+ "\n"
                    + "\n" 
                    + "Fecha/Hora Incidencia:         "+Parametros.get(6)+ "\n"
                    + "\n"
                    + "\n");           
 
            //Mandar a llamra la consulta.
            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
      
            // Agregar el multipart al cuerpo del mensaje
            mimeMessage.setContent(multipart);

            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Correo enviado");
    }

    //Constructor 
    public Emisiondecorreo(String Cartonllegado) {
        this.Carton = Cartonllegado;

    }

    //Metodo 
    public ArrayList EjecutarConsulta() {

        //Arreglo Array  
        ArrayList Datosincidencias = new ArrayList();  //iNSTANCIAR

        try {
            ServiceDAO daox = new ServiceDAO();
        
            // String Tienda = "select DESTINO_SHIP_TO from TRA_TIENDA_USUARIO where NUSER_ID = " + userIDNumber + " ";
            // Statement stmt = daoCte.conectar().createStatement();;
            //   ResultSet rs = stmt.executeQuery(Tienda);
            //    while (rs.next()) {
            String consulta = " SELECT DISTINCT "
                    + " upper(ddh.destino_nombre), "
                    + " tc.tra_caja, "
                    + " nvl(tc.tra_caja_cantidad,0), "
                    + " upper(ae.edo_desc), "
                    + " upper(tc.incidencias_obs), "
                    + " upper(tc.folio_incidencia), "
                    + " NVL(TO_CHAR(TO_DATE(tc.fecha_incidencia,'DD/MM/YYYY HH24:MI:SS')),'SIN INFORMACIÓN') " 
                    + " FROM "
                    + " tra_cajas tc "
                    + " INNER JOIN "
                    + " app_estado ae "
                    + " ON "
                    + " tc.estado_id = ae.edo_valor "
                    + " INNER JOIN "
                    + " tra_tienda_usuario ttu "
                    + " ON "
                    + " tc.user_nid = ttu.nuser_id "
                    + " INNER JOIN "
                    + " dilog_destinos_hilti ddh "
                    + " ON "
                    + " ttu.destino_ship_to = ddh.destino_ship_to "
                    + " WHERE "
                    + " ae.edo_tabla = 'TRA_CAJAS' "
                    + " AND "
                    + " tc.tra_caja = '" + getCarton() + "' "
                    + " AND "
                    + " tc.folio_incidencia "
                    + " IS NOT NULL "
                    + " AND "
                    + " tc.fecha_incidencia "
                    + " IS NOT NULL  "
                    + " AND ddh.destino_sales_org='1' ";
            Statement stmt = daox.conectar().prepareStatement(consulta);
            ResultSet rsx = stmt.executeQuery(consulta);
            
            while (rsx.next()) {

                Datosincidencias.add(rsx.getString(1));  //ADD ES UN METODO
                Datosincidencias.add(rsx.getString(2));
                Datosincidencias.add(rsx.getString(3));
                Datosincidencias.add(rsx.getString(4));
                Datosincidencias.add(rsx.getString(5));
                Datosincidencias.add(rsx.getString(6));
                Datosincidencias.add(rsx.getString(7));

            }

            //}
            //"SELECT em.embarque_id,tc.tra_caja,tc.tra_caja_cantidad,ap.EDO_DESC, nvl(tc.incidencias_obs,' ')"
            rsx.close();

            
        } catch (Exception e) {
            e.printStackTrace();

        }
        return Datosincidencias;
    }

}
