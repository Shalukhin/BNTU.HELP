package command.impl.post;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import command.CommandPOST;
import command.URLManager;
import command.constant.QueryURLConstant;

import static command.constant.AttributeNameConstant.*;
import property.LanguageManager;

public class ChangeLanguageCommand implements CommandPOST {
	
	private static final String RUSSIAN_LANGUAGE = "ru";
	private static final String ENGLISH_LANGUAGE = "en";
	private static final Locale RUSSIAN_LOCALE = new Locale(RUSSIAN_LANGUAGE);
	private static final Locale ENGLISH_LOCALE = new Locale(ENGLISH_LANGUAGE);


	@Override
	public URLManager execute(HttpServletRequest request) {
		
		LanguageManager languageManager = (LanguageManager) request.getSession().getAttribute(LANGUAGE_MANAGER);
		
		switch (languageManager.getCurrentLanguage()) {
		
			case RUSSIAN_LANGUAGE :
				languageManager.changeResource(ENGLISH_LOCALE);
				break;
				
			case ENGLISH_LANGUAGE :
				languageManager.changeResource(RUSSIAN_LOCALE);
				break;
		}
		request.getSession().setAttribute(LANGUAGE_MANAGER, languageManager);		
		
		return new URLManager(QueryURLConstant.CHANGE_LANGUAGE_QUERY_URL);
	}

}
