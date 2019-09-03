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

	String numCliente = request.getParameter("numcliente");
	String numcontrato = request.getParameter("numcontrato");
	String fecha = request.getParameter("fecha");
	
// 	ConsultaManager cm = new ConsultaManager();
// 	List<EjempoConsulta> listabean = null;
// 	if(!numCliente.equalsIgnoreCase("")){
// 		System.out.println("entro a numcliente");
// 		listabean = cm.EjempoConsultaNumCliente(numCliente);
// 	}else if(!numcontrato.equalsIgnoreCase("")){
// 		System.out.println("entro a numcontrato");
// 		listabean = cm.EjempoConsultaNumContrato(numcontrato);
// 	}
	
	Client client = Client.create();
	WebResource webResource = client.resource("http://150.100.22.50:9090/v3/_search/expunic/expunic");

	String input = "{\"must\":{\"t\": \"EXPUNICO\", \"nc\": \"D0176518\", \"ct\": \"007453460000000040\"}}";
	
	ClientResponse clien = webResource.type("application/json").post(ClientResponse.class, input);
	if (clien.getStatus() != 202) {
		throw new RuntimeException("Failed : HTTP error code : " + clien.getStatus());
	}

	System.out.println("Output from Server .... \n");
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
body{
background: rgba(226,226,226,1);

}
table {
	width: 50%;
}

th, td {
	color: black;
	width: 25%;
}
.odd th, .odd td {
   background: #eee;
}
th {
	background: #eee;
}

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
		checkboxes = document.getElementsByTagName('check'); //obtenemos todos los controles del tipo Input
		for (i = 0; i < checkboxes.length; i++){ //recoremos todos los controles
			alert("Llega aqui")
			if (checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
			{
				checkboxes[i].checked = sourse.checked; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
			}
		}
	}
</script>

<body>
<div style="color: black;">
	<h1>Selecciona los audios:</h1>
	<form id="form1" action="" method="POST">
		<table border="1">
			<tr>
				<th><strong></strong></th>
				<th><strong>NumeroCliente</strong></th>
				<th><strong>NumeroContrato</strong></th>
			</tr>
			<%
				int row = 0;
				for (int i = 0; i < geodata.length(); i++) {
				JSONObject obj = (JSONObject) geodata.getJSONObject(i);
				System.out.println("aplicacion: " + obj.getString("dd") + " ext: " + obj.getString("e") + " sha1N: " + obj.getString("sha1N"));
			%>
			<tr>
				<td><strong><input type="checkbox" id="check<%=row%>" /></strong></td>
				<td>
					<strong><%=obj.getString("dd")%></strong>
					<input type="hidden" id="cdAplicacion<%=row%>" value="<%=obj.getString("dd")%>" />			
				</td>
				<td>
					<strong><%=obj.getString("e")%></strong>
					<input type="hidden" id="nbAplicacion<%=row%>" value="<%=obj.getString("e")%>" />
				</td>
			</tr>
			<%
				row++;
				}
			%>
			<tr>
				<td><button type="button" onclick="marcar(this);">Marcar Todos</button></td>
				<td><button type="button" onclick="Generar();">Generar Batch</button></td>
			</tr>
		</table>
		
	</form>
	</div>
	<input type="hidden" value="<%=row%>" id="totalArch">
</body>
</html>
