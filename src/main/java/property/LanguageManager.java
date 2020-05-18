package property;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LanguageManager {
	
	INSTANCE;
	
	private ResourceBundle bundle; 
	private final String nameResource = "property.text";
	private LanguageManager() {
		bundle = ResourceBundle.getBundle(nameResource, Locale.getDefault()); 
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
