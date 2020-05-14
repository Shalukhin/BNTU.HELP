package factory;

import dao.CourseDAO;
import dao.FinishFileDAO;
import dao.OrderDAO;
import dao.PersonalDataDAO;
import dao.RoleDAO;
import dao.StatusDAO;
import dao.SubjectDAO;
import dao.TaskDAO;
import dao.UserDAO;
import dao.impl.CourseDAOImpl;
import dao.impl.FinishFileDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.PersonalDataDAOImpl;
import dao.impl.RoleDAOImpl;
import dao.impl.StatusDAOImpl;
import dao.impl.SubjectDAOImpl;
import dao.impl.TaskDAOImpl;
import dao.impl.UserDAOImpl;

public class DAOFactory {
	
	private static DAOFactory instance;
	
	private UserDAO userDAO = new UserDAOImpl();
	private RoleDAO roleDAO = new RoleDAOImpl();
	private StatusDAO statusDAO = new StatusDAOImpl();
	private PersonalDataDAO personalDataDAO = new PersonalDataDAOImpl();
	private TaskDAO taskDAO = new TaskDAOImpl();
	private OrderDAO orderDAO = new OrderDAOImpl();
	private SubjectDAO subjectDAO = new SubjectDAOImpl();
	private CourseDAO courseDAO = new CourseDAOImpl();
	private FinishFileDAO finishFileDAO = new FinishFileDAOImpl();
	
	private DAOFactory() {};
	
	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	
	public StatusDAO getStatusDAO() {
		return statusDAO;
	}
	
	public PersonalDataDAO getPersonalDataDAO() {
		return personalDataDAO;
	}
	
	public TaskDAO getTaskDAO() {
		return taskDAO;
	}
	
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}
	
	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}
	
	public CourseDAO getCourseDAO() {
		return courseDAO;
	} 

	public FinishFileDAO getFinishFileDAO() {
		return finishFileDAO;
	}
}
