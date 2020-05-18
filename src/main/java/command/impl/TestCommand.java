package command.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import command.Command;
import command.PageManager;
import exception.DAOException;
import exception.PoolException;
import factory.ServiceFactory;
import pool.PoolConnection;
import service.FinishFileService;

public class TestCommand implements Command {

	private static final String SQL_REQUEST_TESTBLOB = "INSERT INTO `testblob` (`mydata`) VALUES (?);";
	private static final String SQL_REQUEST_GETBLOB = "SELECT * FROM `testblob` WHERE `id` = ?";
	
	private FinishFileService finishFileService = ServiceFactory.getInstance().getFinishFileService();

	@Override
	public PageManager execute(HttpServletRequest request) {
		System.out.println("Hi");
		
		
		Enumeration<Object> params = request.getParameterNames();
		StringBuilder sb = new StringBuilder();	
		
		while (params.hasMoreElements()) {
			
			String par = params.nextElement().toString();
			System.out.print(par);
			sb.append(par);
			System.out.print("=");
			sb.append("=");
			System.out.println(request.getParameter(par));
			sb.append(request.getParameter(par));
			sb.append("<br>");			
		}

		
		request.setAttribute("xxx", sb.toString());
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {			
			return PageManager.ERROR_PAGE;
		}		
		
		// Создаём класс фабрику 
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Максимальный буфера данных в байтах,
		// при его привышении данные начнут записываться на диск во временную директорию
		// устанавливаем один мегабайт
		 factory.setSizeThreshold(1024*1024*50);
		
		// устанавливаем временную директорию
		
		//File tempDir = new File(TestCommand.class.getClassLoader().getResource("uploads/temp").getFile());
		//File tempDir = new File(request.getAttribute("tempdir"));
		File tempDir = (File) request.getAttribute("tempdir");
		factory.setRepository(tempDir);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//максимальный размер данных который разрешено загружать в байтах
		//по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт. 
		
		upload.setSizeMax(1024*1024*50);
		
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			
			while (iter.hasNext()) {
				FileItem item = iter.next();
				System.out.println(item.isFormField());
				System.out.println("fildname = " + item.getFieldName());
				System.out.println("name = " + item.getName());
				//System.out.println(item.getString());
				System.out.println("-------");
				
				if (!item.isFormField()) {	
					System.out.println("in cikl");
					//File uploadFile = new File(request.getAttribute("uploads") + item.getName());
					//uploadFile.createNewFile();					
					//item.write(uploadFile);
					finishFileService.addNewFinishFile(item.getName(), item);
				}
			}
			
		} catch (Exception e) {			
			return PageManager.ERROR_PAGE;
		}
		
		
		return PageManager.TEST_PAGE;
	}

//	public boolean testblob(InputStream inputStream) throws DAOException {		
//		
//		int result;
//		try (Connection connection = PoolConnection.INSANCE.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_TESTBLOB);) {
//
//			preparedStatement.setBlob(1, inputStream);
//
//			result = preparedStatement.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new DAOException("Error create order - SQL error", e);
//		} catch (PoolException e) {
//			throw new DAOException("Error create uorderser - pool error", e);
//		}
//
//		return true;
//	}
//	
//	public void testGetblob(int id) throws DAOException {
//		int result;
//		try (Connection connection = PoolConnection.INSANCE.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `testblob` WHERE `id` = ?");) {
//			preparedStatement.setInt(1, id);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			
//			InputStream inputStream =  resultSet.getBinaryStream("mydata");
//			
//			
//			
//			
//		}catch (SQLException e) {
//			throw new DAOException("Error create order - SQL error", e);
//		} catch (PoolException e) {
//			throw new DAOException("Error create uorderser - pool error", e);
//		}
//	}

}
