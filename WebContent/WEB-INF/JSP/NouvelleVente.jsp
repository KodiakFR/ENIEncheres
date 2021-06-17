<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>Nouvelle Vente</title>
</head>
<body>
<header>
<div style="text-align: center;">
<h1>ENI-Enchères</h1>
<h2 class="center">Nouvelle vente</h2>
</div>
</header>

	<div class="container">
			<c:if test="${validateDateDebut == false}">
					<div 	class="alert alert-danger" role="alert">
						La date du début de l'enchère doit être supérieure à la date du jour
					</div>
			</c:if>
			
			
			<form action="NouvelleVente" method="post" id="formNouvelleVente">
				
					 <div class="form-group row">
		   				 <label for="nomArticle" class="col-sm-2 col-form-label">Article</label>
			   				 <div class="col-sm-10">
			     				 <input type="text" class="form-control" id="nomArticle" name="nomArticle" maxlength="30" required="required">
			    			</div>
		  			</div>
		  			
					<div class="form-group row">
		   				 <label for="description" class="col-sm-2 col-form-label">Description</label>
			   				 <div class="col-sm-10">
			     				 <textarea class="form-control" id="description"  rows="5" cols="40" name="description" placeholder="Entrer une description ici" required="required"></textarea>
			    			</div>
		  			</div>
		  			
		  			<div class="form-group row">
		   				 <label for="categorie" class="col-sm-2 col-form-label">Categorie</label>
			   				 <div class="col-sm-10">
							<select class="custom-select" id="categorie" name="categorie">
							    <option value="autre">Choisir...</option>
							    <option value="Voiture">Voiture</option>
							    <option value="Vélo">Vélo</option>
							    <option value="Moto">Moto</option>
							 </select>
			    			</div>
		  			</div>
		  			
		  			<div class="form-group row">
		   				 <label for="image" class="col-sm-2 col-form-label">Photo de l'article</label>
			   				 <div class="col-sm-10">
								<button id="image" type="button" class="btn btn-dark">UPLOADER</button>
			    			</div>
		  			</div>
		  			
		  			<div class="form-group row">
		   				 <label for="map" class="col-sm-2 col-form-label">Mise à prix</label>
			   				 <div class="col-sm-10">
			     				 <input type="number" class="form-control" id="map" name="map" min="0"  required="required">
			    			</div>
		  			</div>
		  			
					<div class="form-group row">
		   				 <label for="dateDebutEncheres" class="col-sm-2 col-form-label">Début de l'enchère</label>
			   				 <div class="col-sm-10">
			     				 <input type="date" class="form-control" id="dateDebutEncheres" name="dateDebutEnchere"  required="required">
			    			</div>
		  			</div>
		  			
		  			<div class="form-group row">
		   				 <label for="dateFinEncheres" class="col-sm-2 col-form-label">Fin de l'enchère</label>
			   				 <div class="col-sm-10">
			     				 <input type="date" class="form-control" id="dateFinEncheres" name="dateFinEnchere" required="required">
			    			</div>
		  			</div>
		  			
					<fieldset form="formNouvelleVente">
					
						<legend>Retrait</legend>
							<div class="form-group row">
			   				 <label for="nomRue" class="col-sm-2 col-form-label">Rue: </label>
				   				 <div class="col-sm-10">
				     				 <input type="text" class="form-control" id="nomRue" value="Rue des licornes" name="nomRue" required="required">
				    			</div>
			  				</div>
			  			
							<div class="form-group row">
			   				 <label for="codePostal" class="col-sm-2 col-form-label">Code postal: </label>
				   				 <div class="col-sm-10">
				     				 <input type="text" class="form-control" id="codePostal" value="99999" name="codePostal" required="required">
				    			</div>
			  				</div>
			  			
							<div class="form-group row">
			   				 <label for="ville" class="col-sm-2 col-form-label">Ville: </label>
				   				 <div class="col-sm-10">
				     				 <input type="text" class="form-control" id="ville" value="PailletesCity" name="ville" required="required">
				    			</div>
			  				</div>
						
					</fieldset>
				<input type="submit" value="Enregistrer" class="btn btn-primary mb-2">
				<a href="<%=request.getContextPath()%>/Accueil">
		   			<input type="button" value="Annuler" class="btn btn-primary mb-2"/>
				</a>
			</form>
	</div>
	
	<div class="container">
	
		// Affichage bandeau vert ajout ok
<%-- 			<c:if test="${validerAjout == true}"> --%>
<!-- 					<div 	class="alert alert-success" role="alert"> -->
<!-- 						Article ajouté avec succés -->
<!-- 					</div> -->
<%-- 			</c:if>		 --%>
					<c:if test="${!empty listArticles}">
						<c:forEach var="a" items="${listArticles}">
							<p>
								${a}
							</p>
						</c:forEach>
					</c:if>
					
			
	</div>
</body>
</html>