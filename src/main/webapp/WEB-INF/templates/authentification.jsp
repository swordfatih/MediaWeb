<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sample Page</title>
</head>

<body>
<h1>Authentification</h1>
<form method="post">
    <input type="type" name="login" placeholder="Votre login"></input>
    <input type="type" name="mdp" placeholder="Votre mot de passe"></input>
    <input type="submit" valeur="Se connecter"></input>
</form>

${requestScope["connexion"]}
</body>

</html>

<!-- \${requestScope["nom"]} -->