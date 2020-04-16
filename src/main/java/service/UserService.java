package service;

import entity.User;
import exception.ServiceException;

public interface UserService {
	
	boolean isExistUserLogin(String login) throws ServiceException;
	User loginUser(User user) throws ServiceException;
	User registrationUser(User user) throws ServiceException;
	

}
