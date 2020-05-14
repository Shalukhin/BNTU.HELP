package command.impl;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import builder.OrderBuilder;
import command.Command;
import command.PageManager;
import entity.Order;
import entity.Task;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.OrderService;
import service.TaskService;
import util.Parser;

public class OrderCommand implements Command {	
	
	private static final Logger LOGGER = LogManager.getLogger(OrderCommand.class.getName());	
	
	private OrderBuilder orderBuilder = new OrderBuilder();
	private OrderService orderService = ServiceFactory.getInstance().getOrderService();
	private TaskService taskService = ServiceFactory.getInstance().getTaskService();

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		if (request.getSession().getAttribute(USER) == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		
								
		Task orderedTask = null;
		try {
			orderedTask = taskService.takeTaskByName(Parser.getStringParameterByName(request, ORDERED_TASK));
		} catch (ServiceException e) {
			LOGGER.error("Error take ordered task", e);
		}
		
		if (orderedTask != null) {
			Order order = orderBuilder
							.createNewOrder()
							.withUser(currentUser)
							.withTask(orderedTask)
							.withNote(Parser.getStringParameterByName(request, NOTE))
							.withAdjustedPriceTask(orderedTask.getPriceTask())
							.build();
			try {
				orderService.addNewOrder(order);
			} catch (ServiceException e) {
				LOGGER.error("Error add new order", e);
				return PageManager.ERROR_PAGE;
			}
			
			List<Order> allUserOrder = new ArrayList<>();
			try {
				allUserOrder = orderService.takeAllUserOrders(currentUser);
			} catch (ServiceException e) {
				LOGGER.error("Error take orders list", e);
			}		
			request.getSession().setAttribute(LIST_USER_ORDER, allUserOrder);
			
			List<Order> allExecuteOrder = new ArrayList<>();
			try {
				allExecuteOrder = orderService.takeExecuteOrders(currentUser);
			}catch (ServiceException e) {
				LOGGER.error("Error take execute orders list", e);
			}		
			request.getSession().setAttribute(LIST_EXECUTE_ORDER, allExecuteOrder);
			
			request.setAttribute(MESSAGE_ORDER, ORDER_FORMED);
			request.setAttribute(MESSAGE_ORDER_JOB_TYPE_ORDERED, orderedTask.getNameTask());
		} else {
			request.setAttribute(MESSAGE_ORDER, ORDER_NOT_FORMED);
		}		
		
		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE);	//for indicate current tab
		return PageManager.ACCOUNT_PAGE;
	}

}
