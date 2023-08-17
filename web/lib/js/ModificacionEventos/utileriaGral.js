/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

async function fetchData(url) {
    const response = await fetch(url);
    const data = await response.text();
    return data;
}

$('.chosen').chosen();

$(document).ready(function () {
    $('.datepicker').flatpickr({
        dateFormat: 'm/d/Y',
        onClose: function (selectedDates, dateStr, instance) {
            instance.setDate(dateStr, true, 'm/d/Y');
        }
    });
});

function alertclose() {
    setTimeout(function () {
        swal.close();
    }, 2000);
}
