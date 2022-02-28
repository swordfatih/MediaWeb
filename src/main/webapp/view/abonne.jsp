<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>MediaWeb - Espace abonné</title>

	<link href="/MediaWeb/assets/styles/main.css" rel="stylesheet">
</head>

<body>
	<main>
		<section>
			<h1>MediaWeb</h1>

			<h2>Espace abonné</h2>
			<p>Salutations, ${requestScope["nom_u"]}</p>
		</section>

		<section>
			<h2>Emprunts et retours</h2>
			<form method="post">
				<input type="number" name="id_d" placeholder="Numero du document"></input>
				<input type="submit" name="emprunt" value="Emprunt"></input>
				<input type="submit" name="retour" value="Retour"></input>
			</form>

			<form method="post">
				<input type="submit" name="deconnexion" value="Deconnexion"></input>
			</form>
		</section>
	</main>
</body>
</html>
