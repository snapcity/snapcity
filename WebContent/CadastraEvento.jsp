<%@page import="snapcity.model.Evento"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@page language="java" import="snapcity.dao.DaoEvento"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

<title>Cadastra Eventos</title>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Cadastra Evento</h3>
			</div>
			<div class="panel-body">
				<form action="" method="POST">
					<div class="form-group">
						<label for="inputdefault">Foto</label> <input class="form-control"
							id="foto" name="foto" type="file" accept="image/*">
					</div>
					<div class="form-group">
						<label for="inputlg">Descrição</label> <input class="form-control"
							id="descricao" name="descricao" type="text">
					</div>
					<div class="form-group">
						<label for="inputsm">Tags</label> <input class="form-control"
							id="tags" name="tags" type="text">
					</div>
					<div class="form-group">
						<label for="inputsm">Latitude</label> <input class="form-control"
							id="latitude" name="latitude" type="text">
					</div>
					<div class="form-group">
						<label for="inputsm">Longitude</label> <input class="form-control"
							id="longitude" name="longitude" type="text">
					</div>

					<input type="submit" id="cadastrar" class="btn btn-default"
						value="Enviar" />
				</form>
			</div>
		</div>
		<%
		snapcity.dao.DaoEvento eventos = new DaoEvento();
			String foto = request.getParameter("foto");
			foto = eventos.encodeToString("/Users/marcelodasrodrigues/Desktop/"+foto); 
			String descricao = request.getParameter("descricao");
			String tags = request.getParameter("tags");
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			
			
			
			out.println("Foto: <img SRC=\"data:image/jpeg;base64,"+foto+"\">");

			
			
			out.println(eventos.toJson(foto, descricao, tags, latitude, longitude));
	%>
	</div>

</body>
</html>