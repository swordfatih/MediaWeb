<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>MediaWeb - Espace bibliothécaire</title>

	<link href="/MediaWeb/assets/styles/main.css" rel="stylesheet">
</head>

<body>
	<main>
		<section>
			<h1>MediaWeb</h1>

			<h2>Espace bibliothécaire</h2>
			<p>Salutations, <c:out value="${ sessionScope.utilisateur.name() }">Anonyme</c:out></p>
		</section>

		<section>
			<h2>Ajout de document</h2>
			<form method="post">
				<input type="text" name="titre" placeholder="Titre"></input>
				<input type="text" name="auteur" placeholder="Auteur"></input>
				<select name="type">
					<option value="Livre">Livre</option>
					<option value="CD">CD</option>
					<option value="DVD">DVD</option>
				</select>
				<input type="submit" name="ajouter" value="Ajouter"></input>
			</form>

			<form method="post">
				<input type="submit" name="deconnexion" value="Deconnexion"></input>
			</form>
		</section>
	</main>
</body>
</html>
