package command.impl.get;

import javax.servlet.http.HttpServletRequest;
import command.CommandGET;
import command.PageManager;
import property.LanguageManager;
import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;

public class IndexCommand implements CommandGET{

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		Object bundle = request.getSession().getAttribute(LANGUAGE_MANAGER);
		if (bundle == null) {
			request.getSession().setAttribute(LANGUAGE_MANAGER, LanguageManager.INSTANCE);
		}
		request.setAttribute(INDEX_MAIN_MENU_POSITION, ACTIVE);		//for indicate main menu current item
		return PageManager.INDEX_PAGE;
	}

}
