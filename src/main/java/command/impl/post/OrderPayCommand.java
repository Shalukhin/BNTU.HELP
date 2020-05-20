package command.impl.post;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import static command.constant.QueryURLConstant.*;
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

public class OrderPayCommand implements CommandPOST {

	private static final Logger LOGGER = LogManager.getLogger(OrderPayCommand.class.getName());

	private OrderService orderService = ServiceFactory.getInstance().getOrderService();

	private static final String ADMINISTRATOR_ROLE = "admin";
	private static final String REALIZER_ROLE = "realizer";

	@Override
	public URLManager execute(HttpServletRequest request, HttpServletResponse response) {

		if (request.getSession().getAttribute(USER) == null) {			
			return new URLManager(SIGN_QUERY_URL).addParameterURL(TAB, LOGIN_TAB_POSITION);
		}		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		if (!(currentUser.getRole().getNameRole().equals(ADMINISTRATOR_ROLE) || currentUser.getRole().getNameRole().equals(REALIZER_ROLE))) {			
			return new URLManager(ACCOUNT_QUERY_URL).addParameterURL(TAB, WELCOME_TAB_POSITION);
		}

		String payIdOrderStr = Parser.getStringParameterByName(request, PAY_ORDER_ID);
		
		try {
			
			if (payIdOrderStr == null || !orderService.payOrder(payIdOrderStr, currentUser)) {

				request.getSession().setAttribute(MESSAGE_ORDER_EXECUTE, LanguageManager.INSTANCE.getString(ORDER_NOT_PAY));
			}
			
			return new URLManager(ACCOUNT_QUERY_URL).addParameterURL(TAB, EXECUTE_TAB_POSITION);
			
		} catch (ServiceException e) {
			LOGGER.error("Error confirm order", e);
			return new URLManager(ERROR_QUERY_URL);
		}		
	}
}
