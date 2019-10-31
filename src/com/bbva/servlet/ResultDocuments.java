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
			
			String bucket = request.getParameter("bucket");
			System.out.println("bucket: " + bucket);
			String nc = request.getParameter("nc");
			System.out.println("numeroCliente: " + nc);
			String ct = request.getParameter("ct");
			System.out.println("numeroContrato: " + ct);
			
			ConsultaManager cm = new ConsultaManager();
			String[][] data = cm.getDefinicionBucket(bucket);
			JSONObject search = new JSONObject();
			JSONObject filtros = new JSONObject();
			for (int i = 0; i < data.length; i++) {
				if(!"".equalsIgnoreCase(request.getParameter(data[i][0])))
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
			
			float percent  = (100 / (data.length+1));

			out.println("<table id=\"tablaDoc\"  width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"tablaDatos\"");
			out.println("<thead");
			out.println("	<tr id=\"encabezado\" height=\"35px\">");
<<<<<<< HEAD
			out.println("		<th id=\"tit0\" width=\""+percent+"%\">Registro</th>");
			int h = 1;
			for (int k = 0; k < data.length; k++) {
				out.println("		<th id=\"tit"+h+"\" width=\""+percent+"%\">"+data[k][1]+"</th>");
				h++;
			}
=======
			
			out.println("		<th id=\"tit0\" width=\"15%\">");
			out.println(""+data[1][1]+"");
			out.println("		</th>");
			out.println("		<th id=\"tit1\" width=\"25%\">");
			out.println(""+data[1][1]+"");
			out.println("		<th id=\"tit2\" width=\"25%\">");
			out.println(""+data[1][1]+"");
			out.println("		</th>");
			
>>>>>>> 59283f55606744ac4bee0984c59c4b1725ba8d03
			out.println("	</tr>");
			out.println("</thead>");
			
			out.println("<tbody>");
			int row = 0;
			String antData = "";
			JSONArray arrayTest = new JSONArray();
			JSONObject jsonTest = new JSONObject();
			for (int i = 0; i < geodata.length(); i++) {
				JSONObject obj = (JSONObject) geodata.getJSONObject(i);
				System.out.println("json linea "+ obj.toString());
				
				out.println("<tr class=\""+((i % 2) == 0 ? "alternateRow" : "normalRow")+"\">");
				out.println("<td align=\"center\" style=\"font-weight:bold;\" width=\""+percent+"%\">");
				out.println("<strong><input type=\"checkbox\" id=\"check"+row+"\" name=\"check\"/></strong>");
				out.println("</td>");
				for (int k = 0; k < data.length; k++) {
					if(!"t".equalsIgnoreCase(data[k][0]) && !antData.equalsIgnoreCase(data[k][0])){
						out.println("<td width=\""+percent+"%\">");
						String val = (obj.isNull(data[k][0])==true) ? "NULL" : obj.getString(data[k][0]);
						out.println("<strong>"+val+"</strong>");
						out.println("<input type=\"hidden\" id=\""+data[k][0]+""+row+"\" value=\""+val+"\"/>");
						out.println("</td>");					
						antData = data[k][0];
						jsonTest.put(data[k][0], val);
					}
				}
				out.println("</tr>");
				row++;
				arrayTest.put(jsonTest);
			}

			out.println("</tbody>");
			out.println("</table>");
			out.println("<input type=\"hidden\" value=\""+row+"\" id=\"totalArch\">");
			out.println("<input type=\"hidden\" value="+String.valueOf(arrayTest)+" id=\"arrayValues\">");
		} finally {
			if (out != null)
				out.close();
		}
	}

}
