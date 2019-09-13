package com.bbva.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bbva.manager.ConsultaManager;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ConsultaAudios extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public ConsultaAudios() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
		
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
		String endPointArchiving = "http://150.100.22.50:9090/v3/_search/" + bucket + "/" + bucket;
		Client client = Client.create();
		WebResource webResource = client.resource(endPointArchiving);
		System.out.println("Consultaremos:  " + search);
		ClientResponse clien = webResource.type("application/json").post(ClientResponse.class, search.toString());
		if (clien.getStatus() != 202) {
			throw new RuntimeException("Failed : HTTP error code : " + clien.getStatus());
		}

		String output = clien.getEntity(String.class);

		JSONObject json = new JSONObject(output);
		JSONArray geodata = json.getJSONArray("result");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<base href=\"<%=basePath%>\">");
		out.println("<title>My JSP 'ConsultaAudios.jsp' starting page</title>");
		out.println("<meta http-equiv=\"pragma\" content=\"no-cache\">");
		out.println("<meta http-equiv=\"cache-control\" content=\"no-cache\">");
		out.println("<meta http-equiv=\"expires\" content=\"0\">");
		out.println("<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">");
		out.println("<meta http-equiv=\"description\" content=\"This is my page\">");
		out.println("<style type=\"text/css\">");
		out.println("<style type=\"text/css\">");
		out.println("th, td {color: black; width: 25%;}");
		out.println("tr.normalRow td {background: #FFF;}");
		out.println("tr.alternateRow td {background: #EEE;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<script type=\"text/javascript\">");
		
		out.println("{");
		out.println("function Generar()");
		out.println("var cds = \"\";");
		out.println("var nbs = \"\";");
		out.println("var nombre = \"\";");
		out.println("var total = document.getElementById(\"totalArch\").value;");
				
		out.println("for(i = 0; i < total; i++){");
		out.println("if(document.getElementById(\"check\" + i).checked){");
		out.println("cds  = cds + document.getElementById(\"cdAplicacion\" + i).value + \"|\";");
		out.println("nbs  = nbs + document.getElementById(\"nbAplicacion\" + i).value + \"|\";");
		out.println("}");
		out.println("}");
				
		out.println("if(cds != \"\" && nbs != \"\"){");
		out.println("location.href = \"./DescargaBatch?cds=\" + cds + \"&nbs=\" + nbs;");
		out.println("}");
		out.println("}");
		out.println("function marcar(sourse) {");
		out.println("checkboxes = document.getElementsByTagName('input');");
		out.println("for (i = 0; i < checkboxes.length; i++){");
		out.println("if (checkboxes[i].type == \"checkbox\")");
		out.println("{");
		out.println("checkboxes[i].checked = sourse.checked;");
		out.println("}");
		out.println("}");
		out.println("}");
		out.println("</script>");
		
		out.println("<body>");
		out.println("<div style=\"color: black;\">");
		out.println("<h1>Selecciona los audios:</h1>");
		out.println("<form id=\"form1\" action=\"\" method=\"POST\">");
		out.println("<table border=\"1\">");
		out.println("<tr>");
		out.println("<th><strong></strong></th>");
		out.println("<th><strong><%=data[1][1] %></strong></th>");
		out.println("<th><strong><%=data[2][1] %></strong></th>");
		out.println("</tr>");
		
		int row = 0;
		for (int i = 0; i < geodata.length(); i++) {
		JSONObject obj = (JSONObject) geodata.getJSONObject(i);
		
		out.println("<tr class=\"<%=((i % 2) == 0 ? \"alternateRow\" : \"normalRow\")%>\">");
		out.println("<td><strong><input type=\"checkbox\" id=\"check<%=row%>\" name=\"check\" /></strong></td>");
		out.println("<td>");
		out.println("<strong><%=obj.getString(\"nc\")%></strong>");
		out.println("<input type=\"hidden\" id=\"cdAplicacion<%=row%>\" value=\"<%=obj.getString(\"nc\")%>\" />");
		out.println("</td>");
		out.println("<td>");
		out.println("<strong><%=obj.getString(\"ct\")%></strong>");
		out.println("<input type=\"hidden\" id=\"nbAplicacion<%=row%>\" value=\"<%=obj.getString(\"ct\")%>\" />");
		out.println("</td>");
		out.println("</tr>");
		
		row++;
		}
		
		out.println("</table>");
		out.println("</form>");
		out.println("</div>");
		out.println("<input type=\"hidden\" value=\"<%=row%>\" id=\"totalArch\">");
		out.println("<input type=\"checkbox\" value=\"Marcar\" onclick=\"marcar(this);\">Marcar Todos</input>");
		out.println("<input type=\"button\" value=\"Generar Batch\" onclick=\"Generar();\"></input>");
		out.println("</body>");
		out.println("</html>");
		
		
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}


}
