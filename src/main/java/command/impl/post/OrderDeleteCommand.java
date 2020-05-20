package command.impl.post;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandPOST;
import command.URLManager;
import static command.constant.ParameterNameConstant.*;
import static command.constant.QueryURLConstant.*;
import exception.ServiceException;
import factory.ServiceFactory;
import service.OrderService;
import util.Parser;

public class OrderDeleteCommand implements CommandPOST {
	
private static final Logger LOGGER = LogManager.getLogger(OrderDeleteCommand.class.getName());

	private OrderService orderService = ServiceFactory.getInstance().getOrderService();

	@Override
	public URLManager execute(HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute(USER) == null) {			
			return new URLManager(SIGN_QUERY_URL).addParameterURL(TAB, LOGIN_TAB_POSITION);
		}
		
		String idOrderStr = Parser.getStringParameterByName(request, DELETE_ORDER_ID);
		
		try {
			if (idOrderStr != null) {				
				orderService.deleteOrder(idOrderStr);
			}
			
			return new URLManager(ACCOUNT_QUERY_URL).addParameterURL(TAB, Parser.getStringParameterByName(request, TAB));
			
		} catch (ServiceException e) {
			LOGGER.error("Error delete order", e);
			return new URLManager(ERROR_QUERY_URL);
		}		
	}
}
