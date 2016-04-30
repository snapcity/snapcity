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
	
<script type="text/javascript">
var Base64 = {


	    _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",


	    encode: function(input) {
	        var output = "";
	        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
	        var i = 0;

	        input = Base64._utf8_encode(input);

	        while (i < input.length) {

	            chr1 = input.charCodeAt(i++);
	            chr2 = input.charCodeAt(i++);
	            chr3 = input.charCodeAt(i++);

	            enc1 = chr1 >> 2;
	            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
	            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
	            enc4 = chr3 & 63;

	            if (isNaN(chr2)) {
	                enc3 = enc4 = 64;
	            } else if (isNaN(chr3)) {
	                enc4 = 64;
	            }

	            output = output + this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) + this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

	        }

	        return output;
	    },


	    decode: function(input) {
	        var output = "";
	        var chr1, chr2, chr3;
	        var enc1, enc2, enc3, enc4;
	        var i = 0;

	        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

	        while (i < input.length) {

	            enc1 = this._keyStr.indexOf(input.charAt(i++));
	            enc2 = this._keyStr.indexOf(input.charAt(i++));
	            enc3 = this._keyStr.indexOf(input.charAt(i++));
	            enc4 = this._keyStr.indexOf(input.charAt(i++));

	            chr1 = (enc1 << 2) | (enc2 >> 4);
	            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
	            chr3 = ((enc3 & 3) << 6) | enc4;

	            output = output + String.fromCharCode(chr1);

	            if (enc3 != 64) {
	                output = output + String.fromCharCode(chr2);
	            }
	            if (enc4 != 64) {
	                output = output + String.fromCharCode(chr3);
	            }

	        }

	        output = Base64._utf8_decode(output);

	        return output;

	    },

	    _utf8_encode: function(string) {
	        string = string.replace(/\r\n/g, "\n");
	        var utftext = "";

	        for (var n = 0; n < string.length; n++) {

	            var c = string.charCodeAt(n);

	            if (c < 128) {
	                utftext += String.fromCharCode(c);
	            }
	            else if ((c > 127) && (c < 2048)) {
	                utftext += String.fromCharCode((c >> 6) | 192);
	                utftext += String.fromCharCode((c & 63) | 128);
	            }
	            else {
	                utftext += String.fromCharCode((c >> 12) | 224);
	                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
	                utftext += String.fromCharCode((c & 63) | 128);
	            }

	        }

	        return utftext;
	    },

	    _utf8_decode: function(utftext) {
	        var string = "";
	        var i = 0;
	        var c = c1 = c2 = 0;

	        while (i < utftext.length) {

	            c = utftext.charCodeAt(i);

	            if (c < 128) {
	                string += String.fromCharCode(c);
	                i++;
	            }
	            else if ((c > 191) && (c < 224)) {
	                c2 = utftext.charCodeAt(i + 1);
	                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
	                i += 2;
	            }
	            else {
	                c2 = utftext.charCodeAt(i + 1);
	                c3 = utftext.charCodeAt(i + 2);
	                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
	                i += 3;
	            }

	        }

	        return string;
	    }

	}
	    
	    
	    
	var encode = document.getElementById('encode'),
	    decode = document.getElementById('decode'),
	    output = document.getElementById('output'),
	    input = document.getElementById('input');


	encode.onclick = function() {
	    output.innerHTML = Base64.encode(input.value);
	}
	    
	decode.onclick = function() {
	    var $str = output.innerHTML;
	    output.innerHTML = Base64.decode($str);
	}  
</script>	
	
	<script type="text/javascript">
	$(document).ready(function() {
        $("#cadastrar").click(function() {
               $.ajax({
               url: "http://localhost:8080/snapcity/rest/evento",
               contentType: "application/json; charset=utf-8",
               type: "post",
               dataType:"json",
               data: JSON.stringify({id : $('#id').val(), descricao : $('#descricao').val(), longitude : $('#longitude').val(),  latitude : $('#latitude').val()),
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
					<input type="hidden" name="id" id="id" value="20" >
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
			
			
			
	%>
	</div>

</body>
</html>