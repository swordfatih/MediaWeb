<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Gestionnaire - Abonne</title>
</head>

<body>
	<h1>Emprunts et retours</h1>
	<h2>Salutations cher ${requestScope["nom_u"]}</h2>
	
	<form method="post">
	    <input type="text" name="titre" placeholder="Titre du document"></input>
	    <input type="submit" name="emprunt" value="Emprunt"></input>
	    <input type="submit" name="retour" value="Retour"></input>
	</form>
</body>
</html>
