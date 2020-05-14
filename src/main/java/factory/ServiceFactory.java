package factory;

import service.CourseService;
import service.FinishFileService;
import service.OrderService;
import service.PersonalDataService;
import service.RoleService;
import service.StatusService;
import service.SubjectService;
import service.TaskService;
import service.UserService;
import service.impl.CourseServiceImpl;
import service.impl.FinishFileServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.PersonalDataServiceImpl;
import service.impl.RoleServiceImpl;
import service.impl.StatusServiceImpl;
import service.impl.SubjectServiceImpl;
import service.impl.TaskServiceImpl;
import service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance;
	
	private UserService userService = new UserServiceImpl();
	private RoleService roleService = new RoleServiceImpl();
	private StatusService statusService = new StatusServiceImpl();
	private PersonalDataService personalDataService = new PersonalDataServiceImpl();
	private TaskService taskService = new TaskServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	private SubjectService subjectService = new SubjectServiceImpl();
	private CourseService courseService = new CourseServiceImpl();
	private FinishFileService finishFileService = new FinishFileServiceImpl();	
	
	private ServiceFactory() {};
	
	public static ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}	
	
	public UserService getUserService() {
		return userService;
	}
	
	public RoleService getRoleService() {
		return roleService;
	}
	
	public StatusService getStatusService() {
		return statusService;
	}
	
	public PersonalDataService getPersonalDataService() {
		return personalDataService;
	}
	
	public TaskService getTaskService() {
		return taskService;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}
	
	public SubjectService getSubjectService() {
		return subjectService;
	}
	
	public CourseService getCourseService() {
		return courseService;
	}	
	
	public FinishFileService getFinishFileService() {
		return finishFileService;
	}
}
