package builder;

import javax.servlet.http.HttpServletRequest;

import entity.User;
import util.Parser;

public class UserBuilder {
	
	public static String PARAMETER_LOGIN = "login";
	public static String PARAMETER_PASSWORD = "password";
	
	public static User getUser(HttpServletRequest request) {
		User user = new User();
		user.setLogin(Parser.getStringParameterByName(request, PARAMETER_LOGIN));
		user.setPassword(Parser.getStringParameterByName(request, PARAMETER_PASSWORD));
		return user;
	}

}
