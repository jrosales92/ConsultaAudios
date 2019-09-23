<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%
String aplicacion = request.getParameter("aplicacion");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Monitor Batch</title>

	<meta http-equiv="pragma" content="no-cache">
	
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Expediente">
	<script type="text/javascript" src="../js/jquery-1.2.6.min.js"></script>
	<link rel="stylesheet" href="../css/estilo-admin.css" type="text/css"></link>

	<style type="text/css">
		div#derecho2sup {margin:0px;border-bottom:1px solid #CCC;padding:0px;left:0px;bottom:5px;right:5px;top:80px;width:100%;position:absolute;vertical-align:middle;}
		div#derecho2infe{margin:0px;border:1px solid #CCC;padding:0px;left:0px;top:48%;width:100%;height:49%;position:absolute;vertical-align:middle;}
		div#central     {margin:0px;padding:0px;width:88px;height:5px;position:absolute;vertical-align:middle;z-index: 99;left:45%}

		#tablaSuperior{height:25px;font-family:Arial;font-size:12px;color:white;}
		B.enlace{border:0px ;cursor:hand;vertical-align: middle;}
 		body, form {margin: 0px;padding: 0px;	border: 0px;overflow: hidden;}
 		fieldset {margin: 0px;padding: 0px;	border:black 1px solid;width: 100%;height: 100%;}
 		.enlaces tr td a,.enlaces tr td a:link,.enlaces tr td a:visited {color: #4166cc;	text-decoration:none;_width:100%;}
	</style>

<script language="javascript" type="text/javascript">
			$(document).ready(function(){
				$(window)
					.load(function(){
						dimencionaWin();
					})
					.resize(function(){
						dimencionaWin();
					});
			});

		function dimencionaWin(){
			var hWindow = $(window).height();
			var wWindow = $(window).width();

			var hMedio =hWindow*0.35;
			var hMedioo =hWindow*0.6;
			$("div#divCalendario").css({top:"0", left:(wWindow-250)+ "px"});
			$("div#derecho2sup" ).css({display:"",height:hMedio,width:wWindow,top:"79",      left:"0"});
			$("div#central").css({display:"",top:"46%"});
			$("div#derecho2infe").css({display:"",height:hMedioo-80,width:wWindow,top:"47%",left:"0"});
			mueveDivCentral();
	  	}

	  	function mueveDivCentral(){
	  		var heightDer2 =  $("div#derecho2sup").get(0).offsetHeight;
			var topDer2 = $("div#derecho2sup").get(0).offsetTop;
	  	}

		function trabajoGaveta(opcion){
	  	    var hWindow = $(window).height();
			var wWindow = $(window).width();

			var hMedio =hWindow*0.35;
			var hMedioo =hWindow*0.6;
	  	    if (opcion==1){
	  			$("div#derecho2sup").css({display:"none"});
	  			$("div#central").css({display:"",top:"79"});
	  			$("div#derecho2infe").css({display:"",top:"82",height:hWindow-80});
	  		}else if(opcion==2){

	  			$("div#derecho2sup").css({display:"",height:hMedio,width:wWindow,top:"79",      left:"0"});
	  			$("div#derecho2infe").css({display:"",height:hMedioo-80,width:wWindow,top:"47%",left:"0"});
	  			$("div#central").css({display:"",top:"46%"});

				mueveDivCentral();
	  		}else if(opcion==3){
	  			$("div#derecho2sup").css({display:"",top:"79",height:hWindow});
	  			$("div#central").css({display:"",top:hWindow-10});
	  			$("div#derecho2infe").css({display:"none"});
	  		}
	  	}

	</script>
  </head>

<body >
	<div id="loginhead" style="height: 80px; width: 100%">
		<div id="logotipos" style="height: 80px;">
			<img src="../imagenes/BBVA_TAGLINE.png" class="img1" alt="Monitor Batch" height="50" width="200"/>

		</div>
	</div>
	<div id="derecho2sup" align="center">
		<iframe id="framePrincipal" src="SearchAudios.jsp?bucket=<%=aplicacion%>" name="framePrincipal" marginwidth="0" marginheight="0" frameborder="0" height="99%" width="100%" ></iframe>
    </div>
    <div id="central" align="center" >
    	<img src="../imagenes/barraSeparadora.png" align="left" width="88" height="5" border="0" usemap="#Map" />
    </div>
	<div id="derecho2infe" align="center" >
		<iframe id="frameResultados" src="" name="frameResultados" marginwidth="0" marginheight="0" frameborder="0" height="100%" width="100%" ></iframe>
    </div>
</body>
</html>

