package command.impl.administrator;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.Command;
import command.PageManager;
import entity.Task;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.TaskService;
import util.Parser;

public class ChangeTaskCommand implements Command {
	
	private static final Logger LOGGER = LogManager.getLogger(ChangeTaskCommand.class.getName());
	
	private static final String ADMINISTRATOR_ROLE = "admin";
	private static final String CREATE = "create";
	private static final String EDIT = "edit";
	private static final String DELETE = "del";
	
	
	private TaskService taskService = ServiceFactory.getInstance().getTaskService();

	@Override
	public PageManager execute(HttpServletRequest request) {
		if (request.getSession().getAttribute(USER) == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		if (!currentUser.getRole().getNameRole().equals(ADMINISTRATOR_ROLE)) {
			request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(WELCOME_TAB_POSITION, ACTIVE);
			return PageManager.ACCOUNT_PAGE;
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
					request.setAttribute(MESSAGE_ADMIN_TASK_LIST, TASK_ADDED + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_MESSAGE)));					
				} else {
					request.setAttribute(MESSAGE_ADMIN_TASK_LIST, TASK_NOT_ADDED_DATA_NOT_CORRECT);
					
					request.setAttribute(INPUT_NAME_TASK, nameTaskFromGUI);
					request.setAttribute(INPUT_PRICE_TASK, priceTaskFromGUI);
					request.setAttribute(INPUT_COURSE_TASK, courseTaskFromGUI);
					request.setAttribute(INPUT_SUBJECT_TASK, subjectTaskFromGUI);					
				}				
				break;
				
			case EDIT :	
				
				if (taskService.editTask(idTaskFromGUI, nameTaskFromGUI, priceTaskFromGUI, courseTaskFromGUI, subjectTaskFromGUI) != null) {
					request.setAttribute(MESSAGE_ADMIN_TASK_LIST, TASK_UPDATE + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_MESSAGE)));					
				} else {
					request.setAttribute(MESSAGE_ADMIN_TASK_LIST, TASK_NOT_UPDATE_DATA_NOT_CORRECT);
				}				
				break;
				
			case DELETE :
				
				if (taskService.deleteTask(idTaskFromGUI)) {
					request.setAttribute(MESSAGE_ADMIN_TASK_LIST, TASK_DELETE + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_MESSAGE)));
				} else {
					request.setAttribute(MESSAGE_ADMIN_TASK_LIST, TASK_NOT_DELETE);
				}
				break;
			default :
				throw new Exception();
			}
			
		} catch (Exception e) {
			LOGGER.error("Error admin change user");
			return PageManager.ERROR_PAGE;
		}
		
		List<Task> allTask = new ArrayList<>();
		try {
			allTask = taskService.takeAllTask();
		} catch (ServiceException e) {
			LOGGER.error("Error take task list", e);			
		}
		request.getSession().setAttribute(LIST_ALL_TASK, allTask);		
		
		request.setAttribute(ADMINISTRATOR_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE);	//for indicate current tab
		return PageManager.ADMINISTATOR_PAGE;
	}

}
