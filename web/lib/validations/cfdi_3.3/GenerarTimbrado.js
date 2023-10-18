/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function Api_Facturacion(id, tipoGeneracion, timbrado) {
    
    const Http = new XMLHttpRequest();
   
    const url = 'https://www.tacts.mx/Facturacion3.3QA/getXML.jsp?idf=' + id + '&cve=' + cbdiv_id + '&tipo=1';
    //const url = 'https://www.tacts.mx/Facturacion3.3PRO/getXML.jsp?idf=' + id + '&cve=' + cbdiv_id + '&tipo=1';
    
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            let respuesta = Http.responseText;
            let palabra = "200";
            let index = respuesta.indexOf(palabra);

            if (index >= 0) {

                //Generación de PDF'S
                visorPdfFactura(2);   // (1 = No Marcado de ruta pdf) | (2= Marcado de ruta pdf)

                //Almacenamiento de PDF'S
                savegeneratepdf(tipoGeneracion);         //tipoGeneracion(cfdi ó cartaPorte)

                //Envío de PDF'S    
                sendmail(tipoGeneracion, timbrado); /*tipoGeneracion(cfdi)|tipoTimbrado(si XML)*/

            } else {
                swal("Error", "Detalle: " + respuesta, "error");
                hidePreloader();
                return false;
            }

        }
    }
}

