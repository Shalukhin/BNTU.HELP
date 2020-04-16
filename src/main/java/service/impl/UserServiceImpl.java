package service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.RoleDAO;
import dao.StatusDAO;
import dao.UserDAO;
import entity.User;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.UserService;
import util.validator.UserValidator;

public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());
	
	private static final String NAME_ROLE_USER = "user";
	private static final String NAME_STATUS_STANDART = "standart";
	
	private UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	private RoleDAO roleDAO = DAOFactory.getInstance().getRoleDAO();
	private StatusDAO statusDAO = DAOFactory.getInstance().getStatusDAO();
	
	

	@Override
	public boolean isExistUserLogin(String login) throws ServiceException {
		
		try {
			return userDAO.checkExistLogin(login);
		} catch (DAOException e) {
			LOGGER.error("Error check login exist", e);
			throw new ServiceException("Error check login exist", e);
		}
	}

	@Override
	public User loginUser(User user) throws ServiceException {
		if (!UserValidator.validateByLoginAndPassword(user)) {
			return null;
		}		
		
		try {
			return userDAO.findByLoginAndPassword(user.getLogin(), user.getPassword());
		} catch (DAOException e) {
			LOGGER.error("Error login user", e);
			throw new ServiceException("Error login user", e);
		}		
	}

	@Override
	public User registrationUser(User user) throws ServiceException {
		if (!UserValidator.validateByLoginAndPassword(user)) {
			LOGGER.error("Error registration user - invalid user");
			return null;
		}
		
//		if (!UserValidator.validate(user)) {
//			throw new ServiceException("Error registration user - invalid user");
//		}
		
		try {
			if (userDAO.checkExistLogin(user.getLogin())) {
				LOGGER.error("Error registration user - login is exist");
				return null;
			}
			
			user.setRole(roleDAO.findByName(NAME_ROLE_USER));
			user.setStatus(statusDAO.findByName(NAME_STATUS_STANDART));
			
			return userDAO.create(user);
			
		} catch (DAOException e) {
			LOGGER.error("Error registration user", e);
			throw new ServiceException("Error registration user", e);
		}	
	}
	
	


}
