package property;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LanguageManager {
	
	INSTANCE;
	
	private final String RUSSIAN_LANGUAGE = "ru";
	private final Locale RUSSIAN_LOCALE = new Locale(RUSSIAN_LANGUAGE);
	private ResourceBundle bundle; 
	private final String nameResource = "property.text";
	private LanguageManager() {
//		bundle = ResourceBundle.getBundle(nameResource, Locale.getDefault());
		bundle = ResourceBundle.getBundle(nameResource, RUSSIAN_LOCALE);
	}
	public void changeResource(Locale locale) {
		bundle = ResourceBundle.getBundle(nameResource, locale); 
	}
	
	public String getString(String key) {
		return bundle.getString(key);
	}	
	
	public String getCurrentLanguage() {
		return bundle.getLocale().getLanguage();
	}

}
