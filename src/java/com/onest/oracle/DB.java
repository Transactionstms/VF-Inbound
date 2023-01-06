package com.onest.oracle;

import com.onest.net.Connection;
import com.onest.util.PropertiesUtil;
import java.io.Serializable;

public class DB implements Serializable {

    private String[][] resultado;
    private String[] columnNames;
    private int numOfRows;
    private int numOfCols;
    public static int SECURITY = 0;
    public static int DATA = 1;
    private Connection[] conns = new Connection[2];
    private int whichServer = 1;
    private int whichDat = 1;   
    private DBConf sec = new DBConf();
    private DBConfData data = new DBConfData();

    public DB(DBConfData data) {
        this.sec.setConf();
        this.conns[0] = new Connection(this.sec.getServerID(), this.sec.getIPv4(), this.sec.getIPv6(), this.sec.getServerName(), this.sec.getSid(), this.sec.getPuerto(), this.sec.getUser(), this.sec.getPassword(), this.sec.getDBName());
        this.conns[1] = new Connection(data.getServerID(), data.getIPv4(), data.getIPv6(), data.getServerName(), data.getSid(), data.getPuerto(), data.getUser(), data.getPassword(), data.getDBName());
        //if (Utils.DEBUG_LEVEL > 5) System.out.println("DB.setMenu([DBConfData])->conns[0] Server Id: " + this.sec.getServerID() + " ipv4:" + this.sec.getIPv4() + " ipv6:" + this.sec.getIPv6() + " Server Name:" + this.sec.getServerName() + " sid: " + this.sec.getSid() + " puerto:" + this.sec.getPuerto() + " usuario:" + this.sec.getUser() + " contraseña:" + this.sec.getPassword() + " DB Name:" + this.sec.getDBName());
        //if (Utils.DEBUG_LEVEL > 5) System.out.println("DB.setMenu([DBConfData])->conns[1] Server Id: " + data.getServerID() + " ipv4:" + data.getIPv4() + " ipv6:" + data.getIPv6() + " Server Name:" + data.getServerName() + " sid: " + data.getSid() + " puerto:" + data.getPuerto() + " usuario:" + data.getUser() + " contraseña:" + data.getPassword() + " DB Name:" + data.getDBName());  
    }

    public DB(int server, int dat) {
        this.sec.setConf();
        this.conns[0] = new Connection(this.sec.getServerID(), this.sec.getIPv4(), this.sec.getIPv6(), this.sec.getServerName(), this.sec.getSid(), this.sec.getPuerto(), this.sec.getUser(), this.sec.getPassword(), this.sec.getDBName());
        this.conns[1] = new Connection(this.data.getServerID(), this.data.getIPv4(), this.data.getIPv6(), this.data.getServerName(), this.data.getSid(), this.data.getPuerto(), this.data.getUser(), this.data.getPassword(), this.data.getDBName());

        /*if (Utils.DEBUG_LEVEL > 5) System.out.println("DB.setMenu([DBConfData])->conns[0] Server Id: " + this.sec.getServerID() + " ipv4:" + this.sec.getIPv4() + " ipv6:" + this.sec.getIPv6() + " Server Name:" + this.sec.getServerName() + " sid: " + this.sec.getSid() + " puerto:" + this.sec.getPuerto() + " usuario:" + this.sec.getUser() + " contraseña:" + this.sec.getPassword() + " DB Name:" + this.sec.getDBName());
         if (Utils.DEBUG_LEVEL > 5) System.out.println("DB.setMenu([server],[dat])->conns[1] Server Id: " + this.data.getServerID() + " ipv4:" + this.data.getIPv4() + " ipv6:" + this.data.getIPv6() + " Server Name:" + this.data.getServerName() + " sid: " + this.data.getSid() + " puerto:" + this.data.getPuerto() + " usuario:" + this.data.getUser() + " contraseña:" + this.data.getPassword() + " DB Name:" + this.data.getDBName());
         if (Utils.DEBUG_LEVEL > 5) System.out.println("DB.setMenu([server],[dat])->whicServer: " + server + " whichDat:" + dat);*/

        this.whichServer = server;
        this.whichDat = dat;
    }

    public synchronized boolean doDB(String query) {
        OracleDB oraDB=null;
        boolean bandera=false;
        try {
            //this.dbLock.lock();
            this.numOfRows = 0;
            this.numOfCols = 0;
            oraDB = new OracleDB(PropertiesUtil.getDbConexionProperties(PropertiesUtil.SERVER_KEY_IP),
                    PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_PORT),
                    PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_SID));
            //OracleDB oraDB = new OracleDB("localhost", "1521", "orcl");
            oraDB.connect(
                    PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER),
                    PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_PASSWORD));
            if (!oraDB.execute(query)) {
                this.resultado = new String[1][1];
                this.resultado[0][0] = "No hay registros";
                this.numOfRows = 1;
                this.numOfCols = 1;
                oraDB.close();
                return false;
            }
            
            this.resultado = new String[oraDB.getNumOfRow()][oraDB.getNumOfCol()];
            this.resultado = oraDB.getResult();
            this.numOfRows = oraDB.getNumOfRow();
            this.numOfCols = oraDB.getNumOfCol();
            this.columnNames = oraDB.getColumnNames();
            bandera= true;
        }catch(Exception exc){
            exc.printStackTrace();
        }
            finally {
            //this.dbLock.unlock();
            oraDB.close();
            oraDB=null;
        }
        return bandera;
    }

    public synchronized ResponseBean consultapendientes(String query) {
        ResponseBean results = null;
        try {
            OracleDB oraDB = new OracleDB(this.conns[this.whichServer].getServerIP("v4"), this.conns[this.whichServer].getPort(), this.conns[this.whichServer].getSID());
            oraDB.connect(this.conns[this.whichServer].getUser(), this.conns[this.whichServer].getPasswd());
            results = oraDB.consultapendientes(query);
            PendientesFleteBean[] bean = new PendientesFleteBean[oraDB.beangral.length];
            bean = oraDB.beangral;
            oraDB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public String[][] getResultado() {
        return this.resultado;
    }

    public int getNumOfRows() {
        return this.numOfRows;
    }

    public int getNumOfCols() {
        return this.numOfCols;
    }

    public String[] getColumnNames() {
        return this.columnNames;
    }
}
