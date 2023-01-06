/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

import com.dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TACTS
 */
public class ConsultaPrioridadAsignaciones extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ownsession = request.getSession();
        String Div = (String) ownsession.getAttribute("cbdivcuenta");
        String bo = (String) ownsession.getAttribute("bodega.id");
        // 
        String fecha = request.getParameter("fecha");
        //       String choferes = getChoferes();
        //       String camiones = getCamiones();
        //     String tracking1 = getSalchichas();
        //      String origen = getOrigen();

        String MostrarDatos = "select ddh.destino_nombre, ods.docto_sal_agrupador , odk.producto_sku, och.chofer_nombre,oca.no_eco,oca1.pg,oca2.pg ,origen.bodega_nombre,oe.EMBARQUE_PRIORIDAD, oe.embarque_id\n"
                + "from ontms_docto_sal ods\n"
                + "inner join ontms_docto_sku odk\n"
                + "on ods.docto_sal_id= odk.docto_sal_id\n"
                + "inner join dilog_destinos_hilti ddh\n"
                + "on ddh.destino_ship_to= ods.destino_id\n"
                + "inner join ontms_embarque oe\n"
                + "on oe.embarque_agrupador= odk.docto_sku_agrupador\n"
                + "left join ontms_chofer och\n"
                + "on oe.chofer_id= och.chofer_id\n"
                + "left join ontms_camion oca\n"
                + "on oe.camion_id=oca.camion_id\n"
                + "left join ontms_camion oca1\n"
                + "on oe.ID_TRACTOR1=oca1.camion_id\n"
                + "left join ontms_camion oca2\n"
                + "on oe.ID_TRACTOR2=oca2.camion_id\n"
                + "left join ontms_bodega origen\n"
                + "on ods.origen_id=origen.BODEGA_PREFIJO "
                + "where   odk.docto_sku_fec_entrega='" + fecha + "'\n"
                //  + "and ods.destino_id='" + shipto + "' \n"
                + "AND origen.bodega_nombre IS NOT NULL \n"
                + "AND ods.docto_sal_agrupador IS NOT NULL \n"
                + "and oe.embarque_estado_id <> 3\n"
                 + "and oe.embarque_estado_id = 1\n"
                + "order by och.chofer_nombre,ddh.destino_id, odk.producto_sku";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(MostrarDatos);
        int sal = 0;
        String OpeNombre = "";
        int conta = 1;
        try (PrintWriter out = response.getWriter()) {
            String salida = "\n";
            while (rs.next()) {
                boolean as=false;
                //"++"
                if (rs.getString(9) != null) {
                    if (!rs.getString(9).equals("100")) {
                        if (rs.getString(4) != null) {
                            if (OpeNombre.equals(rs.getString(4))) {
                                conta++;
                            } else {
                                conta = Integer.parseInt(rs.getString(9));
                                OpeNombre = rs.getString(4);
                                as=true;
                                
                            }
                        } else {
                            OpeNombre = "";
                        }
                    } else {
                        conta = 1;
                    }
                }
                String destino_nombre = rs.getString(1);
                String docto_sal_agrupador = rs.getString(2);
                String producto_sku = rs.getString(3);
                String chofer = rs.getString(4);
                String camion = rs.getString(5);
                String s1 = rs.getString(6);
                String s2 = rs.getString(7);
                String origenes = rs.getString(8);
                String idEmbraque = rs.getString(10);
                boolean editado = false;
                    salida += "                                    <tr id=\"tr" + sal + "\">\n"
                            + "                                        <td style=\"width: 10%\">     \n"
                            + "                                            <input type=\"text\" class=\"form-control\" value=\"" + idEmbraque + "\"  id=\"ss" + sal + "\"  uib-typeahead=\"dest for dest in dest\">\n"
                            + "                                        </td>\n"
                            + "                                        <td style=\"width: 15%\">     \n"
                            + "                                            <input type=\"text\" class=\"form-control\" value=\"" + destino_nombre + "\"  disabled uib-typeahead=\"dest for dest in dest\">\n"
                            + "                                            <input type=\"hidden\" class=\"form-control\" value=\"" + docto_sal_agrupador + "\" id=\"agr" + sal + "\" disabled uib-typeahead=\"dest for dest in dest\">\n"
                            + "\n"
                            + "                                        </td>\n"
                            + "                                        <td>\n";

                    if (producto_sku.equals("1807191")) {
                        salida += "<input type=\"text\" id=\"ref" + sal + "\" class=\"form-control\" disabled value=\"IMPORTACIÓN\" >\n";
                    } else {
                        salida += "<input type=\"text\" id=\"ref" + sal + "\" class=\"form-control\" disabled value=\"PEMEX\" >\n";
                    }

                    salida += " </td>\n"
                            + "\n"
                            + "                                        <td ><select name=\"Ori\" id=\"Ori" + sal + "\"  class=\"chosen-select\" tabindex=\"2\">\n";

                    if (origenes != null) {
                        editado = true;
                        salida += "      <option value=\"\">" + origenes + "</option>\n";
                    } else {
                        salida += "   <option value=\"\">Origen</option>\n";

                    }

                    //    salida += origen;
                    salida += " </td>\n"
                            + "\n"
                            + "                                        <td ><select name=\"Tractor\" id=\"Tra" + sal + "\"  class=\"chosen-select\" tabindex=\"2\">\n";
                    if (camion != null) {
                        editado = true;
                        salida += "      <option value=\"\">" + camion + "</option>\n";
                    } else {
                        salida += "<option value=\"\">Tractor</option>\n";

                    }
                    //  salida += "</div>";
                    //        salida += tracking1;
                    //  + "                                                    out.println(camiones);\n"
                    salida += "\n"
                            + "                                                \n"
                            + "                                            </select></td>\n"
                            + "                                        <td ><select name=\"Remolque1\" class=\"chosen-select\" id=\"Sal1" + sal + "\" tabindex=\"2\">\n";
                    if (s1 != null) {
                        editado = true;
                        salida += "      <option value=\"\">" + s1 + "</option>\n";
                    } else {
                        salida += "      <option value=\"\">Semiremolque 1</option>\n";

                    }
                    //       salida += camiones;
                    //  + "                                                    
                    salida += "\n"
                            + "                                                \n"
                            + "                                            </select></td>\n"
                            + "                                        <td ><select name=\"Remolque2\" class=\"chosen-select\" id=\"Sal2" + sal + "\"  tabindex=\"2\">\n"
                            + "                                              ";
                    if (s2 != null) {
                        editado = true;
                        salida += "      <option value=\"\">" + s2 + "</option>\n";
                    } else {
                        salida += "        <option value=\"\">Semiremolque 2</option>\n";

                    }

                    //       salida += camiones;
                    //  + "                                                    
                    salida += "                                                \n"
                            + "                                            </select></td>\n"
                            + "                                        <td ><select  class=\"chosen-select\" id=\"chofer" + sal + "\"  onselect=\"llenarChofer('" + sal + "')\">\n";
                    if (chofer != null) {
                        editado = true;
                        salida += "      <option value=\"\">" + chofer + "</option>\n";
                    } else {
                        salida += "<option value=\"\">Operador</option>\n";

                    }
                    //          salida += choferes;
                    //  + "                                                    
                    salida += "\n"
                            + "                                                </td>\n"
                            + "<td style=\"width: 3%\">"
                            + "<input type=\"number\" class=\"form-control\" value=\"" + conta + "\"  id=\"prio" + sal + "\"  min=\"1\" max=\"10\" uib-typeahead=\"dest for dest in dest\">\n"
                            + "</td>"
                            
                            ;

                    salida += "                                        <td>    <a class=\"btn btn-primary\" onclick=\"insertarTransporte('" + sal + "')\" id=\"pla" + sal + "\" ><i class=\"fa fa-check\"></i>Asignar Prioridad</a>\n"
                            + "                                        </td>\n";

                    salida += "\n"
                            + "\n"
                            + "                                    </tr>\n";
                    ;
                    sal++;

                }
            

            dao.CerrarConexion();
            //salida+="                                </tbody>";
            out.print(salida);
            if (sal == 0) {
                out.print("No se encontraron datos");
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaPrioridadAsignaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaPrioridadAsignaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
