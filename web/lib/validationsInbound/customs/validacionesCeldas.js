/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function validarMonto$(event) { //validarMonto$,.
    
    // Evitar el comportamiento predeterminado de pegado
    event.preventDefault();

    // Obtener el contenido actual del TD
    let contenido = event.target.innerText;
    var patron = /^[\d.,$]+$/g;
    let resultado = "";

    // Reemplazar comas por puntos para asegurar el formato decimal
    resultado = contenido.replace(',', '');

    // Validar si el contenido es un número o un número decimal
    if (patron.test(resultado)) {
        // Si es un número o un número decimal válido, permitir que permanezca en el TD
        event.target.style.color = 'black';
    } else {
        // Si no es un número o un número decimal válido, eliminar el contenido y mostrar un color de texto rojo
        event.target.innerText = "";
        event.target.style.color = 'red';
    }
}

function validarNumero(event) {
    
    // Evitar el comportamiento predeterminado de pegado
    event.preventDefault();
    
    // Obtener el contenido actual del TD
    let contenido = event.target.innerText;
    var patron = /^[0-9]+([.])?([0-9]+)?$/;
    let resultado = "";
    
    // Reemplazar comas por puntos para asegurar el formato decimal
    resultado = contenido.replace(',', '');

    // Validar si el contenido es un número o un número decimal
    if (patron.test(resultado)) {
        // Si es un número o un número decimal válido, permitir que permanezca en el TD
        event.target.style.color = 'black';
    } else {
        // Si no es un número o un número decimal válido, eliminar el contenido y mostrar un color de texto rojo
        event.target.innerText = "";
        event.target.style.color = 'red';
    }
}

function validarNumeroT_E(event) {
    
    // Evitar el comportamiento predeterminado de pegado
    event.preventDefault();
        
    // Obtener el contenido actual del TD
    let contenido = event.target.innerText;
    var patron = /^[0-9-]+$/;
    let resultado = "";
    
    // Reemplazar comas por puntos para asegurar el formato decimal
    resultado = contenido.replace('.', '-');

    // Validar si el contenido es un número o un número decimal
    if (patron.test(resultado)) {
        // Si es un número o un número decimal válido, permitir que permanezca en el TD
        event.target.style.color = 'black';
    } else {
        // Si no es un número o un número decimal válido, eliminar el contenido y mostrar un color de texto rojo
        event.target.innerText = "";
        event.target.style.color = 'red';
    }
}

function validarTextoAlfanumericoMotivoAtraso(td,namecelda,cont) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z0-9\s.\/ñÑ]+$/.test(contenido)) {
    // Si es alfanumérico, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
  } else {
    // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto alfanumérico.");
    td.innerText = '';
    td.style.color = 'red';
  }
  
  parametrizacionValoresEvento(namecelda,cont); 
  
}


function validarTextoAlfanumericoReferenciaAA(td,namecelda,cont) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z0-9\s.-]+$/.test(contenido)) {
    // Si es alfanumérico, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
  } else {
    // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto alfanumérico.");
    td.innerText = '';
    td.style.color = 'red';
  }
  
  parametrizacionValoresEvento(namecelda,cont); 
  
}

function validarTextoAlfanumericoMotivoRectificacion(td,namecelda,cont) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z0-9\s./-]+$/.test(contenido)) {
    // Si es alfanumérico, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
  } else {
    // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto alfanumérico.");
    td.innerText = '';
    td.style.color = 'red';
  }
  
  parametrizacionValoresEvento(namecelda,cont); 
  
}

function validarTextoAlfanumericoObservaciones(td,namecelda,cont) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z0-9\s./-]+$/.test(contenido)) {
    // Si es alfanumérico, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
  } else {
    // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto alfanumérico.");
    td.innerText = '';
    td.style.color = 'red';
  }
  
  parametrizacionValoresEvento(namecelda,cont); 
  
}

function validarTextoAlfanumerico(td,namecelda,cont) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z0-9\s.-]+$/.test(contenido)) {
    // Si es alfanumérico, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
  } else {
    // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto alfanumérico.");
    td.innerText = '';
    td.style.color = 'red';
  }
  
  parametrizacionValoresEvento(namecelda,cont); 
  
}

function validarTextoAlfanumericoSnParametrizacion(td) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z0-9\s.]+$/.test(contenido)) {
    // Si es alfanumérico, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
  } else {
    // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto alfanumérico.");
    td.innerText = '';
    td.style.color = 'red';
  }
  
}

function validarTextoPais(td, i) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z\s]+$/.test(contenido)) {
    // Si es solo texto, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
    
    parametrizacionValoresEvento("pais_origen",i);
    
  } else {
    // Si contiene números u otros caracteres, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto.");
    td.innerText = '';
    td.style.color = 'red';
    
    parametrizacionValoresEvento("pais_origen",i);
    
  }
  
}

function validarTextoParametrizacion(td,namecelda,cont) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z\u00D1\u00F1\s-.]+$/.test(contenido)) {
    // Si es solo texto, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
  } else {
    // Si contiene números u otros caracteres, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto.");
    td.innerText = '';
    td.style.color = 'red';
  }
  
  parametrizacionValoresEvento(namecelda,cont);
}

function validarTexto(td) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z\s]+$/.test(contenido)) {
    // Si es solo texto, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
  } else {
    // Si contiene números u otros caracteres, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto.");
    td.innerText = '';
    td.style.color = 'red';
  }
}

function validarTextoCheckbox(td) {
    
      // Obtener el contenido actual del TD
      let contenido = td.innerText;
      var expresionRegular = /^[a-zA-ZíÍ\s]*$/;
      
      // Limitar la longitud a dos caracteres
      var dosCaracteres = contenido.slice(0, 2);

      if (expresionRegular.test(dosCaracteres)) {
        // Si es alfanumérico, puedes realizar acciones adicionales
        console.log("Contenido del TD:", dosCaracteres);
        td.style.color = 'black';
      } else {
        // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
        console.log("Por favor, ingrese solo texto alfanumérico.");
        td.innerText = '';
        td.style.color = 'red';
      }
    
}

function formatoNumero(event,i,nameCelda) {
    
      cleanPedimento_r1_1er(i);
      cleanPedimento_r1_2do(i);
      let resultado = "";
      
      // Obtener el código ASCII de la tecla presionada
      var charCode = (event.which) ? event.which : event.keyCode;

      // Validar que solo se permitan números y el carácter de retroceso
      if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode !== 8) {
        event.preventDefault();
        return false;
      }

      // Obtener el contenido actual de la celda
      var valor = event.target.innerText;

      // Filtrar caracteres no numéricos
      var numeroFiltrado = valor;

      // Limitar la longitud total a 16 caracteres
      numeroFiltrado = numeroFiltrado.slice(0, 14);

      // Aplicar el formato XX XX XXXX XXXXXXXX
      if (numeroFiltrado.length > 1) {
        numeroFiltrado = numeroFiltrado.replace(/(\d{2})(\d{2})(\d{4})(\d{6})/, '$1 $2 $3 $4');
      }
      
      let respuesta = document.getElementById(nameCelda+"[" + i + "]").innerHTML;
      
      if(respuesta.length <=15){ 
         resultado = numeroFiltrado.trim();  
      }else{
         resultado = respuesta.substring(0, respuesta.length - 1);
      }
      // Actualizar el contenido de la celda con el número filtrado
      event.target.innerText = resultado;
         
      // Posicionar el cursor al final del contenido
      var range = document.createRange();
      var selection = window.getSelection();
      range.selectNodeContents(event.target);
      range.collapse(false);
      selection.removeAllRanges();
      selection.addRange(range);

      return true;  
}


function formatoFecha(event) {
    
   // Evitar el comportamiento predeterminado de pegado
   event.preventDefault();
    
  // Obtener el contenido actual de la celda
  var valor = event.target.innerText;

  // Filtrar caracteres no numéricos
  var fechaFiltrada = valor.replace(/\D/g, '');

  // Limitar la longitud total a 8 caracteres (formato: MMDDYYYY)
  fechaFiltrada = fechaFiltrada.slice(0, 8);

  // Aplicar el formato MM/DD/YYYY
  if (fechaFiltrada.length > 1) {
    fechaFiltrada = fechaFiltrada.replace(/^\d{2}\/\d{2}\/\d{4}$/, '$1/$2/$3');
  }

  // Actualizar el contenido de la celda con la fecha filtrada
  event.target.innerText = fechaFiltrada;
}

function formatoDosDigitos(texto) {
  // Agregar un cero al principio si el texto tiene solo un dígito
  return texto.length === 1 ? '0' + texto : texto;
}

function handlePasteAlfanumericoMotivoAtraso(event) {
    
    // Evitar el comportamiento predeterminado de pegado
    event.preventDefault();

    // Obtener el texto pegado
    const clipboardData = event.clipboardData || window.clipboardData;
    const contenido = clipboardData.getData('text/plain');

  if (/^[a-zA-Z0-9\s.\/ñÑ]+$/.test(contenido)) {
      // Actualizar la celda con el texto manipulado (si es necesario)
      event.target.innerText = contenido;
      td.style.color = 'black';
  } else {
      // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
      console.log("Por favor, ingrese solo texto alfanumérico.");
      td.innerText = '';
      td.style.color = 'red';
  }
  
}

function handlePasteAlfanumericoMotivoRectificacion(event) {
    
    // Evitar el comportamiento predeterminado de pegado
    event.preventDefault();

    // Obtener el texto pegado
    const clipboardData = event.clipboardData || window.clipboardData;
    const contenido = clipboardData.getData('text/plain');

  if (/^[a-zA-Z0-9\s./-]+$/.test(contenido)) {
      // Actualizar la celda con el texto manipulado (si es necesario)
      event.target.innerText = contenido;
      td.style.color = 'black';
  } else {
      // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
      console.log("Por favor, ingrese solo texto alfanumérico.");
      td.innerText = '';
      td.style.color = 'red';
  }
  
}

function handlePasteAlfanumericoObservaciones(event) {
    
    // Evitar el comportamiento predeterminado de pegado
    event.preventDefault();

    // Obtener el texto pegado
    const clipboardData = event.clipboardData || window.clipboardData;
    const contenido = clipboardData.getData('text/plain');

  if (/^[a-zA-Z0-9\s./-]+$/.test(contenido)) {
      // Actualizar la celda con el texto manipulado (si es necesario)
      event.target.innerText = contenido;
      td.style.color = 'black';
  } else {
      // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
      console.log("Por favor, ingrese solo texto alfanumérico.");
      td.innerText = '';
      td.style.color = 'red';
  }
  
}

function handlePasteAlfanumerico(event) {
    
    // Evitar el comportamiento predeterminado de pegado
    event.preventDefault();

    // Obtener el texto pegado
    const clipboardData = event.clipboardData || window.clipboardData;
    const contenido = clipboardData.getData('text/plain');

  if (/^[a-zA-Z0-9\s.]+$/.test(contenido)) {
      // Actualizar la celda con el texto manipulado (si es necesario)
      event.target.innerText = contenido;
      td.style.color = 'black';
  } else {
      // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
      console.log("Por favor, ingrese solo texto alfanumérico.");
      td.innerText = '';
      td.style.color = 'red';
  }
  
}

function handlePasteText(event) {
    // Evitar el comportamiento predeterminado de pegado
    event.preventDefault();

    // Obtener el texto pegado
    const clipboardData = event.clipboardData || window.clipboardData;
    const contenido = clipboardData.getData('text/plain');
    var patron = /^[a-zA-Z\u00D1\u00F1\s-.]+$/;

    // Realizar manipulaciones con el texto pegado según tus necesidades
    // Aquí puedes agregar lógica para analizar y controlar la información
    if (patron.test(contenido)) {
        event.target.innerText = contenido;
    }else{
        event.target.innerText = "";
    }
}

function handlePasteTextCheckBox(event) {
    // Evitar el comportamiento predeterminado de pegado
    event.preventDefault();

    // Obtener el texto pegado
    const clipboardData = event.clipboardData || window.clipboardData;
    const contenido = clipboardData.getData('text/plain');
    var patron = /^(s[ií]|n[oó])$/i;

    // Realizar manipulaciones con el texto pegado según tus necesidades
    // Aquí puedes agregar lógica para analizar y controlar la información
    if (patron.test(contenido)) {
        event.target.innerText = contenido;
    }else{
        event.target.innerText = "";
    }
}

function handlePasteText2Caracteres(event) {
    // Evitar el comportamiento predeterminado de pegado
    event.preventDefault();

    // Obtener el texto pegado
    const clipboardData = event.clipboardData || window.clipboardData;
    const contenido = clipboardData.getData('text/plain');

    // Realizar manipulaciones con el texto pegado según tus necesidades
    // Aquí puedes agregar lógica para analizar y controlar la información

    // Filtrar caracteres no alfabeticos
    var pastedData = contenido.replace(/[^A-Za-z]/g, '');
    //let data = pastedData.slice(0, 2);
   
    // Ejemplo: Imprimir el texto pegado en la consola
    console.log("Texto pegado:", pastedData);

    // Puedes realizar más manipulaciones aquí según tus requisitos
    // ...

    // Actualizar la celda con el texto manipulado (si es necesario)
    event.target.innerText = pastedData;
}

function handlePasteNumber(event) {
    
  // Evitar el comportamiento predeterminado de pegado
  event.preventDefault();
    
  var clipboardData = event.clipboardData || window.clipboardData;
  var pastedData = clipboardData.getData('text');
  let resultado = "";
  
  var regex = /^[0-9]+([.])?([0-9]+)?$/;
  
  // Reemplazar comas por puntos para asegurar el formato decimal
  resultado = pastedData.replace(',', '');
  
  if (regex.test(resultado)) {
    event.target.innerText = resultado; 
  }
}

function handlePasteMonto$(event) {
    
  // Evitar el comportamiento predeterminado de pegado
  event.preventDefault();
    
  var clipboardData = event.clipboardData || window.clipboardData;
  var pastedData = clipboardData.getData('text').replace(/[\r\n]+/g, ' ').trim();
  //let resultado = "";
  var regex = /^[\d.,$]+$/g;
  
  // Reemplazar comas por puntos para asegurar el formato decimal
  //resultado = pastedData.replace(',', '');
  
  if (regex.test(pastedData)) {
    event.target.innerText = pastedData; 
  }
}

function handlePasteNumberT_E(event) {
    
  // Evitar el comportamiento predeterminado de pegado
  event.preventDefault();
    
  var clipboardData = event.clipboardData || window.clipboardData;
  var pastedData = clipboardData.getData('text');
  var patron = /^[0-9-]+$/;
  let resultado = "";

    // Reemplazar comas por puntos para asegurar el formato decimal
    resultado = pastedData.replace('.', '-');

    // Validar si el contenido es un número o un número decimal
    if (patron.test(resultado)) {
        // Si es un número o un número decimal válido, permitir que permanezca en el TD
        // 
// Actualizar el contenido de la celda con el número filtrado
        event.target.innerText = resultado;
        event.target.style.color = 'black';
    } else {
        // Si no es un número o un número decimal válido, eliminar el contenido y mostrar un color de texto rojo
        event.target.innerText = "";
        event.target.style.color = 'red';
    }
  
}

function handlePasteNumberPedimento(event,i,nameCelda) {
      
      document.getElementById(nameCelda+"[" + i + "]").innerHTML = "";
      
      // Evitar el comportamiento predeterminado de pegado
      event.preventDefault();
      
      let resultado = "";
      
      // Obtener el código ASCII de la tecla presionada
      var charCode = (event.which) ? event.which : event.keyCode;

      // Validar que solo se permitan números y el carácter de retroceso
      if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode !== 8) {
        event.preventDefault(); // Evitar el comportamiento predeterminado de pegado
        return false;
      }
      
      // Obtener el texto pegado
      const clipboardData = event.clipboardData || window.clipboardData;
      
      // Obtener el contenido actual de la celda
      const contenido = clipboardData.getData('text/plain');

      // Filtrar caracteres no numéricos
      var numeroFiltrado = contenido.replace(/\D/g, '');

      // Limitar la longitud total a 16 caracteres
      numeroFiltrado = numeroFiltrado.slice(0, 17);

      // Aplicar el formato XX XX XXXX XXXXXXXX
      if (numeroFiltrado.length > 1) {
        numeroFiltrado = numeroFiltrado.replace(/(\d{2})(\d{2})(\d{4})(\d{6})/, '$1 $2 $3 $4');
      }
      
      let respuesta = document.getElementById(nameCelda+"[" + i + "]").innerHTML;
      
      if(respuesta.length <=15){ 
         resultado = numeroFiltrado.trim();  
      }else{
         resultado = respuesta.substring(0, respuesta.length - 1);
      }
      // Actualizar el contenido de la celda con el número filtrado
      event.target.innerText = resultado;
         
      // Posicionar el cursor al final del contenido
      var range = document.createRange();
      var selection = window.getSelection();
      range.selectNodeContents(event.target);
      range.collapse(false);
      selection.removeAllRanges();
      selection.addRange(range);
      
      cleanPedimento_r1_1er(i);
      cleanPedimento_r1_2do(i);

      return true;  
}

function toggleEditable(name_celda,i) {
     
      let celda = name_celda+'['+i+']';
      console.log(celda);
      
    const cell = document.querySelector(celda);
    cell.setAttribute("contentEditable", "true");
}


function handlePasteFechaEtaPortDischarge(event,i) {
    
  // Evitar el comportamiento predeterminado de pegado
  event.preventDefault();
    
  var clipboardData = event.clipboardData || window.clipboardData;
  var pastedData = clipboardData.getData('text/plain');

  var regex = /^\d{2}\/\d{2}\/\d{4}$/;

  if (regex.test(pastedData)) {
      
     // Actualizar el contenido de la celda con el número filtrado
     event.target.innerText = pastedData; 
     
     pedimento(pastedData, i);
  }
}

function handlePasteFechaPagoPedimento(event,i) {
    
  // Evitar el comportamiento predeterminado de pegado
  event.preventDefault();
    
  var clipboardData = event.clipboardData || window.clipboardData;
  var pastedData = clipboardData.getData('text/plain');

  var regex = /^\d{2}\/\d{2}\/\d{4}$/;

  if (regex.test(pastedData)) {
      
     // Actualizar el contenido de la celda con el número filtrado
     event.target.innerText = pastedData; 
     
     modulacion(pastedData, i);
  }
}

function handlePasteFecha(event) {
    
  // Evitar el comportamiento predeterminado de pegado
  event.preventDefault();
    
  var clipboardData = event.clipboardData || window.clipboardData;
  var pastedData = clipboardData.getData('text/plain');

  var regex = /^\d{2}\/\d{2}\/\d{4}$/;

  if (regex.test(pastedData)) {
      
     // Actualizar el contenido de la celda con el número filtrado
     event.target.innerText = pastedData;  
  }
}