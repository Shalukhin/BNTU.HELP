package controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import factory.ServiceFactory;
import property.LanguageManager;
import service.UserService;

public class AJAXServlet extends HttpServlet {

	private static final long serialVersionUID = -7219222900378068646L;	
	
	private static final Logger LOGGER = LogManager.getLogger(AJAXServlet.class.getName());
	
	private static final String LOGIN = "login";
	
	private UserService userService = ServiceFactory.getInstance().getUserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		try (PrintWriter out = response.getWriter()) {
			JSONObject jsonEnt = new JSONObject();			
			
			if (userService.isExistUserLogin(request.getParameter(LOGIN))) {
				jsonEnt.put(AJAX_BACKGROUND_COLOR, AJAX_COLOR_RED);
				jsonEnt.put(AJAX_LOGIN_INFO, LanguageManager.INSTANCE.getString(AJAX_LOGIN_BUSY));
			} else {
				jsonEnt.put(AJAX_BACKGROUND_COLOR, AJAX_COLOR_GREEN);
				jsonEnt.put(AJAX_LOGIN_INFO, LanguageManager.INSTANCE.getString(AJAX_LOGIN_FREE));
			}
			
			out.print(jsonEnt.toString());
		} catch (Exception e) {
			LOGGER.error("Error AJAX", e);
		}
	}
}