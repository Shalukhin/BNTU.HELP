package validator;

import org.apache.commons.lang3.StringUtils;

import entity.User;

public class UserValidator {
	
	private static String VALIDATE_LOGIN_REGEX = "^[a-zA-Zа-яА-ЯёЁ][a-zA-Zа-яА-ЯёЁ0-9-_\\.]{1,24}$";
	private static String VALIDATE_PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	
	public static boolean validate(User user) {
		if (user == null || user.getRole() == null || user.getStatus() == null) {
			return false;
		}		
		return validateByLoginAndPassword(user);
	}
	
	public static boolean validateByLoginAndPassword(User user) {
		if (user == null) {
			return false;
		}
		return (valadateLogin(user.getLogin()) && validatePassword(user.getPassword()));
	}
	
	private static boolean valadateLogin(String login) {
		
		return !StringUtils.isBlank(login);
		//return !StringUtils.isBlank(login) && login.matches(VALIDATE_LOGIN_REGEX);
		
	}
	
	private static boolean validatePassword(String password) {
		
		return !StringUtils.isBlank(password);
		//return password.matches(VALIDATE_PASSWORD_REGEX) && !StringUtils.isBlank(password);
		
	}
}
