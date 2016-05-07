<%@page import="snapcity.model.Evento"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
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
<script>
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
	
<meta charset="utf-8">
<title>jQuery UI Tabs - Default functionality</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.js"></script>



<script type='text/javascript'>
	
function encodeImageFileAsURL() {

  var filesSelected = document.getElementById("foto").files;
  if (filesSelected.length > 0) {
    var fileToLoad = filesSelected[0];

    var fileReader = new FileReader();

    fileReader.onload = function(fileLoadedEvent) {
      var srcData = fileLoadedEvent.target.result; // <--- data: base64
	  console.log(srcData);
      var newImage = document.createElement('img');
      newImage.src = srcData; 
   

      document.getElementById("imgTest").innerHTML = newImage.outerHTML;
    //  jsonString = "Converted Base64 version is " + document.getElementById("imgTest").innerHTML;
      jsonString = document.getElementById("imgTest").innerHTML;
      
     // console.log(jsonString);
    }
    fileReader.readAsDataURL(fileToLoad);
    return encodeImageFileAsURL;
  }
}
</script>
<script>
$(document).ready(function() {
    $("#cadastrar").click(function() {
    	
	    	var p = new Object();
	        p.foto = srcData;
	      // p.foto = "sdfsdfsdfbsdfsdfdsf";
        
           $.ajax({
           url: "http://localhost:8080/snapcity/rest/evento",
           contentType: "application/json; charset=utf-8",
           type: "post",
           dataType:"json",
          data: JSON.stringify({foto : p.foto, descricao : $('#descricao').val(), longitude : $('#longitude').val(),  latitude : $('#latitude').val(), tags : $('#tags').val(),  id_usuario : $('#id_usuario').val()}),
        
           success: function(data) {
               console.log(data);
               
           }
           
       });
   });
   
});

</script>
		
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
						<input id="foto" type="file" onchange="encodeImageFileAsURL();" /> 
						<!-- <input id="foto" type="text" name="foto" value="asdasdasd" />-->
						
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
							id="latitude" name="latitude" type="number" min="0">
					</div>
					
					<div class="form-group">
						<label for="inputsm">Longitude</label> <input class="form-control"
							id="longitude" name="longitude" type="number" min="0">
					</div>
					
					 <input id="id_usuario" name="id_usuario" type="hidden" value="20">
					
					<input type="submit" id="cadastrar" class="btn btn-default"
						value="Enviar" />
						
				</form>
			</div>
		</div>
		
		<div id="imgTest"></div>
</body>
</html>