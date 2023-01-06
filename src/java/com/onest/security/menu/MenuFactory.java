package com.onest.security.menu;

import com.onest.misc.Utils;
import com.onest.oracle.DB;
import java.io.PrintStream;

public class MenuFactory {

    private String user;
    private String groups;
    private Menu[] menu;
    private String query;
    private int[] subMenuID;
    private String[] subMenuLbl;
    private String[] subMenuPath;
    private String[] subMenuMenuID;
    private String[] subMenuOwner;
    private String[] subMenuGroupID;
    private String[] subMenuOA;
    private String[] subMenuGA;
    private String[] subMenuWA;
    private String[] types;
    private boolean isSuperUser;
    private boolean isOk = true;
    private String sysProfile;

    public MenuFactory(String user, boolean isSuperUser, String groups, String sysProfile) {
        this.user = user;
        this.isSuperUser = isSuperUser;
        this.groups = groups;
        this.sysProfile = sysProfile;
        setMenu();
    }

    private void setMenu() {
        DB db = new DB(DB.SECURITY, DB.SECURITY);
        this.query = ("select distinct(bm.MENU_ID), ETIQUETA, RUTA,OWNER, bm.GROUP_ID, OWNER_ACCESS, GROUP_ACCESS, WORLD_ACCESS, TIPO from BROKER_MENU bm left join BROKER_GRUPO_MENU bgm on bgm.menu_id = bm.menu_id where TIPO = '" + this.sysProfile + "' ");

        if (!this.isSuperUser) {
            this.query = (this.query + "and ((OWNER_ACCESS like 'r%' and OWNER = '" + this.user + "') or bgm.GROUP_ID in (" + this.groups + ") and GROUP_ACCESS like 'r%')");
        }
        this.query += " order by ETIQUETA ";
       
        if (db.doDB(this.query)) {
            String[][] resultado = db.getResultado();
            int index = 0;
            this.menu = new Menu[db.getNumOfRows()];
            for (String[] row : resultado) {
                this.menu[index] = new Menu(Integer.valueOf(row[0]).intValue(), row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]);
                defineSubMenu(Integer.valueOf(row[0]).intValue(), row[8]);
                if (this.subMenuLbl != null) {
                    this.menu[index].setSubmenu(this.subMenuID, this.subMenuLbl, this.subMenuPath, this.subMenuOwner, this.subMenuGroupID, this.subMenuOA, this.subMenuGA, this.subMenuWA, this.types);
                }
                clearSubmenu();
                index++;
            }
        } else {
            this.menu = new Menu[1];
            this.menu[0] = null;
            this.isOk = false;
        }
    }

    private void defineSubMenu(int menuID, String tipo) {
        DB db = new DB(DB.SECURITY, DB.SECURITY);
        this.query = ("select distinct(bs.SUBMENU_ID), ETIQUETA, RUTA,MENU_ID, OWNER, bs.GROUP_ID, OWNER_ACCESS, GROUP_ACCESS, WORLD_ACCESS from  broker_submenu bs left join broker_grupo_submenu bgs on bgs.submenu_id = bs.submenu_id where MENU_ID = " + menuID);

        if (!this.isSuperUser) {
            this.query = (this.query + " and ((OWNER = '" + this.user + "'  and OWNER_ACCESS like 'r%') or bgs.GROUP_ID in (" + this.groups + ") and GROUP_ACCESS like 'r%') ");
        }
        this.query += " order by ETIQUETA ";
        
        if (db.doDB(this.query)) {
            String[][] resultado = db.getResultado();
            int index = 0;
            this.subMenuID = new int[db.getNumOfRows()];
            this.subMenuLbl = new String[db.getNumOfRows()];
            this.subMenuPath = new String[db.getNumOfRows()];
            this.subMenuMenuID = new String[db.getNumOfRows()];
            this.subMenuOwner = new String[db.getNumOfRows()];
            this.subMenuGroupID = new String[db.getNumOfRows()];
            this.subMenuOA = new String[db.getNumOfRows()];
            this.subMenuGA = new String[db.getNumOfRows()];
            this.subMenuWA = new String[db.getNumOfRows()];
            this.types = new String[db.getNumOfRows()];
            for (String[] row : resultado) {
                this.subMenuID[index] = Integer.valueOf(row[0]).intValue();
                this.subMenuLbl[index] = row[1];
                this.subMenuPath[index] = row[2];
                this.subMenuMenuID[index] = row[3];
                this.subMenuOwner[index] = row[4];
                this.subMenuGroupID[index] = row[5];
                this.subMenuOA[index] = row[6];
                this.subMenuGA[index] = row[7];
                this.subMenuWA[index] = row[8];
                this.types[index] = tipo;
                index++;
            }
        }
    }

    public Menu[] getMenu() {
        return this.menu;
    }

    public boolean menuIsOk() {
        return this.isOk;
    }

    private void clearSubmenu() {
        this.subMenuID = null;
        this.subMenuLbl = null;
        this.subMenuPath = null;
        this.subMenuMenuID = null;
        this.subMenuOwner = null;
        this.subMenuGroupID = null;
        this.subMenuOA = null;
        this.subMenuGA = null;
        this.subMenuWA = null;
        this.types = null;
    }
}

/* Location:           C:\Users\OS7-PRO\Desktop\onest-common-utils-0.0.1.jar
 * Qualified Name:     com.onest.security.menu.MenuFactory
 * JD-Core Version:    0.6.0
 */