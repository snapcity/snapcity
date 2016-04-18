<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONException"%>

<%@page language="java" import="snapcity.dao.DaoUsuario"%>
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

<title>Cadastra Usuario</title>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Usuário</h3>
			</div>
			<!doctype html>
			<html lang="en">
<head>
<meta charset="utf-8">
<title>jQuery UI Tabs - Default functionality</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
</head>
<body>

	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">Cadastro</a></li>
			<li><a href="#tabs-2">Alterar Cadastro</a></li>
			<li><a href="#tabs-3">Buscar Cadastro</a></li>
			<li><a href="#tabs-4">Excluir Cadastro</a></li>
		</ul>
		</ul>
		<div id="tabs-1">
			<p>
			<div class="panel-body">
				<form action="" method="POST">
					<div class="form-group">
						<label for="inputlg">Nome Completo</label> <input
							class="form-control" id="nome" name="nome" type="text">
					</div>
					<div class="form-group">
						<label for="inputlg">Senha</label> <input class="form-control"
							id="senha" name="senha" type="text">
					</div>
					<div class="form-group">
						<label for="inputsm">Email</label> <input class="form-control"
							id="email" name="email" type="text">
					</div>
					<input type="submit" id="cadastrar" class="btn btn-default"
						value="Enviar" />
				</form>
			</div>
			</p>
		</div>

		<div id="tabs-2">
			<p>
			<div class="panel-body">
				<form action="" method="POST">
					<div class="form-group">
						<label for="inputlg">Nome Completo</label> <input
							class="form-control" id="nome" name="nome" type="text">
					</div>
					<div class="form-group">
						<label for="inputlg">Senha</label> <input class="form-control"
							id="senha" name="senha" type="text">
					</div>
					<div class="form-group">
						<label for="inputsm">Email</label> <input class="form-control"
							id="email" name="email" type="text">
					</div>
					<input type="submit" id="cadastrar" class="btn btn-default"
						value="Enviar Alteração" />
				</form>
			</div>
			</p>
		</div>

		<div id="tabs-3">
			<p>
			<div class="panel-body">
				<form action="" method="POST">
					<div class="form-group">
						<label for="inputlg">Id</label> <input class="form-control"
							id="id" name="id" type="password">
					</div>
					<input type="submit" id="Buscar" class="btn btn-default"
						value="Buscar" />
				</form>
			</div>
			</p>
		</div>
		<div id="tabs-4">
			<p>
			<div class="panel-body">
				<form action="" method="POST">
					<div class="form-group">
						<label for="inputlg">Nome Completo</label> <input
							class="form-control" id="nome" name="nome" type="text">
					</div>
					<input type="submit" id="Excluir" class="btn btn-default"
						value="Excluir" />
				</form>
			</div>
			</p>
		</div>
	</div>


</body>
			</html>
		</div>
	</div>

	<%
		//recebe o valor digitado no campo usuario
		String nome = request.getParameter("nome");

		//recebe o valor digitado no campo senha
		String senha = request.getParameter("senha");

		//recebe o valor digitado no campo Email
		String email = request.getParameter("email");

		JSONObject usuario = new JSONObject();

		usuario.put("nome", nome);
		usuario.put("senha", senha);
		usuario.put("email", email);

		String user = usuario.toString();
		out.println("objeto original -> " + user);
		System.out.println("objeto original -> " + user);
	%>
	</div>

</body>
</html>