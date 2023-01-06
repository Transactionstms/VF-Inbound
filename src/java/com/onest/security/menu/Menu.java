/*    */ package com.onest.security.menu;
/*    */ 
/*    */ public class Menu
/*    */ {
/*    */   private int idMenu;
/*    */   private String label;
/*    */   private String path;
/*    */   private String owner;
/*    */   private String groupID;
/*    */   private String OA;
/*    */   private String GA;
/*    */   private String WA;
/*    */   private String type;
/*    */   private SubMenu[] submenu;
/*    */ 
/*    */   public Menu(int idMenu, String label, String path, String owner, String groupID, String OA, String GA, String WA, String type)
/*    */   {
/* 25 */     this.idMenu = idMenu;
/* 26 */     this.label = label;
/* 27 */     this.path = path;
/* 28 */     this.owner = owner;
/* 29 */     this.groupID = groupID;
/* 30 */     this.OA = OA;
/* 31 */     this.GA = GA;
/* 32 */     this.WA = WA;
/* 33 */     this.type = type;
/*    */   }
/*    */   public void setSubmenu(int[] idsSubmenu, String[] etiquetas, String[] rutas, String[] owner, String[] groupID, String[] OA, String[] GA, String[] WA, String[] types) {
/* 36 */     this.submenu = new SubMenu[etiquetas.length];
/* 37 */     for (int i = 0; i < idsSubmenu.length; i++)
/* 38 */       this.submenu[i] = new SubMenu(this.idMenu, idsSubmenu[i], etiquetas[i], rutas[i], owner[i], groupID[i], OA[i], GA[i], WA[i], types[i]);
/*    */   }
/*    */ 
/*    */   public String getEtiqueta() {
/* 42 */     return this.label;
/*    */   }
/*    */   public String getRuta() {
/* 45 */     return this.path;
/*    */   }
/*    */   public String getPermission() {
/* 48 */     return this.OA + this.GA + this.WA;
/*    */   }
/*    */   public int getIdMenu() {
/* 51 */     return this.idMenu;
/*    */   }
/*    */   public SubMenu[] getSubmenus() {
/* 54 */     return this.submenu;
/*    */   }
/*    */   public String getType() {
/* 57 */     return this.type;
/*    */   }
/*    */ }

/* Location:           C:\Users\OS7-PRO\Desktop\onest-common-utils-0.0.1.jar
 * Qualified Name:     com.onest.security.menu.Menu
 * JD-Core Version:    0.6.0
 */