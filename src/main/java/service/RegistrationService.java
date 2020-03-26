package service;

import entity.User;
import exception.ServiceException;

public interface RegistrationService {
	
	User addNewUserInDB(User user) throws ServiceException;

}
