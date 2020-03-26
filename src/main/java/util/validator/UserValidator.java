package util.validator;

import entity.User;

public class UserValidator {
	
	private static String VALIDATE_LOGIN_REGEX = "^[a-zA-Zа-яА-ЯёЁ][a-zA-Zа-яА-ЯёЁ0-9-_\\.]{1,24}$";
	private static String VALIDATE_PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	
	public static boolean validate(User user) {
		if (user == null) {
			return false;
		}
		return (valadateLogin(user.getLogin()) && validatePassword(user.getPassword()));
	}
	
	private static boolean valadateLogin(String login) {
		//return login.matches(VALIDATE_LOGIN_REGEX);
		return true;
	}
	
	private static boolean validatePassword(String password) {
		//return password.matches(VALIDATE_PASSWORD_REGEX);
		return true;
	}	

}
