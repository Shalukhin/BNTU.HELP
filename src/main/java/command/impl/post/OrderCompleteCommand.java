package command.impl.post;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import static command.constant.QueryURLConstant.*;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandPOST;
import command.URLManager;
import entity.FinishFile;
import entity.User;
import factory.ServiceFactory;
import property.LanguageManager;
import service.FinishFileService;
import service.OrderService;
import util.Parser;

public class OrderCompleteCommand implements CommandPOST {
	
	private static final Logger LOGGER = LogManager.getLogger(OrderCompleteCommand.class.getName());

	private OrderService orderService = ServiceFactory.getInstance().getOrderService();
	private FinishFileService finishFileService = ServiceFactory.getInstance().getFinishFileService();

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

		String completeIdOrderStr = Parser.getStringParameterByName(request, COMPLETE_ORDER_ID);
		FinishFile finishFile = null;		
		
		if (!ServletFileUpload.isMultipartContent(request)) {	
			LOGGER.error("Error upload file");
			return new URLManager(ERROR_QUERY_URL);
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
			
			if (completeIdOrderStr == null || !orderService.completeOrder(completeIdOrderStr, finishFile, currentUser)) {

				request.getSession().setAttribute(MESSAGE_ORDER_EXECUTE, LanguageManager.INSTANCE.getString(ORDER_NOT_UPLOAD));
			}
			
			return new URLManager(ACCOUNT_QUERY_URL).addParameterURL(TAB, COMPLETE_TAB_POSITION);
			
		} catch (Exception e) {
			LOGGER.error("Error upload file");
			return new URLManager(ERROR_QUERY_URL);
		}		
	}
}
