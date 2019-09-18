package com.bbva.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bbva.manager.ConsultaManager;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Servlet implementation class ResultDocuments
 */
public class ResultDocuments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultDocuments() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			response.setContentType("text/html; charset=ISO-8859-1");

			HttpSession session = request.getSession(false);
			if (session == null) {
				out.println("<codigo>-1</codigo>");
				out.flush();
				return;
			}
			
			String tituloAplicacion = null;
	
			

			tituloAplicacion = request.getParameter("t");
			System.out.println("tituloapp: " + tituloAplicacion);
			String nc = request.getParameter("nc");
			System.out.println("numeroCliente: " + nc);
			String ct = request.getParameter("ct");
			System.out.println("numeroContrato: " + ct);
			
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
//		 	String input = "{\"must\":{\"t\": \"EXPUNICO\", \"nc\": \"D0176518\", \"ct\": \"007453460000000040\"}}";

			//EJEMPLO DE ENDPOINT http://150.100.22.50:9090/v3/_search/expunic/expunic
			//EJEMPLO DE ENDPOINT http://150.100.22.50:9090/v3/_search/verint/verint
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

			out.println("<codigo>" + geodata.length() + "</codigo>)");

			out.println("<table id=\"tablaDoc\"  width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"tablaDatos\"");
			out.println("<thead");
			
//			if("expunico".equalsIgnoreCase(tituloAplicacion)) {
			out.println("	<tr id=\"encabezado\" height=\"35px\">");
			out.println("		<th id=\"tit0\" width=\"15%\">");
			out.println(""+data[1][1]+"");
			out.println("		</th>");
			out.println("		<th id=\"tit1\" width=\"25%\">");
			out.println(""+data[1][1]+"");
			out.println("		<th id=\"tit2\" width=\"60%\">");
			out.println("			Nombre de Cliente");
			out.println("		</th>");
			out.println("	</tr>");
//			}
			out.println("</thead>");
			out.println("<tbody>");

			int row = 0;
			for (int i = 0; i < geodata.length(); i++) {
				JSONObject obj = (JSONObject) geodata.getJSONObject(i);
				
				out.println("<tr class=\""+((i % 2) == 0 ? "alternateRow" : "normalRow")+"\">");
				out.println("<td align=\"center\" style=\"font-weight:bold;\" width=\"15%\">");
				out.println("<strong><input type=\"checkbox\" id=\"check"+row+"\" name=\"check\"/></strong>");
				out.println("</td>");
				
				out.println("<td>");
				out.println("<strong>"+obj.getString("nc")+"</strong>");
				out.println("<input type\"hidden\" id=\"cdAplicacion"+row+"\" value=\""+obj.getString("nc")+"\"/>");
				out.println("</td>");
				
				out.println("<td>");
				out.println("<strong>"+obj.getString("ct")+"</strong>");
				out.println("<input type\"hidden\" id=\"nbAplicacion"+row+"\" value=\""+obj.getString("ct")+"\"/>");
				out.println("</td>");
				
				out.println("</tr>");
				
//					out.println("<tr>");
//					out.println("<td align=\"center\" style=\"font-weight:bold;\" width=\"15%\">");
//					out.println("<a href=\"../HistoricoTree?Gabeton=" + hb.getIdGabinete() + "&Aplicacion=" + hb.getIdGaveta() + "&numcliente=" + hb.getNumCliente() + "&nomcliente=" + hb.getNomCliente() + "\" target=\"_top\">" + contador + "</a>");
//					out.println("</td>");
//					out.println("<td align=\"center\" width=\"25%\">");
//					out.println("<a href=\"../HistoricoTree?Gabeton=" + hb.getIdGabinete() + "&Aplicacion=" + hb.getIdGaveta() + "&numcliente=" + hb.getNumCliente() + "&nomcliente=" + hb.getNomCliente() + "\" target=\"_top\">" + hb.getNumCliente() + "</a>");
//					out.println("</td>");
//					out.println("<td align=\"left\" width=\"60%\">");
//					out.println("<a href=\"../HistoricoTree?Gabeton=" + hb.getIdGabinete() + "&Aplicacion=" + hb.getIdGaveta() + "&numcliente=" + hb.getNumCliente() + "&nomcliente=" + hb.getNomCliente() + "\" target=\"_top\">" + hb.getNomCliente() + "</a>");
//					out.println("</td>");
//					out.println("</tr>");
				
				
//				<tr class="<%=((i % 2) == 0 ? "alternateRow" : "normalRow")%>">
//				<td><strong><input type="checkbox" id="check<%=row%>" name="check" /></strong></td>
//				<td>
//					<strong><%=obj.getString("nc")%></strong>
//					<input type="hidden" id="cdAplicacion<%=row%>" value="<%=obj.getString("nc")%>" />			
//				</td>
//				<td>
//					<strong><%=obj.getString("ct")%></strong>
//					<input type="hidden" id="nbAplicacion<%=row%>" value="<%=obj.getString("ct")%>" />
//				</td>
//				</tr>
			}

			out.println("</tbody>");
			out.println("</table>");

		} finally {
			if (out != null)
				out.close();
		}
	}

}
