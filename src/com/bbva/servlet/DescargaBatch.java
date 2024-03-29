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

import org.json.JSONArray;

public class DescargaBatch extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		BufferedWriter bw = null;
		File file = null;
		String pathTemporal = getServletContext().getRealPath("/") + File.separator + "upload" + File.separator;
		
		String param = request.getParameter("param");
		String checking = request.getParameter("checking");
		
		JSONArray array = new JSONArray(param);
		String[] checks = checking.split("\\|");
		
		try {
			file = new File(pathTemporal + "monitor_audios_Batch.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for (int i = 0; i < checks.length; i++) {
				bw.write(array.getJSONObject(i).toString());
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
