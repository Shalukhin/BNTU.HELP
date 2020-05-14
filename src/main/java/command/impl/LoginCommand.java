package command.impl;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.UserBuilder;
import command.Command;
import command.PageManager;
import entity.Order;
import entity.PersonalData;
import entity.Task;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.OrderService;
import service.PersonalDataService;
import service.TaskService;
import service.UserService;
import util.Parser;
import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import java.util.ArrayList;
import java.util.List;

public class LoginCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class.getName());
	
	
	
	
	private PersonalDataService personalDataService = ServiceFactory.getInstance().getPersonalDataService();
	private TaskService taskService = ServiceFactory.getInstance().getTaskService();
	private OrderService orderService = ServiceFactory.getInstance().getOrderService();
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
				request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);
				request.setAttribute(MESSAGE_LOGIN, INVALID_LOGIN_OR_PASS);
				return PageManager.SIGN_PAGE;
			}
		} catch (ServiceException e) {
			LOGGER.error("Error login", e);
			return PageManager.ERROR_PAGE;
		}
		request.getSession().setAttribute(USER, userFromDB);
		
		PersonalData personalData = null;
		try {
			personalData = personalDataService.takeUserPersonalData(userFromDB.getId());
		} catch (ServiceException e) {
			LOGGER.error("Error take user personal data", e);
		}
		request.getSession().setAttribute(PERSONAL_DATA, personalData);
		
		List<Task> allTask = new ArrayList<>();
		try {
			allTask = taskService.takeAllTask();
		} catch (ServiceException e) {
			LOGGER.error("Error take task list", e);			
		}
		request.getSession().setAttribute(LIST_ALL_TASK, allTask);
		
		List<Order> allUserOrder = new ArrayList<>();
		try {
			allUserOrder = orderService.takeAllUserOrders(userFromDB);
		} catch (ServiceException e) {
			LOGGER.error("Error take orders list", e);
		}		
		request.getSession().setAttribute(LIST_USER_ORDER, allUserOrder);
		
		List<Order> allExecuteOrder = new ArrayList<>();
		try {
			allExecuteOrder = orderService.takeExecuteOrders(userFromDB);
		}catch (ServiceException e) {
			LOGGER.error("Error take execute orders list", e);
		}		
		request.getSession().setAttribute(LIST_EXECUTE_ORDER, allExecuteOrder);
		
		List<Order> allCompleteOrder = new ArrayList<>();
		try {
			allCompleteOrder = orderService.takeCompleteOrders(userFromDB);
		}catch (ServiceException e) {
			LOGGER.error("Error take complete orders list", e);
		}		
		request.getSession().setAttribute(LIST_COMPLETE_ORDER, allCompleteOrder);
		
		
		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE);	//for indicate current tab
		
		return PageManager.ACCOUNT_PAGE;
	}
	

}
