<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONException"%>
<%@page import="snapcity.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro Recebido</title>
</head>
<body>
<% 
//recebe o valor digitado no campo usuario
String nome = request.getParameter("nome");

//recebe o valor digitado no campo senha
String senha = request.getParameter("senha");

//recebe o valor digitado no campo Email
String email = request.getParameter("email");

out.println("Cadastro efetuado com sucesso!");
out.println("Seja bem vindo " + nome);



JSONArray array = new JSONArray();

/*                  * Criação do Objeto JSONObject                  */   

Usuario json = new Usuario();

	json.setNome("nome" + nome); 
	json.setSenha("senha" + senha); 
	json.setEmail("email" + email); 

JSONObject jsonOne = new JSONObject();

	jsonOne.put("nome", json.getNome());
	jsonOne.put("senha", json.getSenha());
	jsonOne.put("email", json.getEmail());
	
Usuario pTwo = new Usuario();

	pTwo.setNome("nome" + nome); 
	pTwo.setSenha("senha" + senha); 
	pTwo.setEmail("email" + email);
	
JSONObject jsonTwo = new JSONObject();

	jsonTwo.put("none", pTwo.getNome()); 
	jsonTwo.put("senha", pTwo.getSenha());
	jsonTwo.put("email", pTwo.getEmail());
	
	array.put(jsonOne);
	array.put(jsonTwo);
	
	System.out.println("JSONArray: "  + array);

%>
</body>
</html>