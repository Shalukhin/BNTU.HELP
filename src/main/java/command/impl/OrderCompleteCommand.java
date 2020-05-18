package command.impl;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.Command;
import command.PageManager;
import entity.FinishFile;
import entity.Order;
import entity.User;
import factory.ServiceFactory;
import service.FinishFileService;
import service.OrderService;
import util.Parser;

public class OrderCompleteCommand implements Command {
	
	private static final Logger LOGGER = LogManager.getLogger(OrderCompleteCommand.class.getName());

	private OrderService orderService = ServiceFactory.getInstance().getOrderService();
	private FinishFileService finishFileService = ServiceFactory.getInstance().getFinishFileService();

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

		String completeIdOrderStr = Parser.getStringParameterByName(request, COMPLETE_ORDER_ID);
		FinishFile finishFile = null;		
		
		if (!ServletFileUpload.isMultipartContent(request)) {	
			LOGGER.error("Error upload file");
			return PageManager.ERROR_PAGE;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();		
		factory.setSizeThreshold(1024*1024*50);
		
		File tempDir = (File) request.getAttribute("tempdir");
		factory.setRepository(tempDir);	
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024*1024*50);		
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();			
			while (iter.hasNext()) {
				FileItem item = iter.next();				
				if (!item.isFormField()) {						
					finishFile = finishFileService.addNewFinishFile(item.getName(), item);					
					break;
				}
			}
			
			if (completeIdOrderStr != null && orderService.completeOrder(completeIdOrderStr, finishFile, currentUser)) {

				List<Order> allUserOrder = orderService.takeAllUserOrders(currentUser);
				request.getSession().setAttribute(LIST_USER_ORDER, allUserOrder);

				List<Order> allExecuteOrder = orderService.takeExecuteOrders(currentUser);
				request.getSession().setAttribute(LIST_EXECUTE_ORDER, allExecuteOrder);
				
				List<Order> allCompleteOrder = orderService.takeCompleteOrders(currentUser);			
				request.getSession().setAttribute(LIST_COMPLETE_ORDER, allCompleteOrder);
			}
			
		} catch (Exception e) {			
			return PageManager.ERROR_PAGE;
		}		
		
		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE); // for indicate current tab
		return PageManager.ACCOUNT_PAGE;
	}

}
