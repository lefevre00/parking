<!DOCTYPE html>
<html lang="fr">
<head>
	<#include "header.ftl">
	<link rel="stylesheet" href="${ressourcesDirectory}css/login.css">
	<link rel="stylesheet" href="${ressourcesDirectory}css/mediaqueries.css" type="text/css" />	
	<style>
		.placeRegister{
			top:180px;
		}
	</style>
</head>

<body>
  
	<!--======================================== Section navigation ====================================-->
	<#include "navAdmin.ftl">	 
  
	<!--======================================== User creation form ====================================-->
	<section id="conteneur" class="jumbotron" style="background-color:transparent !important;">	
		
		<div id="wrapper">

			<form method="post" role="form" name="login-form" class="login-form">
			
				<div class="header">
					<h1>Création de compte</h1>
					<span>Veuillez compléter les champs ci-dessous.</span>
				</div>
			
				<div class="content">
			        <#if email??>
						<input class="input" name="email" type="email" placeholder="adresse@amdm.fr" value="${email}" required/>
			        <#else>
			          	<input class="input" name="email" type="email" placeholder="adresse@amdm.fr" required/>
	                </#if>
					<div class="email-icon emailRegister"></div>
					<input class="input place" name="placeNumber" id="placeNumber" type="text"  placeholder="Numéro de place (si attribuée)" size="3" maxlength="3"/>
					<div class="place-icon placeRegister"></div>	
				</div>
				
				<div class="footer">
					<input type="submit" name="submit" value="Valider" class="valider" />
					<a href="${routesDirectory}protected/usersList" class="register BtnRetour">Retour</a>
				</div>
				
			</form>

		</div>
			
	</section>

	<!--================================== javascripts files section  ===============================-->
	<#include "commonjs.ftl">	
	
	<!-- cryptage md5 -->
	<#include "md5.ftl">
</body>
</html>
