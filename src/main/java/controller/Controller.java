package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.command.CommandName;
import dao.impl.UserDAOImpl;
import exception.ControllerException;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class Controller extends HttpServlet{

	private static final long serialVersionUID = -5618225533764372191L;
	
	private final static Logger LOGGER = LogManager.getLogger(Controller.class.getName());
	
	private CommandProvider commandProvider = new CommandProvider();
	
	@Override
	public void init() throws ServletException {
		try {
			PoolConnection.INSANCE.initialization();
		} catch (PoolException e) {
			LOGGER.error("Error init pool", e);
		}
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) {		
		
		String page = commandProvider.getCommand(req.getParameter("command")).execute(req);
				
		try {
			req.getRequestDispatcher(page).forward(req, resp);
		} catch (Exception e) {
			LOGGER.error("Error request", e);
		}
	}	

}
