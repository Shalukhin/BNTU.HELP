package service;

import java.util.List;
import entity.User;
import exception.ServiceException;

/**
 * @author Vasiliy
 * @version 1.0
 */

public interface UserService {
	/**
	 * Check if this login exists
	 * @param login
	 * @return true - exists; false - does not exist
	 * @throws ServiceException
	 */
	boolean isExistUserLogin(String login) throws ServiceException;
	
	/**
	 * Authorizes user
	 * @param login
	 * @param password
	 * @return successful authorization - return this User, not successful - return null
	 * @throws ServiceException
	 */
	User loginUser(String login, String password) throws ServiceException;
	User registrationUser(String login, String password) throws ServiceException;
	User savePersonalData (String name, String phone, String email, User user) throws ServiceException;
	User refreshUser(int id) throws ServiceException;
	List<User> takeAllUser() throws ServiceException;
	User editUser(String idUserStr, String loginUserStr, String passwordUserStr, String nameRoleStr, String nameStatusStr, String idPersonalDataStr) throws ServiceException;
	boolean deleteUser(String idStr) throws ServiceException;
}
