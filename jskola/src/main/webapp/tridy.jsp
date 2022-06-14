<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Trida" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="cz">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Přehled tříd - jŠkola</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <h1>Třídy</h1>

    <% for (Trida t : (List<Trida>) request.getAttribute("tridy")) { %>
    <div>
        <a href="/demo/tridy?id=<%= t.getId() %>"><%= t.getRocnik() %>.<%= t.getOznaceni() %></a>
    </div>
    <% } %>
    <div>
        <a href="/demo/tridy?edit>">Nová</a>
    </div>
</body>
</html>