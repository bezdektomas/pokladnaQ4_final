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
    <title>Editace třídy - jŠkola</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <h1>Editace třídy</h1>
    <form action="tridy" method="post">
        <input type="hidden" name="id" value="<%= t != null ? t.getId() : 0 %>">
        <div>
            <label for="rocnikInput">Ročník</label>
            <input type="text" id="rocnikInput" name="rocnik" value="<%= t != null ? t.getRocnik() : 0%>">
        </div>
        <div>
            <label for="oznaceniInput">Označení</label>
            <input type="text" id="oznaceniInput" name="oznaceni" value="<%= t != null ? t.getOznaceni() : "" %>">
        </div>
        <div>
            <button type="submit">Odeslat</button>
        </div>
    </form>
</body>
</html>