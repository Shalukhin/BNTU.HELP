package command.impl;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.Command;
import command.PageManager;
import entity.Task;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.TaskService;
import service.UserService;
import util.Parser;
import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

import java.util.ArrayList;
import java.util.List;

public class RegistrationCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(RegistrationCommand.class.getName());
	
	private TaskService taskService = ServiceFactory.getInstance().getTaskService();
	private UserService userService = ServiceFactory.getInstance().getUserService();
	
	@Override
	public PageManager execute(HttpServletRequest request) {
		
		String loginUserFromGUI = Parser.getStringParameterByName(request, LOGIN);
		String passwordUserFromGUI = Parser.getStringParameterByName(request, PASSWORD);
		
		User userAfterRegistration = null;
		
		try {
			userAfterRegistration = userService.registrationUser(loginUserFromGUI, passwordUserFromGUI);
			
			if (userAfterRegistration == null) {			
				
				request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
				request.setAttribute(REGISTRATION_TAB_POSITION, ACTIVE);
				request.setAttribute(MESSAGE_REGISTRATION, INCORRECT_LOGIN_OR_PASS);
				return PageManager.SIGN_PAGE;
			}
		} catch (ServiceException e) {
			LOGGER.error("Error registration", e);
			return PageManager.ERROR_PAGE;			
		}
		request.getSession().setAttribute(USER, userAfterRegistration);
		
		List<Task> allTask = new ArrayList<>();
		try {
			allTask = taskService.takeAllTask();
		} catch (ServiceException e) {
			LOGGER.error("Error take task list", e);			
		}		
		request.getSession().setAttribute(LIST_ALL_TASK, allTask);
		
		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE);	//for indicate current tab	
		
		return PageManager.ACCOUNT_PAGE;
	}	

}
