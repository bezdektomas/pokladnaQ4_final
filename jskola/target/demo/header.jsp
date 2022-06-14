<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<nav>
    <div><a href="/demo">Domů</a></div>
    <div><a href="/demo/tridy">Třídy</a></div>
    <div><a href="/demo/kontakt">Kontakt</a></div>
    <div>
        <%@ page import="model.Uzivatel" %>
        <%
            //String user = (String)session.getAttribute("loggedUser");
            Uzivatel user = (Uzivatel)session.getAttribute("loggedUser"); // PHP: $_SESSION['loggenUser']
            if (user != null) {
        %>
        <a href="/demo/profil"><%= user.getJmeno() %></a>
        <a href="/demo/login?logout">Odhlásit</a>
        <%
            } else {
        %>
        <a href="/demo/login">Přihlásit</a>
        <%
            }
        %>
    </div>
</nav>