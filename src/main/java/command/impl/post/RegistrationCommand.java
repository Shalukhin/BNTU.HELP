package command.impl.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandPOST;
import command.URLManager;
import static command.constant.QueryURLConstant.*;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import property.LanguageManager;
import service.UserService;
import util.Parser;
import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;

public class RegistrationCommand implements CommandPOST {
	
	private final static Logger LOGGER = LogManager.getLogger(RegistrationCommand.class.getName());	
	
	private UserService userService = ServiceFactory.getInstance().getUserService();
	
	@Override
	public URLManager execute(HttpServletRequest request, HttpServletResponse response) {
		
		String loginUserFromGUI = Parser.getStringParameterByName(request, LOGIN);
		String passwordUserFromGUI = Parser.getStringParameterByName(request, PASSWORD);
		
		User userAfterRegistration = null;
		
		try {
			userAfterRegistration = userService.registrationUser(loginUserFromGUI, passwordUserFromGUI);
			
			if (userAfterRegistration == null) {
				request.getSession().setAttribute(MESSAGE_REGISTRATION, LanguageManager.INSTANCE.getString(INCORRECT_LOGIN_OR_PASS));
				return new URLManager(SIGN_QUERY_URL).addParameterURL(TAB, REGISTRATION_TAB_POSITION);								
			}
		} catch (ServiceException e) {
			LOGGER.error("Error registration", e);
			return new URLManager(ERROR_QUERY_URL);			
		}
		request.getSession().setAttribute(USER, userAfterRegistration);
		
		return new URLManager(ACCOUNT_QUERY_URL).addParameterURL(TAB, WELCOME_TAB_POSITION);
	}
}
