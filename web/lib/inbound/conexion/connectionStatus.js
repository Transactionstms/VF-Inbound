/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function updateConnectionStatus(msg, connected) {
    if (connected) {
        document.body.className = "online";
    } else {
        document.body.className = "offline";
    }
}

window.addEventListener('load', function (e) {
    if (navigator.onLine) {
        updateConnectionStatus('Online', true);
    } else {
        updateConnectionStatus('Offline', false);
    }
}, false);

window.addEventListener('online', function (e) {
    updateConnectionStatus('Online', true);
}, false);

window.addEventListener('offline', function (e) {
    updateConnectionStatus('Offline', false);
}, false);

