/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.util;

import com.dao.ServiceDAO;
import com.onest.train.consultas.CapturaDocumento;
import com.onest.train.consultas.Consulta;
import com.onest.train.consultas.ConsultasQuery;
import com.onest.train.consultas.ConsultasReportes;
import java.sql.SQLException;

/**
 *
 * @author RicardoMartinez
 */
public class ValidacionCuenta extends ServiceDAO {

    static private boolean hiltiCuenta = false;
    private String idCuentaBroker;
    static private CapturaDocumento cp = new CapturaDocumento();
    static private ConsultasQuery cq = new ConsultasQuery();
    static private com.onest.train.consultas.ConsultasHilti ch = new com.onest.train.consultas.ConsultasHilti();
    static private ConsultasReportes cr = new ConsultasReportes();
    static private Consulta c = new Consulta();

    public boolean valida(String idCuentaBroker) throws SQLException {
        //boolean bandera = false;

        /*String sql = " SELECT * FROM app_constantes WHERE constante_valor ='" + idCuentaBroker + "' ";

        ResultSet res = this.consulta(sql);

        while (res.next()) {
            hiltiCuenta = true;
        }
        this.desconectarService(res);
        return hiltiCuenta;*/
        return true;
    }

    public String validacionConsultaEmbarqueNBod(String e_cuenta, String tipo, String e, String folio, String idCuentaBroker) {
        String salida = "";
        if(folio.contains(" ")){
            String[] sFolio = folio.split(" ");
            folio = "";
            for(String f : sFolio){
                folio = folio+f+"','";
            }
            folio = folio.substring(0,folio.length()-2);
            folio = "'"+folio;
        }
        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.consultaEmbarqueNBodHilti(e_cuenta, tipo, e, folio);
            } else {
                salida = cp.consultaEmbarqueNBod(e_cuenta, tipo, e, folio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String validacionembarqueDetalle(String e_cuenta, String tipo, String e, String idCuentaBroker, String tipoEmbarque,String shipto, String docto,String fec1, String fec2, String folio) {
        String salida = "";
        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.embarqueDetalleHilti(e_cuenta, tipo, e, tipoEmbarque,shipto,docto,fec1,fec2, folio);
            } else {
                salida = cp.embarqueDetalle(e_cuenta, tipo, e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String validacionCountEmbarqueDetalle(String e_cuenta, String tipo, String e, String idCuentaBroker, String tipoEmbarque,String shipto, String docto,String fec1, String fec2, String folio) {
        String salida = "";
        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.countembarqueDetalle(e_cuenta, tipo, e, tipoEmbarque,shipto,docto,fec1,fec2, folio);
            } else {
                salida = cp.embarqueContar(e_cuenta, tipo, e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String embarqueDetalleSelecconadoHilti(String cbdiv, String docto_sal_id, String division_id) {
        String salida = "";

        salida = ch.embarqueDetalleSelecconadoHilti(cbdiv, docto_sal_id, division_id);

        return salida;
    }
      public String planeacionRutasDoctos(String cbdiv, String docto_sal_id, String division_id, String ruta_id) {
        String salida = "";

        salida = ch.planeacionRutasDoctos(cbdiv, docto_sal_id, division_id, ruta_id);

        return salida;
    }

    public String embarqueDocumentosAceptadosHilti(String sal_id, String division, String cvecuenta, String t, String e, String idCuentaBroker, String doc) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.embarqueDocumentosAceptadosHilti(sal_id, division, cvecuenta, t, e, doc);
            } else {
                salida = cp.embarqueDocumentosAceptados(sal_id, cvecuenta, t, e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }

        return salida;
    }

    public String servicioClienteLista(String embarque, String cve, String idCuentaBroker) {
        String salida = "";
        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = cp.servicioClienteLista(embarque, cve);
                // salida = ch.servicioClienteListaHilti(embarque, cve);
            } else {
                salida = cp.servicioClienteLista(embarque, cve);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String detalleDocumentosEmbarcados(String id_doc, String cveCuenta, String e, String idCuentaBroker) {
        String salida = "";
        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.detalleDocumentosEmbarcadosHilti(id_doc, cveCuenta, e);
            } else {
                salida = cp.detalleDocumentosEmbarcadosCancelar(id_doc, cveCuenta, e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String servicioClienteListaModificar(String embarque, String cve, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.servicioClienteListaModificarHilti(embarque, cve);
            } else {
                salida = cp.servicioClienteLista(embarque, cve);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String embarqueContarReenvios1(String e_cuenta, String tipo, String division, String estado, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.embarqueContarReenvios1Hilti(e_cuenta, tipo, division, estado);
            } else {
                salida = cq.embarqueContarReenvios1(e_cuenta, tipo, division, estado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String servicioClienteListaHis(String embarque, String cve, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.servicioClienteListaHisHilti(embarque, cve);
            } else {
                salida = cp.servicioClienteListaHis(embarque, cve);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String consultaDoc(String[] id) {
        String salida = "";

        try {
            if (this.valida(id[3]) == true) {
                salida = ch.consultaDocHilti(id);
            } else {
                salida = c.consultaDoc(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    
    public String muestraDocumentos2(String id, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.consultaDocHiltiDocuemnto(id);
            } else {
                salida = cp.muestraDocumentos2(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }
    
    public String muestraDocumentosOnLine(String id, String idCuentaBroker) {
        String salida = "";
        salida=ch.consultaDocHiltiDocumentoOnLine(id);
        return salida;
    }

    public String muestraDocumentosEmbarcados(String agrupador, String id, String es, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.muestraDocumentosEmbarcadosHilti(agrupador, id, es);
            } else {
                salida = cp.muestraDocumentosEmbarcados(agrupador, id, es);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String consultaPaq(String[] id) {
        String salida = "";
        try {
            if (this.valida(id[3]) == true) {
                salida = ch.consultaPaqHilti(id);
            } else {
                salida = cp.consultaPaq(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String muestraDocumentosRechazo(String agrupador, String tabla, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.muestraDocumentosRechazoHilti(agrupador, tabla);
            } else {
                salida = cp.muestraDocumentosRechazo(agrupador, tabla);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String muestraDocumentosEvidencia(String agrupador, String tabla, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.muestraDocumentosEvidenciaHilti(agrupador, tabla);
            } else {
                salida = cp.muestraDocumentosEvidencia(agrupador, tabla);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String muestraDocumentosDetalleProducto(String agrupador, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.muestraDocumentosDetalleProductoHilti(agrupador);
            } else {
                salida = cp.muestraDocumentosDetalleProducto(agrupador);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String detalleDocumentoModificar(String id, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.detalleDocumentoModificarHilti(id);
            } else {
                salida = cq.detalleDocumentoModificar(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String trakingGeneralLista(String documento, String cveCuenta, String idCuentaBroker,String cbdivId) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.trakingGeneralListaHilti(documento, cveCuenta, cbdivId);
            } else {
                salida = cq.trakingGeneralLista(documento, cveCuenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String detalleDocumentosEmbarcadosCancelar(String id_doc, String cveCuenta, String division, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.detalleDocumentosEmbarcadosCancelarHilti(id_doc, cveCuenta, division);
            } else {
                salida = cp.detalleDocumentosEmbarcadosCancelar(id_doc, cveCuenta, division);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String detalleEvidenciaGuiaEmbarque(String agrupador, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = cp.detalleEvidenciaGuiaEmbarque(agrupador);
                //salida = ch.detalleEvidenciaGuiaEmbarqueHilti(agrupador);
            } else {
                salida = cp.detalleEvidenciaGuiaEmbarque(agrupador);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String servicioCliente(String embarque, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = cp.servicioCliente(embarque);
                //salida = ch.servicioClienteHilti(embarque);
            } else {
                salida = cp.servicioCliente(embarque);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String pendienteEm(String[] id) {
        String salida = "";

        try {
            if (this.valida(id[4]) == true) {
                salida = ch.pendienteEmHilti(id);
            } else {
                salida = cr.pendienteEm(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String viajesDiariosDocumento(String[] id) {
        String salida = "";

        try {
            if (this.valida(id[3]) == true) {
                salida = ch.viajesDiariosDocumentoHIli(id);
            } else {
                salida = cr.viajesDiariosDocumento(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String viajesx(String[] id) {
        String salida = "";

        try {
            if (this.valida(id[2]) == true) {
                salida = ch.viajesxHilti(id);
            } else {
                salida = c.viajesx(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String pEvidenciaHilti(String[] id) {
        String salida = "";

        try {
            if (this.valida(id[3]) == true) {
                salida = ch.pEvidenciaHilti(id);
            } else {
                salida = cr.pEvidencia(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String docEntregados(String[] id) {
        String salida = "";

        try {
            if (this.valida(id[4]) == true) {
                salida = ch.docEntregadosHilti(id);
            } else {
                salida = cr.docEntregados(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String pEvidenciaReporteHilti(String[] id) {
        String salida = "";

        try {
            if (this.valida(id[3]) == true) {
                salida = ch.pEvidenciaReporteHilti(id);
            } else {
                salida = cr.pEvidencia(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String liquidacion(String cve, String trans, String idCuentaBroker) {
        String salida = "";

        try {
           
                salida = cq.liquidacion(cve, trans);
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String pendSap(String fec1, String fec2, String estado, String cveCuenta, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.pendSapHilti(fec1, fec2, estado, cveCuenta);
            } else {
                salida = cr.pendSap(fec1, fec2, estado, cveCuenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String fletGralCont1(String fec1, String fec2, String estado, String cveCuenta, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.fletGralCont1Hilti(fec1, fec2, estado, cveCuenta);
            } else {
                salida = cr.fletGralCont1(fec1, fec2, estado, cveCuenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String fletGralCont(String fec1, String fec2, String estado, String cveCuenta, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.fletGralContHilti(fec1, fec2, estado, cveCuenta);
            } else {
                salida = cr.fletGralCont(fec1, fec2, estado, cveCuenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String fletegral(String[] id) {
        String salida = "";
        try {
            if (this.valida(id[4]) == true) {
                salida = ch.fletegralHilti(id);
            } else {
                salida = c.fletegral(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String reporteO(String[] id) {
        String salida = "";
        try {
            if (this.valida(id[6]) == true) {
                salida = ch.reporteOHilti(id);
            } else {
                salida = cr.reporteO(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String muestraDocumentosEmbarcadoReImpresion(String embarque, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.validarTipoEmbarque(embarque);
            } else {
                salida = ch.validarTipoEmbarque(embarque);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String validaTipoPDF(String agrupador, String idCuentaBroker) {
        String salida = "";

        try {
            if (this.valida(idCuentaBroker) == true) {
                salida = ch.validarTipoPDF(agrupador);
            } else {
                salida = ch.validarTipoPDF(agrupador);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String validarServicioPE(String agrupador, String idCuentaBroker) {
        String salida = "";

        try {

            salida = ch.validarServicioPE(agrupador);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String validarServicioDHL(String agrupador, String idCuentaBroker) {
        String salida = "";

        try {
            //salida = ch.validarServicioDHL(agrupador);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String doctosnovisibles(String[] id) {
        String salida = "";

        try {
            if (this.valida(id[4]) == true) {
                salida = ch.doctosnovisibles(id);
            } else {
                salida = cr.pendienteEm(id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }
}
