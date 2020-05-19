package command.impl.get;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandGET;
import command.PageManager;
import entity.Order;
import entity.Task;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.OrderService;
import service.TaskService;
import service.UserService;
import util.Parser;

public class AccountCommand implements CommandGET {
	
	private final static Logger LOGGER = LogManager.getLogger(AccountCommand.class.getName());	
	
	private TaskService taskService = ServiceFactory.getInstance().getTaskService();
	private OrderService orderService = ServiceFactory.getInstance().getOrderService();
	private UserService userService = ServiceFactory.getInstance().getUserService();
	
	@Override
	public PageManager execute(HttpServletRequest request) {		
				
		if (request.getSession().getAttribute(USER) == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}
		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		try {
			currentUser = userService.refreshUser(currentUser.getId());
			request.getSession().setAttribute(USER, currentUser);
			
			List<Task> allTask = taskService.takeAllTask();			
			request.getSession().setAttribute(LIST_ALL_TASK, allTask);
			
			List<Order> allUserOrder = orderService.takeAllUserOrders(currentUser);			
			request.getSession().setAttribute(LIST_USER_ORDER, allUserOrder);
			
			List<Order> allExecuteOrder = orderService.takeExecuteOrders(currentUser);			
			request.getSession().setAttribute(LIST_EXECUTE_ORDER, allExecuteOrder);
			
			List<Order> allCompleteOrder = orderService.takeCompleteOrders(currentUser);			
			request.getSession().setAttribute(LIST_COMPLETE_ORDER, allCompleteOrder);
			
		} catch (ServiceException e) {
			LOGGER.error("Error build list resource", e);
		}					

		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE);	//for indicate current tab
		
		return PageManager.ACCOUNT_PAGE;
	}
	
}
