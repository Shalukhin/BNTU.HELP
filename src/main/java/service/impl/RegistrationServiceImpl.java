package service.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.RoleDAO;
import dao.StatusDAO;
import dao.UserDAO;
import entity.User;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.LoginService;
import service.RegistrationService;
import util.validator.UserValidator;

public class RegistrationServiceImpl implements RegistrationService {

	private final static Logger LOGGER = LogManager.getLogger(RegistrationServiceImpl.class.getName());

	private UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	private RoleDAO roleDAO = DAOFactory.getInstance().getRoleDAO();
	private StatusDAO statusDAO = DAOFactory.getInstance().getStatusDAO();
	private LoginService loginService = new LoginServiceImpl();
	
	private static int ID_ROLE_USER = 2;
	private static int ID_STATUS_STANDART = 2;
	

	@Override
	public User addNewUserInDB(User user) throws ServiceException {		
		if (!UserValidator.validateByLoginAndPassword(user)) {
			return null;
		}
		
		try {
			user.setRole(roleDAO.read(ID_ROLE_USER));
			user.setStatus(statusDAO.read(ID_STATUS_STANDART));
		} catch (DAOException e) {
			LOGGER.error("Error create new user: error setup role or status", e);
			throw new ServiceException("Error create new user: error setup role or status", e);
		}
		
		if (!UserValidator.validate(user)) {
			return null;
		}
		
		List<User> userExistList = loginService.takeUserFromDB(user);
		if (userExistList.size() != 0) {
			return null;
		}
		
		try {
			return (userDAO.create(user) ? user : null);
		} catch (DAOException e) {
			LOGGER.error("Error create new user", e);
			throw new ServiceException("Error create new user", e);
		}	
	}

}
