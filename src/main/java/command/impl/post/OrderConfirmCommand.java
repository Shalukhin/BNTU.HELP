package command.impl.post;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import static command.constant.QueryURLConstant.ACCOUNT_QUERY_URL;
import static command.constant.QueryURLConstant.ERROR_QUERY_URL;
import static command.constant.QueryURLConstant.SIGN_QUERY_URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandPOST;
import command.URLManager;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import property.LanguageManager;
import service.OrderService;
import util.Parser;

public class OrderConfirmCommand implements CommandPOST {
	
	private static final Logger LOGGER = LogManager.getLogger(OrderConfirmCommand.class.getName());
	
	private OrderService orderService = ServiceFactory.getInstance().getOrderService();

	@Override
	public URLManager execute(HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute(USER) == null) {			
			return new URLManager(SIGN_QUERY_URL).addParameterURL(TAB, LOGIN_TAB_POSITION);
		}		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		String confirmOrderIdStr = Parser.getStringParameterByName(request, CONFIRM_ORDER_ID);
		
		try {
			
			if (confirmOrderIdStr == null || !orderService.confirmOrder(confirmOrderIdStr, currentUser)) {
				
				request.getSession().setAttribute(MESSAGE_ORDER_LIST, LanguageManager.INSTANCE.getString(ORDER_NOT_CONFIRMED));
			}
			
			return new URLManager(ACCOUNT_QUERY_URL).addParameterURL(TAB, LIST_TAB_POSITION);
			
		} catch (ServiceException e) {
			LOGGER.error("Error confirm order", e);
			return new URLManager(ERROR_QUERY_URL);
		}
	}
}
