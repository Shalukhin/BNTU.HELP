package command.impl;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import command.Command;
import command.PageManager;
import property.LanguageManager;

public class ChangeLanguageCommand implements Command {
	
	private static final String RUSSIAN_LANGUAGE = "ru";
	private static final String ENGLISH_LANGUAGE = "en";
	private static final Locale RUSSIAN_LOCALE = new Locale(RUSSIAN_LANGUAGE);
	private static final Locale ENGLISH_LOCALE = new Locale(ENGLISH_LANGUAGE);

	@Override
	public PageManager execute(HttpServletRequest request) {

		LanguageManager languageManager = (LanguageManager) request.getSession().getAttribute(LANGUAGE_MANAGER);
		switch (languageManager.getCurrentLanguage()) {
		case RUSSIAN_LANGUAGE :
			languageManager.changeResource(ENGLISH_LOCALE);
			break;
		case ENGLISH_LANGUAGE :
			languageManager.changeResource(RUSSIAN_LOCALE);
		}
		request.getSession().setAttribute(LANGUAGE_MANAGER, languageManager);
		
		String lastPage = (String) request.getSession().getAttribute(LAST_PAGE);
		String lastTab = (String) request.getSession().getAttribute(LAST_TAB);
		
		PageManager languagePage = PageManager.LANGUAGE_PAGE;
		languagePage.setUrl(lastPage);		
		
		if (lastTab != null) {
			request.getSession().setAttribute(lastTab, ACTIVE);
		}		
		return languagePage;
	}

}
