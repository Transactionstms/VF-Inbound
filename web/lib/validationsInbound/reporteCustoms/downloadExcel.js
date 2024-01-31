/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.getElementById("downloadLinkEventCustoms").addEventListener("click", function() {
    // Realizar una solicitud al servlet para descargar el archivo
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "../../DownloadExcelEventsCustoms", true);
    xhr.responseType = "blob";

    xhr.onload = function() {
        // Crear un enlace y hacer clic en él para iniciar la descarga
        var blob = new Blob([xhr.response], { type: "application/octet-stream" });
        var link = document.createElement("a");
        link.href = window.URL.createObjectURL(blob);
        link.download = "ReporteEventosCustoms.xls";
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    };

    xhr.send();
});
