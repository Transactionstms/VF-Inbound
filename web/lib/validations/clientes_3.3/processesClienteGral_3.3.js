/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function alertclose() {
    setTimeout(function () {
        swal.close();
    }, 2000);
}

function soloNumeros(e) {
    let key = window.Event ? e.which : e.keyCode
    return (key >= 48 && key <= 57)
}
