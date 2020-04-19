package factory;

import service.PersonalDataService;
import service.UserService;
import service.impl.PersonalDataServiceImpl;
import service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance;
	
	private UserService userService = new UserServiceImpl();
	private PersonalDataService personalDataService = new PersonalDataServiceImpl();
	
	private ServiceFactory() {};
	
	public static ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}	
	
	public UserService getUserService() {
		return userService;
	}
	
	public PersonalDataService getPersonalDataService() {
		return personalDataService;
	}

}
