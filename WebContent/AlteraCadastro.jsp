<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@page import="org.json.JSONObject"%>
  <%@page import="snapcity.model.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exemplo Ajax</title>
<!-- Inclusão do Jquery  -->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.tmpl.min.js"></script>
 
<style type="text/css" media="all">
#carregando {
    text-align: center;
    display:none;
    }
 
</style>
 
<script type="text/javascript">
$(document).ready(function() {
    $('#listaClientes').click(function() {
        $('#carregando').show();
        $.ajax({
            url : "http://localhost:2020/snapcity/rest/usuarios", //url do Servlet
            type : "post",
            contentType : "application/json; charset=utf-8", //define o myme type da requisição
            dataType : "json", //define o tipo de dado retornado pela requisição
            context : jQuery('#bodyTable'), //contexto da resposta
            success : function(listaClientes) { //função executada em caso de sucesso
                $('#bodyTable').empty();
                $('#clienteTemplate').tmpl(listaClientes)
                        .appendTo('#bodyTable'); //adiciona os dados ao template e anexa ao container
                $('#carregando').hide();
 				
            }
        });
    });
});
</script>
 
<!-- Template -->
<script id="clienteTemplate" type="x-jquery-tmpl">
            <tr>
                <td>{{= id }}</td>
                <td>{{= nome }}</td>
            </tr>
 
</script>
<!-- / Template -->
 
</head>
<body>

    <input type="submit" id="listaClientes" class="btn btn-default"
						value="Excluir" />Listar Clientes</a>
 
    <br />
 
    <table id="containerTable" style="text-align: center;">
        <thead>
            <tr>
                <th>Código</th>
                <th>Nome</th>
            </tr>
        </thead>
        <tbody id="bodyTable">
 
        </tbody>
    </table>
 
    <div id="respostaServlet"></div>
 
    <div id="carregando">
        <img  src="images/carregando.gif" alt="Carregando..." />
    </div>
</body>
</html>