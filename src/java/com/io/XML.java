package com.io;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import com.onest.oracle.OracleDB;

public class XML {

    private StringBuffer xmlStr = null;
    private boolean flagExec = false;
    private ResultSet resQry;
    private boolean plain = false;
    private String cols = null;
    private int i = 0;

    public XML(String query, com.onest.oracle.DBConf dbData, String titulo, boolean plain, String cols,String type) {
        this.xmlStr = new StringBuffer();
        this.plain = plain;
        this.cols = cols;
        type = type==null?"":type;
        ADO dbPtr = new ADO(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid(), dbData.getUser(), dbData.getPassword(), "oracle");
        try {
            if ("FUNCTION".equalsIgnoreCase(type)) {
                OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
                oraDB.connect(dbData.getUser(), dbData.getPassword());

                String[] outParam = new String[]{""};
                String[] inParam = new String[]{};
                query = query + ";END;";
                String salida = oraDB.execStore(query, outParam, inParam, new int[]{OracleDB.ORA_VARCHAR});
                  this.xmlStr.append("<report>\n");
                    this.xmlStr.append("<encabezado>\n");
                    this.xmlStr.append("<logo>img/onsys.png</logo>\n");
                    this.xmlStr.append("<titulo>".concat(titulo != null ? titulo : "").concat("</titulo>\n"));
                    this.xmlStr.append("</encabezado>\n");
                    this.xmlStr.append("<contenido>\n");
                    this.xmlStr.append("\n<row><![CDATA[".concat(salida).concat("]]></row>"));
                    this.xmlStr.append("\n</contenido>");
                    this.xmlStr.append("\n</report>");
                oraDB.close();

            } else  if (("INSERT".equalsIgnoreCase(type)) || 
                    ("DELETE".equalsIgnoreCase(type) ) || 
                    ("UPDATE".equalsIgnoreCase(type))) {
                this.flagExec = dbPtr.execute(query);
                this.xmlStr.append("<report>\n");
                this.xmlStr.append("<total_rows>0</total_rows>\n");
                this.xmlStr.append("<total_cols>0</total_cols>\n");
                this.xmlStr.append("<query_result>".concat(String.valueOf(this.flagExec)).concat("</query_result>"));
                this.xmlStr.append("\n</report>");
            } else {
                this.resQry = dbPtr.executeprepared(query);
                this.resQry.beforeFirst();
                if (this.resQry.next()) {
                    this.resQry.beforeFirst();
                    this.xmlStr.append("<report>\n");
                    this.xmlStr.append("<encabezado>\n");
                    this.xmlStr.append("<logo>img/onsys.png</logo>\n");
                    this.xmlStr.append("<titulo>".concat(titulo!=null?titulo:"").concat("</titulo>\n"));
                    this.xmlStr.append("</encabezado>\n");
                    this.xmlStr.append("<contenido>\n");
                    this.i = 1;                    
                    int longitud = 0;
                    try {
                        longitud = Integer.valueOf(this.cols);
                    } catch (Exception e) {
                        longitud = 1;
                    }
                    while (this.resQry.next()) {
                        if(plain){
                           for(int next=1;next<=longitud;next++){
                               String col = this.resQry.getString(next)==null?"":this.resQry.getString(next).trim();
                               col = col.indexOf("&") != -1 ? col.replace("&", "&#38;") : col;
                               this.xmlStr.append("\n<row><![CDATA[".concat(col).concat("]]></row>"));
                           } 
                        } else {
                           this.xmlStr.append("\n<row><![CDATA[".concat(this.resQry.getString(1).indexOf("&") != -1 ? this.resQry.getString(1).replace("&", "&#38;") : this.resQry.getString(1)).concat("]]></row>"));   
                        }                        
                        
                        /*try {
                            if (this.i == 1000) {
                                Thread.sleep(1000L);
                            }
                        } catch (InterruptedException e) {
                        }*/
                        this.i += 1;
                    }
                    this.resQry.previous();
                    this.xmlStr.append("\n<total_cols>".concat(getNumOfColsString(this.resQry.getString(1))).concat("</total_cols>"));
                    try{
                        //getColumnNames(this.resQry.getString(1));
                        this.xmlStr.append("\n<columna>".concat(String.valueOf(
                                this.resQry.getMetaData().getColumnCount())).concat("</columna>"));
                    }catch(Exception e){
                        
                    }
                    this.xmlStr.append("\n<length>".concat(getNumOfColsString(this.resQry.getString(1))).concat("</length>"));
                    this.resQry.last();
                    this.xmlStr.append("\n<total_rows>".concat(String.valueOf(this.resQry.getRow() == 0 ? 1 : this.resQry.getRow())).concat("</total_rows>"));
                    this.xmlStr.append("\n</contenido>");
                    this.xmlStr.append("\n</report>");
                } else {
                    if (this.xmlStr.indexOf("<report>") == -1) {
                        this.xmlStr.append("<report>\n");
                    }
                    this.xmlStr.append("<total_rows>0</total_rows>\n");
                    this.xmlStr.append("<total_cols>0</total_cols>\n");
                    this.xmlStr.append(("<query_fails>" + query.replace("'||'|'||", ",").replace("||'::", " as ").replace("' from", " from").replace("rowid||'::ROWID'||", "").replace("<", "menorque").replace(">", "mayorque") + "</query_fails>"));
                    this.xmlStr.append("\n</report>");
                }
            }
        } catch (ClassNotFoundException e) {
            if (this.xmlStr.indexOf("<report>") == -1) {
                this.xmlStr.append("<report>\n");
            }
            this.xmlStr.append("<total_rows>0</total_rows>\n");
            this.xmlStr.append("<total_cols>0</total_cols>\n");
            this.xmlStr.append((this.xmlStr + "<query_fails>" + e.toString() + "</query_fails>"));
            this.xmlStr.append("\n</report>");
        } catch (SQLException e) {
            if (this.xmlStr.indexOf("<contenido>") != -1) {
                this.xmlStr.append("</contenido>\n");
            }
            if (this.xmlStr.indexOf("<report>") == -1) {
                this.xmlStr.append("<report>\n");
            }
            this.xmlStr.append("<total_rows>0</total_rows>\n");
            this.xmlStr.append("<total_cols>0</total_cols>\n");

            this.xmlStr.append((this.xmlStr + "<query_fails>" + e.toString() + "</query_fails>"));
            this.xmlStr.append("\n</report>");
        }
    }

    public String getXML() {
        return this.xmlStr.toString();
    }

    private int getNumOfCols(String row) {
        Pattern p1 = Pattern.compile("\\|");
        String[] items = p1.split(row);
        return items.length;
    }
    
    private String getNumOfColsString(String row) {
        return String.valueOf(getNumOfCols(row));
    }
    private void getColumnNames(String row) {
        Pattern p1 = Pattern.compile("[\\|]{1}+");
        Pattern p2 = Pattern.compile("[:]{2}+");

        String[] items = p1.split(row);
        for (String data : items) {
            String colName;
            try {
                String[] items2 = p2.split(data);
                items2 = p2.split(data);
                colName = items2[1];
            } catch (NullPointerException e) {                
                String[] items2 = p2.split(data);
                colName = items2[1];
            }
            this.xmlStr.append("\n<columna>".concat(colName).concat("</columna>"));
        }
    }
}