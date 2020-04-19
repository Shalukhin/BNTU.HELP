package command.impl;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.UserBuilder;
import command.Command;
import command.PageManager;
import entity.PersonalData;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.PersonalDataService;
import service.UserService;
import util.Parser;
import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

public class LoginCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class.getName());
	
	private PersonalDataService personalDataService = ServiceFactory.getInstance().getPersonalDataService();
	private UserService userService = ServiceFactory.getInstance().getUserService();
	private UserBuilder userBuilder = new UserBuilder();

	@Override
	public PageManager execute(HttpServletRequest request) {

		User userFromGUI = userBuilder
							.createNewUser()
							.withLogin(Parser.getStringParameterByName(request, LOGIN))
							.withPassword(Parser.getStringParameterByName(request, PASSWORD))
							.build();

		User userFromDB = null;

		try {			
			userFromDB = userService.loginUser(userFromGUI);
			if (userFromDB == null) {				
				request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
				request.setAttribute(LOGIN_SUBMENY_POSITION, ACTIVE);
				request.setAttribute(MESSAGE_LOGIN, INVALID_LOGIN_OR_PASS);
				return PageManager.SIGN_PAGE;
			}
		} catch (ServiceException e) {
			LOGGER.error("Error login", e);
			return PageManager.ERROR_PAGE;
		}	
		
		PersonalData personalData = null;
		try {
			personalData = personalDataService.takeUserPersonalData(userFromDB.getId());
		} catch (ServiceException e) {
			LOGGER.error("Error take user personal data", e);
		}
		
		request.getSession().setAttribute(PERSONAL_DATA, personalData);
		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, KIND_OF_ACCOUNT), ACTIVE);	//for indicate current tab
		request.getSession().setAttribute(USER, userFromDB);
		return PageManager.ACCOUNT_PAGE;
	}
	

}
