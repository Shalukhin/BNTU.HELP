package command.impl.post;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import static command.constant.QueryURLConstant.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandPOST;
import command.URLManager;
import command.constant.QueryURLConstant;
import entity.User;
import factory.ServiceFactory;
import property.LanguageManager;
import service.TaskService;
import util.Parser;

public class ChangeTaskCommand implements CommandPOST {
	
	private static final Logger LOGGER = LogManager.getLogger(ChangeTaskCommand.class.getName());
	
	private static final String ADMINISTRATOR_ROLE = "admin";
	private static final String CREATE = "create";
	private static final String EDIT = "edit";
	private static final String DELETE = "del";
	
	
	private TaskService taskService = ServiceFactory.getInstance().getTaskService();

	@Override
	public URLManager execute(HttpServletRequest request) {

		if (request.getSession().getAttribute(USER) == null) {			
			return new URLManager(SIGN_QUERY_URL).addParameterURL(TAB, LOGIN_TAB_POSITION);
		}		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		if (!currentUser.getRole().getNameRole().equals(ADMINISTRATOR_ROLE)) {			
			return new URLManager(ACCOUNT_QUERY_URL).addParameterURL(TAB, WELCOME_TAB_POSITION);
		}
		
		String idTaskFromGUI = Parser.getStringParameterByName(request, CHANGE_ID_TASK);
		String nameTaskFromGUI = Parser.getStringParameterByName(request, CHANGE_NAME_TASK);
		String priceTaskFromGUI = Parser.getStringParameterByName(request, CHANGE_PRICE_TASK);
		String courseTaskFromGUI = Parser.getStringParameterByName(request, CHANGE_COURSE_TASK);
		String subjectTaskFromGUI = Parser.getStringParameterByName(request, CHANGE_SUBJECT_TASK);
		
		String action = Parser.getStringParameterByName(request, ACTION);
		
		try {			
			switch (action) {
			case CREATE :
				
				if (taskService.addNewTask(nameTaskFromGUI, priceTaskFromGUI, courseTaskFromGUI, subjectTaskFromGUI) != null) {
					request.getSession().setAttribute(MESSAGE_ADMIN_TASK_LIST, LanguageManager.INSTANCE.getString(TASK_ADDED) + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_MESSAGE)));					
				} else {
					request.getSession().setAttribute(MESSAGE_ADMIN_TASK_LIST, LanguageManager.INSTANCE.getString(TASK_NOT_ADDED_DATA_NOT_CORRECT));
					
					request.getSession().setAttribute(INPUT_NAME_TASK, nameTaskFromGUI);
					request.getSession().setAttribute(INPUT_PRICE_TASK, priceTaskFromGUI);
					request.getSession().setAttribute(INPUT_COURSE_TASK, courseTaskFromGUI);
					request.getSession().setAttribute(INPUT_SUBJECT_TASK, subjectTaskFromGUI);					
				}				
				break;
				
			case EDIT :	
				
				if (taskService.editTask(idTaskFromGUI, nameTaskFromGUI, priceTaskFromGUI, courseTaskFromGUI, subjectTaskFromGUI) != null) {
					request.getSession().setAttribute(MESSAGE_ADMIN_TASK_LIST, LanguageManager.INSTANCE.getString(TASK_UPDATE) + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_MESSAGE)));					
				} else {
					request.getSession().setAttribute(MESSAGE_ADMIN_TASK_LIST, LanguageManager.INSTANCE.getString(TASK_NOT_UPDATE_DATA_NOT_CORRECT));
				}				
				break;
				
			case DELETE :
				
				if (taskService.deleteTask(idTaskFromGUI)) {
					request.getSession().setAttribute(MESSAGE_ADMIN_TASK_LIST, LanguageManager.INSTANCE.getString(TASK_DELETE) + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_MESSAGE)));
				} else {
					request.getSession().setAttribute(MESSAGE_ADMIN_TASK_LIST, LanguageManager.INSTANCE.getString(TASK_NOT_DELETE));
				}
				break;
			default :
				LOGGER.error("Error admin change task");
				return new URLManager(ERROR_QUERY_URL);
			}
			
		} catch (Exception e) {
			LOGGER.error("Error admin change task");
			return new URLManager(ERROR_QUERY_URL);
		}		
		
		
		return new URLManager(QueryURLConstant.ADMINISTRATOR_QUERY_URL).addParameterURL(TAB, ALL_TASK_ADMIN_TAB_POSITION);
	}

}
