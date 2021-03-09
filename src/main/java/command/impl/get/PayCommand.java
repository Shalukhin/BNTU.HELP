package command.impl.get;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import command.CommandGET;
import command.PageManager;
import dao.OrderDAO;
import entity.Order;
import entity.User;
import factory.DAOFactory;
import factory.ServiceFactory;
import service.OrderService;
import util.Parser;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;

public class PayCommand implements CommandGET {
	
	private final static Logger LOGGER = LogManager.getLogger(PayCommand.class.getName());
	
	private OrderService orderService = ServiceFactory.getInstance().getOrderService();

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		String idOrderForPay = Parser.getStringParameterByName(request, GO_TO_PAY_ORDER_ID);		
		if (idOrderForPay != null) {
			try {				
				request.setAttribute(ORDER_FOR_PAY, orderService.takeOrderById(idOrderForPay, currentUser));
			} catch(Exception e) {
				LOGGER.error("Error load order for pay", e);
			}
		}
		
		request.setAttribute(PAY_MAIN_MENU_POSITION, ACTIVE);		
		return PageManager.PAY_PAGE;
	}
}
