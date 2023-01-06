/*    */ package com.onest.misc;
/*    */ 
/*    */ import com.onest.security.Authenticate;
/*    */ import java.lang.reflect.Array;
/*    */ 
/*    */ public class Utils
/*    */ {
/* 15 */   public static int DEBUG_LEVEL = 0;
/* 16 */   public static String ORA_CONF = "oraconf";
/*    */ 
/* 18 */   public static Object resizeArray(Object oldArray, int newSize) { int oldSize = Array.getLength(oldArray);
/* 19 */     Class elementType = oldArray.getClass().getComponentType();
/* 20 */     Object newArray = Array.newInstance(elementType, newSize);
/* 21 */     int preserveLength = Math.min(oldSize, newSize);
/* 22 */     if (preserveLength > 0)
/* 23 */       System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
/* 24 */     return newArray; }
/*    */ 
/*    */   public static String getOnsysHash() {
/* 27 */     Authenticate auth = new Authenticate();
/* 28 */     int b = Integer.valueOf(Calendario.getYear()).intValue();
/* 29 */     int a = Integer.valueOf(Calendario.getMonth() + Calendario.getDayOfMonth()).intValue();
/* 30 */     int c = Integer.valueOf(Calendario.getDayOfMonth()).intValue();
/* 31 */     return auth.encrypt(String.valueOf((-b + Math.sqrt(Math.pow(b, 2.0D) - 4 * a * c)) / (2 * a)));
/*    */   }
/*    */ }

/* Location:           C:\Users\OS7-PRO\Desktop\onest-common-utils-0.0.1.jar
 * Qualified Name:     com.onest.misc.Utils
 * JD-Core Version:    0.6.0
 */