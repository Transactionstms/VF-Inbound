/*    */ package com.onest.security.menu;
/*    */ 
/*    */ public class SubMenu
/*    */ {
/*    */   private int idSubmenu;
/*    */   private String etiqueta;
/*    */   private String ruta;
/*    */   private String owner;
/*    */   private String groupID;
/*    */   private String OA;
/*    */   private String GA;
/*    */   private String WA;
/*    */   private int idMenu;
/*    */   private String type;
/*    */ 
/*    */   public SubMenu(int idMenu, int idSubMenu, String etiqueta, String ruta, String owner, String groupID, String OA, String GA, String WA, String type)
/*    */   {
/* 24 */     this.idSubmenu = idSubMenu;
/* 25 */     this.idMenu = idMenu;
/* 26 */     this.etiqueta = etiqueta;
/* 27 */     this.ruta = ruta;
/* 28 */     this.owner = owner;
/* 29 */     this.groupID = groupID;
/* 30 */     this.OA = OA;
/* 31 */     this.GA = GA;
/* 32 */     this.WA = WA;
/* 33 */     this.type = type;
/*    */   }
/*    */   public int getIdSubmenu() {
/* 36 */     return this.idSubmenu;
/*    */   }
/*    */   public String getEtiqueta() {
/* 39 */     return this.etiqueta;
/*    */   }
/*    */   public String getRuta() {
/* 42 */     return this.ruta;
/*    */   }
/*    */   public String getOwner() {
/* 45 */     return this.owner;
/*    */   }
/*    */   public int getIdMenu() {
/* 48 */     return this.idMenu;
/*    */   }
/*    */   public String getType() {
/* 51 */     return this.type;
/*    */   }
/*    */ }

/* Location:           C:\Users\OS7-PRO\Desktop\onest-common-utils-0.0.1.jar
 * Qualified Name:     com.onest.security.menu.SubMenu
 * JD-Core Version:    0.6.0
 */