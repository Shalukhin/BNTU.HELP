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

public class OrderProcessCommand implements Command {

	private static final Logger LOGGER = LogManager.getLogger(OrderProcessCommand.class.getName());

	private OrderService orderService = ServiceFactory.getInstance().getOrderService();
	
	private static final String ADMINISTRATOR_ROLE = "admin";
	private static final String REALIZER_ROLE = "realizer";

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		if (request.getSession().getAttribute(USER) == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		if (!(currentUser.getRole().getNameRole().equals(ADMINISTRATOR_ROLE) || currentUser.getRole().getNameRole().equals(REALIZER_ROLE))) {
			request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(WELCOME_TAB_POSITION, ACTIVE);
			return PageManager.ACCOUNT_PAGE;
		}
		
		String processIdOrderStr = Parser.getStringParameterByName(request, PROCESS_ORDER_ID);
		String adjustedPriceTaskStr = Parser.getStringParameterByName(request, PROCESS_ADJUSTED_PRICE_TASK);
		
		try {
			
			if (processIdOrderStr != null && adjustedPriceTaskStr != null && orderService.processOrder(processIdOrderStr, adjustedPriceTaskStr, currentUser)) {
				
				List<Order> allUserOrder = orderService.takeAllUserOrders(currentUser);
				request.getSession().setAttribute(LIST_USER_ORDER, allUserOrder);
				
				List<Order> allExecuteOrder = orderService.takeExecuteOrders(currentUser);			
				request.getSession().setAttribute(LIST_EXECUTE_ORDER, allExecuteOrder);			
			}
			
			request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE); // for indicate current tab
			return PageManager.ACCOUNT_PAGE;
			
		} catch (ServiceException e) {
			LOGGER.error("Error process order", e);
			return PageManager.ERROR_PAGE;
		}		
	}

}
