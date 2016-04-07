<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuário</title>
</head>
<body>
<!--quando o cliente clicar em enviar vai para pagina de cadastro.recebe.jsp-->
<form action="Cadastro.recebe.jsp" method="POST">
Nome Completo: <input type="text" name="nome"/><br>
Senha:         <input type="password" name="senha"/><br>
Email:         <input type="text" name="email"/><br>


<input type="submit" value="Enviar"/>
</form>
</body>
</html>