package command.impl;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.Command;
import command.PageManager;
import entity.Order;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.OrderService;
import util.Parser;

public class OrderCommand implements Command {	
	
	private static final Logger LOGGER = LogManager.getLogger(OrderCommand.class.getName());	
	
	private OrderService orderService = ServiceFactory.getInstance().getOrderService();	

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		if (request.getSession().getAttribute(USER) == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		String orderedNameTask = Parser.getStringParameterByName(request, ORDERED_TASK);
		String note = Parser.getStringParameterByName(request, NOTE);	
		
		try {
			if (orderService.addNewOrder(orderedNameTask, note, currentUser) != null) {
				
				List<Order> allUserOrder = orderService.takeAllUserOrders(currentUser);
				request.getSession().setAttribute(LIST_USER_ORDER, allUserOrder);
				
				List<Order> allExecuteOrder = orderService.takeExecuteOrders(currentUser);
				request.getSession().setAttribute(LIST_EXECUTE_ORDER, allExecuteOrder);
				
				request.setAttribute(MESSAGE_ORDER, ORDER_FORMED);
				request.setAttribute(MESSAGE_ORDER_JOB_TYPE_ORDERED, orderedNameTask);
				
			}  else {
				request.setAttribute(MESSAGE_ORDER, ORDER_NOT_FORMED);
			}
			
			request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
			request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE);	//for indicate current tab
			return PageManager.ACCOUNT_PAGE;
			
		} catch (ServiceException e) {
			LOGGER.error("Error add new order", e);
			return PageManager.ERROR_PAGE;
			
		}			
	}
}
