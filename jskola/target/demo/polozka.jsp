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
                <form method="POST" action="polozka">
                    <div class="m-5">
                        <h5>Kliknutím vyber položku</h5>
                        <br>
                        <div>
                            <% for (Polozka t : (List<Polozka>) request.getAttribute("polozka")) { %>
                                <input type="radio" id="polozka<%= t.getId() %>" name="polozka" value="<%= t.getId() %>"><label for="polozka<%= t.getId() %>">
                                        <%= t.getNazev() %> - <%= t.getCena() %> Kč
                                </label>
                                <br>

                                <% } %>
                        </div>
                        <div>
                            <h5>Vyber si přídavek</h5>
                            <% for (Polozka t : (List<Polozka>) request.getAttribute("pridavek")) { %>
                                <input type="checkbox" id="pridavek<%= t.getId() %>" name="pridavek" value="<%= t.getId() %>"><label for="pridavek<%= t.getId() %>">
                                        <%= t.getNazev() %> - <%= t.getCena() %> Kč
                                </label>
                                <br>

                                <% } %>
                        </div>
                    </div>
                    <input type="submit" value="Dokončit objednávku">
                </form>

            </body>

            </html>