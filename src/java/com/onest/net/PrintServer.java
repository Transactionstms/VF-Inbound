/*     */ package com.onest.net;
/*     */ 
/*     */ import com.onest.io.Files;
/*     */ import com.onest.misc.Utils;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.Socket;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.swing.JTextArea;
/*     */ 
/*     */ public class PrintServer
/*     */   implements Runnable
/*     */ {
/*     */   private PrintWriter out;
/*     */   private BufferedReader in;
/*  22 */   private Socket socket = null;
/*     */   private JTextArea eventLog;
/*     */ 
/*     */   public PrintServer(JTextArea eventLog, Socket s)
/*     */   {
/*  25 */     this.eventLog = eventLog;
/*  26 */     this.socket = s;
/*     */   }
/*     */   public synchronized void run() {
/*  29 */     String inLine = "";
/*     */     try {
/*  31 */       this.out = new PrintWriter(this.socket.getOutputStream(), true);
/*  32 */       this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
/*  33 */       this.eventLog.append("[Server] : Peticion de impresion desde " + this.socket.getInetAddress() + "\n");
/*  34 */       while ((inLine = this.in.readLine()) != null) {
/*  35 */         doPrint(inLine);
/*     */       }
/*  37 */       this.eventLog.append("[Server] : La impresi�n ha sido enviada\n");
/*  38 */       this.out.println("0");
/*     */     } catch (IOException e) {
/*  40 */       this.eventLog.append("[Server] : Error de E/S  " + e.toString() + "\n");
/*  41 */       this.out.println("1");
/*  42 */       return;
/*     */     } catch (NullPointerException e) {
/*  44 */       this.eventLog.append("[Server] : Error de un nulo " + e.toString() + "\n");
/*  45 */       this.out.println("1");
/*  46 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void doPrint(String str4print) {
/*  51 */     Pattern pattern = Pattern.compile("\\|:\\|");
/*  52 */     String[] items = pattern.split(str4print);
/*     */ 
/*  54 */     if (Utils.DEBUG_LEVEL > 5) this.eventLog.append("[Debug] : Numero de parametros " + items.length + "\n");
/*  55 */     if (items.length >= 7) {
/*  56 */       if (Utils.DEBUG_LEVEL > 5) this.eventLog.append("[Debug] : items [5], items[0], hash " + items[5] + "," + items[0] + "," + Utils.getOnsysHash() + "\n");
/*  57 */       if (Utils.DEBUG_LEVEL > 5) this.eventLog.append("[Debug] : items[5].equals(\"1\") && Utils.getOnsysHash().equals(items[0]) " + ((items[5].equals("1")) && (Utils.getOnsysHash().equals(items[0]))) + "\n");
/*  58 */       if ((items[5].equals("1")) && (Utils.getOnsysHash().equals(items[0]))) {
/*  59 */         Files.setDir(Files.PRINT_DIR);
/*  60 */         Files.write2File(items[6], Files.PRINT_DIR + items[1]);
/*     */         Process proc;
/*  61 */         if (isWindows())
/*     */           try
/*     */           {
/*  64 */             if (Utils.DEBUG_LEVEL > 5) this.eventLog.append("[Debug] : El servidor de impresi�n est� en windows.\n");
/*  65 */             String netCmd = "cmd /c net use LPT2 " + items[4] + "  /user:\"" + items[2] + "\" " + items[3];
/*  66 */             String copyCmd = "cmd /c copy " + Files.getFileName() + " LPT2";
/*  67 */             if (Utils.DEBUG_LEVEL > 5) this.eventLog.append("[Debug] : Comandos ejecutados \"" + netCmd + "\" \"" + copyCmd + "\"" + "\n");
/*  68 */             proc = Runtime.getRuntime().exec(netCmd);
/*  69 */             proc = Runtime.getRuntime().exec(copyCmd);
/*     */           } catch (IOException e) {
/*  71 */             this.eventLog.append("[Server] : Error de E/S  ejecutando comando en Windows - " + e.toString() + "\n");
/*  72 */             this.out.println("1");
/*     */           }
/*     */         else
/*     */           try
/*     */           {
/*  77 */             if (Utils.DEBUG_LEVEL > 5) this.eventLog.append("[Debug] : El servidor de impresi�n est� en linux.\n");
/*  78 */             String copyCmd = "smbclient " + items[4].replaceAll("\\\\", "/") + " -U " + items[2].replaceAll("\\s", "\\\\ ") + '%' + items[3] + " -c " + '"' + "print " + Files.getFileName() + '"';
/*  79 */             if (Utils.DEBUG_LEVEL > 5) {
/*  80 */               this.eventLog.append("[Debug] : Comandos ejecutados \"" + copyCmd + "\"" + "\n");
/*  81 */               System.out.println("[Debug] : Comandos ejecutados \"" + copyCmd + "\"" + "\n");
/*     */             }
/*  83 */             proc = Runtime.getRuntime().exec(new String[] { "sh", "-c", copyCmd });
/*     */           } catch (IOException e) {
/*  85 */             this.eventLog.append("[Server] : Error de E/S  ejecutando comando en Linux - " + e.toString() + "\n");
/*  86 */             this.out.println("1");
/*     */           }
/*     */       }
/*     */       else
/*     */       {
/*  91 */         this.eventLog.append("[Server] : No esta implementada la impresi�n a impresoras conectadas directo a la red \n");
/*  92 */         this.out.println("1");
/*     */       }
/*     */     } else {
/*  95 */       this.eventLog.append("[Server] : La cadena enviada no cumple con los est�ndares - " + str4print + "\n");
/*  96 */       this.out.println("1");
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean isWindows() {
/* 101 */     return System.getProperty("os.name").toLowerCase().indexOf("windows") != -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\OS7-PRO\Desktop\onest-common-utils-0.0.1.jar
 * Qualified Name:     com.onest.net.PrintServer
 * JD-Core Version:    0.6.0
 */