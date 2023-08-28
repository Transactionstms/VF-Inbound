/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

async function getData() {

    const numEventoActual = document.getElementById('numEventoActual').value;
    const bl = document.getElementById('bl').value;
    const responsable = document.getElementById('responsable').value;
    const finalDestination = document.getElementById('finaldes').value;
    const brandDivision = document.getElementById('Brand').value;
    const division = document.getElementById('sbu_name').value;
    const shipmentId = document.getElementById('Shipment').value;
    const loadType = document.getElementById('Load1').value;
    const quantity = document.getElementById('quantity').value;
    const pod = document.getElementById('pod').value;
    const estDeparturePol = document.getElementById('est_departure_pol').value;
    const etaRealPort = document.getElementById('eta_port_discharge').value;
    const max_flete = document.getElementById('max_flete').value;
    const eta_plus2 = document.getElementById('eta_plus2').value;
    const eta_plus = document.getElementById('eta_plus').value;
    const pol = document.getElementById('pol').value;
    const observaciones = document.getElementById('observaciones').value;
    const actual_crd = document.getElementById('actual_crd').value;

    swal("Espere...!");

    try {
        const data = await fetchData('../ModificarEvento?responsable=' + responsable + '&finaldes=' + finalDestination + '&Brand=' + brandDivision + '&sbu_name=' + division + '&Shipment=' + shipmentId + '&Load1=' + loadType + '&quantity=' + quantity + '&pod=' + pod + '&est_departure_pol=' + estDeparturePol + '&eta_port_discharge=' + etaRealPort + '&max_flete=' + max_flete + '&eta_plus2=' + eta_plus2 + '&eta_plus=' + eta_plus + '&pol=' + pol + '&observaciones=' + observaciones + '&bl=' + bl + '&ship=' + campo7 + '&con=' + campo1 + '&actual_crd=' + actual_crd + '&numEventoActual=' + numEventoActual + '&numEventoDB=' + campo0 + '&updateEvento=0');
          if(data==="true"){
             swal("Modificado");
          }else{
            swal("Contacte a Soporte","Error");
          }
          alertclose();
    } catch (error) {
    }
}
