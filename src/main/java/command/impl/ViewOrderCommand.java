package command.impl;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

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

public class ViewOrderCommand implements Command {
	
private final static Logger LOGGER = LogManager.getLogger(ViewOrderCommand.class.getName());	
	
	private OrderService orderService = ServiceFactory.getInstance().getOrderService();

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		if (request.getSession().getAttribute(USER) == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}
		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		try {
			String viewIdOrder = Parser.getStringParameterByName(request, VIEW_ORDER_ID);
			Order order = orderService.takeOrderById(viewIdOrder, currentUser);
			
			if (viewIdOrder == null) {
				LOGGER.error("Error id view order");
				return PageManager.ERROR_PAGE;
			}
			request.setAttribute(VIEW_CURRENT_ORDER, order);			
			
		} catch (ServiceException e) {
			LOGGER.error("Error view order", e);
			return PageManager.ERROR_PAGE;
		}		
		
		request.setAttribute(TAB, Parser.getStringParameterByName(request, TAB));
		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
		return PageManager.ORDER_PAGE;
	}

}
