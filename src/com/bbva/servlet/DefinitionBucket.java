package com.bbva.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbva.manager.ConsultaManager;


/**
 * Servlet implementation class DefinitionBucket
 */
public class DefinitionBucket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DefinitionBucket() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String aplicacion = request.getParameter("aplicacion");
		if (aplicacion == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Sin aplicacion seleccionada");
			return;
		}
		//Aqui deberia ir la consulta a bd
		ConsultaManager cm = new ConsultaManager();
		String[][] data = cm.getDefinicionBucket(aplicacion);
//		for (int i = 0; i < data.length; i++) {
//			System.out.println(data[i][0]);
//			System.out.println(data[i][1]);
//			System.out.println(data[i][2]);
//		}
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>My JSP 'ConsultaAudios.jsp' starting page</title>");
		out.println("<meta http-equiv=\"pragma\" content=\"no-cache\">");
		out.println("<meta http-equiv=\"cache-control\" content=\"no-cache\">");
		out.println("<meta http-equiv=\"expires\" content=\"0\">");
		out.println("<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">");
		out.println("<meta http-equiv=\"description\" content=\"This is my page\">");
		
		out.println("<style type=\"text/css\">");
		out.println("body{background: rgba(226,226,226,1);}");
		out.println(".button {padding-left: 90px;}");
		out.println("button {margin-left: .5em;}");
		out.println("input:focus, textarea:focus {border-color: #000;}");
		out.println("input, textarea {font: 1em sans-serif;width: 300px; -moz-box-sizing: border-box;box-sizing: border-box;border: 2px solid #999;}");
		out.println("label {display: inline-block;width: 90px;text-align: right;}");
		out.println("form div + div {margin-top: 1em;}");
		out.println("form {margin: 0 auto;width: 400px;padding: 1em;border: 3px solid #CCC;border-radius: 1em;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<script type=\"text/javascript\">");
		out.println("function Consulta(){");
		out.println("var selectbucket = document.getElementById(\"selectbucket\").value;");
		out.println("if(selectbucket != -1){");
		out.println("var res = \"\";");
		out.println("var parametros = document.getElementById(\"params\").value;");
		for (int i = 0; i < data.length; i++) {
			out.println("var elem" + i + "= document.getElementById(\""+ data[i][0] +"\").value;");
			out.println("res += \""+data[i][0]+"=\"+ elem"+i+"+\"&\";");
		}
		out.println("document.forms['form1'].action = \"./jsp/ConsultaAudios.jsp?\"+res+ \"&bucket=\" + selectbucket;");
		out.println("document.forms['form1'].submit();");
		out.println("}else{");
		out.println("alert(\"Selecciona un bucket\");");
		out.println("}");
		out.println("}");
		out.println("</script>");
		out.println("<body>");
		out.println("<h1>Realizar busqueda por:</h1>");
		out.println("<form id=\"form1\" action=\"\" method=\"post\">");
		out.println("<select id=\"selectbucket\">");
		out.println("<option value=\"-1\">Selecciona un bucket..</option>");
		out.println("<option value=\"expunic\">expunic</option>");
		out.println("<option value=\"verint\">verint</option>");
		out.println("<option value=\"bucketfroy\">bucketfroy</option>");
		out.println("</select>");
		
		for (int i = 0; i < data.length; i++) {
			out.println("<div>");
			out.println("<label for=\"name\">"+ data[i][1] +"</label>");
			out.println("<input type=\"text\" id=\"" + data[i][0] + "\" name=\"" + data[i][0] + "\" />");
			out.println("</div>");
		}
		
//		out.println("<div>");
//		out.println("<label for=\"name\">Numero Cliente:</label>");
//		out.println("<input type=\"text\" id=\"numeroCliente\" name=\"numcliente\" />");
//		out.println("</div>");
//		out.println("<div>");
//		out.println("<label for=\"mail\">Numero de Contrato:</label>");
//		out.println("<input type=\"text\" id=\"numeroContrato\" name=\"numcontrato\" />");
//		out.println("</div>");
//		out.println("<div>");
//		out.println("<label for=\"msg\">Fecha:</label>");
//		out.println("<input type=\"text\" id=\"fecha\" name=\"fecha\" />");
//		out.println("</div>");
		
		
		out.println("<div class=\"button\">");
		out.println("<input type=\"hidden\" value=\"\" id=\"params\">");
		out.println("<button type=\"submit\" onclick= \"Consulta();\">Consultar</button>");
		out.println("</div>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
