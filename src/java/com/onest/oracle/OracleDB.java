package com.onest.oracle;

import com.onest.misc.Calendario;
import com.onest.util.PropertiesUtil;
import com.onest.util.Util;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import oracle.jdbc.pool.OracleDataSource;

public class OracleDB {

    private String url = "";
    private OracleDataSource oracleDS;
    private Connection connection;
    private String[][] result;
    private int numOfCol = 0;
    private int numOfRow = 0;
    CallableStatement stmt;
    
    private String[] columnNames;
    public static final int ORA_VARCHAR = 12;
    public static final int ORA_NUMBER = 2;
    public static final int ORA_INTEGER = 4;
    public PendientesFleteBean[] beangral;
    private Exception currentException = null;

    public OracleDB(String serverName, String portNum, String SID) {
        //this.url = ("jdbc:oracle:thin:@localhost:1521:orcl");
        
        
        //this.url = ("jdbc:oracle:thin:@transactions.mx:1521:orcl");
        this.url = PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_URL);
    }

    public boolean connect(String user, String password) {
        try {
            this.oracleDS = new OracleDataSource();
            this.oracleDS.setUser(user);
            this.oracleDS.setPassword(password);
            this.oracleDS.setURL(this.url);
            this.connection = this.oracleDS.getConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
          
        }
        return false;
    }

    public String execStore(String query, String[] outParams, String[] inParams, int[] paramTypes) {
        String respuesta="-1";
        
        try {
            int numOfParams = 1;
             stmt = this.connection.prepareCall(query);
            for (int i = 0; i < outParams.length; i++) {
                stmt.registerOutParameter(numOfParams, paramTypes[i]);
                numOfParams++;
            }
            for (int i = 0; i < inParams.length; i++) {
                stmt.setString(numOfParams, inParams[i]);
                numOfParams++;
            }
            stmt.execute();
            Object obj = stmt.getObject(1);
            respuesta= obj.toString();            
        } catch (SQLException e) {
            e.printStackTrace(System.err);
           
            com.management.util.Loggin.error(Calendario.getToday() + " Sentencia: " + query + "\n");
        }
        finally{
            if(stmt!=null){
                try{
                stmt.close();
                }catch(Exception exc){exc.printStackTrace();}
            }
        }
        return respuesta;
    }
    
    public String execStoreWithResponse(String query, String[] outParams, String[] inParams, int[] paramTypes) {
        String respuesta = "-1";
        try {
            int numOfParams = 1;
             stmt = this.connection.prepareCall(query);
            for (int i = 0; i < outParams.length; i++) {
                stmt.registerOutParameter(numOfParams, paramTypes[i]);
                numOfParams++;
            }
            for (int i = 0; i < inParams.length; i++) {
                stmt.setString(numOfParams, inParams[i]);
                numOfParams++;
            }
            stmt.execute();
            Object obj = stmt.getObject(1);
            return obj.toString();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            
            com.management.util.Loggin.error(Calendario.getToday() + " Sentencia: " + query + "\n");
        }
        return respuesta;
    }    

    public ResponseBean consultapendientes(String query) {
        this.beangral = new PendientesFleteBean[5000];
        int cont = 0, cont2 = 0;
        ResponseBean respuesta = new ResponseBean();
        String agente = "0";
        String[] agrupadortemp;
        String[] ltransporte;

        Map rows = new HashMap<String, PendientesFleteBean>();
        int valorx = 0;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query, 1004, 1007);
            stmt.setFetchSize(1000);
            ResultSet rset1 = stmt.executeQuery(query);
            Map sumas = new HashMap();
            while (rset1.next()) {
                valorx++;

            }

            ResultSet rset = stmt.executeQuery(query);
            agrupadortemp = new String[valorx];
            ltransporte = new String[valorx];
            for (int j = 0; j < agrupadortemp.length; j++) {
                agrupadortemp[j] = "";
                ltransporte[j] = "";
            }
            while (rset.next()) {
                PendientesFleteBean bean = new PendientesFleteBean();
                bean.setId(rset.getString("embarque_paq_id"));
                bean.setCuenta(rset.getString("cuenta_nombre"));
                bean.setFcaptura(rset.getString("EMBARQUE_PAQ_CAPTURA"));
                bean.setChofer(rset.getString("CHOFER"));
                bean.setTransporte(rset.getString("paqueteria"));
                // bean.setUnidad(rset.getString("UTRANSPORTE_DESC"));
                bean.setCostoFlete(rset.getString("embarque_paq_costo_real"));
                bean.setAgrupador(rset.getString("EMBARQUE_PAQ_AGRUPADOR"));
                bean.setTransporteId(rset.getString("gtransp_id"));
                String cIndirecto = rset.getString("embarque_indirectos_costo");
                /*   bean.setId(rset.getString("embarque_id"));
                 bean.setCuenta(rset.getString("cuenta_nombre"));
                 bean.setFcaptura(rset.getString("EMBARQUE_FEC_CAPTURA"));
                 bean.setChofer(rset.getString("CHOFER_NOMBRE"));
                 bean.setTransporte(rset.getString("LTRANSPORTE_DESCRIPCION"));
                 bean.setUnidad(rset.getString("UTRANSPORTE_DESC"));
                 bean.setCostoFlete(rset.getString("EMBARQUE_COSTO_REAL"));
                 bean.setAgrupador(rset.getString("EMBARQUE_AGRUPADOR"));
                 bean.setTransporteId(rset.getString("gtransp_id"));
                 String cIndirecto = rset.getString("embarque_indirectos_costo");
                 */
                agrupadortemp[cont] = bean.getId();
                ltransporte[cont] = bean.getTransporte();
                String tId = rset.getString("gtransp_id");

                for (int j = 0; j < agrupadortemp.length; j++) {
                    if (cont > 0 && bean.getId().equals(agrupadortemp[j]) && cont != j) {
                        PendientesFleteBean obj = (PendientesFleteBean) rows.get(j);
                        obj.getCostos().add(new CostoBean(rset.getString("embarque_indirectos_costo"), tId));

                        cont2 = 1;
                        break;
                    }

                }


                if (cont2 != 1) {
                    bean.setCostoIndirecto(cIndirecto);
                    bean.setTdesc(rset.getString("GTRANSP_DESC"));
                    rows.put(cont, bean);
                }
                if (tId != null) {
                    Float cind = Util.getFloatValue(cIndirecto);
                    if (sumas.containsKey(tId + "costo")) {
                        Float tmp = Util.getFloatValue(sumas.get(tId + "costo").toString());
                        sumas.put(tId + "costo", cind + tmp);
                    } else {
                        sumas.put(tId + "costo", cind);
                    }
                }

                cont++;
                cont2 = 0;
            }

            rset.close();
            stmt.close();
            respuesta.setRows(rows);
            respuesta.setSumas(sumas);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(stmt!=null){
                try{
                     
                stmt.close();
                }catch(Exception exc){exc.printStackTrace();}
            }
        }
        
        return respuesta;
    }

    public boolean execute(String query) {
        String queryTmp = query;
        ResultSet rset=null;
        Statement stmt=null;
        boolean bandera =false;
        try {
            if ((queryTmp.toUpperCase().indexOf("INSERT") != -1) || (queryTmp.toUpperCase().indexOf("DELETE") != -1) || (queryTmp.toUpperCase().indexOf("UPDATE") != -1)) {
                stmt = this.connection.createStatement();
                int res = stmt.executeUpdate(query);
                return true;
            }
            stmt = this.connection.prepareStatement(query, 1004, 1007);
            stmt.setFetchSize(1000);
            rset = stmt.executeQuery(query);
            ResultSetMetaData rinfo = rset.getMetaData();
            int index = 0;
            this.numOfCol = rinfo.getColumnCount();
            rset.last();
            this.numOfRow = (rset.getRow() == 0 ? 1 : rset.getRow());
            rset.beforeFirst();
            this.result = new String[this.numOfRow][this.numOfCol];
            this.columnNames = new String[this.numOfCol];
            if (rset.next()) {
                rset.beforeFirst();
                while (rset.next()) {
                    for (int i = 1; i <= this.numOfCol; i++) {
                        this.result[index][(i - 1)] = rset.getString(i);
                    }
                    index++;
                }
                bandera= true;
            } else {
                this.result = new String[1][1];
                this.result[0][0] = "No hay registros";
                bandera= false;
            }

            for (int i = 1; i <= this.numOfCol; i++) {
                this.columnNames[(i - 1)] = rinfo.getColumnName(i);
            }
            
            
        } catch (SQLException e) {
            this.currentException = e;            
            System.out.println("[OracleDB] "+e.getMessage());
            com.management.util.Loggin.error(query);
            bandera= false;
        } catch (NullPointerException e) {
            System.out.println("[OracleDB] "+e.getMessage());
            bandera=false;
           
        }finally{
            try{
                if(rset!=null)
                    rset.close();
                if(stmt!=null)
                    stmt.close();
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return bandera;
    }

    public String executeInsertWithRowId(String query, String[] keys) {
        int res =0;
        String resultado="";
        Statement stmt=null;
        try {
            stmt = this.connection.createStatement();
            res = stmt.executeUpdate(query, keys);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                resultado= rs.getInt(1) + "";
            }
            if(stmt!=null){
            stmt.close();
            }
            
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }finally{
            try{
            if(stmt!=null){
            stmt.close();
            }}catch(Exception exc){
                exc.printStackTrace();
            }
        }
        return resultado;
    }

    public String executeReturnString(String query) {
        String queryTmp = query;
        String salida = "";
        try {
            if ((queryTmp.toUpperCase().indexOf("INSERT") != -1) || (queryTmp.toUpperCase().indexOf("DELETE") != -1) || (queryTmp.toUpperCase().indexOf("UPDATE") != -1)) {
                Statement stmt = this.connection.createStatement();
                int res = stmt.executeUpdate(query);
                salida = "";
            }
            PreparedStatement stmt = this.connection.prepareStatement(query, 1004, 1007);
            stmt.setFetchSize(1000);
            ResultSet rset = stmt.executeQuery(query);
            ResultSetMetaData rinfo = rset.getMetaData();
            int index = 0;
            this.numOfCol = rinfo.getColumnCount();
            rset.last();
            this.numOfRow = (rset.getRow() == 0 ? 1 : rset.getRow());
            rset.beforeFirst();
            this.result = new String[this.numOfRow][this.numOfCol];
            this.columnNames = new String[this.numOfCol];
            if (rset.next()) {
                rset.beforeFirst();
                while (rset.next()) {
                    for (int i = 1; i <= this.numOfCol; i++) {
                        this.result[index][(i - 1)] = rset.getString(i);
                    }
                    index++;
                }
            } else {
                this.result = new String[1][1];
                this.result[0][0] = "No hay registros";
                salida = "";

            }

            for (int i = 1; i <= this.numOfCol; i++) {
                this.columnNames[(i - 1)] = rinfo.getColumnName(i);
            }
            rset.close();
            stmt.close();
            salida = "";
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            com.management.util.Loggin.error(e);
            com.management.util.Loggin.error(query);
            salida = e.getMessage();
        } catch (NullPointerException e) {
            e.printStackTrace(System.err);
            com.management.util.Loggin.error(e);
            com.management.util.Loggin.error(query);
            salida = e.getMessage();
        }

        return salida;
    }

    public ResultSet execute(String query, boolean regresaRSet) throws ClassNotFoundException, SQLException {
        Statement stmt = this.connection.createStatement(1004, 1007);
        ResultSet rset = stmt.executeQuery(query);
        ResultSetMetaData rinfo = rset.getMetaData();
        this.numOfCol = rinfo.getColumnCount();
        rset.last();
        this.numOfRow = (rset.getRow() == 0 ? 1 : rset.getRow());
        rset.beforeFirst();
        this.result = new String[this.numOfRow][this.numOfCol];
        this.columnNames = new String[this.numOfCol];
        return rset;
    }

    public ResultSet execStore(String query, String[] outParams, String[] inParams, int[] paramTypes, boolean regresaRSet) throws ClassNotFoundException, SQLException {
        int numOfParams = 1;
        stmt = this.connection.prepareCall(query);
        for (int i = 0; i < outParams.length; i++) {
            stmt.registerOutParameter(numOfParams, paramTypes[i]);
            numOfParams++;
        }
        for (int i = 0; i < inParams.length; i++) {
            stmt.setString(numOfParams, inParams[i]);
            numOfParams++;
        }
        stmt.execute();
        ResultSet obj = (ResultSet) stmt.getObject(1);
        return obj;
    }

    public void close() {
        try {
            if (this.stmt != null) {
                this.stmt.close();
                stmt = null;
            }
            
            
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            com.management.util.Loggin.error(e);
        }
    }

    public int getNumOfCol() {
        return this.numOfCol;
    }

    public int getNumOfRow() {
        return this.numOfRow;
    }

    public String[][] getResult() {
        return this.result;
    }

    public String[] getColumnNames() {
        return this.columnNames;
    }

    public String consultaManiobra(String query) {

        String idFolio = "";
        try {
            Statement stmt = this.connection.createStatement();
            stmt.setFetchSize(1000);
            ResultSet rset = stmt.executeQuery("SELECT MANIOBRA_ID FROM ONTMS_MANIOBRAS WHERE MANIOBRA_FOLIO='" + query + "'");

            while (rset.next()) {
                idFolio = rset.getString(1);
            }
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            idFolio = query;
        }
        return idFolio;
    }

    public String insertaManiobraDoc(String oracleQueryDoc) {

        try {
            Statement stmt = this.connection.createStatement();
            System.out.println(oracleQueryDoc);
            int res = stmt.executeUpdate(oracleQueryDoc);
            stmt.close();
            return oracleQueryDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return oracleQueryDoc + " -1";
        }

    }
    
    public Exception getCurrentException(){
      return this.currentException;
    }
}
