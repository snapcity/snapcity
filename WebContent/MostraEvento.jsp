<%@page import="snapcity.model.Evento"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@page language="java" import="snapcity.dao.DaoEvento"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"> </script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
 <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/jquery-2.0.3.min.js" type="text/javascript"></script>
<script src="js/script.js" type="text/javascript"></script>
<script src="js/mapa.js"></script>
<script type="text/javascript" src="js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>

<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCE_c3WG1QxjcPrwWNtp3MBZGnrues5Nk0&amp;sensor=false"></script>

 <script>
  
 
 function carregarPontos() {
	 
	    $.getJSON('rest/evento', function(evento) {
	 
	        $.each(evento , function(index, evento) {
	 
	            var marker = new google.maps.Marker({
	                position: new google.maps.LatLng(evento.latitude, evento.longitude),
	                map: map,
   
	            });          	 
	            var infowindow = new google.maps.InfoWindow(), marker;
	            	 
	            	google.maps.event.addListener(marker, 'click', (function(marker, i) {
	            	    return function() {
	            	        infowindow.setContent("<h3>"+evento.descricao+"</h3><p> <strong>Tags: </strong> "+ evento.tags + "</p>");
	            	       
	            	        infowindow.open(map, marker);
	            	    }
	            	})(marker))
	        });
	 
	    });
	 
	}
	 
	carregarPontos();
	
	   
	
 
         $(document).ready(function () {
             $("#exclui").click(function () {
                
                 var del = new Object();
                 del.id = $('#id').val();
                 
 
                 $.ajax({
                     url: 'http://localhost:8080/snapcity/rest/evento/'+del.id,
                     type: 'DELETE',
                     dataType: 'json',
                     //data:del,
                     success: function (data, textStatus, xhr) {
                         console.log(data);
                     },
                     error: function (xhr, textStatus, errorThrown) {
                         console.log('Error in Operation');
                     }
                 });
             });
         });
    
         
    </script>
 

<title>Mostra Eventos</title>
</head>
<body onload="carregarItens()">
	<div class="container">
	<%@ include file="menu.jsp" %>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Mostra Todos os Eventos</h3>
			</div>
			<div class="panel-body">
				
		<section>
			<h1>Eventos</h1>
			<!--Área que mostrará carregando-->
			<h2></h2>
			<!--Tabela-->
			<table id="minhaTabela" class="table table-striped">
				<caption>Retorno de dados</caption>
				<thead>
					<th>id</th>
					<th>Longitude</th>
					<th>Latitude</th>
					<th>Descricao</th>
					<th>Data de Cadastro</th>
					
					
				</thead>
				<tbody>
				</tbody>
			</table>
		</section>
	 <!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title>Google Maps API v3: Criando um mapa personalizado</title>
    </head>
 
    <body>
     <div id="mapa" style="height: 500px; width: 700px">
        </div>
 
        <!-- Maps API Javascript -->
        <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCE_c3WG1QxjcPrwWNtp3MBZGnrues5Nk0&amp;sensor=false"></script>
 
        <!-- Arquivo de inicialização do mapa -->
        <script src="js/mapa.js"></script>
    </body>
			</div>
		</div>
		
	</div>

</html>
</body>
</html>