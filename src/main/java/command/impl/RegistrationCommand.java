package command.impl;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import builder.UserBuilder;
import command.Command;
import command.PageManager;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.UserService;
import util.Parser;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

public class RegistrationCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(RegistrationCommand.class.getName());
	
	private UserService userService = ServiceFactory.getInstance().getUserService();
	private UserBuilder userBuilder = new UserBuilder();

	@Override
	public PageManager execute(HttpServletRequest request) {
		User userFromGUI = userBuilder
							.createNewUser()
							.withLogin(Parser.getStringParameterByName(request, LOGIN))
							.withPassword(Parser.getStringParameterByName(request, PASSWORD))
							.build();
		
		User userAfterRegistration = null;
		
		try {
			userAfterRegistration = userService.registrationUser(userFromGUI);
			
			if (userAfterRegistration == null) {			
				
				request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
				request.setAttribute(REGISTRATION_SUBMENY_POSITION, ACTIVE);
				request.setAttribute(MESSAGE_REGISTRATION, INCORRECT_LOGIN_OR_PASS);
				return PageManager.SIGN_PAGE;
			}
		} catch (ServiceException e) {
			LOGGER.error("Error registration", e);
			return PageManager.ERROR_PAGE;			
		}
		
		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, KIND_OF_ACCOUNT), ACTIVE);	//for indicate current tab
		request.getSession().setAttribute(USER, userAfterRegistration);
		return PageManager.ACCOUNT_PAGE;
	}	

}
