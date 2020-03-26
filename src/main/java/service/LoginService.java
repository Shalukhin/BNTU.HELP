package service;

import java.util.List;

import entity.User;
import exception.ServiceException;

public interface LoginService {
	
	List<User> takeUserFromDB (User user) throws ServiceException;
	

}
