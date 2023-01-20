/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    function save(){
        fetch("../AlertaInbound?tipoEnvio=2&correos=jlmateos@tacts.mx/luis.mateosmc94@gmail.com", {
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
           window.location.href = "../Reportes/eventosDetalle.jsp";
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

