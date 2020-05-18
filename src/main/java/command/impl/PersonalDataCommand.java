package command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.Command;
import command.PageManager;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.UserService;
import service.impl.PersonalDataServiceImpl;
import util.Parser;
import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

public class PersonalDataCommand implements Command {
	
	private static final Logger LOGGER = LogManager.getLogger(PersonalDataServiceImpl.class.getName());	
	
	private UserService userService = ServiceFactory.getInstance().getUserService();

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		if (request.getSession().getAttribute(USER) == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		String nameFromGUI = Parser.getStringParameterByName(request, NAME);
		String phoneFromGUI = Parser.getStringParameterByName(request, PHONE);
		String emailFromGUI = Parser.getStringParameterByName(request, EMAIL);
		
		User updatedUser;
		
		try {
			updatedUser = userService.savePersonalData(nameFromGUI, phoneFromGUI, emailFromGUI, currentUser);
		} catch (ServiceException e) {
			LOGGER.error("Error personal data", e);
			return PageManager.ERROR_PAGE;
		}
		
		if (updatedUser != null) {
			request.getSession().setAttribute(USER, updatedUser);
		}
		
		request.setAttribute(MESSAGE_PERSONAL, (updatedUser != null ? SAVED : NOT_SAVED));
		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE);	//for indicate current tab
		return PageManager.ACCOUNT_PAGE;
	}

}
