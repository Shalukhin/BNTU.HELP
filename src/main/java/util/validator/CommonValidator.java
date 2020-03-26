package util.validator;

import java.util.Properties;

public class CommonValidator {
	private final static String DB_PROPERTY_URL = "url";
	private final static String DB_PROPERTY_USER = "user";
	private final static String DB_PROPERTY_PASSWORD = "password";
	
	private final static String POOL_PROPERTY_CAPASITY = "capasity";
	
	public static boolean isValidPropertyDB(Properties dbInfo) {
				
		if (dbInfo == null) {
			return false;
		}
		
		if (dbInfo.getProperty(DB_PROPERTY_USER) == null || dbInfo.getProperty(DB_PROPERTY_PASSWORD) == null || 
				dbInfo.getProperty(DB_PROPERTY_URL) == null) {
			return false;
		}
		
		return true;		
	}
	
	public static boolean isValidPropertyPool(Properties poolInfo) {
		
		if (poolInfo == null) {
			return false;
		}
		
		String capasityStr = poolInfo.getProperty(POOL_PROPERTY_CAPASITY);	
		
		if (capasityStr == null) {
			return false;
		}
		
		int capasity;
		
		try {
			capasity = Integer.valueOf(capasityStr);
		} catch (NumberFormatException e) {
			return false;
		}
		
		if (capasity < 1) {
			return false;
		}
		
		return true;
	}
}
