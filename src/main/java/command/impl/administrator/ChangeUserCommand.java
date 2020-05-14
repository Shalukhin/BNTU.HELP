package command.impl.administrator;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.Command;
import command.PageManager;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.UserService;
import util.Parser;

public class ChangeUserCommand implements Command {
	
	private static final Logger LOGGER = LogManager.getLogger(ChangeUserCommand.class.getName());
	
	private static final String ADMINISTRATOR_ROLE = "admin";
	private static final String EDIT = "edit";
	private static final String DELETE = "del";
	
	
	private UserService userService = ServiceFactory.getInstance().getUserService();

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		if (request.getSession().getAttribute(USER) == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		if (!currentUser.getRole().getNameRole().equals(ADMINISTRATOR_ROLE)) {
			request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(WELCOME_TAB_POSITION, ACTIVE);
			return PageManager.ACCOUNT_PAGE;
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
					request.setAttribute(MESSAGE_ADMIN_USER_LIST, USER_UPDATE + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_MESSAGE)));
				} else {
					request.setAttribute(MESSAGE_ADMIN_USER_LIST, USER_NOT_UPDATE_DATA_NOT_CORRECT);
				}				
				break;
				
			case DELETE :
				
				if(userService.deleteUser(idUserFromGUI)) {
					request.setAttribute(MESSAGE_ADMIN_USER_LIST, USER_DELETE + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_MESSAGE)));
				} else {
					request.setAttribute(MESSAGE_ADMIN_USER_LIST, USER_NOT_DELETE);
				}				
				break;
				
			default :
				throw new Exception();
			}
			
		}catch (Exception e) {
			LOGGER.error("Error admin change user");
			return PageManager.ERROR_PAGE;
		}		
		
		List<User> allUser = new ArrayList<>();
		try {
			allUser = userService.takeAllUser();
		} catch (ServiceException e) {
			LOGGER.error("Error admin all user", e);
			return PageManager.ERROR_PAGE;
		}
		request.getSession().setAttribute(LIST_ALL_USER, allUser);
			
		request.setAttribute(ADMINISTRATOR_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE);	//for indicate current tab
		return PageManager.ADMINISTATOR_PAGE;
	}

}
