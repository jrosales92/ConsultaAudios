<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.sun.jersey.api.client.ClientResponse"%>
<%@page import="com.sun.jersey.api.client.WebResource"%>
<%@page import="com.sun.jersey.api.client.Client"%>
<%@page import="com.bbva.bean.EjempoConsulta"%>
<%@page import="com.bbva.manager.ConsultaManager"%>
<%@page import="com.bbva.bean.TATN001_APLICACION"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	String bucket = request.getParameter("bucket");
	ConsultaManager cm = new ConsultaManager();
	String[][] data = cm.getDefinicionBucket(bucket);
	String input = "";
	JSONObject search = new JSONObject();
	JSONObject filtros = new JSONObject();
	for (int i = 0; i < data.length; i++) {
		filtros.put(data[i][0], request.getParameter(data[i][0]));
	}
	search.put("must", filtros);

	//EJEMPLO DE ENDPOINT http://150.100.22.50:9090/v3/_search/expunic/expunic
	//EJEMPLO DE ENDPOINT http://150.100.22.50:9090/v3/_search/verint/verint
	String endPointArchiving = "http://150.100.22.50:9090/v3/_search/" + bucket + "/" + bucket;
	Client client = Client.create();
	WebResource webResource = client.resource(endPointArchiving);
// 	String input = "{\"must\":{\"t\": \"EXPUNICO\", \"nc\": \"D0176518\", \"ct\": \"007453460000000040\"}}";
	System.out.println("Consultaremos:  " + search);
	ClientResponse clien = webResource.type("application/json").post(ClientResponse.class, search.toString());
	if (clien.getStatus() != 202) {
		throw new RuntimeException("Failed : HTTP error code : " + clien.getStatus());
	}

	String output = clien.getEntity(String.class);

	JSONObject json = new JSONObject(output);
	JSONArray geodata = json.getJSONArray("result");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'ConsultaAudios.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
table {width: 50%;}
th, td {color: black; width: 25%;}
tr.normalRow td {background: #FFF;}
tr.alternateRow td {background: #EEE;}
</style>
</head>
<script type="text/javascript">

function Generar(){
	var cds = "";
	var nbs = "";
	var nombre = "";
	var total = document.getElementById("totalArch").value;
	for(i = 0; i < total; i++){
		if(document.getElementById("check" + i).checked){
			cds  = cds + document.getElementById("cdAplicacion" + i).value + "|";
			nbs  = nbs + document.getElementById("nbAplicacion" + i).value + "|";
		}
	}
	
	if(cds != "" && nbs != ""){
		location.href = "./DescargaBatch?cds=" + cds + "&nbs=" + nbs;
	}
	}
	function marcar(sourse) {
		checkboxes = document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
		for (i = 0; i < checkboxes.length; i++){ //recoremos todos los controles
			if (checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
			{
				checkboxes[i].checked = sourse.checked; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
			}
		}
	}
</script>

<body>
<div style="color: black;">
	<form id="form1" action="" method="POST">
		<table border="1">
			<tr>
				<th><strong></strong></th>
				<th><strong><%=data[1][1] %></strong></th>
				<th><strong><%=data[2][1] %></strong></th>
			</tr>
			<%
				int row = 0;
				for (int i = 0; i < geodata.length(); i++) {
				JSONObject obj = (JSONObject) geodata.getJSONObject(i);
			%>
			<tr class="<%=((i % 2) == 0 ? "alternateRow" : "normalRow")%>">
				<td><strong><input type="checkbox" id="check<%=row%>" name="check" /></strong></td>
				<td>
					<strong><%=obj.getString("nc")%></strong>
					<input type="hidden" id="cdAplicacion<%=row%>" value="<%=obj.getString("nc")%>" />			
				</td>
				<td>
					<strong><%=obj.getString("ct")%></strong>
					<input type="hidden" id="nbAplicacion<%=row%>" value="<%=obj.getString("ct")%>" />
				</td>
			</tr>
			<%
				row++;
				}
			%>
		</table>
	</form>
	</div>
	<input type="hidden" value="<%=row%>" id="totalArch">
	<input type="checkbox" value="Marcar" onclick="marcar(this);">Marcar Todos</input>
	<input type="button" value="Generar Batch" onclick="Generar();"></input>
</body>
</html>
