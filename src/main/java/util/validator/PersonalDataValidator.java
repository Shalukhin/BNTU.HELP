package util.validator;

import org.apache.commons.lang3.StringUtils;

import entity.PersonalData;

public class PersonalDataValidator {
	
	private static String VALIDATE_NAME_REGEX = "[\\sa-zA-Zа-яА-ЯёЁ0-9_-]{3,40}";
	private static String VALIDATE_PHONE_REGEX = "[+][0-9]{12}";
	private static String VALIDATE_EMAIL_REGEX = "[a-zA-Zа-яА-ЯёЁ0-9_-]{3,20}@[a-zA-Zа-яА-ЯёЁ0-9_-]{3,20}\\.[a-zA-Zа-яА-ЯёЁ0-9_-]{2,5}";
	
	public static boolean validate(PersonalData personalData) {
		if (personalData == null 
				|| personalData.getBonusMoney() == null 
				|| personalData.getId() < 0 || personalData.getIdInvitingUser() < 0
				|| personalData.getBonusMoney().doubleValue() < 0
				|| !validateName(personalData.getName())
				|| !validatePhone(personalData.getPhone())
				|| !validateEmail(personalData.getEmail())) 
		{
			return false;
		}
		
		return true;		
	}
	
	private static boolean validateName(String name) {		
		return StringUtils.isEmpty(name) || name.matches(VALIDATE_NAME_REGEX);
	}
	
	private static boolean validatePhone(String phone) {	
		return StringUtils.isEmpty(phone) || phone.matches(VALIDATE_PHONE_REGEX);
	}
	
	private static boolean validateEmail(String email) {		
		return StringUtils.isEmpty(email) || email.matches(VALIDATE_EMAIL_REGEX);
	}
}
