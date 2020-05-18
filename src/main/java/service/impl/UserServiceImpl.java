package service.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import builder.PersonalDataBuilder;
import builder.UserBuilder;
import dao.PersonalDataDAO;
import dao.RoleDAO;
import dao.StatusDAO;
import dao.UserDAO;
import entity.PersonalData;
import entity.Role;
import entity.Status;
import entity.User;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.UserService;
import util.validator.PersonalDataValidator;
import util.validator.UserValidator;

public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());

	private static final String NAME_ROLE_USER = "user";
	private static final String NAME_STATUS_STANDART = "standart";

	private UserBuilder userBuilder = new UserBuilder();
	private PersonalDataBuilder personalDataBuilder = new PersonalDataBuilder();
	private PersonalDataDAO personalDataDAO = DAOFactory.getInstance().getPersonalDataDAO();	
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
	public User registrationUser(String login, String password) throws ServiceException {

		User user = userBuilder.createNewUser().withLogin(login).withPassword(password).build();

		if (!UserValidator.validateByLoginAndPassword(user)) {
			LOGGER.error("Error registration user - invalid user");
			return null;
		}

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

	@Override
	public User savePersonalData(String name, String phone, String email, User user) throws ServiceException {

		PersonalData personalData = personalDataBuilder
										.createNewPersonalData()
										.withName(name)
										.withPhone(phone)
										.withEmail(email)
										.build();

		if (!PersonalDataValidator.validate(personalData)) {
			LOGGER.error("Error save personal data - invalid personal data");
			return null;
		}
		
		try {
			if (user.getPersonalData() == null) {
				user.setPersonalData(personalDataDAO.create(personalData));
				
			} else {
				personalData.setId(user.getPersonalData().getId());
				personalData.setBonusMoney(user.getPersonalData().getBonusMoney());
				personalData.setIdInvitingUser(user.getPersonalData().getIdInvitingUser());
				user.setPersonalData(personalDataDAO.update(personalData));				
			}			
			return userDAO.update(user);
		} catch (DAOException e) {
			LOGGER.error("Error save personal data", e);
			throw new ServiceException("Error save personal data", e);
		}
	}

	@Override
	public User editUser(String idUserStr, String loginUserStr, String passwordUserStr, String nameRoleStr,
			String nameStatusStr) throws ServiceException {

		User modifiedUser = buildUser(loginUserStr, passwordUserStr, nameRoleStr, nameStatusStr);

		if (!UserValidator.validate(modifiedUser)) {
			LOGGER.error("Error update user - invalid user");
			return null;
		}

		modifiedUser.setId(Integer.valueOf(idUserStr));

		try {
			return userDAO.update(modifiedUser);
		} catch (DAOException e) {
			LOGGER.error("Error update user", e);
			throw new ServiceException("Error update user", e);
		}
	}

	private User buildUser(String loginUserStr, String passwordUserStr, String RoleStr, String nameStatusStr) {

		Role role = null;
		String[] roleParameter = RoleStr.trim().split(" ");
		try {
			if (roleParameter.length == 1) {
				role = roleDAO.findByName(roleParameter[0]);
			} else {
				role = roleDAO.findByNameAndSubject(roleParameter[0], roleParameter[1]);
			}

		} catch (DAOException e) {
			LOGGER.error("Error build user - invalid role user", e);
			return null;
		}

		Status status = null;
		try {
			status = statusDAO.findByName(nameStatusStr);
		} catch (DAOException e) {
			LOGGER.error("Error build user - invalid status user", e);
			return null;
		}

		User user = userBuilder.createNewUser().withLogin(loginUserStr).withPassword(passwordUserStr).withRole(role)
				.withStatus(status).build();

		return user;
	}

	@Override
	public List<User> takeAllUser() throws ServiceException {

		try {
			return userDAO.findAllUser();
		} catch (DAOException e) {
			LOGGER.error("Error take all user", e);
			throw new ServiceException("Error take all user", e);
		}
	}

	@Override
	public boolean deleteUser(String idStr) throws ServiceException {

		try {
			return userDAO.delete(Integer.valueOf(idStr));
		} catch (Exception e) {
			LOGGER.error("Error delete user", e);
			throw new ServiceException("Error delete user", e);
		}
	}

}
