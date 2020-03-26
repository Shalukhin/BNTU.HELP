package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.UserDAO;
import entity.User;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.LoginService;
import util.validator.UserValidator;

public class LoginServiceImpl implements LoginService {
	
	private final static Logger LOGGER = LogManager.getLogger(LoginServiceImpl.class.getName());
	
	UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

	@Override
	public List<User> takeUserFromDB(User user) throws ServiceException {
		if (!UserValidator.validateByLoginAndPassword(user)) {
			return null;
		}
		
		List<User> result = new ArrayList<User>();
		try {
			result = userDAO.read(user.getLogin(), user.getPassword());
		} catch (DAOException e) {
			LOGGER.error("Error take user from DB", e);
			throw new ServiceException("Error take user from DB", e);
		}
		
		return result;
	}

}
