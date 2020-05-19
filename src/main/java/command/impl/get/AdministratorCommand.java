package command.impl.get;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.CommandGET;
import command.PageManager;
import entity.Course;
import entity.Role;
import entity.Status;
import entity.Subject;
import entity.Task;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.CourseService;
import service.RoleService;
import service.StatusService;
import service.SubjectService;
import service.TaskService;
import service.UserService;
import util.Parser;

public class AdministratorCommand implements CommandGET {

	private static final Logger LOGGER = LogManager.getLogger(AdministratorCommand.class.getName());

	private static final String ADMINISTRATOR_ROLE = "admin";

	private UserService userService = ServiceFactory.getInstance().getUserService();
	private TaskService taskService = ServiceFactory.getInstance().getTaskService();
	private RoleService roleService = ServiceFactory.getInstance().getRoleService();
	private StatusService statusService = ServiceFactory.getInstance().getStatusService();
	private CourseService courseService = ServiceFactory.getInstance().getCourseService();
	private SubjectService subjectService = ServiceFactory.getInstance().getSubjectService();

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
		
		try {
			
			List<Task> allTask = taskService.takeAllTask();			
			request.getSession().setAttribute(LIST_ALL_TASK, allTask);
			
			List<User> allUser = userService.takeAllUser();
			request.getSession().setAttribute(LIST_ALL_USER, allUser);
			
			List<Role> allRole = roleService.takeAllRole();
			request.getSession().setAttribute(LIST_ALL_ROLE, allRole);
			
			List<Status> allStatus = statusService.takeAllStatus();
			request.getSession().setAttribute(LIST_ALL_STATUS, allStatus);
			
			List<Course> allCourse = courseService.takeAllCourse();			
			request.getSession().setAttribute(LIST_ALL_COURSE, allCourse);
			
			List<Subject> allSubject = subjectService.takeAllSubject();			
			request.getSession().setAttribute(LIST_ALL_SUBJECT, allSubject);
			
			
		} catch (ServiceException e) {
			LOGGER.error("Error admin control", e);			
		}

		request.setAttribute(ADMINISTRATOR_MAIN_MENU_POSITION, ACTIVE);
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE); // for indicate current tab
		return PageManager.ADMINISTATOR_PAGE;
	}

}
