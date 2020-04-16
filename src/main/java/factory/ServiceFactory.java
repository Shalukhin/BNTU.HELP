package factory;

import service.UserService;
import service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance;
	
	private UserService userService = new UserServiceImpl();
	
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

}
