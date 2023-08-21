package com.onest.train.security;

import com.bb.web.beans.BodegaPaqueteExpress;
import com.bean.BodegaBean;
import com.dao.BodegaPaqRowMapper;
import com.dao.BodegaRowMapper;
import com.dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import com.onest.oracle.*;
import com.onest.security.menu.*;
import com.onest.misc.*;
import com.onest.util.PropertiesUtil;
import java.util.List;

/**
 *
 * @@author sbas
 */
public class HiltOnLineCH extends HttpServlet {

    private String targetOk = "/forms/consulta_documentosDetalleUCPCH.jsp";
    private String targetNotOk = "/index.jsp";
    private String error = "error";
    private String targetNotOkindex = "/index.jsp?fail=" + error + "";
    private String targetOkButTooManyServers = "/forms/choose_server.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        PropertiesUtil.load();
        super.init(config); //To change body of generated methods, choose Tools | Templates.
    }   
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method. @@param request servlet request @@param response
     * servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method. @@param request servlet request @@param
     * response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ownsession = request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Authenticate auth = new Authenticate();
        ObtenerCuentas oc = new ObtenerCuentas();
        DBConfData dbData = new DBConfData();
        Utils.DEBUG_LEVEL = 6;
        //Utils.ORA_CONF = "oraconftrain";
        //Obtiene par��metros de Login
        String user = "geo";
        String password = "1";
        System.out.println("Autenticacion6");

        //oc.verifyLoginN(user, password);
        if (!auth.verifyLogin(user, password)) {
            // Si la autenticaci��n es erronea entra a este if.
            ServletContext context = getServletContext();
            RequestDispatcher dispacher = context.getRequestDispatcher(targetNotOkindex);
            request.setAttribute("fail", "login");
            request.setAttribute("fail.login", "Usuario o Contrase&ntilde;a Incorrectos");
            ownsession.invalidate();
            dispacher.forward(request, response);
        } else {// La autenticaci��n fue correcta.         
            ServletContext context = getServletContext();
            RequestDispatcher dispacher;
            MenuFactory factory = new MenuFactory(auth.getUser(), auth.isSuperUser(), auth.getGroups(), "PC");
            String ip = request.getRemoteAddr();
            if("0:0:0:0:0:0:0:1".equals(ip)){
               ip = "127.0.0.1";
            }
            String lastOctet = ip.substring(ip.lastIndexOf(".") + 1);
            lastOctet = (lastOctet.length() == 1) ? "00" + lastOctet : ((lastOctet.length() == 2) ? "0" + lastOctet : lastOctet);
            ownsession.setAttribute("login.user_id_number", auth.getNID());
            ownsession.setAttribute("login.user_id", auth.getUser());
            ownsession.setAttribute("login.user_name", auth.getUserName());
            ownsession.setAttribute("login.user_group", auth.getGroups());
            ownsession.setAttribute("login.user_main_group", auth.getMainGroup());
            ownsession.setAttribute("login.user_cuenta", auth.getCta());
            ownsession.setAttribute("login.is_super_user", auth.isSuperUser());
            ownsession.setAttribute("login.ip", ip);

            /*
             * Sesion de Control de acceso
             */
            ownsession.setAttribute("login.controlUsuario", auth.getAu());
           
            /**
             * *******
             */
            ownsession.setAttribute("login.lastoctet", lastOctet);
            ownsession.setAttribute("prefix.call.procid", auth.getMainGroup());
            ownsession.setAttribute("menu.factory", factory);
            ownsession.setAttribute("db.servers", auth.getServers());
            request.setAttribute("onest.session", auth.encrypt(ownsession.getId()));
            ownsession.setAttribute("onest.session", auth.encrypt(ownsession.getId()));
            String cbdivId = oc.verifyLogin(auth.getNID());
            ownsession.setAttribute("cbdivcuenta", cbdivId);
            String bodegaId = oc.obtieneBodegaId(cbdivId);
            ownsession.setAttribute("cbbodegaId", (bodegaId!=null?bodegaId.split(",")[0]:""));
       
            ServiceDAO dao = new ServiceDAO();
            List<BodegaBean> lista = dao.doMapQuery("select BODEGA_ID, BODEGA_NOMBRE, BODEGA_DIRECCION, DESTINO_CP, CBDIV_ID from ontms_bodega where cbdiv_id in ("+cbdivId+") and bodega_id in ("+bodegaId+")", new BodegaRowMapper());
            if(lista!=null && !lista.isEmpty()){
               ownsession.setAttribute("user.bodegabean", lista.get(0));
            }
            List<BodegaPaqueteExpress> listaPaquete = dao.doMapQuery("SELECT ORGN_CLNTID AS orgnClntId,  CLNTPSWD         AS clntPswd,  AGREEMENNTKEY    AS agreementKey,  DESCRIPCION      AS descripcion,  CBDIV_ID         AS cbDivId  FROM dilog_bodega_paqueteexp WHERE cbdiv_id in ("+cbdivId+")", new BodegaPaqRowMapper());            
            if(listaPaquete!=null && !listaPaquete.isEmpty()){
               BodegaPaqueteExpress credencialesPaquete = listaPaquete.get(0);
               ownsession.setAttribute("credenciales.paqueteexpress", credencialesPaquete);
            }            
            if (auth.howManyConn() > 1 || auth.howManyAcct() > 1) {
                ownsession.setAttribute("db.too.many.servers", auth.howManyConn() + "");
                ownsession.setAttribute("login.too.many.accounts", auth.howManyAcct() + "");
                ownsession.setAttribute("db.data", dbData);
            } else {
                ownsession.setAttribute("cuenta.valida.lote", auth.getCta()[0].getValidaLote());
                ownsession.setAttribute("cuenta.valida.id", auth.getCta()[0].getValidaId());
                ownsession.setAttribute("cuenta.nombre", auth.getCta()[0].getNombreCta());
                ownsession.setAttribute("bodega.nombre", auth.getCta()[0].getNombreBod());
                ownsession.setAttribute("bodega.id", auth.getCta()[0].getIdBod());
                ownsession.setAttribute("db.too.many.servers", auth.howManyConn() + "");
                ownsession.setAttribute("login.too.many.accounts", auth.howManyAcct() + "");
                dbData.setConf(auth.getServers()[0]);
                ownsession.setAttribute("db.data", dbData);
            }
            if (factory.menuIsOk()) {
                dispacher = context.getRequestDispatcher((auth.howManyConn() > 1 ? targetOkButTooManyServers : targetOk));
            } else {
                request.setAttribute("fail", "menu");
                request.setAttribute("fail.menu", "Revise los permisos de acceso a sus menus");
                String idd = request.getParameter("id");
                dispacher = context.getRequestDispatcher(targetOk+"?id="+idd);
            }
            dispacher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}