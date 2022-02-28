<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>MediaWeb - Authentification</title>

	<link href="/MediaWeb/assets/styles/main.css" rel="stylesheet">
</head>

<body>
	<main>
		<section>
			<h1>MediaWeb</h1>
		</section>

		<section>
			<h2>Authentification</h2>
			<form method="post">
				<input type="text" name="login" placeholder="Votre login"></input>
				<input type="text" name="mdp" placeholder="Votre mot de passe"></input>
				<input type="submit" value="Se connecter"></input>
			</form>
			<p>${requestScope["erreur"] ? "Votre connexion a échouée" : ""}</p>
		</section>
	</main>
</body>
</html>
