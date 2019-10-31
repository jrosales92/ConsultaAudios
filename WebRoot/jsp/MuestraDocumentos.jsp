<%@page import="com.bbva.manager.ConsultaManager"%>
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String bucket =request.getParameter("selectbucket")==null? "":(String)request.getParameter("selectbucket");

ConsultaManager cm = new ConsultaManager();
String[][] data = cm.getDefinicionBucket(bucket);
StringBuilder params = new StringBuilder();
for(int i=0; i< data.length; i++){
	params.append(""+data[i][0]+"="+ (request.getParameter(data[i][0])==null? "":(String)request.getParameter(data[i][0]))+"&");
}
System.out.println("Mis parametros son: " +params);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>Consulta de Expediente</title>
<style>
   div#inferior{margin:0px;border:0px;padding:0px;left:0px;top:0px;overflow:auto;position:absolute;vertical-align:middle;} 
   div#interno{ margin:0px;border:0px;padding:0px;z-index:1; position:absolute;overflow:hidden;_position:absolute;top:0px;white-space:nowrap;} 
 	html body,form {margin: 0px;padding:0px;border:0px;overflow:hidden;}
 	th {background-color: #243a51;padding:0px;text-align:left;border-left:1px solid #CCC;border-right:none;font-size:10px;font-weight:bold;height:35px;color:white;} 
 	tr.normalRow td {background: #FFF; } 
 	tr.alternateRow td {background: #EEE;} 
 	tr.tomadoRow td {background-color: yellow;color:#FF0000;} 
 	td{font-size:10px;padding:1px 2px 2px 3px;border-left:1px solid #CCC;border-right:none;border-bottom:1px solid #DDD;} 
 	table{border-right:1px solid #CCC;font-family: Verdana, Arial, Helvetica, sans-serif;} 
 	a,a:link,a:visited {color: #4166cc;	text-decoration:none;_width:100%;} 
 	a:hover {text-decoration: underline;}
 	.boton {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12pts;
	color: #023f9b;
	cursor: hand;
	
}	 
</style>
<link rel="stylesheet" href="../css/esperaModal.css">
<script type="text/javascript" src="../js/esperaModal.js"></script>
<script type="text/javascript" src="../js/jquery-1.2.6.min.js"></script> 
<script language="javascript" type="text/javascript"> 

var numPagina=0;
var numRegistros=0;
var xmlHttp, xml;// global instance of XMLHttpRequest
var ajax, jx;// global instance of XMLHttpRequest


 		$(document).ready(function(){ 
 			$(window)
 				.load(function(){
 					dimencionaWin();
 					scrollInfe();
 					dimencionaInfer();
 				})
 				.resize(function(){
 					dimencionaWin();
 					scrollInfe();
 					dimencionaInfer();
 				}); 
 			$("div#inferior").scroll(function () {scrollInfe();}); 
 			$("div#inferior").resize(function(){dimencionaInfer();}); 
 		});
 		
	function dimencionaWin(){
		var hWindow = $(window).height();                                                    
		var wWindow = $(window).width();                                                    
		$("div#inferior").css({height:hWindow,width:wWindow,overflow:"auto"});	
  	}                                                                                    
  	                                                                                     
  	function scrollInfe(){
		var sInf = $("div#inferior");                                                        
		$("div#interno").css({left:"-"+sInf.scrollLeft()+"px",display:"inline"});            
		var wInterno = $(window).width()-16;                                                 
		$("div#interno").css({width:wInterno+sInf.scrollLeft()+"px"});				  	           
  	}                                                                                  
  	                                                                                     
  	function dimencionaInfer(){                                                          
        var wTable = $("table#tablaDoc").width();  
        var wTableEncabeza = $("table#tablaDocEncabeza").width();                       
                                                                                        
      	if(wTable>wTableEncabeza){                                                 
      		$("table#tablaDocEncabeza").css("width",wTable);                               
      	}else{                                                                           
      		$("table#tablaDoc").css("width",wTableEncabeza);                               
      	}                                                                                
      	                                                                          
      	var a = [0,1,2];                                                     
		jQuery.each(a, function() {                                                          
			try{                                                                               
				var leftTit = $("th#tit"+this).get(0).offsetLeft;                                
				var widthTit = $("th#tit"+this).get(0).offsetWidth;                              
				var heightTit = $("th#tit"+this).get(0).offsetHeight;                            
			}catch(e){}	                                                                       
			if (leftTit!=undefined&&widthTit!=undefined&&heightTit!=undefined){                
				$("th#titVista"+this).css({left:leftTit, width:widthTit, height:heightTit});     
			}                                                                                  
		});                                                                           
	}
		                                                                             
	function blockButtonBuscar(bloquea){
		if(bloquea){
			parent.framePrincipal.document.getElementById('BuscarButton').disabled = true;
			parent.framePrincipal.document.getElementById('BuscarButton').className = "boton";
		}else{
			parent.framePrincipal.document.getElementById('BuscarButton').disabled = false;
			parent.framePrincipal.document.getElementById('BuscarButton').className = "enabled";
		}

	}

	function inicializa(){
// 		blockButtonBuscar(true);
		soloesperarGeneral();
		numPagina++;
		createXmlHttpRequest();
			var uu="../ResultDocuments?bucket=<%=bucket%>&<%=params%>";
<%-- 		var uu="../ResultDocuments?bucket=<%=bucket%>&nc=<%=numCliente%>&ct=<%=nomCliente%>"; --%>
<%-- 		var uu="../vistaHistorico?tituloAplicacion=<%=tituloAplicacion%>&numCliente=<%=numCliente%>&nomCliente=<%=nomCliente%>&descripcion=<%=descripcion%>&folio=<%=folio%>&numPagina="+numPagina+"&from=<%=fechaInicio%>&to=<%=fechaFin%>&metodo=<%=tipoBusqueda%>&rand="+Math.random(); --%>
		ajax.open("GET",uu,true);
		ajax.onreadystatechange=consulta;
		ajax.send(null);
	}

	function PaginaAnterior( ){
// 		blockButtonBuscar(true);
// 		soloesperarGeneral();
		if(numPagina==1){
			alert("No hay datos previos");
// 			remove_soloesperarGeneral();
// 			blockButtonBuscar(false);
		}else{
			numPagina-=1;
			createXmlHttpRequest();
<%-- 			var uu="../vistaHistorico?tituloAplicacion=<%=tituloAplicacion%>&numCliente=<%=numCliente%>&nomCliente=<%=nomCliente%>&descripcion=<%=descripcion%>&folio=<%=folio%>&numPagina="+numPagina+"&from=<%=fechaInicio%>&to=<%=fechaFin%>&metodo=<%=tipoBusqueda%>&rand="+Math.random(); --%>
			ajax.open("GET",uu,true);
			ajax.onreadystatechange=consulta;
			ajax.send(null);
		}
	}

	function  PaginaSiguiente(){
// 		blockButtonBuscar(true);
// 		soloesperarGeneral();
		if(numRegistros<500){
			alert("No hay mas registros");
// 			remove_soloesperarGeneral();
// 			blockButtonBuscar(false);
		}else{
			numRegistros=0;
			numPagina+=1;
			createXmlHttpRequest();
<%-- 			var uu="../vistaHistorico?tituloAplicacion=<%=tituloAplicacion%>&numCliente=<%=numCliente%>&nomCliente=<%=nomCliente%>&descripcion=<%=descripcion%>&folio=<%=folio%>&numPagina="+numPagina+"&from=<%=fechaInicio%>&to=<%=fechaFin%>&metodo=<%=tipoBusqueda%>&rand="+Math.random(); --%>
			ajax.open("GET",uu,true);
			ajax.onreadystatechange=consulta;
			ajax.send(null);
		}
	}

	function createXmlHttpRequest(){
		var bActiveX =false;
		try{			// Firefox, Opera 8.0+, Safari
			xmlHttp=new XMLHttpRequest();
			ajax=new XMLHttpRequest();
		}catch (e){
			try{	// Internet Explorer
				xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
				ajax=new ActiveXObject("Msxml2.XMLHTTP");
			}catch (e){
				try{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
					ajax=new ActiveXObject("Microsoft.XMLHTTP");
				}catch (e){
					alert("Su browser no suporta AJAX!");
					return false;
				}
			}
		}
	}

	function consulta(){
		if(ajax.readyState==4){
			if(ajax.status==200){
				var salida = ajax.responseText;
				var inicio = 8; // la respuesta inicia con <codigo> = 8
				var fin = salida.lastIndexOf("</codigo>");
				var aux=salida.substring(inicio,fin);
				if(aux == -1){	//Session no valida
					alert("Sesion no valida");
					top.location.href="../Salir.jsp";
				}else{ 
					if ( aux == -2){
						alert("Usuario no valido");
						top.location.href="../Salir.jsp";
					}else{
						if(aux==-3){
							alert("Error de comunicacion");
							remove_soloesperarGeneral();
// 							blockButtonBuscar(false);
						}else{
							numRegistros=aux
							if(aux == -4){
								alert("La busqueda es muy grande favor de delimitarla");
								remove_soloesperarGeneral();
// 								blockButtonBuscar(false);
							}else{
								aux=salida.substring(fin+10);
								document.getElementById("nn").innerHTML=aux;							
								stripedTable();
								dimencionaWin();
	 							scrollInfe();
	 							dimencionaInfer();
	 							remove_soloesperarGeneral();
// 	 							blockButtonBuscar(false);
	 						}
						}
					}
				}			
			}					
		}
	}

	function removeClassName (elem, className) {
		elem.className = elem.className.replace(className, "").trim();
	}

	function addCSSClass (elem, className) {
		removeClassName (elem, className);
		elem.className = (elem.className + " " + className).trim();
	}
	
	String.prototype.trim = function() {
		return this.replace( /^\s+|\s+$/, "" );
	}
	
	function stripedTable() {
		if (document.getElementById && document.getElementsByTagName) {  
			var allTables = document.getElementsByTagName('table');
			if (!allTables) { return; }
			
			for (var i = 0; i < allTables.length; i++) {
				if (allTables[i].className.match(/[\w\s ]*tablaDatos[\w\s ]*/)) {
					var trs = allTables[i].getElementsByTagName("tr");
					for (var j = 0; j < trs.length; j++) {
						removeClassName(trs[j], 'alternateRow');
						addCSSClass(trs[j], 'normalRow');
					}
					for (var k = 0; k < trs.length; k += 2) {
						removeClassName(trs[k], 'normalRow');
						addCSSClass(trs[k], 'alternateRow');
					}
				}
			}
		}
	}
	
	function Generar(){
		var jsonParam = "";
		var checkeados = "";
		var nombre = "";
		var total = document.getElementById("totalArch").value;
		var ar = document.getElementById("arrayValues").value;
		for(i = 0; i < total; i++){
			if(document.getElementById("check" + i).checked){
				checkeados = checkeados + i + "|"
			}
		}
		
		if(ar != "" && checkeados != ""){
			location.href = "../DescargaBatch?param=" + ar + "&checking=" + checkeados;
		}else{
			alert("Debes seleccionar al menos 1 registro");
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
	</head>
<body onload="javascript:inicializa();">
<div id="interno"> 
 	<table id="tablaDocEncabeza" class="tablaDatos" width="100%" cellpadding="0" cellspacing="0" border="0" >  
 	  <thead>	
 		<tr height="35px">
			<th id="titVista0" >&nbsp;Registro</th>
 		<%
 		int o = 1;
 		float percent  = (100 / (data.length+1));
 		for(int i=0; i< data.length; i++){
 			if(!"t".equalsIgnoreCase(data[i][0])){
		%>
<<<<<<< HEAD
			<th id="titVista<%=o%>" width="<%=percent%>%">&nbsp;<%=data[i][1]%></th>
=======
			<th width="35%" id="titVista<%=o%>">&nbsp;<%=data[i][1]%></th>
>>>>>>> 59283f55606744ac4bee0984c59c4b1725ba8d03
		<%
				o++;
			}
		}
 		%>
		</tr>
 	  </thead>		
   </table>
</div>   
<div align="right"  id="inferior">
<div id="nn"></div>
<input type="checkbox" value="Marcar" onclick="marcar(this);">Marcar Todos</input>
<input type="button" value="Generar Batch" onclick="Generar();"></input>
<div align="center"> <br>
	<table cellpadding="0" cellspacing="0" border="0" >
	 	<tr>
		<td align="right">
			<input type="button" onclick="javascript:PaginaAnterior();" class="boton" value="Anterior">
			<input type="button" onclick="javascript:PaginaSiguiente();" class="boton" value="Siguiente">
		</td>
		</tr>
	</table>

</div>
</div>
</body>

</html>
