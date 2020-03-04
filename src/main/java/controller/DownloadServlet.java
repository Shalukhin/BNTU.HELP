package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 4095481178103326188L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fileName = request.getParameter("fileName");

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", String.format("attachment; filename=%s", fileName));

		File my_file = new File(
				DownloadServlet.class.getClassLoader().getResource(fileName).getFile());

		// отправить файл в response
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(my_file);

		byte[] buffer = new byte[4096];
		int length;

		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}

		// освободить ресурсы
		in.close();
		out.flush();
	}

}
