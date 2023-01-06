/*    */ package com.onest.oracle;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class TestingDB
/*    */ {
/*    */   public static void main(String[] argv)
/*    */   {
/* 18 */     String str1 = "select t.clavelocalidad||'::Tienda'||'|'||TRIM(d.nombre)||'::TIENDA'||'|'||TRIM(o.tiporuta)||'::RUTA'||'|'||count(t.clavetarima)||'::Cajas'||'|'||zt.centroregional||'::ZONA' from  tarima t inner join etiquetaproducto et on et.claveetiqueta = t.clavetarima inner join destino d on d.clavedestino = t.clavelocalidad inner join onsys_porteo o on o.clavedestino = t.clavelocalidad inner join zonas_tienda zt on zt.clavedestino = t.clavelocalidad where  t.clavetarima = et.claveetiqueta and d.clavedestino = t.clavelocalidad and o.clavedestino = t.clavelocalidad and t.loteinterno != 'CONTINGENCIA'  group by t.clavelocalidad,d.nombre, o.tiporuta, zt.centroregional order by to_number(t.clavelocalidad)";
/* 19 */     String str2 = "||'::FECHA'||'|'||";
/*    */ 
/* 21 */     System.out.println(str1.replaceAll("[e]{1}[n]{1}[d]{1}[|]{2}", "end,").replaceAll("[']*[|]{1,}", "@").replaceAll("[@]{3}", ";").replaceAll("[@]{1}[']{1}[:]{2}[a-zA-z]*[@]{2}[']{1}", "'").replaceAll("[@]{1}[']{1}[:]{2}[a-zA-Z]*[']{1}[^\\s]", "").replaceAll("[@]{1}[']{1}[:]{2}", " as ").replaceAll("[;]{1}", ",").replaceAll("['][ ]*else", " else ").replaceAll("['][ ]*end [@]{1}", " end, ").replaceAll("['][ ]*end", " end ").replaceAll("as ORDEN_PROVEEDOR,", "||'-'||").replaceAll("['][ ]*(from|From|FROM|fRom|frOm)", " from ").replaceAll("(rowid|ROWID|Rowid|RowID)[\\s]as[\\s]ROW_ID([\\s]|),", "").replaceAll("[@]['][%]", "||'%'").replaceAll("then[ ]['][ ]else", " then '' else ").replaceAll("[@]['][-][@]", "||'-'||").replaceAll("['][-]{3}", "'---'").replaceAll("['][\\[][@]", "'[").replaceAll("[@]['][\\]][ ][@]", "]'||").replaceAll("[-]{3}[']{2}", "---'"));
/*    */   }
/*    */ }

/* Location:           C:\Users\miguel\Documents\NetBeansProjects\Hiltixy\lib\onest-common-utils-0.0.1.jar
 * Qualified Name:     com.onest.oracle.TestingDB
 * JD-Core Version:    0.6.2
 */