swal({   title: "Error!",   
      text: "Los datos se sesion son incorrectos",   
      type: "warning",   
      showCancelButton: true,   
      confirmButtonColor: "#3eae40",  
      confirmButtonText: "Recuperar Datos de cuenta",  
      cancelButtonText: "Intentar nuevamente",   
      closeOnConfirm: false,   
      closeOnCancel: false }, 
     function(isConfirm){   
        if (isConfirm) {     
           window.location.href = 'PaswordReset.jsp' } 
    else {window.location.href = 'index.jsp';   
       } });