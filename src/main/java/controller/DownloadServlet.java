package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.constant.PageURLConstant;
import static command.constant.ParameterNameConstant.*;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import entity.FinishFile;
import factory.ServiceFactory;
import property.LanguageManager;
import service.FinishFileService;
import util.Parser;

public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 4095481178103326188L;

	private FinishFileService finishFileService = ServiceFactory.getInstance().getFinishFileService();

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

		InputStream in = null;
		String fileName = null;

		String source = Parser.getStringParameterByName(request, DOWNLOAD_SOURCE);
		if (source == null) {
			source = "";
		}
		
		try {
			switch (source) {
			case DATA_BASE:
				int id = Integer.valueOf(Parser.getStringParameterByName(request, DOWNLOAD_FINISH_FILE_ID));				
				FinishFile finishFile = finishFileService.takeFinishFileById(id);
				fileName = finishFile.getNameFinishFile();
				in = finishFile.getDataFinishFile();
				break;				
			default:
				fileName = request.getParameter("fileName");
				File my_file = new File(DownloadServlet.class.getClassLoader().getResource(fileName).getFile());
				in = new FileInputStream(my_file);
			}
			
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", String.format("attachment; filename=%s", fileName));
			
			OutputStream out = response.getOutputStream();

			byte[] buffer = new byte[4096];
			int length;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			
			in.close();
			out.flush();
			
		} catch (Exception e) {
			request.setAttribute(ERROR, LanguageManager.INSTANCE.getString(ERROR_FILE_NOT_FOUND));
			request.getRequestDispatcher(PageURLConstant.ERROR_PAGE_URL).forward(request, response);
		}		
	}
}
