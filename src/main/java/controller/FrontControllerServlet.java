package controller;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ParameterNameConstant.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandGETProvider;
import command.CommandPOSTProvider;
import command.PageManager;
import command.URLManager;
import command.constant.QueryURLConstant;
import exception.PoolException;
import exception.ServiceException;
import factory.ServiceFactory;
import pool.PoolConnection;
import property.LanguageManager;
import service.UserService;
import util.Parser;

public class FrontControllerServlet extends HttpServlet{

	private static final long serialVersionUID = -5618225533764372191L;
	
	private static final Logger LOGGER = LogManager.getLogger(FrontControllerServlet.class.getName());
	
	private static final String COOKIE_LOGIN = "Login";
	private static final String COOKIE_PASSWORD = "Password";
	
	private CommandGETProvider commandGETProvider = new CommandGETProvider();
	private CommandPOSTProvider commandPOSTProvider = new CommandPOSTProvider();
	private UserService userService = ServiceFactory.getInstance().getUserService();
	
	@Override
	public void init() throws ServletException {
		
		try {
			PoolConnection.INSANCE.initialization();
		} catch (PoolException e) {
			LOGGER.error("Error init pool", e);
		}
		
		getServletContext().setAttribute(LANGUAGE_MANAGER, LanguageManager.INSTANCE);
		
		super.init();
	}	

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("tempdir", getServletContext().getAttribute("javax.servlet.context.tempdir"));		
			
		if (request.getSession().getAttribute(USER) == null) {
			autoSignIn(request);
		}
		
		super.service(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		QueryURLConstant.CHANGE_LANGUAGE_QUERY_URL.setUrl(request.getRequestURI() + "?" + request.getQueryString());		
		
		String command = Parser.getStringParameterByName(request, COMMAND_PARAMETER);
		PageManager page = commandGETProvider.getCommand(command).execute(request);
		
		try {			
			request.getRequestDispatcher(page.getUrl()).forward(request, response);
		} catch (Exception e) {
			LOGGER.error("Error request", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = Parser.getStringParameterByName(request, COMMAND_PARAMETER);		
		URLManager url = commandPOSTProvider.getCommand(command).execute(request, response);
		
		response.sendRedirect(url.getUrl());		
	}
	
	private void autoSignIn(HttpServletRequest request) {
		
		String loginFromCookie = null;
		String passwordFromCookie = null;
		
		Cookie[] cookies = request.getCookies();		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(COOKIE_LOGIN)) {				
					loginFromCookie = cookie.getValue();
				}
				if (cookie.getName().equals(COOKIE_PASSWORD)) {
					passwordFromCookie = cookie.getValue();
				}
			}
		}
		
		try {
			request.getSession().setAttribute(USER, userService.loginUser(loginFromCookie, passwordFromCookie));
		} catch (ServiceException e) {
			LOGGER.error("Error auto sign in", e);
		}
		
	}
	
}
