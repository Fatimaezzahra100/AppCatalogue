
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- librairie en tomcat : jstl utilisé generalement pour faire des boucles dans une jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<!--  <link href="css/style.css" rel="stylsheet" type="text/css" >-->

<style><%@include file="/style.css"%></style>
<script type="text/javascript">
	function confirmer(url) {
		var rep = confirm ("Etes vous sûr de vouloir supprimer ? ");
		if (rep==true){
			document.location=url;
		}
	}
</script>
</head>
<body>
	<div>
		<!-- formulaire -->
		<form action="catalogue_produits" method="post">
			<table>
				<tr>
					<!-- une ligne  -->
					<td>Mot Clé</td>
					<!-- cad la valeur du mot clé qui se trouve dans le model -->
					<td><input type="text" name="motCle" value="${model.motCle}"></td>
					<td><input type="submit" value="chercher" name="action"></td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		<!-- pour afficher le premier résulltat -->
		<table class="table1">
			<tr>
				<th>REF</th>
				<th>DESIGNATION</th>
				<th>PRIX</th>
				<th>QUANTITE</th>
			</tr>
			<!-- mod est un mot clé en jstl -->

			<c:forEach items="${model.produits}" var="p">
				<tr>
					<td>${p.reference}</td>
					<td>${p.designation}</td>
					<td>${p.prix}</td>
					<td>${p.quantite}</td>
					<!-- href= on va appelé le controlleur -->
					<td><a href="javascript:confirmer('catalogue_produits?action=delete&ref=${p.reference}')">Supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>