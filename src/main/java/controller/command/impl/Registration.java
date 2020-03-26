package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import builder.UserBuilder;
import controller.command.Command;
import entity.Role;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.LoginService;
import service.RegistrationService;

public class Registration implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(Registration.class.getName());

	private RegistrationService registrationService = ServiceFactory.getInstance().getRegistrationService();

	@Override
	public String execute(HttpServletRequest request) {
		User userFromGUI = UserBuilder.getUser(request);
		User userAfterRegistration = null;
		
		try {
			userAfterRegistration = registrationService.addNewUserInDB(userFromGUI);
			if (userAfterRegistration == null) {
				setAttributeForGUI(request, "signMenuPosition", "Логин и пароль заняты", null);
				return "WEB-INF/view/sign.jsp";
			}
		} catch (ServiceException e) {
			LOGGER.error("Error registration", e);
			return "WEB-INF/view/error.jsp";			
		}
		
		setAttributeForGUI(request, "accountMenuPosition", "", userAfterRegistration);
		
		return "WEB-INF/view/account.jsp";
	}
	
	private void setAttributeForGUI(HttpServletRequest request, String nameMainMenu, String message, User user) {
		request.setAttribute(nameMainMenu, "active"); // for indicate main menu current item
		if (nameMainMenu.equals("signMenuPosition")) {
			request.setAttribute("Registration", "active");	// for indicate current tab
			request.setAttribute("message", message);	// for indicate error message
		}
		request.getSession().setAttribute("user", user);
	}

}
