package com.onest.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Ernesto L�pez Hdez
 * 
 */
public class FileIO {

	/**
	 * 
	 * @param pathFile
	 * @param bytes
	 */
	public static void copy(String pathFile, byte[] bytes) {
		try {
				
			FileOutputStream fileOutputStream = new FileOutputStream(pathFile);

			fileOutputStream.write(bytes);

			fileOutputStream.close();

		} catch (IOException e) {
			System.out.println("IOException --> " + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param pathFile
	 * @return
	 */
	public static String readLine(String pathFile){
		
		String txt = null;
	
		BufferedReader entrada;
		
		try {
			entrada = new BufferedReader( new FileReader( pathFile ) );
			txt =  entrada.readLine();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException -->> " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException -->> " + e.getMessage());
		}
	    
		return txt;
		
	}

	/**
	 * 
	 * @param pathFile
	 */
	public static void delete(String pathFile) {

		File file = new File(pathFile);

		if (file.exists()) {
			file.delete();
		}

	}

	/**
	 * 
	 * @param pathFile
	 * @return
	 */
	public static byte[] getBytes(String pathFile) {
		
		byte[] resultado = null;

		try {
			if (pathFile != null) {
			
				FileInputStream fileInputStream = new FileInputStream( new File(pathFile) );

				resultado = new byte[fileInputStream.available()];

				int len = 0;

				while ((len = fileInputStream.read(resultado)) > 0)
					;
				//System.out.print(len);
				
				fileInputStream.close();

			}
		} catch (IOException e) {
			System.out.println("IOException -->  " + e.getMessage());
		}

		return resultado;
	}
	
	/**
	 * 
	 * @param response
	 * @param archivo
	 * @param bytes
	 * @return
	 */
	public static void download(HttpServletResponse response , String archivo , byte[] bytes ) {
	
		//response.setContentType( attachment.getContentType());
		response.setContentType("application/octet-stream");
        response.addHeader("Content-disposition", "attachment; filename=\"" + archivo +"\"");
		
        try {
			ServletOutputStream os = response.getOutputStream();
			os.write(bytes);
			os.flush();
			os.close();
			 //FacesContext.getCurrentInstance().responseComplete();
		} catch(Exception e) {
			System.out.println("Exception -->> " + e.getMessage());
		}
		
	}
	
	
}
