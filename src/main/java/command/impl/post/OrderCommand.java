package command.impl.post;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandPOST;
import command.URLManager;
import static command.constant.QueryURLConstant.*;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import property.LanguageManager;
import service.OrderService;
import util.Parser;

public class OrderCommand implements CommandPOST {	
	
	private static final Logger LOGGER = LogManager.getLogger(OrderCommand.class.getName());	
	
	private OrderService orderService = ServiceFactory.getInstance().getOrderService();	

	@Override
	public URLManager execute(HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute(USER) == null) {			
			return new URLManager(SIGN_QUERY_URL).addParameterURL(TAB, LOGIN_TAB_POSITION);
		}		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		String orderedNameTask = Parser.getStringParameterByName(request, ORDERED_TASK);
		String note = Parser.getStringParameterByName(request, NOTE);	
		
		try {
			if (orderService.addNewOrder(orderedNameTask, note, currentUser) != null) {	
				
				request.getSession().setAttribute(MESSAGE_ORDER, LanguageManager.INSTANCE.getString(ORDER_FORMED));
				request.getSession().setAttribute(MESSAGE_ORDER_JOB_TYPE_ORDERED, orderedNameTask);
				
			}  else {
				
				request.getSession().setAttribute(MESSAGE_ORDER, LanguageManager.INSTANCE.getString(ORDER_NOT_FORMED));
			}
			
			return new URLManager(ACCOUNT_QUERY_URL).addParameterURL(TAB, ORDER_TAB_POSITION);
			
		} catch (ServiceException e) {
			LOGGER.error("Error add new order", e);
			return new URLManager(ERROR_QUERY_URL);
		}			
	}
}
