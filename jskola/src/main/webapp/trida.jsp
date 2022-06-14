<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Trida" %>
<%! Trida t; %>
<% t = (Trida)request.getAttribute("trida"); %>
<!DOCTYPE html>
<html lang="cz">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Třída <%= t.getRocnik() %>.<%= t.getOznaceni() %> - jŠkola</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <h1>Třída <%= t.getRocnik() %>.<%= t.getOznaceni() %></h1>
    <a href="/demo/tridy?id=<%= t.getId() %>&edit">Upravit</a>
</body>
</html>