package factory;

import service.LoginService;
import service.RegistrationService;
import service.impl.LoginServiceImpl;
import service.impl.RegistrationServiceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance;
	
	private LoginService loginService = new LoginServiceImpl();
	private RegistrationService registrationService = new RegistrationServiceImpl();
	
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
	
	public RegistrationService getRegistrationService() {
		return registrationService;
	}

}
