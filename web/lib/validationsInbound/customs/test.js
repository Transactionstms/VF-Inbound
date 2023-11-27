/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
            function leerArchivo() {
                var fileInput = document.getElementById('fileInput');

                if (fileInput.files.length > 0) {
                    var archivo = fileInput.files[0];
                    var lector = new FileReader();

                    lector.onload = function (e) {
                        var contenido = e.target.result;
                        var workbook = XLSX.read(contenido, {type: 'array'});

                        var primeraHoja = workbook.SheetNames[0];
                        var hoja = workbook.Sheets[primeraHoja];

                        var datosJSON = XLSX.utils.sheet_to_json(hoja, {header: 1});

                        // Llama a la función para agregar o actualizar los datos en la tabla existente
                        agregarOActualizarTabla(datosJSON);
                    };

                    lector.readAsArrayBuffer(archivo);
                } else {
                    alert('Por favor, selecciona un archivo Excel.');
                }
            }

            function agregarOActualizarTabla(datos) {
                var tabla = document.getElementById('miTabla');
                var tbody = tabla.getElementsByTagName('tbody')[0];

                // Itera sobre los datos del archivo Excel
                for (var i = 1; i < datos.length; i++) {
                    // Busca una fila existente con el mismo identificador
                    var identificador = datos[i][3];
                    var filaExistente = buscarFilaPorIdentificador(tbody, identificador);

                    if (filaExistente) {
                        // Si se encuentra una fila existente, actualiza sus celdas con los nuevos datos
                        for (var j = 1; j < datos[i].length; j++) {
                            filaExistente.cells[j].innerHTML = datos[i][j];
                        }
                    } else {
                        // Si no se encuentra una fila existente, agrega una nueva fila
                        var fila = tbody.insertRow(-1); // El índice -1 inserta la fila al final de tbody

                        // Agrega celdas a la nueva fila
                        for (var k = 0; k < datos[i].length; k++) {
                            var celda = fila.insertCell(k);
                            celda.innerHTML = datos[i][k];
                        }
                    }
                }
            }

            function buscarFilaPorIdentificador(tbody, identificador) {
                // Itera sobre las filas del tbody y busca una fila con el identificador dado
                for (var i = 0; i < tbody.rows.length; i++) {
                    if (tbody.rows[i].cells[3].innerHTML === identificador) {
                        return tbody.rows[i];
                    }
                }
                return null; // Si no se encuentra ninguna fila con el identificador
            }

