/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.security;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
public class AlarmaService {

    public void execute(AlarmaBean bean,boolean flag) {
        Socket echoSocket = null;
        //ScheduledExecutorService scheduler = null;
        if (bean != null) {
            try {
                echoSocket = new Socket(bean.getAlarmaIp(), Integer.parseInt(bean.getAlarmaPuerto()));
                //String[] result = flag?bean.getAlarmaOn().split(","):bean.getAlarmaOff().split(",");
                //for (String next : result) {                    
                     if(flag){
                         bean.setCurrentCmd(bean.getAlarmaOn());
                     } else {
                         bean.setCurrentCmd(bean.getAlarmaOff());
                     }
                    Alarma alarmaOn = new Alarma(bean, echoSocket);
                    Thread nextThread = new Thread(alarmaOn);
                    nextThread.setPriority(Thread.MAX_PRIORITY);
                    nextThread.run();
             //   }
                /*
                String[] off = bean.getAlarmaOff().split(",");
                for (String nextOff : off) {
                    bean.setCurrentCmd(nextOff);
                    Alarma alarmaOff = new Alarma(bean, echoSocket);
                    scheduler = Executors.newScheduledThreadPool(3);
                    ScheduledFuture handle = scheduler.scheduleAtFixedRate(alarmaOff, 0, 10, TimeUnit.SECONDS);
                }*/
            } 
            catch (IOException e) {
                e.printStackTrace();
            } 
            
            finally {
                try {
                    echoSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //scheduler.shutdown();
            }
        }
    }
}

class Alarma implements Runnable {
    private AlarmaBean abean = null;
    private Socket echoSocket = null;

    public Alarma(AlarmaBean bean, Socket socket) {
        this.abean = bean;
        this.echoSocket = socket;
    }

    /**
     * 
     */
    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            if(echoSocket==null || echoSocket.isClosed()){
                echoSocket = new Socket(this.abean.getAlarmaIp(), Integer.valueOf(this.abean.getAlarmaPuerto()));
            }
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            out.write(this.abean.getCurrentCmd());
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
