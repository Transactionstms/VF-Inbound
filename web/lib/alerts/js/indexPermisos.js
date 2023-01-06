swal({   title: "Error!",   
      text: "No cuenta con los suficentes permisos para realizar esta accion",   
      type: "warning",   
      showCancelButton: true,   
      confirmButtonColor: "#3eae40",  
      confirmButtonText: "Solicitar",  
      cancelButtonText: "Salir",   
      closeOnConfirm: false,   
      closeOnCancel: false }, 
     function(isConfirm){   
        if (isConfirm) {     
           window.location.href = '../PaswordReset.jsp' } 
    else {window.location.href = '../forms/main.jsp';   
       } });