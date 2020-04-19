package util.validator;

import entity.PersonalData;

public class PersonalDataValidator {
	
	private static String VALIDATE_NAME_REGEX = ".+";
	private static String VALIDATE_PHONE_REGEX = ".+";
	private static String VALIDATE_EMAIL_REGEX = ".+";
	
	public static boolean validate(PersonalData personalData) {
		if (personalData == null 
				|| personalData.getBonusMoney() == null 
				|| personalData.getIdUser() < 0 || personalData.getIdInvitingUser() < 0
				|| !personalData.getName().matches(VALIDATE_NAME_REGEX)
				|| !personalData.getPhone().matches(VALIDATE_PHONE_REGEX)
				|| !personalData.getEmail().matches(VALIDATE_EMAIL_REGEX)
				|| personalData.getBonusMoney().doubleValue() < 0) 
		{
			return false;
		}
		return true;		
	}
}
