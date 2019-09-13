<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Audios.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
body{background: rgba(226,226,226,1);}
.button {padding-left: 90px;}
button {margin-left: .5em;}

input:focus, textarea:focus {border-color: #000;}

input, textarea {font: 1em sans-serif;width: 300px; -moz-box-sizing: border-box;box-sizing: border-box;border: 2px solid #999;}

label {display: inline-block;width: 90px;text-align: right;}

form div + div {margin-top: 1em;}

form {margin: 0 auto;width: 400px;padding: 1em;border: 3px solid #CCC;border-radius: 1em;}

</style>

</head>
<script type="text/javascript">
function Consulta(){
	var selectbucket = document.getElementById("selectbucket").value;
	if(selectbucket != -1){
		var numeroCliente = document.getElementById("numeroCliente").value;
		var numeroContrato = document.getElementById("numeroContrato").value;
		document.forms['form1'].action = "./jsp/ConsultaAudios.jsp?numcliente=" + numeroCliente + "&numeroContrato=" + numeroContrato + "&bucket=" + selectbucket;
		document.forms['form1'].submit();
	}else{
		alert("Selecciona un bucket");
	}
}
</script>

<body>
	<h1>Realizar busqueda por:</h1>
<!-- 	<form id="form1" action="" method="POST"> -->
<!-- 		<select id="opciones" name="opciones"> -->
<!-- 			<option selected value="0"> Elige el tipo de sercivio </option> -->
<!-- 			<option value="1">Banquero Remoto</option> -->
<!-- 		<option value="2">Cobranza</option> -->
<!-- 		</select> -->
<!-- 		<div style="position: absolute; top: 15%"> -->
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<td><label for="NumClinente">Numero de cliente:</label><input type="text" id="numcliente" /></td> -->
<!-- 					<td><label for="NumContrato">Numero de Contrato:</label> <input type="text" id="numcontrato" /></td> -->
<!-- 					<td><label for="Fecha">Fecha:</label> <input type="text" id="fecha"/></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td><button type="button" onclick="Consulta();">Generar Audios</button></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</div> -->
<!-- 	</form> -->

<form id="form1" action="" method="post">
	<select id="selectbucket">
		<option value="-1">Selecciona un bucket..</option>
		<option value="expunic">expunic</option>
		<option value="verint">verint</option>
		<option value="bucketfroy">bucketfroy</option>
	</select>
    <div>
        <label for="name">Numero Cliente:</label>
        <input type="text" id="numeroCliente" name="numcliente" />
    </div>
    <div>
        <label for="mail">Numero de Contrato:</label>
        <input type="text" id="numeroContrato" name="numcontrato" />
    </div>
    <div>
        <label for="msg">Fecha:</label>
        <input type="text" id="fecha" name="fecha" />
    </div>
    
    <div class="button">
        <button type="submit" onclick= "Consulta();">Consultar</button>
    </div>
</form>
</body>

</body>
</html>
