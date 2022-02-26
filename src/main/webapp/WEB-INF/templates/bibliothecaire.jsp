<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Gestionnaire - Bibliothecaire</title>
</head>

<body>
	<h1>Ajout de document</h1>
	<h2>Salutations cher ${requestScope["nom_u"]}</h2>
	<form method="post">
	    <input type="text" name="titre" placeholder="Titre"></input>
	    <input type="text" name="auteur" placeholder="Auteur"></input>
	    <label for="type">Choisissez un type:</label>
  		<select name="type" id="type">
			<option value="Livre">Livre</option>
		    <option value="CD">CD</option>
		    <option value="DVD">DVD</option>
		</select>
	    <input type="submit" name="ajouter" value="Ajouter"></input>
	</form>
	
	<form method="post">
	    <input type="submit" name="deconnexion" value="Deconnecter"></input>
	</form>
</body>
</html>
