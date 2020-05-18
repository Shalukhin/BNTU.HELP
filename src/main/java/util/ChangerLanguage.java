package util;

import static command.constant.AttributeNameConstant.LANGUAGE_MANAGER;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import property.LanguageManager;

public class ChangerLanguage {
	
	private static final String RUSSIAN_LANGUAGE = "ru";
	private static final String ENGLISH_LANGUAGE = "en";
	private static final Locale RUSSIAN_LOCALE = new Locale(RUSSIAN_LANGUAGE);
	private static final Locale ENGLISH_LOCALE = new Locale(ENGLISH_LANGUAGE);
	
	public static void change(HttpServletRequest request) {
		
		LanguageManager languageManager = (LanguageManager) request.getSession().getAttribute(LANGUAGE_MANAGER);
		
		switch (languageManager.getCurrentLanguage()) {
		case RUSSIAN_LANGUAGE :
			languageManager.changeResource(ENGLISH_LOCALE);
			break;
		case ENGLISH_LANGUAGE :
			languageManager.changeResource(RUSSIAN_LOCALE);
		}
		request.getSession().setAttribute(LANGUAGE_MANAGER, languageManager);
		
	}

}
