<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Polozka" %>
<%! Polozka t; %>
<% t = (Polozka)request.getAttribute("pridavek"); %>
<!DOCTYPE html>
<html lang="cz">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/style.css">
</head>
<body>

    <h1>Přidáno do košíku -  <%= t.getNazev() %> - <%= t.getCena() %> Kč</h1>
    <a href="/demo/tridy?id=<%= t.getId() %>&edit">Upravit</a>
</body>
</html>