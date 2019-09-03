package com.bbva.servlet;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class DescargaBatch extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		BufferedWriter bw = null;
		JSONObject json = null;
		StringBuffer xml = new StringBuffer();
		String pathTemporal = getServletContext().getRealPath("/") + File.separator + "upload" + File.separator;
		String cds_aplicacion = request.getParameter("cds");
		String nbs_aplicacion = request.getParameter("nbs");
		
		String[] cds = cds_aplicacion.split("\\|");
		String[] nbs = nbs_aplicacion.split("\\|");
		
		File file = new File("C:/Users/GESFOR-676/Documents/Verint Archivos/monitor_audios_Batch.txt");
		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for (int i = 0; i < cds.length; i++) {
				json = new JSONObject();
				json.put("cd_aplicacion", cds[i].toString());
				json.put("nb_aplicacion", nbs[i].toString());
				bw.write(json.toString());
				bw.newLine();
			}
			bw.close();
			doDownload(response, file.getAbsolutePath(), file.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(file.exists()){
				file.delete();
			}
			
			if(file.exists()){
				file.deleteOnExit();
			}
		}
	}
	
	private void doDownload(HttpServletResponse resp, String path_filename, String original_filename) throws IOException {
		int length = 0;
		File f = new File(path_filename);
		ServletOutputStream out = resp.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
		String mimetype = context.getMimeType(original_filename);
		resp.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		resp.setContentLength((int) f.length());

		resp.addHeader("Content-Disposition", "attachment; filename=\"" + original_filename + "\";");

		byte[] bbuf = new byte[4 * 1024]; // 5K buffer
		DataInputStream in = new DataInputStream(new FileInputStream(f));

		while ((in != null) && ((length = in.read(bbuf)) != -1)) {
			out.write(bbuf, 0, length);
		}
		in.close();
		out.flush();
		out.close();
	}
}
