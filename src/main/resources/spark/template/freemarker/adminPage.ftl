<!DOCTYPE html>
<html lang="fr">
<head>
	<#include "header.ftl">
</head>

<body>   

	<!--======================================== Section navigation ====================================-->
	<#include "nav.ftl">


	<!--================================================================================================-->
    <section class="content-section">
		<div class="container containerAttr" style="padding-bottom:50px;">
			<h1>Administration</h1>
			
            <div class="row">			
                <div class="col-md-offset-1 col-md-5 col-sm-5"> 
                    <div class="panel panel-default" >
                        <div class="panel-heading ">- Liste utilisateurs - </div>
                        <div class="panel-body">            
                            Récupérer la liste des utilisateurs en cliquant <a href="">ici</a> <br/>(fichier au format CSV)<br/>
                            <hr>
                            <small>Visualisez la liste de tous les utilisateurs de l'application.</small>
                            <br/><br/>
                            <a href="usersList.html" class="btn btn-primary tailleText">Visualiser</a>	
                        </div>
                    </div>
                </div>
                <div class="col-md-5 col-sm-5">
                    <div class="panel panel-default" >
                        <div class="panel-heading">- Statistiques d'utilisation - </div>
                        <div class="panel-body">
							historiques de connexions<br>
                            historiques de réservation & partage
							<br>
                            <hr>
                            <small>Vous pouvez consulter vos historiques d'utilisations et de partage.<br/></small>
                            <br/>
                            <a href="acountStats.html" class="btn btn-primary tailleText">Visualiser</a>	                            
                        </div>
                    </div> 
                </div>
            </div>
			           
		</div>
	</section>


	<!--==================================== javascripts files section  ==================================-->
	<#include "commonjs.ftl">


</body> 
</html> 