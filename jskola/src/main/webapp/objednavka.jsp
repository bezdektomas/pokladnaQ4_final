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
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
                    rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
                    crossorigin="anonymous">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
                    crossorigin="anonymous"></script>
                <script src='main.js'></script>
            </head>

            <body>
                <div class="m-5">

                    <div class="d-flex">
                        <div class="col-sm-4">
                            <h1>Objednávka</h1>
                            <br>
                            <a class="btn btn-primary" href="polozka?menu=1" style="width: 80%;">Menu</a>
                            <br>
                            <a class="btn btn-primary mt-5" href="polozka?id=5&pridavek=6" style="width: 80%;">Burgery</a>
                            <br>
                            <a class="btn btn-primary mt-5" href="polozka?id=7&pridavek=8" style="width: 80%;">Nápoje</a>
                            <br>
                            <a class="btn btn-primary mt-5" href="polozka?id=9" style="width: 80%;">Ostatní</a>
                        </div>
                        <div class="col-sm-4">
                            <h1>Vaše položky</h1>
                            <% int celkovaCena=0; for (Polozka t : (List<Polozka>) request.getAttribute("objednavka")) {
                                %>
                                <h5>
                                    <%= t.getNazev() %> - <%= t.getCena() %> Kč
                                            <% celkovaCena +=t.getCena(); %>
                                </h5>
                                <br>

                                <% } %>

                        </div>

                        <div class="col-sm-4">
                            <h1>Celková cena:<br> <% out.print(celkovaCena); %> Kč</h1>
                            <br>
                            <br>
                            <a class="btn btn-danger mt-5 w-100" href="smazat">Odstranit položku</a>
                            <br>
                            <a class="btn btn-success mt-5 w-100" href="dokonceni">Dokončit objednávku</a>
                        </div>
                    </div>
                </div>

            </body>

            </html>