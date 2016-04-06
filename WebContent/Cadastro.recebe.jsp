<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro Recebido</title>
</head>
<body>
<% 
//recebe o valor digitado no campo usuario
String usuario = request.getParameter("usuario");

//recebe o valor digitado no campo senha
String senha = request.getParameter("senha");

//recebe o valor digitado no campo Email
String email = request.getParameter("email");

out.println("Cadastro efetuado com sucesso!");
out.println("Seja bem vindo " + usuario);

%>
</body>
</html>