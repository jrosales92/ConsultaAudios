<%@page import="com.bbva.manager.ConsultaManager"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String bucket = request.getParameter("bucket");

	ConsultaManager cm = new ConsultaManager();
	String[][] data = cm.getDefinicionBucket(bucket);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Monitor</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<style type="text/css">
/* 	body{background: rgba(226,226,226,1);} */
.button {
	padding-left: 90px;
}
/* 	button {margin-left: .5em;} */
/* 	input:focus, textarea:focus {border-color: #000;} */
/* 	input, textarea {font: 1em sans-serif;width: 300px; -moz-box-sizing: border-box;box-sizing: border-box;border: 2px solid #999;} */
/* 	label {display: inline-block;width: 90px;text-align: right;} */
/* 	form div + div {margin-top: 1em;} */
/* 	form {margin: 0 auto;width: 400px;padding: 1em;border: 3px solid #CCC;border-radius: 1em;} */
td {
	height: 100px;
}
</style>

</head>
<script type="text/javascript">
	function Consulta() {
		var selectbucket = document.getElementById("selectbucket").value;
		if (selectbucket != 0) {
			var res = "";
			var parametros = document.getElementById("params").value;
			document.forms[0].submit();
		} else {
			alert("Selecciona un bucket");
		}
	}

	function alCambiarBucket() {
		var selectbucket = document.getElementById("selectbucket").value;
		window.location.href = "./jsp/SearchAudios.jsp?bucket=" + selectbucket + "";
	}

	function alCargar() {
		var optcon = document.getElementById("selectbucket").value = "<%=bucket%>";
	// 	optcon.focus();
	}
</script>

<body onload="alCargar();">
	<form action="./jsp/MuestraDocumentos.jsp" method="post" id="buscar" target="frameResultados">
		<select style="font:bold; width: 146px;" id="selectbucket"
			name="selectbucket" class="enabled" onchange="alCambiarBucket();">
			<option value="0">Selecciona un bucket..</option>
			<option value="expunic">expunic</option>
			<option value="verint">verint</option>
		</select>
		<table id="tableExpunico" width="100%" border="0px" cellpadding="0" cellspacing="0">
			<tr>
			<%	
				for (int i = 0; i < data.length; i++) {
			%>
				<%if(i == 5){%>
					<tr>
					<td align="left"><label style="color:#243a51" for="name"><%=data[i][1]%></label></td>
					<td><input type="text" id="<%=data[i][0]%>" name="<%=data[i][0]%>" /></td>
				<%}else {%>
					<td align="left"><label style="color:#243a51" for="name"><%=data[i][1]%></label></td>
					<td><input type="text" id="<%=data[i][0]%>" name="<%=data[i][0]%>" /></td>
					<%if(i == 5){%>
						</tr>
					<%}%>	
				<%}%>	
			<%}%>
			</tr>
		</table>

		<div align="center" class="button">
			<input type="hidden" value="" id="params">
			<button id="Boton1" type="submit" onclick="Consulta();">Consultar</button>
			<input id="Boton2" type="reset" value="Borrar información">
		</div>
	</form>
</body>
</html>
