package command.impl.get;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;

import javax.servlet.http.HttpServletRequest;

import command.CommandGET;
import command.PageManager;

public class AboutCommand implements CommandGET {

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		request.setAttribute(ABOUT_MAIN_MENU_POSITION, ACTIVE);		//for indicate main menu current item
		return PageManager.ABOUT_PAGE;
	}

}
