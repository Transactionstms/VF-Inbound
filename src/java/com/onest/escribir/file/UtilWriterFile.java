/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.escribir.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author RicardoMartinez
 */
public class UtilWriterFile {

    public String fileTxt(List<String> errors) {

        String salida = com.onest.util.Util.INICIO_RUTA+"archivo_errores.txt";
        File archivo = new File(salida);

        try {
            FileWriter fw = new FileWriter(salida);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int x = 0; x < errors.size(); x++) {
                pw.println("Error en la linea " + errors.get(x) + " \n");
            }
            pw.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }
}
