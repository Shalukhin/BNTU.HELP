package controller;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandProvider;
import command.PageManager;
import command.constant.ParameterNameConstant;
import exception.PoolException;
import pool.PoolConnection;
import util.Parser;

public class FrontControllerServlet extends HttpServlet{

	private static final long serialVersionUID = -5618225533764372191L;
	
	private final static Logger LOGGER = LogManager.getLogger(FrontControllerServlet.class.getName());
	
	private CommandProvider commandProvider = new CommandProvider();
	
	@Override
	public void init() throws ServletException {
		
		try {
			PoolConnection.INSANCE.initialization();
		} catch (PoolException e) {
			LOGGER.error("Error init pool", e);
		}
		
		getServletContext().setAttribute(DATE_FORMAT, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_WEB_TABLE));
		
		super.init();
	}	

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("tempdir", getServletContext().getAttribute("javax.servlet.context.tempdir"));
		super.service(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		PageManager page = commandProvider.getCommand(Parser.getStringParameterByName(request, ParameterNameConstant.COMMAND)).execute(request);		
			
		try {
			request.getRequestDispatcher(page.getUrl()).forward(request, response);
		} catch (Exception e) {
			LOGGER.error("Error request", e);
		}
	}	

}
