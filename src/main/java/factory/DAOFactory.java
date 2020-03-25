package factory;

import dao.RoleDAO;
import dao.StatusDAO;
import dao.UserDAO;
import dao.impl.RoleDAOImpl;
import dao.impl.StatusDAOImpl;
import dao.impl.UserDAOImpl;

public class DAOFactory {
	
	private static DAOFactory instance;
	
	private UserDAO userDAO = new UserDAOImpl();
	private RoleDAO roleDAO = new RoleDAOImpl();
	private StatusDAO statusDAO = new StatusDAOImpl();
	
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

}
