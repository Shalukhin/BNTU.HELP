package command.impl;

import static command.constant.AttributeNameConstant.ACCOUNT_MAIN_MENU_POSITION;
import static command.constant.AttributeNameConstant.LIST_EXECUTE_ORDER;
import static command.constant.AttributeNameConstant.LIST_USER_ORDER;
import static command.constant.AttributeNameConstant.LOGIN_TAB_POSITION;
import static command.constant.AttributeNameConstant.SIGN_MAIN_MENU_POSITION;
import static command.constant.AttributeNameConstant.USER;
import static command.constant.AttributeValueConstant.ACTIVE;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import command.Command;
import command.PageManager;
import static command.constant.ParameterNameConstant.*;
import java.util.List;
import entity.Order;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.OrderService;
import util.Parser;

public class OrderDeleteCommand implements Command {
	
private static final Logger LOGGER = LogManager.getLogger(OrderDeleteCommand.class.getName());

	private OrderService orderService = ServiceFactory.getInstance().getOrderService();

	@Override
	public PageManager execute(HttpServletRequest request) {
		if (request.getSession().getAttribute(USER) == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		String idOrderStr = Parser.getStringParameterByName(request, DELETE_ORDER_ID);
		
		try {
			if (idOrderStr != null && orderService.deleteOrder(idOrderStr)) {
				
				List<Order> allUserOrder = orderService.takeAllUserOrders(currentUser);
				request.getSession().setAttribute(LIST_USER_ORDER, allUserOrder);
				
				List<Order> allExecuteOrder = orderService.takeExecuteOrders(currentUser);			
				request.getSession().setAttribute(LIST_EXECUTE_ORDER, allExecuteOrder);
			}			
			
		} catch (ServiceException e) {
			LOGGER.error("Error delete order", e);
			return PageManager.ERROR_PAGE;
		}
		
		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE);	//for indicate current tab
		return PageManager.ACCOUNT_PAGE;
	}

}
