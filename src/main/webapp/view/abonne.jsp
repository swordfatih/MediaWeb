<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<p>Salutations, <c:out value="${ sessionScope.utilisateur.name() }">Anonyme</c:out></p>
		</section>

		<section>
			<h2>Emprunts</h2>
			<form method="post">
				<select name="id_d">
					<c:forEach items="${ documentsDisponibles }" var="document" varStatus="status">
						<option value="<c:out value="${ document.toString().split(',', 2)[0] }" />"><c:out value="${ document.toString().split(',', 2)[1] }" /></option>
					</c:forEach>
				</select>
				<input type="submit" name="emprunt" value="Emprunter"></input>
			</form>

			<h2>Retours</h2>
			<form method="post">
				<select name="id_d">
					<c:forEach items="${ documentsEmpruntes }" var="document" varStatus="status">
						<option value="<c:out value="${ document.toString().split(',', 2)[0] }" />"><c:out value="${ document.toString().split(',', 2)[1] }" /></option>
					</c:forEach>
				</select>
				<input type="submit" name="retour" value="Retourner"></input>
			</form>

			<form method="post">
				<input type="submit" name="deconnexion" value="Deconnexion"></input>
			</form>
		</section>
	</main>
</body>
</html>
