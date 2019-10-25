/*
 * Thickbox 3.1 - One Box To Rule Them All.
 * By Cody Lindley (http://www.codylindley.com)
 * Copyright (c) 2007 cody lindley
 * Licensed under the MIT License: http://www.opensource.org/licenses/mit-license.php
 * Adaptaciones por Ariel Oliva Ramirez
*/
//	$(document).ready(function(){   //mueve en fondo deshabilitado y el frame cuando se desplaza el scroll AOR 
//		$(window).scroll(function () {
//			var sInf = $("body");
//			$("div#divEspera").css({left:"+"+sInf.scrollLeft()+"px",display:"inline"});
//		});	
//	});	   
	  
function esperar(imgLoad,h,w,r){
		imgLoader = new Image();
		imgLoader.src = imgLoad;
		
		if(h==-1){h = $(window).height()*.8;}
		if(w==-1){w = $(window).width()*.8;	}	
	
		if (document.getElementById("frmEspera") === null){
			$("body").append("<iframe id='frmEspera'></iframe><div id='divEspera'></div><div id='divImgLoad'><img src='"+imgLoader.src+"' /></div><div id='divContenido'></div>");
			$("body").css("cursor","wait");
			
			var sInf = $("body");
			$("div#divEspera").css({left:"+"+sInf.scrollLeft()+"px",display:"inline"}); //AOR se mueve con el scroll
			
			$('#divImgLoad').show();//show loader
						
			$("#divImgLoad").unbind();
			
			TB_WIDTH = (w*1) + 30 || 630; //defaults to 630 if no paramaters were added to URL
			TB_HEIGHT = (h*1) + 40 || 440; //defaults to 440 if no paramaters were added to URL
			var ajaxContentW = TB_WIDTH - 30;
			var ajaxContentH = TB_HEIGHT - 45;

			
		if (document.getElementById("frmContenido") === null){			
			$("#divContenido").append("<iframe frameborder='0' hspace='0' src='' id='frmContenido' name='frmContenido' onload='tb_showIframe()' style='width:"+(ajaxContentW + 29)+"px;height:"+(ajaxContentH + 17)+"px;'> </iframe>");
			document.getElementById("frmAplicacion").action =r;
	   	  	document.getElementById("frmAplicacion").target ="frmContenido";
	   	  	document.getElementById("frmAplicacion").submit(); 
		}	
			
		document.onkeyup = function(e){ 	
			if (e == null) { keycode = event.keyCode; // ie
			} else { keycode = e.which; } // mozilla			
			if(keycode == 27){ 	tb_remove();
		}};
		}
	
		if(tb_detectMacXFF()){
			$("#divEspera").addClass("divEsperaMac");//use png overlay so hide flash
		}else{
			$("#divEspera").addClass("divEsperaBG");//use background and opacity
		}	
	
	}
//************Esperar para Carpeta****************
function esperarCarp(imgLoad,h,w,r){
	imgLoader = new Image();
	imgLoader.src = imgLoad;
	
	if(h==-1){h = $(window).height()*.8;}
	if(w==-1){w = $(window).width()*.8;	}	

	if (document.getElementById("frmEspera") === null){
		$("body").append("<iframe id='frmEspera'></iframe><div id='divEspera'></div><div id='divImgLoad'><img src='"+imgLoader.src+"' /></div><div id='divContenido'></div>");
		$("body").css("cursor","wait");
		
		var sInf = $("body");
		$("div#divEspera").css({left:"+"+sInf.scrollLeft()+"px",display:"inline"}); //AOR se mueve con el scroll
		
		$('#divImgLoad').show();//show loader
					
		$("#divImgLoad").unbind();
		
		TB_WIDTH = (w*1) + 30 || 630; //defaults to 630 if no paramaters were added to URL
		TB_HEIGHT = (h*1) + 40 || 440; //defaults to 440 if no paramaters were added to URL
		var ajaxContentW = TB_WIDTH - 30;
		var ajaxContentH = TB_HEIGHT - 45;

		
	if (document.getElementById("frmContenido") === null){			
		$("#divContenido").append("<iframe frameborder='0' hspace='0' src='' id='frmContenido' name='frmContenido' onload='tb_showIframe()' style='width:"+(ajaxContentW + 29)+"px;height:"+(ajaxContentH + 17)+"px;'> </iframe>");
		document.getElementById("frmContenido").src =r;
	}	
		
	document.onkeyup = function(e){ 	
		if (e == null) { keycode = event.keyCode; // ie
		} else { keycode = e.which; } // mozilla			
		if(keycode == 27){ 	tb_remove();
	}};
	}

	if(tb_detectMacXFF()){
		$("#divEspera").addClass("divEsperaMac");//use png overlay so hide flash
	}else{
		$("#divEspera").addClass("divEsperaBG");//use background and opacity
	}	

}

//************Esperar mientra se procesa sin que se nuestren ventanas****************
function esperarOculto(imgLoad,r){
	imgLoader = new Image();
	imgLoader.src = imgLoad;
	h = 50;
	w = 50;
	
	if(h==-1){h = $(window).height()*.8;}
	if(w==-1){w = $(window).width()*.8;	}	

	if (document.getElementById("frmEspera") === null){
		$("body").append("<iframe id='frmEspera'></iframe><div id='divEspera'></div><div id='divImgLoad'><img src='"+imgLoader.src+"' /></div><div id='divContenido'></div>");
		$("body").css("cursor","wait");
		
		var sInf = $("body");
		$("div#divEspera").css({left:"+"+sInf.scrollLeft()+"px",display:"inline"}); //AOR se mueve con el scroll
		
		$('#divImgLoad').show();//show loader
					
		$("#divImgLoad").unbind();
		
		TB_WIDTH = (w*1) + 30 || 630; //defaults to 630 if no paramaters were added to URL
		TB_HEIGHT = (h*1) + 40 || 440; //defaults to 440 if no paramaters were added to URL
		var ajaxContentW = TB_WIDTH - 30;
		var ajaxContentH = TB_HEIGHT - 45;

		
	if (document.getElementById("frmContenido") === null){			
		$("#divContenido").append("<iframe frameborder='0' hspace='0' src='' id='frmContenido' name='frmContenido' onload='tb_showIframe()' style='width:"+(ajaxContentW + 29)+"px;height:"+(ajaxContentH + 17)+"px;'> </iframe>");
		document.getElementById("frmContenido").src =r;
	}	
		
	document.onkeyup = function(e){ 	
		if (e == null) { keycode = event.keyCode; // ie
		} else { keycode = e.which; } // mozilla			
		if(keycode == 27){ 	tb_remove();
	}};
	}

	if(tb_detectMacXFF()){
		$("#divEspera").addClass("divEsperaMac");//use png overlay so hide flash
	}else{
		$("#divEspera").addClass("divEsperaBG");//use background and opacity
	}	

}


function soloesperar(imgLoad){
	imgLoader = new Image();
	imgLoader.src = imgLoad;
	
	if (document.getElementById("frmEspera") === null){
		$("body").append("<iframe id='frmEspera'></iframe><div id='divEspera'></div><div id='divImgLoad'><img src='"+imgLoader.src+"' /></div>");
		$("body").css("cursor","wait");
		
		var sInf = $("body");
		$("div#divEspera").css({left:"+"+sInf.scrollLeft()+"px",display:"inline"}); //AOR se mueve con el scroll
		$('#divImgLoad').show();//show loader				
	}
	
	if(tb_detectMacXFF()){
		$("#divEspera").addClass("divEsperaMac");//use png overlay so hide flash
	}else{
		$("#divEspera").addClass("divEsperaBG");//use background and opacity
	}	

}

function soloesperarGeneral(){
//	alert("Entrando a soloEsperar");
	imgLoader = new Image();
	imgLoader.src = "../Imagenes/loading.gif";
	
	if (document.getElementById("frmEspera2") === null){
		$("body").append("<div id=\"divImgLoad2\" style=\" width: 100%; height: 100%; background: #FFF; position: absolute; z-index: 2; font-size: 9px\"><div style=\"width: 100%; position: absolute; top: 30%;  text-align: center;\"><img src = '../Images/loading.gif'><br>Cargando</div></div>");
//		$("body").append("<img style='position: absolute;' src = '../Images/loading.gif' />");
		$("body").css("cursor","wait");
		
		var sInf = $("body");
		$("div#divEspera2").css({left:"+"+sInf.scrollLeft()+"px",display:"inline"}); //AOR se mueve con el scroll
		$('#divImgLoad2').show();//show loader				
	}
	
	if(tb_detectMacXFF()){
		$("#divEspera2").addClass("divEsperaMac");//use png overlay so hide flash
	}else{
		$("#divEspera2").addClass("divEsperaBG");//use background and opacity
	}
}

function soloesperarDocumentos(){
	imgLoader = new Image();
	imgLoader.src = "../Imagenes/loading.gif";
	if (document.getElementById("frmEspera2") === null){
		
		$("body").append("<div id=\"inferior\" style=\" width: 100%; height: 100%; background: #FFF; position: absolute; z-index: 2; font-size: 9px\"><div style=\"width: 100%; position: absolute; top: 30%;  text-align: center;\"><img src = '../Images/loading.gif'><br>Cargando</div></div>");
//		$("body").append("<img style='position: absolute;' src = '../Images/loading.gif' />");
		$("body").css("cursor","wait");
		
		var sInf = $("body");
		$("div#divEspera2").css({left:"+"+sInf.scrollLeft()+"px",display:"inline"}); //AOR se mueve con el scroll
		$('#inferior').show();//show loader				
	}
	
	if(tb_detectMacXFF()){
		$("#divEspera2").addClass("divEsperaMac");//use png overlay so hide flash
	}else{
		$("#divEspera2").addClass("divEsperaBG");//use background and opacity
	}
}


function remove_soloesperarGeneral() {
		$("body").css("cursor","default");
		$('#divEspera2,#frmEspera2').trigger("unload").unbind().remove();
		$("#divImgLoad2").remove();
		document.onkeydown = "";
		document.onkeyup = "";
		return false;
	}

function remove_soloesperarDocumentos() {
		$("body").css("cursor","default");
		$('#divEspera2,#frmEspera2').trigger("unload").unbind().remove();
		$("#inferior").remove();
		document.onkeydown = "";
		document.onkeyup = "";
		return false;
	}




	function tb_detectMacXFF() {
		var userAgent = navigator.userAgent.toLowerCase();
		if (userAgent.indexOf('mac') != -1 && userAgent.indexOf('firefox')!=-1) {
			return true;
		}
		return false;
	}
	
	document.onkeyup = function(e){ 	
		if (e == null) { keycode = event.keyCode; // ie
		} else { keycode = e.which; } // mozilla			
		if(keycode == 27){ 	tb_remove();} // close	
	};
	
	function tb_showIframe(){
		tb_position();
		$("#divImgLoad").remove();
		$("#divContenido").css({display:"block"});
	}
	
	function tb_position() {
		$("#divContenido").css({marginLeft: '-' + parseInt((TB_WIDTH / 2),10) + 'px', width: TB_WIDTH + 'px'});
		if ( !(jQuery.browser.msie && jQuery.browser.version < 7)) { // take away IE6
			$("#divContenido").css({marginTop: '-' + parseInt((TB_HEIGHT / 2),10) + 'px'});
		}
	}
	
	function tb_remove() {
		$("body").css("cursor","default");
		$('#frmContenido,#divContenido,#divEspera,#frmEspera').trigger("unload").unbind().remove();
		$("#divImgLoad").remove();
		document.onkeydown = "";
		document.onkeyup = "";
		return false;
	}
	
	function remove_soloesperar() {
		$("body").css("cursor","default");
		$('#divEspera,#frmEspera').trigger("unload").unbind().remove();
		$("#divImgLoad").remove();
		document.onkeydown = "";
		document.onkeyup = "";
		return false;
	}
	
	function tb_remove2() {
		$("body").css("cursor","default");
		$('#frmContenido,#divContenido,#divEspera,#frmEspera').trigger("unload").unbind().remove();
		$("#divImgLoad").remove();
	}