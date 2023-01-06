/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.graficas;

import com.onest.misc.Calendario;
import com.onest.misc.Cuenta;
import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.util.PropertiesUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sistemas
 */
@WebServlet(name = "GraficaEmbarquesPaqueterias", urlPatterns = {"/GraficaEmbarquesPaqueterias"})
public class GraficaEmbarquesPaqueterias extends HttpServlet {

    private Connection connection;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession ownsession = request.getSession(false);
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            try {
                //DB db = new DB((DBConfData) dbData);
                oraDB.connect(dbData.getUser(), dbData.getPassword());


                 String sqlText="";
                String[] sqlCuentas=(ownsession.getAttribute("cbdivcuenta")!=null)?ownsession.getAttribute("cbdivcuenta").toString().split(","):null;
                for(int i=0;i<sqlCuentas.length;i++){
                    if(!sqlCuentas[i].equals("")){
                        sqlText+="Pcuentas("+(i+1)+") :="+ sqlCuentas[i]+";";
                    }
                }                
                String oracleQuery = "DECLARE Pcuentas "+PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER)+".ARRAY_INT; BEGIN Pcuentas:="+PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER)+".ARRAY_INT();Pcuentas.EXTEND(10);"+sqlText+"  ? := ONTMS_FN_GRAFICA_EMBARQUES_PC(Pcuentas); END;";

                //String oracleQuery = "BEGIN ? := ONTMS_FN_GRAFICA_EMBARQUES_P; END;";


                String[] outParam = new String[]{""};
                String[] inParam = new String[]{};
                String oraOut = oraDB.execStore(oracleQuery, outParam, inParam, new int[]{OracleDB.ORA_VARCHAR});
             

                out.println(oraOut);





            } catch (Exception e) {
                out.println("Error " + e.toString());
            } finally {
                oraDB.close();
            }
        } catch (NullPointerException e) {
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";


    }
}
