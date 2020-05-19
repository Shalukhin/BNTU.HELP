package command.impl.post;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandPOST;
import command.URLManager;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import property.LanguageManager;
import service.UserService;
import util.Parser;
import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import static command.constant.QueryURLConstant.*;

public class PersonalDataCommand implements CommandPOST {
	
	private static final Logger LOGGER = LogManager.getLogger(PersonalDataCommand.class.getName());	
	
	private UserService userService = ServiceFactory.getInstance().getUserService();

	@Override
	public URLManager execute(HttpServletRequest request) {
		
		if (request.getSession().getAttribute(USER) == null) {			
			return new URLManager(SIGN_QUERY_URL).addParameterURL(TAB, LOGIN_TAB_POSITION);
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
			return new URLManager(ERROR_QUERY_URL);
		}
		
		if (updatedUser != null) {
			request.getSession().setAttribute(USER, updatedUser);
		}
		
		request.getSession().setAttribute(MESSAGE_PERSONAL, (updatedUser != null ? LanguageManager.INSTANCE.getString(SAVED) : LanguageManager.INSTANCE.getString(NOT_SAVED)));
				
		return new URLManager(ACCOUNT_QUERY_URL).addParameterURL(TAB, PERSONAL_TAB_POSITION);
	}
}
