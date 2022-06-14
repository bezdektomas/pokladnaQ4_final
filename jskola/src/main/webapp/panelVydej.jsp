<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ page import="model.Objednavky" %>
        <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Pokladna</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <script src='https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js'></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
    <script src='main.js'></script>
</head>

<body>
    <h1>Výdej</h1>
    <div class="panel row">
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
            //Close the WebSocket connection
            function closeWebSocket() {
              websocket.close();
            }
        
        
            function makeDataOnWeb(data) {
                let obj = JSON.parse(data);
                let panel = document.querySelector(".panel");
                vypis = "";
                let idAktualni = 0;
                let idPosledni = 0;
                let krok = 0;
                let status = 0;
                for (let i in obj.objednavka) {
                  idAktualni = obj.objednavka[i].id;

                  if (idAktualni != idPosledni && krok != 0) {
                    idAktualni = obj.objednavka[i].id;
                    if (status == 1) {
                        vypis += ("<a class='btn btn-primary mt-5' href='#'>Připravuje se...</a></div></div></div>");
                    }
                    if (status == 2) {
                        vypis += ("<a class='btn btn-success mt-5' href='vydej?vydat=" + idPosledni + "'>Vydat objednávku</a></div></div></div>");
                    }
                    if (status == 3) {
                        vypis += ("<a class='btn btn-warning mt-5' href='vydej?vydano=" + idPosledni + "'>Vydáno</a></div></div></div>");
                    }
                  }

                  if (idAktualni != idPosledni) {
                        idAktualni = obj.objednavka[i].id;
                        vypis += ("<div class='col-sm-3'><div class='card mt-4'><div class='card-header'>Objednávka " + obj.objednavka[i].id + "</div><div class='card-body'>");
                    }

                    idPosledni = obj.objednavka[i].id;
                    
                    krok++;
                    status = obj.objednavka[i].status;

                    vypis += "<h4>" + obj.objednavka[i].nazev + " - " + obj.objednavka[i].cena + " Kč</h4><br>";
                }
                if (krok > 0) {
                    if (status == 1) {
                        vypis += ("<a class='btn btn-primary mt-5' href='#'>Připravuje se...</a></div></div></div>");
                    }
                    if (status == 2) {
                        vypis += ("<a class='btn btn-success mt-5' href='vydej?vydat=" + idPosledni + "'>Vydat objednávku</a></div></div></div>");
                    }
                    if (status == 3) {
                        vypis += ("<a class='btn btn-warning mt-5' href='vydej?vydano=" + idPosledni + "'>Vydáno</a></div></div></div>");
                    }}
                    panel.innerHTML = vypis;
            }
        
        
          </script>
    <%
    int idAktualni = 0;
    int idPosledni = 0;
    int krok = 0;
    int status = 0;
    %>

    <% for (Objednavky t : (List<Objednavky>) request.getAttribute("vydej")) { %>
        <%
        
        idAktualni = t.getId();
        

        if (idAktualni != idPosledni && krok != 0) {
            idAktualni = t.getId();
            if (status == 1) {
                out.print("<a class='btn btn-primary mt-5' href='#'>Připravuje se...</a></div></div></div>");
            }
            if (status == 2) {
                out.print("<a class='btn btn-success mt-5' href='vydej?vydat=" + idPosledni + "'>Vydat objednávku</a></div></div></div>");
            }
            if (status == 3) {
                out.print("<a class='btn btn-warning mt-5' href='vydej?vydano=" + idPosledni + "'>Vydáno</a></div></div></div>");
            }
        }
        

        if (idAktualni != idPosledni) {
            idAktualni = t.getId();
            out.print("<div class='col-sm-3'><div class='card mt-4'><div class='card-header'>Objednávka " + t.getId() + "</div><div class='card-body'>");
        }

        idPosledni = t.getId();
        
        krok++;
        status = t.getStatus();
        %>
        <h4>
            <%= t.getNazev() %> - <%= t.getCena() %> Kč
        </h4>
        
        <br>

        <% } %>
        <%
        
        if (krok > 0) {
            if (status == 1) {
                out.print("<a class='btn btn-primary mt-5' href='#'>Připravuje se...</a></div></div></div>");
            }
            if (status == 2) {
                out.print("<a class='btn btn-success mt-5' href='vydej?vydat=" + idPosledni + "'>Vydat objednávku</a></div></div></div>");
            }
            if (status == 3) {
                out.print("<a class='btn btn-warning mt-5' href='vydej?vydano=" + idPosledni + "'>Vydáno</a></div></div></div>");
            }}
        %>
        </div>

</body>

</html>
