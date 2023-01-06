package com.onest.security;

import com.onest.misc.Cuenta;
import com.onest.misc.Utils;
import com.onest.net.Connection;
import com.onest.oracle.DB;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authenticate {

    private String userName = "";
    private String user = "";
    private String groups = "";
    private String mainGroup = "";
    private int isSuperUser = 0;
    private String userNID;
    private String[][] resultado;
    private String[][] resultadop;
    private Connection[] conn;
    private Cuenta[] cta;
    private String[] equ;
    private String sysProfile = "";
    private String pwd = "";
    private boolean setGroup = true;
    private boolean setServers = true;
    private boolean setCuenta = true;
    private boolean setEquipment = true;

    public boolean verifyLogin(String user, String password) {
        this.user = user;
        DB db = new DB(DB.SECURITY, DB.SECURITY);
        String query = "select u.passwd, u.user_name, u.super_user, u.user_nid from broker_passwd u where u.user_id = '" + user + "'";
        String ps = "SELECT ontms_db_security.encode('" + password + "') FROM DUAL";
        
        if (db.doDB(query)) {
            this.resultado = db.getResultado();
            this.userName = this.resultado[0][1];
            this.isSuperUser = Integer.valueOf(this.resultado[0][2]).intValue();
            this.userNID = this.resultado[0][3];
            if (db.doDB(ps)) {
                this.resultadop = db.getResultado();
                this.pwd = this.resultadop[0][0];
                if (this.pwd.equals(this.resultado[0][0])) {
                    if (this.setGroup) {
                        setGroup(user);
                    }
                    if (this.setServers) {
                        setServers(user);
                    }
                    if (this.setCuenta) {
                        setCta(user);
                    }
                    if (this.setEquipment) {
                        setEquipment(user);
                    }
                    return true;
                }
            }
            
            return false;
        }

        
        return false;
    }

    public void setFlags(boolean setGroup, boolean setServers, boolean setCuenta, boolean setEquipment) {
        this.setGroup = setGroup;
        this.setServers = setServers;
        this.setCuenta = setCuenta;
        this.setEquipment = setEquipment;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUser() {
        return this.user;
    }

    public String encrypt(String phrase) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] in = phrase.getBytes();
            byte[] out = digest.digest(in);
            return new String(BASE64.encode(out));
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        }
    }

    public String decrypt(String phrase) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] out = BASE64.decode(phrase);
            return new String(out);
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        }
    }

    public boolean isSuperUser() {
        return this.isSuperUser == 1;
    }

    private void setGroup(String user) {
        this.groups = "";
        String innerQuery = "select grupo_id, main_group from broker_usuario_grupo where user_id = '" + user + "'";
        if (Utils.DEBUG_LEVEL > 5) {
            System.out.println("Authenticate.setGroup([usuario])-> Query: " + innerQuery);
        }
        DB db = new DB(DB.SECURITY, DB.SECURITY);
        if (db.doDB(innerQuery)) {
            for (String[] row : db.getResultado()) {
                this.groups = (this.groups + row[0] + ",");
                if (row[1].equals("1")) {
                    this.mainGroup = row[0];
                }
            }
        }
        if (this.groups.length() > 0) {
            this.groups = this.groups.substring(0, this.groups.length() - 1);
        }
    }

    public String getGroups() {
        return this.groups;
    }

    public String getMainGroup() {
        return this.mainGroup;
    }

    private void setServers(String user) {
        String innerQuery = "select bs.serv_id, serv_app_ipv4, serv_app_ipv6, serv_app_name,db_sid, db_port, db_user, db_passwd, db_name from broker_servidor bs inner join broker_servidor_db bds on bds.serv_id = bs.serv_id inner join broker_db bdb on bdb.db_id = bds.db_id inner join broker_grupo_servidor_db bus on bus.serv_id = bs.serv_id and bus.db_id = bdb.db_id inner join broker_usuario_grupo bug on bug.grupo_id = bus.grupo_id where bug.user_id = '" + user + "' " + "group by bs.serv_id, serv_app_ipv4, serv_app_ipv6, serv_app_name,db_sid, db_port, db_user, db_passwd, db_name  order by db_name";

        if (Utils.DEBUG_LEVEL > 5) {
            System.out.println("Authenticate.setServers([usuario])-> Query: " + innerQuery);
        }
        DB db = new DB(DB.SECURITY, DB.SECURITY);
        if (db.doDB(innerQuery)) {
            this.conn = new Connection[db.getNumOfRows()];
            int i = 0;
            for (String[] row : db.getResultado()) {
                this.conn[i] = new Connection(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]);
                i++;
            }
        }
    }

    private void setCta(String user) {
        String query = "select ucb.cuenta_id, ucb.bodega_id, cta.cuenta_nombre, b.bodega_nombre, cta.cuenta_valida_lote, cta.cuenta_valida_identificador from broker_grupo_bodega_cuenta ucb inner join broker_cuenta cta on cta.cuenta_id = ucb.cuenta_id inner join broker_bodega b on ucb.bodega_id = b.bodega_id inner join broker_usuario_grupo bug on bug.grupo_id = ucb.grupo_id where bug.user_id = '" + user + "' " + "group by ucb.cuenta_id, ucb.bodega_id, cta.cuenta_nombre, b.bodega_nombre, cta.cuenta_valida_lote, cta.cuenta_valida_identificador order by cta.cuenta_nombre ";

        if (Utils.DEBUG_LEVEL > 5) {
            System.out.println("Authenticate.setCta([usuario])-> Query: " + query);
        }
        DB db = new DB(DB.SECURITY, DB.SECURITY);
        if (db.doDB(query)) {
            this.cta = new Cuenta[db.getNumOfRows()];
            int i = 0;
            for (String[] row : db.getResultado()) {
                this.cta[i] = new Cuenta(row[0], row[1], row[2], row[3], row[4], row[5]);
                i++;
            }
        }
    }

    private void setEquipment(String user) {
        String query = "select broker_equipo_id from broker_passwd_equipo where user_id = '" + user + "' ";
        if (Utils.DEBUG_LEVEL > 5) {
            System.out.println("Authenticate.setEquipment([usuario])-> Query: " + query);
        }
        DB db = new DB(DB.SECURITY, DB.SECURITY);
        if (db.doDB(query)) {
            this.equ = new String[db.getNumOfRows()];
            int i = 0;
            for (String[] row : db.getResultado()) {
                this.equ[i] = row[0];
                i++;
            }
        }
    }

    public Cuenta[] getCta() {
        return this.cta;
    }

    public Connection[] getServers() {
        return this.conn;
    }

    public String[] getEquipment() {
        return this.equ;
    }

    public int howManyConn() {
        return this.conn == null ? 0 : this.conn.length;
    }

    public int howManyAcct() {
        return this.cta == null ? 0 : this.cta.length;
    }

    public int howManyEquip() {
        return this.equ == null ? 0 : this.equ.length;
    }

    public String getNID() {
        return this.userNID;
    }
}