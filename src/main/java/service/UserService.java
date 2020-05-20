package service;

import java.util.List;

import entity.User;
import exception.ServiceException;

public interface UserService {
	
	boolean isExistUserLogin(String login) throws ServiceException;
	User loginUser(String login, String password) throws ServiceException;
	User registrationUser(String login, String password) throws ServiceException;
	User savePersonalData (String name, String phone, String email, User user) throws ServiceException;
	User refreshUser(int id) throws ServiceException;
	List<User> takeAllUser() throws ServiceException;
	User editUser(String idUserStr, String loginUserStr, String passwordUserStr, String nameRoleStr, String nameStatusStr, String idPersonalDataStr) throws ServiceException;
	boolean deleteUser(String idStr) throws ServiceException;
}
