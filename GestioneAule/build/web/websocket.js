/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//var wsUri = "ws://localhost:8080/GestioneAule/src/java/Socket/SocketClass.java";
var wsUri = "ws://localhost:8080/GestioneAule/Update";
var websocket = new WebSocket(wsUri);
websocket.binaryType = "arraybuffer";

websocket.onmessage = function() { onMessage() };
websocket.onerror = function(evt) { onError(evt) };

function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}

function sendMessage(){
    websocket.send("Update Request");
}
                
function onMessage() {
    location.reload();
    console.log("onMessage (Javascript) ");
}

function onError(evt) {
    console.log("Errore (Javascript) ");
}
