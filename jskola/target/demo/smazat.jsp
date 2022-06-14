<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ page import="model.Polozka" %>
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
    <div class="m-5">
        <h1>Úprava objednávky</h1>
        <h5>Vyber položku, kterou chceš smazat</h5>
        <br>
        <% for (Polozka t : (List<Polozka>) request.getAttribute("objednavka")) { %>
            <a class="btn btn-success" href="smazat?id=<%= t.getId() %>">
                <%= t.getNazev() %> - <%= t.getCena() %> Kč
            </a>
            <br><br><br>

            <% } %>

            <br><br><br>
            <a class="btn btn-primary" href="/demo/objednavka">Zpět</a>

    </div>
    
</body>

</html>