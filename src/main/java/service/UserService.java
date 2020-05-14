package service;

import java.util.List;

import entity.User;
import exception.ServiceException;

public interface UserService {
	
	boolean isExistUserLogin(String login) throws ServiceException;
	User loginUser(User user) throws ServiceException;
	User registrationUser(User user) throws ServiceException;
	List<User> takeAllUser() throws ServiceException;
	User editUser(String idUserStr, String loginUserStr, String passwordUserStr, String nameRoleStr, String nameStatusStr) throws ServiceException;
	boolean deleteUser(String idStr) throws ServiceException;
}
