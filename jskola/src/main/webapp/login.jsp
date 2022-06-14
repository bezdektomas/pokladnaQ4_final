<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="cz">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Přihlášení - jŠkola</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <h1>Přihlášení</h1>
    <form action="/demo/login" method="post">
        <div>
            <label for="login">Login</label>
            <input type="text" id="login" name="login" placeholder="Uživatelské jméno">
        </div>
        <div>
            <label for="heslo">Heslo</label>
            <input type="text" id="heslo" name="heslo" placeholder="Heslo">
        </div>
        <div>
            <button type="submit">Přihlásit</button>
        </div>
    </form>
</body>
</html>