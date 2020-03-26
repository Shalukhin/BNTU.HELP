package controller.command.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import builder.UserBuilder;
import controller.command.Command;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.LoginService;

public class Login implements Command {

	private final static Logger LOGGER = LogManager.getLogger(Login.class.getName());

	private LoginService loginService = ServiceFactory.getInstance().getLoginService();

	@Override
	public String execute(HttpServletRequest request) {

		User userFromGUI = UserBuilder.getUser(request);

		List<User> userListFromDB = null;

		try {
			userListFromDB = loginService.takeUserFromDB(userFromGUI);
			if (userListFromDB == null) {
				setAttributeForGUI(request, "signMenuPosition", "Не верный формат ввода логина или пароля", null);				
				return "WEB-INF/view/sign.jsp";
			}
		} catch (ServiceException e) {
			LOGGER.error("Error log in", e);
			return "WEB-INF/view/error.jsp";
		}

		if (userListFromDB.size() == 0) {
			setAttributeForGUI(request, "signMenuPosition", "Не верный логин или пароль", null);
			return "WEB-INF/view/sign.jsp";
		}
		
		setAttributeForGUI(request, "accountMenuPosition", "", userListFromDB.get(0));	

		return "WEB-INF/view/account.jsp";
	}

	private void setAttributeForGUI(HttpServletRequest request, String nameMainMenu, String message, User user) {
		request.setAttribute(nameMainMenu, "active"); // for indicate main menu current item
		if (nameMainMenu.equals("signMenuPosition")) {
			request.setAttribute("Login", "active");	// for indicate current tab
			request.setAttribute("message", message);	// for indicate error message
		}
		request.getSession().setAttribute("user", user);
	}

}
