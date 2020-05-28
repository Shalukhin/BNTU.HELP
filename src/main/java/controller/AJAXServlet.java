package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import factory.ServiceFactory;
import service.UserService;

public class AJAXServlet extends HttpServlet {

	private static final long serialVersionUID = -7219222900378068646L;	
	private final static Logger LOGGER = LogManager.getLogger(AJAXServlet.class.getName());
	private UserService userService = ServiceFactory.getInstance().getUserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException ex) {
			System.out.println("err");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException ex) {
			System.out.println("err");
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, JSONException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		try (PrintWriter out = response.getWriter()) {
			JSONObject jsonEnt = new JSONObject();
			System.out.println(request.toString());			
			System.out.println(request.getParameter("login"));
			
			if (userService.isExistUserLogin(request.getParameter("login"))) {
				jsonEnt.put("backgroundColor", "#CC6666");
				jsonEnt.put("serverInfo", "Логин занят!");
			} else {
				jsonEnt.put("backgroundColor", "#99CC66");
				jsonEnt.put("serverInfo", "Логин свободен!");
			}
			
			out.print(jsonEnt.toString());
		} catch (Exception e) {
			LOGGER.error("Error AJAX", e);
		}
	}
}