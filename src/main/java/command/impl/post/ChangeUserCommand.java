package command.impl.post;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import static command.constant.QueryURLConstant.ACCOUNT_QUERY_URL;
import static command.constant.QueryURLConstant.ERROR_QUERY_URL;
import static command.constant.QueryURLConstant.SIGN_QUERY_URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandPOST;
import command.URLManager;
import command.constant.QueryURLConstant;
import entity.User;
import factory.ServiceFactory;
import property.LanguageManager;
import service.UserService;
import util.Parser;

public class ChangeUserCommand implements CommandPOST {
	
	private static final Logger LOGGER = LogManager.getLogger(ChangeUserCommand.class.getName());
	
	private static final String ADMINISTRATOR_ROLE = "admin";
	private static final String EDIT = "edit";
	private static final String DELETE = "del";
	
	
	private UserService userService = ServiceFactory.getInstance().getUserService();

	@Override
	public URLManager execute(HttpServletRequest request) {
		
		if (request.getSession().getAttribute(USER) == null) {			
			return new URLManager(SIGN_QUERY_URL).addParameterURL(TAB, LOGIN_TAB_POSITION);
		}		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		if (!currentUser.getRole().getNameRole().equals(ADMINISTRATOR_ROLE)) {			
			return new URLManager(ACCOUNT_QUERY_URL).addParameterURL(TAB, WELCOME_TAB_POSITION);
		}
		
		String idUserFromGUI = Parser.getStringParameterByName(request, CHANGE_ID_USER);
		String loginUserFromGUI = Parser.getStringParameterByName(request, CHANGE_LOGIN_USER);
		String passwordUserFromGUI = Parser.getStringParameterByName(request, CHANGE_PASSWORD_USER);
		String RoleFromGUI = Parser.getStringParameterByName(request, CHANGE_ROLE_USER);
		String nameStatusFromGUI = Parser.getStringParameterByName(request, CHANGE_STATUS_USER);
		
		String action = Parser.getStringParameterByName(request, ACTION);
		try {
			
			switch (action) {
			case EDIT :	
				
				if(userService.editUser(idUserFromGUI, loginUserFromGUI, passwordUserFromGUI, RoleFromGUI, nameStatusFromGUI) != null) {
					request.getSession().setAttribute(MESSAGE_ADMIN_USER_LIST, LanguageManager.INSTANCE.getString(USER_UPDATE) + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_MESSAGE)));
				} else {
					request.getSession().setAttribute(MESSAGE_ADMIN_USER_LIST, LanguageManager.INSTANCE.getString(USER_NOT_UPDATE_DATA_NOT_CORRECT));
				}				
				break;
				
			case DELETE :
				
				if(userService.deleteUser(idUserFromGUI)) {
					request.getSession().setAttribute(MESSAGE_ADMIN_USER_LIST, LanguageManager.INSTANCE.getString(USER_DELETE) + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_MESSAGE)));
				} else {
					request.getSession().setAttribute(MESSAGE_ADMIN_USER_LIST, LanguageManager.INSTANCE.getString(USER_NOT_DELETE));
				}				
				break;
				
			default :
				LOGGER.error("Error admin change user");
				return new URLManager(ERROR_QUERY_URL);
			}
			
		}catch (Exception e) {
			LOGGER.error("Error admin change user");
			return new URLManager(ERROR_QUERY_URL);
		}			
		
		return new URLManager(QueryURLConstant.ADMINISTRATOR_QUERY_URL).addParameterURL(TAB, ALL_USER_ADMIN_TAB_POSITION);
	}
}
