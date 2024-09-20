/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsRadar;
import java.io.IOException;
import com.tacts.dao.TmsCustoms;
        
/**
 *
 * @author grecendiz
 */
public class wsRadar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        TmsCustoms obj = new TmsCustoms();

        try {
            
            boolean consumoWSRADAR = obj.consultarCustomsRADAR("4003");
            System.out.println("wsrad.Wsrad.main.radar: (" + consumoWSRADAR + ")");
            System.out.println("Proceso Finalizado");

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

    }
    
}
