package command.impl.administrator;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.Command;
import command.PageManager;
import entity.Course;
import entity.Role;
import entity.Status;
import entity.Subject;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.CourseService;
import service.RoleService;
import service.StatusService;
import service.SubjectService;
import service.UserService;
import util.Parser;

public class AdministratorCommand implements Command {

	private static final Logger LOGGER = LogManager.getLogger(AdministratorCommand.class.getName());

	private static final String ADMINISTRATOR_ROLE = "admin";

	private UserService userService = ServiceFactory.getInstance().getUserService();
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

		List<User> allUser = new ArrayList<>();
		try {
			allUser = userService.takeAllUser();
		} catch (ServiceException e) {
			LOGGER.error("Error admin all user", e);
			return PageManager.ERROR_PAGE;
		}
		request.getSession().setAttribute(LIST_ALL_USER, allUser);

		List<Role> allRole = new ArrayList<>();
		try {
			allRole = roleService.takeAllRole();
		} catch (ServiceException e) {
			LOGGER.error("Error admin all role", e);
			return PageManager.ERROR_PAGE;
		}
		request.getSession().setAttribute(LIST_ALL_ROLE, allRole);

		List<Status> allStatus = new ArrayList<>();
		try {
			allStatus = statusService.takeAllStatus();
		} catch (ServiceException e) {
			LOGGER.error("Error admin all status", e);
			return PageManager.ERROR_PAGE;
		}
		request.getSession().setAttribute(LIST_ALL_STATUS, allStatus);

		List<Course> allCourse = new ArrayList<>();
		try {
			allCourse = courseService.takeAllCourse();
		} catch (ServiceException e) {
			LOGGER.error("Error admin all course", e);
			return PageManager.ERROR_PAGE;
		}
		request.getSession().setAttribute(LIST_ALL_COURSE, allCourse);

		List<Subject> allSubject = new ArrayList<>();
		try {
			allSubject = subjectService.takeAllSubject();
		} catch (ServiceException e) {
			LOGGER.error("Error admin all subject", e);
			return PageManager.ERROR_PAGE;
		}
		request.getSession().setAttribute(LIST_ALL_SUBJECT, allSubject);

		request.setAttribute(ADMINISTRATOR_MAIN_MENU_POSITION, ACTIVE);
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE); // for indicate current tab
		return PageManager.ADMINISTATOR_PAGE;
	}

}
