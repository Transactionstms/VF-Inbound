/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    function openModalDetails() {
        $("#modalVistaPreviaDetails").modal("show");
    }

    function save(){
        fetch("../AlertaInbound?tipoEnvio=1&correos=jlmateos@tacts.mx/oamorales@tacts.mx", {
            method: 'POST',
        }).then(r => r.text())
                .then(data => {
                    swal("", "Información actualizada", "success");
                    alertclose();
                    location.reload();
                }).catch(error => console.log(error));
    }
    
    function delete_registro() {
            swal({
      title: "¿Estás seguro?",
      text: "Si confirmas, tus datos seran guardados",
      type: "warning",
      showCancelButton: true,
      confirmButtonColor: "#DD6B55",
      confirmButtonText: "Sí, cancelar.",
      cancelButtonText: "¡No, cancele por favor!",
      closeOnConfirm: false,
      closeOnCancel: false
    },
    function(isConfirm){
      if (isConfirm) {
           window.location.href = "../Importacion/gtnDetalle.jsp";
           
      }else{
          swal.close();
      } 
    });
    }

    function alertclose() {
        setTimeout(function () {
            swal.close();
        }, 2000);
    }
    
    function doSearch() {
        var tableReg = document.getElementById('main-table');
        var searchText = document.getElementById('searchTerm').value.toLowerCase();
        var cellsOfRow = "";
        var found = false;
        var compareWith = "";

        // Recorremos todas las filas con contenido de la tabla
        for (var i = 1; i < tableReg.rows.length; i++)
        {
            cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
            found = false;
            // Recorremos todas las celdas
            for (var j = 0; j < cellsOfRow.length && !found; j++)
            {
                compareWith = cellsOfRow[j].innerHTML.toLowerCase();
                // Buscamos el texto en el contenido de la celda
                if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
                {
                    found = true;
                }
            }
            if (found){
                tableReg.rows[i].style.display = '';
            } else {
                tableReg.rows[i].style.display = 'none'; // si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            }
        }
    }
  

