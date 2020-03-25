package factory;

import service.LoginService;
import service.impl.LoginServiceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance;
	
	private LoginService loginService = new LoginServiceImpl();
	
	private ServiceFactory() {};
	
	public static ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}
	
	public LoginService getLoginService() {
		return loginService;
	}	

}
