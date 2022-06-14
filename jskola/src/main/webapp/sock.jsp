<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="style\style.css">
  </head>

  <body>
    <div id="view"></div>
  </body>
  <script type="text/javascript">

    var websocket = null;
    let first = true;
    //Determine whether the current browser supports WebSocket
    if ('websocket' in window) {
      websocket = new WebSocket("ws://localhost:8080/demo/socket");
    } else {
      alert('Current browser Not support websocket')
    }

    //Callback method for connection error
    websocket.onerror = function () {
      console.log("WebSocket connection error occurred");
    };

    //Callback method for successful connection establishment
    websocket.onopen = function () {
      console.log("WebSocket connection successful");
    }

    //Callback method of received message
    websocket.onmessage = function (event) {
      console.log(event);
      //makeDataOnWeb(event.data);
      makeDataOnWeb(event.data);

    }

    //Callback method for connection closed
    websocket.onclose = function () {

    }

    //Listen to the window close event. When the window is closed, actively close the websocket connection to prevent the window from being closed before the connection is disconnected, and the server will throw an exception.
    window.onbeforeunload = function () {
      closeWebSocket();
    }

    //The function executed after getting the data
    /* function makeDataOnWeb(data) {
                var a = data;
                var divNode = document.getElementById("view");
                var liNode = document.createElement("li");
                liNode.innerHTML = a;
                divNode.appendChild(liNode);
     */
    //Close the WebSocket connection
    function closeWebSocket() {
      websocket.close();
    }


    function makeDataOnWeb(data) {
      if (first) {
        //let a = data;
        var divNode = document.getElementById("view");
        var mainNode = document.createElement("table");
        //liNode.innerHTML = a;

        let node = document.createElement("tr");
        node.className = "trida";

        let node_id = document.createElement("th");
        node_id.innerHTML = "ID";
        let node_rocnik= document.createElement("th");
        node_rocnik.innerHTML = "ročník";
        let node_oznaceni = document.createElement("th");
        node_oznaceni.innerHTML = "označení";

        node.appendChild(node_id);
        node.appendChild(node_rocnik);
        node.appendChild(node_oznaceni);
        mainNode.appendChild(node);
        
        let obj = JSON.parse(data);
        for (let i in obj.objednavka) {
          let node = document.createElement("tr");
          node.className = "trida";

          let node_id = document.createElement("td");
          node_id.className = "id";
          let node_rocnik= document.createElement("td");
          node_rocnik.className = "rocnik";
          let node_oznaceni = document.createElement("td");
          node_oznaceni.className = "oznaceni";
          
          node_id.innerHTML = obj.objednavka[i].id;
          node_rocnik.innerHTML = obj.objednavka[i].rocnik + ".";
          node_oznaceni.innerHTML = obj.objednavka[i].oznaceni;
          
          node.appendChild(node_id);
          node.appendChild(node_rocnik);
          node.appendChild(node_oznaceni);
          mainNode.appendChild(node);
        }
        divNode.appendChild(mainNode);
        first = false;
      } else {
        let obj = JSON.parse(data);
        var divNode = document.getElementById("view");
        divNode.removeChild(divNode.firstElementChild);
        var mainNode = document.createElement("table");

        let node = document.createElement("tr");
        node.className = "trida";

        let node_id = document.createElement("th");
        node_id.innerHTML = "ID";
        let node_rocnik= document.createElement("th");
        node_rocnik.innerHTML = "ročník";
        let node_oznaceni = document.createElement("th");
        node_oznaceni.innerHTML = "označení";

        node.appendChild(node_id);
        node.appendChild(node_rocnik);
        node.appendChild(node_oznaceni);
        mainNode.appendChild(node);

        for (let i in obj.objednavka) {
          let node = document.createElement("tr");
          node.className = "trida";

          let node_id = document.createElement("td");
          let node_rocnik= document.createElement("td");
          let node_oznaceni = document.createElement("td");
          
          node_id.innerHTML = obj.objednavka[i].id;
          node_rocnik.innerHTML = obj.objednavka[i].cena + ".";
          node_oznaceni.innerHTML = obj.objednavka[i].status;
          
          node.appendChild(node_id);
          node.appendChild(node_rocnik);
          node.appendChild(node_oznaceni);
          mainNode.appendChild(node);
        }
        divNode.appendChild(mainNode);

      }

    }


  </script>

  </html>