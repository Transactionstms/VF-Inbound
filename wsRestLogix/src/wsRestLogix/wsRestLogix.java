/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsRestLogix;
import java.io.IOException;
import com.tacts.dao.TmsCustoms;
        
/**
 *
 * @author grecendiz
 */
public class wsRestLogix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception { 

        TmsCustoms obj = new TmsCustoms();

        try {
            
            String consumoWSLOGIX = obj.consultarCustomsLOGIX("4001");
            System.out.println("wsrad.Wsrad.main.logix: (" + consumoWSLOGIX + ")");
            
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

    }
    
}
